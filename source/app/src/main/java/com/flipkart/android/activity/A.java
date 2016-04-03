// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.content.Intent;
import android.view.View;
import com.flipkart.android.config.FlipkartPreferenceManager;

// Referenced classes of package com.flipkart.android.activity:
//            SignupActivity, LoginActivity

final class A
    implements android.view.View.OnClickListener
{

    private LoginActivity a;

    A(LoginActivity loginactivity)
    {
        a = loginactivity;
        super();
    }

    public final void onClick(View view)
    {
        FlipkartPreferenceManager.instance().saveIsFirstTimeLoad(Boolean.valueOf(false));
        FlipkartPreferenceManager.instance().setLoginShownOnFirstLoad(true);
        Intent intent = new Intent(a, com/flipkart/android/activity/SignupActivity);
        a.startActivityForResult(intent, 3);
    }
}
