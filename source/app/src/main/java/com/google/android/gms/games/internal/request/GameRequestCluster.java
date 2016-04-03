// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestEntity;
import com.google.android.gms.internal.gx;
import com.google.android.gms.internal.hk;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.games.internal.request:
//            GameRequestClusterCreator

public final class GameRequestCluster
    implements SafeParcelable, GameRequest
{

    public static final GameRequestClusterCreator CREATOR = new GameRequestClusterCreator();
    private final ArrayList Sm;
    private final int xM;

    GameRequestCluster(int i, ArrayList arraylist)
    {
        xM = i;
        Sm = arraylist;
        ii();
    }

    private void ii()
    {
        boolean flag;
        GameRequest gamerequest;
        int i;
        int j;
        if (!Sm.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        gx.A(flag);
        gamerequest = (GameRequest)Sm.get(0);
        i = Sm.size();
        j = 1;
        while (j < i) 
        {
            GameRequest gamerequest1 = (GameRequest)Sm.get(j);
            boolean flag1;
            if (gamerequest.getType() == gamerequest1.getType())
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            gx.a(flag1, "All the requests must be of the same type");
            gx.a(gamerequest.getSender().equals(gamerequest1.getSender()), "All the requests must be from the same sender");
            j++;
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof GameRequestCluster))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        GameRequestCluster gamerequestcluster = (GameRequestCluster)obj;
        if (gamerequestcluster.Sm.size() != Sm.size())
        {
            return false;
        }
        int i = Sm.size();
        for (int j = 0; j < i; j++)
        {
            if (!((GameRequest)Sm.get(j)).equals((GameRequest)gamerequestcluster.Sm.get(j)))
            {
                return false;
            }
        }

        return true;
    }

    public final GameRequest freeze()
    {
        return this;
    }

    public final volatile Object freeze()
    {
        return freeze();
    }

    public final long getCreationTimestamp()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final byte[] getData()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final long getExpirationTimestamp()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final Game getGame()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final int getRecipientStatus(String s)
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final List getRecipients()
    {
        return ix();
    }

    public final String getRequestId()
    {
        return ((GameRequestEntity)Sm.get(0)).getRequestId();
    }

    public final Player getSender()
    {
        return ((GameRequestEntity)Sm.get(0)).getSender();
    }

    public final int getStatus()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final int getType()
    {
        return ((GameRequestEntity)Sm.get(0)).getType();
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final int hashCode()
    {
        return hk.hashCode(Sm.toArray());
    }

    public final boolean isConsumed(String s)
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final boolean isDataValid()
    {
        return true;
    }

    public final ArrayList iw()
    {
        return new ArrayList(Sm);
    }

    public final ArrayList ix()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        GameRequestClusterCreator.a(this, parcel, i);
    }

}
