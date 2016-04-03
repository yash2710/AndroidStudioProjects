// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.measurement;


// Referenced classes of package com.adobe.adms.measurement:
//            ADMS_Measurement, ADMS_Worker, ADMS_RequestProperties, ADMS_RequestHandler

final class g extends Thread
{

    public boolean a;
    private long b;
    private ADMS_Worker c;

    public g(ADMS_Worker adms_worker)
    {
        b = 0L;
        c = null;
        a = false;
        c = adms_worker;
    }

    public final void run()
    {
_L9:
        if (a) goto _L2; else goto _L1
_L1:
        if (!ADMS_Measurement.isOnline())
        {
            break MISSING_BLOCK_LABEL_159;
        }
        if (c.getTrackingQueueSize() <= 0) goto _L4; else goto _L3
_L3:
        String s = c.popRequest();
        if (s == null) goto _L6; else goto _L5
_L5:
        ADMS_RequestProperties adms_requestproperties = new ADMS_RequestProperties(s);
        if (!ADMS_RequestHandler.sendRequest(adms_requestproperties.getUrl(), adms_requestproperties.getHeaders())) goto _L8; else goto _L7
_L7:
        if (c.trackOffline)
        {
            c.writeToDisk();
        }
_L6:
        Exception exception;
        Thread.sleep(b);
        b = 0L;
          goto _L9
_L2:
        return;
_L8:
        ADMS_Measurement.sharedInstance().debugLog("AppMeasurement Error : Error Sending Hit");
        if (c.trackOffline)
        {
            b = 30000L;
            c.pushRequest(s);
        }
          goto _L6
_L4:
        try
        {
            a = true;
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            ADMS_Measurement.sharedInstance().debugLog((new StringBuilder("AppMeasurement Error : Background Thread Interrupted : ")).append(exception.getMessage()).toString());
        }
          goto _L2
        b = 30000L;
          goto _L6
    }
}
