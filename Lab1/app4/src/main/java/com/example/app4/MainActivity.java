package com.example.app4;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    DBHelper mydb;
    Button login, reg;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);

        login = (Button) findViewById(R.id.login);
        reg = (Button) findViewById(R.id.reg);

        user = (EditText) findViewById(R.id.uname);
        pass = (EditText) findViewById(R.id.pass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList list = mydb.getAll();
                Cursor res = mydb.getData(user.getText().toString(), pass.getText().toString());
//                res.moveToFirst();
                if (res.getCount() >= 1)
                    Toast.makeText(MainActivity.this, "Welcome, " + res.getString(0), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Wrong Username/Password", Toast.LENGTH_SHORT).show();
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getData(user.getText().toString(), pass.getText().toString());
//                res.moveToFirst();
                if (res.getCount() == 0)
                    if (mydb.insert(user.getText().toString(), pass.getText().toString()))
                        Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Unable to register", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"User already exists",Toast.LENGTH_SHORT).show();
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
