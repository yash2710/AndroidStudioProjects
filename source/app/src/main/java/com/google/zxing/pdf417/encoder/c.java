// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import java.math.BigInteger;
import java.util.Arrays;

// Referenced classes of package com.google.zxing.pdf417.encoder:
//            Compaction

final class c
{

    private static final byte a[] = {
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
        38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 
        43, 37, 42, 61, 94, 0, 32, 0, 0, 0
    };
    private static final byte b[] = {
        59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 
        33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 
        34, 124, 42, 40, 41, 63, 123, 125, 39, 0
    };
    private static final byte c[];
    private static final byte d[];

    private static int a(CharSequence charsequence, int i)
    {
        int j = charsequence.length();
        int k = 0;
        if (i < j)
        {
            char c1 = charsequence.charAt(i);
            do
            {
                if (!a(c1) || i >= j)
                {
                    break;
                }
                k++;
                if (++i < j)
                {
                    c1 = charsequence.charAt(i);
                }
            } while (true);
        }
        return k;
    }

    private static int a(CharSequence charsequence, int i, int j, StringBuilder stringbuilder, int k)
    {
        StringBuilder stringbuilder1;
        int l;
        stringbuilder1 = new StringBuilder(j);
        l = 0;
_L10:
        char c1 = charsequence.charAt(i + l);
        k;
        JVM INSTR tableswitch 0 2: default 52
    //                   0 150
    //                   1 259
    //                   2 377;
           goto _L1 _L2 _L3 _L4
_L1:
        if (!e(c1)) goto _L6; else goto _L5
_L5:
        stringbuilder1.append((char)d[c1]);
_L8:
        int i1;
        int j1;
        if (++l >= j)
        {
            i1 = stringbuilder1.length();
            j1 = 0;
            int k1 = 0;
            while (k1 < i1) 
            {
                boolean flag;
                char c2;
                if (k1 % 2 != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    c2 = (char)(j1 * 30 + stringbuilder1.charAt(k1));
                    stringbuilder.append(c2);
                } else
                {
                    c2 = stringbuilder1.charAt(k1);
                }
                k1++;
                j1 = c2;
            }
            break; /* Loop/switch isn't completed */
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (b(c1))
        {
            if (c1 == ' ')
            {
                stringbuilder1.append('\032');
            } else
            {
                stringbuilder1.append((char)(c1 - 65));
            }
            continue; /* Loop/switch isn't completed */
        }
        if (c(c1))
        {
            stringbuilder1.append('\033');
            k = 1;
        } else
        if (d(c1))
        {
            k = 2;
            stringbuilder1.append('\034');
        } else
        {
            stringbuilder1.append('\035');
            stringbuilder1.append((char)d[c1]);
            continue; /* Loop/switch isn't completed */
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (c(c1))
        {
            if (c1 == ' ')
            {
                stringbuilder1.append('\032');
            } else
            {
                stringbuilder1.append((char)(c1 - 97));
            }
            continue; /* Loop/switch isn't completed */
        }
        if (b(c1))
        {
            stringbuilder1.append('\033');
            stringbuilder1.append((char)(c1 - 65));
            continue; /* Loop/switch isn't completed */
        }
        if (d(c1))
        {
            k = 2;
            stringbuilder1.append('\034');
        } else
        {
            stringbuilder1.append('\035');
            stringbuilder1.append((char)d[c1]);
            continue; /* Loop/switch isn't completed */
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (d(c1))
        {
            stringbuilder1.append((char)c[c1]);
            continue; /* Loop/switch isn't completed */
        }
        if (b(c1))
        {
            stringbuilder1.append('\034');
            k = 0;
            continue; /* Loop/switch isn't completed */
        }
        if (c(c1))
        {
            stringbuilder1.append('\033');
            k = 1;
            continue; /* Loop/switch isn't completed */
        }
        if (1 + (i + l) < j && e(charsequence.charAt(1 + (i + l))))
        {
            k = 3;
            stringbuilder1.append('\031');
            continue; /* Loop/switch isn't completed */
        }
        stringbuilder1.append('\035');
        stringbuilder1.append((char)d[c1]);
        if (true) goto _L8; else goto _L7
_L7:
        break; /* Loop/switch isn't completed */
_L6:
        stringbuilder1.append('\035');
        k = 0;
        if (true) goto _L10; else goto _L9
_L9:
        if (i1 % 2 != 0)
        {
            stringbuilder.append((char)(29 + j1 * 30));
        }
        return k;
    }

    private static int a(CharSequence charsequence, byte abyte0[], int i)
    {
        int j = charsequence.length();
        int k;
        for (k = i; k < j; k++)
        {
            char c1 = charsequence.charAt(k);
            int l = 0;
            do
            {
                if (l >= 13 || !a(c1))
                {
                    break;
                }
                l++;
                int k1 = k + l;
                if (k1 >= j)
                {
                    break;
                }
                c1 = charsequence.charAt(k1);
            } while (true);
            if (l >= 13)
            {
                return k - i;
            }
            char c2 = c1;
            int i1 = 0;
            do
            {
                if (i1 >= 5 || !f(c2))
                {
                    break;
                }
                i1++;
                int j1 = k + i1;
                if (j1 >= j)
                {
                    break;
                }
                c2 = charsequence.charAt(j1);
            } while (true);
            if (i1 >= 5)
            {
                return k - i;
            }
            char c3 = charsequence.charAt(k);
            if (abyte0[k] == 63 && c3 != '?')
            {
                throw new WriterException((new StringBuilder("Non-encodable character detected: ")).append(c3).append(" (Unicode: ").append(c3).append(')').toString());
            }
        }

        return k - i;
    }

    static String a(String s, Compaction compaction)
    {
        byte abyte0[];
        StringBuilder stringbuilder;
        int i;
        abyte0 = null;
        stringbuilder = new StringBuilder(s.length());
        i = s.length();
        if (compaction != Compaction.TEXT) goto _L2; else goto _L1
_L1:
        a(((CharSequence) (s)), 0, i, stringbuilder, 0);
_L4:
        return stringbuilder.toString();
_L2:
        if (compaction == Compaction.BYTE)
        {
            byte abyte1[] = s.getBytes();
            a(abyte1, 0, abyte1.length, 1, stringbuilder);
            continue; /* Loop/switch isn't completed */
        }
        if (compaction != Compaction.NUMERIC)
        {
            break; /* Loop/switch isn't completed */
        }
        stringbuilder.append('\u0386');
        a(s, 0, i, stringbuilder);
        if (true) goto _L4; else goto _L3
_L3:
        int j = 0;
        int k = 0;
        byte byte0 = 0;
        while (k < i) 
        {
            int l = a(((CharSequence) (s)), k);
            if (l >= 13)
            {
                stringbuilder.append('\u0386');
                byte0 = 2;
                a(s, k, l, stringbuilder);
                k += l;
                j = 0;
            } else
            {
                int i1 = b(s, k);
                if (i1 >= 5 || l == i)
                {
                    if (byte0 != 0)
                    {
                        stringbuilder.append('\u0384');
                        j = 0;
                        byte0 = 0;
                    }
                    j = a(((CharSequence) (s)), k, i1, stringbuilder, j);
                    k += i1;
                } else
                {
                    if (abyte0 == null)
                    {
                        abyte0 = s.getBytes();
                    }
                    int j1 = a(((CharSequence) (s)), abyte0, k);
                    if (j1 == 0)
                    {
                        j1 = 1;
                    }
                    if (j1 == 1 && byte0 == 0)
                    {
                        a(abyte0, k, 1, 0, stringbuilder);
                    } else
                    {
                        a(abyte0, k, j1, byte0, stringbuilder);
                        byte0 = 1;
                        j = 0;
                    }
                    k += j1;
                }
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    private static void a(String s, int i, int j, StringBuilder stringbuilder)
    {
        StringBuilder stringbuilder1 = new StringBuilder(1 + j / 3);
        BigInteger biginteger = BigInteger.valueOf(900L);
        BigInteger biginteger1 = BigInteger.valueOf(0L);
        int l;
        for (int k = 0; k < j - 1; k += l)
        {
            stringbuilder1.setLength(0);
            l = Math.min(44, j - k);
            BigInteger biginteger2 = new BigInteger((new StringBuilder("1")).append(s.substring(i + k, l + (i + k))).toString());
            do
            {
                stringbuilder1.append((char)biginteger2.mod(biginteger).intValue());
                biginteger2 = biginteger2.divide(biginteger);
            } while (!biginteger2.equals(biginteger1));
            for (int i1 = -1 + stringbuilder1.length(); i1 >= 0; i1--)
            {
                stringbuilder.append(stringbuilder1.charAt(i1));
            }

        }

    }

    private static void a(byte abyte0[], int i, int j, int k, StringBuilder stringbuilder)
    {
        if (j == 1 && k == 0)
        {
            stringbuilder.append('\u0391');
        }
        int l;
        if (j >= 6)
        {
            stringbuilder.append('\u039C');
            char ac[] = new char[5];
            for (l = i; (i + j) - l >= 6; l += 6)
            {
                long l1 = 0L;
                for (int i1 = 0; i1 < 6; i1++)
                {
                    l1 = (l1 << 8) + (long)(0xff & abyte0[l + i1]);
                }

                for (int j1 = 0; j1 < 5; j1++)
                {
                    ac[j1] = (char)(int)(l1 % 900L);
                    l1 /= 900L;
                }

                for (int k1 = 4; k1 >= 0; k1--)
                {
                    stringbuilder.append(ac[k1]);
                }

            }

        } else
        {
            l = i;
        }
        if (l < i + j)
        {
            stringbuilder.append('\u0385');
        }
        for (; l < i + j; l++)
        {
            stringbuilder.append((char)(0xff & abyte0[l]));
        }

    }

    private static boolean a(char c1)
    {
        return c1 >= '0' && c1 <= '9';
    }

    private static int b(CharSequence charsequence, int i)
    {
        int j = charsequence.length();
        int k = i;
        do
        {
            if (k >= j)
            {
                break;
            }
            char c1 = charsequence.charAt(k);
            for (int l = 0; l < 13 && a(c1) && k < j;)
            {
                l++;
                int i1 = k + 1;
                if (i1 < j)
                {
                    c1 = charsequence.charAt(i1);
                    k = i1;
                } else
                {
                    k = i1;
                }
            }

            if (l >= 13)
            {
                return k - i - l;
            }
            if (l > 0)
            {
                continue;
            }
            if (!f(charsequence.charAt(k)))
            {
                break;
            }
            k++;
        } while (true);
        return k - i;
    }

    private static boolean b(char c1)
    {
        return c1 == ' ' || c1 >= 'A' && c1 <= 'Z';
    }

    private static boolean c(char c1)
    {
        return c1 == ' ' || c1 >= 'a' && c1 <= 'z';
    }

    private static boolean d(char c1)
    {
        return c[c1] != -1;
    }

    private static boolean e(char c1)
    {
        return d[c1] != -1;
    }

    private static boolean f(char c1)
    {
        return c1 == '\t' || c1 == '\n' || c1 == '\r' || c1 >= ' ' && c1 <= '~';
    }

    static 
    {
        byte byte0 = 0;
        c = new byte[128];
        d = new byte[128];
        Arrays.fill(c, (byte)-1);
        byte byte1 = 0;
        do
        {
            byte[] _tmp = a;
            if (byte1 >= 30)
            {
                break;
            }
            byte byte3 = a[byte1];
            if (byte3 > 0)
            {
                c[byte3] = byte1;
            }
            byte1++;
        } while (true);
        Arrays.fill(d, (byte)-1);
        do
        {
            byte[] _tmp1 = b;
            if (byte0 < 30)
            {
                byte byte2 = b[byte0];
                if (byte2 > 0)
                {
                    d[byte2] = byte0;
                }
                byte0++;
            }
        } while (true);
    }
}
