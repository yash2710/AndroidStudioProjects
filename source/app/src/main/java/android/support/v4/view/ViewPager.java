// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Referenced classes of package android.support.v4.view:
//            PagerAdapter, ViewCompat, MotionEventCompat, VelocityTrackerCompat, 
//            KeyEventCompat, ViewConfigurationCompat

public class ViewPager extends ViewGroup
{

    private static final int CLOSE_ENOUGH = 2;
    private static final Comparator COMPARATOR = new _cls1();
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int LAYOUT_ATTRS[] = {
        0x10100b3
    };
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE;
    private static final Interpolator sInterpolator = new _cls2();
    private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();
    private int mActivePointerId;
    private PagerAdapter mAdapter;
    private OnAdapterChangeListener mAdapterChangeListener;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    private int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mIgnoreGutter;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsUnableToDrag;
    private final ArrayList mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffectCompat mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private OnPageChangeListener mOnPageChangeListener;
    private int mPageMargin;
    private PageTransformer mPageTransformer;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffectCompat mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private Method mSetChildrenDrawingOrderEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    public ViewPager(Context context)
    {
        super(context);
        mItems = new ArrayList();
        mTempItem = new ItemInfo();
        mTempRect = new Rect();
        mRestoredCurItem = -1;
        mRestoredAdapterState = null;
        mRestoredClassLoader = null;
        mFirstOffset = -3.402823E+38F;
        mLastOffset = 3.402823E+38F;
        mOffscreenPageLimit = 1;
        mActivePointerId = -1;
        mFirstLayout = true;
        mNeedCalculatePageOffsets = false;
        mEndScrollRunnable = new _cls3();
        mScrollState = 0;
        initViewPager();
    }

