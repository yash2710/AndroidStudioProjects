// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.customviews.CustomRobotoLightTextView;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.SearchVDataHander;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.response.discovery.MetaDataResponse;
import com.flipkart.android.response.discovery.SearchResponse;
import com.flipkart.android.response.discovery.StoreMetaInfo;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.FontCache;
import com.flipkart.android.utils.RefineByCategoryResponse;
import com.flipkart.android.utils.ScreenMathUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            bs, bt, br, bu

public class SubCategoryTreeView extends LinearLayout
{

    public static boolean isShowingLoading = false;
    int a;
    private Fragment b;
    private ArrayList c;
    private ArrayList d;
    private int e;
    private ArrayList f;
    private FkProductListContext g;
    private SearchVDataHander h;
    private AnalyticData i;
    private int j;
    private Typeface k;
    private int l;
    private int m;
    private int n;
    private LinearLayout o;
    private int p;
    private LayoutInflater q;
    private LinearLayout r;
    private BrowseParam s;
    private Context t;
    private String u;
    private ArrayList v;
    private android.view.View.OnClickListener w;
    private android.view.View.OnClickListener x;

    public SubCategoryTreeView(Context context, AttributeSet attributeset, ArrayList arraylist)
    {
        super(context, attributeset);
        c = null;
        d = null;
        e = -1;
        f = null;
        g = null;
        h = null;
        a = 0;
        j = 0;
        k = null;
        l = 0;
        m = 0;
        n = 0;
        p = -1;
        r = null;
        s = new BrowseParam();
        u = "";
        v = new ArrayList();
        w = new bs(this);
        x = new bt(this);
        t = context;
        o = this;
        setOrientation(1);
        v = arraylist;
    }

    static int a(SubCategoryTreeView subcategorytreeview, int i1)
    {
        subcategorytreeview.p = i1;
        return i1;
    }

    static Context a(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.t;
    }

    static LinearLayout a(SubCategoryTreeView subcategorytreeview, LinearLayout linearlayout)
    {
        subcategorytreeview.r = linearlayout;
        return linearlayout;
    }

    static StoreMetaInfo a(SubCategoryTreeView subcategorytreeview, SearchResponse searchresponse)
    {
        StoreMetaInfo storemetainfo = new StoreMetaInfo();
        storemetainfo.setTotalProduct(searchresponse.getMetadata().getTotalProduct());
        storemetainfo.setTitle("View All ");
        storemetainfo.setId(searchresponse.getStoreIdInProductList());
        return storemetainfo;
    }

    static void a(SubCategoryTreeView subcategorytreeview, String s1)
    {
        if (subcategorytreeview.d != null)
        {
            for (int i1 = 0; i1 < subcategorytreeview.d.size(); i1++)
            {
                subcategorytreeview.p = 1 + subcategorytreeview.p;
                if (subcategorytreeview.d.get(i1) != null)
                {
                    subcategorytreeview.o.addView(subcategorytreeview.getChildStoreView(i1, true, s1));
                }
            }

        }
    }

    static String[] a(SubCategoryTreeView subcategorytreeview, ArrayList arraylist)
    {
        return a(arraylist);
    }

