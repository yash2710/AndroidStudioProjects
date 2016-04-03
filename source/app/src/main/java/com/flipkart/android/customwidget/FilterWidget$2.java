// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.flipkart.android.activity.FilterActivity;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.FilterResponse;
import java.util.UUID;

// Referenced classes of package com.flipkart.android.customwidget:
//            FilterWidget

class this._cls0
    implements android.view.tener
{

    final FilterWidget this$0;

    public void onClick(View view)
    {
        String s = (String)view.getTag();
        UUID uuid = UUID.randomUUID();
        FilterResponse filterresponse = new FilterResponse();
        filterresponse.setFilterKey(s);
        filterresponse.setSaveCheckedItemInFkContext(true);
        filterresponse.setFkContext(FilterWidget.access$100(FilterWidget.this));
        ContextCache.getInstance().putResponse((new StringBuilder()).append(uuid).append("_filterResponse").toString(), filterresponse);
        Intent intent = new Intent(activity, com/flipkart/android/activity/FilterActivity);
        intent.putExtra("PRODUCT_LIST_EXTRAS_SCREEN_TYPE", 1);
        intent.putExtra("PRODUCT_PAGE_UUID", uuid.toString());
        activity.startActivityForResult(intent, 1);
    }

    ()
    {
        this$0 = FilterWidget.this;
        super();
    }
}
