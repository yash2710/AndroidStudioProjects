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
import com.flipkart.android.utils.MiscScreenUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            CollapsedExpandableWidget

class this._cls0
    implements android.widget.ickListener
{

    final CollapsedExpandableWidget this$0;

    public boolean onGroupClick(ExpandableListView expandablelistview, View view, int i, long l)
    {
        if (CollapsedExpandableWidget.access$700(CollapsedExpandableWidget.this) > 0 && i == -1 + adapter.getGroupCount())
        {
            CollapsedExpandableWidget.access$702(CollapsedExpandableWidget.this, 0);
            adapter.notifyDataSetChanged();
            if (CollapsedExpandableWidget.access$500(CollapsedExpandableWidget.this) == 1)
            {
                MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, true);
                return true;
            } else
            {
                TrackingHelper.sendViewMoreOnClpClicked();
                MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, false);
                return true;
            }
        }
        if (CollapsedExpandableWidget.access$800(CollapsedExpandableWidget.this) && i == items.size()) goto _L2; else goto _L1
_L1:
        if (!((ExpandableValue)((WidgetItem)items.get(i)).getValue()).isExpandable()) goto _L4; else goto _L3
_L3:
        ExpandableValue expandablevalue = (ExpandableValue)((WidgetItem)items.get(i)).getValue();
        boolean flag;
        if (!((ExpandableValue)((WidgetItem)items.get(i)).getValue()).isAutoExpand())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        expandablevalue.setAutoExpand(flag);
_L6:
        return false;
_L4:
        if (CollapsedExpandableWidget.access$500(CollapsedExpandableWidget.this) == 1)
        {
            TrackingHelper.setProductFindingMethod(ProductFindingMethod.Flyout.name());
        }
_L2:
        onClickListener.(view);
        if (true) goto _L6; else goto _L5
_L5:
    }

    ndableValue()
    {
        this$0 = CollapsedExpandableWidget.this;
        super();
    }
}
