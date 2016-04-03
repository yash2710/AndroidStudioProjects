// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.flipkart.android.activity.base.FlipkartBaseFragmentActivity;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.NewEditText;
import com.flipkart.android.datahandler.LoginVDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.login.FacebookConstants;
import com.flipkart.android.login.FacebookTokenFetcher;
import com.flipkart.android.login.GoogleTokenFetcher;
import com.flipkart.android.login.LoginType;
import com.flipkart.android.login.onFacebookTokenFetchedListener;
import com.flipkart.android.login.onGoogleTokenFetchedListener;
import com.flipkart.android.utils.FkLoadingDialog;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import java.util.Arrays;

// Referenced classes of package com.flipkart.android.activity:
//            x, y, z, A, 
//            B, C, D, s, 
//            u, t, SignupActivity, r, 
//            w, v

public class LoginActivity extends FlipkartBaseFragmentActivity
    implements android.content.DialogInterface.OnCancelListener, onFacebookTokenFetchedListener, onGoogleTokenFetchedListener
{

    public static final String EXTRA_IS_FIRST_TIME_LOAD = "EXTRA_IS_FIRST_TIME_LOAD";
    public static final String EXTRA_IS_FROM_FK_ACTIVITY = "EXTRA_IS_FROM_FK_ACTIVITY";
    public static final String EXTRA_IS_WAIT_FOR_WISHLIST_SYNC = "EXTRA_IS_WAIT_FOR_WISHLIST_SYNC";
    public static final int RequestCodeGoogleAuthActivity = 0;
    public static final int RequestCodeGoogleWebActivity = 1;
    public static final int RequestCodeSignup = 3;
    private static final String t = com/flipkart/android/activity/LoginActivity.getSimpleName();
    private android.view.View.OnClickListener A;
    private android.view.View.OnClickListener B;
    GoogleTokenFetcher a;
    private NewEditText b;
    private NewEditText c;
    private EditText d;
    private Button e;
    private Button f;
    private TextView g;
    private TextView h;
    private ImageView i;
    private LinearLayout j;
    private LinearLayout k;
    private TextView l;
    private Button m;
    private UiLifecycleHelper n;
    private FkLoadingDialog o;
    private FkLoadingDialog p;
    private LoginVDataHandler q;
    private LoginVDataHandler r;
    private LoginVDataHandler s;
    private android.widget.TextView.OnEditorActionListener u;
    private android.view.View.OnClickListener v;
    private android.view.View.OnClickListener w;
    private android.view.View.OnClickListener x;
    private android.view.View.OnClickListener y;
    private android.view.View.OnClickListener z;

    public LoginActivity()
    {
        u = new x(this);
        v = new y(this);
        w = new z(this);
        x = new A(this);
        y = new B(this);
        z = new C(this);
        A = new D(this);
        B = new s(this);
    }

    static String a()
    {
        return t;
    }

    private void a(int i1)
    {
        Intent intent = new Intent();
        getIntent().getAction();
        getIntent().getData();
        intent.setAction(getIntent().getAction());
        intent.setData(getIntent().getData());
        setResult(i1, intent);
        finish();
    }

    static void a(LoginActivity loginactivity)
    {
        d();
    }

    static void a(LoginActivity loginactivity, int i1)
    {
        loginactivity.a(-1);
    }

    static void a(LoginActivity loginactivity, Session session, SessionState sessionstate, Exception exception)
    {
        if (sessionstate.isOpened())
        {
            loginactivity.a(session.getAccessToken());
            return;
        } else
        {
            sessionstate.isClosed();
            return;
        }
    }

    private void a(String s1)
    {
        s = new u(this, s1);
        b();
        o = new FkLoadingDialog(this);
        o.showDlg("", "Logging in. Please wait...", null, false);
        s.doLoginFacebook(s1);
    }

    static boolean a(LoginActivity loginactivity, String s1, String s2)
    {
        if (s1.length() == 0)
        {
            ToastMessageUtils.showErrorToastMessage("Please enter a valid email id", loginactivity, true);
            return false;
        }
        if (s2.length() == 0)
        {
            ToastMessageUtils.showErrorToastMessage("Please enter your password", loginactivity, true);
            return false;
        } else
        {
            d();
            loginactivity.q = new t(loginactivity);
            loginactivity.b();
            loginactivity.o = new FkLoadingDialog(loginactivity);
            loginactivity.o.showDlg("", "Logging in. Please wait...", null, false);
            loginactivity.q.doLogin(s1, s2);
            return true;
        }
    }

    static Button b(LoginActivity loginactivity)
    {
        return loginactivity.e;
    }

    private void b()
    {
        e();
        if (p != null)
        {
            p.dismissDlg();
        }
        if (a != null)
        {
            a.cancelDialog();
        }
    }

    static EditText c(LoginActivity loginactivity)
    {
        return loginactivity.d;
    }

    private void c()
    {
        TrackingHelper.sendLoginComplete();
        a(-1);
    }

    static NewEditText d(LoginActivity loginactivity)
    {
        return loginactivity.b;
    }

    private static void d()
    {
        FlipkartPreferenceManager.instance().saveLastLoginType(LoginType.Unknown);
        FlipkartPreferenceManager.instance().saveLoginAccessToken("");
        FlipkartPreferenceManager.instance().saveLoginAccessExpires(0L);
    }

    static NewEditText e(LoginActivity loginactivity)
    {
        return loginactivity.c;
    }

    private void e()
    {
        if (o != null)
        {
            o.dismissDlg();
            o = null;
        }
    }

    static Button f(LoginActivity loginactivity)
    {
        return loginactivity.m;
    }

    static void g(LoginActivity loginactivity)
    {
        InputMethodManager inputmethodmanager;
        try
        {
            inputmethodmanager = (InputMethodManager)loginactivity.getSystemService("input_method");
        }
        catch (Exception exception)
        {
            return;
        }
        if (inputmethodmanager == null)
        {
            break MISSING_BLOCK_LABEL_34;
        }
        inputmethodmanager.hideSoftInputFromWindow(loginactivity.getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

    static void h(LoginActivity loginactivity)
    {
        loginactivity.e();
    }

    static void i(LoginActivity loginactivity)
    {
        loginactivity.c();
    }

    static void j(LoginActivity loginactivity)
    {
        loginactivity.b();
    }

    protected void onActivityResult(int i1, int j1, Intent intent)
    {
        if (i1 != 0 && i1 != 1) goto _L2; else goto _L1
_L1:
        a.fetchTokenCallback(i1, j1, intent);
_L4:
        n.onActivityResult(i1, j1, intent);
        return;
_L2:
        if (i1 == 3)
        {
            if (j1 == -1)
            {
                String s1 = intent.getStringExtra(SignupActivity.KeyEmail);
                String s2 = intent.getStringExtra(SignupActivity.KeyPassword);
                if (s1 == null || s2 == null)
                {
                    ToastMessageUtils.showErrorToastMessage("Signup Failed. Please try again later.", this, true);
                } else
                {
                    FlipkartPreferenceManager.instance().saveLastLoginType(LoginType.Direct);
                    FlipkartPreferenceManager.instance().saveLoginAccessToken("");
                    FlipkartPreferenceManager.instance().saveLoginAccessExpires(0L);
                    c();
                }
            } else
            if (j1 != 0)
            {
                ToastMessageUtils.showErrorToastMessage("Sorry, something went wrong. Please try again later.", this, true);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onBackPressed()
    {
        setResult(34);
        finish();
        super.onBackPressed();
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        b();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03005e);
        LoginButton loginbutton = (LoginButton)findViewById(0x7f0a0130);
        loginbutton.setReadPermissions(Arrays.asList(new String[] {
            "email"
        }));
        loginbutton.setFbLoginClickListener(new r(this));
        m = (Button)findViewById(0x7f0a0133);
        m.setOnClickListener(A);
        i = (ImageView)findViewById(0x7f0a0129);
        i.setOnClickListener(B);
        j = (LinearLayout)findViewById(0x7f0a0128);
        k = (LinearLayout)findViewById(0x7f0a0132);
        Bundle bundle1 = getIntent().getExtras();
        Session session;
        if (bundle1 != null && bundle1.getBoolean("EXTRA_IS_FIRST_TIME_LOAD"))
        {
            k.setVisibility(0);
            android.widget.RelativeLayout.LayoutParams layoutparams1 = new android.widget.RelativeLayout.LayoutParams(-1, -1);
            layoutparams1.setMargins(0, 0, 0, ScreenMathUtils.dpToPx(60, getApplicationContext()));
            j.setLayoutParams(layoutparams1);
            getWindow().setSoftInputMode(32);
        } else
        {
            android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, -1);
            layoutparams.setMargins(0, 0, 0, 0);
            j.setLayoutParams(layoutparams);
            k.setVisibility(8);
            getWindow().setSoftInputMode(16);
        }
        if (bundle1 == null || !bundle1.getBoolean("EXTRA_IS_FROM_FK_ACTIVITY")) goto _L2; else goto _L1
_L1:
        session = Session.getActiveSession();
        if (session == null) goto _L4; else goto _L3
_L3:
        session.closeAndClearTokenInformation();
_L2:
        l = (TextView)findViewById(0x7f0a012c);
        b = (NewEditText)findViewById(0x7f0a012a);
        c = (NewEditText)findViewById(0x7f0a012b);
        e = (Button)findViewById(0x7f0a012d);
        g = (TextView)findViewById(0x7f0a012f);
        g.setPaintFlags(8 | g.getPaintFlags());
        h = (TextView)findViewById(0x7f0a012e);
        h.setPaintFlags(8 | h.getPaintFlags());
        f = (Button)findViewById(0x7f0a0131);
        b.setParams("Enter your email id", 32, 5);
        c.setParams("********", 129, 6);
        d = c.getEditText();
        d.setOnEditorActionListener(u);
        e.setOnClickListener(z);
        f.setOnClickListener(v);
        g.setOnClickListener(x);
        h.setOnClickListener(y);
        l.setOnClickListener(w);
        p = new FkLoadingDialog(this);
        o = new FkLoadingDialog(this);
        a = new GoogleTokenFetcher(this, this);
        TrackingHelper.sendLoginPage();
        n = new UiLifecycleHelper(this, new w(this));
        n.onCreate(bundle);
        return;
_L4:
        Session session1 = Session.openActiveSession(this, false, null);
        if (session1 != null)
        {
            session1.closeAndClearTokenInformation();
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    protected void onDestroy()
    {
        View view = findViewById(0x7f0a0127);
        if (view != null)
        {
            view.setBackgroundDrawable(null);
        }
        b();
        e();
        super.onDestroy();
        n.onDestroy();
    }

    public void onFacebookTokenFetchError(com.flipkart.android.login.onFacebookTokenFetchedListener.FacebookTokenFetcherErrorType facebooktokenfetchererrortype, String s1)
    {
        if (facebooktokenfetchererrortype == com.flipkart.android.login.onFacebookTokenFetchedListener.FacebookTokenFetcherErrorType.ErrorCancel || facebooktokenfetchererrortype == com.flipkart.android.login.onFacebookTokenFetchedListener.FacebookTokenFetcherErrorType.ErrorFacebook)
        {
            ToastMessageUtils.showErrorToastMessage(s1, this, true);
            return;
        } else
        {
            ToastMessageUtils.showErrorToastMessage("Sorry, something went wrong. Please try again later.", this, true);
            return;
        }
    }

    public void onFacebookTokenFetcherEvent(com.flipkart.android.login.onFacebookTokenFetchedListener.FacebookTokenFetcherEventType facebooktokenfetchereventtype, Bundle bundle)
    {
        if (facebooktokenfetchereventtype == com.flipkart.android.login.onFacebookTokenFetchedListener.FacebookTokenFetcherEventType.EventTypeAuthTokenReceived)
        {
            if (bundle != null)
            {
                String s1 = bundle.getString(FacebookTokenFetcher.KeyAccessToken);
                if (s1 != null)
                {
                    a(s1);
                    return;
                } else
                {
                    ToastMessageUtils.showErrorToastMessage("Sorry, something went wrong. Please try again later.", this, true);
                    return;
                }
            } else
            {
                ToastMessageUtils.showErrorToastMessage("Sorry, something went wrong. Please try again later.", this, true);
                return;
            }
        } else
        {
            ToastMessageUtils.showErrorToastMessage("Sorry, something went wrong. Please try again later.", this, true);
            return;
        }
    }

    public void onGoogleTokenFetchError(int i1)
    {
        String s1;
        try
        {
            p.dismissDlg();
        }
        catch (IllegalArgumentException illegalargumentexception) { }
        i1;
        JVM INSTR tableswitch 0 3: default 40
    //                   0 58
    //                   1 51
    //                   2 65
    //                   3 58;
           goto _L1 _L2 _L3 _L4 _L2
_L1:
        s1 = "Sorry, something went wrong. Please try again later.";
_L6:
        ToastMessageUtils.showErrorToastMessage(s1, this, true);
        return;
_L3:
        s1 = "Login cancelled";
        continue; /* Loop/switch isn't completed */
_L2:
        s1 = "Sorry, an error occured while login. Please try after some time";
        continue; /* Loop/switch isn't completed */
_L4:
        s1 = "Sorry, an error occured while authentication";
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void onGoogleTokenFetcherEvent(int i1, String s1)
    {
        switch (i1)
        {
        default:
            return;

        case 0: // '\0'
            b();
            r = new v(this);
            b();
            o = new FkLoadingDialog(this);
            o.showDlg("", "Logging in. Please wait...", null, false);
            r.doLoginGoogle(s1);
            return;

        case 1: // '\001'
            p.showDlg("", "Authenticating with google. Please wait...", null, false);
            break;
        }
    }

    protected void onPause()
    {
        b();
        super.onPause();
        n.onPause();
    }

    public void onResume()
    {
        super.onResume();
        ((FlipkartApplication)getApplicationContext()).setCurrentActivity(this);
        n.onResume();
        try
        {
            AppEventsLogger.activateApp(getApplicationContext(), FacebookConstants.AppId);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

}
