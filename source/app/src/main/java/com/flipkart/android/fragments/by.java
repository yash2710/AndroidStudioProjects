// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import com.flipkart.android.activity.FilterActivity;
import com.flipkart.android.log.CrashLoggerUtils;

// Referenced classes of package com.flipkart.android.fragments:
//            SubFilterFragment

final class by
    implements android.view.View.OnClickListener
{

    private SubFilterFragment a;

    by(SubFilterFragment subfilterfragment)
    {
        a = subfilterfragment;
        super();
    }

    public final void onClick(View view)
    {
        CrashLoggerUtils.pushAndUpdate("cancel filtering");
        SubFilterFragment.f(a).putContextToCache();
        SubFilterFragment.f(a).finish();
    }
}
