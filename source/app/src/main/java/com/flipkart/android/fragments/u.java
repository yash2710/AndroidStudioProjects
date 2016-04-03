// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.PinCodeViewWidget;
import com.flipkart.android.utils.PinCodeWidgetState;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.fragments:
//            FiltersListFragment

final class u
    implements android.view.View.OnClickListener
{

    private FiltersListFragment a;

    u(FiltersListFragment filterslistfragment)
    {
        a = filterslistfragment;
        super();
    }

    public final void onClick(View view)
    {
        if (!(view.getTag() instanceof String)) goto _L2; else goto _L1
_L1:
        String s = (String)view.getTag();
        if (StringUtils.isNullOrEmpty(s)) goto _L2; else goto _L3
_L3:
        if (!s.equals("pincode_clicked")) goto _L5; else goto _L4
_L4:
        String s1;
        EditText edittext = (EditText)a.pincodeViewWidget.findViewById(0x7f0a0148);
        ((InputMethodManager)a.activity.getSystemService("input_method")).hideSoftInputFromWindow(edittext.getWindowToken(), 0);
        s1 = edittext.getText().toString();
        if (s1 == null)
        {
            a.showError(456, 0, "Pin Code you have entered is invalid. Please retry.");
        }
        if (!StringUtils.isValidIndianPin(s1)) goto _L7; else goto _L6
_L6:
        TrackingHelper.sendPincodeCheck(s1);
        FlipkartPreferenceManager.instance().saveUserPinCode(s1);
        a.pushAndChangeContext(s1, null, null, null, null, null);
_L2:
        return;
_L7:
        a.showError(456, 0, "Pin Code you have entered is invalid. Please retry.");
        return;
_L5:
        if (s.equals("showAtAvailablePin"))
        {
            a.pushAndChangeContext(FlipkartPreferenceManager.instance().getUserPinCode(), null, null, null, null, null);
            return;
        }
        if (s.equals("showAll"))
        {
            if (a.pincodeViewWidget.getState() == PinCodeWidgetState.NotFoundShowAll)
            {
                a.browseAllProduct = true;
            }
            TrackingHelper.sendShowAllClicked();
            a.pushAndChangeContext("", null, null, null, null, null);
            return;
        }
        if (s.equals("change_pincode"))
        {
            a.onClickOfAllFilters(false);
            return;
        }
        if (s.contains("dismiss_pincodewidget"))
        {
            FiltersListFragment.a(a).removeAllViews();
            FiltersListFragment.a(a).setVisibility(8);
            FlipkartPreferenceManager.instance().saveDismissPincodeWidget(true);
            a.pincodeViewWidget.removeAllViews();
            a.pincodeViewWidget.setVisibility(8);
            return;
        }
        if (true) goto _L2; else goto _L8
_L8:
    }
}
