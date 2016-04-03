// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import com.flipkart.android.log.CrashLoggerUtils;

// Referenced classes of package com.flipkart.android.utils:
//            E, D, StringUtils, A

public class ToastMessageUtils
{

    private static View a = null;
    private static Thread b = null;

    public ToastMessageUtils()
    {
    }

    static View a()
    {
        return a;
    }

    static View a(View view)
    {
        a = view;
        return view;
    }

    static void a(Activity activity)
    {
        if (activity != null && !activity.isFinishing() && a != null)
        {
            activity.runOnUiThread(new E(activity));
        }
    }

    public static void dismissToast(Activity activity)
    {
        CrashLoggerUtils.pushAndUpdate("dismissing toast @ ToastMessageUtils");
        if (b != null)
        {
            b.interrupt();
            if (activity != null && !activity.isFinishing() && a != null)
            {
                (new Handler()).post(new D(activity));
            }
        }
    }

    public static void showErrorToastMessage(String s, Activity activity, boolean flag)
    {
        if (activity != null && !StringUtils.isNullOrEmpty(s))
        {
            if (b != null)
            {
                b.interrupt();
                b = null;
            }
            A a1 = new A(activity, s, flag);
            b = a1;
            a1.start();
        }
    }

}
