// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.facebook.FacebookException;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;

// Referenced classes of package com.facebook.widget:
//            FacebookDialog

abstract class plicationId
{

    protected final Activity activity;
    protected final all appCall = new all(64207);
    protected final String applicationId;
    protected String applicationName;
    protected Fragment fragment;

    public FacebookDialog build()
    {
        validate();
        Bundle bundle = new Bundle();
        putExtra(bundle, "com.facebook.platform.extra.APPLICATION_ID", applicationId);
        putExtra(bundle, "com.facebook.platform.extra.APPLICATION_NAME", applicationName);
        Intent intent = handleBuild(bundle);
        if (intent == null)
        {
            throw new FacebookException("Unable to create Intent; this likely means the Facebook app is not installed.");
        } else
        {
            all.access._mth100(appCall, intent);
            return new FacebookDialog(activity, fragment, appCall, getOnPresentCallback(), null);
        }
    }

    public boolean canPresent()
    {
        return handleCanPresent();
    }

    tCallback getOnPresentCallback()
    {
        return null;
    }

    abstract Intent handleBuild(Bundle bundle);

    boolean handleCanPresent()
    {
        return FacebookDialog.access$300(activity, Integer.valueOf(0x1332b3a)) != -1;
    }

    void putExtra(Bundle bundle, String s, String s1)
    {
        if (s1 != null)
        {
            bundle.putString(s, s1);
        }
    }

    public activity setApplicationName(String s)
    {
        applicationName = s;
        return this;
    }

    public applicationName setFragment(Fragment fragment1)
    {
        fragment = fragment1;
        return this;
    }

    public fragment setRequestCode(int i)
    {
        all.access._mth000(appCall, i);
        return this;
    }

    void validate()
    {
    }

    tCallback(Activity activity1)
    {
        Validate.notNull(activity1, "activity");
        activity = activity1;
        applicationId = Utility.getMetadataApplicationId(activity1);
    }
}
