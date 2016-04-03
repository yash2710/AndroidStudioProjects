// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import android.os.AsyncTask;
import com.flipkart.android.DB.ComponentWidgetLayout;
import com.flipkart.android.DB.ComponentWidgetLayoutDao;
import com.flipkart.android.datahandler.param.CompoentParams;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.layout.LayoutContainer;
import com.flipkart.android.utils.GsonUtils;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.android.datahandler:
//            ComponentLayoutDatahandler

final class f extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private Map a;
    private ComponentLayoutDatahandler b;

    f(ComponentLayoutDatahandler componentlayoutdatahandler)
    {
        b = componentlayoutdatahandler;
        super();
        a = new HashMap();
    }

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

    protected final transient CompoentParams doInBackground(CompoentParams acompoentparams[])
    {
        CompoentParams compoentparams = acompoentparams[0];
        ComponentWidgetLayout componentwidgetlayout = (new ComponentWidgetLayoutDao(FlipkartApplication.getAppContext())).getComponentWidgetLayoutById(compoentparams.getScreenName());
        if (componentwidgetlayout != null)
        {
            LayoutContainer layoutcontainer = (LayoutContainer)GsonUtils.getResponse(com/flipkart/android/response/component/layout/LayoutContainer, componentwidgetlayout.getResponse(), true);
            a.put(compoentparams.getScreenName(), layoutcontainer);
            compoentparams.setLastUpdated(componentwidgetlayout.getLastUpdated());
            FkLogger.debug("Test", (new StringBuilder("Data from db is not null ")).append(componentwidgetlayout.getLastUpdated()).toString());
        }
        return compoentparams;
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "f#doInBackground", null);
_L1:
        CompoentParams compoentparams = doInBackground((CompoentParams[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return compoentparams;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "f#doInBackground", null);
          goto _L1
    }

    protected final void onPostExecute(CompoentParams compoentparams)
    {
        b.onComponentLayoutResponseReceived(a, true);
        ComponentLayoutDatahandler.a(b, compoentparams, b.pageTrackingRequestId, b.omnitureData);
    }

    protected final volatile void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "f#onPostExecute", null);
_L1:
        onPostExecute((CompoentParams)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "f#onPostExecute", null);
          goto _L1
    }
}
