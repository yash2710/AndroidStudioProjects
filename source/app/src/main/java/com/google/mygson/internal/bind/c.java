// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal.bind;

import com.google.mygson.Gson;
import com.google.mygson.TypeAdapter;
import com.google.mygson.TypeAdapterFactory;
import com.google.mygson.reflect.TypeToken;
import java.util.Date;

// Referenced classes of package com.google.mygson.internal.bind:
//            DateTypeAdapter

final class c
    implements TypeAdapterFactory
{

    c()
    {
    }

    public final TypeAdapter create(Gson gson, TypeToken typetoken)
    {
        if (typetoken.getRawType() == java/util/Date)
        {
            return new DateTypeAdapter();
        } else
        {
            return null;
        }
    }
}
