// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.widget.TextView;
import com.flipkart.android.utils.TimerUtils;

// Referenced classes of package com.flipkart.android.customwidget:
//            NewTitleWidget

class this._cls1
    implements Runnable
{

    final ncel this$1;

    public void run()
    {
        NewTitleWidget.access$102(_fld0, NewTitleWidget.access$100(_fld0) - 1L);
        String s = TimerUtils.getTimerAsText(NewTitleWidget.access$100(_fld0));
        merView.setText(s);
        if (NewTitleWidget.access$100(_fld0) <= 0L)
        {
            ncel();
            refreshView();
        }
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
