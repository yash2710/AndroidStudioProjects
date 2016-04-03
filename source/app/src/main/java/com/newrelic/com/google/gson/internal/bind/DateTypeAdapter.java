// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.JsonSyntaxException;
import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.TypeAdapterFactory;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import com.newrelic.com.google.gson.stream.JsonWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package com.newrelic.com.google.gson.internal.bind:
//            c

public final class DateTypeAdapter extends TypeAdapter
{

    public static final TypeAdapterFactory FACTORY = new c();
    private final DateFormat a;
    private final DateFormat b = DateFormat.getDateTimeInstance(2, 2);
    private final DateFormat c;

    public DateTypeAdapter()
    {
        a = DateFormat.getDateTimeInstance(2, 2, Locale.US);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpledateformat.setTimeZone(TimeZone.getTimeZone("UTC"));
        c = simpledateformat;
    }

    private Date a(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Date date3 = b.parse(s);
        Date date1 = date3;
_L1:
        this;
        JVM INSTR monitorexit ;
        return date1;
        ParseException parseexception;
        parseexception;
        Date date2 = a.parse(s);
        date1 = date2;
          goto _L1
        ParseException parseexception1;
        parseexception1;
        Date date = c.parse(s);
        date1 = date;
          goto _L1
        ParseException parseexception2;
        parseexception2;
        throw new JsonSyntaxException(s, parseexception2);
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final volatile Object read(JsonReader jsonreader)
    {
        return read(jsonreader);
    }

    public final Date read(JsonReader jsonreader)
    {
        if (jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
            return null;
        } else
        {
            return a(jsonreader.nextString());
        }
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
        jsonwriter.nullValue();
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        jsonwriter.value(a.format(date));
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

}
