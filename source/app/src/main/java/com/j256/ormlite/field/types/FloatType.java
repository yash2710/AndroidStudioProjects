// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

// Referenced classes of package com.j256.ormlite.field.types:
//            FloatObjectType

public class FloatType extends FloatObjectType
{

    private static final FloatType singleTon = new FloatType();

    private FloatType()
    {
        SqlType sqltype = SqlType.FLOAT;
        Class aclass[] = new Class[1];
        aclass[0] = Float.TYPE;
        super(sqltype, aclass);
    }

    protected FloatType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static FloatType getSingleton()
    {
        return singleTon;
    }

    public boolean isPrimitive()
    {
        return true;
    }

}
