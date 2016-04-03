// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

// Referenced classes of package com.google.android.gms.games.leaderboard:
//            LeaderboardVariant, LeaderboardVariantEntity

public final class LeaderboardVariantRef extends d
    implements LeaderboardVariant
{

    LeaderboardVariantRef(DataHolder dataholder, int i)
    {
        super(dataholder, i);
    }

    public final boolean equals(Object obj)
    {
        return LeaderboardVariantEntity.a(this, obj);
    }

    public final Object freeze()
    {
        return iG();
    }

    public final int getCollection()
    {
        return getInteger("collection");
    }

    public final String getDisplayPlayerRank()
    {
        return getString("player_display_rank");
    }

    public final String getDisplayPlayerScore()
    {
        return getString("player_display_score");
    }

    public final long getNumScores()
    {
        if (ax("total_scores"))
        {
            return -1L;
        } else
        {
            return getLong("total_scores");
        }
    }

    public final long getPlayerRank()
    {
        if (ax("player_rank"))
        {
            return -1L;
        } else
        {
            return getLong("player_rank");
        }
    }

    public final String getPlayerScoreTag()
    {
        return getString("player_score_tag");
    }

    public final long getRawPlayerScore()
    {
        if (ax("player_raw_score"))
        {
            return -1L;
        } else
        {
            return getLong("player_raw_score");
        }
    }

    public final int getTimeSpan()
    {
        return getInteger("timespan");
    }

    public final boolean hasPlayerInfo()
    {
        return !ax("player_raw_score");
    }

    public final int hashCode()
    {
        return LeaderboardVariantEntity.a(this);
    }

    public final String iD()
    {
        return getString("top_page_token_next");
    }

    public final String iE()
    {
        return getString("window_page_token_prev");
    }

    public final String iF()
    {
        return getString("window_page_token_next");
    }

    public final LeaderboardVariant iG()
    {
        return new LeaderboardVariantEntity(this);
    }

    public final String toString()
    {
        return LeaderboardVariantEntity.b(this);
    }
}
