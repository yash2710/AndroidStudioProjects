// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


public class TimerUtils
{

    public TimerUtils()
    {
    }

    public static String getTimerAsText(long l)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (l < 0L)
        {
            l = 0L;
        }
        int i;
        int j;
        String s;
        String s1;
        String s2;
        int k;
        if (l >= 0x15180L)
        {
            k = (int)(l / 0x15180L);
            i = (int)((l - (long)(60 * (60 * (k * 24)))) / 3600L);
            j = (int)((l - (long)(60 * (60 * (k * 24)) + 60 * (i * 60))) / 60L);
            s = "D:";
            s1 = "H:";
            s2 = "M";
        } else
        if (l >= 3600L)
        {
            k = (int)(l / 3600L);
            i = (int)((l - (long)(60 * (k * 60))) / 60L);
            j = (int)(l - (long)(60 * (k * 60) + i * 60));
            s = "H:";
            s1 = "M:";
            s2 = "S";
        } else
        {
            i = (int)(l / 60L);
            j = (int)(l - (long)(i * 60));
            s = "H:";
            s1 = "M:";
            s2 = "S";
            k = 0;
        }
        if (k < 10)
        {
            stringbuilder.append("0");
        }
        stringbuilder.append((new StringBuilder()).append(k).append(s).toString());
        if (i < 10)
        {
            stringbuilder.append("0");
        }
        stringbuilder.append((new StringBuilder()).append(i).append(s1).toString());
        if (!s1.equals("S"))
        {
            if (j < 10)
            {
                stringbuilder.append("0");
            }
            stringbuilder.append((new StringBuilder()).append(j).append(s2).toString());
        }
        return stringbuilder.toString();
    }
}
