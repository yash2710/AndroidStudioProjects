// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal.bind;

import com.google.mygson.Gson;
import com.google.mygson.TypeAdapter;
import com.google.mygson.TypeAdapterFactory;
import com.google.mygson.internal.ConstructorConstructor;
import com.google.mygson.reflect.TypeToken;
import java.util.Map;

// Referenced classes of package com.google.mygson.internal.bind:
//            TypeAdapters, f

public final class MapTypeAdapterFactory
    implements TypeAdapterFactory
{

    private final ConstructorConstructor a;
    private final boolean b;

    public MapTypeAdapterFactory(ConstructorConstructor constructorconstructor, boolean flag)
    {
        a = constructorconstructor;
        b = flag;
    }

    static boolean a(MapTypeAdapterFactory maptypeadapterfactory)
    {
        return maptypeadapterfactory.b;
    }

    public final TypeAdapter create(Gson gson, TypeToken typetoken)
    {
        java.lang.reflect.Type type = typetoken.getType();
        if (!java/util/Map.isAssignableFrom(typetoken.getRawType()))
        {
            return null;
        }
        java.lang.reflect.Type atype[] = com.google.mygson.internal..Gson.Types.getMapKeyAndValueTypes(type, com.google.mygson.internal..Gson.Types.getRawType(type));
        java.lang.reflect.Type type1 = atype[0];
        TypeAdapter typeadapter;
        TypeAdapter typeadapter1;
        com.google.mygson.internal.ObjectConstructor objectconstructor;
        if (type1 == Boolean.TYPE || type1 == java/lang/Boolean)
        {
            typeadapter = TypeAdapters.BOOLEAN_AS_STRING;
        } else
        {
            typeadapter = gson.getAdapter(TypeToken.get(type1));
        }
        typeadapter1 = gson.getAdapter(TypeToken.get(atype[1]));
        objectconstructor = a.get(typetoken);
        return new f(this, gson, atype[0], typeadapter, atype[1], typeadapter1, objectconstructor);
    }
}