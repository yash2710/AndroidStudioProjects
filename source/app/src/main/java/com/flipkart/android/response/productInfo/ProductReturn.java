// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;


public class ProductReturn
{

    private String returnPolicy;
    private boolean showReturn;

    public ProductReturn()
    {
    }

    public String getReturnPolicy()
    {
        return returnPolicy;
    }

    public boolean isShowReturn()
    {
        return showReturn;
    }

    public void setReturnPolicy(String s)
    {
        returnPolicy = s;
    }

    public void setShowReturn(boolean flag)
    {
        showReturn = flag;
    }
}
