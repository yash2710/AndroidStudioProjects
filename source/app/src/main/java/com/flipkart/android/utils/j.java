// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import com.android.volley.VolleyError;

final class j
    implements com.android.volley.toolbox.ImageLoader.ImageListener
{

    private View a;

    j(View view)
    {
        a = view;
        super();
    }

    public final void onErrorResponse(VolleyError volleyerror)
    {
    }

    public final void onResponse(com.android.volley.toolbox.ImageLoader.ImageContainer imagecontainer, boolean flag)
    {
        if (imagecontainer.getBitmap() != null)
        {
            BitmapDrawable bitmapdrawable = new BitmapDrawable(imagecontainer.getBitmap());
            bitmapdrawable.setTileModeY(android.graphics.Shader.TileMode.REPEAT);
            bitmapdrawable.setTileModeX(android.graphics.Shader.TileMode.REPEAT);
            a.setBackgroundDrawable(bitmapdrawable);
        }
    }
}
