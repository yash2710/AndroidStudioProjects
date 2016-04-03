package yash.mp1.itnusip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.doubango.ngn.NgnEngine;
import org.doubango.ngn.media.NgnMediaType;
import org.doubango.ngn.services.INgnConfigurationService;
import org.doubango.ngn.services.INgnSipService;
import org.doubango.ngn.sip.NgnAVSession;
import org.doubango.ngn.sip.NgnSipStack;
import org.doubango.ngn.utils.NgnConfigurationEntry;
import org.doubango.ngn.utils.NgnUriUtils;

/**
 * Created by yash on 5/4/15.
 */
public class DialCallActivity extends ActionBarActivity implements View.OnClickListener {
    Intent intent;
    String name,sipAddress;
    TextView nameTV;
    Button endCall;
    static NgnAVSession activeCall;
    static NgnAVSession avSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_in);
        intent = getIntent();
        name = intent.getStringExtra("name");
        sipAddress = intent.getStringExtra("sipAddress");

        nameTV = (TextView) findViewById(R.id.name);
        nameTV.setText(name);

        endCall= (Button) findViewById(R.id.end_call);
        endCall.setOnClickListener(this);

        makeCall(sipAddress, NgnMediaType.Audio);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public static boolean makeCall(String remoteUri, NgnMediaType mediaType){
        NgnEngine mEngine = NgnEngine.getInstance();
        final INgnSipService sipService = mEngine.getSipService();
        final INgnConfigurationService configurationService = mEngine.getConfigurationService();
        //final IScreenService screenService = engine.getScreenService();
        final String validUri = NgnUriUtils.makeValidSipUri(remoteUri);
        if(validUri == null){
            Log.e("TAG", "failed to normalize sip uri '" + remoteUri + "'");
            return false;
        }
        else{
            remoteUri = validUri;
            if(remoteUri.startsWith("tel:")){
                // E.164 number => use ENUM protocol
                final NgnSipStack sipStack = sipService.getSipStack();
                if(sipStack != null){
                    String phoneNumber = NgnUriUtils.getValidPhoneNumber(remoteUri);
                    if(phoneNumber != null){
                        String enumDomain = configurationService.getString(
                                NgnConfigurationEntry.GENERAL_ENUM_DOMAIN, NgnConfigurationEntry.DEFAULT_GENERAL_ENUM_DOMAIN);
                        String sipUri = sipStack.dnsENUM("E2U+SIP", phoneNumber, enumDomain);
                        if(sipUri != null){
                            remoteUri = sipUri;
                        }
                    }
                }
            }
        }

        avSession = NgnAVSession.createOutgoingSession(sipService.getSipStack(), mediaType);
        avSession.setRemotePartyUri(remoteUri);

        // Hold the active call
        activeCall = NgnAVSession.getFirstActiveCallAndNot(avSession.getId());
        if(activeCall != null){
            activeCall.holdCall();
        }

        return avSession.makeCall(remoteUri);
    }

    @Override
    public void onClick(View view) {
        avSession.hangUpCall();
        finish();
    }
}
