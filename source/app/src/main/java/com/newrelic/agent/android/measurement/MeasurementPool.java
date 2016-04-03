// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.consumer.MeasurementConsumer;
import com.newrelic.agent.android.measurement.producer.BaseMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.MeasurementProducer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.newrelic.agent.android.measurement:
//            MeasurementType, Measurement

public class MeasurementPool extends BaseMeasurementProducer
    implements MeasurementConsumer
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private final Collection b = new ArrayList();
    private final Collection c = new ArrayList();

    public MeasurementPool()
    {
        super(MeasurementType.Any);
        addMeasurementProducer(this);
    }

    public void addMeasurementConsumer(MeasurementConsumer measurementconsumer)
    {
label0:
        {
            synchronized (c)
            {
                if (!c.contains(measurementconsumer))
                {
                    break label0;
                }
                a.debug((new StringBuilder("Attempted to add the same MeasurementConsumer ")).append(measurementconsumer).append(" multiple times.").toString());
            }
            return;
        }
        c.add(measurementconsumer);
        collection;
        JVM INSTR monitorexit ;
    }

    public void addMeasurementProducer(MeasurementProducer measurementproducer)
    {
label0:
        {
            synchronized (b)
            {
                if (!b.contains(measurementproducer))
                {
                    break label0;
                }
                a.debug((new StringBuilder("Attempted to add the same MeasurementProducer ")).append(measurementproducer).append("  multiple times.").toString());
            }
            return;
        }
        b.add(measurementproducer);
        collection;
        JVM INSTR monitorexit ;
    }

    public void broadcastMeasurements()
    {
        ArrayList arraylist = new ArrayList();
        Collection collection = b;
        collection;
        JVM INSTR monitorenter ;
        Iterator iterator = b.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Collection collection2 = ((MeasurementProducer)iterator.next()).drainMeasurements();
            if (collection2.size() > 0)
            {
                arraylist.addAll(collection2);
            }
        } while (true);
        break MISSING_BLOCK_LABEL_80;
        Exception exception;
        exception;
        throw exception;
        collection;
        JVM INSTR monitorexit ;
        Collection collection1 = c;
        collection1;
        JVM INSTR monitorenter ;
        for (Iterator iterator1 = c.iterator(); iterator1.hasNext();)
        {
            MeasurementConsumer measurementconsumer = (MeasurementConsumer)iterator1.next();
            Iterator iterator2 = (new ArrayList(arraylist)).iterator();
            while (iterator2.hasNext()) 
            {
                Measurement measurement = (Measurement)iterator2.next();
                if (measurementconsumer.getMeasurementType() == measurement.getType() || measurementconsumer.getMeasurementType() == MeasurementType.Any)
                {
                    measurementconsumer.consumeMeasurement(measurement);
                }
            }
        }

        break MISSING_BLOCK_LABEL_211;
        Exception exception1;
        exception1;
        throw exception1;
        collection1;
        JVM INSTR monitorexit ;
    }

    public void consumeMeasurement(Measurement measurement)
    {
        produceMeasurement(measurement);
    }

    public void consumeMeasurements(Collection collection)
    {
        produceMeasurements(collection);
    }

    public Collection getMeasurementConsumers()
    {
        return c;
    }

    public Collection getMeasurementProducers()
    {
        return b;
    }

    public MeasurementType getMeasurementType()
    {
        return MeasurementType.Any;
    }

    public void removeMeasurementConsumer(MeasurementConsumer measurementconsumer)
    {
label0:
        {
            synchronized (c)
            {
                if (c.contains(measurementconsumer))
                {
                    break label0;
                }
                a.debug((new StringBuilder("Attempted to remove MeasurementConsumer ")).append(measurementconsumer).append(" which is not registered.").toString());
            }
            return;
        }
        c.remove(measurementconsumer);
        collection;
        JVM INSTR monitorexit ;
    }

    public void removeMeasurementProducer(MeasurementProducer measurementproducer)
    {
label0:
        {
            synchronized (b)
            {
                if (b.contains(measurementproducer))
                {
                    break label0;
                }
                a.debug((new StringBuilder("Attempted to remove MeasurementProducer ")).append(measurementproducer).append(" which is not registered.").toString());
            }
            return;
        }
        b.remove(measurementproducer);
        collection;
        JVM INSTR monitorexit ;
    }

}
