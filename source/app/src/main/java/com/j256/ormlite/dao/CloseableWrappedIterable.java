// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;


// Referenced classes of package com.j256.ormlite.dao:
//            CloseableIterable

public interface CloseableWrappedIterable
    extends CloseableIterable
{

    public abstract void close();
}
