// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import java.util.ArrayList;

public class ProductExtraMessages
{

    private boolean disableBuy;
    private ArrayList extraMessages;
    private String message;

    public ProductExtraMessages()
    {
        extraMessages = new ArrayList();
    }

    public ArrayList getExtraMessages()
    {
        if (extraMessages == null)
        {
            extraMessages = new ArrayList();
        }
        return extraMessages;
    }

    public String getMessage()
    {
        return message;
    }

    public boolean isDisableBuy()
    {
        return disableBuy;
    }

    public void setDisableBuy(boolean flag)
    {
        disableBuy = flag;
    }

    public void setExtraMessages(ArrayList arraylist)
    {
        extraMessages = arraylist;
    }

    public void setMessage(String s)
    {
        message = s;
    }
}
