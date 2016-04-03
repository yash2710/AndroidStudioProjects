// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.flipkart.android.fragments:
//            AllFiltersFragment, q

final class i
    implements TextWatcher
{

    private AllFiltersFragment a;

    i(AllFiltersFragment allfiltersfragment)
    {
        a = allfiltersfragment;
        super();
    }

    public final void afterTextChanged(Editable editable)
    {
    }

    public final void beforeTextChanged(CharSequence charsequence, int j, int k, int l)
    {
    }

    public final void onTextChanged(CharSequence charsequence, int j, int k, int l)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = AllFiltersFragment.b(a).iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)iterator.next();
            if (s.toLowerCase().startsWith(AllFiltersFragment.a(a).getText().toString().toLowerCase()))
            {
                arraylist.add(s);
            }
        } while (true);
        if (a.a != null)
        {
            a.a.setFinalString(arraylist);
            a.a.notifyDataSetChanged();
            AllFiltersFragment.c(a).invalidateViews();
        }
    }
}
