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

public class TopStaticDrawer extends StaticDrawer
{

    private int b;

    TopStaticDrawer(Activity activity, int i)
    {
        super(activity, i);
    }

    public TopStaticDrawer(Context context)
    {
        super(context);
    }

    public TopStaticDrawer(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public TopStaticDrawer(Context context, AttributeSet attributeset, int i)
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
                int j = mMenuSize;
                int k = mActiveIndicator.getHeight();
                mActiveView.getDrawingRect(mActiveRect);
                offsetDescendantRectToMyCoords(mActiveView, mActiveRect);
                int l = mActiveIndicator.getWidth();
                int i1 = j - k;
                if (mIndicatorAnimating)
                {
                    int j1 = mActiveRect.left + (mActiveRect.width() - l) / 2;
                    int k1 = mIndicatorStartPos;
                    b = k1 + (int)((float)(j1 - k1) * mIndicatorOffset);
                } else
                {
                    b = mActiveRect.left + (mActiveRect.width() - l) / 2;
                }
                canvas.save();
                canvas.clipRect(b, i1, l + b, j);
                canvas.drawBitmap(mActiveIndicator, b, i1, null);
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
        mPosition = Position.TOP;
    }

    public void setDropShadowColor(int i)
    {
        int j = 0xffffff & i;
        mDropShadowDrawable = new GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP, new int[] {
            i, j
        });
        invalidate();
    }
}
