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

public class RightStaticDrawer extends StaticDrawer
{

    private int b;

    RightStaticDrawer(Activity activity, int i)
    {
        super(activity, i);
    }

    public RightStaticDrawer(Context context)
    {
        super(context);
    }

    public RightStaticDrawer(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public RightStaticDrawer(Context context, AttributeSet attributeset, int i)
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
                int j = getWidth();
                int k = mMenuSize;
                int l = mActiveIndicator.getWidth();
                int i1 = j - k;
                mActiveView.getDrawingRect(mActiveRect);
                offsetDescendantRectToMyCoords(mActiveView, mActiveRect);
                int j1 = i1 + l;
                if (mIndicatorAnimating)
                {
                    int k1 = mActiveRect.top + (mActiveRect.height() - mActiveIndicator.getHeight()) / 2;
                    int l1 = mIndicatorStartPos;
                    b = l1 + (int)((float)(k1 - l1) * mIndicatorOffset);
                } else
                {
                    b = mActiveRect.top + (mActiveRect.height() - mActiveIndicator.getHeight()) / 2;
                }
                canvas.save();
                canvas.clipRect(i1, 0, j1, getHeight());
                canvas.drawBitmap(mActiveIndicator, i1, b, null);
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
        mPosition = Position.RIGHT;
    }

    public void setDropShadowColor(int i)
    {
        int j = 0xffffff & i;
        mDropShadowDrawable = new GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.LEFT_RIGHT, new int[] {
            i, j
        });
        invalidate();
    }
}
