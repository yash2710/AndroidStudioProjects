// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.utils.FacetData;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            bA, SubFilterFragment

final class bz extends ArrayAdapter
{

    private Context a;
    private int b;
    private ArrayList c;
    private SubFilterFragment d;

    public bz(SubFilterFragment subfilterfragment, Context context, int i, ArrayList arraylist)
    {
        d = subfilterfragment;
        super(context, 0x1090003, arraylist);
        c = new ArrayList();
        a = context;
        c.addAll(arraylist);
        b = i;
    }

    public final int getCount()
    {
        return c.size();
    }

    public final volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public final String getItem(int i)
    {
        return (String)c.get(i);
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        LinearLayout linearlayout;
        bA ba;
        String s;
        if (view == null)
        {
            LinearLayout linearlayout1 = (LinearLayout)View.inflate(a, b, null);
            ba = new bA(this, (byte)0);
            ba.a = (TextView)linearlayout1.findViewById(0x7f0a00b6);
            ba.b = (CheckBox)linearlayout1.findViewById(0x7f0a0055);
            ba.b.setFocusable(false);
            ba.b.setClickable(false);
            linearlayout1.setTag(ba);
            linearlayout = linearlayout1;
        } else
        {
            linearlayout = (LinearLayout)view;
            ba = (bA)linearlayout.getTag();
        }
        linearlayout.setVisibility(0);
        s = (String)c.get(i);
        if (StringUtils.isNullOrEmpty(s) || ((Map)d.a.getFilterMap().get(SubFilterFragment.d(d))).get(s) == null)
        {
            linearlayout.setVisibility(8);
            return linearlayout;
        }
        int j = ((FacetData)((Map)d.a.getFilterMap().get(SubFilterFragment.d(d))).get(s)).getCount();
        String s1 = (new StringBuilder()).append(s).append(" (").append(j).append(" results)").toString();
        ba.a.setText(s1);
        ba.a.setSingleLine(true);
        ba.a.setEllipsize(android.text.TextUtils.TruncateAt.END);
        if (!SubFilterFragment.g(d).contains((String)c.get(i)))
        {
            if (SubFilterFragment.b(d).contains((String)c.get(i)))
            {
                ba.a.setTypeface(Typeface.DEFAULT_BOLD);
                ba.b.setChecked(true);
                ba.a.setTextColor(Color.parseColor("#565656"));
                ba.b.setVisibility(0);
                return linearlayout;
            } else
            {
                ba.a.setTypeface(Typeface.DEFAULT);
                ba.a.setTextColor(Color.parseColor("#565656"));
                ba.b.setChecked(false);
                ba.b.setVisibility(0);
                return linearlayout;
            }
        } else
        {
            ba.b.setVisibility(8);
            ba.a.setTypeface(Typeface.DEFAULT);
            ba.a.setTextColor(Color.parseColor("#A4A4A4"));
            return linearlayout;
        }
    }
}