    private static String[] a(ArrayList arraylist)
    {
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    static FkProductListContext b(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.g;
    }

    static ArrayList b(SubCategoryTreeView subcategorytreeview, ArrayList arraylist)
    {
        subcategorytreeview.c = arraylist;
        return arraylist;
    }

    static void b(SubCategoryTreeView subcategorytreeview, int i1)
    {
        View view = ((HomeFragmentHolderActivity)subcategorytreeview.t).getSupportActionBar().getCustomView();
        if (view != null)
        {
            CustomRobotoLightTextView customrobotolighttextview = (CustomRobotoLightTextView)view.findViewById(0x7f0a00eb);
            if (customrobotolighttextview != null)
            {
                customrobotolighttextview.setText((new StringBuilder()).append(i1).append(" products").toString());
            }
        }
    }

    static Fragment c(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.b;
    }

    static ArrayList c(SubCategoryTreeView subcategorytreeview, ArrayList arraylist)
    {
        subcategorytreeview.d = arraylist;
        return arraylist;
    }

    static LinearLayout d(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.r;
    }

    static ArrayList d(SubCategoryTreeView subcategorytreeview, ArrayList arraylist)
    {
        subcategorytreeview.f = arraylist;
        return arraylist;
    }

    static int e(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.e;
    }

    static LinearLayout f(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.o;
    }

    static AnalyticData g(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.i;
    }

    static BrowseParam h(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.s;
    }

    static ArrayList i(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.v;
    }

    static SearchVDataHander j(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.h;
    }

    static ArrayList k(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.c;
    }

    static ArrayList l(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.d;
    }

    static int m(SubCategoryTreeView subcategorytreeview)
    {
        int i1 = subcategorytreeview.p;
        subcategorytreeview.p = i1 + 1;
        return i1;
    }

    static int n(SubCategoryTreeView subcategorytreeview)
    {
        return subcategorytreeview.p;
    }

    public boolean checkIfClickToProcess(int i1)
    {
        LinearLayout linearlayout = (LinearLayout)o.getChildAt(i1);
        if (linearlayout != null)
        {
            TextView textview = (TextView)linearlayout.getChildAt(0);
            if (textview != null && textview.getText().toString().contains("View"))
            {
                return false;
            }
        }
        return true;
    }

    public TextView cloneChildToParent(TextView textview)
    {
        textview.setPadding(l, m, l, m);
        textview.setCompoundDrawablesWithIntrinsicBounds(t.getResources().getDrawable(0x7f0200dd), null, null, null);
        textview.setCompoundDrawablePadding(20);
        textview.setTextColor(0xff000000);
        return textview;
    }

    public View getBorderView(boolean flag)
    {
        p = 1 + p;
        View view = new View(t);
        view.setBackgroundColor(t.getResources().getColor(0x7f09002a));
        view.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(1, t)));
        if (flag)
        {
            view.setBackgroundColor(t.getResources().getColor(0x7f090039));
        }
        return view;
    }

    public View getChildStoreView(int i1, boolean flag, String s1)
    {
        ArrayList arraylist = d;
        ArrayList arraylist1;
        LinearLayout linearlayout;
        TextView textview;
        String s2;
        String s3;
        if (!flag || arraylist == null)
        {
            arraylist1 = c;
        } else
        {
            arraylist1 = arraylist;
        }
        if (arraylist1 == null)
        {
            return new View(t);
        }
        linearlayout = new LinearLayout(t);
        linearlayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
        linearlayout.setOrientation(0);
        textview = new TextView(t);
        if (s1 != null && s1.equals(((StoreMetaInfo)arraylist1.get(i1)).getId()))
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(null, null, t.getResources().getDrawable(0x7f020155), null);
            textview.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(5, t));
            e = p;
            textview.setContentDescription("subcategory_selected");
        } else
        if (u != null && u.equals(((StoreMetaInfo)arraylist1.get(i1)).getId()))
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(null, null, t.getResources().getDrawable(0x7f020155), null);
            textview.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(5, t));
            e = p;
            u = "";
            textview.setContentDescription("subcategory_selected");
        }
        s2 = ((StoreMetaInfo)arraylist1.get(i1)).getTitle();
        if (((StoreMetaInfo)arraylist1.get(i1)).getTotalProduct() != 0)
        {
            s3 = (new StringBuilder()).append(s2).append(" (").append(((StoreMetaInfo)arraylist1.get(i1)).getTotalProduct()).append(")").toString();
        } else
        {
            s3 = s2;
        }
        textview.setText(s3);
        textview.setTextSize(2, 16F);
        textview.setGravity(19);
        textview.setTextColor(0xff2e2e2e);
        textview.setTypeface(k);
        textview.setPadding(n, m, l, m);
        linearlayout.addView(textview);
        if (flag)
        {
            linearlayout.setTag((new StringBuilder()).append(((StoreMetaInfo)arraylist1.get(i1)).getId()).append(",").append(p).append(",cached_child").toString());
        } else
        {
            linearlayout.setTag((new StringBuilder()).append(((StoreMetaInfo)arraylist1.get(i1)).getId()).append(",").append(p).append(",child").toString());
        }
        linearlayout.setBackgroundDrawable(t.getResources().getDrawable(0x7f020172));
        if (((StoreMetaInfo)arraylist1.get(i1)).getTitle().contains("View"))
        {
            linearlayout.setOnClickListener(w);
            linearlayout.setTag((new StringBuilder()).append(((StoreMetaInfo)arraylist1.get(i1)).getId()).append(",").append(p).append(",view_all").toString());
        } else
        {
            linearlayout.setOnClickListener(x);
        }
        if (((StoreMetaInfo)arraylist1.get(i1)).getTotalProduct() == 0 && FlipkartDeviceInfoProvider.getAndroidSDKVersion() > 10)
        {
            linearlayout.setAlpha(0.55F);
            linearlayout.setOnClickListener(new br(this));
        }
        return linearlayout;
    }

    public ArrayList getFacetsString()
    {
        return v;
    }

    public void getOfferFilter()
    {
        s = (BrowseParam)g.getParam();
    }

    public View getParentStoreView(int i1)
    {
        LinearLayout linearlayout = new LinearLayout(t);
        linearlayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
        linearlayout.setOrientation(0);
        TextView textview = new TextView(t);
        textview.setGravity(19);
        textview.setText(((StoreMetaInfo)f.get(i1)).getTitle());
        if (i1 != -1 + a || c.size() == 0)
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(t.getResources().getDrawable(0x7f02005e), null, null, null);
            textview.setTextColor(0xff2e2e2e);
        } else
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(t.getResources().getDrawable(0x7f0200dd), null, null, null);
            textview.setTextColor(0xff000000);
        }
        textview.setCompoundDrawablePadding(20);
        textview.setTypeface(k);
        textview.setTextSize(2, 16F);
        textview.setPadding(l, m, l, m);
        linearlayout.addView(textview);
        linearlayout.setTag((new StringBuilder()).append(((StoreMetaInfo)f.get(i1)).getId()).append(",").append(p).append(",parent").toString());
        linearlayout.setOnClickListener(x);
        linearlayout.setBackgroundDrawable(t.getResources().getDrawable(0x7f020171));
        return linearlayout;
    }

    public void init(LayoutInflater layoutinflater, RefineByCategoryResponse refinebycategoryresponse)
    {
        q = layoutinflater;
        if (refinebycategoryresponse != null)
        {
            c = refinebycategoryresponse.getStoreList();
            f = refinebycategoryresponse.getParentStoreList();
            g = refinebycategoryresponse.getFkContext();
            h = new bu(this);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
            layoutparams.setMargins(0, ScreenMathUtils.dpToPx(10, t), 0, 0);
            o.setLayoutParams(layoutparams);
            l = ScreenMathUtils.dpToPx(10, t);
            m = ScreenMathUtils.dpToPx(15, t);
            n = ScreenMathUtils.dpToPx(40, t);
            k = FontCache.getFont("robotolight.ttf");
            u = g.getStoreID();
            o.addView(getBorderView(true));
            if (c != null && c.size() > 0 && c.get(0) != null)
            {
                j = c.size();
            }
            getOfferFilter();
            if (f.size() > 0)
            {
                showLoadingPanel();
                int l1;
                if (f.size() == 1)
                {
                    a = f.size();
                    l1 = 0;
                } else
                {
                    int k1 = -2 + f.size();
                    a = -1 + f.size();
                    l1 = k1;
                }
                i = new AnalyticData();
                i.setIsPageFirstLanding(false);
                h.doSearch(null, s.getQuery(), ((StoreMetaInfo)f.get(l1)).getId(), null, a(v), null, s.getTags(), 0, 0, 0, s.isEnableAugmentSearch(), i, s.getViews());
            } else
            {
                a = f.size();
                for (int i1 = 0; i1 < a; i1++)
                {
                    p = 1 + p;
                    o.addView(getParentStoreView(i1));
                }

                int j1 = 0;
                while (j1 < j) 
                {
                    p = 1 + p;
                    if (c.get(j1) != null)
                    {
                        o.addView(getChildStoreView(j1, false, null));
                    }
                    j1++;
                }
            }
        }
    }

    public void setFacetsString(ArrayList arraylist)
    {
        v = arraylist;
    }

    public void setListener(Fragment fragment)
    {
        b = fragment;
    }

    public void showLoadingPanel()
    {
        isShowingLoading = true;
        View view = q.inflate(0x7f03005d, null);
        view.setId(10000);
        o.addView(view);
    }

}
