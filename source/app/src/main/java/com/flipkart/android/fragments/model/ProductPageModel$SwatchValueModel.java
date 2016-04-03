// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments.model;


public class 
{

    private String a;
    private String b;
    private String c;
    private boolean d;
    private boolean e;
    private boolean f;
    private String g;

    public String getCartItemId()
    {
        return c;
    }

    public String getImageUrl()
    {
        return a;
    }

    public String getItemId()
    {
        return b;
    }

    public String getText()
    {
        return g;
    }

    public boolean isAvailable()
    {
        return e;
    }

    public boolean isImage()
    {
        return f;
    }

    public boolean isSelected()
    {
        return d;
    }

    public void setAvailable(boolean flag)
    {
        e = flag;
    }

    public void setCartItemId(String s)
    {
        c = s;
    }

    public void setImage(boolean flag)
    {
        f = flag;
    }

    public void setImageUrl(String s)
    {
        a = s;
    }

    public void setItemId(String s)
    {
        b = s;
    }

    public void setSelected(boolean flag)
    {
        d = flag;
    }

    public void setText(String s)
    {
        g = s;
    }

    public String toString()
    {
        return getText();
    }

    public ()
    {
    }
}
