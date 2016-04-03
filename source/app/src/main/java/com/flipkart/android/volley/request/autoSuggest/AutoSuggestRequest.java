// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.autoSuggest;

import com.flipkart.android.response.autoSuggest.AutoSuggestResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.autoSuggest.params.AutoSuggestParams;

public class AutoSuggestRequest extends GsonRequest
{

    private String a;

    public AutoSuggestRequest(AutoSuggestParams autosuggestparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, String s)
    {
        super(0, constructGetUri("2", "discover/getAutoSuggest", autosuggestparams.generateURI()), null, com/flipkart/android/response/autoSuggest/AutoSuggestResponse, listener, errorlistener);
        a = null;
        a = s;
    }

    public String getRequestId()
    {
        return a;
    }

    public void performJsonUpdate(byte abyte0[], AutoSuggestResponse autosuggestresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (AutoSuggestResponse)obj, flag);
    }

    public void setRequestId(String s)
    {
        a = s;
    }
}
