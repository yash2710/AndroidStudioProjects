// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;

// Referenced classes of package com.google.android.gms.internal:
//            me, mh

public final class lz
{

    private final int amW;
    private final byte buffer[];
    private int position;

    private lz(byte abyte0[], int i, int j)
    {
        buffer = abyte0;
        position = i;
        amW = i + j;
    }

    public static int D(long l)
    {
        return G(l);
    }

    public static int E(long l)
    {
        return G(I(l));
    }

    public static int G(long l)
    {
        if ((-128L & l) == 0L)
        {
            return 1;
        }
        if ((-16384L & l) == 0L)
        {
            return 2;
        }
        if ((0xffffffffffe00000L & l) == 0L)
        {
            return 3;
        }
        if ((0xfffffffff0000000L & l) == 0L)
        {
            return 4;
        }
        if ((0xfffffff800000000L & l) == 0L)
        {
            return 5;
        }
        if ((0xfffffc0000000000L & l) == 0L)
        {
            return 6;
        }
        if ((0xfffe000000000000L & l) == 0L)
        {
            return 7;
        }
        if ((0xff00000000000000L & l) == 0L)
        {
            return 8;
        }
        return (0x8000000000000000L & l) != 0L ? 10 : 9;
    }

    public static long I(long l)
    {
        return l << 1 ^ l >> 63;
    }

    public static int J(boolean flag)
    {
        return 1;
    }

    public static int b(int i, double d1)
    {
        return eH(i) + f(d1);
    }

    public static int b(int i, me me1)
    {
        return eH(i) + c(me1);
    }

    public static int b(int i, boolean flag)
    {
        return eH(i) + J(flag);
    }

    public static int b(int i, byte abyte0[])
    {
        return eH(i) + s(abyte0);
    }

    public static lz b(byte abyte0[], int i, int j)
    {
        return new lz(abyte0, i, j);
    }

    public static int c(int i, float f1)
    {
        return eH(i) + e(f1);
    }

    public static int c(me me1)
    {
        int i = me1.oa();
        return i + eJ(i);
    }

    public static int cz(String s1)
    {
        int i;
        int j;
        try
        {
            byte abyte0[] = s1.getBytes("UTF-8");
            i = eJ(abyte0.length);
            j = abyte0.length;
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new RuntimeException("UTF-8 not supported.");
        }
        return j + i;
    }

    public static int d(int i, long l)
    {
        return eH(i) + D(l);
    }

    public static int e(float f1)
    {
        return 4;
    }

    public static int e(int i, long l)
    {
        return eH(i) + E(l);
    }

    public static int eE(int i)
    {
        if (i >= 0)
        {
            return eJ(i);
        } else
        {
            return 10;
        }
    }

    public static int eF(int i)
    {
        return eJ(eL(i));
    }

    public static int eH(int i)
    {
        return eJ(mh.u(i, 0));
    }

    public static int eJ(int i)
    {
        if ((i & 0xffffff80) == 0)
        {
            return 1;
        }
        if ((i & 0xffffc000) == 0)
        {
            return 2;
        }
        if ((0xffe00000 & i) == 0)
        {
            return 3;
        }
        return (0xf0000000 & i) != 0 ? 5 : 4;
    }

    public static int eL(int i)
    {
        return i << 1 ^ i >> 31;
    }

    public static int f(double d1)
    {
        return 8;
    }

    public static int h(int i, String s1)
    {
        return eH(i) + cz(s1);
    }

    public static lz q(byte abyte0[])
    {
        return b(abyte0, 0, abyte0.length);
    }

    public static int r(int i, int j)
    {
        return eH(i) + eE(j);
    }

    public static int s(int i, int j)
    {
        return eH(i) + eF(j);
    }

    public static int s(byte abyte0[])
    {
        return eJ(abyte0.length) + abyte0.length;
    }

    public final void B(long l)
    {
        F(l);
    }

    public final void C(long l)
    {
        F(I(l));
    }

