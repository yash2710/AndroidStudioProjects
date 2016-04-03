// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.internal:
//            ef, ex, af, aa, 
//            al, ae, eu, ad, 
//            bb, z, ev

public final class ac
    implements android.view.ViewTreeObserver.OnGlobalLayoutListener, android.view.ViewTreeObserver.OnScrollChangedListener
{

    private static final long lG;
    private final ae lA;
    private boolean lB;
    private final WindowManager lC;
    private final PowerManager lD;
    private final KeyguardManager lE;
    private ad lF;
    private boolean lH;
    private long lI;
    private boolean lJ;
    private BroadcastReceiver lK;
    private HashSet lL;
    private boolean lj;
    private final Object ls;
    private final WeakReference lv;
    private WeakReference lw;
    private final WeakReference lx;
    private final aa ly;
    private final Context lz;

    public ac(al al1, ef ef1)
    {
        this(al1, ef1, ef1.oy.cd(), ((View) (ef1.oy)), ((ae) (new af(ef1.oy.getContext(), ef1.oy.cd()))));
    }

    public ac(al al1, ef ef1, ev ev, View view, ae ae1)
    {
        ls = new Object();
        lj = false;
        lH = false;
        lI = 0x8000000000000000L;
        lL = new HashSet();
        lv = new WeakReference(ef1);
        lx = new WeakReference(view);
        lw = new WeakReference(null);
        lJ = true;
        ly = new aa(Integer.toString(ef1.hashCode()), ev, al1.me, ef1.ry);
        lA = ae1;
        lC = (WindowManager)view.getContext().getSystemService("window");
        lD = (PowerManager)view.getContext().getApplicationContext().getSystemService("power");
        lE = (KeyguardManager)view.getContext().getSystemService("keyguard");
        lz = view.getContext().getApplicationContext();
        a(ae1);
        lA.a(new _cls1());
        b(lA);
        eu.B((new StringBuilder("Tracking ad unit: ")).append(ly.au()).toString());
    }

    static ae b(ac ac1)
    {
        return ac1.lA;
    }

    static boolean b(ac ac1, boolean flag)
    {
        ac1.lB = flag;
        return flag;
    }

    static aa c(ac ac1)
    {
        return ac1.ly;
    }

    protected final int a(int i, DisplayMetrics displaymetrics)
    {
        float f = displaymetrics.density;
        return (int)((float)i / f);
    }

    public final void a(ad ad1)
    {
        synchronized (ls)
        {
            lF = ad1;
        }
    }

    protected final void a(ae ae1)
    {
        ae1.d("http://googleads.g.doubleclick.net/mads/static/sdk/native/sdk-core-v40.html");
    }

    protected final void a(ex ex1, Map map)
    {
        e(false);
    }

    public final void a(z z1)
    {
        lL.add(z1);
    }

    protected final void a(JSONObject jsonobject)
    {
        JSONArray jsonarray = new JSONArray();
        JSONObject jsonobject1 = new JSONObject();
        jsonarray.put(jsonobject);
        jsonobject1.put("units", jsonarray);
        lA.a("AFMA_updateActiveView", jsonobject1);
    }

    protected final boolean a(View view, boolean flag)
    {
        return view.getVisibility() == 0 && flag && view.isShown() && lD.isScreenOn() && !lE.inKeyguardRestrictedInputMode();
    }

    protected final void aA()
    {
        View view = (View)lx.get();
        if (view != null)
        {
            ViewTreeObserver viewtreeobserver = (ViewTreeObserver)lw.get();
            ViewTreeObserver viewtreeobserver1 = view.getViewTreeObserver();
            if (viewtreeobserver1 != viewtreeobserver)
            {
                lw = new WeakReference(viewtreeobserver1);
                viewtreeobserver1.addOnScrollChangedListener(this);
                viewtreeobserver1.addOnGlobalLayoutListener(this);
                return;
            }
        }
    }

    protected final void aB()
    {
        ViewTreeObserver viewtreeobserver = (ViewTreeObserver)lw.get();
        if (viewtreeobserver == null || !viewtreeobserver.isAlive())
        {
            return;
        } else
        {
            viewtreeobserver.removeOnScrollChangedListener(this);
            viewtreeobserver.removeGlobalOnLayoutListener(this);
            return;
        }
    }

    protected final JSONObject aC()
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("afmaVersion", ly.as()).put("activeViewJSON", ly.at()).put("timestamp", TimeUnit.NANOSECONDS.toMillis(System.nanoTime())).put("adFormat", ly.ar()).put("hashCode", ly.au());
        return jsonobject;
    }

    protected final JSONObject aD()
    {
        JSONObject jsonobject = aC();
        jsonobject.put("doneReasonCode", "u");
        return jsonobject;
    }

    protected final void av()
    {
label0:
        {
            IntentFilter intentfilter;
            synchronized (ls)
            {
                if (lK == null)
                {
                    break label0;
                }
            }
            return;
        }
        intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.SCREEN_ON");
        intentfilter.addAction("android.intent.action.SCREEN_OFF");
        lK = new _cls2();
        lz.registerReceiver(lK, intentfilter);
        obj;
        JVM INSTR monitorexit ;
    }

    protected final void aw()
    {
        synchronized (ls)
        {
            if (lK != null)
            {
                lz.unregisterReceiver(lK);
                lK = null;
            }
        }
    }

    public final void ax()
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        if (!lJ) goto _L2; else goto _L1
_L1:
        aB();
        aw();
        a(aD());
