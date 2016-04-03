// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.flipkart.android.config.FlipkartPreferenceManager;

// Referenced classes of package com.flipkart.android.activity:
//            LoginActivity, HomeFragmentHolderActivity

final class s
    implements android.view.View.OnClickListener
{

    private LoginActivity a;

    s(LoginActivity loginactivity)
    {
        a = loginactivity;
        super();
    }

    public final void onClick(View view)
    {
        if (LoginActivity.f(a).getVisibility() == 0)
        {
            FlipkartPreferenceManager.instance().saveIsFirstTimeLoad(Boolean.valueOf(false));
            FlipkartPreferenceManager.instance().setLoginShownOnFirstLoad(true);
        }
        Intent intent = new Intent(a.getApplicationContext(), com/flipkart/android/activity/HomeFragmentHolderActivity);
        intent.setAction(a.getIntent().getAction());
        intent.setData(a.getIntent().getData());
        if (LoginActivity.f(a).getVisibility() == 0)
        {
            intent.addFlags(32768);
        }
        LoginActivity.g(a);
        a.startActivity(intent);
        a.finish();
    }
}
