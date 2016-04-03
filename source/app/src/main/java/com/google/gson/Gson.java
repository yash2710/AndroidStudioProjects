// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.gson:
//            FieldNamingPolicy, LongSerializationPolicy, g, h, 
//            k, i, j, JsonIOException, 
//            JsonSyntaxException, TypeAdapter, l, TypeAdapterFactory, 
//            JsonNull, JsonDeserializationContext, JsonSerializationContext, FieldNamingStrategy, 
//            JsonElement

public final class Gson
{

    final JsonDeserializationContext a;
    final JsonSerializationContext b;
    private final ThreadLocal c;
    private final Map d;
    private final List e;
    private final ConstructorConstructor f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final boolean j;

    public Gson()
    {
        this(Excluder.DEFAULT, ((FieldNamingStrategy) (FieldNamingPolicy.IDENTITY)), Collections.emptyMap(), false, false, false, true, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    Gson(Excluder excluder, FieldNamingStrategy fieldnamingstrategy, Map map, boolean flag, boolean flag1, boolean flag2, boolean flag3, 
            boolean flag4, boolean flag5, LongSerializationPolicy longserializationpolicy, List list)
    {
        c = new ThreadLocal();
        d = Collections.synchronizedMap(new HashMap());
        a = new g(this);
        b = new h(this);
        f = new ConstructorConstructor(map);
        g = flag;
        i = flag2;
        h = flag3;
        j = flag4;
        ArrayList arraylist = new ArrayList();
        arraylist.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        arraylist.add(ObjectTypeAdapter.FACTORY);
        arraylist.add(excluder);
        arraylist.addAll(list);
        arraylist.add(TypeAdapters.STRING_FACTORY);
        arraylist.add(TypeAdapters.INTEGER_FACTORY);
        arraylist.add(TypeAdapters.BOOLEAN_FACTORY);
        arraylist.add(TypeAdapters.BYTE_FACTORY);
        arraylist.add(TypeAdapters.SHORT_FACTORY);
        Class class1 = Long.TYPE;
        Object obj;
        Class class2;
        Object obj1;
        Class class3;
        Object obj2;
        if (longserializationpolicy == LongSerializationPolicy.DEFAULT)
        {
            obj = TypeAdapters.LONG;
        } else
        {
            obj = new k(this);
        }
        arraylist.add(TypeAdapters.newFactory(class1, java/lang/Long, ((TypeAdapter) (obj))));
        class2 = Double.TYPE;
        if (flag5)
        {
            obj1 = TypeAdapters.DOUBLE;
        } else
        {
            obj1 = new i(this);
        }
        arraylist.add(TypeAdapters.newFactory(class2, java/lang/Double, ((TypeAdapter) (obj1))));
        class3 = Float.TYPE;
        if (flag5)
        {
            obj2 = TypeAdapters.FLOAT;
        } else
        {
            obj2 = new j(this);
        }
        arraylist.add(TypeAdapters.newFactory(class3, java/lang/Float, ((TypeAdapter) (obj2))));
        arraylist.add(TypeAdapters.NUMBER_FACTORY);
        arraylist.add(TypeAdapters.CHARACTER_FACTORY);
        arraylist.add(TypeAdapters.STRING_BUILDER_FACTORY);
        arraylist.add(TypeAdapters.STRING_BUFFER_FACTORY);
        arraylist.add(TypeAdapters.newFactory(java/math/BigDecimal, TypeAdapters.BIG_DECIMAL));
        arraylist.add(TypeAdapters.newFactory(java/math/BigInteger, TypeAdapters.BIG_INTEGER));
        arraylist.add(TypeAdapters.URL_FACTORY);
        arraylist.add(TypeAdapters.URI_FACTORY);
        arraylist.add(TypeAdapters.UUID_FACTORY);
        arraylist.add(TypeAdapters.LOCALE_FACTORY);
        arraylist.add(TypeAdapters.INET_ADDRESS_FACTORY);
        arraylist.add(TypeAdapters.BIT_SET_FACTORY);
        arraylist.add(DateTypeAdapter.FACTORY);
        arraylist.add(TypeAdapters.CALENDAR_FACTORY);
        arraylist.add(TimeTypeAdapter.FACTORY);
        arraylist.add(SqlDateTypeAdapter.FACTORY);
        arraylist.add(TypeAdapters.TIMESTAMP_FACTORY);
        arraylist.add(ArrayTypeAdapter.FACTORY);
        arraylist.add(TypeAdapters.ENUM_FACTORY);
        arraylist.add(TypeAdapters.CLASS_FACTORY);
        arraylist.add(new CollectionTypeAdapterFactory(f));
        arraylist.add(new MapTypeAdapterFactory(f, flag1));
        arraylist.add(new JsonAdapterAnnotationTypeAdapterFactory(f));
        arraylist.add(new ReflectiveTypeAdapterFactory(f, fieldnamingstrategy, excluder));
        e = Collections.unmodifiableList(arraylist);
    }

    private JsonWriter a(Writer writer)
    {
        if (i)
        {
            writer.write(")]}'\n");
        }
        JsonWriter jsonwriter = new JsonWriter(writer);
        if (j)
        {
            jsonwriter.setIndent("  ");
        }
        jsonwriter.setSerializeNulls(g);
        return jsonwriter;
    }

    static void a(Gson gson, double d1)
    {
        if (Double.isNaN(d1) || Double.isInfinite(d1))
        {
            throw new IllegalArgumentException((new StringBuilder()).append(d1).append(" is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.").toString());
        } else
        {
            return;
        }
    }

    private static void a(Object obj, JsonReader jsonreader)
    {
        if (obj != null)
        {
            try
            {
                if (jsonreader.peek() != JsonToken.END_DOCUMENT)
                {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            }
            catch (MalformedJsonException malformedjsonexception)
            {
                throw new JsonSyntaxException(malformedjsonexception);
            }
            catch (IOException ioexception)
            {
                throw new JsonIOException(ioexception);
            }
        }
    }

    public final Object fromJson(JsonElement jsonelement, Class class1)
    {
        Object obj = fromJson(jsonelement, ((Type) (class1)));
        return Primitives.wrap(class1).cast(obj);
    }

    public final Object fromJson(JsonElement jsonelement, Type type)
    {
        if (jsonelement == null)
        {
            return null;
        } else
        {
            return fromJson(((JsonReader) (new JsonTreeReader(jsonelement))), type);
        }
    }

    public final Object fromJson(JsonReader jsonreader, Type type)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = jsonreader.isLenient();
        jsonreader.setLenient(flag);
        jsonreader.peek();
        flag = false;
        Object obj = getAdapter(TypeToken.get(type)).read(jsonreader);
        jsonreader.setLenient(flag1);
        return obj;
        EOFException eofexception;
        eofexception;
        if (flag)
        {
            jsonreader.setLenient(flag1);
            return null;
        }
        throw new JsonSyntaxException(eofexception);
        Exception exception;
        exception;
        jsonreader.setLenient(flag1);
        throw exception;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        throw new JsonSyntaxException(illegalstateexception);
        IOException ioexception;
        ioexception;
        throw new JsonSyntaxException(ioexception);
    }

    public final Object fromJson(Reader reader, Class class1)
    {
        JsonReader jsonreader = new JsonReader(reader);
        Object obj = fromJson(jsonreader, ((Type) (class1)));
        a(obj, jsonreader);
        return Primitives.wrap(class1).cast(obj);
    }

    public final Object fromJson(Reader reader, Type type)
    {
        JsonReader jsonreader = new JsonReader(reader);
        Object obj = fromJson(jsonreader, type);
        a(obj, jsonreader);
        return obj;
    }

    public final Object fromJson(String s, Class class1)
    {
        Object obj = fromJson(s, ((Type) (class1)));
        return Primitives.wrap(class1).cast(obj);
    }

    public final Object fromJson(String s, Type type)
    {
        if (s == null)
        {
            return null;
        } else
        {
            return fromJson(((Reader) (new StringReader(s))), type);
        }
    }

    public final TypeAdapter getAdapter(TypeToken typetoken)
    {
        Object obj = (TypeAdapter)d.get(typetoken);
        if (obj == null) goto _L2; else goto _L1
_L1:
        return ((TypeAdapter) (obj));
_L2:
        Map map = (Map)c.get();
        Object obj1;
        boolean flag;
        l l1;
        Exception exception;
        Iterator iterator;
        if (map == null)
        {
            HashMap hashmap = new HashMap();
            c.set(hashmap);
            obj1 = hashmap;
            flag = true;
        } else
        {
            obj1 = map;
            flag = false;
        }
        obj = (l)((Map) (obj1)).get(typetoken);
        if (obj != null) goto _L1; else goto _L3
_L3:
        l1 = new l();
        ((Map) (obj1)).put(typetoken, l1);
        iterator = e.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_180;
            }
            obj = ((TypeAdapterFactory)iterator.next()).create(this, typetoken);
        } while (obj == null);
        l1.setDelegate(((TypeAdapter) (obj)));
        d.put(typetoken, obj);
        ((Map) (obj1)).remove(typetoken);
        if (!flag) goto _L1; else goto _L4
_L4:
        c.remove();
        return ((TypeAdapter) (obj));
        throw new IllegalArgumentException((new StringBuilder("GSON cannot handle ")).append(typetoken).toString());
        exception;
        ((Map) (obj1)).remove(typetoken);
        if (flag)
        {
            c.remove();
        }
        throw exception;
    }

