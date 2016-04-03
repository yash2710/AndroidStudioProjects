// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.plus.model.moments.Moment;

// Referenced classes of package com.google.android.gms.internal:
//            kp, kq

public final class kr extends d
    implements Moment
{

    private kp adp;

    public kr(DataHolder dataholder, int i)
    {
        super(dataholder, i);
    }

    private kp kE()
    {
        this;
        JVM INSTR monitorenter ;
        if (adp == null)
        {
            byte abyte0[] = getByteArray("momentImpl");
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(abyte0, 0, abyte0.length);
            parcel.setDataPosition(0);
            adp = kp.CREATOR.bF(parcel);
            parcel.recycle();
        }
        this;
        JVM INSTR monitorexit ;
        return adp;
        Exception exception;
        exception;
        throw exception;
    }

    public final Object freeze()
    {
        return kD();
    }

    public final String getId()
    {
        return kE().getId();
    }

    public final ItemScope getResult()
    {
        return kE().getResult();
    }

    public final String getStartDate()
    {
        return kE().getStartDate();
    }

    public final ItemScope getTarget()
    {
        return kE().getTarget();
    }

    public final String getType()
    {
        return kE().getType();
    }

    public final boolean hasId()
    {
        return kE().hasId();
    }

    public final boolean hasResult()
    {
        return kE().hasId();
    }

    public final boolean hasStartDate()
    {
        return kE().hasStartDate();
    }

    public final boolean hasTarget()
    {
        return kE().hasTarget();
    }

    public final boolean hasType()
    {
        return kE().hasType();
    }

    public final kp kD()
    {
        return kE();
    }
}
