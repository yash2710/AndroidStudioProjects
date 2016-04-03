// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data;

import com.flipkart.android.response.component.data.customvalues.Renderable;
import com.flipkart.logging.FkLogger;
import com.google.mygson.JsonElement;
import com.google.mygson.JsonSerializationContext;
import com.google.mygson.JsonSerializer;
import java.lang.reflect.Type;

public class RenderableSerializer
    implements JsonSerializer
{

    private final String PACKAGE_BASE = "com.flipkart.android.response.component.data.customvalues.";

    public RenderableSerializer()
    {
    }

    public JsonElement serialize(Renderable renderable, Type type, JsonSerializationContext jsonserializationcontext)
    {
        JsonElement jsonelement;
        try
        {
            String s = renderable.getType();
            jsonelement = jsonserializationcontext.serialize(Class.forName((new StringBuilder("com.flipkart.android.response.component.data.customvalues.")).append(s).toString()).cast(renderable));
        }
        catch (Exception exception)
        {
            FkLogger.debug("Test", (new StringBuilder(" exception ")).append(exception.toString()).toString());
            return null;
        }
        return jsonelement;
    }

    public volatile JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonserializationcontext)
    {
        return serialize((Renderable)obj, type, jsonserializationcontext);
    }
}
