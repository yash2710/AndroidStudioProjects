// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            FragmentManager, FragmentActivity, Fragment, BackStackRecord, 
//            LoaderManagerImpl, SuperNotCalledException, NoSaveStateFrameLayout, FragmentContainer, 
//            FragmentManagerState, FragmentState, BackStackState, FragmentTransaction

final class FragmentManagerImpl extends FragmentManager
{

    static final Interpolator ACCELERATE_CUBIC = new AccelerateInterpolator(1.5F);
    static final Interpolator ACCELERATE_QUINT = new AccelerateInterpolator(2.5F);
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC = new DecelerateInterpolator(1.5F);
    static final Interpolator DECELERATE_QUINT = new DecelerateInterpolator(2.5F);
    static final boolean HONEYCOMB = false;
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    ArrayList mActive;
    FragmentActivity mActivity;
    ArrayList mAdded;
    ArrayList mAvailBackStackIndices;
    ArrayList mAvailIndices;
    ArrayList mBackStack;
    ArrayList mBackStackChangeListeners;
    ArrayList mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    boolean mNeedMenuInvalidate;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList mPendingActions;
    SparseArray mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    Runnable mTmpActions[];

    FragmentManagerImpl()
    {
        mCurState = 0;
        mStateBundle = null;
        mStateArray = null;
        mExecCommit = new _cls1();
    }

