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


public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    ImageButton btnSx, btndx, btnGas, btnFreno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                                                 //Caricamento grafica
        setContentView(R.layout.activity_main);                                             //Visualizzazione grafica
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);            //Per bloccare la activity in landscape (orizzontale)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSx = findViewById(R.id.btnSinistra);
        btnSx.setOnTouchListener(this);
      /*  btnSx.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("direzione", "sinistra");
                return false;
            }
        });*/

        btndx = findViewById(R.id.btnDestra);
        btndx.setOnTouchListener(this);
      /*  btndx.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent evt) {
                switch (evt.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("direzione", "destra");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("direzione", "stop");
                        view.performClick();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });*/
        btnGas = findViewById(R.id.btnGas);
        btnGas.setOnTouchListener(this);

        btnFreno = findViewById(R.id.btnFreno);
        btnFreno.setOnTouchListener(this);

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId()==btnSx.getId()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.i("direzione", "sinistra");
                    String sentence;
                    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                    Socket s;
                    try {
                        InetAddress ip = InetAddress.getByName("192.168.42.1"); //192.168.42.1
                        s = new Socket(ip, 1999);
                        s.getOutputStream().write(1);
                        s.close();
                    } catch (UnknownHostException e1) {
                        System.out.println("Host not found");
                        e1.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }catch (NetworkOnMainThreadException eN){
                        Log.i("connessione","errore main");
                        eN.printStackTrace();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("direzione", "stop");
                    v.performClick();
                    break;
                default:
                    break;
            }
        }

        if (v.getId()==btndx.getId()){
            Log.i("direzione", "destra");
        }

        if (v.getId()==btnGas.getId()){
            Log.i("direzione", "gas");
        }

        if (v.getId()==btnFreno.getId()){
            Log.i("direzione", "freno");
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
}
