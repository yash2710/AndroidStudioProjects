// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDimensionalCodeWriter, Code128Reader

public final class Code128Writer extends OneDimensionalCodeWriter
{

    public Code128Writer()
    {
    }

    private static boolean a(CharSequence charsequence, int i, int j)
    {
        int k = i + j;
        int l;
        for (l = charsequence.length(); i < k && i < l; i++)
        {
            char c = charsequence.charAt(i);
            if (c >= '0' && c <= '9')
            {
                continue;
            }
            if (c != '\361')
            {
                return false;
            }
            k++;
        }

        return k <= l;
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
    {
        if (barcodeformat != BarcodeFormat.CODE_128)
        {
            throw new IllegalArgumentException((new StringBuilder("Can only encode CODE_128, but got ")).append(barcodeformat).toString());
        } else
        {
            return super.encode(s, barcodeformat, i, j, map);
        }
    }

    public final boolean[] encode(String s)
    {
        int i;
        int j;
        int k;
        i = 0;
        j = s.length();
        if (j <= 0 || j > 80)
        {
            throw new IllegalArgumentException((new StringBuilder("Contents length should be between 1 and 80 characters, but got ")).append(j).toString());
        }
        k = 0;
_L11:
        ArrayList arraylist;
        int l;
        int i1;
        int j1;
        int k1;
        if (k < j)
        {
            char c = s.charAt(k);
            if (c < ' ' || c > '~')
            {
                switch (c)
                {
                default:
                    throw new IllegalArgumentException((new StringBuilder("Bad character in input: ")).append(c).toString());

                case 241: 
                case 242: 
                case 243: 
                case 244: 
                    break;
                }
            }
            k++;
            continue; /* Loop/switch isn't completed */
        }
        arraylist = new ArrayList();
        l = 0;
        i1 = 0;
        j1 = 1;
        k1 = 0;
_L7:
        if (l >= j) goto _L2; else goto _L1
_L1:
        byte byte1;
        int l2;
        int j3;
        int k3;
        byte byte0;
        int l3;
        if (i1 == 99)
        {
            byte0 = 2;
        } else
        {
            byte0 = 4;
        }
        if (a(s, l, byte0))
        {
            byte1 = 99;
        } else
        {
            byte1 = 100;
        }
        if (byte1 != i1) goto _L4; else goto _L3
_L3:
        if (i1 != 100) goto _L6; else goto _L5
_L5:
        l2 = -32 + s.charAt(l);
        k3 = l + 1;
        j3 = i1;
_L9:
        arraylist.add(Code128Reader.a[l2]);
        l3 = k1 + l2 * j1;
        int l1;
        Iterator iterator;
        int i2;
        boolean aflag[];
        Iterator iterator1;
        int ai[];
        int j2;
        int k2;
        int i3;
        int i4;
        if (k3 != 0)
        {
            i4 = j1 + 1;
        } else
        {
            i4 = j1;
        }
        j1 = i4;
        k1 = l3;
        i1 = j3;
        l = k3;
          goto _L7
_L6:
        switch (s.charAt(l))
        {
        default:
            l2 = Integer.parseInt(s.substring(l, l + 2));
            k3 = l + 2;
            j3 = i1;
            break;

        case 241: 
            l2 = 102;
            k3 = l + 1;
            j3 = i1;
            break;

        case 242: 
            l2 = 97;
            k3 = l + 1;
            j3 = i1;
            break;

        case 243: 
            l2 = 96;
            k3 = l + 1;
            j3 = i1;
            break;

        case 244: 
            k3 = l + 1;
            j3 = i1;
            l2 = 100;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (i1 == 0)
        {
            if (byte1 == 100)
            {
                l2 = 104;
            } else
            {
                l2 = 105;
            }
        } else
        {
            l2 = byte1;
        }
        i3 = l;
        j3 = byte1;
        k3 = i3;
        continue; /* Loop/switch isn't completed */
_L2:
        l1 = k1 % 103;
        arraylist.add(Code128Reader.a[l1]);
        arraylist.add(Code128Reader.a[106]);
        iterator = arraylist.iterator();
        i2 = 0;
        while (iterator.hasNext()) 
        {
            ai = (int[])iterator.next();
            j2 = ai.length;
            k2 = 0;
            while (k2 < j2) 
            {
                i2 += ai[k2];
                k2++;
            }
        }
        aflag = new boolean[i2];
        for (iterator1 = arraylist.iterator(); iterator1.hasNext();)
        {
            i += appendPattern(aflag, i, (int[])iterator1.next(), true);
        }

        return aflag;
        if (true) goto _L9; else goto _L8
_L8:
        if (true) goto _L11; else goto _L10
_L10:
    }
}
