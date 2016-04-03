// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.response.discovery.StoreMetaInfo;
import com.flipkart.android.utils.FontCache;
import com.flipkart.android.utils.ScreenMathUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            RefineCategoryFragment

final class aT extends BaseExpandableListAdapter
{

    public Map a;
    private RefineCategoryFragment b;

    private aT(RefineCategoryFragment refinecategoryfragment)
    {
        b = refinecategoryfragment;
        super();
        a = new HashMap();
    }

    aT(RefineCategoryFragment refinecategoryfragment, byte byte0)
    {
        this(refinecategoryfragment);
    }

    public final Object getChild(int i, int j)
    {
        return null;
    }

    public final long getChildId(int i, int j)
    {
        return 0L;
    }

    public final View getChildView(int i, int j, boolean flag, View view, ViewGroup viewgroup)
    {
        if (RefineCategoryFragment.a(b).getStoreId() != null && !RefineCategoryFragment.a(b).getStoreId().equalsIgnoreCase("search.flipkart.com") && RefineCategoryFragment.a(b).getStoreId().length() != 0 || RefineCategoryFragment.b(b).size() <= 0) goto _L2; else goto _L1
_L1:
        StoreMetaInfo storemetainfo2 = (StoreMetaInfo)RefineCategoryFragment.b(b).get(i);
        if (j < 4) goto _L4; else goto _L3
_L3:
        if (a.get(storemetainfo2.getId()) != null && ((Boolean)a.get(storemetainfo2.getId())).booleanValue()) goto _L5; else goto _L4
_L4:
        StoreMetaInfo storemetainfo3 = (StoreMetaInfo)storemetainfo2.getChildMetaInfo().get(j);
          goto _L6
_L11:
        StoreMetaInfo storemetainfo;
        int k;
        StoreMetaInfo storemetainfo1;
        LinearLayout linearlayout;
        TextView textview;
        boolean flag1;
        try
        {
            linearlayout = (LinearLayout)LayoutInflater.from(RefineCategoryFragment.f(b)).inflate(0x7f03007a, null);
            linearlayout.setBackgroundColor(RefineCategoryFragment.f(b).getApplicationContext().getResources().getColor(0x7f090045));
            textview = (TextView)linearlayout.findViewById(0x7f0a0134);
            textview.setTypeface(FontCache.getFont("robotolight.ttf"));
            textview.setTextColor(0xff2e2e2e);
            textview.setTextSize(2, 16F);
        }
        catch (Exception exception)
        {
            return new View(RefineCategoryFragment.f(b));
        }
        if (storemetainfo1 != null) goto _L8; else goto _L7
_L7:
        textview.setText("View More");
        textview.setGravity(21);
        textview.setCompoundDrawablesWithIntrinsicBounds(null, null, RefineCategoryFragment.f(b).getResources().getDrawable(0x7f020088), null);
        textview.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(3, RefineCategoryFragment.f(b)));
        return linearlayout;
_L2:
        if (RefineCategoryFragment.a(b).getQuery() != null && RefineCategoryFragment.a(b).getQuery().length() != 0)
        {
            break MISSING_BLOCK_LABEL_418;
        }
        storemetainfo = (StoreMetaInfo)RefineCategoryFragment.c(b).get(i);
_L9:
        if (j < 4)
        {
            break MISSING_BLOCK_LABEL_379;
        }
        if (a.get(storemetainfo.getId()) == null)
        {
            break MISSING_BLOCK_LABEL_379;
        }
        flag1 = ((Boolean)a.get(storemetainfo.getId())).booleanValue();
        storemetainfo1 = null;
        if (flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        k = RefineCategoryFragment.b(b).size();
        storemetainfo1 = null;
        if (k <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        storemetainfo1 = (StoreMetaInfo)RefineCategoryFragment.b(b).get(j);
        continue; /* Loop/switch isn't completed */
        storemetainfo = (StoreMetaInfo)RefineCategoryFragment.c(b).get(i - 1);
          goto _L9
_L8:
        textview.setText((new StringBuilder()).append(storemetainfo1.getTitle()).append(" (").append(storemetainfo1.getTotalProduct()).append(")").toString());
        textview.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        textview.setGravity(19);
        return linearlayout;
_L5:
        storemetainfo3 = null;
_L6:
        storemetainfo1 = storemetainfo3;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public final int getChildrenCount(int i)
    {
        if (RefineCategoryFragment.a(b).getStoreId() == null || RefineCategoryFragment.a(b).getStoreId().equalsIgnoreCase("search.flipkart.com") || RefineCategoryFragment.a(b).getStoreId().length() == 0)
        {
            if (RefineCategoryFragment.b(b) != null)
            {
                StoreMetaInfo storemetainfo = (StoreMetaInfo)RefineCategoryFragment.b(b).get(i);
                ArrayList arraylist = storemetainfo.getChildMetaInfo();
                if (arraylist != null)
                {
                    if (arraylist.size() > 5 && (a.get(storemetainfo.getId()) == null || ((Boolean)a.get(storemetainfo.getId())).booleanValue()))
                    {
                        a.put(storemetainfo.getId(), Boolean.valueOf(true));
                        return 5;
                    } else
                    {
                        return arraylist.size();
                    }
                } else
                {
                    return 0;
                }
            }
        } else
        if (i == -1 + getGroupCount())
        {
            return RefineCategoryFragment.b(b).size();
        }
        return 0;
    }

    public final Object getGroup(int i)
    {
        return null;
    }

    public final int getGroupCount()
    {
        if (RefineCategoryFragment.b(b) != null)
        {
            if (RefineCategoryFragment.a(b).getStoreId() == null || RefineCategoryFragment.a(b).getStoreId().equalsIgnoreCase("search.flipkart.com") || RefineCategoryFragment.a(b).getStoreId().length() == 0)
            {
                return RefineCategoryFragment.b(b).size();
            }
            if (RefineCategoryFragment.a(b).getQuery() == null || RefineCategoryFragment.a(b).getQuery().length() == 0)
            {
                return RefineCategoryFragment.c(b).size();
            } else
            {
                return 1 + RefineCategoryFragment.c(b).size();
            }
        } else
        {
            return 0;
        }
    }

    public final long getGroupId(int i)
    {
        return 0L;
    }

    public final View getGroupView(int i, boolean flag, View view, ViewGroup viewgroup)
    {
        boolean flag1 = true;
        LinearLayout linearlayout = (LinearLayout)LayoutInflater.from(RefineCategoryFragment.f(b)).inflate(0x7f030079, null);
        linearlayout.setVisibility(0);
        TextView textview = (TextView)linearlayout.findViewById(0x7f0a0168);
        android.graphics.Typeface typeface = FontCache.getFont("robotolight.ttf");
        textview.setTypeface(typeface);
        textview.setTextColor(0xff2e2e2e);
        textview.setTextSize(2, 16F);
        int j = ScreenMathUtils.dpToPx(10, RefineCategoryFragment.f(b));
        int k = ScreenMathUtils.dpToPx(15, RefineCategoryFragment.f(b));
        textview.setPadding(j, k, j, k);
        if ((RefineCategoryFragment.a(b).getStoreId() == null || RefineCategoryFragment.a(b).getStoreId().equalsIgnoreCase("search.flipkart.com") || RefineCategoryFragment.a(b).getStoreId().length() == 0) && RefineCategoryFragment.b(b) != null && RefineCategoryFragment.b(b).size() > 0)
        {
            StoreMetaInfo storemetainfo1 = (StoreMetaInfo)RefineCategoryFragment.b(b).get(i);
            if (storemetainfo1 != null)
            {
                textview.setText((new StringBuilder()).append(storemetainfo1.getTitle()).append(" (").append(storemetainfo1.getTotalProduct()).append(")").toString());
                if (getChildrenCount(i) > 0)
                {
                    if (flag)
                    {
                        textview.setCompoundDrawablesWithIntrinsicBounds(null, null, RefineCategoryFragment.f(b).getResources().getDrawable(0x7f020087), null);
                        textview.setTypeface(null, 0);
                    } else
                    {
                        textview.setCompoundDrawablesWithIntrinsicBounds(null, null, RefineCategoryFragment.f(b).getResources().getDrawable(0x7f020088), null);
                        textview.setTypeface(typeface);
                    }
                }
                textview.setGravity(19);
                return linearlayout;
            } else
            {
                linearlayout.setVisibility(8);
                return linearlayout;
            }
        }
        String s = RefineCategoryFragment.a(b).getQuery();
        int l = 0;
        if (s != null)
        {
            int i1 = RefineCategoryFragment.a(b).getQuery().length();
            l = 0;
            StoreMetaInfo storemetainfo;
            if (i1 != 0)
            {
                l = ((flag1) ? 1 : 0);
                flag1 = false;
            }
        }
        if (i == 0 && !flag1)
        {
            textview.setText("ALL CATEGORIES");
        } else
        {
            storemetainfo = (StoreMetaInfo)RefineCategoryFragment.c(b).get(i - l);
            if (storemetainfo != null)
            {
                textview.setText(storemetainfo.getTitle());
            } else
            {
                linearlayout.setVisibility(8);
            }
        }
        if (i == -1 + getGroupCount())
        {
            linearlayout.findViewById(0x7f0a0136).setVisibility(4);
            textview.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        linearlayout.setBackgroundColor(0xff272830);
        return linearlayout;
    }

    public final boolean hasStableIds()
    {
        return false;
    }

    public final boolean isChildSelectable(int i, int j)
    {
        return true;
    }
}
