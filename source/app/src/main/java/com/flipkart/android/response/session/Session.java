// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.session;


public class Session
{

    private String accountId;
    private String emailId;
    private String firstName;
    private boolean flipkartFirstUser;
    private boolean isLoggedIn;
    private String lastName;
    private String nsid;
    private String secureToken;
    private String sn;
    private long timeStamp;
    private String vid;

    public Session()
    {
    }

    public String getAccountId()
    {
        return accountId;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getNsid()
    {
        return nsid;
    }

    public String getSecureToken()
    {
        return secureToken;
    }

    public String getSn()
    {
        return sn;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public String getVid()
    {
        return vid;
    }

    public boolean isFlipkartFirstUser()
    {
        return flipkartFirstUser;
    }

    public boolean isLoggedIn()
    {
        return isLoggedIn;
    }

    public void setAccountId(String s)
    {
        accountId = s;
    }

    public void setEmailId(String s)
    {
        emailId = s;
    }

    public void setFirstName(String s)
    {
        firstName = s;
    }

    public void setFlipkartFirstUser(boolean flag)
    {
        flipkartFirstUser = flag;
    }

    public void setLastName(String s)
    {
        lastName = s;
    }

    public void setLoggedIn(boolean flag)
    {
        isLoggedIn = flag;
    }

    public void setNsid(String s)
    {
        nsid = s;
    }

    public void setSecureToken(String s)
    {
        secureToken = s;
    }

    public void setSn(String s)
    {
        sn = s;
    }

    public void setTimeStamp(long l)
    {
        timeStamp = l;
    }

    public void setVid(String s)
    {
        vid = s;
    }

    public String toString()
    {
        return (new StringBuilder("Session [nsid=")).append(nsid).append(", vid=").append(vid).append(", sn=").append(sn).append(", secureToken=").append(secureToken).append(", isLoggedIn=").append(isLoggedIn).append(", timeStamp=").append(timeStamp).append(", emailId=").append(emailId).append(", firstName=").append(firstName).append(", lastName=").append(lastName).append(", accountId=").append(accountId).append("]").toString();
    }
}
