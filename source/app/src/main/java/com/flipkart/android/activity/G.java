// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.view.View;
import com.flipkart.android.customviews.NewEditText;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.ToastMessageUtils;

// Referenced classes of package com.flipkart.android.activity:
//            SignupActivity

final class G
    implements android.view.View.OnClickListener
{

    private SignupActivity a;

    G(SignupActivity signupactivity)
    {
        a = signupactivity;
        super();
    }

    public final void onClick(View view)
    {
        String s = SignupActivity.a(a).getText().toString();
        String s1 = SignupActivity.b(a).getText().toString();
        String s2 = "";
        if (StringUtils.isNullOrEmpty(s))
        {
            s2 = "Please enter a valid email id";
        } else
        if (StringUtils.isNullOrEmpty(s1))
        {
            s2 = "Please enter your password";
        } else
        if (s1.length() < 4)
        {
            s2 = "Password should be minimum 4 characters long";
        } else
        {
            SignupActivity.a(a, s, s1);
        }
        ToastMessageUtils.showErrorToastMessage(s2, a, true);
    }
}
