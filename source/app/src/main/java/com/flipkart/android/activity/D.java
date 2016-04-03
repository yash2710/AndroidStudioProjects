// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.content.Intent;
import android.view.View;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;

// Referenced classes of package com.flipkart.android.activity:
//            LoginActivity, HomeFragmentHolderActivity

final class D
    implements android.view.View.OnClickListener
{

    private LoginActivity a;

    D(LoginActivity loginactivity)
    {
        a = loginactivity;
        super();
    }

    public final void onClick(View view)
    {
        FlipkartPreferenceManager.instance().saveIsFirstTimeLoad(Boolean.valueOf(false));
        FlipkartPreferenceManager.instance().setLoginShownOnFirstLoad(true);
        Intent intent = new Intent(a.getApplicationContext(), com/flipkart/android/activity/HomeFragmentHolderActivity);
        intent.addFlags(32768);
        intent.setAction(a.getIntent().getAction());
        intent.setData(a.getIntent().getData());
        TrackingHelper.sendLoginSkipClicked();
        a.startActivity(intent);
        a.finish();
    }
}
