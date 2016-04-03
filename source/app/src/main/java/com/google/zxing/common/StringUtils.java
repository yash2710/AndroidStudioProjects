// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.util.Map;

public final class StringUtils
{

    public static final String GB2312 = "GB2312";
    public static final String SHIFT_JIS = "SJIS";
    private static final String a = System.getProperty("file.encoding");
    private static final boolean b;

    private StringUtils()
    {
    }

    public static String guessEncoding(byte abyte0[], Map map)
    {
        int i;
        boolean flag;
        boolean flag1;
        boolean flag2;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        boolean flag3;
        int i3;
        int j3;
        int k3;
        boolean flag5;
        int l3;
        boolean flag6;
        if (map != null)
        {
            String s = (String)map.get(DecodeHintType.CHARACTER_SET);
            if (s != null)
            {
                return s;
            }
        }
        i = abyte0.length;
        flag = true;
        flag1 = true;
        flag2 = true;
        j = 0;
        k = 0;
        l = 0;
        i1 = 0;
        j1 = 0;
        k1 = 0;
        l1 = 0;
        i2 = 0;
        j2 = 0;
        k2 = 0;
        int l2;
        int j7;
        if (abyte0.length > 3 && abyte0[0] == -17 && abyte0[1] == -69 && abyte0[2] == -65)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        l2 = 0;
        i3 = 0;
        if (l2 >= i || !flag && !flag1 && !flag2) goto _L2; else goto _L1
_L1:
        j3 = 0xff & abyte0[l2];
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_957;
        }
        if (i3 <= 0) goto _L4; else goto _L3
_L3:
        if ((j3 & 0x80) == 0) goto _L6; else goto _L5
_L5:
        k3 = i3 - 1;
        flag5 = flag2;
_L14:
        if (!flag) goto _L8; else goto _L7
_L7:
        if (j3 <= 127 || j3 >= 160) goto _L10; else goto _L9
_L9:
        l3 = k2;
        flag6 = false;
_L12:
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        int j5;
        boolean flag7;
        if (flag1)
        {
            if (i1 > 0)
            {
                if (j3 < 64 || j3 == 127 || j3 > 252)
                {
                    j7 = j2;
                    j4 = i2;
                    k4 = l1;
                    l4 = k1;
                    i5 = j1;
                    j5 = i1;
                    flag7 = false;
                    i4 = j7;
                } else
                {
                    int k7 = i1 - 1;
                    flag7 = flag1;
                    int l7 = i2;
                    k4 = l1;
                    l4 = k1;
                    i5 = j1;
                    j5 = k7;
                    i4 = j2;
                    j4 = l7;
                }
            } else
            if (j3 == 128 || j3 == 160 || j3 > 239)
            {
                int k5 = j2;
                j4 = i2;
                k4 = l1;
                l4 = k1;
                i5 = j1;
                j5 = i1;
                i4 = k5;
                flag7 = false;
            } else
            if (j3 > 160 && j3 < 224)
            {
                int k6 = j1 + 1;
                int l6 = k1 + 1;
                boolean flag4;
                int l5;
                int i6;
                int j6;
                if (l6 > i2)
                {
                    i5 = k6;
                    l4 = l6;
                    j5 = i1;
                    flag7 = flag1;
                    int i7 = j2;
                    j4 = l6;
                    i4 = i7;
                    k4 = 0;
                } else
                {
                    i5 = k6;
                    j5 = i1;
                    flag7 = flag1;
                    i4 = j2;
                    j4 = i2;
                    l4 = l6;
                    k4 = 0;
                }
            } else
            if (j3 > 127)
            {
                i6 = i1 + 1;
                i4 = l1 + 1;
                if (i4 > j2)
                {
                    j4 = i2;
                    k4 = i4;
                    i5 = j1;
                    j5 = i6;
                    flag7 = flag1;
                    l4 = 0;
                } else
                {
                    i5 = j1;
                    j5 = i6;
                    flag7 = flag1;
                    j6 = i2;
                    k4 = i4;
                    i4 = j2;
                    j4 = j6;
                    l4 = 0;
                }
            } else
            {
                i5 = j1;
                j5 = i1;
                flag7 = flag1;
                l5 = j2;
                j4 = i2;
                i4 = l5;
                k4 = 0;
                l4 = 0;
            }
        } else
        {
            i4 = j2;
            j4 = i2;
            k4 = l1;
            l4 = k1;
            i5 = j1;
            j5 = i1;
            flag7 = flag1;
        }
        l2++;
        flag = flag6;
        flag1 = flag7;
        k2 = l3;
        i1 = j5;
        flag2 = flag5;
        j1 = i5;
        k1 = l4;
        l1 = k4;
        i2 = j4;
        j2 = i4;
        i3 = k3;
        break MISSING_BLOCK_LABEL_106;
_L4:
        if ((j3 & 0x80) == 0)
        {
            break MISSING_BLOCK_LABEL_957;
        }
        if ((j3 & 0x40) != 0)
        {
            k3 = i3 + 1;
            if ((j3 & 0x20) == 0)
            {
                j++;
                flag5 = flag2;
            } else
            {
label0:
                {
                    k3++;
                    if ((j3 & 0x10) != 0)
                    {
                        break label0;
                    }
                    k++;
                    flag5 = flag2;
                }
            }
            continue; /* Loop/switch isn't completed */
        }
          goto _L6
        for (i3 = k3 + 1; (j3 & 8) == 0; i3 = k3 + 1)
        {
            l++;
            k3 = i3;
            flag5 = flag2;
            continue; /* Loop/switch isn't completed */
        }

_L6:
        k3 = i3;
        flag5 = false;
        continue; /* Loop/switch isn't completed */
_L10:
        if (j3 > 159 && (j3 < 192 || j3 == 215 || j3 == 247))
        {
            l3 = k2 + 1;
            flag6 = flag;
            continue; /* Loop/switch isn't completed */
        }
          goto _L8
_L2:
        if (flag2 && i3 > 0)
        {
            flag4 = false;
        } else
        {
            flag4 = flag2;
        }
        if (flag1 && i1 > 0)
        {
            flag1 = false;
        }
        if (flag4 && (flag3 || l + (j + k) > 0))
        {
            return "UTF8";
        }
        if (flag1 && (b || i2 >= 3 || j2 >= 3))
        {
            return "SJIS";
        }
        if (flag && flag1)
        {
            if (i2 == 2 && j1 == 2 || k2 * 10 >= i)
            {
                return "SJIS";
            } else
            {
                return "ISO8859_1";
            }
        }
        if (flag)
        {
            return "ISO8859_1";
        }
        if (flag1)
        {
            return "SJIS";
        }
        if (flag4)
        {
            return "UTF8";
        } else
        {
            return a;
        }
_L8:
        l3 = k2;
        flag6 = flag;
        if (true) goto _L12; else goto _L11
_L11:
        k3 = i3;
        flag5 = flag2;
        if (true) goto _L14; else goto _L13
_L13:
    }

    static 
    {
        boolean flag;
        if ("SJIS".equalsIgnoreCase(a) || "EUC_JP".equalsIgnoreCase(a))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        b = flag;
    }
}
