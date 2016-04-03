// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

// Referenced classes of package com.j256.ormlite.field.types:
//            LongObjectType

public class LongType extends LongObjectType
{

    private static final LongType singleTon = new LongType();

    private LongType()
    {
        SqlType sqltype = SqlType.LONG;
        Class aclass[] = new Class[1];
        aclass[0] = Long.TYPE;
        super(sqltype, aclass);
    }

    protected LongType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static LongType getSingleton()
    {
        return singleTon;
    }

    public boolean isPrimitive()
    {
        return true;
    }

}
