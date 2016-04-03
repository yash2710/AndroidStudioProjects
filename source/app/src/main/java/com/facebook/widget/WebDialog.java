// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;

public class WebDialog extends Dialog
{

    private static final int BACKGROUND_GRAY = 0xcc000000;
    static final String CANCEL_URI = "fbconnect://cancel";
    public static final int DEFAULT_THEME = 0x1030010;
    static final boolean DISABLE_SSL_CHECK_FOR_TESTING = false;
    private static final String DISPLAY_TOUCH = "touch";
    private static final String LOG_TAG = "FacebookSDK.WebDialog";
    private static final int MAX_BUFFER_SCREEN_WIDTH = 1024;
    private static final double MIN_SCALE_FACTOR = 0.59999999999999998D;
    private static final int NO_BUFFER_SCREEN_WIDTH = 512;
    static final String REDIRECT_URI = "fbconnect://success";
    private static final String USER_AGENT = "user_agent";
    private FrameLayout contentFrameLayout;
    private ImageView crossImageView;
    private boolean isDetached;
    private boolean listenerCalled;
    private OnCompleteListener onCompleteListener;
    private ProgressDialog spinner;
    private String url;
    private WebView webView;

    public WebDialog(Context context, String s)
    {
        this(context, s, 0x1030010);
    }

    public WebDialog(Context context, String s, int i)
    {
        super(context, i);
        listenerCalled = false;
        isDetached = false;
        url = s;
    }

    public WebDialog(Context context, String s, Bundle bundle, int i, OnCompleteListener oncompletelistener)
    {
        super(context, i);
        listenerCalled = false;
        isDetached = false;
        if (bundle == null)
        {
            bundle = new Bundle();
        }
        bundle.putString("display", "touch");
        bundle.putString("type", "user_agent");
        url = Utility.buildUri(ServerProtocol.getDialogAuthority(), (new StringBuilder("dialog/")).append(s).toString(), bundle).toString();
        onCompleteListener = oncompletelistener;
    }

    private void createCrossImage()
    {
        crossImageView = new ImageView(getContext());
        crossImageView.setOnClickListener(new _cls3());
        Drawable drawable = getContext().getResources().getDrawable(com.facebook.android.R.drawable.com_facebook_close);
        crossImageView.setImageDrawable(drawable);
        crossImageView.setVisibility(4);
    }

