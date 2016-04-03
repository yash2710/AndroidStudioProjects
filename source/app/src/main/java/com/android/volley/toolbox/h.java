// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.VolleyError;

// Referenced classes of package com.android.volley.toolbox:
//            f, ImageLoader

final class h
    implements com.android.volley.Response.ErrorListener
{

    private f a;

    h(f f1)
    {
        a = f1;
        super();
    }

    public final void onErrorResponse(VolleyError volleyerror)
    {
        ImageLoader.a(a.c, a.a, volleyerror);
    }
}
