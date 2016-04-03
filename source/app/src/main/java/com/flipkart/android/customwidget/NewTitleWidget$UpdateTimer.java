// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.os.Handler;
import android.widget.TextView;
import java.util.TimerTask;

// Referenced classes of package com.flipkart.android.customwidget:
//            NewTitleWidget

class timerView extends TimerTask
{

    final NewTitleWidget this$0;
    TextView timerView;

    public void run()
    {
        class _cls1
            implements Runnable
        {

            final NewTitleWidget.UpdateTimer this$1;

            public void run()
            {
                NewTitleWidget.access$102(this$0, NewTitleWidget.access$100(this$0) - 1L);
                String s = TimerUtils.getTimerAsText(NewTitleWidget.access$100(this$0));
                timerView.setText(s);
                if (NewTitleWidget.access$100(this$0) <= 0L)
                {
                    cancel();
                    refreshView();
                }
            }

            _cls1()
            {
                this$1 = NewTitleWidget.UpdateTimer.this;
                super();
            }
        }

        if (NewTitleWidget.access$000(NewTitleWidget.this) != null)
        {
            NewTitleWidget.access$000(NewTitleWidget.this).post(new _cls1());
        }
    }

    public _cls1(TextView textview)
    {
        this$0 = NewTitleWidget.this;
        super();
        timerView = textview;
    }
}
