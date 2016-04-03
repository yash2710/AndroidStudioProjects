// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.os.Handler;
import android.widget.TextView;
import java.util.TimerTask;

// Referenced classes of package com.flipkart.android.customwidget:
//            ProductWidget

class timerView extends TimerTask
{

    final ProductWidget this$0;
    TextView timerView;

    public void run()
    {
        class _cls1
            implements Runnable
        {

            final ProductWidget.UpdateTimer this$1;

            public void run()
            {
                timeRemaining = timeRemaining - 1L;
                String s = TimerUtils.getTimerAsText(timeRemaining);
                timerView.setText(s);
                if (timeRemaining <= 0L)
                {
                    cancel();
                    refreshView();
                }
            }

            _cls1()
            {
                this$1 = ProductWidget.UpdateTimer.this;
                super();
            }
        }

        if (handler != null)
        {
            handler.post(new _cls1());
        }
    }

    public _cls1(TextView textview)
    {
        this$0 = ProductWidget.this;
        super();
        timerView = textview;
    }
}
