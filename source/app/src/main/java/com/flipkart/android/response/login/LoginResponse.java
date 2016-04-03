// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.login;


public class LoginResponse
{

    boolean loggedIn;
    String message;

    public LoginResponse()
    {
    }

    public String getMessage()
    {
        return message;
    }

    public boolean isLoggedIn()
    {
        return loggedIn;
    }

    public void setLoggedIn(boolean flag)
    {
        loggedIn = flag;
    }

    public void setMessage(String s)
    {
        message = s;
    }
}
