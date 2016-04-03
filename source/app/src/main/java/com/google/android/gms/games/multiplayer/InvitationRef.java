// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.internal.hm;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.games.multiplayer:
//            Invitation, ParticipantRef, InvitationEntity, Participant

public final class InvitationRef extends d
    implements Invitation
{

    private final ArrayList SX;
    private final Game Ss;
    private final ParticipantRef Ta;

    InvitationRef(DataHolder dataholder, int i, int j)
    {
        super(dataholder, i);
        Ss = new GameRef(dataholder, i);
        SX = new ArrayList(j);
        String s = getString("external_inviter_id");
        int k = 0;
        ParticipantRef participantref = null;
        for (; k < j; k++)
        {
            ParticipantRef participantref1 = new ParticipantRef(DG, k + EC);
            if (participantref1.getParticipantId().equals(s))
            {
                participantref = participantref1;
            }
            SX.add(participantref1);
        }

        Ta = (ParticipantRef)hm.b(participantref, "Must have a valid inviter!");
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return InvitationEntity.a(this, obj);
    }

    public final Invitation freeze()
    {
        return new InvitationEntity(this);
    }

    public final volatile Object freeze()
    {
        return freeze();
    }

    public final int getAvailableAutoMatchSlots()
    {
        if (!getBoolean("has_automatch_criteria"))
        {
            return 0;
        } else
        {
            return getInteger("automatch_max_players");
        }
    }

    public final long getCreationTimestamp()
    {
        return Math.max(getLong("creation_timestamp"), getLong("last_modified_timestamp"));
    }

    public final Game getGame()
    {
        return Ss;
    }

    public final String getInvitationId()
    {
        return getString("external_invitation_id");
    }

    public final int getInvitationType()
    {
        return getInteger("type");
    }

    public final Participant getInviter()
    {
        return Ta;
    }

    public final ArrayList getParticipants()
    {
        return SX;
    }

    public final int getVariant()
    {
        return getInteger("variant");
    }

    public final int hashCode()
    {
        return InvitationEntity.a(this);
    }

    public final String toString()
    {
        return InvitationEntity.b(this);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        ((InvitationEntity)freeze()).writeToParcel(parcel, i);
    }
}