    public final TypeAdapter getAdapter(Class class1)
    {
        return getAdapter(TypeToken.get(class1));
    }

    public final TypeAdapter getDelegateAdapter(TypeAdapterFactory typeadapterfactory, TypeToken typetoken)
    {
        Iterator iterator = e.iterator();
        boolean flag = false;
        while (iterator.hasNext()) 
        {
            TypeAdapterFactory typeadapterfactory1 = (TypeAdapterFactory)iterator.next();
            if (!flag)
            {
                if (typeadapterfactory1 == typeadapterfactory)
                {
                    flag = true;
                }
            } else
            {
                TypeAdapter typeadapter = typeadapterfactory1.create(this, typetoken);
                if (typeadapter != null)
                {
                    return typeadapter;
                }
            }
        }
        throw new IllegalArgumentException((new StringBuilder("GSON cannot serialize ")).append(typetoken).toString());
    }

    public final String toJson(JsonElement jsonelement)
    {
        StringWriter stringwriter = new StringWriter();
        toJson(jsonelement, ((Appendable) (stringwriter)));
        return stringwriter.toString();
    }

    public final String toJson(Object obj)
    {
        if (obj == null)
        {
            return toJson(((JsonElement) (JsonNull.INSTANCE)));
        } else
        {
            return toJson(obj, ((Type) (obj.getClass())));
        }
    }

