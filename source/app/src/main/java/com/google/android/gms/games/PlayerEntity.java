// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import com.google.android.gms.internal.gx;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.ik;

// Referenced classes of package com.google.android.gms.games:
//            Player, PlayerEntityCreator, PlayerLevelInfo

public final class PlayerEntity extends GamesDowngradeableSafeParcel
    implements Player
{

    public static final android.os.Parcelable.Creator CREATOR = new PlayerEntityCreatorCompat();
    private final String HY;
    private final String Ln;
    private final String MC;
    private final String MD;
    private final String MS;
    private final long MT;
    private final int MU;
    private final long MV;
    private final MostRecentGameInfoEntity MW;
    private final PlayerLevelInfo MX;
    private final boolean MY;
    private final Uri Mr;
    private final Uri Ms;
    private final int xM;

    PlayerEntity(int i, String s, String s1, Uri uri, Uri uri1, long l, 
            int j, long l1, String s2, String s3, String s4, MostRecentGameInfoEntity mostrecentgameinfoentity, 
            PlayerLevelInfo playerlevelinfo, boolean flag)
    {
        xM = i;
        MS = s;
        Ln = s1;
        Mr = uri;
        MC = s2;
        Ms = uri1;
        MD = s3;
        MT = l;
        MU = j;
        MV = l1;
        HY = s4;
        MY = flag;
        MW = mostrecentgameinfoentity;
        MX = playerlevelinfo;
    }

    public PlayerEntity(Player player)
    {
        xM = 10;
        MS = player.getPlayerId();
        Ln = player.getDisplayName();
        Mr = player.getIconImageUri();
        MC = player.getIconImageUrl();
        Ms = player.getHiResImageUri();
        MD = player.getHiResImageUrl();
        MT = player.getRetrievedTimestamp();
        MU = player.gS();
        MV = player.getLastPlayedWithTimestamp();
        HY = player.getTitle();
        MY = player.gT();
        MostRecentGameInfo mostrecentgameinfo = player.gU();
        MostRecentGameInfoEntity mostrecentgameinfoentity;
        boolean flag;
        if (mostrecentgameinfo == null)
        {
            mostrecentgameinfoentity = null;
        } else
        {
            mostrecentgameinfoentity = new MostRecentGameInfoEntity(mostrecentgameinfo);
        }
        MW = mostrecentgameinfoentity;
        MX = player.getLevelInfo();
        gx.c(MS);
        gx.c(Ln);
        if (MT > 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        gx.A(flag);
    }

    static int a(Player player)
    {
        Object aobj[] = new Object[7];
        aobj[0] = player.getPlayerId();
        aobj[1] = player.getDisplayName();
        aobj[2] = player.getIconImageUri();
        aobj[3] = player.getHiResImageUri();
        aobj[4] = Long.valueOf(player.getRetrievedTimestamp());
        aobj[5] = player.getTitle();
        aobj[6] = player.getLevelInfo();
        return hk.hashCode(aobj);
    }

    static boolean a(Player player, Object obj)
    {
        if (obj instanceof Player)
        {
            if (player == obj)
            {
                return true;
            }
            Player player1 = (Player)obj;
            if (hk.equal(player1.getPlayerId(), player.getPlayerId()) && hk.equal(player1.getDisplayName(), player.getDisplayName()) && hk.equal(player1.getIconImageUri(), player.getIconImageUri()) && hk.equal(player1.getHiResImageUri(), player.getHiResImageUri()) && hk.equal(Long.valueOf(player1.getRetrievedTimestamp()), Long.valueOf(player.getRetrievedTimestamp())) && hk.equal(player1.getTitle(), player.getTitle()) && hk.equal(player1.getLevelInfo(), player.getLevelInfo()))
            {
                return true;
            }
        }
        return false;
    }

    static boolean aQ(String s)
    {
        return aA(s);
    }

    static String b(Player player)
    {
        return hk.e(player).a("PlayerId", player.getPlayerId()).a("DisplayName", player.getDisplayName()).a("IconImageUri", player.getIconImageUri()).a("IconImageUrl", player.getIconImageUrl()).a("HiResImageUri", player.getHiResImageUri()).a("HiResImageUrl", player.getHiResImageUrl()).a("RetrievedTimestamp", Long.valueOf(player.getRetrievedTimestamp())).a("Title", player.getTitle()).a("LevelInfo", player.getLevelInfo()).toString();
    }

    static boolean b(Integer integer)
    {
        return c(integer);
    }

    static Integer gR()
    {
        return fq();
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public final Player freeze()
    {
        return this;
    }

    public final volatile Object freeze()
    {
        return freeze();
    }

    public final int gS()
    {
        return MU;
    }

    public final boolean gT()
    {
        return MY;
    }

    public final MostRecentGameInfo gU()
    {
        return MW;
    }

    public final String getDisplayName()
    {
        return Ln;
    }

    public final void getDisplayName(CharArrayBuffer chararraybuffer)
    {
        ik.b(Ln, chararraybuffer);
    }

    public final Uri getHiResImageUri()
    {
        return Ms;
    }

    public final String getHiResImageUrl()
    {
        return MD;
    }

    public final Uri getIconImageUri()
    {
        return Mr;
    }

    public final String getIconImageUrl()
    {
        return MC;
    }

    public final long getLastPlayedWithTimestamp()
    {
        return MV;
    }

    public final PlayerLevelInfo getLevelInfo()
    {
        return MX;
    }

    public final String getPlayerId()
    {
        return MS;
    }

    public final long getRetrievedTimestamp()
    {
        return MT;
    }

    public final String getTitle()
    {
        return HY;
    }

    public final void getTitle(CharArrayBuffer chararraybuffer)
    {
        ik.b(HY, chararraybuffer);
    }

    public final int getVersionCode()
    {
        return xM;
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
        return a(this);
    }

    public final boolean isDataValid()
    {
        return true;
    }

    public final String toString()
    {
        return b(this);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        if (!fr())
        {
            PlayerEntityCreator.a(this, parcel, i);
            return;
        }
        parcel.writeString(MS);
        parcel.writeString(Ln);
        String s;
        Uri uri;
        String s1;
        if (Mr == null)
        {
            s = null;
        } else
        {
            s = Mr.toString();
        }
        parcel.writeString(s);
        uri = Ms;
        s1 = null;
        if (uri != null)
        {
            s1 = Ms.toString();
        }
        parcel.writeString(s1);
        parcel.writeLong(MT);
    }


    private class PlayerEntityCreatorCompat extends PlayerEntityCreator
    {

        public final PlayerEntity be(Parcel parcel)
        {
            if (PlayerEntity.b(PlayerEntity.gR()) || PlayerEntity.aQ(com/google/android/gms/games/PlayerEntity.getCanonicalName()))
            {
                return super.be(parcel);
            }
            String s = parcel.readString();
            String s1 = parcel.readString();
            String s2 = parcel.readString();
            String s3 = parcel.readString();
            Uri uri;
            Uri uri1;
            if (s2 == null)
            {
                uri = null;
            } else
            {
                uri = Uri.parse(s2);
            }
            if (s3 == null)
            {
                uri1 = null;
            } else
            {
                uri1 = Uri.parse(s3);
            }
            return new PlayerEntity(10, s, s1, uri, uri1, parcel.readLong(), -1, -1L, null, null, null, null, null, true);
        }

        public final Object createFromParcel(Parcel parcel)
        {
            return be(parcel);
        }

        PlayerEntityCreatorCompat()
        {
        }
    }

}
