// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.flipkart.android.activity.FilterActivity;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.FacetData;
import com.flipkart.android.utils.FilterResponse;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.StringUtils;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.fragments:
//            bz, bv, bw, bx, 
//            by

public class SubFilterFragment extends Fragment
    implements TraceFieldInterface
{

    FkProductListContext a;
    private FilterActivity b;
    private bz c;
    private ListView d;
    private String e;
    private Button f;
    private Button g;
    private Button h;
    private ArrayList i;
    private ArrayList j;
    private ArrayList k;
    private boolean l;

    public SubFilterFragment()
    {
        b = null;
        e = null;
        i = new ArrayList();
        j = new ArrayList();
        k = new ArrayList();
        a = null;
        l = false;
    }

    static ArrayList a(SubFilterFragment subfilterfragment)
    {
        return subfilterfragment.j;
    }

    static ArrayList b(SubFilterFragment subfilterfragment)
    {
        return subfilterfragment.i;
    }

    static bz c(SubFilterFragment subfilterfragment)
    {
        return subfilterfragment.c;
    }

    static String d(SubFilterFragment subfilterfragment)
    {
        return subfilterfragment.e;
    }

    static boolean e(SubFilterFragment subfilterfragment)
    {
        return subfilterfragment.l;
    }

    static FilterActivity f(SubFilterFragment subfilterfragment)
    {
        return subfilterfragment.b;
    }

    static ArrayList g(SubFilterFragment subfilterfragment)
    {
        return subfilterfragment.k;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TraceMachine.enterMethod(_nr_trace, "SubFilterFragment#onCreateView", null);
_L2:
        super.onCreateView(layoutinflater, viewgroup, bundle);
        b = (FilterActivity)getActivity();
        if (b == null)
        {
            View view = new View(FlipkartApplication.getAppContext());
            TraceMachine.exitMethod();
            return view;
        }
        break; /* Loop/switch isn't completed */
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "SubFilterFragment#onCreateView", null);
        if (true) goto _L2; else goto _L1
_L1:
        i.clear();
        FilterResponse filterresponse = b.getFilterResponse();
        if (filterresponse != null)
        {
            e = filterresponse.getFilterKey();
            a = filterresponse.getFkContext();
            l = filterresponse.isSaveCheckedItemInFkContext();
        }
        if (a == null || StringUtils.isNullOrEmpty(e) || a.getFilterMap().get(e) == null)
        {
            b.finish();
            View view1 = new View(b);
            TraceMachine.exitMethod();
            return view1;
        }
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.FilterPage);
        View view2 = layoutinflater.inflate(0x7f0300c0, viewgroup, false);
        d = (ListView)view2.findViewById(0x7f0a0234);
        f = (Button)view2.findViewById(0x7f0a0236);
        g = (Button)view2.findViewById(0x7f0a0235);
        h = (Button)view2.findViewById(0x7f0a0233);
        ((TextView)view2.findViewById(0x7f0a0053)).setText((new StringBuilder("Filter By - ")).append(e).toString());
        if (a.getSelectedFilterMap() != null && a.getSelectedFilterMap().containsKey(e))
        {
            i.addAll((Collection)a.getSelectedFilterMap().get(e));
        }
        Map map = (Map)a.getFilterMap().get(e);
        Iterator iterator = map.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s1 = (String)iterator.next();
            if (((FacetData)map.get(s1)).getCount() == 0 && !i.contains(s1))
            {
                k.add(s1);
            }
            if (!e.toLowerCase().equals("brand"))
            {
                j.add(s1);
            }
        } while (true);
        if (e.toLowerCase().equals("brand"))
        {
            j.addAll(i);
            Iterator iterator1 = map.keySet().iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                String s = (String)iterator1.next();
                if (!i.contains(s) && !k.contains(s))
                {
                    j.add(s);
                }
            } while (true);
            j.addAll(k);
        }
        c = new bz(this, b, 0x7f0300bf, j);
        d.setAdapter(c);
        d.setOnItemClickListener(new bv(this));
        h.setOnClickListener(new bw(this));
        f.setOnClickListener(new bx(this));
        g.setOnClickListener(new by(this));
        TraceMachine.exitMethod();
        return view2;
    }

    public void onDestroy()
    {
        super.onDestroy();
        b = null;
        c = null;
        d = null;
        e = null;
        f = null;
        g = null;
        h = null;
        i = null;
        j = null;
        k = null;
        a = null;
    }

    public void onResume()
    {
        super.onResume();
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop()
    {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }
}
