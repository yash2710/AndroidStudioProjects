// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.measurement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

// Referenced classes of package com.adobe.adms.measurement:
//            ADMS_MeasurementBase

public abstract class ADMS_ChurnBase
{

    protected static final String kADMS_InstallDate = "ADMS_InstallDate";
    protected static final String kADMS_LastDateUsed = "ADMS_LastDateUsed";
    protected static final String kADMS_LastVersion = "ADMS_LastVersion";
    protected static final String kADMS_Launches = "ADMS_Launches";
    protected static final String kADMS_LaunchesAfterUpgrade = "ADMS_LaunchesAfterUpgrade";
    protected static final String kADMS_PauseDate = "ADMS_PauseDate";
    protected static final String kADMS_PrevSessionLength = "a.PrevSessionLength";
    protected static final String kADMS_SessionStart = "ADMS_SessionStart";
    protected static final String kADMS_SuccessfulClose = "ADMS_SuccessfulClose";
    protected static final String kADMS_UpgradeDate = "ADMS_UpgradeDate";
    private String a;
    protected String appEnvironmentEvar;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    protected int lifecycleSessionTimeout;
    private String m;
    private String n;
    private String o;
    private String p;
    private Hashtable q;
    private ArrayList r;
    private Hashtable s;
    private String t;
    private String u;
    private Date v;
    private ADMS_MeasurementBase w;
    private DateFormat x;
    private DateFormat y;
    private DateFormat z;

    protected ADMS_ChurnBase(ADMS_MeasurementBase adms_measurementbase)
    {
        a = "a.InstallEvent";
        b = "a.UpgradeEvent";
        c = "a.DailyEngUserEvent";
        d = "a.MonthlyEngUserEvent";
        e = "a.LaunchEvent";
        f = "a.CrashEvent";
        g = "a.InstallDate";
        h = "a.AppID";
        i = "a.EngDaysLifetime";
        j = "a.DaysSinceFirstUse";
        k = "a.DaysSinceLastUse";
        l = "a.Launches";
        m = "a.HourOfDay";
        n = "a.DayOfWeek";
        appEnvironmentEvar = "a.OSEnvironment";
        o = "a.DaysSinceLastUpgrade";
        p = "a.LaunchesSinceUpgrade";
        lifecycleSessionTimeout = 300;
        q = new Hashtable();
        r = new ArrayList();
        s = new Hashtable();
        t = null;
        u = null;
        v = null;
        w = null;
        x = new SimpleDateFormat("M/d/yyyy");
        y = new SimpleDateFormat("H");
        z = new SimpleDateFormat("M/yyyy");
        w = adms_measurementbase;
    }

    private static int a(Date date, Date date1)
    {
        return (int)((date1.getTime() - date.getTime()) / 1000L);
    }

    private String a(Date date)
    {
        return x.format(date);
    }

    private void a()
    {
        Date date = b("ADMS_LastDateUsed");
        if (!a(date).equalsIgnoreCase(a(v)))
        {
            setContextDataValue("+1", i);
            a(c);
        }
        if (!b(date).equals(b(v)))
        {
            a(d);
        }
        Date date1 = b("ADMS_InstallDate");
        setContextDataValue((new StringBuilder()).append(b(date1, v)).toString(), j);
        setContextDataValue((new StringBuilder()).append(b(date, v)).toString(), k);
        if (!prefsGetBool("ADMS_SuccessfulClose", false))
        {
            a(f);
        }
    }

    private void a(int i1)
    {
        if (prefsContains("ADMS_UpgradeDate"))
        {
            int j1 = 1 + prefsGetInt("ADMS_LaunchesAfterUpgrade", 0);
            setContextDataValue((new StringBuilder()).append(j1).toString(), p);
            if (i1 == 2)
            {
                int k1 = b(b("ADMS_UpgradeDate"), v);
                setContextDataValue((new StringBuilder()).append(k1).toString(), o);
            }
            prefsPutInt("ADMS_LaunchesAfterUpgrade", j1);
        }
    }

    private void a(String s1)
    {
        if (w.isSet(s1))
        {
            r.add(s1);
        }
    }

    private void a(String s1, String s2)
    {
        if (w.isSet(s1) && w.isSet(s2))
        {
            s.put(s2, s1);
        }
    }

    private void a(Date date, String s1)
    {
        prefsPutLong(s1, date.getTime());
    }

    private static int b(Date date, Date date1)
    {
        return (int)((date1.getTime() - date.getTime()) / 0x5265c00L);
    }

    private String b(Date date)
    {
        return z.format(date);
    }

