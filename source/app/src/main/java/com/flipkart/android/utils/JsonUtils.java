// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.logging.FkLogger;
import org.json.JSONObject;

public class JsonUtils
{

    public JsonUtils()
    {
    }

    public static boolean getBoolean(JSONObject jsonobject, String s, Boolean boolean1)
    {
        Boolean boolean2 = Boolean.valueOf(jsonobject.getBoolean(s));
        boolean1 = boolean2;
_L2:
        return boolean1.booleanValue();
        Exception exception;
        exception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static int getInt(JSONObject jsonobject, String s)
    {
        int i;
        try
        {
            i = jsonobject.getInt(s);
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
            return -1;
        }
        return i;
    }

    public static String getString(JSONObject jsonobject, String s)
    {
        String s1 = "";
        try
        {
            s1 = jsonobject.getString(s);
        }
        catch (Exception exception)
        {
            return s1;
        }
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        if (!s1.equals("null"))
        {
            break MISSING_BLOCK_LABEL_25;
        }
        s1 = "";
        return s1;
    }
}
