// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.mediameasurement;

import com.adobe.adms.measurement.ADMS_Measurement;

// Referenced classes of package com.adobe.adms.mediameasurement:
//            ADMS_MediaState

public interface ADMS_MediaMeasurementDelegate
{

    public abstract void ADMS_MediaMeasurementMonitor(ADMS_Measurement adms_measurement, ADMS_MediaState adms_mediastate);
}
