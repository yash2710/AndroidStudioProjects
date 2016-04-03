// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.component;

import com.flipkart.android.DB.ComponentWidgetData;
import com.flipkart.android.DB.ComponentWidgetDataDao;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetData;
import com.flipkart.android.utils.GZipCompressorUtil;
import com.flipkart.android.volley.request.component.params.ComponentDataParam;
import com.google.mygson.Gson;
import java.util.Map;

// Referenced classes of package com.flipkart.android.volley.request.component:
//            ComponentDataRequest

final class b
    implements Runnable
{

    private Map a;
    private ComponentDataRequest b;

    b(ComponentDataRequest componentdatarequest, Map map)
    {
        b = componentdatarequest;
        a = map;
        super();
    }

    public final void run()
    {
        String as[];
        ComponentWidgetDataDao componentwidgetdatadao;
        as = ComponentDataRequest.a(b).getWidgets();
        componentwidgetdatadao = new ComponentWidgetDataDao(FlipkartApplication.getAppContext());
        int i = as.length;
        int j = 0;
_L2:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_170;
        }
        String s;
        WidgetData widgetdata;
        s = as[j];
        widgetdata = (WidgetData)a.get(s);
        if (widgetdata != null)
        {
            ComponentWidgetData componentwidgetdata;
            try
            {
                componentwidgetdatadao.create(new ComponentWidgetData((new StringBuilder()).append(ComponentDataRequest.a(b).getScreenName()).append("/").append(s).toString(), System.currentTimeMillis(), GZipCompressorUtil.compress(FlipkartApplication.getGsonInstance().toJson(widgetdata).getBytes())));
                break MISSING_BLOCK_LABEL_171;
            }
            catch (Exception exception) { }
            break MISSING_BLOCK_LABEL_170;
        }
        componentwidgetdata = componentwidgetdatadao.getComponentWidgetDataById(ComponentDataRequest.a(b).getScreenName(), s);
        if (componentwidgetdata == null)
        {
            break MISSING_BLOCK_LABEL_171;
        }
        componentwidgetdata.setLastUpdated(System.currentTimeMillis());
        componentwidgetdatadao.update(componentwidgetdata);
        break MISSING_BLOCK_LABEL_171;
        return;
        j++;
        if (true) goto _L2; else goto _L1
_L1:
    }
}
