// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.maxicode.decoder;

import com.google.zxing.common.DecoderResult;
import java.text.DecimalFormat;
import java.text.NumberFormat;

final class b
{

    private static final NumberFormat a = new DecimalFormat("000000000");
    private static final NumberFormat b = new DecimalFormat("000");
    private static final String c[] = {
        "\nABCDEFGHIJKLMNOPQRSTUVWXYZ\uFFFA\034\035\036\uFFFB \uFFFC\"#$%&'()*+,-./0123456789:\uFFF1\uFFF2\uFFF3\uFFF4\uFFF8", "`abcdefghijklmnopqrstuvwxyz\uFFFA\034\035\036\uFFFB{\uFFFC}~\177;<=>?[\\]^_ ,./:@!|\uFFFC\uFFF5\uFFF6\uFFFC\uFFF0\uFFF2\uFFF3\uFFF4\uFFF7", "\300\301\302\303\304\305\306\307\310\311\312\313\314\315\316\317\320\321\322\323\324\325\326\327\330\331\332\uFFFA\034\035\036\333\334\335\336\337\252\254\261\262\263\265\271\272\274\275\276\200\201\202\203\204\205\206\207\210\211\uFFF7 \uFFF9\uFFF3\uFFF4\uFFF8", "\340\341\342\343\344\345\346\347\350\351\352\353\354\355\356\357\360\361\362\363\364\365\366\367\370\371\372\uFFFA\034\035\036\uFFFB\373\374\375\376\377\241\250\253\257\260\264\267\270\273\277\212\213\214\215\216\217\220\221\222\223\224\uFFF7 \uFFF2\uFFF9\uFFF4\uFFF8", "\000\001\002\003\004\005\006\007\b\t\n\013\f\r\016\017\020\021\022\023\024\025\026\027\030\031\032\uFFFA\uFFFC\uFFFC\033\uFFFB\034\035\036\037\237\240\242\243\244\245\246\247\251\255\256\266\225\226\227\230\231\232\233\234\235\236\uFFF7 \uFFF2\uFFF3\uFFF9\uFFF8", "\000\001\002\003\004\005\006\007\b\t\n\013\f\r\016\017\020\021\022\023\024\025\026\027\030\031\032\033\034\035\036\037 !\"#$%&'()*+,-./0123456789:;<=>?"
    };

    private static int a(byte abyte0[], byte abyte1[])
    {
        int i = 0;
        int j = 0;
        while (i < abyte1.length) 
        {
            int k = -1 + abyte1[i];
            int l;
            if ((abyte0[k / 6] & 1 << 5 - k % 6) == 0)
            {
                l = 0;
            } else
            {
                l = 1;
            }
            j += l << -1 + (abyte1.length - i);
            i++;
        }
        return j;
    }

    static DecoderResult a(byte abyte0[], int i)
    {
        StringBuilder stringbuilder = new StringBuilder(144);
        i;
        JVM INSTR tableswitch 2 5: default 44
    //                   2 62
    //                   3 62
    //                   4 892
    //                   5 907;
           goto _L1 _L2 _L2 _L3 _L4
_L1:
        return new DecoderResult(abyte0, stringbuilder.toString(), null, String.valueOf(i));
_L2:
        String s;
        String s1;
        String s2;
        if (i == 2)
        {
            int j = a(abyte0, new byte[] {
                33, 34, 35, 36, 25, 26, 27, 28, 29, 30, 
                19, 20, 21, 22, 23, 24, 13, 14, 15, 16, 
                17, 18, 7, 8, 9, 10, 11, 12, 1, 2
            });
            s = (new DecimalFormat("0000000000".substring(0, a(abyte0, new byte[] {
                39, 40, 41, 42, 31, 32
            })))).format(j);
        } else
        {
            char ac[] = new char[6];
            ac[0] = c[0].charAt(a(abyte0, new byte[] {
                39, 40, 41, 42, 31, 32
            }));
            ac[1] = c[0].charAt(a(abyte0, new byte[] {
                33, 34, 35, 36, 25, 26
            }));
            ac[2] = c[0].charAt(a(abyte0, new byte[] {
                27, 28, 29, 30, 19, 20
            }));
            ac[3] = c[0].charAt(a(abyte0, new byte[] {
                21, 22, 23, 24, 13, 14
            }));
            ac[4] = c[0].charAt(a(abyte0, new byte[] {
                15, 16, 17, 18, 7, 8
            }));
            ac[5] = c[0].charAt(a(abyte0, new byte[] {
                9, 10, 11, 12, 1, 2
            }));
            s = String.valueOf(ac);
        }
        s1 = b.format(a(abyte0, new byte[] {
            53, 54, 43, 44, 45, 46, 47, 48, 37, 38
        }));
        s2 = b.format(a(abyte0, new byte[] {
            55, 56, 57, 58, 59, 60, 49, 50, 51, 52
        }));
        stringbuilder.append(a(abyte0, 10, 84));
        if (stringbuilder.toString().startsWith("[)>\03601\035"))
        {
            stringbuilder.insert(9, (new StringBuilder()).append(s).append('\035').append(s1).append('\035').append(s2).append('\035').toString());
        } else
        {
            stringbuilder.insert(0, (new StringBuilder()).append(s).append('\035').append(s1).append('\035').append(s2).append('\035').toString());
        }
        continue; /* Loop/switch isn't completed */
_L3:
        stringbuilder.append(a(abyte0, 1, 93));
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append(a(abyte0, 1, 77));
        if (true) goto _L1; else goto _L5
_L5:
    }

