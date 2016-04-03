// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.measurement;

import java.util.Hashtable;

public final class ADMS_RequestProperties
{

    private String a;
    private Hashtable b;

    protected ADMS_RequestProperties(String s)
    {
        b = new Hashtable();
        String as[] = s.split("\t");
        if (as.length > 0 && as[0].length() > 0)
        {
            a = as[0];
        }
        for (int i = 1; i < as.length && i + 1 <= as.length; i += 2)
        {
            String s1 = as[i];
            String s2 = as[i + 1];
            if (s1.trim().length() > 0 && s2.trim().length() > 0)
            {
                b.put(s1, s2);
            }
        }

    }

    protected final Hashtable getHeaders()
    {
        return b;
    }

    protected final String getUrl()
    {
        return a;
    }
}
