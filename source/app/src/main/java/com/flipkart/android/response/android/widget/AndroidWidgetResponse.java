// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.android.widget;

import com.flipkart.android.response.baseresponse.BaseResponse;
import java.util.ArrayList;

public class AndroidWidgetResponse extends BaseResponse
{

    private ArrayList items;
    private String type;

    public AndroidWidgetResponse()
    {
        items = new ArrayList();
    }

    public ArrayList getItems()
    {
        if (items == null)
        {
            items = new ArrayList();
        }
        return items;
    }

    public String getType()
    {
        return type;
    }

    public void setItems(ArrayList arraylist)
    {
        items = arraylist;
    }

    public void setType(String s)
    {
        type = s;
    }
}