    public final void F(long l)
    {
        do
        {
            if ((-128L & l) == 0L)
            {
                eG((int)l);
                return;
            }
            eG(0x80 | 0x7f & (int)l);
            l >>>= 7;
        } while (true);
    }

    public final void H(long l)
    {
        eG(0xff & (int)l);
        eG(0xff & (int)(l >> 8));
        eG(0xff & (int)(l >> 16));
        eG(0xff & (int)(l >> 24));
        eG(0xff & (int)(l >> 32));
        eG(0xff & (int)(l >> 40));
        eG(0xff & (int)(l >> 48));
        eG(0xff & (int)(l >> 56));
    }

    public final void I(boolean flag)
    {
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        eG(i);
    }

    public final void a(int i, double d1)
    {
        t(i, 1);
        e(d1);
    }

    public final void a(int i, me me1)
    {
        t(i, 2);
        b(me1);
    }

    public final void a(int i, boolean flag)
    {
        t(i, 0);
        I(flag);
    }

    public final void a(int i, byte abyte0[])
    {
        t(i, 2);
        r(abyte0);
    }

    public final void b(byte byte0)
    {
        if (position == amW)
        {
            throw new a(position, amW);
        } else
        {
            byte abyte0[] = buffer;
            int i = position;
            position = i + 1;
            abyte0[i] = byte0;
            return;
        }
    }

    public final void b(int i, float f1)
    {
        t(i, 5);
        d(f1);
    }

    public final void b(int i, long l)
    {
        t(i, 0);
        B(l);
    }

    public final void b(int i, String s1)
    {
        t(i, 2);
        cy(s1);
    }

    public final void b(me me1)
    {
        eI(me1.nZ());
        me1.a(this);
    }

    public final void c(int i, long l)
    {
        t(i, 0);
        C(l);
    }

    public final void c(byte abyte0[], int i, int j)
    {
        if (amW - position >= j)
        {
            System.arraycopy(abyte0, i, buffer, position, j);
            position = j + position;
            return;
        } else
        {
            throw new a(position, amW);
        }
    }

    public final void cy(String s1)
    {
        byte abyte0[] = s1.getBytes("UTF-8");
        eI(abyte0.length);
        t(abyte0);
    }

    public final void d(float f1)
    {
        eK(Float.floatToIntBits(f1));
    }

    public final void e(double d1)
    {
        H(Double.doubleToLongBits(d1));
    }

    public final void eC(int i)
    {
        if (i >= 0)
        {
            eI(i);
            return;
        } else
        {
            F(i);
            return;
        }
    }

    public final void eD(int i)
    {
        eI(eL(i));
    }

    public final void eG(int i)
    {
        b((byte)i);
    }

    public final void eI(int i)
    {
        do
        {
            if ((i & 0xffffff80) == 0)
            {
                eG(i);
                return;
            }
            eG(0x80 | i & 0x7f);
            i >>>= 7;
        } while (true);
    }

    public final void eK(int i)
    {
        eG(i & 0xff);
        eG(0xff & i >> 8);
        eG(0xff & i >> 16);
        eG(i >>> 24);
    }

    public final int nQ()
    {
        return amW - position;
    }

    public final void nR()
    {
        if (nQ() != 0)
        {
            throw new IllegalStateException("Did not write as much data as expected.");
        } else
        {
            return;
        }
    }

    public final void p(int i, int j)
    {
        t(i, 0);
        eC(j);
    }

    public final void q(int i, int j)
    {
        t(i, 0);
        eD(j);
    }

    public final void r(byte abyte0[])
    {
        eI(abyte0.length);
        t(abyte0);
    }

    public final void t(int i, int j)
    {
        eI(mh.u(i, j));
    }

    public final void t(byte abyte0[])
    {
        c(abyte0, 0, abyte0.length);
    }

    private class a extends IOException
    {

        a(int i, int j)
        {
            super((new StringBuilder("CodedOutputStream was writing to a flat byte array and ran out of space (pos ")).append(i).append(" limit ").append(j).append(").").toString());
        }
    }

}
