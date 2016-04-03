// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import android.os.Handler;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            Group

final class e
    implements com.android.volley.Response.Listener
{

    private Group a;

    e(Group group)
    {
        a = group;
        super();
    }

    public final volatile void onResponse(Object obj)
    {
        onResponse((String)obj);
    }

    public final void onResponse(String s)
    {
        a.a.sendEmptyMessage(2);
    }
}
