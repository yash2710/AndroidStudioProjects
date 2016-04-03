// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.ugc;

import java.util.LinkedHashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.android.response.ugc:
//            UGCSortOptions

public class UGCResponse
{

    private Map rating;
    private Map review;
    private UGCSortOptions sortOption;

    public UGCResponse()
    {
        sortOption = new UGCSortOptions();
        rating = new LinkedHashMap();
        review = new LinkedHashMap();
    }

    public Map getRating()
    {
        if (rating == null)
        {
            rating = new LinkedHashMap();
        }
        return rating;
    }

    public Map getReview()
    {
        if (review == null)
        {
            review = new LinkedHashMap();
        }
        return review;
    }

    public UGCSortOptions getSortOption()
    {
        return sortOption;
    }

    public void setRating(Map map)
    {
        rating = map;
    }

    public void setReview(Map map)
    {
        review = map;
    }

    public void setSortOption(UGCSortOptions ugcsortoptions)
    {
        sortOption = ugcsortoptions;
    }
}
