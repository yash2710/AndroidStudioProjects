// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

// Referenced classes of package com.flipkart.android.fragments:
//            AllFiltersFragment

final class m
    implements android.view.View.OnTouchListener
{

    private RadioButton a;
    private RadioButton b;
    private EditText c;
    private AllFiltersFragment d;

    m(AllFiltersFragment allfiltersfragment, RadioButton radiobutton, RadioButton radiobutton1, EditText edittext)
    {
        d = allfiltersfragment;
        a = radiobutton;
        b = radiobutton1;
        c = edittext;
        super();
    }

    public final boolean onTouch(View view, MotionEvent motionevent)
    {
        a.setChecked(true);
        AllFiltersFragment.a(d, 0);
        b.setChecked(false);
        c.setCursorVisible(true);
        return false;
    }
}
