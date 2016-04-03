// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.seller;


public class SellerUGCReviews
{

    private int rating;
    private String review;
    private String time;
    private String userName;

    public SellerUGCReviews()
    {
    }

    public int getRating()
    {
        return rating;
    }

    public String getReview()
    {
        return review;
    }

    public String getTime()
    {
        return time;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setRating(int i)
    {
        rating = i;
    }

    public void setReview(String s)
    {
        review = s;
    }

    public void setTime(String s)
    {
        time = s;
    }

    public void setUserName(String s)
    {
        userName = s;
    }
}
