// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.view.View;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.login.GoogleTokenFetcher;

// Referenced classes of package com.flipkart.android.activity:
//            LoginActivity

final class y
    implements android.view.View.OnClickListener
{

    private LoginActivity a;

    y(LoginActivity loginactivity)
    {
        a = loginactivity;
        super();
    }

    public final void onClick(View view)
    {
        FlipkartPreferenceManager.instance().saveIsFirstTimeLoad(Boolean.valueOf(false));
        FlipkartPreferenceManager.instance().setLoginShownOnFirstLoad(true);
        if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            LoginActivity.a(a, -1);
            return;
        } else
        {
            LoginActivity.a(a);
            a.a.fetchToken(0, 1);
            return;
        }
    }
}
