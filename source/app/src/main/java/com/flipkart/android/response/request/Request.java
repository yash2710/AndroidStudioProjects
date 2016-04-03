// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.request;


// Referenced classes of package com.flipkart.android.response.request:
//            Params

public class Request
{

    private Params params;
    private String requestType;
    private String version;

    public Request()
    {
        params = new Params();
    }

    public Params getParams()
    {
        return params;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public String getVersion()
    {
        return version;
    }

    public void setParams(Params params1)
    {
        params = params1;
    }

    public void setRequestType(String s)
    {
        requestType = s;
    }

    public void setVersion(String s)
    {
        version = s;
    }
}
