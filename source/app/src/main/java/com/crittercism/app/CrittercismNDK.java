// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class CrittercismNDK
{

    private static String a;

    public CrittercismNDK()
    {
    }

    public static native boolean checkLibraryVersion(int i);

    public static boolean installLib(Context context)
    {
        String s;
        s = "armeabi";
        if (System.getProperty("os.arch").contains("v7"))
        {
            s = (new StringBuilder()).append(s).append("-v7a").toString();
        }
        FileOutputStream fileoutputstream;
        InputStream inputstream;
        byte abyte0[];
        (new File(a)).mkdirs();
        fileoutputstream = new FileOutputStream(new File((new StringBuilder()).append(a).append("libcrittercism-ndk.so").toString()));
        inputstream = context.getAssets().open((new StringBuilder()).append(s).append("/libcrittercism-ndk.so").toString());
        abyte0 = new byte[8192];
_L1:
        int i = inputstream.read(abyte0);
label0:
        {
            if (i < 0)
            {
                break label0;
            }
            try
            {
                fileoutputstream.write(abyte0, 0, i);
            }
            catch (Exception exception)
            {
                return false;
            }
        }
          goto _L1
        inputstream.close();
        fileoutputstream.close();
        System.load((new StringBuilder()).append(a).append("libcrittercism-ndk.so").toString());
        return true;
    }

    public static native boolean installNdk(String s);

    public static void installNdkLib(Context context, String s)
    {
        String s1;
        a = (new StringBuilder()).append(context.getFilesDir().getAbsolutePath()).append("/com.crittercism/lib/").toString();
        s1 = (new StringBuilder()).append(context.getFilesDir().getAbsolutePath()).append("/").append(s).toString();
        if (!(new File((new StringBuilder()).append(a).append("libcrittercism-ndk.so").toString())).exists())
        {
            break MISSING_BLOCK_LABEL_177;
        }
        boolean flag1;
        System.load((new StringBuilder()).append(a).append("libcrittercism-ndk.so").toString());
        flag1 = checkLibraryVersion(2);
        boolean flag = flag1;
_L1:
        if (!flag)
        {
            installLib(context);
        }
_L2:
        UnsatisfiedLinkError unsatisfiedlinkerror;
        try
        {
            if (installNdk(s1))
            {
                File file = new File(s1);
                file.getAbsolutePath();
                file.mkdirs();
            }
            return;
        }
        catch (Throwable throwable)
        {
            return;
        }
        unsatisfiedlinkerror;
        flag = installLib(context);
          goto _L1
        installLib(context);
          goto _L2
    }
}
