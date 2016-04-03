// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments.model;

import com.flipkart.android.response.customwidgetitemvalue.Action;

public class PromiseWidgetModel
{

    private com.flipkart.android.response.productInfo.PromiseWidget.BulletType a;
    private String b;
    private Action c;
    private String d;
    private String e;
    private Action f;

    public PromiseWidgetModel()
    {
    }

    public String getBody()
    {
        return d;
    }

    public com.flipkart.android.response.productInfo.PromiseWidget.BulletType getBulletType()
    {
        return a;
    }

    public Action getImageView1Action()
    {
        return c;
    }

    public String getImageView1Url()
    {
        return b;
    }

    public Action getImageView2Action()
    {
        return f;
    }

    public String getImageView2Url()
    {
        return e;
    }

    public void setBody(String s)
    {
        d = s;
    }

    public void setBulletType(com.flipkart.android.response.productInfo.PromiseWidget.BulletType bullettype)
    {
        a = bullettype;
    }

    public void setImageView1Action(Action action)
    {
        c = action;
    }

    public void setImageView1Url(String s)
    {
        b = s;
    }

    public void setImageView2Action(Action action)
    {
        f = action;
    }

    public void setImageView2Url(String s)
    {
        e = s;
    }
}
