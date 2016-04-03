// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.seller;

import java.util.LinkedHashMap;
import java.util.Map;

public class SellerUGCRatings
{

    private double averageRating;
    private long numberOfRatings;
    private Map ratingDistribution;

    public SellerUGCRatings()
    {
        ratingDistribution = new LinkedHashMap();
    }

    public double getAverageRating()
    {
        return averageRating;
    }

    public long getNumberOfRatings()
    {
        return numberOfRatings;
    }

    public Map getRatingDistribution()
    {
        if (ratingDistribution == null)
        {
            ratingDistribution = new LinkedHashMap();
        }
        return ratingDistribution;
    }

    public void setAverageRating(double d)
    {
        averageRating = d;
    }

    public void setNumberOfRatings(long l)
    {
        numberOfRatings = l;
    }

    public void setRatingDistribution(Map map)
    {
        ratingDistribution = map;
    }
}
