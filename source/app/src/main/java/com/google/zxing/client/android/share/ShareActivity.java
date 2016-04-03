// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.share;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.Button;
import com.flipkart.logging.FkLogger;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.Contents;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.zxing.client.android.share:
//            e, f, g, h, 
//            i

public final class ShareActivity extends Activity
    implements TraceFieldInterface
{

    private static final String a = com/google/zxing/client/android/share/ShareActivity.getSimpleName();
    private Button b;
    private final android.view.View.OnClickListener c = new e(this);
    private final android.view.View.OnClickListener d = new f(this);
    private final android.view.View.OnClickListener e = new g(this);
    private final android.view.View.OnClickListener f = new h(this);
    private final android.view.View.OnKeyListener g = new i(this);

    public ShareActivity()
    {
    }

    private static String a(String s)
    {
        if (s.indexOf('\n') >= 0)
        {
            s = s.replace("\n", " ");
        }
        if (s.indexOf('\r') >= 0)
        {
            s = s.replace("\r", " ");
        }
        return s;
    }

    private void a(Uri uri)
    {
        int j;
        j = 0;
        FkLogger.info(a, (new StringBuilder("Showing contact URI as barcode: ")).append(uri).toString());
        if (uri != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ContentResolver contentresolver = getContentResolver();
        Cursor cursor;
        Exception exception;
        boolean flag;
        String s;
        String s1;
        int k;
        boolean flag1;
        Bundle bundle;
        Cursor cursor1;
        Cursor cursor2;
        Intent intent;
        Exception exception1;
        int l;
        String s2;
        Exception exception2;
        String s3;
        Cursor cursor3;
        Exception exception3;
        int i1;
        int j1;
        String s4;
        try
        {
            cursor = contentresolver.query(uri, null, null, null, null);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return;
        }
        if (cursor == null) goto _L1; else goto _L3
_L3:
        flag = cursor.moveToFirst();
        if (!flag)
        {
            cursor.close();
            return;
        }
        s = cursor.getString(cursor.getColumnIndex("_id"));
        s1 = cursor.getString(cursor.getColumnIndex("display_name"));
        k = cursor.getInt(cursor.getColumnIndex("has_phone_number"));
        if (k > 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        cursor.close();
        bundle = new Bundle();
        if (s1 != null && s1.length() > 0)
        {
            bundle.putString("name", a(s1));
        }
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_322;
        }
        cursor3 = contentresolver.query(android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, (new StringBuilder("contact_id=")).append(s).toString(), null, null);
        if (cursor3 == null)
        {
            break MISSING_BLOCK_LABEL_322;
        }
        i1 = cursor3.getColumnIndex("data1");
        j1 = 0;
_L5:
        if (!cursor3.moveToNext() || j1 >= Contents.PHONE_KEYS.length)
        {
            break; /* Loop/switch isn't completed */
        }
        s4 = cursor3.getString(i1);
        if (s4 == null)
        {
            break MISSING_BLOCK_LABEL_291;
        }
        if (s4.length() > 0)
        {
            bundle.putString(Contents.PHONE_KEYS[j1], a(s4));
        }
        j1++;
        if (true) goto _L5; else goto _L4
        exception;
        cursor.close();
        throw exception;
_L4:
        cursor3.close();
        cursor1 = contentresolver.query(android.provider.ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null, (new StringBuilder("contact_id=")).append(s).toString(), null, null);
        if (cursor1 == null)
        {
            break MISSING_BLOCK_LABEL_417;
        }
        if (!cursor1.moveToNext())
        {
            break MISSING_BLOCK_LABEL_410;
        }
        s3 = cursor1.getString(cursor1.getColumnIndex("data1"));
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_410;
        }
        if (s3.length() > 0)
        {
            bundle.putString("postal", a(s3));
        }
        cursor1.close();
        cursor2 = contentresolver.query(android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, (new StringBuilder("contact_id=")).append(s).toString(), null, null);
        if (cursor2 == null)
        {
            break MISSING_BLOCK_LABEL_557;
        }
        l = cursor2.getColumnIndex("data1");
_L6:
        if (!cursor2.moveToNext() || j >= Contents.EMAIL_KEYS.length)
        {
            break MISSING_BLOCK_LABEL_550;
        }
        s2 = cursor2.getString(l);
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_520;
        }
        if (s2.length() > 0)
        {
            bundle.putString(Contents.EMAIL_KEYS[j], a(s2));
        }
        j++;
          goto _L6
        exception3;
        cursor3.close();
        throw exception3;
        exception2;
        cursor1.close();
        throw exception2;
        cursor2.close();
        intent = new Intent("com.google.zxing.client.android.ENCODE");
        intent.addFlags(0x80000);
        intent.putExtra("ENCODE_TYPE", "CONTACT_TYPE");
        intent.putExtra("ENCODE_DATA", bundle);
        intent.putExtra("ENCODE_FORMAT", BarcodeFormat.QR_CODE.toString());
        FkLogger.info(a, (new StringBuilder("Sending bundle for encoding: ")).append(bundle).toString());
        startActivity(intent);
        return;
        exception1;
        cursor2.close();
        throw exception1;
    }

    static void a(ShareActivity shareactivity, String s)
    {
        Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
        intent.addFlags(0x80000);
        intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
        intent.putExtra("ENCODE_DATA", s);
        intent.putExtra("ENCODE_FORMAT", BarcodeFormat.QR_CODE.toString());
        shareactivity.startActivity(intent);
    }

    public final void onActivityResult(int j, int k, Intent intent)
    {
        if (k != -1) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 0 2: default 32
    //                   0 33
    //                   1 130
    //                   2 33;
           goto _L2 _L3 _L4 _L3
_L2:
        return;
_L3:
        String s = intent.getStringExtra("url");
        FkLogger.info(a, (new StringBuilder("Showing text as barcode: ")).append(s).toString());
        if (s != null)
        {
            Intent intent1 = new Intent("com.google.zxing.client.android.ENCODE");
            intent1.addFlags(0x80000);
            intent1.putExtra("ENCODE_TYPE", "TEXT_TYPE");
            intent1.putExtra("ENCODE_DATA", s);
            intent1.putExtra("ENCODE_FORMAT", BarcodeFormat.QR_CODE.toString());
            startActivity(intent1);
            return;
        }
        if (true) goto _L2; else goto _L4
_L4:
        a(intent.getData());
        return;
    }

    public final void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("ShareActivity");
        TraceMachine.enterMethod(_nr_trace, "ShareActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        setContentView(com.google.zxing.client.android.R.layout.share);
        findViewById(com.google.zxing.client.android.R.id.share_contact_button).setOnClickListener(c);
        findViewById(com.google.zxing.client.android.R.id.share_bookmark_button).setOnClickListener(d);
        findViewById(com.google.zxing.client.android.R.id.share_app_button).setOnClickListener(e);
        b = (Button)findViewById(com.google.zxing.client.android.R.id.share_clipboard_button);
        b.setOnClickListener(f);
        findViewById(com.google.zxing.client.android.R.id.share_text_view).setOnKeyListener(g);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "ShareActivity#onCreate", null);
          goto _L1
    }

    protected final void onResume()
    {
        super.onResume();
        ClipboardManager clipboardmanager = (ClipboardManager)getSystemService("clipboard");
        b.setEnabled(clipboardmanager.hasText());
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop()
    {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

}
