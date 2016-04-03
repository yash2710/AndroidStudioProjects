// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

// Referenced classes of package com.flipkart.android.utils:
//            ToastMessageUtils

final class s
    implements android.view.View.OnClickListener
{

    private View a;
    private Activity b;
    private EditText c;

    s(View view, Activity activity, EditText edittext)
    {
        a = view;
        b = activity;
        c = edittext;
        super();
    }

    public final void onClick(View view)
    {
        a.scrollTo(0, 0);
        ToastMessageUtils.showErrorToastMessage("Enter Pincode to check delivery charge", b, true);
        InputMethodManager inputmethodmanager = (InputMethodManager)c.getContext().getSystemService("input_method");
        c.requestFocus();
        inputmethodmanager.showSoftInput(c, 2);
    }
}
