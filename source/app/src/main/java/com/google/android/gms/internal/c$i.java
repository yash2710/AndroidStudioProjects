// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mc, lz, ly, 
//            me

public final class p extends ma
{

    private static volatile anb fG[];
    public anb fH;
    public anb fI;
    public String name;

    public static p[] o()
    {
        if (fG == null)
        {
            synchronized (mc.ana)
            {
                if (fG == null)
                {
                    fG = new fG[0];
                }
            }
        }
        return fG;
    }

    public final void a(lz lz1)
    {
        if (!name.equals(""))
        {
            lz1.b(1, name);
        }
        if (fH != null)
        {
            lz1.a(2, fH);
        }
        if (fI != null)
        {
            lz1.a(3, fI);
        }
        super.a(lz1);
    }

    public final me b(ly ly1)
    {
        return j(ly1);
    }

    protected final int c()
    {
        int i = super.c();
        if (!name.equals(""))
        {
            i += lz.h(1, name);
        }
        if (fH != null)
        {
            i += lz.b(2, fH);
        }
        if (fI != null)
        {
            i += lz.b(3, fI);
        }
        return i;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof fI))
            {
                return false;
            }
            fI fi = (fI)obj;
            if (name == null)
            {
                if (fi.name != null)
                {
                    return false;
                }
            } else
            if (!name.equals(fi.name))
            {
                return false;
            }
            if (fH == null)
            {
                if (fi.fH != null)
                {
                    return false;
                }
            } else
            if (!fH.equals(fi.fH))
            {
                return false;
            }
            if (fI == null)
            {
                if (fi.fI != null)
                {
                    return false;
                }
            } else
            if (!fI.equals(fi.fI))
            {
                return false;
            }
            if (amX == null || amX.isEmpty())
            {
                if (fi.amX != null && !fi.amX.isEmpty())
                {
                    return false;
                }
            } else
            {
                return amX.equals(fi.amX);
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int i;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        List list;
        int l1;
        if (name == null)
        {
            i = 0;
        } else
        {
            i = name.hashCode();
        }
        k = 31 * (i + 527);
        if (fH == null)
        {
            l = 0;
        } else
        {
            l = fH.hashCode();
        }
        i1 = 31 * (l + k);
        if (fI == null)
        {
            j1 = 0;
        } else
        {
            j1 = fI.hashCode();
        }
        k1 = 31 * (j1 + i1);
        list = amX;
        l1 = 0;
        if (list != null)
        {
            boolean flag = amX.isEmpty();
            l1 = 0;
            if (!flag)
            {
                l1 = amX.hashCode();
            }
        }
        return k1 + l1;
    }

    public final amX j(ly ly1)
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
                name = ly1.readString();
                break;

            case 18: // '\022'
                if (fH == null)
                {
                    fH = new <init>();
                }
                ly1.a(fH);
                break;

            case 26: // '\032'
                if (fI == null)
                {
                    fI = new <init>();
                }
                ly1.a(fI);
                break;
            }
        } while (true);
    }

    public final fI p()
    {
        name = "";
        fH = null;
        fI = null;
        amX = null;
        anb = -1;
        return this;
    }

    public ()
    {
        p();
    }
}
