// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.appconfig;


// Referenced classes of package com.flipkart.android.response.appconfig:
//            ConfigResponseData

public class ConfigResponse
{

    ConfigResponseData config;
    long ts;

    public ConfigResponse()
    {
    }

    public ConfigResponseData getConfig()
    {
        return config;
    }

    public long getTs()
    {
        return ts;
    }

    public void setConfig(ConfigResponseData configresponsedata)
    {
        config = configresponsedata;
    }

    public void setTs(long l)
    {
        ts = l;
    }
}
