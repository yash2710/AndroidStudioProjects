// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;


// Referenced classes of package com.flipkart.android.response.discovery:
//            OmnitureData

public class MetaDataResponse
{

    private String layout;
    private OmnitureData omnitureData;
    private String title;
    private int totalProduct;

    public MetaDataResponse()
    {
        omnitureData = new OmnitureData();
    }

    public String getLayout()
    {
        return layout;
    }

    public OmnitureData getOmnitureData()
    {
        return omnitureData;
    }

    public String getTitle()
    {
        return title;
    }

    public int getTotalProduct()
    {
        return totalProduct;
    }

    public void setLayout(String s)
    {
        layout = s;
    }

    public void setOmnitureData(OmnitureData omnituredata)
    {
        omnitureData = omnituredata;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setTotalProduct(int i)
    {
        totalProduct = i;
    }
}
