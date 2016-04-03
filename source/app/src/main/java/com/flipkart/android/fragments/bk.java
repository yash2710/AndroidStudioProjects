// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.ImageView;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.utils.FkProductListContext;

// Referenced classes of package com.flipkart.android.fragments:
//            SearchListFragment

final class bk
    implements android.view.View.OnClickListener
{

    private BrowseParam a;
    private SearchListFragment b;

    bk(SearchListFragment searchlistfragment, BrowseParam browseparam)
    {
        b = searchlistfragment;
        a = browseparam;
        super();
    }

    public final void onClick(View view)
    {
        b.augmentedSearchCloseButton.setVisibility(8);
        b.fkContext.clearSpellSuggestionList();
        b.fkContext.clearAugmentedQueriesList();
        a.setTitle(null);
        b.setBrowseParamTitle(a, true);
        b.updateListHeight();
    }
}
