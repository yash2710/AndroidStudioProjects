// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;


public interface ObjectCache
{

    public abstract void clear(Class class1);

    public abstract void clearAll();

    public abstract Object get(Class class1, Object obj);

    public abstract void put(Class class1, Object obj, Object obj1);

    public abstract void registerClass(Class class1);

    public abstract void remove(Class class1, Object obj);

    public abstract int size(Class class1);

    public abstract int sizeAll();

    public abstract Object updateId(Class class1, Object obj, Object obj1);
}
