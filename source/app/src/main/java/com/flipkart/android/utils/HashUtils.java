// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.logging.FkLogger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils
{

    public HashUtils()
    {
    }

    public static final String md5(String s)
    {
        byte abyte0[];
        StringBuffer stringbuffer;
        int i;
        String s1;
        String s2;
        try
        {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(s.getBytes());
            abyte0 = messagedigest.digest();
            stringbuffer = new StringBuffer();
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            FkLogger.printStackTrace(nosuchalgorithmexception);
            return null;
        }
        i = 0;
        if (i >= abyte0.length)
        {
            break; /* Loop/switch isn't completed */
        }
        for (s2 = Integer.toHexString(0xff & abyte0[i]); s2.length() < 2; s2 = (new StringBuilder("0")).append(s2).toString()) { }
        stringbuffer.append(s2);
        i++;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_31;
_L1:
        s1 = stringbuffer.toString();
        return s1;
    }
}
