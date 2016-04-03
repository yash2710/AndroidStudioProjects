// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.view.View;

// Referenced classes of package com.flipkart.android.activity:
//            HomeFragmentHolderActivity

final class p
    implements android.view.View.OnClickListener
{

    private HomeFragmentHolderActivity a;

    p(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        a = homefragmentholderactivity;
        super();
    }

    public final void onClick(View view)
    {
        a.openSearchPage();
    }
}
