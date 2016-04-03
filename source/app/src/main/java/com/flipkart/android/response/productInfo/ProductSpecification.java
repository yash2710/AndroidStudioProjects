// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProductSpecification
{

    private String key;
    private Map value;

    public ProductSpecification()
    {
        value = new LinkedHashMap();
    }

    public String getKey()
    {
        return key;
    }

    public Map getValue()
    {
        if (value == null)
        {
            value = new LinkedHashMap();
        }
        return value;
    }

    public void setKey(String s)
    {
        key = s;
    }

    public void setValue(Map map)
    {
        value = map;
    }
}
