// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import android.content.Context;
import com.crashlytics.android.internal.aQ;
import com.crashlytics.android.internal.ab;

final class x
{

    private final Context a;
    private final aQ b;

    public x(Context context, aQ aq)
    {
        a = context;
        b = aq;
    }

    private String a(String s, String s1)
    {
        String s2 = ab.a(a, s);
        boolean flag;
        if (s2 == null || s2.length() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return s1;
        } else
        {
            return s2;
        }
    }

    public final String a()
    {
        return a("com.crashlytics.CrashSubmissionPromptTitle", b.a);
    }

    public final String b()
    {
        return a("com.crashlytics.CrashSubmissionPromptMessage", b.b);
    }

    public final String c()
    {
        return a("com.crashlytics.CrashSubmissionSendTitle", b.c);
    }

    public final String d()
    {
        return a("com.crashlytics.CrashSubmissionAlwaysSendTitle", b.g);
    }

    public final String e()
    {
        return a("com.crashlytics.CrashSubmissionCancelTitle", b.e);
    }
}
