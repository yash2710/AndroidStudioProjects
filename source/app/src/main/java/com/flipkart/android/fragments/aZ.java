// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.content.Intent;
import android.view.View;
import com.flipkart.android.analytics.TrackingHelper;

// Referenced classes of package com.flipkart.android.fragments:
//            bi, SearchFragment

final class aZ
    implements android.view.View.OnClickListener
{

    private SearchFragment a;

    aZ(SearchFragment searchfragment)
    {
        a = searchfragment;
        super();
    }

    public final void onClick(View view)
    {
        SearchFragment.a(a, bi.c);
        TrackingHelper.sendBarCodeClicked();
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        a.startActivityForResult(intent, 12);
    }
}
