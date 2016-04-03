// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.internal.widget.LinearLayoutICS;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.support.v7.internal.view.menu:
//            MenuView, ActionMenuItemView, MenuBuilder, ActionMenuPresenter, 
//            MenuItemImpl

public class ActionMenuView extends LinearLayoutICS
    implements MenuBuilder.ItemInvoker, MenuView
{

    static final int GENERATED_ITEM_PADDING = 4;
    static final int MIN_CELL_SIZE = 56;
    private static final String TAG = "ActionMenuView";
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private int mMaxItemHeight;
    private int mMeasuredExtraWidth;
    private MenuBuilder mMenu;
    private int mMinCellSize;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    public ActionMenuView(Context context)
    {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        mMinCellSize = (int)(56F * f);
        mGeneratedItemPadding = (int)(f * 4F);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ActionBar, android.support.v7.appcompat.R.attr.actionBarStyle, 0);
        mMaxItemHeight = typedarray.getDimensionPixelSize(0, 0);
        typedarray.recycle();
    }

    static int measureChildForCells(View view, int i, int j, int k, int l)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        int i1 = android.view.View.MeasureSpec.makeMeasureSpec(android.view.View.MeasureSpec.getSize(k) - l, android.view.View.MeasureSpec.getMode(k));
        ActionMenuItemView actionmenuitemview;
        boolean flag;
        int j1;
        boolean flag1;
        boolean flag2;
        if (view instanceof ActionMenuItemView)
        {
            actionmenuitemview = (ActionMenuItemView)view;
        } else
        {
            actionmenuitemview = null;
        }
        if (actionmenuitemview != null && actionmenuitemview.hasText())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (j > 0 && (!flag || j >= 2))
        {
            view.measure(android.view.View.MeasureSpec.makeMeasureSpec(i * j, 0x80000000), i1);
            int k1 = view.getMeasuredWidth();
            j1 = k1 / i;
            if (k1 % i != 0)
            {
                j1++;
            }
            if (flag && j1 < 2)
            {
                j1 = 2;
            }
        } else
        {
            j1 = 0;
        }
        flag1 = layoutparams.isOverflowButton;
        flag2 = false;
        if (!flag1)
        {
            flag2 = false;
            if (flag)
            {
                flag2 = true;
            }
        }
        layoutparams.expandable = flag2;
        layoutparams.cellsUsed = j1;
        view.measure(android.view.View.MeasureSpec.makeMeasureSpec(j1 * i, 0x40000000), i1);
        return j1;
    }

    private void onMeasureExactFormat(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(j);
        int l = android.view.View.MeasureSpec.getSize(i);
        int i1 = android.view.View.MeasureSpec.getSize(j);
        int j1 = getPaddingLeft() + getPaddingRight();
        int k1 = getPaddingTop() + getPaddingBottom();
        int l1;
        int i2;
        int j2;
        int k2;
        if (k == 0x40000000)
        {
            l1 = android.view.View.MeasureSpec.makeMeasureSpec(i1 - k1, 0x40000000);
        } else
        {
            l1 = android.view.View.MeasureSpec.makeMeasureSpec(Math.min(mMaxItemHeight, i1 - k1), 0x80000000);
        }
        i2 = l - j1;
        j2 = i2 / mMinCellSize;
        k2 = i2 % mMinCellSize;
        if (j2 == 0)
        {
            setMeasuredDimension(i2, 0);
            return;
        }
        int l2 = mMinCellSize + k2 / j2;
        int i3 = 0;
        int j3 = 0;
        int k3 = 0;
        int l3 = 0;
        boolean flag = false;
        long l4 = 0L;
        int i4 = getChildCount();
        int j4 = 0;
        while (j4 < i4) 
        {
            View view3 = getChildAt(j4);
            int k9;
            long l9;
            int i10;
            int j10;
            int k10;
            if (view3.getVisibility() != 8)
            {
                boolean flag6 = view3 instanceof ActionMenuItemView;
                int l10 = l3 + 1;
                if (flag6)
                {
                    view3.setPadding(mGeneratedItemPadding, 0, mGeneratedItemPadding, 0);
                }
                LayoutParams layoutparams4 = (LayoutParams)view3.getLayoutParams();
                layoutparams4.expanded = false;
                layoutparams4.extraPixels = 0;
                layoutparams4.cellsUsed = 0;
                layoutparams4.expandable = false;
                layoutparams4.leftMargin = 0;
                layoutparams4.rightMargin = 0;
                boolean flag7;
                int i11;
                int j11;
                int k11;
                int l11;
                boolean flag8;
                int i12;
                int j12;
                int k12;
                if (flag6 && ((ActionMenuItemView)view3).hasText())
                {
                    flag7 = true;
                } else
                {
                    flag7 = false;
                }
                layoutparams4.preventEdgeOffset = flag7;
                if (layoutparams4.isOverflowButton)
                {
                    i11 = 1;
                } else
                {
                    i11 = j2;
                }
                j11 = measureChildForCells(view3, l2, i11, l1, k1);
                k11 = Math.max(j3, j11);
                boolean flag1;
                boolean flag2;
                long l5;
                int k4;
                long l6;
                boolean flag3;
                boolean flag4;
                int i5;
                int j5;
                View view;
                LayoutParams layoutparams;
                int k5;
                float f;
                float f1;
                int i6;
                int j6;
                boolean flag5;
                View view1;
                LayoutParams layoutparams1;
                int k6;
                long l7;
                int i7;
                int j7;
                int k7;
                int i8;
                int j8;
                long l8;
                View view2;
                LayoutParams layoutparams2;
                int k8;
                LayoutParams layoutparams3;
                int i9;
                int j9;
                if (layoutparams4.expandable)
                {
                    l11 = k3 + 1;
                } else
                {
                    l11 = k3;
                }
                if (layoutparams4.isOverflowButton)
                {
                    flag8 = true;
                } else
                {
                    flag8 = flag;
                }
                i12 = j2 - j11;
                j12 = view3.getMeasuredHeight();
                k12 = Math.max(i3, j12);
                if (j11 == 1)
                {
                    long l13 = l4 | (long)(1 << j4);
                    i10 = k12;
                    j10 = i12;
                    k9 = l10;
                    flag = flag8;
                    k10 = k11;
                    k3 = l11;
                    l9 = l13;
                } else
                {
                    k3 = l11;
                    k10 = k11;
                    long l12 = l4;
                    i10 = k12;
                    j10 = i12;
                    flag = flag8;
                    k9 = l10;
                    l9 = l12;
                }
            } else
            {
                k9 = l3;
                l9 = l4;
                i10 = i3;
                j10 = j2;
                k10 = j3;
            }
            j4++;
            j3 = k10;
            i3 = i10;
            j2 = j10;
            l4 = l9;
            l3 = k9;
        }
        if (flag && l3 == 2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        flag2 = false;
        l5 = l4;
        k4 = j2;
        if (k3 <= 0 || k4 <= 0) goto _L2; else goto _L1
_L1:
        k6 = 0x7fffffff;
        l7 = 0L;
        i7 = 0;
        j7 = 0;
_L4:
        if (j7 >= i4)
        {
            break; /* Loop/switch isn't completed */
        }
        layoutparams3 = (LayoutParams)getChildAt(j7).getLayoutParams();
        if (!layoutparams3.expandable)
        {
            break MISSING_BLOCK_LABEL_1273;
        }
        if (layoutparams3.cellsUsed < k6)
        {
            j9 = layoutparams3.cellsUsed;
            l7 = 1 << j7;
            i9 = 1;
        } else
        {
            if (layoutparams3.cellsUsed != k6)
            {
                break MISSING_BLOCK_LABEL_1273;
            }
            l7 |= 1 << j7;
            i9 = i7 + 1;
            j9 = k6;
        }
_L13:
        j7++;
        k6 = j9;
        i7 = i9;
        if (true) goto _L4; else goto _L3
_L3:
        l5 |= l7;
        if (i7 <= k4)
        {
            k7 = k6 + 1;
            i8 = 0;
            j8 = k4;
            l8 = l5;
            while (i8 < i4) 
            {
                view2 = getChildAt(i8);
                layoutparams2 = (LayoutParams)view2.getLayoutParams();
                if ((l7 & (long)(1 << i8)) == 0L)
                {
                    if (layoutparams2.cellsUsed == k7)
                    {
                        l8 |= 1 << i8;
                        k8 = j8;
                    } else
                    {
                        k8 = j8;
                    }
                } else
                {
                    if (flag1 && layoutparams2.preventEdgeOffset && j8 == 1)
                    {
                        view2.setPadding(l2 + mGeneratedItemPadding, 0, mGeneratedItemPadding, 0);
                    }
                    layoutparams2.cellsUsed = 1 + layoutparams2.cellsUsed;
                    layoutparams2.expanded = true;
                    k8 = j8 - 1;
                }
                i8++;
                j8 = k8;
            }
            l5 = l8;
            flag2 = true;
            k4 = j8;
            break MISSING_BLOCK_LABEL_477;
        }
_L2:
        l6 = l5;
        if (!flag && l3 == 1)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (k4 <= 0 || l6 == 0L)
        {
            break MISSING_BLOCK_LABEL_1255;
        }
        k5 = l3 - 1;
        if (k4 >= k5 && !flag3 && j3 <= 1)
        {
            break MISSING_BLOCK_LABEL_1255;
        }
        f = Long.bitCount(l6);
        if (flag3) goto _L6; else goto _L5
_L5:
        if ((1L & l6) != 0L && !((LayoutParams)getChildAt(0).getLayoutParams()).preventEdgeOffset)
        {
            f -= 0.5F;
        }
        if ((l6 & (long)(1 << i4 - 1)) == 0L || ((LayoutParams)getChildAt(i4 - 1).getLayoutParams()).preventEdgeOffset) goto _L6; else goto _L7
_L7:
        f1 = f - 0.5F;
_L11:
        if (f1 > 0.0F)
        {
            i6 = (int)((float)(k4 * l2) / f1);
        } else
        {
            i6 = 0;
        }
        j6 = 0;
        flag4 = flag2;
_L9:
        if (j6 >= i4)
        {
            break MISSING_BLOCK_LABEL_1141;
        }
        if ((l6 & (long)(1 << j6)) == 0L)
        {
            break MISSING_BLOCK_LABEL_1134;
        }
        view1 = getChildAt(j6);
        layoutparams1 = (LayoutParams)view1.getLayoutParams();
        if (!(view1 instanceof ActionMenuItemView))
        {
            break; /* Loop/switch isn't completed */
        }
        layoutparams1.extraPixels = i6;
        layoutparams1.expanded = true;
        if (j6 == 0 && !layoutparams1.preventEdgeOffset)
        {
            layoutparams1.leftMargin = -i6 / 2;
        }
        flag5 = true;
_L10:
        j6++;
        flag4 = flag5;
        if (true) goto _L9; else goto _L8
_L8:
label0:
        {
            if (!layoutparams1.isOverflowButton)
            {
                break label0;
            }
            layoutparams1.extraPixels = i6;
            layoutparams1.expanded = true;
            layoutparams1.rightMargin = -i6 / 2;
            flag5 = true;
        }
          goto _L10
        if (j6 != 0)
        {
            layoutparams1.leftMargin = i6 / 2;
        }
        if (j6 != i4 - 1)
        {
            layoutparams1.rightMargin = i6 / 2;
        }
        flag5 = flag4;
          goto _L10
        i5 = 0;
_L12:
        if (flag4)
        {
            for (j5 = 0; j5 < i4; j5++)
            {
                view = getChildAt(j5);
                layoutparams = (LayoutParams)view.getLayoutParams();
                if (layoutparams.expanded)
                {
                    view.measure(android.view.View.MeasureSpec.makeMeasureSpec(l2 * layoutparams.cellsUsed + layoutparams.extraPixels, 0x40000000), l1);
                }
            }

        }
        if (k == 0x40000000)
        {
            i3 = i1;
        }
        setMeasuredDimension(i2, i3);
        mMeasuredExtraWidth = i5 * l2;
        return;
_L6:
        f1 = f;
          goto _L11
        flag4 = flag2;
        i5 = k4;
          goto _L12
        i9 = i7;
        j9 = k6;
          goto _L13
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams != null && (layoutparams instanceof LayoutParams);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        return false;
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        LayoutParams layoutparams = new LayoutParams(-2, -2);
        layoutparams.gravity = 16;
        return layoutparams;
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected volatile android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (layoutparams instanceof LayoutParams)
        {
            LayoutParams layoutparams1 = new LayoutParams((LayoutParams)layoutparams);
            if (layoutparams1.gravity <= 0)
            {
                layoutparams1.gravity = 16;
            }
            return layoutparams1;
        } else
        {
            return generateDefaultLayoutParams();
        }
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public volatile android.widget.LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.widget.LinearLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public LayoutParams generateOverflowButtonLayoutParams()
    {
        LayoutParams layoutparams = generateDefaultLayoutParams();
        layoutparams.isOverflowButton = true;
        return layoutparams;
    }

    public int getWindowAnimations()
    {
        return 0;
    }

    protected boolean hasSupportDividerBeforeChildAt(int i)
    {
        View view = getChildAt(i - 1);
        View view1 = getChildAt(i);
        int j = getChildCount();
        boolean flag = false;
        if (i < j)
        {
            boolean flag1 = view instanceof ActionMenuChildView;
            flag = false;
            if (flag1)
            {
                flag = false | ((ActionMenuChildView)view).needsDividerAfter();
            }
        }
        if (i > 0 && (view1 instanceof ActionMenuChildView))
        {
            return flag | ((ActionMenuChildView)view1).needsDividerBefore();
        } else
        {
            return flag;
        }
    }

    public void initialize(MenuBuilder menubuilder)
    {
        mMenu = menubuilder;
    }

    public boolean invokeItem(MenuItemImpl menuitemimpl)
    {
        return mMenu.performItemAction(menuitemimpl, 0);
    }

    public boolean isExpandedFormat()
    {
        return mFormatItems;
    }

    public boolean isOverflowReserved()
    {
        return mReserveOverflow;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        if (android.os.Build.VERSION.SDK_INT >= 8)
        {
            super.onConfigurationChanged(configuration);
        }
        mPresenter.updateMenuView(false);
        if (mPresenter != null && mPresenter.isOverflowMenuShowing())
        {
            mPresenter.hideOverflowMenu();
            mPresenter.showOverflowMenu();
        }
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mPresenter.dismissPopupMenus();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if (!mFormatItems)
        {
            super.onLayout(flag, i, j, k, l);
        } else
        {
            int i1 = getChildCount();
            int j1 = (j + l) / 2;
            int k1 = getSupportDividerWidth();
            int l1 = 0;
            int i2 = k - i - getPaddingRight() - getPaddingLeft();
            boolean flag1 = false;
            int j2 = 0;
            while (j2 < i1) 
            {
                View view2 = getChildAt(j2);
                int k2;
                int l2;
                int i3;
                int j3;
                int k3;
                int l3;
                View view;
                LayoutParams layoutparams;
                int i4;
                int j4;
                int k4;
                int l4;
                int i5;
                View view1;
                int j5;
                int k5;
                int l5;
                int i6;
                int j6;
                int k6;
                boolean flag2;
                if (view2.getVisibility() != 8)
                {
                    LayoutParams layoutparams1 = (LayoutParams)view2.getLayoutParams();
                    if (layoutparams1.isOverflowButton)
                    {
                        int l6 = view2.getMeasuredWidth();
                        if (hasSupportDividerBeforeChildAt(j2))
                        {
                            l6 += k1;
                        }
                        int i7 = view2.getMeasuredHeight();
                        int j7 = getWidth() - getPaddingRight() - layoutparams1.rightMargin;
                        int k7 = j7 - l6;
                        int l7 = j1 - i7 / 2;
                        view2.layout(k7, l7, j7, i7 + l7);
                        j6 = i2 - l6;
                        flag2 = true;
                        k6 = l1;
                    } else
                    {
                        j6 = i2 - (view2.getMeasuredWidth() + layoutparams1.leftMargin + layoutparams1.rightMargin);
                        hasSupportDividerBeforeChildAt(j2);
                        k6 = l1 + 1;
                        flag2 = flag1;
                    }
                } else
                {
                    j6 = i2;
                    k6 = l1;
                    flag2 = flag1;
                }
                j2++;
                flag1 = flag2;
                l1 = k6;
                i2 = j6;
            }
            if (i1 == 1 && !flag1)
            {
                view1 = getChildAt(0);
                j5 = view1.getMeasuredWidth();
                k5 = view1.getMeasuredHeight();
                l5 = (k - i) / 2 - j5 / 2;
                i6 = j1 - k5 / 2;
                view1.layout(l5, i6, j5 + l5, k5 + i6);
                return;
            }
            if (flag1)
            {
                k2 = 0;
            } else
            {
                k2 = 1;
            }
            l2 = l1 - k2;
            if (l2 > 0)
            {
                i3 = i2 / l2;
            } else
            {
                i3 = 0;
            }
            j3 = Math.max(0, i3);
            k3 = getPaddingLeft();
            l3 = 0;
            while (l3 < i1) 
            {
                view = getChildAt(l3);
                layoutparams = (LayoutParams)view.getLayoutParams();
                if (view.getVisibility() != 8 && !layoutparams.isOverflowButton)
                {
                    j4 = k3 + layoutparams.leftMargin;
                    k4 = view.getMeasuredWidth();
                    l4 = view.getMeasuredHeight();
                    i5 = j1 - l4 / 2;
                    view.layout(j4, i5, j4 + k4, l4 + i5);
                    i4 = j4 + (j3 + (k4 + layoutparams.rightMargin));
                } else
                {
                    i4 = k3;
                }
                l3++;
                k3 = i4;
            }
        }
    }

    protected void onMeasure(int i, int j)
    {
        boolean flag = mFormatItems;
        boolean flag1;
        int k;
        if (android.view.View.MeasureSpec.getMode(i) == 0x40000000)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        mFormatItems = flag1;
        if (flag != mFormatItems)
        {
            mFormatItemsWidth = 0;
        }
        k = android.view.View.MeasureSpec.getMode(i);
        if (mFormatItems && mMenu != null && k != mFormatItemsWidth)
        {
            mFormatItemsWidth = k;
            mMenu.onItemsChanged(true);
        }
        if (mFormatItems)
        {
            onMeasureExactFormat(i, j);
            return;
        }
        int l = getChildCount();
        for (int i1 = 0; i1 < l; i1++)
        {
            LayoutParams layoutparams = (LayoutParams)getChildAt(i1).getLayoutParams();
            layoutparams.rightMargin = 0;
            layoutparams.leftMargin = 0;
        }

        super.onMeasure(i, j);
    }

    public void setOverflowReserved(boolean flag)
    {
        mReserveOverflow = flag;
    }

    public void setPresenter(ActionMenuPresenter actionmenupresenter)
    {
        mPresenter = actionmenupresenter;
    }

    private class LayoutParams extends android.widget.LinearLayout.LayoutParams
    {

        public int cellsUsed;
        public boolean expandable;
        public boolean expanded;
        public int extraPixels;
        public boolean isOverflowButton;
        public boolean preventEdgeOffset;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            isOverflowButton = false;
        }

        public LayoutParams(int i, int j, boolean flag)
        {
            super(i, j);
            isOverflowButton = flag;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            isOverflowButton = layoutparams.isOverflowButton;
        }
    }


    private class ActionMenuChildView
    {

        public abstract boolean needsDividerAfter();

        public abstract boolean needsDividerBefore();
    }

}
