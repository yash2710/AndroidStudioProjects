// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import android.content.Context;
import android.content.res.AssetManager;
import com.newrelic.agent.android.AndroidAgentImpl;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.logging.AndroidAgentLog;
import com.newrelic.agent.android.logging.NullAgentLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Bootstrap
{

    public static final String CONF_NEW_RELIC_APPLICATION_TOKEN = "new_relic_application_token";
    private static final AgentLog a = AgentLogManager.getAgentLog();

    public Bootstrap()
    {
    }

    private static boolean a(String s)
    {
        return "on".equals(s) || "1".equals(s) || "true".equals(s) || "yes".equals(s);
    }

    public static void go(Context context)
    {
        InputStream inputstream = context.getAssets().open("newrelic.properties");
        Properties properties;
        properties = new Properties();
        properties.load(inputstream);
        if (a(properties.getProperty("new_relic_enable_logging", "true"))) goto _L2; else goto _L1
_L1:
        AgentLogManager.setAgentLog(new NullAgentLog());
_L3:
        String s;
        String s1;
        a.debug("Trying to bootstrap New Relic");
        s = properties.getProperty("new_relic_collector", "mobile-collector.newrelic.com");
        s1 = properties.getProperty("new_relic_application_token", null);
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_290;
        }
        boolean flag;
        boolean flag1;
        String s2;
        a.info("License key found! Starting the New Relic agent.");
        a.debug((new StringBuilder("Collector host is ")).append(s).toString());
        flag = a(properties.getProperty("new_relic_enable_ssl", "true"));
        flag1 = a(properties.getProperty("new_relic_enable_location", "false"));
        s2 = properties.getProperty("new_relic_application_name", null);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_174;
        }
        a.debug("SSL enabled");
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_204;
        }
        a.debug((new StringBuilder("App name override: ")).append(s2).toString());
        AndroidAgentImpl.init(context, s1, s, flag, flag1, s2);
_L4:
        IOException ioexception;
        try
        {
            inputstream.close();
            return;
        }
        catch (Throwable throwable)
        {
            a.error("Error occurred while starting the New Relic agent!", throwable);
        }
        break MISSING_BLOCK_LABEL_267;
        ioexception;
        AgentLogManager.setAgentLog(new AndroidAgentLog());
        a.error("Could not open assets/newrelic.properties! New Relic will not start.");
        AgentLogManager.setAgentLog(new NullAgentLog());
        return;
        return;
_L2:
        AgentLogManager.setAgentLog(new AndroidAgentLog());
          goto _L3
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
        a.warning("No licenase key found: New Relic will not start");
          goto _L4
    }

}
