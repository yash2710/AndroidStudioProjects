package yash.mp1.itnusip;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Yash on 04-Apr-15.
 */
public class SipSettings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}

