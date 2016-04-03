// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.datahandler.SearchVDataHander;
import com.flipkart.android.response.discovery.DiscoveryResponse;
import com.flipkart.android.response.discovery.MetaDataResponse;
import com.flipkart.android.response.discovery.SearchResponse;
import com.flipkart.android.response.discovery.StoreMetaInfo;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkProductListContext;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            SubCategoryTreeView, AllFiltersFragment

final class bu extends SearchVDataHander
{

    private SubCategoryTreeView a;

    bu(SubCategoryTreeView subcategorytreeview)
    {
        a = subcategorytreeview;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        if (SubCategoryTreeView.f(a) != null && SubCategoryTreeView.f(a).findViewById(10000) != null)
        {
            SubCategoryTreeView.f(a).removeView(SubCategoryTreeView.f(a).findViewById(10000));
            SubCategoryTreeView.isShowingLoading = false;
        }
        CustomDialog.showErrorMessage(i, j, s, (HomeFragmentHolderActivity)SubCategoryTreeView.a(a));
    }

    public final void resultReceived(DiscoveryResponse discoveryresponse, boolean flag)
    {
        if (SubCategoryTreeView.a(a) != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (SubCategoryTreeView.f(a) != null && SubCategoryTreeView.f(a).findViewById(10000) != null)
        {
            SubCategoryTreeView.f(a).removeView(SubCategoryTreeView.f(a).findViewById(10000));
            SubCategoryTreeView.isShowingLoading = false;
        }
        if (SubCategoryTreeView.d(a) == null)
        {
            ArrayList arraylist = discoveryresponse.getSearchResponse().getStoreMetaInfoList();
            int l = 0;
            int i1 = 0;
            for (; l < arraylist.size(); l++)
            {
                if (((StoreMetaInfo)arraylist.get(l)).getId().equals(SubCategoryTreeView.b(a).getStoreID()))
                {
                    i1 = ((StoreMetaInfo)arraylist.get(l)).getTotalProduct();
                }
            }

            if (i1 == 0)
            {
                i1 = discoveryresponse.getSearchResponse().getMetadata().getTotalProduct();
            }
            SubCategoryTreeView.b(a, i1);
        }
        if (SubCategoryTreeView.d(a) == null)
        {
            break; /* Loop/switch isn't completed */
        }
        ((AllFiltersFragment)SubCategoryTreeView.c(a)).subStoreCalled(false, discoveryresponse);
        if (SubCategoryTreeView.d(a).getTag().toString().contains("view_all")) goto _L1; else goto _L3
_L3:
        if (SubCategoryTreeView.d(a) != null)
        {
            break; /* Loop/switch isn't completed */
        }
        SubCategoryTreeView.b(a, discoveryresponse.getSearchResponse().getStoreMetaInfoList());
        if (SubCategoryTreeView.k(a).size() > 1)
        {
            SubCategoryTreeView.k(a).add(0, SubCategoryTreeView.a(a, discoveryresponse.getSearchResponse()));
        }
        for (int j = 0; j < a.a; j++)
        {
            SubCategoryTreeView.m(a);
            SubCategoryTreeView.f(a).addView(a.getParentStoreView(j));
        }

        int k = 0;
        while (k < SubCategoryTreeView.k(a).size()) 
        {
            SubCategoryTreeView.m(a);
            if (SubCategoryTreeView.k(a).get(k) != null)
            {
                SubCategoryTreeView.f(a).addView(a.getChildStoreView(k, false, null));
            }
            k++;
        }
        if (true) goto _L1; else goto _L4
_L4:
        if (SubCategoryTreeView.k(a).size() != 0 && (SubCategoryTreeView.k(a).size() != 1 || SubCategoryTreeView.k(a).get(0) != null))
        {
            SubCategoryTreeView.c(a, SubCategoryTreeView.k(a));
        }
        SubCategoryTreeView.b(a, discoveryresponse.getSearchResponse().getStoreMetaInfoList());
        SubCategoryTreeView.d(a, discoveryresponse.getSearchResponse().getParentMetaInfoList());
        if (SubCategoryTreeView.k(a).size() == 0 || SubCategoryTreeView.k(a).size() == 1 && SubCategoryTreeView.k(a).get(0) == null)
        {
            SubCategoryTreeView.b(a).setStoreID(discoveryresponse.getSearchResponse().getStoreIdInProductList());
            SubCategoryTreeView.a(a, discoveryresponse.getSearchResponse().getStoreIdInProductList());
            return;
        }
        String as[] = ((String)SubCategoryTreeView.d(a).getTag()).split(",");
        if (((String)SubCategoryTreeView.d(a).getTag()).split(",")[2].contains("child"))
        {
            TextView textview = (TextView)SubCategoryTreeView.d(a).getChildAt(0);
            SubCategoryTreeView.d(a).setBackgroundDrawable(SubCategoryTreeView.a(a).getResources().getDrawable(0x7f020171));
            a.cloneChildToParent(textview);
            SubCategoryTreeView.m(a);
            SubCategoryTreeView.d(a).setTag((new StringBuilder()).append(as[0]).append(",").append(SubCategoryTreeView.n(a)).append(",parent").toString());
            if (SubCategoryTreeView.d(a).getParent() == null)
            {
                SubCategoryTreeView.f(a).addView(SubCategoryTreeView.d(a));
            }
        }
        if (SubCategoryTreeView.k(a).size() > 1)
        {
            SubCategoryTreeView.k(a).add(0, SubCategoryTreeView.a(a, discoveryresponse.getSearchResponse()));
        }
        int i = 0;
        while (i < SubCategoryTreeView.k(a).size()) 
        {
            SubCategoryTreeView.m(a);
            if (SubCategoryTreeView.k(a).get(i) != null)
            {
                SubCategoryTreeView.f(a).addView(a.getChildStoreView(i, false, null));
            }
            i++;
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((DiscoveryResponse)obj, flag);
    }
}
