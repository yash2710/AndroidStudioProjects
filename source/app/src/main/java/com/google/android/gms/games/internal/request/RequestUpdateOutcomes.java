// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.hm;
import java.util.HashMap;
import java.util.Set;

public final class RequestUpdateOutcomes
{

    private static final String Sn[] = {
        "requestId", "outcome"
    };
    private final int CT;
    private final HashMap So;

    private RequestUpdateOutcomes(int i, HashMap hashmap)
    {
        CT = i;
        So = hashmap;
    }

    RequestUpdateOutcomes(int i, HashMap hashmap, _cls1 _pcls1)
    {
        this(i, hashmap);
    }

    public static RequestUpdateOutcomes U(DataHolder dataholder)
    {
        Builder builder = new Builder();
        builder.cw(dataholder.getStatusCode());
        int i = dataholder.getCount();
        for (int j = 0; j < i; j++)
        {
            int k = dataholder.ae(j);
            builder.v(dataholder.c("requestId", j, k), dataholder.b("outcome", j, k));
        }

        return builder.iy();
    }

    public final Set getRequestIds()
    {
        return So.keySet();
    }

    public final int getRequestOutcome(String s)
    {
        hm.b(So.containsKey(s), (new StringBuilder("Request ")).append(s).append(" was not part of the update operation!").toString());
        return ((Integer)So.get(s)).intValue();
    }


    private class Builder
    {

        private int CT;
        private HashMap So;

        public final Builder cw(int i)
        {
            CT = i;
            return this;
        }

        public final RequestUpdateOutcomes iy()
        {
            return new RequestUpdateOutcomes(CT, So, null);
        }

        public final Builder v(String s, int i)
        {
            if (RequestUpdateResultOutcome.isValid(i))
            {
                So.put(s, Integer.valueOf(i));
            }
            return this;
        }

        public Builder()
        {
            So = new HashMap();
            CT = 0;
        }
    }

}
