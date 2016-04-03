package yash.mp1.itnusip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import org.doubango.ngn.NgnEngine;
import org.doubango.ngn.sip.NgnAVSession;


public class ReceiveCallActivity extends Activity {

    private NgnAVSession mSession;
    public static Activity callActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Receive UI ('Accept' & 'Reject' button, caller number and co)
        setContentView(R.layout.in_call);
        callActivity = this;

        Button endCall = (Button) findViewById(R.id.end_call);
        Button ansCall = (Button) findViewById(R.id.ans_call);

        ansCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptBtnClicked(v);
            }
        });

        endCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejectBtnClicked(v);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mSession = NgnAVSession.getSession(extras.getLong("SIP_SESSION_ID"));
//            Toast.makeText(this,mSession.getMediaType().toString(), Toast.LENGTH_LONG).show();
        }

        // Wake the screen and ignore "face touches"
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES);

    }

    public void acceptBtnClicked(View v) {
        mSession.acceptCall();
    }

    public void rejectBtnClicked(View v) {
        if (mSession != null) {
            mSession.hangUpCall();
            NgnEngine mEngine = NgnEngine.getInstance();
            mEngine.getSoundService().stopRingTone();
            mEngine.getSoundService().stopRingBackTone();
            finish();
        }
    }


}