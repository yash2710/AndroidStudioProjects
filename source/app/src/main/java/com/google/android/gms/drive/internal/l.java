// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class l extends Metadata
{

    private final MetadataBundle II;

    public l(MetadataBundle metadatabundle)
    {
        II = metadatabundle;
    }

    protected final Object a(MetadataField metadatafield)
    {
        return II.a(metadatafield);
    }

    public final Object freeze()
    {
        return gl();
    }

    public final Metadata gl()
    {
        return new l(MetadataBundle.a(II));
    }

    public final boolean isDataValid()
    {
        return II != null;
    }

    public final String toString()
    {
        return (new StringBuilder("Metadata [mImpl=")).append(II).append("]").toString();
    }
}
