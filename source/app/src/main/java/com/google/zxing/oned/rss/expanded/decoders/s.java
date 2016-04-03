// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            l, p, o, k, 
//            n, r

final class s
{

    private final BitArray a;
    private final l b = new l();
    private final StringBuilder c = new StringBuilder();

    s(BitArray bitarray)
    {
        a = bitarray;
    }

    static int a(BitArray bitarray, int i, int j)
    {
        int i1 = 0;
        if (j > 32)
        {
            throw new IllegalArgumentException("extractNumberValueFromBitArray can't handle more than 32 bits");
        }
        for (int j1 = 0; j1 < j; j1++)
        {
            if (bitarray.get(i + j1))
            {
                i1 |= 1 << -1 + (j - j1);
            }
        }

        return i1;
    }

    private k a()
    {
_L6:
        int i = b.a();
        if (i + 7 <= a.getSize()) goto _L2; else goto _L1
_L1:
        boolean flag;
        int k1;
        p p1;
        if (i + 4 <= a.getSize())
        {
            flag = true;
        } else
        {
            flag = false;
        }
_L3:
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        k1 = b.a();
        if (k1 + 7 > a.getSize())
        {
            int k2 = a(k1, 4);
            int j;
            if (k2 == 0)
            {
                p1 = new p(a.getSize(), 10, 10);
            } else
            {
                p1 = new p(a.getSize(), k2 - 1, 10);
            }
        } else
        {
            int l1 = a(k1, 7);
            int i2 = (l1 - 8) / 11;
            int j2 = (l1 - 8) % 11;
            p1 = new p(k1 + 7, i2, j2);
        }
        b.a(p1.e());
        if (p1.c())
        {
            o o1;
            if (p1.d())
            {
                o1 = new o(b.a(), c.toString());
            } else
            {
                o1 = new o(b.a(), c.toString(), p1.b());
            }
            return new k(o1, true);
        }
        break MISSING_BLOCK_LABEL_318;
_L2:
        j = i;
_L4:
label0:
        {
            if (j >= i + 3)
            {
                break MISSING_BLOCK_LABEL_197;
            }
            if (!a.get(j))
            {
                break label0;
            }
            flag = true;
        }
          goto _L3
        j++;
          goto _L4
        flag = a.get(i + 3);
          goto _L3
        c.append(p1.a());
        if (p1.d())
        {
            return new k(new o(b.a(), c.toString()), true);
        }
        c.append(p1.b());
        if (true) goto _L6; else goto _L5
_L5:
        int i1 = b.a();
        if (i1 + 1 <= a.getSize()) goto _L8; else goto _L7
_L7:
        boolean flag1 = false;
_L10:
        if (flag1)
        {
            b.e();
            b.b(4);
        }
        return new k(false);
_L8:
        int j1 = 0;
        do
        {
            if (j1 >= 4 || j1 + i1 >= a.getSize())
            {
                break;
            }
            if (a.get(i1 + j1))
            {
                flag1 = false;
                continue; /* Loop/switch isn't completed */
            }
            j1++;
        } while (true);
        flag1 = true;
        if (true) goto _L10; else goto _L9
_L9:
    }

    private boolean a(int i)
    {
        if (i + 1 <= a.getSize()) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int j = 0;
_L6:
        if (j >= 5 || j + i >= a.getSize())
        {
            break MISSING_BLOCK_LABEL_74;
        }
        if (j != 2) goto _L4; else goto _L3
_L3:
        if (!a.get(i + 2)) goto _L1; else goto _L5
_L5:
        j++;
          goto _L6
_L4:
        if (!a.get(i + j)) goto _L5; else goto _L7
_L7:
        return false;
        return true;
    }

