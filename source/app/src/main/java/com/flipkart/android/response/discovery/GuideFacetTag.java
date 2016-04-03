// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;


// Referenced classes of package com.flipkart.android.response.discovery:
//            GuideFacet

public class GuideFacetTag
{

    private GuideFacet facet;
    private String title;

    public GuideFacetTag()
    {
    }

    public GuideFacet getFacet()
    {
        return facet;
    }

    public String getTitle()
    {
        return title;
    }

    public void setFacet(GuideFacet guidefacet)
    {
        facet = guidefacet;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
