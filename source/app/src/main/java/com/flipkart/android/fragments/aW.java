// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.text.Editable;
import android.text.TextWatcher;
import com.flipkart.android.datahandler.AutoSuggestVDataHandler;
import com.flipkart.android.utils.AppConfigUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            SearchFragment, aX

final class aW
    implements TextWatcher
{

    final SearchFragment a;

    aW(SearchFragment searchfragment)
    {
        a = searchfragment;
        super();
    }

    public final void afterTextChanged(Editable editable)
    {
    }

    public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        SearchFragment.c(a);
        if (charsequence.length() > 0)
        {
            if (charsequence.length() < AppConfigUtils.getInstance().getMaxAutoSuggestLength())
            {
                aX ax = new aX(this);
                ax.doAutoSuggest(charsequence.toString(), false);
                SearchFragment.d(a).add(ax);
            }
            SearchFragment.e(a);
            return;
        } else
        {
            SearchFragment.f(a);
            return;
        }
    }
}
