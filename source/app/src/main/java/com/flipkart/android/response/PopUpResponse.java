// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response;

import com.flipkart.android.response.component.data.customvalues.Image;
import com.flipkart.android.response.customwidgetitemvalue.Action;

public class PopUpResponse
{

    Action action1;
    Action action2;
    String buttonText1;
    String buttonText2;
    Image imageMap;
    String pageType;
    String subTitle;
    String title;

    public PopUpResponse()
    {
    }

    public Action getAction1()
    {
        return action1;
    }

    public Action getAction2()
    {
        return action2;
    }

    public String getButtonText1()
    {
        return buttonText1;
    }

    public String getButtonText2()
    {
        return buttonText2;
    }

    public Image getImageMap()
    {
        return imageMap;
    }

    public String getPageType()
    {
        return pageType;
    }

    public String getSubTitle()
    {
        return subTitle;
    }

    public String getTitle()
    {
        return title;
    }

    public void setAction1(Action action)
    {
        action1 = action;
    }

    public void setAction2(Action action)
    {
        action2 = action;
    }

    public void setButtonText1(String s)
    {
        buttonText1 = s;
    }

    public void setButtonText2(String s)
    {
        buttonText2 = s;
    }

    public void setImageMap(Image image)
    {
        imageMap = image;
    }

    public void setPageType(String s)
    {
        pageType = s;
    }

    public void setSubTitle(String s)
    {
        subTitle = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
