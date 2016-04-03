// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import android.os.Handler;
import com.android.volley.VolleyError;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            Group

final class f
    implements com.android.volley.Response.ErrorListener
{

    private Group a;

    f(Group group)
    {
        a = group;
        super();
    }

    public final void onErrorResponse(VolleyError volleyerror)
    {
        a.a.sendEmptyMessage(3);
    }
}
