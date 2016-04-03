// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// Referenced classes of package com.android.volley.toolbox:
//            a

public class ByteArrayPool
{

    protected static final Comparator BUF_COMPARATOR = new a();
    private List a;
    private List b;
    private int c;
    private final int d;

    public ByteArrayPool(int i)
    {
        a = new LinkedList();
        b = new ArrayList(64);
        c = 0;
        d = i;
    }

    private void a()
    {
        this;
        JVM INSTR monitorenter ;
        byte abyte0[];
        for (; c > d; c = c - abyte0.length)
        {
            abyte0 = (byte[])a.remove(0);
            b.remove(abyte0);
        }

        break MISSING_BLOCK_LABEL_57;
        Exception exception;
        exception;
        throw exception;
        this;
        JVM INSTR monitorexit ;
    }

    public byte[] getBuf(int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j = 0;
_L8:
        if (j >= b.size()) goto _L2; else goto _L1
_L1:
        byte abyte0[] = (byte[])b.get(j);
        if (abyte0.length < i) goto _L4; else goto _L3
_L3:
        c = c - abyte0.length;
        b.remove(j);
        a.remove(abyte0);
_L6:
        this;
        JVM INSTR monitorexit ;
        return abyte0;
_L4:
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        abyte0 = new byte[i];
        if (true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void returnBuf(byte abyte0[])
    {
        this;
        JVM INSTR monitorenter ;
        if (abyte0 == null) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = abyte0.length;
        j = d;
        if (i <= j) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        int k;
        a.add(abyte0);
        k = Collections.binarySearch(b, abyte0, BUF_COMPARATOR);
        if (k < 0)
        {
            k = -1 + -k;
        }
        b.add(k, abyte0);
        c = c + abyte0.length;
        a();
        if (true) goto _L2; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

}
