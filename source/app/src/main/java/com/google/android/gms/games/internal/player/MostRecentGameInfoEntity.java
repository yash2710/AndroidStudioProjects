// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hk;

// Referenced classes of package com.google.android.gms.games.internal.player:
//            MostRecentGameInfo, MostRecentGameInfoEntityCreator

public final class MostRecentGameInfoEntity
    implements SafeParcelable, MostRecentGameInfo
{

    public static final MostRecentGameInfoEntityCreator CREATOR = new MostRecentGameInfoEntityCreator();
    private final String RI;
    private final String RJ;
    private final long RK;
    private final Uri RL;
    private final Uri RM;
    private final Uri RN;
    private final int xM;

    MostRecentGameInfoEntity(int i, String s, String s1, long l, Uri uri, Uri uri1, 
            Uri uri2)
    {
        xM = i;
        RI = s;
        RJ = s1;
        RK = l;
        RL = uri;
        RM = uri1;
        RN = uri2;
    }

    public MostRecentGameInfoEntity(MostRecentGameInfo mostrecentgameinfo)
    {
        xM = 2;
        RI = mostrecentgameinfo.ip();
        RJ = mostrecentgameinfo.iq();
        RK = mostrecentgameinfo.ir();
        RL = mostrecentgameinfo.is();
        RM = mostrecentgameinfo.it();
        RN = mostrecentgameinfo.iu();
    }

    static int a(MostRecentGameInfo mostrecentgameinfo)
    {
        Object aobj[] = new Object[6];
        aobj[0] = mostrecentgameinfo.ip();
        aobj[1] = mostrecentgameinfo.iq();
        aobj[2] = Long.valueOf(mostrecentgameinfo.ir());
        aobj[3] = mostrecentgameinfo.is();
        aobj[4] = mostrecentgameinfo.it();
        aobj[5] = mostrecentgameinfo.iu();
        return hk.hashCode(aobj);
    }

    static boolean a(MostRecentGameInfo mostrecentgameinfo, Object obj)
    {
        if (obj instanceof MostRecentGameInfo)
        {
            if (mostrecentgameinfo == obj)
            {
                return true;
            }
            MostRecentGameInfo mostrecentgameinfo1 = (MostRecentGameInfo)obj;
            if (hk.equal(mostrecentgameinfo1.ip(), mostrecentgameinfo.ip()) && hk.equal(mostrecentgameinfo1.iq(), mostrecentgameinfo.iq()) && hk.equal(Long.valueOf(mostrecentgameinfo1.ir()), Long.valueOf(mostrecentgameinfo.ir())) && hk.equal(mostrecentgameinfo1.is(), mostrecentgameinfo.is()) && hk.equal(mostrecentgameinfo1.it(), mostrecentgameinfo.it()) && hk.equal(mostrecentgameinfo1.iu(), mostrecentgameinfo.iu()))
            {
                return true;
            }
        }
        return false;
    }

    static String b(MostRecentGameInfo mostrecentgameinfo)
    {
        return hk.e(mostrecentgameinfo).a("GameId", mostrecentgameinfo.ip()).a("GameName", mostrecentgameinfo.iq()).a("ActivityTimestampMillis", Long.valueOf(mostrecentgameinfo.ir())).a("GameIconUri", mostrecentgameinfo.is()).a("GameHiResUri", mostrecentgameinfo.it()).a("GameFeaturedUri", mostrecentgameinfo.iu()).toString();
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
        return iv();
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final int hashCode()
    {
        return a(this);
    }

    public final String ip()
    {
        return RI;
    }

    public final String iq()
    {
        return RJ;
    }

    public final long ir()
    {
        return RK;
    }

    public final Uri is()
    {
        return RL;
    }

    public final boolean isDataValid()
    {
        return true;
    }

    public final Uri it()
    {
        return RM;
    }

    public final Uri iu()
    {
        return RN;
    }

    public final MostRecentGameInfo iv()
    {
        return this;
    }

    public final String toString()
    {
        return b(this);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        MostRecentGameInfoEntityCreator.a(this, parcel, i);
    }

}
