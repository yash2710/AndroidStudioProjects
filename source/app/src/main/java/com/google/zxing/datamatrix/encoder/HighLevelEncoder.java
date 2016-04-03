// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.nio.charset.Charset;
import java.util.Arrays;

// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            SymbolShapeHint, f, a, c, 
//            h, i, e, b, 
//            g, SymbolInfo

public final class HighLevelEncoder
{

    private HighLevelEncoder()
    {
    }

    static int a(CharSequence charsequence, int j, int k)
    {
        if (j < charsequence.length()) goto _L2; else goto _L1
_L1:
        int i2 = k;
_L4:
        return i2;
_L2:
        float af[];
        int l;
        int ai1[];
        int j2;
        int l2;
        if (k == 0)
        {
            af = (new float[] {
                0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.25F
            });
        } else
        {
            af = (new float[] {
                1.0F, 2.0F, 2.0F, 2.0F, 2.0F, 2.25F
            });
            af[k] = 0.0F;
        }
        l = 0;
_L6:
        int ai[];
        byte abyte0[];
        int i1;
        do
        {
            if (j + l == charsequence.length())
            {
                byte abyte1[] = new byte[6];
                ai1 = new int[6];
                j2 = a(af, ai1, 0x7fffffff, abyte1);
                int k2 = a(abyte1);
                l2 = ai1[0];
                i2 = 0;
                if (l2 != j2)
                {
                    if (k2 == 1 && abyte1[5] > 0)
                    {
                        return 5;
                    }
                    if (k2 == 1 && abyte1[4] > 0)
                    {
                        return 4;
                    }
                    if (k2 == 1 && abyte1[2] > 0)
                    {
                        return 2;
                    }
                    return k2 != 1 || abyte1[3] <= 0 ? 1 : 3;
                }
                continue; /* Loop/switch isn't completed */
            }
            char c1 = charsequence.charAt(j + l);
            l++;
            boolean flag;
            boolean flag1;
            boolean flag2;
            int k1;
            int l1;
            if (a(c1))
            {
                af[0] = (float)(0.5D + (double)af[0]);
            } else
            if (b(c1))
            {
                af[0] = (int)Math.ceil(af[0]);
                af[0] = 2.0F + af[0];
            } else
            {
                af[0] = (int)Math.ceil(af[0]);
                af[0] = 1.0F + af[0];
            }
            if (c1 == ' ' || c1 >= '0' && c1 <= '9' || c1 >= 'A' && c1 <= 'Z')
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                af[1] = 0.6666667F + af[1];
            } else
            if (b(c1))
            {
                af[1] = 2.666667F + af[1];
            } else
            {
                af[1] = 1.333333F + af[1];
            }
            if (c1 == ' ' || c1 >= '0' && c1 <= '9' || c1 >= 'a' && c1 <= 'z')
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                af[2] = 0.6666667F + af[2];
            } else
            if (b(c1))
            {
                af[2] = 2.666667F + af[2];
            } else
            {
                af[2] = 1.333333F + af[2];
            }
            if (d(c1))
            {
                af[3] = 0.6666667F + af[3];
            } else
            if (b(c1))
            {
                af[3] = 4.333333F + af[3];
            } else
            {
                af[3] = 3.333333F + af[3];
            }
            if (c1 >= ' ' && c1 <= '^')
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (flag2)
            {
                af[4] = 0.75F + af[4];
            } else
            if (b(c1))
            {
                af[4] = 4.25F + af[4];
            } else
            {
                af[4] = 3.25F + af[4];
            }
            af[5] = 1.0F + af[5];
        } while (l < 4);
        ai = new int[6];
        abyte0 = new byte[6];
        a(af, ai, 0x7fffffff, abyte0);
        i1 = a(abyte0);
        if (ai[0] >= ai[5] || ai[0] >= ai[1] || ai[0] >= ai[2] || ai[0] >= ai[3])
        {
            break; /* Loop/switch isn't completed */
        }
        k1 = ai[0];
        l1 = ai[4];
        i2 = 0;
        if (k1 < l1) goto _L4; else goto _L3
_L3:
        if (ai[5] < ai[0] || abyte0[1] + abyte0[2] + abyte0[3] + abyte0[4] == 0)
        {
            return 5;
        }
        if (i1 == 1 && abyte0[4] > 0)
        {
            return 4;
        }
        if (i1 == 1 && abyte0[2] > 0)
        {
            return 2;
        }
        if (i1 == 1 && abyte0[3] > 0)
        {
            return 3;
        }
        if (1 + ai[1] >= ai[0] || 1 + ai[1] >= ai[5] || 1 + ai[1] >= ai[4] || 1 + ai[1] >= ai[2]) goto _L6; else goto _L5
_L5:
        if (ai[1] < ai[3])
        {
            return 1;
        }
        if (ai[1] != ai[3]) goto _L6; else goto _L7
