// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.view.View;
import android.widget.ExpandableListView;
import com.flipkart.android.analytics.ProductFindingMethod;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.ExpandableValue;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            CollapsedExpandableWidget

class this._cls0
    implements android.widget.ickListener
{

    final CollapsedExpandableWidget this$0;

    public boolean onChildClick(ExpandableListView expandablelistview, View view, int i, int j, long l)
    {
        ExpandableValue expandablevalue = (ExpandableValue)((WidgetItem)items.get(i)).getValue();
        if (expandablevalue.getChildViewMoreCount() > 0 && j == -1 + adapter.getChildrenCount(i))
        {
            expandablevalue.setChildViewMoreCount(0);
            adapter.notifyDataSetChanged();
            return false;
        }
        if (CollapsedExpandableWidget.access$500(CollapsedExpandableWidget.this) == 1)
        {
            TrackingHelper.setProductFindingMethod(ProductFindingMethod.Flyout.name());
        }
        onClickListener.(view);
        return false;
    }

    pandableCatAdapter()
    {
        this$0 = CollapsedExpandableWidget.this;
        super();
    }
}
