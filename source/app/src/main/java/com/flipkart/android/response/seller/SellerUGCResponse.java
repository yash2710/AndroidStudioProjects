// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.seller;

import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.response.seller:
//            SellerUGCRatings

public class SellerUGCResponse
{

    private SellerUGCRatings sellerUgcRatings;
    private ArrayList sellerUgcReviews;

    public SellerUGCResponse()
    {
        sellerUgcRatings = new SellerUGCRatings();
        sellerUgcReviews = new ArrayList();
    }

    public SellerUGCRatings getSellerUgcRatings()
    {
        return sellerUgcRatings;
    }

    public ArrayList getSellerUgcReviews()
    {
        if (sellerUgcReviews == null)
        {
            sellerUgcReviews = new ArrayList();
        }
        return sellerUgcReviews;
    }

    public void setSellerUgcRatings(SellerUGCRatings sellerugcratings)
    {
        sellerUgcRatings = sellerugcratings;
    }

    public void setSellerUgcReviews(ArrayList arraylist)
    {
        sellerUgcReviews = arraylist;
    }
}
