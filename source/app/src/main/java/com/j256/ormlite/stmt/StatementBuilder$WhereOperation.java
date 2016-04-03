// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;


public final class after extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES AND;
    public static final .VALUES FIRST;
    public static final .VALUES OR;
    private final String after;
    private final String before;

    public static after valueOf(String s)
    {
        return (after)Enum.valueOf(com/j256/ormlite/stmt/StatementBuilder$WhereOperation, s);
    }

    public static after[] values()
    {
        return (after[])$VALUES.clone();
    }

    public final void appendAfter(StringBuilder stringbuilder)
    {
        if (after != null)
        {
            stringbuilder.append(after);
        }
    }

    public final void appendBefore(StringBuilder stringbuilder)
    {
        if (before != null)
        {
            stringbuilder.append(before);
        }
    }

    static 
    {
        FIRST = new <init>("FIRST", 0, "WHERE ", null);
        AND = new <init>("AND", 1, "AND (", ") ");
        OR = new <init>("OR", 2, "OR (", ") ");
        before abefore[] = new <init>[3];
        abefore[0] = FIRST;
        abefore[1] = AND;
        abefore[2] = OR;
        $VALUES = abefore;
    }

    private (String s, int i, String s1, String s2)
    {
        super(s, i);
        before = s1;
        after = s2;
    }
}
