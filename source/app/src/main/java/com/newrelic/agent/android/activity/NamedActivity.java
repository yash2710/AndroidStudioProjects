// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.activity;


// Referenced classes of package com.newrelic.agent.android.activity:
//            BaseMeasuredActivity

public class NamedActivity extends BaseMeasuredActivity
{

    public NamedActivity(String s)
    {
        setName(s);
        setAutoInstrumented(false);
    }

    public void rename(String s)
    {
        setName(s);
    }
}
