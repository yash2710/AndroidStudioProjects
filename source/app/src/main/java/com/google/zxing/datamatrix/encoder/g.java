// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.nio.charset.Charset;

// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            SymbolShapeHint, SymbolInfo

final class g
{

    String a;
    StringBuilder b;
    int c;
    int d;
    SymbolInfo e;
    private SymbolShapeHint f;
    private Dimension g;
    private Dimension h;
    private int i;

    g(String s)
    {
        byte abyte0[] = s.getBytes(Charset.forName("ISO-8859-1"));
        StringBuilder stringbuilder = new StringBuilder(abyte0.length);
        int j = 0;
        for (int k = abyte0.length; j < k; j++)
        {
            char c1 = (char)(0xff & abyte0[j]);
            if (c1 == '?' && s.charAt(j) != '?')
            {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
            stringbuilder.append(c1);
        }

        a = stringbuilder.toString();
        f = SymbolShapeHint.FORCE_NONE;
        b = new StringBuilder(s.length());
        d = -1;
    }

    private int a()
    {
        return a.length() - i;
    }

    public final int getCodewordCount()
    {
        return b.length();
    }

    public final char getCurrent()
    {
        return a.charAt(c);
    }

    public final char getCurrentChar()
    {
        return a.charAt(c);
    }

    public final String getMessage()
    {
        return a;
    }

    public final int getRemainingCharacters()
    {
        return a() - c;
    }

    public final boolean hasMoreCharacters()
    {
        return c < a();
    }

    public final void resetEncoderSignal()
    {
        d = -1;
    }

    public final void resetSymbolInfo()
    {
        e = null;
    }

    public final void setSizeConstraints(Dimension dimension, Dimension dimension1)
    {
        g = dimension;
        h = dimension1;
    }

    public final void setSkipAtEnd(int j)
    {
        i = j;
    }

    public final void setSymbolShape(SymbolShapeHint symbolshapehint)
    {
        f = symbolshapehint;
    }

    public final void signalEncoderChange(int j)
    {
        d = j;
    }

    public final void updateSymbolInfo()
    {
        updateSymbolInfo(getCodewordCount());
    }

    public final void updateSymbolInfo(int j)
    {
        if (e == null || j > e.a)
        {
            e = SymbolInfo.lookup(j, f, g, h, true);
        }
    }

    public final void writeCodeword(char c1)
    {
        b.append(c1);
    }

    public final void writeCodewords(String s)
    {
        b.append(s);
    }
}
