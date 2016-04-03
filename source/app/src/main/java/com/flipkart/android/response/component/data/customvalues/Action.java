// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;

import com.flipkart.android.response.customwidgetitemvalue.TrackingParams;
import java.util.HashMap;
import java.util.Map;

public class Action
{

    public static final String OMNITURE_DATA = "OMNITURE_DATA";
    public static final String REQUEST_ID = "REQUEST_ID";
    private String omnitureData;
    private String originalUrl;
    private Map params;
    private String screenType;
    private TrackingParams tracking;
    private String url;

    public Action()
    {
        params = new HashMap();
    }

    public Action(String s, String s1)
    {
        params = new HashMap();
        screenType = s;
        omnitureData = s1;
    }

    public String getOmnitureData()
    {
        return omnitureData;
    }

    public String getOriginalUrl()
    {
        return originalUrl;
    }

    public Map getParams()
    {
        return params;
    }

    public String getScreenType()
    {
        return screenType;
    }

    public TrackingParams getTracking()
    {
        return tracking;
    }

    public String getUrl()
    {
        return url;
    }

    public void setOmnitureData(String s)
    {
        omnitureData = s;
    }

    public void setOriginalUrl(String s)
    {
        originalUrl = s;
        url = s;
    }

    public void setParams(Map map)
    {
        params = map;
    }

    public void setScreenType(String s)
    {
        screenType = s;
    }

    public void setTracking(TrackingParams trackingparams)
    {
        tracking = trackingparams;
    }

    public void setUrl(String s)
    {
        url = s;
    }

    public String toString()
    {
        return (new StringBuilder("Action{screenType='")).append(screenType).append('\'').append(", omnitureData='").append(omnitureData).append('\'').append(", originalUrl='").append(originalUrl).append('\'').append(", url='").append(url).append('\'').append(", params=").append(params).append(", tracking=").append(params).append('}').toString();
    }
}
