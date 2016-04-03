// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import com.j256.ormlite.support.DatabaseResults;
import java.util.Iterator;

public interface CloseableIterator
    extends Iterator
{

    public abstract void close();

    public abstract void closeQuietly();

    public abstract Object current();

    public abstract Object first();

    public abstract DatabaseResults getRawResults();

    public abstract Object moveRelative(int i);

    public abstract void moveToNext();

    public abstract Object nextThrow();

    public abstract Object previous();
}
