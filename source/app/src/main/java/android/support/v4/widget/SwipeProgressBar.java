// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

// Referenced classes of package android.support.v4.widget:
//            BakedBezierInterpolator

final class SwipeProgressBar
{

    private static final int ANIMATION_DURATION_MS = 2000;
    private static final int COLOR1 = 0xb3000000;
    private static final int COLOR2 = 0x80000000;
    private static final int COLOR3 = 0x4d000000;
    private static final int COLOR4 = 0x1a000000;
    private static final int FINISH_ANIMATION_DURATION_MS = 1000;
    private static final Interpolator INTERPOLATOR = BakedBezierInterpolator.getInstance();
    private Rect mBounds;
    private final RectF mClipRect = new RectF();
    private int mColor1;
    private int mColor2;
    private int mColor3;
    private int mColor4;
    private long mFinishTime;
    private final Paint mPaint = new Paint();
    private View mParent;
    private boolean mRunning;
    private long mStartTime;
    private float mTriggerPercentage;

    public SwipeProgressBar(View view)
    {
        mBounds = new Rect();
        mParent = view;
        mColor1 = 0xb3000000;
        mColor2 = 0x80000000;
        mColor3 = 0x4d000000;
        mColor4 = 0x1a000000;
    }

    private void drawCircle(Canvas canvas, float f, float f1, int i, float f2)
    {
        mPaint.setColor(i);
        canvas.save();
        canvas.translate(f, f1);
        float f3 = INTERPOLATOR.getInterpolation(f2);
        canvas.scale(f3, f3);
        canvas.drawCircle(0.0F, 0.0F, f, mPaint);
        canvas.restore();
    }

    private void drawTrigger(Canvas canvas, int i, int j)
    {
        mPaint.setColor(mColor1);
        canvas.drawCircle(i, j, (float)i * mTriggerPercentage, mPaint);
    }

    final void draw(Canvas canvas)
    {
label0:
        {
            {
                int i = mBounds.width();
                int j = mBounds.height();
                int k = i / 2;
                int l = j / 2;
                int i1 = canvas.save();
                canvas.clipRect(mBounds);
                if (!mRunning && mFinishTime <= 0L)
                {
                    break label0;
                }
                long l1 = AnimationUtils.currentAnimationTimeMillis();
                long l2 = (l1 - mStartTime) % 2000L;
                long l3 = (l1 - mStartTime) / 2000L;
                float f = (float)l2 / 20F;
                boolean flag;
                int j1;
                if (!mRunning)
                {
                    if (l1 - mFinishTime >= 1000L)
                    {
                        mFinishTime = 0L;
                        return;
                    }
                    float f6 = (float)((l1 - mFinishTime) % 1000L) / 10F / 100F;
                    float f7 = (float)k * INTERPOLATOR.getInterpolation(f6);
                    mClipRect.set((float)k - f7, 0.0F, f7 + (float)k, j);
                    canvas.saveLayerAlpha(mClipRect, 0, 0);
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (l3 == 0L)
                {
                    canvas.drawColor(mColor1);
                } else
                if (f >= 0.0F && f < 25F)
                {
                    canvas.drawColor(mColor4);
                } else
                if (f >= 25F && f < 50F)
                {
                    canvas.drawColor(mColor1);
                } else
                if (f >= 50F && f < 75F)
                {
                    canvas.drawColor(mColor2);
                } else
                {
                    canvas.drawColor(mColor3);
                }
                if (f >= 0.0F && f <= 25F)
                {
                    float f5 = (2.0F * (25F + f)) / 100F;
                    drawCircle(canvas, k, l, mColor1, f5);
                }
                if (f >= 0.0F && f <= 50F)
                {
                    float f4 = (2.0F * f) / 100F;
                    drawCircle(canvas, k, l, mColor2, f4);
                }
                if (f >= 25F && f <= 75F)
                {
                    float f3 = (2.0F * (f - 25F)) / 100F;
                    drawCircle(canvas, k, l, mColor3, f3);
                }
                if (f >= 50F && f <= 100F)
                {
                    float f2 = (2.0F * (f - 50F)) / 100F;
                    drawCircle(canvas, k, l, mColor4, f2);
                }
                if (f >= 75F && f <= 100F)
                {
                    float f1 = (2.0F * (f - 75F)) / 100F;
                    drawCircle(canvas, k, l, mColor1, f1);
                }
                if (mTriggerPercentage > 0.0F && flag)
                {
                    canvas.restoreToCount(i1);
                    j1 = canvas.save();
                    canvas.clipRect(mBounds);
                    drawTrigger(canvas, k, l);
                } else
                {
                    j1 = i1;
                }
                ViewCompat.postInvalidateOnAnimation(mParent);
                i1 = j1;
            }
            canvas.restoreToCount(i1);
            return;
        }
        if (mTriggerPercentage > 0.0F && (double)mTriggerPercentage <= 1.0D)
        {
            drawTrigger(canvas, k, l);
        }
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_485;
        }
    }

    final boolean isRunning()
    {
        return mRunning || mFinishTime > 0L;
    }

    final void setBounds(int i, int j, int k, int l)
    {
        mBounds.left = i;
        mBounds.top = j;
        mBounds.right = k;
        mBounds.bottom = l;
    }

    final void setColorScheme(int i, int j, int k, int l)
    {
        mColor1 = i;
        mColor2 = j;
        mColor3 = k;
        mColor4 = l;
    }

    final void setTriggerPercentage(float f)
    {
        mTriggerPercentage = f;
        mStartTime = 0L;
        ViewCompat.postInvalidateOnAnimation(mParent);
    }

    final void start()
    {
        if (!mRunning)
        {
            mTriggerPercentage = 0.0F;
            mStartTime = AnimationUtils.currentAnimationTimeMillis();
            mRunning = true;
            mParent.postInvalidate();
        }
    }

    final void stop()
    {
        if (mRunning)
        {
            mTriggerPercentage = 0.0F;
            mFinishTime = AnimationUtils.currentAnimationTimeMillis();
            mRunning = false;
            mParent.postInvalidate();
        }
    }

}
