// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

// Referenced classes of package com.j256.ormlite.field.types:
//            IntegerObjectType

public class IntType extends IntegerObjectType
{

    private static final IntType singleTon = new IntType();

    private IntType()
    {
        SqlType sqltype = SqlType.INTEGER;
        Class aclass[] = new Class[1];
        aclass[0] = Integer.TYPE;
        super(sqltype, aclass);
    }

    protected IntType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static IntType getSingleton()
    {
        return singleTon;
    }

    public boolean isPrimitive()
    {
        return true;
    }

}
