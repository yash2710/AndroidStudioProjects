// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.share;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import java.util.List;

// Referenced classes of package com.google.zxing.client.android.share:
//            a, c

final class d extends ArrayAdapter
{

    private List a;

    d(c c, Context context, int i, int j, List list, List list1)
    {
        a = list1;
        super(context, i, j, list);
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1 = super.getView(i, view, viewgroup);
        android.graphics.drawable.Drawable drawable = ((a)a.get(i)).b();
        if (drawable != null)
        {
            ((ImageView)view1.findViewById(com.google.zxing.client.android.R.id.app_picker_list_item_icon)).setImageDrawable(drawable);
        }
        return view1;
    }
}
