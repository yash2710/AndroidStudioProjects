// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.AppEventsLogger;
import com.flipkart.android.activity.base.FlipkartBaseFragmentActivity;
import com.flipkart.android.analytics.PageName;
import com.flipkart.android.analytics.PageType;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.customviews.NewEditText;
import com.flipkart.android.datahandler.SignUpVDataHandler;
import com.flipkart.android.login.FacebookConstants;
import com.flipkart.android.utils.FkLoadingDialog;

// Referenced classes of package com.flipkart.android.activity:
//            F, G, I, J, 
//            H, E

public class SignupActivity extends FlipkartBaseFragmentActivity
{

    public static String KeyEmail = "email";
    public static String KeyPassword = "password";
    Button a;
    private NewEditText b;
    private NewEditText c;
    private EditText d;
    private ImageView e;
    private TextView f;
    private SignUpVDataHandler g;
    private FkLoadingDialog h;
    private android.widget.TextView.OnEditorActionListener i;
    private android.view.View.OnClickListener j;
    private android.view.View.OnClickListener k;
    private android.view.View.OnClickListener l;

    public SignupActivity()
    {
        i = new F(this);
        j = new G(this);
        k = new I(this);
        l = new J(this);
    }

    static NewEditText a(SignupActivity signupactivity)
    {
        return signupactivity.b;
    }

    private void a()
    {
        if (h != null)
        {
            h.dismissDlg();
            h = null;
        }
    }

    static void a(SignupActivity signupactivity, String s, String s1)
    {
        signupactivity.g = new H(signupactivity);
        signupactivity.h = new FkLoadingDialog(signupactivity);
        signupactivity.h.showDlg("", "Signing up. Please wait...", null, false);
        signupactivity.g.doSignUp(s, s1);
    }

    static NewEditText b(SignupActivity signupactivity)
    {
        return signupactivity.c;
    }

    static void c(SignupActivity signupactivity)
    {
        signupactivity.a();
    }

    static EditText d(SignupActivity signupactivity)
    {
        return signupactivity.d;
    }

    static void e(SignupActivity signupactivity)
    {
        InputMethodManager inputmethodmanager;
        try
        {
            inputmethodmanager = (InputMethodManager)signupactivity.getSystemService("input_method");
        }
        catch (Exception exception)
        {
            return;
        }
        if (inputmethodmanager == null)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        inputmethodmanager.hideSoftInputFromWindow(signupactivity.getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300b9);
        b = (NewEditText)findViewById(0x7f0a012a);
        b.setParams("Email id", 32, 5);
        c = (NewEditText)findViewById(0x7f0a012b);
        c.setParams("********", 129, 6);
        d = c.getEditText();
        d.setTransformationMethod(new PasswordTransformationMethod());
        a = (Button)findViewById(0x7f0a012f);
        f = (TextView)findViewById(0x7f0a012c);
        d.setOnEditorActionListener(i);
        a.setOnClickListener(j);
        f.setOnClickListener(k);
        e = (ImageView)findViewById(0x7f0a0129);
        e.setOnClickListener(l);
        TextView textview = (TextView)findViewById(0x7f0a012d);
        textview.setPaintFlags(8 | textview.getPaintFlags());
        textview.setOnClickListener(new E(this));
        TrackingHelper.sendPageView(PageName.SignUpPage.name(), PageType.Login);
    }

    protected void onDestroy()
    {
        a();
        super.onDestroy();
    }

    protected void onPause()
    {
        a();
        super.onPause();
    }

    protected void onResume()
    {
        super.onResume();
        try
        {
            AppEventsLogger.activateApp(getApplicationContext(), FacebookConstants.AppId);
        }
        catch (Exception exception) { }
        try
        {
            AppEventsLogger.activateApp(getApplicationContext(), FacebookConstants.AppId);
            return;
        }
        catch (Exception exception1)
        {
            return;
        }
    }

}
