// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantRef;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.games.multiplayer.turnbased:
//            TurnBasedMatch, TurnBasedMatchEntity, TurnBasedMatchConfig

public final class TurnBasedMatchRef extends d
    implements TurnBasedMatch
{

    private final int RG;
    private final Game Ss;

    TurnBasedMatchRef(DataHolder dataholder, int i, int j)
    {
        super(dataholder, i);
        Ss = new GameRef(dataholder, i);
        RG = j;
    }

    public final boolean canRematch()
    {
        return getTurnStatus() == 3 && getRematchId() == null && getParticipants().size() > 1;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return TurnBasedMatchEntity.a(this, obj);
    }

    public final TurnBasedMatch freeze()
    {
        return new TurnBasedMatchEntity(this);
    }

    public final volatile Object freeze()
    {
        return freeze();
    }

    public final Bundle getAutoMatchCriteria()
    {
        if (!getBoolean("has_automatch_criteria"))
        {
            return null;
        } else
        {
            return TurnBasedMatchConfig.createAutoMatchCriteria(getInteger("automatch_min_players"), getInteger("automatch_max_players"), getLong("automatch_bit_mask"));
        }
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
        return getLong("creation_timestamp");
    }

    public final String getCreatorId()
    {
        return getString("creator_external");
    }

    public final byte[] getData()
    {
        return getByteArray("data");
    }

    public final String getDescription()
    {
        return getString("description");
    }

    public final void getDescription(CharArrayBuffer chararraybuffer)
    {
        a("description", chararraybuffer);
    }

    public final Participant getDescriptionParticipant()
    {
        return getParticipant(getDescriptionParticipantId());
    }

    public final String getDescriptionParticipantId()
    {
        return getString("description_participant_id");
    }

    public final Game getGame()
    {
        return Ss;
    }

    public final long getLastUpdatedTimestamp()
    {
        return getLong("last_updated_timestamp");
    }

    public final String getLastUpdaterId()
    {
        return getString("last_updater_external");
    }

    public final String getMatchId()
    {
        return getString("external_match_id");
    }

    public final int getMatchNumber()
    {
        return getInteger("match_number");
    }

    public final Participant getParticipant(String s)
    {
        return TurnBasedMatchEntity.c(this, s);
    }

    public final String getParticipantId(String s)
    {
        return TurnBasedMatchEntity.b(this, s);
    }

    public final ArrayList getParticipantIds()
    {
        return TurnBasedMatchEntity.c(this);
    }

    public final int getParticipantStatus(String s)
    {
        return TurnBasedMatchEntity.a(this, s);
    }

    public final ArrayList getParticipants()
    {
        ArrayList arraylist = new ArrayList(RG);
        for (int i = 0; i < RG; i++)
        {
            arraylist.add(new ParticipantRef(DG, i + EC));
        }

        return arraylist;
    }

    public final String getPendingParticipantId()
    {
        return getString("pending_participant_external");
    }

    public final byte[] getPreviousMatchData()
    {
        return getByteArray("previous_match_data");
    }

    public final String getRematchId()
    {
        return getString("rematch_id");
    }

    public final int getStatus()
    {
        return getInteger("status");
    }

    public final int getTurnStatus()
    {
        return getInteger("user_match_status");
    }

    public final int getVariant()
    {
        return getInteger("variant");
    }

    public final int getVersion()
    {
        return getInteger("version");
    }

    public final int hashCode()
    {
        return TurnBasedMatchEntity.a(this);
    }

    public final boolean isLocallyModified()
    {
        return getBoolean("upsync_required");
    }

    public final String toString()
    {
        return TurnBasedMatchEntity.b(this);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        ((TurnBasedMatchEntity)freeze()).writeToParcel(parcel, i);
    }
}
