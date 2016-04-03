// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package net.simonvt.menudrawer:
//            StaticDrawer, Position

public class BottomStaticDrawer extends StaticDrawer
{

    private int b;

    BottomStaticDrawer(Activity activity, int i)
    {
        super(activity, i);
    }

    public BottomStaticDrawer(Context context)
    {
        super(context);
    }

    public BottomStaticDrawer(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public BottomStaticDrawer(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void drawIndicator(Canvas canvas)
    {
        if (mActiveView != null && isViewDescendant(mActiveView))
        {
            Integer integer = (Integer)mActiveView.getTag(R.id.mdActiveViewPosition);
            int i;
            if (integer == null)
            {
                i = 0;
            } else
            {
                i = integer.intValue();
            }
            if (i == mActivePosition)
            {
                int j = getHeight();
                int k = mMenuSize;
                int l = mActiveIndicator.getHeight();
                mActiveView.getDrawingRect(mActiveRect);
                offsetDescendantRectToMyCoords(mActiveView, mActiveRect);
                int i1 = mActiveIndicator.getWidth();
                int j1 = j - k;
                int k1 = j1 + l;
                if (mIndicatorAnimating)
                {
                    int l1 = mActiveRect.left + (mActiveRect.width() - i1) / 2;
                    int i2 = mIndicatorStartPos;
                    b = i2 + (int)((float)(l1 - i2) * mIndicatorOffset);
                } else
                {
                    b = mActiveRect.left + (mActiveRect.width() - i1) / 2;
                }
                canvas.save();
                canvas.clipRect(b, j1, i1 + b, k1);
                canvas.drawBitmap(mActiveIndicator, b, j1, null);
                canvas.restore();
            }
        }
    }

    protected int getIndicatorStartPos()
    {
        return b;
    }

    protected void initDrawer(Context context, AttributeSet attributeset, int i)
    {
        super.initDrawer(context, attributeset, i);
        mPosition = Position.BOTTOM;
    }

    public void setDropShadowColor(int i)
    {
        int j = 0xffffff & i;
        mDropShadowDrawable = new GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
            i, j
        });
        invalidate();
    }
}
