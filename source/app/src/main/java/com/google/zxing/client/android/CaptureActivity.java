// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.flipkart.logging.FkLogger;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.client.android.history.HistoryActivity;
import com.google.zxing.client.android.history.HistoryItem;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.android.result.ResultButtonListener;
import com.google.zxing.client.android.result.ResultHandler;
import com.google.zxing.client.android.result.ResultHandlerFactory;
import com.google.zxing.client.android.result.supplement.SupplementalInfoRetriever;
import com.google.zxing.client.android.share.ShareActivity;
import com.google.zxing.client.result.ParsedResultType;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.zxing.client.android:
//            CaptureActivityHandler, ViewfinderView, r, o, 
//            FinishListener, n, b, d, 
//            a, PreferencesActivity, HelpActivity, f, 
//            h

public class CaptureActivity extends Activity
    implements android.view.SurfaceHolder.Callback, TraceFieldInterface
{

    public static final int HISTORY_REQUEST_CODE = 47820;
    private static final String a = com/google/zxing/client/android/CaptureActivity.getSimpleName();
    private static final String b[] = {
        "http://zxing.appspot.com/scan", "zxing://scan/"
    };
    private static final Set c;
    private CameraManager d;
    private CaptureActivityHandler e;
    private Result f;
    private ViewfinderView g;
    private TextView h;
    private View i;
    private Result j;
    private boolean k;
    private boolean l;
    private r m;
    private String n;
    private o o;
    private Collection p;
    private Map q;
    private String r;
    private HistoryManager s;
    private n t;
    private b u;
    private a v;

    public CaptureActivity()
    {
    }

    private void a(int i1, Object obj, long l1)
    {
        Message message = Message.obtain(e, i1, obj);
        if (l1 > 0L)
        {
            e.sendMessageDelayed(message, l1);
            return;
        } else
        {
            e.sendMessage(message);
            return;
        }
    }

    private static void a(Canvas canvas, Paint paint, ResultPoint resultpoint, ResultPoint resultpoint1, float f1)
    {
        if (resultpoint != null && resultpoint1 != null)
        {
            canvas.drawLine(f1 * resultpoint.getX(), f1 * resultpoint.getY(), f1 * resultpoint1.getX(), f1 * resultpoint1.getY(), paint);
        }
    }

    private void a(SurfaceHolder surfaceholder)
    {
        if (surfaceholder == null)
        {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (d.isOpen())
        {
            FkLogger.warn(a, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try
        {
            d.openDriver(surfaceholder);
            if (e == null)
            {
                e = new CaptureActivityHandler(this, p, q, r, d);
            }
            a(((Result) (null)));
            return;
        }
        catch (IOException ioexception)
        {
            FkLogger.warn(a, ioexception);
            c();
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            FkLogger.warn(a, "Unexpected error initializing camera", runtimeexception);
        }
        c();
    }

    private void a(Result result)
    {
        if (e == null)
        {
            f = result;
            return;
        }
        if (result != null)
        {
            f = result;
        }
        if (f != null)
        {
            Message message = Message.obtain(e, R.id.decode_succeeded, f);
            e.sendMessage(message);
        }
        f = null;
    }

    private void a(Result result, ResultHandler resulthandler, Bitmap bitmap)
    {
        CharSequence charsequence;
        ClipboardManager clipboardmanager;
        h.setVisibility(8);
        g.setVisibility(8);
        i.setVisibility(0);
        ImageView imageview = (ImageView)findViewById(R.id.barcode_image_view);
        String s1;
        TextView textview;
        View view;
        Map map;
        if (bitmap == null)
        {
            imageview.setImageBitmap(BitmapFactoryInstrumentation.decodeResource(getResources(), R.drawable.launcher_icon));
        } else
        {
            imageview.setImageBitmap(bitmap);
        }
        ((TextView)findViewById(R.id.format_text_view)).setText(result.getBarcodeFormat().toString());
        ((TextView)findViewById(R.id.type_text_view)).setText(resulthandler.getType().toString());
        s1 = DateFormat.getDateTimeInstance(3, 3).format(new Date(result.getTimestamp()));
        ((TextView)findViewById(R.id.time_text_view)).setText(s1);
        textview = (TextView)findViewById(R.id.meta_text_view);
        view = findViewById(R.id.meta_text_view_label);
        textview.setVisibility(8);
        view.setVisibility(8);
        map = result.getResultMetadata();
        if (map != null)
        {
            StringBuilder stringbuilder = new StringBuilder(20);
            Iterator iterator = map.entrySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                if (c.contains(entry.getKey()))
                {
                    stringbuilder.append(entry.getValue()).append('\n');
                }
            } while (true);
            if (stringbuilder.length() > 0)
            {
                stringbuilder.setLength(-1 + stringbuilder.length());
                textview.setText(stringbuilder);
                textview.setVisibility(0);
                view.setVisibility(0);
            }
        }
        TextView textview1 = (TextView)findViewById(R.id.contents_text_view);
        charsequence = resulthandler.getDisplayContents();
        textview1.setText(charsequence);
        textview1.setTextSize(2, Math.max(22, 32 - charsequence.length() / 4));
        TextView textview2 = (TextView)findViewById(R.id.contents_supplement_text_view);
        textview2.setText("");
        textview2.setOnClickListener(null);
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("preferences_supplemental", true))
        {
            SupplementalInfoRetriever.maybeInvokeRetrieval(textview2, resulthandler.getResult(), s, this);
        }
        int i1 = resulthandler.getButtonCount();
        ViewGroup viewgroup = (ViewGroup)findViewById(R.id.result_button_view);
        viewgroup.requestFocus();
        int j1 = 0;
        while (j1 < 4) 
        {
            TextView textview3 = (TextView)viewgroup.getChildAt(j1);
            if (j1 < i1)
            {
                textview3.setVisibility(0);
                textview3.setText(resulthandler.getButtonText(j1));
                textview3.setOnClickListener(new ResultButtonListener(resulthandler, j1));
            } else
            {
                textview3.setVisibility(8);
            }
            j1++;
        }
        if (!l || resulthandler.areContentsSecure())
        {
            break MISSING_BLOCK_LABEL_556;
        }
        clipboardmanager = (ClipboardManager)getSystemService("clipboard");
        if (charsequence == null)
        {
            break MISSING_BLOCK_LABEL_556;
        }
        clipboardmanager.setText(charsequence);
        return;
        NullPointerException nullpointerexception;
        nullpointerexception;
        FkLogger.warn(a, "Clipboard bug", nullpointerexception);
        return;
    }

    private void b(Result result, ResultHandler resulthandler, Bitmap bitmap)
    {
        long l1 = 1500L;
        int i1 = 0;
        if (bitmap != null)
        {
            g.drawResultBitmap(bitmap);
        }
        if (getIntent() != null)
        {
            l1 = getIntent().getLongExtra("RESULT_DISPLAY_DURATION_MS", l1);
        }
        if (l1 > 0L)
        {
            String s4 = String.valueOf(result);
            if (s4.length() > 32)
            {
                s4 = (new StringBuilder()).append(s4.substring(0, 32)).append(" ...").toString();
            }
            h.setText((new StringBuilder()).append(getString(resulthandler.getDisplayTitle())).append(" : ").append(s4).toString());
        }
        if (l && !resulthandler.areContentsSecure())
        {
            ClipboardManager clipboardmanager = (ClipboardManager)getSystemService("clipboard");
            CharSequence charsequence = resulthandler.getDisplayContents();
            byte abyte0[];
            Map map;
            Integer integer;
            String s1;
            Iterable iterable;
            Iterator iterator;
            byte abyte1[];
            if (charsequence != null)
            {
                try
                {
                    clipboardmanager.setText(charsequence);
                }
                catch (NullPointerException nullpointerexception)
                {
                    FkLogger.warn(a, "Clipboard bug", nullpointerexception);
                }
            }
        }
        if (m == r.a)
        {
            Intent intent = new Intent(getIntent().getAction());
            intent.addFlags(0x80000);
            intent.putExtra("SCAN_TYPE", getString(resulthandler.getDisplayTitle()));
            intent.putExtra("SCAN_RESULT", result.toString());
            intent.putExtra("SCAN_RESULT_FORMAT", result.getBarcodeFormat().toString());
            abyte0 = result.getRawBytes();
            if (abyte0 != null && abyte0.length > 0)
            {
                intent.putExtra("SCAN_RESULT_BYTES", abyte0);
            }
            map = result.getResultMetadata();
            if (map != null)
            {
                if (map.containsKey(ResultMetadataType.UPC_EAN_EXTENSION))
                {
                    intent.putExtra("SCAN_RESULT_UPC_EAN_EXTENSION", map.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
                }
                integer = (Integer)map.get(ResultMetadataType.ORIENTATION);
                if (integer != null)
                {
                    intent.putExtra("SCAN_RESULT_ORIENTATION", integer.intValue());
                }
                s1 = (String)map.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
                if (s1 != null)
                {
                    intent.putExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL", s1);
                }
                iterable = (Iterable)map.get(ResultMetadataType.BYTE_SEGMENTS);
                if (iterable != null)
                {
                    for (iterator = iterable.iterator(); iterator.hasNext();)
                    {
                        abyte1 = (byte[])iterator.next();
                        intent.putExtra((new StringBuilder("SCAN_RESULT_BYTE_SEGMENTS_")).append(i1).toString(), abyte1);
                        i1++;
                    }

                }
            }
            a(R.id.return_scan_result, intent, l1);
        } else
        {
            if (m == r.b)
            {
                int j1 = n.lastIndexOf("/scan");
                String s3 = (new StringBuilder()).append(n.substring(0, j1)).append("?q=").append(resulthandler.getDisplayContents()).append("&source=zxing").toString();
                a(R.id.launch_product_query, s3, l1);
                return;
            }
            if (m == r.c && o != null && o.a())
            {
                String s2 = o.a(result, resulthandler);
                a(R.id.launch_product_query, s2, l1);
                return;
            }
        }
    }

    private void c()
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(R.string.msg_camera_framework_bug));
        builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    private void d()
    {
        i.setVisibility(8);
        h.setText(R.string.msg_default_status);
        h.setVisibility(0);
        g.setVisibility(0);
        j = null;
    }

    final ViewfinderView a()
    {
        return g;
    }

    final CameraManager b()
    {
        return d;
    }

    public void drawViewfinder()
    {
        g.drawViewfinder();
    }

    public Handler getHandler()
    {
        return e;
    }

    public void handleDecode(Result result, Bitmap bitmap, float f1)
    {
        t.a();
        j = result;
        ResultHandler resulthandler = ResultHandlerFactory.makeResultHandler(this, result);
        boolean flag;
        if (bitmap != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            s.addHistoryItem(result, resulthandler);
            u.b();
            ResultPoint aresultpoint[] = result.getResultPoints();
            if (aresultpoint != null && aresultpoint.length > 0)
            {
                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                paint.setColor(getResources().getColor(R.color.result_points));
                if (aresultpoint.length == 2)
                {
                    paint.setStrokeWidth(4F);
                    a(canvas, paint, aresultpoint[0], aresultpoint[1], f1);
                } else
                if (aresultpoint.length == 4 && (result.getBarcodeFormat() == BarcodeFormat.UPC_A || result.getBarcodeFormat() == BarcodeFormat.EAN_13))
                {
                    a(canvas, paint, aresultpoint[0], aresultpoint[1], f1);
                    a(canvas, paint, aresultpoint[2], aresultpoint[3], f1);
                } else
                {
                    paint.setStrokeWidth(10F);
                    int i1 = aresultpoint.length;
                    int j1 = 0;
                    while (j1 < i1) 
                    {
                        ResultPoint resultpoint = aresultpoint[j1];
                        canvas.drawPoint(f1 * resultpoint.getX(), f1 * resultpoint.getY(), paint);
                        j1++;
                    }
                }
            }
        }
        SharedPreferences sharedpreferences;
        switch (d.a[m.ordinal()])
        {
        default:
            return;

        case 1: // '\001'
        case 2: // '\002'
            b(result, resulthandler, bitmap);
            return;

        case 3: // '\003'
            if (o == null || !o.a())
            {
                a(result, resulthandler, bitmap);
                return;
            } else
            {
                b(result, resulthandler, bitmap);
                return;
            }

        case 4: // '\004'
            sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
            break;
        }
        if (flag && sharedpreferences.getBoolean("preferences_bulk_mode", false))
        {
            String s1 = (new StringBuilder()).append(getResources().getString(R.string.msg_bulk_mode_scanned)).append(" (").append(result.getText()).append(')').toString();
            Toast.makeText(getApplicationContext(), s1, 0).show();
            restartPreviewAfterDelay(1000L);
            return;
        } else
        {
            a(result, resulthandler, bitmap);
            return;
        }
    }

    public void onActivityResult(int i1, int j1, Intent intent)
    {
        if (j1 == -1 && i1 == 47820)
        {
            int k1 = intent.getIntExtra("ITEM_NUMBER", -1);
            if (k1 >= 0)
            {
                a(s.buildHistoryItem(k1).getResult());
            }
        }
    }

    public void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("CaptureActivity");
        TraceMachine.enterMethod(_nr_trace, "CaptureActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(R.layout.capture);
        k = false;
        s = new HistoryManager(this);
        s.trimHistory();
        t = new n(this);
        u = new b(this);
        v = new a(this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "CaptureActivity#onCreate", null);
          goto _L1
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.capture, menu);
        return false;
    }

    protected void onDestroy()
    {
        t.b();
        super.onDestroy();
    }

    public boolean onKeyDown(int i1, KeyEvent keyevent)
    {
        boolean flag = true;
        i1;
        JVM INSTR lookupswitch 5: default 52
    //                   4: 61
    //                   24: 127
    //                   25: 117
    //                   27: 59
    //                   80: 59;
           goto _L1 _L2 _L3 _L4 _L5 _L5
_L1:
        flag = super.onKeyDown(i1, keyevent);
_L5:
        return flag;
_L2:
        if (m == r.a)
        {
            setResult(100);
            finish();
            return flag;
        }
        if ((m == r.d || m == r.c) && j != null)
        {
            restartPreviewAfterDelay(0L);
            return flag;
        }
          goto _L1
_L4:
        d.setTorch(false);
        return flag;
_L3:
        d.setTorch(flag);
        return flag;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(0x80000);
        int i1 = menuitem.getItemId();
        if (i1 == R.id.menu_share)
        {
            intent.setClassName(this, com/google/zxing/client/android/share/ShareActivity.getName());
            startActivity(intent);
        } else
        if (i1 == R.id.menu_history)
        {
            intent.setClassName(this, com/google/zxing/client/android/history/HistoryActivity.getName());
            startActivityForResult(intent, 47820);
        } else
        if (i1 == R.id.menu_settings)
        {
            intent.setClassName(this, com/google/zxing/client/android/PreferencesActivity.getName());
            startActivity(intent);
        } else
        if (i1 == R.id.menu_help)
        {
            intent.setClassName(this, com/google/zxing/client/android/HelpActivity.getName());
            startActivity(intent);
        } else
        {
            return super.onOptionsItemSelected(menuitem);
        }
        return true;
    }

    protected void onPause()
    {
        if (e != null)
        {
            e.quitSynchronously();
            e = null;
        }
        t.onPause();
        v.a();
        d.closeDriver();
        if (!k)
        {
            ((SurfaceView)findViewById(R.id.preview_view)).getHolder().removeCallback(this);
        }
        super.onPause();
    }

    protected void onResume()
    {
        boolean flag;
        String s2;
        flag = true;
        super.onResume();
        d = new CameraManager(getApplication());
        g = (ViewfinderView)findViewById(R.id.viewfinder_view);
        g.setCameraManager(d);
        i = findViewById(R.id.result_view);
        h = (TextView)findViewById(R.id.status_view);
        e = null;
        j = null;
        d();
        SurfaceHolder surfaceholder = ((SurfaceView)findViewById(R.id.preview_view)).getHolder();
        Intent intent;
        boolean flag1;
        String s1;
        String s3;
        int j1;
        int k1;
        if (k)
        {
            a(surfaceholder);
        } else
        {
            surfaceholder.addCallback(this);
            surfaceholder.setType(3);
        }
        u.a();
        v.a(d);
        t.onResume();
        intent = getIntent();
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("preferences_copy_to_clipboard", flag) && (intent == null || intent.getBooleanExtra("SAVE_HISTORY", flag)))
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        l = flag1;
        m = r.d;
        p = null;
        r = null;
        if (intent == null) goto _L2; else goto _L1
_L1:
        s1 = intent.getAction();
        s2 = intent.getDataString();
        if ("com.google.zxing.client.android.SCAN".equals(s1))
        {
            m = r.a;
            p = com.google.zxing.client.android.f.a(intent);
            q = com.google.zxing.client.android.h.a(intent);
            if (intent.hasExtra("SCAN_WIDTH") && intent.hasExtra("SCAN_HEIGHT"))
            {
                j1 = intent.getIntExtra("SCAN_WIDTH", 0);
                k1 = intent.getIntExtra("SCAN_HEIGHT", 0);
                if (j1 > 0 && k1 > 0)
                {
                    d.setManualFramingRect(j1, k1);
                }
            }
            s3 = intent.getStringExtra("PROMPT_MESSAGE");
            if (s3 != null)
            {
                h.setText(s3);
            }
        } else
        {
label0:
            {
                if (s2 == null || !s2.contains("http://www.google") || !s2.contains("/m/products/scan"))
                {
                    break label0;
                }
                m = r.b;
                n = s2;
                p = f.a;
            }
        }
_L5:
        r = intent.getStringExtra("CHARACTER_SET");
_L2:
        return;
        String as[];
        int i1;
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_513;
        }
        as = b;
        i1 = 0;
_L6:
        if (i1 >= 2)
        {
            break MISSING_BLOCK_LABEL_513;
        }
        if (!s2.startsWith(as[i1])) goto _L4; else goto _L3
_L3:
        if (flag)
        {
            m = r.c;
            n = s2;
            Uri uri = Uri.parse(s2);
            o = new o(uri);
            p = com.google.zxing.client.android.f.a(uri);
            q = com.google.zxing.client.android.h.a(uri);
        }
          goto _L5
_L4:
        i1++;
          goto _L6
        flag = false;
          goto _L3
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

    public void restartPreviewAfterDelay(long l1)
    {
        if (e != null)
        {
            e.sendEmptyMessageDelayed(R.id.restart_preview, l1);
        }
        d();
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i1, int j1, int k1)
    {
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        if (surfaceholder == null)
        {
            FkLogger.error(a, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!k)
        {
            k = true;
            a(surfaceholder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        k = false;
    }

    static 
    {
        c = EnumSet.of(ResultMetadataType.ISSUE_NUMBER, ResultMetadataType.SUGGESTED_PRICE, ResultMetadataType.ERROR_CORRECTION_LEVEL, ResultMetadataType.POSSIBLE_COUNTRY);
    }
}
