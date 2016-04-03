// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

// Referenced classes of package com.flipkart.android.customviews:
//            l, n, g, h, 
//            i, j, m, k

public class EnhancedListView extends ListView
{

    private PopupWindow A;
    private int B;
    private Handler C;
    private Button D;
    private float a;
    private int b;
    private int c;
    private long d;
    private final Object e[];
    private boolean f;
    private OnDismissCallback g;
    private OnShouldSwipeCallback h;
    private UndoStyle i;
    private boolean j;
    private SwipeDirection k;
    private int l;
    private int m;
    public boolean mSwipePaused;
    private List n;
    private SortedSet o;
    private List p;
    private int q;
    private boolean r;
    private int s;
    private View t;
    private View u;
    private TextView v;
    private VelocityTracker w;
    private float x;
    private int y;
    private float z;

    public EnhancedListView(Context context)
    {
        super(context);
        e = new Object[0];
        f = true;
        i = UndoStyle.SINGLE_POPUP;
        j = true;
        k = SwipeDirection.END;
        l = 1;
        n = new ArrayList();
        o = new TreeSet();
        p = new LinkedList();
        s = 1;
        C = new l(this, (byte)0);
        a(context);
    }

    public EnhancedListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        e = new Object[0];
        f = true;
        i = UndoStyle.SINGLE_POPUP;
        j = true;
        k = SwipeDirection.END;
        l = 1;
        n = new ArrayList();
        o = new TreeSet();
        p = new LinkedList();
        s = 1;
        C = new l(this, (byte)0);
        a(context);
    }

    public EnhancedListView(Context context, AttributeSet attributeset, int i1)
    {
        super(context, attributeset, i1);
        e = new Object[0];
        f = true;
        i = UndoStyle.SINGLE_POPUP;
        j = true;
        k = SwipeDirection.END;
        l = 1;
        n = new ArrayList();
        o = new TreeSet();
        p = new LinkedList();
        s = 1;
        C = new l(this, (byte)0);
        a(context);
    }

    static List a(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.n;
    }

    private void a(Context context)
    {
        if (isInEditMode())
        {
            return;
        } else
        {
            ViewConfiguration viewconfiguration = ViewConfiguration.get(context);
            a = getResources().getDimension(0x7f0b0033);
            b = viewconfiguration.getScaledMinimumFlingVelocity();
            c = viewconfiguration.getScaledMaximumFlingVelocity();
            d = context.getResources().getInteger(0x10e0000);
            View view = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03003f, null);
            D = (Button)view.findViewById(0x7f0a00d4);
            D.setOnClickListener(new n(this, (byte)0));
            D.setOnTouchListener(new g(this));
            v = (TextView)view.findViewById(0x7f0a00b6);
            A = new PopupWindow(view, -2, -2, false);
            A.setAnimationStyle(0x7f0e00a0);
            z = getResources().getDisplayMetrics().density;
            return;
        }
    }

    private void a(View view, View view1, int i1, boolean flag)
    {
label0:
        {
            synchronized (e)
            {
                if (!p.contains(view))
                {
                    break label0;
                }
            }
            return;
        }
        q = 1 + q;
        p.add(view);
        aobj;
        JVM INSTR monitorexit ;
        ViewPropertyAnimator viewpropertyanimator = ViewPropertyAnimator.animate(view);
        float f1;
        if (flag)
        {
            f1 = s;
        } else
        {
            f1 = -s;
        }
        viewpropertyanimator.translationX(f1).alpha(0.0F).setDuration(d).setListener(new h(this, view, view1, i1));
        return;
        exception;
        throw exception;
    }

    static void a(EnhancedListView enhancedlistview, View view, View view1, int i1)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view1.getLayoutParams();
        int j1 = layoutparams.height;
        ValueAnimator valueanimator = ValueAnimator.ofInt(new int[] {
            view1.getHeight(), 1
        }).setDuration(enhancedlistview.d);
        valueanimator.addListener(new i(enhancedlistview, view, j1));
        valueanimator.addUpdateListener(new j(enhancedlistview, layoutparams, view1));
        enhancedlistview.o.add(new m(enhancedlistview, i1, view, view1));
        valueanimator.start();
    }

    private boolean a(float f1)
    {
        int i1;
        if (android.os.Build.VERSION.SDK_INT >= 17 && getLayoutDirection() == 1)
        {
            i1 = -1;
        } else
        {
            i1 = 1;
        }
        k.b[k.ordinal()];
        JVM INSTR tableswitch 2 3: default 52
    //                   2 54
    //                   3 65;
           goto _L1 _L2 _L3
_L1:
        return true;
_L2:
        if (f1 * (float)i1 >= 0.0F)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (f1 * (float)i1 <= 0.0F)
        {
            return false;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    static UndoStyle b(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.i;
    }

    static PopupWindow c(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.A;
    }

    static void d(EnhancedListView enhancedlistview)
    {
        if (enhancedlistview.n.size() <= 1) goto _L2; else goto _L1
_L1:
        String s1;
        Resources resources = enhancedlistview.getResources();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(enhancedlistview.n.size());
        s1 = resources.getString(0x7f0d0066, aobj);
_L4:
        enhancedlistview.v.setText(s1);
        return;
_L2:
        int i1 = enhancedlistview.n.size();
        s1 = null;
        if (i1 > 0)
        {
            s1 = ((Undoable)enhancedlistview.n.get(-1 + enhancedlistview.n.size())).getTitle();
            if (s1 == null)
            {
                s1 = enhancedlistview.getResources().getString(0x7f0d0065);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    static void e(EnhancedListView enhancedlistview)
    {
        String s1;
        if (enhancedlistview.n.size() > 1 && enhancedlistview.i == UndoStyle.COLLAPSED_POPUP)
        {
            s1 = enhancedlistview.getResources().getString(0x7f0d0068);
        } else
        {
            s1 = enhancedlistview.getResources().getString(0x7f0d0067);
        }
        enhancedlistview.D.setText(s1);
    }

    static int f(EnhancedListView enhancedlistview)
    {
        int i1 = enhancedlistview.B;
        enhancedlistview.B = i1 + 1;
        return i1;
    }

    static int g(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.B;
    }

    static Object[] h(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.e;
    }

    static int i(EnhancedListView enhancedlistview)
    {
        int i1 = -1 + enhancedlistview.q;
        enhancedlistview.q = i1;
        return i1;
    }

    static List j(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.p;
    }

    static int k(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.q;
    }

    static SortedSet l(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.o;
    }

    static OnDismissCallback m(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.g;
    }

    static float n(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.z;
    }

    static boolean o(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.j;
    }

    static Handler p(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.C;
    }

    static int q(EnhancedListView enhancedlistview)
    {
        return enhancedlistview.l;
    }

    public void delete(int i1)
    {
        if (g == null)
        {
            throw new IllegalStateException("You must set an OnDismissCallback, before deleting items.");
        }
        if (i1 < 0 || i1 >= getCount())
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(i1);
            aobj[1] = Integer.valueOf(getCount());
            throw new IndexOutOfBoundsException(String.format("Tried to delete item %d. #items in list: %d", aobj));
        }
        View view = getChildAt(i1 - getFirstVisiblePosition());
        int j1 = m;
        View view1 = null;
        if (j1 > 0)
        {
            view1 = view.findViewById(m);
        }
        if (view1 == null)
        {
            view1 = view;
        }
        a(view1, view, i1, true);
    }

    public EnhancedListView disableSwipeToDismiss()
    {
        f = false;
        return this;
    }

    public void discardUndo()
    {
        for (Iterator iterator = n.iterator(); iterator.hasNext(); ((Undoable)iterator.next()).discard()) { }
        n.clear();
        if (A.isShowing())
        {
            A.dismiss();
        }
    }

    public EnhancedListView enableSwipeToDismiss()
    {
        if (g == null)
        {
            throw new IllegalStateException("You must pass an OnDismissCallback to the list before enabling Swipe to Dismiss.");
        } else
        {
            f = true;
            return this;
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        flag = true;
        if (!f)
        {
            return super.onTouchEvent(motionevent);
        }
        if (j && A.isShowing())
        {
            C.sendMessageDelayed(C.obtainMessage(B), l);
        }
        if (s < 2)
        {
            s = getWidth();
        }
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 2: default 104
    //                   0 110
    //                   1 368
    //                   2 649;
           goto _L1 _L2 _L3 _L4
_L1:
        return super.onTouchEvent(motionevent);
_L2:
        Rect rect;
        int i1;
        int j1;
        int k1;
        int l1;
        if (mSwipePaused)
        {
            return super.onTouchEvent(motionevent);
        }
        rect = new Rect();
        i1 = getChildCount();
        int ai[] = new int[2];
        getLocationOnScreen(ai);
        j1 = (int)motionevent.getRawX() - ai[0];
        k1 = (int)motionevent.getRawY() - ai[flag];
        l1 = getHeaderViewsCount();
_L13:
        if (l1 >= i1) goto _L6; else goto _L5
_L5:
        View view = getChildAt(l1);
        if (view == null) goto _L8; else goto _L7
_L7:
        view.getHitRect(rect);
        if (!rect.contains(j1, k1)) goto _L8; else goto _L9
_L9:
        if (m <= 0) goto _L11; else goto _L10
_L10:
        View view1 = view.findViewById(m);
        if (view1 == null) goto _L11; else goto _L12
_L12:
        t = view1;
        u = view;
_L6:
        if (t != null)
        {
            int i2 = getPositionForView(t) - getHeaderViewsCount();
            if (h == null || h.onShouldSwipe(this, i2))
            {
                x = motionevent.getRawX();
                y = i2;
                w = VelocityTracker.obtain();
                w.addMovement(motionevent);
            } else
            {
                u = null;
                t = null;
            }
        }
        super.onTouchEvent(motionevent);
        return flag;
_L11:
        u = view;
        t = view;
          goto _L6
_L8:
        l1++;
          goto _L13
_L3:
        if (w == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        float f2 = motionevent.getRawX() - x;
        w.addMovement(motionevent);
        w.computeCurrentVelocity(1000);
        float f3 = Math.abs(w.getXVelocity());
        float f4 = Math.abs(w.getYVelocity());
        float f1;
        ViewParent viewparent;
        MotionEvent motionevent1;
        boolean flag1;
        if (Math.abs(f2) > (float)(s / 2) && r)
        {
            if (f2 > 0.0F)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
        } else
        if ((float)b <= f3 && f3 <= (float)c && f4 < f3 && r && a(w.getXVelocity()) && f2 >= 0.2F * (float)s)
        {
            if (w.getXVelocity() > 0.0F)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
        } else
        {
            flag1 = false;
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        a(t, u, y, flag1);
        w = null;
        x = 0.0F;
        t = null;
        u = null;
        y = -1;
        r = false;
        if (true) goto _L1; else goto _L14
_L14:
        if (!r)
        {
            break MISSING_BLOCK_LABEL_483;
        }
        ViewPropertyAnimator.animate(t).translationX(0.0F).alpha(1.0F).setDuration(d).setListener(null);
        break MISSING_BLOCK_LABEL_483;
_L4:
        if (w != null && !mSwipePaused)
        {
            w.addMovement(motionevent);
            f1 = motionevent.getRawX() - x;
            if (a(f1))
            {
                viewparent = getParent();
                if (viewparent != null)
                {
                    viewparent.requestDisallowInterceptTouchEvent(flag);
                }
                if (Math.abs(f1) > a)
                {
                    r = flag;
                    requestDisallowInterceptTouchEvent(flag);
                    motionevent1 = MotionEvent.obtain(motionevent);
                    motionevent1.setAction(3 | motionevent.getActionIndex() << 8);
                    super.onTouchEvent(motionevent1);
                }
            } else
            {
                x = motionevent.getRawX();
                f1 = 0.0F;
            }
            if (r)
            {
                ViewHelper.setTranslationX(t, f1);
                ViewHelper.setAlpha(t, Math.max(0.0F, Math.min(1.0F, 1.0F - (2.0F * Math.abs(f1)) / (float)s)));
                return flag;
            }
        }
        if (true) goto _L1; else goto _L15
_L15:
    }

    protected void onWindowVisibilityChanged(int i1)
    {
        super.onWindowVisibilityChanged(i1);
        if (i1 != 0)
        {
            discardUndo();
        }
    }

    public EnhancedListView setDismissCallback(OnDismissCallback ondismisscallback)
    {
        g = ondismisscallback;
        return this;
    }

    public EnhancedListView setRequireTouchBeforeDismiss(boolean flag)
    {
        j = flag;
        return this;
    }

    public EnhancedListView setShouldSwipeCallback(OnShouldSwipeCallback onshouldswipecallback)
    {
        h = onshouldswipecallback;
        return this;
    }

    public EnhancedListView setSwipeDirection(SwipeDirection swipedirection)
    {
        k = swipedirection;
        return this;
    }

    public EnhancedListView setSwipingLayout(int i1)
    {
        m = i1;
        return this;
    }

    public EnhancedListView setUndoHideDelay(int i1)
    {
        l = i1;
        return this;
    }

    public EnhancedListView setUndoStyle(UndoStyle undostyle)
    {
        i = undostyle;
        return this;
    }

    private class UndoStyle extends Enum
    {

        public static final UndoStyle COLLAPSED_POPUP;
        public static final UndoStyle MULTILEVEL_POPUP;
        public static final UndoStyle SINGLE_POPUP;
        private static final UndoStyle a[];

        public static UndoStyle valueOf(String s1)
        {
            return (UndoStyle)Enum.valueOf(com/flipkart/android/customviews/EnhancedListView$UndoStyle, s1);
        }

        public static UndoStyle[] values()
        {
            return (UndoStyle[])a.clone();
        }

        static 
        {
            SINGLE_POPUP = new UndoStyle("SINGLE_POPUP", 0);
            MULTILEVEL_POPUP = new UndoStyle("MULTILEVEL_POPUP", 1);
            COLLAPSED_POPUP = new UndoStyle("COLLAPSED_POPUP", 2);
            UndoStyle aundostyle[] = new UndoStyle[3];
            aundostyle[0] = SINGLE_POPUP;
            aundostyle[1] = MULTILEVEL_POPUP;
            aundostyle[2] = COLLAPSED_POPUP;
            a = aundostyle;
        }

        private UndoStyle(String s1, int i1)
        {
            super(s1, i1);
        }
    }


    private class SwipeDirection extends Enum
    {

        public static final SwipeDirection BOTH;
        public static final SwipeDirection END;
        public static final SwipeDirection START;
        private static final SwipeDirection a[];

        public static SwipeDirection valueOf(String s1)
        {
            return (SwipeDirection)Enum.valueOf(com/flipkart/android/customviews/EnhancedListView$SwipeDirection, s1);
        }

        public static SwipeDirection[] values()
        {
            return (SwipeDirection[])a.clone();
        }

        static 
        {
            BOTH = new SwipeDirection("BOTH", 0);
            START = new SwipeDirection("START", 1);
            END = new SwipeDirection("END", 2);
            SwipeDirection aswipedirection[] = new SwipeDirection[3];
            aswipedirection[0] = BOTH;
            aswipedirection[1] = START;
            aswipedirection[2] = END;
            a = aswipedirection;
        }

        private SwipeDirection(String s1, int i1)
        {
            super(s1, i1);
        }
    }


    private class Undoable
    {

        public void discard()
        {
        }

        public String getTitle()
        {
            return null;
        }

        public abstract void undo();

        public Undoable()
        {
        }
    }


    private class OnShouldSwipeCallback
    {

        public abstract boolean onShouldSwipe(EnhancedListView enhancedlistview, int i1);
    }

}
