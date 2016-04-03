// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, me, lz, mc, 
//            ly, mh

public final class q extends ma
{

    public anb fJ[];
    public anb fK;
    public String fL;

    public static q b(byte abyte0[])
    {
        return (q)me.a(new <init>(), abyte0);
    }

    public final void a(lz lz1)
    {
        if (fJ != null && fJ.length > 0)
        {
            for (int i = 0; i < fJ.length; i++)
            {
                <init> <init>1 = fJ[i];
                if (<init>1 != null)
                {
                    lz1.a(1, <init>1);
                }
            }

        }
        if (fK != null)
        {
            lz1.a(2, fK);
        }
        if (!fL.equals(""))
        {
            lz1.b(3, fL);
        }
        super.a(lz1);
    }

    public final me b(ly ly1)
    {
        return k(ly1);
    }

    protected final int c()
    {
        int i = super.c();
        if (fJ != null && fJ.length > 0)
        {
            for (int j = 0; j < fJ.length; j++)
            {
                k k1 = fJ[j];
                if (k1 != null)
                {
                    i += lz.b(1, k1);
                }
            }

        }
        if (fK != null)
        {
            i += lz.b(2, fK);
        }
        if (!fL.equals(""))
        {
            i += lz.h(3, fL);
        }
        return i;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof fL))
            {
                return false;
            }
            fL fl = (fL)obj;
            if (!mc.equals(fJ, fl.fJ))
            {
                return false;
            }
            if (fK == null)
            {
                if (fl.fK != null)
                {
                    return false;
                }
            } else
            if (!fK.equals(fl.fK))
            {
                return false;
            }
            if (fL == null)
            {
                if (fl.fL != null)
                {
                    return false;
                }
            } else
            if (!fL.equals(fl.fL))
            {
                return false;
            }
            if (amX == null || amX.isEmpty())
            {
                if (fl.amX != null && !fl.amX.isEmpty())
                {
                    return false;
                }
            } else
            {
                return amX.equals(fl.amX);
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int i = 31 * (527 + mc.hashCode(fJ));
        int j;
        int l;
        int i1;
        int j1;
        List list;
        int k1;
        if (fK == null)
        {
            j = 0;
        } else
        {
            j = fK.hashCode();
        }
        l = 31 * (j + i);
        if (fL == null)
        {
            i1 = 0;
        } else
        {
            i1 = fL.hashCode();
        }
        j1 = 31 * (i1 + l);
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

    public final amX k(ly ly1)
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

            case 10: // '\n'
                int j = mh.b(ly1, 10);
                int l;
                amX aamx[];
                if (fJ == null)
                {
                    l = 0;
                } else
                {
                    l = fJ.length;
                }
                aamx = new fJ[j + l];
                if (l != 0)
                {
                    System.arraycopy(fJ, 0, aamx, 0, l);
                }
                for (; l < -1 + aamx.length; l++)
                {
                    aamx[l] = new <init>();
                    ly1.a(aamx[l]);
                    ly1.nB();
                }

                aamx[l] = new <init>();
                ly1.a(aamx[l]);
                fJ = aamx;
                break;

            case 18: // '\022'
                if (fK == null)
                {
                    fK = new <init>();
                }
                ly1.a(fK);
                break;

            case 26: // '\032'
                fL = ly1.readString();
                break;
            }
        } while (true);
    }

    public final eadString q()
    {
        fJ = o();
        fK = null;
        fL = "";
        amX = null;
        anb = -1;
        return this;
    }

    public ()
    {
        q();
    }
}
