// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

// Referenced classes of package com.google.android.gms.games.event:
//            Event, EventEntity

public final class EventRef extends d
    implements Event
{

    EventRef(DataHolder dataholder, int i)
    {
        super(dataholder, i);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return EventEntity.a(this, obj);
    }

    public final Event freeze()
    {
        return new EventEntity(this);
    }

    public final volatile Object freeze()
    {
        return freeze();
    }

    public final String getDescription()
    {
        return getString("description");
    }

    public final void getDescription(CharArrayBuffer chararraybuffer)
    {
        a("description", chararraybuffer);
    }

    public final String getEventId()
    {
        return getString("external_event_id");
    }

    public final String getFormattedValue()
    {
        return getString("formatted_value");
    }

    public final void getFormattedValue(CharArrayBuffer chararraybuffer)
    {
        a("formatted_value", chararraybuffer);
    }

    public final Uri getIconImageUri()
    {
        return aw("icon_image_uri");
    }

    public final String getIconImageUrl()
    {
        return getString("icon_image_url");
    }

    public final String getName()
    {
        return getString("name");
    }

    public final void getName(CharArrayBuffer chararraybuffer)
    {
        a("name", chararraybuffer);
    }

    public final Player getPlayer()
    {
        return new PlayerRef(DG, EC);
    }

    public final long getValue()
    {
        return getLong("value");
    }

    public final int hashCode()
    {
        return EventEntity.a(this);
    }

    public final boolean isVisible()
    {
        return getBoolean("visibility");
    }

    public final String toString()
    {
        return EventEntity.b(this);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        ((EventEntity)freeze()).writeToParcel(parcel, i);
    }
}
