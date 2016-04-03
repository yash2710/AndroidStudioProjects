// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import com.flipkart.android.datahandler.UrlDecodeDatahandler;
import com.flipkart.android.response.urlencoder.UrlDecodeResponse;
import com.flipkart.android.utils.FkLoadingDialog;

// Referenced classes of package com.flipkart.android.activity:
//            HomeFragmentHolderActivity

final class q extends UrlDecodeDatahandler
{

    private HomeFragmentHolderActivity a;

    q(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        a = homefragmentholderactivity;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        if (HomeFragmentHolderActivity.d(a) != null)
        {
            HomeFragmentHolderActivity.d(a).dismissDlg();
        }
        a.loadHomeFragment();
        HomeFragmentHolderActivity.a(a, true);
    }

    public final void resultReceived(UrlDecodeResponse urldecoderesponse, boolean flag)
    {
        if (HomeFragmentHolderActivity.d(a) != null)
        {
            HomeFragmentHolderActivity.d(a).dismissDlg();
        }
        if (urldecoderesponse != null)
        {
            com.flipkart.android.response.customwidgetitemvalue.Action action = urldecoderesponse.getAction();
            HomeFragmentHolderActivity.a(a, action);
            if (action != null && !HomeFragmentHolderActivity.g(a))
            {
                HomeFragmentHolderActivity.h(a);
                return;
            }
        }
        a.loadHomeFragment();
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((UrlDecodeResponse)obj, flag);
    }
}
