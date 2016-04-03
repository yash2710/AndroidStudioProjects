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

public class LeftStaticDrawer extends StaticDrawer
{

    private int b;

    LeftStaticDrawer(Activity activity, int i)
    {
        super(activity, i);
    }

    public LeftStaticDrawer(Context context)
    {
        super(context);
    }

    public LeftStaticDrawer(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public LeftStaticDrawer(Context context, AttributeSet attributeset, int i)
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
                mActiveView.getDrawingRect(mActiveRect);
                offsetDescendantRectToMyCoords(mActiveView, mActiveRect);
                int j;
                int k;
                if (mIndicatorAnimating)
                {
                    int l = mActiveRect.top + (mActiveRect.height() - mActiveIndicator.getHeight()) / 2;
                    int i1 = mIndicatorStartPos;
                    b = i1 + (int)((float)(l - i1) * mIndicatorOffset);
                } else
                {
                    b = mActiveRect.top + (mActiveRect.height() - mActiveIndicator.getHeight()) / 2;
                }
                j = mMenuSize;
                k = j - mActiveIndicator.getWidth();
                canvas.save();
                canvas.clipRect(k, 0, j, getHeight());
                canvas.drawBitmap(mActiveIndicator, k, b, null);
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
        mPosition = Position.LEFT;
    }

    public void setDropShadowColor(int i)
    {
        int j = 0xffffff & i;
        mDropShadowDrawable = new GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.RIGHT_LEFT, new int[] {
            i, j
        });
        invalidate();
    }
}
