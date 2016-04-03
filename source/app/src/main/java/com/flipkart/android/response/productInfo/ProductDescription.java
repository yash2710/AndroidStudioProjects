// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;


public class ProductDescription
{

    private String decriptionText;
    private boolean showDescription;

    public ProductDescription()
    {
    }

    public String getDecriptionText()
    {
        return decriptionText;
    }

    public boolean isShowDescription()
    {
        return showDescription;
    }

    public void setDecriptionText(String s)
    {
        decriptionText = s;
    }

    public void setShowDescription(boolean flag)
    {
        showDescription = flag;
    }
}
