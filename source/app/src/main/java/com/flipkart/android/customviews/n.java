// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;
import android.widget.PopupWindow;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.flipkart.android.customviews:
//            EnhancedListView, k

final class n
    implements android.view.View.OnClickListener
{

    private EnhancedListView a;

    private n(EnhancedListView enhancedlistview)
    {
        a = enhancedlistview;
        super();
    }

    n(EnhancedListView enhancedlistview, byte byte0)
    {
        this(enhancedlistview);
    }

    public final void onClick(View view)
    {
        if (EnhancedListView.a(a).isEmpty()) goto _L2; else goto _L1
_L1:
        k.a[EnhancedListView.b(a).ordinal()];
        JVM INSTR tableswitch 1 3: default 56
    //                   1 103
    //                   2 137
    //                   3 202;
           goto _L3 _L4 _L5 _L6
_L3:
        break; /* Loop/switch isn't completed */
_L6:
        break MISSING_BLOCK_LABEL_202;
_L2:
        Iterator iterator;
        if (EnhancedListView.a(a).isEmpty())
        {
            if (EnhancedListView.c(a).isShowing())
            {
                EnhancedListView.c(a).dismiss();
            }
        } else
        {
            EnhancedListView.d(a);
            EnhancedListView.e(a);
        }
        EnhancedListView.f(a);
        return;
_L4:
        ((EnhancedListView.Undoable)EnhancedListView.a(a).get(0)).undo();
        EnhancedListView.a(a).clear();
          goto _L2
_L5:
        Collections.reverse(EnhancedListView.a(a));
        for (iterator = EnhancedListView.a(a).iterator(); iterator.hasNext(); ((EnhancedListView.Undoable)iterator.next()).undo()) { }
        EnhancedListView.a(a).clear();
          goto _L2
        ((EnhancedListView.Undoable)EnhancedListView.a(a).get(-1 + EnhancedListView.a(a).size())).undo();
        EnhancedListView.a(a).remove(-1 + EnhancedListView.a(a).size());
          goto _L2
    }
}
