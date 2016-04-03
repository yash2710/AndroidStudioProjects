// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.support;


// Referenced classes of package com.j256.ormlite.support:
//            DatabaseConnection

class nestedC
{

    public final DatabaseConnection connection;
    private int nestedC;

    public int decrementAndGet()
    {
        nestedC = -1 + nestedC;
        return nestedC;
    }

    public void increment()
    {
        nestedC = 1 + nestedC;
    }

    public (DatabaseConnection databaseconnection)
    {
        connection = databaseconnection;
        nestedC = 1;
    }
}