    public ViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mItems = new ArrayList();
        mTempItem = new ItemInfo();
        mTempRect = new Rect();
        mRestoredCurItem = -1;
        mRestoredAdapterState = null;
        mRestoredClassLoader = null;
        mFirstOffset = -3.402823E+38F;
        mLastOffset = 3.402823E+38F;
        mOffscreenPageLimit = 1;
        mActivePointerId = -1;
        mFirstLayout = true;
        mNeedCalculatePageOffsets = false;
        mEndScrollRunnable = new _cls3();
        mScrollState = 0;
        initViewPager();
    }

    private void calculatePageOffsets(ItemInfo iteminfo, int i, ItemInfo iteminfo1)
    {
        float f11;
        int k5;
        float f12;
        int i6;
        int j = mAdapter.getCount();
        int k = getClientWidth();
        float f;
        if (k > 0)
        {
            f = (float)mPageMargin / (float)k;
        } else
        {
            f = 0.0F;
        }
        if (iteminfo1 != null)
        {
            int i3 = iteminfo1.position;
            if (i3 < iteminfo.position)
            {
                float f10 = f + (iteminfo1.offset + iteminfo1.widthFactor);
                int i5 = i3 + 1;
                f11 = f10;
                int j5 = 0;
                k5 = i5;
                ItemInfo iteminfo5;
                float f13;
                int j6;
                float f14;
                do
                {
                    if (k5 > iteminfo.position || j5 >= mItems.size())
                    {
                        break;
                    }
                    for (iteminfo5 = (ItemInfo)mItems.get(j5); k5 > iteminfo5.position && j5 < -1 + mItems.size(); iteminfo5 = (ItemInfo)mItems.get(j5))
                    {
                        j5++;
                    }

                    break MISSING_BLOCK_LABEL_800;
                } while (true);
            } else
            if (i3 > iteminfo.position)
            {
                int j3 = -1 + mItems.size();
                float f5 = iteminfo1.offset;
                int k3 = i3 - 1;
                int l3 = j3;
                float f6 = f5;
                int i4 = k3;
                do
                {
                    if (i4 < iteminfo.position || l3 < 0)
                    {
                        break;
                    }
                    int l;
                    float f1;
                    int i1;
                    float f2;
                    float f3;
                    int j1;
                    float f4;
                    int k1;
                    int l1;
                    ItemInfo iteminfo2;
                    int i2;
                    PagerAdapter pageradapter;
                    int j2;
                    ItemInfo iteminfo3;
                    int k2;
                    PagerAdapter pageradapter1;
                    int l2;
                    ItemInfo iteminfo4;
                    float f7;
                    int k4;
                    float f8;
                    int l4;
                    for (iteminfo4 = (ItemInfo)mItems.get(l3); i4 < iteminfo4.position && l3 > 0; iteminfo4 = (ItemInfo)mItems.get(l3))
                    {
                        l3--;
                    }

                    int j4 = i4;
                    f7 = f6;
                    k4 = j4;
                    while (k4 > iteminfo4.position) 
                    {
                        float f9 = f7 - (f + mAdapter.getPageWidth(k4));
                        k4--;
                        f7 = f9;
                    }
                    f8 = f7 - (f + iteminfo4.widthFactor);
                    iteminfo4.offset = f8;
                    l4 = k4 - 1;
                    f6 = f8;
                    i4 = l4;
                } while (true);
            }
        }
        l = mItems.size();
        f1 = iteminfo.offset;
        i1 = -1 + iteminfo.position;
        if (iteminfo.position == 0)
        {
            f2 = iteminfo.offset;
        } else
        {
            f2 = -3.402823E+38F;
        }
        mFirstOffset = f2;
        if (iteminfo.position == j - 1)
        {
            f3 = (iteminfo.offset + iteminfo.widthFactor) - 1.0F;
        } else
        {
            f3 = 3.402823E+38F;
        }
        mLastOffset = f3;
        for (j1 = i - 1; j1 >= 0; j1 = k2)
        {
            for (iteminfo3 = (ItemInfo)mItems.get(j1); i1 > iteminfo3.position; i1 = l2)
            {
                pageradapter1 = mAdapter;
                l2 = i1 - 1;
                f1 -= f + pageradapter1.getPageWidth(i1);
            }

            f1 -= f + iteminfo3.widthFactor;
            iteminfo3.offset = f1;
            if (iteminfo3.position == 0)
            {
                mFirstOffset = f1;
            }
            k2 = j1 - 1;
            i1--;
        }

        f4 = f + (iteminfo.offset + iteminfo.widthFactor);
        k1 = 1 + iteminfo.position;
        for (l1 = i + 1; l1 < l; l1 = i2)
        {
            for (iteminfo2 = (ItemInfo)mItems.get(l1); k1 < iteminfo2.position; k1 = j2)
            {
                pageradapter = mAdapter;
                j2 = k1 + 1;
                f4 += f + pageradapter.getPageWidth(k1);
            }

            if (iteminfo2.position == j - 1)
            {
                mLastOffset = (f4 + iteminfo2.widthFactor) - 1.0F;
            }
            iteminfo2.offset = f4;
            f4 += f + iteminfo2.widthFactor;
            i2 = l1 + 1;
            k1++;
        }

        mNeedCalculatePageOffsets = false;
        return;
        int l5 = k5;
        f12 = f11;
        i6 = l5;
        while (i6 < iteminfo5.position) 
        {
            f14 = f12 + (f + mAdapter.getPageWidth(i6));
            i6++;
            f12 = f14;
        }
        iteminfo5.offset = f12;
        f13 = f12 + (f + iteminfo5.widthFactor);
        j6 = i6 + 1;
        f11 = f13;
        k5 = j6;
        continue;
    }

    private void completeScroll(boolean flag)
    {
label0:
        {
            boolean flag1;
            int i;
            boolean flag2;
            if (mScrollState == 2)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                setScrollingCacheEnabled(false);
                mScroller.abortAnimation();
                int j = getScrollX();
                int k = getScrollY();
                int l = mScroller.getCurrX();
                int i1 = mScroller.getCurrY();
                if (j != l || k != i1)
                {
                    scrollTo(l, i1);
                }
            }
            mPopulatePending = false;
            i = 0;
            flag2 = flag1;
            for (; i < mItems.size(); i++)
            {
                ItemInfo iteminfo = (ItemInfo)mItems.get(i);
                if (iteminfo.scrolling)
                {
                    iteminfo.scrolling = false;
                    flag2 = true;
                }
            }

            if (flag2)
            {
                if (!flag)
                {
                    break label0;
                }
                ViewCompat.postOnAnimation(this, mEndScrollRunnable);
            }
            return;
        }
        mEndScrollRunnable.run();
    }

    private int determineTargetPage(int i, float f, int j, int k)
    {
        if (Math.abs(k) > mFlingDistance && Math.abs(j) > mMinimumVelocity)
        {
            if (j <= 0)
            {
                i++;
            }
        } else
        {
            float f1;
            if (i >= mCurItem)
            {
                f1 = 0.4F;
            } else
            {
                f1 = 0.6F;
            }
            i = (int)(f1 + (f + (float)i));
        }
        if (mItems.size() > 0)
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(0);
            ItemInfo iteminfo1 = (ItemInfo)mItems.get(-1 + mItems.size());
            i = Math.max(iteminfo.position, Math.min(i, iteminfo1.position));
        }
        return i;
    }

    private void enableLayers(boolean flag)
    {
        int i = getChildCount();
        int j = 0;
        while (j < i) 
        {
            byte byte0;
            if (flag)
            {
                byte0 = 2;
            } else
            {
                byte0 = 0;
            }
            ViewCompat.setLayerType(getChildAt(j), byte0, null);
            j++;
        }
    }

    private void endDrag()
    {
        mIsBeingDragged = false;
        mIsUnableToDrag = false;
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private Rect getChildRectInPagerCoordinates(Rect rect, View view)
    {
        Rect rect1;
        if (rect == null)
        {
            rect1 = new Rect();
        } else
        {
            rect1 = rect;
        }
        if (view == null)
        {
            rect1.set(0, 0, 0, 0);
            return rect1;
        }
        rect1.left = view.getLeft();
        rect1.right = view.getRight();
        rect1.top = view.getTop();
        rect1.bottom = view.getBottom();
        ViewGroup viewgroup;
        for (ViewParent viewparent = view.getParent(); (viewparent instanceof ViewGroup) && viewparent != this; viewparent = viewgroup.getParent())
        {
            viewgroup = (ViewGroup)viewparent;
            rect1.left = rect1.left + viewgroup.getLeft();
            rect1.right = rect1.right + viewgroup.getRight();
            rect1.top = rect1.top + viewgroup.getTop();
            rect1.bottom = rect1.bottom + viewgroup.getBottom();
        }

        return rect1;
    }

    private int getClientWidth()
    {
        return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    private ItemInfo infoForCurrentScrollPosition()
    {
        int i = getClientWidth();
        float f;
        float f1;
        float f2;
        float f3;
        int j;
        int k;
        boolean flag;
        ItemInfo iteminfo;
        if (i > 0)
        {
            f = (float)getScrollX() / (float)i;
        } else
        {
            f = 0.0F;
        }
        if (i > 0)
        {
            f1 = (float)mPageMargin / (float)i;
        } else
        {
            f1 = 0.0F;
        }
        f2 = 0.0F;
        f3 = 0.0F;
        j = -1;
        k = 0;
        flag = true;
        iteminfo = null;
        do
        {
label0:
            {
                if (k < mItems.size())
                {
                    ItemInfo iteminfo1 = (ItemInfo)mItems.get(k);
                    int l;
                    ItemInfo iteminfo2;
                    float f4;
                    float f5;
                    int i1;
                    float f6;
                    int j1;
                    if (!flag && iteminfo1.position != j + 1)
                    {
                        ItemInfo iteminfo3 = mTempItem;
                        iteminfo3.offset = f1 + (f2 + f3);
                        iteminfo3.position = j + 1;
                        iteminfo3.widthFactor = mAdapter.getPageWidth(iteminfo3.position);
                        l = k - 1;
                        iteminfo2 = iteminfo3;
                    } else
                    {
                        l = k;
                        iteminfo2 = iteminfo1;
                    }
                    f4 = iteminfo2.offset;
                    f5 = f1 + (f4 + iteminfo2.widthFactor);
                    if (flag || f >= f4)
                    {
                        if (f >= f5 && l != -1 + mItems.size())
                        {
                            break label0;
                        }
                        iteminfo = iteminfo2;
                    }
                }
                return iteminfo;
            }
            i1 = iteminfo2.position;
            f6 = iteminfo2.widthFactor;
            j1 = l + 1;
            f3 = f4;
            j = i1;
            f2 = f6;
            iteminfo = iteminfo2;
            k = j1;
            flag = false;
        } while (true);
    }

    private boolean isGutterDrag(float f, float f1)
    {
        return f < (float)mGutterSize && f1 > 0.0F || f > (float)(getWidth() - mGutterSize) && f1 < 0.0F;
    }

    private void onSecondaryPointerUp(MotionEvent motionevent)
    {
        int i = MotionEventCompat.getActionIndex(motionevent);
        if (MotionEventCompat.getPointerId(motionevent, i) == mActivePointerId)
        {
            int j;
            if (i == 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            mLastMotionX = MotionEventCompat.getX(motionevent, j);
            mActivePointerId = MotionEventCompat.getPointerId(motionevent, j);
            if (mVelocityTracker != null)
            {
                mVelocityTracker.clear();
            }
        }
    }

    private boolean pageScrolled(int i)
    {
        boolean flag;
        if (mItems.size() == 0)
        {
            mCalledSuper = false;
            onPageScrolled(0, 0.0F, 0);
            boolean flag1 = mCalledSuper;
            flag = false;
            if (!flag1)
            {
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            }
        } else
        {
            ItemInfo iteminfo = infoForCurrentScrollPosition();
            int j = getClientWidth();
            int k = j + mPageMargin;
            float f = (float)mPageMargin / (float)j;
            int l = iteminfo.position;
            float f1 = ((float)i / (float)j - iteminfo.offset) / (f + iteminfo.widthFactor);
            int i1 = (int)(f1 * (float)k);
            mCalledSuper = false;
            onPageScrolled(l, f1, i1);
            if (!mCalledSuper)
            {
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            }
            flag = true;
        }
        return flag;
    }

    private boolean performDrag(float f)
    {
        boolean flag = true;
        float f1 = mLastMotionX - f;
        mLastMotionX = f;
        float f2 = f1 + (float)getScrollX();
        int i = getClientWidth();
        float f3 = (float)i * mFirstOffset;
        float f4 = (float)i * mLastOffset;
        ItemInfo iteminfo = (ItemInfo)mItems.get(0);
        ItemInfo iteminfo1 = (ItemInfo)mItems.get(-1 + mItems.size());
        boolean flag1;
        float f5;
        boolean flag2;
        if (iteminfo.position != 0)
        {
            f3 = iteminfo.offset * (float)i;
            flag1 = false;
        } else
        {
            flag1 = flag;
        }
        if (iteminfo1.position != -1 + mAdapter.getCount())
        {
            f5 = iteminfo1.offset * (float)i;
            flag = false;
        } else
        {
            f5 = f4;
        }
        if (f2 < f3)
        {
            flag2 = false;
            if (flag1)
            {
                float f6 = f3 - f2;
                flag2 = mLeftEdge.onPull(Math.abs(f6) / (float)i);
            }
        } else
        if (f2 > f5)
        {
            flag2 = false;
            if (flag)
            {
                float f7 = f2 - f5;
                flag2 = mRightEdge.onPull(Math.abs(f7) / (float)i);
            }
            f3 = f5;
        } else
        {
            f3 = f2;
            flag2 = false;
        }
        mLastMotionX = mLastMotionX + (f3 - (float)(int)f3);
        scrollTo((int)f3, getScrollY());
        pageScrolled((int)f3);
        return flag2;
    }

    private void recomputeScrollPosition(int i, int j, int k, int l)
    {
        if (j > 0 && !mItems.isEmpty())
        {
            int j1 = k + (i - getPaddingLeft() - getPaddingRight());
            int k1 = l + (j - getPaddingLeft() - getPaddingRight());
            int l1 = (int)(((float)getScrollX() / (float)k1) * (float)j1);
            scrollTo(l1, getScrollY());
            if (!mScroller.isFinished())
            {
                int i2 = mScroller.getDuration() - mScroller.timePassed();
                ItemInfo iteminfo1 = infoForPosition(mCurItem);
                mScroller.startScroll(l1, 0, (int)(iteminfo1.offset * (float)i), 0, i2);
            }
        } else
        {
            ItemInfo iteminfo = infoForPosition(mCurItem);
            float f;
            int i1;
            if (iteminfo != null)
            {
                f = Math.min(iteminfo.offset, mLastOffset);
            } else
            {
                f = 0.0F;
            }
            i1 = (int)(f * (float)(i - getPaddingLeft() - getPaddingRight()));
            if (i1 != getScrollX())
            {
                completeScroll(false);
                scrollTo(i1, getScrollY());
                return;
            }
        }
    }

    private void removeNonDecorViews()
    {
        for (int i = 0; i < getChildCount(); i++)
        {
            if (!((LayoutParams)getChildAt(i).getLayoutParams()).isDecor)
            {
                removeViewAt(i);
                i--;
            }
        }

    }

    private void requestParentDisallowInterceptTouchEvent(boolean flag)
    {
        ViewParent viewparent = getParent();
        if (viewparent != null)
        {
            viewparent.requestDisallowInterceptTouchEvent(flag);
        }
    }

    private void scrollToItem(int i, boolean flag, int j, boolean flag1)
    {
        ItemInfo iteminfo = infoForPosition(i);
        int k;
        if (iteminfo != null)
        {
            k = (int)((float)getClientWidth() * Math.max(mFirstOffset, Math.min(iteminfo.offset, mLastOffset)));
        } else
        {
            k = 0;
        }
        if (flag)
        {
            smoothScrollTo(k, 0, j);
            if (flag1 && mOnPageChangeListener != null)
            {
                mOnPageChangeListener.onPageSelected(i);
            }
            if (flag1 && mInternalPageChangeListener != null)
            {
                mInternalPageChangeListener.onPageSelected(i);
            }
            return;
        }
        if (flag1 && mOnPageChangeListener != null)
        {
            mOnPageChangeListener.onPageSelected(i);
        }
        if (flag1 && mInternalPageChangeListener != null)
        {
            mInternalPageChangeListener.onPageSelected(i);
        }
        completeScroll(false);
        scrollTo(k, 0);
        pageScrolled(k);
    }

    private void setScrollState(int i)
    {
        if (mScrollState != i)
        {
            mScrollState = i;
            if (mPageTransformer != null)
            {
                boolean flag;
                if (i != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                enableLayers(flag);
            }
            if (mOnPageChangeListener != null)
            {
                mOnPageChangeListener.onPageScrollStateChanged(i);
                return;
            }
        }
    }

    private void setScrollingCacheEnabled(boolean flag)
    {
        if (mScrollingCacheEnabled != flag)
        {
            mScrollingCacheEnabled = flag;
        }
    }

    private void sortChildDrawingOrder()
    {
        if (mDrawingOrder != 0)
        {
            int i;
            if (mDrawingOrderedChildren == null)
            {
                mDrawingOrderedChildren = new ArrayList();
            } else
            {
                mDrawingOrderedChildren.clear();
            }
            i = getChildCount();
            for (int j = 0; j < i; j++)
            {
                View view = getChildAt(j);
                mDrawingOrderedChildren.add(view);
            }

            Collections.sort(mDrawingOrderedChildren, sPositionComparator);
        }
    }

    public void addFocusables(ArrayList arraylist, int i, int j)
    {
        int k = arraylist.size();
        int l = getDescendantFocusability();
        if (l != 0x60000)
        {
            for (int i1 = 0; i1 < getChildCount(); i1++)
            {
                View view = getChildAt(i1);
                if (view.getVisibility() == 0)
                {
                    ItemInfo iteminfo = infoForChild(view);
                    if (iteminfo != null && iteminfo.position == mCurItem)
                    {
                        view.addFocusables(arraylist, i, j);
                    }
                }
            }

        }
        while (l == 0x40000 && k != arraylist.size() || !isFocusable() || (j & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode() || arraylist == null) 
        {
            return;
        }
        arraylist.add(this);
    }

    ItemInfo addNewItem(int i, int j)
    {
        ItemInfo iteminfo = new ItemInfo();
        iteminfo.position = i;
        iteminfo.object = mAdapter.instantiateItem(this, i);
        iteminfo.widthFactor = mAdapter.getPageWidth(i);
        if (j < 0 || j >= mItems.size())
        {
            mItems.add(iteminfo);
            return iteminfo;
        } else
        {
            mItems.add(j, iteminfo);
            return iteminfo;
        }
    }

    public void addTouchables(ArrayList arraylist)
    {
        for (int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            if (view.getVisibility() != 0)
            {
                continue;
            }
            ItemInfo iteminfo = infoForChild(view);
            if (iteminfo != null && iteminfo.position == mCurItem)
            {
                view.addTouchables(arraylist);
            }
        }

    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        android.view.ViewGroup.LayoutParams layoutparams1;
        LayoutParams layoutparams2;
        if (!checkLayoutParams(layoutparams))
        {
            layoutparams1 = generateLayoutParams(layoutparams);
        } else
        {
            layoutparams1 = layoutparams;
        }
        layoutparams2 = (LayoutParams)layoutparams1;
        layoutparams2.isDecor = layoutparams2.isDecor | (view instanceof Decor);
        if (mInLayout)
        {
            if (layoutparams2 != null && layoutparams2.isDecor)
            {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            } else
            {
                layoutparams2.needsMeasure = true;
                addViewInLayout(view, i, layoutparams1);
                return;
            }
        } else
        {
            super.addView(view, i, layoutparams1);
            return;
        }
    }

    public boolean arrowScroll(int i)
    {
        View view = findFocus();
        if (view != this) goto _L2; else goto _L1
_L1:
        View view1 = null;
_L13:
        View view2 = FocusFinder.getInstance().findNextFocus(this, view1, i);
        if (view2 == null || view2 == view1) goto _L4; else goto _L3
_L3:
        if (i != 17) goto _L6; else goto _L5
_L5:
        boolean flag;
        boolean flag1;
        int l = getChildRectInPagerCoordinates(mTempRect, view2).left;
        int i1 = getChildRectInPagerCoordinates(mTempRect, view1).left;
        ViewParent viewparent;
        StringBuilder stringbuilder;
        ViewParent viewparent1;
        if (view1 != null && l >= i1)
        {
            flag = pageLeft();
        } else
        {
            flag = view2.requestFocus();
        }
_L16:
        if (flag)
        {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return flag;
_L2:
        if (view == null) goto _L8; else goto _L7
_L7:
        viewparent = view.getParent();
_L12:
        if (!(viewparent instanceof ViewGroup))
        {
            break MISSING_BLOCK_LABEL_372;
        }
        if (viewparent != this) goto _L10; else goto _L9
_L9:
        flag1 = true;
_L18:
        if (flag1) goto _L8; else goto _L11
_L10:
        viewparent = viewparent.getParent();
          goto _L12
_L11:
        stringbuilder = new StringBuilder();
        stringbuilder.append(view.getClass().getSimpleName());
        for (viewparent1 = view.getParent(); viewparent1 instanceof ViewGroup; viewparent1 = viewparent1.getParent())
        {
            stringbuilder.append(" => ").append(viewparent1.getClass().getSimpleName());
        }

        Log.e("ViewPager", (new StringBuilder("arrowScroll tried to find focus based on non-child current focused view ")).append(stringbuilder.toString()).toString());
        view1 = null;
          goto _L13
_L6:
        if (i != 66) goto _L15; else goto _L14
_L14:
        int j = getChildRectInPagerCoordinates(mTempRect, view2).left;
        int k = getChildRectInPagerCoordinates(mTempRect, view1).left;
        if (view1 != null && j <= k)
        {
            flag = pageRight();
        } else
        {
            flag = view2.requestFocus();
        }
          goto _L16
_L4:
label0:
        {
            if (i != 17 && i != 1)
            {
                break label0;
            }
            flag = pageLeft();
        }
          goto _L16
        if (i != 66 && i != 2) goto _L15; else goto _L17
_L17:
        flag = pageRight();
          goto _L16
_L15:
        flag = false;
          goto _L16
_L8:
        view1 = view;
          goto _L13
        flag1 = false;
          goto _L18
    }

    public boolean beginFakeDrag()
    {
        if (mIsBeingDragged)
        {
            return false;
        }
        mFakeDragging = true;
        setScrollState(1);
        mLastMotionX = 0.0F;
        mInitialMotionX = 0.0F;
        long l;
        MotionEvent motionevent;
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        } else
        {
            mVelocityTracker.clear();
        }
        l = SystemClock.uptimeMillis();
        motionevent = MotionEvent.obtain(l, l, 0, 0.0F, 0.0F, 0);
        mVelocityTracker.addMovement(motionevent);
        motionevent.recycle();
        mFakeDragBeginTime = l;
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
_L8:
        if (j1 < 0) goto _L2; else goto _L3
_L3:
        View view1 = viewgroup.getChildAt(j1);
        if (j + l < view1.getLeft() || j + l >= view1.getRight() || k + i1 < view1.getTop() || k + i1 >= view1.getBottom() || !canScroll(view1, true, i, (j + l) - view1.getLeft(), (k + i1) - view1.getTop())) goto _L5; else goto _L4
_L4:
        return true;
_L5:
        j1--;
        continue; /* Loop/switch isn't completed */
_L2:
        if (flag && ViewCompat.canScrollHorizontally(view, -i)) goto _L4; else goto _L6
_L6:
        return false;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public boolean canScrollHorizontally(int i)
    {
        if (mAdapter != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int j;
        int k;
        j = getClientWidth();
        k = getScrollX();
        if (i >= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (k <= (int)((float)j * mFirstOffset)) goto _L1; else goto _L3
_L3:
        return true;
        if (i <= 0 || k >= (int)((float)j * mLastOffset)) goto _L1; else goto _L4
_L4:
        return true;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return (layoutparams instanceof LayoutParams) && super.checkLayoutParams(layoutparams);
    }

    public void computeScroll()
    {
        if (!mScroller.isFinished() && mScroller.computeScrollOffset())
        {
            int i = getScrollX();
            int j = getScrollY();
            int k = mScroller.getCurrX();
            int l = mScroller.getCurrY();
            if (i != k || j != l)
            {
                scrollTo(k, l);
                if (!pageScrolled(k))
                {
                    mScroller.abortAnimation();
                    scrollTo(0, l);
                }
            }
            ViewCompat.postInvalidateOnAnimation(this);
            return;
        } else
        {
            completeScroll(true);
            return;
        }
    }

    void dataSetChanged()
    {
        boolean flag1;
        int k;
        boolean flag2;
        int l;
        ItemInfo iteminfo;
        int k1;
        int i = mAdapter.getCount();
        mExpectedAdapterCount = i;
        boolean flag;
        int j;
        int j2;
        int l2;
        if (mItems.size() < 1 + (mOffscreenPageLimit << 1) && mItems.size() < i)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        j = mCurItem;
        flag1 = false;
        k = j;
        flag2 = flag;
        l = 0;
        if (l >= mItems.size())
        {
            break; /* Loop/switch isn't completed */
        }
        iteminfo = (ItemInfo)mItems.get(l);
        k1 = mAdapter.getItemPosition(iteminfo.object);
        if (k1 == -1)
        {
            break MISSING_BLOCK_LABEL_379;
        }
        if (k1 == -2)
        {
            mItems.remove(l);
            int k2 = l - 1;
            if (!flag1)
            {
                mAdapter.startUpdate(this);
                flag1 = true;
            }
            mAdapter.destroyItem(this, iteminfo.position, iteminfo.object);
            int i1;
            int j1;
            LayoutParams layoutparams;
            int l1;
            boolean flag3;
            int i2;
            boolean flag4;
            if (mCurItem == iteminfo.position)
            {
                l2 = Math.max(0, Math.min(mCurItem, i - 1));
                l1 = k2;
                flag3 = flag1;
                i2 = l2;
                flag4 = true;
            } else
            {
                l1 = k2;
                flag3 = flag1;
                i2 = k;
                flag4 = true;
            }
        } else
        {
            if (iteminfo.position == k1)
            {
                break MISSING_BLOCK_LABEL_379;
            }
            if (iteminfo.position == mCurItem)
            {
                k = k1;
            }
            iteminfo.position = k1;
            l1 = l;
            flag3 = flag1;
            i2 = k;
            flag4 = true;
        }
        j2 = l1 + 1;
        flag2 = flag4;
        k = i2;
        flag1 = flag3;
        l = j2;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_61;
_L1:
        if (flag1)
        {
            mAdapter.finishUpdate(this);
        }
        Collections.sort(mItems, COMPARATOR);
        if (flag2)
        {
            i1 = getChildCount();
            for (j1 = 0; j1 < i1; j1++)
            {
                layoutparams = (LayoutParams)getChildAt(j1).getLayoutParams();
                if (!layoutparams.isDecor)
                {
                    layoutparams.widthFactor = 0.0F;
                }
            }

            setCurrentItemInternal(k, false, true);
            requestLayout();
        }
        return;
        l1 = l;
        flag3 = flag1;
        i2 = k;
        flag4 = flag2;
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_207;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        return super.dispatchKeyEvent(keyevent) || executeKeyEvent(keyevent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if (accessibilityevent.getEventType() != 4096) goto _L2; else goto _L1
_L1:
        boolean flag = super.dispatchPopulateAccessibilityEvent(accessibilityevent);
_L4:
        return flag;
_L2:
        int i = getChildCount();
        int j = 0;
        do
        {
            flag = false;
            if (j >= i)
            {
                continue;
            }
            View view = getChildAt(j);
            if (view.getVisibility() == 0)
            {
                ItemInfo iteminfo = infoForChild(view);
                if (iteminfo != null && iteminfo.position == mCurItem && view.dispatchPopulateAccessibilityEvent(accessibilityevent))
                {
                    return true;
                }
            }
            j++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    float distanceInfluenceForSnapDuration(float f)
    {
        return (float)Math.sin((float)(0.4712389167638204D * (double)(f - 0.5F)));
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        int i = ViewCompat.getOverScrollMode(this);
        boolean flag1;
        if (i == 0 || i == 1 && mAdapter != null && mAdapter.getCount() > 1)
        {
            boolean flag = mLeftEdge.isFinished();
            flag1 = false;
            if (!flag)
            {
                int i1 = canvas.save();
                int j1 = getHeight() - getPaddingTop() - getPaddingBottom();
                int k1 = getWidth();
                canvas.rotate(270F);
                canvas.translate(-j1 + getPaddingTop(), mFirstOffset * (float)k1);
                mLeftEdge.setSize(j1, k1);
                flag1 = false | mLeftEdge.draw(canvas);
                canvas.restoreToCount(i1);
            }
            if (!mRightEdge.isFinished())
            {
                int j = canvas.save();
                int k = getWidth();
                int l = getHeight() - getPaddingTop() - getPaddingBottom();
                canvas.rotate(90F);
                canvas.translate(-getPaddingTop(), -(1.0F + mLastOffset) * (float)k);
                mRightEdge.setSize(l, k);
                flag1 |= mRightEdge.draw(canvas);
                canvas.restoreToCount(j);
            }
        } else
        {
            mLeftEdge.finish();
            mRightEdge.finish();
            flag1 = false;
        }
        if (flag1)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = mMarginDrawable;
        if (drawable != null && drawable.isStateful())
        {
            drawable.setState(getDrawableState());
        }
    }

    public void endFakeDrag()
    {
        if (!mFakeDragging)
        {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        } else
        {
            VelocityTracker velocitytracker = mVelocityTracker;
            velocitytracker.computeCurrentVelocity(1000, mMaximumVelocity);
            int i = (int)VelocityTrackerCompat.getXVelocity(velocitytracker, mActivePointerId);
            mPopulatePending = true;
            int j = getClientWidth();
            int k = getScrollX();
            ItemInfo iteminfo = infoForCurrentScrollPosition();
            setCurrentItemInternal(determineTargetPage(iteminfo.position, ((float)k / (float)j - iteminfo.offset) / iteminfo.widthFactor, i, (int)(mLastMotionX - mInitialMotionX)), true, true, i);
            endDrag();
            mFakeDragging = false;
            return;
        }
    }

    public boolean executeKeyEvent(KeyEvent keyevent)
    {
        if (keyevent.getAction() != 0) goto _L2; else goto _L1
_L1:
        keyevent.getKeyCode();
        JVM INSTR lookupswitch 3: default 44
    //                   21: 46
    //                   22: 53
    //                   61: 60;
           goto _L2 _L3 _L4 _L5
_L2:
        return false;
_L3:
        return arrowScroll(17);
_L4:
        return arrowScroll(66);
_L5:
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            if (KeyEventCompat.hasNoModifiers(keyevent))
            {
                return arrowScroll(2);
            }
            if (KeyEventCompat.hasModifiers(keyevent, 1))
            {
                return arrowScroll(1);
            }
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    public void fakeDragBy(float f)
    {
        if (!mFakeDragging)
        {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        mLastMotionX = f + mLastMotionX;
        float f1 = (float)getScrollX() - f;
        int i = getClientWidth();
        float f2 = (float)i * mFirstOffset;
        float f3 = (float)i * mLastOffset;
        ItemInfo iteminfo = (ItemInfo)mItems.get(0);
        ItemInfo iteminfo1 = (ItemInfo)mItems.get(-1 + mItems.size());
        float f4;
        float f5;
        long l;
        MotionEvent motionevent;
        if (iteminfo.position != 0)
        {
            f4 = iteminfo.offset * (float)i;
        } else
        {
            f4 = f2;
        }
        if (iteminfo1.position != -1 + mAdapter.getCount())
        {
            f5 = iteminfo1.offset * (float)i;
        } else
        {
            f5 = f3;
        }
        if (f1 >= f4)
        {
            if (f1 > f5)
            {
                f4 = f5;
            } else
            {
                f4 = f1;
            }
        }
        mLastMotionX = mLastMotionX + (f4 - (float)(int)f4);
        scrollTo((int)f4, getScrollY());
        pageScrolled((int)f4);
        l = SystemClock.uptimeMillis();
        motionevent = MotionEvent.obtain(mFakeDragBeginTime, l, 2, mLastMotionX, 0.0F, 0);
        mVelocityTracker.addMovement(motionevent);
        motionevent.recycle();
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
        return generateDefaultLayoutParams();
    }

    public PagerAdapter getAdapter()
    {
        return mAdapter;
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        if (mDrawingOrder == 2)
        {
            j = i - 1 - j;
        }
        return ((LayoutParams)((View)mDrawingOrderedChildren.get(j)).getLayoutParams()).childIndex;
    }

    public int getCurrentItem()
    {
        return mCurItem;
    }

    public int getOffscreenPageLimit()
    {
        return mOffscreenPageLimit;
    }

    public int getPageMargin()
    {
        return mPageMargin;
    }

    ItemInfo infoForAnyChild(View view)
    {
        do
        {
            ViewParent viewparent = view.getParent();
            if (viewparent != this)
            {
                if (viewparent == null || !(viewparent instanceof View))
                {
                    return null;
                }
                view = (View)viewparent;
            } else
            {
                return infoForChild(view);
            }
        } while (true);
    }

    ItemInfo infoForChild(View view)
    {
        for (int i = 0; i < mItems.size(); i++)
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(i);
            if (mAdapter.isViewFromObject(view, iteminfo.object))
            {
                return iteminfo;
            }
        }

        return null;
    }

    ItemInfo infoForPosition(int i)
    {
        for (int j = 0; j < mItems.size(); j++)
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(j);
            if (iteminfo.position == i)
            {
                return iteminfo;
            }
        }

        return null;
    }

    void initViewPager()
    {
        setWillNotDraw(false);
        setDescendantFocusability(0x40000);
        setFocusable(true);
        Context context = getContext();
        mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewconfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(viewconfiguration);
        mMinimumVelocity = (int)(400F * f);
        mMaximumVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        mLeftEdge = new EdgeEffectCompat(context);
        mRightEdge = new EdgeEffectCompat(context);
        mFlingDistance = (int)(25F * f);
        mCloseEnough = (int)(2.0F * f);
        mDefaultGutterSize = (int)(16F * f);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
        if (ViewCompat.getImportantForAccessibility(this) == 0)
        {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
    }

    public boolean isFakeDragging()
    {
        return mFakeDragging;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mFirstLayout = true;
    }

    protected void onDetachedFromWindow()
    {
        removeCallbacks(mEndScrollRunnable);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (mPageMargin > 0 && mMarginDrawable != null && mItems.size() > 0 && mAdapter != null)
        {
            int i = getScrollX();
            int j = getWidth();
            float f = (float)mPageMargin / (float)j;
            ItemInfo iteminfo = (ItemInfo)mItems.get(0);
            float f1 = iteminfo.offset;
            int k = mItems.size();
            int l = iteminfo.position;
            int i1 = ((ItemInfo)mItems.get(k - 1)).position;
            int j1 = 0;
            int k1 = l;
            do
            {
                if (k1 >= i1)
                {
                    break;
                }
                ArrayList arraylist;
                for (; k1 > iteminfo.position && j1 < k; iteminfo = (ItemInfo)arraylist.get(j1))
                {
                    arraylist = mItems;
                    j1++;
                }

                float f3;
                if (k1 == iteminfo.position)
                {
                    f3 = (iteminfo.offset + iteminfo.widthFactor) * (float)j;
                    f1 = f + (iteminfo.offset + iteminfo.widthFactor);
                } else
                {
                    float f2 = mAdapter.getPageWidth(k1);
                    f3 = (f1 + f2) * (float)j;
                    f1 += f2 + f;
                }
                if (f3 + (float)mPageMargin > (float)i)
                {
                    mMarginDrawable.setBounds((int)f3, mTopPageBounds, (int)(0.5F + (f3 + (float)mPageMargin)), mBottomPageBounds);
                    mMarginDrawable.draw(canvas);
                }
                if (f3 > (float)(i + j))
                {
                    break;
                }
                k1++;
            } while (true);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i = 0xff & motionevent.getAction();
        if (i != 3 && i != 1) goto _L2; else goto _L1
_L1:
        mIsBeingDragged = false;
        mIsUnableToDrag = false;
        mActivePointerId = -1;
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
_L4:
        return false;
_L2:
        if (i == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (mIsBeingDragged)
        {
            return true;
        }
        if (mIsUnableToDrag) goto _L4; else goto _L3
_L3:
        i;
        JVM INSTR lookupswitch 3: default 112
    //                   0: 391
    //                   2: 139
    //                   6: 526;
           goto _L5 _L6 _L7 _L8
_L5:
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        return mIsBeingDragged;
_L7:
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        int j = mActivePointerId;
        if (j == -1)
        {
            continue; /* Loop/switch isn't completed */
        }
        int k = MotionEventCompat.findPointerIndex(motionevent, j);
        f2 = MotionEventCompat.getX(motionevent, k);
        f3 = f2 - mLastMotionX;
        f4 = Math.abs(f3);
        f5 = MotionEventCompat.getY(motionevent, k);
        f6 = Math.abs(f5 - mInitialMotionY);
        if (f3 != 0.0F && !isGutterDrag(mLastMotionX, f3) && canScroll(this, false, (int)f3, (int)f2, (int)f5))
        {
            mLastMotionX = f2;
            mLastMotionY = f5;
            mIsUnableToDrag = true;
            return false;
        }
        if (f4 <= (float)mTouchSlop || 0.5F * f4 <= f6) goto _L10; else goto _L9
_L9:
        mIsBeingDragged = true;
        requestParentDisallowInterceptTouchEvent(true);
        setScrollState(1);
        float f7;
        if (f3 > 0.0F)
        {
            f7 = mInitialMotionX + (float)mTouchSlop;
        } else
        {
            f7 = mInitialMotionX - (float)mTouchSlop;
        }
        mLastMotionX = f7;
        mLastMotionY = f5;
        setScrollingCacheEnabled(true);
_L11:
        if (mIsBeingDragged && performDrag(f2))
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if (f6 > (float)mTouchSlop)
        {
            mIsUnableToDrag = true;
        }
        if (true) goto _L11; else goto _L6
_L6:
        float f = motionevent.getX();
        mInitialMotionX = f;
        mLastMotionX = f;
        float f1 = motionevent.getY();
        mInitialMotionY = f1;
        mLastMotionY = f1;
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, 0);
        mIsUnableToDrag = false;
        mScroller.computeScrollOffset();
        if (mScrollState == 2 && Math.abs(mScroller.getFinalX() - mScroller.getCurrX()) > mCloseEnough)
        {
            mScroller.abortAnimation();
            mPopulatePending = false;
            populate();
            mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            setScrollState(1);
        } else
        {
            completeScroll(false);
            mIsBeingDragged = false;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        onSecondaryPointerUp(motionevent);
        if (true) goto _L5; else goto _L12
_L12:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        i1 = getChildCount();
        j1 = k - i;
        k1 = l - j;
        l1 = getPaddingLeft();
        i2 = getPaddingTop();
        j2 = getPaddingRight();
        k2 = getPaddingBottom();
        l2 = getScrollX();
        i3 = 0;
        j3 = 0;
_L14:
        if (j3 >= i1) goto _L2; else goto _L1
_L1:
        View view1 = getChildAt(j3);
        if (view1.getVisibility() == 8) goto _L4; else goto _L3
_L3:
        LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
        if (!layoutparams1.isDecor) goto _L4; else goto _L5
_L5:
        int j5;
        int k5;
        j5 = 7 & layoutparams1.gravity;
        k5 = 0x70 & layoutparams1.gravity;
        j5;
        JVM INSTR tableswitch 1 5: default 152
    //                   1 305
    //                   2 152
    //                   3 284
    //                   4 152
    //                   5 325;
           goto _L6 _L7 _L6 _L8 _L6 _L9
_L6:
        int i6 = l1;
_L15:
        k5;
        JVM INSTR lookupswitch 3: default 192
    //                   16: 384
    //                   48: 355
    //                   80: 416;
           goto _L10 _L11 _L12 _L13
_L10:
        int j6;
        int l6;
        int i7;
        j6 = i2;
        int j8 = k2;
        l6 = i2;
        i7 = j8;
_L16:
        int j4;
        int k4;
        int l4;
        int i5;
        int j7 = i6 + l2;
        view1.layout(j7, j6, j7 + view1.getMeasuredWidth(), j6 + view1.getMeasuredHeight());
        j4 = i3 + 1;
        k4 = l6;
        k2 = i7;
        l4 = j2;
        i5 = l1;
_L17:
        j3++;
        l1 = i5;
        j2 = l4;
        i2 = k4;
        i3 = j4;
          goto _L14
_L8:
        int k8 = l1 + view1.getMeasuredWidth();
        i6 = l1;
        l1 = k8;
          goto _L15
_L7:
        i6 = Math.max((j1 - view1.getMeasuredWidth()) / 2, l1);
          goto _L15
_L9:
        int l5 = j1 - j2 - view1.getMeasuredWidth();
        j2 += view1.getMeasuredWidth();
        i6 = l5;
          goto _L15
_L12:
        int l7 = i2 + view1.getMeasuredHeight();
        int i8 = i2;
        i7 = k2;
        l6 = l7;
        j6 = i8;
          goto _L16
_L11:
        j6 = Math.max((k1 - view1.getMeasuredHeight()) / 2, i2);
        int k7 = k2;
        l6 = i2;
        i7 = k7;
          goto _L16
_L13:
        j6 = k1 - k2 - view1.getMeasuredHeight();
        int k6 = k2 + view1.getMeasuredHeight();
        l6 = i2;
        i7 = k6;
          goto _L16
_L2:
        int k3 = j1 - l1 - j2;
        for (int l3 = 0; l3 < i1; l3++)
        {
            View view = getChildAt(l3);
            if (view.getVisibility() == 8)
            {
                continue;
            }
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            if (layoutparams.isDecor)
            {
                continue;
            }
            ItemInfo iteminfo = infoForChild(view);
            if (iteminfo == null)
            {
                continue;
            }
            int i4 = l1 + (int)((float)k3 * iteminfo.offset);
            if (layoutparams.needsMeasure)
            {
                layoutparams.needsMeasure = false;
                view.measure(android.view.View.MeasureSpec.makeMeasureSpec((int)((float)k3 * layoutparams.widthFactor), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(k1 - i2 - k2, 0x40000000));
            }
            view.layout(i4, i2, i4 + view.getMeasuredWidth(), i2 + view.getMeasuredHeight());
        }

        mTopPageBounds = i2;
        mBottomPageBounds = k1 - k2;
        mDecorChildCount = i3;
        if (mFirstLayout)
        {
            scrollToItem(mCurItem, false, 0, false);
        }
        mFirstLayout = false;
        return;
_L4:
        j4 = i3;
        k4 = i2;
        l4 = j2;
        i5 = l1;
          goto _L17
    }

    protected void onMeasure(int i, int j)
    {
        int l;
        int i1;
        int j1;
        int k1;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, j));
        int k = getMeasuredWidth();
        mGutterSize = Math.min(k / 10, mDefaultGutterSize);
        l = k - getPaddingLeft() - getPaddingRight();
        i1 = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        j1 = getChildCount();
        k1 = 0;
_L3:
        if (k1 >= j1) goto _L2; else goto _L1
_L1:
        View view1 = getChildAt(k1);
        if (view1.getVisibility() == 8)
        {
            continue; /* Loop/switch isn't completed */
        }
        LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
        if (layoutparams1 == null || !layoutparams1.isDecor)
        {
            continue; /* Loop/switch isn't completed */
        }
        int j2 = 7 & layoutparams1.gravity;
        int k2 = 0x70 & layoutparams1.gravity;
        int l2 = 0x80000000;
        int i3 = 0x80000000;
        boolean flag;
        boolean flag1;
        int j3;
        int k3;
        if (k2 == 48 || k2 == 80)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (j2 == 3 || j2 == 5)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag)
        {
            l2 = 0x40000000;
        } else
        if (flag1)
        {
            i3 = 0x40000000;
        }
        if (layoutparams1.width != -2)
        {
            j3 = 0x40000000;
            int l1;
            int i2;
            View view;
            LayoutParams layoutparams;
            int l3;
            if (layoutparams1.width != -1)
            {
                k3 = layoutparams1.width;
            } else
            {
                k3 = l;
            }
        } else
        {
            j3 = l2;
            k3 = l;
        }
        if (layoutparams1.height == -2)
        {
            break MISSING_BLOCK_LABEL_464;
        }
        i3 = 0x40000000;
        if (layoutparams1.height == -1)
        {
            break MISSING_BLOCK_LABEL_464;
        }
        l3 = layoutparams1.height;
        view1.measure(android.view.View.MeasureSpec.makeMeasureSpec(k3, j3), android.view.View.MeasureSpec.makeMeasureSpec(l3, i3));
        if (flag)
        {
            i1 -= view1.getMeasuredHeight();
        } else
        if (flag1)
        {
            l -= view1.getMeasuredWidth();
        }
        k1++;
          goto _L3
_L2:
        mChildWidthMeasureSpec = android.view.View.MeasureSpec.makeMeasureSpec(l, 0x40000000);
        mChildHeightMeasureSpec = android.view.View.MeasureSpec.makeMeasureSpec(i1, 0x40000000);
        mInLayout = true;
        populate();
        mInLayout = false;
        l1 = getChildCount();
        for (i2 = 0; i2 < l1; i2++)
        {
            view = getChildAt(i2);
            if (view.getVisibility() == 8)
            {
                continue;
            }
            layoutparams = (LayoutParams)view.getLayoutParams();
            if (layoutparams == null || !layoutparams.isDecor)
            {
                view.measure(android.view.View.MeasureSpec.makeMeasureSpec((int)((float)l * layoutparams.widthFactor), 0x40000000), mChildHeightMeasureSpec);
            }
        }

        return;
        l3 = i1;
        break MISSING_BLOCK_LABEL_254;
    }

    protected void onPageScrolled(int i, float f, int j)
    {
        if (mDecorChildCount <= 0) goto _L2; else goto _L1
_L1:
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        j1 = getScrollX();
        k1 = getPaddingLeft();
        l1 = getPaddingRight();
        i2 = getWidth();
        j2 = getChildCount();
        k2 = 0;
_L8:
        if (k2 >= j2) goto _L2; else goto _L3
_L3:
        View view1;
        LayoutParams layoutparams;
        view1 = getChildAt(k2);
        layoutparams = (LayoutParams)view1.getLayoutParams();
        if (!layoutparams.isDecor)
        {
            break MISSING_BLOCK_LABEL_401;
        }
        7 & layoutparams.gravity;
        JVM INSTR tableswitch 1 5: default 116
    //                   1 204
    //                   2 116
    //                   3 175
    //                   4 116
    //                   5 236;
           goto _L4 _L5 _L4 _L6 _L4 _L7
_L4:
        int i3;
        int j3;
        int l3;
        l3 = k1;
        int j5 = l1;
        i3 = k1;
        j3 = j5;
_L9:
        int j4 = (l3 + j1) - view1.getLeft();
        if (j4 != 0)
        {
            view1.offsetLeftAndRight(j4);
        }
_L10:
        k2++;
        int k3 = j3;
        k1 = i3;
        l1 = k3;
          goto _L8
_L6:
        int l4 = k1 + view1.getWidth();
        int i5 = k1;
        j3 = l1;
        i3 = l4;
        l3 = i5;
          goto _L9
_L5:
        l3 = Math.max((i2 - view1.getMeasuredWidth()) / 2, k1);
        int k4 = l1;
        i3 = k1;
        j3 = k4;
          goto _L9
_L7:
        l3 = i2 - l1 - view1.getMeasuredWidth();
        int i4 = l1 + view1.getMeasuredWidth();
        i3 = k1;
        j3 = i4;
          goto _L9
_L2:
        if (mOnPageChangeListener != null)
        {
            mOnPageChangeListener.onPageScrolled(i, f, j);
        }
        if (mInternalPageChangeListener != null)
        {
            mInternalPageChangeListener.onPageScrolled(i, f, j);
        }
        if (mPageTransformer != null)
        {
            int k = getScrollX();
            int l = getChildCount();
            for (int i1 = 0; i1 < l; i1++)
            {
                View view = getChildAt(i1);
                if (!((LayoutParams)view.getLayoutParams()).isDecor)
                {
                    float f1 = (float)(view.getLeft() - k) / (float)getClientWidth();
                    mPageTransformer.transformPage(view, f1);
                }
            }

        }
        mCalledSuper = true;
        return;
        int l2 = l1;
        i3 = k1;
        j3 = l2;
          goto _L10
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect)
    {
        byte byte0 = -1;
        int j = getChildCount();
        int k;
        if ((i & 2) != 0)
        {
            byte0 = 1;
            k = 0;
        } else
        {
            k = j - 1;
            j = byte0;
        }
        for (; k != j; k += byte0)
        {
            View view = getChildAt(k);
            if (view.getVisibility() != 0)
            {
                continue;
            }
            ItemInfo iteminfo = infoForChild(view);
            if (iteminfo != null && iteminfo.position == mCurItem && view.requestFocus(i, rect))
            {
                return true;
            }
        }

        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        if (mAdapter != null)
        {
            mAdapter.restoreState(savedstate.adapterState, savedstate.loader);
            setCurrentItemInternal(savedstate.position, false, true);
            return;
        } else
        {
            mRestoredCurItem = savedstate.position;
            mRestoredAdapterState = savedstate.adapterState;
            mRestoredClassLoader = savedstate.loader;
            return;
        }
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.position = mCurItem;
        if (mAdapter != null)
        {
            savedstate.adapterState = mAdapter.saveState();
        }
        return savedstate;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if (i != k)
        {
            recomputeScrollPosition(i, k, mPageMargin, mPageMargin);
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag;
        if (mFakeDragging)
        {
            return true;
        }
        if (motionevent.getAction() == 0 && motionevent.getEdgeFlags() != 0)
        {
            return false;
        }
        if (mAdapter == null || mAdapter.getCount() == 0)
        {
            return false;
        }
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        i = 0xff & motionevent.getAction();
        flag = false;
        i;
        JVM INSTR tableswitch 0 6: default 120
    //                   0 130
    //                   1 397
    //                   2 196
    //                   3 548
    //                   4 120
    //                   5 600
    //                   6 631;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7
_L1:
        if (flag)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
_L2:
        mScroller.abortAnimation();
        mPopulatePending = false;
        populate();
        float f5 = motionevent.getX();
        mInitialMotionX = f5;
        mLastMotionX = f5;
        float f6 = motionevent.getY();
        mInitialMotionY = f6;
        mLastMotionY = f6;
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, 0);
        flag = false;
        continue; /* Loop/switch isn't completed */
_L4:
        if (!mIsBeingDragged)
        {
            int j1 = MotionEventCompat.findPointerIndex(motionevent, mActivePointerId);
            float f = MotionEventCompat.getX(motionevent, j1);
            float f1 = Math.abs(f - mLastMotionX);
            float f2 = MotionEventCompat.getY(motionevent, j1);
            float f3 = Math.abs(f2 - mLastMotionY);
            if (f1 > (float)mTouchSlop && f1 > f3)
            {
                mIsBeingDragged = true;
                requestParentDisallowInterceptTouchEvent(true);
                boolean flag3;
                float f4;
                ViewParent viewparent;
                if (f - mInitialMotionX > 0.0F)
                {
                    f4 = mInitialMotionX + (float)mTouchSlop;
                } else
                {
                    f4 = mInitialMotionX - (float)mTouchSlop;
                }
                mLastMotionX = f4;
                mLastMotionY = f2;
                setScrollState(1);
                setScrollingCacheEnabled(true);
                viewparent = getParent();
                if (viewparent != null)
                {
                    viewparent.requestDisallowInterceptTouchEvent(true);
                }
            }
        }
        flag3 = mIsBeingDragged;
        flag = false;
        if (flag3)
        {
            flag = false | performDrag(MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId)));
        }
        continue; /* Loop/switch isn't completed */
_L3:
        boolean flag2 = mIsBeingDragged;
        flag = false;
        if (flag2)
        {
            VelocityTracker velocitytracker = mVelocityTracker;
            velocitytracker.computeCurrentVelocity(1000, mMaximumVelocity);
            int k = (int)VelocityTrackerCompat.getXVelocity(velocitytracker, mActivePointerId);
            mPopulatePending = true;
            int l = getClientWidth();
            int i1 = getScrollX();
            ItemInfo iteminfo = infoForCurrentScrollPosition();
            setCurrentItemInternal(determineTargetPage(iteminfo.position, ((float)i1 / (float)l - iteminfo.offset) / iteminfo.widthFactor, k, (int)(MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId)) - mInitialMotionX)), true, true, k);
            mActivePointerId = -1;
            endDrag();
            flag = mLeftEdge.onRelease() | mRightEdge.onRelease();
        }
        continue; /* Loop/switch isn't completed */
_L5:
        boolean flag1 = mIsBeingDragged;
        flag = false;
        if (flag1)
        {
            scrollToItem(mCurItem, true, 0, false);
            mActivePointerId = -1;
            endDrag();
            flag = mLeftEdge.onRelease() | mRightEdge.onRelease();
        }
        continue; /* Loop/switch isn't completed */
_L6:
        int j = MotionEventCompat.getActionIndex(motionevent);
        mLastMotionX = MotionEventCompat.getX(motionevent, j);
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, j);
        flag = false;
        continue; /* Loop/switch isn't completed */
_L7:
        onSecondaryPointerUp(motionevent);
        mLastMotionX = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId));
        flag = false;
        if (true) goto _L1; else goto _L8
_L8:
    }

    boolean pageLeft()
    {
        if (mCurItem > 0)
        {
            setCurrentItem(-1 + mCurItem, true);
            return true;
        } else
        {
            return false;
        }
    }

    boolean pageRight()
    {
        if (mAdapter != null && mCurItem < -1 + mAdapter.getCount())
        {
            setCurrentItem(1 + mCurItem, true);
            return true;
        } else
        {
            return false;
        }
    }

    void populate()
    {
        populate(mCurItem);
    }

    void populate(int i)
    {
        byte byte0;
        ItemInfo iteminfo;
        int k;
        int l;
        int i1;
        int j1;
        ItemInfo iteminfo1;
        ItemInfo iteminfo2;
        ItemInfo iteminfo6;
        int l2;
        float f1;
        int j3;
        int k3;
        int l3;
        android.content.res.Resources.NotFoundException notfoundexception;
        String s;
        String s1;
        if (mCurItem != i)
        {
            byte byte1;
            ItemInfo iteminfo15;
            if (mCurItem < i)
            {
                byte1 = 66;
            } else
            {
                byte1 = 17;
            }
            iteminfo15 = infoForPosition(mCurItem);
            mCurItem = i;
            iteminfo = iteminfo15;
            byte0 = byte1;
        } else
        {
            byte0 = 2;
            iteminfo = null;
        }
        if (mAdapter != null) goto _L2; else goto _L1
_L1:
        sortChildDrawingOrder();
_L4:
        return;
_L2:
        if (mPopulatePending)
        {
            sortChildDrawingOrder();
            return;
        }
        if (getWindowToken() == null) goto _L4; else goto _L3
_L3:
        mAdapter.startUpdate(this);
        int j = mOffscreenPageLimit;
        k = Math.max(0, mCurItem - j);
        l = mAdapter.getCount();
        i1 = Math.min(l - 1, j + mCurItem);
        if (l == mExpectedAdapterCount) goto _L6; else goto _L5
_L5:
        s1 = getResources().getResourceName(getId());
        s = s1;
_L7:
        throw new IllegalStateException((new StringBuilder("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ")).append(mExpectedAdapterCount).append(", found: ").append(l).append(" Pager id: ").append(s).append(" Pager class: ").append(getClass()).append(" Problematic adapter: ").append(mAdapter.getClass()).toString());
        notfoundexception;
        s = Integer.toHexString(getId());
        if (true) goto _L7; else goto _L6
_L6:
        j1 = 0;
_L17:
        if (j1 >= mItems.size())
        {
            break MISSING_BLOCK_LABEL_1295;
        }
        iteminfo1 = (ItemInfo)mItems.get(j1);
        if (iteminfo1.position < mCurItem) goto _L9; else goto _L8
_L8:
        if (iteminfo1.position != mCurItem)
        {
            break MISSING_BLOCK_LABEL_1295;
        }
_L24:
        if (iteminfo1 == null && l > 0)
        {
            iteminfo2 = addNewItem(mCurItem, j1);
        } else
        {
            iteminfo2 = iteminfo1;
        }
        if (iteminfo2 == null) goto _L11; else goto _L10
_L10:
        int k2 = j1 - 1;
        float f;
        int i3;
        if (k2 >= 0)
        {
            iteminfo6 = (ItemInfo)mItems.get(k2);
        } else
        {
            iteminfo6 = null;
        }
        l2 = getClientWidth();
        if (l2 <= 0)
        {
            f = 0.0F;
        } else
        {
            f = (2.0F - iteminfo2.widthFactor) + (float)getPaddingLeft() / (float)l2;
        }
        i3 = -1 + mCurItem;
        f1 = 0.0F;
        j3 = i3;
        k3 = j1;
        l3 = k2;
        if (j3 < 0) goto _L13; else goto _L12
_L12:
        if (f1 < f || j3 >= k) goto _L15; else goto _L14
_L14:
        if (iteminfo6 == null) goto _L13; else goto _L16
_L16:
        if (j3 == iteminfo6.position && !iteminfo6.scrolling)
        {
            mItems.remove(l3);
            mAdapter.destroyItem(this, j3, iteminfo6.object);
            l3--;
            k3--;
            if (l3 >= 0)
            {
                iteminfo6 = (ItemInfo)mItems.get(l3);
            } else
            {
                iteminfo6 = null;
            }
        }
_L18:
        j3--;
        break MISSING_BLOCK_LABEL_392;
_L9:
        j1++;
          goto _L17
_L15:
        if (iteminfo6 != null && j3 == iteminfo6.position)
        {
            f1 += iteminfo6.widthFactor;
            if (--l3 >= 0)
            {
                iteminfo6 = (ItemInfo)mItems.get(l3);
            } else
            {
                iteminfo6 = null;
            }
        } else
        {
            f1 += addNewItem(j3, l3 + 1).widthFactor;
            k3++;
            if (l3 >= 0)
            {
                iteminfo6 = (ItemInfo)mItems.get(l3);
            } else
            {
                iteminfo6 = null;
            }
        }
          goto _L18
_L13:
        float f2 = iteminfo2.widthFactor;
        int i4 = k3 + 1;
        if (f2 >= 2.0F)
        {
            break MISSING_BLOCK_LABEL_1005;
        }
        ItemInfo iteminfo7;
        float f3;
        int j4;
        ItemInfo iteminfo8;
        int k4;
        int l4;
        ItemInfo iteminfo9;
        if (i4 < mItems.size())
        {
            iteminfo7 = (ItemInfo)mItems.get(i4);
        } else
        {
            iteminfo7 = null;
        }
        if (l2 <= 0)
        {
            f3 = 0.0F;
        } else
        {
            f3 = 2.0F + (float)getPaddingRight() / (float)l2;
        }
        j4 = 1 + mCurItem;
        iteminfo8 = iteminfo7;
        k4 = i4;
        l4 = j4;
        iteminfo9 = iteminfo8;
        do
        {
            if (l4 >= l)
            {
                break MISSING_BLOCK_LABEL_1005;
            }
            if (f2 >= f3 && l4 > i1)
            {
                if (iteminfo9 == null)
                {
                    break MISSING_BLOCK_LABEL_1005;
                }
                PagerAdapter pageradapter;
                int k1;
                Object obj;
                int l1;
                int i2;
                View view;
                ItemInfo iteminfo3;
                int j2;
                View view1;
                ItemInfo iteminfo4;
                View view2;
                LayoutParams layoutparams;
                ItemInfo iteminfo5;
                ItemInfo iteminfo10;
                float f4;
                ItemInfo iteminfo11;
                ItemInfo iteminfo12;
                float f5;
                float f7;
                ItemInfo iteminfo13;
                if (l4 == iteminfo9.position && !iteminfo9.scrolling)
                {
                    mItems.remove(k4);
                    mAdapter.destroyItem(this, l4, iteminfo9.object);
                    float f6;
                    ItemInfo iteminfo14;
                    float f9;
                    if (k4 < mItems.size())
                    {
                        iteminfo14 = (ItemInfo)mItems.get(k4);
                    } else
                    {
                        iteminfo14 = null;
                    }
                    f9 = f2;
                    iteminfo12 = iteminfo14;
                    f5 = f9;
                } else
                {
                    float f8 = f2;
                    iteminfo12 = iteminfo9;
                    f5 = f8;
                }
            } else
            if (iteminfo9 != null && l4 == iteminfo9.position)
            {
                f7 = f2 + iteminfo9.widthFactor;
                if (++k4 < mItems.size())
                {
                    iteminfo13 = (ItemInfo)mItems.get(k4);
                } else
                {
                    iteminfo13 = null;
                }
                iteminfo12 = iteminfo13;
                f5 = f7;
            } else
            {
                iteminfo10 = addNewItem(l4, k4);
                k4++;
                f4 = f2 + iteminfo10.widthFactor;
                if (k4 < mItems.size())
                {
                    iteminfo11 = (ItemInfo)mItems.get(k4);
                } else
                {
                    iteminfo11 = null;
                }
                iteminfo12 = iteminfo11;
                f5 = f4;
            }
            l4++;
            f6 = f5;
            iteminfo9 = iteminfo12;
            f2 = f6;
        } while (true);
          goto _L17
        calculatePageOffsets(iteminfo2, k3, iteminfo);
_L11:
        pageradapter = mAdapter;
        k1 = mCurItem;
        if (iteminfo2 != null)
        {
            obj = iteminfo2.object;
        } else
        {
            obj = null;
        }
        pageradapter.setPrimaryItem(this, k1, obj);
        mAdapter.finishUpdate(this);
        l1 = getChildCount();
        for (i2 = 0; i2 < l1; i2++)
        {
            view2 = getChildAt(i2);
            layoutparams = (LayoutParams)view2.getLayoutParams();
            layoutparams.childIndex = i2;
            if (layoutparams.isDecor || layoutparams.widthFactor != 0.0F)
            {
                continue;
            }
            iteminfo5 = infoForChild(view2);
            if (iteminfo5 != null)
            {
                layoutparams.widthFactor = iteminfo5.widthFactor;
                layoutparams.position = iteminfo5.position;
            }
        }

        sortChildDrawingOrder();
        if (!hasFocus()) goto _L4; else goto _L19
_L19:
        view = findFocus();
        if (view != null)
        {
            iteminfo3 = infoForAnyChild(view);
        } else
        {
            iteminfo3 = null;
        }
        if (iteminfo3 != null && iteminfo3.position == mCurItem) goto _L4; else goto _L20
_L20:
        j2 = 0;
_L23:
        if (j2 >= getChildCount()) goto _L4; else goto _L21
_L21:
        view1 = getChildAt(j2);
        iteminfo4 = infoForChild(view1);
        if (iteminfo4 != null && iteminfo4.position == mCurItem && view1.requestFocus(byte0)) goto _L4; else goto _L22
_L22:
        j2++;
          goto _L23
        iteminfo1 = null;
          goto _L24
    }

    public void removeView(View view)
    {
        if (mInLayout)
        {
            removeViewInLayout(view);
            return;
        } else
        {
            super.removeView(view);
            return;
        }
    }

    public void setAdapter(PagerAdapter pageradapter)
    {
        if (mAdapter != null)
        {
            mAdapter.unregisterDataSetObserver(mObserver);
            mAdapter.startUpdate(this);
            for (int i = 0; i < mItems.size(); i++)
            {
                ItemInfo iteminfo = (ItemInfo)mItems.get(i);
                mAdapter.destroyItem(this, iteminfo.position, iteminfo.object);
            }

            mAdapter.finishUpdate(this);
            mItems.clear();
            removeNonDecorViews();
            mCurItem = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pageradapter1 = mAdapter;
        mAdapter = pageradapter;
        mExpectedAdapterCount = 0;
        if (mAdapter != null)
        {
            if (mObserver == null)
            {
                mObserver = new PagerObserver(null);
            }
            mAdapter.registerDataSetObserver(mObserver);
            mPopulatePending = false;
            boolean flag = mFirstLayout;
            mFirstLayout = true;
            mExpectedAdapterCount = mAdapter.getCount();
            if (mRestoredCurItem >= 0)
            {
                mAdapter.restoreState(mRestoredAdapterState, mRestoredClassLoader);
                setCurrentItemInternal(mRestoredCurItem, false, true);
                mRestoredCurItem = -1;
                mRestoredAdapterState = null;
                mRestoredClassLoader = null;
            } else
            if (!flag)
            {
                populate();
            } else
            {
                requestLayout();
            }
        }
        if (mAdapterChangeListener != null && pageradapter1 != pageradapter)
        {
            mAdapterChangeListener.onAdapterChanged(pageradapter1, pageradapter);
        }
    }

    void setChildrenDrawingOrderEnabledCompat(boolean flag)
    {
        if (android.os.Build.VERSION.SDK_INT < 7)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        Method method;
        Object aobj[];
        if (mSetChildrenDrawingOrderEnabled == null)
        {
            try
            {
                Class aclass[] = new Class[1];
                aclass[0] = Boolean.TYPE;
                mSetChildrenDrawingOrderEnabled = android/view/ViewGroup.getDeclaredMethod("setChildrenDrawingOrderEnabled", aclass);
            }
            catch (NoSuchMethodException nosuchmethodexception)
            {
                Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", nosuchmethodexception);
            }
        }
        method = mSetChildrenDrawingOrderEnabled;
        aobj = new Object[1];
        aobj[0] = Boolean.valueOf(flag);
        method.invoke(this, aobj);
        return;
        Exception exception;
        exception;
        Log.e("ViewPager", "Error changing children drawing order", exception);
        return;
    }

    public void setCurrentItem(int i)
    {
        mPopulatePending = false;
        boolean flag;
        if (!mFirstLayout)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setCurrentItemInternal(i, flag, false);
    }

    public void setCurrentItem(int i, boolean flag)
    {
        mPopulatePending = false;
        setCurrentItemInternal(i, flag, false);
    }

    void setCurrentItemInternal(int i, boolean flag, boolean flag1)
    {
        setCurrentItemInternal(i, flag, flag1, 0);
    }

    void setCurrentItemInternal(int i, boolean flag, boolean flag1, int j)
    {
        if (mAdapter == null || mAdapter.getCount() <= 0)
        {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!flag1 && mCurItem == i && mItems.size() != 0)
        {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i >= 0) goto _L2; else goto _L1
_L1:
        i = 0;
_L4:
        int k = mOffscreenPageLimit;
        if (i > k + mCurItem || i < mCurItem - k)
        {
            for (int l = 0; l < mItems.size(); l++)
            {
                ((ItemInfo)mItems.get(l)).scrolling = true;
            }

        }
        break; /* Loop/switch isn't completed */
_L2:
        if (i >= mAdapter.getCount())
        {
            i = -1 + mAdapter.getCount();
        }
        if (true) goto _L4; else goto _L3
_L3:
        int i1 = mCurItem;
        boolean flag2 = false;
        if (i1 != i)
        {
            flag2 = true;
        }
        if (mFirstLayout)
        {
            mCurItem = i;
            if (flag2 && mOnPageChangeListener != null)
            {
                mOnPageChangeListener.onPageSelected(i);
            }
            if (flag2 && mInternalPageChangeListener != null)
            {
                mInternalPageChangeListener.onPageSelected(i);
            }
            requestLayout();
            return;
        } else
        {
            populate(i);
            scrollToItem(i, flag, j, flag2);
            return;
        }
    }

    OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener onpagechangelistener)
    {
        OnPageChangeListener onpagechangelistener1 = mInternalPageChangeListener;
        mInternalPageChangeListener = onpagechangelistener;
        return onpagechangelistener1;
    }

    public void setOffscreenPageLimit(int i)
    {
        if (i <= 0)
        {
            Log.w("ViewPager", (new StringBuilder("Requested offscreen page limit ")).append(i).append(" too small; defaulting to 1").toString());
            i = 1;
        }
        if (i != mOffscreenPageLimit)
        {
            mOffscreenPageLimit = i;
            populate();
        }
    }

    void setOnAdapterChangeListener(OnAdapterChangeListener onadapterchangelistener)
    {
        mAdapterChangeListener = onadapterchangelistener;
    }

    public void setOnPageChangeListener(OnPageChangeListener onpagechangelistener)
    {
        mOnPageChangeListener = onpagechangelistener;
    }

    public void setPageMargin(int i)
    {
        int j = mPageMargin;
        mPageMargin = i;
        int k = getWidth();
        recomputeScrollPosition(k, k, i, j);
        requestLayout();
    }

    public void setPageMarginDrawable(int i)
    {
        setPageMarginDrawable(getContext().getResources().getDrawable(i));
    }

    public void setPageMarginDrawable(Drawable drawable)
    {
        mMarginDrawable = drawable;
        if (drawable != null)
        {
            refreshDrawableState();
        }
        boolean flag;
        if (drawable == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setWillNotDraw(flag);
        invalidate();
    }

    public void setPageTransformer(boolean flag, PageTransformer pagetransformer)
    {
        int i = 1;
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            boolean flag1;
            int j;
            int k;
            if (pagetransformer != null)
            {
                flag1 = i;
            } else
            {
                flag1 = false;
            }
            if (mPageTransformer != null)
            {
                j = i;
            } else
            {
                j = 0;
            }
            if (flag1 != j)
            {
                k = i;
            } else
            {
                k = 0;
            }
            mPageTransformer = pagetransformer;
            setChildrenDrawingOrderEnabledCompat(flag1);
            if (flag1)
            {
                if (flag)
                {
                    i = 2;
                }
                mDrawingOrder = i;
            } else
            {
                mDrawingOrder = 0;
            }
            if (k != 0)
            {
                populate();
            }
        }
    }

    void smoothScrollTo(int i, int j)
    {
        smoothScrollTo(i, j, 0);
    }

    void smoothScrollTo(int i, int j, int k)
    {
        if (getChildCount() == 0)
        {
            setScrollingCacheEnabled(false);
            return;
        }
        int l = getScrollX();
        int i1 = getScrollY();
        int j1 = i - l;
        int k1 = j - i1;
        if (j1 == 0 && k1 == 0)
        {
            completeScroll(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int l1 = getClientWidth();
        int i2 = l1 / 2;
        float f = Math.min(1.0F, (1.0F * (float)Math.abs(j1)) / (float)l1);
        float f1 = (float)i2 + (float)i2 * distanceInfluenceForSnapDuration(f);
        int j2 = Math.abs(k);
        int k2;
        int l2;
        if (j2 > 0)
        {
            k2 = 4 * Math.round(1000F * Math.abs(f1 / (float)j2));
        } else
        {
            float f2 = (float)l1 * mAdapter.getPageWidth(mCurItem);
            k2 = (int)(100F * (1.0F + (float)Math.abs(j1) / (f2 + (float)mPageMargin)));
        }
        l2 = Math.min(k2, 600);
        mScroller.startScroll(l, i1, j1, k1, l2);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return super.verifyDrawable(drawable) || drawable == mMarginDrawable;
    }






    private class ItemInfo
    {

        Object object;
        float offset;
        int position;
        boolean scrolling;
        float widthFactor;

        ItemInfo()
        {
        }
    }


    private class _cls3
        implements Runnable
    {

        final ViewPager this$0;

        public void run()
        {
            setScrollState(0);
            populate();
        }

        _cls3()
        {
            this$0 = ViewPager.this;
            super();
        }
    }


    private class LayoutParams extends android.view.ViewGroup.LayoutParams
    {

        int childIndex;
        public int gravity;
        public boolean isDecor;
        boolean needsMeasure;
        int position;
        float widthFactor;

        public LayoutParams()
        {
            super(-1, -1);
            widthFactor = 0.0F;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            widthFactor = 0.0F;
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, ViewPager.LAYOUT_ATTRS);
            gravity = typedarray.getInteger(0, 48);
            typedarray.recycle();
        }
    }


    private class OnPageChangeListener
    {

        public abstract void onPageScrollStateChanged(int i);

        public abstract void onPageScrolled(int i, float f, int j);

        public abstract void onPageSelected(int i);
    }


    private class Decor
    {
    }


    private class MyAccessibilityDelegate extends AccessibilityDelegateCompat
    {

        final ViewPager this$0;

        private boolean canScroll()
        {
            return mAdapter != null && mAdapter.getCount() > 1;
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(view, accessibilityevent);
            accessibilityevent.setClassName(android/support/v4/view/ViewPager.getName());
            AccessibilityRecordCompat accessibilityrecordcompat = AccessibilityRecordCompat.obtain();
            accessibilityrecordcompat.setScrollable(canScroll());
            if (accessibilityevent.getEventType() == 4096 && mAdapter != null)
            {
                accessibilityrecordcompat.setItemCount(mAdapter.getCount());
                accessibilityrecordcompat.setFromIndex(mCurItem);
                accessibilityrecordcompat.setToIndex(mCurItem);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            accessibilitynodeinfocompat.setClassName(android/support/v4/view/ViewPager.getName());
            accessibilitynodeinfocompat.setScrollable(canScroll());
            if (canScrollHorizontally(1))
            {
                accessibilitynodeinfocompat.addAction(4096);
            }
            if (canScrollHorizontally(-1))
            {
                accessibilitynodeinfocompat.addAction(8192);
            }
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            if (super.performAccessibilityAction(view, i, bundle))
            {
                return true;
            }
            switch (i)
            {
            default:
                return false;

            case 4096: 
                if (canScrollHorizontally(1))
                {
                    setCurrentItem(1 + mCurItem);
                    return true;
                } else
                {
                    return false;
                }

            case 8192: 
                break;
            }
            if (canScrollHorizontally(-1))
            {
                setCurrentItem(-1 + mCurItem);
                return true;
            } else
            {
                return false;
            }
        }

        MyAccessibilityDelegate()
        {
            this$0 = ViewPager.this;
            super();
        }
    }


    private class PageTransformer
    {

        public abstract void transformPage(View view, float f);
    }


    private class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = ParcelableCompat.newCreator(new _cls1());
        Parcelable adapterState;
        ClassLoader loader;
        int position;

        public String toString()
        {
            return (new StringBuilder("FragmentPager.SavedState{")).append(Integer.toHexString(System.identityHashCode(this))).append(" position=").append(position).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(position);
            parcel.writeParcelable(adapterState, i);
        }


        SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel);
            if (classloader == null)
            {
                classloader = getClass().getClassLoader();
            }
            position = parcel.readInt();
            adapterState = parcel.readParcelable(classloader);
            loader = classloader;
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }

        class _cls1
            implements ParcelableCompatCreatorCallbacks
        {

            public final SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public final volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
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


    private class PagerObserver extends DataSetObserver
    {

        final ViewPager this$0;

        public void onChanged()
        {
            dataSetChanged();
        }

        public void onInvalidated()
        {
            dataSetChanged();
        }

        private PagerObserver()
        {
            this$0 = ViewPager.this;
            super();
        }

        PagerObserver(_cls1 _pcls1)
        {
            this();
        }
    }


    private class OnAdapterChangeListener
    {

        public abstract void onAdapterChanged(PagerAdapter pageradapter, PagerAdapter pageradapter1);
    }


    private class _cls1
        implements Comparator
    {

        public final int compare(ItemInfo iteminfo, ItemInfo iteminfo1)
        {
            return iteminfo.position - iteminfo1.position;
        }

        public final volatile int compare(Object obj, Object obj1)
        {
            return compare((ItemInfo)obj, (ItemInfo)obj1);
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements Interpolator
    {

        public final float getInterpolation(float f)
        {
            float f1 = f - 1.0F;
            return 1.0F + f1 * (f1 * (f1 * (f1 * f1)));
        }

        _cls2()
        {
        }
    }


    private class ViewPositionComparator
        implements Comparator
    {

        public int compare(View view, View view1)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
            if (layoutparams.isDecor != layoutparams1.isDecor)
            {
                return !layoutparams.isDecor ? -1 : 1;
            } else
            {
                return layoutparams.position - layoutparams1.position;
            }
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((View)obj, (View)obj1);
        }

        ViewPositionComparator()
        {
        }
    }

}
