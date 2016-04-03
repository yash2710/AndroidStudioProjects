// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.flipkart.android.utils.ScreenMathUtils;

// Referenced classes of package com.flipkart.android.customviews:
//            r, s, t

public class NewEditText extends LinearLayout
{

    EditText a;
    ImageView b;
    LinearLayout c;

    public NewEditText(Context context)
    {
        super(context);
        a = null;
        b = null;
        c = null;
        a(context);
    }

    public NewEditText(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        a = null;
        b = null;
        c = null;
        a(context);
    }

    private void a(Context context)
    {
        setOrientation(0);
        setGravity(16);
        setBackgroundResource(0x7f02012c);
        a = (EditText)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(0x7f03004b, null);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(0, -2, 5F);
        int i = ScreenMathUtils.dpToPx(10, context);
        layoutparams.setMargins(i, 0, i, 0);
        a.setLayoutParams(layoutparams);
        addView(a);
        b = new ImageView(context);
        android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(0, -2, 1.0F);
        layoutparams1.gravity = 16;
        layoutparams1.setMargins(ScreenMathUtils.dpToPx(5, context), 0, i, 0);
        b.setLayoutParams(layoutparams1);
        b.setImageResource(0x7f020084);
        b.setVisibility(8);
        addView(b);
        c = this;
        b.setOnClickListener(new r(this));
        a.addTextChangedListener(new s(this));
        a.setOnFocusChangeListener(new t(this));
    }

    public EditText getEditText()
    {
        return a;
    }

    public String getText()
    {
        if (a != null)
        {
            return a.getText().toString();
        } else
        {
            return "";
        }
    }

    public void setParams(String s1, int i, int j)
    {
        a.setHint(s1);
        a.setInputType(i);
        a.setImeOptions(j);
    }

    public void setText(String s1)
    {
        if (a != null)
        {
            a.setText(s1);
        }
    }
}
