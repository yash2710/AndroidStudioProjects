// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.shareapp;


public class ShareAppResponse
{

    String message;
    boolean status;

    public ShareAppResponse()
    {
    }

    public String getMessage()
    {
        return message;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setMessage(String s)
    {
        message = s;
    }

    public void setStatus(boolean flag)
    {
        status = flag;
    }
}
