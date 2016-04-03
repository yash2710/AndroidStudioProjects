// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HttpError;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.http.HttpErrorMeasurement;

// Referenced classes of package com.newrelic.agent.android.measurement.consumer:
//            BaseMeasurementConsumer

public class HttpErrorHarvestingConsumer extends BaseMeasurementConsumer
{

    public HttpErrorHarvestingConsumer()
    {
        super(MeasurementType.HttpError);
    }

    public void consumeMeasurement(Measurement measurement)
    {
        Harvest.addHttpError(new HttpError((HttpErrorMeasurement)measurement));
    }
}
