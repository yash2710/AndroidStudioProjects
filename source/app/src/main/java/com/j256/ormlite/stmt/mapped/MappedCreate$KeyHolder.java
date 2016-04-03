// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.support.GeneratedKeyHolder;
import java.sql.SQLException;

class <init>
    implements GeneratedKeyHolder
{

    Number key;

    public void addKey(Number number)
    {
        if (key == null)
        {
            key = number;
            return;
        } else
        {
            throw new SQLException((new StringBuilder("generated key has already been set to ")).append(key).append(", now set to ").append(number).toString());
        }
    }

    public Number getKey()
    {
        return key;
    }

    private _cls9()
    {
    }

    _cls9(_cls9 _pcls9)
    {
        this();
    }
}
