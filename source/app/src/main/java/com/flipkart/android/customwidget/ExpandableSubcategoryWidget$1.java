// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.ExpandableValue;
import java.util.Comparator;

// Referenced classes of package com.flipkart.android.customwidget:
//            ExpandableSubcategoryWidget

class this._cls0
    implements Comparator
{

    final ExpandableSubcategoryWidget this$0;

    public int compare(WidgetItem widgetitem, WidgetItem widgetitem1)
    {
        ExpandableValue expandablevalue = (ExpandableValue)widgetitem.getValue();
        ExpandableValue expandablevalue1 = (ExpandableValue)widgetitem1.getValue();
        if (!expandablevalue.isShowInViewMore())
        {
            return !expandablevalue1.isShowInViewMore() ? 0 : -1;
        }
        return !expandablevalue1.isShowInViewMore() ? 1 : 0;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((WidgetItem)obj, (WidgetItem)obj1);
    }

    ableValue()
    {
        this$0 = ExpandableSubcategoryWidget.this;
        super();
    }
}
