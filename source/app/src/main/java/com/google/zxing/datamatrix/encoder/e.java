// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            f, g, HighLevelEncoder, SymbolInfo

final class e
    implements f
{

    e()
    {
    }

    private static String a(CharSequence charsequence, int i)
    {
        int j = charsequence.length();
        if (j == 0)
        {
            throw new IllegalStateException("StringBuilder must not be empty");
        }
        char c = charsequence.charAt(0);
        char c1;
        char c2;
        char c3;
        int k;
        char c4;
        char c5;
        char c6;
        StringBuilder stringbuilder;
        if (j >= 2)
        {
            c1 = charsequence.charAt(1);
        } else
        {
            c1 = '\0';
        }
        if (j >= 3)
        {
            c2 = charsequence.charAt(2);
        } else
        {
            c2 = '\0';
        }
        c3 = '\0';
        if (j >= 4)
        {
            c3 = charsequence.charAt(3);
        }
        k = c3 + ((c << 18) + (c1 << 12) + (c2 << 6));
        c4 = (char)(0xff & k >> 16);
        c5 = (char)(0xff & k >> 8);
        c6 = (char)(k & 0xff);
        stringbuilder = new StringBuilder(3);
        stringbuilder.append(c4);
        if (j >= 2)
        {
            stringbuilder.append(c5);
        }
        if (j >= 3)
        {
            stringbuilder.append(c6);
        }
        return stringbuilder.toString();
    }

    public final void encode(g g1)
    {
        int i;
        StringBuilder stringbuilder;
        int j;
        i = 1;
        stringbuilder = new StringBuilder();
        do
        {
            if (!g1.hasMoreCharacters())
            {
                break;
            }
            char c = g1.getCurrentChar();
            if (c >= ' ' && c <= '?')
            {
                stringbuilder.append(c);
            } else
            if (c >= '@' && c <= '^')
            {
                stringbuilder.append((char)(c - 64));
            } else
            {
                HighLevelEncoder.c(c);
            }
            g1.c = 1 + g1.c;
            if (stringbuilder.length() < 4)
            {
                continue;
            }
            g1.writeCodewords(a(stringbuilder, 0));
            stringbuilder.delete(0, 4);
            if (HighLevelEncoder.a(g1.a, g1.c, getEncodingMode()) == getEncodingMode())
            {
                continue;
            }
            g1.signalEncoderChange(0);
            break;
        } while (true);
        stringbuilder.append('\037');
        j = stringbuilder.length();
        if (j == 0)
        {
            g1.signalEncoderChange(0);
            return;
        }
        if (j != i)
        {
            break MISSING_BLOCK_LABEL_214;
        }
        int i1;
        int j1;
        g1.updateSymbolInfo();
        i1 = g1.e.a - g1.getCodewordCount();
        j1 = g1.getRemainingCharacters();
        if (j1 == 0 && i1 <= 2)
        {
            g1.signalEncoderChange(0);
            return;
        }
        if (j <= 4)
        {
            break MISSING_BLOCK_LABEL_240;
        }
        throw new IllegalStateException("Count must not exceed 4");
        Exception exception;
        exception;
        g1.signalEncoderChange(0);
        throw exception;
        int k = j - 1;
        String s = a(stringbuilder, 0);
        int l;
        if (!g1.hasMoreCharacters())
        {
            l = i;
        } else
        {
            l = 0;
        }
          goto _L1
_L5:
        if (k > 2)
        {
            break MISSING_BLOCK_LABEL_315;
        }
        g1.updateSymbolInfo(k + g1.getCodewordCount());
        if (g1.e.a - g1.getCodewordCount() < 3)
        {
            break MISSING_BLOCK_LABEL_315;
        }
        g1.updateSymbolInfo(g1.getCodewordCount() + s.length());
        i = 0;
        if (i == 0) goto _L3; else goto _L2
_L2:
        g1.resetSymbolInfo();
        g1.c = g1.c - k;
_L4:
        g1.signalEncoderChange(0);
        return;
_L6:
        i = 0;
        break; /* Loop/switch isn't completed */
_L3:
        g1.writeCodewords(s);
          goto _L4
_L1:
        if (l == 0 || k > 2) goto _L6; else goto _L5
    }

    public final int getEncodingMode()
    {
        return 4;
    }
}
