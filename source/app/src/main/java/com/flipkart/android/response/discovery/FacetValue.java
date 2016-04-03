// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;


// Referenced classes of package com.flipkart.android.response.discovery:
//            ResourceResponse, PerFilterMetaData

public class FacetValue
{

    private int count;
    private PerFilterMetaData metadata;
    private ResourceResponse resource;
    private String title;

    public FacetValue()
    {
        resource = new ResourceResponse();
        metadata = new PerFilterMetaData();
    }

    public int getCount()
    {
        return count;
    }

    public PerFilterMetaData getMetadata()
    {
        return metadata;
    }

    public ResourceResponse getResource()
    {
        return resource;
    }

    public String getTitle()
    {
        return title;
    }

    public void setCount(int i)
    {
        count = i;
    }

    public void setMetadata(PerFilterMetaData perfiltermetadata)
    {
        metadata = perfiltermetadata;
    }

    public void setResource(ResourceResponse resourceresponse)
    {
        resource = resourceresponse;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
