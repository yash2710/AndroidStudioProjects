// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.widget.Filter;
import com.flipkart.android.utils.AutoSuggestType;
import com.flipkart.android.utils.AutoSuggestWord;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            bd

final class bg extends Filter
{

    private bd a;

    private bg(bd bd1)
    {
        a = bd1;
        super();
    }

    bg(bd bd1, byte byte0)
    {
        this(bd1);
    }

    protected final android.widget.Filter.FilterResults performFiltering(CharSequence charsequence)
    {
        android.widget.Filter.FilterResults filterresults = new android.widget.Filter.FilterResults();
        if (bd.a(a) == null)
        {
            synchronized (bd.b(a))
            {
                bd.a(a, new ArrayList(bd.c(a)));
            }
        }
        if (charsequence == null || charsequence.length() == 0)
        {
            ArrayList arraylist;
            int i;
            ArrayList arraylist1;
            ArrayList arraylist2;
            ArrayList arraylist3;
            int j;
            synchronized (bd.b(a))
            {
                arraylist = new ArrayList(bd.a(a));
            }
            i = arraylist.size();
            arraylist1 = new ArrayList();
            arraylist2 = new ArrayList();
            arraylist3 = new ArrayList();
            j = 0;
            while (j < i) 
            {
                AutoSuggestWord autosuggestword = (AutoSuggestWord)arraylist.get(j);
                if (autosuggestword.getAutoSuggestType() == AutoSuggestType.BrowseHistory)
                {
                    arraylist1.add(autosuggestword);
                } else
                if (autosuggestword.getAutoSuggestType() == AutoSuggestType.TopSearches)
                {
                    arraylist2.add(autosuggestword);
                }
                j++;
            }
            arraylist3.addAll(arraylist1);
            arraylist3.addAll(arraylist2);
            filterresults.values = arraylist3;
            filterresults.count = arraylist3.size();
            return filterresults;
        }
        String s = charsequence.toString().toLowerCase();
        ArrayList arraylist4;
        int k;
        ArrayList arraylist5;
        ArrayList arraylist6;
        ArrayList arraylist7;
        ArrayList arraylist8;
        int l;
        synchronized (bd.b(a))
        {
            arraylist4 = new ArrayList(bd.a(a));
        }
        k = arraylist4.size();
        arraylist5 = new ArrayList();
        arraylist6 = new ArrayList();
        arraylist7 = new ArrayList();
        arraylist8 = new ArrayList();
        l = 0;
        while (l < k) 
        {
            AutoSuggestWord autosuggestword1 = (AutoSuggestWord)arraylist4.get(l);
            if (autosuggestword1.toString().toLowerCase().startsWith(s))
            {
                if (autosuggestword1.getAutoSuggestType() == AutoSuggestType.BrowseHistory)
                {
                    arraylist6.add(autosuggestword1);
                } else
                if (autosuggestword1.getAutoSuggestType() == AutoSuggestType.TopSearches)
                {
                    arraylist7.add(autosuggestword1);
                } else
                if (autosuggestword1.getAutoSuggestType() == AutoSuggestType.RecentSearch)
                {
                    arraylist8.add(autosuggestword1);
                }
            }
            l++;
        }
        if (arraylist8.size() > 0)
        {
            arraylist5.addAll(arraylist8);
        }
        if (arraylist7.size() > 0)
        {
            arraylist5.addAll(arraylist7);
        }
        if (arraylist6.size() > 0)
        {
            arraylist5.addAll(arraylist6);
        }
        filterresults.values = arraylist5;
        filterresults.count = arraylist5.size();
        return filterresults;
    }

    protected final void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
    {
        bd.b(a, (ArrayList)filterresults.values);
        if (filterresults.count > 0)
        {
            a.notifyDataSetChanged();
            return;
        } else
        {
            a.notifyDataSetInvalidated();
            return;
        }
    }
}
