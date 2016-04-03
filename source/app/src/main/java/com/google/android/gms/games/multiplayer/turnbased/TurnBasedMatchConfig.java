// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import java.util.ArrayList;

public final class TurnBasedMatchConfig
{

    private final int SY;
    private final String Tn[];
    private final Bundle To;
    private final int Tz;

    private TurnBasedMatchConfig(Builder builder1)
    {
        SY = builder1.SY;
        Tz = builder1.Tz;
        To = builder1.To;
        int i = builder1.Tr.size();
        Tn = (String[])builder1.Tr.toArray(new String[i]);
    }

    TurnBasedMatchConfig(Builder builder1, _cls1 _pcls1)
    {
        this(builder1);
    }

    public static Builder builder()
    {
        return new Builder(null);
    }

    public static Bundle createAutoMatchCriteria(int i, int j, long l)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("min_automatch_players", i);
        bundle.putInt("max_automatch_players", j);
        bundle.putLong("exclusive_bit_mask", l);
        return bundle;
    }

    public final Bundle getAutoMatchCriteria()
    {
        return To;
    }

    public final String[] getInvitedPlayerIds()
    {
        return Tn;
    }

    public final int getVariant()
    {
        return SY;
    }

    public final int iH()
    {
        return Tz;
    }

    private class Builder
    {

        int SY;
        Bundle To;
        ArrayList Tr;
        int Tz;

        public final Builder addInvitedPlayer(String s)
        {
            hm.f(s);
            Tr.add(s);
            return this;
        }

        public final Builder addInvitedPlayers(ArrayList arraylist)
        {
            hm.f(arraylist);
            Tr.addAll(arraylist);
            return this;
        }

        public final TurnBasedMatchConfig build()
        {
            return new TurnBasedMatchConfig(this, null);
        }

        public final Builder setAutoMatchCriteria(Bundle bundle)
        {
            To = bundle;
            return this;
        }

        public final Builder setVariant(int i)
        {
            boolean flag;
            if (i == -1 || i > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            hm.b(flag, "Variant must be a positive integer or TurnBasedMatch.MATCH_VARIANT_ANY");
            SY = i;
            return this;
        }

        private Builder()
        {
            SY = -1;
            Tr = new ArrayList();
            To = null;
            Tz = 2;
        }

        Builder(_cls1 _pcls1)
        {
            this();
        }
    }

}