    private void checkStateLoss()
    {
        if (mStateSaved)
        {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (mNoTransactionsBecause != null)
        {
            throw new IllegalStateException((new StringBuilder("Can not perform this action inside of ")).append(mNoTransactionsBecause).toString());
        } else
        {
            return;
        }
    }

    static Animation makeFadeAnimation(Context context, float f, float f1)
    {
        AlphaAnimation alphaanimation = new AlphaAnimation(f, f1);
        alphaanimation.setInterpolator(DECELERATE_CUBIC);
        alphaanimation.setDuration(220L);
        return alphaanimation;
    }

    static Animation makeOpenCloseAnimation(Context context, float f, float f1, float f2, float f3)
    {
        AnimationSet animationset = new AnimationSet(false);
        ScaleAnimation scaleanimation = new ScaleAnimation(f, f1, f, f1, 1, 0.5F, 1, 0.5F);
        scaleanimation.setInterpolator(DECELERATE_QUINT);
        scaleanimation.setDuration(220L);
        animationset.addAnimation(scaleanimation);
        AlphaAnimation alphaanimation = new AlphaAnimation(f2, f3);
        alphaanimation.setInterpolator(DECELERATE_CUBIC);
        alphaanimation.setDuration(220L);
        animationset.addAnimation(alphaanimation);
        return animationset;
    }

    public static int reverseTransit(int i)
    {
        switch (i)
        {
        default:
            return 0;

        case 4097: 
            return 8194;

        case 8194: 
            return 4097;

        case 4099: 
            return 4099;
        }
    }

    private void throwException(RuntimeException runtimeexception)
    {
        Log.e("FragmentManager", runtimeexception.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printwriter = new PrintWriter(new LogWriter("FragmentManager"));
        if (mActivity != null)
        {
            try
            {
                mActivity.dump("  ", null, printwriter, new String[0]);
            }
            catch (Exception exception1)
            {
                Log.e("FragmentManager", "Failed dumping state", exception1);
            }
        } else
        {
            try
            {
                dump("  ", null, printwriter, new String[0]);
            }
            catch (Exception exception)
            {
                Log.e("FragmentManager", "Failed dumping state", exception);
            }
        }
        throw runtimeexception;
    }

    public static int transitToStyleIndex(int i, boolean flag)
    {
        switch (i)
        {
        default:
            return -1;

        case 4097: 
            return !flag ? 2 : 1;

        case 8194: 
            return !flag ? 4 : 3;

        case 4099: 
            break;
        }
        return !flag ? 6 : 5;
    }

    final void addBackStackState(BackStackRecord backstackrecord)
    {
        if (mBackStack == null)
        {
            mBackStack = new ArrayList();
        }
        mBackStack.add(backstackrecord);
        reportBackStackChanged();
    }

    public final void addFragment(Fragment fragment, boolean flag)
    {
        if (mAdded == null)
        {
            mAdded = new ArrayList();
        }
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("add: ")).append(fragment).toString());
        }
        makeActive(fragment);
        if (!fragment.mDetached)
        {
            if (mAdded.contains(fragment))
            {
                throw new IllegalStateException((new StringBuilder("Fragment already added: ")).append(fragment).toString());
            }
            mAdded.add(fragment);
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mHasMenu && fragment.mMenuVisible)
            {
                mNeedMenuInvalidate = true;
            }
            if (flag)
            {
                moveToState(fragment);
            }
        }
    }

    public final void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if (mBackStackChangeListeners == null)
        {
            mBackStackChangeListeners = new ArrayList();
        }
        mBackStackChangeListeners.add(onbackstackchangedlistener);
    }

    public final int allocBackStackIndex(BackStackRecord backstackrecord)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        if (mAvailBackStackIndices != null && mAvailBackStackIndices.size() > 0)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        if (mBackStackIndices == null)
        {
            mBackStackIndices = new ArrayList();
        }
        i = mBackStackIndices.size();
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("Setting back stack index ")).append(i).append(" to ").append(backstackrecord).toString());
        }
        mBackStackIndices.add(backstackrecord);
        this;
        JVM INSTR monitorexit ;
        return i;
        int j;
        j = ((Integer)mAvailBackStackIndices.remove(-1 + mAvailBackStackIndices.size())).intValue();
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("Adding back stack index ")).append(j).append(" with ").append(backstackrecord).toString());
        }
        mBackStackIndices.set(j, backstackrecord);
        this;
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        throw exception;
    }

    public final void attachActivity(FragmentActivity fragmentactivity, FragmentContainer fragmentcontainer, Fragment fragment)
    {
        if (mActivity != null)
        {
            throw new IllegalStateException("Already attached");
        } else
        {
            mActivity = fragmentactivity;
            mContainer = fragmentcontainer;
            mParent = fragment;
            return;
        }
    }

    public final void attachFragment(Fragment fragment, int i, int j)
    {
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("attach: ")).append(fragment).toString());
        }
        if (fragment.mDetached)
        {
            fragment.mDetached = false;
            if (!fragment.mAdded)
            {
                if (mAdded == null)
                {
                    mAdded = new ArrayList();
                }
                if (mAdded.contains(fragment))
                {
                    throw new IllegalStateException((new StringBuilder("Fragment already added: ")).append(fragment).toString());
                }
                if (DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder("add from attach: ")).append(fragment).toString());
                }
                mAdded.add(fragment);
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible)
                {
                    mNeedMenuInvalidate = true;
                }
                moveToState(fragment, mCurState, i, j, false);
            }
        }
    }

    public final FragmentTransaction beginTransaction()
    {
        return new BackStackRecord(this);
    }

    public final void detachFragment(Fragment fragment, int i, int j)
    {
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("detach: ")).append(fragment).toString());
        }
        if (!fragment.mDetached)
        {
            fragment.mDetached = true;
            if (fragment.mAdded)
            {
                if (mAdded != null)
                {
                    if (DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder("remove from detach: ")).append(fragment).toString());
                    }
                    mAdded.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible)
                {
                    mNeedMenuInvalidate = true;
                }
                fragment.mAdded = false;
                moveToState(fragment, 1, i, j, false);
            }
        }
    }

    public final void dispatchActivityCreated()
    {
        mStateSaved = false;
        moveToState(2, false);
    }

    public final void dispatchConfigurationChanged(Configuration configuration)
    {
        if (mAdded != null)
        {
            for (int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null)
                {
                    fragment.performConfigurationChanged(configuration);
                }
            }

        }
    }

    public final boolean dispatchContextItemSelected(MenuItem menuitem)
    {
        ArrayList arraylist;
        boolean flag;
        arraylist = mAdded;
        flag = false;
        if (arraylist == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L7:
        int j;
        j = mAdded.size();
        flag = false;
        if (i >= j) goto _L2; else goto _L3
_L3:
        Fragment fragment = (Fragment)mAdded.get(i);
        if (fragment == null || !fragment.performContextItemSelected(menuitem)) goto _L5; else goto _L4
_L4:
        flag = true;
_L2:
        return flag;
_L5:
        i++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void dispatchCreate()
    {
        mStateSaved = false;
        moveToState(1, false);
    }

    public final boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        ArrayList arraylist = null;
        boolean flag;
        if (mAdded != null)
        {
            int j = 0;
            boolean flag1;
            for (flag = false; j < mAdded.size(); flag = flag1)
            {
                Fragment fragment1 = (Fragment)mAdded.get(j);
                if (fragment1 != null && fragment1.performCreateOptionsMenu(menu, menuinflater))
                {
                    flag = true;
                    if (arraylist == null)
                    {
                        arraylist = new ArrayList();
                    }
                    arraylist.add(fragment1);
                }
                flag1 = flag;
                j++;
            }

        } else
        {
            flag = false;
        }
        ArrayList arraylist1 = mCreatedMenus;
        int i = 0;
        if (arraylist1 != null)
        {
            for (; i < mCreatedMenus.size(); i++)
            {
                Fragment fragment = (Fragment)mCreatedMenus.get(i);
                if (arraylist == null || !arraylist.contains(fragment))
                {
                    fragment.onDestroyOptionsMenu();
                }
            }

        }
        mCreatedMenus = arraylist;
        return flag;
    }

    public final void dispatchDestroy()
    {
        mDestroyed = true;
        execPendingActions();
        moveToState(0, false);
        mActivity = null;
        mContainer = null;
        mParent = null;
    }

    public final void dispatchDestroyView()
    {
        moveToState(1, false);
    }

    public final void dispatchLowMemory()
    {
        if (mAdded != null)
        {
            for (int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null)
                {
                    fragment.performLowMemory();
                }
            }

        }
    }

    public final boolean dispatchOptionsItemSelected(MenuItem menuitem)
    {
        ArrayList arraylist;
        boolean flag;
        arraylist = mAdded;
        flag = false;
        if (arraylist == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L7:
        int j;
        j = mAdded.size();
        flag = false;
        if (i >= j) goto _L2; else goto _L3
_L3:
        Fragment fragment = (Fragment)mAdded.get(i);
        if (fragment == null || !fragment.performOptionsItemSelected(menuitem)) goto _L5; else goto _L4
_L4:
        flag = true;
_L2:
        return flag;
_L5:
        i++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void dispatchOptionsMenuClosed(Menu menu)
    {
        if (mAdded != null)
        {
            for (int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null)
                {
                    fragment.performOptionsMenuClosed(menu);
                }
            }

        }
    }

    public final void dispatchPause()
    {
        moveToState(4, false);
    }

    public final boolean dispatchPrepareOptionsMenu(Menu menu)
    {
        boolean flag;
        if (mAdded != null)
        {
            int i = 0;
            flag = false;
            for (; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null && fragment.performPrepareOptionsMenu(menu))
                {
                    flag = true;
                }
            }

        } else
        {
            flag = false;
        }
        return flag;
    }

    public final void dispatchReallyStop()
    {
        moveToState(2, false);
    }

    public final void dispatchResume()
    {
        mStateSaved = false;
        moveToState(5, false);
    }

    public final void dispatchStart()
    {
        mStateSaved = false;
        moveToState(4, false);
    }

    public final void dispatchStop()
    {
        mStateSaved = true;
        moveToState(3, false);
    }

    public final void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        int i;
        i = 0;
        String s1 = (new StringBuilder()).append(s).append("    ").toString();
        if (mActive != null)
        {
            int k2 = mActive.size();
            if (k2 > 0)
            {
                printwriter.print(s);
                printwriter.print("Active Fragments in ");
                printwriter.print(Integer.toHexString(System.identityHashCode(this)));
                printwriter.println(":");
                for (int l2 = 0; l2 < k2; l2++)
                {
                    Fragment fragment2 = (Fragment)mActive.get(l2);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(l2);
                    printwriter.print(": ");
                    printwriter.println(fragment2);
                    if (fragment2 != null)
                    {
                        fragment2.dump(s1, filedescriptor, printwriter, as);
                    }
                }

            }
        }
        if (mAdded != null)
        {
            int i2 = mAdded.size();
            if (i2 > 0)
            {
                printwriter.print(s);
                printwriter.println("Added Fragments:");
                for (int j2 = 0; j2 < i2; j2++)
                {
                    Fragment fragment1 = (Fragment)mAdded.get(j2);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(j2);
                    printwriter.print(": ");
                    printwriter.println(fragment1.toString());
                }

            }
        }
        if (mCreatedMenus != null)
        {
            int k1 = mCreatedMenus.size();
            if (k1 > 0)
            {
                printwriter.print(s);
                printwriter.println("Fragments Created Menus:");
                for (int l1 = 0; l1 < k1; l1++)
                {
                    Fragment fragment = (Fragment)mCreatedMenus.get(l1);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(l1);
                    printwriter.print(": ");
                    printwriter.println(fragment.toString());
                }

            }
        }
        if (mBackStack != null)
        {
            int i1 = mBackStack.size();
            if (i1 > 0)
            {
                printwriter.print(s);
                printwriter.println("Back Stack:");
                for (int j1 = 0; j1 < i1; j1++)
                {
                    BackStackRecord backstackrecord1 = (BackStackRecord)mBackStack.get(j1);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(j1);
                    printwriter.print(": ");
                    printwriter.println(backstackrecord1.toString());
                    backstackrecord1.dump(s1, filedescriptor, printwriter, as);
                }

            }
        }
        this;
        JVM INSTR monitorenter ;
        if (mBackStackIndices == null) goto _L2; else goto _L1
_L1:
        int k = mBackStackIndices.size();
        if (k <= 0) goto _L2; else goto _L3
_L3:
        printwriter.print(s);
        printwriter.println("Back Stack Indices:");
        int l = 0;
