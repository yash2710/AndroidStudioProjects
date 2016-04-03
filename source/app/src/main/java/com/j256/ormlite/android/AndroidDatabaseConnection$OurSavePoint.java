// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android;

import java.sql.Savepoint;

class name
    implements Savepoint
{

    private String name;

    public int getSavepointId()
    {
        return 0;
    }

    public String getSavepointName()
    {
        return name;
    }

    public (String s)
    {
        name = s;
    }
}
