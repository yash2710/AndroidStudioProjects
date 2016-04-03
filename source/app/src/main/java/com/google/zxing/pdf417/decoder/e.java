// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.math.BigInteger;
import java.util.Arrays;

// Referenced classes of package com.google.zxing.pdf417.decoder:
//            g, f

final class e
{

    private static final char a[] = {
        ';', '<', '>', '@', '[', '\\', '}', '_', '`', '~', 
        '!', '\r', '\t', ',', ':', '\n', '-', '.', '$', '/', 
        '"', '|', '*', '(', ')', '?', '{', '}', '\''
    };
    private static final char b[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '&', '\r', '\t', ',', ':', '#', '-', '.', '$', '/', 
        '+', '%', '*', '=', '^'
    };
    private static final BigInteger c[];

    private static int a(int i, int ai[], int j, StringBuilder stringbuilder)
    {
label0:
        {
            {
                if (i != 901)
                {
                    break label0;
                }
                int i2 = 0;
                long l3 = 0L;
                char ac1[] = new char[6];
                int ai1[] = new int[6];
                boolean flag1 = false;
                int j2 = j + 1;
                int k2 = ai[j];
                while (j2 < ai[0] && !flag1) 
                {
                    int k3 = i2 + 1;
                    ai1[i2] = k2;
                    l3 = l3 * 900L + (long)k2;
                    int i4 = j2 + 1;
                    k2 = ai[j2];
                    int k;
                    long l;
                    boolean flag;
                    int i1;
                    int j1;
                    int k1;
                    char ac[];
                    int l1;
                    long l2;
                    int i3;
                    int j3;
                    if (k2 == 900 || k2 == 901 || k2 == 902 || k2 == 924 || k2 == 928 || k2 == 923 || k2 == 922)
                    {
                        j2 = i4 - 1;
                        flag1 = true;
                        i2 = k3;
                    } else
                    if (k3 % 5 == 0 && k3 > 0)
                    {
                        for (int j4 = 0; j4 < 6;)
                        {
                            ac1[5 - j4] = (char)(int)(l3 % 256L);
                            long l4 = l3 >> 8;
                            j4++;
                            l3 = l4;
                        }

                        stringbuilder.append(ac1);
                        j2 = i4;
                        i2 = 0;
                    } else
                    {
                        j2 = i4;
                        i2 = k3;
                    }
                }
                if (j2 == ai[0] && k2 < 900)
                {
                    j3 = i2 + 1;
                    ai1[i2] = k2;
                    i2 = j3;
                }
                for (i3 = 0; i3 < i2; i3++)
                {
                    stringbuilder.append((char)ai1[i3]);
                }

                j = j2;
            }
            return j;
        }
        if (i != 924)
        {
            continue;
        }
        k = 0;
        l = 0L;
        flag = false;
        do
        {
            i1 = ai[0];
            if (j >= i1 || flag)
            {
                continue;
            }
            j1 = j + 1;
            k1 = ai[j];
            if (k1 < 900)
            {
                k++;
                l = l * 900L + (long)k1;
                j = j1;
            } else
            if (k1 == 900 || k1 == 901 || k1 == 902 || k1 == 924 || k1 == 928 || k1 == 923 || k1 == 922)
            {
                j = j1 - 1;
                flag = true;
            } else
            {
                j = j1;
            }
            if (k % 5 == 0 && k > 0)
            {
                ac = new char[6];
                for (l1 = 0; l1 < 6;)
                {
                    ac[5 - l1] = (char)(int)(255L & l);
                    l2 = l >> 8;
                    l1++;
                    l = l2;
                }

                stringbuilder.append(ac);
                k = 0;
            }
        } while (true);
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_294;
_L1:
    }

    private static int a(int ai[], int i, PDF417ResultMetadata pdf417resultmetadata)
    {
        if (i + 2 > ai[0])
        {
            throw FormatException.getFormatInstance();
        }
        int ai1[] = new int[2];
        for (int j = 0; j < 2;)
        {
            ai1[j] = ai[i];
            j++;
            i++;
        }

        pdf417resultmetadata.setSegmentIndex(Integer.parseInt(a(ai1, 2)));
        StringBuilder stringbuilder = new StringBuilder();
        int k = a(ai, i, stringbuilder);
        pdf417resultmetadata.setFileId(stringbuilder.toString());
        if (ai[k] == 923)
        {
            int l = k + 1;
            int ai2[] = new int[ai[0] - l];
            int i1 = 0;
            k = l;
            for (boolean flag = false; k < ai[0] && !flag;)
            {
                int j1 = k + 1;
                int k1 = ai[k];
                if (k1 < 900)
                {
                    int l1 = i1 + 1;
                    ai2[i1] = k1;
                    i1 = l1;
                    k = j1;
                } else
                {
                    switch (k1)
                    {
                    default:
                        throw FormatException.getFormatInstance();

                    case 922: 
                        pdf417resultmetadata.setLastSegment(true);
                        break;
                    }
                    k = j1 + 1;
                    flag = true;
                }
            }

            pdf417resultmetadata.setOptionalData(Arrays.copyOf(ai2, i1));
        } else
        if (ai[k] == 922)
        {
            pdf417resultmetadata.setLastSegment(true);
            return k + 1;
        }
        return k;
    }

