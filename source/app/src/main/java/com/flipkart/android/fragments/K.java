// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.flipkart.android.fragments.model.InAppNotificationModel;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.utils.InAppNotificationItemBuilder;
import com.flipkart.android.utils.animation.FkAnimationUtils;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            InAppNotificationFragment

final class K extends BaseAdapter
{

    private ArrayList a;
    private boolean b;
    private String c;
    private InAppNotificationFragment d;

    public K(InAppNotificationFragment inappnotificationfragment)
    {
        d = inappnotificationfragment;
        super();
        a = new ArrayList();
        b = false;
        c = "";
    }

    static void a(K k, ArrayList arraylist)
    {
        if (k.a == null)
        {
            k.a = new ArrayList();
        }
        for (int i = 0; i < arraylist.size(); i++)
        {
            k.a.remove(arraylist.get(i));
            k.a.add(arraylist.get(i));
        }

    }

    public final int getCount()
    {
        return (int)Math.ceil(1 + a.size());
    }

    public final Object getItem(int i)
    {
        String s;
        try
        {
            s = (String)a.get(i);
        }
        catch (Exception exception)
        {
            return "";
        }
        return s;
    }

    public final long getItemId(int i)
    {
        return (long)i;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        Object obj;
        if (i == -1 + getCount())
        {
            obj = InAppNotificationItemBuilder.buildRefreshingListitem(view, InAppNotificationFragment.l(d), d, d.activity, d.isRefreshing);
        } else
        {
            if (i >= getCount())
            {
                return new View(FlipkartApplication.getAppContext());
            }
            String s = (String)a.get(i);
            obj = (ViewGroup)InAppNotificationItemBuilder.buildInAppNotificationItem((InAppNotificationModel)InAppNotificationFragment.d(d).get(s), view, InAppNotificationFragment.l(d), d, d.activity);
            if (obj == null)
            {
                return new View(FlipkartApplication.getAppContext());
            }
            if (b && c.equals(s))
            {
                FkAnimationUtils.performRightToLeftSwipeAnim(((View) (obj)), d.activity);
                b = false;
                return ((View) (obj));
            }
        }
        return ((View) (obj));
    }

    public final void insert(int i, String s)
    {
        a.add(i, s);
        c = s;
        notifyDataSetChanged(true);
    }

    public final void notifyDataSetChanged(boolean flag)
    {
        b = flag;
        super.notifyDataSetChanged();
    }

    public final void remove(int i)
    {
        try
        {
            a.remove(i);
            notifyDataSetChanged(false);
            return;
        }
        catch (Exception exception)
        {
            CrashLoggerUtils.pushAndUpdate("indexOutOfBound in remove in InAppNotiFrag");
        }
    }
}
