// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.utils:
//            ProductPageUgcBuilder, ProductPageReviewContext

final class v extends BaseAdapter
{

    public v()
    {
    }

    public final int getCount()
    {
        return ProductPageUgcBuilder.b().getReviewList().size();
    }

    public final Object getItem(int i)
    {
        return null;
    }

    public final long getItemId(int i)
    {
        return 0L;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1;
        try
        {
            view1 = ProductPageUgcBuilder.a(i, view);
        }
        catch (Exception exception)
        {
            return new View(ProductPageUgcBuilder.a());
        }
        return view1;
    }
}
