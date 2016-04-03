// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mc, lz, ly, 
//            me

public final class j extends ma
{

    private static volatile anb eU[];
    public int key;
    public int value;

    public static j[] i()
    {
        if (eU == null)
        {
            synchronized (mc.ana)
            {
                if (eU == null)
                {
                    eU = new eU[0];
                }
            }
        }
        return eU;
    }

    public final void a(lz lz1)
    {
        lz1.p(1, key);
        lz1.p(2, value);
        super.a(lz1);
    }

    public final me b(ly ly1)
    {
        return f(ly1);
    }

    protected final int c()
    {
        return super.c() + lz.r(1, key) + lz.r(2, value);
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof value))
            {
                return false;
            }
            value value1 = (value)obj;
            if (key != value1.key)
            {
                return false;
            }
            if (value != value1.value)
            {
                return false;
            }
            if (amX == null || amX.isEmpty())
            {
                if (value1.amX != null && !value1.amX.isEmpty())
                {
                    return false;
                }
            } else
            {
                return amX.equals(value1.amX);
            }
        }
        return true;
    }

    public final amX f(ly ly1)
    {
        do
        {
            int k = ly1.nB();
            switch (k)
            {
            default:
                if (a(ly1, k))
                {
                    continue;
                }
                // fall through

            case 0: // '\0'
                return this;

            case 8: // '\b'
                key = ly1.nE();
                break;

            case 16: // '\020'
                value = ly1.nE();
                break;
            }
        } while (true);
    }

    public final int hashCode()
    {
        int k = 31 * (31 * (527 + key) + value);
        int l;
        if (amX == null || amX.isEmpty())
        {
            l = 0;
        } else
        {
            l = amX.hashCode();
        }
        return l + k;
    }

    public final amX j()
    {
        key = 0;
        value = 0;
        amX = null;
        anb = -1;
        return this;
    }

    public ()
    {
        j();
    }
}
