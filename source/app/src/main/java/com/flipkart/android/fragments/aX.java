// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import com.flipkart.android.datahandler.AutoSuggestVDataHandler;
import com.flipkart.android.response.autoSuggest.AutoSuggestResponse;

// Referenced classes of package com.flipkart.android.fragments:
//            aW, SearchFragment

final class aX extends AutoSuggestVDataHandler
{

    private aW a;

    aX(aW aw)
    {
        a = aw;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
    }

    public final void resultReceived(AutoSuggestResponse autosuggestresponse, boolean flag)
    {
        SearchFragment.a(a.a, autosuggestresponse);
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((AutoSuggestResponse)obj, flag);
    }
}
