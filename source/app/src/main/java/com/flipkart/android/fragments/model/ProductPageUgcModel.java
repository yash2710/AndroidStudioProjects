// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments.model;

import android.content.Context;
import com.flipkart.android.response.ugc.UGCReviewDetail;
import com.flipkart.android.utils.ProductPageReviewContext;
import com.flipkart.android.utils.StringUtils;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Map;

public class ProductPageUgcModel
{

    private ProductUgcModel a[];
    private Map b;
    private boolean c;
    private ProductPageReviewContext d;

    public ProductPageUgcModel()
    {
    }

    public static ProductPageUgcModel getModel(ArrayList arraylist, Context context)
    {
        if (arraylist.size() != 0)
        {
            ProductPageUgcModel productpageugcmodel = new ProductPageUgcModel();
            ProductUgcModel aproductugcmodel[] = new ProductUgcModel[arraylist.size()];
            int i = 0;
            while (i < arraylist.size()) 
            {
                aproductugcmodel[i] = new ProductUgcModel();
                UGCReviewDetail ugcreviewdetail = (UGCReviewDetail)arraylist.get(i);
                String s = ugcreviewdetail.getAuthor();
                if (StringUtils.isNullOrEmpty(s))
                {
                    s = "null";
                }
                aproductugcmodel[i].setUserName(s);
                aproductugcmodel[i].setReview(ugcreviewdetail.getReviewText());
                aproductugcmodel[i].setTitle(ugcreviewdetail.getTitle());
                aproductugcmodel[i].setTotalCount(ugcreviewdetail.getYesNoCount());
                aproductugcmodel[i].setYesCount(ugcreviewdetail.getYesCount());
                String as[];
                String s1;
                if (ugcreviewdetail.isCertifiedBuyer())
                {
                    aproductugcmodel[i].setIsCertifiedBuyer(Boolean.valueOf(true));
                } else
                {
                    aproductugcmodel[i].setIsCertifiedBuyer(Boolean.valueOf(false));
                }
                aproductugcmodel[i].setRating(ugcreviewdetail.getRating());
                as = ugcreviewdetail.getDate().split(" ")[0].split("/");
                s1 = "";
                try
                {
                    int j = Integer.parseInt(as[1]);
                    s1 = (new DateFormatSymbols()).getMonths()[j - 1];
                }
                catch (Exception exception) { }
                aproductugcmodel[i].setTime((new StringBuilder()).append(as[0]).append(" ").append(s1).append(" ").append(as[2]).toString());
                i++;
            }
            productpageugcmodel.setProductUgc(aproductugcmodel);
            return productpageugcmodel;
        } else
        {
            return null;
        }
    }

    public ProductPageReviewContext getProductPageReviewContext()
    {
        return d;
    }

    public ProductUgcModel[] getProductUgc()
    {
        return a;
    }

    public Map getSwatchesMap()
    {
        return b;
    }

    public boolean isSizeToBeSelected()
    {
        return c;
    }

    public void setProductPageReviewContext(ProductPageReviewContext productpagereviewcontext)
    {
        d = productpagereviewcontext;
    }

    public void setProductUgc(ProductUgcModel aproductugcmodel[])
    {
        a = aproductugcmodel;
    }

    public void setSizeToBeSelected(boolean flag)
    {
        c = flag;
    }

    public void setSwatchesMap(Map map)
    {
        b = map;
    }

    private class ProductUgcModel
    {

        private String a;
        private String b;
        private String c;
        private float d;
        private Boolean e;
        private int f;
        private int g;
        private String h;

        public Boolean getIsCertifiedBuyer()
        {
            return e;
        }

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

        public String getTitle()
        {
            return h;
        }

        public int getTotalCount()
        {
            return g;
        }

        public String getUserName()
        {
            return a;
        }

        public int getYesCount()
        {
            return f;
        }

        public void setIsCertifiedBuyer(Boolean boolean1)
        {
            e = boolean1;
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

        public void setTitle(String s)
        {
            h = s;
        }

        public void setTotalCount(int i)
        {
            g = i;
        }

        public void setUserName(String s)
        {
            a = s;
        }

        public void setYesCount(int i)
        {
            f = i;
        }

        public ProductUgcModel()
        {
        }
    }

}
