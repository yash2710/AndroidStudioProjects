// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments.model;

import java.util.ArrayList;

public class 
{

    public static final int SWATCH_TYPE_BOXES_INT = 2;
    public static final String SWATCH_TYPE_BOXES_STRING = "boxes";
    public static final int SWATCH_TYPE_DROPDOWN_INT = 3;
    public static final String SWATCH_TYPE_DROPDOWN_STRING = "dropDown";
    public static final int SWATCH_TYPE_PALETTE_INT = 1;
    public static final String SWATCH_TYPE_PALETTE_STRING = "paletteImage";
    private String a;
    private int b;
    private int c;
    private ArrayList d;

    public int getCurrentIndex()
    {
        return c;
    }

    public ArrayList getSwaatchValues()
    {
        return d;
    }

    public String getTitle()
    {
        return a;
    }

    public int getType()
    {
        return b;
    }

    public void setCurrentIndex(int i)
    {
        c = i;
    }

    public void setSwaatchValues(ArrayList arraylist)
    {
        d = arraylist;
    }

    public void setTitle(String s)
    {
        a = s;
    }

    public void setType(int i)
    {
        b = i;
    }

    public ()
    {
    }
}
