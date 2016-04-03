// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            f, g, HighLevelEncoder

final class a
    implements f
{

    a()
    {
    }

    public final void encode(g g1)
    {
        if (HighLevelEncoder.determineConsecutiveDigitCount(g1.a, g1.c) >= 2)
        {
            char c1 = g1.a.charAt(g1.c);
            char c2 = g1.a.charAt(1 + g1.c);
            if (HighLevelEncoder.a(c1) && HighLevelEncoder.a(c2))
            {
                g1.writeCodeword((char)(130 + (10 * (c1 - 48) + (c2 - 48))));
                g1.c = 2 + g1.c;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder("not digits: ")).append(c1).append(c2).toString());
            }
        }
        char c = g1.getCurrentChar();
        int i = HighLevelEncoder.a(g1.a, g1.c, getEncodingMode());
        if (i != getEncodingMode())
        {
            switch (i)
            {
            default:
                throw new IllegalStateException((new StringBuilder("Illegal mode: ")).append(i).toString());

            case 5: // '\005'
                g1.writeCodeword('\347');
                g1.signalEncoderChange(5);
                return;

            case 1: // '\001'
                g1.writeCodeword('\346');
                g1.signalEncoderChange(1);
                return;

            case 3: // '\003'
                g1.writeCodeword('\356');
                g1.signalEncoderChange(3);
                return;

            case 2: // '\002'
                g1.writeCodeword('\357');
                g1.signalEncoderChange(2);
                return;

            case 4: // '\004'
                g1.writeCodeword('\360');
                g1.signalEncoderChange(4);
                return;
            }
        }
        if (HighLevelEncoder.b(c))
        {
            g1.writeCodeword('\353');
            g1.writeCodeword((char)(1 + (c - 128)));
            g1.c = 1 + g1.c;
            return;
        } else
        {
            g1.writeCodeword((char)(c + 1));
            g1.c = 1 + g1.c;
            return;
        }
    }

    public final int getEncodingMode()
    {
        return 0;
    }
}