    private Pair getMargins()
    {
        Display display = ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);
        int i = displaymetrics.widthPixels;
        int j = displaymetrics.heightPixels;
        int k = (int)((float)i / displaymetrics.density);
        double d;
        int l;
        int i1;
        if (k <= 512)
        {
            d = 1.0D;
        } else
        if (k >= 1024)
        {
            d = 0.59999999999999998D;
        } else
        {
            d = 0.59999999999999998D + 0.40000000000000002D * ((double)(1024 - k) / 512D);
        }
        l = (int)(((double)i * (1.0D - d)) / 2D);
        i1 = (int)(((double)j * (1.0D - d)) / 2D);
        return new Pair(Integer.valueOf(l), Integer.valueOf(i1));
    }

    private void sendCancelToListener()
    {
        sendErrorToListener(new FacebookOperationCanceledException());
    }

    private void sendErrorToListener(Throwable throwable)
    {
        if (onCompleteListener != null && !listenerCalled)
        {
            listenerCalled = true;
            FacebookException facebookexception;
            if (throwable instanceof FacebookException)
            {
                facebookexception = (FacebookException)throwable;
            } else
            {
                facebookexception = new FacebookException(throwable);
            }
            onCompleteListener.onComplete(null, facebookexception);
        }
    }

    private void sendSuccessToListener(Bundle bundle)
    {
        if (onCompleteListener != null && !listenerCalled)
        {
            listenerCalled = true;
            onCompleteListener.onComplete(bundle, null);
        }
    }

    private void setUpWebView(int i)
    {
        LinearLayout linearlayout = new LinearLayout(getContext());
        webView = new WebView(getContext());
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new DialogWebViewClient(null));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, -1));
        webView.setVisibility(4);
        webView.getSettings().setSavePassword(false);
        linearlayout.setPadding(i, i, i, i);
        linearlayout.addView(webView);
        linearlayout.setBackgroundColor(0xcc000000);
        contentFrameLayout.addView(linearlayout);
    }

    public void dismiss()
    {
        if (webView != null)
        {
            webView.stopLoading();
        }
        if (!isDetached)
        {
            if (spinner.isShowing())
            {
                spinner.dismiss();
            }
            super.dismiss();
        }
    }

    public OnCompleteListener getOnCompleteListener()
    {
        return onCompleteListener;
    }

    public void onAttachedToWindow()
    {
        isDetached = false;
        super.onAttachedToWindow();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setOnCancelListener(new _cls1());
        spinner = new ProgressDialog(getContext());
        spinner.requestWindowFeature(1);
        spinner.setMessage(getContext().getString(com.facebook.android.R.string.com_facebook_loading));
        spinner.setOnCancelListener(new _cls2());
        requestWindowFeature(1);
        contentFrameLayout = new FrameLayout(getContext());
        Pair pair = getMargins();
        contentFrameLayout.setPadding(((Integer)pair.first).intValue(), ((Integer)pair.second).intValue(), ((Integer)pair.first).intValue(), ((Integer)pair.second).intValue());
        createCrossImage();
        setUpWebView(1 + crossImageView.getDrawable().getIntrinsicWidth() / 2);
        contentFrameLayout.addView(crossImageView, new android.view.ViewGroup.LayoutParams(-2, -2));
        addContentView(contentFrameLayout, new android.view.ViewGroup.LayoutParams(-1, -1));
    }

    public void onDetachedFromWindow()
    {
        isDetached = true;
        super.onDetachedFromWindow();
    }

    public void setOnCompleteListener(OnCompleteListener oncompletelistener)
    {
        onCompleteListener = oncompletelistener;
    }









    private class _cls3
        implements android.view.View.OnClickListener
    {

        final WebDialog this$0;

        public void onClick(View view)
        {
            sendCancelToListener();
            dismiss();
        }

        _cls3()
        {
            this$0 = WebDialog.this;
            super();
        }
    }


    private class OnCompleteListener
    {

        public abstract void onComplete(Bundle bundle, FacebookException facebookexception);
    }


    private class DialogWebViewClient extends WebViewClient
    {

        final WebDialog this$0;

        public void onPageFinished(WebView webview, String s)
        {
            super.onPageFinished(webview, s);
            if (!isDetached)
            {
                spinner.dismiss();
            }
            contentFrameLayout.setBackgroundColor(0);
            webView.setVisibility(0);
            crossImageView.setVisibility(0);
        }

        public void onPageStarted(WebView webview, String s, Bitmap bitmap)
        {
            Utility.logd("FacebookSDK.WebDialog", (new StringBuilder("Webview loading URL: ")).append(s).toString());
            super.onPageStarted(webview, s, bitmap);
            if (!isDetached)
            {
                spinner.show();
            }
        }

        public void onReceivedError(WebView webview, int i, String s, String s1)
        {
            super.onReceivedError(webview, i, s, s1);
            sendErrorToListener(new FacebookDialogException(s, i, s1));
            dismiss();
        }

        public void onReceivedSslError(WebView webview, SslErrorHandler sslerrorhandler, SslError sslerror)
        {
            super.onReceivedSslError(webview, sslerrorhandler, sslerror);
            sendErrorToListener(new FacebookDialogException(null, -11, null));
            sslerrorhandler.cancel();
            dismiss();
        }

        public boolean shouldOverrideUrlLoading(WebView webview, String s)
        {
            Utility.logd("FacebookSDK.WebDialog", (new StringBuilder("Redirect URL: ")).append(s).toString());
            if (!s.startsWith("fbconnect://success")) goto _L2; else goto _L1
_L1:
            Bundle bundle;
            String s1;
            String s2;
            String s3;
            bundle = Util.parseUrl(s);
            s1 = bundle.getString("error");
            if (s1 == null)
            {
                s1 = bundle.getString("error_type");
            }
            s2 = bundle.getString("error_msg");
            if (s2 == null)
            {
                s2 = bundle.getString("error_description");
            }
            s3 = bundle.getString("error_code");
            if (Utility.isNullOrEmpty(s3)) goto _L4; else goto _L3
_L3:
            int i;
            int j;
            try
            {
                j = Integer.parseInt(s3);
            }
            catch (NumberFormatException numberformatexception)
            {
                i = -1;
                continue; /* Loop/switch isn't completed */
            }
            i = j;
_L6:
            if (Utility.isNullOrEmpty(s1) && Utility.isNullOrEmpty(s2) && i == -1)
            {
                sendSuccessToListener(bundle);
            } else
            if (s1 != null && (s1.equals("access_denied") || s1.equals("OAuthAccessDeniedException")))
            {
                sendCancelToListener();
            } else
            {
                FacebookRequestError facebookrequesterror = new FacebookRequestError(i, s1, s2);
                sendErrorToListener(new FacebookServiceException(facebookrequesterror, s2));
            }
            dismiss();
            return true;
_L2:
            if (s.startsWith("fbconnect://cancel"))
            {
                sendCancelToListener();
                dismiss();
                return true;
            }
            if (s.contains("touch"))
            {
                return false;
            } else
            {
                getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
                return true;
            }
_L4:
            i = -1;
            if (true) goto _L6; else goto _L5
_L5:
        }

        private DialogWebViewClient()
        {
            this$0 = WebDialog.this;
            super();
        }

        DialogWebViewClient(_cls1 _pcls1)
        {
            this();
        }
    }


    private class _cls1
        implements android.content.DialogInterface.OnCancelListener
    {

        final WebDialog this$0;

        public void onCancel(DialogInterface dialoginterface)
        {
            sendCancelToListener();
        }

        _cls1()
        {
            this$0 = WebDialog.this;
            super();
        }
    }


    private class _cls2
        implements android.content.DialogInterface.OnCancelListener
    {

        final WebDialog this$0;

        public void onCancel(DialogInterface dialoginterface)
        {
            sendCancelToListener();
            dismiss();
        }

        _cls2()
        {
            this$0 = WebDialog.this;
            super();
        }
    }

}