    private k b()
    {
_L34:
        int i = b.a();
        if (i + 5 > a.getSize()) goto _L2; else goto _L1
_L1:
        int l1 = a(i, 5);
        if (l1 < 5 || l1 >= 16) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        int j;
        n n1;
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        j = b.a();
        int i1 = a(j, 5);
        int i2;
        int j2;
        if (i1 == 15)
        {
            n1 = new n(j + 5, '$');
        } else
        if (i1 >= 5 && i1 < 15)
        {
            n1 = new n(j + 5, (char)(-5 + (i1 + 48)));
        } else
        {
            int j1 = a(j, 7);
            if (j1 >= 64 && j1 < 90)
            {
                n1 = new n(j + 7, (char)(j1 + 1));
            } else
            {
label0:
                {
                    if (j1 < 90 || j1 >= 116)
                    {
                        break label0;
                    }
                    n1 = new n(j + 7, (char)(j1 + 7));
                }
            }
        }
_L31:
        b.a(n1.e());
        if (n1.b())
        {
            return new k(new o(b.a(), c.toString()), true);
        }
        break MISSING_BLOCK_LABEL_639;
_L4:
        if (i + 7 > a.getSize()) goto _L2; else goto _L5
_L5:
label1:
        {
            i2 = a(i, 7);
            if (i2 < 64 || i2 >= 116)
            {
                break label1;
            }
            flag = true;
        }
          goto _L6
        if (i + 8 > a.getSize()) goto _L2; else goto _L7
_L7:
        j2 = a(i, 8);
        if (j2 < 232 || j2 >= 253) goto _L2; else goto _L8
_L8:
        flag = true;
          goto _L6
_L2:
        flag = false;
          goto _L6
        int k1 = a(j, 8);
        k1;
        JVM INSTR tableswitch 232 252: default 452
    //                   232 477
    //                   233 499
    //                   234 506
    //                   235 513
    //                   236 520
    //                   237 527
    //                   238 534
    //                   239 541
    //                   240 548
    //                   241 555
    //                   242 562
    //                   243 569
    //                   244 576
    //                   245 583
    //                   246 590
    //                   247 597
    //                   248 604
    //                   249 611
    //                   250 618
    //                   251 625
    //                   252 632;
           goto _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30
_L30:
        break MISSING_BLOCK_LABEL_632;
_L9:
        throw new IllegalArgumentException((new StringBuilder("Decoding invalid ISO/IEC 646 value: ")).append(k1).toString());
_L10:
        char c1 = '!';
_L32:
        n1 = new n(j + 8, c1);
          goto _L31
_L11:
        c1 = '"';
          goto _L32
_L12:
        c1 = '%';
          goto _L32
_L13:
        c1 = '&';
          goto _L32
_L14:
        c1 = '\'';
          goto _L32
_L15:
        c1 = '(';
          goto _L32
_L16:
        c1 = ')';
          goto _L32
_L17:
        c1 = '*';
          goto _L32
_L18:
        c1 = '+';
          goto _L32
_L19:
        c1 = ',';
          goto _L32
_L20:
        c1 = '-';
          goto _L32
_L21:
        c1 = '.';
          goto _L32
_L22:
        c1 = '/';
          goto _L32
_L23:
        c1 = ':';
          goto _L32
_L24:
        c1 = ';';
          goto _L32
_L25:
        c1 = '<';
          goto _L32
_L26:
        c1 = '=';
          goto _L32
_L27:
        c1 = '>';
          goto _L32
_L28:
        c1 = '?';
          goto _L32
_L29:
        c1 = '_';
          goto _L32
        c1 = ' ';
          goto _L32
        c.append(n1.a());
        if (true) goto _L34; else goto _L33
_L33:
        if (!b(b.a())) goto _L36; else goto _L35
_L35:
        b.b(3);
        b.d();
_L38:
        return new k(false);
_L36:
        if (a(b.a()))
        {
            if (5 + b.a() < a.getSize())
            {
                b.b(5);
            } else
            {
                b.a(a.getSize());
            }
            b.e();
        }
        if (true) goto _L38; else goto _L37
_L37:
    }

