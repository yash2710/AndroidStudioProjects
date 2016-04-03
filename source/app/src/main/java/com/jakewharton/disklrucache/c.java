// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.jakewharton.disklrucache;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class c extends FilterOutputStream
{

    private DiskLruCache.Editor a;

    private c(DiskLruCache.Editor editor, OutputStream outputstream)
    {
        a = editor;
        super(outputstream);
    }

    c(DiskLruCache.Editor editor, OutputStream outputstream, byte byte0)
    {
        this(editor, outputstream);
    }

    public final void close()
    {
        try
        {
            out.close();
            return;
        }
        catch (IOException ioexception)
        {
            DiskLruCache.Editor.a(a, true);
        }
    }

    public final void flush()
    {
        try
        {
            out.flush();
            return;
        }
        catch (IOException ioexception)
        {
            DiskLruCache.Editor.a(a, true);
        }
    }

    public final void write(int i)
    {
        try
        {
            out.write(i);
            return;
        }
        catch (IOException ioexception)
        {
            DiskLruCache.Editor.a(a, true);
        }
    }

    public final void write(byte abyte0[], int i, int j)
    {
        try
        {
            out.write(abyte0, i, j);
            return;
        }
        catch (IOException ioexception)
        {
            DiskLruCache.Editor.a(a, true);
        }
    }
}
