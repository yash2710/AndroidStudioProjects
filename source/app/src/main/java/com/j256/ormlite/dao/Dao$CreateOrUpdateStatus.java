// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;


public class numLinesChanged
{

    private boolean created;
    private int numLinesChanged;
    private boolean updated;

    public int getNumLinesChanged()
    {
        return numLinesChanged;
    }

    public boolean isCreated()
    {
        return created;
    }

    public boolean isUpdated()
    {
        return updated;
    }

    public (boolean flag, boolean flag1, int i)
    {
        created = flag;
        updated = flag1;
        numLinesChanged = i;
    }
}
