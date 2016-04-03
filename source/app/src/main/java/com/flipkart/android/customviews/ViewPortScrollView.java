// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;
import com.flipkart.android.utils.ScreenMathUtils;

// Referenced classes of package com.flipkart.android.customviews:
//            ScrollViewFixed, IViewPortView

public class ViewPortScrollView extends ScrollViewFixed
{

    private LinearLayout a;
    private IViewPortView b;

    public ViewPortScrollView(Context context)
    {
        super(context);
        setVerticalScrollBarEnabled(false);
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        a = new LinearLayout(context);
        a.setOrientation(1);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        a.setLayoutParams(layoutparams);
        a.setPadding(0, 0, 0, ScreenMathUtils.dpToPx(15, context));
        addView(a);
        setTag("scrollView");
    }

    public LinearLayout getRootLayout()
    {
        return a;
    }

    public IViewPortView getiViewPortView()
    {
        return b;
    }

    public boolean isInViewPort(View view)
    {
        Rect rect = new Rect();
        getDrawingRect(rect);
        return view.getLocalVisibleRect(rect);
    }

    protected void onScrollChanged(int i, int j, int k, int l)
    {
        super.onScrollChanged(i, j, k, l);
        View view;
        if (b != null)
        {
            if ((view = b.getView()) != null && isInViewPort(view))
            {
                b.isInViewPort();
                return;
            }
        }
    }

    public void setiViewPortView(IViewPortView iviewportview)
    {
        b = iviewportview;
    }
}
