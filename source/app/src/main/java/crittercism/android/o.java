// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

final class o extends Thread
{

    private InputStream a;
    private StringBuilder b;
    private BufferedReader c;

    public o(InputStream inputstream)
    {
        b = new StringBuilder();
        c = null;
        a = inputstream;
    }

    public final StringBuilder a()
    {
        return b;
    }

    public final void b()
    {
        if (c == null)
        {
            break MISSING_BLOCK_LABEL_14;
        }
        c.close();
        return;
        Exception exception;
        exception;
        c = null;
        return;
    }

    public final void run()
    {
        new String();
        c = new BufferedReader(new InputStreamReader(a));
_L1:
        String s;
        try
        {
            s = c.readLine();
        }
        catch (Exception exception2)
        {
            Exception exception4;
            try
            {
                c.close();
                return;
            }
            catch (Exception exception3)
            {
                (new StringBuilder("CrittercismAPI.StreamThread$makeLogcatJsonArray: ERROR closing bufferedReader!!! ")).append(exception3.getClass().getName());
            }
            break MISSING_BLOCK_LABEL_132;
        }
        finally
        {
            try
            {
                c.close();
            }
            catch (Exception exception1)
            {
                (new StringBuilder("CrittercismAPI.StreamThread$makeLogcatJsonArray: ERROR closing bufferedReader!!! ")).append(exception1.getClass().getName());
            }
            throw exception;
        }
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        b.append(s);
        b.append("\n");
          goto _L1
        try
        {
            c.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception4)
        {
            (new StringBuilder("CrittercismAPI.StreamThread$makeLogcatJsonArray: ERROR closing bufferedReader!!! ")).append(exception4.getClass().getName());
        }
        return;
    }
}
