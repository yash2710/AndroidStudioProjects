// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.appconfig;

import com.flipkart.android.response.component.data.customvalues.Image;
import com.flipkart.android.response.customwidgetitemvalue.Action;

public class FBFData
{

    Action action;
    private Image end_image;
    private String title;

    public FBFData()
    {
    }

    public Action getAction()
    {
        return action;
    }

    public Image getEnd_image()
    {
        return end_image;
    }

    public String getTitle()
    {
        return title;
    }

    public void setAction(Action action1)
    {
        action = action1;
    }

    public void setEnd_image(Image image)
    {
        end_image = image;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
