// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


public class 
{

    public String height;
    public String quality;
    public String width;

    public static  parseString(String s)
    {
        String as[] = s.split(",");
        if (as.length == 3)
        {
              = new <init>();
            .height = as[0];
            .width = as[1];
            .quality = as[2];
            return ;
        } else
        {
            return null;
        }
    }

    public ()
    {
    }
}
