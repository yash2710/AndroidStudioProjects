// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.a;
import com.google.android.gms.drive.metadata.internal.j;
import java.util.Collection;

final class .internal.j extends j
{

    protected final Object b(DataHolder dataholder, int k, int l)
    {
        return i(dataholder, k, l);
    }

    protected final a i(DataHolder dataholder, int k, int l)
    {
        throw new IllegalStateException("Thumbnail field is write only");
    }

    taHolder(String s, Collection collection, Collection collection1, int k)
    {
        super(s, collection, collection1, k);
    }
}
