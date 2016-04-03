// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.flipkart.android.response.OmuProdData;
import com.flipkart.android.response.component.data.customvalues.OMUValue;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            OMUInfiniteListView, BucketWidget

class <init> extends BaseAdapter
{

    final OMUInfiniteListView this$0;

    public int getCount()
    {
        return OMUInfiniteListView.access$600(OMUInfiniteListView.this);
    }

    public Object getItem(int i)
    {
        return dataList.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1;
        int j;
        Exception exception;
        View view2;
        boolean flag;
        View view3;
        if (view == null)
        {
            view1 = new View(mContext);
        } else
        {
            view1 = view;
        }
        j = OMUInfiniteListView.access$900(OMUInfiniteListView.this, i);
        if (j >= dataList.size())
        {
            break MISSING_BLOCK_LABEL_130;
        }
        if (((OmuProdData)dataList.get(j)).getValue().isBanner())
        {
            break MISSING_BLOCK_LABEL_95;
        }
        flag = view instanceof BucketWidget;
        view3 = null;
        if (!flag)
        {
            view3 = view;
        }
        return OMUInfiniteListView.access$1000(OMUInfiniteListView.this, j, view3);
        if (!(view instanceof BucketWidget))
        {
            view = null;
        }
        try
        {
            view2 = OMUInfiniteListView.access$1100(OMUInfiniteListView.this, j, view);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            return view1;
        }
        return view2;
        return view1;
    }

    private ()
    {
        this$0 = OMUInfiniteListView.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
