// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;


public final class okForExecute extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES DELETE;
    public static final .VALUES EXECUTE;
    public static final .VALUES SELECT;
    public static final .VALUES SELECT_LONG;
    public static final .VALUES SELECT_RAW;
    public static final .VALUES UPDATE;
    private final boolean okForExecute;
    private final boolean okForQuery;
    private final boolean okForStatementBuilder;
    private final boolean okForUpdate;

    public static okForExecute valueOf(String s)
    {
        return (okForExecute)Enum.valueOf(com/j256/ormlite/stmt/StatementBuilder$StatementType, s);
    }

    public static okForExecute[] values()
    {
        return (okForExecute[])$VALUES.clone();
    }

    public final boolean isOkForExecute()
    {
        return okForExecute;
    }

    public final boolean isOkForQuery()
    {
        return okForQuery;
    }

    public final boolean isOkForStatementBuilder()
    {
        return okForStatementBuilder;
    }

    public final boolean isOkForUpdate()
    {
        return okForUpdate;
    }

    static 
    {
        SELECT = new <init>("SELECT", 0, true, true, false, false);
        SELECT_LONG = new <init>("SELECT_LONG", 1, true, true, false, false);
        SELECT_RAW = new <init>("SELECT_RAW", 2, true, true, false, false);
        UPDATE = new <init>("UPDATE", 3, true, false, true, false);
        DELETE = new <init>("DELETE", 4, true, false, true, false);
        EXECUTE = new <init>("EXECUTE", 5, false, false, false, true);
        okForUpdate aokforupdate[] = new <init>[6];
        aokforupdate[0] = SELECT;
        aokforupdate[1] = SELECT_LONG;
        aokforupdate[2] = SELECT_RAW;
        aokforupdate[3] = UPDATE;
        aokforupdate[4] = DELETE;
        aokforupdate[5] = EXECUTE;
        $VALUES = aokforupdate;
    }

    private (String s, int i, boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        super(s, i);
        okForStatementBuilder = flag;
        okForQuery = flag1;
        okForUpdate = flag2;
        okForExecute = flag3;
    }
}