_L7:
        int j1 = 1 + (j + l);
        do
        {
            if (j1 >= charsequence.length())
            {
                break;
            }
            char c2 = charsequence.charAt(j1);
            if (e(c2))
            {
                return 3;
            }
            if (!d(c2))
            {
                break;
            }
            j1++;
        } while (true);
        return 1;
        if (true) goto _L4; else goto _L8
_L8:
    }

    private static int a(byte abyte0[])
    {
        int j = 0;
        int k = 0;
        for (; j < 6; j++)
        {
            k += abyte0[j];
        }

        return k;
    }

    private static int a(float af[], int ai[], int j, byte abyte0[])
    {
        Arrays.fill(abyte0, (byte)0);
        int k = 0;
        int l = j;
        for (; k < 6; k++)
        {
            ai[k] = (int)Math.ceil(af[k]);
            int i1 = ai[k];
            if (l > i1)
            {
                Arrays.fill(abyte0, (byte)0);
                l = i1;
            }
            if (l == i1)
            {
                abyte0[k] = (byte)(1 + abyte0[k]);
            }
        }

        return l;
    }

    static boolean a(char c1)
    {
        return c1 >= '0' && c1 <= '9';
    }

    static boolean b(char c1)
    {
        return c1 >= '\200' && c1 <= '\377';
    }

    static void c(char c1)
    {
        String s = Integer.toHexString(c1);
        String s1 = (new StringBuilder()).append("0000".substring(0, 4 - s.length())).append(s).toString();
        throw new IllegalArgumentException((new StringBuilder("Illegal character: ")).append(c1).append(" (0x").append(s1).append(')').toString());
    }

    private static boolean d(char c1)
    {
        return e(c1) || c1 == ' ' || c1 >= '0' && c1 <= '9' || c1 >= 'A' && c1 <= 'Z';
    }

    public static int determineConsecutiveDigitCount(CharSequence charsequence, int j)
    {
        int k = charsequence.length();
        int l = 0;
        if (j < k)
        {
            char c1 = charsequence.charAt(j);
            do
            {
                if (!a(c1) || j >= k)
                {
                    break;
                }
                l++;
                if (++j < k)
                {
                    c1 = charsequence.charAt(j);
                }
            } while (true);
        }
        return l;
    }

    private static boolean e(char c1)
    {
        return c1 == '\r' || c1 == '*' || c1 == '>';
    }

    public static String encodeHighLevel(String s)
    {
        return encodeHighLevel(s, SymbolShapeHint.FORCE_NONE, null, null);
    }

    public static String encodeHighLevel(String s, SymbolShapeHint symbolshapehint, Dimension dimension, Dimension dimension1)
    {
        int j;
        f af[];
        g g1;
        j = 0;
        af = new f[6];
        af[0] = new a();
        af[1] = new c();
        af[2] = new h();
        af[3] = new i();
        af[4] = new e();
        af[5] = new b();
        g1 = new g(s);
        g1.setSymbolShape(symbolshapehint);
        g1.setSizeConstraints(dimension, dimension1);
        if (!s.startsWith("[)>\03605\035") || !s.endsWith("\036\004")) goto _L2; else goto _L1
_L1:
        g1.writeCodeword('\354');
        g1.setSkipAtEnd(2);
        g1.c = 7 + g1.c;
_L4:
        if (!g1.hasMoreCharacters())
        {
            break; /* Loop/switch isn't completed */
        }
        af[j].encode(g1);
        if (g1.d >= 0)
        {
            j = g1.d;
            g1.resetEncoderSignal();
        }
        continue; /* Loop/switch isn't completed */
_L2:
        boolean flag = s.startsWith("[)>\03606\035");
        j = 0;
        if (flag)
        {
            boolean flag1 = s.endsWith("\036\004");
            j = 0;
            if (flag1)
            {
                g1.writeCodeword('\355');
                g1.setSkipAtEnd(2);
                g1.c = 7 + g1.c;
                j = 0;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        int k = g1.b.length();
        g1.updateSymbolInfo();
        int l = g1.e.a;
        if (k < l && j != 0 && j != 5)
        {
            g1.writeCodeword('\376');
        }
        StringBuilder stringbuilder = g1.b;
        if (stringbuilder.length() < l)
        {
            stringbuilder.append('\201');
        }
        while (stringbuilder.length() < l) 
        {
            int i1 = 129 + (1 + (149 * (1 + stringbuilder.length())) % 253);
            char c1;
            if (i1 <= 254)
            {
                c1 = (char)i1;
            } else
            {
                c1 = (char)(i1 - 254);
            }
            stringbuilder.append(c1);
        }
        return g1.b.toString();
    }

    public static byte[] getBytesForMessage(String s)
    {
        return s.getBytes(Charset.forName("cp437"));
    }
}
