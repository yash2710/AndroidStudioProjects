// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.measurement;

import java.util.Hashtable;

public class ADMS_ContextData
{

    protected Hashtable contextData;
    protected Object value;

    public ADMS_ContextData()
    {
        value = null;
        contextData = new Hashtable();
    }

    protected boolean containsKey(String s)
    {
        return contextData.containsKey(s);
    }

    protected ADMS_ContextData get(String s)
    {
        return (ADMS_ContextData)contextData.get(s);
    }

    protected void put(String s, ADMS_ContextData adms_contextdata)
    {
        contextData.put(s, adms_contextdata);
    }

    public String toString()
    {
        this;
        JVM INSTR monitorenter ;
        String s = "";
        String s1;
        if (value != null)
        {
            s = value.toString();
        }
        s1 = (new StringBuilder()).append(super.toString()).append(s).toString();
        this;
        JVM INSTR monitorexit ;
        return s1;
        Exception exception;
        exception;
        throw exception;
    }
}
