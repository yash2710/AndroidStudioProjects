// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.share;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

final class b extends BaseAdapter
{

    private final Context a;
    private final Cursor b;

    b(Context context, Cursor cursor)
    {
        a = context;
        b = cursor;
    }

    public final int getCount()
    {
        return b.getCount();
    }

    public final Object getItem(int i)
    {
        return null;
    }

    public final long getItemId(int i)
    {
        return (long)i;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        LinearLayout linearlayout;
        if (view instanceof LinearLayout)
        {
            linearlayout = (LinearLayout)view;
        } else
        {
            linearlayout = (LinearLayout)LayoutInflater.from(a).inflate(com.google.zxing.client.android.R.layout.bookmark_picker_list_item, viewgroup, false);
        }
        if (!b.isClosed())
        {
            b.moveToPosition(i);
            String s = b.getString(0);
            ((TextView)linearlayout.findViewById(com.google.zxing.client.android.R.id.bookmark_title)).setText(s);
            String s1 = b.getString(1);
            ((TextView)linearlayout.findViewById(com.google.zxing.client.android.R.id.bookmark_url)).setText(s1);
        }
        return linearlayout;
    }
}
