// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import android.os.AsyncTask;
import com.flipkart.android.datahandler.param.ComponentDataParams;
import com.flipkart.android.volley.filters.ComponentDataFilter;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.Map;
import java.util.UUID;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler, e

public abstract class ComponentDataDataHandler extends BaseVDataHandler
{

    private String a;
    private ComponentDataParams b[];
    public String omnitureData;
    public String requestId;

    public ComponentDataDataHandler()
    {
        a = null;
        requestId = null;
        omnitureData = null;
        a = UUID.randomUUID().toString();
        requestFilter = new ComponentDataFilter(a);
    }

    static ComponentDataParams[] a(ComponentDataDataHandler componentdatadatahandler)
    {
        return componentdatadatahandler.b;
    }

    static String b(ComponentDataDataHandler componentdatadatahandler)
    {
        return componentdatadatahandler.a;
    }

    public void getComponentData(ComponentDataParams acomponentdataparams[], String s, String s1)
    {
        b = acomponentdataparams;
        requestId = s;
        omnitureData = s1;
        e e1 = new e(this);
        Void avoid[] = new Void[0];
        if (!(e1 instanceof AsyncTask))
        {
            e1.execute(avoid);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)e1, avoid);
            return;
        }
    }

    public abstract void onComponentDataResponseReceived(Map map, boolean flag);

    public volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((Map)obj, flag);
    }

    public void resultReceived(Map map, boolean flag)
    {
        FkLogger.debug("Test", "result received for data");
        onComponentDataResponseReceived(map, false);
    }
}
