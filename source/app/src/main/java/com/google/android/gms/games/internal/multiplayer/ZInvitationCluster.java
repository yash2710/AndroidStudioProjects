// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.internal.gx;
import com.google.android.gms.internal.hk;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.games.internal.multiplayer:
//            InvitationClusterCreator

public final class ZInvitationCluster
    implements SafeParcelable, Invitation
{

    public static final InvitationClusterCreator CREATOR = new InvitationClusterCreator();
    private final ArrayList RH;
    private final int xM;

    ZInvitationCluster(int i, ArrayList arraylist)
    {
        xM = i;
        RH = arraylist;
        ii();
    }

    private void ii()
    {
        boolean flag;
        Invitation invitation;
        int i;
        if (!RH.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        gx.A(flag);
        invitation = (Invitation)RH.get(0);
        i = RH.size();
        for (int j = 1; j < i; j++)
        {
            Invitation invitation1 = (Invitation)RH.get(j);
            gx.a(invitation.getInviter().equals(invitation1.getInviter()), "All the invitations must be from the same inviter");
        }

    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof ZInvitationCluster))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        ZInvitationCluster zinvitationcluster = (ZInvitationCluster)obj;
        if (zinvitationcluster.RH.size() != RH.size())
        {
            return false;
        }
        int i = RH.size();
        for (int j = 0; j < i; j++)
        {
            if (!((Invitation)RH.get(j)).equals((Invitation)zinvitationcluster.RH.get(j)))
            {
                return false;
            }
        }

        return true;
    }

    public final Invitation freeze()
    {
        return this;
    }

    public final volatile Object freeze()
    {
        return freeze();
    }

    public final int getAvailableAutoMatchSlots()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final long getCreationTimestamp()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final Game getGame()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final String getInvitationId()
    {
        return ((InvitationEntity)RH.get(0)).getInvitationId();
    }

    public final int getInvitationType()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final Participant getInviter()
    {
        return ((InvitationEntity)RH.get(0)).getInviter();
    }

    public final ArrayList getParticipants()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final int getVariant()
    {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final int hashCode()
    {
        return hk.hashCode(RH.toArray());
    }

    public final ArrayList ij()
    {
        return new ArrayList(RH);
    }

    public final boolean isDataValid()
    {
        return true;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        InvitationClusterCreator.a(this, parcel, i);
    }

}
