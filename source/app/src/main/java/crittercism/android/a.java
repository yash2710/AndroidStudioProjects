// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import java.io.File;
import java.util.Locale;

public final class a
{

    private static String a = "";
    private static String b = "en";

    static String a()
    {
        return a;
    }

    public static String a(int i)
    {
        return b.a(i);
    }

    public static void a(Context context)
    {
        try
        {
            a = context.getCacheDir().getAbsolutePath();
            b.a();
            b = b(context);
            return;
        }
        catch (Exception exception)
        {
            Log.e("Crittercism", "Unable to initialize Crittercism's resources. Please report this error to support@crittercism.com.");
        }
    }

    public static String b(Context context)
    {
        String s;
        s = context.getResources().getConfiguration().locale.getLanguage();
        b = s;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        if (!b.equals(""))
        {
            break MISSING_BLOCK_LABEL_38;
        }
        b = "en";
_L2:
        return b;
        Exception exception;
        exception;
        (new StringBuilder("Exception in getLocale(): ")).append(exception.getClass().getName());
        if (true) goto _L2; else goto _L1
_L1:
    }


    private class b
    {

        private static HashMap a = new HashMap();

        public static String a(int i)
        {
            crittercism/android/a$b;
            JVM INSTR monitorenter ;
            String s = "";
            if (a.containsKey(Integer.valueOf(i)))
            {
                s = (String)a.get(Integer.valueOf(i));
            }
            crittercism/android/a$b;
            JVM INSTR monitorexit ;
            return s;
            Exception exception;
            exception;
            throw exception;
        }

        public static void a()
        {
            a.put(Integer.valueOf(10), "Error: no internet connection");
            a.put(Integer.valueOf(11), "Error: connection timed out, please try again later.");
            a.put(Integer.valueOf(13), "Unknown Error");
            a.put(Integer.valueOf(28), "Developer Reply");
            a.put(Integer.valueOf(29), "Loading...");
        }

        static 
        {
            (new StringBuilder()).append(a.a()).append("/strings/");
        }
    }

}
