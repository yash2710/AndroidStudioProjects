// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

// Referenced classes of package net.simonvt.menudrawer:
//            MenuDrawer, l, Position, BuildLayerFrameLayout

public abstract class StaticDrawer extends MenuDrawer
{

    protected Position mPosition;

    StaticDrawer(Activity activity, int i)
    {
        super(activity, i);
    }

    public StaticDrawer(Context context)
    {
        super(context);
    }

    public StaticDrawer(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public StaticDrawer(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public void closeMenu(boolean flag)
    {
    }

    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        if (!mDropShadowEnabled) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        int k;
        int i1;
        i = getWidth();
        j = getHeight();
        k = mMenuSize;
        i1 = mDropShadowSize;
        l.a[mPosition.ordinal()];
        JVM INSTR tableswitch 1 4: default 76
    //                   1 97
    //                   2 116
    //                   3 135
    //                   4 158;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        mDropShadowDrawable.draw(canvas);
_L2:
        if (mActiveIndicator != null)
        {
            drawIndicator(canvas);
        }
        return;
_L4:
        mDropShadowDrawable.setBounds(k - i1, 0, k, j);
        continue; /* Loop/switch isn't completed */
_L5:
        mDropShadowDrawable.setBounds(0, k - i1, i, k);
        continue; /* Loop/switch isn't completed */
_L6:
        mDropShadowDrawable.setBounds(i - k, 0, i1 + (i - k), j);
        continue; /* Loop/switch isn't completed */
_L7:
        mDropShadowDrawable.setBounds(0, j - k, i, i1 + (j - k));
        if (true) goto _L3; else goto _L8
_L8:
    }

    protected abstract void drawIndicator(Canvas canvas);

    public boolean getOffsetMenuEnabled()
    {
        return false;
    }

    public int getTouchBezelSize()
    {
        return 0;
    }

    public int getTouchMode()
    {
        return 0;
    }

    public boolean isMenuVisible()
    {
        return true;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int i1)
    {
        int j1 = k - i;
        int k1 = i1 - j;
        switch (l.a[mPosition.ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            mMenuContainer.layout(0, 0, mMenuSize, k1);
            mContentContainer.layout(mMenuSize, 0, j1, k1);
            return;

        case 3: // '\003'
            mMenuContainer.layout(j1 - mMenuSize, 0, j1, k1);
            mContentContainer.layout(0, 0, j1 - mMenuSize, k1);
            return;

        case 2: // '\002'
            mMenuContainer.layout(0, 0, j1, mMenuSize);
            mContentContainer.layout(0, mMenuSize, j1, k1);
            return;

        case 4: // '\004'
            mMenuContainer.layout(0, k1 - mMenuSize, j1, k1);
            break;
        }
        mContentContainer.layout(0, 0, j1, k1 - mMenuSize);
    }

    protected void onMeasure(int i, int j)
    {
        int j1;
        int k1;
        int k = android.view.View.MeasureSpec.getMode(i);
        int i1 = android.view.View.MeasureSpec.getMode(j);
        if (k != 0x40000000 || i1 != 0x40000000)
        {
            throw new IllegalStateException("Must measure with an exact size");
        }
        j1 = android.view.View.MeasureSpec.getSize(i);
        k1 = android.view.View.MeasureSpec.getSize(j);
        if (!mMenuSizeSet)
        {
            mMenuSize = (int)(0.25F * (float)k1);
        }
        l.a[mPosition.ordinal()];
        JVM INSTR tableswitch 1 4: default 104
    //                   1 113
    //                   2 174
    //                   3 113
    //                   4 174;
           goto _L1 _L2 _L3 _L2 _L3
_L1:
        setMeasuredDimension(j1, k1);
        return;
_L2:
        int l2 = android.view.View.MeasureSpec.makeMeasureSpec(k1, 0x40000000);
        int i3 = mMenuSize;
        int j3 = android.view.View.MeasureSpec.makeMeasureSpec(i3, 0x40000000);
        int k3 = android.view.View.MeasureSpec.makeMeasureSpec(j1 - i3, 0x40000000);
        mContentContainer.measure(k3, l2);
        mMenuContainer.measure(j3, l2);
        continue; /* Loop/switch isn't completed */
_L3:
        int l1 = android.view.View.MeasureSpec.makeMeasureSpec(j1, 0x40000000);
        int i2 = mMenuSize;
        int j2 = android.view.View.MeasureSpec.makeMeasureSpec(i2, 0x40000000);
        int k2 = android.view.View.MeasureSpec.makeMeasureSpec(k1 - i2, 0x40000000);
        mContentContainer.measure(l1, k2);
        mMenuContainer.measure(l1, j2);
        if (true) goto _L1; else goto _L4
_L4:
    }

    public void openMenu(boolean flag)
    {
    }

    public void peekDrawer()
    {
    }

    public void peekDrawer(long l1)
    {
    }

    public void peekDrawer(long l1, long l2)
    {
    }

    public void setHardwareLayerEnabled(boolean flag)
    {
    }

    public void setMenuSize(int i)
    {
        mMenuSize = i;
        mMenuSizeSet = true;
        requestLayout();
        invalidate();
    }

    public void setOffsetMenuEnabled(boolean flag)
    {
    }

    public void setTouchBezelSize(int i)
    {
    }

    public void setTouchMode(int i)
    {
    }

    public void toggleMenu(boolean flag)
    {
    }
}
