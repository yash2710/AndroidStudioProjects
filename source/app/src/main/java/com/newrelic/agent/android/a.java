// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;

import com.newrelic.agent.android.api.common.TransactionData;
import java.util.Comparator;

final class a
    implements Comparator
{

    a()
    {
    }

    public final int compare(TransactionData transactiondata, TransactionData transactiondata1)
    {
        if (transactiondata.getTimestamp() > transactiondata1.getTimestamp())
        {
            return -1;
        }
        return transactiondata.getTimestamp() >= transactiondata1.getTimestamp() ? 0 : 1;
    }

    public final volatile int compare(Object obj, Object obj1)
    {
        return compare((TransactionData)obj, (TransactionData)obj1);
    }
}
