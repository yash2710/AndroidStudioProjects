// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package com.google.android.gms.internal:
//            ex, eu, cf

public class ez extends WebChromeClient
{

    private final ex lN;

    public ez(ex ex1)
    {
        lN = ex1;
    }

    private static void a(android.app.AlertDialog.Builder builder, String s, JsResult jsresult)
    {
        builder.setMessage(s).setPositiveButton(0x104000a, new _cls3(jsresult)).setNegativeButton(0x1040000, new _cls2(jsresult)).setOnCancelListener(new _cls1(jsresult)).create().show();
    }

    private static void a(Context context, android.app.AlertDialog.Builder builder, String s, String s1, JsPromptResult jspromptresult)
    {
        LinearLayout linearlayout = new LinearLayout(context);
        linearlayout.setOrientation(1);
        TextView textview = new TextView(context);
        textview.setText(s);
        EditText edittext = new EditText(context);
        edittext.setText(s1);
        linearlayout.addView(textview);
        linearlayout.addView(edittext);
        builder.setView(linearlayout).setPositiveButton(0x104000a, new _cls6(jspromptresult, edittext)).setNegativeButton(0x1040000, new _cls5(jspromptresult)).setOnCancelListener(new _cls4(jspromptresult)).create().show();
    }

    protected final void a(View view, int i, android.webkit.WebChromeClient.CustomViewCallback customviewcallback)
    {
        cf cf1 = lN.ca();
        if (cf1 == null)
        {
            eu.D("Could not get ad overlay when showing custom view.");
            customviewcallback.onCustomViewHidden();
            return;
        } else
        {
            cf1.a(view, customviewcallback);
            cf1.setRequestedOrientation(i);
            return;
        }
    }

    protected boolean a(Context context, String s, String s1, String s2, JsResult jsresult, JsPromptResult jspromptresult, boolean flag)
    {
        android.app.AlertDialog.Builder builder;
        builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle(s);
        if (flag)
        {
            try
            {
                a(context, builder, s1, s2, jspromptresult);
            }
            catch (android.view.WindowManager.BadTokenException badtokenexception)
            {
                eu.c("Fail to display Dialog.", badtokenexception);
            }
            break MISSING_BLOCK_LABEL_56;
        }
        a(builder, s1, jsresult);
        return true;
    }

    public final void onCloseWindow(WebView webview)
    {
        if (!(webview instanceof ex))
        {
            eu.D("Tried to close a WebView that wasn't an AdWebView.");
            return;
        }
        cf cf1 = ((ex)webview).ca();
        if (cf1 == null)
        {
            eu.D("Tried to close an AdWebView not associated with an overlay.");
            return;
        } else
        {
            cf1.close();
            return;
        }
    }

