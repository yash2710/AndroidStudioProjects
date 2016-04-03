// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

// Referenced classes of package net.simonvt.menudrawer:
//            MenuDrawer, PeekInterpolator, d, e, 
//            BuildLayerFrameLayout, Scroller, g, f

public abstract class DraggableDrawer extends MenuDrawer
{

    protected static final Interpolator INDICATOR_INTERPOLATOR = new AccelerateInterpolator();
    protected static final int MAX_MENU_OVERLAY_ALPHA = 185;
    protected static final int PEEK_DURATION = 5000;
    private static final Interpolator b = new PeekInterpolator();
    private final Runnable c;
    private Runnable d;
    private Scroller e;
    private boolean f;
    protected int mCloseEnough;
    protected float mInitialMotionX;
    protected float mInitialMotionY;
    protected boolean mIsDragging;
    protected float mLastMotionX;
    protected float mLastMotionY;
    protected int mMaxVelocity;
    protected boolean mOffsetMenu;
    protected float mOffsetPixels;
    protected long mPeekDelay;
    protected final Runnable mPeekRunnable;
    protected Scroller mPeekScroller;
    protected int mTouchSlop;
    protected VelocityTracker mVelocityTracker;

    DraggableDrawer(Activity activity, int i)
    {
        super(activity, i);
        mPeekRunnable = new d(this);
        c = new e(this);
        mLastMotionX = -1F;
        mLastMotionY = -1F;
        mOffsetMenu = true;
    }

    public DraggableDrawer(Context context)
    {
        super(context);
        mPeekRunnable = new d(this);
        c = new e(this);
        mLastMotionX = -1F;
        mLastMotionY = -1F;
        mOffsetMenu = true;
    }

    public DraggableDrawer(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mPeekRunnable = new d(this);
        c = new e(this);
        mLastMotionX = -1F;
        mLastMotionY = -1F;
        mOffsetMenu = true;
    }

    public DraggableDrawer(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mPeekRunnable = new d(this);
        c = new e(this);
        mLastMotionX = -1F;
        mLastMotionY = -1F;
        mOffsetMenu = true;
    }

