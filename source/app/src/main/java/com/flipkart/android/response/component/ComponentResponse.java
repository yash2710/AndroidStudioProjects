// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component;

import com.flipkart.android.response.baseresponse.BaseResponse;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.response.component:
//            ComponentOmnitureData

public class ComponentResponse extends BaseResponse
{

    private ArrayList layoutData;
    private ComponentOmnitureData omnitureData;
    private long timeStamp;

    public ComponentResponse()
    {
        layoutData = new ArrayList();
        omnitureData = new ComponentOmnitureData();
    }

    public ArrayList getLayoutData()
    {
        FkLogger.debug("mnop", layoutData.toString());
        if (layoutData == null)
        {
            layoutData = new ArrayList();
        }
        return layoutData;
    }

    public ComponentOmnitureData getOmnitureData()
    {
        if (omnitureData == null)
        {
            omnitureData = new ComponentOmnitureData();
        }
        return omnitureData;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public void setLayoutData(ArrayList arraylist)
    {
        layoutData = arraylist;
    }

    public void setOmnitureData(ComponentOmnitureData componentomnituredata)
    {
        omnitureData = componentomnituredata;
    }

    public void setTimeStamp(long l)
    {
        timeStamp = l;
    }
}
