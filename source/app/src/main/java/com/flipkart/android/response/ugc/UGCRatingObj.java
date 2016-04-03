// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.ugc;

import java.util.ArrayList;

public class UGCRatingObj
{

    private double overallRating;
    private ArrayList ratingBreakupCount;
    private long totalRatingCount;

    public UGCRatingObj()
    {
        ratingBreakupCount = new ArrayList();
    }

    public double getOverallRating()
    {
        return overallRating;
    }

    public ArrayList getRatingBreakupCount()
    {
        if (ratingBreakupCount == null)
        {
            ratingBreakupCount = new ArrayList();
        }
        return ratingBreakupCount;
    }

    public long getTotalRatingCount()
    {
        return totalRatingCount;
    }

    public void setOverallRating(double d)
    {
        overallRating = d;
    }

    public void setRatingBreakupCount(ArrayList arraylist)
    {
        ratingBreakupCount = arraylist;
    }

    public void setTotalRatingCount(long l)
    {
        totalRatingCount = l;
    }
}
