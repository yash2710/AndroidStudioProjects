// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

public class GifView extends View
{

    private int a;
    private Movie b;
    private long c;
    private int d;
    private int e;
    private int f;

    public GifView(Context context, AttributeSet attributeset)
    {
        TypedArray typedarray;
        super(context, attributeset);
        typedarray = context.getTheme().obtainStyledAttributes(attributeset, com.flipkart.android.R.styleable.GifView, 0, 0);
        a = -1;
        b = null;
        c = 0L;
        d = 0;
        e = 0;
        a = typedarray.getResourceId(0, -1);
        b = Movie.decodeStream(context.getResources().openRawResource(a));
        if (b != null)
        {
            e = b.width();
            d = b.height();
        }
        typedarray.recycle();
        return;
        Exception exception;
        exception;
        typedarray.recycle();
        throw exception;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (b == null)
        {
            return;
        }
        long l = System.currentTimeMillis();
        if (c == 0L)
        {
            c = l;
        }
        int i = (int)((l - c) % (long)b.duration());
        b.setTime(i);
        b.draw(canvas, f, getPaddingTop());
    }

    protected void onMeasure(int i, int j)
    {
        int k = getPaddingTop();
        int l = android.view.View.MeasureSpec.makeMeasureSpec(getPaddingBottom() + (k + d), 0x40000000);
        setMeasuredDimension(i, l);
        super.onMeasure(i, l);
        f = Math.max((getWidth() - e) / 2, 0);
    }
}
