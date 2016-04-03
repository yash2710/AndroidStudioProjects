// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.ActionBarView;
import android.support.v7.internal.widget.ProgressBarICS;
import android.support.v7.view.ActionMode;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

// Referenced classes of package android.support.v7.app:
//            ActionBarActivityDelegate, ActionBarActivity, ActionBarImplBase, ActionBar

class ActionBarActivityDelegateBase extends ActionBarActivityDelegate
    implements android.support.v7.internal.view.menu.MenuBuilder.Callback, android.support.v7.internal.view.menu.MenuPresenter.Callback
{

    private static final int ACTION_BAR_DRAWABLE_TOGGLE_ATTRS[];
    private static final String TAG = "ActionBarActivityDelegateBase";
    private ActionBarView mActionBarView;
    private ActionMode mActionMode;
    private boolean mClosingActionMenu;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private ListMenuPresenter mListMenuPresenter;
    private MenuBuilder mMenu;
    private Bundle mPanelFrozenActionViewState;
    private boolean mPanelIsPrepared;
    private boolean mPanelRefreshContent;
    private boolean mSubDecorInstalled;
    private CharSequence mTitleToSet;

    ActionBarActivityDelegateBase(ActionBarActivity actionbaractivity)
    {
        super(actionbaractivity);
    }

    private void applyFixedSizeWindow()
    {
        TypedArray typedarray = mActivity.obtainStyledAttributes(android.support.v7.appcompat.R.styleable.ActionBarWindow);
        TypedValue typedvalue;
        TypedValue typedvalue1;
        TypedValue typedvalue2;
        boolean flag;
        TypedValue typedvalue3;
        DisplayMetrics displaymetrics;
        boolean flag1;
        int i;
        int j;
        if (typedarray.hasValue(3))
        {
            typedvalue = new TypedValue();
            typedarray.getValue(3, typedvalue);
        } else
        {
            typedvalue = null;
        }
        if (typedarray.hasValue(5))
        {
            typedvalue1 = new TypedValue();
            typedarray.getValue(5, typedvalue1);
        } else
        {
            typedvalue1 = null;
        }
        if (typedarray.hasValue(6))
        {
            typedvalue2 = new TypedValue();
            typedarray.getValue(6, typedvalue2);
        } else
        {
            typedvalue2 = null;
        }
        flag = typedarray.hasValue(4);
        typedvalue3 = null;
        if (flag)
        {
            typedvalue3 = new TypedValue();
            typedarray.getValue(4, typedvalue3);
        }
        displaymetrics = mActivity.getResources().getDisplayMetrics();
        if (displaymetrics.widthPixels < displaymetrics.heightPixels)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            typedvalue1 = typedvalue;
        }
        if (typedvalue1 == null || typedvalue1.type == 0) goto _L2; else goto _L1
_L1:
        if (typedvalue1.type != 5) goto _L4; else goto _L3
_L3:
        i = (int)typedvalue1.getDimension(displaymetrics);
_L11:
        if (!flag1)
        {
            typedvalue2 = typedvalue3;
        }
        if (typedvalue2 == null || typedvalue2.type == 0) goto _L6; else goto _L5
_L5:
        if (typedvalue2.type != 5) goto _L8; else goto _L7
_L7:
        j = (int)typedvalue2.getDimension(displaymetrics);
_L9:
        if (i != -1 || j != -1)
        {
            mActivity.getWindow().setLayout(i, j);
        }
        typedarray.recycle();
        return;
_L4:
        if (typedvalue1.type != 6)
        {
            break; /* Loop/switch isn't completed */
        }
        i = (int)typedvalue1.getFraction(displaymetrics.widthPixels, displaymetrics.widthPixels);
        continue; /* Loop/switch isn't completed */
_L8:
        if (typedvalue2.type == 6)
        {
            j = (int)typedvalue2.getFraction(displaymetrics.heightPixels, displaymetrics.heightPixels);
            continue; /* Loop/switch isn't completed */
        }
_L6:
        j = -1;
        if (true) goto _L9; else goto _L2
_L2:
        i = -1;
        if (true) goto _L11; else goto _L10
_L10:
    }

    private ProgressBarICS getCircularProgressBar()
    {
        ProgressBarICS progressbarics = (ProgressBarICS)mActionBarView.findViewById(android.support.v7.appcompat.R.id.progress_circular);
        if (progressbarics != null)
        {
            progressbarics.setVisibility(4);
        }
        return progressbarics;
    }

    private ProgressBarICS getHorizontalProgressBar()
    {
        ProgressBarICS progressbarics = (ProgressBarICS)mActionBarView.findViewById(android.support.v7.appcompat.R.id.progress_horizontal);
        if (progressbarics != null)
        {
            progressbarics.setVisibility(4);
        }
        return progressbarics;
    }

    private MenuView getListMenuView(Context context, android.support.v7.internal.view.menu.MenuPresenter.Callback callback)
    {
        if (mMenu == null)
        {
            return null;
        }
        if (mListMenuPresenter == null)
        {
            TypedArray typedarray = context.obtainStyledAttributes(android.support.v7.appcompat.R.styleable.Theme);
            int i = typedarray.getResourceId(4, android.support.v7.appcompat.R.style.Theme_AppCompat_CompactMenu);
            typedarray.recycle();
            mListMenuPresenter = new ListMenuPresenter(android.support.v7.appcompat.R.layout.abc_list_menu_item_layout, i);
            mListMenuPresenter.setCallback(callback);
            mMenu.addMenuPresenter(mListMenuPresenter);
        } else
        {
            mListMenuPresenter.updateMenuView(false);
        }
        return mListMenuPresenter.getMenuView(new FrameLayout(context));
    }

    private void hideProgressBars(ProgressBarICS progressbarics, ProgressBarICS progressbarics1)
    {
        if (mFeatureIndeterminateProgress && progressbarics1.getVisibility() == 0)
        {
            progressbarics1.setVisibility(4);
        }
        if (mFeatureProgress && progressbarics.getVisibility() == 0)
        {
            progressbarics.setVisibility(4);
        }
    }

    private boolean initializePanelMenu()
    {
        mMenu = new MenuBuilder(getActionBarThemedContext());
        mMenu.setCallback(this);
        return true;
    }

    private boolean preparePanel()
    {
        if (mPanelIsPrepared)
        {
            return true;
        }
        if (mMenu == null || mPanelRefreshContent)
        {
            if (mMenu == null && (!initializePanelMenu() || mMenu == null))
            {
                return false;
            }
            if (mActionBarView != null)
            {
                mActionBarView.setMenu(mMenu, this);
            }
            mMenu.stopDispatchingItemsChanged();
            if (!mActivity.superOnCreatePanelMenu(0, mMenu))
            {
                mMenu = null;
                if (mActionBarView != null)
                {
                    mActionBarView.setMenu(null, this);
                }
                return false;
            }
            mPanelRefreshContent = false;
        }
        mMenu.stopDispatchingItemsChanged();
        if (mPanelFrozenActionViewState != null)
        {
            mMenu.restoreActionViewStates(mPanelFrozenActionViewState);
            mPanelFrozenActionViewState = null;
        }
        if (!mActivity.superOnPreparePanel(0, null, mMenu))
        {
            if (mActionBarView != null)
            {
                mActionBarView.setMenu(null, this);
            }
            mMenu.startDispatchingItemsChanged();
            return false;
        } else
        {
            mMenu.startDispatchingItemsChanged();
            mPanelIsPrepared = true;
            return true;
        }
    }

    private void reopenMenu(MenuBuilder menubuilder, boolean flag)
    {
        if (mActionBarView != null && mActionBarView.isOverflowReserved())
        {
            if (!mActionBarView.isOverflowMenuShowing() || !flag)
            {
                if (mActionBarView.getVisibility() == 0)
                {
                    mActionBarView.showOverflowMenu();
                }
                return;
            } else
            {
                mActionBarView.hideOverflowMenu();
                return;
            }
        } else
        {
            menubuilder.close();
            return;
        }
    }

    private void showProgressBars(ProgressBarICS progressbarics, ProgressBarICS progressbarics1)
    {
        if (mFeatureIndeterminateProgress && progressbarics1.getVisibility() == 4)
        {
            progressbarics1.setVisibility(0);
        }
        if (mFeatureProgress && progressbarics.getProgress() < 10000)
        {
            progressbarics.setVisibility(0);
        }
    }

    private void updateProgressBars(int i)
    {
        ProgressBarICS progressbarics;
        ProgressBarICS progressbarics1;
        progressbarics = getCircularProgressBar();
        progressbarics1 = getHorizontalProgressBar();
        if (i != -1) goto _L2; else goto _L1
_L1:
        if (mFeatureProgress)
        {
            int j = progressbarics1.getProgress();
            int k;
            if (progressbarics1.isIndeterminate() || j < 10000)
            {
                k = 0;
            } else
            {
                k = 4;
            }
            progressbarics1.setVisibility(k);
        }
        if (mFeatureIndeterminateProgress)
        {
            progressbarics.setVisibility(0);
        }
_L4:
        return;
_L2:
        if (i != -2)
        {
            break; /* Loop/switch isn't completed */
        }
        if (mFeatureProgress)
        {
            progressbarics1.setVisibility(8);
        }
        if (mFeatureIndeterminateProgress)
        {
            progressbarics.setVisibility(8);
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (i == -3)
        {
            progressbarics1.setIndeterminate(true);
            return;
        }
        if (i == -4)
        {
            progressbarics1.setIndeterminate(false);
            return;
        }
        if (i >= 0 && i <= 10000)
        {
            progressbarics1.setProgress(i);
            if (i < 10000)
            {
                showProgressBars(progressbarics1, progressbarics);
                return;
            } else
            {
                hideProgressBars(progressbarics1, progressbarics);
                return;
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        ensureSubDecor();
        ((ViewGroup)mActivity.findViewById(0x1020002)).addView(view, layoutparams);
        mActivity.onSupportContentChanged();
    }

    public ActionBar createSupportActionBar()
    {
        ensureSubDecor();
        return new ActionBarImplBase(mActivity, mActivity);
    }

    final void ensureSubDecor()
    {
        if (!mSubDecorInstalled)
        {
            if (mHasActionBar)
            {
                boolean flag;
                boolean flag2;
                ActionBarContainer actionbarcontainer;
                if (mOverlayActionBar)
                {
                    mActivity.superSetContentView(android.support.v7.appcompat.R.layout.abc_action_bar_decor_overlay);
                } else
                {
                    mActivity.superSetContentView(android.support.v7.appcompat.R.layout.abc_action_bar_decor);
                }
                mActionBarView = (ActionBarView)mActivity.findViewById(android.support.v7.appcompat.R.id.action_bar);
                mActionBarView.setWindowCallback(mActivity);
                if (mFeatureProgress)
                {
                    mActionBarView.initProgress();
                }
                if (mFeatureIndeterminateProgress)
                {
                    mActionBarView.initIndeterminateProgress();
                }
                flag = "splitActionBarWhenNarrow".equals(getUiOptionsFromMetadata());
                if (flag)
                {
                    flag2 = mActivity.getResources().getBoolean(android.support.v7.appcompat.R.bool.abc_split_action_bar_is_narrow);
                } else
                {
                    TypedArray typedarray = mActivity.obtainStyledAttributes(android.support.v7.appcompat.R.styleable.ActionBarWindow);
                    boolean flag1 = typedarray.getBoolean(2, false);
                    typedarray.recycle();
                    flag2 = flag1;
                }
                actionbarcontainer = (ActionBarContainer)mActivity.findViewById(android.support.v7.appcompat.R.id.split_action_bar);
                if (actionbarcontainer != null)
                {
                    mActionBarView.setSplitView(actionbarcontainer);
                    mActionBarView.setSplitActionBar(flag2);
                    mActionBarView.setSplitWhenNarrow(flag);
                    ActionBarContextView actionbarcontextview = (ActionBarContextView)mActivity.findViewById(android.support.v7.appcompat.R.id.action_context_bar);
                    actionbarcontextview.setSplitView(actionbarcontainer);
                    actionbarcontextview.setSplitActionBar(flag2);
                    actionbarcontextview.setSplitWhenNarrow(flag);
                }
            } else
            {
                mActivity.superSetContentView(android.support.v7.appcompat.R.layout.abc_simple_decor);
            }
            mActivity.findViewById(0x1020002).setId(-1);
            mActivity.findViewById(android.support.v7.appcompat.R.id.action_bar_activity_content).setId(0x1020002);
            if (mTitleToSet != null)
            {
                mActionBarView.setWindowTitle(mTitleToSet);
                mTitleToSet = null;
            }
            applyFixedSizeWindow();
            mSubDecorInstalled = true;
            mActivity.getWindow().getDecorView().post(new _cls1());
        }
    }

    int getHomeAsUpIndicatorAttrId()
    {
        return android.support.v7.appcompat.R.attr.homeAsUpIndicator;
    }

    public boolean onBackPressed()
    {
        if (mActionMode != null)
        {
            mActionMode.finish();
            return true;
        }
        if (mActionBarView != null && mActionBarView.hasExpandedActionView())
        {
            mActionBarView.collapseActionView();
            return true;
        } else
        {
            return false;
        }
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if (mClosingActionMenu)
        {
            return;
        } else
        {
            mClosingActionMenu = true;
            mActivity.closeOptionsMenu();
            mActionBarView.dismissPopupMenus();
            mClosingActionMenu = false;
            return;
        }
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        if (mHasActionBar && mSubDecorInstalled)
        {
            ((ActionBarImplBase)getSupportActionBar()).onConfigurationChanged(configuration);
        }
    }

    public void onContentChanged()
    {
    }

    public boolean onCreatePanelMenu(int i, Menu menu)
    {
        if (i != 0)
        {
            return mActivity.superOnCreatePanelMenu(i, menu);
        } else
        {
            return false;
        }
    }

    public View onCreatePanelView(int i)
    {
        View view = null;
        if (i == 0)
        {
            boolean flag = preparePanel();
            view = null;
            if (flag)
            {
                view = (View)getListMenuView(mActivity, this);
            }
        }
        return view;
    }

    public boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
        if (i == 0)
        {
            menuitem = MenuWrapperFactory.createMenuItemWrapper(menuitem);
        }
        return mActivity.superOnMenuItemSelected(i, menuitem);
    }

    public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
    {
        return mActivity.onMenuItemSelected(0, menuitem);
    }

    public void onMenuModeChange(MenuBuilder menubuilder)
    {
        reopenMenu(menubuilder, true);
    }

    public boolean onOpenSubMenu(MenuBuilder menubuilder)
    {
        return false;
    }

    public void onPostResume()
    {
        ActionBarImplBase actionbarimplbase = (ActionBarImplBase)getSupportActionBar();
        if (actionbarimplbase != null)
        {
            actionbarimplbase.setShowHideAnimationEnabled(true);
        }
    }

    public boolean onPreparePanel(int i, View view, Menu menu)
    {
        if (i != 0)
        {
            return mActivity.superOnPreparePanel(i, view, menu);
        } else
        {
            return false;
        }
    }

    public void onStop()
    {
        ActionBarImplBase actionbarimplbase = (ActionBarImplBase)getSupportActionBar();
        if (actionbarimplbase != null)
        {
            actionbarimplbase.setShowHideAnimationEnabled(false);
        }
    }

    public void onTitleChanged(CharSequence charsequence)
    {
        if (mActionBarView != null)
        {
            mActionBarView.setWindowTitle(charsequence);
            return;
        } else
        {
            mTitleToSet = charsequence;
            return;
        }
    }

    public void setContentView(int i)
    {
        ensureSubDecor();
        ViewGroup viewgroup = (ViewGroup)mActivity.findViewById(0x1020002);
        viewgroup.removeAllViews();
        mActivity.getLayoutInflater().inflate(i, viewgroup);
        mActivity.onSupportContentChanged();
    }

    public void setContentView(View view)
    {
        ensureSubDecor();
        ViewGroup viewgroup = (ViewGroup)mActivity.findViewById(0x1020002);
        viewgroup.removeAllViews();
        viewgroup.addView(view);
        mActivity.onSupportContentChanged();
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        ensureSubDecor();
        ViewGroup viewgroup = (ViewGroup)mActivity.findViewById(0x1020002);
        viewgroup.removeAllViews();
        viewgroup.addView(view, layoutparams);
        mActivity.onSupportContentChanged();
    }

    void setSupportProgress(int i)
    {
        updateProgressBars(i + 0);
    }

    void setSupportProgressBarIndeterminate(boolean flag)
    {
        byte byte0;
        if (flag)
        {
            byte0 = -3;
        } else
        {
            byte0 = -4;
        }
        updateProgressBars(byte0);
    }

    void setSupportProgressBarIndeterminateVisibility(boolean flag)
    {
        byte byte0;
        if (flag)
        {
            byte0 = -1;
        } else
        {
            byte0 = -2;
        }
        updateProgressBars(byte0);
    }

    void setSupportProgressBarVisibility(boolean flag)
    {
        byte byte0;
        if (flag)
        {
            byte0 = -1;
        } else
        {
            byte0 = -2;
        }
        updateProgressBars(byte0);
    }

    public ActionMode startSupportActionMode(android.support.v7.view.ActionMode.Callback callback)
    {
        if (callback == null)
        {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (mActionMode != null)
        {
            mActionMode.finish();
        }
        ActionModeCallbackWrapper actionmodecallbackwrapper = new ActionModeCallbackWrapper(callback);
        ActionBarImplBase actionbarimplbase = (ActionBarImplBase)getSupportActionBar();
        if (actionbarimplbase != null)
        {
            mActionMode = actionbarimplbase.startActionMode(actionmodecallbackwrapper);
        }
        if (mActionMode != null)
        {
            mActivity.onSupportActionModeStarted(mActionMode);
        }
        return mActionMode;
    }

    public void supportInvalidateOptionsMenu()
    {
        if (mMenu != null)
        {
            Bundle bundle = new Bundle();
            mMenu.saveActionViewStates(bundle);
            if (bundle.size() > 0)
            {
                mPanelFrozenActionViewState = bundle;
            }
            mMenu.stopDispatchingItemsChanged();
            mMenu.clear();
        }
        mPanelRefreshContent = true;
        if (mActionBarView != null)
        {
            mPanelIsPrepared = false;
            preparePanel();
        }
    }

    public boolean supportRequestWindowFeature(int i)
    {
        switch (i)
        {
        case 3: // '\003'
        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
        default:
            return mActivity.requestWindowFeature(i);

        case 8: // '\b'
            mHasActionBar = true;
            return true;

        case 9: // '\t'
            mOverlayActionBar = true;
            return true;

        case 2: // '\002'
            mFeatureProgress = true;
            return true;

        case 5: // '\005'
            mFeatureIndeterminateProgress = true;
            return true;
        }
    }

    static 
    {
        int ai[] = new int[1];
        ai[0] = android.support.v7.appcompat.R.attr.homeAsUpIndicator;
        ACTION_BAR_DRAWABLE_TOGGLE_ATTRS = ai;
    }


/*
    static ActionMode access$002(ActionBarActivityDelegateBase actionbaractivitydelegatebase, ActionMode actionmode)
    {
        actionbaractivitydelegatebase.mActionMode = actionmode;
        return actionmode;
    }

*/

    private class _cls1
        implements Runnable
    {

        final ActionBarActivityDelegateBase this$0;

        public void run()
        {
            supportInvalidateOptionsMenu();
        }

        _cls1()
        {
            this$0 = ActionBarActivityDelegateBase.this;
            super();
        }
    }


    private class ActionModeCallbackWrapper
        implements android.support.v7.view.ActionMode.Callback
    {

        private android.support.v7.view.ActionMode.Callback mWrapped;
        final ActionBarActivityDelegateBase this$0;

        public boolean onActionItemClicked(ActionMode actionmode, MenuItem menuitem)
        {
            return mWrapped.onActionItemClicked(actionmode, menuitem);
        }

        public boolean onCreateActionMode(ActionMode actionmode, Menu menu)
        {
            return mWrapped.onCreateActionMode(actionmode, menu);
        }

        public void onDestroyActionMode(ActionMode actionmode)
        {
            mWrapped.onDestroyActionMode(actionmode);
            mActivity.onSupportActionModeFinished(actionmode);
            mActionMode = null;
        }

        public boolean onPrepareActionMode(ActionMode actionmode, Menu menu)
        {
            return mWrapped.onPrepareActionMode(actionmode, menu);
        }

        public ActionModeCallbackWrapper(android.support.v7.view.ActionMode.Callback callback)
        {
            this$0 = ActionBarActivityDelegateBase.this;
            super();
            mWrapped = callback;
        }
    }

}
