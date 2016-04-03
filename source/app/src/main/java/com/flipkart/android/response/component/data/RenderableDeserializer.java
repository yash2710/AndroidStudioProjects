// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data;

import com.flipkart.android.response.component.data.customvalues.Renderable;
import com.flipkart.logging.FkLogger;
import com.google.mygson.JsonDeserializationContext;
import com.google.mygson.JsonDeserializer;
import com.google.mygson.JsonElement;
import com.google.mygson.JsonObject;
import java.lang.reflect.Type;

public class RenderableDeserializer
    implements JsonDeserializer
{

    private final String PACKAGE_BASE = "com.flipkart.android.response.component.data.customvalues.";

    public RenderableDeserializer()
    {
    }

    public Renderable deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext)
    {
        Renderable renderable;
        try
        {
            String s = jsonelement.getAsJsonObject().get("type").getAsString();
            renderable = (Renderable)jsondeserializationcontext.deserialize(jsonelement, Class.forName((new StringBuilder("com.flipkart.android.response.component.data.customvalues.")).append(s).toString()));
        }
        catch (Exception exception)
        {
            FkLogger.debug("Test", exception.toString());
            FkLogger.printStackTrace(exception);
            return null;
        }
        return renderable;
    }

    public volatile Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext)
    {
        return deserialize(jsonelement, type, jsondeserializationcontext);
    }
}
