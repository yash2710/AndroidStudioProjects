// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments.model;

import android.content.Context;
import com.flipkart.android.response.seller.SellerInfoResponse;
import com.flipkart.android.response.seller.SellerResponse;
import com.flipkart.android.response.seller.SellerUGCRatings;
import com.flipkart.android.response.seller.SellerUGCResponse;
import com.flipkart.android.response.seller.SellerUGCReviews;
import com.flipkart.android.utils.StringUtils;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SellerPageModel
{

    private String a;
    private String b;
    private String c;
    private float d;
    private long e;
    private String f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private SellerUgcModel l[];
    private long m[];

    public SellerPageModel()
    {
    }

    public static SellerPageModel getModel(SellerResponse sellerresponse, Context context, String s)
    {
        SellerPageModel sellerpagemodel;
        if (StringUtils.isNull(sellerresponse))
        {
            return null;
        }
        sellerpagemodel = new SellerPageModel();
        if (StringUtils.isNullOrEmpty(sellerresponse.getSellerInfoResponse())) goto _L2; else goto _L1
_L1:
        SellerInfoResponse sellerinforesponse = (SellerInfoResponse)sellerresponse.getSellerInfoResponse().get(s);
        if (StringUtils.isNull(sellerinforesponse)) goto _L4; else goto _L3
_L3:
        if (StringUtils.isNullOrEmpty(sellerinforesponse.getDisplayName())) goto _L6; else goto _L5
_L5:
        sellerpagemodel.setDisplayNamevisible(true);
        sellerpagemodel.setDisplayName(sellerinforesponse.getDisplayName());
        sellerpagemodel.setBusinessName(sellerinforesponse.getBusinessName());
        String s1 = sellerinforesponse.getDescription();
        String s2;
        SellerUGCResponse sellerugcresponse;
        Map map;
        Iterator iterator;
        String s7;
        if (StringUtils.isNullOrEmpty(s1))
        {
            sellerpagemodel.setDescriptionVisible(false);
        } else
        {
            sellerpagemodel.setDescriptionVisible(true);
        }
        sellerpagemodel.setDescription(s1);
        s2 = (String)sellerresponse.getSellerPolicy().get(s);
        if (StringUtils.isNullOrEmpty(s2))
        {
            sellerpagemodel.setPoliciesVisible(false);
        } else
        {
            sellerpagemodel.setPoliciesVisible(true);
        }
        sellerpagemodel.setSellerPolicy(s2);
        sellerugcresponse = (SellerUGCResponse)sellerresponse.getSellerUgcResponse().get(s);
        if (StringUtils.isNull(sellerugcresponse)) goto _L8; else goto _L7
_L7:
        ArrayList arraylist;
        SellerUgcModel asellerugcmodel[];
        int i1;
        SellerUGCReviews sellerugcreviews;
        String s3;
        String as[];
        String s4;
        Exception exception;
        int j1;
        String s5;
        String s6;
        double d1;
        long l1;
        long al[];
        if (!StringUtils.isNull(sellerugcresponse.getSellerUgcRatings()))
        {
            d1 = sellerugcresponse.getSellerUgcRatings().getAverageRating();
            l1 = sellerugcresponse.getSellerUgcRatings().getNumberOfRatings();
        } else
        {
            d1 = 0.0D;
            l1 = 0L;
        }
        sellerpagemodel.setTotalRatings(l1);
        sellerpagemodel.setAvgRating((float)d1);
        if (l1 == 0L) goto _L10; else goto _L9
_L9:
        map = sellerugcresponse.getSellerUgcRatings().getRatingDistribution();
        if (map == null || map.size() != 5) goto _L10; else goto _L11
_L11:
        sellerpagemodel.setProgressBarVisible(true);
        al = new long[5];
        for (iterator = map.keySet().iterator(); iterator.hasNext();)
        {
            s7 = (String)iterator.next();
            al[-1 + Integer.parseInt(s7)] = ((Long)map.get(s7)).longValue();
        }

        sellerpagemodel.setRatingsBreakupCount(al);
_L16:
        arraylist = ((SellerUGCResponse)sellerresponse.getSellerUgcResponse().get(s)).getSellerUgcReviews();
        if (StringUtils.isNullOrEmpty(arraylist)) goto _L13; else goto _L12
_L12:
        sellerpagemodel.setSellerUgcVisible(true);
        asellerugcmodel = new SellerUgcModel[arraylist.size()];
        i1 = 0;
_L15:
        if (i1 >= arraylist.size())
        {
            break; /* Loop/switch isn't completed */
        }
        asellerugcmodel[i1] = new SellerUgcModel();
        sellerugcreviews = (SellerUGCReviews)arraylist.get(i1);
        s3 = sellerugcreviews.getUserName();
        if (StringUtils.isNullOrEmpty(s3))
        {
            s3 = "null";
        }
        asellerugcmodel[i1].setUserName(s3);
        as = sellerugcreviews.getTime().split("-");
        s4 = "";
        if (as.length != 3)
        {
            break MISSING_BLOCK_LABEL_541;
        }
        j1 = Integer.parseInt(as[1]);
        s5 = (new DateFormatSymbols()).getMonths()[j1 - 1];
        s6 = (new StringBuilder()).append(as[2]).append(" ").append(s5).append(" ").append(as[0]).toString();
        s4 = s6;
_L19:
        asellerugcmodel[i1].setTime(s4);
        asellerugcmodel[i1].setRating(sellerugcreviews.getRating());
        asellerugcmodel[i1].setReview(sellerugcreviews.getReview());
        i1++;
        if (true) goto _L15; else goto _L14
_L10:
        sellerpagemodel.setProgressBarVisible(false);
          goto _L16
_L8:
        sellerpagemodel.setTotalRatings(0L);
        sellerpagemodel.setAvgRating(0.0F);
        sellerpagemodel.setProgressBarVisible(false);
          goto _L16
_L14:
        sellerpagemodel.setSellerUgc(asellerugcmodel);
_L18:
        return sellerpagemodel;
_L13:
        sellerpagemodel.setSellerUgcVisible(false);
        continue; /* Loop/switch isn't completed */
_L6:
        sellerpagemodel.setDisplayNamevisible(false);
        continue; /* Loop/switch isn't completed */
_L4:
        sellerpagemodel.setDisplayNamevisible(false);
        continue; /* Loop/switch isn't completed */
_L2:
        sellerpagemodel.setDisplayNamevisible(false);
        if (true) goto _L18; else goto _L17
_L17:
        exception;
          goto _L19
    }

    public float getAvgRating()
    {
        return d;
    }

    public String getBusinessName()
    {
        return b;
    }

    public String getDescription()
    {
        return c;
    }

    public String getDisplayName()
    {
        return a;
    }

    public long[] getRatingsBreakupCount()
    {
        return m;
    }

    public String getSellerPolicy()
    {
        return f;
    }

    public SellerUgcModel[] getSellerUgc()
    {
        return l;
    }

    public long getTotalRatings()
    {
        return e;
    }

    public boolean isDescriptionVisible()
    {
        return i;
    }

    public boolean isDisplayNamevisible()
    {
        return j;
    }

    public boolean isPoliciesVisible()
    {
        return h;
    }

    public boolean isProgressBarVisible()
    {
        return g;
    }

    public boolean isSellerUgcVisible()
    {
        return k;
    }

    public void setAvgRating(float f1)
    {
        d = f1;
    }

    public void setBusinessName(String s)
    {
        b = s;
    }

    public void setDescription(String s)
    {
        c = s;
    }

    public void setDescriptionVisible(boolean flag)
    {
        i = flag;
    }

    public void setDisplayName(String s)
    {
        a = s;
    }

    public void setDisplayNamevisible(boolean flag)
    {
        j = flag;
    }

    public void setPoliciesVisible(boolean flag)
    {
        h = flag;
    }

    public void setProgressBarVisible(boolean flag)
    {
        g = flag;
    }

    public void setRatingsBreakupCount(long al[])
    {
        m = al;
    }

    public void setSellerPolicy(String s)
    {
        f = s;
    }

    public void setSellerUgc(SellerUgcModel asellerugcmodel[])
    {
        l = asellerugcmodel;
    }

    public void setSellerUgcVisible(boolean flag)
    {
        k = flag;
    }

    public void setTotalRatings(long l1)
    {
        e = l1;
    }

    private class SellerUgcModel
    {

        private String a;
        private String b;
        private String c;
        private float d;

        public float getRating()
        {
            return d;
        }

        public String getReview()
        {
            return b;
        }

        public String getTime()
        {
            return c;
        }

        public String getUserName()
        {
            return a;
        }

        public void setRating(float f1)
        {
            d = f1;
        }

        public void setReview(String s)
        {
            b = s;
        }

        public void setTime(String s)
        {
            c = s;
        }

        public void setUserName(String s)
        {
            a = s;
        }

        public SellerUgcModel()
        {
        }
    }

}
