// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v7.internal.view.menu.ActionMenuItem;
import android.support.v7.internal.view.menu.ActionMenuPresenter;
import android.support.v7.internal.view.menu.ActionMenuView;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

// Referenced classes of package android.support.v7.internal.widget:
//            AbsActionBarView, SpinnerICS, ProgressBarICS, ScrollingTabContainerView, 
//            ActionBarContextView, ActionBarContainer

public class ActionBarView extends AbsActionBarView
{

    private static final int DEFAULT_CUSTOM_GRAVITY = 19;
    public static final int DISPLAY_DEFAULT = 0;
    private static final int DISPLAY_RELAYOUT_MASK = 31;
    private static final String TAG = "ActionBarView";
    private android.support.v7.app.ActionBar.OnNavigationListener mCallback;
    private Context mContext;
    private ActionBarContextView mContextView;
    private View mCustomNavView;
    private int mDisplayOptions;
    View mExpandedActionView;
    private final android.view.View.OnClickListener mExpandedActionViewUpListener = new _cls2();
    private HomeView mExpandedHomeLayout;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private HomeView mHomeLayout;
    private Drawable mIcon;
    private boolean mIncludeTabs;
    private int mIndeterminateProgressStyle;
    private ProgressBarICS mIndeterminateProgressView;
    private boolean mIsCollapsable;
    private boolean mIsCollapsed;
    private int mItemPadding;
    private LinearLayout mListNavLayout;
    private Drawable mLogo;
    private ActionMenuItem mLogoNavItem;
    private final AdapterViewICS.OnItemSelectedListener mNavItemSelectedListener = new _cls1();
    private int mNavigationMode;
    private MenuBuilder mOptionsMenu;
    private int mProgressBarPadding;
    private int mProgressStyle;
    private ProgressBarICS mProgressView;
    private SpinnerICS mSpinner;
    private SpinnerAdapter mSpinnerAdapter;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private ScrollingTabContainerView mTabScrollView;
    private Runnable mTabSelector;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private int mTitleStyleRes;
    private View mTitleUpView;
    private TextView mTitleView;
    private final android.view.View.OnClickListener mUpClickListener = new _cls3();
    private boolean mUserTitle;
    android.view.Window.Callback mWindowCallback;

