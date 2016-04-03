// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.LinearLayout;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.datahandler.ComponentDataDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.component.MultiWidgetPageBuilder;
import com.flipkart.logging.FkLogger;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            MultiWidgetFragment

final class N extends ComponentDataDataHandler
{

    private MultiWidgetFragment a;

    N(MultiWidgetFragment multiwidgetfragment)
    {
        a = multiwidgetfragment;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        FkLogger.debug("Test", (new StringBuilder("error received for data ")).append(i).append(" ").append(s).toString());
        if (a.activity != null)
        {
            a.h.setVisibility(8);
            ((HomeFragmentHolderActivity)a.activity).closeRefreshing();
            if (i != 204 && i != 400)
            {
                if (a.i != null && a.i.getChildCount() <= 0)
                {
                    a.showError(a.g, i, a, MultiWidgetFragment.d(a));
                    return;
                } else
                {
                    CustomDialog.showErrorMessage(i, j, s, a.activity);
                    return;
                }
            }
        }
    }

    public final void onComponentDataResponseReceived(Map map, boolean flag)
    {
        FkLogger.debug("Test", (new StringBuilder("Result received for component data req ")).append(map).toString());
        if (a.l != null && a.activity != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        FlipkartBaseFragment flipkartbasefragment;
        a.h.setVisibility(8);
        ((HomeFragmentHolderActivity)a.activity).closeRefreshing();
        if (map == null || map.size() == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        MultiWidgetFragment.b(a);
        MultiWidgetFragment.c(a);
        long l = System.currentTimeMillis();
        MultiWidgetPageBuilder.buildMultiWidgetPage(a.j, map, a.i, a.activity, a, FlipkartApplication.getImageLoader(), null, a.b, a.a);
        FkLogger.debug("Metrics", (new StringBuilder("Time to buildWidget(")).append(a.a).append(") ").append(System.currentTimeMillis() - l).toString());
        if (a.i != null && a.i.getChildCount() > 1)
        {
            FkLogger.debug("RefreshProblem", (new StringBuilder("ContainerView ")).append(a.i.getChildCount()).toString());
            a.h.setVisibility(8);
            ((HomeFragmentHolderActivity)a.activity).closeRefreshing();
        }
        flipkartbasefragment = ((HomeFragmentHolderActivity)a.activity).getCurrentFragment();
        if (StringUtils.isNullOrEmpty(a.a) || a.d || flipkartbasefragment == null || flipkartbasefragment != a) goto _L1; else goto _L3
_L3:
        ActionBarView.showSearchIcon();
        return;
        if (flag) goto _L1; else goto _L4
_L4:
        if (a.i.getChildCount() <= 0)
        {
            a.showError(a.g, 500, a, MultiWidgetFragment.d(a));
            return;
        } else
        {
            CustomDialog.showErrorMessage("Please try after sometime.", a.activity);
            return;
        }
    }
}
