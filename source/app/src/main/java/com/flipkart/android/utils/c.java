// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import com.flipkart.android.customwidget.WidgetAction;
import com.flipkart.android.response.customwidgetitemvalue.Action;

// Referenced classes of package com.flipkart.android.utils:
//            CustomPopupDialog, PageTypeUtils

final class c
    implements android.view.View.OnClickListener
{

    private Action a;
    private Activity b;
    private CustomPopupDialog c;

    c(CustomPopupDialog custompopupdialog, Action action, Activity activity)
    {
        c = custompopupdialog;
        a = action;
        b = activity;
        super();
    }

    public final void onClick(View view)
    {
        if (CustomPopupDialog.a(c) != null && CustomPopupDialog.a(c).isShowing())
        {
            CustomPopupDialog.a(c).dismiss();
            if (a != null)
            {
                WidgetAction.performAction(a, b, PageTypeUtils.ProductPage);
            }
        }
    }
}
