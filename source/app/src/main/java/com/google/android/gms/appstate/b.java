// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.appstate;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

// Referenced classes of package com.google.android.gms.appstate:
//            AppState, a

public final class b extends d
    implements AppState
{

    b(DataHolder dataholder, int i)
    {
        super(dataholder, i);
    }

    public final AppState dS()
    {
        return new a(this);
    }

    public final boolean equals(Object obj)
    {
        return a.a(this, obj);
    }

    public final Object freeze()
    {
        return dS();
    }

    public final byte[] getConflictData()
    {
        return getByteArray("conflict_data");
    }

    public final String getConflictVersion()
    {
        return getString("conflict_version");
    }

    public final int getKey()
    {
        return getInteger("key");
    }

    public final byte[] getLocalData()
    {
        return getByteArray("local_data");
    }

    public final String getLocalVersion()
    {
        return getString("local_version");
    }

    public final boolean hasConflict()
    {
        return !ax("conflict_version");
    }

    public final int hashCode()
    {
        return a.a(this);
    }

    public final String toString()
    {
        return a.b(this);
    }
}
