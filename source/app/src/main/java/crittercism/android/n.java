// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

// Referenced classes of package crittercism.android:
//            o

final class n
    implements Callable
{

    private static boolean c = false;
    private static Object e;
    private StringBuilder a;
    private String b[];
    private Process d;
    private o f;
    private o g;

    public n(Object obj, Thread thread)
    {
        a = new StringBuilder();
        new StringBuilder();
        d = null;
        e = obj;
        if (android.os.Build.VERSION.SDK_INT >= 8)
        {
            b = new String[5];
            b[0] = "logcat";
            b[1] = "-t";
            b[2] = "100";
            b[3] = "-v";
            b[4] = "time";
            return;
        } else
        {
            b = new String[4];
            b[0] = "logcat";
            b[1] = "-d";
            b[2] = "-v";
            b[3] = "time";
            return;
        }
    }

    public static boolean a()
    {
        return c;
    }

    public static void b()
    {
        c = true;
    }

    private StringBuilder d()
    {
        f = null;
        g = null;
        if (c)
        {
            break MISSING_BLOCK_LABEL_165;
        }
        d = Runtime.getRuntime().exec(b);
        f = new o(d.getInputStream());
        g = new o(d.getErrorStream());
        f.start();
        g.start();
        d.waitFor();
        if (f != null)
        {
            a = f.a();
        }
        if (g != null)
        {
            g.a();
        }
        Exception exception2;
        if (d != null)
        {
            try
            {
                d.getInputStream().close();
                d.getErrorStream().close();
                d.getOutputStream().close();
                d.destroy();
            }
            catch (Exception exception3) { }
        }
        return a;
        exception2;
        c = true;
        if (f != null)
        {
            a = f.a();
        }
        if (g != null)
        {
            g.a();
        }
        if (d == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        d.getInputStream().close();
        d.getErrorStream().close();
        d.getOutputStream().close();
        d.destroy();
        if (true) goto _L2; else goto _L1
_L1:
        break MISSING_BLOCK_LABEL_255;
_L2:
        break MISSING_BLOCK_LABEL_165;
        Exception exception;
        exception;
        if (f != null)
        {
            a = f.a();
        }
        if (g != null)
        {
            g.a();
        }
        if (d != null)
        {
            try
            {
                d.getInputStream().close();
                d.getErrorStream().close();
                d.getOutputStream().close();
                d.destroy();
            }
            catch (Exception exception1) { }
        }
        throw exception;
    }

    public final void c()
    {
        Exception exception;
        Exception exception1;
        synchronized (e)
        {
            try
            {
                f.b();
                g.b();
                if (d != null)
                {
                    d.getInputStream().close();
                    d.getErrorStream().close();
                    d.getOutputStream().close();
                }
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception) { }
            try
            {
                if (d != null)
                {
                    d.destroy();
                }
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception1) { }
        }
    }

    public final Object call()
    {
        return d();
    }

}
