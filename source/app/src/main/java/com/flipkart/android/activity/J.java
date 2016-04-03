// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.content.Intent;
import android.view.View;

// Referenced classes of package com.flipkart.android.activity:
//            SignupActivity, HomeFragmentHolderActivity

final class J
    implements android.view.View.OnClickListener
{

    private SignupActivity a;

    J(SignupActivity signupactivity)
    {
        a = signupactivity;
        super();
    }

    public final void onClick(View view)
    {
        SignupActivity.e(a);
        Intent intent = new Intent(a.getApplicationContext(), com/flipkart/android/activity/HomeFragmentHolderActivity);
        a.startActivity(intent);
        a.finish();
    }
}
