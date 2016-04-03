// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;

import android.os.Bundle;

public interface onFacebookTokenFetchedListener
{

    public abstract void onFacebookTokenFetchError(FacebookTokenFetcherErrorType facebooktokenfetchererrortype, String s);

    public abstract void onFacebookTokenFetcherEvent(FacebookTokenFetcherEventType facebooktokenfetchereventtype, Bundle bundle);
}
