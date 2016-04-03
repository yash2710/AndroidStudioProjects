// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.history;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.zxing.Result;
import java.util.ArrayList;

// Referenced classes of package com.google.zxing.client.android.history:
//            HistoryItem

final class c extends ArrayAdapter
{

    private final Activity a;

    c(Activity activity)
    {
        super(activity, com.google.zxing.client.android.R.layout.history_list_item, new ArrayList());
        a = activity;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        LinearLayout linearlayout;
        HistoryItem historyitem;
        Result result;
        String s2;
        String s3;
        if (view instanceof LinearLayout)
        {
            linearlayout = (LinearLayout)view;
        } else
        {
            linearlayout = (LinearLayout)LayoutInflater.from(a).inflate(com.google.zxing.client.android.R.layout.history_list_item, viewgroup, false);
        }
        historyitem = (HistoryItem)getItem(i);
        result = historyitem.getResult();
        if (result != null)
        {
            String s4 = result.getText();
            String s5 = historyitem.getDisplayAndDetails();
            s2 = s4;
            s3 = s5;
        } else
        {
            Resources resources = getContext().getResources();
            String s = resources.getString(com.google.zxing.client.android.R.string.history_empty);
            String s1 = resources.getString(com.google.zxing.client.android.R.string.history_empty_detail);
            s2 = s;
            s3 = s1;
        }
        ((TextView)linearlayout.findViewById(com.google.zxing.client.android.R.id.history_title)).setText(s2);
        ((TextView)linearlayout.findViewById(com.google.zxing.client.android.R.id.history_detail)).setText(s3);
        return linearlayout;
    }
}