    public final String toJson(Object obj, Type type)
    {
        StringWriter stringwriter = new StringWriter();
        toJson(obj, type, ((Appendable) (stringwriter)));
        return stringwriter.toString();
    }

    public final void toJson(JsonElement jsonelement, JsonWriter jsonwriter)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        flag = jsonwriter.isLenient();
        jsonwriter.setLenient(true);
        flag1 = jsonwriter.isHtmlSafe();
        jsonwriter.setHtmlSafe(h);
        flag2 = jsonwriter.getSerializeNulls();
        jsonwriter.setSerializeNulls(g);
        Streams.write(jsonelement, jsonwriter);
        jsonwriter.setLenient(flag);
        jsonwriter.setHtmlSafe(flag1);
        jsonwriter.setSerializeNulls(flag2);
        return;
        IOException ioexception;
        ioexception;
        throw new JsonIOException(ioexception);
        Exception exception;
        exception;
        jsonwriter.setLenient(flag);
        jsonwriter.setHtmlSafe(flag1);
        jsonwriter.setSerializeNulls(flag2);
        throw exception;
    }

    public final void toJson(JsonElement jsonelement, Appendable appendable)
    {
        try
        {
            toJson(jsonelement, a(Streams.writerForAppendable(appendable)));
            return;
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }
    }

    public final void toJson(Object obj, Appendable appendable)
    {
        if (obj != null)
        {
            toJson(obj, ((Type) (obj.getClass())), appendable);
            return;
        } else
        {
            toJson(((JsonElement) (JsonNull.INSTANCE)), appendable);
            return;
        }
    }

    public final void toJson(Object obj, Type type, JsonWriter jsonwriter)
    {
        TypeAdapter typeadapter;
        boolean flag;
        boolean flag1;
        boolean flag2;
        typeadapter = getAdapter(TypeToken.get(type));
        flag = jsonwriter.isLenient();
        jsonwriter.setLenient(true);
        flag1 = jsonwriter.isHtmlSafe();
        jsonwriter.setHtmlSafe(h);
        flag2 = jsonwriter.getSerializeNulls();
        jsonwriter.setSerializeNulls(g);
        typeadapter.write(jsonwriter, obj);
        jsonwriter.setLenient(flag);
        jsonwriter.setHtmlSafe(flag1);
        jsonwriter.setSerializeNulls(flag2);
        return;
        IOException ioexception;
        ioexception;
        throw new JsonIOException(ioexception);
        Exception exception;
        exception;
        jsonwriter.setLenient(flag);
        jsonwriter.setHtmlSafe(flag1);
        jsonwriter.setSerializeNulls(flag2);
        throw exception;
    }

    public final void toJson(Object obj, Type type, Appendable appendable)
    {
        try
        {
            toJson(obj, type, a(Streams.writerForAppendable(appendable)));
            return;
        }
        catch (IOException ioexception)
        {
            throw new JsonIOException(ioexception);
        }
    }

    public final JsonElement toJsonTree(Object obj)
    {
        if (obj == null)
        {
            return JsonNull.INSTANCE;
        } else
        {
            return toJsonTree(obj, ((Type) (obj.getClass())));
        }
    }

    public final JsonElement toJsonTree(Object obj, Type type)
    {
        JsonTreeWriter jsontreewriter = new JsonTreeWriter();
        toJson(obj, type, jsontreewriter);
        return jsontreewriter.get();
    }

    public final String toString()
    {
        return (new StringBuilder("{serializeNulls:")).append(g).append("factories:").append(e).append(",instanceCreators:").append(f).append("}").toString();
    }
}
