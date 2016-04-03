// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Collection;

public final class PreferencesActivity extends PreferenceActivity
    implements android.content.SharedPreferences.OnSharedPreferenceChangeListener, TraceFieldInterface
{

    public static final String KEY_AUTO_FOCUS = "preferences_auto_focus";
    public static final String KEY_BULK_MODE = "preferences_bulk_mode";
    public static final String KEY_COPY_TO_CLIPBOARD = "preferences_copy_to_clipboard";
    public static final String KEY_CUSTOM_PRODUCT_SEARCH = "preferences_custom_product_search";
    public static final String KEY_DECODE_1D = "preferences_decode_1D";
    public static final String KEY_DECODE_DATA_MATRIX = "preferences_decode_Data_Matrix";
    public static final String KEY_DECODE_QR = "preferences_decode_QR";
    public static final String KEY_DISABLE_CONTINUOUS_FOCUS = "preferences_disable_continuous_focus";
    public static final String KEY_FRONT_LIGHT_MODE = "preferences_front_light_mode";
    public static final String KEY_HELP_VERSION_SHOWN = "preferences_help_version_shown";
    public static final String KEY_INVERT_SCAN = "preferences_invert_scan";
    public static final String KEY_PLAY_BEEP = "preferences_play_beep";
    public static final String KEY_REMEMBER_DUPLICATES = "preferences_remember_duplicates";
    public static final String KEY_SEARCH_COUNTRY = "preferences_search_country";
    public static final String KEY_SUPPLEMENTAL = "preferences_supplemental";
    public static final String KEY_VIBRATE = "preferences_vibrate";
    private CheckBoxPreference a;
    private CheckBoxPreference b;
    private CheckBoxPreference c;

    public PreferencesActivity()
    {
    }

    private void a()
    {
        ArrayList arraylist = new ArrayList(3);
        if (a.isChecked())
        {
            arraylist.add(a);
        }
        if (b.isChecked())
        {
            arraylist.add(b);
        }
        if (c.isChecked())
        {
            arraylist.add(c);
        }
        boolean flag;
        CheckBoxPreference acheckboxpreference[];
        int i;
        if (arraylist.size() < 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        acheckboxpreference = new CheckBoxPreference[3];
        acheckboxpreference[0] = a;
        acheckboxpreference[1] = b;
        acheckboxpreference[2] = c;
        i = 0;
        while (i < 3) 
        {
            CheckBoxPreference checkboxpreference = acheckboxpreference[i];
            boolean flag1;
            if (!flag || !arraylist.contains(checkboxpreference))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            checkboxpreference.setEnabled(flag1);
            i++;
        }
    }

    protected final void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("PreferencesActivity");
        TraceMachine.enterMethod(_nr_trace, "PreferencesActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preferences);
        PreferenceScreen preferencescreen = getPreferenceScreen();
        preferencescreen.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        a = (CheckBoxPreference)preferencescreen.findPreference("preferences_decode_1D");
        b = (CheckBoxPreference)preferencescreen.findPreference("preferences_decode_QR");
        c = (CheckBoxPreference)preferencescreen.findPreference("preferences_decode_Data_Matrix");
        a();
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "PreferencesActivity#onCreate", null);
          goto _L1
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
    {
        a();
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop()
    {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }
}
