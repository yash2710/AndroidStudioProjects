// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;


// Referenced classes of package com.google.android.gms.wallet:
//            LineItem

public final class <init>
{

    final LineItem ajh;

    public final LineItem build()
    {
        return ajh;
    }

    public final ajh setCurrencyCode(String s)
    {
        ajh.aiL = s;
        return this;
    }

    public final ajh setDescription(String s)
    {
        ajh.description = s;
        return this;
    }

    public final ion setQuantity(String s)
    {
        ajh.aje = s;
        return this;
    }

    public final ajh setRole(int i)
    {
        ajh.ajg = i;
        return this;
    }

    public final ajh setTotalPrice(String s)
    {
        ajh.aiK = s;
        return this;
    }

    public final ajh setUnitPrice(String s)
    {
        ajh.ajf = s;
        return this;
    }

    private (LineItem lineitem)
    {
        ajh = lineitem;
        super();
    }

    ajh(LineItem lineitem, ajh ajh1)
    {
        this(lineitem);
    }
}
