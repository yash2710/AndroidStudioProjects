// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.graphics.Bitmap;
import com.flipkart.android.init.FlipkartApplication;
import com.jakewharton.disklrucache.DiskLruImageCache;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils

public class DiskImageCache
    implements com.android.volley.toolbox.ImageLoader.ImageCache
{

    private static DiskImageCache c = null;
    private final android.graphics.Bitmap.CompressFormat a;
    private DiskLruImageCache b;

    private DiskImageCache()
    {
        a = android.graphics.Bitmap.CompressFormat.PNG;
        android.content.Context context = FlipkartApplication.getAppContext();
        if (b == null)
        {
            b = new DiskLruImageCache(context, "data/fka/images", 0xf00000, a, 80);
        }
    }

    private static String a(String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            return String.valueOf(s.trim().hashCode());
        } else
        {
            return null;
        }
    }

    public static DiskImageCache getInstance()
    {
        if (c != null) goto _L2; else goto _L1
_L1:
        com/flipkart/android/utils/DiskImageCache;
        JVM INSTR monitorenter ;
        if (c == null)
        {
            c = new DiskImageCache();
        }
        com/flipkart/android/utils/DiskImageCache;
        JVM INSTR monitorexit ;
_L2:
        return c;
        Exception exception;
        exception;
        throw exception;
    }

    public Bitmap getBitmap(String s)
    {
        String s1 = a(s);
        return b.getBitmap(s1);
    }

    public void putBitmap(String s, Bitmap bitmap)
    {
        String s1 = a(s);
        DiskLruImageCache disklruimagecache;
        String s2;
        try
        {
            disklruimagecache = b;
            s2 = s.substring(1 + s.lastIndexOf("."));
        }
        catch (Exception exception)
        {
            return;
        }
        if (s2 == null)
        {
            s2 = "";
        }
        disklruimagecache.put(s1, bitmap, s2);
        return;
    }

}
