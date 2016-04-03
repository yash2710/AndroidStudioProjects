// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.support;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.SqlType;

// Referenced classes of package com.j256.ormlite.support:
//            DatabaseResults

public interface CompiledStatement
{

    public abstract void cancel();

    public abstract void close();

    public abstract void closeQuietly();

    public abstract int getColumnCount();

    public abstract String getColumnName(int i);

    public abstract int runExecute();

    public abstract DatabaseResults runQuery(ObjectCache objectcache);

    public abstract int runUpdate();

    public abstract void setMaxRows(int i);

    public abstract void setObject(int i, Object obj, SqlType sqltype);

    public abstract void setQueryTimeout(long l);
}
