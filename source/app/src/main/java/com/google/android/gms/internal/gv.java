// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.net.Uri;
import android.widget.ImageView;

public final class gv extends ImageView
{

    private Uri FO;
    private int FP;
    private int FQ;
    private a FR;

    public final void al(int i)
    {
        FP = i;
    }

    public final int fi()
    {
        return FP;
    }

    public final void g(Uri uri)
    {
        FO = uri;
    }

    protected final void onDraw(Canvas canvas)
    {
        if (FR != null)
        {
            canvas.clipPath(FR.d(getWidth(), getHeight()));
        }
        super.onDraw(canvas);
        if (FQ != 0)
        {
            canvas.drawColor(FQ);
        }
    }

    private class a
    {

        public abstract Path d(int i, int j);
    }

}
