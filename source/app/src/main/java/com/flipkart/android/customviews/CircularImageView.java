// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircularImageView extends ImageView
{

    private int a;
    private int b;
    private int c;
    private Bitmap d;
    private Paint e;
    private Paint f;
    private BitmapShader g;

    public CircularImageView(Context context)
    {
        super(context);
        a = 4;
        a();
    }

    public CircularImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        a = 4;
        a();
    }

    public CircularImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        a = 4;
        a();
    }

    private void a()
    {
        e = new Paint();
        e.setAntiAlias(true);
        f = new Paint();
        setBorderColor(-1);
        f.setAntiAlias(true);
        setLayerType(1, f);
        f.setShadowLayer(4F, 0.0F, 2.0F, 0xff000000);
    }

    public void onDraw(Canvas canvas)
    {
        BitmapDrawable bitmapdrawable = (BitmapDrawable)getDrawable();
        if (bitmapdrawable != null)
        {
            d = bitmapdrawable.getBitmap();
        }
        if (d != null)
        {
            g = new BitmapShader(Bitmap.createScaledBitmap(d, canvas.getWidth(), canvas.getHeight(), false), android.graphics.Shader.TileMode.CLAMP, android.graphics.Shader.TileMode.CLAMP);
            e.setShader(g);
            int i = b / 2;
            canvas.drawCircle(i + a, i + a, (float)(i + a) - 4F, f);
            canvas.drawCircle(i + a, i + a, (float)i - 4F, e);
        }
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        int l = android.view.View.MeasureSpec.getSize(i);
        int i1;
        int j1;
        int k1;
        if (k != 0x40000000)
        {
            l = b;
        }
        i1 = android.view.View.MeasureSpec.getMode(j);
        j1 = android.view.View.MeasureSpec.getSize(j);
        if (i1 != 0x40000000)
        {
            j1 = c;
        }
        k1 = j1 + 2;
        b = l - (a << 1);
        c = k1 - (a << 1);
        setMeasuredDimension(l, k1);
    }

    public void setBorderColor(int i)
    {
        if (f != null)
        {
            f.setColor(i);
        }
        invalidate();
    }

    public void setBorderWidth(int i)
    {
        a = i;
        invalidate();
    }
}