    private boolean b(int i)
    {
        if (i + 3 <= a.getSize()) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int j = i;
label0:
        do
        {
label1:
            {
                if (j >= i + 3)
                {
                    break label1;
                }
                if (a.get(j))
                {
                    break label0;
                }
                j++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return true;
    }

    final int a(int i, int j)
    {
        return a(a, i, j);
    }

    final o a(int i, String s1)
    {
        c.setLength(0);
        if (s1 != null)
        {
            c.append(s1);
        }
        b.a(i);
_L9:
        int j;
        j = b.a();
        if (!b.b())
        {
            break MISSING_BLOCK_LABEL_650;
        }
_L21:
        int i1 = b.a();
        if (i1 + 5 > a.getSize()) goto _L2; else goto _L1
_L1:
        int j2 = a(i1, 5);
        if (j2 < 5 || j2 >= 16) goto _L4; else goto _L3
_L3:
        boolean flag2 = true;
_L12:
        if (!flag2) goto _L6; else goto _L5
_L5:
        k k1;
        boolean flag;
        int j1;
        n n1;
        j1 = b.a();
        int l1 = a(j1, 5);
        o o1;
        int k2;
        if (l1 == 15)
        {
            n1 = new n(j1 + 5, '$');
        } else
        if (l1 >= 5 && l1 < 15)
        {
            n1 = new n(j1 + 5, (char)(-5 + (l1 + 48)));
        } else
        {
label0:
            {
                i2 = a(j1, 6);
                if (i2 < 32 || i2 >= 58)
                {
                    break label0;
                }
                n1 = new n(j1 + 6, (char)(i2 + 33));
            }
        }
_L19:
        b.a(n1.e());
        if (!n1.b()) goto _L8; else goto _L7
_L7:
        k1 = new k(new o(b.a(), c.toString()), true);
_L22:
        flag = k1.b();
_L23:
        boolean flag1;
        if (j != b.a())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1 && !flag || flag)
        {
            o1 = k1.a();
            int i2;
            char c1;
            if (o1 != null && o1.b())
            {
                return new o(b.a(), c.toString(), o1.c());
            } else
            {
                return new o(b.a(), c.toString());
            }
        }
          goto _L9
_L4:
        if (i1 + 6 > a.getSize()) goto _L2; else goto _L10
_L10:
        k2 = a(i1, 6);
        if (k2 < 16 || k2 >= 63) goto _L2; else goto _L11
_L11:
        flag2 = true;
          goto _L12
_L2:
        flag2 = false;
          goto _L12
        i2;
        JVM INSTR tableswitch 58 62: default 448
    //                   58 473
    //                   59 496
    //                   60 503
    //                   61 510
    //                   62 517;
           goto _L13 _L14 _L15 _L16 _L17 _L18
_L18:
        break MISSING_BLOCK_LABEL_517;
_L13:
        throw new IllegalStateException((new StringBuilder("Decoding invalid alphanumeric value: ")).append(i2).toString());
_L14:
        c1 = '*';
_L20:
        n1 = new n(j1 + 6, c1);
          goto _L19
_L15:
        c1 = ',';
          goto _L20
_L16:
        c1 = '-';
          goto _L20
_L17:
        c1 = '.';
          goto _L20
        c1 = '/';
          goto _L20
_L8:
        c.append(n1.a());
          goto _L21
_L6:
        if (b(b.a()))
        {
            b.b(3);
            b.d();
        } else
        if (a(b.a()))
        {
            if (5 + b.a() < a.getSize())
            {
                b.b(5);
            } else
            {
                b.a(a.getSize());
            }
            b.f();
        }
        k1 = new k(false);
          goto _L22
        if (b.c())
        {
            k1 = b();
            flag = k1.b();
        } else
        {
            k1 = a();
            flag = k1.b();
        }
          goto _L23
    }

    final String a(StringBuilder stringbuilder, int i)
    {
        String s1 = null;
        do
        {
            o o1 = a(i, s1);
            String s2 = r.a(o1.a());
            if (s2 != null)
            {
                stringbuilder.append(s2);
            }
            if (o1.b())
            {
                s1 = String.valueOf(o1.c());
            } else
            {
                s1 = null;
            }
            if (i != o1.e())
            {
                i = o1.e();
            } else
            {
                return stringbuilder.toString();
            }
        } while (true);
    }
}
