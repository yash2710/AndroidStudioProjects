// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.jakewharton.disklrucache;

import android.content.Context;
import android.os.Environment;
import java.io.File;

public class Utils
{

    public static final int IO_BUFFER_SIZE = 8192;

    private Utils()
    {
    }

    public static File getExternalCacheDir(Context context)
    {
        if (hasExternalCacheDir())
        {
            return context.getExternalCacheDir();
        } else
        {
            String s = (new StringBuilder("/Android/data/")).append(context.getPackageName()).append("/cache/").toString();
            return new File((new StringBuilder()).append(Environment.getExternalStorageDirectory().getPath()).append(s).toString());
        }
    }

    public static boolean hasExternalCacheDir()
    {
        return android.os.Build.VERSION.SDK_INT >= 8;
    }

    public static boolean isExternalStorageRemovable()
    {
        if (android.os.Build.VERSION.SDK_INT >= 9)
        {
            return Environment.isExternalStorageRemovable();
        } else
        {
            return true;
        }
    }
}
