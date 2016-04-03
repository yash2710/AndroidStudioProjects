// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            c, HighLevelEncoder

final class h extends c
{

    h()
    {
    }

    final int a(char c1, StringBuilder stringbuilder)
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
        if (c1 >= 'a' && c1 <= 'z')
        {
            stringbuilder.append((char)(14 + (c1 - 97)));
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
        if (c1 == '`')
        {
            stringbuilder.append('\002');
            stringbuilder.append((char)(c1 - 96));
            return 2;
        }
        if (c1 >= 'A' && c1 <= 'Z')
        {
            stringbuilder.append('\002');
            stringbuilder.append((char)(1 + (c1 - 65)));
            return 2;
        }
        if (c1 >= '{' && c1 <= '\177')
        {
            stringbuilder.append('\002');
            stringbuilder.append((char)(27 + (c1 - 123)));
            return 2;
        }
        if (c1 >= '\200')
        {
            stringbuilder.append("\001\036");
            return 2 + a((char)(c1 - 128), stringbuilder);
        } else
        {
            HighLevelEncoder.c(c1);
            return -1;
        }
    }

    public final int getEncodingMode()
    {
        return 2;
    }
}
