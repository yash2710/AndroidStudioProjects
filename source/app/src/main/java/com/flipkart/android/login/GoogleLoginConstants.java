// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;


public final class GoogleLoginConstants extends Enum
{

    public static String KKeyAccessToken = "access_token";
    public static String KKeyClientId = "client_id";
    public static String KKeyClientSecret = "client_secret";
    public static String KKeyCode = "code";
    public static String KKeyGrantType = "grant_type";
    public static String KKeyRedirectUri = "redirect_uri";
    public static String KKeyResponseType = "response_type";
    public static String KKeyScope = "scope";
    public static String KKeyState = "state";
    public static String KValueAuthCodeBaseUrl = "https://accounts.google.com/o/oauth2/auth";
    public static String KValueAuthTokenBaseUrl = "https://accounts.google.com/o/oauth2/token";
    public static String KValueClientId = "656085505957.apps.googleusercontent.com";
    public static String KValueClientSecret = "SaXUoNk7I6LFJco28aGtuFbx";
    public static String KValueGrantTypeAuthorizationCode = "authorization_code";
    public static String KValueRedirectUriLocalhost = "http://localhost";
    public static String KValueRedirectUriLocalhostDomain = "localhost";
    public static String KValueResponseTypeCode = "code";
    public static String KValueScopeUserEmail = "https://www.googleapis.com/auth/userinfo.email";
    private static final GoogleLoginConstants a[];
    public static final GoogleLoginConstants instance;

    private GoogleLoginConstants(String s, int i)
    {
        super(s, 0);
    }

    public static GoogleLoginConstants valueOf(String s)
    {
        return (GoogleLoginConstants)Enum.valueOf(com/flipkart/android/login/GoogleLoginConstants, s);
    }

    public static GoogleLoginConstants[] values()
    {
        return (GoogleLoginConstants[])a.clone();
    }

    static 
    {
        instance = new GoogleLoginConstants("instance", 0);
        GoogleLoginConstants agoogleloginconstants[] = new GoogleLoginConstants[1];
        agoogleloginconstants[0] = instance;
        a = agoogleloginconstants;
    }
}
