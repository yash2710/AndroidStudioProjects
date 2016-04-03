// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Interpolator;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;

// Referenced classes of package net.simonvt.menudrawer:
//            SmoothInterpolator, h, i, FloatScroller, 
//            Position, j, LeftStaticDrawer, RightStaticDrawer, 
//            TopStaticDrawer, BottomStaticDrawer, LeftDrawer, RightDrawer, 
//            TopDrawer, BottomDrawer, BuildLayerFrameLayout, NoClickThroughFrameLayout, 
//            ColorDrawable

public abstract class MenuDrawer extends ViewGroup
{

    protected static final int ANIMATION_DELAY = 16;
    public static final int MENU_DRAG_CONTENT = 0;
    public static final int MENU_DRAG_WINDOW = 1;
    protected static final Interpolator SMOOTH_INTERPOLATOR = new SmoothInterpolator();
    public static final int STATE_CLOSED = 0;
    public static final int STATE_CLOSING = 1;
    public static final int STATE_DRAGGING = 2;
    public static final int STATE_OPEN = 8;
    public static final int STATE_OPENING = 4;
    public static final int TOUCH_MODE_BEZEL = 1;
    public static final int TOUCH_MODE_FULLSCREEN = 2;
    public static final int TOUCH_MODE_NONE;
    static final boolean a;
    private boolean b;
    private final Rect c;
    private View d;
    private int e;
    private OnDrawerStateChangeListener f;
    private Activity g;
    private FloatScroller h;
    private Runnable i;
    private android.view.ViewTreeObserver.OnScrollChangedListener j;
    protected Bitmap mActiveIndicator;
    protected int mActivePosition;
    protected final Rect mActiveRect;
    protected View mActiveView;
    protected BuildLayerFrameLayout mContentContainer;
    protected int mDrawerState;
    protected Drawable mDropShadowDrawable;
    protected boolean mDropShadowEnabled;
    protected int mDropShadowSize;
    protected boolean mHardwareLayersEnabled;
    protected boolean mIndicatorAnimating;
    protected float mIndicatorOffset;
    protected int mIndicatorStartPos;
    protected int mMaxAnimationDuration;
    protected BuildLayerFrameLayout mMenuContainer;
    protected Drawable mMenuOverlay;
    protected int mMenuSize;
    protected boolean mMenuSizeSet;
    protected boolean mMenuVisible;
    protected OnInterceptMoveEventListener mOnInterceptMoveEventListener;
    protected Bundle mState;
    protected int mTouchBezelSize;
    protected int mTouchMode;
    protected int mTouchSize;

    MenuDrawer(Activity activity, int k)
    {
        this(((Context) (activity)));
        g = activity;
        e = k;
    }

    public MenuDrawer(Context context)
    {
        this(context, ((AttributeSet) (null)));
    }

