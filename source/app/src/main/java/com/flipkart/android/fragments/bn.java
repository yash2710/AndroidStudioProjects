// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.utils.FkProductListContext;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            bp, SearchListFragment

final class bn extends ClickableSpan
{

    private ArrayList a;
    private bp b;
    private BrowseParam c;
    private SearchListFragment d;

    bn(SearchListFragment searchlistfragment, ArrayList arraylist, bp bp1, BrowseParam browseparam)
    {
        d = searchlistfragment;
        a = arraylist;
        b = bp1;
        c = browseparam;
        super();
    }

    public final void onClick(View view)
    {
        if (a.contains(b.getQuery()))
        {
            TrackingHelper.sendAugmentedSearchClicked();
        }
        d.augmentedSearchCloseButton.setVisibility(8);
        c.setEnableAugmentSearch(false);
        d.fkContext.clearAugmentedQueriesList();
        d.fkContext.clearSpellSuggestionList();
        d.pushAndChangeContext(c.getPincode(), c.getStoreId(), null, null, null, b.getQuery());
    }
}
