// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;


public final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES ACTIVITY_CIRCLE;
    public static final .VALUES GRAPH_OBJECT;
    public static final .VALUES SECTION_HEADER;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/facebook/widget/GraphObjectAdapter$SectionAndItem$Type, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        GRAPH_OBJECT = new <init>("GRAPH_OBJECT", 0);
        SECTION_HEADER = new <init>("SECTION_HEADER", 1);
        ACTIVITY_CIRCLE = new <init>("ACTIVITY_CIRCLE", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = GRAPH_OBJECT;
        aclone[1] = SECTION_HEADER;
        aclone[2] = ACTIVITY_CIRCLE;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
