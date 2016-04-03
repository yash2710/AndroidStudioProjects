// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.shareapp.params;


public class ShareAppParams
{

    private String a;

    public ShareAppParams(String s)
    {
        a = s;
    }

    public byte[] generateToByteArray()
    {
        return (new StringBuilder("offerId=")).append(a).toString().getBytes();
    }

    public String generateURI()
    {
        return "";
    }
}
