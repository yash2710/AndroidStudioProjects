// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.imagematrix.params;


public class ImageMatrixParams
{

    private String a;
    private long b;

    public ImageMatrixParams(String s, long l)
    {
        a = s;
        b = l;
    }

    public String generateURI()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("/").append(a);
        if (b != -1L)
        {
            stringbuilder.append("?hashCode=").append(b);
        }
        return stringbuilder.toString();
    }
}