    private static int a(View view)
    {
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            return (int)view.getTranslationY();
        } else
        {
            return 0;
        }
    }

    private void a()
    {
        if (f)
        {
            f = false;
            mContentContainer.setLayerType(0, null);
            mMenuContainer.setLayerType(0, null);
        }
    }

    static void a(DraggableDrawer draggabledrawer)
    {
        draggabledrawer.c();
    }

    private static int b(View view)
    {
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            return (int)view.getTranslationX();
        } else
        {
            return 0;
        }
    }

    private void b()
    {
        if (e.computeScrollOffset())
        {
            int k = (int)mOffsetPixels;
            int l = e.getCurrX();
            if (l != k)
            {
                setOffsetPixels(l);
            }
            if (l != e.getFinalX())
            {
                postOnAnimation(c);
                return;
            }
        }
        e.abortAnimation();
        int i = e.getFinalX();
        setOffsetPixels(i);
        int j;
        if (i == 0)
        {
            j = 0;
        } else
        {
            j = 8;
        }
        setDrawerState(j);
        a();
    }

    static void b(DraggableDrawer draggabledrawer)
    {
        draggabledrawer.b();
    }

    private void c()
    {
        if (mPeekScroller.computeScrollOffset())
        {
            int i = (int)mOffsetPixels;
            int j = mPeekScroller.getCurrX();
            if (j != i)
            {
                setOffsetPixels(j);
            }
            if (!mPeekScroller.isFinished())
            {
                postOnAnimation(mPeekRunnable);
                return;
            }
            if (mPeekDelay > 0L)
            {
                d = new g(this);
                postDelayed(d, mPeekDelay);
            }
        }
        mPeekScroller.abortAnimation();
        setOffsetPixels(0.0F);
        setDrawerState(0);
        a();
    }

    final void a(Bundle bundle)
    {
        boolean flag;
        if (mDrawerState == 8 || mDrawerState == 4)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        bundle.putBoolean("net.simonvt.menudrawer.MenuDrawer.menuVisible", flag);
    }

    protected void animateOffsetTo(int i, int j, boolean flag)
    {
        endDrag();
        endPeek();
        int k = (int)mOffsetPixels;
        int l = i - k;
        if (l == 0 || !flag)
        {
            setOffsetPixels(i);
            int i1 = 0;
            if (i != 0)
            {
                i1 = 8;
            }
            setDrawerState(i1);
            a();
            return;
        }
        int j1 = Math.abs(j);
        int k1;
        int l1;
        if (j1 > 0)
        {
            k1 = 4 * Math.round(1000F * Math.abs((float)l / (float)j1));
        } else
        {
            k1 = (int)(600F * Math.abs((float)l / (float)mMenuSize));
        }
        l1 = Math.min(k1, mMaxAnimationDuration);
        if (l > 0)
        {
            setDrawerState(4);
            e.startScroll(k, 0, l, 0, l1);
        } else
        {
            setDrawerState(1);
            e.startScroll(k, 0, l, 0, l1);
        }
        startLayerTranslation();
        b();
    }

    protected boolean canChildScrollHorizontally(View view, boolean flag, int i, int j, int k)
    {
        if (!(view instanceof ViewGroup)) goto _L2; else goto _L1
_L1:
        ViewGroup viewgroup;
        int l;
        viewgroup = (ViewGroup)view;
        l = -1 + viewgroup.getChildCount();
_L8:
        if (l < 0) goto _L2; else goto _L3
_L3:
        View view1;
        int i1;
        int j1;
        int k1;
        int l1;
        view1 = viewgroup.getChildAt(l);
        i1 = view1.getLeft() + b(view1);
        j1 = view1.getRight() + b(view1);
        k1 = view1.getTop() + a(view1);
        l1 = view1.getBottom() + a(view1);
        if (j < i1 || j >= j1 || k < k1 || k >= l1 || !canChildScrollHorizontally(view1, true, i, j - i1, k - k1)) goto _L5; else goto _L4
_L4:
        return true;
_L5:
        l--;
        continue; /* Loop/switch isn't completed */
_L2:
        if (flag && mOnInterceptMoveEventListener.isViewDraggable(view, i, j, k)) goto _L4; else goto _L6
_L6:
        return false;
        if (true) goto _L8; else goto _L7
_L7:
    }

    protected boolean canChildScrollVertically(View view, boolean flag, int i, int j, int k)
    {
        if (!(view instanceof ViewGroup)) goto _L2; else goto _L1
_L1:
        ViewGroup viewgroup;
        int l;
        viewgroup = (ViewGroup)view;
        l = -1 + viewgroup.getChildCount();
_L8:
        if (l < 0) goto _L2; else goto _L3
_L3:
        View view1;
        int i1;
        int j1;
        int k1;
        int l1;
        view1 = viewgroup.getChildAt(l);
        i1 = view1.getLeft() + b(view1);
        j1 = view1.getRight() + b(view1);
        k1 = view1.getTop() + a(view1);
        l1 = view1.getBottom() + a(view1);
        if (j < i1 || j >= j1 || k < k1 || k >= l1 || !canChildScrollVertically(view1, true, i, j - i1, k - k1)) goto _L5; else goto _L4
_L4:
        return true;
_L5:
        l--;
        continue; /* Loop/switch isn't completed */
_L2:
        if (flag && mOnInterceptMoveEventListener.isViewDraggable(view, i, j, k)) goto _L4; else goto _L6
_L6:
        return false;
        if (true) goto _L8; else goto _L7
_L7:
    }

    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        int i = (int)mOffsetPixels;
        if (i != 0)
        {
            drawMenuOverlay(canvas, i);
            drawContentOverlay(canvas, i);
        }
        if (mDropShadowEnabled)
        {
            drawDropShadow(canvas, i);
        }
        if (mActiveIndicator != null)
        {
            drawIndicator(canvas, i);
        }
    }

    protected abstract void drawContentOverlay(Canvas canvas, int i);

    protected abstract void drawDropShadow(Canvas canvas, int i);

    protected abstract void drawIndicator(Canvas canvas, int i);

    protected abstract void drawMenuOverlay(Canvas canvas, int i);

    protected void endDrag()
    {
        mIsDragging = false;
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    protected void endPeek()
    {
        removeCallbacks(d);
        removeCallbacks(mPeekRunnable);
        a();
    }

    public boolean getOffsetMenuEnabled()
    {
        return mOffsetMenu;
    }

    public int getTouchBezelSize()
    {
        return mTouchBezelSize;
    }

    public int getTouchMode()
    {
        return mTouchMode;
    }

    protected void initDrawer(Context context, AttributeSet attributeset, int i)
    {
        super.initDrawer(context, attributeset, i);
        ViewConfiguration viewconfiguration = ViewConfiguration.get(context);
        mTouchSlop = viewconfiguration.getScaledTouchSlop();
        mMaxVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        e = new Scroller(context, MenuDrawer.SMOOTH_INTERPOLATOR);
        mPeekScroller = new Scroller(context, b);
        mCloseEnough = dpToPx(3);
    }

    protected abstract void initPeekScroller();

    protected boolean isCloseEnough()
    {
        return Math.abs(mOffsetPixels) <= (float)mCloseEnough;
    }

    protected abstract boolean isContentTouch(MotionEvent motionevent);

    public boolean isMenuVisible()
    {
        return mMenuVisible;
    }

    protected abstract boolean onDownAllowDrag(MotionEvent motionevent);

    protected abstract boolean onMoveAllowDrag(MotionEvent motionevent, float f1);

    protected abstract void onMoveEvent(float f1);

    protected abstract void onOffsetPixelsChanged(int i);

    protected abstract void onUpEvent(MotionEvent motionevent);

    public void peekDrawer()
    {
        peekDrawer(5000L, 10000L);
    }

    public void peekDrawer(long l)
    {
        peekDrawer(5000L, l);
    }

    public void peekDrawer(long l, long l1)
    {
        if (l < 0L)
        {
            throw new IllegalArgumentException("startDelay must be zero or larger.");
        }
        if (l1 < 0L)
        {
            throw new IllegalArgumentException("delay must be zero or larger");
        } else
        {
            removeCallbacks(mPeekRunnable);
            removeCallbacks(d);
            mPeekDelay = l1;
            d = new f(this);
            postDelayed(d, l);
            return;
        }
    }

    public void restoreState(Parcelable parcelable)
    {
        super.restoreState(parcelable);
        boolean flag = ((Bundle)parcelable).getBoolean("net.simonvt.menudrawer.MenuDrawer.menuVisible");
        int i;
        if (flag)
        {
            openMenu(false);
        } else
        {
            setOffsetPixels(0.0F);
        }
        i = 0;
        if (flag)
        {
            i = 8;
        }
        mDrawerState = i;
    }

    public void setHardwareLayerEnabled(boolean flag)
    {
        if (flag != mHardwareLayersEnabled)
        {
            mHardwareLayersEnabled = flag;
            mMenuContainer.a(flag);
            mContentContainer.a(flag);
            a();
        }
    }

    public void setMenuSize(int i)
    {
        mMenuSize = i;
        mMenuSizeSet = true;
        if (mDrawerState == 8 || mDrawerState == 4)
        {
            setOffsetPixels(mMenuSize);
        }
        requestLayout();
        invalidate();
    }

    public void setOffsetMenuEnabled(boolean flag)
    {
        if (flag != mOffsetMenu)
        {
            mOffsetMenu = flag;
            requestLayout();
            invalidate();
        }
    }

    protected void setOffsetPixels(float f1)
    {
        int i = (int)mOffsetPixels;
        int j = (int)f1;
        mOffsetPixels = f1;
        if (j != i)
        {
            onOffsetPixelsChanged(j);
            boolean flag;
            if (j != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mMenuVisible = flag;
        }
    }

    public void setTouchBezelSize(int i)
    {
        mTouchBezelSize = i;
    }

    public void setTouchMode(int i)
    {
        if (mTouchMode != i)
        {
            mTouchMode = i;
            updateTouchAreaSize();
        }
    }

    protected void startLayerTranslation()
    {
        if (a && mHardwareLayersEnabled && !f)
        {
            f = true;
            mContentContainer.setLayerType(2, null);
            mMenuContainer.setLayerType(2, null);
        }
    }

    protected void startPeek()
    {
        initPeekScroller();
        startLayerTranslation();
        c();
    }

    protected void stopAnimation()
    {
        removeCallbacks(c);
        e.abortAnimation();
        a();
    }

    public void toggleMenu(boolean flag)
    {
        if (mDrawerState == 8 || mDrawerState == 4)
        {
            closeMenu(flag);
        } else
        if (mDrawerState == 0 || mDrawerState == 1)
        {
            openMenu(flag);
            return;
        }
    }

    protected void updateTouchAreaSize()
    {
        if (mTouchMode == 1)
        {
            mTouchSize = mTouchBezelSize;
            return;
        }
        if (mTouchMode == 2)
        {
            mTouchSize = getMeasuredWidth();
            return;
        } else
        {
            mTouchSize = 0;
            return;
        }
    }

}
