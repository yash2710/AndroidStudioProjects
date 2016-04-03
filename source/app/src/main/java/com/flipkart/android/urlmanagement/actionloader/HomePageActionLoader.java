// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement.actionloader;

import android.app.Activity;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.urlmanagement.AppParams;

// Referenced classes of package com.flipkart.android.urlmanagement.actionloader:
//            AppActionLoader

public class HomePageActionLoader extends AppActionLoader
{

    public HomePageActionLoader(AppParams appparams, Activity activity)
    {
        super(appparams, activity);
    }

    public void load()
    {
        if (activity instanceof HomeFragmentHolderActivity)
        {
            ((HomeFragmentHolderActivity)activity).clearStackAndLoadMultiWidgetScreen("homepage", null);
        }
    }
}
