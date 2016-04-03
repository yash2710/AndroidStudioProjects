// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.graphics.Typeface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            SubFilterFragment

final class bv
    implements android.widget.AdapterView.OnItemClickListener
{

    private SubFilterFragment a;

    bv(SubFilterFragment subfilterfragment)
    {
        a = subfilterfragment;
        super();
    }

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        TextView textview;
        CheckBox checkbox;
label0:
        {
            textview = (TextView)view.findViewById(0x7f0a00b6);
            checkbox = (CheckBox)view.findViewById(0x7f0a0055);
            if (checkbox.getVisibility() == 0)
            {
                if (!SubFilterFragment.b(a).contains(SubFilterFragment.a(a).get(i)))
                {
                    break label0;
                }
                textview.setTypeface(Typeface.DEFAULT);
                SubFilterFragment.b(a).remove(SubFilterFragment.a(a).get(i));
                checkbox.setChecked(false);
            }
            return;
        }
        textview.setTypeface(Typeface.DEFAULT_BOLD);
        SubFilterFragment.b(a).add(SubFilterFragment.a(a).get(i));
        checkbox.setChecked(true);
    }
}
