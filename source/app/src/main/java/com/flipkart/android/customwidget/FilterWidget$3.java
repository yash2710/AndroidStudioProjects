// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.utils.FkProductListContext;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            FilterWidget

class this._cls0
    implements android.view.tener
{

    final FilterWidget this$0;

    public void onClick(View view)
    {
        String s = (String)view.getTag();
        String as[];
        if (!s.equals("zeroCount"))
        {
            if ((as = s.split("/")).length > 1)
            {
                String s1 = as[0];
                String s2 = as[1];
                TextView textview = (TextView)view;
                if (((ArrayList)FilterWidget.access$100(FilterWidget.this).getSelectedFilterMap().get(s1)).contains(s2))
                {
                    ((ArrayList)FilterWidget.access$100(FilterWidget.this).getSelectedFilterMap().get(s1)).remove(s2);
                    textview.setCompoundDrawablesWithIntrinsicBounds(0x7f02008d, 0, 0, 0);
                } else
                {
                    ((ArrayList)FilterWidget.access$100(FilterWidget.this).getSelectedFilterMap().get(s1)).add(s2);
                    textview.setCompoundDrawablesWithIntrinsicBounds(0x7f02008c, 0, 0, 0);
                }
                FilterWidget.access$300(FilterWidget.this, (LinearLayout)FilterWidget.access$200(FilterWidget.this).get(s1));
                return;
            }
        }
    }

    ()
    {
        this$0 = FilterWidget.this;
        super();
    }
}
