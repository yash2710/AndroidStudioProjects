// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.LinkedList;

// Referenced classes of package com.android.volley.toolbox:
//            ImageLoader, k

public class b
{

    private Bitmap a;
    private final d b;
    private final String c;
    private final String d;
    private ImageLoader e;

    static Bitmap a(b b1, Bitmap bitmap)
    {
        b1.a = bitmap;
        return bitmap;
    }

    static a a(a a1)
    {
        return a1.b;
    }

    public void cancelRequest()
    {
        if (b != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        k k1 = (k)ImageLoader.a(e).get(c);
        if (k1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (k1.removeContainerAndCancelIfNecessary(this))
        {
            ImageLoader.a(e).remove(c);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        k k2 = (k)ImageLoader.d(e).get(c);
        if (k2 != null)
        {
            k2.removeContainerAndCancelIfNecessary(this);
            if (k.a(k2).size() == 0)
            {
                ImageLoader.d(e).remove(c);
                return;
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public Bitmap getBitmap()
    {
        return a;
    }

    public String getRequestUrl()
    {
        return d;
    }

    public (ImageLoader imageloader, Bitmap bitmap, String s, String s1,  )
    {
        e = imageloader;
        super();
        a = bitmap;
        d = s;
        c = s1;
        b = ;
    }
}
