// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.wrapper;

import com.flipkart.android.response.request.Request;
import com.flipkart.android.response.session.Session;

public class ResponseWrapper
{

    private String cacheInvalidationTTL;
    private int errorCode;
    private String errorMessage;
    private long hashCode;
    private Request request;
    private String requestId;
    private Object response;
    private Session session;
    private int status;

    public ResponseWrapper()
    {
        request = new Request();
        session = new Session();
    }

    public String getCacheInvalidationTTL()
    {
        return cacheInvalidationTTL;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public long getHashCode()
    {
        return hashCode;
    }

    public Request getRequest()
    {
        return request;
    }

    public String getRequestId()
    {
        return requestId;
    }

    public Object getResponse()
    {
        return response;
    }

    public Session getSession()
    {
        return session;
    }

    public int getStatus()
    {
        return status;
    }

    public void setCacheInvalidationTTL(String s)
    {
        cacheInvalidationTTL = s;
    }

    public void setErrorCode(int i)
    {
        errorCode = i;
    }

    public void setErrorMessage(String s)
    {
        errorMessage = s;
    }

    public void setHashCode(long l)
    {
        hashCode = l;
    }

    public void setRequest(Request request1)
    {
        request = request1;
    }

    public void setRequestId(String s)
    {
        requestId = s;
    }

    public void setResponse(Object obj)
    {
        response = obj;
    }

    public void setSession(Session session1)
    {
        session = session1;
    }

    public void setStatus(int i)
    {
        status = i;
    }
}
