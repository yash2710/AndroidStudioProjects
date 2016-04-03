// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

// Referenced classes of package com.google.android.gms.games.quest:
//            Milestone, MilestoneEntity

public final class MilestoneRef extends d
    implements Milestone
{

    MilestoneRef(DataHolder dataholder, int i)
    {
        super(dataholder, i);
    }

    private long iI()
    {
        return getLong("initial_value");
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return MilestoneEntity.a(this, obj);
    }

    public final Milestone freeze()
    {
        return new MilestoneEntity(this);
    }

    public final volatile Object freeze()
    {
        return freeze();
    }

    public final byte[] getCompletionRewardData()
    {
        return getByteArray("completion_reward_data");
    }

    public final long getCurrentProgress()
    {
        switch (getState())
        {
        case 1: // '\001'
        default:
            return 0L;

        case 3: // '\003'
        case 4: // '\004'
            return getTargetProgress();

        case 2: // '\002'
            return getLong("current_value") - iI();
        }
    }

    public final String getEventId()
    {
        return getString("external_event_id");
    }

    public final String getMilestoneId()
    {
        return getString("external_milestone_id");
    }

    public final int getState()
    {
        return getInteger("milestone_state");
    }

    public final long getTargetProgress()
    {
        return getLong("target_value");
    }

    public final int hashCode()
    {
        return MilestoneEntity.a(this);
    }

    public final String toString()
    {
        return MilestoneEntity.b(this);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        ((MilestoneEntity)freeze()).writeToParcel(parcel, i);
    }
}
