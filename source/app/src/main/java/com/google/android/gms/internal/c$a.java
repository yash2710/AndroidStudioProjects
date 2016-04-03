// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ma, ly, lz, me

public final class b extends ma
{

    public int eE;
    public int eF;
    public int level;

    public final b a(ly ly1)
    {
_L6:
        int i = ly1.nB();
        i;
        JVM INSTR lookupswitch 4: default 48
    //                   0: 57
    //                   8: 59
    //                   16: 103
    //                   24: 114;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if (a(ly1, i)) goto _L6; else goto _L2
_L2:
        return this;
_L3:
        int j = ly1.nE();
        switch (j)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            level = j;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        eE = ly1.nE();
        continue; /* Loop/switch isn't completed */
_L5:
        eF = ly1.nE();
        if (true) goto _L6; else goto _L7
_L7:
    }

    public final void a(lz lz1)
    {
        if (level != 1)
        {
            lz1.p(1, level);
        }
        if (eE != 0)
        {
            lz1.p(2, eE);
        }
        if (eF != 0)
        {
            lz1.p(3, eF);
        }
        super.a(lz1);
    }

    public final  b()
    {
        level = 1;
        eE = 0;
        eF = 0;
        amX = null;
        anb = -1;
        return this;
    }

    public final me b(ly ly1)
    {
        return a(ly1);
    }

    protected final int c()
    {
        int i = super.c();
        if (level != 1)
        {
            i += lz.r(1, level);
        }
        if (eE != 0)
        {
            i += lz.r(2, eE);
        }
        if (eF != 0)
        {
            i += lz.r(3, eF);
        }
        return i;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof eF))
            {
                return false;
            }
            eF ef = (eF)obj;
            if (level != ef.level)
            {
                return false;
            }
            if (eE != ef.eE)
            {
                return false;
            }
            if (eF != ef.eF)
            {
                return false;
            }
            if (amX == null || amX.isEmpty())
            {
                if (ef.amX != null && !ef.amX.isEmpty())
                {
                    return false;
                }
            } else
            {
                return amX.equals(ef.amX);
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int i = 31 * (31 * (31 * (527 + level) + eE) + eF);
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
        b();
    }
}
