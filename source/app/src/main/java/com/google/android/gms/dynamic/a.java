// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.LinkedList;

// Referenced classes of package com.google.android.gms.dynamic:
//            LifecycleDelegate, f

public abstract class a
{

    private LifecycleDelegate LX;
    private Bundle LY;
    private LinkedList LZ;
    private final f Ma = new _cls1();

    public a()
    {
    }

    static Bundle a(a a1, Bundle bundle)
    {
        a1.LY = bundle;
        return bundle;
    }

    static LifecycleDelegate a(a a1, LifecycleDelegate lifecycledelegate)
    {
        a1.LX = lifecycledelegate;
        return lifecycledelegate;
    }

    static LinkedList a(a a1)
    {
        return a1.LZ;
    }

    private void a(Bundle bundle, a a1)
    {
        if (LX != null)
        {
            a1.b(LX);
            return;
        }
        if (LZ == null)
        {
            LZ = new LinkedList();
        }
        LZ.add(a1);
        if (bundle != null)
        {
            if (LY == null)
            {
                LY = (Bundle)bundle.clone();
            } else
            {
                LY.putAll(bundle);
            }
        }
        a(Ma);
    }

    static LifecycleDelegate b(com.google.android.gms.dynamic.a a1)
    {
        return a1.LX;
    }

