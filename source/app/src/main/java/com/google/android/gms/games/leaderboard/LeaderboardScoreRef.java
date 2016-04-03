// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

// Referenced classes of package com.google.android.gms.games.leaderboard:
//            LeaderboardScore, LeaderboardScoreEntity

public final class LeaderboardScoreRef extends d
    implements LeaderboardScore
{

    private final PlayerRef SG;

    LeaderboardScoreRef(DataHolder dataholder, int i)
    {
        super(dataholder, i);
        SG = new PlayerRef(dataholder, i);
    }

    public final boolean equals(Object obj)
    {
        return LeaderboardScoreEntity.a(this, obj);
    }

    public final Object freeze()
    {
        return iC();
    }

    public final String getDisplayRank()
    {
        return getString("display_rank");
    }

    public final void getDisplayRank(CharArrayBuffer chararraybuffer)
    {
        a("display_rank", chararraybuffer);
    }

    public final String getDisplayScore()
    {
        return getString("display_score");
    }

    public final void getDisplayScore(CharArrayBuffer chararraybuffer)
    {
        a("display_score", chararraybuffer);
    }

    public final long getRank()
    {
        return getLong("rank");
    }

    public final long getRawScore()
    {
        return getLong("raw_score");
    }

    public final Player getScoreHolder()
    {
        if (ax("external_player_id"))
        {
            return null;
        } else
        {
            return SG;
        }
    }

    public final String getScoreHolderDisplayName()
    {
        if (ax("external_player_id"))
        {
            return getString("default_display_name");
        } else
        {
            return SG.getDisplayName();
        }
    }

    public final void getScoreHolderDisplayName(CharArrayBuffer chararraybuffer)
    {
        if (ax("external_player_id"))
        {
            a("default_display_name", chararraybuffer);
            return;
        } else
        {
            SG.getDisplayName(chararraybuffer);
            return;
        }
    }

    public final Uri getScoreHolderHiResImageUri()
    {
        if (ax("external_player_id"))
        {
            return null;
        } else
        {
            return SG.getHiResImageUri();
        }
    }

    public final String getScoreHolderHiResImageUrl()
    {
        if (ax("external_player_id"))
        {
            return null;
        } else
        {
            return SG.getHiResImageUrl();
        }
    }

    public final Uri getScoreHolderIconImageUri()
    {
        if (ax("external_player_id"))
        {
            return aw("default_display_image_uri");
        } else
        {
            return SG.getIconImageUri();
        }
    }

    public final String getScoreHolderIconImageUrl()
    {
        if (ax("external_player_id"))
        {
            return getString("default_display_image_url");
        } else
        {
            return SG.getIconImageUrl();
        }
    }

    public final String getScoreTag()
    {
        return getString("score_tag");
    }

    public final long getTimestampMillis()
    {
        return getLong("achieved_timestamp");
    }

    public final int hashCode()
    {
        return LeaderboardScoreEntity.a(this);
    }

    public final LeaderboardScore iC()
    {
        return new LeaderboardScoreEntity(this);
    }

    public final String toString()
    {
        return LeaderboardScoreEntity.b(this);
    }
}
