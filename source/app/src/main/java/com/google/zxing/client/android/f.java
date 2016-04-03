// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.content.Intent;
import android.net.Uri;
import com.google.zxing.BarcodeFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

final class f
{

    static final Collection a;
    static final Collection b;
    static final Collection c;
    static final Collection d;
    private static final Pattern e = Pattern.compile(",");

    static Collection a(Intent intent)
    {
        String s = intent.getStringExtra("SCAN_FORMATS");
        List list = null;
        if (s != null)
        {
            list = Arrays.asList(e.split(s));
        }
        return a(((Iterable) (list)), intent.getStringExtra("SCAN_MODE"));
    }

    static Collection a(Uri uri)
    {
        List list = uri.getQueryParameters("SCAN_FORMATS");
        if (list != null && list.size() == 1 && list.get(0) != null)
        {
            list = Arrays.asList(e.split((CharSequence)list.get(0)));
        }
        return a(((Iterable) (list)), uri.getQueryParameter("SCAN_MODE"));
    }

    private static Collection a(Iterable iterable, String s)
    {
label0:
        {
label1:
            {
                EnumSet enumset;
label2:
                {
                    if (iterable != null)
                    {
                        enumset = EnumSet.noneOf(com/google/zxing/BarcodeFormat);
                        try
                        {
                            for (Iterator iterator = iterable.iterator(); iterator.hasNext(); enumset.add(BarcodeFormat.valueOf((String)iterator.next()))) { }
                            break label2;
                        }
                        catch (IllegalArgumentException illegalargumentexception) { }
                    }
                    if (s == null)
                    {
                        break label0;
                    }
                    if ("PRODUCT_MODE".equals(s))
                    {
                        return a;
                    }
                    break label1;
                }
                return enumset;
            }
            if ("QR_CODE_MODE".equals(s))
            {
                return c;
            }
            if ("DATA_MATRIX_MODE".equals(s))
            {
                return d;
            }
            if ("ONE_D_MODE".equals(s))
            {
                return b;
            }
        }
        return null;
    }

    static 
    {
        c = EnumSet.of(BarcodeFormat.QR_CODE);
        d = EnumSet.of(BarcodeFormat.DATA_MATRIX);
        BarcodeFormat barcodeformat = BarcodeFormat.UPC_A;
        BarcodeFormat abarcodeformat[] = new BarcodeFormat[5];
        abarcodeformat[0] = BarcodeFormat.UPC_E;
        abarcodeformat[1] = BarcodeFormat.EAN_13;
        abarcodeformat[2] = BarcodeFormat.EAN_8;
        abarcodeformat[3] = BarcodeFormat.RSS_14;
        abarcodeformat[4] = BarcodeFormat.RSS_EXPANDED;
        a = EnumSet.of(barcodeformat, abarcodeformat);
        EnumSet enumset = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
        b = enumset;
        enumset.addAll(a);
    }
}
