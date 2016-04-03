// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import com.facebook.Session;
import com.facebook.SessionState;

// Referenced classes of package com.flipkart.android.activity:
//            LoginActivity

final class w
    implements com.facebook.Session.StatusCallback
{

    private LoginActivity a;

    w(LoginActivity loginactivity)
    {
        a = loginactivity;
        super();
    }

    public final void call(Session session, SessionState sessionstate, Exception exception)
    {
        LoginActivity.a(a, session, sessionstate, exception);
    }
}
