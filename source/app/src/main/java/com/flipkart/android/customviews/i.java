// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.res.Resources;
import android.os.Handler;
import android.view.View;
import android.widget.PopupWindow;
import com.flipkart.android.utils.ScreenMathUtils;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.view.ViewHelper;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

// Referenced classes of package com.flipkart.android.customviews:
//            EnhancedListView, m

final class i extends AnimatorListenerAdapter
{

    private View a;
    private int b;
    private EnhancedListView c;

    i(EnhancedListView enhancedlistview, View view, int j)
    {
        c = enhancedlistview;
        a = view;
        b = j;
        super();
    }

    public final void onAnimationEnd(Animator animator)
    {
        Object aobj[] = EnhancedListView.h(c);
        aobj;
        JVM INSTR monitorenter ;
        EnhancedListView.i(c);
        EnhancedListView.j(c).remove(a);
        boolean flag;
        Iterator iterator;
        m m2;
        if (EnhancedListView.k(c) == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        aobj;
        JVM INSTR monitorexit ;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_485;
        }
        iterator = EnhancedListView.l(c).iterator();
_L1:
        if (iterator.hasNext())
        {
            m2 = (m)iterator.next();
            if (EnhancedListView.b(c) != EnhancedListView.UndoStyle.SINGLE_POPUP)
            {
                break MISSING_BLOCK_LABEL_167;
            }
            for (Iterator iterator2 = EnhancedListView.a(c).iterator(); iterator2.hasNext(); ((EnhancedListView.Undoable)iterator2.next()).discard()) { }
            break MISSING_BLOCK_LABEL_155;
        }
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
        EnhancedListView.a(c).clear();
        EnhancedListView.Undoable undoable = EnhancedListView.m(c).onDismiss(c, m2.a);
        if (undoable != null)
        {
            EnhancedListView.a(c).add(undoable);
        }
        EnhancedListView.f(c);
          goto _L1
        if (!EnhancedListView.a(c).isEmpty())
        {
            EnhancedListView.d(c);
            EnhancedListView.e(c);
            float f = c.getResources().getDimension(0x7f0b0034);
            EnhancedListView.c(c).setWidth((int)Math.min(400F * EnhancedListView.n(c), 0.9F * (float)c.getWidth()));
            EnhancedListView.c(c).setHeight(ScreenMathUtils.dpToPx(40, c.getContext()));
            EnhancedListView.c(c).showAtLocation(c, 81, 0, (int)f);
            if (!EnhancedListView.o(c))
            {
                EnhancedListView.p(c).sendMessageDelayed(EnhancedListView.p(c).obtainMessage(EnhancedListView.g(c)), EnhancedListView.q(c));
            }
        }
        m m1;
        android.view.ViewGroup.LayoutParams layoutparams;
        for (Iterator iterator1 = EnhancedListView.l(c).iterator(); iterator1.hasNext(); m1.c.setLayoutParams(layoutparams))
        {
            m1 = (m)iterator1.next();
            ViewHelper.setAlpha(m1.b, 1.0F);
            ViewHelper.setTranslationX(m1.b, 0.0F);
            layoutparams = m1.c.getLayoutParams();
            layoutparams.height = b;
        }

        EnhancedListView.l(c).clear();
    }
}
