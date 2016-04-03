// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import com.flipkart.android.activity.FilterActivity;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.utils.FkProductListContext;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            SubFilterFragment

final class bx
    implements android.view.View.OnClickListener
{

    private SubFilterFragment a;

    bx(SubFilterFragment subfilterfragment)
    {
        a = subfilterfragment;
        super();
    }

    public final void onClick(View view)
    {
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("filter by ")).append(SubFilterFragment.d(a)).append(":").append(SubFilterFragment.b(a).toString()).toString());
        if (SubFilterFragment.e(a))
        {
            Map map = a.a.getSelectedFilterMap();
            map.put(SubFilterFragment.d(a), SubFilterFragment.b(a));
            a.a.setSelectedFilterMap(map);
        }
        SubFilterFragment.f(a).putContextToCache();
        SubFilterFragment.f(a).finish();
    }
}
