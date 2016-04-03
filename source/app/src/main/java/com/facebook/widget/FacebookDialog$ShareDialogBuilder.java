// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.facebook.widget:
//            FacebookDialog

public class  extends 
{

    private String caption;
    private boolean dataErrorsFatal;
    private String description;
    private ArrayList friends;
    private String link;
    private String name;
    private String picture;
    private String place;
    private String ref;

    public volatile FacebookDialog build()
    {
        return super.();
    }

    public volatile boolean canPresent()
    {
        return super.();
    }

    Intent handleBuild(Bundle bundle)
    {
        putExtra(bundle, "com.facebook.platform.extra.APPLICATION_ID", applicationId);
        putExtra(bundle, "com.facebook.platform.extra.APPLICATION_NAME", applicationName);
        putExtra(bundle, "com.facebook.platform.extra.TITLE", name);
        putExtra(bundle, "com.facebook.platform.extra.SUBTITLE", caption);
        putExtra(bundle, "com.facebook.platform.extra.DESCRIPTION", description);
        putExtra(bundle, "com.facebook.platform.extra.LINK", link);
        putExtra(bundle, "com.facebook.platform.extra.IMAGE", picture);
        putExtra(bundle, "com.facebook.platform.extra.PLACE", place);
        putExtra(bundle, "com.facebook.platform.extra.TITLE", name);
        putExtra(bundle, "com.facebook.platform.extra.REF", ref);
        bundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", dataErrorsFatal);
        if (!Utility.isNullOrEmpty(friends))
        {
            bundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", friends);
        }
        int i = FacebookDialog.access$300(activity, Integer.valueOf(0x1332b3a));
        return NativeProtocol.createPlatformActivityIntent(activity, "com.facebook.platform.action.request.FEED_DIALOG", i, bundle);
    }

    boolean handleCanPresent()
    {
        Activity activity = this.activity;
        activity aactivity[] = new activity[1];
        aactivity[0] = SHARE_DIALOG;
        return FacebookDialog.canPresentShareDialog(activity, aactivity);
    }

    public og setCaption(String s)
    {
        caption = s;
        return this;
    }

    public caption setDataErrorsFatal(boolean flag)
    {
        dataErrorsFatal = flag;
        return this;
    }

    public dataErrorsFatal setDescription(String s)
    {
        description = s;
        return this;
    }

    public description setFriends(List list)
    {
        friends = new ArrayList(list);
        return this;
    }

    public friends setLink(String s)
    {
        link = s;
        return this;
    }

    public link setName(String s)
    {
        name = s;
        return this;
    }

    public name setPicture(String s)
    {
        picture = s;
        return this;
    }

    public picture setPlace(String s)
    {
        place = s;
        return this;
    }

    public place setRef(String s)
    {
        ref = s;
        return this;
    }

    public (Activity activity)
    {
        super(activity);
    }
}
