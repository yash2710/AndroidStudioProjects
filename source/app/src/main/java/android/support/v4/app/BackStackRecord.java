// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.support.v4.util.LogWriter;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransaction, Fragment, FragmentManagerImpl, FragmentActivity

final class BackStackRecord extends FragmentTransaction
    implements FragmentManager.BackStackEntry, Runnable
{

    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SHOW = 5;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    Op mHead;
    int mIndex;
    final FragmentManagerImpl mManager;
    String mName;
    int mNumOp;
    int mPopEnterAnim;
    int mPopExitAnim;
    Op mTail;
    int mTransition;
    int mTransitionStyle;

    public BackStackRecord(FragmentManagerImpl fragmentmanagerimpl)
    {
        mAllowAddToBackStack = true;
        mIndex = -1;
        mManager = fragmentmanagerimpl;
    }

    private void doAddOp(int i, Fragment fragment, String s, int j)
    {
        fragment.mFragmentManager = mManager;
        if (s != null)
        {
            if (fragment.mTag != null && !s.equals(fragment.mTag))
            {
                throw new IllegalStateException((new StringBuilder("Can't change tag of fragment ")).append(fragment).append(": was ").append(fragment.mTag).append(" now ").append(s).toString());
            }
            fragment.mTag = s;
        }
        if (i != 0)
        {
            if (fragment.mFragmentId != 0 && fragment.mFragmentId != i)
            {
                throw new IllegalStateException((new StringBuilder("Can't change container ID of fragment ")).append(fragment).append(": was ").append(fragment.mFragmentId).append(" now ").append(i).toString());
            }
            fragment.mFragmentId = i;
            fragment.mContainerId = i;
        }
        Op op = new Op();
        op.cmd = j;
        op.fragment = fragment;
        addOp(op);
    }

    public final FragmentTransaction add(int i, Fragment fragment)
    {
        doAddOp(i, fragment, null, 1);
        return this;
    }

    public final FragmentTransaction add(int i, Fragment fragment, String s)
    {
        doAddOp(i, fragment, s, 1);
        return this;
    }

    public final FragmentTransaction add(Fragment fragment, String s)
    {
        doAddOp(0, fragment, s, 1);
        return this;
    }

    final void addOp(Op op)
    {
        if (mHead == null)
        {
            mTail = op;
            mHead = op;
        } else
        {
            op.prev = mTail;
            mTail.next = op;
            mTail = op;
        }
        op.enterAnim = mEnterAnim;
        op.exitAnim = mExitAnim;
        op.popEnterAnim = mPopEnterAnim;
        op.popExitAnim = mPopExitAnim;
        mNumOp = 1 + mNumOp;
    }

    public final FragmentTransaction addToBackStack(String s)
    {
        if (!mAllowAddToBackStack)
        {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        } else
        {
            mAddToBackStack = true;
            mName = s;
            return this;
        }
    }

    public final FragmentTransaction attach(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 7;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    final void bumpBackStackNesting(int i)
    {
        if (mAddToBackStack)
        {
            if (FragmentManagerImpl.DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder("Bump nesting in ")).append(this).append(" by ").append(i).toString());
            }
            Op op = mHead;
            while (op != null) 
            {
                if (op.fragment != null)
                {
                    Fragment fragment1 = op.fragment;
                    fragment1.mBackStackNesting = i + fragment1.mBackStackNesting;
                    if (FragmentManagerImpl.DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder("Bump nesting of ")).append(op.fragment).append(" to ").append(op.fragment.mBackStackNesting).toString());
                    }
                }
                if (op.removed != null)
                {
                    for (int j = -1 + op.removed.size(); j >= 0; j--)
                    {
                        Fragment fragment = (Fragment)op.removed.get(j);
                        fragment.mBackStackNesting = i + fragment.mBackStackNesting;
                        if (FragmentManagerImpl.DEBUG)
                        {
                            Log.v("FragmentManager", (new StringBuilder("Bump nesting of ")).append(fragment).append(" to ").append(fragment.mBackStackNesting).toString());
                        }
                    }

                }
                op = op.next;
            }
        }
    }

    public final int commit()
    {
        return commitInternal(false);
    }

    public final int commitAllowingStateLoss()
    {
        return commitInternal(true);
    }

    final int commitInternal(boolean flag)
    {
        if (mCommitted)
        {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManagerImpl.DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("Commit: ")).append(this).toString());
            dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        mCommitted = true;
        if (mAddToBackStack)
        {
            mIndex = mManager.allocBackStackIndex(this);
        } else
        {
            mIndex = -1;
        }
        mManager.enqueueAction(this, flag);
        return mIndex;
    }

    public final FragmentTransaction detach(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 6;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    public final FragmentTransaction disallowAddToBackStack()
    {
        if (mAddToBackStack)
        {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        } else
        {
            mAllowAddToBackStack = false;
            return this;
        }
    }

    public final void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        dump(s, printwriter, true);
    }

    public final void dump(String s, PrintWriter printwriter, boolean flag)
    {
        String s1;
        int i;
        Op op1;
        if (flag)
        {
            printwriter.print(s);
            printwriter.print("mName=");
            printwriter.print(mName);
            printwriter.print(" mIndex=");
            printwriter.print(mIndex);
            printwriter.print(" mCommitted=");
            printwriter.println(mCommitted);
            if (mTransition != 0)
            {
                printwriter.print(s);
                printwriter.print("mTransition=#");
                printwriter.print(Integer.toHexString(mTransition));
                printwriter.print(" mTransitionStyle=#");
                printwriter.println(Integer.toHexString(mTransitionStyle));
            }
            if (mEnterAnim != 0 || mExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("mEnterAnim=#");
                printwriter.print(Integer.toHexString(mEnterAnim));
                printwriter.print(" mExitAnim=#");
                printwriter.println(Integer.toHexString(mExitAnim));
            }
            if (mPopEnterAnim != 0 || mPopExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("mPopEnterAnim=#");
                printwriter.print(Integer.toHexString(mPopEnterAnim));
                printwriter.print(" mPopExitAnim=#");
                printwriter.println(Integer.toHexString(mPopExitAnim));
            }
            if (mBreadCrumbTitleRes != 0 || mBreadCrumbTitleText != null)
            {
                printwriter.print(s);
                printwriter.print("mBreadCrumbTitleRes=#");
                printwriter.print(Integer.toHexString(mBreadCrumbTitleRes));
                printwriter.print(" mBreadCrumbTitleText=");
                printwriter.println(mBreadCrumbTitleText);
            }
            if (mBreadCrumbShortTitleRes != 0 || mBreadCrumbShortTitleText != null)
            {
                printwriter.print(s);
                printwriter.print("mBreadCrumbShortTitleRes=#");
                printwriter.print(Integer.toHexString(mBreadCrumbShortTitleRes));
                printwriter.print(" mBreadCrumbShortTitleText=");
                printwriter.println(mBreadCrumbShortTitleText);
            }
        }
        if (mHead == null)
        {
            break MISSING_BLOCK_LABEL_818;
        }
        printwriter.print(s);
        printwriter.println("Operations:");
        s1 = (new StringBuilder()).append(s).append("    ").toString();
        Op op = mHead;
        i = 0;
        op1 = op;
_L13:
        if (op1 == null) goto _L2; else goto _L1
_L1:
        op1.cmd;
        JVM INSTR tableswitch 0 7: default 428
    //                   0 700
    //                   1 708
    //                   2 716
    //                   3 724
    //                   4 732
    //                   5 740
    //                   6 748
    //                   7 756;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L11:
        break MISSING_BLOCK_LABEL_756;
_L3:
        String s2 = (new StringBuilder("cmd=")).append(op1.cmd).toString();
_L12:
        printwriter.print(s);
        printwriter.print("  Op #");
        printwriter.print(i);
        printwriter.print(": ");
        printwriter.print(s2);
        printwriter.print(" ");
        printwriter.println(op1.fragment);
        if (flag)
        {
            if (op1.enterAnim != 0 || op1.exitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("enterAnim=#");
                printwriter.print(Integer.toHexString(op1.enterAnim));
                printwriter.print(" exitAnim=#");
                printwriter.println(Integer.toHexString(op1.exitAnim));
            }
            if (op1.popEnterAnim != 0 || op1.popExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("popEnterAnim=#");
                printwriter.print(Integer.toHexString(op1.popEnterAnim));
                printwriter.print(" popExitAnim=#");
                printwriter.println(Integer.toHexString(op1.popExitAnim));
            }
        }
        if (op1.removed != null && op1.removed.size() > 0)
        {
            int j = 0;
            while (j < op1.removed.size()) 
            {
                printwriter.print(s1);
                if (op1.removed.size() == 1)
                {
                    printwriter.print("Removed: ");
                } else
                {
                    if (j == 0)
                    {
                        printwriter.println("Removed:");
                    }
                    printwriter.print(s1);
                    printwriter.print("  #");
                    printwriter.print(j);
                    printwriter.print(": ");
                }
                printwriter.println(op1.removed.get(j));
                j++;
            }
        }
        break MISSING_BLOCK_LABEL_805;
_L4:
        s2 = "NULL";
          goto _L12
_L5:
        s2 = "ADD";
          goto _L12
_L6:
        s2 = "REPLACE";
          goto _L12
_L7:
        s2 = "REMOVE";
          goto _L12
_L8:
        s2 = "HIDE";
          goto _L12
_L9:
        s2 = "SHOW";
          goto _L12
_L10:
        s2 = "DETACH";
          goto _L12
        s2 = "ATTACH";
          goto _L12
        op1 = op1.next;
        i++;
          goto _L13
_L2:
    }

    public final CharSequence getBreadCrumbShortTitle()
    {
        if (mBreadCrumbShortTitleRes != 0)
        {
            return mManager.mActivity.getText(mBreadCrumbShortTitleRes);
        } else
        {
            return mBreadCrumbShortTitleText;
        }
    }

    public final int getBreadCrumbShortTitleRes()
    {
        return mBreadCrumbShortTitleRes;
    }

    public final CharSequence getBreadCrumbTitle()
    {
        if (mBreadCrumbTitleRes != 0)
        {
            return mManager.mActivity.getText(mBreadCrumbTitleRes);
        } else
        {
            return mBreadCrumbTitleText;
        }
    }

    public final int getBreadCrumbTitleRes()
    {
        return mBreadCrumbTitleRes;
    }

    public final int getId()
    {
        return mIndex;
    }

    public final String getName()
    {
        return mName;
    }

    public final int getTransition()
    {
        return mTransition;
    }

    public final int getTransitionStyle()
    {
        return mTransitionStyle;
    }

    public final FragmentTransaction hide(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 4;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    public final boolean isAddToBackStackAllowed()
    {
        return mAllowAddToBackStack;
    }

    public final boolean isEmpty()
    {
        return mNumOp == 0;
    }

    public final void popFromBackStack(boolean flag)
    {
        Op op;
        if (FragmentManagerImpl.DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("popFromBackStack: ")).append(this).toString());
            dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        bumpBackStackNesting(-1);
        op = mTail;
_L10:
        if (op == null)
        {
            break MISSING_BLOCK_LABEL_461;
        }
        op.cmd;
        JVM INSTR tableswitch 1 7: default 112
    //                   1 140
    //                   2 183
    //                   3 284
    //                   4 312
    //                   5 350
    //                   6 388
    //                   7 426;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_426;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder("Unknown cmd: ")).append(op.cmd).toString());
_L2:
        Fragment fragment7 = op.fragment;
        fragment7.mNextAnim = op.popExitAnim;
        mManager.removeFragment(fragment7, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
_L11:
        op = op.prev;
        if (true) goto _L10; else goto _L9
_L9:
        Fragment fragment5 = op.fragment;
        if (fragment5 != null)
        {
            fragment5.mNextAnim = op.popExitAnim;
            mManager.removeFragment(fragment5, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
        }
        if (op.removed != null)
        {
            int i = 0;
            while (i < op.removed.size()) 
            {
                Fragment fragment6 = (Fragment)op.removed.get(i);
                fragment6.mNextAnim = op.popEnterAnim;
                mManager.addFragment(fragment6, false);
                i++;
            }
        }
          goto _L11
_L4:
        Fragment fragment4 = op.fragment;
        fragment4.mNextAnim = op.popEnterAnim;
        mManager.addFragment(fragment4, false);
          goto _L11
_L5:
        Fragment fragment3 = op.fragment;
        fragment3.mNextAnim = op.popEnterAnim;
        mManager.showFragment(fragment3, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
          goto _L11
_L6:
        Fragment fragment2 = op.fragment;
        fragment2.mNextAnim = op.popExitAnim;
        mManager.hideFragment(fragment2, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
          goto _L11
_L7:
        Fragment fragment1 = op.fragment;
        fragment1.mNextAnim = op.popEnterAnim;
        mManager.attachFragment(fragment1, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
          goto _L11
        Fragment fragment = op.fragment;
        fragment.mNextAnim = op.popEnterAnim;
        mManager.detachFragment(fragment, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
          goto _L11
        if (flag)
        {
            mManager.moveToState(mManager.mCurState, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle, true);
        }
        if (mIndex >= 0)
        {
            mManager.freeBackStackIndex(mIndex);
            mIndex = -1;
        }
        return;
    }

    public final FragmentTransaction remove(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 3;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    public final FragmentTransaction replace(int i, Fragment fragment)
    {
        return replace(i, fragment, null);
    }

    public final FragmentTransaction replace(int i, Fragment fragment, String s)
    {
        if (i == 0)
        {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        } else
        {
            doAddOp(i, fragment, s, 2);
            return this;
        }
    }

    public final void run()
    {
        Op op;
        if (FragmentManagerImpl.DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder("Run: ")).append(this).toString());
        }
        if (mAddToBackStack && mIndex < 0)
        {
            throw new IllegalStateException("addToBackStack() called after commit()");
        }
        bumpBackStackNesting(1);
        op = mHead;
_L10:
        if (op == null)
        {
            break MISSING_BLOCK_LABEL_630;
        }
        op.cmd;
        JVM INSTR tableswitch 1 7: default 116
    //                   1 144
    //                   2 177
    //                   3 461
    //                   4 496
    //                   5 531
    //                   6 566
    //                   7 598;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_598;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder("Unknown cmd: ")).append(op.cmd).toString());
_L2:
        Fragment fragment8 = op.fragment;
        fragment8.mNextAnim = op.enterAnim;
        mManager.addFragment(fragment8, false);
_L11:
        op = op.next;
        if (true) goto _L10; else goto _L9
_L9:
        Fragment fragment5 = op.fragment;
        Fragment fragment6;
        if (mManager.mAdded != null)
        {
            int i = 0;
            fragment6 = fragment5;
            while (i < mManager.mAdded.size()) 
            {
                Fragment fragment7 = (Fragment)mManager.mAdded.get(i);
                if (FragmentManagerImpl.DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder("OP_REPLACE: adding=")).append(fragment6).append(" old=").append(fragment7).toString());
                }
                if (fragment6 == null || fragment7.mContainerId == fragment6.mContainerId)
                {
                    if (fragment7 == fragment6)
                    {
                        fragment6 = null;
                        op.fragment = null;
                    } else
                    {
                        if (op.removed == null)
                        {
                            op.removed = new ArrayList();
                        }
                        op.removed.add(fragment7);
                        fragment7.mNextAnim = op.exitAnim;
                        if (mAddToBackStack)
                        {
                            fragment7.mBackStackNesting = 1 + fragment7.mBackStackNesting;
                            if (FragmentManagerImpl.DEBUG)
                            {
                                Log.v("FragmentManager", (new StringBuilder("Bump nesting of ")).append(fragment7).append(" to ").append(fragment7.mBackStackNesting).toString());
                            }
                        }
                        mManager.removeFragment(fragment7, mTransition, mTransitionStyle);
                    }
                }
                i++;
            }
        } else
        {
            fragment6 = fragment5;
        }
        if (fragment6 != null)
        {
            fragment6.mNextAnim = op.enterAnim;
            mManager.addFragment(fragment6, false);
        }
          goto _L11
_L4:
        Fragment fragment4 = op.fragment;
        fragment4.mNextAnim = op.exitAnim;
        mManager.removeFragment(fragment4, mTransition, mTransitionStyle);
          goto _L11
_L5:
        Fragment fragment3 = op.fragment;
        fragment3.mNextAnim = op.exitAnim;
        mManager.hideFragment(fragment3, mTransition, mTransitionStyle);
          goto _L11
_L6:
        Fragment fragment2 = op.fragment;
        fragment2.mNextAnim = op.enterAnim;
        mManager.showFragment(fragment2, mTransition, mTransitionStyle);
          goto _L11
_L7:
        Fragment fragment1 = op.fragment;
        fragment1.mNextAnim = op.exitAnim;
        mManager.detachFragment(fragment1, mTransition, mTransitionStyle);
          goto _L11
        Fragment fragment = op.fragment;
        fragment.mNextAnim = op.enterAnim;
        mManager.attachFragment(fragment, mTransition, mTransitionStyle);
          goto _L11
        mManager.moveToState(mManager.mCurState, mTransition, mTransitionStyle, true);
        if (mAddToBackStack)
        {
            mManager.addBackStackState(this);
        }
        return;
    }

    public final FragmentTransaction setBreadCrumbShortTitle(int i)
    {
        mBreadCrumbShortTitleRes = i;
        mBreadCrumbShortTitleText = null;
        return this;
    }

    public final FragmentTransaction setBreadCrumbShortTitle(CharSequence charsequence)
    {
        mBreadCrumbShortTitleRes = 0;
        mBreadCrumbShortTitleText = charsequence;
        return this;
    }

    public final FragmentTransaction setBreadCrumbTitle(int i)
    {
        mBreadCrumbTitleRes = i;
        mBreadCrumbTitleText = null;
        return this;
    }

    public final FragmentTransaction setBreadCrumbTitle(CharSequence charsequence)
    {
        mBreadCrumbTitleRes = 0;
        mBreadCrumbTitleText = charsequence;
        return this;
    }

    public final FragmentTransaction setCustomAnimations(int i, int j)
    {
        return setCustomAnimations(i, j, 0, 0);
    }

    public final FragmentTransaction setCustomAnimations(int i, int j, int k, int l)
    {
        mEnterAnim = i;
        mExitAnim = j;
        mPopEnterAnim = k;
        mPopExitAnim = l;
        return this;
    }

    public final FragmentTransaction setTransition(int i)
    {
        mTransition = i;
        return this;
    }

    public final FragmentTransaction setTransitionStyle(int i)
    {
        mTransitionStyle = i;
        return this;
    }

    public final FragmentTransaction show(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 5;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("BackStackEntry{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if (mIndex >= 0)
        {
            stringbuilder.append(" #");
            stringbuilder.append(mIndex);
        }
        if (mName != null)
        {
            stringbuilder.append(" ");
            stringbuilder.append(mName);
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private class Op
    {

        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        Op next;
        int popEnterAnim;
        int popExitAnim;
        Op prev;
        ArrayList removed;

        Op()
        {
        }
    }

}
