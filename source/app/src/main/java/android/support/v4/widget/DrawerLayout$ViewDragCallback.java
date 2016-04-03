// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.view.View;

// Referenced classes of package android.support.v4.widget:
//            DrawerLayout, ViewDragHelper

class mAbsGravity extends mAbsGravity
{

    private final int mAbsGravity;
    private ViewDragHelper mDragger;
    private final Runnable mPeekRunnable = new _cls1();
    final DrawerLayout this$0;

    private void closeOtherDrawer()
    {
        byte byte0 = 3;
        if (mAbsGravity == byte0)
        {
            byte0 = 5;
        }
        View view = findDrawerWithGravity(byte0);
        if (view != null)
        {
            closeDrawer(view);
        }
    }

    private void peekDrawer()
    {
        int i = mDragger.getEdgeSize();
        boolean flag;
        View view1;
        int k;
        if (mAbsGravity == 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            View view2 = findDrawerWithGravity(3);
            int l = 0;
            if (view2 != null)
            {
                l = -view2.getWidth();
            }
            int i1 = l + i;
            view1 = view2;
            k = i1;
        } else
        {
            View view = findDrawerWithGravity(5);
            int j = getWidth() - i;
            view1 = view;
            k = j;
        }
        if (view1 != null && (flag && view1.getLeft() < k || !flag && view1.getLeft() > k) && getDrawerLockMode(view1) == 0)
        {
            vity vity = ()view1.getLayoutParams();
            mDragger.smoothSlideViewTo(view1, k, view1.getTop());
            vity.eking = true;
            invalidate();
            closeOtherDrawer();
            cancelChildViewTouch();
        }
    }

    public int clampViewPositionHorizontal(View view, int i, int j)
    {
        if (checkDrawerViewAbsoluteGravity(view, 3))
        {
            return Math.max(-view.getWidth(), Math.min(i, 0));
        } else
        {
            int k = getWidth();
            return Math.max(k - view.getWidth(), Math.min(i, k));
        }
    }

    public int clampViewPositionVertical(View view, int i, int j)
    {
        return view.getTop();
    }

    public int getViewHorizontalDragRange(View view)
    {
        return view.getWidth();
    }

    public void onEdgeDragStarted(int i, int j)
    {
        View view;
        if ((i & 1) == 1)
        {
            view = findDrawerWithGravity(3);
        } else
        {
            view = findDrawerWithGravity(5);
        }
        if (view != null && getDrawerLockMode(view) == 0)
        {
            mDragger.captureChildView(view, j);
        }
    }

    public boolean onEdgeLock(int i)
    {
        return false;
    }

    public void onEdgeTouched(int i, int j)
    {
        postDelayed(mPeekRunnable, 160L);
    }

    public void onViewCaptured(View view, int i)
    {
        ((mPeekRunnable)view.getLayoutParams()).eking = false;
        closeOtherDrawer();
    }

    public void onViewDragStateChanged(int i)
    {
        updateDrawerState(mAbsGravity, i, mDragger.getCapturedView());
    }

    public void onViewPositionChanged(View view, int i, int j, int k, int l)
    {
        int i1 = view.getWidth();
        float f;
        byte byte0;
        if (checkDrawerViewAbsoluteGravity(view, 3))
        {
            f = (float)(i1 + i) / (float)i1;
        } else
        {
            f = (float)(getWidth() - i) / (float)i1;
        }
        setDrawerViewOffset(view, f);
        if (f == 0.0F)
        {
            byte0 = 4;
        } else
        {
            byte0 = 0;
        }
        view.setVisibility(byte0);
        invalidate();
    }

    public void onViewReleased(View view, float f, float f1)
    {
        float f2;
        int i;
        f2 = getDrawerViewOffset(view);
        i = view.getWidth();
        if (!checkDrawerViewAbsoluteGravity(view, 3)) goto _L2; else goto _L1
_L1:
        int j;
        if (f > 0.0F || f == 0.0F && f2 > 0.5F)
        {
            j = 0;
        } else
        {
            j = -i;
        }
_L4:
        mDragger.settleCapturedViewAt(j, view.getTop());
        invalidate();
        return;
_L2:
        j = getWidth();
        if (f < 0.0F || f == 0.0F && f2 > 0.5F)
        {
            j -= i;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void removeCallbacks()
    {
        DrawerLayout.this.removeCallbacks(mPeekRunnable);
    }

    public void setDragger(ViewDragHelper viewdraghelper)
    {
        mDragger = viewdraghelper;
    }

    public boolean tryCaptureView(View view, int i)
    {
        return isDrawerView(view) && checkDrawerViewAbsoluteGravity(view, mAbsGravity) && getDrawerLockMode(view) == 0;
    }


    public _cls1.this._cls1(int i)
    {
        this$0 = DrawerLayout.this;
        super();
        class _cls1
            implements Runnable
        {

            final DrawerLayout.ViewDragCallback this$1;

            public void run()
            {
                peekDrawer();
            }

            _cls1()
            {
                this$1 = DrawerLayout.ViewDragCallback.this;
                super();
            }
        }

        mAbsGravity = i;
    }
}
