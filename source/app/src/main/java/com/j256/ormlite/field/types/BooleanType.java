// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

// Referenced classes of package com.j256.ormlite.field.types:
//            BooleanObjectType

public class BooleanType extends BooleanObjectType
{

    private static final BooleanType singleTon = new BooleanType();

    private BooleanType()
    {
        SqlType sqltype = SqlType.BOOLEAN;
        Class aclass[] = new Class[1];
        aclass[0] = Boolean.TYPE;
        super(sqltype, aclass);
    }

    protected BooleanType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static BooleanType getSingleton()
    {
        return singleTon;
    }

    public boolean isPrimitive()
    {
        return true;
    }

}
