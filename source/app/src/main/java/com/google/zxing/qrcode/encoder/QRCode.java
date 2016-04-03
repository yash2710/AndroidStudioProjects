// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.encoder;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;

// Referenced classes of package com.google.zxing.qrcode.encoder:
//            ByteMatrix

public final class QRCode
{

    public static final int NUM_MASK_PATTERNS = 8;
    private Mode a;
    private ErrorCorrectionLevel b;
    private Version c;
    private int d;
    private ByteMatrix e;

    public QRCode()
    {
        d = -1;
    }

    public static boolean isValidMaskPattern(int i)
    {
        return i >= 0 && i < 8;
    }

    public final ErrorCorrectionLevel getECLevel()
    {
        return b;
    }

    public final int getMaskPattern()
    {
        return d;
    }

    public final ByteMatrix getMatrix()
    {
        return e;
    }

    public final Mode getMode()
    {
        return a;
    }

    public final Version getVersion()
    {
        return c;
    }

    public final void setECLevel(ErrorCorrectionLevel errorcorrectionlevel)
    {
        b = errorcorrectionlevel;
    }

    public final void setMaskPattern(int i)
    {
        d = i;
    }

    public final void setMatrix(ByteMatrix bytematrix)
    {
        e = bytematrix;
    }

    public final void setMode(Mode mode)
    {
        a = mode;
    }

    public final void setVersion(Version version)
    {
        c = version;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(200);
        stringbuilder.append("<<\n");
        stringbuilder.append(" mode: ");
        stringbuilder.append(a);
        stringbuilder.append("\n ecLevel: ");
        stringbuilder.append(b);
        stringbuilder.append("\n version: ");
        stringbuilder.append(c);
        stringbuilder.append("\n maskPattern: ");
        stringbuilder.append(d);
        if (e == null)
        {
            stringbuilder.append("\n matrix: null\n");
        } else
        {
            stringbuilder.append("\n matrix:\n");
            stringbuilder.append(e.toString());
        }
        stringbuilder.append(">>\n");
        return stringbuilder.toString();
    }
}
