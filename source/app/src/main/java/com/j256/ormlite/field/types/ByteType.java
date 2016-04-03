// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

// Referenced classes of package com.j256.ormlite.field.types:
//            ByteObjectType

public class ByteType extends ByteObjectType
{

    private static final ByteType singleTon = new ByteType();

    private ByteType()
    {
        SqlType sqltype = SqlType.BYTE;
        Class aclass[] = new Class[1];
        aclass[0] = Byte.TYPE;
        super(sqltype, aclass);
    }

    protected ByteType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static ByteType getSingleton()
    {
        return singleTon;
    }

    public boolean isPrimitive()
    {
        return true;
    }

}
