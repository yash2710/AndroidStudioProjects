// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.ugc;


public class UGCReviewDetail
{

    private String author;
    private boolean certifiedBuyer;
    private String date;
    private int rating;
    private String reviewText;
    private String title;
    private int yesCount;
    private int yesNoCount;

    public UGCReviewDetail()
    {
    }

    public String getAuthor()
    {
        return author;
    }

    public String getDate()
    {
        return date;
    }

    public int getRating()
    {
        return rating;
    }

    public String getReviewText()
    {
        return reviewText;
    }

    public String getTitle()
    {
        return title;
    }

    public int getYesCount()
    {
        return yesCount;
    }

    public int getYesNoCount()
    {
        return yesNoCount;
    }

    public boolean isCertifiedBuyer()
    {
        return certifiedBuyer;
    }

    public void setAuthor(String s)
    {
        author = s;
    }

    public void setCertifiedBuyer(boolean flag)
    {
        certifiedBuyer = flag;
    }

    public void setDate(String s)
    {
        date = s;
    }

    public void setRating(int i)
    {
        rating = i;
    }

    public void setReviewText(String s)
    {
        reviewText = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setYesCount(int i)
    {
        yesCount = i;
    }

    public void setYesNoCount(int i)
    {
        yesNoCount = i;
    }
}
