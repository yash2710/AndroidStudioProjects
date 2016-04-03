// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;

// Referenced classes of package com.j256.ormlite.field.types:
//            CharacterObjectType

public class CharType extends CharacterObjectType
{

    private static final CharType singleTon = new CharType();

    private CharType()
    {
        SqlType sqltype = SqlType.CHAR;
        Class aclass[] = new Class[1];
        aclass[0] = Character.TYPE;
        super(sqltype, aclass);
    }

    protected CharType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static CharType getSingleton()
    {
        return singleTon;
    }

    public boolean isPrimitive()
    {
        return true;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        Character character = (Character)obj;
        if (character == null || character.charValue() == 0)
        {
            character = null;
        }
        return character;
    }

}
