// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.graphics.Typeface;
import com.flipkart.android.init.FlipkartApplication;
import java.util.HashMap;
import java.util.Map;

public class FontCache
{

    private static Map a = new HashMap();

    public FontCache()
    {
    }

    public static Typeface getFont(String s)
    {
        Typeface typeface = (Typeface)a.get(s);
        if (typeface != null)
        {
            return typeface;
        } else
        {
            Typeface typeface1 = Typeface.createFromAsset(FlipkartApplication.getAppContext().getAssets(), s);
            a.put(s, typeface1);
            return typeface1;
        }
    }

}