    private static int a(int ai[], int i, StringBuilder stringbuilder)
    {
        int ai1[];
        int ai2[];
        int j;
        ai1 = new int[ai[0] - i << 1];
        ai2 = new int[ai[0] - i << 1];
        j = 0;
_L12:
        g g1;
        g g2;
        int k;
        for (boolean flag = false; i < ai[0] && !flag;)
        {
            int j1 = i + 1;
            int k1 = ai[i];
            if (k1 < 900)
            {
                ai1[j] = k1 / 30;
                ai1[j + 1] = k1 % 30;
                j += 2;
                i = j1;
            } else
            {
                switch (k1)
                {
                default:
                    i = j1;
                    break;

                case 900: 
                    int l1 = j + 1;
                    ai1[j] = 900;
                    j = l1;
                    i = j1;
                    break;

                case 901: 
                    i = j1 - 1;
                    flag = true;
                    break;

                case 902: 
                    i = j1 - 1;
                    flag = true;
                    break;

                case 928: 
                    i = j1 - 1;
                    flag = true;
                    break;

                case 923: 
                    i = j1 - 1;
                    flag = true;
                    break;

                case 922: 
                    i = j1 - 1;
                    flag = true;
                    break;

                case 913: 
                    ai1[j] = 913;
                    i = j1 + 1;
                    ai2[j] = ai[j1];
                    j++;
                    break;

                case 924: 
                    i = j1 - 1;
                    flag = true;
                    break;
                }
                continue; /* Loop/switch isn't completed */
            }
        }

        g1 = g.a;
        g2 = g.a;
        k = 0;
_L10:
        if (k >= j) goto _L2; else goto _L1
_L1:
        int l;
        int i1;
        char c1;
        l = ai1[k];
        i1 = f.a[g1.ordinal()];
        c1 = '\0';
        i1;
        JVM INSTR tableswitch 1 6: default 368
    //                   1 386
    //                   2 531
    //                   3 688
    //                   4 851
    //                   5 934
    //                   6 993;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L3:
        if (c1 != 0)
        {
            stringbuilder.append(c1);
        }
        k++;
          goto _L10
_L4:
        if (l < 26)
        {
            c1 = (char)(l + 65);
        } else
        if (l == 26)
        {
            c1 = ' ';
        } else
        if (l == 27)
        {
            g1 = g.b;
            c1 = '\0';
        } else
        if (l == 28)
        {
            g1 = g.c;
            c1 = '\0';
        } else
        if (l == 29)
        {
            g g9 = g.f;
            g g10 = g1;
            g1 = g9;
            g2 = g10;
            c1 = '\0';
        } else
        if (l == 913)
        {
            stringbuilder.append((char)ai2[k]);
            c1 = '\0';
        } else
        {
            c1 = '\0';
            if (l == 900)
            {
                g1 = g.a;
                c1 = '\0';
            }
        }
          goto _L3
_L5:
        if (l < 26)
        {
            c1 = (char)(l + 97);
        } else
        if (l == 26)
        {
            c1 = ' ';
        } else
        if (l == 27)
        {
            g g7 = g.e;
            g g8 = g1;
            g1 = g7;
            g2 = g8;
            c1 = '\0';
        } else
        if (l == 28)
        {
            g1 = g.c;
            c1 = '\0';
        } else
        if (l == 29)
        {
            g g5 = g.f;
            g g6 = g1;
            g1 = g5;
            g2 = g6;
            c1 = '\0';
        } else
        if (l == 913)
        {
            stringbuilder.append((char)ai2[k]);
            c1 = '\0';
        } else
        {
            c1 = '\0';
            if (l == 900)
            {
                g1 = g.a;
                c1 = '\0';
            }
        }
          goto _L3
_L6:
        if (l < 25)
        {
            c1 = b[l];
        } else
        if (l == 25)
        {
            g1 = g.d;
            c1 = '\0';
        } else
        if (l == 26)
        {
            c1 = ' ';
        } else
        if (l == 27)
        {
            g1 = g.b;
            c1 = '\0';
        } else
        if (l == 28)
        {
            g1 = g.a;
            c1 = '\0';
        } else
        if (l == 29)
        {
            g g3 = g.f;
            g g4 = g1;
            g1 = g3;
            g2 = g4;
            c1 = '\0';
        } else
        if (l == 913)
        {
            stringbuilder.append((char)ai2[k]);
            c1 = '\0';
        } else
        {
            c1 = '\0';
            if (l == 900)
            {
                g1 = g.a;
                c1 = '\0';
            }
        }
          goto _L3
_L7:
        if (l < 29)
        {
            c1 = a[l];
        } else
        if (l == 29)
        {
            g1 = g.a;
            c1 = '\0';
        } else
        if (l == 913)
        {
            stringbuilder.append((char)ai2[k]);
            c1 = '\0';
        } else
        {
            c1 = '\0';
            if (l == 900)
            {
                g1 = g.a;
                c1 = '\0';
            }
        }
          goto _L3
_L8:
        if (l < 26)
        {
            c1 = (char)(l + 65);
            g1 = g2;
        } else
        if (l == 26)
        {
            c1 = ' ';
            g1 = g2;
        } else
        {
            if (l != 900)
            {
                break MISSING_BLOCK_LABEL_1083;
            }
            g1 = g.a;
            c1 = '\0';
        }
          goto _L3
_L9:
        if (l < 29)
        {
            c1 = a[l];
            g1 = g2;
        } else
        if (l == 29)
        {
            g1 = g.a;
            c1 = '\0';
        } else
        if (l == 913)
        {
            stringbuilder.append((char)ai2[k]);
            g1 = g2;
            c1 = '\0';
        } else
        {
            if (l != 900)
            {
                break MISSING_BLOCK_LABEL_1083;
            }
            g1 = g.a;
            c1 = '\0';
        }
          goto _L3
_L2:
        return i;
        g1 = g2;
        c1 = '\0';
          goto _L3
        if (true) goto _L12; else goto _L11
_L11:
    }