    private Date b(String s1)
    {
        return new Date(prefsGetLong(s1, 0L));
    }

    private void b()
    {
        String s1;
        for (Iterator iterator = r.iterator(); iterator.hasNext(); setContextDataValue(s1.replace("a.", ""), s1))
        {
            s1 = (String)iterator.next();
        }

    }

    public static String join(Collection collection, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        Iterator iterator = collection.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            stringbuffer.append((String)iterator.next());
            if (iterator.hasNext())
            {
                stringbuffer.append(s1);
            }
        } while (true);
        return stringbuffer.toString();
    }

    protected abstract String getApplicationName();

    protected abstract String getApplicationVersion();

    protected abstract void handleReferrers();

    protected abstract boolean prefsContains(String s1);

    protected abstract boolean prefsGetBool(String s1, boolean flag);

    protected abstract int prefsGetInt(String s1, int i1);

    protected abstract long prefsGetLong(String s1, long l1);

    protected abstract String prefsGetString(String s1, String s2);

    protected abstract void prefsPutBool(String s1, boolean flag);

    protected abstract void prefsPutInt(String s1, int i1);

    protected abstract void prefsPutLong(String s1, long l1);

    protected abstract void prefsPutString(String s1, String s2);

    protected abstract void removeObjectFromPrefsWithKey(String s1);

    protected void setContextDataValue(String s1, String s2)
    {
        if (w.isSet(s1) && w.isSet(s2))
        {
            q.put(s2, s1);
        }
    }

    protected void setGenericVariables()
    {
        a(e);
        int i1 = 1 + prefsGetInt("ADMS_Launches", 0);
        setContextDataValue((new StringBuilder()).append(i1).toString(), l);
        setContextDataValue(u, h);
        Date date = v;
        setContextDataValue(y.format(date), m);
        Date date1 = v;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        setContextDataValue(Integer.toString(calendar.get(7)), n);
        a(v, "ADMS_LastDateUsed");
        prefsPutInt("ADMS_Launches", i1);
    }

    protected void startSession()
    {
        boolean flag;
        if (!prefsGetBool("ADMS_SuccessfulClose", false))
        {
            flag = true;
        } else
        if (prefsContains("ADMS_PauseDate") && a(b("ADMS_PauseDate"), new Date()) > lifecycleSessionTimeout)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        s = new Hashtable();
        q = new Hashtable();
        r = new ArrayList();
        t = getApplicationVersion();
        u = (new StringBuilder()).append(getApplicationName()).append("(").append(t).append(")").toString();
        v = new Date();
        if (prefsContains("ADMS_InstallDate")) goto _L4; else goto _L3
_L3:
        a((new StringBuilder()).append(u).append(" Install").toString(), "pageName");
        setContextDataValue(a(v), g);
        setContextDataValue("+1", i);
        handleReferrers();
        a(a);
        a(c);
        a(d);
        a(v, "ADMS_InstallDate");
        a(0);
_L6:
        setGenericVariables();
        b();
        if (prefsContains("ADMS_PauseDate"))
        {
            Date date = b("ADMS_PauseDate");
            if (a(date, v) > lifecycleSessionTimeout)
            {
                int i1 = a(b("ADMS_SessionStart"), date);
                if (i1 > 0)
                {
                    setContextDataValue((new StringBuilder()).append(i1).toString(), "a.PrevSessionLength");
                }
                removeObjectFromPrefsWithKey("ADMS_SessionStart");
            }
        }
        w.trackLink(null, "o", "ADMS BP Event", q, s);
        if (!prefsContains("ADMS_SessionStart"))
        {
            a(v, "ADMS_SessionStart");
        }
        prefsPutString("ADMS_LastVersion", t);
        prefsPutBool("ADMS_SuccessfulClose", false);
_L2:
        return;
_L4:
        if (prefsContains("ADMS_LastVersion"))
        {
            if (prefsGetString("ADMS_LastVersion", null).equalsIgnoreCase(t))
            {
                a((new StringBuilder()).append(u).append(" Launch").toString(), "pageName");
                a();
                a(2);
            } else
            {
                a((new StringBuilder()).append(u).append(" Upgrade").toString(), "pageName");
                a(b);
                prefsPutInt("ADMS_LaunchesAfterUpgrade", 0);
                a(v, "ADMS_UpgradeDate");
                a();
                a(1);
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected void stopSession()
    {
        a(new Date(), "ADMS_PauseDate");
        prefsPutBool("ADMS_SuccessfulClose", true);
    }
}
