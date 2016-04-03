package yash.mp1.itnusip.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.doubango.ngn.NgnEngine;
import org.doubango.ngn.events.NgnEventArgs;
import org.doubango.ngn.events.NgnInviteEventArgs;
import org.doubango.ngn.events.NgnRegistrationEventArgs;
import org.doubango.ngn.sip.NgnAVSession;
import org.doubango.ngn.sip.NgnInviteSession;

import yash.mp1.itnusip.ReceiveCallActivity;

public class RegistrationBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		final String action = intent.getAction();
//        Toast.makeText(context,"Receive",Toast.LENGTH_LONG).show();
        // Registration Event
		if (NgnRegistrationEventArgs.ACTION_REGISTRATION_EVENT.equals(action)) {
			NgnRegistrationEventArgs args = intent
					.getParcelableExtra(NgnEventArgs.EXTRA_EMBEDDED);
			if (args == null) {
				Log.d("DEBUG", "Invalid event args");
				return;
			}
			switch (args.getEventType()) {
			case REGISTRATION_NOK:
				Log.d("DEBUG", "Failed to register :(");
				break;
			case UNREGISTRATION_OK:
				Log.d("DEBUG", "You are now unregistered :)");
				break;
			case REGISTRATION_OK:
				Log.d("DEBUG", "You are now registered :)");
//				Toast.makeText(context, "registered", Toast.LENGTH_SHORT).show();
				break;
			case REGISTRATION_INPROGRESS:
//				Toast.makeText(context, "registering", Toast.LENGTH_SHORT).show();
				Log.d("DEBUG", "Trying to register...");
				break;
			case UNREGISTRATION_INPROGRESS:
				Log.d("DEBUG", "Trying to unregister...");
				break;
			case UNREGISTRATION_NOK:
				Log.d("DEBUG", "Failed to unregister :(");
				break;
			}

		}
        Toast.makeText(context,action.toString(),Toast.LENGTH_LONG).show();
        if (NgnInviteEventArgs.ACTION_INVITE_EVENT.equals(action)) {
            NgnInviteEventArgs args = intent
                    .getParcelableExtra(NgnEventArgs.EXTRA_EMBEDDED);
            if (args == null) {
                Log.d("DEBUG", "Invalid event args");
                return;
            }

            NgnAVSession avSession = NgnAVSession.getSession(args
                    .getSessionId());
            if (avSession == null) {
                return;
            }

            final NgnInviteSession.InviteState callState = avSession.getState();
            NgnEngine mEngine = NgnEngine.getInstance();

            switch (callState) {
                case NONE:
                default:
                    break;
                case INCOMING:
                    Log.i("DEBUG", "Incoming call");
                    // Ringtone
                    mEngine.getSoundService().startRingTone();
                    // Start a ReceiveActivity
//                Toast.makeText(context, "Receiving", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context.getApplicationContext(),
                            ReceiveCallActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("SIP_SESSION_ID", avSession.getId());
                    i.putExtra("PHONE_NUMBER_EXTRA",
                            avSession.getRemotePartyDisplayName());
                    context.startActivity(i);
                    break;
                case INCALL:
                    Log.i("DEBUG", "Call connected");
                    mEngine.getSoundService().stopRingTone();
                    break;
                case TERMINATED:
                    Log.i("DEBUG", "Call terminated");
                    mEngine.getSoundService().stopRingTone();
                    mEngine.getSoundService().stopRingBackTone();
                    ReceiveCallActivity.callActivity.finish();
                    break;

            }
        }
	}
}