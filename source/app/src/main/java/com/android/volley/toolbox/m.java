// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;


// Referenced classes of package com.android.volley.toolbox:
//            l

final class m
    implements Runnable
{

    private ImageLoader.ImageContainer a;
    private l b;

    m(l l1, ImageLoader.ImageContainer imagecontainer)
    {
        b = l1;
        a = imagecontainer;
        super();
    }

    public final void run()
    {
        b.onResponse(a, false);
    }
}
