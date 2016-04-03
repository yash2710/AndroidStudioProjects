// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.games.leaderboard:
//            Leaderboard, LeaderboardEntity, LeaderboardVariantRef

public final class LeaderboardRef extends d
    implements Leaderboard
{

    private final int RG;
    private final Game Ss;

    LeaderboardRef(DataHolder dataholder, int i, int j)
    {
        super(dataholder, i);
        RG = j;
        Ss = new GameRef(dataholder, i);
    }

    public final boolean equals(Object obj)
    {
        return LeaderboardEntity.a(this, obj);
    }

    public final Object freeze()
    {
        return iz();
    }

    public final String getDisplayName()
    {
        return getString("name");
    }

    public final void getDisplayName(CharArrayBuffer chararraybuffer)
    {
        a("name", chararraybuffer);
    }

    public final Game getGame()
    {
        return Ss;
    }

    public final Uri getIconImageUri()
    {
        return aw("board_icon_image_uri");
    }

    public final String getIconImageUrl()
    {
        return getString("board_icon_image_url");
    }

    public final String getLeaderboardId()
    {
        return getString("external_leaderboard_id");
    }

    public final int getScoreOrder()
    {
        return getInteger("score_order");
    }

    public final ArrayList getVariants()
    {
        ArrayList arraylist = new ArrayList(RG);
        for (int i = 0; i < RG; i++)
        {
            arraylist.add(new LeaderboardVariantRef(DG, i + EC));
        }

        return arraylist;
    }

    public final int hashCode()
    {
        return LeaderboardEntity.a(this);
    }

    public final Leaderboard iz()
    {
        return new LeaderboardEntity(this);
    }

    public final String toString()
    {
        return LeaderboardEntity.b(this);
    }
}
