package com.example.andre.telecomando;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    ImageButton btnSx, btnDx, btnGas, btnFreno, btnSpegni, btnHelp;
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
        btnSpegni.setOnTouchListener(this);

        btnHelp = findViewById(R.id.btnHelp);
        btnHelp.setOnTouchListener(this);

        btnSx = findViewById(R.id.btnSinistra);
        btnSx.setOnTouchListener(this);

        btnDx = findViewById(R.id.btnDestra);
        btnDx.setOnTouchListener(this);

        btnGas = findViewById(R.id.btnGas);
        btnGas.setOnTouchListener(this);

        btnFreno = findViewById(R.id.btnFreno);
        btnFreno.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {
            case R.id.btnSinistra:
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.i("direzione", "sinistra");

                    float x = (float) 0.85;
                    float y = (float) 0.85;

                    btnSx.setScaleX(x);
                    btnSx.setScaleY(y);

                    new Thread(new ClientThread(1)).start();

                } else if(event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i("direzione", "stop");

                    float x = 1;
                    float y = 1;

                    btnSx.setScaleX(x);
                    btnSx.setScaleY(y);

                    v.performClick();
                    new Thread(new ClientThread(2)).start();

                }
                break;

            case R.id.btnDestra:
                if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    Log.i("direzione", "destra");

                    float x = (float) 0.85;
                    float y = (float) 0.85;

                    btnDx.setScaleX(x);
                    btnDx.setScaleY(y);

                    new Thread(new ClientThread(3)).start();

                } else if(event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i("direzione", "stop");

                    float x = 1;
                    float y = 1;

                    btnDx.setScaleX(x);
                    btnDx.setScaleY(y);

                    v.performClick();
                    new Thread(new ClientThread(4)).start();
                }
                break;

            case R.id.btnGas:
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.i("direzione", "gas");

                    float x = (float) 0.85;
                    float y = (float) 0.85;

                    btnGas.setScaleX(x);
                    btnGas.setScaleY(y);

                    new Thread(new ClientThread(5)).start();

                } else if(event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i("direzione", "stop");

                    float x = 1;
                    float y = 1;

                    btnGas.setScaleX(x);
                    btnGas.setScaleY(y);

                    v.performClick();
                    new Thread(new ClientThread(6)).start();
                }
                break;

            case R.id.btnFreno:
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.i("direzione", "freno");

                    float x = (float) 0.85;
                    float y = (float) 0.85;

                    btnFreno.setScaleX(x);
                    btnFreno.setScaleY(y);

                    new Thread(new ClientThread(7)).start();

                } else if(event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i("direzione", "stop");

                    float x = 1;
                    float y = 1;

                    btnFreno.setScaleX(x);
                    btnFreno.setScaleY(y);

                    v.performClick();
                    new Thread(new ClientThread(8)).start();
                }
                break;

            case R.id.btnSpegni:
                if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    float x = (float) 0.85;
                    float y = (float) 0.85;

                    btnSpegni.setScaleX(x);
                    btnSpegni.setScaleY(y);

                } else if(event.getAction() == MotionEvent.ACTION_UP) {

                    float x = 1;
                    float y = 1;

                    btnSpegni.setScaleX(x);
                    btnSpegni.setScaleY(y);

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Sei sicuro di voler spegnere?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    new Thread(new ClientThread(0)).start();
                                    finish();
                                }
                            });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {}
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                break;
            case R.id.btnHelp:
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    float x = (float) 0.85;
                    float y = (float) 0.85;

                    btnHelp.setScaleX(x);
                    btnHelp.setScaleY(y);

                    //Cambio activity

                } else if(event.getAction() == MotionEvent.ACTION_UP) {

                    float x = 1;
                    float y = 1;

                    btnHelp.setScaleX(x);
                    btnHelp.setScaleY(y);

                    v.performClick();
                }
                break;
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
