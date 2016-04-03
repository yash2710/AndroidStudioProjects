// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            f, g, HighLevelEncoder, SymbolInfo

final class b
    implements f
{

    b()
    {
    }

    public final void encode(g g1)
    {
        int i = 0;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('\0');
        do
        {
            if (!g1.hasMoreCharacters())
            {
                break;
            }
            stringbuilder.append(g1.getCurrentChar());
            g1.c = 1 + g1.c;
            int j1 = HighLevelEncoder.a(g1.a, g1.c, getEncodingMode());
            if (j1 == getEncodingMode())
            {
                continue;
            }
            g1.signalEncoderChange(j1);
            break;
        } while (true);
        int j = -1 + stringbuilder.length();
        int k = 1 + (j + g1.getCodewordCount());
        g1.updateSymbolInfo(k);
        boolean flag;
        int l;
        if (g1.e.a - k > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (g1.hasMoreCharacters() || flag)
        {
            if (j <= 249)
            {
                stringbuilder.setCharAt(0, (char)j);
            } else
            if (j > 249 && j <= 1555)
            {
                stringbuilder.setCharAt(0, (char)(249 + j / 250));
                stringbuilder.insert(1, (char)(j % 250));
            } else
            {
                throw new IllegalStateException((new StringBuilder("Message length not in valid ranges: ")).append(j).toString());
            }
        }
        l = stringbuilder.length();
        while (i < l) 
        {
            int i1 = stringbuilder.charAt(i) + (1 + (149 * (1 + g1.getCodewordCount())) % 255);
            char c;
            if (i1 <= 255)
            {
                c = (char)i1;
            } else
            {
                c = (char)(i1 - 256);
            }
            g1.writeCodeword(c);
            i++;
        }
    }

    public final int getEncodingMode()
    {
        return 5;
    }
}
