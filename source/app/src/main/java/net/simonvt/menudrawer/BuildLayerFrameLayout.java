// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

// Referenced classes of package net.simonvt.menudrawer:
//            MenuDrawer, b, a

public class BuildLayerFrameLayout extends FrameLayout
{

    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;

    public BuildLayerFrameLayout(Context context)
    {
        super(context);
        b = true;
        d = true;
        if (MenuDrawer.a)
        {
            setLayerType(2, null);
        }
    }

    public BuildLayerFrameLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        b = true;
        d = true;
        if (MenuDrawer.a)
        {
            setLayerType(2, null);
        }
    }

    public BuildLayerFrameLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        b = true;
        d = true;
        if (MenuDrawer.a)
        {
            setLayerType(2, null);
        }
    }

    static boolean a(BuildLayerFrameLayout buildlayerframelayout)
    {
        return buildlayerframelayout.c;
    }

    static boolean a(BuildLayerFrameLayout buildlayerframelayout, boolean flag)
    {
        buildlayerframelayout.a = true;
        return true;
    }

    static boolean b(BuildLayerFrameLayout buildlayerframelayout)
    {
        return buildlayerframelayout.d;
    }

    static boolean b(BuildLayerFrameLayout buildlayerframelayout, boolean flag)
    {
        buildlayerframelayout.d = false;
        return false;
    }

    final void a(boolean flag)
    {
        b = flag;
    }

    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        if (a && MenuDrawer.a)
        {
            post(new b(this));
            a = false;
        }
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        c = true;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        c = false;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if (MenuDrawer.a && b)
        {
            post(new a(this));
        }
    }
}