    static DecoderResult a(int ai[], String s)
    {
        StringBuilder stringbuilder;
        int i;
        int j;
        PDF417ResultMetadata pdf417resultmetadata;
        stringbuilder = new StringBuilder(ai.length << 1);
        i = 2;
        j = ai[1];
        pdf417resultmetadata = new PDF417ResultMetadata();
_L8:
        if (i >= ai[0])
        {
            break MISSING_BLOCK_LABEL_204;
        }
        j;
        JVM INSTR lookupswitch 6: default 96
    //                   900: 127
    //                   901: 138
    //                   902: 151
    //                   913: 162
    //                   924: 175
    //                   928: 188;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        int k = a(ai, i - 1, stringbuilder);
_L9:
        if (k < ai.length)
        {
            i = k + 1;
            j = ai[k];
        } else
        {
            throw FormatException.getFormatInstance();
        }
        if (true) goto _L8; else goto _L2
_L2:
        k = a(ai, i, stringbuilder);
          goto _L9
_L3:
        k = a(j, ai, i, stringbuilder);
          goto _L9
_L4:
        k = b(ai, i, stringbuilder);
          goto _L9
_L5:
        k = a(j, ai, i, stringbuilder);
          goto _L9
_L6:
        k = a(j, ai, i, stringbuilder);
          goto _L9
_L7:
        k = a(ai, i, pdf417resultmetadata);
          goto _L9
        if (stringbuilder.length() == 0)
        {
            throw FormatException.getFormatInstance();
        } else
        {
            DecoderResult decoderresult = new DecoderResult(null, stringbuilder.toString(), null, s);
            decoderresult.setOther(pdf417resultmetadata);
            return decoderresult;
        }
    }

    private static String a(int ai[], int i)
    {
        BigInteger biginteger = BigInteger.ZERO;
        for (int j = 0; j < i; j++)
        {
            biginteger = biginteger.add(c[-1 + (i - j)].multiply(BigInteger.valueOf(ai[j])));
        }

        String s = biginteger.toString();
        if (s.charAt(0) != '1')
        {
            throw FormatException.getFormatInstance();
        } else
        {
            return s.substring(1);
        }
    }

    private static int b(int ai[], int i, StringBuilder stringbuilder)
    {
        int ai1[] = new int[15];
        boolean flag = false;
        int j = 0;
        do
        {
            if (i >= ai[0] || flag)
            {
                break;
            }
            int k = i + 1;
            int l = ai[i];
            if (k == ai[0])
            {
                flag = true;
            }
            if (l < 900)
            {
                ai1[j] = l;
                j++;
                i = k;
            } else
            if (l == 900 || l == 901 || l == 924 || l == 928 || l == 923 || l == 922)
            {
                i = k - 1;
                flag = true;
            } else
            {
                i = k;
            }
            if (j % 15 == 0 || l == 902 || flag)
            {
                stringbuilder.append(a(ai1, j));
                j = 0;
            }
        } while (true);
        return i;
    }

    static 
    {
        BigInteger abiginteger[] = new BigInteger[16];
        c = abiginteger;
        abiginteger[0] = BigInteger.ONE;
        BigInteger biginteger = BigInteger.valueOf(900L);
        c[1] = biginteger;
        int i = 2;
        do
        {
            BigInteger[] _tmp = c;
            if (i < 16)
            {
                c[i] = c[i - 1].multiply(biginteger);
                i++;
            }
        } while (true);
    }
}
