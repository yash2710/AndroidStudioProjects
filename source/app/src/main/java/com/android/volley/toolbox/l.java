// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.VolleyError;

// Referenced classes of package com.android.volley.toolbox:
//            NetworkImageView, m

final class l
    implements ImageLoader.ImageListener
{

    private boolean a;
    private NetworkImageView b;

    l(NetworkImageView networkimageview, boolean flag)
    {
        b = networkimageview;
        a = flag;
        super();
    }

    public final void onErrorResponse(VolleyError volleyerror)
    {
        if (NetworkImageView.a(b) != 0)
        {
            b.setImageResource(NetworkImageView.a(b));
        }
        b.sendErrorBroadcast(NetworkImageView.b(b));
    }

    public final void onResponse(ImageLoader.ImageContainer imagecontainer, boolean flag)
    {
        if (flag && a)
        {
            b.post(new m(this, imagecontainer));
        } else
        {
            if (imagecontainer.getBitmap() != null)
            {
                NetworkImageView.a(b, imagecontainer.getBitmap());
                return;
            }
            if (NetworkImageView.c(b) != 0)
            {
                NetworkImageView.d(b);
                return;
            }
        }
    }
}
