// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.ScrollView;
import android.widget.TextView;
import com.crashlytics.android.internal.aQ;

// Referenced classes of package com.crashlytics.android:
//            U, Crashlytics, x, V, 
//            W, Y

final class T
    implements Runnable
{

    final Y a;
    final Crashlytics b;
    private Activity c;
    private x d;
    private aQ e;

    T(Crashlytics crashlytics, Activity activity, Y y, x x1, aQ aq)
    {
        b = crashlytics;
        c = activity;
        a = y;
        d = x1;
        e = aq;
        super();
    }

    public final void run()
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(c);
        U u = new U(this);
        float f = c.getResources().getDisplayMetrics().density;
        Crashlytics _tmp = b;
        int i = Crashlytics.a(f, 5);
        TextView textview = new TextView(c);
        textview.setAutoLinkMask(15);
        textview.setText(d.b());
        textview.setTextAppearance(c, 0x1030044);
        textview.setPadding(i, i, i, i);
        textview.setFocusable(false);
        ScrollView scrollview = new ScrollView(c);
        Crashlytics _tmp1 = b;
        int j = Crashlytics.a(f, 14);
        Crashlytics _tmp2 = b;
        int k = Crashlytics.a(f, 2);
        Crashlytics _tmp3 = b;
        int l = Crashlytics.a(f, 10);
        Crashlytics _tmp4 = b;
        scrollview.setPadding(j, k, l, Crashlytics.a(f, 12));
        scrollview.addView(textview);
        builder.setView(scrollview).setTitle(d.a()).setCancelable(false).setNeutralButton(d.c(), u);
        if (e.d)
        {
            V v = new V(this);
            builder.setNegativeButton(d.e(), v);
        }
        if (e.f)
        {
            W w = new W(this);
            builder.setPositiveButton(d.d(), w);
        }
        builder.show();
    }
}
