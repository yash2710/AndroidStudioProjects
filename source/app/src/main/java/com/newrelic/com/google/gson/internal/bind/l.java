// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.TypeAdapterFactory;
import com.newrelic.com.google.gson.reflect.TypeToken;
import java.sql.Time;

// Referenced classes of package com.newrelic.com.google.gson.internal.bind:
//            TimeTypeAdapter

final class l
    implements TypeAdapterFactory
{

    l()
    {
    }

    public final TypeAdapter create(Gson gson, TypeToken typetoken)
    {
        if (typetoken.getRawType() == java/sql/Time)
        {
            return new TimeTypeAdapter();
        } else
        {
            return null;
        }
    }
}
