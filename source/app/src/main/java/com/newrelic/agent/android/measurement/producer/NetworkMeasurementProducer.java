// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.util.Util;

// Referenced classes of package com.newrelic.agent.android.measurement.producer:
//            BaseMeasurementProducer

public class NetworkMeasurementProducer extends BaseMeasurementProducer
{

    public NetworkMeasurementProducer()
    {
        super(MeasurementType.Network);
    }

    public void produceMeasurement(HttpTransactionMeasurement httptransactionmeasurement)
    {
        String s = Util.sanitizeUrl(httptransactionmeasurement.getUrl());
        if (s == null)
        {
            return;
        } else
        {
            httptransactionmeasurement.setUrl(s);
            super.produceMeasurement(httptransactionmeasurement);
            return;
        }
    }

    public void produceMeasurement(String s, int i, int j, long l, double d, 
            long l1, long l2, String s1)
    {
        String s2 = Util.sanitizeUrl(s);
        if (s2 == null)
        {
            return;
        } else
        {
            produceMeasurement(new HttpTransactionMeasurement(s2, i, j, l, d, l1, l2, s1));
            return;
        }
    }
}
