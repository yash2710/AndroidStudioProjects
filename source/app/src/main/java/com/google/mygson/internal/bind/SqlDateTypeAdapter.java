// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal.bind;

import com.google.mygson.JsonSyntaxException;
import com.google.mygson.TypeAdapter;
import com.google.mygson.TypeAdapterFactory;
import com.google.mygson.stream.JsonReader;
import com.google.mygson.stream.JsonToken;
import com.google.mygson.stream.JsonWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Referenced classes of package com.google.mygson.internal.bind:
//            k

public final class SqlDateTypeAdapter extends TypeAdapter
{

    public static final TypeAdapterFactory FACTORY = new k();
    private final DateFormat a = new SimpleDateFormat("MMM d, yyyy");

    public SqlDateTypeAdapter()
    {
    }

    public final volatile Object read(JsonReader jsonreader)
    {
        return read(jsonreader);
    }

    public final Date read(JsonReader jsonreader)
    {
        this;
        JVM INSTR monitorenter ;
        if (jsonreader.peek() != JsonToken.NULL) goto _L2; else goto _L1
_L1:
        jsonreader.nextNull();
        Date date = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return date;
_L2:
        date = new Date(a.parse(jsonreader.nextString()).getTime());
        if (true) goto _L4; else goto _L3
_L3:
        ParseException parseexception;
        parseexception;
        throw new JsonSyntaxException(parseexception);
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final volatile void write(JsonWriter jsonwriter, Object obj)
    {
        write(jsonwriter, (Date)obj);
    }

    public final void write(JsonWriter jsonwriter, Date date)
    {
        this;
        JVM INSTR monitorenter ;
        if (date != null) goto _L2; else goto _L1
_L1:
        String s1 = null;
_L4:
        jsonwriter.value(s1);
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        String s = a.format(date);
        s1 = s;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

}
