// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mc, lz, ly, 
//            me

public final class g extends ma
{

    private static volatile amX eL[];
    public String eM;
    public long eN;
    public long eO;
    public boolean eP;
    public long eQ;

    public static g[] f()
    {
        if (eL == null)
        {
            synchronized (mc.ana)
            {
                if (eL == null)
                {
                    eL = new eL[0];
                }
            }
        }
        return eL;
    }

    public final void a(lz lz1)
    {
        if (!eM.equals(""))
        {
            lz1.b(1, eM);
        }
        if (eN != 0L)
        {
            lz1.b(2, eN);
        }
        if (eO != 0x7fffffffL)
        {
            lz1.b(3, eO);
        }
        if (eP)
        {
            lz1.a(4, eP);
        }
        if (eQ != 0L)
        {
            lz1.b(5, eQ);
        }
        super.a(lz1);
    }

    public final me b(ly ly1)
    {
        return d(ly1);
    }

    protected final int c()
    {
        int i = super.c();
        if (!eM.equals(""))
        {
            i += lz.h(1, eM);
        }
        if (eN != 0L)
        {
            i += lz.d(2, eN);
        }
        if (eO != 0x7fffffffL)
        {
            i += lz.d(3, eO);
        }
        if (eP)
        {
            i += lz.b(4, eP);
        }
        if (eQ != 0L)
        {
            i += lz.d(5, eQ);
        }
        return i;
    }

    public final eQ d(ly ly1)
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
                eM = ly1.readString();
                break;

            case 16: // '\020'
                eN = ly1.nD();
                break;

            case 24: // '\030'
                eO = ly1.nD();
                break;

            case 32: // ' '
                eP = ly1.nF();
                break;

            case 40: // '('
                eQ = ly1.nD();
                break;
            }
        } while (true);
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof D))
            {
                return false;
            }
            D d1 = (D)obj;
            if (eM == null)
            {
                if (d1.eM != null)
                {
                    return false;
                }
            } else
            if (!eM.equals(d1.eM))
            {
                return false;
            }
            if (eN != d1.eN)
            {
                return false;
            }
            if (eO != d1.eO)
            {
                return false;
            }
            if (eP != d1.eP)
            {
                return false;
            }
            if (eQ != d1.eQ)
            {
                return false;
            }
            if (amX == null || amX.isEmpty())
            {
                if (d1.amX != null && !d1.amX.isEmpty())
                {
                    return false;
                }
            } else
            {
                return amX.equals(d1.amX);
            }
        }
        return true;
    }

    public final amX g()
    {
        eM = "";
        eN = 0L;
        eO = 0x7fffffffL;
        eP = false;
        eQ = 0L;
        amX = null;
        anb = -1;
        return this;
    }

    public final int hashCode()
    {
        int i;
        int j;
        char c1;
        int k;
        List list;
        int l;
        if (eM == null)
        {
            i = 0;
        } else
        {
            i = eM.hashCode();
        }
        j = 31 * (31 * (31 * (i + 527) + (int)(eN ^ eN >>> 32)) + (int)(eO ^ eO >>> 32));
        if (eP)
        {
            c1 = '\u04CF';
        } else
        {
            c1 = '\u04D5';
        }
        k = 31 * (31 * (c1 + j) + (int)(eQ ^ eQ >>> 32));
        list = amX;
        l = 0;
        if (list != null)
        {
            boolean flag = amX.isEmpty();
            l = 0;
            if (!flag)
            {
                l = amX.hashCode();
            }
        }
        return k + l;
    }

    public ()
    {
        g();
    }
}
