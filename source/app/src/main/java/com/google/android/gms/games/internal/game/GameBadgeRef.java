// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

// Referenced classes of package com.google.android.gms.games.internal.game:
//            GameBadge, GameBadgeEntity

public final class GameBadgeRef extends d
    implements GameBadge
{

    GameBadgeRef(DataHolder dataholder, int i)
    {
        super(dataholder, i);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return GameBadgeEntity.a(this, obj);
    }

    public final Object freeze()
    {
        return ic();
    }

    public final String getDescription()
    {
        return getString("badge_description");
    }

    public final Uri getIconImageUri()
    {
        return aw("badge_icon_image_uri");
    }

    public final String getTitle()
    {
        return getString("badge_title");
    }

    public final int getType()
    {
        return getInteger("badge_type");
    }

    public final int hashCode()
    {
        return GameBadgeEntity.a(this);
    }

    public final GameBadge ic()
    {
        return new GameBadgeEntity(this);
    }

    public final String toString()
    {
        return GameBadgeEntity.b(this);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        ((GameBadgeEntity)ic()).writeToParcel(parcel, i);
    }
}
