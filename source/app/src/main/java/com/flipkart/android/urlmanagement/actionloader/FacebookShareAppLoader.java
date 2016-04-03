// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement.actionloader;

import android.app.Activity;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.urlmanagement.AppParams;
import com.flipkart.android.utils.AppConfigUtils;
import java.util.Map;

// Referenced classes of package com.flipkart.android.urlmanagement.actionloader:
//            AppActionLoader

public class FacebookShareAppLoader extends AppActionLoader
{

    private static String a = "offerId";
    private static String b = "orderId";

    public FacebookShareAppLoader(AppParams appparams, Activity activity)
    {
        super(appparams, activity);
    }

    public void load()
    {
        String s = (String)getQueryParams().get(a);
        String s1 = (String)getQueryParams().get(b);
        ((HomeFragmentHolderActivity)activity).shareFacebookDialog(AppConfigUtils.getInstance().getFacebookShareAppUrl(), "Hey, have you tried Flipkart's new mobile app yet? I think you'd like it. ", s, s1);
    }

}
