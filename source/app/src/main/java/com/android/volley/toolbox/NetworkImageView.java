// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

// Referenced classes of package com.android.volley.toolbox:
//            l, ImageLoader

public class NetworkImageView extends ImageView
{

    private String a;
    private int b;
    private int c;
    private ImageLoader d;
    private ImageLoader.ImageContainer e;
    private android.widget.ImageView.ScaleType f;
    private boolean g;

    public NetworkImageView(Context context)
    {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        b = com.android.volley.R.drawable.default_image;
        c = com.android.volley.R.drawable.default_image;
        g = false;
    }

    static int a(NetworkImageView networkimageview)
    {
        return networkimageview.c;
    }

    private void a()
    {
        g = true;
        if (f == null)
        {
            f = getScaleType();
        }
        setScaleType(android.widget.ImageView.ScaleType.CENTER);
        setImageResource(b);
        g = false;
    }

    static void a(NetworkImageView networkimageview, Bitmap bitmap)
    {
        networkimageview.g = true;
        if (networkimageview.f != null)
        {
            networkimageview.setScaleType(networkimageview.f);
        }
        networkimageview.setImageBitmap(bitmap);
    }

    private void a(boolean flag)
    {
        int i = getWidth();
        int j = getHeight();
        boolean flag1;
        if (getLayoutParams() != null && getLayoutParams().height == -2 && getLayoutParams().width == -2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (i != 0 || j != 0 || flag1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (TextUtils.isEmpty(a))
        {
            if (e != null)
            {
                e.cancelRequest();
                e = null;
            }
            a();
            return;
        }
        if (e == null || e.getRequestUrl() == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (e.getRequestUrl().equals(a))
        {
            continue; /* Loop/switch isn't completed */
        }
        e.cancelRequest();
        a();
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L3
_L3:
        e = d.get(a, new l(this, flag));
        return;
    }

    static String b(NetworkImageView networkimageview)
    {
        return networkimageview.a;
    }

    static int c(NetworkImageView networkimageview)
    {
        return networkimageview.b;
    }

    static void d(NetworkImageView networkimageview)
    {
        networkimageview.a();
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        invalidate();
    }

    protected void onDetachedFromWindow()
    {
        if (e != null)
        {
            e.cancelRequest();
            setImageBitmap(null);
            e = null;
        }
        super.onDetachedFromWindow();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int i1)
    {
        super.onLayout(flag, i, j, k, i1);
        a(true);
    }

    protected void sendErrorBroadcast(String s)
    {
        Intent intent = new Intent();
        intent.putExtra("url", s);
        intent.setAction("com.flipkart.android.rukminierror");
        getContext().sendBroadcast(intent);
    }

    public void setDefaultImageResId(int i)
    {
        b = i;
    }

    public void setErrorImageResId(int i)
    {
        c = i;
    }

    public void setImageBitmap(Bitmap bitmap)
    {
        getDrawable();
        ColorDrawable colordrawable = new ColorDrawable(0x106000d);
        Drawable adrawable[] = new Drawable[2];
        adrawable[0] = colordrawable;
        adrawable[1] = new BitmapDrawable(getContext().getResources(), bitmap);
        TransitionDrawable transitiondrawable = new TransitionDrawable(adrawable);
        setImageDrawable(transitiondrawable);
        transitiondrawable.startTransition(250);
    }

    public void setImageUrl(String s, ImageLoader imageloader)
    {
        a = s;
        d = imageloader;
        a(false);
    }

    public void setScaleType(android.widget.ImageView.ScaleType scaletype)
    {
        if (g)
        {
            super.setScaleType(scaletype);
            return;
        } else
        {
            f = scaletype;
            return;
        }
    }
}
