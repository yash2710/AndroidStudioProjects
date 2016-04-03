// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.ik;

// Referenced classes of package com.google.android.gms.games.event:
//            Event, EventEntityCreator

public final class EventEntity
    implements SafeParcelable, Event
{

    public static final EventEntityCreator CREATOR = new EventEntityCreator();
    private final String MC;
    private final String Mp;
    private final Uri Mr;
    private final String Ni;
    private final PlayerEntity Nj;
    private final long Nk;
    private final String Nl;
    private final boolean Nm;
    private final String mName;
    private final int xM;

    EventEntity(int i, String s, String s1, String s2, Uri uri, String s3, Player player, 
            long l, String s4, boolean flag)
    {
        xM = i;
        Ni = s;
        mName = s1;
        Mp = s2;
        Mr = uri;
        MC = s3;
        Nj = new PlayerEntity(player);
        Nk = l;
        Nl = s4;
        Nm = flag;
    }

    public EventEntity(Event event)
    {
        xM = 1;
        Ni = event.getEventId();
        mName = event.getName();
        Mp = event.getDescription();
        Mr = event.getIconImageUri();
        MC = event.getIconImageUrl();
        Nj = (PlayerEntity)event.getPlayer().freeze();
        Nk = event.getValue();
        Nl = event.getFormattedValue();
        Nm = event.isVisible();
    }

    static int a(Event event)
    {
        Object aobj[] = new Object[9];
        aobj[0] = event.getEventId();
        aobj[1] = event.getName();
        aobj[2] = event.getDescription();
        aobj[3] = event.getIconImageUri();
        aobj[4] = event.getIconImageUrl();
        aobj[5] = event.getPlayer();
        aobj[6] = Long.valueOf(event.getValue());
        aobj[7] = event.getFormattedValue();
        aobj[8] = Boolean.valueOf(event.isVisible());
        return hk.hashCode(aobj);
    }

    static boolean a(Event event, Object obj)
    {
        if (obj instanceof Event)
        {
            if (event == obj)
            {
                return true;
            }
            Event event1 = (Event)obj;
            if (hk.equal(event1.getEventId(), event.getEventId()) && hk.equal(event1.getName(), event.getName()) && hk.equal(event1.getDescription(), event.getDescription()) && hk.equal(event1.getIconImageUri(), event.getIconImageUri()) && hk.equal(event1.getIconImageUrl(), event.getIconImageUrl()) && hk.equal(event1.getPlayer(), event.getPlayer()) && hk.equal(Long.valueOf(event1.getValue()), Long.valueOf(event.getValue())) && hk.equal(event1.getFormattedValue(), event.getFormattedValue()) && hk.equal(Boolean.valueOf(event1.isVisible()), Boolean.valueOf(event.isVisible())))
            {
                return true;
            }
        }
        return false;
    }

    static String b(Event event)
    {
        return hk.e(event).a("Id", event.getEventId()).a("Name", event.getName()).a("Description", event.getDescription()).a("IconImageUri", event.getIconImageUri()).a("IconImageUrl", event.getIconImageUrl()).a("Player", event.getPlayer()).a("Value", Long.valueOf(event.getValue())).a("FormattedValue", event.getFormattedValue()).a("isVisible", Boolean.valueOf(event.isVisible())).toString();
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public final Event freeze()
    {
        return this;
    }

    public final volatile Object freeze()
    {
        return freeze();
    }

    public final String getDescription()
    {
        return Mp;
    }

    public final void getDescription(CharArrayBuffer chararraybuffer)
    {
        ik.b(Mp, chararraybuffer);
    }

    public final String getEventId()
    {
        return Ni;
    }

    public final String getFormattedValue()
    {
        return Nl;
    }

    public final void getFormattedValue(CharArrayBuffer chararraybuffer)
    {
        ik.b(Nl, chararraybuffer);
    }

    public final Uri getIconImageUri()
    {
        return Mr;
    }

    public final String getIconImageUrl()
    {
        return MC;
    }

    public final String getName()
    {
        return mName;
    }

    public final void getName(CharArrayBuffer chararraybuffer)
    {
        ik.b(mName, chararraybuffer);
    }

    public final Player getPlayer()
    {
        return Nj;
    }

    public final long getValue()
    {
        return Nk;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final int hashCode()
    {
        return a(this);
    }

    public final boolean isDataValid()
    {
        return true;
    }

    public final boolean isVisible()
    {
        return Nm;
    }

    public final String toString()
    {
        return b(this);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        EventEntityCreator.a(this, parcel, i);
    }

}
