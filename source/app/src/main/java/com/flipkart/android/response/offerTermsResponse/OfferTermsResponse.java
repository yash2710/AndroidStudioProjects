// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.offerTermsResponse;

import java.util.LinkedHashMap;
import java.util.Map;

public class OfferTermsResponse
{

    private Map santaOffer;

    public OfferTermsResponse()
    {
        santaOffer = new LinkedHashMap();
    }

    public Map getSantaOffer()
    {
        if (santaOffer == null)
        {
            santaOffer = new LinkedHashMap();
        }
        return santaOffer;
    }

    public void setSantaOffer(Map map)
    {
        santaOffer = map;
    }
}
