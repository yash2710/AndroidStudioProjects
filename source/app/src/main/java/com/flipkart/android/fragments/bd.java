// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.utils.AutoSuggestType;
import com.flipkart.android.utils.AutoSuggestWord;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// Referenced classes of package com.flipkart.android.fragments:
//            bh, be, bf, SearchFragment, 
//            bg

final class bd extends ArrayAdapter
    implements Filterable
{

    final SearchFragment a;
    private bh b;
    private ArrayList c;
    private final Object d = new Object();
    private int e;
    private int f;
    private boolean g;
    private Context h;
    private ArrayList i;
    private bg j;
    private LayoutInflater k;

    public bd(SearchFragment searchfragment, Context context, int l, ArrayList arraylist)
    {
        a = searchfragment;
        super(context, l, arraylist);
        g = true;
        ArrayList arraylist1 = new ArrayList(arraylist);
        h = context;
        k = (LayoutInflater)context.getSystemService("layout_inflater");
        f = l;
        e = l;
        c = arraylist1;
    }

    private View a(int l, View view, ViewGroup viewgroup, int i1)
    {
        AutoSuggestType autosuggesttype;
        LinearLayout linearlayout1;
        AutoSuggestWord autosuggestword;
        if (view == null)
        {
            linearlayout1 = (LinearLayout)k.inflate(i1, viewgroup, false);
            b = new bh(this, (byte)0);
            b.a = (TextView)linearlayout1.findViewById(0x7f0a00b6);
            b.b = (ImageView)linearlayout1.findViewById(0x7f0a00b7);
            linearlayout1.setTag(b);
        } else
        {
            LinearLayout linearlayout = (LinearLayout)view;
            b = (bh)linearlayout.getTag();
            linearlayout1 = linearlayout;
        }
        autosuggestword = getItem(l);
        if (autosuggestword == null)
        {
            return new View(linearlayout1.getContext());
        }
        autosuggesttype = autosuggestword.getAutoSuggestType();
        b.b.setVisibility(0);
        b.a.setClickable(true);
        String s = autosuggestword.getWord();
        String s1 = autosuggestword.getStoreTitle();
        if (!StringUtils.isNullOrEmpty(s1))
        {
            s = (new StringBuilder()).append(s).append(" in <b>").append(s1).append("<b>").toString();
        }
        b.a.setText(Html.fromHtml(s));
        if (autosuggestword.getIsStore())
        {
            b.b.setVisibility(8);
        } else
        {
            b.b.setVisibility(0);
        }
        if (autosuggesttype != AutoSuggestType.BrowseHistory) goto _L2; else goto _L1
_L1:
        b.a.setCompoundDrawablesWithIntrinsicBounds(0x7f0200fa, 0, 0, 0);
_L4:
        b.b.setOnClickListener(new be(this, autosuggestword));
        b.a.setOnClickListener(new bf(this, autosuggestword));
        return linearlayout1;
_L2:
        if (autosuggesttype == AutoSuggestType.RecentSearch)
        {
            b.a.setCompoundDrawablesWithIntrinsicBounds(0x7f020146, 0, 0, 0);
        } else
        if (autosuggesttype == AutoSuggestType.TopSearches)
        {
            b.a.setCompoundDrawablesWithIntrinsicBounds(0x7f020178, 0, 0, 0);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    static ArrayList a(bd bd1)
    {
        return bd1.i;
    }

    static ArrayList a(bd bd1, ArrayList arraylist)
    {
        bd1.i = arraylist;
        return arraylist;
    }

    static Object b(bd bd1)
    {
        return bd1.d;
    }

    static ArrayList b(bd bd1, ArrayList arraylist)
    {
        bd1.c = arraylist;
        return arraylist;
    }

    static ArrayList c(bd bd1)
    {
        return bd1.c;
    }

    public final void add(AutoSuggestWord autosuggestword)
    {
        Object obj = d;
        obj;
        JVM INSTR monitorenter ;
        if (i == null)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        if (i.size() > SearchFragment.g(a))
        {
            i.remove(40);
        }
        i.add(autosuggestword);
_L2:
        if (g)
        {
            notifyDataSetChanged();
        }
        return;
        if (c.size() > SearchFragment.g(a))
        {
            c.remove(40);
        }
        c.add(autosuggestword);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public final volatile void add(Object obj)
    {
        add((AutoSuggestWord)obj);
    }

    public final void addAll(Collection collection)
    {
        Object obj = d;
        obj;
        JVM INSTR monitorenter ;
        if (i == null)
        {
            break MISSING_BLOCK_LABEL_37;
        }
        i.addAll(collection);
_L2:
        if (g)
        {
            notifyDataSetChanged();
        }
        return;
        c.addAll(collection);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public final transient void addAll(AutoSuggestWord aautosuggestword[])
    {
        Object obj = d;
        obj;
        JVM INSTR monitorenter ;
        if (i == null)
        {
            break MISSING_BLOCK_LABEL_37;
        }
        Collections.addAll(i, aautosuggestword);
_L2:
        if (g)
        {
            notifyDataSetChanged();
        }
        return;
        Collections.addAll(c, aautosuggestword);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public final volatile void addAll(Object aobj[])
    {
        addAll((AutoSuggestWord[])aobj);
    }

    public final void clear()
    {
        Object obj = d;
        obj;
        JVM INSTR monitorenter ;
        if (i == null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        i.clear();
_L2:
        if (g)
        {
            notifyDataSetChanged();
        }
        return;
        c.clear();
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean contains(AutoSuggestWord autosuggestword)
    {
        if (i != null)
        {
            return i.contains(autosuggestword);
        } else
        {
            return c.contains(autosuggestword);
        }
    }

    public final Context getContext()
    {
        return h;
    }

    public final int getCount()
    {
        return c.size();
    }

    public final View getDropDownView(int l, View view, ViewGroup viewgroup)
    {
        return a(l, view, viewgroup, f);
    }

    public final Filter getFilter()
    {
        if (j == null)
        {
            j = new bg(this, (byte)0);
        }
        return j;
    }

    public final AutoSuggestWord getItem(int l)
    {
        if (c.size() > l)
        {
            return (AutoSuggestWord)c.get(l);
        } else
        {
            return null;
        }
    }

    public final volatile Object getItem(int l)
    {
        return getItem(l);
    }

    public final long getItemId(int l)
    {
        return (long)l;
    }

    public final int getPosition(AutoSuggestWord autosuggestword)
    {
        return c.indexOf(autosuggestword);
    }

    public final volatile int getPosition(Object obj)
    {
        return getPosition((AutoSuggestWord)obj);
    }

    public final View getView(int l, View view, ViewGroup viewgroup)
    {
        return a(l, view, viewgroup, e);
    }

    public final void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
        g = true;
    }

    public final void remove(AutoSuggestWord autosuggestword)
    {
        Object obj = d;
        obj;
        JVM INSTR monitorenter ;
        if (i == null)
        {
            break MISSING_BLOCK_LABEL_37;
        }
        i.remove(autosuggestword);
_L2:
        if (g)
        {
            notifyDataSetChanged();
        }
        return;
        c.remove(autosuggestword);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public final volatile void remove(Object obj)
    {
        remove((AutoSuggestWord)obj);
    }

    public final void setDropDownViewResource(int l)
    {
        f = l;
    }

    public final void setNotifyOnChange(boolean flag)
    {
        g = flag;
    }
}
