// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.internal.gt;
import com.google.android.gms.internal.gu;
import com.google.android.gms.internal.gw;
import com.google.android.gms.internal.gx;

public abstract class a
{

    final a Fm;
    protected int Fn;
    protected int Fo;
    private boolean Fp;
    private boolean Fq;
    protected int Fr;

    public com.google.android.gms.common.images.a(Uri uri, int i)
    {
        Fn = 0;
        Fo = 0;
        Fp = true;
        Fq = false;
        Fm = new a(uri);
        Fo = i;
    }

    private Drawable a(Context context, gw gw1, int i)
    {
        Resources resources = context.getResources();
        if (Fr > 0)
        {
            com.google.android.gms.internal.gw.a a1 = new com.google.android.gms.internal.gw.a(i, Fr);
            Drawable drawable = (Drawable)gw1.get(a1);
            if (drawable == null)
            {
                drawable = resources.getDrawable(i);
                if ((1 & Fr) != 0)
                {
                    drawable = a(resources, drawable);
                }
                gw1.put(a1, drawable);
            }
            return drawable;
        } else
        {
            return resources.getDrawable(i);
        }
    }

    protected Drawable a(Resources resources, Drawable drawable)
    {
        return gu.a(resources, drawable);
    }

    protected gt a(Drawable drawable, Drawable drawable1)
    {
        if (drawable != null)
        {
            if (drawable instanceof gt)
            {
                drawable = ((gt)drawable).fg();
            }
        } else
        {
            drawable = null;
        }
        return new gt(drawable, drawable1);
    }

    void a(Context context, Bitmap bitmap, boolean flag)
    {
        gx.c(bitmap);
        if ((1 & Fr) != 0)
        {
            bitmap = gu.a(bitmap);
        }
        a(((Drawable) (new BitmapDrawable(context.getResources(), bitmap))), flag, false, true);
    }

    void a(Context context, gw gw1)
    {
        int i = Fn;
        Drawable drawable = null;
        if (i != 0)
        {
            drawable = a(context, gw1, Fn);
        }
        a(drawable, false, true, false);
    }

    void a(Context context, gw gw1, boolean flag)
    {
        int i = Fo;
        Drawable drawable = null;
        if (i != 0)
        {
            drawable = a(context, gw1, Fo);
        }
        a(drawable, flag, false, false);
    }

    protected abstract void a(Drawable drawable, boolean flag, boolean flag1, boolean flag2);

    public void aj(int i)
    {
        Fo = i;
    }

    protected boolean b(boolean flag, boolean flag1)
    {
        return Fp && !flag1 && (!flag || Fq);
    }

    private class a
    {

        public final Uri uri;

        public final boolean equals(Object obj)
        {
            if (!(obj instanceof a))
            {
                return false;
            }
            if (this == obj)
            {
                return true;
            } else
            {
                return hk.equal(((a)obj).uri, uri);
            }
        }

        public final int hashCode()
        {
            Object aobj[] = new Object[1];
            aobj[0] = uri;
            return hk.hashCode(aobj);
        }

        public a(Uri uri1)
        {
            uri = uri1;
        }
    }

}
