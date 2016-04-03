// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

// Referenced classes of package com.google.gson.internal.bind:
//            JsonAdapterAnnotationTypeAdapterFactory, i, j

public final class ReflectiveTypeAdapterFactory
    implements TypeAdapterFactory
{

    private final ConstructorConstructor a;
    private final FieldNamingStrategy b;
    private final Excluder c;

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorconstructor, FieldNamingStrategy fieldnamingstrategy, Excluder excluder)
    {
        a = constructorconstructor;
        b = fieldnamingstrategy;
        c = excluder;
    }

    static TypeAdapter a(ReflectiveTypeAdapterFactory reflectivetypeadapterfactory, Gson gson, Field field, TypeToken typetoken)
    {
        JsonAdapter jsonadapter = (JsonAdapter)field.getAnnotation(com/google/gson/annotations/JsonAdapter);
        if (jsonadapter != null)
        {
            TypeAdapter typeadapter = JsonAdapterAnnotationTypeAdapterFactory.a(reflectivetypeadapterfactory.a, gson, typetoken, jsonadapter);
            if (typeadapter != null)
            {
                return typeadapter;
            }
        }
        return gson.getAdapter(typetoken);
    }

    private Map a(Gson gson, TypeToken typetoken, Class class1)
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        if (class1.isInterface())
        {
            return linkedhashmap;
        }
        java.lang.reflect.Type type = typetoken.getType();
        for (; class1 != java/lang/Object; class1 = typetoken.getRawType())
        {
            Field afield[] = class1.getDeclaredFields();
            int k = afield.length;
            SerializedName serializedname;
            String s;
            for (int l = 0; l < k; l++)
            {
                Field field = afield[l];
                boolean flag = excludeField(field, true);
                boolean flag1 = excludeField(field, false);
                if (!flag && !flag1)
                {
                    continue;
                }
                field.setAccessible(true);
                java.lang.reflect.Type type3 = typetoken.getType();
                java.lang.reflect.Type type4 = field.getGenericType();
                java.lang.reflect.Type type5 = com.google.gson.internal..Gson.Types.resolve(type3, class1, type4);
                serializedname = (SerializedName)field.getAnnotation(com/google/gson/annotations/SerializedName);
                TypeToken typetoken1;
                i i1;
                j j1;
                if (serializedname == null)
                {
                    s = b.translateName(field);
                } else
                {
                    s = serializedname.value();
                }
                typetoken1 = TypeToken.get(type5);
                i1 = new i(this, s, flag, flag1, gson, field, typetoken1, Primitives.isPrimitive(typetoken1.getRawType()));
                j1 = (j)linkedhashmap.put(((j) (i1)).a, i1);
                if (j1 != null)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append(type).append(" declares multiple JSON fields named ").append(j1.a).toString());
                }
            }

            java.lang.reflect.Type type1 = typetoken.getType();
            java.lang.reflect.Type type2 = class1.getGenericSuperclass();
            typetoken = TypeToken.get(com.google.gson.internal..Gson.Types.resolve(type1, class1, type2));
        }

        return linkedhashmap;
    }

    public final TypeAdapter create(Gson gson, TypeToken typetoken)
    {
        Class class1 = typetoken.getRawType();
        if (!java/lang/Object.isAssignableFrom(class1))
        {
            return null;
        } else
        {
            return new Adapter(a.get(typetoken), a(gson, typetoken, class1), (byte)0);
        }
    }

    public final boolean excludeField(Field field, boolean flag)
    {
        return !c.excludeClass(field.getType(), flag) && !c.excludeField(field, flag);
    }

    private class Adapter extends TypeAdapter
    {

        private final ObjectConstructor a;
        private final Map b;

        public final Object read(JsonReader jsonreader)
        {
            Object obj;
            if (jsonreader.peek() == JsonToken.NULL)
            {
                jsonreader.nextNull();
                return null;
            }
            obj = a.construct();
            jsonreader.beginObject();
_L1:
            j j1;
            if (!jsonreader.hasNext())
            {
                break MISSING_BLOCK_LABEL_111;
            }
            String s = jsonreader.nextName();
            j1 = (j)b.get(s);
            if (j1 == null)
            {
                break MISSING_BLOCK_LABEL_72;
            }
            if (j1.c)
            {
                break MISSING_BLOCK_LABEL_91;
            }
            IllegalStateException illegalstateexception;
            jsonreader.skipValue();
              goto _L1
            try
            {
                j1.a(jsonreader, obj);
            }
            // Misplaced declaration of an exception variable
            catch (IllegalStateException illegalstateexception)
            {
                throw new JsonSyntaxException(illegalstateexception);
            }
            catch (IllegalAccessException illegalaccessexception)
            {
                throw new AssertionError(illegalaccessexception);
            }
              goto _L1
            jsonreader.endObject();
            return obj;
        }

        public final void write(JsonWriter jsonwriter, Object obj)
        {
            if (obj == null)
            {
                jsonwriter.nullValue();
                return;
            }
            jsonwriter.beginObject();
            try
            {
                Iterator iterator = b.values().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    j j1 = (j)iterator.next();
                    if (j1.b)
                    {
                        jsonwriter.name(j1.a);
                        j1.a(jsonwriter, obj);
                    }
                } while (true);
            }
            catch (IllegalAccessException illegalaccessexception)
            {
                throw new AssertionError();
            }
            jsonwriter.endObject();
        }

        private Adapter(ObjectConstructor objectconstructor, Map map)
        {
            a = objectconstructor;
            b = map;
        }

        Adapter(ObjectConstructor objectconstructor, Map map, byte byte0)
        {
            this(objectconstructor, map);
        }
    }

}
