// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import com.android.volley.VolleyError;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;

final class b
    implements com.android.volley.toolbox.ImageLoader.ImageListener
{

    private View a;
    private LayoutDetails b;

    b(View view, LayoutDetails layoutdetails)
    {
        a = view;
        b = layoutdetails;
        super();
    }

    public final void onErrorResponse(VolleyError volleyerror)
    {
        FkLogger.debug("Test", "error got for image bg");
        try
        {
            a.setBackgroundDrawable(new ColorDrawable(Color.parseColor(b.getBackgroundColor())));
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public final void onResponse(com.android.volley.toolbox.ImageLoader.ImageContainer imagecontainer, boolean flag)
    {
        if (imagecontainer.getBitmap() != null && a != null)
        {
            FkLogger.debug("Test", "Response got for image bg");
            BitmapDrawable bitmapdrawable = new BitmapDrawable(a.getContext().getResources(), imagecontainer.getBitmap());
            if (!StringUtils.isNullOrEmpty(b.getBackgroundTileMode()) && b.getBackgroundTileMode().equalsIgnoreCase("repeat"))
            {
                bitmapdrawable.setTileModeX(android.graphics.Shader.TileMode.REPEAT);
                bitmapdrawable.setTileModeY(android.graphics.Shader.TileMode.REPEAT);
            }
            a.setBackgroundDrawable(bitmapdrawable);
        } else
        if (StringUtils.isNullOrEmpty(b.getBackgroundColor()))
        {
            try
            {
                a.setBackgroundDrawable(new ColorDrawable(Color.parseColor(b.getBackgroundColor())));
                return;
            }
            catch (Exception exception)
            {
                return;
            }
        }
    }
}
