// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.ik;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.games.leaderboard:
//            Leaderboard, LeaderboardVariant, LeaderboardVariantEntity

public final class LeaderboardEntity
    implements Leaderboard
{

    private final String Ln;
    private final String MC;
    private final Uri Mr;
    private final String Sp;
    private final int Sq;
    private final ArrayList Sr;
    private final Game Ss;

    public LeaderboardEntity(Leaderboard leaderboard)
    {
        Sp = leaderboard.getLeaderboardId();
        Ln = leaderboard.getDisplayName();
        Mr = leaderboard.getIconImageUri();
        MC = leaderboard.getIconImageUrl();
        Sq = leaderboard.getScoreOrder();
        Game game = leaderboard.getGame();
        Object obj;
        ArrayList arraylist;
        int i;
        if (game == null)
        {
            obj = null;
        } else
        {
            obj = new GameEntity(game);
        }
        Ss = ((Game) (obj));
        arraylist = leaderboard.getVariants();
        i = arraylist.size();
        Sr = new ArrayList(i);
        for (int j = 0; j < i; j++)
        {
            Sr.add((LeaderboardVariantEntity)((LeaderboardVariant)arraylist.get(j)).freeze());
        }

    }

    static int a(Leaderboard leaderboard)
    {
        Object aobj[] = new Object[5];
        aobj[0] = leaderboard.getLeaderboardId();
        aobj[1] = leaderboard.getDisplayName();
        aobj[2] = leaderboard.getIconImageUri();
        aobj[3] = Integer.valueOf(leaderboard.getScoreOrder());
        aobj[4] = leaderboard.getVariants();
        return hk.hashCode(aobj);
    }

    static boolean a(Leaderboard leaderboard, Object obj)
    {
        if (obj instanceof Leaderboard)
        {
            if (leaderboard == obj)
            {
                return true;
            }
            Leaderboard leaderboard1 = (Leaderboard)obj;
            if (hk.equal(leaderboard1.getLeaderboardId(), leaderboard.getLeaderboardId()) && hk.equal(leaderboard1.getDisplayName(), leaderboard.getDisplayName()) && hk.equal(leaderboard1.getIconImageUri(), leaderboard.getIconImageUri()) && hk.equal(Integer.valueOf(leaderboard1.getScoreOrder()), Integer.valueOf(leaderboard.getScoreOrder())) && hk.equal(leaderboard1.getVariants(), leaderboard.getVariants()))
            {
                return true;
            }
        }
        return false;
    }

    static String b(Leaderboard leaderboard)
    {
        return hk.e(leaderboard).a("LeaderboardId", leaderboard.getLeaderboardId()).a("DisplayName", leaderboard.getDisplayName()).a("IconImageUri", leaderboard.getIconImageUri()).a("IconImageUrl", leaderboard.getIconImageUrl()).a("ScoreOrder", Integer.valueOf(leaderboard.getScoreOrder())).a("Variants", leaderboard.getVariants()).toString();
    }

    public final boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public final Object freeze()
    {
        return iz();
    }

    public final String getDisplayName()
    {
        return Ln;
    }

    public final void getDisplayName(CharArrayBuffer chararraybuffer)
    {
        ik.b(Ln, chararraybuffer);
    }

    public final Game getGame()
    {
        return Ss;
    }

    public final Uri getIconImageUri()
    {
        return Mr;
    }

    public final String getIconImageUrl()
    {
        return MC;
    }

    public final String getLeaderboardId()
    {
        return Sp;
    }

    public final int getScoreOrder()
    {
        return Sq;
    }

    public final ArrayList getVariants()
    {
        return new ArrayList(Sr);
    }

    public final int hashCode()
    {
        return a(this);
    }

    public final boolean isDataValid()
    {
        return true;
    }

    public final Leaderboard iz()
    {
        return this;
    }

    public final String toString()
    {
        return b(this);
    }
}
