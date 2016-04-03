// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing;


public final class EncodeHintType extends Enum
{

    public static final EncodeHintType CHARACTER_SET;
    public static final EncodeHintType DATA_MATRIX_SHAPE;
    public static final EncodeHintType ERROR_CORRECTION;
    public static final EncodeHintType MARGIN;
    public static final EncodeHintType MAX_SIZE;
    public static final EncodeHintType MIN_SIZE;
    public static final EncodeHintType PDF417_COMPACT;
    public static final EncodeHintType PDF417_COMPACTION;
    public static final EncodeHintType PDF417_DIMENSIONS;
    private static final EncodeHintType a[];

    private EncodeHintType(String s, int i)
    {
        super(s, i);
    }

    public static EncodeHintType valueOf(String s)
    {
        return (EncodeHintType)Enum.valueOf(com/google/zxing/EncodeHintType, s);
    }

    public static EncodeHintType[] values()
    {
        return (EncodeHintType[])a.clone();
    }

    static 
    {
        ERROR_CORRECTION = new EncodeHintType("ERROR_CORRECTION", 0);
        CHARACTER_SET = new EncodeHintType("CHARACTER_SET", 1);
        DATA_MATRIX_SHAPE = new EncodeHintType("DATA_MATRIX_SHAPE", 2);
        MIN_SIZE = new EncodeHintType("MIN_SIZE", 3);
        MAX_SIZE = new EncodeHintType("MAX_SIZE", 4);
        MARGIN = new EncodeHintType("MARGIN", 5);
        PDF417_COMPACT = new EncodeHintType("PDF417_COMPACT", 6);
        PDF417_COMPACTION = new EncodeHintType("PDF417_COMPACTION", 7);
        PDF417_DIMENSIONS = new EncodeHintType("PDF417_DIMENSIONS", 8);
        EncodeHintType aencodehinttype[] = new EncodeHintType[9];
        aencodehinttype[0] = ERROR_CORRECTION;
        aencodehinttype[1] = CHARACTER_SET;
        aencodehinttype[2] = DATA_MATRIX_SHAPE;
        aencodehinttype[3] = MIN_SIZE;
        aencodehinttype[4] = MAX_SIZE;
        aencodehinttype[5] = MARGIN;
        aencodehinttype[6] = PDF417_COMPACT;
        aencodehinttype[7] = PDF417_COMPACTION;
        aencodehinttype[8] = PDF417_DIMENSIONS;
        a = aencodehinttype;
    }
}
