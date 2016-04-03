// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder.ec;


// Referenced classes of package com.google.zxing.pdf417.decoder.ec:
//            ModulusGF

final class a
{

    private final ModulusGF a;
    private final int b[];

    a(ModulusGF modulusgf, int ai[])
    {
        int i = 1;
        super();
        if (ai.length == 0)
        {
            throw new IllegalArgumentException();
        }
        a = modulusgf;
        int j = ai.length;
        if (j > i && ai[0] == 0)
        {
            for (; i < j && ai[i] == 0; i++) { }
            if (i == j)
            {
                b = modulusgf.a().b;
                return;
            } else
            {
                b = new int[j - i];
                System.arraycopy(ai, i, b, 0, b.length);
                return;
            }
        } else
        {
            b = ai;
            return;
        }
    }

    final int a()
    {
        return -1 + b.length;
    }

    final int a(int i)
    {
        return b[(-1 + b.length) - i];
    }

    final a a(int i, int j)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException();
        }
        if (j == 0)
        {
            return a.a();
        }
        int k = b.length;
        int ai[] = new int[k + i];
        for (int l = 0; l < k; l++)
        {
            ai[l] = a.d(b[l], j);
        }

        return new a(a, ai);
    }

    final a a(a a1)
    {
        if (!a.equals(a1.a))
        {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        }
        if (b())
        {
            return a1;
        }
        if (a1.b())
        {
            return this;
        }
        int ai[] = b;
        int ai1[] = a1.b;
        int ai3[];
        int i;
        if (ai.length <= ai1.length)
        {
            int ai2[] = ai;
            ai = ai1;
            ai1 = ai2;
        }
        ai3 = new int[ai.length];
        i = ai.length - ai1.length;
        System.arraycopy(ai, 0, ai3, 0, i);
        for (int j = i; j < ai.length; j++)
        {
            ai3[j] = a.b(ai1[j - i], ai[j]);
        }

        return new a(a, ai3);
    }

    final int b(int i)
    {
        int j = 0;
        if (i != 0) goto _L2; else goto _L1
_L1:
        int l = a(0);
_L4:
        return l;
_L2:
        int k;
        k = b.length;
        if (i != 1)
        {
            break; /* Loop/switch isn't completed */
        }
        int ai[] = b;
        int k1 = ai.length;
        l = 0;
        while (j < k1) 
        {
            int l1 = ai[j];
            l = a.b(l, l1);
            j++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        l = b[0];
        int i1 = 1;
        while (i1 < k) 
        {
            int j1 = a.b(a.d(i, l), b[i1]);
            i1++;
            l = j1;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    final a b(a a1)
    {
        if (!a.equals(a1.a))
        {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        }
        if (a1.b())
        {
            return this;
        } else
        {
            return a(a1.c());
        }
    }

    final boolean b()
    {
        int i = b[0];
        boolean flag = false;
        if (i == 0)
        {
            flag = true;
        }
        return flag;
    }

    final a c()
    {
        int i = b.length;
        int ai[] = new int[i];
        for (int j = 0; j < i; j++)
        {
            ai[j] = a.c(0, b[j]);
        }

        return new a(a, ai);
    }

    final a c(int i)
    {
        if (i == 0)
        {
            this = a.a();
        } else
        if (i != 1)
        {
            int j = b.length;
            int ai[] = new int[j];
            for (int k = 0; k < j; k++)
            {
                ai[k] = a.d(b[k], i);
            }

            return new a(a, ai);
        }
        return this;
    }

    final a c(a a1)
    {
        if (!a.equals(a1.a))
        {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        }
        if (b() || a1.b())
        {
            return a.a();
        }
        int ai[] = b;
        int i = ai.length;
        int ai1[] = a1.b;
        int j = ai1.length;
        int ai2[] = new int[-1 + (i + j)];
        for (int k = 0; k < i; k++)
        {
            int l = ai[k];
            for (int i1 = 0; i1 < j; i1++)
            {
                ai2[k + i1] = a.b(ai2[k + i1], a.d(l, ai1[i1]));
            }

        }

        return new a(a, ai2);
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(8 * (-1 + b.length));
        int i = -1 + b.length;
        do
        {
            if (i >= 0)
            {
                int j = a(i);
                if (j != 0)
                {
                    if (j < 0)
                    {
                        stringbuilder.append(" - ");
                        j = -j;
                    } else
                    if (stringbuilder.length() > 0)
                    {
                        stringbuilder.append(" + ");
                    }
                    if (i == 0 || j != 1)
                    {
                        stringbuilder.append(j);
                    }
                    if (i != 0)
                    {
                        if (i == 1)
                        {
                            stringbuilder.append('x');
                        } else
                        {
                            stringbuilder.append("x^");
                            stringbuilder.append(i);
                        }
                    }
                }
                i--;
                continue;
            }
            return stringbuilder.toString();
        } while (true);
    }
}
