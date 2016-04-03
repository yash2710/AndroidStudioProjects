// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            SubFilterFragment, bz

final class bw
    implements android.view.View.OnClickListener
{

    private SubFilterFragment a;

    bw(SubFilterFragment subfilterfragment)
    {
        a = subfilterfragment;
        super();
    }

    public final void onClick(View view)
    {
        SubFilterFragment.b(a).clear();
        SubFilterFragment.c(a).notifyDataSetChanged();
    }
}
