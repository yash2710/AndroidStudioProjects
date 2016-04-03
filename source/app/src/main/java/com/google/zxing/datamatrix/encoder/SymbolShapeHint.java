// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.encoder;


public final class SymbolShapeHint extends Enum
{

    public static final SymbolShapeHint FORCE_NONE;
    public static final SymbolShapeHint FORCE_RECTANGLE;
    public static final SymbolShapeHint FORCE_SQUARE;
    private static final SymbolShapeHint a[];

    private SymbolShapeHint(String s, int i)
    {
        super(s, i);
    }

    public static SymbolShapeHint valueOf(String s)
    {
        return (SymbolShapeHint)Enum.valueOf(com/google/zxing/datamatrix/encoder/SymbolShapeHint, s);
    }

    public static SymbolShapeHint[] values()
    {
        return (SymbolShapeHint[])a.clone();
    }

    static 
    {
        FORCE_NONE = new SymbolShapeHint("FORCE_NONE", 0);
        FORCE_SQUARE = new SymbolShapeHint("FORCE_SQUARE", 1);
        FORCE_RECTANGLE = new SymbolShapeHint("FORCE_RECTANGLE", 2);
        SymbolShapeHint asymbolshapehint[] = new SymbolShapeHint[3];
        asymbolshapehint[0] = FORCE_NONE;
        asymbolshapehint[1] = FORCE_SQUARE;
        asymbolshapehint[2] = FORCE_RECTANGLE;
        a = asymbolshapehint;
    }
}
