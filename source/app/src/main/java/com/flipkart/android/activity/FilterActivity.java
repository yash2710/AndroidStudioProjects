// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import com.facebook.AppEventsLogger;
import com.flipkart.android.activity.base.FlipkartBaseFragmentActivity;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.fragments.AllFiltersFragment;
import com.flipkart.android.fragments.RefineCategoryFragment;
import com.flipkart.android.fragments.SubFilterFragment;
import com.flipkart.android.login.FacebookConstants;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.FilterResponse;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.RefineByCategoryResponse;
import java.util.UUID;

public class FilterActivity extends FlipkartBaseFragmentActivity
{

    private String a;
    private int b;
    private FkProductListContext c;
    private String d;
    private FilterResponse e;
    private RefineByCategoryResponse f;

    public FilterActivity()
    {
        a = null;
        b = 1;
        c = null;
        e = null;
        f = null;
    }

    public FilterResponse getFilterResponse()
    {
        return e;
    }

    public FkProductListContext getFkContext()
    {
        return c;
    }

    public RefineByCategoryResponse getRefineByCatReponse()
    {
        return f;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03001b);
        getWindow().setLayout(-1, -1);
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null)
        {
            b = bundle1.getInt("PRODUCT_LIST_EXTRAS_SCREEN_TYPE");
            String s = bundle1.getString("PRODUCT_PAGE_UUID");
            Intent intent;
            if (b == 1 || b == 4)
            {
                e = (FilterResponse)ContextCache.getInstance().getResponse((new StringBuilder()).append(s).append("_filterResponse").toString());
                if (e != null)
                {
                    a = e.getFilterKey();
                    c = e.getFkContext();
                }
            } else
            {
                f = (RefineByCategoryResponse)ContextCache.getInstance().getResponse((new StringBuilder()).append(s).append("_refineByCategoryResponse").toString());
                if (f != null)
                {
                    c = f.getFkContext();
                }
            }
        }
        if (b == 1)
        {
            openSubFilterPage();
        } else
        if (b == 2)
        {
            openRefineCatPage();
        } else
        if (b == 3)
        {
            openSubCategoryFilterPage();
        } else
        {
            openAllFiltersPage();
        }
        d = UUID.randomUUID().toString();
        if (b == 1 || b == 4)
        {
            intent = new Intent();
            intent.putExtra("filterString", a);
            intent.putExtra("PRODUCT_PAGE_UUID", d);
            setResult(-1, intent);
        }
    }

    public void onResume()
    {
        super.onResume();
        try
        {
            AppEventsLogger.activateApp(getApplicationContext(), FacebookConstants.AppId);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public void openAllFiltersPage()
    {
        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        fragmenttransaction.replace(0x7f0a006c, new AllFiltersFragment());
        fragmenttransaction.commit();
    }

    public void openRefineCatPage()
    {
        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        fragmenttransaction.replace(0x7f0a006c, new RefineCategoryFragment());
        fragmenttransaction.commit();
    }

    public void openSubCategoryFilterPage()
    {
        getSupportFragmentManager().beginTransaction().commit();
    }

    public void openSubFilterPage()
    {
        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        fragmenttransaction.replace(0x7f0a006c, new SubFilterFragment());
        fragmenttransaction.commit();
    }

    public void putContextToCache()
    {
        if (d != null)
        {
            ContextCache.getInstance().putResponse(d, c);
        }
    }

    public void returnResult(String s, String s1)
    {
        TrackingHelper.sendCategoryRefinedOnSearch();
        Intent intent = new Intent();
        intent.putExtra("filterString", s);
        intent.putExtra("storeId", s1);
        setResult(-1, intent);
        finish();
    }

    public void setFilterResponse(FilterResponse filterresponse)
    {
        e = filterresponse;
    }

    public void setFkContext(FkProductListContext fkproductlistcontext)
    {
        c = fkproductlistcontext;
    }

    public void setRefineByCatReponse(RefineByCategoryResponse refinebycategoryresponse)
    {
        f = refinebycategoryresponse;
    }
}
