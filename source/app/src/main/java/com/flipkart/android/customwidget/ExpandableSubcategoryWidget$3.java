// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import com.flipkart.android.utils.MiscScreenUtils;

// Referenced classes of package com.flipkart.android.customwidget:
//            ExpandableSubcategoryWidget

class this._cls0
    implements android.widget.apseListener
{

    final ExpandableSubcategoryWidget this$0;

    public void onGroupCollapse(int i)
    {
        if (ExpandableSubcategoryWidget.access$500(ExpandableSubcategoryWidget.this) == 1)
        {
            MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, true);
            return;
        } else
        {
            MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, false);
            return;
        }
    }

    ()
    {
        this$0 = ExpandableSubcategoryWidget.this;
        super();
    }
}
