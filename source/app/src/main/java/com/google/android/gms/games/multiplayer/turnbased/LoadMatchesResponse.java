// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.TurnBasedMatchTurnStatus;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

// Referenced classes of package com.google.android.gms.games.multiplayer.turnbased:
//            TurnBasedMatchBuffer

public final class LoadMatchesResponse
{

    private final InvitationBuffer Tv;
    private final TurnBasedMatchBuffer Tw;
    private final TurnBasedMatchBuffer Tx;
    private final TurnBasedMatchBuffer Ty;

    public LoadMatchesResponse(Bundle bundle)
    {
        DataHolder dataholder = a(bundle, 0);
        DataHolder dataholder1;
        DataHolder dataholder2;
        DataHolder dataholder3;
        if (dataholder != null)
        {
            Tv = new InvitationBuffer(dataholder);
        } else
        {
            Tv = null;
        }
        dataholder1 = a(bundle, 1);
        if (dataholder1 != null)
        {
            Tw = new TurnBasedMatchBuffer(dataholder1);
        } else
        {
            Tw = null;
        }
        dataholder2 = a(bundle, 2);
        if (dataholder2 != null)
        {
            Tx = new TurnBasedMatchBuffer(dataholder2);
        } else
        {
            Tx = null;
        }
        dataholder3 = a(bundle, 3);
        if (dataholder3 != null)
        {
            Ty = new TurnBasedMatchBuffer(dataholder3);
            return;
        } else
        {
            Ty = null;
            return;
        }
    }

    private static DataHolder a(Bundle bundle, int i)
    {
        String s = TurnBasedMatchTurnStatus.cm(i);
        if (!bundle.containsKey(s))
        {
            return null;
        } else
        {
            return (DataHolder)bundle.getParcelable(s);
        }
    }

    public final void close()
    {
        if (Tv != null)
        {
            Tv.close();
        }
        if (Tw != null)
        {
            Tw.close();
        }
        if (Tx != null)
        {
            Tx.close();
        }
        if (Ty != null)
        {
            Ty.close();
        }
    }

    public final TurnBasedMatchBuffer getCompletedMatches()
    {
        return Ty;
    }

    public final InvitationBuffer getInvitations()
    {
        return Tv;
    }

    public final TurnBasedMatchBuffer getMyTurnMatches()
    {
        return Tw;
    }

    public final TurnBasedMatchBuffer getTheirTurnMatches()
    {
        return Tx;
    }

    public final boolean hasData()
    {
        while (Tv != null && Tv.getCount() > 0 || Tw != null && Tw.getCount() > 0 || Tx != null && Tx.getCount() > 0 || Ty != null && Ty.getCount() > 0) 
        {
            return true;
        }
        return false;
    }
}
