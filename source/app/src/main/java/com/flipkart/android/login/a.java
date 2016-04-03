// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;

import android.os.Bundle;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;

// Referenced classes of package com.flipkart.android.login:
//            FacebookTokenFetcher

final class a
    implements com.facebook.android.Facebook.DialogListener
{

    private FacebookTokenFetcher a;

    a(FacebookTokenFetcher facebooktokenfetcher)
    {
        a = facebooktokenfetcher;
        super();
    }

    public final void onCancel()
    {
        FacebookTokenFetcher.a(a, onFacebookTokenFetchedListener.FacebookTokenFetcherErrorType.ErrorCancel, "Login cancelled");
    }

    public final void onComplete(Bundle bundle)
    {
        Bundle bundle1 = new Bundle();
        bundle1.putString(FacebookTokenFetcher.KeyAccessToken, a.a.getAccessToken());
        bundle1.putLong(FacebookTokenFetcher.KeyAccessExpires, a.a.getAccessExpires());
        FacebookTokenFetcher.a(a, onFacebookTokenFetchedListener.FacebookTokenFetcherEventType.EventTypeAuthTokenReceived, bundle1);
    }

    public final void onError(DialogError dialogerror)
    {
        FacebookTokenFetcher.a(a, onFacebookTokenFetchedListener.FacebookTokenFetcherErrorType.ErrorFacebook, dialogerror.toString());
    }

    public final void onFacebookError(FacebookError facebookerror)
    {
        FacebookTokenFetcher.a(a, onFacebookTokenFetchedListener.FacebookTokenFetcherErrorType.ErrorFacebook, facebookerror.toString());
    }
}