    public ActionBarView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mDisplayOptions = -1;
        mContext = context;
        setBackgroundResource(0);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ActionBar, android.support.v7.appcompat.R.attr.actionBarStyle, 0);
        ApplicationInfo applicationinfo = context.getApplicationInfo();
        PackageManager packagemanager = context.getPackageManager();
        mNavigationMode = typedarray.getInt(2, 0);
        mTitle = typedarray.getText(1);
        mSubtitle = typedarray.getText(4);
        mLogo = typedarray.getDrawable(8);
        if (mLogo == null && android.os.Build.VERSION.SDK_INT >= 9)
        {
            LayoutInflater layoutinflater;
            int i;
            int j;
            if (context instanceof Activity)
            {
                try
                {
                    mLogo = packagemanager.getActivityLogo(((Activity)context).getComponentName());
                }
                catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception1)
                {
                    Log.e("ActionBarView", "Activity component name not found!", namenotfoundexception1);
                }
            }
            if (mLogo == null)
            {
                mLogo = applicationinfo.loadLogo(packagemanager);
            }
        }
        mIcon = typedarray.getDrawable(7);
        if (mIcon == null)
        {
            if (context instanceof Activity)
            {
                try
                {
                    mIcon = packagemanager.getActivityIcon(((Activity)context).getComponentName());
                }
                catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
                {
                    Log.e("ActionBarView", "Activity component name not found!", namenotfoundexception);
                }
            }
            if (mIcon == null)
            {
                mIcon = applicationinfo.loadIcon(packagemanager);
            }
        }
        layoutinflater = LayoutInflater.from(context);
        i = typedarray.getResourceId(14, android.support.v7.appcompat.R.layout.abc_action_bar_home);
        mHomeLayout = (HomeView)layoutinflater.inflate(i, this, false);
        mExpandedHomeLayout = (HomeView)layoutinflater.inflate(i, this, false);
        mExpandedHomeLayout.setUp(true);
        mExpandedHomeLayout.setOnClickListener(mExpandedActionViewUpListener);
        mExpandedHomeLayout.setContentDescription(getResources().getText(android.support.v7.appcompat.R.string.abc_action_bar_up_description));
        mTitleStyleRes = typedarray.getResourceId(5, 0);
        mSubtitleStyleRes = typedarray.getResourceId(6, 0);
        mProgressStyle = typedarray.getResourceId(15, 0);
        mIndeterminateProgressStyle = typedarray.getResourceId(16, 0);
        mProgressBarPadding = typedarray.getDimensionPixelOffset(17, 0);
        mItemPadding = typedarray.getDimensionPixelOffset(18, 0);
        setDisplayOptions(typedarray.getInt(3, 0));
        j = typedarray.getResourceId(13, 0);
        if (j != 0)
        {
            mCustomNavView = layoutinflater.inflate(j, this, false);
            mNavigationMode = 0;
            setDisplayOptions(0x10 | mDisplayOptions);
        }
        mContentHeight = typedarray.getLayoutDimension(0, 0);
        typedarray.recycle();
        mLogoNavItem = new ActionMenuItem(context, 0, 0x102002c, 0, 0, mTitle);
        mHomeLayout.setOnClickListener(mUpClickListener);
        mHomeLayout.setClickable(true);
        mHomeLayout.setFocusable(true);
    }

    private void configPresenters(MenuBuilder menubuilder)
    {
        if (menubuilder != null)
        {
            menubuilder.addMenuPresenter(mActionMenuPresenter);
            menubuilder.addMenuPresenter(mExpandedMenuPresenter);
        } else
        {
            mActionMenuPresenter.initForMenu(mContext, null);
            mExpandedMenuPresenter.initForMenu(mContext, null);
        }
        mActionMenuPresenter.updateMenuView(true);
        mExpandedMenuPresenter.updateMenuView(true);
    }

    private void initTitle()
    {
        boolean flag = true;
        if (mTitleLayout == null)
        {
            mTitleLayout = (LinearLayout)LayoutInflater.from(getContext()).inflate(android.support.v7.appcompat.R.layout.abc_action_bar_title_item, this, false);
            mTitleView = (TextView)mTitleLayout.findViewById(android.support.v7.appcompat.R.id.action_bar_title);
            mSubtitleView = (TextView)mTitleLayout.findViewById(android.support.v7.appcompat.R.id.action_bar_subtitle);
            mTitleUpView = mTitleLayout.findViewById(android.support.v7.appcompat.R.id.up);
            mTitleLayout.setOnClickListener(mUpClickListener);
            if (mTitleStyleRes != 0)
            {
                mTitleView.setTextAppearance(mContext, mTitleStyleRes);
            }
            if (mTitle != null)
            {
                mTitleView.setText(mTitle);
            }
            if (mSubtitleStyleRes != 0)
            {
                mSubtitleView.setTextAppearance(mContext, mSubtitleStyleRes);
            }
            if (mSubtitle != null)
            {
                mSubtitleView.setText(mSubtitle);
                mSubtitleView.setVisibility(0);
            }
            boolean flag1;
            boolean flag2;
            View view;
            int i;
            LinearLayout linearlayout;
            if ((4 & mDisplayOptions) != 0)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            if ((2 & mDisplayOptions) != 0)
            {
                flag2 = flag;
            } else
            {
                flag2 = false;
            }
            view = mTitleUpView;
            if (!flag2)
            {
                if (flag1)
                {
                    i = 0;
                } else
                {
                    i = 4;
                }
            } else
            {
                i = 8;
            }
            view.setVisibility(i);
            linearlayout = mTitleLayout;
            if (!flag1 || flag2)
            {
                flag = false;
            }
            linearlayout.setEnabled(flag);
        }
        addView(mTitleLayout);
        if (mExpandedActionView != null || TextUtils.isEmpty(mTitle) && TextUtils.isEmpty(mSubtitle))
        {
            mTitleLayout.setVisibility(8);
        }
    }

    private void setTitleImpl(CharSequence charsequence)
    {
        mTitle = charsequence;
        if (mTitleView != null)
        {
            mTitleView.setText(charsequence);
            boolean flag;
            LinearLayout linearlayout;
            int i;
            if (mExpandedActionView == null && (8 & mDisplayOptions) != 0 && (!TextUtils.isEmpty(mTitle) || !TextUtils.isEmpty(mSubtitle)))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            linearlayout = mTitleLayout;
            i = 0;
            if (!flag)
            {
                i = 8;
            }
            linearlayout.setVisibility(i);
        }
        if (mLogoNavItem != null)
        {
            mLogoNavItem.setTitle(charsequence);
        }
    }

    public volatile void animateToVisibility(int i)
    {
        super.animateToVisibility(i);
    }

    public void collapseActionView()
    {
        MenuItemImpl menuitemimpl;
        if (mExpandedMenuPresenter == null)
        {
            menuitemimpl = null;
        } else
        {
            menuitemimpl = mExpandedMenuPresenter.mCurrentExpandedItem;
        }
        if (menuitemimpl != null)
        {
            menuitemimpl.collapseActionView();
        }
    }

    public volatile void dismissPopupMenus()
    {
        super.dismissPopupMenus();
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new android.support.v7.app.ActionBar.LayoutParams(19);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new android.support.v7.app.ActionBar.LayoutParams(getContext(), attributeset);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (layoutparams == null)
        {
            layoutparams = generateDefaultLayoutParams();
        }
        return layoutparams;
    }

    public volatile int getAnimatedVisibility()
    {
        return super.getAnimatedVisibility();
    }

    public volatile int getContentHeight()
    {
        return super.getContentHeight();
    }

    public View getCustomNavigationView()
    {
        return mCustomNavView;
    }

    public int getDisplayOptions()
    {
        return mDisplayOptions;
    }

    public SpinnerAdapter getDropdownAdapter()
    {
        return mSpinnerAdapter;
    }

    public int getDropdownSelectedPosition()
    {
        return mSpinner.getSelectedItemPosition();
    }

    public int getNavigationMode()
    {
        return mNavigationMode;
    }

    public CharSequence getSubtitle()
    {
        return mSubtitle;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public boolean hasEmbeddedTabs()
    {
        return mIncludeTabs;
    }

    public boolean hasExpandedActionView()
    {
        return mExpandedMenuPresenter != null && mExpandedMenuPresenter.mCurrentExpandedItem != null;
    }

    public volatile boolean hideOverflowMenu()
    {
        return super.hideOverflowMenu();
    }

    public void initIndeterminateProgress()
    {
        mIndeterminateProgressView = new ProgressBarICS(mContext, null, 0, mIndeterminateProgressStyle);
        mIndeterminateProgressView.setId(android.support.v7.appcompat.R.id.progress_circular);
        mIndeterminateProgressView.setVisibility(8);
        addView(mIndeterminateProgressView);
    }

    public void initProgress()
    {
        mProgressView = new ProgressBarICS(mContext, null, 0, mProgressStyle);
        mProgressView.setId(android.support.v7.appcompat.R.id.progress_horizontal);
        mProgressView.setMax(10000);
        mProgressView.setVisibility(8);
        addView(mProgressView);
    }

    public boolean isCollapsed()
    {
        return mIsCollapsed;
    }

    public volatile boolean isOverflowMenuShowing()
    {
        return super.isOverflowMenuShowing();
    }

    public volatile boolean isOverflowReserved()
    {
        return super.isOverflowReserved();
    }

    public boolean isSplitActionBar()
    {
        return mSplitActionBar;
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        mTitleView = null;
        mSubtitleView = null;
        mTitleUpView = null;
        if (mTitleLayout != null && mTitleLayout.getParent() == this)
        {
            removeView(mTitleLayout);
        }
        mTitleLayout = null;
        if ((8 & mDisplayOptions) != 0)
        {
            initTitle();
        }
        if (mTabScrollView != null && mIncludeTabs)
        {
            android.view.ViewGroup.LayoutParams layoutparams = mTabScrollView.getLayoutParams();
            if (layoutparams != null)
            {
                layoutparams.width = -2;
                layoutparams.height = -1;
            }
            mTabScrollView.setAllowCollapse(true);
        }
        if (mProgressView != null)
        {
            removeView(mProgressView);
            initProgress();
        }
        if (mIndeterminateProgressView != null)
        {
            removeView(mIndeterminateProgressView);
            initIndeterminateProgress();
        }
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        removeCallbacks(mTabSelector);
        if (mActionMenuPresenter != null)
        {
            mActionMenuPresenter.hideOverflowMenu();
            mActionMenuPresenter.hideSubMenus();
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        addView(mHomeLayout);
        if (mCustomNavView != null && (0x10 & mDisplayOptions) != 0)
        {
            android.view.ViewParent viewparent = mCustomNavView.getParent();
            if (viewparent != this)
            {
                if (viewparent instanceof ViewGroup)
                {
                    ((ViewGroup)viewparent).removeView(mCustomNavView);
                }
                addView(mCustomNavView);
            }
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        int j1;
        int k1;
        i1 = getPaddingLeft();
        j1 = getPaddingTop();
        k1 = l - j - getPaddingTop() - getPaddingBottom();
        if (k1 > 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        HomeView homeview;
        int l1;
        int i2;
        int j2;
        int k2;
        View view;
        int l2;
        android.view.ViewGroup.LayoutParams layoutparams;
        android.support.v7.app.ActionBar.LayoutParams layoutparams1;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        int j5;
        int l5;
        int i6;
        int j6;
        int k6;
        int l6;
        boolean flag1;
        if (mExpandedActionView != null)
        {
            homeview = mExpandedHomeLayout;
        } else
        {
            homeview = mHomeLayout;
        }
        int k5;
        if (homeview.getVisibility() != 8)
        {
            int i7 = homeview.getLeftOffset();
            l1 = i1 + (i7 + positionChild(homeview, i1 + i7, j1, k1));
        } else
        {
            l1 = i1;
        }
        if (mExpandedActionView != null) goto _L4; else goto _L3
_L3:
        if (mTitleLayout != null && mTitleLayout.getVisibility() != 8 && (8 & mDisplayOptions) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            l1 += positionChild(mTitleLayout, l1, j1, k1);
        }
        mNavigationMode;
        JVM INSTR tableswitch 0 2: default 180
    //                   0 626
    //                   1 633
    //                   2 681;
           goto _L4 _L5 _L6 _L7
_L4:
        i2 = l1;
_L17:
        j2 = k - i - getPaddingRight();
        if (mMenuView != null && mMenuView.getParent() == this)
        {
            positionChildInverse(mMenuView, j2, j1, k1);
            j2 -= mMenuView.getMeasuredWidth();
        }
        if (mIndeterminateProgressView != null && mIndeterminateProgressView.getVisibility() != 8)
        {
            positionChildInverse(mIndeterminateProgressView, j2, j1, k1);
            k2 = j2 - mIndeterminateProgressView.getMeasuredWidth();
        } else
        {
            k2 = j2;
        }
        if (mExpandedActionView != null)
        {
            view = mExpandedActionView;
        } else
        if ((0x10 & mDisplayOptions) != 0 && mCustomNavView != null)
        {
            view = mCustomNavView;
        } else
        {
            view = null;
        }
        if (view == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        layoutparams = view.getLayoutParams();
        if (layoutparams instanceof android.support.v7.app.ActionBar.LayoutParams)
        {
            layoutparams1 = (android.support.v7.app.ActionBar.LayoutParams)layoutparams;
        } else
        {
            layoutparams1 = null;
        }
        if (layoutparams1 != null)
        {
            i3 = layoutparams1.gravity;
        } else
        {
            i3 = 19;
        }
        j3 = view.getMeasuredWidth();
        if (layoutparams1 != null)
        {
            i6 = i2 + layoutparams1.leftMargin;
            j6 = k2 - layoutparams1.rightMargin;
            k6 = layoutparams1.topMargin;
            l6 = layoutparams1.bottomMargin;
            k3 = j6;
            j4 = k6;
            l3 = i6;
            i4 = l6;
        } else
        {
            k3 = k2;
            l3 = i2;
            i4 = 0;
            j4 = 0;
        }
        k4 = i3 & 7;
        if (k4 == 1)
        {
            l5 = (getWidth() - j3) / 2;
            if (l5 < l3)
            {
                l4 = 3;
            } else
            {
                if (l5 + j3 > k3)
                {
                    k4 = 5;
                }
                l4 = k4;
            }
        } else
        if (i3 == -1)
        {
            l4 = 3;
        } else
        {
            l4 = k4;
        }
        l4;
        JVM INSTR tableswitch 1 5: default 472
    //                   1 800
    //                   2 472
    //                   3 475
    //                   4 472
    //                   5 814;
           goto _L8 _L9 _L8 _L10 _L8 _L11
_L10:
        break; /* Loop/switch isn't completed */
_L8:
        l3 = 0;
_L20:
        i5 = i3 & 0x70;
        if (i3 == -1)
        {
            i5 = 16;
        }
        j5 = 0;
        i5;
        JVM INSTR lookupswitch 3: default 532
    //                   16: 824
    //                   48: 855
    //                   80: 867;
           goto _L12 _L13 _L14 _L15
_L12:
        view.layout(l3, j5, l3 + view.getMeasuredWidth(), j5 + view.getMeasuredHeight());
        if (mProgressView == null) goto _L1; else goto _L16
_L16:
        mProgressView.bringToFront();
        l2 = mProgressView.getMeasuredHeight() / 2;
        mProgressView.layout(mProgressBarPadding, -l2, mProgressBarPadding + mProgressView.getMeasuredWidth(), l2);
        return;
_L5:
        i2 = l1;
          goto _L17
_L6:
        if (mListNavLayout == null) goto _L4; else goto _L18
_L18:
        if (flag1)
        {
            l1 += mItemPadding;
        }
        i2 = l1 + (positionChild(mListNavLayout, l1, j1, k1) + mItemPadding);
          goto _L17
_L7:
        if (mTabScrollView == null) goto _L4; else goto _L19
_L19:
        if (flag1)
        {
            l1 += mItemPadding;
        }
        i2 = l1 + (positionChild(mTabScrollView, l1, j1, k1) + mItemPadding);
          goto _L17
_L9:
        l3 = (getWidth() - j3) / 2;
          goto _L20
_L11:
        l3 = k3 - j3;
          goto _L20
_L13:
        k5 = getPaddingTop();
        j5 = (getHeight() - getPaddingBottom() - k5 - view.getMeasuredHeight()) / 2;
          goto _L12
_L14:
        j5 = j4 + getPaddingTop();
          goto _L12
_L15:
        j5 = getHeight() - getPaddingBottom() - view.getMeasuredHeight() - i4;
          goto _L12
    }

    protected void onMeasure(int i, int j)
    {
        int k = getChildCount();
        if (!mIsCollapsable) goto _L2; else goto _L1
_L1:
        int i10;
        i10 = 0;
        for (int j10 = 0; j10 < k; j10++)
        {
            View view1 = getChildAt(j10);
            if (view1.getVisibility() != 8 && (view1 != mMenuView || mMenuView.getChildCount() != 0))
            {
                i10++;
            }
        }

        if (i10 != 0) goto _L2; else goto _L3
_L3:
        setMeasuredDimension(0, 0);
        mIsCollapsed = true;
_L12:
        return;
_L2:
        mIsCollapsed = false;
        if (android.view.View.MeasureSpec.getMode(i) != 0x40000000)
        {
            throw new IllegalStateException((new StringBuilder()).append(getClass().getSimpleName()).append(" can only be used with android:layout_width=\"MATCH_PARENT\" (or fill_parent)").toString());
        }
        if (android.view.View.MeasureSpec.getMode(j) != 0x80000000)
        {
            throw new IllegalStateException((new StringBuilder()).append(getClass().getSimpleName()).append(" can only be used with android:layout_height=\"wrap_content\"").toString());
        }
        int l = android.view.View.MeasureSpec.getSize(i);
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        HomeView homeview;
        int i3;
        int j3;
        boolean flag;
        int k3;
        int l3;
        View view;
        int i4;
        int k4;
        android.support.v7.app.ActionBar.LayoutParams layoutparams1;
        int j5;
        int l5;
        int i6;
        int j6;
        int k6;
        int l6;
        if (mContentHeight > 0)
        {
            i1 = mContentHeight;
        } else
        {
            i1 = android.view.View.MeasureSpec.getSize(j);
        }
        j1 = getPaddingTop() + getPaddingBottom();
        k1 = getPaddingLeft();
        l1 = getPaddingRight();
        i2 = i1 - j1;
        j2 = android.view.View.MeasureSpec.makeMeasureSpec(i2, 0x80000000);
        k2 = l - k1 - l1;
        l2 = k2 / 2;
        if (mExpandedActionView != null)
        {
            homeview = mExpandedHomeLayout;
        } else
        {
            homeview = mHomeLayout;
        }
        int i7;
        int j7;
        int k7;
        int l7;
        int i8;
        int j8;
        int k8;
        int l8;
        int i9;
        int j9;
        if (homeview.getVisibility() != 8)
        {
            android.view.ViewGroup.LayoutParams layoutparams2 = homeview.getLayoutParams();
            int j4;
            android.view.ViewGroup.LayoutParams layoutparams;
            int l4;
            int i5;
            int k5;
            int k9;
            int l9;
            if (layoutparams2.width < 0)
            {
                k9 = android.view.View.MeasureSpec.makeMeasureSpec(k2, 0x80000000);
            } else
            {
                k9 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams2.width, 0x40000000);
            }
            homeview.measure(k9, android.view.View.MeasureSpec.makeMeasureSpec(i2, 0x40000000));
            l9 = homeview.getMeasuredWidth() + homeview.getLeftOffset();
            j3 = Math.max(0, k2 - l9);
            i3 = Math.max(0, j3 - l9);
        } else
        {
            i3 = l2;
            j3 = k2;
        }
        if (mMenuView != null && mMenuView.getParent() == this)
        {
            j3 = measureChildView(mMenuView, j3, j2, 0);
            l2 = Math.max(0, l2 - mMenuView.getMeasuredWidth());
        }
        if (mIndeterminateProgressView != null && mIndeterminateProgressView.getVisibility() != 8)
        {
            j3 = measureChildView(mIndeterminateProgressView, j3, j2, 0);
            l2 = Math.max(0, l2 - mIndeterminateProgressView.getMeasuredWidth());
        }
        if (mTitleLayout != null && mTitleLayout.getVisibility() != 8 && (8 & mDisplayOptions) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (mExpandedActionView != null) goto _L5; else goto _L4
_L4:
        mNavigationMode;
        JVM INSTR tableswitch 1 2: default 516
    //                   1 916
    //                   2 1028;
           goto _L5 _L6 _L7
_L5:
        k3 = i3;
        l3 = j3;
          goto _L8
_L6:
        if (mListNavLayout == null) goto _L5; else goto _L9
_L9:
        if (flag)
        {
            j8 = mItemPadding << 1;
        } else
        {
            j8 = mItemPadding;
        }
        k8 = Math.max(0, j3 - j8);
        l8 = Math.max(0, i3 - j8);
        mListNavLayout.measure(android.view.View.MeasureSpec.makeMeasureSpec(k8, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(i2, 0x40000000));
        i9 = mListNavLayout.getMeasuredWidth();
        j9 = Math.max(0, k8 - i9);
        k3 = Math.max(0, l8 - i9);
        l3 = j9;
          goto _L8
_L7:
        if (mTabScrollView == null) goto _L5; else goto _L10
_L10:
        if (flag)
        {
            i7 = mItemPadding << 1;
        } else
        {
            i7 = mItemPadding;
        }
        j7 = Math.max(0, j3 - i7);
        k7 = Math.max(0, i3 - i7);
        mTabScrollView.measure(android.view.View.MeasureSpec.makeMeasureSpec(j7, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(i2, 0x40000000));
        l7 = mTabScrollView.getMeasuredWidth();
        i8 = Math.max(0, j7 - l7);
        k3 = Math.max(0, k7 - l7);
        l3 = i8;
_L8:
        if (mExpandedActionView != null)
        {
            view = mExpandedActionView;
        } else
        if ((0x10 & mDisplayOptions) != 0 && mCustomNavView != null)
        {
            view = mCustomNavView;
        } else
        {
            view = null;
        }
        if (view != null)
        {
            layoutparams = generateLayoutParams(view.getLayoutParams());
            if (layoutparams instanceof android.support.v7.app.ActionBar.LayoutParams)
            {
                layoutparams1 = (android.support.v7.app.ActionBar.LayoutParams)layoutparams;
            } else
            {
                layoutparams1 = null;
            }
            l4 = 0;
            i5 = 0;
            if (layoutparams1 != null)
            {
                i5 = layoutparams1.leftMargin + layoutparams1.rightMargin;
                l4 = layoutparams1.topMargin + layoutparams1.bottomMargin;
            }
            if (mContentHeight > 0 && layoutparams.height != -2)
            {
                j5 = 0x40000000;
            } else
            {
                j5 = 0x80000000;
            }
            if (layoutparams.height >= 0)
            {
                i2 = Math.min(layoutparams.height, i2);
            }
            k5 = Math.max(0, i2 - l4);
            if (layoutparams.width != -2)
            {
                l5 = 0x40000000;
            } else
            {
                l5 = 0x80000000;
            }
            if (layoutparams.width >= 0)
            {
                i6 = Math.min(layoutparams.width, l3);
            } else
            {
                i6 = l3;
            }
            j6 = Math.max(0, i6 - i5);
            if (layoutparams1 != null)
            {
                k6 = layoutparams1.gravity;
            } else
            {
                k6 = 19;
            }
            if ((k6 & 7) == 1 && layoutparams.width == -1)
            {
                l6 = Math.min(k3, l2) << 1;
            } else
            {
                l6 = j6;
            }
            view.measure(android.view.View.MeasureSpec.makeMeasureSpec(l6, l5), android.view.View.MeasureSpec.makeMeasureSpec(k5, j5));
            l3 -= i5 + view.getMeasuredWidth();
        }
        if (mExpandedActionView == null && flag)
        {
            measureChildView(mTitleLayout, l3, android.view.View.MeasureSpec.makeMeasureSpec(mContentHeight, 0x40000000), 0);
            Math.max(0, k3 - mTitleLayout.getMeasuredWidth());
        }
        if (mContentHeight > 0)
        {
            break; /* Loop/switch isn't completed */
        }
        i4 = 0;
        j4 = 0;
        while (j4 < k) 
        {
            k4 = j1 + getChildAt(j4).getMeasuredHeight();
            if (k4 <= i4)
            {
                k4 = i4;
            }
            j4++;
            i4 = k4;
        }
        setMeasuredDimension(l, i4);
_L13:
        if (mContextView != null)
        {
            mContextView.setContentHeight(getMeasuredHeight());
        }
        if (mProgressView != null && mProgressView.getVisibility() != 8)
        {
            mProgressView.measure(android.view.View.MeasureSpec.makeMeasureSpec(l - (mProgressBarPadding << 1), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0x80000000));
            return;
        }
        if (true) goto _L12; else goto _L11
_L11:
        setMeasuredDimension(l, i1);
          goto _L13
        if (true) goto _L12; else goto _L14
_L14:
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        if (savedstate.expandedMenuItemId != 0 && mExpandedMenuPresenter != null && mOptionsMenu != null)
        {
            SupportMenuItem supportmenuitem = (SupportMenuItem)mOptionsMenu.findItem(savedstate.expandedMenuItemId);
            if (supportmenuitem != null)
            {
                supportmenuitem.expandActionView();
            }
        }
        if (savedstate.isOverflowOpen)
        {
            postShowOverflowMenu();
        }
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        if (mExpandedMenuPresenter != null && mExpandedMenuPresenter.mCurrentExpandedItem != null)
        {
            savedstate.expandedMenuItemId = mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        }
        savedstate.isOverflowOpen = isOverflowMenuShowing();
        return savedstate;
    }

    public volatile void postShowOverflowMenu()
    {
        super.postShowOverflowMenu();
    }

    public void setCallback(android.support.v7.app.ActionBar.OnNavigationListener onnavigationlistener)
    {
        mCallback = onnavigationlistener;
    }

    public void setCollapsable(boolean flag)
    {
        mIsCollapsable = flag;
    }

    public volatile void setContentHeight(int i)
    {
        super.setContentHeight(i);
    }

    public void setContextView(ActionBarContextView actionbarcontextview)
    {
        mContextView = actionbarcontextview;
    }

    public void setCustomNavigationView(View view)
    {
        boolean flag;
        if ((0x10 & mDisplayOptions) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (mCustomNavView != null && flag)
        {
            removeView(mCustomNavView);
        }
        mCustomNavView = view;
        if (mCustomNavView != null && flag)
        {
            addView(mCustomNavView);
        }
    }

    public void setDisplayOptions(int i)
    {
        byte byte0 = 8;
        int j = -1;
        boolean flag = true;
        if (mDisplayOptions != j)
        {
            j = i ^ mDisplayOptions;
        }
        mDisplayOptions = i;
        if ((j & 0x1f) != 0)
        {
            boolean flag1;
            int k;
            if ((i & 2) != 0)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            if (flag1 && mExpandedActionView == null)
            {
                k = 0;
            } else
            {
                k = byte0;
            }
            mHomeLayout.setVisibility(k);
            if ((j & 4) != 0)
            {
                View view;
                LinearLayout linearlayout;
                HomeView homeview;
                boolean flag4;
                if ((i & 4) != 0)
                {
                    flag4 = flag;
                } else
                {
                    flag4 = false;
                }
                mHomeLayout.setUp(flag4);
                if (flag4)
                {
                    setHomeButtonEnabled(flag);
                }
            }
            if ((j & 1) != 0)
            {
                boolean flag3;
                Drawable drawable;
                if (mLogo != null && (i & 1) != 0)
                {
                    flag3 = flag;
                } else
                {
                    flag3 = false;
                }
                homeview = mHomeLayout;
                if (flag3)
                {
                    drawable = mLogo;
                } else
                {
                    drawable = mIcon;
                }
                homeview.setIcon(drawable);
            }
            if ((j & 8) != 0)
            {
                if ((i & 8) != 0)
                {
                    initTitle();
                } else
                {
                    removeView(mTitleLayout);
                }
            }
            if (mTitleLayout != null && (j & 6) != 0)
            {
                boolean flag2;
                if ((4 & mDisplayOptions) != 0)
                {
                    flag2 = flag;
                } else
                {
                    flag2 = false;
                }
                view = mTitleUpView;
                if (!flag1)
                {
                    if (flag2)
                    {
                        byte0 = 0;
                    } else
                    {
                        byte0 = 4;
                    }
                }
                view.setVisibility(byte0);
                linearlayout = mTitleLayout;
                if (flag1 || !flag2)
                {
                    flag = false;
                }
                linearlayout.setEnabled(flag);
            }
            if ((j & 0x10) != 0 && mCustomNavView != null)
            {
                if ((i & 0x10) != 0)
                {
                    addView(mCustomNavView);
                } else
                {
                    removeView(mCustomNavView);
                }
            }
            requestLayout();
        } else
        {
            invalidate();
        }
        if (!mHomeLayout.isEnabled())
        {
            mHomeLayout.setContentDescription(null);
            return;
        }
        if ((i & 4) != 0)
        {
            mHomeLayout.setContentDescription(mContext.getResources().getText(android.support.v7.appcompat.R.string.abc_action_bar_up_description));
            return;
        } else
        {
            mHomeLayout.setContentDescription(mContext.getResources().getText(android.support.v7.appcompat.R.string.abc_action_bar_home_description));
            return;
        }
    }

    public void setDropdownAdapter(SpinnerAdapter spinneradapter)
    {
        mSpinnerAdapter = spinneradapter;
        if (mSpinner != null)
        {
            mSpinner.setAdapter(spinneradapter);
        }
    }

    public void setDropdownSelectedPosition(int i)
    {
        mSpinner.setSelection(i);
    }

    public void setEmbeddedTabView(ScrollingTabContainerView scrollingtabcontainerview)
    {
        if (mTabScrollView != null)
        {
            removeView(mTabScrollView);
        }
        mTabScrollView = scrollingtabcontainerview;
        boolean flag;
        if (scrollingtabcontainerview != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mIncludeTabs = flag;
        if (mIncludeTabs && mNavigationMode == 2)
        {
            addView(mTabScrollView);
            android.view.ViewGroup.LayoutParams layoutparams = mTabScrollView.getLayoutParams();
            layoutparams.width = -2;
            layoutparams.height = -1;
            scrollingtabcontainerview.setAllowCollapse(true);
        }
    }

    public void setHomeAsUpIndicator(int i)
    {
        mHomeLayout.setUpIndicator(i);
    }

    public void setHomeAsUpIndicator(Drawable drawable)
    {
        mHomeLayout.setUpIndicator(drawable);
    }

    public void setHomeButtonEnabled(boolean flag)
    {
        mHomeLayout.setEnabled(flag);
        mHomeLayout.setFocusable(flag);
        if (!flag)
        {
            mHomeLayout.setContentDescription(null);
            return;
        }
        if ((4 & mDisplayOptions) != 0)
        {
            mHomeLayout.setContentDescription(mContext.getResources().getText(android.support.v7.appcompat.R.string.abc_action_bar_up_description));
            return;
        } else
        {
            mHomeLayout.setContentDescription(mContext.getResources().getText(android.support.v7.appcompat.R.string.abc_action_bar_home_description));
            return;
        }
    }

    public void setIcon(int i)
    {
        setIcon(mContext.getResources().getDrawable(i));
    }

    public void setIcon(Drawable drawable)
    {
        mIcon = drawable;
        if (drawable != null && ((1 & mDisplayOptions) == 0 || mLogo == null))
        {
            mHomeLayout.setIcon(drawable);
        }
        if (mExpandedActionView != null)
        {
            mExpandedHomeLayout.setIcon(mIcon.getConstantState().newDrawable(getResources()));
        }
    }

    public void setLogo(int i)
    {
        setLogo(mContext.getResources().getDrawable(i));
    }

    public void setLogo(Drawable drawable)
    {
        mLogo = drawable;
        if (drawable != null && (1 & mDisplayOptions) != 0)
        {
            mHomeLayout.setIcon(drawable);
        }
    }

    public void setMenu(SupportMenu supportmenu, android.support.v7.internal.view.menu.MenuPresenter.Callback callback)
    {
        if (supportmenu == mOptionsMenu)
        {
            return;
        }
        if (mOptionsMenu != null)
        {
            mOptionsMenu.removeMenuPresenter(mActionMenuPresenter);
            mOptionsMenu.removeMenuPresenter(mExpandedMenuPresenter);
        }
        MenuBuilder menubuilder = (MenuBuilder)supportmenu;
        mOptionsMenu = menubuilder;
        if (mMenuView != null)
        {
            ViewGroup viewgroup2 = (ViewGroup)mMenuView.getParent();
            if (viewgroup2 != null)
            {
                viewgroup2.removeView(mMenuView);
            }
        }
        if (mActionMenuPresenter == null)
        {
            mActionMenuPresenter = new ActionMenuPresenter(mContext);
            mActionMenuPresenter.setCallback(callback);
            mActionMenuPresenter.setId(android.support.v7.appcompat.R.id.action_menu_presenter);
            mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(null);
        }
        android.view.ViewGroup.LayoutParams layoutparams = new android.view.ViewGroup.LayoutParams(-2, -1);
        ActionMenuView actionmenuview;
        if (!mSplitActionBar)
        {
            mActionMenuPresenter.setExpandedActionViewsExclusive(getResources().getBoolean(android.support.v7.appcompat.R.bool.abc_action_bar_expanded_action_views_exclusive));
            configPresenters(menubuilder);
            actionmenuview = (ActionMenuView)mActionMenuPresenter.getMenuView(this);
            actionmenuview.initialize(menubuilder);
            ViewGroup viewgroup1 = (ViewGroup)actionmenuview.getParent();
            if (viewgroup1 != null && viewgroup1 != this)
            {
                viewgroup1.removeView(actionmenuview);
            }
            addView(actionmenuview, layoutparams);
        } else
        {
            mActionMenuPresenter.setExpandedActionViewsExclusive(false);
            mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
            mActionMenuPresenter.setItemLimit(0x7fffffff);
            layoutparams.width = -1;
            configPresenters(menubuilder);
            actionmenuview = (ActionMenuView)mActionMenuPresenter.getMenuView(this);
            if (mSplitView != null)
            {
                ViewGroup viewgroup = (ViewGroup)actionmenuview.getParent();
                if (viewgroup != null && viewgroup != mSplitView)
                {
                    viewgroup.removeView(actionmenuview);
                }
                actionmenuview.setVisibility(getAnimatedVisibility());
                mSplitView.addView(actionmenuview, layoutparams);
            } else
            {
                actionmenuview.setLayoutParams(layoutparams);
            }
        }
        mMenuView = actionmenuview;
    }

    public void setNavigationMode(int i)
    {
        int j = mNavigationMode;
        if (i == j) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 1 2: default 32
    //                   1 66
    //                   2 84;
           goto _L3 _L4 _L5
_L3:
        i;
        JVM INSTR tableswitch 1 2: default 56
    //                   1 109
    //                   2 232;
           goto _L6 _L7 _L8
_L6:
        mNavigationMode = i;
        requestLayout();
_L2:
        return;
_L4:
        if (mListNavLayout != null)
        {
            removeView(mListNavLayout);
        }
          goto _L3
_L5:
        if (mTabScrollView != null && mIncludeTabs)
        {
            removeView(mTabScrollView);
        }
          goto _L3
_L7:
        if (mSpinner == null)
        {
            mSpinner = new SpinnerICS(mContext, null, android.support.v7.appcompat.R.attr.actionDropDownStyle);
            mListNavLayout = (LinearLayout)LayoutInflater.from(mContext).inflate(android.support.v7.appcompat.R.layout.abc_action_bar_view_list_nav_layout, null);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -1);
            layoutparams.gravity = 17;
            mListNavLayout.addView(mSpinner, layoutparams);
        }
        if (mSpinner.getAdapter() != mSpinnerAdapter)
        {
            mSpinner.setAdapter(mSpinnerAdapter);
        }
        mSpinner.setOnItemSelectedListener(mNavItemSelectedListener);
        addView(mListNavLayout);
          goto _L6
_L8:
        if (mTabScrollView != null && mIncludeTabs)
        {
            addView(mTabScrollView);
        }
          goto _L6
    }

    public void setSplitActionBar(boolean flag)
    {
        if (mSplitActionBar != flag)
        {
            if (mMenuView != null)
            {
                ViewGroup viewgroup = (ViewGroup)mMenuView.getParent();
                if (viewgroup != null)
                {
                    viewgroup.removeView(mMenuView);
                }
                ActionBarContainer actionbarcontainer;
                if (flag)
                {
                    if (mSplitView != null)
                    {
                        mSplitView.addView(mMenuView);
                    }
                    mMenuView.getLayoutParams().width = -1;
                } else
                {
                    addView(mMenuView);
                    mMenuView.getLayoutParams().width = -2;
                }
                mMenuView.requestLayout();
            }
            if (mSplitView != null)
            {
                actionbarcontainer = mSplitView;
                int i;
                if (flag)
                {
                    i = 0;
                } else
                {
                    i = 8;
                }
                actionbarcontainer.setVisibility(i);
            }
            if (mActionMenuPresenter != null)
            {
                if (!flag)
                {
                    mActionMenuPresenter.setExpandedActionViewsExclusive(getResources().getBoolean(android.support.v7.appcompat.R.bool.abc_action_bar_expanded_action_views_exclusive));
                } else
                {
                    mActionMenuPresenter.setExpandedActionViewsExclusive(false);
                    mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
                    mActionMenuPresenter.setItemLimit(0x7fffffff);
                }
            }
            super.setSplitActionBar(flag);
        }
    }

    public volatile void setSplitView(ActionBarContainer actionbarcontainer)
    {
        super.setSplitView(actionbarcontainer);
    }

    public volatile void setSplitWhenNarrow(boolean flag)
    {
        super.setSplitWhenNarrow(flag);
    }

    public void setSubtitle(CharSequence charsequence)
    {
        mSubtitle = charsequence;
        if (mSubtitleView != null)
        {
            mSubtitleView.setText(charsequence);
            TextView textview = mSubtitleView;
            int i;
            boolean flag;
            LinearLayout linearlayout;
            int j;
            if (charsequence != null)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            textview.setVisibility(i);
            if (mExpandedActionView == null && (8 & mDisplayOptions) != 0 && (!TextUtils.isEmpty(mTitle) || !TextUtils.isEmpty(mSubtitle)))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            linearlayout = mTitleLayout;
            j = 0;
            if (!flag)
            {
                j = 8;
            }
            linearlayout.setVisibility(j);
        }
    }

    public void setTitle(CharSequence charsequence)
    {
        mUserTitle = true;
        setTitleImpl(charsequence);
    }

    public volatile void setVisibility(int i)
    {
        super.setVisibility(i);
    }

    public void setWindowCallback(android.view.Window.Callback callback)
    {
        mWindowCallback = callback;
    }

    public void setWindowTitle(CharSequence charsequence)
    {
        if (!mUserTitle)
        {
            setTitleImpl(charsequence);
        }
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }

    public volatile boolean showOverflowMenu()
    {
        return super.showOverflowMenu();
    }














    private class _cls1
        implements AdapterViewICS.OnItemSelectedListener
    {

        final ActionBarView this$0;

        public void onItemSelected(AdapterViewICS adapterviewics, View view, int i, long l)
        {
            if (mCallback != null)
            {
                mCallback.onNavigationItemSelected(i, l);
            }
        }

        public void onNothingSelected(AdapterViewICS adapterviewics)
        {
        }

        _cls1()
        {
            this$0 = ActionBarView.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        final ActionBarView this$0;

        public void onClick(View view)
        {
            MenuItemImpl menuitemimpl = mExpandedMenuPresenter.mCurrentExpandedItem;
            if (menuitemimpl != null)
            {
                menuitemimpl.collapseActionView();
            }
        }

        _cls2()
        {
            this$0 = ActionBarView.this;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        final ActionBarView this$0;

        public void onClick(View view)
        {
            mWindowCallback.onMenuItemSelected(0, mLogoNavItem);
        }

        _cls3()
        {
            this$0 = ActionBarView.this;
            super();
        }
    }


    private class HomeView extends FrameLayout
    {

        private Drawable mDefaultUpIndicator;
        private ImageView mIconView;
        private int mUpIndicatorRes;
        private ImageView mUpView;
        private int mUpWidth;

        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            CharSequence charsequence = getContentDescription();
            if (!TextUtils.isEmpty(charsequence))
            {
                accessibilityevent.getText().add(charsequence);
            }
            return true;
        }

        public int getLeftOffset()
        {
            if (mUpView.getVisibility() == 8)
            {
                return mUpWidth;
            } else
            {
                return 0;
            }
        }

        protected void onConfigurationChanged(Configuration configuration)
        {
            super.onConfigurationChanged(configuration);
            if (mUpIndicatorRes != 0)
            {
                setUpIndicator(mUpIndicatorRes);
            }
        }

        protected void onFinishInflate()
        {
            mUpView = (ImageView)findViewById(android.support.v7.appcompat.R.id.up);
            mIconView = (ImageView)findViewById(android.support.v7.appcompat.R.id.home);
            mDefaultUpIndicator = mUpView.getDrawable();
        }

        protected void onLayout(boolean flag, int i, int j, int k, int l)
        {
            int i1 = (l - j) / 2;
            int j1 = mUpView.getVisibility();
            int k1 = 0;
            if (j1 != 8)
            {
                android.widget.FrameLayout.LayoutParams layoutparams1 = (android.widget.FrameLayout.LayoutParams)mUpView.getLayoutParams();
                int i3 = mUpView.getMeasuredHeight();
                int j3 = mUpView.getMeasuredWidth();
                int k3 = i1 - i3 / 2;
                mUpView.layout(0, k3, j3, i3 + k3);
                int l3 = j3 + layoutparams1.leftMargin + layoutparams1.rightMargin;
                i += l3;
                k1 = l3;
            }
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)mIconView.getLayoutParams();
            int l1 = mIconView.getMeasuredHeight();
            int i2 = mIconView.getMeasuredWidth();
            int j2 = (k - i) / 2;
            int k2 = k1 + Math.max(layoutparams.leftMargin, j2 - i2 / 2);
            int l2 = Math.max(layoutparams.topMargin, i1 - l1 / 2);
            mIconView.layout(k2, l2, i2 + k2, l1 + l2);
        }

        protected void onMeasure(int i, int j)
        {
            int i1;
            int j1;
            int i2;
            int j2;
            measureChildWithMargins(mUpView, i, 0, j, 0);
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)mUpView.getLayoutParams();
            mUpWidth = layoutparams.leftMargin + mUpView.getMeasuredWidth() + layoutparams.rightMargin;
            int k;
            int l;
            android.widget.FrameLayout.LayoutParams layoutparams1;
            int k1;
            int l1;
            if (mUpView.getVisibility() == 8)
            {
                k = 0;
            } else
            {
                k = mUpWidth;
            }
            l = layoutparams.topMargin + mUpView.getMeasuredHeight() + layoutparams.bottomMargin;
            measureChildWithMargins(mIconView, i, k, j, 0);
            layoutparams1 = (android.widget.FrameLayout.LayoutParams)mIconView.getLayoutParams();
            i1 = k + (layoutparams1.leftMargin + mIconView.getMeasuredWidth() + layoutparams1.rightMargin);
            j1 = Math.max(l, layoutparams1.topMargin + mIconView.getMeasuredHeight() + layoutparams1.bottomMargin);
            k1 = android.view.View.MeasureSpec.getMode(i);
            l1 = android.view.View.MeasureSpec.getMode(j);
            i2 = android.view.View.MeasureSpec.getSize(i);
            j2 = android.view.View.MeasureSpec.getSize(j);
            k1;
            JVM INSTR lookupswitch 2: default 204
        //                       -2147483648: 258
        //                       1073741824: 208;
               goto _L1 _L2 _L3
_L1:
            i2 = i1;
_L3:
            l1;
            JVM INSTR lookupswitch 2: default 236
        //                       -2147483648: 270
        //                       1073741824: 240;
               goto _L4 _L5 _L6
_L6:
            break; /* Loop/switch isn't completed */
_L4:
            j2 = j1;
_L7:
            setMeasuredDimension(i2, j2);
            return;
_L2:
            i2 = Math.min(i1, i2);
              goto _L3
_L5:
            j2 = Math.min(j1, j2);
              goto _L7
        }

        public void setIcon(Drawable drawable)
        {
            mIconView.setImageDrawable(drawable);
        }

        public void setUp(boolean flag)
        {
            ImageView imageview = mUpView;
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            imageview.setVisibility(i);
        }

        public void setUpIndicator(int i)
        {
            mUpIndicatorRes = i;
            ImageView imageview = mUpView;
            Drawable drawable;
            if (i != 0)
            {
                drawable = getResources().getDrawable(i);
            } else
            {
                drawable = mDefaultUpIndicator;
            }
            imageview.setImageDrawable(drawable);
        }

        public void setUpIndicator(Drawable drawable)
        {
            ImageView imageview = mUpView;
            if (drawable == null)
            {
                drawable = mDefaultUpIndicator;
            }
            imageview.setImageDrawable(drawable);
            mUpIndicatorRes = 0;
        }

        public HomeView(Context context)
        {
            this(context, null);
        }

        public HomeView(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }


    private class ExpandedActionViewMenuPresenter
        implements MenuPresenter
    {

        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;
        final ActionBarView this$0;

        public boolean collapseItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
        {
            if (mExpandedActionView instanceof CollapsibleActionView)
            {
                ((CollapsibleActionView)mExpandedActionView).onActionViewCollapsed();
            }
            removeView(mExpandedActionView);
            removeView(mExpandedHomeLayout);
            mExpandedActionView = null;
            if ((2 & mDisplayOptions) != 0)
            {
                mHomeLayout.setVisibility(0);
            }
            if ((8 & mDisplayOptions) != 0)
            {
                if (mTitleLayout == null)
                {
                    initTitle();
                } else
                {
                    mTitleLayout.setVisibility(0);
                }
            }
            if (mTabScrollView != null && mNavigationMode == 2)
            {
                mTabScrollView.setVisibility(0);
            }
            if (mSpinner != null && mNavigationMode == 1)
            {
                mSpinner.setVisibility(0);
            }
            if (mCustomNavView != null && (0x10 & mDisplayOptions) != 0)
            {
                mCustomNavView.setVisibility(0);
            }
            mExpandedHomeLayout.setIcon(null);
            mCurrentExpandedItem = null;
            requestLayout();
            menuitemimpl.setActionViewExpanded(false);
            return true;
        }

        public boolean expandItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
        {
            mExpandedActionView = menuitemimpl.getActionView();
            mExpandedHomeLayout.setIcon(mIcon.getConstantState().newDrawable(getResources()));
            mCurrentExpandedItem = menuitemimpl;
            if (mExpandedActionView.getParent() != ActionBarView.this)
            {
                addView(mExpandedActionView);
            }
            if (mExpandedHomeLayout.getParent() != ActionBarView.this)
            {
                addView(mExpandedHomeLayout);
            }
            mHomeLayout.setVisibility(8);
            if (mTitleLayout != null)
            {
                mTitleLayout.setVisibility(8);
            }
            if (mTabScrollView != null)
            {
                mTabScrollView.setVisibility(8);
            }
            if (mSpinner != null)
            {
                mSpinner.setVisibility(8);
            }
            if (mCustomNavView != null)
            {
                mCustomNavView.setVisibility(8);
            }
            requestLayout();
            menuitemimpl.setActionViewExpanded(true);
            if (mExpandedActionView instanceof CollapsibleActionView)
            {
                ((CollapsibleActionView)mExpandedActionView).onActionViewExpanded();
            }
            return true;
        }

        public boolean flagActionItems()
        {
            return false;
        }

        public int getId()
        {
            return 0;
        }

        public MenuView getMenuView(ViewGroup viewgroup)
        {
            return null;
        }

        public void initForMenu(Context context, MenuBuilder menubuilder)
        {
            if (mMenu != null && mCurrentExpandedItem != null)
            {
                mMenu.collapseItemActionView(mCurrentExpandedItem);
            }
            mMenu = menubuilder;
        }

        public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
        }

        public void onRestoreInstanceState(Parcelable parcelable)
        {
        }

        public Parcelable onSaveInstanceState()
        {
            return null;
        }

        public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
        {
            return false;
        }

        public void setCallback(android.support.v7.internal.view.menu.MenuPresenter.Callback callback)
        {
        }

        public void updateMenuView(boolean flag)
        {
            if (mCurrentExpandedItem == null) goto _L2; else goto _L1
_L1:
            int i;
            int j;
            if (mMenu == null)
            {
                break MISSING_BLOCK_LABEL_78;
            }
            i = mMenu.size();
            j = 0;
_L5:
            if (j >= i)
            {
                break MISSING_BLOCK_LABEL_78;
            }
            if ((SupportMenuItem)mMenu.getItem(j) != mCurrentExpandedItem) goto _L4; else goto _L3
_L3:
            boolean flag1 = true;
_L6:
            if (!flag1)
            {
                collapseItemActionView(mMenu, mCurrentExpandedItem);
            }
_L2:
            return;
_L4:
            j++;
              goto _L5
            flag1 = false;
              goto _L6
        }

        private ExpandedActionViewMenuPresenter()
        {
            this$0 = ActionBarView.this;
            super();
        }

        ExpandedActionViewMenuPresenter(_cls1 _pcls1)
        {
            this();
        }
    }


    private class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        int expandedMenuItemId;
        boolean isOverflowOpen;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(expandedMenuItemId);
            int j;
            if (isOverflowOpen)
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
            expandedMenuItemId = parcel.readInt();
            boolean flag;
            if (parcel.readInt() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            isOverflowOpen = flag;
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
