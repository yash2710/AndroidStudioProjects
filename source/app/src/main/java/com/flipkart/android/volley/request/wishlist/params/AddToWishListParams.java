// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.wishlist.params;


public class AddToWishListParams
{

    private String a[];

    public AddToWishListParams(String as[])
    {
        a = as;
    }

    public byte[] generateToByteArray()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (a != null && a.length > 0)
        {
            stringbuilder.append("productIds=");
            String as[] = a;
            int i = as.length;
            for (int j = 0; j < i; j++)
            {
                stringbuilder.append(as[j]).append(",");
            }

            if (stringbuilder.charAt(-1 + stringbuilder.length()) == ',')
            {
                stringbuilder.deleteCharAt(-1 + stringbuilder.length());
            }
        }
        return stringbuilder.toString().getBytes();
    }

    public String generateURI()
    {
        return "";
    }

    public String[] getProductIds()
    {
        return a;
    }

    public void setProductIds(String as[])
    {
        a = as;
    }
}
