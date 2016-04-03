// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.games.quest:
//            Quest, QuestEntity, Milestone, MilestoneRef

public final class QuestRef extends d
    implements Quest
{

    private final int RG;
    private final Game Ss;

    QuestRef(DataHolder dataholder, int i, int j)
    {
        super(dataholder, i);
        Ss = new GameRef(dataholder, i);
        RG = j;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return QuestEntity.a(this, obj);
    }

    public final Quest freeze()
    {
        return new QuestEntity(this);
    }

    public final volatile Object freeze()
    {
        return freeze();
    }

    public final long getAcceptedTimestamp()
    {
        return getLong("accepted_ts");
    }

    public final Uri getBannerImageUri()
    {
        return aw("quest_banner_image_uri");
    }

    public final String getBannerImageUrl()
    {
        return getString("quest_banner_image_url");
    }

    public final Milestone getCurrentMilestone()
    {
        return (Milestone)iJ().get(0);
    }

    public final String getDescription()
    {
        return getString("quest_description");
    }

    public final void getDescription(CharArrayBuffer chararraybuffer)
    {
        a("quest_description", chararraybuffer);
    }

    public final long getEndTimestamp()
    {
        return getLong("quest_end_ts");
    }

    public final Game getGame()
    {
        return Ss;
    }

    public final Uri getIconImageUri()
    {
        return aw("quest_icon_image_uri");
    }

    public final String getIconImageUrl()
    {
        return getString("quest_icon_image_url");
    }

    public final long getLastUpdatedTimestamp()
    {
        return getLong("quest_last_updated_ts");
    }

    public final String getName()
    {
        return getString("quest_name");
    }

    public final void getName(CharArrayBuffer chararraybuffer)
    {
        a("quest_name", chararraybuffer);
    }

    public final String getQuestId()
    {
        return getString("external_quest_id");
    }

    public final long getStartTimestamp()
    {
        return getLong("quest_start_ts");
    }

    public final int getState()
    {
        return getInteger("quest_state");
    }

    public final int getType()
    {
        return getInteger("quest_type");
    }

    public final int hashCode()
    {
        return QuestEntity.a(this);
    }

    public final List iJ()
    {
        ArrayList arraylist = new ArrayList(RG);
        for (int i = 0; i < RG; i++)
        {
            arraylist.add(new MilestoneRef(DG, i + EC));
        }

        return arraylist;
    }

    public final long iK()
    {
        return getLong("notification_ts");
    }

    public final boolean isEndingSoon()
    {
        return iK() <= 0x1b7740L + System.currentTimeMillis();
    }

    public final String toString()
    {
        return QuestEntity.b(this);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        ((QuestEntity)freeze()).writeToParcel(parcel, i);
    }
}
