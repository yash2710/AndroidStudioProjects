// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, me, lz, ly

public final class nf extends ma
{

    public long aiG;
    public aiH aiH;
    public aiH fK;

    public static nf l(byte abyte0[])
    {
        return (nf)me.a(new <init>(), abyte0);
    }

    public final void a(lz lz1)
    {
        lz1.b(1, aiG);
        if (fK != null)
        {
            lz1.a(2, fK);
        }
        if (aiH != null)
        {
            lz1.a(3, aiH);
        }
        super.a(lz1);
    }

    public final me b(ly ly1)
    {
        return p(ly1);
    }

    protected final int c()
    {
        int i = super.c() + lz.d(1, aiG);
        if (fK != null)
        {
            i += lz.b(2, fK);
        }
        if (aiH != null)
        {
            i += lz.b(3, aiH);
        }
        return i;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof aiH))
            {
                return false;
            }
            aiH aih = (aiH)obj;
            if (aiG != aih.aiG)
            {
                return false;
            }
            if (fK == null)
            {
                if (aih.fK != null)
                {
                    return false;
                }
            } else
            if (!fK.quals(aih.fK))
            {
                return false;
            }
            if (aiH == null)
            {
                if (aih.aiH != null)
                {
                    return false;
                }
            } else
            if (!aiH.quals(aih.aiH))
            {
                return false;
            }
            if (amX == null || amX.isEmpty())
            {
                if (aih.amX != null && !aih.amX.isEmpty())
                {
                    return false;
                }
            } else
            {
                return amX.equals(aih.amX);
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int i = 31 * (527 + (int)(aiG ^ aiG >>> 32));
        int j;
        int k;
        int i1;
        int j1;
        List list;
        int k1;
        if (fK == null)
        {
            j = 0;
        } else
        {
            j = fK.ashCode();
        }
        k = 31 * (j + i);
        if (aiH == null)
        {
            i1 = 0;
        } else
        {
            i1 = aiH.ashCode();
        }
        j1 = 31 * (i1 + k);
        list = amX;
        k1 = 0;
        if (list != null)
        {
            boolean flag = amX.isEmpty();
            k1 = 0;
            if (!flag)
            {
                k1 = amX.hashCode();
            }
        }
        return j1 + k1;
    }

    public final amX nf()
    {
        aiG = 0L;
        fK = null;
        aiH = null;
        amX = null;
        anb = -1;
        return this;
    }

    public final anb p(ly ly1)
    {
        do
        {
            int i = ly1.nB();
            switch (i)
            {
            default:
                if (a(ly1, i))
                {
                    continue;
                }
                // fall through

            case 0: // '\0'
                return this;

            case 8: // '\b'
                aiG = ly1.nD();
                break;

            case 18: // '\022'
                if (fK == null)
                {
                    fK = new init>();
                }
                ly1.a(fK);
                break;

            case 26: // '\032'
                if (aiH == null)
                {
                    aiH = new init>();
                }
                ly1.a(aiH);
                break;
            }
        } while (true);
    }

    public ()
    {
        nf();
    }
}
