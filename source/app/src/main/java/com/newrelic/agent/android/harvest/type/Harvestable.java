// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;

public interface Harvestable
{

    public abstract JsonElement asJson();

    public abstract JsonArray asJsonArray();

    public abstract JsonObject asJsonObject();

    public abstract JsonPrimitive asJsonPrimitive();

    public abstract Type getType();

    public abstract String toJsonString();
}
