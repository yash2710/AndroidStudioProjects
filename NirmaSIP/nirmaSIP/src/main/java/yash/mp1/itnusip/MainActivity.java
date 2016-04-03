package yash.mp1.itnusip;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import org.doubango.ngn.NgnEngine;
import org.doubango.ngn.services.INgnConfigurationService;
import org.doubango.ngn.services.INgnSipService;
import org.doubango.ngn.utils.NgnConfigurationEntry;

import java.util.ArrayList;
import java.util.List;

import yash.mp1.itnusip.adapter.RecyclerViewAdapter;
import yash.mp1.itnusip.model.Contact;

public class MainActivity extends AppCompatActivity implements ClickListener {

    private static NgnEngine mEngine;
    private INgnSipService mSipService;
    SharedPreferences sharedPreferences;
    INgnConfigurationService mConfigurationService;


    EditText contactSearch;
    RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private List<Contact> contacts = new ArrayList<Contact>();

    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String username = sharedPreferences.getString("namePref", "");
        String domain = sharedPreferences.getString("domainPref", "");
        String password = sharedPreferences.getString("passPref", "");

        if (username.length() == 0 || domain.length() == 0 || password.length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please update SIP Settings")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MainActivity.this, SipSettings.class));
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        contactSearch = (EditText) findViewById(R.id.contact_search);
        contactSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                contacts=mydb.getAll(contactSearch.getText().toString());
                adapter.setContactList(contacts);
                mRecyclerView.swapAdapter(adapter,false);
            }
        });
        adapter = new RecyclerViewAdapter(this);
        contactSearch = (EditText) findViewById(R.id.contact_search);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, this));
        mydb = new DBHelper(this);
        mydb.insertNoDuplicate("Yash", "13bce123", "1002", "CSE");
        mydb.insertNoDuplicate("Prajal", "13bce122", "1001", "CSE");
        contacts = mydb.getAll("");
        adapter.setContactList(contacts);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Get engines
        mEngine = NgnEngine.getInstance();
        mConfigurationService = mEngine
                .getConfigurationService();
        mConfigurationService.putString(NgnConfigurationEntry.IDENTITY_IMPI,
                username);
        mConfigurationService.putString(NgnConfigurationEntry.IDENTITY_IMPU,
                String.format("sip:%s@%s", username, domain));
        mConfigurationService.putString(
                NgnConfigurationEntry.IDENTITY_PASSWORD, password);
        mConfigurationService.putString(
                NgnConfigurationEntry.NETWORK_PCSCF_HOST, domain);
        mConfigurationService.putInt(NgnConfigurationEntry.NETWORK_PCSCF_PORT,
                5060);
        mConfigurationService.putString(NgnConfigurationEntry.NETWORK_REALM,
                domain);
        // By default, using 3G for calls disabled
        mConfigurationService.putBoolean(NgnConfigurationEntry.NETWORK_USE_3G,
                false);
        // You may want to leave the registration timeout to the default 1700
        // seconds
        mConfigurationService.putInt(
                NgnConfigurationEntry.NETWORK_REGISTRATION_TIMEOUT, 10);
        mConfigurationService.commit();
        mSipService = mEngine.getSipService();

//        startService(new Intent(this, SIPService.class));

        // Register broadcast receivers
//        regBroadcastReceiver = new RegistrationBroadcastReceiver();
//        final IntentFilter intentFilter = new IntentFilter();
//        intentFilter
//                .addAction(NgnRegistrationEventArgs.ACTION_REGISTRATION_EVENT);
//        Log.d("Registration", NgnRegistrationEventArgs.ACTION_REGISTRATION_EVENT);
//        this.registerReceiver(regBroadcastReceiver, intentFilter);
//        // Incoming call broadcast receiver
//        final IntentFilter intentRFilter = new IntentFilter();
//        callStateReceiver = new CallStateReceiver();
//        intentRFilter.addAction(NgnInviteEventArgs.ACTION_INVITE_EVENT);
//        Log.d("CallState",NgnInviteEventArgs.ACTION_INVITE_EVENT);
//        registerReceiver(callStateReceiver, intentRFilter);
        initializeManager();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SipSettings.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeManager() {
        if (!mEngine.isStarted()) {
            if (!mEngine.start()) {
                Log.e("DEBUG", "Failed to start the engine :(");
                return;
            }
        }

        // Register
        if (!mSipService.isRegistered()) {
            mSipService.register(this);
        }

    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, DialCallActivity.class);
        intent.putExtra("name", contacts.get(position).getName());
        intent.putExtra("sipAddress", contacts.get(position).getSip());
        startActivity(intent);
    }

    @Override
    public void onLongClick(View view, int position) {

    }


}

class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private ClickListener clickListener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null)
                    clickListener.onLongClick(child, recyclerView.getChildLayoutPosition(child));
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e))
            clickListener.onClick(child, rv.getChildLayoutPosition(child));

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}

interface ClickListener {
    public void onClick(View view, int position);

    public void onLongClick(View view, int position);
}