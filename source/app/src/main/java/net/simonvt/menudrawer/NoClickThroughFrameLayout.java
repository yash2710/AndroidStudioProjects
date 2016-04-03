// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

// Referenced classes of package net.simonvt.menudrawer:
//            BuildLayerFrameLayout

public class NoClickThroughFrameLayout extends BuildLayerFrameLayout
{

    public NoClickThroughFrameLayout(Context context)
    {
        super(context);
    }

    public NoClickThroughFrameLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public NoClickThroughFrameLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        return true;
    }
}
