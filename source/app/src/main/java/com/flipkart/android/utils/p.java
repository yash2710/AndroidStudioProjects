// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

final class p extends ArrayAdapter
{

    private String a;
    private Context b;

    p(Context context, int i, List list, String s, Context context1)
    {
        a = s;
        b = context1;
        super(context, i, list);
    }

    public final View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        View view1 = super.getDropDownView(i, view, viewgroup);
        view1.setTag((new StringBuilder("on_click_item_swatch/")).append(a).append("/").append(((com.flipkart.android.fragments.model.ProductPageModel.SwatchValueModel)getItem(i)).getItemId()).toString());
        return view1;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        if (view == null)
        {
            view = ((LayoutInflater)b.getSystemService("layout_inflater")).inflate(0x7f03007c, null);
        }
        ((TextView)view.findViewById(0x7f0a0171)).setText((new StringBuilder("Select ")).append(a).toString());
        ((TextView)view.findViewById(0x7f0a0172)).setText(((com.flipkart.android.fragments.model.ProductPageModel.SwatchValueModel)getItem(i)).toString());
        view.setTag((new StringBuilder("on_click_item_swatch/")).append(a).append("/").append(((com.flipkart.android.fragments.model.ProductPageModel.SwatchValueModel)getItem(i)).getItemId()).toString());
        return view;
    }
}
