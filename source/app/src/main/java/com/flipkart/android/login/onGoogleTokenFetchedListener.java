// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;


public interface onGoogleTokenFetchedListener
{

    public static final int ErrorCancel = 1;
    public static final int ErrorIO = 3;
    public static final int ErrorUnableToAuthenticate = 2;
    public static final int ErrorUnknown = 0;
    public static final int EventTypeGoogleAccountSelected = 1;
    public static final int EventTypeOfferAuthToken;

    public abstract void onGoogleTokenFetchError(int i);

    public abstract void onGoogleTokenFetcherEvent(int i, String s);
}