_L3:
        lJ = false;
        ay();
        eu.B((new StringBuilder("Untracked ad unit: ")).append(ly.au()).toString());
_L2:
        return;
        JSONException jsonexception;
        jsonexception;
        eu.b("JSON Failure while processing active view data.", jsonexception);
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    protected final void ay()
    {
        if (lF != null)
        {
            lF.a(this);
        }
    }

    public final boolean az()
    {
        boolean flag;
        synchronized (ls)
        {
            flag = lJ;
        }
        return flag;
    }

    protected final void b(ae ae1)
    {
        ae1.a("/updateActiveView", new _cls3());
        ae1.a("/activeViewPingSent", new _cls4());
        ae1.a("/visibilityChanged", new _cls5());
        ae1.a("/viewabilityChanged", bb.mT);
    }

    protected final JSONObject c(View view)
    {
        int ai[] = new int[2];
        int ai1[] = new int[2];
        view.getLocationOnScreen(ai);
        view.getLocationInWindow(ai1);
        JSONObject jsonobject = aC();
        DisplayMetrics displaymetrics = view.getContext().getResources().getDisplayMetrics();
        Rect rect = new Rect();
        rect.left = ai[0];
        rect.top = ai[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect1 = new Rect();
        rect1.right = lC.getDefaultDisplay().getWidth();
        rect1.bottom = lC.getDefaultDisplay().getHeight();
        Rect rect2 = new Rect();
        boolean flag = view.getGlobalVisibleRect(rect2, null);
        Rect rect3 = new Rect();
        view.getLocalVisibleRect(rect3);
        jsonobject.put("viewBox", (new JSONObject()).put("top", a(rect1.top, displaymetrics)).put("bottom", a(rect1.bottom, displaymetrics)).put("left", a(rect1.left, displaymetrics)).put("right", a(rect1.right, displaymetrics))).put("adBox", (new JSONObject()).put("top", a(rect.top, displaymetrics)).put("bottom", a(rect.bottom, displaymetrics)).put("left", a(rect.left, displaymetrics)).put("right", a(rect.right, displaymetrics))).put("globalVisibleBox", (new JSONObject()).put("top", a(rect2.top, displaymetrics)).put("bottom", a(rect2.bottom, displaymetrics)).put("left", a(rect2.left, displaymetrics)).put("right", a(rect2.right, displaymetrics))).put("localVisibleBox", (new JSONObject()).put("top", a(rect3.top, displaymetrics)).put("bottom", a(rect3.bottom, displaymetrics)).put("left", a(rect3.left, displaymetrics)).put("right", a(rect3.right, displaymetrics))).put("screenDensity", displaymetrics.density).put("isVisible", a(view, flag)).put("isStopped", lH).put("isPaused", lj);
        return jsonobject;
    }

    protected final void c(ae ae1)
    {
        ae1.e("/viewabilityChanged");
        ae1.e("/visibilityChanged");
        ae1.e("/activeViewPingSent");
        ae1.e("/updateActiveView");
    }

    protected final void d(boolean flag)
    {
        for (Iterator iterator = lL.iterator(); iterator.hasNext(); ((z)iterator.next()).a(this, flag)) { }
    }

    protected final void e(boolean flag)
    {
        long l;
label0:
        {
            synchronized (ls)
            {
                if (lB && lJ)
                {
                    break label0;
                }
            }
            return;
        }
        l = System.nanoTime();
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        if (lI + lG <= l)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        ef ef1;
        View view;
        lI = l;
        ef1 = (ef)lv.get();
        view = (View)lx.get();
        boolean flag1;
        JSONException jsonexception;
        if (view != null && ef1 != null)
        {
            flag1 = false;
        } else
        {
            flag1 = true;
        }
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_116;
        }
        ax();
        obj;
        JVM INSTR monitorexit ;
        return;
        a(c(view));
_L1:
        aA();
        ay();
        obj;
        JVM INSTR monitorexit ;
        return;
        jsonexception;
        eu.b("Active view update failed.", jsonexception);
          goto _L1
    }

    public final void onGlobalLayout()
    {
        e(false);
    }

    public final void onScrollChanged()
    {
        e(true);
    }

    public final void pause()
    {
        synchronized (ls)
        {
            lj = true;
            e(false);
            lA.pause();
        }
    }

    public final void resume()
    {
        synchronized (ls)
        {
            lA.resume();
            lj = false;
            e(false);
        }
    }

    public final void stop()
    {
        synchronized (ls)
        {
            lH = true;
            e(false);
            lA.pause();
        }
    }

    static 
    {
        lG = TimeUnit.MILLISECONDS.toNanos(100L);
    }

    private class _cls1
        implements ae.a
    {

        final ac lM;

        public void aE()
        {
            ac.b(lM, true);
            lM.e(false);
            lM.av();
        }

        _cls1()
        {
            lM = ac.this;
            super();
        }
    }


    private class _cls2 extends BroadcastReceiver
    {

        final ac lM;

        public void onReceive(Context context, Intent intent)
        {
            lM.e(false);
        }

        _cls2()
        {
            lM = ac.this;
            super();
        }
    }


    private class _cls3
        implements bc
    {

        final ac lM;

        public void b(ex ex1, Map map)
        {
            lM.a(ex1, map);
        }

        _cls3()
        {
            lM = ac.this;
            super();
        }
    }


    private class _cls4
        implements bc
    {

        final ac lM;

        public void b(ex ex1, Map map)
        {
            if (map.containsKey("pingType") && "unloadPing".equals(map.get("pingType")))
            {
                lM.c(ac.b(lM));
                eu.B((new StringBuilder("Unregistered GMSG handlers for: ")).append(ac.c(lM).au()).toString());
            }
        }

        _cls4()
        {
            lM = ac.this;
            super();
        }
    }


    private class _cls5
        implements bc
    {

        final ac lM;

        public void b(ex ex1, Map map)
        {
            if (!map.containsKey("isVisible"))
            {
                return;
            }
            boolean flag;
            Boolean boolean1;
            if ("1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible")))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            boolean1 = Boolean.valueOf(flag);
            lM.d(boolean1.booleanValue());
        }

        _cls5()
        {
            lM = ac.this;
            super();
        }
    }

}
