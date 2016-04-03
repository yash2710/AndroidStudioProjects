// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.rest.resource;

import org.json.JSONObject;

public class ImageResource
{

    private JSONObject a;

    public ImageResource(JSONObject jsonobject)
    {
        a = null;
        a = jsonobject;
    }

    private int a(String s)
    {
        JSONObject jsonobject = a;
        int i = 0;
        if (jsonobject != null)
        {
            i = 0;
            if (s != "")
            {
                i = a.optInt(s);
            }
        }
        return i;
    }

    public String getUrl()
    {
        String s = "";
        if (a != null && "s3_path" != "")
        {
            s = a.optString("s3_path");
        }
        return s;
    }

    public int imageHeight()
    {
        return a("actual_height");
    }

    public int imageWidth()
    {
        return a("actual_width");
    }
}
