// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.view.View;
import android.widget.ExpandableListView;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.logging.FkLogger;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            CollapsedExpandableWidget, NewTitleWidget

class this._cls0
    implements android.view.edExpandableWidget._cls6
{

    final CollapsedExpandableWidget this$0;

    public void onClick(View view)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        FkLogger.debug("Test ", "Test on click listner");
        Action action;
        String s3;
        String s4;
        CollapsedExpandableWidget collapsedexpandablewidget3;
        String s5;
        if (expandableListView.getVisibility() == 8)
        {
            expandableListView.setVisibility(0);
            flag1 = flag;
        } else
        {
            expandableListView.setVisibility(8);
            flag1 = false;
        }
        FkLogger.debug("Title", (new StringBuilder("Tag value is ")).append(view.getTag()).append(" View is ").append(view).toString());
        if (!(view.getTag() instanceof Action)) goto _L2; else goto _L1
_L1:
        action = (Action)view.getTag();
        if (!action.getScreenType().equalsIgnoreCase("infiniteScroll")) goto _L4; else goto _L3
_L3:
        if (action.getParams() == null) goto _L6; else goto _L5
_L5:
        s3 = (String)action.getParams().get("affectedDataKey");
        s4 = (String)action.getParams().get("dataPath");
        FkLogger.debug("Title", (new StringBuilder("dataKey value is ")).append(s3).append(" baseUrl is ").append(s4).toString());
        CollapsedExpandableWidget.access$1302(CollapsedExpandableWidget.this, (String)action.getParams().get("heading"));
        TrackingHelper.sendSubCategory(CollapsedExpandableWidget.access$1300(CollapsedExpandableWidget.this));
        if (onItemClickListner != null)
        {
            onItemClickListner.onClickedItem(s3, s4);
        }
        collapsedexpandablewidget3 = CollapsedExpandableWidget.this;
        s5 = CollapsedExpandableWidget.access$1300(CollapsedExpandableWidget.this);
        if (flag1)
        {
            flag = false;
        }
        collapsedexpandablewidget3.renderTitle(s5, flag);
_L8:
        return;
_L6:
        if (view instanceof NewTitleWidget)
        {
            CollapsedExpandableWidget.access$1302(CollapsedExpandableWidget.this, ((NewTitleWidget)view).getTitleString());
            FkLogger.debug("Title", (new StringBuilder("current title ")).append(CollapsedExpandableWidget.access$1300(CollapsedExpandableWidget.this)).toString());
            CollapsedExpandableWidget collapsedexpandablewidget2 = CollapsedExpandableWidget.this;
            String s2 = CollapsedExpandableWidget.access$1300(CollapsedExpandableWidget.this);
            if (flag1)
            {
                flag = false;
            }
            collapsedexpandablewidget2.renderTitle(s2, flag);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (view instanceof NewTitleWidget)
        {
            CollapsedExpandableWidget.access$1302(CollapsedExpandableWidget.this, ((NewTitleWidget)view).getTitleString());
            FkLogger.debug("Title", (new StringBuilder("current title ")).append(CollapsedExpandableWidget.access$1300(CollapsedExpandableWidget.this)).toString());
            CollapsedExpandableWidget collapsedexpandablewidget1 = CollapsedExpandableWidget.this;
            String s1 = CollapsedExpandableWidget.access$1300(CollapsedExpandableWidget.this);
            if (flag1)
            {
                flag = false;
            }
            collapsedexpandablewidget1.renderTitle(s1, flag);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (view instanceof NewTitleWidget)
        {
            CollapsedExpandableWidget.access$1302(CollapsedExpandableWidget.this, ((NewTitleWidget)view).getTitleString());
            FkLogger.debug("Title", (new StringBuilder("current title ")).append(CollapsedExpandableWidget.access$1300(CollapsedExpandableWidget.this)).toString());
            CollapsedExpandableWidget collapsedexpandablewidget = CollapsedExpandableWidget.this;
            String s = CollapsedExpandableWidget.access$1300(CollapsedExpandableWidget.this);
            if (flag1)
            {
                flag = false;
            }
            collapsedexpandablewidget.renderTitle(s, flag);
            return;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    ClickItemListner()
    {
        this$0 = CollapsedExpandableWidget.this;
        super();
    }
}