    public final boolean onConsoleMessage(ConsoleMessage consolemessage)
    {
        String s = (new StringBuilder("JS: ")).append(consolemessage.message()).append(" (").append(consolemessage.sourceId()).append(":").append(consolemessage.lineNumber()).append(")").toString();
        _cls7.sS[consolemessage.messageLevel().ordinal()];
        JVM INSTR tableswitch 1 5: default 96
    //                   1 106
    //                   2 113
    //                   3 120
    //                   4 120
    //                   5 127;
           goto _L1 _L2 _L3 _L4 _L4 _L5
_L1:
        eu.B(s);
_L7:
        return super.onConsoleMessage(consolemessage);
_L2:
        eu.A(s);
        continue; /* Loop/switch isn't completed */
_L3:
        eu.D(s);
        continue; /* Loop/switch isn't completed */
_L4:
        eu.B(s);
        continue; /* Loop/switch isn't completed */
_L5:
        eu.z(s);
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final boolean onCreateWindow(WebView webview, boolean flag, boolean flag1, Message message)
    {
        android.webkit.WebView.WebViewTransport webviewtransport = (android.webkit.WebView.WebViewTransport)message.obj;
        WebView webview1 = new WebView(webview.getContext());
        webview1.setWebViewClient(lN.cb());
        webviewtransport.setWebView(webview1);
        message.sendToTarget();
        return true;
    }

    public final void onExceededDatabaseQuota(String s, String s1, long l, long l1, long l2, android.webkit.WebStorage.QuotaUpdater quotaupdater)
    {
        long l3;
        l3 = 0x500000L - l2;
        if (l3 <= 0L)
        {
            quotaupdater.updateQuota(l);
            return;
        }
        if (l != 0L) goto _L2; else goto _L1
_L1:
        if (l1 > l3 || l1 > 0x100000L)
        {
            l1 = 0L;
        }
_L4:
        quotaupdater.updateQuota(l1);
        return;
_L2:
        if (l1 != 0L)
        {
            break; /* Loop/switch isn't completed */
        }
        l = Math.min(l + Math.min(0x20000L, l3), 0x100000L);
_L6:
        l1 = l;
        if (true) goto _L4; else goto _L3
_L3:
        if (l1 > Math.min(0x100000L - l, l3)) goto _L6; else goto _L5
_L5:
        l += l1;
          goto _L6
    }

    public final void onHideCustomView()
    {
        cf cf1 = lN.ca();
        if (cf1 == null)
        {
            eu.D("Could not get ad overlay when hiding custom view.");
            return;
        } else
        {
            cf1.aR();
            return;
        }
    }

    public final boolean onJsAlert(WebView webview, String s, String s1, JsResult jsresult)
    {
        if ((webview instanceof ex) && ((ex)webview).cf() != null)
        {
            return a(((ex)webview).cf(), s, s1, null, jsresult, null, false);
        } else
        {
            return a(webview.getContext(), s, s1, null, jsresult, null, false);
        }
    }

    public final boolean onJsBeforeUnload(WebView webview, String s, String s1, JsResult jsresult)
    {
        return a(webview.getContext(), s, s1, null, jsresult, null, false);
    }

    public final boolean onJsConfirm(WebView webview, String s, String s1, JsResult jsresult)
    {
        return a(webview.getContext(), s, s1, null, jsresult, null, false);
    }

    public final boolean onJsPrompt(WebView webview, String s, String s1, String s2, JsPromptResult jspromptresult)
    {
        return a(webview.getContext(), s, s1, s2, null, jspromptresult, true);
    }

    public final void onReachedMaxAppCacheSize(long l, long l1, android.webkit.WebStorage.QuotaUpdater quotaupdater)
    {
        long l2 = 0x500000L - l1;
        long l3 = 0x20000L + l;
        if (l2 < l3)
        {
            quotaupdater.updateQuota(0L);
            return;
        } else
        {
            quotaupdater.updateQuota(l3);
            return;
        }
    }

    public final void onShowCustomView(View view, android.webkit.WebChromeClient.CustomViewCallback customviewcallback)
    {
        a(view, -1, customviewcallback);
    }

    private class _cls3
        implements android.content.DialogInterface.OnClickListener
    {

        final JsResult sP;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            sP.confirm();
        }

        _cls3(JsResult jsresult)
        {
            sP = jsresult;
            super();
        }
    }


    private class _cls2
        implements android.content.DialogInterface.OnClickListener
    {

        final JsResult sP;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            sP.cancel();
        }

        _cls2(JsResult jsresult)
        {
            sP = jsresult;
            super();
        }
    }


    private class _cls1
        implements android.content.DialogInterface.OnCancelListener
    {

        final JsResult sP;

        public final void onCancel(DialogInterface dialoginterface)
        {
            sP.cancel();
        }

        _cls1(JsResult jsresult)
        {
            sP = jsresult;
            super();
        }
    }


    private class _cls6
        implements android.content.DialogInterface.OnClickListener
    {

        final JsPromptResult sQ;
        final EditText sR;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            sQ.confirm(sR.getText().toString());
        }

        _cls6(JsPromptResult jspromptresult, EditText edittext)
        {
            sQ = jspromptresult;
            sR = edittext;
            super();
        }
    }


    private class _cls5
        implements android.content.DialogInterface.OnClickListener
    {

        final JsPromptResult sQ;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            sQ.cancel();
        }

        _cls5(JsPromptResult jspromptresult)
        {
            sQ = jspromptresult;
            super();
        }
    }


    private class _cls4
        implements android.content.DialogInterface.OnCancelListener
    {

        final JsPromptResult sQ;

        public final void onCancel(DialogInterface dialoginterface)
        {
            sQ.cancel();
        }

        _cls4(JsPromptResult jspromptresult)
        {
            sQ = jspromptresult;
            super();
        }
    }


    private class _cls7
    {

        static final int sS[];

        static 
        {
            sS = new int[android.webkit.ConsoleMessage.MessageLevel.values().length];
            try
            {
                sS[android.webkit.ConsoleMessage.MessageLevel.ERROR.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror) { }
            try
            {
                sS[android.webkit.ConsoleMessage.MessageLevel.WARNING.ordinal()] = 2;
            }
            catch (NoSuchFieldError nosuchfielderror1) { }
            try
            {
                sS[android.webkit.ConsoleMessage.MessageLevel.LOG.ordinal()] = 3;
            }
            catch (NoSuchFieldError nosuchfielderror2) { }
            try
            {
                sS[android.webkit.ConsoleMessage.MessageLevel.TIP.ordinal()] = 4;
            }
            catch (NoSuchFieldError nosuchfielderror3) { }
            try
            {
                sS[android.webkit.ConsoleMessage.MessageLevel.DEBUG.ordinal()] = 5;
            }
            catch (NoSuchFieldError nosuchfielderror4)
            {
                return;
            }
        }
    }

}
