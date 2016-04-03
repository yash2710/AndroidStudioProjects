// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            Mode, ErrorCorrectionLevel, Version

final class l
{

    private static final char a[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
        'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', 
        '+', '-', '.', '/', ':'
    };

    private static char a(int i)
    {
        char[] _tmp = a;
        if (i >= 45)
        {
            throw FormatException.getFormatInstance();
        } else
        {
            return a[i];
        }
    }

    static DecoderResult a(byte abyte0[], Version version, ErrorCorrectionLevel errorcorrectionlevel, Map map)
    {
        BitSource bitsource;
        StringBuilder stringbuilder;
        ArrayList arraylist;
        boolean flag;
        CharacterSetECI characterseteci;
        bitsource = new BitSource(abyte0);
        stringbuilder = new StringBuilder(50);
        arraylist = new ArrayList(1);
        flag = false;
        characterseteci = null;
_L18:
        if (bitsource.available() >= 4) goto _L2; else goto _L1
_L1:
        Mode mode = Mode.TERMINATOR;
_L7:
        if (mode == Mode.TERMINATOR) goto _L4; else goto _L3
_L3:
        if (mode != Mode.FNC1_FIRST_POSITION && mode != Mode.FNC1_SECOND_POSITION) goto _L6; else goto _L5
_L2:
        mode = Mode.forBits(bitsource.readBits(4));
          goto _L7
_L6:
        if (mode != Mode.STRUCTURED_APPEND)
        {
            break MISSING_BLOCK_LABEL_189;
        }
        if (bitsource.available() < 16)
        {
            throw FormatException.getFormatInstance();
        }
        bitsource.readBits(16);
        boolean flag1 = flag;
          goto _L8
        if (mode != Mode.ECI) goto _L10; else goto _L9
_L9:
        i1 = bitsource.readBits(8);
        if ((i1 & 0x80) != 0) goto _L12; else goto _L11
_L11:
        j1 = i1 & 0x7f;
_L15:
        characterseteci = CharacterSetECI.getCharacterSetECIByValue(j1);
        if (characterseteci != null) goto _L14; else goto _L13
_L13:
        throw FormatException.getFormatInstance();
_L12:
        if ((i1 & 0xc0) != 128)
        {
            break MISSING_BLOCK_LABEL_271;
        }
        j1 = bitsource.readBits(8) | (i1 & 0x3f) << 8;
          goto _L15
        if ((i1 & 0xe0) != 192)
        {
            break MISSING_BLOCK_LABEL_304;
        }
        j1 = bitsource.readBits(16) | (i1 & 0x1f) << 16;
          goto _L15
        throw FormatException.getFormatInstance();
_L10:
        if (mode != Mode.HANZI) goto _L17; else goto _L16
_L16:
        j = bitsource.readBits(4);
        k = bitsource.readBits(mode.getCharacterCountBits(version));
        if (j != 1)
        {
            break MISSING_BLOCK_LABEL_511;
        }
        a(bitsource, stringbuilder, k);
        break MISSING_BLOCK_LABEL_511;
_L17:
        i = bitsource.readBits(mode.getCharacterCountBits(version));
        if (mode != Mode.NUMERIC)
        {
            break MISSING_BLOCK_LABEL_392;
        }
        c(bitsource, stringbuilder, i);
        flag1 = flag;
          goto _L8
        if (mode != Mode.ALPHANUMERIC)
        {
            break MISSING_BLOCK_LABEL_418;
        }
        a(bitsource, stringbuilder, i, flag);
        flag1 = flag;
          goto _L8
        if (mode != Mode.BYTE)
        {
            break MISSING_BLOCK_LABEL_447;
        }
        a(bitsource, stringbuilder, i, characterseteci, ((Collection) (arraylist)), map);
        flag1 = flag;
          goto _L8
        if (mode != Mode.KANJI)
        {
            break MISSING_BLOCK_LABEL_471;
        }
        b(bitsource, stringbuilder, i);
        flag1 = flag;
          goto _L8
        throw FormatException.getFormatInstance();
_L4:
        flag1 = flag;
_L8:
        Mode mode1;
        try
        {
            mode1 = Mode.TERMINATOR;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw FormatException.getFormatInstance();
        }
        if (mode == mode1)
        {
            String s = stringbuilder.toString();
            if (arraylist.isEmpty())
            {
                arraylist = null;
            }
            String s1;
            int i;
            int j;
            int k;
            int i1;
            int j1;
            if (errorcorrectionlevel == null)
            {
                s1 = null;
            } else
            {
                s1 = errorcorrectionlevel.toString();
            }
            return new DecoderResult(abyte0, s, arraylist, s1);
        }
        flag = flag1;
          goto _L18
_L5:
        flag1 = true;
          goto _L8
_L14:
        flag1 = flag;
          goto _L8
        flag1 = flag;
          goto _L8
    }

