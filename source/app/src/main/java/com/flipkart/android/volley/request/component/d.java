// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.component;

import com.flipkart.android.DB.ComponentWidgetLayout;
import com.flipkart.android.DB.ComponentWidgetLayoutDao;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.layout.LayoutContainer;
import com.flipkart.android.utils.GZipCompressorUtil;
import com.flipkart.logging.FkLogger;
import com.google.mygson.Gson;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.volley.request.component:
//            ComponentLayoutRequest

final class d
    implements Runnable
{

    private Map a;

    d(ComponentLayoutRequest componentlayoutrequest, Map map)
    {
        a = map;
        super();
    }

    public final void run()
    {
        if (a != null)
        {
            Set set = a.keySet();
            ComponentWidgetLayoutDao componentwidgetlayoutdao = new ComponentWidgetLayoutDao(FlipkartApplication.getAppContext());
            for (Iterator iterator = set.iterator(); iterator.hasNext();)
            {
                String s = (String)iterator.next();
                LayoutContainer layoutcontainer = (LayoutContainer)a.get(s);
                try
                {
                    componentwidgetlayoutdao.create(new ComponentWidgetLayout(s, GZipCompressorUtil.compress(FlipkartApplication.getGsonInstance().toJson(layoutcontainer).getBytes()), layoutcontainer.getLayoutId()));
                }
                catch (Exception exception)
                {
                    FkLogger.printStackTrace(exception);
                }
            }

        }
    }
}
