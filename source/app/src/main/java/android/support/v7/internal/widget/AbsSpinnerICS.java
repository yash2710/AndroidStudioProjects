// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.SpinnerAdapter;

// Referenced classes of package android.support.v7.internal.widget:
//            AdapterViewICS

abstract class AbsSpinnerICS extends AdapterViewICS
{

    SpinnerAdapter mAdapter;
    boolean mBlockLayoutRequests;
    private DataSetObserver mDataSetObserver;
    int mHeightMeasureSpec;
    final RecycleBin mRecycler;
    int mSelectionBottomPadding;
    int mSelectionLeftPadding;
    int mSelectionRightPadding;
    int mSelectionTopPadding;
    final Rect mSpinnerPadding;
    private Rect mTouchFrame;
    int mWidthMeasureSpec;

    AbsSpinnerICS(Context context)
    {
        super(context);
        mSelectionLeftPadding = 0;
        mSelectionTopPadding = 0;
        mSelectionRightPadding = 0;
        mSelectionBottomPadding = 0;
        mSpinnerPadding = new Rect();
        mRecycler = new RecycleBin();
        initAbsSpinner();
    }

    AbsSpinnerICS(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    AbsSpinnerICS(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mSelectionLeftPadding = 0;
        mSelectionTopPadding = 0;
        mSelectionRightPadding = 0;
        mSelectionBottomPadding = 0;
        mSpinnerPadding = new Rect();
        mRecycler = new RecycleBin();
        initAbsSpinner();
    }

    private void initAbsSpinner()
    {
        setFocusable(true);
        setWillNotDraw(false);
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new android.view.ViewGroup.LayoutParams(-1, -2);
    }

    public volatile Adapter getAdapter()
    {
        return getAdapter();
    }

    public SpinnerAdapter getAdapter()
    {
        return mAdapter;
    }

    int getChildHeight(View view)
    {
        return view.getMeasuredHeight();
    }

    int getChildWidth(View view)
    {
        return view.getMeasuredWidth();
    }

    public int getCount()
    {
        return mItemCount;
    }

    public View getSelectedView()
    {
        if (mItemCount > 0 && mSelectedPosition >= 0)
        {
            return getChildAt(mSelectedPosition - mFirstPosition);
        } else
        {
            return null;
        }
    }

    abstract void layout(int i, boolean flag);

    protected void onMeasure(int i, int j)
    {
        boolean flag;
        int l2;
        int i3;
        int k = android.view.View.MeasureSpec.getMode(i);
        int l = getPaddingLeft();
        int i1 = getPaddingTop();
        int j1 = getPaddingRight();
        int k1 = getPaddingBottom();
        Rect rect = mSpinnerPadding;
        Rect rect1;
        int l1;
        Rect rect2;
        int i2;
        Rect rect3;
        int j2;
        int k2;
        int j3;
        int k3;
        int l3;
        View view;
        if (l <= mSelectionLeftPadding)
        {
            l = mSelectionLeftPadding;
        }
        rect.left = l;
        rect1 = mSpinnerPadding;
        if (i1 > mSelectionTopPadding)
        {
            l1 = i1;
        } else
        {
            l1 = mSelectionTopPadding;
        }
        rect1.top = l1;
        rect2 = mSpinnerPadding;
        if (j1 > mSelectionRightPadding)
        {
            i2 = j1;
        } else
        {
            i2 = mSelectionRightPadding;
        }
        rect2.right = i2;
        rect3 = mSpinnerPadding;
        if (k1 > mSelectionBottomPadding)
        {
            j2 = k1;
        } else
        {
            j2 = mSelectionBottomPadding;
        }
        rect3.bottom = j2;
        if (mDataChanged)
        {
            handleDataChanged();
        }
        k2 = getSelectedItemPosition();
        if (k2 < 0 || mAdapter == null || k2 >= mAdapter.getCount()) goto _L2; else goto _L1
_L1:
        view = mRecycler.get(k2);
        if (view == null)
        {
            view = mAdapter.getView(k2, null, this);
        }
        if (view != null)
        {
            mRecycler.put(k2, view);
        }
        if (view == null) goto _L2; else goto _L3
_L3:
        if (view.getLayoutParams() == null)
        {
            mBlockLayoutRequests = true;
            view.setLayoutParams(generateDefaultLayoutParams());
            mBlockLayoutRequests = false;
        }
        measureChild(view, i, j);
        i3 = getChildHeight(view) + mSpinnerPadding.top + mSpinnerPadding.bottom;
        l2 = getChildWidth(view) + mSpinnerPadding.left + mSpinnerPadding.right;
        flag = false;
_L5:
        if (flag)
        {
            i3 = mSpinnerPadding.top + mSpinnerPadding.bottom;
            if (k == 0)
            {
                l2 = mSpinnerPadding.left + mSpinnerPadding.right;
            }
        }
        j3 = Math.max(i3, getSuggestedMinimumHeight());
        k3 = Math.max(l2, getSuggestedMinimumWidth());
        l3 = resolveSize(j3, j);
        setMeasuredDimension(resolveSize(k3, i), l3);
        mHeightMeasureSpec = j;
        mWidthMeasureSpec = i;
        return;
_L2:
        flag = true;
        l2 = 0;
        i3 = 0;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        if (savedstate.selectedId >= 0L)
        {
            mDataChanged = true;
            mNeedSync = true;
            mSyncRowId = savedstate.selectedId;
            mSyncPosition = savedstate.position;
            mSyncMode = 0;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.selectedId = getSelectedItemId();
        if (savedstate.selectedId >= 0L)
        {
            savedstate.position = getSelectedItemPosition();
            return savedstate;
        } else
        {
            savedstate.position = -1;
            return savedstate;
        }
    }

    public int pointToPosition(int i, int j)
    {
        Rect rect = mTouchFrame;
        if (rect == null)
        {
            mTouchFrame = new Rect();
            rect = mTouchFrame;
        }
        for (int k = -1 + getChildCount(); k >= 0; k--)
        {
            View view = getChildAt(k);
            if (view.getVisibility() != 0)
            {
                continue;
            }
            view.getHitRect(rect);
            if (rect.contains(i, j))
            {
                return k + mFirstPosition;
            }
        }

        return -1;
    }

    void recycleAllViews()
    {
        int i = getChildCount();
        RecycleBin recyclebin = mRecycler;
        int j = mFirstPosition;
        for (int k = 0; k < i; k++)
        {
            View view = getChildAt(k);
            recyclebin.put(j + k, view);
        }

    }

    public void requestLayout()
    {
        if (!mBlockLayoutRequests)
        {
            super.requestLayout();
        }
    }

    void resetList()
    {
        mDataChanged = false;
        mNeedSync = false;
        removeAllViewsInLayout();
        mOldSelectedPosition = -1;
        mOldSelectedRowId = 0x8000000000000000L;
        setSelectedPositionInt(-1);
        setNextSelectedPositionInt(-1);
        invalidate();
    }

    public volatile void setAdapter(Adapter adapter)
    {
        setAdapter((SpinnerAdapter)adapter);
    }

    public void setAdapter(SpinnerAdapter spinneradapter)
    {
        int i = -1;
        if (mAdapter != null)
        {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
            resetList();
        }
        mAdapter = spinneradapter;
        mOldSelectedPosition = i;
        mOldSelectedRowId = 0x8000000000000000L;
        if (mAdapter != null)
        {
            mOldItemCount = mItemCount;
            mItemCount = mAdapter.getCount();
            checkFocus();
            mDataSetObserver = new AdapterViewICS.AdapterDataSetObserver(this);
            mAdapter.registerDataSetObserver(mDataSetObserver);
            if (mItemCount > 0)
            {
                i = 0;
            }
            setSelectedPositionInt(i);
            setNextSelectedPositionInt(i);
            if (mItemCount == 0)
            {
                checkSelectionChanged();
            }
        } else
        {
            checkFocus();
            resetList();
            checkSelectionChanged();
        }
        requestLayout();
    }

    public void setSelection(int i)
    {
        setNextSelectedPositionInt(i);
        requestLayout();
        invalidate();
    }

    public void setSelection(int i, boolean flag)
    {
        boolean flag1;
        if (flag && mFirstPosition <= i && i <= -1 + (mFirstPosition + getChildCount()))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        setSelectionInt(i, flag1);
    }

    void setSelectionInt(int i, boolean flag)
    {
        if (i != mOldSelectedPosition)
        {
            mBlockLayoutRequests = true;
            int j = i - mSelectedPosition;
            setNextSelectedPositionInt(i);
            layout(j, flag);
            mBlockLayoutRequests = false;
        }
    }


    private class RecycleBin
    {

        private final SparseArray mScrapHeap = new SparseArray();
        final AbsSpinnerICS this$0;

        void clear()
        {
            SparseArray sparsearray = mScrapHeap;
            int i = sparsearray.size();
            for (int j = 0; j < i; j++)
            {
                View view = (View)sparsearray.valueAt(j);
                if (view != null)
                {
                    removeDetachedView(view, true);
                }
            }

            sparsearray.clear();
        }

        View get(int i)
        {
            View view = (View)mScrapHeap.get(i);
            if (view != null)
            {
                mScrapHeap.delete(i);
            }
            return view;
        }

        public void put(int i, View view)
        {
            mScrapHeap.put(i, view);
        }

        RecycleBin()
        {
            this$0 = AbsSpinnerICS.this;
            super();
        }
    }


    private class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        int position;
        long selectedId;

        public String toString()
        {
            return (new StringBuilder("AbsSpinner.SavedState{")).append(Integer.toHexString(System.identityHashCode(this))).append(" selectedId=").append(selectedId).append(" position=").append(position).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeLong(selectedId);
            parcel.writeInt(position);
        }


        private SavedState(Parcel parcel)
        {
            super(parcel);
            selectedId = parcel.readLong();
            position = parcel.readInt();
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