    private static void a(BitSource bitsource, StringBuilder stringbuilder, int i)
    {
        if (i * 13 > bitsource.available())
        {
            throw FormatException.getFormatInstance();
        }
        byte abyte0[] = new byte[i * 2];
        int j = 0;
        while (i > 0) 
        {
            int k = bitsource.readBits(13);
            int i1 = k / 96 << 8 | k % 96;
            int j1;
            int k1;
            if (i1 < 959)
            {
                j1 = i1 + 41377;
            } else
            {
                j1 = i1 + 42657;
            }
            abyte0[j] = (byte)(j1 >> 8);
            abyte0[j + 1] = (byte)j1;
            k1 = j + 2;
            i--;
            j = k1;
        }
        try
        {
            stringbuilder.append(new String(abyte0, "GB2312"));
            return;
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw FormatException.getFormatInstance();
        }
    }

    private static void a(BitSource bitsource, StringBuilder stringbuilder, int i, CharacterSetECI characterseteci, Collection collection, Map map)
    {
        if (i << 3 > bitsource.available())
        {
            throw FormatException.getFormatInstance();
        }
        byte abyte0[] = new byte[i];
        for (int j = 0; j < i; j++)
        {
            abyte0[j] = (byte)bitsource.readBits(8);
        }

        String s;
        if (characterseteci == null)
        {
            s = StringUtils.guessEncoding(abyte0, map);
        } else
        {
            s = characterseteci.name();
        }
        try
        {
            stringbuilder.append(new String(abyte0, s));
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw FormatException.getFormatInstance();
        }
        collection.add(abyte0);
    }

    private static void a(BitSource bitsource, StringBuilder stringbuilder, int i, boolean flag)
    {
        int j = stringbuilder.length();
        for (; i > 1; i -= 2)
        {
            if (bitsource.available() < 11)
            {
                throw FormatException.getFormatInstance();
            }
            int k = bitsource.readBits(11);
            stringbuilder.append(a(k / 45));
            stringbuilder.append(a(k % 45));
        }

        if (i == 1)
        {
            if (bitsource.available() < 6)
            {
                throw FormatException.getFormatInstance();
            }
            stringbuilder.append(a(bitsource.readBits(6)));
        }
        if (flag)
        {
            while (j < stringbuilder.length()) 
            {
                if (stringbuilder.charAt(j) == '%')
                {
                    if (j < -1 + stringbuilder.length() && stringbuilder.charAt(j + 1) == '%')
                    {
                        stringbuilder.deleteCharAt(j + 1);
                    } else
                    {
                        stringbuilder.setCharAt(j, '\035');
                    }
                }
                j++;
            }
        }
    }

    private static void b(BitSource bitsource, StringBuilder stringbuilder, int i)
    {
        if (i * 13 > bitsource.available())
        {
            throw FormatException.getFormatInstance();
        }
        byte abyte0[] = new byte[i * 2];
        int j = 0;
        while (i > 0) 
        {
            int k = bitsource.readBits(13);
            int i1 = k / 192 << 8 | k % 192;
            int j1;
            int k1;
            if (i1 < 7936)
            {
                j1 = i1 + 33088;
            } else
            {
                j1 = i1 + 49472;
            }
            abyte0[j] = (byte)(j1 >> 8);
            abyte0[j + 1] = (byte)j1;
            k1 = j + 2;
            i--;
            j = k1;
        }
        try
        {
            stringbuilder.append(new String(abyte0, "SJIS"));
            return;
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw FormatException.getFormatInstance();
        }
    }

    private static void c(BitSource bitsource, StringBuilder stringbuilder, int i)
    {
        for (; i >= 3; i -= 3)
        {
            if (bitsource.available() < 10)
            {
                throw FormatException.getFormatInstance();
            }
            int i1 = bitsource.readBits(10);
            if (i1 >= 1000)
            {
                throw FormatException.getFormatInstance();
            }
            stringbuilder.append(a(i1 / 100));
            stringbuilder.append(a((i1 / 10) % 10));
            stringbuilder.append(a(i1 % 10));
        }

        if (i == 2)
        {
            if (bitsource.available() < 7)
            {
                throw FormatException.getFormatInstance();
            }
            int k = bitsource.readBits(7);
            if (k >= 100)
            {
                throw FormatException.getFormatInstance();
            }
            stringbuilder.append(a(k / 10));
            stringbuilder.append(a(k % 10));
        } else
        if (i == 1)
        {
            if (bitsource.available() < 4)
            {
                throw FormatException.getFormatInstance();
            }
            int j = bitsource.readBits(4);
            if (j >= 10)
            {
                throw FormatException.getFormatInstance();
            } else
            {
                stringbuilder.append(a(j));
                return;
            }
        }
    }

}