_L4:
        if (l >= k)
        {
            break; /* Loop/switch isn't completed */
        }
        BackStackRecord backstackrecord = (BackStackRecord)mBackStackIndices.get(l);
        printwriter.print(s);
        printwriter.print("  #");
        printwriter.print(l);
        printwriter.print(": ");
        printwriter.println(backstackrecord);
        l++;
        if (true) goto _L4; else goto _L2
_L2:
        if (mAvailBackStackIndices != null && mAvailBackStackIndices.size() > 0)
        {
            printwriter.print(s);
            printwriter.print("mAvailBackStackIndices: ");
            printwriter.println(Arrays.toString(mAvailBackStackIndices.toArray()));
        }
        this;
        JVM INSTR monitorexit ;
        if (mPendingActions != null)
        {
            int j = mPendingActions.size();
            if (j > 0)
            {
                printwriter.print(s);
                printwriter.println("Pending Actions:");
                for (; i < j; i++)
                {
                    Runnable runnable = (Runnable)mPendingActions.get(i);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(i);
                    printwriter.print(": ");
                    printwriter.println(runnable);
                }

            }
        }
        break MISSING_BLOCK_LABEL_694;
        Exception exception;
        exception;
        throw exception;
        printwriter.print(s);
        printwriter.println("FragmentManager misc state:");
        printwriter.print(s);
        printwriter.print("  mActivity=");
        printwriter.println(mActivity);
        printwriter.print(s);
        printwriter.print("  mContainer=");
        printwriter.println(mContainer);
        if (mParent != null)
        {
            printwriter.print(s);
            printwriter.print("  mParent=");
            printwriter.println(mParent);
        }
        printwriter.print(s);
        printwriter.print("  mCurState=");
        printwriter.print(mCurState);
        printwriter.print(" mStateSaved=");
        printwriter.print(mStateSaved);
        printwriter.print(" mDestroyed=");
        printwriter.println(mDestroyed);
        if (mNeedMenuInvalidate)
        {
            printwriter.print(s);
            printwriter.print("  mNeedMenuInvalidate=");
            printwriter.println(mNeedMenuInvalidate);
        }
        if (mNoTransactionsBecause != null)
        {
            printwriter.print(s);
            printwriter.print("  mNoTransactionsBecause=");
            printwriter.println(mNoTransactionsBecause);
        }
        if (mAvailIndices != null && mAvailIndices.size() > 0)
        {
            printwriter.print(s);
            printwriter.print("  mAvailIndices: ");
            printwriter.println(Arrays.toString(mAvailIndices.toArray()));
        }
        return;
    }

    public final void enqueueAction(Runnable runnable, boolean flag)
    {
        if (!flag)
        {
            checkStateLoss();
        }
        this;
        JVM INSTR monitorenter ;
        if (mDestroyed || mActivity == null)
        {
            throw new IllegalStateException("Activity has been destroyed");
        }
        break MISSING_BLOCK_LABEL_40;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if (mPendingActions == null)
        {
            mPendingActions = new ArrayList();
        }
        mPendingActions.add(runnable);
        if (mPendingActions.size() == 1)
        {
            mActivity.mHandler.removeCallbacks(mExecCommit);
            mActivity.mHandler.post(mExecCommit);
        }
        this;
        JVM INSTR monitorexit ;
    }

    public final boolean execPendingActions()
    {
        if (mExecutingActions)
        {
            throw new IllegalStateException("Recursive entry to executePendingTransactions");
        }
        if (Looper.myLooper() != mActivity.mHandler.getLooper())
        {
            throw new IllegalStateException("Must be called from main thread of process");
        }
        boolean flag = false;
_L2:
        this;
        JVM INSTR monitorenter ;
        if (mPendingActions != null && mPendingActions.size() != 0)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        this;
        JVM INSTR monitorexit ;
        boolean flag1;
        if (!mHavePendingDeferredStart)
        {
            break MISSING_BLOCK_LABEL_276;
        }
        int i = 0;
        flag1 = false;
        for (; i < mActive.size(); i++)
        {
            Fragment fragment = (Fragment)mActive.get(i);
            if (fragment != null && fragment.mLoaderManager != null)
            {
                flag1 |= fragment.mLoaderManager.hasRunningLoaders();
            }
        }

        break; /* Loop/switch isn't completed */
        int j;
        j = mPendingActions.size();
        if (mTmpActions == null || mTmpActions.length < j)
        {
            mTmpActions = new Runnable[j];
        }
        mPendingActions.toArray(mTmpActions);
        mPendingActions.clear();
        mActivity.mHandler.removeCallbacks(mExecCommit);
        this;
        JVM INSTR monitorexit ;
        mExecutingActions = true;
        for (int k = 0; k < j; k++)
        {
            mTmpActions[k].run();
            mTmpActions[k] = null;
        }

        break MISSING_BLOCK_LABEL_252;
        Exception exception;
        exception;
        throw exception;
        mExecutingActions = false;
        flag = true;
        if (true) goto _L2; else goto _L1
_L1:
        if (!flag1)
        {
            mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
        return flag;
    }

    public final boolean executePendingTransactions()
    {
        return execPendingActions();
    }

    public final Fragment findFragmentById(int i)
    {
        if (mAdded == null) goto _L2; else goto _L1
_L1:
        int k = -1 + mAdded.size();
_L8:
        if (k < 0) goto _L2; else goto _L3
_L3:
        Fragment fragment = (Fragment)mAdded.get(k);
        if (fragment == null || fragment.mFragmentId != i) goto _L5; else goto _L4
_L4:
        return fragment;
_L5:
        k--;
        continue; /* Loop/switch isn't completed */
_L2:
label0:
        {
            if (mActive == null)
            {
                break label0;
            }
            int j = -1 + mActive.size();
            do
            {
                if (j < 0)
                {
                    break label0;
                }
                fragment = (Fragment)mActive.get(j);
                if (fragment != null && fragment.mFragmentId == i)
                {
                    break;
                }
                j--;
            } while (true);
        }
        if (true) goto _L4; else goto _L6
_L6:
        return null;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final Fragment findFragmentByTag(String s)
    {
        if (mAdded == null || s == null) goto _L2; else goto _L1
_L1:
        int j = -1 + mAdded.size();
_L8:
        if (j < 0) goto _L2; else goto _L3
_L3:
        Fragment fragment = (Fragment)mAdded.get(j);
        if (fragment == null || !s.equals(fragment.mTag)) goto _L5; else goto _L4
_L4:
        return fragment;
_L5:
        j--;
        continue; /* Loop/switch isn't completed */
_L2:
label0:
        {
            if (mActive == null || s == null)
            {
                break label0;
            }
            int i = -1 + mActive.size();
            do
            {
                if (i < 0)
                {
                    break label0;
                }
                fragment = (Fragment)mActive.get(i);
                if (fragment != null && s.equals(fragment.mTag))
                {
                    break;
                }
                i--;
            } while (true);
        }
        if (true) goto _L4; else goto _L6
_L6:
        return null;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final Fragment findFragmentByWho(String s)
    {
        if (mActive != null && s != null)
        {
            for (int i = -1 + mActive.size(); i >= 0; i--)
            {
                Fragment fragment = (Fragment)mActive.get(i);
                if (fragment == null)
                {
                    continue;
                }
                Fragment fragment1 = fragment.findFragmentByWho(s);
                if (fragment1 != null)
                {
                    return fragment1;
                }
            }

        }
        return null;
    }

    public final void freeBackStackIndex(int i)
    {
        this;
        JVM INSTR monitorenter ;
        mBackStackIndices.set(i, null);
        if (mAvailBackStackIndices == null)
        {
            mAvailBackStackIndices = new ArrayList();
        }
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("Freeing back stack index ")).append(i).toString());
        }
        mAvailBackStackIndices.add(Integer.valueOf(i));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final FragmentManager.BackStackEntry getBackStackEntryAt(int i)
    {
        return (FragmentManager.BackStackEntry)mBackStack.get(i);
    }

    public final int getBackStackEntryCount()
    {
        if (mBackStack != null)
        {
            return mBackStack.size();
        } else
        {
            return 0;
        }
    }

    public final Fragment getFragment(Bundle bundle, String s)
    {
        int i = bundle.getInt(s, -1);
        Fragment fragment;
        if (i == -1)
        {
            fragment = null;
        } else
        {
            if (i >= mActive.size())
            {
                throwException(new IllegalStateException((new StringBuilder("Fragment no longer exists for key ")).append(s).append(": index ").append(i).toString()));
            }
            fragment = (Fragment)mActive.get(i);
            if (fragment == null)
            {
                throwException(new IllegalStateException((new StringBuilder("Fragment no longer exists for key ")).append(s).append(": index ").append(i).toString()));
                return fragment;
            }
        }
        return fragment;
    }

    public final List getFragments()
    {
        return mActive;
    }

    public final void hideFragment(Fragment fragment, int i, int j)
    {
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("hide: ")).append(fragment).toString());
        }
        if (!fragment.mHidden)
        {
            fragment.mHidden = true;
            if (fragment.mView != null)
            {
                Animation animation = loadAnimation(fragment, i, false, j);
                if (animation != null)
                {
                    fragment.mView.startAnimation(animation);
                }
                fragment.mView.setVisibility(8);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible)
            {
                mNeedMenuInvalidate = true;
            }
            fragment.onHiddenChanged(true);
        }
    }

    public final boolean isDestroyed()
    {
        return mDestroyed;
    }

    final Animation loadAnimation(Fragment fragment, int i, boolean flag, int j)
    {
        Animation animation = fragment.onCreateAnimation(i, flag, fragment.mNextAnim);
        if (animation == null)
        {
            if (fragment.mNextAnim == 0 || (animation = AnimationUtils.loadAnimation(mActivity, fragment.mNextAnim)) == null)
            {
                if (i == 0)
                {
                    return null;
                }
                int k = transitToStyleIndex(i, flag);
                if (k < 0)
                {
                    return null;
                }
                switch (k)
                {
                default:
                    if (j == 0 && mActivity.getWindow() != null)
                    {
                        j = mActivity.getWindow().getAttributes().windowAnimations;
                    }
                    if (j == 0)
                    {
                        return null;
                    } else
                    {
                        return null;
                    }

                case 1: // '\001'
                    return makeOpenCloseAnimation(mActivity, 1.125F, 1.0F, 0.0F, 1.0F);

                case 2: // '\002'
                    return makeOpenCloseAnimation(mActivity, 1.0F, 0.975F, 1.0F, 0.0F);

                case 3: // '\003'
                    return makeOpenCloseAnimation(mActivity, 0.975F, 1.0F, 0.0F, 1.0F);

                case 4: // '\004'
                    return makeOpenCloseAnimation(mActivity, 1.0F, 1.075F, 1.0F, 0.0F);

                case 5: // '\005'
                    return makeFadeAnimation(mActivity, 0.0F, 1.0F);

                case 6: // '\006'
                    return makeFadeAnimation(mActivity, 1.0F, 0.0F);
                }
            }
        }
        return animation;
    }

    final void makeActive(Fragment fragment)
    {
        if (fragment.mIndex < 0)
        {
            if (mAvailIndices == null || mAvailIndices.size() <= 0)
            {
                if (mActive == null)
                {
                    mActive = new ArrayList();
                }
                fragment.setIndex(mActive.size(), mParent);
                mActive.add(fragment);
            } else
            {
                fragment.setIndex(((Integer)mAvailIndices.remove(-1 + mAvailIndices.size())).intValue(), mParent);
                mActive.set(fragment.mIndex, fragment);
            }
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder("Allocated fragment index ")).append(fragment).toString());
                return;
            }
        }
    }

    final void makeInactive(Fragment fragment)
    {
        if (fragment.mIndex < 0)
        {
            return;
        }
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("Freeing fragment index ")).append(fragment).toString());
        }
        mActive.set(fragment.mIndex, null);
        if (mAvailIndices == null)
        {
            mAvailIndices = new ArrayList();
        }
        mAvailIndices.add(Integer.valueOf(fragment.mIndex));
        mActivity.invalidateSupportFragment(fragment.mWho);
        fragment.initState();
    }

    final void moveToState(int i, int j, int k, boolean flag)
    {
        if (mActivity == null && i != 0)
        {
            throw new IllegalStateException("No activity");
        }
        if (flag || mCurState != i) goto _L2; else goto _L1
_L1:
        return;
_L2:
        mCurState = i;
        if (mActive == null) goto _L1; else goto _L3
_L3:
        int l;
        boolean flag1;
        l = 0;
        flag1 = false;
_L5:
        boolean flag2;
        if (l >= mActive.size())
        {
            break; /* Loop/switch isn't completed */
        }
        Fragment fragment = (Fragment)mActive.get(l);
        if (fragment == null)
        {
            break MISSING_BLOCK_LABEL_170;
        }
        moveToState(fragment, i, j, k, false);
        if (fragment.mLoaderManager == null)
        {
            break MISSING_BLOCK_LABEL_170;
        }
        flag2 = flag1 | fragment.mLoaderManager.hasRunningLoaders();
_L6:
        l++;
        flag1 = flag2;
        if (true) goto _L5; else goto _L4
_L4:
        if (!flag1)
        {
            startPendingDeferredFragments();
        }
        if (mNeedMenuInvalidate && mActivity != null && mCurState == 5)
        {
            mActivity.supportInvalidateOptionsMenu();
            mNeedMenuInvalidate = false;
            return;
        }
          goto _L1
        flag2 = flag1;
          goto _L6
    }

    final void moveToState(int i, boolean flag)
    {
        moveToState(i, 0, 0, flag);
    }

    final void moveToState(Fragment fragment)
    {
        moveToState(fragment, mCurState, 0, 0, false);
    }

    final void moveToState(final Fragment fragment, int i, int j, int k, boolean flag)
    {
        if ((!fragment.mAdded || fragment.mDetached) && i > 1)
        {
            i = 1;
        }
        if (fragment.mRemoving && i > fragment.mState)
        {
            i = fragment.mState;
        }
        if (fragment.mDeferStart && fragment.mState < 4 && i > 3)
        {
            i = 3;
        }
        if (fragment.mState >= i) goto _L2; else goto _L1
_L1:
        if (fragment.mFromLayout && !fragment.mInLayout)
        {
            return;
        }
        if (fragment.mAnimatingAway != null)
        {
            fragment.mAnimatingAway = null;
            moveToState(fragment, fragment.mStateAfterAnimating, 0, 0, true);
        }
        fragment.mState;
        JVM INSTR tableswitch 0 4: default 148
    //                   0 154
    //                   1 489
    //                   2 780
    //                   3 780
    //                   4 818;
           goto _L3 _L4 _L5 _L6 _L6 _L7
_L3:
        fragment.mState = i;
        return;
_L4:
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("moveto CREATED: ")).append(fragment).toString());
        }
        if (fragment.mSavedFragmentState != null)
        {
            fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
            fragment.mTarget = getFragment(fragment.mSavedFragmentState, "android:target_state");
            if (fragment.mTarget != null)
            {
                fragment.mTargetRequestCode = fragment.mSavedFragmentState.getInt("android:target_req_state", 0);
            }
            fragment.mUserVisibleHint = fragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
            if (!fragment.mUserVisibleHint)
            {
                fragment.mDeferStart = true;
                if (i > 3)
                {
                    i = 3;
                }
            }
        }
        fragment.mActivity = mActivity;
        fragment.mParentFragment = mParent;
        FragmentManagerImpl fragmentmanagerimpl;
        if (mParent != null)
        {
            fragmentmanagerimpl = mParent.mChildFragmentManager;
        } else
        {
            fragmentmanagerimpl = mActivity.mFragments;
        }
        fragment.mFragmentManager = fragmentmanagerimpl;
        fragment.mCalled = false;
        fragment.onAttach(mActivity);
        if (!fragment.mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onAttach()").toString());
        }
        if (fragment.mParentFragment == null)
        {
            mActivity.onAttachFragment(fragment);
        }
        if (!fragment.mRetaining)
        {
            fragment.performCreate(fragment.mSavedFragmentState);
        }
        fragment.mRetaining = false;
        if (fragment.mFromLayout)
        {
            fragment.mView = fragment.performCreateView(fragment.getLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
            Animation animation1;
            if (fragment.mView != null)
            {
                fragment.mInnerView = fragment.mView;
                fragment.mView = NoSaveStateFrameLayout.wrap(fragment.mView);
                if (fragment.mHidden)
                {
                    fragment.mView.setVisibility(8);
                }
                fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
            } else
            {
                fragment.mInnerView = null;
            }
        }
_L5:
        if (i > 1)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder("moveto ACTIVITY_CREATED: ")).append(fragment).toString());
            }
            if (!fragment.mFromLayout)
            {
                View view;
                Animation animation;
                ViewGroup viewgroup;
                if (fragment.mContainerId != 0)
                {
                    viewgroup = (ViewGroup)mContainer.findViewById(fragment.mContainerId);
                    if (viewgroup == null && !fragment.mRestored)
                    {
                        throwException(new IllegalArgumentException((new StringBuilder("No view found for id 0x")).append(Integer.toHexString(fragment.mContainerId)).append(" (").append(fragment.getResources().getResourceName(fragment.mContainerId)).append(") for fragment ").append(fragment).toString()));
                    }
                } else
                {
                    viewgroup = null;
                }
                fragment.mContainer = viewgroup;
                fragment.mView = fragment.performCreateView(fragment.getLayoutInflater(fragment.mSavedFragmentState), viewgroup, fragment.mSavedFragmentState);
                if (fragment.mView != null)
                {
                    fragment.mInnerView = fragment.mView;
                    fragment.mView = NoSaveStateFrameLayout.wrap(fragment.mView);
                    if (viewgroup != null)
                    {
                        animation1 = loadAnimation(fragment, j, true, k);
                        if (animation1 != null)
                        {
                            fragment.mView.startAnimation(animation1);
                        }
                        viewgroup.addView(fragment.mView);
                    }
                    if (fragment.mHidden)
                    {
                        fragment.mView.setVisibility(8);
                    }
                    fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                } else
                {
                    fragment.mInnerView = null;
                }
            }
            fragment.performActivityCreated(fragment.mSavedFragmentState);
            if (fragment.mView != null)
            {
                fragment.restoreViewState(fragment.mSavedFragmentState);
            }
            fragment.mSavedFragmentState = null;
        }
