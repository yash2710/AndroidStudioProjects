// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.seller;


public class SellerInfoResponse
{

    private String businessName;
    private String description;
    private String displayName;

    public SellerInfoResponse()
    {
    }

    public String getBusinessName()
    {
        return businessName;
    }

    public String getDescription()
    {
        return description;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setBusinessName(String s)
    {
        businessName = s;
    }

    public void setDescription(String s)
    {
        description = s;
    }

    public void setDisplayName(String s)
    {
        displayName = s;
    }
}
