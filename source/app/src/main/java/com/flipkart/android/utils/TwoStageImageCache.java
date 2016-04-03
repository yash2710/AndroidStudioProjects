// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.flipkart.android.init.FlipkartApplication;
import com.jakewharton.disklrucache.DiskLruImageCache;

// Referenced classes of package com.flipkart.android.utils:
//            F, StringUtils

public class TwoStageImageCache
    implements com.android.volley.toolbox.ImageLoader.ImageCache
{

    private static TwoStageImageCache d = null;
    private final android.graphics.Bitmap.CompressFormat a;
    private LruCache b;
    private DiskLruImageCache c;

    private TwoStageImageCache()
    {
        a = android.graphics.Bitmap.CompressFormat.PNG;
        Context context = FlipkartApplication.getAppContext();
        if (context != null)
        {
            if (b == null)
            {
                b = new F(this, (0x100000 * ((ActivityManager)context.getSystemService("activity")).getMemoryClass()) / 8);
            }
            if (c == null)
            {
                c = new DiskLruImageCache(context, "data/fka/images", 0xf00000, a, 80);
            }
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

    public static TwoStageImageCache getInstance()
    {
        if (d != null) goto _L2; else goto _L1
_L1:
        com/flipkart/android/utils/TwoStageImageCache;
        JVM INSTR monitorenter ;
        if (d == null)
        {
            d = new TwoStageImageCache();
        }
        com/flipkart/android/utils/TwoStageImageCache;
        JVM INSTR monitorexit ;
_L2:
        return d;
        Exception exception;
        exception;
        throw exception;
    }

    public Bitmap getBitmap(String s)
    {
        this;
        JVM INSTR monitorenter ;
        String s1;
        boolean flag;
        s1 = a(s);
        flag = StringUtils.isNullOrEmpty(s1);
        Bitmap bitmap;
        bitmap = null;
        if (flag)
        {
            break MISSING_BLOCK_LABEL_34;
        }
        bitmap = (Bitmap)b.get(s1);
        this;
        JVM INSTR monitorexit ;
        return bitmap;
        Exception exception;
        exception;
        throw exception;
    }

    public DiskLruImageCache getDiskCache()
    {
        return c;
    }

    public void putBitmap(String s, Bitmap bitmap)
    {
        this;
        JVM INSTR monitorenter ;
        String s1 = a(s);
        b.put(s1, bitmap);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

}
