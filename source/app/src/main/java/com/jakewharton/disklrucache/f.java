// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.jakewharton.disklrucache;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

// Referenced classes of package com.jakewharton.disklrucache:
//            e

final class f extends ByteArrayOutputStream
{

    private e a;

    f(e e1, int i)
    {
        a = e1;
        super(i);
    }

    public final String toString()
    {
        int i;
        String s;
        if (count > 0 && buf[-1 + count] == 13)
        {
            i = -1 + count;
        } else
        {
            i = count;
        }
        try
        {
            s = new String(buf, 0, i, e.a(a).name());
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new AssertionError(unsupportedencodingexception);
        }
        return s;
    }
}
