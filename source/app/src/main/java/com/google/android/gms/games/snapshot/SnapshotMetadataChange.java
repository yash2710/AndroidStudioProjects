// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.games.snapshot:
//            SnapshotMetadataChangeCreator

public final class SnapshotMetadataChange
    implements SafeParcelable
{

    public static final SnapshotMetadataChangeCreator CREATOR = new SnapshotMetadataChangeCreator();
    public static final SnapshotMetadataChange EMPTY_CHANGE = new SnapshotMetadataChange();
    private final String Mp;
    private final Long Ug;
    private final Uri Uh;
    private a Ui;
    private final int xM;

    SnapshotMetadataChange()
    {
        this(4, null, null, null, null);
    }

    SnapshotMetadataChange(int i, String s, Long long1, a a1, Uri uri)
    {
        boolean flag = true;
        super();
        xM = i;
        Mp = s;
        Ug = long1;
        Ui = a1;
        Uh = uri;
        if (Ui != null)
        {
            if (Uh != null)
            {
                flag = false;
            }
            hm.a(flag, "Cannot set both a URI and an image");
        } else
        if (Uh != null)
        {
            if (Ui != null)
            {
                flag = false;
            }
            hm.a(flag, "Cannot set both a URI and an image");
            return;
        }
    }

    SnapshotMetadataChange(String s, Long long1, a a1, Uri uri)
    {
        this(4, s, long1, a1, uri);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final Bitmap getCoverImage()
    {
        if (Ui == null)
        {
            return null;
        } else
        {
            return Ui.eS();
        }
    }

    public final Uri getCoverImageUri()
    {
        return Uh;
    }

    public final String getDescription()
    {
        return Mp;
    }

    public final Long getPlayedTimeMillis()
    {
        return Ug;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final a iN()
    {
        return Ui;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        com.google.android.gms.games.snapshot.SnapshotMetadataChangeCreator.a(this, parcel, i);
    }

}
