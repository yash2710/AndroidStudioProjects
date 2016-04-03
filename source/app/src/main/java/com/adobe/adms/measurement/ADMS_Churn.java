// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.measurement;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

// Referenced classes of package com.adobe.adms.measurement:
//            ADMS_ChurnBase, ADMS_Measurement

public final class ADMS_Churn extends ADMS_ChurnBase
{

    private ADMS_Measurement a;
    private Context b;

    protected ADMS_Churn(ADMS_Measurement adms_measurement)
    {
        super(adms_measurement);
        a = null;
        b = null;
        a = adms_measurement;
    }

    protected final String getApplicationName()
    {
        String s;
        try
        {
            PackageManager packagemanager = a.context.getPackageManager();
            s = (String)packagemanager.getApplicationLabel(packagemanager.getApplicationInfo(a.context.getPackageName(), 0));
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
            return null;
        }
        return s;
    }

    protected final String getApplicationVersion()
    {
        String s;
        try
        {
            s = a.context.getPackageManager().getPackageInfo(a.context.getPackageName(), 0).versionName;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
            return "";
        }
        return s;
    }

    protected final void handleFBReferrer()
    {
        if (b != null)
        {
            Uri uri = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
            Cursor cursor = b.getContentResolver().query(uri, new String[] {
                "aid"
            }, null, null, null);
            if (cursor != null && cursor.moveToFirst())
            {
                setContextDataValue(cursor.getString(cursor.getColumnIndex("aid")), "a.fb.attrib");
                return;
            }
        }
    }

    protected final void handleGPReferrer()
    {
        if (!prefsGetBool("ADMS_ReferrerProcessed", false))
        {
            String s = prefsGetString("utm_source", null);
            String s1 = prefsGetString("utm_medium", null);
            String s2 = prefsGetString("utm_term", null);
            String s3 = prefsGetString("utm_content", null);
            String s4 = prefsGetString("utm_campaign", null);
            if (s != null && s1 != null && s4 != null)
            {
                setContextDataValue(s, "a.referrer.campaign.source");
                setContextDataValue(s1, "a.referrer.campaign.medium");
                setContextDataValue(s2, "a.referrer.campaign.term");
                setContextDataValue(s3, "a.referrer.campaign.content");
                setContextDataValue(s4, "a.referrer.campaign.name");
                prefsPutBool("ADMS_ReferrerProcessed", true);
            }
        }
    }

    protected final void handleReferrers()
    {
        handleGPReferrer();
    }

    protected final boolean prefsContains(String s)
    {
        return ADMS_Measurement.a.contains(s);
    }

    protected final boolean prefsGetBool(String s, boolean flag)
    {
        return ADMS_Measurement.a.getBoolean(s, flag);
    }

    protected final int prefsGetInt(String s, int i)
    {
        return ADMS_Measurement.a.getInt(s, i);
    }

    protected final long prefsGetLong(String s, long l)
    {
        return ADMS_Measurement.a.getLong(s, l);
    }

    protected final String prefsGetString(String s, String s1)
    {
        return ADMS_Measurement.a.getString(s, s1);
    }

    protected final void prefsPutBool(String s, boolean flag)
    {
        ADMS_Measurement.b.putBoolean(s, flag);
        ADMS_Measurement.b.commit();
    }

    protected final void prefsPutInt(String s, int i)
    {
        ADMS_Measurement.b.putInt(s, i);
        ADMS_Measurement.b.commit();
    }

    protected final void prefsPutLong(String s, long l)
    {
        ADMS_Measurement.b.putLong(s, l);
        ADMS_Measurement.b.commit();
    }

    protected final void prefsPutString(String s, String s1)
    {
        ADMS_Measurement.b.putString(s, s1);
        ADMS_Measurement.b.commit();
    }

    protected final void removeObjectFromPrefsWithKey(String s)
    {
        ADMS_Measurement.b.remove(s);
        ADMS_Measurement.b.commit();
    }

    protected final void setGenericVariables()
    {
        super.setGenericVariables();
        setContextDataValue((new StringBuilder("Android ")).append(ADMS_Measurement.getAndroidVersion()).toString(), appEnvironmentEvar);
    }

    protected final void startActivity(Context context)
    {
        b = context;
        String s = (new StringBuilder()).append(context.getApplicationInfo().packageName).append("open").toString();
        if (prefsGetBool(s, false))
        {
            prefsPutBool("ADMS_SuccessfulClose", false);
        }
        prefsPutBool(s, true);
        startSession();
    }

    protected final void stopActivity(Context context)
    {
        prefsPutBool((new StringBuilder()).append(context.getApplicationInfo().packageName).append("open").toString(), false);
        stopSession();
    }
}
