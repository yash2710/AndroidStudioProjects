// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.android.widget;


// Referenced classes of package com.flipkart.android.response.android.widget:
//            AndroidWidgetActionParams

public class AndroidWidgetAction
{

    private String omnitureData;
    private AndroidWidgetActionParams params;
    private String screenType;

    public AndroidWidgetAction()
    {
        params = new AndroidWidgetActionParams();
    }

    public String getOmnitureData()
    {
        return omnitureData;
    }

    public AndroidWidgetActionParams getParams()
    {
        return params;
    }

    public String getScreenType()
    {
        return screenType;
    }

    public void setOmnitureData(String s)
    {
        omnitureData = s;
    }

    public void setParams(AndroidWidgetActionParams androidwidgetactionparams)
    {
        params = androidwidgetactionparams;
    }

    public void setScreenType(String s)
    {
        screenType = s;
    }
}
