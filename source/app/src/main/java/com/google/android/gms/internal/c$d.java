// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, lz, ly, mh, 
//            mc, me

public final class h extends ma
{

    public amX eR[];
    public amX eS[];
    public amX eT[];

    public final void a(lz lz1)
    {
        if (eR != null && eR.length > 0)
        {
            for (int l = 0; l < eR.length; l++)
            {
                h h3 = eR[l];
                if (h3 != null)
                {
                    lz1.a(1, h3);
                }
            }

        }
        if (eS != null && eS.length > 0)
        {
            for (int k = 0; k < eS.length; k++)
            {
                h h2 = eS[k];
                if (h2 != null)
                {
                    lz1.a(2, h2);
                }
            }

        }
        if (eT != null)
        {
            int i = eT.length;
            int j = 0;
            if (i > 0)
            {
                for (; j < eT.length; j++)
                {
                    h h1 = eT[j];
                    if (h1 != null)
                    {
                        lz1.a(3, h1);
                    }
                }

            }
        }
        super.a(lz1);
    }

    public final me b(ly ly1)
    {
        return e(ly1);
    }

    protected final int c()
    {
        int i = super.c();
        if (eR != null && eR.length > 0)
        {
            int j1 = i;
            for (int k1 = 0; k1 < eR.length; k1++)
            {
                e e3 = eR[k1];
                if (e3 != null)
                {
                    j1 += lz.b(1, e3);
                }
            }

            i = j1;
        }
        if (eS != null && eS.length > 0)
        {
            int l = i;
            for (int i1 = 0; i1 < eS.length; i1++)
            {
                e e2 = eS[i1];
                if (e2 != null)
                {
                    l += lz.b(2, e2);
                }
            }

            i = l;
        }
        if (eT != null)
        {
            int j = eT.length;
            int k = 0;
            if (j > 0)
            {
                for (; k < eT.length; k++)
                {
                    e e1 = eT[k];
                    if (e1 != null)
                    {
                        i += lz.b(3, e1);
                    }
                }

            }
        }
        return i;
    }

    public final  e(ly ly1)
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
                int j1 = mh.b(ly1, 10);
                int k1;
                 a3[];
                if (eR == null)
                {
                    k1 = 0;
                } else
                {
                    k1 = eR.length;
                }
                a3 = new eR[j1 + k1];
                if (k1 != 0)
                {
                    System.arraycopy(eR, 0, a3, 0, k1);
                }
                for (; k1 < -1 + a3.length; k1++)
                {
                    a3[k1] = new <init>();
                    ly1.a(a3[k1]);
                    ly1.nB();
                }

                a3[k1] = new <init>();
                ly1.a(a3[k1]);
                eR = a3;
                break;

            case 18: // '\022'
                int l = mh.b(ly1, 18);
                int i1;
                 a2[];
                if (eS == null)
                {
                    i1 = 0;
                } else
                {
                    i1 = eS.length;
                }
                a2 = new eS[l + i1];
                if (i1 != 0)
                {
                    System.arraycopy(eS, 0, a2, 0, i1);
                }
                for (; i1 < -1 + a2.length; i1++)
                {
                    a2[i1] = new <init>();
                    ly1.a(a2[i1]);
                    ly1.nB();
                }

                a2[i1] = new <init>();
                ly1.a(a2[i1]);
                eS = a2;
                break;

            case 26: // '\032'
                int j = mh.b(ly1, 26);
                int k;
                 a1[];
                if (eT == null)
                {
                    k = 0;
                } else
                {
                    k = eT.length;
                }
                a1 = new eT[j + k];
                if (k != 0)
                {
                    System.arraycopy(eT, 0, a1, 0, k);
                }
                for (; k < -1 + a1.length; k++)
                {
                    a1[k] = new <init>();
                    ly1.a(a1[k]);
                    ly1.nB();
                }

                a1[k] = new <init>();
                ly1.a(a1[k]);
                eT = a1;
                break;
            }
        } while (true);
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof eT))
            {
                return false;
            }
            eT et = (eT)obj;
            if (!mc.equals(eR, et.eR))
            {
                return false;
            }
            if (!mc.equals(eS, et.eS))
            {
                return false;
            }
            if (!mc.equals(eT, et.eT))
            {
                return false;
            }
            if (amX == null || amX.isEmpty())
            {
                if (et.amX != null && !et.amX.isEmpty())
                {
                    return false;
                }
            } else
            {
                return amX.equals(et.amX);
            }
        }
        return true;
    }

    public final amX h()
    {
        eR = r();
        eS = r();
        eT = f();
        amX = null;
        anb = -1;
        return this;
    }

    public final int hashCode()
    {
        int i = 31 * (31 * (31 * (527 + mc.hashCode(eR)) + mc.hashCode(eS)) + mc.hashCode(eT));
        int j;
        if (amX == null || amX.isEmpty())
        {
            j = 0;
        } else
        {
            j = amX.hashCode();
        }
        return j + i;
    }

    public ()
    {
        h();
    }
}
