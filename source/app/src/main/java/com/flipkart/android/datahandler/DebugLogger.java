// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.flipkart.fk_android_batchnetworking.BatchNetworking;
import org.json.JSONObject;

public class DebugLogger
{

    public DebugLogger()
    {
    }

    public static void logDebuggingJson(JSONObject jsonobject)
    {
        BatchNetworking.getDefaultInstance().pushDataForGroupId(jsonobject, "debug_logger");
    }
}
