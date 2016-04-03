// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

// Referenced classes of package com.flipkart.android.utils:
//            ToastMessageUtils, ScreenMathUtils, B, C

final class A extends Thread
{

    final Activity a;
    final String b;
    private boolean c;

    A(Activity activity, String s, boolean flag)
    {
        a = activity;
        b = s;
        c = flag;
        super();
    }

    public final void run()
    {
        ToastMessageUtils.a(((ViewGroup)a.getWindow().getDecorView()).findViewWithTag("toast_overlay"));
        if (ToastMessageUtils.a() != null) goto _L2; else goto _L1
_L1:
        ToastMessageUtils.a(((LayoutInflater)a.getApplicationContext().getSystemService("layout_inflater")).inflate(0x7f0300c2, null));
        android.widget.FrameLayout.LayoutParams layoutparams = new android.widget.FrameLayout.LayoutParams(-1, -2);
        layoutparams.gravity = 0x800033;
        layoutparams.setMargins(0, ScreenMathUtils.dpToPx(74, a.getApplicationContext()), 0, 0);
        ToastMessageUtils.a().setLayoutParams(layoutparams);
        ToastMessageUtils.a().setTag("toast_overlay");
        ((TextView)ToastMessageUtils.a().findViewById(0x7f0a00b6)).setText(b);
        if (a != null && !a.isFinishing() && ToastMessageUtils.a() != null)
        {
            a.runOnUiThread(new B(this));
        }
_L4:
        if (!c)
        {
            break MISSING_BLOCK_LABEL_235;
        }
        sleep(3500L);
_L5:
        ToastMessageUtils.a(a);
        return;
_L2:
        if (a == null || a.isFinishing() || ToastMessageUtils.a() == null) goto _L4; else goto _L3
_L3:
        Exception exception;
        a.runOnUiThread(new C(this));
          goto _L4
        try
        {
            sleep(2000L);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            ToastMessageUtils.a(a);
            return;
        }
          goto _L5
    }
}
