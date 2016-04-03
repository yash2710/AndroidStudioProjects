// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.widget;

import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.response.android.widget.AndroidWidgetResponseWrapper;
import com.flipkart.android.utils.GZipCompressorUtil;
import com.flipkart.android.volley.request.GsonRequest;

public class AndroidWidgetRequest extends GsonRequest
{

    public AndroidWidgetRequest(com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, constructGetUri("2", "component/banner", ""), null, com/flipkart/android/response/android/widget/AndroidWidgetResponseWrapper, listener, errorlistener);
    }

    public void performJsonUpdate(byte abyte0[], AndroidWidgetResponseWrapper androidwidgetresponsewrapper, boolean flag)
    {
        if (abyte0 == null)
        {
            return;
        }
        String s;
        if (flag)
        {
            s = new String(GZipCompressorUtil.decompress(abyte0));
        } else
        {
            s = new String(abyte0);
        }
        FlipkartPreferenceManager.instance().saveAppWidgetResponse(s);
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (AndroidWidgetResponseWrapper)obj, flag);
    }
}
