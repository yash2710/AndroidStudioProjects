// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data;

import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.response.component.data:
//            WidgetItem

public class WidgetData
{

    ArrayList data;
    int dataId;
    WidgetItem header;
    String type;

    public WidgetData()
    {
    }

    public ArrayList getData()
    {
        return data;
    }

    public int getDataId()
    {
        return dataId;
    }

    public WidgetItem getHeader()
    {
        return header;
    }

    public String getType()
    {
        return type;
    }

    public void setData(ArrayList arraylist)
    {
        data = arraylist;
    }

    public void setDataId(int i)
    {
        dataId = i;
    }

    public void setHeader(WidgetItem widgetitem)
    {
        header = widgetitem;
    }

    public void setType(String s)
    {
        type = s;
    }
}
