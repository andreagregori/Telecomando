package com.example.andre.telecomando;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import java.net.*;
import java.io.*;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{
    ImageButton btnSx, btndx, btnGas, btnFreno, btnSpegni;
    private Socket socket;
    private static final int SERVERPORT = 1999;
    private static final String SERVER_IP = "192.168.42.1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ciclo di vita", "onCreate");
        super.onCreate(savedInstanceState);                                                 //Caricamento grafica
        setContentView(R.layout.activity_main);                                             //Visualizzazione grafica
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);            //Per bloccare la activity in landscape (orizzontale)

        btnSpegni = findViewById(R.id.btnSpegni);
        btnSpegni.setOnClickListener(this);

        btnSx = findViewById(R.id.btnSinistra);
        btnSx.setOnTouchListener(this);

        btndx = findViewById(R.id.btnDestra);
        btndx.setOnTouchListener(this);

        btnGas = findViewById(R.id.btnGas);
        btnGas.setOnTouchListener(this);

        btnFreno = findViewById(R.id.btnFreno);
        btnFreno.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==btnSpegni.getId()){
            new Thread(new ClientThread(0)).start();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId()==btnSx.getId()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.i("direzione", "sinistra");
                    new Thread(new ClientThread(1)).start();
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("direzione", "stop");
                    v.performClick();
                    new Thread(new ClientThread(2)).start();
                    break;
                default:
                    break;
            }
        }

        if (v.getId()==btndx.getId()){
            Log.i("direzione", "destra");
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.i("direzione", "destra");
                    new Thread(new ClientThread(3)).start();
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("direzione", "stop");
                    v.performClick();
                    new Thread(new ClientThread(4)).start();
                    break;
                default:
                    break;
            }
        }

        if (v.getId()==btnGas.getId()){
            Log.i("direzione", "gas");
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.i("direzione", "gas");
                    new Thread(new ClientThread(5)).start();
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("direzione", "stop");
                    v.performClick();
                    new Thread(new ClientThread(6)).start();
                    break;
                default:
                    break;
            }
        }

        if (v.getId()==btnFreno.getId()){
            Log.i("direzione", "freno");
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.i("direzione", "freno");
                    new Thread(new ClientThread(7)).start();
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("direzione", "stop");
                    v.performClick();
                    new Thread(new ClientThread(8)).start();
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    @Override
    protected void onPause() {
        Log.i("ciclo di vita", "onPause");
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.i("ciclo di vita", "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i("ciclo di vita", "onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i("ciclo di vita", "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i("ciclo di vita", "onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i("ciclo di vita", "onDestroy");
        super.onDestroy();
    }

    class ClientThread implements Runnable {
        private int messaggio;

        public ClientThread(int messaggio){
            this.messaggio=messaggio;
        }

        @Override
        public void run() {
            try {
                InetAddress serverIP = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverIP, SERVERPORT);
                Log.i("connessione","connessione");
                socket.getOutputStream().write(messaggio);
                Log.i("connessione","invio");
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

}
