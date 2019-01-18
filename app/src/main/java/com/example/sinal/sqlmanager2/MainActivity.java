package com.example.sinal.sqlmanager2;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.os.StrictMode;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.content.Context.*;

public class MainActivity extends AppCompatActivity {
    //Global variable for Connection
    private Connection sqlConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize variables
        final TextView serverAddress = (TextView)findViewById(R.id.servertext);
        final TextView tv = (TextView)findViewById(R.id.databasetxt);
        final TextView messagetext = (TextView)findViewById(R.id.messagetxt);
        final TextView portText = (TextView)findViewById(R.id.porttxt);
        final TextView pwtext = (TextView)findViewById(R.id.passwordtxt);
        final TextView dbtxt = (TextView)findViewById(R.id.databasetxt);
        final TextView usertxt = (TextView)findViewById(R.id.usernametxt);

        //Create connection object



        //Start connection when button is clicked
        final Button connectionBtn = (Button)findViewById(R.id.connectbtn);
        connectionBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                try {
                    final Connection con = new Connection(usertxt.getText().toString(),
                            Integer.valueOf(portText.getText().toString()), pwtext.getText().toString(),
                            serverAddress.getText().toString(), dbtxt.getText().toString());
                    messagetext.setText(con.connectionTest());
                    sqlConnection = con;

                }catch (Exception ex){
                    messagetext.setText("Wrong format or empty field" + "   "+ex.getMessage() );
                }

            }
        });

        //Loginbutton
        final Button loginButton = (Button)findViewById(R.id.loginbtn);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Open next window
            }
        });

    }

    public String save(Connection connection){
        FileOutputStream fs = null;
        ObjectOutputStream os = null;
        try {
            fs = openFileOutput("ser.xml", Context.MODE_PRIVATE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  e.getMessage() + "@openFileOutput()";
        }
        try {
            os = new ObjectOutputStream(fs);
            os.writeObject(connection);
            os.close();
            fs.close();
            return "file Saved";
        }catch (IOException e){
            e.printStackTrace();
            return e.getMessage() + "@ObjectOutputStream";
        }



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
}
