// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.userRegistration;


public class UserRegistrationResponse
{

    private boolean optIn;
    private boolean success;

    public UserRegistrationResponse()
    {
    }

    public boolean isOptIn()
    {
        return optIn;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setOptIn(boolean flag)
    {
        optIn = flag;
    }

    public void setSuccess(boolean flag)
    {
        success = flag;
    }
}
