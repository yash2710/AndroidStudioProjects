// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


// Referenced classes of package com.google.android.gms.internal:
//            hk

public final class FT
{

    public final int FS;
    public final int FT;

    public final boolean equals(Object obj)
    {
        if (obj instanceof FT)
        {
            if (this == obj)
            {
                return true;
            }
            FT ft = (FT)obj;
            if (ft.FS == FS && ft.FT == FT)
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(FS);
        aobj[1] = Integer.valueOf(FT);
        return hk.hashCode(aobj);
    }

    public (int i, int j)
    {
        FS = i;
        FT = j;
    }
}
