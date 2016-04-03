// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import com.flipkart.android.config.FlipkartPreferenceManager;

// Referenced classes of package com.flipkart.android.activity:
//            LoginActivity

final class r
    implements com.facebook.widget.LoginButton.FbLoginClickListener
{

    private LoginActivity a;

    r(LoginActivity loginactivity)
    {
        a = loginactivity;
        super();
    }

    public final boolean onClick()
    {
        FlipkartPreferenceManager.instance().saveIsFirstTimeLoad(Boolean.valueOf(false));
        FlipkartPreferenceManager.instance().setLoginShownOnFirstLoad(true);
        if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            LoginActivity.a(a, -1);
            return true;
        } else
        {
            LoginActivity.a(a);
            return false;
        }
    }
}
