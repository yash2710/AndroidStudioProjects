// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal.bind;

import com.google.mygson.FieldNamingStrategy;
import com.google.mygson.Gson;
import com.google.mygson.TypeAdapter;
import com.google.mygson.TypeAdapterFactory;
import com.google.mygson.annotations.SerializedName;
import com.google.mygson.internal.ConstructorConstructor;
import com.google.mygson.internal.Excluder;
import com.google.mygson.internal.Primitives;
import com.google.mygson.reflect.TypeToken;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

// Referenced classes of package com.google.mygson.internal.bind:
//            i, j

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
                java.lang.reflect.Type type5 = com.google.mygson.internal..Gson.Types.resolve(type3, class1, type4);
                serializedname = (SerializedName)field.getAnnotation(com/google/mygson/annotations/SerializedName);
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
                i1 = new i(this, s, flag, flag1, gson, typetoken1, field, Primitives.isPrimitive(typetoken1.getRawType()));
                j1 = (j)linkedhashmap.put(((j) (i1)).a, i1);
                if (j1 != null)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append(type).append(" declares multiple JSON fields named ").append(j1.a).toString());
                }
            }

            java.lang.reflect.Type type1 = typetoken.getType();
            java.lang.reflect.Type type2 = class1.getGenericSuperclass();
            typetoken = TypeToken.get(com.google.mygson.internal..Gson.Types.resolve(type1, class1, type2));
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
