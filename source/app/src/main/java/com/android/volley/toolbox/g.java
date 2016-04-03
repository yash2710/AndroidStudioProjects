// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.graphics.Bitmap;

// Referenced classes of package com.android.volley.toolbox:
//            f, ImageLoader

final class g
    implements com.android.volley.Response.Listener
{

    private f a;

    g(f f1)
    {
        a = f1;
        super();
    }

    public final void onResponse(Bitmap bitmap)
    {
        ImageLoader.a(a.c, a.a, bitmap);
        if (bitmap == null)
        {
            a.c.sendErrorBroadcast(a.b);
        }
    }

    public final volatile void onResponse(Object obj)
    {
        onResponse((Bitmap)obj);
    }
}
