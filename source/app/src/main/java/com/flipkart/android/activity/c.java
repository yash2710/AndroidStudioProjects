// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import com.flipkart.android.login.onGoogleLoginWebViewClientListener;

// Referenced classes of package com.flipkart.android.activity:
//            b

final class c
    implements Runnable
{

    private com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientEventType a;
    private String b;
    private b c;

    c(b b1, com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientEventType tgoogleloginwebclienteventtype, String s)
    {
        c = b1;
        a = tgoogleloginwebclienteventtype;
        b = s;
        super();
    }

    public final void run()
    {
        com.flipkart.android.activity.b.a(c).offerGoogleLoginWebViewClientEvent(a, b);
    }
}
