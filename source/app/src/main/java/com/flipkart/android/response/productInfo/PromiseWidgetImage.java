// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import com.flipkart.android.response.component.data.customvalues.Image;
import com.flipkart.android.response.customwidgetitemvalue.Action;

public class PromiseWidgetImage extends Image
{

    Action action;

    public PromiseWidgetImage()
    {
    }

    public Action getAction()
    {
        return action;
    }

    public void setAction(Action action1)
    {
        action = action1;
    }
}
