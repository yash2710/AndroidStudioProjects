// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.wishlist.params;


public class DeleteFromWishListParams
{

    private String a[];
    private boolean b;

    public DeleteFromWishListParams(String as[], boolean flag)
    {
        a = as;
        b = flag;
    }

    public byte[] generateToByteArray()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (!b) goto _L2; else goto _L1
_L1:
        stringbuilder.append("productIds=all");
_L4:
        return stringbuilder.toString().getBytes();
_L2:
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
        if (true) goto _L4; else goto _L3
_L3:
    }

    public String generateURI()
    {
        return "";
    }

    public String[] getProductIds()
    {
        return a;
    }

    public boolean isDeleteAll()
    {
        return b;
    }

    public void setDeleteAll(boolean flag)
    {
        b = flag;
    }

    public void setProductIds(String as[])
    {
        a = as;
    }
}
