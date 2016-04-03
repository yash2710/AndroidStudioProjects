// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import com.flipkart.android.response.discovery.OmnitureData;
import com.google.mygson.JsonDeserializationContext;
import com.google.mygson.JsonDeserializer;
import com.google.mygson.JsonElement;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.lang.reflect.Type;
import org.json.JSONObject;

public class OmnitureDataDeSerializer
    implements JsonDeserializer
{

    public OmnitureDataDeSerializer()
    {
    }

    public OmnitureData deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext)
    {
        OmnitureData omnituredata;
        try
        {
            JSONObject _tmp = JVM INSTR new #17  <Class JSONObject>;
            JSONObject jsonobject = JSONObjectInstrumentation.init(jsonelement.toString());
            omnituredata = new OmnitureData();
            omnituredata.setCategory(jsonobject.optString("anlt_category"));
            omnituredata.setVertical(jsonobject.optString("anlt_vertical"));
            omnituredata.setSuperCategory(jsonobject.optString("anlt_supercategory"));
            omnituredata.setSubCategory(jsonobject.optString("anlt_subcategory"));
        }
        catch (Exception exception)
        {
            return null;
        }
        return omnituredata;
    }

    public volatile Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext)
    {
        return deserialize(jsonelement, type, jsondeserializationcontext);
    }
}
