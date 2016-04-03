// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.component;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import com.android.volley.VolleyError;
import com.flipkart.android.activity.base.FlipkartBaseSherlockFragmentActivity;

final class a
    implements com.android.volley.toolbox.ImageLoader.ImageListener
{

    private ActionBar a;
    private FlipkartBaseSherlockFragmentActivity b;

    a(ActionBar actionbar, FlipkartBaseSherlockFragmentActivity flipkartbasesherlockfragmentactivity)
    {
        a = actionbar;
        b = flipkartbasesherlockfragmentactivity;
        super();
    }

    public final void onErrorResponse(VolleyError volleyerror)
    {
    }

    public final void onResponse(com.android.volley.toolbox.ImageLoader.ImageContainer imagecontainer, boolean flag)
    {
        if (imagecontainer != null && imagecontainer.getBitmap() != null && a != null)
        {
            BitmapDrawable bitmapdrawable = new BitmapDrawable(imagecontainer.getBitmap());
            bitmapdrawable.setTileModeX(android.graphics.Shader.TileMode.REPEAT);
            bitmapdrawable.setTileModeY(android.graphics.Shader.TileMode.REPEAT);
            a.setBackgroundDrawable(bitmapdrawable);
            b.setmActionBarBackgroundDrawable(bitmapdrawable);
        }
    }
}
