package yash.mp1.itnusip.receiver;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.doubango.ngn.NgnEngine;
import org.doubango.ngn.services.INgnConfigurationService;
import org.doubango.ngn.utils.NgnConfigurationEntry;

/**
 * Created by Yash on 05-May-15.
 */
public class SIPService extends Service {



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"Started", Toast.LENGTH_SHORT).show();
        NgnEngine mEngine;
        SharedPreferences sharedPreferences;
        INgnConfigurationService mConfigurationService;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String username = sharedPreferences.getString("namePref", "");
        String domain = sharedPreferences.getString("domainPref", "");
        String password = sharedPreferences.getString("passPref", "");

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

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
