// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import java.util.LinkedHashMap;

class capacity extends LinkedHashMap
{

    private static final long serialVersionUID = 0xc0a06ee7c72ce80cL;
    private final int capacity;

    protected boolean removeEldestEntry(java.util.kedHashMap kedhashmap)
    {
        return size() > capacity;
    }

    public (int i)
    {
        super(i, 0.75F, true);
        capacity = i;
    }
}
