package com.example.notificationdemorecord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.cancelButton).setEnabled(false);
        final NotificationCompat.Builder builder=
                new NotificationCompat.Builder(this,"id")
                .setSmallIcon(R.drawable.ic_bluetooth_audio_black_24dp)
                .setContentTitle("Title")
                .setContentText("Custom text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                .bigText("This is a paragraph...... \n add as much as you want... will appear\n\n\n\n........"));
        createNotificationChannel();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManagerCompat notificationManager=NotificationManagerCompat.from(MainActivity.this);

                notificationManager.notify(999,builder.build());

                findViewById(R.id.button).setEnabled(false);
                findViewById(R.id.cancelButton).setEnabled(true);
            }
        });
        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManagerCompat notificationManager=NotificationManagerCompat.from(MainActivity.this);
                notificationManager.cancel(999);

                findViewById(R.id.button).setEnabled(true);
                findViewById(R.id.cancelButton).setEnabled(false);

            }
        });

            }

    void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel("id","name",NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("This is a channel description");

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

}

