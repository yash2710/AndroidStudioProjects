// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;


// Referenced classes of package com.flipkart.android.response.discovery:
//            ResourceResponse

public class TagResponse
{

    private int count;
    private ResourceResponse resource;
    private String title;

    public TagResponse()
    {
        resource = new ResourceResponse();
    }

    public int getCount()
    {
        return count;
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

    public void setResource(ResourceResponse resourceresponse)
    {
        resource = resourceresponse;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
