// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoRef;
import com.google.android.gms.games.internal.player.PlayerColumnNames;

// Referenced classes of package com.google.android.gms.games:
//            Player, PlayerLevel, PlayerLevelInfo, PlayerEntity

public final class PlayerRef extends d
    implements Player
{

    private final PlayerLevelInfo MX;
    private final PlayerColumnNames Ng;
    private final MostRecentGameInfoRef Nh;

    public PlayerRef(DataHolder dataholder, int i)
    {
        this(dataholder, i, null);
    }

    public PlayerRef(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i);
        Ng = new PlayerColumnNames(s);
        Nh = new MostRecentGameInfoRef(dataholder, i, Ng);
        if (gV())
        {
            int j = getInteger(Ng.RY);
            int k = getInteger(Ng.Sb);
            PlayerLevel playerlevel = new PlayerLevel(j, getLong(Ng.RZ), getLong(Ng.Sa));
            PlayerLevel playerlevel1;
            if (j != k)
            {
                playerlevel1 = new PlayerLevel(k, getLong(Ng.Sa), getLong(Ng.Sc));
            } else
            {
                playerlevel1 = playerlevel;
            }
            MX = new PlayerLevelInfo(getLong(Ng.RX), getLong(Ng.Sd), playerlevel, playerlevel1);
            return;
        } else
        {
            MX = null;
            return;
        }
    }

    private boolean gV()
    {
        while (ax(Ng.RX) || getLong(Ng.RX) == -1L) 
        {
            return false;
        }
        return true;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return PlayerEntity.a(this, obj);
    }

    public final Player freeze()
    {
        return new PlayerEntity(this);
    }

    public final volatile Object freeze()
    {
        return freeze();
    }

    public final int gS()
    {
        return getInteger(Ng.RV);
    }

    public final boolean gT()
    {
        return getBoolean(Ng.Sf);
    }

    public final MostRecentGameInfo gU()
    {
        if (ax(Ng.Sg))
        {
            return null;
        } else
        {
            return Nh;
        }
    }

    public final String getDisplayName()
    {
        return getString(Ng.RP);
    }

    public final void getDisplayName(CharArrayBuffer chararraybuffer)
    {
        a(Ng.RP, chararraybuffer);
    }

    public final Uri getHiResImageUri()
    {
        return aw(Ng.RS);
    }

    public final String getHiResImageUrl()
    {
        return getString(Ng.RT);
    }

    public final Uri getIconImageUri()
    {
        return aw(Ng.RQ);
    }

    public final String getIconImageUrl()
    {
        return getString(Ng.RR);
    }

    public final long getLastPlayedWithTimestamp()
    {
        if (!av(Ng.RW) || ax(Ng.RW))
        {
            return -1L;
        } else
        {
            return getLong(Ng.RW);
        }
    }

    public final PlayerLevelInfo getLevelInfo()
    {
        return MX;
    }

    public final String getPlayerId()
    {
        return getString(Ng.RO);
    }

    public final long getRetrievedTimestamp()
    {
        return getLong(Ng.RU);
    }

    public final String getTitle()
    {
        return getString(Ng.Se);
    }

    public final void getTitle(CharArrayBuffer chararraybuffer)
    {
        a(Ng.Se, chararraybuffer);
    }

    public final boolean hasHiResImage()
    {
        return getHiResImageUri() != null;
    }

    public final boolean hasIconImage()
    {
        return getIconImageUri() != null;
    }

    public final int hashCode()
    {
        return PlayerEntity.a(this);
    }

    public final String toString()
    {
        return PlayerEntity.b(this);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        ((PlayerEntity)freeze()).writeToParcel(parcel, i);
    }
}
