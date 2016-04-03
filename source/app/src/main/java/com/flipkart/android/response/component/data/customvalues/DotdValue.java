// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;

import com.flipkart.android.response.productInfo.ProductInfo;
import java.util.List;

// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable

public class DotdValue extends Renderable
{

    String description;
    long end;
    String offerType;
    List products;
    long start;
    String title;

    public DotdValue()
    {
        products = null;
    }

    public void addProduct(ProductInfo productinfo)
    {
        products.add(productinfo);
    }

    public String getDescription()
    {
        return description;
    }

    public long getEnd()
    {
        return end;
    }

    public String getOfferType()
    {
        return offerType;
    }

    public List getProducts()
    {
        return products;
    }

    public long getStart()
    {
        return start;
    }

    public String getTitle()
    {
        return title;
    }

    public void setDescription(String s)
    {
        description = s;
    }

    public void setEnd(long l)
    {
        end = l;
    }

    public void setOfferType(String s)
    {
        offerType = s;
    }

    public void setProducts(List list)
    {
        products = list;
    }

    public void setStart(long l)
    {
        start = l;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
