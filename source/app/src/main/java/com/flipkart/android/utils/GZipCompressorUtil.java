// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.logging.FkLogger;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils, e

public class GZipCompressorUtil
{

    public GZipCompressorUtil()
    {
    }

    public static String compress(String s)
    {
        if (StringUtils.isNullOrEmpty(s))
        {
            return s;
        } else
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            GZIPOutputStream gzipoutputstream = new GZIPOutputStream(bytearrayoutputstream);
            gzipoutputstream.write(s.getBytes());
            gzipoutputstream.close();
            return bytearrayoutputstream.toString("ISO-8859-1");
        }
    }

    public static byte[] compress(byte abyte0[])
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        try
        {
            GZIPOutputStream gzipoutputstream = new GZIPOutputStream(bytearrayoutputstream);
            gzipoutputstream.write(abyte0);
            gzipoutputstream.close();
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }
        return bytearrayoutputstream.toByteArray();
    }

    public static String decompress(String s)
    {
        if (StringUtils.isNullOrEmpty(s))
        {
            return s;
        }
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(s.getBytes("ISO-8859-1"))), "ISO-8859-1"));
        String s1 = "";
        do
        {
            String s2 = bufferedreader.readLine();
            if (s2 != null)
            {
                s1 = (new StringBuilder()).append(s1).append(s2).toString();
            } else
            {
                return s1;
            }
        } while (true);
    }

    public static byte[] decompress(byte abyte0[])
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        try
        {
            e.copy(new GZIPInputStream(new ByteArrayInputStream(abyte0)), bytearrayoutputstream);
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }
        catch (InterruptedException interruptedexception)
        {
            FkLogger.printStackTrace(interruptedexception);
        }
        return bytearrayoutputstream.toByteArray();
    }
}
