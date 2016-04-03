// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.fragments.MultiWidgetFragment;
import com.flipkart.android.utils.TabContextCache;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            CustomTabWidget

class this._cls0
    implements com.viewpagerindicator.ator.PageChangedListener
{

    final CustomTabWidget this$0;

    public void pageChanged(int i)
    {
        TabContextCache.getInstance().putResponse("currentTabPosition", Integer.valueOf(i));
        TrackingHelper.sendTabWidgetChange((String)CustomTabWidget.access$000(CustomTabWidget.this).get(i));
        if (aliveFragmentArray != null && i < aliveFragmentArray.length && aliveFragmentArray[i] != null)
        {
            aliveFragmentArray[i].refreshPage();
        }
    }

    public void pageStateChanged(int i)
    {
        MultiWidgetFragment amultiwidgetfragment[];
        int j;
        int k;
        MultiWidgetFragment multiwidgetfragment;
        try
        {
            amultiwidgetfragment = aliveFragmentArray;
            j = amultiwidgetfragment.length;
        }
        catch (Exception exception)
        {
            break; /* Loop/switch isn't completed */
        }
        k = 0;
_L2:
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        multiwidgetfragment = amultiwidgetfragment[k];
        if (multiwidgetfragment == null)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        multiwidgetfragment.tabViewScrollChanged(i);
        k++;
        continue; /* Loop/switch isn't completed */
        if (true) goto _L2; else goto _L1
_L1:
    }

    angedListener()
    {
        this$0 = CustomTabWidget.this;
        super();
    }
}
