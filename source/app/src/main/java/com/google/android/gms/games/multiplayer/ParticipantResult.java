// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.internal.constants.MatchResult;
import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.games.multiplayer:
//            ParticipantResultCreator

public final class ParticipantResult
    implements SafeParcelable
{

    public static final ParticipantResultCreator CREATOR = new ParticipantResultCreator();
    public static final int MATCH_RESULT_DISAGREED = 5;
    public static final int MATCH_RESULT_DISCONNECT = 4;
    public static final int MATCH_RESULT_LOSS = 1;
    public static final int MATCH_RESULT_NONE = 3;
    public static final int MATCH_RESULT_TIE = 2;
    public static final int MATCH_RESULT_UNINITIALIZED = -1;
    public static final int MATCH_RESULT_WIN = 0;
    public static final int PLACING_UNINITIALIZED = -1;
    private final String Oq;
    private final int Tf;
    private final int Tg;
    private final int xM;

    public ParticipantResult(int i, String s, int j, int k)
    {
        xM = i;
        Oq = (String)hm.f(s);
        hm.A(MatchResult.isValid(j));
        Tf = j;
        Tg = k;
    }

    public ParticipantResult(String s, int i, int j)
    {
        this(1, s, i, j);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final String getParticipantId()
    {
        return Oq;
    }

    public final int getPlacing()
    {
        return Tg;
    }

    public final int getResult()
    {
        return Tf;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        ParticipantResultCreator.a(this, parcel, i);
    }

}
