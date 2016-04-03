// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable;

import android.os.Looper;
import com.android.volley.VolleyError;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.wearable:
//            g, e

final class f
    implements com.android.volley.toolbox.ImageLoader.ImageListener
{

    final e a;

    f(e e)
    {
        a = e;
        super();
    }

    public final void onErrorResponse(VolleyError volleyerror)
    {
        FkLogger.printStackTrace(volleyerror);
    }

    public final void onResponse(com.android.volley.toolbox.ImageLoader.ImageContainer imagecontainer, boolean flag)
    {
        g g1 = new g(this, imagecontainer);
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            (new Thread(g1)).start();
            return;
        } else
        {
            g1.run();
            return;
        }
    }
}
