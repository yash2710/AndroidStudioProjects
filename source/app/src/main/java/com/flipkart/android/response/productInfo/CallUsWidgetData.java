// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

public class CallUsWidgetData
{

    private String id;
    private ArrayList items;
    private long timeStamp;
    private String type;

    public CallUsWidgetData()
    {
        items = new ArrayList();
    }

    public String getId()
    {
        return id;
    }

    public ArrayList getItems()
    {
        if (items == null)
        {
            items = new ArrayList();
        }
        return items;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public String getType()
    {
        return type;
    }

    public void setId(String s)
    {
        id = s;
    }

    public void setItems(ArrayList arraylist)
    {
        FkLogger.debug("mnop", (new StringBuilder("size of setItems(ArrayList<WidgetItem> items) = ")).append(arraylist.size()).toString());
        items = arraylist;
    }

    public void setTimeStamp(long l)
    {
        timeStamp = l;
    }

    public void setType(String s)
    {
        type = s;
    }
}
