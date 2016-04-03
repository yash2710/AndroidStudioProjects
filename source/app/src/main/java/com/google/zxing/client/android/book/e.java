// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;

// Referenced classes of package com.google.zxing.client.android.book:
//            SearchBookContentsListItem, f

final class e extends ArrayAdapter
{

    e(Context context, List list)
    {
        super(context, com.google.zxing.client.android.R.layout.search_book_contents_list_item, 0, list);
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        if (view != null) goto _L2; else goto _L1
_L1:
        view = (SearchBookContentsListItem)LayoutInflater.from(getContext()).inflate(com.google.zxing.client.android.R.layout.search_book_contents_list_item, viewgroup, false);
_L6:
        view.set((f)getItem(i));
_L4:
        return view;
_L2:
        if (!(view instanceof SearchBookContentsListItem)) goto _L4; else goto _L3
_L3:
        view = (SearchBookContentsListItem)view;
        if (true) goto _L6; else goto _L5
_L5:
    }
}
