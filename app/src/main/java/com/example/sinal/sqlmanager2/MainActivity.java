package com.example.sinal.sqlmanager2;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Connection con = new Connection("sinan",3306, "sinan", "secret", "sinan");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView tv = (TextView)findViewById(R.id.databasetxt);
        final TextView messagetext = (TextView)findViewById(R.id.messagetxt);

        final Button connectionBtn = (Button)findViewById(R.id.connectbtn);
        connectionBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                messagetext.setText("Connection test...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    messagetext.setText(e.getMessage());

                }
                messagetext.setText(con.connectionTest());

            }
        });

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