_L6:
        if (i > 3)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder("moveto STARTED: ")).append(fragment).toString());
            }
            fragment.performStart();
        }
_L7:
        if (i > 4)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder("moveto RESUMED: ")).append(fragment).toString());
            }
            fragment.mResumed = true;
            fragment.performResume();
            fragment.mSavedFragmentState = null;
            fragment.mSavedViewState = null;
        }
          goto _L8
_L2:
        if (fragment.mState <= i) goto _L8; else goto _L9
_L9:
        fragment.mState;
        JVM INSTR tableswitch 1 5: default 936
    //                   1 939
    //                   2 1109
    //                   3 1071
    //                   4 1033
    //                   5 990;
           goto _L10 _L11 _L12 _L13 _L14 _L15
_L10:
        break; /* Loop/switch isn't completed */
_L11:
        if (i <= 0)
        {
            if (mDestroyed && fragment.mAnimatingAway != null)
            {
                view = fragment.mAnimatingAway;
                fragment.mAnimatingAway = null;
                view.clearAnimation();
            }
            if (fragment.mAnimatingAway != null)
            {
                fragment.mStateAfterAnimating = i;
                i = 1;
            } else
            {
                if (DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder("movefrom CREATED: ")).append(fragment).toString());
                }
                if (!fragment.mRetaining)
                {
                    fragment.performDestroy();
                }
                fragment.mCalled = false;
                fragment.onDetach();
                if (!fragment.mCalled)
                {
                    throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onDetach()").toString());
                }
                if (!flag)
                {
                    if (!fragment.mRetaining)
                    {
                        makeInactive(fragment);
                    } else
                    {
                        fragment.mActivity = null;
                        fragment.mParentFragment = null;
                        fragment.mFragmentManager = null;
                        fragment.mChildFragmentManager = null;
                    }
                }
            }
        }
        break; /* Loop/switch isn't completed */