    private static String a(byte abyte0[], int i, int j)
    {
        StringBuilder stringbuilder;
        int k;
        int l;
        int i1;
        int j1;
        stringbuilder = new StringBuilder();
        k = i;
        l = 0;
        i1 = 0;
        j1 = -1;
_L10:
        char c1;
        if (k >= i + j)
        {
            break MISSING_BLOCK_LABEL_383;
        }
        c1 = c[i1].charAt(abyte0[k]);
        c1;
        JVM INSTR tableswitch 65520 65531: default 108
    //                   65520 193
    //                   65521 193
    //                   65522 193
    //                   65523 193
    //                   65524 193
    //                   65525 222
    //                   65526 239
    //                   65527 167
    //                   65528 180
    //                   65529 365
    //                   65530 108
    //                   65531 256;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L3 _L4 _L5 _L6 _L7 _L1 _L8
_L7:
        break MISSING_BLOCK_LABEL_365;
_L5:
        break; /* Loop/switch isn't completed */
_L1:
        int l1;
        int i2;
        stringbuilder.append(c1);
        int i6 = k;
        i2 = i1;
        l1 = i6;
_L11:
        int j2 = j1 - 1;
        if (j1 == 0)
        {
            i2 = l;
        }
        int k2 = l1 + 1;
        j1 = j2;
        int l2 = i2;
        k = k2;
        i1 = l2;
        if (true) goto _L10; else goto _L9
_L9:
        l1 = k;
        j1 = -1;
        i2 = 0;
          goto _L11
_L6:
        l1 = k;
        j1 = -1;
        i2 = 1;
          goto _L11
_L2:
        int k5 = c1 - 65520;
        j1 = 1;
        int l5 = i1;
        l1 = k;
        i2 = k5;
        l = l5;
          goto _L11
_L3:
        j1 = 2;
        l = i1;
        l1 = k;
        i2 = 0;
          goto _L11
_L4:
        j1 = 3;
        l = i1;
        l1 = k;
        i2 = 0;
          goto _L11
_L8:
        int i3 = k + 1;
        int j3 = abyte0[i3] << 24;
        int k3 = i3 + 1;
        int l3 = j3 + (abyte0[k3] << 18);
        int i4 = k3 + 1;
        int j4 = l3 + (abyte0[i4] << 12);
        int k4 = i4 + 1;
        int l4 = j4 + (abyte0[k4] << 6);
        int i5 = k4 + 1;
        int j5 = l4 + abyte0[i5];
        stringbuilder.append(a.format(j5));
        i2 = i1;
        l1 = i5;
          goto _L11
        j1 = -1;
        int k1 = i1;
        l1 = k;
        i2 = k1;
          goto _L11
        for (; stringbuilder.length() > 0 && stringbuilder.charAt(-1 + stringbuilder.length()) == '\uFFFC'; stringbuilder.setLength(-1 + stringbuilder.length())) { }
        return stringbuilder.toString();
    }

}
