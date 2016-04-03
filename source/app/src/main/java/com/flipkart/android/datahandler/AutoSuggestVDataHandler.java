// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.filters.AutoSuggestFilter;
import com.flipkart.android.volley.request.autoSuggest.AutoSuggestRequest;
import com.flipkart.android.volley.request.autoSuggest.params.AutoSuggestParams;
import java.util.UUID;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class AutoSuggestVDataHandler extends BaseVDataHandler
{

    private String a;

    public AutoSuggestVDataHandler()
    {
        a = null;
        a = UUID.randomUUID().toString();
        requestFilter = new AutoSuggestFilter(a);
    }

    public void doAutoSuggest(String s, boolean flag)
    {
        AutoSuggestRequest autosuggestrequest = new AutoSuggestRequest(new AutoSuggestParams(s, flag), listner, errorListner, a);
        request = autosuggestrequest;
        FlipkartApplication.getRequestQueue().add(autosuggestrequest);
    }
}
