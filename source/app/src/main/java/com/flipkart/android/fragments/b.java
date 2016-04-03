// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.SearchVDataHander;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FilterPagePreCallBackCache;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            AllFiltersFragment, q, t

final class b
    implements android.view.View.OnClickListener
{

    private AllFiltersFragment a;

    b(AllFiltersFragment allfiltersfragment)
    {
        a = allfiltersfragment;
        super();
    }

    public final void onClick(View view)
    {
        if (a.activity != null && AllFiltersFragment.e(a) != null && AllFiltersFragment.e(a).findViewById(0x7f0a007b) != null)
        {
            InputMethodManager inputmethodmanager = (InputMethodManager)a.activity.getSystemService("input_method");
            if (inputmethodmanager != null)
            {
                inputmethodmanager.hideSoftInputFromWindow(AllFiltersFragment.e(a).findViewById(0x7f0a007b).getWindowToken(), 0);
            }
            String s = (String)view.getTag();
            AllFiltersFragment.a(a, "");
            if (s != null && s.contains("Offers"))
            {
                AllFiltersFragment.f(a);
            }
            if (!AllFiltersFragment.g(a))
            {
                if (!s.contains("onclick_subcategory"))
                {
                    AllFiltersFragment.a(false);
                }
                if (AllFiltersFragment.h(a) != null && AllFiltersFragment.h(a).getTag().toString().contains("onclick_pincode") && AllFiltersFragment.i(a) == 0)
                {
                    EditText edittext = (EditText)AllFiltersFragment.j(a).findViewById(0x7f0a014b);
                    if (edittext != null)
                    {
                        AllFiltersFragment.b(a, edittext.getText().toString());
                    }
                    if (AllFiltersFragment.k(a) == null)
                    {
                        CustomDialog.showErrorMessage(456, 0, "Pin Code you have entered is invalid. Please retry.", AllFiltersFragment.l(a));
                        return;
                    }
                    if (!StringUtils.isNullOrEmpty(AllFiltersFragment.k(a)) && !StringUtils.isValidIndianPin(AllFiltersFragment.k(a)))
                    {
                        CustomDialog.showErrorMessage(456, 0, "Pin Code you have entered is invalid. Please retry.", AllFiltersFragment.l(a));
                        return;
                    }
                }
                if (s.contains("apply_filters"))
                {
                    a.applyAndExit();
                    return;
                }
                if (s.contains("clear_filters") && !AllFiltersFragment.a())
                {
                    AllFiltersFragment.m(a);
                    CrashLoggerUtils.pushAndUpdate("clearing selected filters");
                    AllFiltersFragment.d(a).clear();
                    AllFiltersFragment.n(a).clear();
                    if (AllFiltersFragment.o(a))
                    {
                        if (a.a != null)
                        {
                            a.a.notifyDataSetChanged();
                            return;
                        }
                    } else
                    {
                        AllFiltersFragment.p(a).clearSelectedFilterMap();
                        LinearLayout linearlayout = (LinearLayout)AllFiltersFragment.e(a).findViewById(0x7f0a0074);
                        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() > 10)
                        {
                            AllFiltersFragment.q(a).setAlpha(0.4F);
                            linearlayout.setAlpha(0.4F);
                            AllFiltersFragment.r(a).setAlpha(0.4F);
                        }
                        AllFiltersFragment.s(a);
                        BrowseParam browseparam = (BrowseParam)AllFiltersFragment.t(a).getParam();
                        String s8 = browseparam.getStoreId();
                        if (AllFiltersFragment.t(a).getStoreID() != null)
                        {
                            s8 = AllFiltersFragment.t(a).getStoreID();
                        }
                        String s1;
                        String s2;
                        String s3;
                        String s4;
                        String s5;
                        String s6;
                        String s7;
                        String s9;
                        if (a.c && AllFiltersFragment.i(a) == 0 && AllFiltersFragment.j(a) != null)
                        {
                            s9 = ((EditText)AllFiltersFragment.j(a).findViewById(0x7f0a014b)).getText().toString();
                        } else
                        {
                            s9 = null;
                        }
                        a.analyticData.setIsPageFirstLanding(false);
                        AllFiltersFragment.u(a).doSearch(s9, browseparam.getQuery(), s8, null, null, null, browseparam.getTags(), 0, 0, 0, browseparam.isEnableAugmentSearch(), a.analyticData, browseparam.getViews());
                        if (AllFiltersFragment.h(a).getTag().toString().contains("onclick_more"))
                        {
                            AllFiltersFragment.v(a);
                            return;
                        }
                    }
                }
            }
            while (false) 
            {
                if (s.contains("on_change_of_filters"))
                {
                    s5 = s.split(";")[1];
                    CrashLoggerUtils.pushAndUpdate((new StringBuilder("clicked on list filter : ")).append(s5).toString());
                    s6 = AllFiltersFragment.b();
                    if (s6.contains("in_more"))
                    {
                        s7 = s6.split("/")[1];
                    } else
                    {
                        s7 = s6;
                    }
                    if (!s7.equals("pincode") && !s7.equals("onclick_more") && !AllFiltersFragment.a())
                    {
                        if (AllFiltersFragment.n(a).get(s7) != null)
                        {
                            ((ArrayList)AllFiltersFragment.n(a).get(s7)).clear();
                        }
                        AllFiltersFragment.n(a).put(s7, new ArrayList(AllFiltersFragment.d(a)));
                    }
                    AllFiltersFragment.a(s5);
                    AllFiltersFragment.a(a, view);
                    AllFiltersFragment.a(a, true);
                    return;
                }
                if (s.contains("onclick_subcategory"))
                {
                    if (AllFiltersFragment.h(a) != view)
                    {
                        s3 = AllFiltersFragment.b();
                        if (s3.contains("in_more"))
                        {
                            s4 = s3.split("/")[1];
                        } else
                        {
                            s4 = s3;
                        }
                        if (!s4.equals("pincode") && !s4.equals("onclick_more"))
                        {
                            if (AllFiltersFragment.n(a).get(s4) != null)
                            {
                                ((ArrayList)AllFiltersFragment.n(a).get(s4)).clear();
                            }
                            AllFiltersFragment.n(a).put(s4, new ArrayList(AllFiltersFragment.d(a)));
                        }
                        AllFiltersFragment.a(a, t.b);
                        AllFiltersFragment.a(a, view);
                        CrashLoggerUtils.pushAndUpdate("clicked on list Subcategories");
                        if (AllFiltersFragment.h(a) != null && !AllFiltersFragment.h(a).getTag().toString().contains("more"))
                        {
                            AllFiltersFragment.a(a, AllFiltersFragment.h(a), AllFiltersFragment.d(a).size());
                        }
                        AllFiltersFragment.a(true);
                        AllFiltersFragment.a(a, (LinearLayout)view);
                        return;
                    }
                    continue;
                }
                if (!s.contains("onclick_more"))
                {
                    continue;
                }
                if (AllFiltersFragment.h(a) != view)
                {
                    s1 = s.split(";")[0];
                    CrashLoggerUtils.pushAndUpdate("clicked on list more filter");
                    s2 = AllFiltersFragment.b();
                    if (!s2.equals("pincode") && !s2.equals("onclick_more") && !AllFiltersFragment.a())
                    {
                        if (AllFiltersFragment.n(a).get(s2) != null)
                        {
                            ((ArrayList)AllFiltersFragment.n(a).get(s2)).clear();
                        }
                        AllFiltersFragment.n(a).put(s2, AllFiltersFragment.d(a));
                    }
                    AllFiltersFragment.a(s1);
                    AllFiltersFragment.a(a, view);
                    AllFiltersFragment.a(a, (LinearLayout)view);
                    AllFiltersFragment.a(a, true);
                    return;
                }
            }
        }
        do
        {
            return;
        } while (!s.contains("onclick_pincode") || AllFiltersFragment.h(a) == view);
        AllFiltersFragment.a(a, t.c);
        AllFiltersFragment.a(a, view);
        AllFiltersFragment.a(a, (LinearLayout)view);
        CrashLoggerUtils.pushAndUpdate("clicked on list Pincode");
    }
}
