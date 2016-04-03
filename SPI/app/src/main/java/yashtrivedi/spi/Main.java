package yashtrivedi.spi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseObject;

/**
 * Created by Yash on 13-Jun-15.
 */
public class Main extends AppCompatActivity {
    Spinner dc, co, ds, psna, eco, mp, sem, cpw, cs;
    String[] grades = {"A+", "A", "B+", "B", "C+", "C", "D"};
    String mdc, mco, mds, mpsna, meco, mmp, msem, mcpw, mcs;
    int pdc, pco, pds, ppsna, peco, pmp, psem, pcpw, pcs;
    float ptr;
    Button submit;
    EditText roll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, grades);
        dc = (Spinner) findViewById(R.id.dc);
        dc.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_item, this));
        co = (Spinner) findViewById(R.id.co);
        co.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_item, this));
        ds = (Spinner) findViewById(R.id.ds);
        ds.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_item, this));
        psna = (Spinner) findViewById(R.id.psna);
        psna.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_item, this));
        eco = (Spinner) findViewById(R.id.eco);
        eco.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_item, this));
        mp = (Spinner) findViewById(R.id.mp);
        mp.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_item, this));
        sem = (Spinner) findViewById(R.id.sem);
        sem.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_item, this));
        cpw = (Spinner) findViewById(R.id.cpw);
        cpw.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_item, this));
        cs = (Spinner) findViewById(R.id.cs);
        cs.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_item, this));

        roll = (EditText) findViewById(R.id.roll);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rol = roll.getText().toString();

                if (dc.getSelectedItemPosition() != 0 && co.getSelectedItemPosition() != 0 && ds.getSelectedItemPosition() != 0 && psna.getSelectedItemPosition() != 0 && eco.getSelectedItemPosition() != 0 && mp.getSelectedItemPosition() != 0 && sem.getSelectedItemPosition() != 0 && cpw.getSelectedItemPosition() != 0 && cs.getSelectedItemPosition() != 0) {
                    if (!rol.equals("")) {
                        mdc = grades[dc.getSelectedItemPosition() - 1];
                        mco = grades[co.getSelectedItemPosition() - 1];
                        mds = grades[ds.getSelectedItemPosition() - 1];
                        mpsna = grades[psna.getSelectedItemPosition() - 1];
                        meco = grades[eco.getSelectedItemPosition() - 1];
                        mmp = grades[mp.getSelectedItemPosition() - 1];
                        msem = grades[sem.getSelectedItemPosition() - 1];
                        mcpw = grades[cpw.getSelectedItemPosition() - 1];
                        mcs = grades[cs.getSelectedItemPosition() - 1];

                        pdc = getPointers(mdc);
                        pco = getPointers(mco);
                        pds = getPointers(mds);
                        ppsna = getPointers(mpsna);
                        peco = getPointers(meco);
                        pmp = getPointers(mmp);
                        psem = getPointers(msem);
                        pcpw = getPointers(mcpw);
                        pcs = getPointers(mcs);

                        ptr = (4 * pdc + 5 * pco + 5 * pds + 5 * ppsna + 2 * peco + pmp + psem + pcpw) / 24.0f;

                        ParseObject parseObject = new ParseObject("marks");
                        parseObject.put("Roll_No", "13BCE" + rol);
                        parseObject.put("DC", mdc);
                        parseObject.put("CO", mco);
                        parseObject.put("DS", mds);
                        parseObject.put("PSNA", mpsna);
                        parseObject.put("ECO", meco);
                        parseObject.put("MP", mmp);
                        parseObject.put("SEM", msem);
                        parseObject.put("CPW", mcpw);
                        parseObject.put("CS", mcs);
                        parseObject.put("ptr",ptr);
                        parseObject.saveInBackground();

                        AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
                        builder.setMessage("Your Pointers are " + ptr)
                                .setTitle("")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    } else {
                        Toast.makeText(Main.this, "Enter Your Roll No.", Toast.LENGTH_LONG).show();
                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
                    builder.setMessage("Please select all grades")
                            .setTitle("Error")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    static int getPointers(String grades) {
        if (grades.equals("A+"))
            return 10;
        else if (grades.equals("A"))
            return 9;
        else if (grades.equals("B+"))
            return 8;
        else if (grades.equals("B"))
            return 7;
        else if (grades.equals("C+"))
            return 6;
        else if (grades.equals("C"))
            return 5;
        else if (grades.equals("D"))
            return 4;
        else
            return 0;
    }
}
