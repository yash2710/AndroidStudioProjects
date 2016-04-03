// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.widget:
//            ViewDragHelper

public class SlidingPaneLayout extends ViewGroup
{

    private static final int DEFAULT_FADE_COLOR = 0xcccccccc;
    private static final int DEFAULT_OVERHANG_SIZE = 32;
    static final SlidingPanelLayoutImpl IMPL;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    private final ArrayList mPostedRunnables;
    private boolean mPreservedOpenState;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    private float mSlideOffset;
    private int mSlideRange;
    private View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;

    public SlidingPaneLayout(Context context)
    {
        this(context, null);
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mSliderFadeColor = 0xcccccccc;
        mFirstLayout = true;
        mTmpRect = new Rect();
        mPostedRunnables = new ArrayList();
        float f = context.getResources().getDisplayMetrics().density;
        mOverhangSize = (int)(0.5F + 32F * f);
        ViewConfiguration.get(context);
        setWillNotDraw(false);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        ViewCompat.setImportantForAccessibility(this, 1);
        mDragHelper = ViewDragHelper.create(this, 0.5F, new DragHelperCallback(null));
        mDragHelper.setMinVelocity(f * 400F);
    }

    private boolean closePane(View view, int i)
    {
        boolean flag;
label0:
        {
            if (!mFirstLayout)
            {
                boolean flag1 = smoothSlideTo(0.0F, i);
                flag = false;
                if (!flag1)
                {
                    break label0;
                }
            }
            mPreservedOpenState = false;
            flag = true;
        }
        return flag;
    }

