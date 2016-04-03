// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.encode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.flipkart.logging.FkLogger;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.FinishListener;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.android.encode:
//            g

public final class EncodeActivity extends Activity
    implements TraceFieldInterface
{

    private static final String a = com/google/zxing/client/android/encode/EncodeActivity.getSimpleName();
    private static final Pattern b = Pattern.compile("[^A-Za-z0-9]");
    private g c;

    public EncodeActivity()
    {
    }

    private void a(int i)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage(i);
        builder.setPositiveButton(com.google.zxing.client.android.R.string.button_ok, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    public final void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("EncodeActivity");
        TraceMachine.enterMethod(_nr_trace, "EncodeActivity#onCreate", null);
_L2:
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null)
        {
            String s = intent.getAction();
            if ("com.google.zxing.client.android.ENCODE".equals(s) || "android.intent.action.SEND".equals(s))
            {
                setContentView(com.google.zxing.client.android.R.layout.encode);
                TraceMachine.exitMethod();
                return;
            }
        }
        break; /* Loop/switch isn't completed */
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "EncodeActivity#onCreate", null);
        if (true) goto _L2; else goto _L1
_L1:
        finish();
        TraceMachine.exitMethod();
        return;
    }

    public final boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(com.google.zxing.client.android.R.menu.encode, menu);
        boolean flag;
        int i;
        MenuItem menuitem;
        Intent intent;
        if (c != null && c.d())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            i = com.google.zxing.client.android.R.string.menu_encode_mecard;
        } else
        {
            i = com.google.zxing.client.android.R.string.menu_encode_vcard;
        }
        menuitem = menu.findItem(com.google.zxing.client.android.R.id.menu_encode);
        menuitem.setTitle(i);
        intent = getIntent();
        if (intent != null)
        {
            menuitem.setVisible("CONTACT_TYPE".equals(intent.getStringExtra("ENCODE_TYPE")));
        }
        return super.onCreateOptionsMenu(menu);
    }

    public final boolean onOptionsItemSelected(MenuItem menuitem)
    {
        int i = menuitem.getItemId();
        if (i != com.google.zxing.client.android.R.id.menu_share) goto _L2; else goto _L1
_L1:
        g g1 = c;
        if (g1 != null) goto _L4; else goto _L3
_L3:
        FkLogger.warn(a, "No existing barcode to send?");
_L6:
        return true;
_L4:
        String s;
        Bitmap bitmap;
        s = g1.a();
        if (s == null)
        {
            FkLogger.warn(a, "No existing barcode to send?");
            return true;
        }
        try
        {
            bitmap = g1.e();
        }
        catch (WriterException writerexception)
        {
            FkLogger.warn(a, writerexception);
            return true;
        }
        if (bitmap == null) goto _L6; else goto _L5
_L5:
        File file1;
        File file = new File(new File(Environment.getExternalStorageDirectory(), "BarcodeScanner"), "Barcodes");
        if (!file.exists() && !file.mkdirs())
        {
            FkLogger.warn(a, (new StringBuilder("Couldn't make dir ")).append(file).toString());
            a(com.google.zxing.client.android.R.string.msg_unmount_usb);
            return true;
        }
        StringBuilder stringbuilder = new StringBuilder();
        String s1 = b.matcher(s).replaceAll("_");
        if (s1.length() > 24)
        {
            s1 = s1.substring(0, 24);
        }
        file1 = new File(file, stringbuilder.append(s1).append(".png").toString());
        file1.delete();
        FileOutputStream fileoutputstream = new FileOutputStream(file1);
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 0, fileoutputstream);
        FileNotFoundException filenotfoundexception;
        Exception exception;
        Intent intent;
        boolean flag;
        IOException ioexception1;
        Intent intent1;
        try
        {
            fileoutputstream.close();
        }
        catch (IOException ioexception2) { }
        intent1 = new Intent("android.intent.action.SEND", Uri.parse("mailto:"));
        intent1.putExtra("android.intent.extra.SUBJECT", (new StringBuilder()).append(getString(com.google.zxing.client.android.R.string.app_name)).append(" - ").append(g1.c()).toString());
        intent1.putExtra("android.intent.extra.TEXT", s);
        intent1.putExtra("android.intent.extra.STREAM", Uri.parse((new StringBuilder("file://")).append(file1.getAbsolutePath()).toString()));
        intent1.setType("image/png");
        intent1.addFlags(0x80000);
        startActivity(Intent.createChooser(intent1, null));
        return true;
        filenotfoundexception;
        fileoutputstream = null;
_L10:
        FkLogger.warn(a, (new StringBuilder("Couldn't access file ")).append(file1).append(" due to ").append(filenotfoundexception).toString());
        a(com.google.zxing.client.android.R.string.msg_unmount_usb);
        if (fileoutputstream == null) goto _L6; else goto _L7
_L7:
        try
        {
            fileoutputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception1)
        {
            return true;
        }
        return true;
        exception;
        fileoutputstream = null;
_L9:
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
_L2:
        if (i == com.google.zxing.client.android.R.id.menu_encode)
        {
            intent = getIntent();
            if (intent == null)
            {
                return false;
            }
            if (!c.d())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            intent.putExtra("USE_VCARD", flag);
            startActivity(intent);
            finish();
            return true;
        } else
        {
            return false;
        }
        exception;
        if (true) goto _L9; else goto _L8
_L8:
        filenotfoundexception;
          goto _L10
    }

    protected final void onResume()
    {
        int k;
        Intent intent;
        super.onResume();
        Display display = ((WindowManager)getSystemService("window")).getDefaultDisplay();
        int i = display.getWidth();
        int j = display.getHeight();
        if (i >= j)
        {
            i = j;
        }
        k = (i * 7) / 8;
        intent = getIntent();
        if (intent == null)
        {
            return;
        }
        Bitmap bitmap;
        c = new g(this, intent, k, intent.getBooleanExtra("USE_VCARD", false));
        bitmap = c.e();
        if (bitmap == null)
        {
            try
            {
                FkLogger.warn(a, "Could not encode barcode");
                a(com.google.zxing.client.android.R.string.msg_encode_contents_failed);
                c = null;
                return;
            }
            catch (WriterException writerexception)
            {
                FkLogger.warn(a, "Could not encode barcode", writerexception);
            }
            a(com.google.zxing.client.android.R.string.msg_encode_contents_failed);
            c = null;
            return;
        }
        TextView textview;
        ((ImageView)findViewById(com.google.zxing.client.android.R.id.image_view)).setImageBitmap(bitmap);
        textview = (TextView)findViewById(com.google.zxing.client.android.R.id.contents_text_view);
        if (intent.getBooleanExtra("ENCODE_SHOW_CONTENTS", true))
        {
            textview.setText(c.b());
            setTitle(c.c());
            return;
        }
        textview.setText("");
        setTitle("");
        return;
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
