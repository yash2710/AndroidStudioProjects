// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.activity:
//            LoginActivity

final class z
    implements android.view.View.OnClickListener
{

    private LoginActivity a;

    z(LoginActivity loginactivity)
    {
        a = loginactivity;
        super();
    }

    public final void onClick(View view)
    {
        String s;
        TextView textview;
        int i;
        s = (String)view.getTag();
        textview = (TextView)view;
        i = LoginActivity.c(a).getSelectionStart();
        if (StringUtils.isNullOrEmpty(s)) goto _L2; else goto _L1
_L1:
        if (!s.equals("checked")) goto _L4; else goto _L3
_L3:
        LoginActivity.c(a).setTransformationMethod(new PasswordTransformationMethod());
        textview.setCompoundDrawablesWithIntrinsicBounds(0x7f02008d, 0, 0, 0);
        textview.setTag("unchecked");
_L2:
        LoginActivity.c(a).setSelection(i);
        return;
_L4:
        if (s.equals("unchecked"))
        {
            LoginActivity.c(a).setTransformationMethod(null);
            textview.setCompoundDrawablesWithIntrinsicBounds(0x7f02008c, 0, 0, 0);
            textview.setTag("checked");
        }
        if (true) goto _L2; else goto _L5
_L5:
    }
}
