// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.Window;

// Referenced classes of package com.flipkart.android.utils:
//            ToastMessageUtils

final class D
    implements Runnable
{

    private Activity a;

    D(Activity activity)
    {
        a = activity;
        super();
    }

    public final void run()
    {
        try
        {
            ((ViewGroup)a.getWindow().getDecorView()).removeView(ToastMessageUtils.a());
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }
}
