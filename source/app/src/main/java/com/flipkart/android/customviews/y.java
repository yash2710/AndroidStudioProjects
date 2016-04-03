// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package com.flipkart.android.customviews:
//            Panel, D, C

final class y
    implements android.view.View.OnTouchListener
{

    private int a;
    private int b;
    private boolean c;
    private Panel d;

    y(Panel panel)
    {
        d = panel;
        super();
    }

    public final boolean onTouch(View view, MotionEvent motionevent)
    {
        int i = -1;
        if (Panel.a(d) != D.b)
        {
            int j = motionevent.getAction();
            if (j == 0)
            {
                if (Panel.b(d))
                {
                    d.bringToFront();
                }
                a = 0;
                b = 0;
                if (Panel.c(d).getVisibility() == 8)
                {
                    if (Panel.d(d) == 1)
                    {
                        if (Panel.e(d) != 0)
                        {
                            i = 1;
                        }
                        b = i;
                    } else
                    {
                        if (Panel.e(d) != 2)
                        {
                            i = 1;
                        }
                        a = i;
                    }
                }
                c = true;
            } else
            {
                if (c)
                {
                    a = a * Panel.f(d);
                    b = b * Panel.g(d);
                    Panel.h(d).setScroll(a, b);
                    c = false;
                    a = -a;
                    b = -b;
                }
                motionevent.offsetLocation(a, b);
            }
            if (!Panel.i(d).onTouchEvent(motionevent) && j == 1)
            {
                d.post(d.a);
                return false;
            }
        }
        return false;
    }
}
