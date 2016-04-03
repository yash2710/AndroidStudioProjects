// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class ProgressBarICS extends View
{

    private static final int ANIMATION_RESOLUTION = 200;
    private static final int MAX_LEVEL = 10000;
    private static final int android_R_styleable_ProgressBar[] = {
        0x1010136, 0x1010137, 0x1010138, 0x1010139, 0x101013a, 0x101013b, 0x101013c, 0x101013d, 0x101013e, 0x101013f, 
        0x101011f, 0x1010140, 0x1010120, 0x1010141
    };
    private AlphaAnimation mAnimation;
    private int mBehavior;
    private Drawable mCurrentDrawable;
    private int mDuration;
    private boolean mInDrawing;
    private boolean mIndeterminate;
    private Drawable mIndeterminateDrawable;
    private Interpolator mInterpolator;
    private long mLastDrawTime;
    private int mMax;
    int mMaxHeight;
    int mMaxWidth;
    int mMinHeight;
    int mMinWidth;
    private boolean mNoInvalidate;
    private boolean mOnlyIndeterminate;
    private int mProgress;
    private Drawable mProgressDrawable;
    private RefreshProgressRunnable mRefreshProgressRunnable;
    Bitmap mSampleTile;
    private int mSecondaryProgress;
    private boolean mShouldStartAnimationDrawable;
    private Transformation mTransformation;
    private long mUiThreadId;

    public ProgressBarICS(Context context, AttributeSet attributeset, int i, int j)
    {
        boolean flag1;
label0:
        {
            super(context, attributeset, i);
            mUiThreadId = Thread.currentThread().getId();
            initProgressBar();
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, android_R_styleable_ProgressBar, i, j);
            mNoInvalidate = true;
            setMax(typedarray.getInt(0, mMax));
            setProgress(typedarray.getInt(1, mProgress));
            setSecondaryProgress(typedarray.getInt(2, mSecondaryProgress));
            boolean flag = typedarray.getBoolean(3, mIndeterminate);
            mOnlyIndeterminate = typedarray.getBoolean(4, mOnlyIndeterminate);
            Drawable drawable = typedarray.getDrawable(5);
            if (drawable != null)
            {
                setIndeterminateDrawable(tileifyIndeterminate(drawable));
            }
            Drawable drawable1 = typedarray.getDrawable(6);
            if (drawable1 != null)
            {
                setProgressDrawable(tileify(drawable1, false));
            }
            mDuration = typedarray.getInt(7, mDuration);
            mBehavior = typedarray.getInt(8, mBehavior);
            mMinWidth = typedarray.getDimensionPixelSize(9, mMinWidth);
            mMaxWidth = typedarray.getDimensionPixelSize(10, mMaxWidth);
            mMinHeight = typedarray.getDimensionPixelSize(11, mMinHeight);
            mMaxHeight = typedarray.getDimensionPixelSize(12, mMaxHeight);
            int k = typedarray.getResourceId(13, 0x10a000b);
            if (k > 0)
            {
                setInterpolator(context, k);
            }
            typedarray.recycle();
            mNoInvalidate = false;
            if (!mOnlyIndeterminate)
            {
                flag1 = false;
                if (!flag)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        setIndeterminate(flag1);
    }

    private void doRefreshProgress(int i, int j, boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        if (mMax <= 0) goto _L2; else goto _L1
_L1:
        float f = (float)j / (float)mMax;
_L5:
        Drawable drawable = mCurrentDrawable;
        if (drawable == null) goto _L4; else goto _L3
_L3:
        boolean flag2 = drawable instanceof LayerDrawable;
        Drawable drawable1;
        drawable1 = null;
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_90;
        }
        drawable1 = ((LayerDrawable)drawable).findDrawableByLayerId(i);
        break MISSING_BLOCK_LABEL_90;
_L7:
        int k;
        drawable.setLevel(k);
_L6:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        f = 0.0F;
          goto _L5
_L4:
        invalidate();
          goto _L6
        Exception exception;
        exception;
        throw exception;
        k = (int)(f * 10000F);
        if (drawable1 != null)
        {
            drawable = drawable1;
        }
          goto _L7
    }

    private void initProgressBar()
    {
        mMax = 100;
        mProgress = 0;
        mSecondaryProgress = 0;
        mIndeterminate = false;
        mOnlyIndeterminate = false;
        mDuration = 4000;
        mBehavior = 1;
        mMinWidth = 24;
        mMaxWidth = 48;
        mMinHeight = 24;
        mMaxHeight = 48;
    }

    private void refreshProgress(int i, int j, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if (mUiThreadId != Thread.currentThread().getId()) goto _L2; else goto _L1
_L1:
        doRefreshProgress(i, j, flag, true);
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        RefreshProgressRunnable refreshprogressrunnable;
        if (mRefreshProgressRunnable == null)
        {
            break; /* Loop/switch isn't completed */
        }
        refreshprogressrunnable = mRefreshProgressRunnable;
        mRefreshProgressRunnable = null;
        refreshprogressrunnable.setup(i, j, flag);
_L5:
        post(refreshprogressrunnable);
        if (true) goto _L4; else goto _L3
        Exception exception;
        exception;
        throw exception;
_L3:
        refreshprogressrunnable = new RefreshProgressRunnable(i, j, flag);
          goto _L5
    }

    private Drawable tileify(Drawable drawable, boolean flag)
    {
        int i = 0;
        if (drawable instanceof LayerDrawable)
        {
            LayerDrawable layerdrawable = (LayerDrawable)drawable;
            int j = layerdrawable.getNumberOfLayers();
            Drawable adrawable[] = new Drawable[j];
            int k = 0;
            while (k < j) 
            {
                int l = layerdrawable.getId(k);
                Drawable drawable1 = layerdrawable.getDrawable(k);
                boolean flag1;
                if (l == 0x102000d || l == 0x102000f)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                adrawable[k] = tileify(drawable1, flag1);
                k++;
            }
            LayerDrawable layerdrawable1 = new LayerDrawable(adrawable);
            for (; i < j; i++)
            {
                layerdrawable1.setId(i, layerdrawable.getId(i));
            }

            drawable = layerdrawable1;
        } else
        if (drawable instanceof BitmapDrawable)
        {
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            if (mSampleTile == null)
            {
                mSampleTile = bitmap;
            }
            ShapeDrawable shapedrawable = new ShapeDrawable(getDrawableShape());
            BitmapShader bitmapshader = new BitmapShader(bitmap, android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.CLAMP);
            shapedrawable.getPaint().setShader(bitmapshader);
            if (flag)
            {
                return new ClipDrawable(shapedrawable, 3, 1);
            } else
            {
                return shapedrawable;
            }
        }
        return drawable;
    }

    private Drawable tileifyIndeterminate(Drawable drawable)
    {
        if (drawable instanceof AnimationDrawable)
        {
            AnimationDrawable animationdrawable = (AnimationDrawable)drawable;
            int i = animationdrawable.getNumberOfFrames();
            AnimationDrawable animationdrawable1 = new AnimationDrawable();
            animationdrawable1.setOneShot(animationdrawable.isOneShot());
            for (int j = 0; j < i; j++)
            {
                Drawable drawable1 = tileify(animationdrawable.getFrame(j), true);
                drawable1.setLevel(10000);
                animationdrawable1.addFrame(drawable1, animationdrawable.getDuration(j));
            }

            animationdrawable1.setLevel(10000);
            drawable = animationdrawable1;
        }
        return drawable;
    }

    private void updateDrawableBounds(int i, int j)
    {
        int k;
        int l;
        k = i - getPaddingRight() - getPaddingLeft();
        l = j - getPaddingBottom() - getPaddingTop();
        if (mIndeterminateDrawable == null)
        {
            break MISSING_BLOCK_LABEL_223;
        }
        if (!mOnlyIndeterminate || (mIndeterminateDrawable instanceof AnimationDrawable)) goto _L2; else goto _L1
_L1:
        float f;
        float f1;
        int i2 = mIndeterminateDrawable.getIntrinsicWidth();
        int j2 = mIndeterminateDrawable.getIntrinsicHeight();
        f = (float)i2 / (float)j2;
        f1 = (float)i / (float)j;
        if (f == f1) goto _L2; else goto _L3
_L3:
        int i1;
        int j1;
        int k1;
        int l1;
        if (f1 > f)
        {
            int j3 = (int)(f * (float)j);
            k1 = (i - j3) / 2;
            i1 = j3 + k1;
            j1 = l;
            l1 = 0;
        } else
        {
            int k2 = (int)((float)i * (1.0F / f));
            int l2 = (j - k2) / 2;
            int i3 = k2 + l2;
            i1 = k;
            j1 = i3;
            l1 = l2;
            k1 = 0;
        }
_L4:
        mIndeterminateDrawable.setBounds(k1, l1, i1, j1);
_L5:
        if (mProgressDrawable != null)
        {
            mProgressDrawable.setBounds(0, 0, i1, j1);
        }
        return;
_L2:
        i1 = k;
        j1 = l;
        k1 = 0;
        l1 = 0;
          goto _L4
        i1 = k;
        j1 = l;
          goto _L5
    }

    private void updateDrawableState()
    {
        int ai[] = getDrawableState();
        if (mProgressDrawable != null && mProgressDrawable.isStateful())
        {
            mProgressDrawable.setState(ai);
        }
        if (mIndeterminateDrawable != null && mIndeterminateDrawable.isStateful())
        {
            mIndeterminateDrawable.setState(ai);
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        updateDrawableState();
    }

    Shape getDrawableShape()
    {
        return new RoundRectShape(new float[] {
            5F, 5F, 5F, 5F, 5F, 5F, 5F, 5F
        }, null, null);
    }

    public Drawable getIndeterminateDrawable()
    {
        return mIndeterminateDrawable;
    }

    public Interpolator getInterpolator()
    {
        return mInterpolator;
    }

    public int getMax()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mMax;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getProgress()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIndeterminate;
        if (!flag) goto _L2; else goto _L1
_L1:
        int i = 0;
_L4:
        this;
        JVM INSTR monitorexit ;
        return i;
_L2:
        i = mProgress;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Drawable getProgressDrawable()
    {
        return mProgressDrawable;
    }

    public int getSecondaryProgress()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIndeterminate;
        if (!flag) goto _L2; else goto _L1
_L1:
        int i = 0;
_L4:
        this;
        JVM INSTR monitorexit ;
        return i;
_L2:
        i = mSecondaryProgress;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final void incrementProgressBy(int i)
    {
        this;
        JVM INSTR monitorenter ;
        setProgress(i + mProgress);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void incrementSecondaryProgressBy(int i)
    {
        this;
        JVM INSTR monitorenter ;
        setSecondaryProgress(i + mSecondaryProgress);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void invalidateDrawable(Drawable drawable)
    {
label0:
        {
            if (!mInDrawing)
            {
                if (!verifyDrawable(drawable))
                {
                    break label0;
                }
                Rect rect = drawable.getBounds();
                int i = getScrollX() + getPaddingLeft();
                int j = getScrollY() + getPaddingTop();
                invalidate(i + rect.left, j + rect.top, i + rect.right, j + rect.bottom);
            }
            return;
        }
        super.invalidateDrawable(drawable);
    }

    public boolean isIndeterminate()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIndeterminate;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (mIndeterminate)
        {
            startAnimation();
        }
    }

    protected void onDetachedFromWindow()
    {
        if (mIndeterminate)
        {
            stopAnimation();
        }
        if (mRefreshProgressRunnable != null)
        {
            removeCallbacks(mRefreshProgressRunnable);
        }
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas)
    {
        this;
        JVM INSTR monitorenter ;
        Drawable drawable;
        super.onDraw(canvas);
        drawable = mCurrentDrawable;
        if (drawable == null)
        {
            break MISSING_BLOCK_LABEL_158;
        }
        float f;
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        long l = getDrawingTime();
        if (mAnimation == null)
        {
            break MISSING_BLOCK_LABEL_121;
        }
        mAnimation.getTransformation(l, mTransformation);
        f = mTransformation.getAlpha();
        mInDrawing = true;
        drawable.setLevel((int)(f * 10000F));
        mInDrawing = false;
        if (SystemClock.uptimeMillis() - mLastDrawTime >= 200L)
        {
            mLastDrawTime = SystemClock.uptimeMillis();
            postInvalidateDelayed(200L);
        }
        drawable.draw(canvas);
        canvas.restore();
        if (mShouldStartAnimationDrawable && (drawable instanceof Animatable))
        {
            ((Animatable)drawable).start();
            mShouldStartAnimationDrawable = false;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception1;
        exception1;
        mInDrawing = false;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void onMeasure(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        Drawable drawable = mCurrentDrawable;
        if (drawable == null)
        {
            break MISSING_BLOCK_LABEL_111;
        }
        int k;
        int l;
        k = Math.max(mMinWidth, Math.min(mMaxWidth, drawable.getIntrinsicWidth()));
        l = Math.max(mMinHeight, Math.min(mMaxHeight, drawable.getIntrinsicHeight()));
_L1:
        updateDrawableState();
        int i1 = k + (getPaddingLeft() + getPaddingRight());
        int j1 = l + (getPaddingTop() + getPaddingBottom());
        setMeasuredDimension(resolveSize(i1, i), resolveSize(j1, j));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
        l = 0;
        k = 0;
          goto _L1
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        setProgress(savedstate.progress);
        setSecondaryProgress(savedstate.secondaryProgress);
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.progress = mProgress;
        savedstate.secondaryProgress = mSecondaryProgress;
        return savedstate;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        updateDrawableBounds(i, j);
    }

    protected void onVisibilityChanged(View view, int i)
    {
label0:
        {
            super.onVisibilityChanged(view, i);
            if (mIndeterminate)
            {
                if (i != 8 && i != 4)
                {
                    break label0;
                }
                stopAnimation();
            }
            return;
        }
        startAnimation();
    }

    public void postInvalidate()
    {
        if (!mNoInvalidate)
        {
            super.postInvalidate();
        }
    }

    public void setIndeterminate(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if (mOnlyIndeterminate && mIndeterminate || flag == mIndeterminate) goto _L2; else goto _L1
_L1:
        mIndeterminate = flag;
        if (!flag) goto _L4; else goto _L3
_L3:
        mCurrentDrawable = mIndeterminateDrawable;
        startAnimation();
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L4:
        mCurrentDrawable = mProgressDrawable;
        stopAnimation();
        if (true) goto _L2; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public void setIndeterminateDrawable(Drawable drawable)
    {
        if (drawable != null)
        {
            drawable.setCallback(this);
        }
        mIndeterminateDrawable = drawable;
        if (mIndeterminate)
        {
            mCurrentDrawable = drawable;
            postInvalidate();
        }
    }

    public void setInterpolator(Context context, int i)
    {
        setInterpolator(AnimationUtils.loadInterpolator(context, i));
    }

    public void setInterpolator(Interpolator interpolator)
    {
        mInterpolator = interpolator;
    }

    public void setMax(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if (i < 0)
        {
            i = 0;
        }
        if (i != mMax)
        {
            mMax = i;
            postInvalidate();
            if (mProgress > i)
            {
                mProgress = i;
            }
            refreshProgress(0x102000d, mProgress, false);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setProgress(int i)
    {
        this;
        JVM INSTR monitorenter ;
        setProgress(i, false);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void setProgress(int i, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag1 = mIndeterminate;
        if (!flag1) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Exception exception;
        int j;
        if (i < 0)
        {
            j = 0;
        } else
        {
            j = i;
        }
        if (j > mMax)
        {
            j = mMax;
        }
        if (j != mProgress)
        {
            mProgress = j;
            refreshProgress(0x102000d, mProgress, flag);
        }
        if (true) goto _L1; else goto _L3
_L3:
        exception;
        throw exception;
    }

    public void setProgressDrawable(Drawable drawable)
    {
        boolean flag;
        if (mProgressDrawable != null && drawable != mProgressDrawable)
        {
            mProgressDrawable.setCallback(null);
            flag = true;
        } else
        {
            flag = false;
        }
        if (drawable != null)
        {
            drawable.setCallback(this);
            int i = drawable.getMinimumHeight();
            if (mMaxHeight < i)
            {
                mMaxHeight = i;
                requestLayout();
            }
        }
        mProgressDrawable = drawable;
        if (!mIndeterminate)
        {
            mCurrentDrawable = drawable;
            postInvalidate();
        }
        if (flag)
        {
            updateDrawableBounds(getWidth(), getHeight());
            updateDrawableState();
            doRefreshProgress(0x102000d, mProgress, false, false);
            doRefreshProgress(0x102000f, mSecondaryProgress, false, false);
        }
    }

    public void setSecondaryProgress(int i)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIndeterminate;
        if (!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        int j = 0;
        Exception exception;
        if (i >= 0)
        {
            j = i;
        }
        if (j > mMax)
        {
            j = mMax;
        }
        if (j != mSecondaryProgress)
        {
            mSecondaryProgress = j;
            refreshProgress(0x102000f, mSecondaryProgress, false);
        }
        if (true) goto _L1; else goto _L3
_L3:
        exception;
        throw exception;
    }

    public void setVisibility(int i)
    {
label0:
        {
            if (getVisibility() != i)
            {
                super.setVisibility(i);
                if (mIndeterminate)
                {
                    if (i != 8 && i != 4)
                    {
                        break label0;
                    }
                    stopAnimation();
                }
            }
            return;
        }
        startAnimation();
    }

    void startAnimation()
    {
        if (getVisibility() != 0)
        {
            return;
        }
        if (mIndeterminateDrawable instanceof Animatable)
        {
            mShouldStartAnimationDrawable = true;
            mAnimation = null;
        } else
        {
            if (mInterpolator == null)
            {
                mInterpolator = new LinearInterpolator();
            }
            mTransformation = new Transformation();
            mAnimation = new AlphaAnimation(0.0F, 1.0F);
            mAnimation.setRepeatMode(mBehavior);
            mAnimation.setRepeatCount(-1);
            mAnimation.setDuration(mDuration);
            mAnimation.setInterpolator(mInterpolator);
            mAnimation.setStartTime(-1L);
        }
        postInvalidate();
    }

    void stopAnimation()
    {
        mAnimation = null;
        mTransformation = null;
        if (mIndeterminateDrawable instanceof Animatable)
        {
            ((Animatable)mIndeterminateDrawable).stop();
            mShouldStartAnimationDrawable = false;
        }
        postInvalidate();
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return drawable == mProgressDrawable || drawable == mIndeterminateDrawable || super.verifyDrawable(drawable);
    }




/*
    static RefreshProgressRunnable access$102(ProgressBarICS progressbarics, RefreshProgressRunnable refreshprogressrunnable)
    {
        progressbarics.mRefreshProgressRunnable = refreshprogressrunnable;
        return refreshprogressrunnable;
    }

*/

    private class RefreshProgressRunnable
        implements Runnable
    {

        private boolean mFromUser;
        private int mId;
        private int mProgress;
        final ProgressBarICS this$0;

        public void run()
        {
            doRefreshProgress(mId, mProgress, mFromUser, true);
            mRefreshProgressRunnable = this;
        }

        public void setup(int i, int j, boolean flag)
        {
            mId = i;
            mProgress = j;
            mFromUser = flag;
        }

        RefreshProgressRunnable(int i, int j, boolean flag)
        {
            this$0 = ProgressBarICS.this;
            super();
            mId = i;
            mProgress = j;
            mFromUser = flag;
        }
    }


    private class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        int progress;
        int secondaryProgress;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(progress);
            parcel.writeInt(secondaryProgress);
        }


        private SavedState(Parcel parcel)
        {
            super(parcel);
            progress = parcel.readInt();
            secondaryProgress = parcel.readInt();
        }

        SavedState(Parcel parcel, _cls1 _pcls1)
        {
            this(parcel);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }

        class _cls1
            implements android.os.Parcelable.Creator
        {

            public final SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public final volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public final SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public final volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

                _cls1()
                {
                }
        }

    }

}
