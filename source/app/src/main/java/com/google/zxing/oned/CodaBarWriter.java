// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import java.util.Arrays;

// Referenced classes of package com.google.zxing.oned:
//            OneDimensionalCodeWriter, CodaBarReader

public final class CodaBarWriter extends OneDimensionalCodeWriter
{

    private static final char a[] = {
        'A', 'B', 'C', 'D'
    };
    private static final char b[] = {
        'T', 'N', '*', 'E'
    };

    public CodaBarWriter()
    {
    }

    public final boolean[] encode(String s)
    {
        boolean aflag[];
        int k;
        int l;
        if (!CodaBarReader.a(a, Character.toUpperCase(s.charAt(0))))
        {
            throw new IllegalArgumentException((new StringBuilder("Codabar should start with one of the following: ")).append(Arrays.toString(a)).toString());
        }
        if (!CodaBarReader.a(b, Character.toUpperCase(s.charAt(-1 + s.length()))))
        {
            throw new IllegalArgumentException((new StringBuilder("Codabar should end with one of the following: ")).append(Arrays.toString(b)).toString());
        }
        char ac[] = {
            '/', ':', '+', '.'
        };
        int i = 20;
        int j = 1;
        while (j < -1 + s.length()) 
        {
            if (Character.isDigit(s.charAt(j)) || s.charAt(j) == '-' || s.charAt(j) == '$')
            {
                i += 9;
            } else
            if (CodaBarReader.a(ac, s.charAt(j)))
            {
                i += 10;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder("Cannot encode : '")).append(s.charAt(j)).append('\'').toString());
            }
            j++;
        }
        aflag = new boolean[i + (-1 + s.length())];
        k = 0;
        l = 0;
_L10:
        if (k >= s.length()) goto _L2; else goto _L1
_L1:
        char c = Character.toUpperCase(s.charAt(k));
        if (k != -1 + s.length()) goto _L4; else goto _L3
_L3:
        c;
        JVM INSTR lookupswitch 4: default 328
    //                   42: 446
    //                   69: 453
    //                   78: 439
    //                   84: 432;
           goto _L5 _L6 _L7 _L8 _L9
_L5:
        int j1;
        int l1;
        break; /* Loop/switch isn't completed */
_L9:
        c = 'A';
          goto _L4
_L8:
        c = 'B';
          goto _L4
_L6:
        c = 'C';
          goto _L4
_L7:
        c = 'D';
_L4:
        int i1 = 0;
        do
        {
            if (i1 >= CodaBarReader.a.length)
            {
                break MISSING_BLOCK_LABEL_511;
            }
            int k1;
            boolean flag;
            if (c == CodaBarReader.a[i1])
            {
                j1 = CodaBarReader.b[i1];
                break MISSING_BLOCK_LABEL_359;
            }
            i1++;
        } while (true);
_L11:
        k1 = 0;
        flag = true;
        l1 = 0;
        while (k1 < 7) 
        {
            aflag[l] = flag;
            int i2 = l + 1;
            if ((1 & j1 >> 6 - k1) == 0 || l1 == 1)
            {
                boolean flag1;
                if (!flag)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                k1++;
                flag = flag1;
                l = i2;
                l1 = 0;
            } else
            {
                l1++;
                l = i2;
            }
        }
        if (k < -1 + s.length())
        {
            aflag[l] = false;
            l++;
        }
        k++;
          goto _L10
_L2:
        return aflag;
        j1 = 0;
          goto _L11
    }

}
