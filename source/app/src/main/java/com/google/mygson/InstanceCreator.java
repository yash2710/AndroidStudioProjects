// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson;

import java.lang.reflect.Type;

public interface InstanceCreator
{

    public abstract Object createInstance(Type type);
}
