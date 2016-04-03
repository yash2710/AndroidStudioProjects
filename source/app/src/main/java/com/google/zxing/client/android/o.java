// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.net.Uri;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.result.ResultHandler;
import com.google.zxing.client.result.ParsedResultType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class o
{

    private final String a;
    private final boolean b;

    o(Uri uri)
    {
        a = uri.getQueryParameter("ret");
        boolean flag;
        if (uri.getQueryParameter("raw") != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        b = flag;
    }

    private static String a(CharSequence charsequence, CharSequence charsequence1, String s)
    {
        if (charsequence1 == null)
        {
            charsequence1 = "";
        }
        String s1 = URLEncoder.encode(charsequence1.toString(), "UTF-8");
        charsequence1 = s1;
_L2:
        return s.replace(charsequence, charsequence1);
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    String a(Result result, ResultHandler resulthandler)
    {
        String s = a;
        Object obj;
        String s1;
        String s2;
        String s3;
        String s4;
        if (b)
        {
            obj = result.getText();
        } else
        {
            obj = resulthandler.getDisplayContents();
        }
        s1 = a("{CODE}", ((CharSequence) (obj)), s);
        s2 = a("{RAWCODE}", ((CharSequence) (result.getText())), s1);
        s3 = a("{FORMAT}", ((CharSequence) (result.getBarcodeFormat().toString())), s2);
        s4 = a("{TYPE}", ((CharSequence) (resulthandler.getType().toString())), s3);
        return a("{META}", ((CharSequence) (String.valueOf(result.getResultMetadata()))), s4);
    }

    boolean a()
    {
        return a != null;
    }
}
