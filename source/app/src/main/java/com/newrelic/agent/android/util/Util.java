// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Util
{

    private static final Random a = new Random();

    public Util()
    {
    }

    public static Random getRandom()
    {
        return a;
    }

    public static String sanitizeUrl(String s)
    {
        if (s == null)
        {
            return null;
        }
        URL url;
        StringBuffer stringbuffer;
        try
        {
            url = new URL(s);
        }
        catch (MalformedURLException malformedurlexception)
        {
            return null;
        }
        stringbuffer = new StringBuffer();
        stringbuffer.append(url.getProtocol());
        stringbuffer.append("://");
        stringbuffer.append(url.getHost());
        if (url.getPort() != -1)
        {
            stringbuffer.append(":");
            stringbuffer.append(url.getPort());
        }
        stringbuffer.append(url.getPath());
        return stringbuffer.toString();
    }

    public static String slurp(InputStream inputstream)
    {
        char ac[] = new char[8192];
        StringBuilder stringbuilder = new StringBuilder();
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        do
        {
            int i = bufferedreader.read(ac);
            if (i >= 0)
            {
                stringbuilder.append(ac, 0, i);
            } else
            {
                return stringbuilder.toString();
            }
        } while (true);
    }

}
