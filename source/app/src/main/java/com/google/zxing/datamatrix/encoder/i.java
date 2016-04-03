// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            c, HighLevelEncoder, g, SymbolInfo

final class i extends c
{

    i()
    {
    }

    final int a(char c1, StringBuilder stringbuilder)
    {
        if (c1 == '\r')
        {
            stringbuilder.append('\0');
            return 1;
        }
        if (c1 == '*')
        {
            stringbuilder.append('\001');
            return 1;
        }
        if (c1 == '>')
        {
            stringbuilder.append('\002');
            return 1;
        }
        if (c1 == ' ')
        {
            stringbuilder.append('\003');
            return 1;
        }
        if (c1 >= '0' && c1 <= '9')
        {
            stringbuilder.append((char)(4 + (c1 - 48)));
            return 1;
        }
        if (c1 >= 'A' && c1 <= 'Z')
        {
            stringbuilder.append((char)(14 + (c1 - 65)));
            return 1;
        } else
        {
            HighLevelEncoder.c(c1);
            return 1;
        }
    }

    final void b(g g1, StringBuilder stringbuilder)
    {
        int j;
        int k;
        g1.updateSymbolInfo();
        j = g1.e.a - g1.getCodewordCount();
        k = stringbuilder.length();
        if (k != 2) goto _L2; else goto _L1
_L1:
        g1.writeCodeword('\376');
        g1.c = -2 + g1.c;
_L6:
        g1.signalEncoderChange(0);
_L4:
        return;
_L2:
        if (k != 1) goto _L4; else goto _L3
_L3:
        g1.c = -1 + g1.c;
        if (j > 1)
        {
            g1.writeCodeword('\376');
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void encode(g g1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        do
        {
            if (!g1.hasMoreCharacters())
            {
                break;
            }
            char c1 = g1.getCurrentChar();
            g1.c = 1 + g1.c;
            a(c1, stringbuilder);
            if (stringbuilder.length() % 3 != 0)
            {
                continue;
            }
            a(g1, stringbuilder);
            int j = HighLevelEncoder.a(g1.a, g1.c, getEncodingMode());
            if (j == getEncodingMode())
            {
                continue;
            }
            g1.signalEncoderChange(j);
            break;
        } while (true);
        b(g1, stringbuilder);
    }

    public final int getEncodingMode()
    {
        return 3;
    }
}
