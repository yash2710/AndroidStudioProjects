// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Looper;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.api.v1.ConnectionEvent;
import com.newrelic.agent.android.api.v1.ConnectionListener;
import com.newrelic.agent.android.api.v1.DeviceForm;
import com.newrelic.agent.android.api.v2.TraceMachineInterface;
import com.newrelic.agent.android.background.ApplicationStateEvent;
import com.newrelic.agent.android.background.ApplicationStateListener;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestConfiguration;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.sample.MachineMeasurementConsumer;
import com.newrelic.agent.android.sample.Sampler;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.AndroidEncoder;
import com.newrelic.agent.android.util.Carrier;
import com.newrelic.agent.android.util.Encoder;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Referenced classes of package com.newrelic.agent.android:
//            AgentImpl, a, AgentConfiguration, SavedState, 
//            Agent, AgentInitializationException, b, Measurements, 
//            NullAgentImpl

public class AndroidAgentImpl
    implements AgentImpl, ConnectionListener, TraceMachineInterface, ApplicationStateListener
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private final Context b;
    private SavedState c;
    private LocationListener d;
    private final Lock e = new ReentrantLock();
    private final Encoder f = new AndroidEncoder();
    private DeviceInformation g;
    private ApplicationInformation h;
    private final AgentConfiguration i = new AgentConfiguration();
    private MachineMeasurementConsumer j;

    public AndroidAgentImpl(Context context, String s, String s1, boolean flag, boolean flag1, String s2)
    {
        Context context1;
        if (!(context instanceof Application))
        {
            context1 = context.getApplicationContext();
        } else
        {
            context1 = context;
        }
        b = context1;
        c = new SavedState(b);
        if (!s.equals(c.getAppToken()))
        {
            a.debug("License key has changed. Clearing saved state.");
            c.clear();
        }
        if (!Agent.getVersion().equals(c.getAgentVersion()))
        {
            a.debug("Agent version has changed. Clearing saved state.");
            c.clear();
        }
        if (isDisabled())
        {
            throw new AgentInitializationException("This version of the agent has been disabled");
        }
        initApplicationInformation();
        if (flag1 && b.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", getApplicationInformation().getPackageId()) == 0)
        {
            a.debug("Location stats enabled");
            LocationManager locationmanager = (LocationManager)b.getSystemService("location");
            if (locationmanager == null)
            {
                a.error("Unable to retrieve reference to LocationManager. Disabling location listener.");
            } else
            {
                d = new b(this);
                locationmanager.requestLocationUpdates("passive", 1000L, 0.0F, d);
            }
        }
        TraceMachine.setTraceMachineInterface(this);
        c.saveAppToken(s);
        c.saveAgentVersion(Agent.getVersion());
        i.setApplicationToken(s);
        i.setCollectorHost(s1);
        i.setUseSsl(flag);
        ApplicationStateMonitor.getInstance().addApplicationStateListener(this);
        Sampler.init(context);
    }

    private void a()
    {
        LocationManager locationmanager;
        if (d == null)
        {
            return;
        }
        locationmanager = (LocationManager)b.getSystemService("location");
        if (locationmanager == null)
        {
            a.error("Unable to retrieve reference to LocationManager. Can't unregister location listener.");
            return;
        }
        locationmanager;
        JVM INSTR monitorenter ;
        locationmanager.removeUpdates(d);
        d = null;
        locationmanager;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        locationmanager;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static void a(AndroidAgentImpl androidagentimpl)
    {
        androidagentimpl.a();
    }

    private static void a(boolean flag)
    {
        Sampler.shutdown();
        TraceMachine.haltTracing();
        if (flag)
        {
            Harvest.harvestNow();
        }
        TraceMachine.clearActivityHistory();
        Harvest.shutdown();
        Measurements.shutdown();
    }

    static boolean a(AndroidAgentImpl androidagentimpl, Location location)
    {
        return location != null && 500F >= location.getAccuracy();
    }

    private static String b()
    {
        String s;
        try
        {
            s = Thread.getDefaultUncaughtExceptionHandler().getClass().getName();
        }
        catch (Exception exception)
        {
            return "unknown";
        }
        return s;
    }

    public static void init(Context context, String s, String s1, boolean flag, boolean flag1, String s2)
    {
        try
        {
            Agent.setImpl(new AndroidAgentImpl(context, s, s1, flag, flag1, s2));
            Agent.start();
            return;
        }
        catch (AgentInitializationException agentinitializationexception)
        {
            a.error((new StringBuilder("Failed to initialize the agent: ")).append(agentinitializationexception.toString()).toString());
        }
    }

    public void addTransactionData(TransactionData transactiondata)
    {
    }

    public void applicationBackgrounded(ApplicationStateEvent applicationstateevent)
    {
        a.error("AndroidAgentImpl: application backgrounded ");
        stop();
    }

    public void applicationForegrounded(ApplicationStateEvent applicationstateevent)
    {
        a.error("AndroidAgentImpl: application foregrounded ");
        start();
    }

    public void connected(ConnectionEvent connectionevent)
    {
        a.error("AndroidAgentImpl: connected ");
    }

    public void disable()
    {
        a.warning((new StringBuilder("PERMANENTLY DISABLING AGENT v")).append(Agent.getVersion()).toString());
        c.saveDisabledVersion(Agent.getVersion());
        a(false);
        Agent.setImpl(NullAgentImpl.instance);
        return;
        Exception exception2;
        exception2;
        Agent.setImpl(NullAgentImpl.instance);
        throw exception2;
        Exception exception;
        exception;
        a(false);
        Agent.setImpl(NullAgentImpl.instance);
        throw exception;
        Exception exception1;
        exception1;
        Agent.setImpl(NullAgentImpl.instance);
        throw exception1;
    }

    public void disconnected(ConnectionEvent connectionevent)
    {
        c.clear();
    }

    public List getAndClearTransactionData()
    {
        return null;
    }

    public ApplicationInformation getApplicationInformation()
    {
        return h;
    }

    public String getCrossProcessId()
    {
        e.lock();
        String s = c.getCrossProcessId();
        e.unlock();
        return s;
        Exception exception;
        exception;
        e.unlock();
        throw exception;
    }

    public long getCurrentThreadId()
    {
        return Thread.currentThread().getId();
    }

    public String getCurrentThreadName()
    {
        return Thread.currentThread().getName();
    }

    public DeviceInformation getDeviceInformation()
    {
        DeviceInformation deviceinformation;
        int k;
        if (g != null)
        {
            return g;
        }
        deviceinformation = new DeviceInformation();
        deviceinformation.setOsName("Android");
        deviceinformation.setOsVersion(android.os.Build.VERSION.RELEASE);
        deviceinformation.setModel(Build.MODEL);
        deviceinformation.setAgentName("AndroidAgent");
        deviceinformation.setAgentVersion(Agent.getVersion());
        deviceinformation.setManufacturer(Build.MANUFACTURER);
        String s = c.getAndroidIdBugWorkAround();
        if (s == null)
        {
            s = UUID.randomUUID().toString();
            c.saveAndroidIdBugWorkAround(s);
        }
        deviceinformation.setDeviceId(s);
        k = 0xf & b.getResources().getConfiguration().screenLayout;
        k;
        JVM INSTR tableswitch 1 3: default 140
    //                   1 175
    //                   2 183
    //                   3 191;
           goto _L1 _L2 _L3 _L4
_L1:
        DeviceForm deviceform;
        if (k > 3)
        {
            deviceform = DeviceForm.XLARGE;
        } else
        {
            deviceform = DeviceForm.UNKNOWN;
        }
_L6:
        deviceinformation.addMisc("size", deviceform.name().toLowerCase());
        g = deviceinformation;
        return g;
_L2:
        deviceform = DeviceForm.SMALL;
        continue; /* Loop/switch isn't completed */
_L3:
        deviceform = DeviceForm.NORMAL;
        continue; /* Loop/switch isn't completed */
_L4:
        deviceform = DeviceForm.LARGE;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public Encoder getEncoder()
    {
        return f;
    }

    public String getNetworkCarrier()
    {
        return Carrier.nameFromContext(b);
    }

    public int getResponseBodyLimit()
    {
        e.lock();
        int k = c.getHarvestConfiguration().getResponse_body_limit();
        e.unlock();
        return k;
        Exception exception;
        exception;
        e.unlock();
        throw exception;
    }

    public int getStackTraceLimit()
    {
        e.lock();
        int k = c.getStackTraceLimit();
        e.unlock();
        return k;
        Exception exception;
        exception;
        e.unlock();
        throw exception;
    }

    public void initApplicationInformation()
    {
        String s;
        String s2;
        s = b.getPackageName();
        PackageManager packagemanager = b.getPackageManager();
        PackageInfo packageinfo;
        String s1;
        android.content.pm.ApplicationInfo applicationinfo;
        String s3;
        try
        {
            packageinfo = packagemanager.getPackageInfo(s, 0);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            throw new AgentInitializationException((new StringBuilder("Could not determine package version: ")).append(namenotfoundexception.getMessage()).toString());
        }
        if (packageinfo == null) goto _L2; else goto _L1
_L1:
        if (packageinfo.versionName == null || packageinfo.versionName.length() <= 0) goto _L2; else goto _L3
_L3:
        s1 = packageinfo.versionName;
        applicationinfo = packagemanager.getApplicationInfo(s, 0);
        if (applicationinfo == null) goto _L5; else goto _L4
_L4:
        s3 = packagemanager.getApplicationLabel(applicationinfo).toString();
        s2 = s3;
_L7:
        h = new ApplicationInformation(s2, s1, s);
        return;
_L2:
        throw new AgentInitializationException("Your app doesn't appear to have a version defined. Ensure you have defined 'versionName' in your manifest.");
_L5:
        s2 = s;
        continue; /* Loop/switch isn't completed */
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception1;
        namenotfoundexception1;
        a.warning(namenotfoundexception1.toString());
        s2 = s;
        continue; /* Loop/switch isn't completed */
        SecurityException securityexception;
        securityexception;
        a.warning(securityexception.toString());
        s2 = s;
        if (true) goto _L7; else goto _L6
_L6:
    }

    protected void initialize()
    {
        Harvest.addHarvestListener(c);
        Harvest.initialize(i);
        Harvest.setHarvestConfiguration(c.getHarvestConfiguration());
        Measurements.initialize();
        AgentLog agentlog = a;
        Object aobj[] = new Object[1];
        aobj[0] = Agent.getVersion();
        agentlog.info(MessageFormat.format("New Relic Agent v{0}", aobj));
        AgentLog agentlog1 = a;
        Object aobj1[] = new Object[1];
        aobj1[0] = i.getApplicationToken();
        agentlog1.verbose(MessageFormat.format("Application token: {0}", aobj1));
        j = new MachineMeasurementConsumer();
        Measurements.addMeasurementConsumer(j);
        StatsEngine.get().inc((new StringBuilder("Supportability/AgentHealth/UncaughtExceptionHandler/")).append(b()).toString());
    }

    public boolean isDisabled()
    {
        return Agent.getVersion().equals(c.getDisabledVersion());
    }

    public boolean isUIThread()
    {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public void mergeTransactionData(List list)
    {
    }

    public void setLocation(Location location)
    {
        List list;
        if (location == null)
        {
            throw new IllegalArgumentException("Location must not be null.");
        }
        Geocoder geocoder = new Geocoder(b);
        List list1;
        try
        {
            list1 = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        }
        catch (IOException ioexception)
        {
            a.error((new StringBuilder("Unable to geocode location: ")).append(ioexception.toString()).toString());
            list = null;
            continue; /* Loop/switch isn't completed */
        }
        list = list1;
_L6:
        if (list != null && list.size() != 0) goto _L2; else goto _L1
_L1:
        Address address;
        return;
_L2:
        if ((address = (Address)list.get(0)) == null) goto _L1; else goto _L3
_L3:
        String s;
        String s1;
        s = address.getCountryCode();
        s1 = address.getAdminArea();
        if (s == null || s1 == null) goto _L1; else goto _L4
_L4:
        setLocation(s, s1);
        a();
        return;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void setLocation(String s, String s1)
    {
        if (s == null || s1 == null)
        {
            throw new IllegalArgumentException("Country code and administrative region are required.");
        } else
        {
            return;
        }
    }

    public void start()
    {
        if (!isDisabled())
        {
            initialize();
            Harvest.start();
            return;
        } else
        {
            a(false);
            return;
        }
    }

    public void stop()
    {
        a(true);
    }

    static 
    {
        new a();
    }
}
