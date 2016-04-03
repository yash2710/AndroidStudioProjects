// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;

import android.app.Activity;
import com.facebook.android.Facebook;

// Referenced classes of package com.flipkart.android.login:
//            FacebookTokenFetcher, onFacebookTokenFetchedListener

public class ActivityFacebookTokenFetcher extends FacebookTokenFetcher
{

    public ActivityFacebookTokenFetcher(Activity activity, onFacebookTokenFetchedListener onfacebooktokenfetchedlistener)
    {
        super(activity, onfacebooktokenfetchedlistener);
    }

    public void doAuthorize(int i, String as[])
    {
        a.authorize(b, as, i, d);
    }
}
