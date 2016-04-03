// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;

// Referenced classes of package com.google.android.gms.games.snapshot:
//            SnapshotEntity, SnapshotMetadataEntity

public class SnapshotEntityCreator
    implements android.os.Parcelable.Creator
{

    public static final int CONTENT_DESCRIPTION;

    public SnapshotEntityCreator()
    {
    }

    static void a(SnapshotEntity snapshotentity, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, snapshotentity.getMetadata(), i, false);
        b.c(parcel, 1000, snapshotentity.getVersionCode());
        b.a(parcel, 2, snapshotentity.getContents(), i, false);
        b.G(parcel, j);
    }

    public SnapshotEntity createFromParcel(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        SnapshotMetadataEntity snapshotmetadataentity = null;
        int j = 0;
        Contents contents = null;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(k))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
                    break;

                case 1: // '\001'
                    snapshotmetadataentity = (SnapshotMetadataEntity)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, SnapshotMetadataEntity.CREATOR);
                    break;

                case 1000: 
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 2: // '\002'
                    contents = (Contents)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, Contents.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder("Overread allowed size end=")).append(i).toString(), parcel);
            } else
            {
                return new SnapshotEntity(j, snapshotmetadataentity, contents);
            }
        } while (true);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public SnapshotEntity[] newArray(int i)
    {
        return new SnapshotEntity[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}
