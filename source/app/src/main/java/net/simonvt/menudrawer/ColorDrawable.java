// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

// Referenced classes of package net.simonvt.menudrawer:
//            c

public class ColorDrawable extends Drawable
{

    private c a;
    private final Paint b;

    public ColorDrawable()
    {
        this(((c) (null)));
    }

    public ColorDrawable(int i)
    {
        this(((c) (null)));
        setColor(i);
    }

    private ColorDrawable(c c1)
    {
        b = new Paint();
        a = new c(c1);
    }

    ColorDrawable(c c1, byte byte0)
    {
        this(c1);
    }

    public void draw(Canvas canvas)
    {
        if (a.b >>> 24 != 0)
        {
            b.setColor(a.b);
            canvas.drawRect(getBounds(), b);
        }
    }

    public int getAlpha()
    {
        return a.b >>> 24;
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | a.c;
    }

    public int getColor()
    {
        return a.b;
    }

    public android.graphics.drawable.Drawable.ConstantState getConstantState()
    {
        a.c = getChangingConfigurations();
        return a;
    }

    public int getOpacity()
    {
        switch (a.b >>> 24)
        {
        default:
            return -3;

        case 255: 
            return -1;

        case 0: // '\0'
            return -2;
        }
    }

    public void setAlpha(int i)
    {
        int j = (i + (i >> 7)) * (a.a >>> 24) >> 8;
        int k = a.b;
        a.b = (a.a << 8) >>> 8 | j << 24;
        if (k != a.b)
        {
            invalidateSelf();
        }
    }

    public void setColor(int i)
    {
        if (a.a != i || a.b != i)
        {
            invalidateSelf();
            c c1 = a;
            a.b = i;
            c1.a = i;
        }
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
    }
}
