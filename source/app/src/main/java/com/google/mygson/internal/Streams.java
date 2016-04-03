// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal;

import com.google.mygson.JsonElement;
import com.google.mygson.JsonIOException;
import com.google.mygson.JsonNull;
import com.google.mygson.JsonSyntaxException;
import com.google.mygson.TypeAdapter;
import com.google.mygson.internal.bind.TypeAdapters;
import com.google.mygson.stream.JsonReader;
import com.google.mygson.stream.JsonWriter;
import com.google.mygson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

// Referenced classes of package com.google.mygson.internal:
//            x

public final class Streams
{

    public Streams()
    {
    }

    public static JsonElement parse(JsonReader jsonreader)
    {
        boolean flag = true;
        JsonElement jsonelement;
        try
        {
            jsonreader.peek();
        }
        catch (EOFException eofexception)
        {
            if (flag)
            {
                return JsonNull.INSTANCE;
            } else
            {
                throw new JsonSyntaxException(eofexception);
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
        catch (NumberFormatException numberformatexception)
        {
            throw new JsonSyntaxException(numberformatexception);
        }
        flag = false;
        jsonelement = (JsonElement)TypeAdapters.JSON_ELEMENT.read(jsonreader);
        return jsonelement;
    }

    public static void write(JsonElement jsonelement, JsonWriter jsonwriter)
    {
        TypeAdapters.JSON_ELEMENT.write(jsonwriter, jsonelement);
    }

    public static Writer writerForAppendable(Appendable appendable)
    {
        if (appendable instanceof Writer)
        {
            return (Writer)appendable;
        } else
        {
            return new x(appendable, (byte)0);
        }
    }
}
