// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;

// Referenced classes of package com.crashlytics.android.internal:
//            aq, ab

public class aj
{

    private final File a;
    private final String b;
    private aq c;
    private File d;
    private File e;

    public aj(File file, String s, String s1)
    {
        a = file;
        b = s1;
        d = new File(file, s);
        c = new aq(d);
        e = new File(a, b);
        if (!e.exists())
        {
            e.mkdirs();
        }
    }

    public int a()
    {
        return c.a();
    }

    public List a(int i)
    {
        ArrayList arraylist = new ArrayList();
        File afile[] = e.listFiles();
        int j = afile.length;
        int k = 0;
        do
        {
            if (k >= j)
            {
                break;
            }
            arraylist.add(afile[k]);
            if (arraylist.size() > 0)
            {
                break;
            }
            k++;
        } while (true);
        return arraylist;
    }

    public void a(String s)
    {
        Object obj;
        File file;
        File file1;
        obj = null;
        c.close();
        file = d;
        file1 = new File(e, s);
        FileInputStream fileinputstream = new FileInputStream(file);
        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(new FileOutputStream(file1));
        ab.a(fileinputstream, gzipoutputstream, new byte[1024]);
        ab.a(fileinputstream, "Failed to close file input stream");
        ab.a(gzipoutputstream, "Failed to close gzip output stream");
        file.delete();
        c = new aq(d);
        return;
        Exception exception;
        exception;
        fileinputstream = null;
_L2:
        ab.a(fileinputstream, "Failed to close file input stream");
        ab.a(((java.io.Closeable) (obj)), "Failed to close gzip output stream");
        file.delete();
        throw exception;
        exception;
        obj = null;
        continue; /* Loop/switch isn't completed */
        exception;
        obj = gzipoutputstream;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void a(List list)
    {
        File file;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); file.delete())
        {
            file = (File)iterator.next();
            Object aobj[] = new Object[1];
            aobj[0] = file.getName();
            ab.c(String.format("deleting sent analytics file %s", aobj));
        }

    }

    public void a(byte abyte0[])
    {
        c.a(abyte0);
    }

    public boolean a(int i, int j)
    {
        return c.a(i, j);
    }

    public boolean b()
    {
        return c.b();
    }

    public List c()
    {
        return Arrays.asList(e.listFiles());
    }

    public void d()
    {
        try
        {
            c.close();
        }
        catch (IOException ioexception) { }
        d.delete();
    }
}
