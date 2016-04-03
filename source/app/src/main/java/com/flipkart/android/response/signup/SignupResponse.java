// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.signup;


public class SignupResponse
{

    String message;
    boolean signUp;

    public SignupResponse()
    {
    }

    public String getMessage()
    {
        return message;
    }

    public boolean isSignUp()
    {
        return signUp;
    }

    public void setMessage(String s)
    {
        message = s;
    }

    public void setSignUp(boolean flag)
    {
        signUp = flag;
    }
}