_L15:
        if (i < 5)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder("movefrom RESUMED: ")).append(fragment).toString());
            }
            fragment.performPause();
            fragment.mResumed = false;
        }
_L14:
        if (i < 4)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder("movefrom STARTED: ")).append(fragment).toString());
            }
            fragment.performStop();
        }
_L13:
        if (i < 3)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder("movefrom STOPPED: ")).append(fragment).toString());
            }
            fragment.performReallyStop();
        }
_L12:
        if (i < 2)
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder("movefrom ACTIVITY_CREATED: ")).append(fragment).toString());
            }
            if (fragment.mView != null && !mActivity.isFinishing() && fragment.mSavedViewState == null)
            {
                saveFragmentViewState(fragment);
            }
            fragment.performDestroyView();
            if (fragment.mView != null && fragment.mContainer != null)
            {
                if (mCurState > 0 && !mDestroyed)
                {
                    animation = loadAnimation(fragment, j, false, k);
                } else
                {
                    animation = null;
                }
                if (animation != null)
                {
                    fragment.mAnimatingAway = fragment.mView;
                    fragment.mStateAfterAnimating = i;
                    animation.setAnimationListener(new _cls5());
                    fragment.mView.startAnimation(animation);
                }
                fragment.mContainer.removeView(fragment.mView);
            }
            fragment.mContainer = null;
            fragment.mView = null;
            fragment.mInnerView = null;
        }
        if (true) goto _L11; else goto _L8
