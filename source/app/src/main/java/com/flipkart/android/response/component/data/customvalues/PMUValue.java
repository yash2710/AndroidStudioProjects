// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;

import com.flipkart.android.response.productInfo.ProductInfo;

// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable

public class PMUValue extends Renderable
{

    ProductInfo product;
    String source;
    String title;

    public PMUValue()
    {
    }

    public PMUValue(ProductInfo productinfo)
    {
        product = productinfo;
    }

    public ProductInfo getProduct()
    {
        return product;
    }

    public String getSource()
    {
        return source;
    }

    public String getTitle()
    {
        return title;
    }

    public void setProduct(ProductInfo productinfo)
    {
        product = productinfo;
    }

    public void setSource(String s)
    {
        source = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
