// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.appstate;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

// Referenced classes of package com.google.android.gms.appstate:
//            b, AppState

public final class AppStateBuffer extends DataBuffer
{

    public AppStateBuffer(DataHolder dataholder)
    {
        super(dataholder);
    }

    public final AppState get(int i)
    {
        return new b(DG, i);
    }

    public final volatile Object get(int i)
    {
        return get(i);
    }
}
