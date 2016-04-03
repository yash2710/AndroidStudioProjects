// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import java.util.HashMap;

// Referenced classes of package crittercism.android:
//            a

public final class 
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
        (new StringBuilder()).append(crittercism.android.a.a()).append("/strings/");
    }
}