_L8:
        if (true) goto _L3; else goto _L16
_L16:
    }

    public final void noteStateNotSaved()
    {
        mStateSaved = false;
    }

    public final void performPendingDeferredStart(Fragment fragment)
    {
label0:
        {
            if (fragment.mDeferStart)
            {
                if (!mExecutingActions)
                {
                    break label0;
                }
                mHavePendingDeferredStart = true;
            }
            return;
        }
        fragment.mDeferStart = false;
        moveToState(fragment, mCurState, 0, 0, false);
    }

    public final void popBackStack()
    {
        enqueueAction(new _cls2(), false);
    }

    public final void popBackStack(final int id, final int flags)
    {
        if (id < 0)
        {
            throw new IllegalArgumentException((new StringBuilder("Bad id: ")).append(id).toString());
        } else
        {
            enqueueAction(new _cls4(), false);
            return;
        }
    }

    public final void popBackStack(final String name, final int flags)
    {
        enqueueAction(new _cls3(), false);
    }

    public final boolean popBackStackImmediate()
    {
        checkStateLoss();
        executePendingTransactions();
        return popBackStackState(mActivity.mHandler, null, -1, 0);
    }

    public final boolean popBackStackImmediate(int i, int j)
    {
        checkStateLoss();
        executePendingTransactions();
        if (i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder("Bad id: ")).append(i).toString());
        } else
        {
            return popBackStackState(mActivity.mHandler, null, i, j);
        }
    }

    public final boolean popBackStackImmediate(String s, int i)
    {
        checkStateLoss();
        executePendingTransactions();
        return popBackStackState(mActivity.mHandler, s, -1, i);
    }

    final boolean popBackStackState(Handler handler, String s, int i, int j)
    {
        if (mBackStack != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int l1;
        if (s != null || i >= 0 || (j & 1) != 0) goto _L4; else goto _L3
_L3:
        if ((l1 = -1 + mBackStack.size()) < 0) goto _L1; else goto _L5
_L5:
        ((BackStackRecord)mBackStack.remove(l1)).popFromBackStack(true);
_L8:
        reportBackStackChanged();
        return true;
_L4:
        int k;
        int l;
        k = -1;
        if (s == null && i < 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        l = -1 + mBackStack.size();
        do
        {
            if (l < 0)
            {
                break;
            }
            BackStackRecord backstackrecord2 = (BackStackRecord)mBackStack.get(l);
            if (s != null && s.equals(backstackrecord2.getName()) || i >= 0 && i == backstackrecord2.mIndex)
            {
                break;
            }
            l--;
        } while (true);
        if (l < 0) goto _L1; else goto _L6
_L6:
        if ((j & 1) != 0)
        {
            l--;
            do
            {
                if (l < 0)
                {
                    break;
                }
                BackStackRecord backstackrecord1 = (BackStackRecord)mBackStack.get(l);
                if ((s == null || !s.equals(backstackrecord1.getName())) && (i < 0 || i != backstackrecord1.mIndex))
                {
                    break;
                }
                l--;
            } while (true);
        }
        k = l;
        if (k == -1 + mBackStack.size()) goto _L1; else goto _L7
_L7:
        ArrayList arraylist = new ArrayList();
        for (int i1 = -1 + mBackStack.size(); i1 > k; i1--)
        {
            arraylist.add(mBackStack.remove(i1));
        }

        int j1 = -1 + arraylist.size();
        int k1 = 0;
        while (k1 <= j1) 
        {
            if (DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder("Popping back stack state: ")).append(arraylist.get(k1)).toString());
            }
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(k1);
            boolean flag;
            if (k1 == j1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            backstackrecord.popFromBackStack(flag);
            k1++;
        }
          goto _L8
    }

    public final void putFragment(Bundle bundle, String s, Fragment fragment)
    {
        if (fragment.mIndex < 0)
        {
            throwException(new IllegalStateException((new StringBuilder("Fragment ")).append(fragment).append(" is not currently in the FragmentManager").toString()));
        }
        bundle.putInt(s, fragment.mIndex);
    }

    public final void removeFragment(Fragment fragment, int i, int j)
    {
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("remove: ")).append(fragment).append(" nesting=").append(fragment.mBackStackNesting).toString());
        }
        boolean flag;
        if (!fragment.isInBackStack())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!fragment.mDetached || flag)
        {
            if (mAdded != null)
            {
                mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible)
            {
                mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
            int k;
            if (flag)
            {
                k = 0;
            } else
            {
                k = 1;
            }
            moveToState(fragment, k, i, j, false);
        }
    }

    public final void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if (mBackStackChangeListeners != null)
        {
            mBackStackChangeListeners.remove(onbackstackchangedlistener);
        }
    }

    final void reportBackStackChanged()
    {
        if (mBackStackChangeListeners != null)
        {
            for (int i = 0; i < mBackStackChangeListeners.size(); i++)
            {
                ((FragmentManager.OnBackStackChangedListener)mBackStackChangeListeners.get(i)).onBackStackChanged();
            }

        }
    }

    final void restoreAllState(Parcelable parcelable, ArrayList arraylist)
    {
        if (parcelable != null) goto _L2; else goto _L1
_L1:
        FragmentManagerState fragmentmanagerstate;
        return;
_L2:
        if ((fragmentmanagerstate = (FragmentManagerState)parcelable).mActive != null)
        {
            if (arraylist != null)
            {
                for (int i1 = 0; i1 < arraylist.size(); i1++)
                {
                    Fragment fragment3 = (Fragment)arraylist.get(i1);
                    if (DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder("restoreAllState: re-attaching retained ")).append(fragment3).toString());
                    }
                    FragmentState fragmentstate1 = fragmentmanagerstate.mActive[fragment3.mIndex];
                    fragmentstate1.mInstance = fragment3;
                    fragment3.mSavedViewState = null;
                    fragment3.mBackStackNesting = 0;
                    fragment3.mInLayout = false;
                    fragment3.mAdded = false;
                    fragment3.mTarget = null;
                    if (fragmentstate1.mSavedFragmentState != null)
                    {
                        fragmentstate1.mSavedFragmentState.setClassLoader(mActivity.getClassLoader());
                        fragment3.mSavedViewState = fragmentstate1.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                    }
                }

            }
            mActive = new ArrayList(fragmentmanagerstate.mActive.length);
            if (mAvailIndices != null)
            {
                mAvailIndices.clear();
            }
            int i = 0;
            while (i < fragmentmanagerstate.mActive.length) 
            {
                FragmentState fragmentstate = fragmentmanagerstate.mActive[i];
                if (fragmentstate != null)
                {
                    Fragment fragment2 = fragmentstate.instantiate(mActivity, mParent);
                    if (DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder("restoreAllState: active #")).append(i).append(": ").append(fragment2).toString());
                    }
                    mActive.add(fragment2);
                    fragmentstate.mInstance = null;
                } else
                {
                    mActive.add(null);
                    if (mAvailIndices == null)
                    {
                        mAvailIndices = new ArrayList();
                    }
                    if (DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder("restoreAllState: avail #")).append(i).toString());
                    }
                    mAvailIndices.add(Integer.valueOf(i));
                }
                i++;
            }
            if (arraylist != null)
            {
                int l = 0;
                while (l < arraylist.size()) 
                {
                    Fragment fragment1 = (Fragment)arraylist.get(l);
                    if (fragment1.mTargetIndex >= 0)
                    {
                        if (fragment1.mTargetIndex < mActive.size())
                        {
                            fragment1.mTarget = (Fragment)mActive.get(fragment1.mTargetIndex);
                        } else
                        {
                            Log.w("FragmentManager", (new StringBuilder("Re-attaching retained fragment ")).append(fragment1).append(" target no longer exists: ").append(fragment1.mTargetIndex).toString());
                            fragment1.mTarget = null;
                        }
                    }
                    l++;
                }
            }
            if (fragmentmanagerstate.mAdded != null)
            {
                mAdded = new ArrayList(fragmentmanagerstate.mAdded.length);
                for (int k = 0; k < fragmentmanagerstate.mAdded.length; k++)
                {
                    Fragment fragment = (Fragment)mActive.get(fragmentmanagerstate.mAdded[k]);
                    if (fragment == null)
                    {
                        throwException(new IllegalStateException((new StringBuilder("No instantiated fragment for index #")).append(fragmentmanagerstate.mAdded[k]).toString()));
                    }
                    fragment.mAdded = true;
                    if (DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder("restoreAllState: added #")).append(k).append(": ").append(fragment).toString());
                    }
                    if (mAdded.contains(fragment))
                    {
                        throw new IllegalStateException("Already added!");
                    }
                    mAdded.add(fragment);
                }

            } else
            {
                mAdded = null;
            }
            if (fragmentmanagerstate.mBackStack != null)
            {
                mBackStack = new ArrayList(fragmentmanagerstate.mBackStack.length);
                int j = 0;
                while (j < fragmentmanagerstate.mBackStack.length) 
                {
                    BackStackRecord backstackrecord = fragmentmanagerstate.mBackStack[j].instantiate(this);
                    if (DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder("restoreAllState: back stack #")).append(j).append(" (index ").append(backstackrecord.mIndex).append("): ").append(backstackrecord).toString());
                        backstackrecord.dump("  ", new PrintWriter(new LogWriter("FragmentManager")), false);
                    }
                    mBackStack.add(backstackrecord);
                    if (backstackrecord.mIndex >= 0)
                    {
                        setBackStackIndex(backstackrecord.mIndex, backstackrecord);
                    }
                    j++;
                }
            } else
            {
                mBackStack = null;
                return;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    final ArrayList retainNonConfig()
    {
        ArrayList arraylist = mActive;
        ArrayList arraylist1 = null;
        if (arraylist != null)
        {
            int i = 0;
            while (i < mActive.size()) 
            {
                Fragment fragment = (Fragment)mActive.get(i);
                if (fragment == null || !fragment.mRetainInstance)
                {
                    continue;
                }
                if (arraylist1 == null)
                {
                    arraylist1 = new ArrayList();
                }
                arraylist1.add(fragment);
                fragment.mRetaining = true;
                int j;
                if (fragment.mTarget != null)
                {
                    j = fragment.mTarget.mIndex;
                } else
                {
                    j = -1;
                }
                fragment.mTargetIndex = j;
                if (DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder("retainNonConfig: keeping retained ")).append(fragment).toString());
                }
                i++;
            }
        }
        return arraylist1;
    }

    final Parcelable saveAllState()
    {
        execPendingActions();
        if (HONEYCOMB)
        {
            mStateSaved = true;
        }
        if (mActive != null && mActive.size() > 0) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        int i = mActive.size();
        FragmentState afragmentstate[] = new FragmentState[i];
        int j = 0;
        boolean flag = false;
        while (j < i) 
        {
            Fragment fragment = (Fragment)mActive.get(j);
            int ai[];
            ArrayList arraylist;
            BackStackState abackstackstate[];
            FragmentManagerState fragmentmanagerstate;
            int k;
            int l;
            int i1;
            int j1;
            boolean flag1;
            if (fragment != null)
            {
                if (fragment.mIndex < 0)
                {
                    throwException(new IllegalStateException((new StringBuilder("Failure saving state: active ")).append(fragment).append(" has cleared index: ").append(fragment.mIndex).toString()));
                }
                FragmentState fragmentstate = new FragmentState(fragment);
                afragmentstate[j] = fragmentstate;
                if (fragment.mState > 0 && fragmentstate.mSavedFragmentState == null)
                {
                    fragmentstate.mSavedFragmentState = saveFragmentBasicState(fragment);
                    if (fragment.mTarget != null)
                    {
                        if (fragment.mTarget.mIndex < 0)
                        {
                            throwException(new IllegalStateException((new StringBuilder("Failure saving state: ")).append(fragment).append(" has target not in fragment manager: ").append(fragment.mTarget).toString()));
                        }
                        if (fragmentstate.mSavedFragmentState == null)
                        {
                            fragmentstate.mSavedFragmentState = new Bundle();
                        }
                        putFragment(fragmentstate.mSavedFragmentState, "android:target_state", fragment.mTarget);
                        if (fragment.mTargetRequestCode != 0)
                        {
                            fragmentstate.mSavedFragmentState.putInt("android:target_req_state", fragment.mTargetRequestCode);
                        }
                    }
                } else
                {
                    fragmentstate.mSavedFragmentState = fragment.mSavedFragmentState;
                }
                if (DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder("Saved state of ")).append(fragment).append(": ").append(fragmentstate.mSavedFragmentState).toString());
                }
                flag1 = true;
            } else
            {
                flag1 = flag;
            }
            j++;
            flag = flag1;
        }
        if (flag)
        {
            break; /* Loop/switch isn't completed */
        }
        if (DEBUG)
        {
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return null;
        }
        if (true) goto _L1; else goto _L3
