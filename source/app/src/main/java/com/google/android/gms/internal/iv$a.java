// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, lz, mc, ly, 
//            mh, me

public final class iQ extends ma
{

    public a Uy[];

    public final void a(lz lz1)
    {
        if (Uy != null && Uy.length > 0)
        {
            for (int i = 0; i < Uy.length; i++)
            {
                a a1 = Uy[i];
                if (a1 != null)
                {
                    lz1.a(1, a1);
                }
            }

        }
        super.a(lz1);
    }

    public final me b(ly ly1)
    {
        return n(ly1);
    }

    protected final int c()
    {
        int i = super.c();
        if (Uy != null && Uy.length > 0)
        {
            for (int j = 0; j < Uy.length; j++)
            {
                a a1 = Uy[j];
                if (a1 != null)
                {
                    i += lz.b(1, a1);
                }
            }

        }
        return i;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof Uy))
            {
                return false;
            }
            Uy uy = (Uy)obj;
            if (!mc.equals(Uy, uy.Uy))
            {
                return false;
            }
            if (amX == null || amX.isEmpty())
            {
                if (uy.amX != null && !uy.amX.isEmpty())
                {
                    return false;
                }
            } else
            {
                return amX.equals(uy.amX);
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int i = 31 * (527 + mc.hashCode(Uy));
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

    public final amX iQ()
    {
        class a extends ma
        {

            private static volatile a Uz[];
            public String UA;
            public String UB;
            public int viewId;

            public static a[] iR()
            {
                if (Uz == null)
                {
                    synchronized (mc.ana)
                    {
                        if (Uz == null)
                        {
                            Uz = new a[0];
                        }
                    }
                }
                return Uz;
            }

            public final void a(lz lz1)
            {
                if (!UA.equals(""))
                {
                    lz1.b(1, UA);
                }
                if (!UB.equals(""))
                {
                    lz1.b(2, UB);
                }
                if (viewId != 0)
                {
                    lz1.p(3, viewId);
                }
                super.a(lz1);
            }

            public final me b(ly ly1)
            {
                return o(ly1);
            }

            protected final int c()
            {
                int i = super.c();
                if (!UA.equals(""))
                {
                    i += lz.h(1, UA);
                }
                if (!UB.equals(""))
                {
                    i += lz.h(2, UB);
                }
                if (viewId != 0)
                {
                    i += lz.r(3, viewId);
                }
                return i;
            }

            public final boolean equals(Object obj)
            {
                if (obj != this)
                {
                    if (!(obj instanceof a))
                    {
                        return false;
                    }
                    a a1 = (a)obj;
                    if (UA == null)
                    {
                        if (a1.UA != null)
                        {
                            return false;
                        }
                    } else
                    if (!UA.equals(a1.UA))
                    {
                        return false;
                    }
                    if (UB == null)
                    {
                        if (a1.UB != null)
                        {
                            return false;
                        }
                    } else
                    if (!UB.equals(a1.UB))
                    {
                        return false;
                    }
                    if (viewId != a1.viewId)
                    {
                        return false;
                    }
                    if (amX == null || amX.isEmpty())
                    {
                        if (a1.amX != null && !a1.amX.isEmpty())
                        {
                            return false;
                        }
                    } else
                    {
                        return amX.equals(a1.amX);
                    }
                }
                return true;
            }

            public final int hashCode()
            {
                int i;
                int j;
                int k;
                int l;
                List list;
                int i1;
                if (UA == null)
                {
                    i = 0;
                } else
                {
                    i = UA.hashCode();
                }
                j = 31 * (i + 527);
                if (UB == null)
                {
                    k = 0;
                } else
                {
                    k = UB.hashCode();
                }
                l = 31 * (31 * (k + j) + viewId);
                list = amX;
                i1 = 0;
                if (list != null)
                {
                    boolean flag = amX.isEmpty();
                    i1 = 0;
                    if (!flag)
                    {
                        i1 = amX.hashCode();
                    }
                }
                return l + i1;
            }

            public final a iS()
            {
                UA = "";
                UB = "";
                viewId = 0;
                amX = null;
                anb = -1;
                return this;
            }

            public final a o(ly ly1)
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
                        UA = ly1.readString();
                        break;

                    case 18: // '\022'
                        UB = ly1.readString();
                        break;

                    case 24: // '\030'
                        viewId = ly1.nE();
                        break;
                    }
                } while (true);
            }

            public a()
            {
                iS();
            }
        }

        Uy = a.iR();
        amX = null;
        anb = -1;
        return this;
    }

    public final anb n(ly ly1)
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
                int k;
                a aa[];
                if (Uy == null)
                {
                    k = 0;
                } else
                {
                    k = Uy.length;
                }
                aa = new a[j + k];
                if (k != 0)
                {
                    System.arraycopy(Uy, 0, aa, 0, k);
                }
                for (; k < -1 + aa.length; k++)
                {
                    aa[k] = new a();
                    ly1.a(aa[k]);
                    ly1.nB();
                }

                aa[k] = new a();
                ly1.a(aa[k]);
                Uy = aa;
                break;
            }
        } while (true);
    }

    public a()
    {
        iQ();
    }
}
