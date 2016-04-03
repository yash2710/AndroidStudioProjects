// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;


// Referenced classes of package com.flipkart.android.response.discovery:
//            GuideResource

public class GuideFacetValue
{

    private int count;
    private GuideResource resource;
    private String title;

    public GuideFacetValue()
    {
    }

    public int getCount()
    {
        return count;
    }

    public GuideResource getResource()
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

    public void setResource(GuideResource guideresource)
    {
        resource = guideresource;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
