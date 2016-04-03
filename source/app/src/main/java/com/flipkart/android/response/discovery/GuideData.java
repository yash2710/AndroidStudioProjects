// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;

import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.response.discovery:
//            GuideFacetTag, GuideMetaData, GuideStoreTag

public class GuideData
{

    private GuideFacetTag facetTag;
    private GuideMetaData meta;
    private ArrayList searchKeywords;
    private GuideStoreTag storeTag;

    public GuideData()
    {
    }

    public GuideFacetTag getFacetTag()
    {
        return facetTag;
    }

    public GuideMetaData getMeta()
    {
        return meta;
    }

    public ArrayList getSearchKeywords()
    {
        return searchKeywords;
    }

    public GuideStoreTag getStoreTag()
    {
        return storeTag;
    }

    public void setFacetTag(GuideFacetTag guidefacettag)
    {
        facetTag = guidefacettag;
    }

    public void setMeta(GuideMetaData guidemetadata)
    {
        meta = guidemetadata;
    }

    public void setSearchKeywords(ArrayList arraylist)
    {
        searchKeywords = arraylist;
    }

    public void setStoreTag(GuideStoreTag guidestoretag)
    {
        storeTag = guidestoretag;
    }
}
