// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.android.widget;

import java.util.LinkedHashMap;
import java.util.Map;

public class AndroidWidgetValue
{

    private Map image;

    public AndroidWidgetValue()
    {
        image = new LinkedHashMap();
    }

    public Map getImage()
    {
        if (image == null)
        {
            image = new LinkedHashMap();
        }
        return image;
    }

    public void setImage(Map map)
    {
        image = map;
    }
}
