// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.view.View;

// Referenced classes of package com.flipkart.android.customwidget:
//            NewTitleWidget

class width
{

    final NewTitleWidget this$0;
    private View view;
    private int width;

    public View getView()
    {
        return view;
    }

    public int getWidth()
    {
        return width;
    }

    public void setView(View view1)
    {
        view = view1;
    }

    public void setWidth(int i)
    {
        width = i;
    }

    public (View view1, int i)
    {
        this$0 = NewTitleWidget.this;
        super();
        view = view1;
        width = i;
    }
}
