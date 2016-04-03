// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import com.crashlytics.android.internal.q;
import com.crashlytics.android.internal.v;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CrashlyticsMissingDependencyException extends RuntimeException
{

    CrashlyticsMissingDependencyException(String s, String s1)
    {
        super(a(s, s1));
    }

    private static String a(String s, String s1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        try
        {
            stringbuilder.append("\nThis app relies on Crashlytics. Configure your build environment here: \n");
            StringBuilder stringbuilder1 = new StringBuilder();
            Object aobj[] = new Object[2];
            aobj[0] = URLEncoder.encode(s, "UTF-8");
            aobj[1] = URLEncoder.encode(s1, "UTF-8");
            stringbuilder.append(stringbuilder1.append(String.format("https://crashlytics.com/register/%s/android/%s", aobj)).append("\n").toString());
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            v.a().b().a("Crashlytics", "Could not find UTF-8 encoding.", unsupportedencodingexception);
        }
        return stringbuilder.toString();
    }
}
