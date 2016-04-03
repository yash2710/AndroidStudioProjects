// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.content.res.AssetManager;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.logging.FkLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MiscUtils
{

    public MiscUtils()
    {
    }

    public static String readConfigFromAssets(String s)
    {
        BufferedReader bufferedreader;
        String s2;
        bufferedreader = new BufferedReader(new InputStreamReader(FlipkartApplication.getAppContext().getAssets().open(s)));
        s2 = bufferedreader.readLine();
        String s1 = s2;
        bufferedreader.close();
        return s1;
        IOException ioexception;
        ioexception;
        IOException ioexception1;
        s1 = "";
        ioexception1 = ioexception;
_L2:
        FkLogger.printStackTrace(ioexception1);
        return s1;
        ioexception1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static int roundoffDecimal(float f)
    {
        return (int)f;
    }
}
