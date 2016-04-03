// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.jakewharton.disklrucache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

// Referenced classes of package com.jakewharton.disklrucache:
//            d, DiskLruCache, c, Util

public final class <init>
{

    private final d a;
    private final boolean b[];
    private boolean c;
    private boolean d;
    private DiskLruCache e;

    static d a(<init> <init>1)
    {
        return <init>1.a;
    }

    static boolean a(a a1, boolean flag)
    {
        a1.c = true;
        return true;
    }

    static boolean[] b(c c1)
    {
        return c1.b;
    }

    public final void abort()
    {
        DiskLruCache.a(e, this, false);
    }

    public final void abortUnlessCommitted()
    {
        if (d)
        {
            break MISSING_BLOCK_LABEL_11;
        }
        abort();
        return;
        IOException ioexception;
        ioexception;
    }

    public final void commit()
    {
        if (c)
        {
            DiskLruCache.a(e, this, false);
            e.remove(com.jakewharton.disklrucache.d.c(a));
        } else
        {
            DiskLruCache.a(e, this, true);
        }
        d = true;
    }

    public final String getString(int i)
    {
        InputStream inputstream = newInputStream(i);
        if (inputstream != null)
        {
            return DiskLruCache.a(inputstream);
        } else
        {
            return null;
        }
    }

    public final InputStream newInputStream(int i)
    {
        synchronized (e)
        {
            if (com.jakewharton.disklrucache.d.a(a) != this)
            {
                throw new IllegalStateException();
            }
            break MISSING_BLOCK_LABEL_31;
        }
        if (com.jakewharton.disklrucache.d.d(a))
        {
            break MISSING_BLOCK_LABEL_45;
        }
        disklrucache;
        JVM INSTR monitorexit ;
        return null;
        FileInputStream fileinputstream;
        try
        {
            fileinputstream = new FileInputStream(a.getCleanFile(i));
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            return null;
        }
        disklrucache;
        JVM INSTR monitorexit ;
        return fileinputstream;
    }

    public final OutputStream newOutputStream(int i)
    {
        synchronized (e)
        {
            if (com.jakewharton.disklrucache.d.a(a) != this)
            {
                throw new IllegalStateException();
            }
            break MISSING_BLOCK_LABEL_31;
        }
        File file;
        if (!com.jakewharton.disklrucache.d.d(a))
        {
            b[i] = true;
        }
        file = a.getDirtyFile(i);
        FileOutputStream fileoutputstream = new FileOutputStream(file);
        FileOutputStream fileoutputstream1 = fileoutputstream;
_L1:
        c c1 = new c(this, fileoutputstream1, (byte)0);
        disklrucache;
        JVM INSTR monitorexit ;
        return c1;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        DiskLruCache.f(e).mkdirs();
        FileOutputStream fileoutputstream2 = new FileOutputStream(file);
        fileoutputstream1 = fileoutputstream2;
          goto _L1
        FileNotFoundException filenotfoundexception1;
        filenotfoundexception1;
        OutputStream outputstream = DiskLruCache.a();
        disklrucache;
        JVM INSTR monitorexit ;
        return outputstream;
    }

    public final void set(int i, String s)
    {
        OutputStreamWriter outputstreamwriter = new OutputStreamWriter(newOutputStream(i), Util.b);
        outputstreamwriter.write(s);
        Util.a(outputstreamwriter);
        return;
        Exception exception;
        exception;
        outputstreamwriter = null;
_L2:
        Util.a(outputstreamwriter);
        throw exception;
        exception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private (DiskLruCache disklrucache, d d1)
    {
        e = disklrucache;
        super();
        a = d1;
        boolean aflag[];
        if (com.jakewharton.disklrucache.d.d(d1))
        {
            aflag = null;
        } else
        {
            aflag = new boolean[DiskLruCache.e(disklrucache)];
        }
        b = aflag;
    }

    b(DiskLruCache disklrucache, d d1, byte byte0)
    {
        this(disklrucache, d1);
    }
}
