// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

// Referenced classes of package com.flipkart.android.fragments:
//            AllFiltersFragment

final class o
    implements android.view.View.OnClickListener
{

    private RadioButton a;
    private TextView b;
    private TextView c;
    private AllFiltersFragment d;

    o(AllFiltersFragment allfiltersfragment, RadioButton radiobutton, TextView textview, TextView textview1)
    {
        d = allfiltersfragment;
        a = radiobutton;
        b = textview;
        c = textview1;
        super();
    }

    public final void onClick(View view)
    {
        a.setChecked(false);
        AllFiltersFragment.a(d, 0);
        b.setTypeface(null, 1);
        c.setTypeface(null, 0);
    }
}
