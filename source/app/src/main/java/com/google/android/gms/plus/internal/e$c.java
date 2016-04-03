// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.plus.model.moments.MomentBuffer;

// Referenced classes of package com.google.android.gms.plus.internal:
//            e

final class abN extends com.google.android.gms.internal.
    implements com.google.android.gms.plus.nts.LoadMomentsResult
{

    private final String HS;
    final e abM;
    private final String abN;
    private MomentBuffer abO;
    private final Status yz;

    protected final void a(com.google.android.gms.common.api..c c, DataHolder dataholder)
    {
        MomentBuffer momentbuffer;
        if (dataholder != null)
        {
            momentbuffer = new MomentBuffer(dataholder);
        } else
        {
            momentbuffer = null;
        }
        abO = momentbuffer;
        c.a(this);
    }

    protected final volatile void a(Object obj, DataHolder dataholder)
    {
        a((com.google.android.gms.common.api.)obj, dataholder);
    }

    public final MomentBuffer getMomentBuffer()
    {
        return abO;
    }

    public final String getNextPageToken()
    {
        return HS;
    }

    public final Status getStatus()
    {
        return yz;
    }

    public final String getUpdated()
    {
        return abN;
    }

    public final void release()
    {
        if (abO != null)
        {
            abO.close();
        }
    }

    public lder(e e, com.google.android.gms.common.api. , Status status, DataHolder dataholder, String s, String s1)
    {
        abM = e;
        super(e, , dataholder);
        yz = status;
        HS = s;
        abN = s1;
    }
}
