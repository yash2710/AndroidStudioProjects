// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable;

import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.wearable:
//            WearableDataSender, f, e

final class g
    implements Runnable
{

    private com.android.volley.toolbox.ImageLoader.ImageContainer a;
    private f b;

    g(f f1, com.android.volley.toolbox.ImageLoader.ImageContainer imagecontainer)
    {
        b = f1;
        a = imagecontainer;
        super();
    }

    public final void run()
    {
        android.graphics.Bitmap bitmap = a.getBitmap();
        if (bitmap != null)
        {
            FkLogger.verbose(WearableDataSender.a(), (new StringBuilder("Successfully downloaded bitmap for ")).append(b.a.a).toString());
            WearableDataSender.a(new WearableDataSender(WearableDataSender.a(b.a.b)), b.a.a, bitmap);
            return;
        } else
        {
            FkLogger.verbose(WearableDataSender.a(), (new StringBuilder("Could not download bitmap for ")).append(b.a.a).toString());
            return;
        }
    }
}
