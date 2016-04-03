// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;

import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.response.discovery:
//            MetaDataMap

public class FacetResponse
{

    private MetaDataMap metadata;
    private String title;
    private ArrayList value;

    public FacetResponse()
    {
        value = new ArrayList();
    }

    public MetaDataMap getMetadata()
    {
        return metadata;
    }

    public String getTitle()
    {
        return title;
    }

    public ArrayList getValue()
    {
        return value;
    }

    public void setMetadata(MetaDataMap metadatamap)
    {
        metadata = metadatamap;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setValues(ArrayList arraylist)
    {
        value = arraylist;
    }
}