    private void dimChildView(View view, float f, int i)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (f > 0.0F && i != 0)
        {
            int j = (int)(f * (float)((0xff000000 & i) >>> 24)) << 24 | 0xffffff & i;
            if (layoutparams.dimPaint == null)
            {
                layoutparams.dimPaint = new Paint();
            }
            layoutparams.dimPaint.setColorFilter(new PorterDuffColorFilter(j, android.graphics.PorterDuff.Mode.SRC_OVER));
            if (ViewCompat.getLayerType(view) != 2)
            {
                ViewCompat.setLayerType(view, 2, layoutparams.dimPaint);
            }
            invalidateChildRegion(view);
        } else
        if (ViewCompat.getLayerType(view) != 0)
        {
            if (layoutparams.dimPaint != null)
            {
                layoutparams.dimPaint.setColorFilter(null);
            }
            DisableLayerRunnable disablelayerrunnable = new DisableLayerRunnable(view);
            mPostedRunnables.add(disablelayerrunnable);
            ViewCompat.postOnAnimation(this, disablelayerrunnable);
            return;
        }
    }

    private void invalidateChildRegion(View view)
    {
        IMPL.invalidateChildRegion(this, view);
    }

    private boolean isLayoutRtlSupport()
    {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    private void onPanelDragged(int i)
    {
        if (mSlideableView == null)
        {
            mSlideOffset = 0.0F;
            return;
        }
        boolean flag = isLayoutRtlSupport();
        LayoutParams layoutparams = (LayoutParams)mSlideableView.getLayoutParams();
        int j = mSlideableView.getWidth();
        if (flag)
        {
            i = getWidth() - i - j;
        }
        int k;
        int l;
        if (flag)
        {
            k = getPaddingRight();
        } else
        {
            k = getPaddingLeft();
        }
        if (flag)
        {
            l = layoutparams.rightMargin;
        } else
        {
            l = layoutparams.leftMargin;
        }
        mSlideOffset = (float)(i - (l + k)) / (float)mSlideRange;
        if (mParallaxBy != 0)
        {
            parallaxOtherViews(mSlideOffset);
        }
        if (layoutparams.dimWhenOffset)
        {
            dimChildView(mSlideableView, mSlideOffset, mSliderFadeColor);
        }
        dispatchOnPanelSlide(mSlideableView);
    }

    private boolean openPane(View view, int i)
    {
        if (mFirstLayout || smoothSlideTo(1.0F, i))
        {
            mPreservedOpenState = true;
            return true;
        } else
        {
            return false;
        }
    }

    private void parallaxOtherViews(float f)
    {
        boolean flag1;
        boolean flag = isLayoutRtlSupport();
        LayoutParams layoutparams = (LayoutParams)mSlideableView.getLayoutParams();
        if (!layoutparams.dimWhenOffset)
        {
            break MISSING_BLOCK_LABEL_169;
        }
        int i;
        int j;
        View view;
        int k;
        int l;
        int i1;
        if (flag)
        {
            i1 = layoutparams.rightMargin;
        } else
        {
            i1 = layoutparams.leftMargin;
        }
        if (i1 > 0)
        {
            break MISSING_BLOCK_LABEL_169;
        }
        flag1 = true;
_L1:
        i = getChildCount();
        j = 0;
        while (j < i) 
        {
            view = getChildAt(j);
            if (view != mSlideableView)
            {
                k = (int)((1.0F - mParallaxOffset) * (float)mParallaxBy);
                mParallaxOffset = f;
                l = k - (int)((1.0F - f) * (float)mParallaxBy);
                if (flag)
                {
                    l = -l;
                }
                view.offsetLeftAndRight(l);
                if (flag1)
                {
                    float f1;
                    if (flag)
                    {
                        f1 = mParallaxOffset - 1.0F;
                    } else
                    {
                        f1 = 1.0F - mParallaxOffset;
                    }
                    dimChildView(view, f1, mCoveredFadeColor);
                }
            }
            j++;
        }
        break MISSING_BLOCK_LABEL_186;
        flag1 = false;
          goto _L1
    }

    private static boolean viewIsOpaque(View view)
    {
        if (!ViewCompat.isOpaque(view))
        {
            if (android.os.Build.VERSION.SDK_INT >= 18)
            {
                return false;
            }
            Drawable drawable = view.getBackground();
            if (drawable != null)
            {
                if (drawable.getOpacity() != -1)
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    protected boolean canScroll(View view, boolean flag, int i, int j, int k)
    {
        if (!(view instanceof ViewGroup)) goto _L2; else goto _L1
_L1:
        ViewGroup viewgroup;
        int l;
        int i1;
        int j1;
        viewgroup = (ViewGroup)view;
        l = view.getScrollX();
        i1 = view.getScrollY();
        j1 = -1 + viewgroup.getChildCount();
_L4:
        if (j1 < 0) goto _L2; else goto _L3
_L3:
        View view1 = viewgroup.getChildAt(j1);
        if (j + l < view1.getLeft() || j + l >= view1.getRight() || k + i1 < view1.getTop() || k + i1 >= view1.getBottom())
        {
            continue; /* Loop/switch isn't completed */
        }
        int k1 = (j + l) - view1.getLeft();
        int l1 = (k + i1) - view1.getTop();
        if (!canScroll(view1, true, i, k1, l1))
        {
            continue; /* Loop/switch isn't completed */
        }
_L6:
        return true;
        j1--;
          goto _L4
_L2:
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!isLayoutRtlSupport())
        {
            i = -i;
        }
        if (ViewCompat.canScrollHorizontally(view, i)) goto _L6; else goto _L5
_L5:
        return false;
    }

    public boolean canSlide()
    {
        return mCanSlide;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return (layoutparams instanceof LayoutParams) && super.checkLayoutParams(layoutparams);
    }

    public boolean closePane()
    {
        return closePane(mSlideableView, 0);
    }

    public void computeScroll()
    {
label0:
        {
            if (mDragHelper.continueSettling(true))
            {
                if (mCanSlide)
                {
                    break label0;
                }
                mDragHelper.abort();
            }
            return;
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    void dispatchOnPanelClosed(View view)
    {
        if (mPanelSlideListener != null)
        {
            mPanelSlideListener.onPanelClosed(view);
        }
        sendAccessibilityEvent(32);
    }

    void dispatchOnPanelOpened(View view)
    {
        if (mPanelSlideListener != null)
        {
            mPanelSlideListener.onPanelOpened(view);
        }
        sendAccessibilityEvent(32);
    }

    void dispatchOnPanelSlide(View view)
    {
        if (mPanelSlideListener != null)
        {
            mPanelSlideListener.onPanelSlide(view, mSlideOffset);
        }
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        Drawable drawable;
        View view;
        if (isLayoutRtlSupport())
        {
            drawable = mShadowDrawableRight;
        } else
        {
            drawable = mShadowDrawableLeft;
        }
        if (getChildCount() > 1)
        {
            view = getChildAt(1);
        } else
        {
            view = null;
        }
        if (view == null || drawable == null)
        {
            return;
        }
        int i = view.getTop();
        int j = view.getBottom();
        int k = drawable.getIntrinsicWidth();
        int l;
        int i1;
        if (isLayoutRtlSupport())
        {
            i1 = view.getRight();
            l = i1 + k;
        } else
        {
            l = view.getLeft();
            i1 = l - k;
        }
        drawable.setBounds(i1, i, l, j);
        drawable.draw(canvas);
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        boolean flag;
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        int i = canvas.save(2);
        if (mCanSlide && !layoutparams.slideable && mSlideableView != null)
        {
            canvas.getClipBounds(mTmpRect);
            android.graphics.Bitmap bitmap;
            if (isLayoutRtlSupport())
            {
                mTmpRect.left = Math.max(mTmpRect.left, mSlideableView.getRight());
            } else
            {
                mTmpRect.right = Math.min(mTmpRect.right, mSlideableView.getLeft());
            }
            canvas.clipRect(mTmpRect);
        }
        if (android.os.Build.VERSION.SDK_INT >= 11) goto _L2; else goto _L1
_L1:
        if (!layoutparams.dimWhenOffset || mSlideOffset <= 0.0F) goto _L4; else goto _L3
_L3:
        if (!view.isDrawingCacheEnabled())
        {
            view.setDrawingCacheEnabled(true);
        }
        bitmap = view.getDrawingCache();
        if (bitmap != null)
        {
            canvas.drawBitmap(bitmap, view.getLeft(), view.getTop(), layoutparams.dimPaint);
            flag = false;
        } else
        {
            Log.e("SlidingPaneLayout", (new StringBuilder("drawChild: child view ")).append(view).append(" returned null drawing cache").toString());
            flag = super.drawChild(canvas, view, l);
        }
_L6:
        canvas.restoreToCount(i);
        return flag;
_L4:
        if (view.isDrawingCacheEnabled())
        {
            view.setDrawingCacheEnabled(false);
        }
_L2:
        flag = super.drawChild(canvas, view, l);
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams();
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
        {
            return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        } else
        {
            return new LayoutParams(layoutparams);
        }
    }

    public int getCoveredFadeColor()
    {
        return mCoveredFadeColor;
    }

    public int getParallaxDistance()
    {
        return mParallaxBy;
    }

    public int getSliderFadeColor()
    {
        return mSliderFadeColor;
    }

    boolean isDimmed(View view)
    {
        if (view == null)
        {
            return false;
        }
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        return mCanSlide && layoutparams.dimWhenOffset && mSlideOffset > 0.0F;
    }

    public boolean isOpen()
    {
        return !mCanSlide || mSlideOffset == 1.0F;
    }

    public boolean isSlideable()
    {
        return mCanSlide;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mFirstLayout = true;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mFirstLayout = true;
        int i = mPostedRunnables.size();
        for (int j = 0; j < i; j++)
        {
            ((DisableLayerRunnable)mPostedRunnables.get(j)).run();
        }

        mPostedRunnables.clear();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag;
        i = MotionEventCompat.getActionMasked(motionevent);
        if (!mCanSlide && i == 0 && getChildCount() > 1)
        {
            View view = getChildAt(1);
            if (view != null)
            {
                boolean flag2;
                if (!mDragHelper.isViewUnder(view, (int)motionevent.getX(), (int)motionevent.getY()))
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                mPreservedOpenState = flag2;
            }
        }
        if (mCanSlide && (!mIsUnableToDrag || i == 0)) goto _L2; else goto _L1
_L1:
        mDragHelper.cancel();
        flag = super.onInterceptTouchEvent(motionevent);
_L7:
        return flag;
_L2:
        if (i == 3 || i == 1)
        {
            mDragHelper.cancel();
            return false;
        }
        i;
        JVM INSTR tableswitch 0 2: default 152
    //                   0 175
    //                   1 152
    //                   2 241;
           goto _L3 _L4 _L3 _L5
_L3:
        boolean flag1 = false;
_L9:
        if (mDragHelper.shouldInterceptTouchEvent(motionevent))
        {
            break; /* Loop/switch isn't completed */
        }
        flag = false;
        if (!flag1) goto _L7; else goto _L6
_L6:
        return true;
_L4:
        float f4;
        float f5;
        mIsUnableToDrag = false;
        f4 = motionevent.getX();
        f5 = motionevent.getY();
        mInitialMotionX = f4;
        mInitialMotionY = f5;
        if (!mDragHelper.isViewUnder(mSlideableView, (int)f4, (int)f5) || !isDimmed(mSlideableView)) goto _L3; else goto _L8
_L8:
        flag1 = true;
          goto _L9
_L5:
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        float f2 = Math.abs(f - mInitialMotionX);
        float f3 = Math.abs(f1 - mInitialMotionY);
        if (f2 > (float)mDragHelper.getTouchSlop() && f3 > f2)
        {
            mDragHelper.cancel();
            mIsUnableToDrag = true;
            return false;
        }
          goto _L3
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        boolean flag1 = isLayoutRtlSupport();
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int k2;
        if (flag1)
        {
            mDragHelper.setEdgeTrackingEnabled(2);
        } else
        {
            mDragHelper.setEdgeTrackingEnabled(1);
        }
        i1 = k - i;
        if (flag1)
        {
            j1 = getPaddingRight();
        } else
        {
            j1 = getPaddingLeft();
        }
        if (flag1)
        {
            k1 = getPaddingLeft();
        } else
        {
            k1 = getPaddingRight();
        }
        l1 = getPaddingTop();
        i2 = getChildCount();
        if (mFirstLayout)
        {
            int j2;
            View view;
            int i5;
            int j5;
            int l5;
            float f;
            if (mCanSlide && mPreservedOpenState)
            {
                f = 1.0F;
            } else
            {
                f = 0.0F;
            }
            mSlideOffset = f;
        }
        j2 = 0;
        k2 = j1;
        while (j2 < i2) 
        {
            view = getChildAt(j2);
            int i3;
            int j3;
            if (view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                int k3 = view.getMeasuredWidth();
                int l3 = 0;
                int j4;
                int k4;
                int l4;
                if (layoutparams.slideable)
                {
                    i5 = layoutparams.leftMargin + layoutparams.rightMargin;
                    j5 = Math.min(j1, i1 - k1 - mOverhangSize) - k2 - i5;
                    mSlideRange = j5;
                    int k5;
                    boolean flag2;
                    if (flag1)
                    {
                        k5 = layoutparams.rightMargin;
                    } else
                    {
                        k5 = layoutparams.leftMargin;
                    }
                    if (j5 + (k2 + k5) + k3 / 2 > i1 - k1)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    layoutparams.dimWhenOffset = flag2;
                    l5 = (int)((float)j5 * mSlideOffset);
                    j4 = k2 + (k5 + l5);
                    mSlideOffset = (float)l5 / (float)mSlideRange;
                } else
                {
                    int l2;
                    int i4;
                    if (mCanSlide && mParallaxBy != 0)
                    {
                        i4 = (int)((1.0F - mSlideOffset) * (float)mParallaxBy);
                    } else
                    {
                        i4 = 0;
                    }
                    l3 = i4;
                    j4 = j1;
                }
                if (flag1)
                {
                    l4 = l3 + (i1 - j4);
                    k4 = l4 - k3;
                } else
                {
                    k4 = j4 - l3;
                    l4 = k4 + k3;
                }
                view.layout(k4, l1, l4, l1 + view.getMeasuredHeight());
                i3 = j1 + view.getWidth();
                j3 = j4;
            } else
            {
                i3 = j1;
                j3 = k2;
            }
            j2++;
            j1 = i3;
            k2 = j3;
        }
        if (mFirstLayout)
        {
            if (mCanSlide)
            {
                if (mParallaxBy != 0)
                {
                    parallaxOtherViews(mSlideOffset);
                }
                if (((LayoutParams)mSlideableView.getLayoutParams()).dimWhenOffset)
                {
                    dimChildView(mSlideableView, mSlideOffset, mSliderFadeColor);
                }
            } else
            {
                l2 = 0;
                while (l2 < i2) 
                {
                    dimChildView(getChildAt(l2), 0.0F, mSliderFadeColor);
                    l2++;
                }
            }
            updateObscuredViewsVisibility(mSlideableView);
        }
        mFirstLayout = false;
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        int l;
        int i1;
        int j1;
        k = android.view.View.MeasureSpec.getMode(i);
        l = android.view.View.MeasureSpec.getSize(i);
        i1 = android.view.View.MeasureSpec.getMode(j);
        j1 = android.view.View.MeasureSpec.getSize(j);
        if (k == 0x40000000) goto _L2; else goto _L1
_L1:
        if (!isInEditMode()) goto _L4; else goto _L3
_L3:
        if (k == 0x80000000 || k != 0) goto _L6; else goto _L5
_L5:
        int k1;
        int l1;
        int i2;
        k1 = i1;
        l1 = 300;
        i2 = j1;
_L21:
        k1;
        JVM INSTR lookupswitch 2: default 88
    //                   -2147483648: 307
    //                   1073741824: 286;
           goto _L7 _L8 _L9
_L7:
        int j2;
        int k2;
        k2 = 0;
        j2 = -1;
_L15:
        boolean flag;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        float f;
        flag = false;
        l2 = l1 - getPaddingLeft() - getPaddingRight();
        i3 = getChildCount();
        if (i3 > 2)
        {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        mSlideableView = null;
        j3 = 0;
        k3 = l2;
        l3 = k2;
        f = 0.0F;
_L14:
        if (j3 >= i3) goto _L11; else goto _L10
_L10:
        View view1;
        LayoutParams layoutparams1;
        view1 = getChildAt(j3);
        layoutparams1 = (LayoutParams)view1.getLayoutParams();
        if (view1.getVisibility() != 8) goto _L13; else goto _L12
_L12:
        int k7;
        float f1;
        boolean flag4;
        int l7;
        layoutparams1.dimWhenOffset = false;
        k7 = k3;
        l7 = l3;
        f1 = f;
        flag4 = flag;
_L19:
        j3++;
        flag = flag4;
        l3 = l7;
        k3 = k7;
        f = f1;
          goto _L14
_L4:
        throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
_L2:
        if (i1 == 0)
        {
            if (isInEditMode())
            {
                if (i1 == 0)
                {
                    k1 = 0x80000000;
                    l1 = l;
                    i2 = 300;
                    continue; /* Loop/switch isn't completed */
                }
            } else
            {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            }
        }
          goto _L6
_L9:
        k2 = i2 - getPaddingTop() - getPaddingBottom();
        j2 = k2;
          goto _L15
_L8:
        j2 = i2 - getPaddingTop() - getPaddingBottom();
        k2 = 0;
          goto _L15
_L13:
        if (layoutparams1.weight <= 0.0F) goto _L17; else goto _L16
_L16:
        f += layoutparams1.weight;
        if (layoutparams1.width == 0) goto _L18; else goto _L17
_L17:
        int i6 = layoutparams1.leftMargin + layoutparams1.rightMargin;
        int j6;
        int k6;
        int l6;
        int i7;
        int j7;
        boolean flag2;
        boolean flag3;
        if (layoutparams1.width == -2)
        {
            j6 = android.view.View.MeasureSpec.makeMeasureSpec(l2 - i6, 0x80000000);
        } else
        if (layoutparams1.width == -1)
        {
            j6 = android.view.View.MeasureSpec.makeMeasureSpec(l2 - i6, 0x40000000);
        } else
        {
            j6 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams1.width, 0x40000000);
        }
        if (layoutparams1.height == -2)
        {
            k6 = android.view.View.MeasureSpec.makeMeasureSpec(j2, 0x80000000);
        } else
        if (layoutparams1.height == -1)
        {
            k6 = android.view.View.MeasureSpec.makeMeasureSpec(j2, 0x40000000);
        } else
        {
            k6 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams1.height, 0x40000000);
        }
        view1.measure(j6, k6);
        l6 = view1.getMeasuredWidth();
        i7 = view1.getMeasuredHeight();
        if (k1 == 0x80000000 && i7 > l3)
        {
            l3 = Math.min(i7, j2);
        }
        j7 = k3 - l6;
        if (j7 < 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        layoutparams1.slideable = flag2;
        flag3 = flag2 | flag;
        if (layoutparams1.slideable)
        {
            mSlideableView = view1;
        }
        k7 = j7;
        f1 = f;
        flag4 = flag3;
        l7 = l3;
          goto _L19
_L11:
        if (flag || f > 0.0F)
        {
            int i4 = l2 - mOverhangSize;
            int j4 = 0;
            while (j4 < i3) 
            {
                View view = getChildAt(j4);
                if (view.getVisibility() == 8)
                {
                    continue;
                }
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                if (view.getVisibility() != 8)
                {
                    boolean flag1;
                    int k4;
                    if (layoutparams.width == 0 && layoutparams.weight > 0.0F)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (flag1)
                    {
                        k4 = 0;
                    } else
                    {
                        k4 = view.getMeasuredWidth();
                    }
                    if (flag && view != mSlideableView)
                    {
                        if (layoutparams.width < 0 && (k4 > i4 || layoutparams.weight > 0.0F))
                        {
                            int l5;
                            if (flag1)
                            {
                                if (layoutparams.height == -2)
                                {
                                    l5 = android.view.View.MeasureSpec.makeMeasureSpec(j2, 0x80000000);
                                } else
                                if (layoutparams.height == -1)
                                {
                                    l5 = android.view.View.MeasureSpec.makeMeasureSpec(j2, 0x40000000);
                                } else
                                {
                                    l5 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.height, 0x40000000);
                                }
                            } else
                            {
                                l5 = android.view.View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0x40000000);
                            }
                            view.measure(android.view.View.MeasureSpec.makeMeasureSpec(i4, 0x40000000), l5);
                        }
                    } else
                    if (layoutparams.weight > 0.0F)
                    {
                        int l4;
                        if (layoutparams.width == 0)
                        {
                            if (layoutparams.height == -2)
                            {
                                l4 = android.view.View.MeasureSpec.makeMeasureSpec(j2, 0x80000000);
                            } else
                            if (layoutparams.height == -1)
                            {
                                l4 = android.view.View.MeasureSpec.makeMeasureSpec(j2, 0x40000000);
                            } else
                            {
                                l4 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.height, 0x40000000);
                            }
                        } else
                        {
                            l4 = android.view.View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0x40000000);
                        }
                        if (flag)
                        {
                            int j5 = l2 - (layoutparams.leftMargin + layoutparams.rightMargin);
                            int k5 = android.view.View.MeasureSpec.makeMeasureSpec(j5, 0x40000000);
                            if (k4 != j5)
                            {
                                view.measure(k5, l4);
                            }
                        } else
                        {
                            int i5 = Math.max(0, k3);
                            view.measure(android.view.View.MeasureSpec.makeMeasureSpec(k4 + (int)((layoutparams.weight * (float)i5) / f), 0x40000000), l4);
                        }
                    }
                }
                j4++;
            }
        }
        setMeasuredDimension(l1, l3 + getPaddingTop() + getPaddingBottom());
        mCanSlide = flag;
        if (mDragHelper.getViewDragState() != 0 && !flag)
        {
            mDragHelper.abort();
        }
        return;
_L18:
        k7 = k3;
        l7 = l3;
        f1 = f;
        flag4 = flag;
          goto _L19
_L6:
        k1 = i1;
        l1 = l;
        i2 = j1;
        if (true) goto _L21; else goto _L20
_L20:
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        if (savedstate.isOpen)
        {
            openPane();
        } else
        {
            closePane();
        }
        mPreservedOpenState = savedstate.isOpen;
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        boolean flag;
        if (isSlideable())
        {
            flag = isOpen();
        } else
        {
            flag = mPreservedOpenState;
        }
        savedstate.isOpen = flag;
        return savedstate;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if (i != k)
        {
            mFirstLayout = true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (!mCanSlide)
        {
            return super.onTouchEvent(motionevent);
        }
        mDragHelper.processTouchEvent(motionevent);
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 0 1: default 52
    //                   0 54
    //                   1 81;
           goto _L1 _L2 _L3
_L1:
        return true;
_L2:
        float f4 = motionevent.getX();
        float f5 = motionevent.getY();
        mInitialMotionX = f4;
        mInitialMotionY = f5;
        continue; /* Loop/switch isn't completed */
_L3:
        if (isDimmed(mSlideableView))
        {
            float f = motionevent.getX();
            float f1 = motionevent.getY();
            float f2 = f - mInitialMotionX;
            float f3 = f1 - mInitialMotionY;
            int i = mDragHelper.getTouchSlop();
            if (f2 * f2 + f3 * f3 < (float)(i * i) && mDragHelper.isViewUnder(mSlideableView, (int)f, (int)f1))
            {
                closePane(mSlideableView, 0);
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public boolean openPane()
    {
        return openPane(mSlideableView, 0);
    }

    public void requestChildFocus(View view, View view1)
    {
        super.requestChildFocus(view, view1);
        if (!isInTouchMode() && !mCanSlide)
        {
            boolean flag;
            if (view == mSlideableView)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mPreservedOpenState = flag;
        }
    }

    void setAllChildrenVisible()
    {
        int i = getChildCount();
        for (int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if (view.getVisibility() == 4)
            {
                view.setVisibility(0);
            }
        }

    }

    public void setCoveredFadeColor(int i)
    {
        mCoveredFadeColor = i;
    }

    public void setPanelSlideListener(PanelSlideListener panelslidelistener)
    {
        mPanelSlideListener = panelslidelistener;
    }

    public void setParallaxDistance(int i)
    {
        mParallaxBy = i;
        requestLayout();
    }

    public void setShadowDrawable(Drawable drawable)
    {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(Drawable drawable)
    {
        mShadowDrawableLeft = drawable;
    }

    public void setShadowDrawableRight(Drawable drawable)
    {
        mShadowDrawableRight = drawable;
    }

    public void setShadowResource(int i)
    {
        setShadowDrawable(getResources().getDrawable(i));
    }

    public void setShadowResourceLeft(int i)
    {
        setShadowDrawableLeft(getResources().getDrawable(i));
    }

    public void setShadowResourceRight(int i)
    {
        setShadowDrawableRight(getResources().getDrawable(i));
    }

    public void setSliderFadeColor(int i)
    {
        mSliderFadeColor = i;
    }

    public void smoothSlideClosed()
    {
        closePane();
    }

    public void smoothSlideOpen()
    {
        openPane();
    }

    boolean smoothSlideTo(float f, int i)
    {
        if (!mCanSlide)
        {
            return false;
        }
        boolean flag = isLayoutRtlSupport();
        LayoutParams layoutparams = (LayoutParams)mSlideableView.getLayoutParams();
        int j;
        if (flag)
        {
            int k = getPaddingRight() + layoutparams.rightMargin;
            int l = mSlideableView.getWidth();
            j = (int)((float)getWidth() - ((float)k + f * (float)mSlideRange + (float)l));
        } else
        {
            j = (int)((float)(getPaddingLeft() + layoutparams.leftMargin) + f * (float)mSlideRange);
        }
        if (mDragHelper.smoothSlideViewTo(mSlideableView, j, mSlideableView.getTop()))
        {
            setAllChildrenVisible();
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        } else
        {
            return false;
        }
    }

    void updateObscuredViewsVisibility(View view)
    {
        boolean flag = isLayoutRtlSupport();
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        if (flag)
        {
            i = getWidth() - getPaddingRight();
        } else
        {
            i = getPaddingLeft();
        }
        if (flag)
        {
            j = getPaddingLeft();
        } else
        {
            j = getWidth() - getPaddingRight();
        }
        k = getPaddingTop();
        l = getHeight() - getPaddingBottom();
        if (view != null && viewIsOpaque(view))
        {
            l1 = view.getLeft();
            k1 = view.getRight();
            j1 = view.getTop();
            i1 = view.getBottom();
        } else
        {
            i1 = 0;
            j1 = 0;
            k1 = 0;
            l1 = 0;
        }
        i2 = getChildCount();
        j2 = 0;
        do
        {
            if (j2 >= i2)
            {
                break;
            }
            View view1 = getChildAt(j2);
            if (view1 == view)
            {
                break;
            }
            int k2;
            int l2;
            int i3;
            int j3;
            int k3;
            int l3;
            byte byte0;
            if (flag)
            {
                k2 = j;
            } else
            {
                k2 = i;
            }
            l2 = Math.max(k2, view1.getLeft());
            i3 = Math.max(k, view1.getTop());
            if (flag)
            {
                j3 = i;
            } else
            {
                j3 = j;
            }
            k3 = Math.min(j3, view1.getRight());
            l3 = Math.min(l, view1.getBottom());
            if (l2 >= l1 && i3 >= j1 && k3 <= k1 && l3 <= i1)
            {
                byte0 = 4;
            } else
            {
                byte0 = 0;
            }
            view1.setVisibility(byte0);
            j2++;
        } while (true);
    }

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if (i >= 17)
        {
            IMPL = new SlidingPanelLayoutImplJBMR1();
        } else
        if (i >= 16)
        {
            IMPL = new SlidingPanelLayoutImplJB();
        } else
        {
            IMPL = new SlidingPanelLayoutImplBase();
        }
    }








/*
    static boolean access$502(SlidingPaneLayout slidingpanelayout, boolean flag)
    {
        slidingpanelayout.mPreservedOpenState = flag;
        return flag;
    }

*/




    private class AccessibilityDelegate extends AccessibilityDelegateCompat
    {

        private final Rect mTmpRect = new Rect();
        final SlidingPaneLayout this$0;

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat accessibilitynodeinfocompat, AccessibilityNodeInfoCompat accessibilitynodeinfocompat1)
        {
            Rect rect = mTmpRect;
            accessibilitynodeinfocompat1.getBoundsInParent(rect);
            accessibilitynodeinfocompat.setBoundsInParent(rect);
            accessibilitynodeinfocompat1.getBoundsInScreen(rect);
            accessibilitynodeinfocompat.setBoundsInScreen(rect);
            accessibilitynodeinfocompat.setVisibleToUser(accessibilitynodeinfocompat1.isVisibleToUser());
            accessibilitynodeinfocompat.setPackageName(accessibilitynodeinfocompat1.getPackageName());
            accessibilitynodeinfocompat.setClassName(accessibilitynodeinfocompat1.getClassName());
            accessibilitynodeinfocompat.setContentDescription(accessibilitynodeinfocompat1.getContentDescription());
            accessibilitynodeinfocompat.setEnabled(accessibilitynodeinfocompat1.isEnabled());
            accessibilitynodeinfocompat.setClickable(accessibilitynodeinfocompat1.isClickable());
            accessibilitynodeinfocompat.setFocusable(accessibilitynodeinfocompat1.isFocusable());
            accessibilitynodeinfocompat.setFocused(accessibilitynodeinfocompat1.isFocused());
            accessibilitynodeinfocompat.setAccessibilityFocused(accessibilitynodeinfocompat1.isAccessibilityFocused());
            accessibilitynodeinfocompat.setSelected(accessibilitynodeinfocompat1.isSelected());
            accessibilitynodeinfocompat.setLongClickable(accessibilitynodeinfocompat1.isLongClickable());
            accessibilitynodeinfocompat.addAction(accessibilitynodeinfocompat1.getActions());
            accessibilitynodeinfocompat.setMovementGranularities(accessibilitynodeinfocompat1.getMovementGranularities());
        }

        public boolean filter(View view)
        {
            return isDimmed(view);
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(view, accessibilityevent);
            accessibilityevent.setClassName(android/support/v4/widget/SlidingPaneLayout.getName());
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            AccessibilityNodeInfoCompat accessibilitynodeinfocompat1 = AccessibilityNodeInfoCompat.obtain(accessibilitynodeinfocompat);
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat1);
            copyNodeInfoNoChildren(accessibilitynodeinfocompat, accessibilitynodeinfocompat1);
            accessibilitynodeinfocompat1.recycle();
            accessibilitynodeinfocompat.setClassName(android/support/v4/widget/SlidingPaneLayout.getName());
            accessibilitynodeinfocompat.setSource(view);
            android.view.ViewParent viewparent = ViewCompat.getParentForAccessibility(view);
            if (viewparent instanceof View)
            {
                accessibilitynodeinfocompat.setParent((View)viewparent);
            }
            int i = getChildCount();
            for (int j = 0; j < i; j++)
            {
                View view1 = getChildAt(j);
                if (!filter(view1) && view1.getVisibility() == 0)
                {
                    ViewCompat.setImportantForAccessibility(view1, 1);
                    accessibilitynodeinfocompat.addChild(view1);
                }
            }

        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
        {
            if (!filter(view))
            {
                return super.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
            } else
            {
                return false;
            }
        }

        AccessibilityDelegate()
        {
            this$0 = SlidingPaneLayout.this;
            super();
        }
    }


    private class DragHelperCallback extends ViewDragHelper.Callback
    {

        final SlidingPaneLayout this$0;

        public int clampViewPositionHorizontal(View view, int i, int j)
        {
            LayoutParams layoutparams = (LayoutParams)mSlideableView.getLayoutParams();
            if (isLayoutRtlSupport())
            {
                int i1 = getWidth() - (getPaddingRight() + layoutparams.rightMargin + mSlideableView.getWidth());
                int j1 = i1 - mSlideRange;
                return Math.max(Math.min(i, i1), j1);
            } else
            {
                int k = getPaddingLeft() + layoutparams.leftMargin;
                int l = k + mSlideRange;
                return Math.min(Math.max(i, k), l);
            }
        }

        public int clampViewPositionVertical(View view, int i, int j)
        {
            return view.getTop();
        }

        public int getViewHorizontalDragRange(View view)
        {
            return mSlideRange;
        }

        public void onEdgeDragStarted(int i, int j)
        {
            mDragHelper.captureChildView(mSlideableView, j);
        }

        public void onViewCaptured(View view, int i)
        {
            setAllChildrenVisible();
        }

        public void onViewDragStateChanged(int i)
        {
label0:
            {
                if (mDragHelper.getViewDragState() == 0)
                {
                    if (mSlideOffset != 0.0F)
                    {
                        break label0;
                    }
                    updateObscuredViewsVisibility(mSlideableView);
                    dispatchOnPanelClosed(mSlideableView);
                    mPreservedOpenState = false;
                }
                return;
            }
            dispatchOnPanelOpened(mSlideableView);
            mPreservedOpenState = true;
        }

        public void onViewPositionChanged(View view, int i, int j, int k, int l)
        {
            onPanelDragged(i);
            invalidate();
        }

        public void onViewReleased(View view, float f, float f1)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            if (!isLayoutRtlSupport()) goto _L2; else goto _L1
_L1:
            int i;
            int j = getPaddingRight() + layoutparams.rightMargin;
            if (f < 0.0F || f == 0.0F && mSlideOffset > 0.5F)
            {
                j += mSlideRange;
            }
            int k = mSlideableView.getWidth();
            i = getWidth() - j - k;
_L4:
            mDragHelper.settleCapturedViewAt(i, view.getTop());
            invalidate();
            return;
_L2:
            i = getPaddingLeft() + layoutparams.leftMargin;
            if (f > 0.0F || f == 0.0F && mSlideOffset > 0.5F)
            {
                i += mSlideRange;
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public boolean tryCaptureView(View view, int i)
        {
            if (mIsUnableToDrag)
            {
                return false;
            } else
            {
                return ((LayoutParams)view.getLayoutParams()).slideable;
            }
        }

        private DragHelperCallback()
        {
            this$0 = SlidingPaneLayout.this;
            super();
        }

        DragHelperCallback(_cls1 _pcls1)
        {
            this();
        }
    }


    private class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        private static final int ATTRS[] = {
            0x1010181
        };
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;
        public float weight;


        public LayoutParams()
        {
            super(-1, -1);
            weight = 0.0F;
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
            weight = 0.0F;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            weight = 0.0F;
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, ATTRS);
            weight = typedarray.getFloat(0, 0.0F);
            typedarray.recycle();
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            weight = 0.0F;
            weight = layoutparams.weight;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            weight = 0.0F;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            weight = 0.0F;
        }
    }


    private class DisableLayerRunnable
        implements Runnable
    {

        final View mChildView;
        final SlidingPaneLayout this$0;

        public void run()
        {
            if (mChildView.getParent() == SlidingPaneLayout.this)
            {
                ViewCompat.setLayerType(mChildView, 0, null);
                invalidateChildRegion(mChildView);
            }
            mPostedRunnables.remove(this);
        }

        DisableLayerRunnable(View view)
        {
            this$0 = SlidingPaneLayout.this;
            super();
            mChildView = view;
        }
    }


    private class SlidingPanelLayoutImpl
    {

        public abstract void invalidateChildRegion(SlidingPaneLayout slidingpanelayout, View view);
    }


    private class PanelSlideListener
    {

        public abstract void onPanelClosed(View view);

        public abstract void onPanelOpened(View view);

        public abstract void onPanelSlide(View view, float f);
    }


    private class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        boolean isOpen;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            int j;
            if (isOpen)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            parcel.writeInt(j);
        }


        private SavedState(Parcel parcel)
        {
            super(parcel);
            boolean flag;
            if (parcel.readInt() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            isOpen = flag;
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


    private class SlidingPanelLayoutImplJBMR1 extends SlidingPanelLayoutImplBase
    {
        private class SlidingPanelLayoutImplBase
            implements SlidingPanelLayoutImpl
        {

            public void invalidateChildRegion(SlidingPaneLayout slidingpanelayout, View view)
            {
                ViewCompat.postInvalidateOnAnimation(slidingpanelayout, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }

            SlidingPanelLayoutImplBase()
            {
            }
        }


        public void invalidateChildRegion(SlidingPaneLayout slidingpanelayout, View view)
        {
            ViewCompat.setLayerPaint(view, ((LayoutParams)view.getLayoutParams()).dimPaint);
        }

        SlidingPanelLayoutImplJBMR1()
        {
        }
    }


    private class SlidingPanelLayoutImplJB extends SlidingPanelLayoutImplBase
    {

        private Method mGetDisplayList;
        private Field mRecreateDisplayList;

        public void invalidateChildRegion(SlidingPaneLayout slidingpanelayout, View view)
        {
            if (mGetDisplayList != null && mRecreateDisplayList != null)
            {
                try
                {
                    mRecreateDisplayList.setBoolean(view, true);
                    mGetDisplayList.invoke(view, null);
                }
                catch (Exception exception)
                {
                    Log.e("SlidingPaneLayout", "Error refreshing display list state", exception);
                }
                super.invalidateChildRegion(slidingpanelayout, view);
                return;
            } else
            {
                view.invalidate();
                return;
            }
        }

        SlidingPanelLayoutImplJB()
        {
            try
            {
                mGetDisplayList = android/view/View.getDeclaredMethod("getDisplayList", null);
            }
            catch (NoSuchMethodException nosuchmethodexception)
            {
                Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", nosuchmethodexception);
            }
            try
            {
                mRecreateDisplayList = android/view/View.getDeclaredField("mRecreateDisplayList");
                mRecreateDisplayList.setAccessible(true);
                return;
            }
            catch (NoSuchFieldException nosuchfieldexception)
            {
                Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", nosuchfieldexception);
            }
        }
    }

}
