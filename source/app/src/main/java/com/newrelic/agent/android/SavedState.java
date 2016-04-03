// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;

import android.content.Context;
import android.content.SharedPreferences;
import com.newrelic.agent.android.harvest.DataToken;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.harvest.HarvestConfiguration;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

// Referenced classes of package com.newrelic.agent.android:
//            Agent

public class SavedState extends HarvestAdapter
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private final HarvestConfiguration b = new HarvestConfiguration();
    private Float c;
    private final SharedPreferences d;
    private final android.content.SharedPreferences.Editor e;
    private final Lock f = new ReentrantLock();

    public SavedState(Context context)
    {
        String s = context.getPackageName();
        d = context.getSharedPreferences((new StringBuilder("com.newrelic.android.agent.v1_")).append(s).toString(), 0);
        e = d.edit();
        loadHarvestConfiguration();
    }

    private boolean a(String s)
    {
        return d.contains(s);
    }

    public void clear()
    {
        f.lock();
        e.clear();
        e.commit();
        b.setDefaultValues();
        f.unlock();
        return;
        Exception exception;
        exception;
        f.unlock();
        throw exception;
    }

    public float getActivityTraceMinUtilization()
    {
        if (c == null)
        {
            c = getFloat("activityTraceMinUtilization");
        }
        return c.floatValue();
    }

    public String getAgentVersion()
    {
        return getString("agentVersion");
    }

    public String getAndroidIdBugWorkAround()
    {
        return getString("androidIdBugWorkAround");
    }

    public String getAppToken()
    {
        return getString("appToken");
    }

    public boolean getBoolean(String s)
    {
        return d.getBoolean(s, false);
    }

    public String getCrossProcessId()
    {
        return getString("crossProcessId");
    }

    public int[] getDataToken()
    {
        int ai[];
        String s;
        ai = new int[2];
        s = getString("dataToken");
        if (s == null)
        {
            return null;
        }
        JSONArray jsonarray = (JSONArray)(new JSONTokener(s)).nextValue();
        if (jsonarray == null)
        {
            return null;
        }
        try
        {
            ai[0] = jsonarray.getInt(0);
            ai[1] = jsonarray.getInt(1);
        }
        catch (JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        return ai;
    }

    public String getDisabledVersion()
    {
        return getString("NewRelicAgentDisabledVersion");
    }

    public int getErrorLimit()
    {
        return getInt("errorLimit");
    }

    public Float getFloat(String s)
    {
        if (!d.contains(s))
        {
            return null;
        } else
        {
            return Float.valueOf((float)(int)(100F * d.getFloat(s, 0.0F)) / 100F);
        }
    }

    public HarvestConfiguration getHarvestConfiguration()
    {
        return b;
    }

    public long getHarvestInterval()
    {
        return getLong("harvestIntervalInSeconds");
    }

    public long getHarvestIntervalInSeconds()
    {
        return getHarvestInterval();
    }

    public int getInt(String s)
    {
        return d.getInt(s, 0);
    }

    public long getLong(String s)
    {
        return d.getLong(s, 0L);
    }

    public long getMaxTransactionAge()
    {
        return getLong("maxTransactionAgeInSeconds");
    }

    public long getMaxTransactionAgeInSeconds()
    {
        return getMaxTransactionAge();
    }

    public long getMaxTransactionCount()
    {
        return getLong("maxTransactionCount");
    }

    public int getResponseBodyLimit()
    {
        return getInt("responseBodyLimit");
    }

    public long getServerTimestamp()
    {
        return getLong("serverTimestamp");
    }

    public int getStackTraceLimit()
    {
        return getInt("stackTraceLimit");
    }

    public String getString(String s)
    {
        if (!d.contains(s))
        {
            return null;
        } else
        {
            return d.getString(s, null);
        }
    }

    public boolean isCollectingNetworkErrors()
    {
        return getBoolean("collectNetworkErrors");
    }

    public void loadHarvestConfiguration()
    {
        if (a("dataToken"))
        {
            b.setData_token(getDataToken());
        }
        if (a("crossProcessId"))
        {
            b.setCross_process_id(getCrossProcessId());
        }
        if (a("serverTimestamp"))
        {
            b.setServer_timestamp(getServerTimestamp());
        }
        if (a("harvestIntervalInSeconds"))
        {
            b.setData_report_period((int)getHarvestIntervalInSeconds());
        }
        if (a("maxTransactionAgeInSeconds"))
        {
            b.setReport_max_transaction_age((int)getMaxTransactionAgeInSeconds());
        }
        if (a("maxTransactionCount"))
        {
            b.setReport_max_transaction_count((int)getMaxTransactionCount());
        }
        if (a("stackTraceLimit"))
        {
            b.setStack_trace_limit(getStackTraceLimit());
        }
        if (a("responseBodyLimit"))
        {
            b.setResponse_body_limit(getResponseBodyLimit());
        }
        if (a("collectNetworkErrors"))
        {
            b.setCollect_network_errors(isCollectingNetworkErrors());
        }
        if (a("errorLimit"))
        {
            b.setError_limit(getErrorLimit());
        }
        if (a("activityTraceMinUtilization"))
        {
            b.setActivity_trace_min_utilization(getActivityTraceMinUtilization());
        }
        a.info((new StringBuilder("Loaded configuration: ")).append(b).toString());
    }

    public void onHarvestComplete()
    {
        saveHarvestConfiguration(Harvest.getHarvestConfiguration());
    }

    public void onHarvestConnected()
    {
        saveHarvestConfiguration(Harvest.getHarvestConfiguration());
    }

    public void onHarvestDisabled()
    {
        String s = Agent.getDeviceInformation().getAgentVersion();
        a.info((new StringBuilder("Disabling agent version ")).append(s).toString());
        saveDisabledVersion(s);
    }

    public void onHarvestDisconnected()
    {
        a.info("Clearing harvest configuration.");
        clear();
    }

    public void save(String s, float f1)
    {
        f.lock();
        e.putFloat(s, f1);
        e.commit();
        f.unlock();
        return;
        Exception exception;
        exception;
        f.unlock();
        throw exception;
    }

    public void save(String s, int i)
    {
        f.lock();
        e.putInt(s, i);
        e.commit();
        f.unlock();
        return;
        Exception exception;
        exception;
        f.unlock();
        throw exception;
    }

    public void save(String s, long l)
    {
        f.lock();
        e.putLong(s, l);
        e.commit();
        f.unlock();
        return;
        Exception exception;
        exception;
        f.unlock();
        throw exception;
    }

    public void save(String s, String s1)
    {
        f.lock();
        e.putString(s, s1);
        e.commit();
        f.unlock();
        return;
        Exception exception;
        exception;
        f.unlock();
        throw exception;
    }

    public void save(String s, boolean flag)
    {
        f.lock();
        e.putBoolean(s, flag);
        e.commit();
        f.unlock();
        return;
        Exception exception;
        exception;
        f.unlock();
        throw exception;
    }

    public void saveActivityTraceMinUtilization(float f1)
    {
        c = Float.valueOf(f1);
        save("activityTraceMinUtilization", f1);
    }

    public void saveAgentVersion(String s)
    {
        save("agentVersion", s);
    }

    public void saveAndroidIdBugWorkAround(String s)
    {
        save("androidIdBugWorkAround", s);
    }

    public void saveAppToken(String s)
    {
        save("appToken", s);
    }

    public void saveCollectNetworkErrors(boolean flag)
    {
        save("collectNetworkErrors", flag);
    }

    public void saveCrossProcessId(String s)
    {
        save("crossProcessId", s);
    }

    public void saveDataToken(String s)
    {
        a.debug((new StringBuilder("!! saving data token: ")).append(s).toString());
        save("dataToken", s);
    }

    public void saveDisabledVersion(String s)
    {
        save("NewRelicAgentDisabledVersion", s);
    }

    public void saveErrorLimit(int i)
    {
        save("errorLimit", i);
    }

    public void saveHarvestConfiguration(HarvestConfiguration harvestconfiguration)
    {
        if (b.equals(harvestconfiguration))
        {
            return;
        }
        if (!harvestconfiguration.getDataToken().isValid())
        {
            harvestconfiguration.setData_token(b.getData_token());
        }
        a.info((new StringBuilder("Saving configuration: ")).append(harvestconfiguration).toString());
        saveDataToken(harvestconfiguration.getDataToken().toJsonString());
        saveCrossProcessId(harvestconfiguration.getCross_process_id());
        saveServerTimestamp(harvestconfiguration.getServer_timestamp());
        saveHarvestInterval(harvestconfiguration.getData_report_period());
        saveMaxTransactionAge(harvestconfiguration.getReport_max_transaction_age());
        saveMaxTransactionCount(harvestconfiguration.getReport_max_transaction_count());
        saveStackTraceLimit(harvestconfiguration.getStack_trace_limit());
        saveResponseBodyLimit(harvestconfiguration.getResponse_body_limit());
        saveCollectNetworkErrors(harvestconfiguration.isCollect_network_errors());
        saveErrorLimit(harvestconfiguration.getError_limit());
        saveActivityTraceMinUtilization((float)harvestconfiguration.getActivity_trace_min_utilization());
        loadHarvestConfiguration();
    }

    public void saveHarvestInterval(long l)
    {
        save("harvestIntervalInSeconds", l);
    }

    public void saveMaxTransactionAge(long l)
    {
        save("maxTransactionAgeInSeconds", l);
    }

    public void saveMaxTransactionCount(long l)
    {
        save("maxTransactionCount", l);
    }

    public void saveResponseBodyLimit(int i)
    {
        save("responseBodyLimit", i);
    }

    public void saveServerTimestamp(long l)
    {
        save("serverTimestamp", l);
    }

    public void saveStackTraceLimit(int i)
    {
        save("stackTraceLimit", i);
    }

}
