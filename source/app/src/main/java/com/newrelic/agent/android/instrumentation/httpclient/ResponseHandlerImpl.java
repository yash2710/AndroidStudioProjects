// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

public final class ResponseHandlerImpl
    implements ResponseHandler
{

    private final ResponseHandler a;
    private final TransactionState b;

    private ResponseHandlerImpl(ResponseHandler responsehandler, TransactionState transactionstate)
    {
        a = responsehandler;
        b = transactionstate;
    }

    public static ResponseHandler wrap(ResponseHandler responsehandler, TransactionState transactionstate)
    {
        return new ResponseHandlerImpl(responsehandler, transactionstate);
    }

    public final Object handleResponse(HttpResponse httpresponse)
    {
        TransactionStateUtil.inspectAndInstrument(b, httpresponse);
        return a.handleResponse(httpresponse);
    }
}
