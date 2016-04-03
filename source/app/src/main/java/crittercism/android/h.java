// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.math.BigInteger;

public final class h
{

    public static BigInteger a()
    {
        BigInteger biginteger = BigInteger.valueOf(-1L);
        BigInteger biginteger1;
        try
        {
            StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
            biginteger1 = BigInteger.valueOf(statfs.getAvailableBlocks()).multiply(BigInteger.valueOf(statfs.getBlockSize()));
        }
        catch (Exception exception)
        {
            return biginteger;
        }
        return biginteger1;
    }

    public static BigInteger b()
    {
        BigInteger biginteger = BigInteger.valueOf(-1L);
        BigInteger biginteger1;
        try
        {
            StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
            biginteger1 = BigInteger.valueOf(statfs.getBlockCount()).multiply(BigInteger.valueOf(statfs.getBlockSize()));
        }
        catch (Exception exception)
        {
            return biginteger;
        }
        return biginteger1;
    }

    public static BigInteger c()
    {
        BigInteger biginteger = BigInteger.valueOf(-1L);
        BigInteger biginteger1;
        try
        {
            StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            biginteger1 = BigInteger.valueOf(statfs.getAvailableBlocks()).multiply(BigInteger.valueOf(statfs.getBlockSize()));
        }
        catch (Exception exception)
        {
            return biginteger;
        }
        return biginteger1;
    }

    public static BigInteger d()
    {
        BigInteger biginteger = BigInteger.valueOf(-1L);
        BigInteger biginteger1;
        try
        {
            StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            biginteger1 = BigInteger.valueOf(statfs.getBlockCount()).multiply(BigInteger.valueOf(statfs.getBlockSize()));
        }
        catch (Exception exception)
        {
            return biginteger;
        }
        return biginteger1;
    }
}