    public MenuDrawer(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, R.attr.menuDrawerStyle);
    }

    public MenuDrawer(Context context, AttributeSet attributeset, int k)
    {
        super(context, attributeset, k);
        mActiveRect = new Rect();
        c = new Rect();
        e = 0;
        mDrawerState = 0;
        mTouchMode = 1;
        mHardwareLayersEnabled = true;
        i = new h(this);
        mMaxAnimationDuration = 600;
        j = new i(this);
        initDrawer(context, attributeset, k);
    }

    private void a()
    {
        if (h.computeScrollOffset())
        {
            mIndicatorOffset = h.getCurr();
            invalidate();
            if (!h.isFinished())
            {
                postOnAnimation(i);
                return;
            }
        }
        b();
    }

    static void a(MenuDrawer menudrawer)
    {
        menudrawer.a();
    }

    public static MenuDrawer attach(Activity activity)
    {
        return attach(activity, 0);
    }

    public static MenuDrawer attach(Activity activity, int k)
    {
        return attach(activity, k, Position.LEFT);
    }

    public static MenuDrawer attach(Activity activity, int k, Position position)
    {
        return attach(activity, k, position, false);
    }

    public static MenuDrawer attach(Activity activity, int k, Position position, boolean flag)
    {
        if (flag)
        {
            Object obj;
            switch (j.a[position.ordinal()])
            {
            default:
                throw new IllegalArgumentException("position must be one of LEFT, TOP, RIGHT or BOTTOM");

            case 1: // '\001'
                obj = new LeftStaticDrawer(activity, k);
                break;

            case 2: // '\002'
                obj = new RightStaticDrawer(activity, k);
                break;

            case 3: // '\003'
                obj = new TopStaticDrawer(activity, k);
                break;

            case 4: // '\004'
                obj = new BottomStaticDrawer(activity, k);
                break;
            }
            do
            {
                ((MenuDrawer) (obj)).setId(R.id.md__drawer);
                ViewGroup viewgroup;
                switch (k)
                {
                default:
                    throw new RuntimeException((new StringBuilder("Unknown menu mode: ")).append(k).toString());

                case 0: // '\0'
                    ViewGroup viewgroup2 = (ViewGroup)activity.findViewById(0x1020002);
                    viewgroup2.removeAllViews();
                    viewgroup2.addView(((View) (obj)), -1, -1);
                    return ((MenuDrawer) (obj));

                case 1: // '\001'
                    viewgroup = (ViewGroup)activity.getWindow().getDecorView();
                    break;
                }
                ViewGroup viewgroup1 = (ViewGroup)viewgroup.getChildAt(0);
                viewgroup.removeAllViews();
                viewgroup.addView(((View) (obj)), -1, -1);
                ((MenuDrawer) (obj)).mContentContainer.addView(viewgroup1, viewgroup1.getLayoutParams());
                return ((MenuDrawer) (obj));
            } while (true);
        } else
        {
            switch (j.a[position.ordinal()])
            {
            default:
                throw new IllegalArgumentException("position must be one of LEFT, TOP, RIGHT or BOTTOM");

            case 1: // '\001'
                obj = new LeftDrawer(activity, k);
                break;

            case 2: // '\002'
                obj = new RightDrawer(activity, k);
                break;

            case 3: // '\003'
                obj = new TopDrawer(activity, k);
                break;

            case 4: // '\004'
                obj = new BottomDrawer(activity, k);
                break;
            }
            continue;
        }
    }

    public static MenuDrawer attach(Activity activity, Position position)
    {
        return attach(activity, 0, position);
    }

    static Rect b(MenuDrawer menudrawer)
    {
        return menudrawer.c;
    }

    private void b()
    {
        mIndicatorOffset = 1.0F;
        mIndicatorAnimating = false;
        invalidate();
    }

    void a(Bundle bundle)
    {
    }

    public void addView(View view, int k, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (mMenuContainer.getChildCount() == 0)
        {
            mMenuContainer.addView(view, k, layoutparams);
            return;
        }
        if (mContentContainer.getChildCount() == 0)
        {
            mContentContainer.addView(view, k, layoutparams);
            return;
        } else
        {
            throw new IllegalStateException("MenuDrawer can only hold two child views");
        }
    }

    public void closeMenu()
    {
        closeMenu(true);
    }

    public abstract void closeMenu(boolean flag);

    protected int dpToPx(int k)
    {
        return (int)(0.5F + getResources().getDisplayMetrics().density * (float)k);
    }

    protected boolean fitSystemWindows(Rect rect)
    {
        if (e == 1)
        {
            mMenuContainer.setPadding(0, rect.top, 0, 0);
        }
        return super.fitSystemWindows(rect);
    }

    public boolean getAllowIndicatorAnimation()
    {
        return b;
    }

    public ViewGroup getContentContainer()
    {
        if (e == 0)
        {
            return mContentContainer;
        } else
        {
            return (ViewGroup)findViewById(0x1020002);
        }
    }

    public int getDrawerState()
    {
        return mDrawerState;
    }

    public Drawable getDropShadow()
    {
        return mDropShadowDrawable;
    }

    protected abstract int getIndicatorStartPos();

    public ViewGroup getMenuContainer()
    {
        return mMenuContainer;
    }

    public int getMenuSize()
    {
        return mMenuSize;
    }

    public View getMenuView()
    {
        return d;
    }

    public abstract boolean getOffsetMenuEnabled();

    public abstract int getTouchBezelSize();

    public abstract int getTouchMode();

    protected void initDrawer(Context context, AttributeSet attributeset, int k)
    {
        setWillNotDraw(false);
        setFocusable(false);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.MenuDrawer, R.attr.menuDrawerStyle, R.style.Widget_MenuDrawer);
        Drawable drawable = typedarray.getDrawable(0);
        Drawable drawable1 = typedarray.getDrawable(1);
        mMenuSize = typedarray.getDimensionPixelSize(2, -1);
        boolean flag;
        int l;
        if (mMenuSize != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mMenuSizeSet = flag;
        l = typedarray.getResourceId(3, 0);
        if (l != 0)
        {
            mActiveIndicator = BitmapFactoryInstrumentation.decodeResource(getResources(), l);
        }
        mDropShadowEnabled = typedarray.getBoolean(4, true);
        mDropShadowDrawable = typedarray.getDrawable(7);
        if (mDropShadowDrawable == null)
        {
            setDropShadowColor(typedarray.getColor(6, 0xff000000));
        }
        mDropShadowSize = typedarray.getDimensionPixelSize(5, dpToPx(6));
        mTouchBezelSize = typedarray.getDimensionPixelSize(8, dpToPx(24));
        b = typedarray.getBoolean(9, false);
        mMaxAnimationDuration = typedarray.getInt(10, 600);
        typedarray.recycle();
        mMenuContainer = new BuildLayerFrameLayout(context);
        mMenuContainer.setId(R.id.md__menu);
        mMenuContainer.setBackgroundDrawable(drawable1);
        super.addView(mMenuContainer, -1, new android.view.ViewGroup.LayoutParams(-1, -1));
        mContentContainer = new NoClickThroughFrameLayout(context);
        mContentContainer.setId(R.id.md__content);
        mContentContainer.setBackgroundDrawable(drawable);
        super.addView(mContentContainer, -1, new android.view.ViewGroup.LayoutParams(-1, -1));
        mMenuOverlay = new ColorDrawable(0xff000000);
        h = new FloatScroller(SMOOTH_INTERPOLATOR);
    }

    public abstract boolean isMenuVisible();

    protected boolean isViewDescendant(View view)
    {
        for (ViewParent viewparent = view.getParent(); viewparent != null; viewparent = viewparent.getParent())
        {
            if (viewparent == this)
            {
                return true;
            }
        }

        return false;
    }

    protected void logDrawerState(int k)
    {
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnScrollChangedListener(j);
    }

    protected void onDetachedFromWindow()
    {
        getViewTreeObserver().removeOnScrollChangedListener(j);
        super.onDetachedFromWindow();
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        restoreState(savedstate.a);
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        if (mState == null)
        {
            mState = new Bundle();
        }
        a(mState);
        savedstate.a = mState;
        return savedstate;
    }

    public void openMenu()
    {
        openMenu(true);
    }

    public abstract void openMenu(boolean flag);

    public abstract void peekDrawer();

    public abstract void peekDrawer(long l);

    public abstract void peekDrawer(long l, long l1);

    public void postOnAnimation(Runnable runnable)
    {
        if (android.os.Build.VERSION.SDK_INT >= 16)
        {
            super.postOnAnimation(runnable);
            return;
        } else
        {
            postDelayed(runnable, 16L);
            return;
        }
    }

    public void restoreState(Parcelable parcelable)
    {
        mState = (Bundle)parcelable;
    }

    public final Parcelable saveState()
    {
        if (mState == null)
        {
            mState = new Bundle();
        }
        a(mState);
        return mState;
    }

    public void setActiveView(View view)
    {
        setActiveView(view, 0);
    }

    public void setActiveView(View view, int k)
    {
        View view1 = mActiveView;
        mActiveView = view;
        mActivePosition = k;
        if (b && view1 != null)
        {
            mIndicatorStartPos = getIndicatorStartPos();
            mIndicatorAnimating = true;
            h.startScroll(0.0F, 1.0F, 800);
            a();
        }
        invalidate();
    }

    public void setAllowIndicatorAnimation(boolean flag)
    {
        if (flag != b)
        {
            b = flag;
            b();
        }
    }

    public void setContentView(int k)
    {
        switch (e)
        {
        default:
            return;

        case 0: // '\0'
            mContentContainer.removeAllViews();
            LayoutInflater.from(getContext()).inflate(k, mContentContainer, true);
            return;

        case 1: // '\001'
            g.setContentView(k);
            break;
        }
    }

    public void setContentView(View view)
    {
        setContentView(view, new android.view.ViewGroup.LayoutParams(-1, -1));
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        switch (e)
        {
        default:
            return;

        case 0: // '\0'
            mContentContainer.removeAllViews();
            mContentContainer.addView(view, layoutparams);
            return;

        case 1: // '\001'
            g.setContentView(view, layoutparams);
            break;
        }
    }

    protected void setDrawerState(int k)
    {
        if (k != mDrawerState)
        {
            int l = mDrawerState;
            mDrawerState = k;
            if (f != null)
            {
                f.onDrawerStateChange(l, k);
            }
        }
    }

    public void setDropShadow(int k)
    {
        setDropShadow(getResources().getDrawable(k));
    }

    public void setDropShadow(Drawable drawable)
    {
        mDropShadowDrawable = drawable;
        invalidate();
    }

    public abstract void setDropShadowColor(int k);

    public void setDropShadowEnabled(boolean flag)
    {
        mDropShadowEnabled = flag;
        invalidate();
    }

    public void setDropShadowSize(int k)
    {
        mDropShadowSize = k;
        invalidate();
    }

    public abstract void setHardwareLayerEnabled(boolean flag);

    public void setMaxAnimationDuration(int k)
    {
        mMaxAnimationDuration = k;
    }

    public abstract void setMenuSize(int k);

    public void setMenuView(int k)
    {
        mMenuContainer.removeAllViews();
        d = LayoutInflater.from(getContext()).inflate(k, mMenuContainer, false);
        mMenuContainer.addView(d);
    }

    public void setMenuView(View view)
    {
        setMenuView(view, new android.view.ViewGroup.LayoutParams(-1, -1));
    }

    public void setMenuView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        d = view;
        mMenuContainer.removeAllViews();
        mMenuContainer.addView(view, layoutparams);
    }

    public abstract void setOffsetMenuEnabled(boolean flag);

    public void setOnDrawerStateChangeListener(OnDrawerStateChangeListener ondrawerstatechangelistener)
    {
        f = ondrawerstatechangelistener;
    }

    public void setOnInterceptMoveEventListener(OnInterceptMoveEventListener oninterceptmoveeventlistener)
    {
        mOnInterceptMoveEventListener = oninterceptmoveeventlistener;
    }

    public abstract void setTouchBezelSize(int k);

    public abstract void setTouchMode(int k);

    public void toggleMenu()
    {
        toggleMenu(true);
    }

    public abstract void toggleMenu(boolean flag);

    static 
    {
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT >= 12)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        a = flag;
    }

    private class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new k();
        Bundle a;

        public void writeToParcel(Parcel parcel, int l)
        {
            super.writeToParcel(parcel, l);
            parcel.writeBundle(a);
        }


        public SavedState(Parcel parcel)
        {
            super(parcel);
            a = parcel.readBundle();
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    private class OnDrawerStateChangeListener
    {

        public abstract void onDrawerStateChange(int k, int l);
    }

}