    public static void b(FrameLayout framelayout)
    {
        Context context = framelayout.getContext();
        int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        String s = GooglePlayServicesUtil.d(context, i);
        String s1 = GooglePlayServicesUtil.e(context, i);
        LinearLayout linearlayout = new LinearLayout(framelayout.getContext());
        linearlayout.setOrientation(1);
        linearlayout.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-2, -2));
        framelayout.addView(linearlayout);
        TextView textview = new TextView(framelayout.getContext());
        textview.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-2, -2));
        textview.setText(s);
        linearlayout.addView(textview);
        if (s1 != null)
        {
            Button button = new Button(context);
            button.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-2, -2));
            button.setText(s1);
            linearlayout.addView(button);
            button.setOnClickListener(new _cls5(context, i));
        }
    }

    private void ca(int i)
    {
        for (; !LZ.isEmpty() && ((a)LZ.getLast()).getState() >= i; LZ.removeLast()) { }
    }

    protected void a(FrameLayout framelayout)
    {
        b(framelayout);
    }

    protected abstract void a(f f);

    public LifecycleDelegate gH()
    {
        return LX;
    }

    public void onCreate(Bundle bundle)
    {
        a(bundle, new _cls3(bundle));
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        FrameLayout framelayout = new FrameLayout(layoutinflater.getContext());
        a(bundle, new _cls4(framelayout, layoutinflater, viewgroup, bundle));
        if (LX == null)
        {
            a(framelayout);
        }
        return framelayout;
    }

    public void onDestroy()
    {
        if (LX != null)
        {
            LX.onDestroy();
            return;
        } else
        {
            ca(1);
            return;
        }
    }

    public void onDestroyView()
    {
        if (LX != null)
        {
            LX.onDestroyView();
            return;
        } else
        {
            ca(2);
            return;
        }
    }

    public void onInflate(Activity activity, Bundle bundle, Bundle bundle1)
    {
        a(bundle1, new _cls2(activity, bundle, bundle1));
    }

    public void onLowMemory()
    {
        if (LX != null)
        {
            LX.onLowMemory();
        }
    }

    public void onPause()
    {
        if (LX != null)
        {
            LX.onPause();
            return;
        } else
        {
            ca(5);
            return;
        }
    }

    public void onResume()
    {
        a(((Bundle) (null)), new _cls7());
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        if (LX != null)
        {
            LX.onSaveInstanceState(bundle);
        } else
        if (LY != null)
        {
            bundle.putAll(LY);
            return;
        }
    }

    public void onStart()
    {
        a(((Bundle) (null)), new _cls6());
    }

    public void onStop()
    {
        if (LX != null)
        {
            LX.onStop();
            return;
        } else
        {
            ca(4);
            return;
        }
    }

    private class _cls1
        implements f
    {

        final com.google.android.gms.dynamic.a Mb;

        public void a(LifecycleDelegate lifecycledelegate)
        {
            com.google.android.gms.dynamic.a.a(Mb, lifecycledelegate);
            for (Iterator iterator = com.google.android.gms.dynamic.a.a(Mb).iterator(); iterator.hasNext(); ((a)iterator.next()).b(com.google.android.gms.dynamic.a.b(Mb))) { }
            com.google.android.gms.dynamic.a.a(Mb).clear();
            com.google.android.gms.dynamic.a.a(Mb, null);
        }

        _cls1()
        {
            Mb = a.this;
            super();
        }
    }


    private class a
    {

        public abstract void b(LifecycleDelegate lifecycledelegate);

        public abstract int getState();
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        final int Mi;
        final Context qu;

        public final void onClick(View view)
        {
            qu.startActivity(GooglePlayServicesUtil.c(qu, Mi));
        }

        _cls5(Context context, int i)
        {
            qu = context;
            Mi = i;
            super();
        }
    }


    private class _cls3
        implements a
    {

        final com.google.android.gms.dynamic.a Mb;
        final Bundle Me;

        public void b(LifecycleDelegate lifecycledelegate)
        {
            com.google.android.gms.dynamic.a.b(Mb).onCreate(Me);
        }

        public int getState()
        {
            return 1;
        }

        _cls3(Bundle bundle)
        {
            Mb = com.google.android.gms.dynamic.a.this;
            Me = bundle;
            super();
        }
    }


    private class _cls4
        implements a
    {

        final com.google.android.gms.dynamic.a Mb;
        final Bundle Me;
        final FrameLayout Mf;
        final LayoutInflater Mg;
        final ViewGroup Mh;

        public void b(LifecycleDelegate lifecycledelegate)
        {
            Mf.removeAllViews();
            Mf.addView(com.google.android.gms.dynamic.a.b(Mb).onCreateView(Mg, Mh, Me));
        }

        public int getState()
        {
            return 2;
        }

        _cls4(FrameLayout framelayout, LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
        {
            Mb = com.google.android.gms.dynamic.a.this;
            Mf = framelayout;
            Mg = layoutinflater;
            Mh = viewgroup;
            Me = bundle;
            super();
        }
    }


    private class _cls2
        implements a
    {

        final com.google.android.gms.dynamic.a Mb;
        final Activity Mc;
        final Bundle Md;
        final Bundle Me;

        public void b(LifecycleDelegate lifecycledelegate)
        {
            com.google.android.gms.dynamic.a.b(Mb).onInflate(Mc, Md, Me);
        }

        public int getState()
        {
            return 0;
        }

        _cls2(Activity activity, Bundle bundle, Bundle bundle1)
        {
            Mb = com.google.android.gms.dynamic.a.this;
            Mc = activity;
            Md = bundle;
            Me = bundle1;
            super();
        }
    }


    private class _cls7
        implements a
    {

        final com.google.android.gms.dynamic.a Mb;

        public void b(LifecycleDelegate lifecycledelegate)
        {
            com.google.android.gms.dynamic.a.b(Mb).onResume();
        }

        public int getState()
        {
            return 5;
        }

        _cls7()
        {
            Mb = com.google.android.gms.dynamic.a.this;
            super();
        }
    }


    private class _cls6
        implements a
    {

        final com.google.android.gms.dynamic.a Mb;

        public void b(LifecycleDelegate lifecycledelegate)
        {
            com.google.android.gms.dynamic.a.b(Mb).onStart();
        }

        public int getState()
        {
            return 4;
        }

        _cls6()
        {
            Mb = com.google.android.gms.dynamic.a.this;
            super();
        }
    }

}
