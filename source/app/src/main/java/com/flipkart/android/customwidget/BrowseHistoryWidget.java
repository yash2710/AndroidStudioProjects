// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            ScrollWidget

public class BrowseHistoryWidget extends ScrollWidget
{

    public static final String TITLE_VALUE = "You Recently Viewed";
    public static final String WIDGET_COMMON_NAME = "BrowseHistoryWidget";
    public static final String WIDGET_TYPE = "recently_viewed";
    AsyncTask loadHistoryAndBuild;
    private String requestId;

    public BrowseHistoryWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, String s, Activity activity)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, true, true, activity, 0);
        requestId = null;
        loadHistoryAndBuild = new _cls1();
        FkLogger.debug("BrowseHistoryTest", (new StringBuilder("layout details is ")).append(layoutdetails).toString());
        requestId = s;
        AsyncTask asynctask = loadHistoryAndBuild;
        Void avoid[] = new Void[0];
        if (!(asynctask instanceof AsyncTask))
        {
            asynctask.execute(avoid);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)asynctask, avoid);
            return;
        }
    }

    private ArrayList getSubList(int i, ArrayList arraylist)
    {
        ArrayList arraylist1 = new ArrayList();
        for (int j = 0; j < i; j++)
        {
            arraylist1.add(arraylist.get(j));
        }

        return arraylist1;
    }

    public void updateWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist)
    {
        super.updateWidget(context, layoutdetails, widgettheme, onclicklistener, widgetitem, arraylist);
        AsyncTask asynctask = loadHistoryAndBuild;
        Void avoid[] = new Void[0];
        if (!(asynctask instanceof AsyncTask))
        {
            asynctask.execute(avoid);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)asynctask, avoid);
            return;
        }
    }



    private class _cls1 extends AsyncTask
        implements TraceFieldInterface
    {

        public Trace _nr_trace;
        final BrowseHistoryWidget this$0;

        public void _nr_setTrace(Trace trace)
        {
            try
            {
                _nr_trace = trace;
                return;
            }
            catch (Exception exception)
            {
                return;
            }
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            TraceMachine.enterMethod(_nr_trace, "BrowseHistoryWidget$1#doInBackground", null);
_L1:
            ArrayList arraylist = doInBackground((Void[])aobj);
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return arraylist;
            NoSuchFieldError nosuchfielderror;
            nosuchfielderror;
            TraceMachine.enterMethod(null, "BrowseHistoryWidget$1#doInBackground", null);
              goto _L1
        }

        protected transient ArrayList doInBackground(Void avoid[])
        {
            ArrayList arraylist = BrowseHistoryUtils.getHistoryProducts("You Recently Viewed", requestId);
            if (arraylist.size() <= 6)
            {
                return arraylist;
            } else
            {
                return getSubList(6, arraylist);
            }
        }

        protected volatile void onPostExecute(Object obj)
        {
            TraceMachine.enterMethod(_nr_trace, "BrowseHistoryWidget$1#onPostExecute", null);
_L1:
            onPostExecute((ArrayList)obj);
            TraceMachine.exitMethod();
            return;
            NoSuchFieldError nosuchfielderror;
            nosuchfielderror;
            TraceMachine.enterMethod(null, "BrowseHistoryWidget$1#onPostExecute", null);
              goto _L1
        }

        protected void onPostExecute(ArrayList arraylist)
        {
            if (arraylist == null || arraylist.size() == 0)
            {
                setVisibility(8);
                setTitleGone();
            } else
            if (!PmuWidgetBuilder.buildPmuWidget(context, onClickListener, FlipkartApplication.getImageLoader(), arraylist, scrollLayout, false, null))
            {
                setTitleGone();
                setVisibility(8);
                return;
            }
        }

        _cls1()
        {
            this$0 = BrowseHistoryWidget.this;
            super();
        }
    }

}
