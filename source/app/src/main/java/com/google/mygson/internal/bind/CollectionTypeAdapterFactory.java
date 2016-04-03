// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal.bind;

import com.google.mygson.Gson;
import com.google.mygson.TypeAdapter;
import com.google.mygson.TypeAdapterFactory;
import com.google.mygson.internal.ConstructorConstructor;
import com.google.mygson.reflect.TypeToken;
import java.util.Collection;

// Referenced classes of package com.google.mygson.internal.bind:
//            b

public final class CollectionTypeAdapterFactory
    implements TypeAdapterFactory
{

    private final ConstructorConstructor a;

    public CollectionTypeAdapterFactory(ConstructorConstructor constructorconstructor)
    {
        a = constructorconstructor;
    }

    public final TypeAdapter create(Gson gson, TypeToken typetoken)
    {
        java.lang.reflect.Type type = typetoken.getType();
        Class class1 = typetoken.getRawType();
        if (!java/util/Collection.isAssignableFrom(class1))
        {
            return null;
        } else
        {
            java.lang.reflect.Type type1 = com.google.mygson.internal..Gson.Types.getCollectionElementType(type, class1);
            return new b(gson, type1, gson.getAdapter(TypeToken.get(type1)), a.get(typetoken));
        }
    }
}
