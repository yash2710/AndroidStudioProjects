// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field;


public final class SqlType extends Enum
{

    private static final SqlType $VALUES[];
    public static final SqlType BIG_DECIMAL;
    public static final SqlType BLOB;
    public static final SqlType BOOLEAN;
    public static final SqlType BYTE;
    public static final SqlType BYTE_ARRAY;
    public static final SqlType CHAR;
    public static final SqlType DATE;
    public static final SqlType DOUBLE;
    public static final SqlType FLOAT;
    public static final SqlType INTEGER;
    public static final SqlType LONG;
    public static final SqlType LONG_STRING;
    public static final SqlType OTHER;
    public static final SqlType SERIALIZABLE;
    public static final SqlType SHORT;
    public static final SqlType STRING;
    public static final SqlType UNKNOWN;

    private SqlType(String s, int i)
    {
        super(s, i);
    }

    public static SqlType valueOf(String s)
    {
        return (SqlType)Enum.valueOf(com/j256/ormlite/field/SqlType, s);
    }

    public static SqlType[] values()
    {
        return (SqlType[])$VALUES.clone();
    }

    static 
    {
        STRING = new SqlType("STRING", 0);
        LONG_STRING = new SqlType("LONG_STRING", 1);
        DATE = new SqlType("DATE", 2);
        BOOLEAN = new SqlType("BOOLEAN", 3);
        CHAR = new SqlType("CHAR", 4);
        BYTE = new SqlType("BYTE", 5);
        BYTE_ARRAY = new SqlType("BYTE_ARRAY", 6);
        SHORT = new SqlType("SHORT", 7);
        INTEGER = new SqlType("INTEGER", 8);
        LONG = new SqlType("LONG", 9);
        FLOAT = new SqlType("FLOAT", 10);
        DOUBLE = new SqlType("DOUBLE", 11);
        SERIALIZABLE = new SqlType("SERIALIZABLE", 12);
        BLOB = new SqlType("BLOB", 13);
        BIG_DECIMAL = new SqlType("BIG_DECIMAL", 14);
        OTHER = new SqlType("OTHER", 15);
        UNKNOWN = new SqlType("UNKNOWN", 16);
        SqlType asqltype[] = new SqlType[17];
        asqltype[0] = STRING;
        asqltype[1] = LONG_STRING;
        asqltype[2] = DATE;
        asqltype[3] = BOOLEAN;
        asqltype[4] = CHAR;
        asqltype[5] = BYTE;
        asqltype[6] = BYTE_ARRAY;
        asqltype[7] = SHORT;
        asqltype[8] = INTEGER;
        asqltype[9] = LONG;
        asqltype[10] = FLOAT;
        asqltype[11] = DOUBLE;
        asqltype[12] = SERIALIZABLE;
        asqltype[13] = BLOB;
        asqltype[14] = BIG_DECIMAL;
        asqltype[15] = OTHER;
        asqltype[16] = UNKNOWN;
        $VALUES = asqltype;
    }
}
