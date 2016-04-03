// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.jakewharton.disklrucache;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package com.jakewharton.disklrucache:
//            DiskLruCache, Utils

public class DiskLruImageCache
{

    private DiskLruCache a;
    private android.graphics.Bitmap.CompressFormat b;
    private int c;
    private String d;

    public DiskLruImageCache(Context context, String s, int i, android.graphics.Bitmap.CompressFormat compressformat, int j)
    {
        File file;
        b = android.graphics.Bitmap.CompressFormat.PNG;
        c = 80;
        d = "";
        file = null;
        if (context == null) goto _L2; else goto _L1
_L1:
        boolean flag = StringUtils.isNullOrEmpty(s);
        file = null;
        if (!flag) goto _L3; else goto _L2
_L3:
        if (!"mounted".equals(Environment.getExternalStorageState()) && Utils.isExternalStorageRemovable()) goto _L5; else goto _L4
_L4:
        file1 = Utils.getExternalCacheDir(context);
        if (file1 == null)
        {
            break MISSING_BLOCK_LABEL_206;
        }
        String s1 = file1.getPath();
_L8:
        file = null;
        if (s1 == null) goto _L2; else goto _L6
_L6:
        k = s1.length();
        file = null;
        if (k == 0) goto _L2; else goto _L7
_L7:
        d = (new StringBuilder()).append(s1).append(File.separator).append(s).toString();
        file = new File(d);
_L2:
        File file1;
        int k;
        try
        {
            a = DiskLruCache.open(file, 1, 1, i);
            b = compressformat;
            c = j;
            return;
        }
        catch (IOException ioexception)
        {
            FkLogger.printStackTrace(ioexception);
        }
        return;
_L5:
        File file2 = context.getCacheDir();
        if (file2 == null)
        {
            break MISSING_BLOCK_LABEL_206;
        }
        String s2 = file2.getPath();
        s1 = s2;
          goto _L8
        s1 = null;
          goto _L8
    }

    private boolean a(Bitmap bitmap, DiskLruCache.Editor editor, String s)
    {
        BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(editor.newOutputStream(0), 8192);
        if (!s.equalsIgnoreCase("jpg")) goto _L2; else goto _L1
_L1:
        android.graphics.Bitmap.CompressFormat compressformat = android.graphics.Bitmap.CompressFormat.JPEG;
_L3:
        boolean flag1 = bitmap.compress(compressformat, c, bufferedoutputstream);
        boolean flag;
        flag = flag1;
        bufferedoutputstream.close();
_L5:
        return flag;
_L2:
label0:
        {
            if (!s.equalsIgnoreCase("jpeg"))
            {
                break label0;
            }
            compressformat = android.graphics.Bitmap.CompressFormat.JPEG;
        }
          goto _L3
label1:
        {
            if (!s.equalsIgnoreCase("png"))
            {
                break label1;
            }
            compressformat = android.graphics.Bitmap.CompressFormat.PNG;
        }
          goto _L3
        compressformat = b;
          goto _L3
        Exception exception2;
        exception2;
        bufferedoutputstream = null;
_L8:
        flag = false;
        if (bufferedoutputstream == null) goto _L5; else goto _L4
_L4:
        bufferedoutputstream.close();
        return false;
        Exception exception1;
        exception1;
        bufferedoutputstream = null;
_L7:
        if (bufferedoutputstream != null)
        {
            bufferedoutputstream.close();
        }
        throw exception1;
        exception1;
        if (true) goto _L7; else goto _L6
_L6:
        Exception exception;
        exception;
          goto _L8
    }

    public void clearCache()
    {
        try
        {
            a.delete();
            return;
        }
        catch (IOException ioexception)
        {
            FkLogger.printStackTrace(ioexception);
        }
    }

    public boolean containsKey(String s)
    {
        DiskLruCache.Snapshot snapshot = a.get(s);
        boolean flag;
        flag = false;
        if (snapshot != null)
        {
            flag = true;
        }
        if (snapshot != null)
        {
            snapshot.close();
        }
_L2:
        return flag;
        IOException ioexception;
        ioexception;
        FkLogger.printStackTrace(ioexception);
        flag = false;
        if (true) goto _L2; else goto _L1
_L1:
        null.close();
        return false;
        Exception exception;
        exception;
        if (false)
        {
            null.close();
        }
        throw exception;
    }

    public Bitmap getBitmap(String s)
    {
        DiskLruCache.Snapshot snapshot1 = a.get(s);
        DiskLruCache.Snapshot snapshot = snapshot1;
        if (snapshot != null) goto _L2; else goto _L1
_L1:
        Bitmap bitmap;
        bitmap = null;
        if (snapshot != null)
        {
            snapshot.close();
        }
_L4:
        return bitmap;
_L2:
        java.io.InputStream inputstream = snapshot.getInputStream(0);
        bitmap = null;
        if (inputstream == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        Bitmap bitmap1 = BitmapFactoryInstrumentation.decodeStream(new BufferedInputStream(inputstream, 8192));
        bitmap = bitmap1;
        if (snapshot == null) goto _L4; else goto _L3
_L3:
        snapshot.close();
        return bitmap;
        IOException ioexception;
        ioexception;
        snapshot = null;
_L10:
        FkLogger.printStackTrace(ioexception);
        bitmap = null;
        if (snapshot == null) goto _L4; else goto _L5
_L5:
        snapshot.close();
        return null;
        NullPointerException nullpointerexception;
        nullpointerexception;
        snapshot = null;
_L9:
        FkLogger.printStackTrace(nullpointerexception);
        bitmap = null;
        if (snapshot == null) goto _L4; else goto _L6
_L6:
        snapshot.close();
        return null;
        Exception exception;
        exception;
        Exception exception1;
        snapshot = null;
        exception1 = exception;
_L8:
        if (snapshot != null)
        {
            snapshot.close();
        }
        throw exception1;
        exception1;
        if (true) goto _L8; else goto _L7
_L7:
        nullpointerexception;
          goto _L9
        ioexception;
          goto _L10
    }

    public File getCacheFolder()
    {
        return a.getDirectory();
    }

    public String getFilePath()
    {
        return d;
    }

    public void put(String s, Bitmap bitmap, String s1)
    {
        DiskLruCache.Editor editor = null;
        editor = a.edit(s);
        if (editor == null)
        {
            return;
        }
        IOException ioexception;
        IOException ioexception1;
        if (a(bitmap, editor, s1))
        {
            a.flush();
            editor.commit();
            return;
        }
        try
        {
            editor.abort();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            if (editor != null)
            {
                try
                {
                    editor.abort();
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception1)
                {
                    return;
                }
            }
        }
        catch (NullPointerException nullpointerexception) { }
        return;
    }
}