_L3:
label0:
        {
            if (mAdded != null)
            {
                i1 = mAdded.size();
                if (i1 > 0)
                {
                    ai = new int[i1];
                    for (j1 = 0; j1 < i1; j1++)
                    {
                        ai[j1] = ((Fragment)mAdded.get(j1)).mIndex;
                        if (ai[j1] < 0)
                        {
                            throwException(new IllegalStateException((new StringBuilder("Failure saving state: active ")).append(mAdded.get(j1)).append(" has cleared index: ").append(ai[j1]).toString()));
                        }
                        if (DEBUG)
                        {
                            Log.v("FragmentManager", (new StringBuilder("saveAllState: adding fragment #")).append(j1).append(": ").append(mAdded.get(j1)).toString());
                        }
                    }

                    break label0;
                }
            }
            ai = null;
        }
        arraylist = mBackStack;
        abackstackstate = null;
        if (arraylist != null)
        {
            k = mBackStack.size();
            abackstackstate = null;
            if (k > 0)
            {
                abackstackstate = new BackStackState[k];
                for (l = 0; l < k; l++)
                {
                    abackstackstate[l] = new BackStackState(this, (BackStackRecord)mBackStack.get(l));
                    if (DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder("saveAllState: adding back stack #")).append(l).append(": ").append(mBackStack.get(l)).toString());
                    }
                }

            }
        }
        fragmentmanagerstate = new FragmentManagerState();
        fragmentmanagerstate.mActive = afragmentstate;
        fragmentmanagerstate.mAdded = ai;
        fragmentmanagerstate.mBackStack = abackstackstate;
        return fragmentmanagerstate;
    }

    final Bundle saveFragmentBasicState(Fragment fragment)
    {
        if (mStateBundle == null)
        {
            mStateBundle = new Bundle();
        }
        fragment.performSaveInstanceState(mStateBundle);
        Bundle bundle;
        if (!mStateBundle.isEmpty())
        {
            bundle = mStateBundle;
            mStateBundle = null;
        } else
        {
            bundle = null;
        }
        if (fragment.mView != null)
        {
            saveFragmentViewState(fragment);
        }
        if (fragment.mSavedViewState != null)
        {
            if (bundle == null)
            {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint)
        {
            if (bundle == null)
            {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    public final Fragment.SavedState saveFragmentInstanceState(Fragment fragment)
    {
        if (fragment.mIndex < 0)
        {
            throwException(new IllegalStateException((new StringBuilder("Fragment ")).append(fragment).append(" is not currently in the FragmentManager").toString()));
        }
        int i = fragment.mState;
        Fragment.SavedState savedstate = null;
        if (i > 0)
        {
            Bundle bundle = saveFragmentBasicState(fragment);
            savedstate = null;
            if (bundle != null)
            {
                savedstate = new Fragment.SavedState(bundle);
            }
        }
        return savedstate;
    }

    final void saveFragmentViewState(Fragment fragment)
    {
        if (fragment.mInnerView != null)
        {
            if (mStateArray == null)
            {
                mStateArray = new SparseArray();
            } else
            {
                mStateArray.clear();
            }
            fragment.mInnerView.saveHierarchyState(mStateArray);
            if (mStateArray.size() > 0)
            {
                fragment.mSavedViewState = mStateArray;
                mStateArray = null;
                return;
            }
        }
    }

    public final void setBackStackIndex(int i, BackStackRecord backstackrecord)
    {
        this;
        JVM INSTR monitorenter ;
        int j;
        if (mBackStackIndices == null)
        {
            mBackStackIndices = new ArrayList();
        }
        j = mBackStackIndices.size();
        if (i >= j) goto _L2; else goto _L1
_L1:
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("Setting back stack index ")).append(i).append(" to ").append(backstackrecord).toString());
        }
        mBackStackIndices.set(i, backstackrecord);
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        mBackStackIndices.add(null);
        if (mAvailBackStackIndices == null)
        {
            mAvailBackStackIndices = new ArrayList();
        }
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("Adding available back stack index ")).append(j).toString());
        }
        mAvailBackStackIndices.add(Integer.valueOf(j));
        j++;
        if (true) goto _L2; else goto _L3
