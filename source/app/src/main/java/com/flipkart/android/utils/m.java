// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

final class m
    implements android.widget.TextView.OnEditorActionListener
{

    private Button a;

    m(Button button)
    {
        a = button;
        super();
    }

    public final boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
    {
        if (i == 3 || i == 6 || keyevent.getAction() == 0 && keyevent.getKeyCode() == 66)
        {
            a.performClick();
            return true;
        } else
        {
            return false;
        }
    }
}
