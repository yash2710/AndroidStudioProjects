// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;

import com.adobe.adms.measurement.ADMS_Measurement;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.analytics:
//            PageName, TrackingUtil, PageType

public class TrackingBuilder
{

    private static TrackingBuilder d;
    private ADMS_Measurement a;
    private String b;
    private Boolean c;

    private TrackingBuilder()
    {
        b = PageName.Homepage.toString();
        c = Boolean.valueOf(true);
    }

    public static TrackingBuilder getInstance()
    {
        com/flipkart/android/analytics/TrackingBuilder;
        JVM INSTR monitorenter ;
        TrackingBuilder trackingbuilder;
        if (d == null)
        {
            d = new TrackingBuilder();
        }
        trackingbuilder = d;
        com/flipkart/android/analytics/TrackingBuilder;
        JVM INSTR monitorexit ;
        return trackingbuilder;
        Exception exception;
        exception;
        throw exception;
    }

    public TrackingBuilder addPageParams(String s, PageType pagetype, String s1)
    {
        TrackingUtil.setPageParams(a, s, pagetype, s1);
        b = s;
        return this;
    }

    public void configureAppMeasurement()
    {
        TrackingUtil.configureAppMeasurement();
    }

    public TrackingBuilder init()
    {
        if (c.booleanValue())
        {
            a = TrackingUtil.getMeasurement();
            c = Boolean.valueOf(false);
        }
        return this;
    }

    public TrackingBuilder setEvar(int i, String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            a.setEvar(i, s);
        }
        return this;
    }

    public TrackingBuilder setEvent(String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            TrackingUtil.setEvent(a, s);
        }
        return this;
    }

    public TrackingBuilder setLastPageEvar()
    {
        a.setEvar(7, b);
        return this;
    }

    public TrackingBuilder setListVar(int i, String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            a.setListVar(i, s);
        }
        return this;
    }

    public TrackingBuilder setProducts(String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            a.setProducts(s);
        }
        return this;
    }

    public TrackingBuilder setProp(int i, String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            a.setProp(i, s);
        }
        return this;
    }

    public TrackingBuilder setSubCategory(String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            a.setProp(17, s);
            a.setEvar(17, s);
        }
        return this;
    }

    public TrackingBuilder setSuperCategory(String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            a.setProp(16, s);
            a.setEvar(16, s);
        }
        return this;
    }

    public TrackingBuilder setVertical(String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            a.setProp(19, s);
            a.setEvar(19, s);
        }
        return this;
    }

    public void trackEvents(String s)
    {
        TrackingUtil.trackEvent(a, s);
    }

    public void trackLink()
    {
        c = Boolean.valueOf(true);
        TrackingUtil.trackLink(a);
    }

    public void trackPage()
    {
        c = Boolean.valueOf(true);
        TrackingUtil.trackPage(a);
    }
}