_L3:
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("Adding back stack index ")).append(i).append(" with ").append(backstackrecord).toString());
        }
        mBackStackIndices.add(backstackrecord);
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    public final void showFragment(Fragment fragment, int i, int j)
    {
        if (DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("show: ")).append(fragment).toString());
        }
        if (fragment.mHidden)
        {
            fragment.mHidden = false;
            if (fragment.mView != null)
            {
                Animation animation = loadAnimation(fragment, i, true, j);
                if (animation != null)
                {
                    fragment.mView.startAnimation(animation);
                }
                fragment.mView.setVisibility(0);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible)
            {
                mNeedMenuInvalidate = true;
            }
            fragment.onHiddenChanged(false);
        }
    }

    final void startPendingDeferredFragments()
    {
        if (mActive != null)
        {
            int i = 0;
            while (i < mActive.size()) 
            {
                Fragment fragment = (Fragment)mActive.get(i);
                if (fragment != null)
                {
                    performPendingDeferredStart(fragment);
                }
                i++;
            }
        }
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("FragmentManager{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(" in ");
        if (mParent != null)
        {
            DebugUtils.buildShortClassTag(mParent, stringbuilder);
        } else
        {
            DebugUtils.buildShortClassTag(mActivity, stringbuilder);
        }
        stringbuilder.append("}}");
        return stringbuilder.toString();
    }

    static 
    {
        DEBUG = false;
        int i = android.os.Build.VERSION.SDK_INT;
        boolean flag = false;
        if (i >= 11)
        {
            flag = true;
        }
        HONEYCOMB = flag;
    }

    private class _cls1
        implements Runnable
    {

        final FragmentManagerImpl this$0;

        public void run()
        {
            execPendingActions();
        }

        _cls1()
        {
            this$0 = FragmentManagerImpl.this;
            super();
        }
    }


    private class _cls5
        implements android.view.animation.Animation.AnimationListener
    {

        final FragmentManagerImpl this$0;
        final Fragment val$fragment;

        public void onAnimationEnd(Animation animation)
        {
            if (fragment.mAnimatingAway != null)
            {
                fragment.mAnimatingAway = null;
                moveToState(fragment, fragment.mStateAfterAnimating, 0, 0, false);
            }
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        _cls5()
        {
            this$0 = FragmentManagerImpl.this;
            fragment = fragment1;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        final FragmentManagerImpl this$0;

        public void run()
        {
            popBackStackState(mActivity.mHandler, null, -1, 0);
        }

        _cls2()
        {
            this$0 = FragmentManagerImpl.this;
            super();
        }
    }


    private class _cls4
        implements Runnable
    {

        final FragmentManagerImpl this$0;
        final int val$flags;
        final int val$id;

        public void run()
        {
            popBackStackState(mActivity.mHandler, null, id, flags);
        }

        _cls4()
        {
            this$0 = FragmentManagerImpl.this;
            id = i;
            flags = j;
            super();
        }
    }


    private class _cls3
        implements Runnable
    {

        final FragmentManagerImpl this$0;
        final int val$flags;
        final String val$name;

        public void run()
        {
            popBackStackState(mActivity.mHandler, name, -1, flags);
        }

        _cls3()
        {
            this$0 = FragmentManagerImpl.this;
            name = s;
            flags = i;
            super();
        }
    }

}
