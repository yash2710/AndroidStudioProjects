// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.view.View;
import com.flipkart.android.utils.animation.FkAnimationUtils;

// Referenced classes of package com.flipkart.android.customwidget:
//            BalloonWidget

class lon
    implements Runnable
{

    final BalloonWidget this$0;
    final View val$ballon;

    public void run()
    {
        val$ballon.setVisibility(0);
        FkAnimationUtils.performScaleYAnimation(context, val$ballon);
    }

    ils()
    {
        this$0 = final_balloonwidget;
        val$ballon = View.this;
        super();
    }
}
