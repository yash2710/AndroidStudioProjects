package com.example.app3;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    RadioGroup rdg;
    CheckBox chk;
    ListView l;
    EditText editText;

    String[] listItems ={"Option 1", "Option 2", "Option 3"};
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rdg = (RadioGroup) findViewById(R.id.grp);
        chk = (CheckBox) findViewById(R.id.chk);
        l = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this,R.layout.list,listItems);
        editText = (EditText) findViewById(R.id.ed);
        l.setAdapter(adapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) adapter.getItem(position);
                Toast.makeText(MainActivity.this,value,Toast.LENGTH_LONG).show();
            }
        });

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rd = (RadioButton) findViewById(rdg.getCheckedRadioButtonId());
                String s;
                s ="Checkbox : ";
                s+=chk.isChecked();
                s +="\nRadio : ";
                s +=rd.getText();
                s +="\nEntered Text: ";
                s +=editText.getText();
                Toast.makeText(MainActivity.this, s,Toast.LENGTH_LONG).show();
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
            Toast.makeText(this,"This is toast",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
