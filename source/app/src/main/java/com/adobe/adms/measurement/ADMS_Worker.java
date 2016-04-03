// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.measurement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.adobe.adms.measurement:
//            g, ADMS_Measurement, ADMS_DefaultValues

public final class ADMS_Worker
{

    private final ArrayList a = new ArrayList();
    private g b;
    private boolean c;
    protected String cacheFilename;
    private final Object d = new Object();
    protected int offlineLimit;
    protected boolean trackOffline;

    protected ADMS_Worker()
    {
        b = null;
        cacheFilename = null;
        trackOffline = false;
        offlineLimit = 100;
        c = false;
    }

    private void a()
    {
        if (b != null)
        {
            b.a = true;
            b = null;
        }
    }

    protected final void clearTrackingQueue()
    {
        synchronized (a)
        {
            a.clear();
            writeToDisk();
        }
    }

    protected final int getTrackingQueueSize()
    {
        int i;
        synchronized (a)
        {
            i = a.size();
        }
        return i;
    }

    protected final String popRequest()
    {
        ArrayList arraylist = a;
        arraylist;
        JVM INSTR monitorenter ;
        int i = a.size();
        String s;
        s = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        s = (String)a.get(0);
        a.remove(0);
        arraylist;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    protected final void pushRequest(String s)
    {
        synchronized (a)
        {
            a.add(0, s);
        }
    }

    protected final void queue(String s)
    {
        if (trackOffline || !c) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ArrayList arraylist = a;
        arraylist;
        JVM INSTR monitorenter ;
        a.add(s);
        if (trackOffline)
        {
            for (; a.size() > offlineLimit; a.remove(0)) { }
        }
        break MISSING_BLOCK_LABEL_69;
        Exception exception;
        exception;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
        if (trackOffline)
        {
            writeToDisk();
        }
        if (!c)
        {
            setOnline(true);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected final void readFromDisk()
    {
        File file;
        while (cacheFilename == null || !(file = new File(cacheFilename)).exists()) 
        {
            return;
        }
        BufferedReader bufferedreader;
        ArrayList arraylist;
        bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        arraylist = new ArrayList();
_L1:
        String s = bufferedreader.readLine();
label0:
        {
            if (s == null)
            {
                break label0;
            }
            try
            {
                arraylist.add(s);
            }
            catch (IOException ioexception)
            {
                ADMS_Measurement.sharedInstance().debugLog((new StringBuilder("AppMeasurement Error : Cannot Read Requests From Disk : ")).append(ioexception.getMessage()).toString());
                ADMS_DefaultValues.exceptionLog(ioexception);
                return;
            }
        }
          goto _L1
        if (arraylist.size() > 0)
        {
            synchronized (a)
            {
                a.clear();
                a.addAll(arraylist);
            }
        }
        bufferedreader.close();
        return;
        exception;
        arraylist1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected final void setOnline(boolean flag)
    {
label0:
        {
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_91;
            }
            synchronized (d)
            {
                if (b == null || b.a)
                {
                    break label0;
                }
            }
            return;
        }
        if (b == null || b.a)
        {
            a();
            b = new g(this);
            b.start();
        }
        c = false;
        obj1;
        JVM INSTR monitorexit ;
        return;
        synchronized (d)
        {
            if (b != null && !b.a)
            {
                a();
            }
        }
        if (trackOffline)
        {
            writeToDisk();
        }
        c = true;
        return;
    }

    protected final void writeToDisk()
    {
        if (cacheFilename != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        File file = new File(cacheFilename);
        BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        ArrayList arraylist1;
        synchronized (a)
        {
            arraylist1 = new ArrayList(a);
        }
        for (Iterator iterator = arraylist1.iterator(); iterator.hasNext(); bufferedwriter.newLine())
        {
            String s = (String)iterator.next();
            bufferedwriter.write(s, 0, s.length());
        }

          goto _L3
        IOException ioexception1;
        ioexception1;
_L7:
        ADMS_Measurement.sharedInstance().debugLog((new StringBuilder("AppMeasurement Error : Cannot Write Requests To Disk : ")).append(ioexception1.getMessage()).toString());
        ADMS_DefaultValues.exceptionLog(ioexception1);
        if (bufferedwriter == null) goto _L1; else goto _L4
_L4:
        try
        {
            bufferedwriter.close();
            return;
        }
        catch (IOException ioexception2)
        {
            ADMS_Measurement.sharedInstance().debugLog((new StringBuilder("AppMeasurement Error : Cannont closed buffered writer : ")).append(ioexception2.getMessage()).toString());
            ADMS_DefaultValues.exceptionLog(ioexception2);
            return;
        }
        exception1;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
_L6:
        IOException ioexception3;
        if (bufferedwriter != null)
        {
            try
            {
                bufferedwriter.close();
            }
            catch (IOException ioexception)
            {
                ADMS_Measurement.sharedInstance().debugLog((new StringBuilder("AppMeasurement Error : Cannont closed buffered writer : ")).append(ioexception.getMessage()).toString());
                ADMS_DefaultValues.exceptionLog(ioexception);
            }
        }
        throw exception;
_L3:
        try
        {
            bufferedwriter.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception3)
        {
            ADMS_Measurement.sharedInstance().debugLog((new StringBuilder("AppMeasurement Error : Cannont closed buffered writer : ")).append(ioexception3.getMessage()).toString());
            ADMS_DefaultValues.exceptionLog(ioexception3);
            return;
        }
        exception;
        bufferedwriter = null;
        if (true) goto _L6; else goto _L5
_L5:
        ioexception1;
        bufferedwriter = null;
          goto _L7
    }
}
