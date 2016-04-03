// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.ugc;


// Referenced classes of package com.flipkart.android.response.ugc:
//            UGCRatingObj, UGCReviewObj

public class UGCRating
{

    private UGCRatingObj ratingObj;
    private UGCReviewObj reviewObj;

    public UGCRating()
    {
        ratingObj = new UGCRatingObj();
        reviewObj = new UGCReviewObj();
    }

    public UGCRatingObj getRatingObj()
    {
        return ratingObj;
    }

    public UGCReviewObj getReviewObj()
    {
        return reviewObj;
    }

    public void setRatingObj(UGCRatingObj ugcratingobj)
    {
        ratingObj = ugcratingobj;
    }

    public void setReviewObj(UGCReviewObj ugcreviewobj)
    {
        reviewObj = ugcreviewobj;
    }
}
