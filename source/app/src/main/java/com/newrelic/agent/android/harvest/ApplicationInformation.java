// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

public class ApplicationInformation extends HarvestableArray
{

    private String a;
    private String b;
    private String c;

    public ApplicationInformation()
    {
    }

    public ApplicationInformation(String s, String s1, String s2)
    {
        this();
        a = s;
        b = s1;
        c = s2;
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        notEmpty(a);
        jsonarray.add(new JsonPrimitive(a));
        notEmpty(b);
        jsonarray.add(new JsonPrimitive(b));
        notEmpty(c);
        jsonarray.add(new JsonPrimitive(c));
        return jsonarray;
    }

    public String getPackageId()
    {
        return c;
    }

    public void setAppName(String s)
    {
        a = s;
    }

    public void setAppVersion(String s)
    {
        b = s;
    }

    public void setPackageId(String s)
    {
        c = s;
    }
}
