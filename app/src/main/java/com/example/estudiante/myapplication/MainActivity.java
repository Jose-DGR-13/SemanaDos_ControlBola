package com.example.estudiante.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Button up;
    Button down;
    Button left;
    Button right;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        up = (Button) findViewById(R.id.up);
        down = (Button) findViewById(R.id.down);
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);
        reset = (Button) findViewById(R.id.reset);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Conexion().execute("up");
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Conexion().execute("down");
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Conexion().execute("left");
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Conexion().execute("right");
            }
        });


    }

    public class Conexion extends AsyncTask<String, Integer, String> {

        InetAddress dirAddres;
        DatagramSocket socket;
        DatagramPacket packet;
        int destPort = 6000;
        String comando = null;

        @Override
        protected String doInBackground(String... params) {
            try {
                dirAddres = InetAddress.getByName("192.168.112.16");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {
                socket = new DatagramSocket(destPort);
            } catch (SocketException e) {
                e.printStackTrace();
            }
            comando = params[0];
            byte[] data = comando.getBytes();
            packet = new DatagramPacket(data, data.length, dirAddres, destPort);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Mensaje no enviado";
        }
    }
}
