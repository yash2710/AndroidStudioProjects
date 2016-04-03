// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.CustomRobotoLightTextView;
import com.flipkart.android.customviews.CustomRobotoMediumTextView;
import com.flipkart.android.customviews.SubCategoryCallBack;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.SearchVDataHander;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.response.discovery.DiscoveryResponse;
import com.flipkart.android.response.discovery.MetaDataResponse;
import com.flipkart.android.response.discovery.SearchResponse;
import com.flipkart.android.response.discovery.ShowPinResponse;
import com.flipkart.android.response.discovery.StoreMetaInfo;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.FacetData;
import com.flipkart.android.utils.FilterImageUtils;
import com.flipkart.android.utils.FilterPagePreCallBackCache;
import com.flipkart.android.utils.FkLoadingDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.RefineByCategoryResponse;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.drawable.FilterDrawableMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, b, t, SubCategoryTreeView, 
//            m, n, o, p, 
//            q, s, c, d, 
//            e, g, h, l, 
//            FiltersListFragment, f, a, i, 
//            j, k

public class AllFiltersFragment extends FlipkartBaseFragment
    implements SubCategoryCallBack
{

    public static final String ALL_FILTERS_FRAGMENT = "ALL_FILTERS_FRAG";
    private static String e = null;
    private static boolean f = false;
    private static int z = 0;
    private Context A;
    private boolean B;
    private FkLoadingDialog C;
    private FilterPagePreCallBackCache D;
    private View E;
    private String F;
    private int G;
    private int H;
    private int I;
    private String J;
    private HomeFragmentHolderActivity K;
    private ImageLoader L;
    private String M;
    private boolean N;
    private boolean O;
    private android.view.View.OnClickListener P;
    private View Q;
    q a;
    s b;
    boolean c;
    private RefineByCategoryResponse d;
    private CustomRobotoMediumTextView g;
    private CustomRobotoMediumTextView h;
    private ArrayList i;
    private ArrayList j;
    private ArrayList k;
    private SearchVDataHander l;
    private Map m;
    private ArrayList n;
    private ArrayList o;
    private FkProductListContext p;
    private String q;
    private SubCategoryTreeView r;
    private RelativeLayout s;
    private LayoutInflater t;
    private ListView u;
    private EditText v;
    private LinearLayout w;
    private ExpandableListView x;
    private LinearLayout y;

    public AllFiltersFragment()
    {
        a = null;
        i = new ArrayList();
        j = new ArrayList();
        k = new ArrayList();
        l = null;
        m = new HashMap();
        n = null;
        o = null;
        p = null;
        q = "";
        A = null;
        B = false;
        c = false;
        E = null;
        F = null;
        G = -1;
        H = -1;
        I = -1;
        J = "";
        N = false;
        O = false;
        P = new b(this);
        Q = null;
    }

    static FkLoadingDialog A(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.C;
    }

    static void B(AllFiltersFragment allfiltersfragment)
    {
        allfiltersfragment.f();
    }

    static ArrayList C(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.k;
    }

    static void D(AllFiltersFragment allfiltersfragment)
    {
        String s1 = ((BrowseParam)allfiltersfragment.p.getParam()).getStoreId();
        allfiltersfragment.p.setStoreID(s1);
    }

    static int a(AllFiltersFragment allfiltersfragment, int i1)
    {
        allfiltersfragment.G = i1;
        return i1;
    }

    static EditText a(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.v;
    }

    static LinearLayout a(AllFiltersFragment allfiltersfragment, LinearLayout linearlayout)
    {
        allfiltersfragment.y = linearlayout;
        return linearlayout;
    }

    static String a(AllFiltersFragment allfiltersfragment, String s1)
    {
        allfiltersfragment.J = s1;
        return s1;
    }

    static String a(AllFiltersFragment allfiltersfragment, Map map, int i1)
    {
        Iterator iterator = map.keySet().iterator();
        for (int j1 = 0; iterator.hasNext(); j1++)
        {
            String s1 = (String)iterator.next();
            if (j1 == i1)
            {
                return s1;
            }
        }

        return "";
    }

    static String a(String s1)
    {
        e = s1;
        return s1;
    }

    private void a(int i1)
    {
        View view = K.getSupportActionBar().getCustomView();
        if (view != null)
        {
            CustomRobotoLightTextView customrobotolighttextview = (CustomRobotoLightTextView)view.findViewById(0x7f0a00eb);
            if (customrobotolighttextview != null)
            {
                customrobotolighttextview.setText((new StringBuilder()).append(i1).append(" products").toString());
            }
        }
    }

    private static void a(View view, int i1)
    {
        TextView textview = (TextView)((LinearLayout)view).findViewById(0x7f0a022f);
        if (i1 == 0)
        {
            if (textview.getVisibility() == 0)
            {
                textview.setVisibility(8);
            }
            return;
        }
        if (textview.getVisibility() == 8)
        {
            textview.setVisibility(0);
        }
        textview.setText((new StringBuilder()).append(i1).toString());
    }

    static void a(AllFiltersFragment allfiltersfragment, View view)
    {
        if (view == null)
        {
            if (allfiltersfragment.y != null)
            {
                allfiltersfragment.y.setBackgroundColor(Color.parseColor("#f6f4ee"));
                ((TextView)allfiltersfragment.y.findViewById(0x7f0a00df)).setTextColor(0xff000000);
            }
        } else
        {
            if (view != null && view != allfiltersfragment.y)
            {
                view.setBackgroundColor(Color.parseColor("#f6f4ee"));
                ((TextView)view.findViewById(0x7f0a00df)).setTextColor(0xff000000);
            }
            if (allfiltersfragment.y != null)
            {
                allfiltersfragment.y.setBackgroundColor(Color.parseColor("#434343"));
                ((TextView)allfiltersfragment.y.findViewById(0x7f0a00df)).setTextColor(-1);
                return;
            }
        }
    }

    static void a(AllFiltersFragment allfiltersfragment, View view, int i1)
    {
        a(view, i1);
    }

    static void a(AllFiltersFragment allfiltersfragment, t t1)
    {
        allfiltersfragment.a(t1);
    }

    static void a(AllFiltersFragment allfiltersfragment, boolean flag)
    {
        if (!allfiltersfragment.e())
        {
            if (flag)
            {
                allfiltersfragment.b(true);
                allfiltersfragment.f();
                allfiltersfragment.m.clear();
            }
            return;
        }
        LinearLayout linearlayout = (LinearLayout)allfiltersfragment.s.findViewById(0x7f0a0074);
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() > 10)
        {
            allfiltersfragment.w.setAlpha(0.4F);
            linearlayout.setAlpha(0.4F);
            allfiltersfragment.x.setAlpha(0.4F);
        }
        allfiltersfragment.i();
        BrowseParam browseparam = (BrowseParam)allfiltersfragment.p.getParam();
        String s1 = browseparam.getStoreId();
        if (allfiltersfragment.p.getStoreID() != null)
        {
            s1 = allfiltersfragment.p.getStoreID();
        }
        SearchVDataHander searchvdatahander = allfiltersfragment.l;
        String s2 = browseparam.getQuery();
        ArrayList arraylist = allfiltersfragment.d();
        searchvdatahander.doSearch(null, s2, s1, null, (String[])arraylist.toArray(new String[arraylist.size()]), null, browseparam.getTags(), 0, 0, 0, browseparam.isEnableAugmentSearch(), allfiltersfragment.analyticData, browseparam.getViews());
    }

    private void a(t t1)
    {
        if (x != null)
        {
            x.setVisibility(8);
        }
        if (t1 == t.a)
        {
            if (w.findViewById(1010) != null)
            {
                w.findViewById(1010).setVisibility(8);
            }
            if (w.findViewById(9999) != null)
            {
                w.findViewById(9999).setVisibility(8);
            }
            u.setVisibility(0);
            v.setVisibility(0);
        } else
        {
            if (t1 == t.b)
            {
                v.setVisibility(8);
                u.setVisibility(8);
                if (w.findViewById(9999) != null)
                {
                    w.findViewById(9999).setVisibility(8);
                }
                if (w.findViewById(1010) == null || O || e())
                {
                    O = false;
                    if (w.findViewById(1010) != null)
                    {
                        w.removeView(w.findViewById(1010));
                    }
                    LinearLayout linearlayout1 = w;
                    r = new SubCategoryTreeView(activity, null, d());
                    RefineByCategoryResponse refinebycategoryresponse = new RefineByCategoryResponse();
                    refinebycategoryresponse.setParentStoreList(o);
                    refinebycategoryresponse.setStoreList(n);
                    refinebycategoryresponse.setFkContext(p);
                    r.setListener(this);
                    r.init(t, refinebycategoryresponse);
                    ScrollView scrollview = new ScrollView(A);
                    scrollview.addView(r);
                    scrollview.setId(1010);
                    linearlayout1.addView(scrollview);
                    return;
                } else
                {
                    w.findViewById(1010).setVisibility(0);
                    return;
                }
            }
            if (t1 == t.c)
            {
                v.setVisibility(8);
                u.setVisibility(8);
                if (w.findViewById(9999) == null)
                {
                    LinearLayout linearlayout = w;
                    E = t.inflate(0x7f03006d, null);
                    E.setId(9999);
                    RadioButton radiobutton = (RadioButton)E.findViewById(0x7f0a014c);
                    RadioButton radiobutton1 = (RadioButton)E.findViewById(0x7f0a014e);
                    EditText edittext = (EditText)E.findViewById(0x7f0a014b);
                    TextView textview = (TextView)E.findViewById(0x7f0a014d);
                    TextView textview1 = (TextView)E.findViewById(0x7f0a014a);
                    edittext.setOnTouchListener(new m(this, radiobutton, radiobutton1, edittext));
                    edittext.setOnEditorActionListener(new n(this, edittext));
                    M = FlipkartPreferenceManager.instance().getUserPinCode();
                    String s1 = ((BrowseParam)p.getParam()).getPincode();
                    if (!StringUtils.isNullOrEmpty(M) && !StringUtils.isNullOrEmpty(s1))
                    {
                        edittext.setText(M);
                        radiobutton.setChecked(true);
                        textview1.setTypeface(null, 1);
                        G = 0;
                        edittext.setCursorVisible(true);
                    } else
                    {
                        radiobutton1.setChecked(true);
                        G = 1;
                        textview.setTypeface(null, 1);
                        textview1.setTypeface(null, 0);
                        edittext.setCursorVisible(false);
                    }
                    H = G;
                    radiobutton.setOnClickListener(new o(this, radiobutton1, textview1, textview));
                    radiobutton1.setOnClickListener(new p(this, radiobutton, edittext, textview, textview1));
                    linearlayout.addView(E);
                } else
                {
                    w.findViewById(9999).setVisibility(0);
                }
                if (w.findViewById(1010) != null)
                {
                    w.findViewById(1010).setVisibility(8);
                    return;
                }
            }
        }
    }

    static boolean a()
    {
        return f;
    }

    static boolean a(boolean flag)
    {
        f = flag;
        return flag;
    }

    static int b(AllFiltersFragment allfiltersfragment, int i1)
    {
        allfiltersfragment.I = i1;
        return i1;
    }

    static View b(AllFiltersFragment allfiltersfragment, View view)
    {
        allfiltersfragment.Q = view;
        return view;
    }

    static String b()
    {
        return e;
    }

    static String b(AllFiltersFragment allfiltersfragment, String s1)
    {
        allfiltersfragment.F = s1;
        return s1;
    }

    static ArrayList b(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.j;
    }

    private void b(String s1)
    {
        LinearLayout linearlayout = (LinearLayout)s.findViewById(0x7f0a0074);
        for (int i1 = 0; i1 < linearlayout.getChildCount(); i1++)
        {
            LinearLayout linearlayout1 = (LinearLayout)linearlayout.getChildAt(i1);
            String s2 = linearlayout1.getTag().toString();
            String as[] = s2.split(";");
            if (as.length > 1 && as[1].equals(s1))
            {
                linearlayout1.setBackgroundColor(Color.parseColor("#f6f4ee"));
                ((TextView)linearlayout1.findViewById(0x7f0a00df)).setTextColor(0xff000000);
                y = linearlayout1;
            }
            if (s2.contains("onclick_subcategory") || s2.contains("onclick_pincode"))
            {
                linearlayout1.setBackgroundColor(Color.parseColor("#434343"));
                ((TextView)linearlayout1.findViewById(0x7f0a00df)).setTextColor(-1);
            }
        }

    }

    private void b(boolean flag)
    {
        if (x != null)
        {
            x.setVisibility(8);
        }
        LinearLayout linearlayout = (LinearLayout)s.findViewById(0x7f0a0074);
        if (!flag)
        {
            linearlayout.setVisibility(8);
            v.setVisibility(8);
            u.setVisibility(8);
            if (w.findViewById(1010) != null)
            {
                w.findViewById(1010).setVisibility(8);
            }
            return;
        } else
        {
            linearlayout.setVisibility(0);
            v.setVisibility(0);
            u.setVisibility(0);
            return;
        }
    }

    static boolean b(AllFiltersFragment allfiltersfragment, boolean flag)
    {
        allfiltersfragment.O = true;
        return true;
    }

    static ListView c(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.u;
    }

    static String c(AllFiltersFragment allfiltersfragment, int i1)
    {
        Iterator iterator = allfiltersfragment.D.getSelectedFilterMap().keySet().iterator();
        for (int j1 = 0; iterator.hasNext(); j1++)
        {
            String s1 = (String)iterator.next();
            if (j1 == i1 + 5)
            {
                return s1;
            }
        }

        return "";
    }

    private void c()
    {
        String s1;
        s1 = "";
        for (Iterator iterator = p.getSelectedFilterMap().keySet().iterator(); iterator.hasNext();)
        {
            Object obj = iterator.next();
            int i1 = 0;
            while (i1 < ((ArrayList)p.getSelectedFilterMap().get(obj)).size()) 
            {
                if (i1 == 0)
                {
                    s1 = s1.concat((new StringBuilder()).append(obj).append("-").toString());
                }
                String s2 = s1.concat((String)((ArrayList)p.getSelectedFilterMap().get(obj)).get(i1));
                String s3;
                if (i1 == -1 + ((ArrayList)p.getSelectedFilterMap().get(obj)).size())
                {
                    s3 = s2.concat(";");
                } else
                {
                    s3 = s2.concat(",");
                }
                i1++;
                s1 = s3;
            }
        }

        if (!StringUtils.isNullOrEmpty(p.getPincode()) && c)
        {
            s1 = s1.concat((new StringBuilder("Pincode-")).append(p.getPincode()).append(";").toString());
        }
        if (StringUtils.isNullOrEmpty(q)) goto _L2; else goto _L1
_L1:
        s1 = s1.concat((new StringBuilder("SubCategory-")).append(q).toString());
_L4:
        if (s1.endsWith(";"))
        {
            s1 = s1.substring(0, -1 + s1.length());
        }
        TrackingHelper.sendFilterApplied(s1);
        return;
_L2:
        if (p.getStoreMetaInfo() != null)
        {
            s1 = s1.concat((new StringBuilder("SubCategory-")).append(p.getStoreMetaInfo().getTitle()).toString());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    static void c(AllFiltersFragment allfiltersfragment, boolean flag)
    {
        allfiltersfragment.b(flag);
    }

    private void c(String s1)
    {
        if (s1 != null)
        {
            ArrayList arraylist = AppConfigUtils.getInstance().getSearchSupportedFilters();
            boolean flag;
            Map map;
            if (arraylist != null && !StringUtils.isNullOrEmpty(s1) && arraylist.contains(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                v.setVisibility(8);
            }
            b(s1);
            i = new ArrayList();
            i.clear();
            k.clear();
            j.clear();
            if (m != null && m.containsKey(s1))
            {
                i.addAll((Collection)m.get(s1));
            } else
            if (D.getSelectedFilterMap() != null && D.getSelectedFilterMap().containsKey(s1))
            {
                i.addAll((Collection)D.getSelectedFilterMap().get(s1));
            }
            map = (Map)D.getFilterMap().get(s1);
            if (map != null)
            {
                for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
                {
                    String s3 = (String)iterator.next();
                    if (((FacetData)map.get(s3)).getCount() == 0 && !i.contains(s3))
                    {
                        k.add(s3);
                    }
                }

                j.addAll(i);
                Iterator iterator1 = map.keySet().iterator();
                do
                {
                    if (!iterator1.hasNext())
                    {
                        break;
                    }
                    String s2 = (String)iterator1.next();
                    if (!i.contains(s2) && !k.contains(s2))
                    {
                        j.add(s2);
                    }
                } while (true);
                j.addAll(k);
                if (a == null)
                {
                    u.setVisibility(0);
                    a = new q(this, A, 0x7f0300bf, j);
                    u.setAdapter(a);
                    return;
                } else
                {
                    a.setFinalString(j);
                    a.notifyDataSetChanged();
                    u.invalidateViews();
                    return;
                }
            }
        }
    }

    private void c(boolean flag)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        LinearLayout linearlayout;
        Map map;
        String s1;
        z = 0;
        linearlayout = (LinearLayout)s.findViewById(0x7f0a0074);
        map = D.getSelectedFilterMap();
        if (flag)
        {
            LinearLayout linearlayout6 = (LinearLayout)t.inflate(0x7f0300bb, null);
            LinearLayout linearlayout7 = (LinearLayout)linearlayout6.findViewById(0x7f0a022d);
            TextView textview3 = (TextView)linearlayout6.findViewById(0x7f0a00df);
            textview3.setText("Sub-category");
            textview3.setCompoundDrawablesWithIntrinsicBounds(0, 0x7f020168, 0, 0);
            linearlayout7.setTag("onclick_subcategory");
            linearlayout7.setOnClickListener(P);
            linearlayout.addView(linearlayout6);
        }
        if (c)
        {
            LinearLayout linearlayout4 = (LinearLayout)t.inflate(0x7f0300bb, null);
            LinearLayout linearlayout5 = (LinearLayout)linearlayout4.findViewById(0x7f0a022d);
            TextView textview2 = (TextView)linearlayout4.findViewById(0x7f0a00df);
            textview2.setText("Pincode");
            textview2.setCompoundDrawablesWithIntrinsicBounds(0, 0x7f02010e, 0, 0);
            linearlayout5.setTag("onclick_pincode");
            linearlayout5.setOnClickListener(P);
            linearlayout.addView(linearlayout4);
        }
        int i1 = linearlayout.getChildCount();
        int j1;
        Iterator iterator;
        LinearLayout linearlayout2;
        LinearLayout linearlayout3;
        TextView textview1;
        if (c)
        {
            linearlayout.removeViews(2, i1 - 2);
        } else
        {
            linearlayout.removeViews(1, i1 - 1);
        }
        j1 = map.keySet().size();
        iterator = map.keySet().iterator();
_L4:
        if (iterator.hasNext())
        {
label0:
            {
                s1 = (String)iterator.next();
                if (z != 5)
                {
                    break label0;
                }
                if (j1 > 5)
                {
                    linearlayout2 = (LinearLayout)t.inflate(0x7f0300bb, null);
                    linearlayout3 = (LinearLayout)linearlayout2.findViewById(0x7f0a022d);
                    textview1 = (TextView)linearlayout2.findViewById(0x7f0a00df);
                    textview1.setPadding(ScreenMathUtils.dpToPx(15, A), 0, ScreenMathUtils.dpToPx(10, A), 0);
                    textview1.setText("More");
                    textview1.setCompoundDrawablesWithIntrinsicBounds(0, 0x7f020113, 0, 0);
                    linearlayout3.setTag("onclick_more");
                    linearlayout3.setOnClickListener(P);
                    linearlayout.addView(linearlayout2);
                    return;
                }
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
        View view;
        LinearLayout linearlayout1;
        TextView textview;
        view = t.inflate(0x7f0300bb, null);
        linearlayout1 = (LinearLayout)view.findViewById(0x7f0a022d);
        textview = (TextView)view.findViewById(0x7f0a00df);
        boolean flag2 = FilterImageUtils.showFilterImage(s1, linearlayout1, L);
        boolean flag1 = flag2;
_L5:
        if (!flag1)
        {
            ((NetworkImageView)linearlayout1.findViewById(0x7f0a00e3)).setVisibility(8);
            textview.setCompoundDrawablesWithIntrinsicBounds(0, FlipkartApplication.getFilterDrawableMap().getDrawableForFilter(s1).intValue(), 0, 0);
        }
        if (z == 0)
        {
            y = linearlayout1;
        }
        z = 1 + z;
        textview.setText(s1);
        a(linearlayout1, ((ArrayList)map.get(s1)).size());
        linearlayout1.setTag((new StringBuilder("on_change_of_filters;")).append(s1).toString());
        linearlayout1.setOnClickListener(P);
        linearlayout.addView(view);
          goto _L4
        Exception exception;
        exception;
        flag1 = false;
          goto _L5
    }

    private ArrayList d()
    {
        ArrayList arraylist;
        Map map;
        if (p == null)
        {
            return null;
        }
        arraylist = new ArrayList();
        map = D.getFilterMap();
        Iterator iterator = m.keySet().iterator();
        Map map1 = null;
        while (iterator.hasNext()) 
        {
            String s3 = (String)iterator.next();
            ((ArrayList)m.get(s3)).size();
            if (map != null)
            {
                map1 = (Map)map.get(s3);
            }
            int j1 = 0;
            while (j1 < ((ArrayList)m.get(s3)).size()) 
            {
                String s4 = (String)((ArrayList)m.get(s3)).get(j1);
                if (map1 != null)
                {
                    FacetData facetdata1 = (FacetData)map1.get(s4);
                    if (facetdata1 != null)
                    {
                        arraylist.add(facetdata1.getParams());
                    }
                }
                j1++;
            }
        }
        if (D.getSelectedFilterMap() == null) goto _L2; else goto _L1
_L1:
        Iterator iterator1 = D.getSelectedFilterMap().keySet().iterator();
_L4:
        if (!iterator1.hasNext()) goto _L2; else goto _L3
_L3:
        String s1 = (String)iterator1.next();
        if (!m.containsKey(s1))
        {
            Map map2;
            int i1;
            if (map != null)
            {
                map2 = (Map)map.get(s1);
            } else
            {
                map2 = null;
            }
            i1 = 0;
            while (i1 < ((ArrayList)D.getSelectedFilterMap().get(s1)).size()) 
            {
                String s2 = (String)((ArrayList)D.getSelectedFilterMap().get(s1)).get(i1);
                if (map2 != null)
                {
                    FacetData facetdata = (FacetData)map2.get(s2);
                    if (facetdata != null)
                    {
                        arraylist.add(facetdata.getParams());
                    }
                }
                i1++;
            }
        }
        if (true) goto _L4; else goto _L2
_L2:
        return arraylist;
    }

    static ArrayList d(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.i;
    }

    static void d(AllFiltersFragment allfiltersfragment, int i1)
    {
        allfiltersfragment.a(i1);
    }

    static boolean d(AllFiltersFragment allfiltersfragment, boolean flag)
    {
        allfiltersfragment.B = false;
        return false;
    }

    static RelativeLayout e(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.s;
    }

    static void e(AllFiltersFragment allfiltersfragment, boolean flag)
    {
        allfiltersfragment.c(false);
    }

    private boolean e()
    {
        Iterator iterator = m.keySet().iterator();
        do
        {
            ArrayList arraylist;
            ArrayList arraylist1;
label0:
            {
label1:
                {
                    if (iterator.hasNext())
                    {
                        String s1 = (String)iterator.next();
                        arraylist = (ArrayList)m.get(s1);
                        arraylist1 = (ArrayList)D.getSelectedFilterMap().get(s1);
                        if (arraylist1 != null)
                        {
                            break label0;
                        }
                        if (arraylist.size() != 0)
                        {
                            break label1;
                        }
                    }
                    return false;
                }
                return true;
            }
            if (arraylist.size() != arraylist1.size())
            {
                return true;
            }
            int i1 = 0;
            while (i1 < arraylist.size()) 
            {
                if (!arraylist1.contains(arraylist.get(i1)))
                {
                    return true;
                }
                i1++;
            }
        } while (true);
    }

    private void f()
    {
        if (e.contains("onclick_more"))
        {
            if (D.getSelectedFilterMap().size() == 5)
            {
                Iterator iterator = D.getSelectedFilterMap().keySet().iterator();
                if (iterator.hasNext())
                {
                    e = (String)iterator.next();
                }
                c(e);
                return;
            } else
            {
                v.setVisibility(8);
                u.setVisibility(8);
                i.clear();
                b(e);
                g();
                return;
            }
        }
        if (e.contains("in_more"))
        {
            v.setVisibility(8);
            u.setVisibility(8);
            b("onclick_more");
            g();
            return;
        } else
        {
            c(e);
            return;
        }
    }

    static void f(AllFiltersFragment allfiltersfragment)
    {
        Map map;
        ArrayList arraylist;
        if (allfiltersfragment.D == null || allfiltersfragment.D.getFilterMap() == null)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        map = (Map)allfiltersfragment.D.getFilterMap().get("Offers");
        arraylist = new ArrayList();
        if (map == null)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); arraylist.add(((FacetData)map.get((String)iterator.next())).getOfferId())) { }
        try
        {
            TrackingHelper.sendOfferImpressions(allfiltersfragment.analyticData.getRequestId(), arraylist);
            return;
        }
        catch (Exception exception) { }
    }

    private void g()
    {
        k.clear();
        j.clear();
        x.setVisibility(0);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -1);
        b = new s(this, (byte)0);
        x.setAdapter(b);
        x.setBackgroundColor(activity.getResources().getColor(0x7f090070));
        x.setDividerHeight(ScreenMathUtils.dpToPx(2, activity));
        x.setChildIndicator(null);
        x.setGroupIndicator(null);
        x.setDivider(new ColorDrawable(activity.getResources().getColor(0x7f090036)));
        x.setChildDivider(new ColorDrawable(activity.getResources().getColor(0x7f090036)));
        x.setLayoutParams(layoutparams);
        x.setOnGroupClickListener(new c(this));
        x.setOnChildClickListener(new d(this));
        x.setOnGroupExpandListener(new e(this));
    }

    static boolean g(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.B;
    }

    static LinearLayout h(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.y;
    }

    private boolean h()
    {
        Map map;
        Map map1;
        if (p == null || D == null)
        {
            return false;
        }
        map = p.getSelectedFilterMap();
        map1 = D.getSelectedFilterMap();
        if (map.size() == map1.size()) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        if (!flag)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L2:
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
        {
            String s2 = (String)iterator.next();
            if (!((ArrayList)map.get(s2)).equals(map1.get(s2)))
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
        }

        flag = true;
        if (true) goto _L4; else goto _L3
_L3:
        BrowseParam browseparam = (BrowseParam)p.getParam();
        if (p.getStoreID() != null && !browseparam.getStoreId().equals(p.getStoreID()))
        {
            return true;
        }
        if (c)
        {
            if (G != H)
            {
                return true;
            }
            if (G != 1 && E != null)
            {
                String s1 = ((EditText)E.findViewById(0x7f0a014b)).getText().toString();
                F = s1;
                if (s1 != null && !M.equals(s1) && StringUtils.isValidIndianPin(s1))
                {
                    return true;
                }
            }
        }
        return false;
    }

    static int i(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.G;
    }

    private void i()
    {
        C = new FkLoadingDialog(activity);
        if (C != null)
        {
            C.showDlg("", "Loading...", null, true);
            B = true;
        }
    }

    static View j(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.E;
    }

    static String k(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.F;
    }

    static HomeFragmentHolderActivity l(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.K;
    }

    static void m(AllFiltersFragment allfiltersfragment)
    {
        TrackingHelper.setClearFilterEvent();
    }

    static Map n(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.m;
    }

    static boolean o(AllFiltersFragment allfiltersfragment)
    {
        for (Iterator iterator = allfiltersfragment.D.getSelectedFilterMap().keySet().iterator(); iterator.hasNext();)
        {
            Object obj = iterator.next();
            if (((ArrayList)allfiltersfragment.D.getSelectedFilterMap().get(obj)).size() > 0)
            {
                return false;
            }
        }

        return true;
    }

    static FilterPagePreCallBackCache p(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.D;
    }

    static LinearLayout q(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.w;
    }

    static ExpandableListView r(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.x;
    }

    static void s(AllFiltersFragment allfiltersfragment)
    {
        allfiltersfragment.i();
    }

    static FkProductListContext t(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.p;
    }

    static SearchVDataHander u(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.l;
    }

    static void v(AllFiltersFragment allfiltersfragment)
    {
        allfiltersfragment.g();
    }

    static View w(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.Q;
    }

    static int x(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.I;
    }

    static String y(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.J;
    }

    static Context z(AllFiltersFragment allfiltersfragment)
    {
        return allfiltersfragment.A;
    }

    public void applyAndExit()
    {
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("filter by ")).append(e).append(":").append(i.toString()).toString());
        if (e != null)
        {
            if (e.contains("in_more"))
            {
                e = e.split("/")[1];
            }
            if (!e.equals("onclick_more") && !e.equals("pincode"))
            {
                D.getSelectedFilterMap().put(e, i);
            }
            p.clearFilterMaps();
            p.setFilterMap(D.getFilterMap());
            p.setSelectedFilterMap(D.getSelectedFilterMap());
            m.clear();
            if (c && G == 0)
            {
                FlipkartPreferenceManager.instance().saveUserPinCode(F);
                TrackingHelper.sendPincodeCheck(F);
                p.setPincode(F);
            } else
            {
                p.setPincode("");
            }
            p.setShowPin(false);
        }
        if (FlipkartPreferenceManager.instance().isPoppingRefineByFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingRefineByFragment(Boolean.valueOf(false));
        }
        FlipkartPreferenceManager.instance().saveIsPoppingAllRefineFragment(Boolean.valueOf(true));
        c();
        popFragmentStack();
    }

    public void createDialog()
    {
        View view = ((LayoutInflater)A.getSystemService("layout_inflater")).inflate(0x7f03003d, null);
        TextView textview = (TextView)view.findViewById(0x7f0a0053);
        textview.setVisibility(0);
        textview.setText("Exit Filter ?");
        ((TextView)view.findViewById(0x7f0a00cc)).setText("Your Filter selections will go away");
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(K);
        builder.setView(view);
        builder.setCancelable(true);
        builder.setPositiveButton("APPLY & EXIT", new g(this));
        builder.setNegativeButton("EXIT ANYWAY", new h(this));
        AlertDialog alertdialog = builder.create();
        alertdialog.setCanceledOnTouchOutside(true);
        alertdialog.show();
        Button button = alertdialog.getButton(-1);
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 11)
        {
            button.setBackgroundResource(0x7f020076);
            button.setTextColor(activity.getResources().getColor(0x7f090070));
        }
    }

    public boolean handleBackPress()
    {
        CrashLoggerUtils.pushAndUpdate("prssing back in AllFiltersFragment");
        if (FlipkartPreferenceManager.instance().isPoppingRefineByFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingRefineByFragment(Boolean.valueOf(false));
            FlipkartPreferenceManager.instance().saveIsPoppingAllRefineFragment(Boolean.valueOf(true));
        }
        if (e != null && !e.equals("onclick_more") && !e.equals("pincode"))
        {
            if (e.contains("in_more"))
            {
                e = e.split("/")[1];
            }
            if (D != null)
            {
                D.getSelectedFilterMap().put(e, i);
            }
        }
        if (!B && h())
        {
            createDialog();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean handleOnClick()
    {
        return false;
    }

    protected void initActionBar()
    {
        K.getSupportActionBar();
        View view = ActionBarView.setActionBarCustomView(activity, ActionBarState.FiltersPage);
        ((CustomRobotoMediumTextView)view.findViewById(0x7f0a00ea)).setOnClickListener(new l(this));
        CustomRobotoLightTextView customrobotolighttextview = (CustomRobotoLightTextView)view.findViewById(0x7f0a00eb);
        if (customrobotolighttextview != null)
        {
            customrobotolighttextview.setText((new StringBuilder()).append(p.getTotalProductCount()).append(" products").toString());
        }
    }

    public void onBackCaretClick()
    {
        if (FlipkartPreferenceManager.instance().isPoppingRefineByFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingRefineByFragment(Boolean.valueOf(false));
            FlipkartPreferenceManager.instance().saveIsPoppingAllRefineFragment(Boolean.valueOf(true));
        }
        if (e != null && !e.equals("onclick_more") && !e.equals("pincode"))
        {
            if (e.contains("in_more"))
            {
                e = e.split("/")[1];
            }
            D.getSelectedFilterMap().put(e, i);
        }
        if (!B && h())
        {
            createDialog();
        } else
        if ((HomeFragmentHolderActivity)getActivity() != null)
        {
            TrackingHelper.sendUpCarrotClicked();
            ((HomeFragmentHolderActivity)getActivity()).onBackPressed();
            return;
        }
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        super.onCreateView(layoutinflater, viewgroup, bundle);
        t = layoutinflater;
        K = (HomeFragmentHolderActivity)activity;
        K.lockDrawer();
        a = null;
        Bundle bundle1 = getArguments();
        if (bundle1 != null)
        {
            String s1 = bundle1.getString("ALL_FILTERS_FRAG");
            N = bundle1.getBoolean(FiltersListFragment.IS_FROM_OFFERS);
            d = (RefineByCategoryResponse)ContextCache.getInstance().getResponse(s1);
            if (d != null)
            {
                n = d.getStoreList();
                o = d.getParentStoreList();
                p = d.getFkContext();
                c = p.isShowPin();
                D = new FilterPagePreCallBackCache(p);
                analyticData.setRequestId(bundle1.getString("REQUEST_ID"));
            }
        }
        l = new f(this);
        A = getActivity().getApplicationContext();
        L = FlipkartApplication.getImageLoader();
        if (p == null)
        {
            ((HomeFragmentHolderActivity)activity).loadHomeFragmentNotImmediate();
            return new View(A);
        }
        initActionBar();
        s = (RelativeLayout)t.inflate(0x7f03001f, null);
        g = (CustomRobotoMediumTextView)s.findViewById(0x7f0a007b);
        h = (CustomRobotoMediumTextView)s.findViewById(0x7f0a007a);
        u = (ListView)s.findViewById(0x7f0a0078);
        v = (EditText)s.findViewById(0x7f0a0077);
        w = (LinearLayout)s.findViewById(0x7f0a0076);
        x = (ExpandableListView)s.findViewById(0x7f0a0075);
        z = 0;
        Iterator iterator = D.getSelectedFilterMap().keySet().iterator();
        if (iterator.hasNext())
        {
            e = (String)iterator.next();
        }
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.FilterPage);
        if (p != null && p.getCurrPageVertical() != null)
        {
            TrackingHelper.sendFilterPage((new StringBuilder("Store Filters:")).append(p.getCurrPageVertical()).toString());
        }
        v.setOnTouchListener(new a(this));
        v.addTextChangedListener(new i(this));
        v.setOnEditorActionListener(new j(this));
        c(true);
        if (!c || N)
        {
            c(e);
        } else
        {
            a(t.c);
            y = (LinearLayout)((LinearLayout)s.findViewById(0x7f0a0074)).findViewWithTag("onclick_pincode");
            y.setBackgroundColor(Color.parseColor("#f6f4ee"));
            ((TextView)y.findViewById(0x7f0a00df)).setTextColor(0xff000000);
            e = "pincode";
        }
        u.setOnItemClickListener(new k(this));
        h.setOnClickListener(P);
        g.setOnClickListener(P);
        return s;
    }

    public void onDestroy()
    {
        super.onDestroy();
    }

    public void onDestroyView()
    {
        if (l != null)
        {
            l.cancelRequests();
            l = null;
        }
        InputMethodManager inputmethodmanager = (InputMethodManager)activity.getSystemService("input_method");
        if (v != null)
        {
            inputmethodmanager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        analyticData.setPageTypeUtils(PageTypeUtils.FilterPage);
        ((HomeFragmentHolderActivity)activity).unlockDrawer();
        K.showMenu();
        K.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        s = null;
        n = null;
        o = null;
        A = null;
        E = null;
        super.onDestroyView();
    }

    public void subStoreCalled(boolean flag, DiscoveryResponse discoveryresponse)
    {
        if (flag)
        {
            LinearLayout linearlayout = (LinearLayout)s.findViewById(0x7f0a0074);
            linearlayout.removeViews(1, -1 + linearlayout.getChildCount());
            return;
        }
        m.clear();
        k.clear();
        j.clear();
        c = discoveryresponse.getShowPin().isShowPinWidget();
        D.setSortOptions(discoveryresponse.getSearchResponse().getSortOptions());
        D.clearFilterMaps();
        D.setFilterMaps(discoveryresponse.getSearchResponse().getFacetResponseList());
        D.setShowPin(c);
        if (r != null)
        {
            r.setFacetsString(d());
        }
        ArrayList arraylist = discoveryresponse.getSearchResponse().getStoreMetaInfoList();
        int i1 = 0;
        int j1 = 0;
        for (; i1 < arraylist.size(); i1++)
        {
            if (((StoreMetaInfo)arraylist.get(i1)).getId().equals(p.getStoreID()))
            {
                j1 = ((StoreMetaInfo)arraylist.get(i1)).getTotalProduct();
            }
        }

        n = discoveryresponse.getSearchResponse().getStoreMetaInfoList();
        o = discoveryresponse.getSearchResponse().getParentMetaInfoList();
        if (j1 == 0)
        {
            j1 = discoveryresponse.getSearchResponse().getMetadata().getTotalProduct();
        }
        a(j1);
        q = discoveryresponse.getSearchResponse().getMetadata().getTitle();
        c(false);
    }

    static 
    {
        (new StringBuilder("ASIMO.")).append(com/flipkart/android/fragments/AllFiltersFragment.getSimpleName());
    }
}
