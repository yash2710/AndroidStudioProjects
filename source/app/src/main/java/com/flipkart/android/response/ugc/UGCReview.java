// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.ugc;

import java.util.ArrayList;

public class UGCReview
{

    private ArrayList reviewList;

    public UGCReview()
    {
        reviewList = new ArrayList();
    }

    public ArrayList getReviewLst()
    {
        if (reviewList == null)
        {
            reviewList = new ArrayList();
        }
        return reviewList;
    }

    public void setReviewLst(ArrayList arraylist)
    {
        reviewList = arraylist;
    }
}
