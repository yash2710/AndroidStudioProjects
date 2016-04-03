// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import java.util.List;

// Referenced classes of package com.j256.ormlite.dao:
//            CloseableWrappedIterable

public interface GenericRawResults
    extends CloseableWrappedIterable
{

    public abstract void close();

    public abstract String[] getColumnNames();

    public abstract Object getFirstResult();

    public abstract int getNumberColumns();

    public abstract List getResults();
}
