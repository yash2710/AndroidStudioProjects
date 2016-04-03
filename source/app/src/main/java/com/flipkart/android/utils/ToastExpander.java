// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.widget.Toast;

// Referenced classes of package com.flipkart.android.utils:
//            z

public class ToastExpander
{

    public static final String TAG = "ToastExpander";
    private static Thread a;

    public ToastExpander()
    {
    }

    public static void cancel()
    {
        if (a != null)
        {
            a.interrupt();
        }
    }

    public static void showFor(Toast toast, long l)
    {
        toast.setDuration(0);
        z z1 = new z(l, toast);
        a = z1;
        z1.start();
    }
}
