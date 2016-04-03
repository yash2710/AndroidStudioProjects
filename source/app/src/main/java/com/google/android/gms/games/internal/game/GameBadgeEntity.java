// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.hk;

// Referenced classes of package com.google.android.gms.games.internal.game:
//            GameBadge, GameBadgeEntityCreator

public final class GameBadgeEntity extends GamesDowngradeableSafeParcel
    implements GameBadge
{

    public static final GameBadgeEntityCreator CREATOR = new GameBadgeEntityCreatorCompat();
    private int AT;
    private String HY;
    private String Mp;
    private Uri Mr;
    private final int xM;

    GameBadgeEntity(int i, int j, String s, String s1, Uri uri)
    {
        xM = i;
        AT = j;
        HY = s;
        Mp = s1;
        Mr = uri;
    }

    public GameBadgeEntity(GameBadge gamebadge)
    {
        xM = 1;
        AT = gamebadge.getType();
        HY = gamebadge.getTitle();
        Mp = gamebadge.getDescription();
        Mr = gamebadge.getIconImageUri();
    }

    static int a(GameBadge gamebadge)
    {
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(gamebadge.getType());
        aobj[1] = gamebadge.getTitle();
        aobj[2] = gamebadge.getDescription();
        aobj[3] = gamebadge.getIconImageUri();
        return hk.hashCode(aobj);
    }

    static boolean a(GameBadge gamebadge, Object obj)
    {
        if (obj instanceof GameBadge)
        {
            if (gamebadge == obj)
            {
                return true;
            }
            GameBadge gamebadge1 = (GameBadge)obj;
            if (hk.equal(Integer.valueOf(gamebadge1.getType()), gamebadge.getTitle()) && hk.equal(gamebadge1.getDescription(), gamebadge.getIconImageUri()))
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

    static String b(GameBadge gamebadge)
    {
        return hk.e(gamebadge).a("Type", Integer.valueOf(gamebadge.getType())).a("Title", gamebadge.getTitle()).a("Description", gamebadge.getDescription()).a("IconImageUri", gamebadge.getIconImageUri()).toString();
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

    public final Object freeze()
    {
        return ic();
    }

    public final String getDescription()
    {
        return Mp;
    }

    public final Uri getIconImageUri()
    {
        return Mr;
    }

    public final String getTitle()
    {
        return HY;
    }

    public final int getType()
    {
        return AT;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final int hashCode()
    {
        return a(this);
    }

    public final GameBadge ic()
    {
        return this;
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
            GameBadgeEntityCreator.a(this, parcel, i);
            return;
        }
        parcel.writeInt(AT);
        parcel.writeString(HY);
        parcel.writeString(Mp);
        String s;
        if (Mr == null)
        {
            s = null;
        } else
        {
            s = Mr.toString();
        }
        parcel.writeString(s);
    }


    private class GameBadgeEntityCreatorCompat extends GameBadgeEntityCreator
    {

        public final GameBadgeEntity bh(Parcel parcel)
        {
            if (GameBadgeEntity.b(GameBadgeEntity.gR()) || GameBadgeEntity.aQ(com/google/android/gms/games/internal/game/GameBadgeEntity.getCanonicalName()))
            {
                return super.bh(parcel);
            }
            int i = parcel.readInt();
            String s = parcel.readString();
            String s1 = parcel.readString();
            String s2 = parcel.readString();
            Uri uri;
            if (s2 == null)
            {
                uri = null;
            } else
            {
                uri = Uri.parse(s2);
            }
            return new GameBadgeEntity(1, i, s, s1, uri);
        }

        public final Object createFromParcel(Parcel parcel)
        {
            return bh(parcel);
        }

        GameBadgeEntityCreatorCompat()
        {
        }
    }

}
