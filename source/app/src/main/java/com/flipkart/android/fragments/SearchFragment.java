// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.flipkart.android.DB.AutoSuggest;
import com.flipkart.android.DB.AutoSuggestDao;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.SearchMode;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.AutoSuggestVDataHandler;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.response.autoSuggest.AutoSuggestInfo;
import com.flipkart.android.response.autoSuggest.AutoSuggestResponse;
import com.flipkart.android.response.autoSuggest.StoreInfo;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.AutoSuggestType;
import com.flipkart.android.utils.AutoSuggestWord;
import com.flipkart.android.utils.HashUtils;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, bi, bb, bd, 
//            aU, aV, aW, aY, 
//            aZ, ba, bc

public class SearchFragment extends FlipkartBaseFragment
    implements android.view.View.OnClickListener
{

    public static final String DEAFAULT_QUERY = "SEARCH_QUERY";
    private View a;
    private ArrayList b;
    private bd c;
    private int d;
    private AutoCompleteTextView e;
    private LinearLayout f;
    private LinearLayout g;
    private ListView h;
    private LinearLayout i;
    private ImageView j;
    private ImageView k;
    private View l;
    private HomeFragmentHolderActivity m;
    private ArrayList n;
    private boolean o;
    private String p;
    private boolean q;
    private bi r;

    public SearchFragment()
    {
        b = new ArrayList();
        d = 300;
        n = new ArrayList();
        o = false;
        p = null;
        q = false;
        r = bi.a;
    }

    static bi a(SearchFragment searchfragment, bi bi1)
    {
        searchfragment.r = bi1;
        return bi1;
    }

    private void a()
    {
        (new Timer()).schedule(new bb(this), 10L);
    }

    static void a(SearchFragment searchfragment)
    {
        searchfragment.f();
        searchfragment.b();
        searchfragment.m.onBackPressed();
    }

    static void a(SearchFragment searchfragment, AutoSuggestResponse autosuggestresponse)
    {
        if (autosuggestresponse != null)
        {
            Map map = autosuggestresponse.getWordMap();
            if (map != null)
            {
                Iterator iterator = map.keySet().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    String s = (String)iterator.next();
                    ArrayList arraylist = ((AutoSuggestInfo)map.get(s)).getSuggestions();
                    ArrayList arraylist1 = ((AutoSuggestInfo)map.get(s)).getStores();
                    if (arraylist != null)
                    {
                        for (int i1 = 0; i1 < arraylist.size(); i1++)
                        {
                            String s1 = (String)arraylist.get(i1);
                            AutoSuggestWord autosuggestword = new AutoSuggestWord(AutoSuggestType.RecentSearch, s1, i1, null, null);
                            if (!searchfragment.c.contains(autosuggestword))
                            {
                                searchfragment.c.add(autosuggestword);
                            }
                            if (i1 != 0 || arraylist1 == null)
                            {
                                continue;
                            }
                            for (int j1 = 0; j1 < arraylist1.size(); j1++)
                            {
                                String s2 = ((StoreInfo)arraylist1.get(j1)).getTitle();
                                String s3 = ((StoreInfo)arraylist1.get(j1)).getStore_id();
                                if (StringUtils.isNullOrEmpty(s3) || StringUtils.isNullOrEmpty(s2))
                                {
                                    continue;
                                }
                                AutoSuggestWord autosuggestword1 = new AutoSuggestWord(AutoSuggestType.RecentSearch, s1, i1 + j1, s2, s3);
                                if (!searchfragment.c.contains(autosuggestword1))
                                {
                                    searchfragment.c.add(autosuggestword1);
                                }
                            }

                        }

                        TrackingHelper.sendAutoSuggestShown();
                        searchfragment.e();
                    }
                } while (true);
            }
        }
    }

    static void a(SearchFragment searchfragment, AutoSuggestWord autosuggestword)
    {
        searchfragment.a(autosuggestword);
    }

    private void a(AutoSuggestWord autosuggestword)
    {
        if (autosuggestword != null)
        {
            CrashLoggerUtils.pushAndUpdate((new StringBuilder("searching for word ")).append(autosuggestword.getWord()).toString());
            AutoSuggestDao autosuggestdao = new AutoSuggestDao(activity);
            String s = autosuggestword.getWord();
            String s1 = autosuggestword.getStoreId();
            String s2 = autosuggestword.getStoreTitle();
            String s3;
            if (!StringUtils.isNullOrEmpty(s1))
            {
                s3 = HashUtils.md5((new StringBuilder()).append(s.toLowerCase()).append(s1.toLowerCase()).toString());
            } else
            {
                s3 = HashUtils.md5(s.toLowerCase());
            }
            autosuggestdao.create(new AutoSuggest(s3, ScreenMathUtils.getCurrentLinuxTimeInSeconds(), s, s1, s2));
        }
        f();
        b();
        m.callSearch(requestId, autosuggestword, true, r.toString());
    }

    static AutoCompleteTextView b(SearchFragment searchfragment)
    {
        return searchfragment.e;
    }

    private void b()
    {
        if (activity != null)
        {
            InputMethodManager inputmethodmanager = (InputMethodManager)activity.getSystemService("input_method");
            if (inputmethodmanager != null && e != null)
            {
                inputmethodmanager.hideSoftInputFromWindow(e.getWindowToken(), 0);
            }
        }
    }

    private void c()
    {
        if (!o)
        {
            i.setVisibility(0);
        }
        j.setVisibility(8);
    }

    static void c(SearchFragment searchfragment)
    {
        searchfragment.e();
    }

    static ArrayList d(SearchFragment searchfragment)
    {
        return searchfragment.n;
    }

    private void d()
    {
        i.setVisibility(8);
        j.setVisibility(0);
    }

    private void e()
    {
        c.notifyDataSetChanged();
        c.getFilter().filter(e.getText().toString());
    }

    static void e(SearchFragment searchfragment)
    {
        searchfragment.d();
    }

    private void f()
    {
        for (int i1 = 0; i1 < n.size(); i1++)
        {
            AutoSuggestVDataHandler autosuggestvdatahandler = (AutoSuggestVDataHandler)n.get(i1);
            if (autosuggestvdatahandler != null)
            {
                autosuggestvdatahandler.cancelRequests();
            }
        }

    }

    static void f(SearchFragment searchfragment)
    {
        searchfragment.c();
    }

    static int g(SearchFragment searchfragment)
    {
        return searchfragment.d;
    }

    static void h(SearchFragment searchfragment)
    {
        searchfragment.a();
    }

    static bd i(SearchFragment searchfragment)
    {
        return searchfragment.c;
    }

    public boolean handleBackPress()
    {
        f();
        b();
        return false;
    }

    public boolean handleOnClick()
    {
        return false;
    }

    protected void initActionBar()
    {
        getActivity();
        View view = ActionBarView.setActionBarCustomView(activity, ActionBarState.Search_Page);
        k = (ImageView)view.findViewById(0x7f0a0129);
        e = (AutoCompleteTextView)view.findViewById(0x7f0a020d);
        j = (ImageView)view.findViewById(0x7f0a020e);
        if (AppConfigUtils.getInstance().isEnableFlipkartFirst() && FlipkartPreferenceManager.instance().getUsersFfStatus().booleanValue())
        {
            k.setImageResource(0x7f0200eb);
        }
        ((HomeFragmentHolderActivity)activity).hideMainMenu();
    }

    public void onActivityResult(int i1, int j1, Intent intent)
    {
        super.onActivityResult(i1, j1, intent);
        if (i1 != 12) goto _L2; else goto _L1
_L1:
        if (j1 != -1) goto _L4; else goto _L3
_L3:
        q = false;
        String s2 = intent.getStringExtra("SCAN_RESULT");
        TrackingHelper.sendSearchTriggered(s2, SearchMode.Barcode);
        analyticData.setSearchType(r.toString());
        m.openProductPage(null, null, 0, null, "BarCode Result", s2, null, null, null, null, null, "BarCode", analyticData, true, null);
_L6:
        return;
_L4:
        if (j1 == 0 && q)
        {
            q = false;
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
            builder.setMessage("Barcode Timeout");
            builder.setCancelable(true);
            builder.setPositiveButton("OK", null);
            builder.create().show();
            return;
        }
        if (j1 == 100)
        {
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (i1 == 14 && j1 == -1)
        {
            ArrayList arraylist = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
            if (arraylist != null && arraylist.size() > 0)
            {
                String s = (String)arraylist.get(0);
                TrackingHelper.sendSearchTriggered((String)arraylist.get(0), SearchMode.Voice);
                if (s != null && s.length() > 0)
                {
                    String s1 = s.trim();
                    a(new AutoSuggestWord(AutoSuggestType.RecentSearch, s1, 0, null, null));
                    return;
                }
            } else
            {
                TrackingHelper.sendVoiceFailed();
                return;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void onClick(View view)
    {
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        super.onCreateView(layoutinflater, viewgroup, bundle);
        closeRefresing();
        a = layoutinflater.inflate(0x7f0300b2, viewgroup, false);
        initActionBar();
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() < 11)
        {
            e.clearFocus();
        }
        Bundle bundle1 = getArguments();
        if (bundle1 != null)
        {
            p = bundle1.getString("SEARCH_QUERY");
        }
        if (activity != null)
        {
            ((HomeFragmentHolderActivity)activity).lockDrawer();
        }
        e.setHorizontallyScrolling(true);
        f = (LinearLayout)a.findViewById(0x7f0a0215);
        g = (LinearLayout)a.findViewById(0x7f0a0217);
        l = a.findViewById(0x7f0a0216);
        h = (ListView)a.findViewById(0x7f0a0218);
        i = (LinearLayout)a.findViewById(0x7f0a0214);
        c = new bd(this, activity, 0x7f030039, b);
        h.setAdapter(c);
        m = (HomeFragmentHolderActivity)getActivity();
        if (FlipkartPreferenceManager.instance().isNokiaDevice())
        {
            o = true;
        }
        k.setOnClickListener(new aU(this));
        ArrayList arraylist;
        AutoSuggestDao autosuggestdao;
        bc bc1;
        List list;
        if (m != null)
        {
            if (m.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera.autofocus"))
            {
                g.setVisibility(0);
                l.setVisibility(0);
            } else
            {
                g.setVisibility(8);
                l.setVisibility(8);
            }
        }
        e.setOnEditorActionListener(new aV(this));
        e.addTextChangedListener(new aW(this));
        f.setOnClickListener(new aY(this));
        g.setOnClickListener(new aZ(this));
        j.setOnClickListener(new ba(this));
        if (!StringUtils.isNullOrEmpty(p))
        {
            e.setText(p);
            e.setSelection(e.getText().length());
        }
        arraylist = new ArrayList();
        autosuggestdao = new AutoSuggestDao(activity);
        bc1 = new bc(this);
        bc1.doAutoSuggest("", true);
        n.add(bc1);
        list = autosuggestdao.getSortByTime(false);
        if (list != null && list.size() > 0)
        {
            int i1 = 0;
            int k1;
            do
            {
                int j1 = list.size();
                k1 = 0;
                if (i1 >= j1)
                {
                    break;
                }
                AutoSuggest autosuggest = (AutoSuggest)list.get(i1);
                String s = autosuggest.getQuery();
                String s1 = autosuggest.getStoreName();
                String s2 = autosuggest.getStoreId();
                if (s != null && s.length() != 0)
                {
                    if (!StringUtils.isNullOrEmpty(s1) && !StringUtils.isNullOrEmpty(s2))
                    {
                        arraylist.add(new AutoSuggestWord(AutoSuggestType.BrowseHistory, s, i1, s1, s2));
                    } else
                    {
                        arraylist.add(new AutoSuggestWord(AutoSuggestType.BrowseHistory, s, i1, null, null));
                    }
                }
                i1++;
            } while (true);
            for (; k1 < arraylist.size(); k1++)
            {
                AutoSuggestWord autosuggestword = (AutoSuggestWord)arraylist.get(k1);
                if (c.contains(autosuggestword))
                {
                    c.remove(autosuggestword);
                }
                c.add(autosuggestword);
                if (k1 != 0)
                {
                    continue;
                }
                int l1 = FlipkartPreferenceManager.instance().getLastPageType();
                if ((l1 == PageTypeUtils.ProductList.ordinal() || l1 == PageTypeUtils.RefineByCategoryPage.ordinal() || l1 == PageTypeUtils.FilterPage.ordinal() || l1 == PageTypeUtils.SearchListPage.ordinal()) && StringUtils.isNullOrEmpty(p))
                {
                    e.setText(autosuggestword.getWord());
                    e.setSelection(e.getText().length());
                }
            }

        }
        e();
        if (e.getText().length() == 0)
        {
            c();
        } else
        {
            d();
        }
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 11)
        {
            a();
        }
        return a;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        m.unlockDrawer();
    }

    public void onResume()
    {
        super.onResume();
        q = false;
    }

    public void onStop()
    {
        super.onStop();
        q = true;
    }
}
