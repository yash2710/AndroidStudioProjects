// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.ly;
import com.google.android.gms.internal.lz;
import com.google.android.gms.internal.ma;
import com.google.android.gms.internal.me;
import java.util.List;

public final class af extends ma
{

    public String Jt;
    public long Ju;
    public long Jv;
    public int versionCode;

    public af()
    {
        gs();
    }

    public static af g(byte abyte0[])
    {
        return (af)me.a(new af(), abyte0);
    }

    public final void a(lz lz1)
    {
        lz1.p(1, versionCode);
        lz1.b(2, Jt);
        lz1.c(3, Ju);
        lz1.c(4, Jv);
        super.a(lz1);
    }

    public final me b(ly ly1)
    {
        return m(ly1);
    }

    protected final int c()
    {
        return super.c() + lz.r(1, versionCode) + lz.h(2, Jt) + lz.e(3, Ju) + lz.e(4, Jv);
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof af))
            {
                return false;
            }
            af af1 = (af)obj;
            if (versionCode != af1.versionCode)
            {
                return false;
            }
            if (Jt == null)
            {
                if (af1.Jt != null)
                {
                    return false;
                }
            } else
            if (!Jt.equals(af1.Jt))
            {
                return false;
            }
            if (Ju != af1.Ju)
            {
                return false;
            }
            if (Jv != af1.Jv)
            {
                return false;
            }
            if (amX == null || amX.isEmpty())
            {
                if (af1.amX != null && !af1.amX.isEmpty())
                {
                    return false;
                }
            } else
            {
                return amX.equals(af1.amX);
            }
        }
        return true;
    }

    public final af gs()
    {
        versionCode = 1;
        Jt = "";
        Ju = -1L;
        Jv = -1L;
        amX = null;
        anb = -1;
        return this;
    }

    public final int hashCode()
    {
        int i = 31 * (527 + versionCode);
        int j;
        int k;
        List list;
        int l;
        if (Jt == null)
        {
            j = 0;
        } else
        {
            j = Jt.hashCode();
        }
        k = 31 * (31 * (31 * (j + i) + (int)(Ju ^ Ju >>> 32)) + (int)(Jv ^ Jv >>> 32));
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

    public final af m(ly ly1)
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
                versionCode = ly1.nE();
                break;

            case 18: // '\022'
                Jt = ly1.readString();
                break;

            case 24: // '\030'
                Ju = ly1.nH();
                break;

            case 32: // ' '
                Jv = ly1.nH();
                break;
            }
        } while (true);
    }
}
