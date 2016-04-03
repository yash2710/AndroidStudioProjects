// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;


public interface onGoogleLoginWebViewClientListener
{

    public abstract void offerGoogleLoginWebViewClientError(TGoogleLoginWebClientErrorType tgoogleloginwebclienterrortype);

    public abstract void offerGoogleLoginWebViewClientEvent(TGoogleLoginWebClientEventType tgoogleloginwebclienteventtype, String s);
}
