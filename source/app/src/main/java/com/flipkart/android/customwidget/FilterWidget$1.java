// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.view.View;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.utils.FacetData;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.customwidget:
//            FilterWidget, WidgetAction

class val.activity
    implements android.view.tener
{

    final FilterWidget this$0;
    final Activity val$activity;

    public void onClick(View view)
    {
        if (FilterWidget.access$000(FilterWidget.this) != null)
        {
            Action action = FilterWidget.access$000(FilterWidget.this);
            Map map = action.getParams();
            StringBuilder stringbuilder = new StringBuilder();
            Map map1 = FilterWidget.access$100(FilterWidget.this).getSelectedFilterMap();
            if (map1 != null)
            {
                Iterator iterator = map1.keySet().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    String s1 = (String)iterator.next();
                    ArrayList arraylist = (ArrayList)map1.get(s1);
                    if (arraylist != null)
                    {
                        int i = 0;
                        while (i < arraylist.size()) 
                        {
                            String s2 = ((FacetData)((Map)FilterWidget.access$100(FilterWidget.this).getFilterMap().get(s1)).get(arraylist.get(i))).getParams();
                            if (!StringUtils.isNullOrEmpty(s2))
                            {
                                stringbuilder.append(s2);
                                stringbuilder.append("&");
                            }
                            i++;
                        }
                    }
                } while (true);
            }
            String s;
            if (stringbuilder.length() > 0)
            {
                stringbuilder.setLength(-1 + stringbuilder.length());
                s = stringbuilder.toString();
            } else
            {
                s = "";
            }
            map.put("filter", s);
            action.setParams(map);
            WidgetAction.performAction(action, val$activity, PageTypeUtils.None);
        }
    }

    omvalues.Action()
    {
        this$0 = final_filterwidget;
        val$activity = Activity.this;
        super();
    }
}
