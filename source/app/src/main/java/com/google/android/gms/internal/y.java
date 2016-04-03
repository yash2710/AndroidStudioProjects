// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


// Referenced classes of package com.google.android.gms.internal:
//            et, eu, ai, u

public final class y
{

    private final a lf;
    private final Runnable lg;
    private ai lh;
    private boolean li;
    private boolean lj;
    private long lk;

    public y(u u)
    {
        this(u, new a(et.sv));
    }

    y(u u, a a1)
    {
        li = false;
        lj = false;
        lk = 0L;
        lf = a1;
        lg = new _cls1(u);
    }

    static ai a(y y1)
    {
        return y1.lh;
    }

    static boolean a(y y1, boolean flag)
    {
        y1.li = flag;
        return flag;
    }

    public final void a(ai ai, long l)
    {
        if (li)
        {
            eu.D("An ad refresh is already scheduled.");
        } else
        {
            lh = ai;
            li = true;
            lk = l;
            if (!lj)
            {
                eu.B((new StringBuilder("Scheduling ad refresh ")).append(l).append(" milliseconds from now.").toString());
                lf.postDelayed(lg, l);
                return;
            }
        }
    }

    public final void cancel()
    {
        li = false;
        lf.removeCallbacks(lg);
    }

    public final void d(ai ai)
    {
        a(ai, 60000L);
    }

    public final void pause()
    {
        lj = true;
        if (li)
        {
            lf.removeCallbacks(lg);
        }
    }

    public final void resume()
    {
        lj = false;
        if (li)
        {
            li = false;
            a(lh, lk);
        }
    }

    private class a
    {

        private final Handler mHandler;

        public boolean postDelayed(Runnable runnable, long l)
        {
            return mHandler.postDelayed(runnable, l);
        }

        public void removeCallbacks(Runnable runnable)
        {
            mHandler.removeCallbacks(runnable);
        }

        public a(Handler handler)
        {
            mHandler = handler;
        }
    }


    private class _cls1
        implements Runnable
    {

        private final WeakReference ll;
        final u lm;
        final y ln;

        public void run()
        {
            y.a(ln, false);
            u u1 = (u)ll.get();
            if (u1 != null)
            {
                u1.b(y.a(ln));
            }
        }

        _cls1(u u1)
        {
            ln = y.this;
            lm = u1;
            super();
            ll = new WeakReference(lm);
        }
    }

}
