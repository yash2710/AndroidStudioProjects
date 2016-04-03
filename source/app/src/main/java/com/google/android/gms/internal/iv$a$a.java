// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, mc, lz, ly, 
//            me

public final class iS extends ma
{

    private static volatile viewId Uz[];
    public String UA;
    public String UB;
    public int viewId;

    public static iS[] iR()
    {
        if (Uz == null)
        {
            synchronized (mc.ana)
            {
                if (Uz == null)
                {
                    Uz = new Uz[0];
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
            if (!(obj instanceof viewId))
            {
                return false;
            }
            viewId viewid = (viewId)obj;
            if (UA == null)
            {
                if (viewid.UA != null)
                {
                    return false;
                }
            } else
            if (!UA.equals(viewid.UA))
            {
                return false;
            }
            if (UB == null)
            {
                if (viewid.UB != null)
                {
                    return false;
                }
            } else
            if (!UB.equals(viewid.UB))
            {
                return false;
            }
            if (viewId != viewid.viewId)
            {
                return false;
            }
            if (amX == null || amX.isEmpty())
            {
                if (viewid.amX != null && !viewid.amX.isEmpty())
                {
                    return false;
                }
            } else
            {
                return amX.equals(viewid.amX);
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

    public final amX iS()
    {
        UA = "";
        UB = "";
        viewId = 0;
        amX = null;
        anb = -1;
        return this;
    }

    public final anb o(ly ly1)
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

    public ()
    {
        iS();
    }
}
