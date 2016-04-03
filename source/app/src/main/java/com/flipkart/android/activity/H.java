// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.content.Intent;
import android.widget.Toast;
import com.flipkart.android.customviews.NewEditText;
import com.flipkart.android.datahandler.SignUpVDataHandler;
import com.flipkart.android.response.signup.SignupResponse;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.activity:
//            SignupActivity

final class H extends SignUpVDataHandler
{

    private SignupActivity a;

    H(SignupActivity signupactivity)
    {
        a = signupactivity;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        SignupActivity.c(a);
        ToastMessageUtils.showErrorToastMessage((new StringBuilder("SignUp Failed. ")).append(CustomDialog.getErrorMessage(i)).toString(), a, true);
    }

    public final void resultReceived(SignupResponse signupresponse, boolean flag)
    {
        SignupActivity.c(a);
        if (signupresponse.isSignUp())
        {
            try
            {
                String s1 = SignupActivity.a(a).getText().toString();
                String s2 = SignupActivity.b(a).getText().toString();
                Intent intent = new Intent();
                intent.putExtra(SignupActivity.KeyEmail, s1);
                intent.putExtra(SignupActivity.KeyPassword, s2);
                a.setResult(-1, intent);
                Toast.makeText(a, "Your account has been created successfully", 1).show();
                a.finish();
                return;
            }
            catch (Exception exception)
            {
                ToastMessageUtils.showErrorToastMessage("SignUp Failed. Please try again", a, true);
                FkLogger.debug("SIGNUP", (new StringBuilder("Exception while signing in.")).append(exception.getMessage()).toString());
                return;
            }
        }
        String s = signupresponse.getMessage();
        if (s.equalsIgnoreCase("null"))
        {
            s = "";
        }
        ToastMessageUtils.showErrorToastMessage((new StringBuilder("SignUp Failed. ")).append(s).toString(), a, true);
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((SignupResponse)obj, flag);
    }
}
