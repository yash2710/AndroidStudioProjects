// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            f, g, SymbolInfo, HighLevelEncoder

class c
    implements f
{

    c()
    {
    }

    private int a(g g1, StringBuilder stringbuilder, StringBuilder stringbuilder1, int i)
    {
        int j = stringbuilder.length();
        stringbuilder.delete(j - i, j);
        g1.c = -1 + g1.c;
        int k = a(g1.getCurrentChar(), stringbuilder1);
        g1.resetSymbolInfo();
        return k;
    }

    static void a(g g1, StringBuilder stringbuilder)
    {
        char c1 = stringbuilder.charAt(0);
        char c2 = stringbuilder.charAt(1);
        int i = 1 + (stringbuilder.charAt(2) + (c1 * 1600 + c2 * 40));
        g1.writeCodewords(new String(new char[] {
            (char)(i / 256), (char)(i % 256)
        }));
        stringbuilder.delete(0, 3);
    }

    int a(char c1, StringBuilder stringbuilder)
    {
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
        }
        if (c1 >= 0 && c1 <= '\037')
        {
            stringbuilder.append('\0');
            stringbuilder.append(c1);
            return 2;
        }
        if (c1 >= '!' && c1 <= '/')
        {
            stringbuilder.append('\001');
            stringbuilder.append((char)(c1 - 33));
            return 2;
        }
        if (c1 >= ':' && c1 <= '@')
        {
            stringbuilder.append('\001');
            stringbuilder.append((char)(15 + (c1 - 58)));
            return 2;
        }
        if (c1 >= '[' && c1 <= '_')
        {
            stringbuilder.append('\001');
            stringbuilder.append((char)(22 + (c1 - 91)));
            return 2;
        }
        if (c1 >= '`' && c1 <= '\177')
        {
            stringbuilder.append('\002');
            stringbuilder.append((char)(c1 - 96));
            return 2;
        }
        if (c1 >= '\200')
        {
            stringbuilder.append("\001\036");
            return 2 + a((char)(c1 - 128), stringbuilder);
        } else
        {
            throw new IllegalArgumentException((new StringBuilder("Illegal character: ")).append(c1).toString());
        }
    }

    void b(g g1, StringBuilder stringbuilder)
    {
        int j;
        int l;
        int i = stringbuilder.length() / 3 << 1;
        j = stringbuilder.length() % 3;
        int k = i + g1.getCodewordCount();
        g1.updateSymbolInfo(k);
        l = g1.e.a - k;
        if (j != 2) goto _L2; else goto _L1
_L1:
        stringbuilder.append('\0');
        for (; stringbuilder.length() >= 3; a(g1, stringbuilder)) { }
        if (g1.hasMoreCharacters())
        {
            g1.writeCodeword('\376');
        }
_L4:
        g1.signalEncoderChange(0);
        return;
_L2:
        if (l == 1 && j == 1)
        {
            for (; stringbuilder.length() >= 3; a(g1, stringbuilder)) { }
            if (g1.hasMoreCharacters())
            {
                g1.writeCodeword('\376');
            }
            g1.c = -1 + g1.c;
            continue; /* Loop/switch isn't completed */
        }
        if (j != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        for (; stringbuilder.length() >= 3; a(g1, stringbuilder)) { }
        if (l > 0 || g1.hasMoreCharacters())
        {
            g1.writeCodeword('\376');
        }
        if (true) goto _L4; else goto _L3
_L3:
        throw new IllegalStateException("Unexpected case. Please report!");
    }

    public void encode(g g1)
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
            int i = a(c1, stringbuilder);
            int j = (stringbuilder.length() / 3 << 1) + g1.getCodewordCount();
            g1.updateSymbolInfo(j);
            int k = g1.e.a - j;
            if (!g1.hasMoreCharacters())
            {
                StringBuilder stringbuilder1 = new StringBuilder();
                if (stringbuilder.length() % 3 == 2 && (k < 2 || k > 2))
                {
                    i = a(g1, stringbuilder, stringbuilder1, i);
                }
                for (; stringbuilder.length() % 3 == 1 && (i <= 3 && k != 1 || i > 3); i = a(g1, stringbuilder, stringbuilder1, i)) { }
                break;
            }
            if (stringbuilder.length() % 3 != 0)
            {
                continue;
            }
            int l = HighLevelEncoder.a(g1.a, g1.c, getEncodingMode());
            if (l == getEncodingMode())
            {
                continue;
            }
            g1.signalEncoderChange(l);
            break;
        } while (true);
        b(g1, stringbuilder);
    }

    public int getEncodingMode()
    {
        return 1;
    }
}
