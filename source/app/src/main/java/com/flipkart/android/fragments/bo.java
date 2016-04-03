// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.ImageView;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.utils.FkProductListContext;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            SearchListFragment

final class bo
    implements android.view.View.OnClickListener
{

    private ArrayList a;
    private BrowseParam b;
    private SearchListFragment c;

    bo(SearchListFragment searchlistfragment, ArrayList arraylist, BrowseParam browseparam)
    {
        c = searchlistfragment;
        a = arraylist;
        b = browseparam;
        super();
    }

    public final void onClick(View view)
    {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < a.size(); i++)
        {
            stringbuilder.append((String)a.get(i));
            if (i < -1 + a.size())
            {
                stringbuilder.append(",");
            }
        }

        c.fkContext.clearAugmentedQueriesList();
        c.fkContext.clearSpellSuggestionList();
        String s = stringbuilder.toString();
        b.setTitle((new StringBuilder("Showing ")).append(c.fkContext.getTotalProductCount()).append(" results for ").append(s).toString());
        c.augmentedSearchCloseButton.setVisibility(8);
        c.updateListHeight();
        c.setBrowseParamTitle(b, true);
    }
}
