// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.graphics.Bitmap;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import java.util.LinkedList;

// Referenced classes of package com.android.volley.toolbox:
//            ImageLoader

final class k
{

    private final Request a;
    private Bitmap b;
    private VolleyError c;
    private final LinkedList d = new LinkedList();

    public k(ImageLoader imageloader, Request request, ImageLoader.ImageContainer imagecontainer)
    {
        a = request;
        d.add(imagecontainer);
    }

    static Bitmap a(k k1, Bitmap bitmap)
    {
        k1.b = bitmap;
        return bitmap;
    }

    static LinkedList a(k k1)
    {
        return k1.d;
    }

    static Bitmap b(k k1)
    {
        return k1.b;
    }

    public final void addContainer(ImageLoader.ImageContainer imagecontainer)
    {
        d.add(imagecontainer);
    }

    public final VolleyError getError()
    {
        return c;
    }

    public final boolean removeContainerAndCancelIfNecessary(ImageLoader.ImageContainer imagecontainer)
    {
        d.remove(imagecontainer);
        if (d.size() == 0)
        {
            a.cancel();
            return true;
        } else
        {
            return false;
        }
    }

    public final void setError(VolleyError volleyerror)
    {
        c = volleyerror;
    }
}
