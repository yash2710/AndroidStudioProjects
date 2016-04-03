// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.camera.CameraManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ViewfinderView extends View
{

    private static final int a[] = {
        0, 64, 128, 192, 255, 192, 128, 64
    };
    private CameraManager b;
    private final Paint c = new Paint(1);
    private Bitmap d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private int i;
    private List j;
    private List k;

    public ViewfinderView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        Resources resources = getResources();
        e = resources.getColor(R.color.viewfinder_mask);
        f = resources.getColor(R.color.result_view);
        g = resources.getColor(R.color.viewfinder_laser);
        h = resources.getColor(R.color.possible_result_points);
        i = 0;
        j = new ArrayList(5);
        k = null;
    }

    public final void addPossibleResultPoint(ResultPoint resultpoint)
    {
        List list = j;
        list;
        JVM INSTR monitorenter ;
        int l;
        list.add(resultpoint);
        l = list.size();
        if (l <= 20)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        list.subList(0, l - 10).clear();
        list;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void drawResultBitmap(Bitmap bitmap)
    {
        d = bitmap;
        invalidate();
    }

    public final void drawViewfinder()
    {
        Bitmap bitmap = d;
        d = null;
        if (bitmap != null)
        {
            bitmap.recycle();
        }
        invalidate();
    }

    public final void onDraw(Canvas canvas)
    {
        Rect rect;
        float f1;
        float f2;
        List list;
        List list1;
        int i2;
        int j2;
        while (b == null || (rect = b.getFramingRect()) == null) 
        {
            return;
        }
        int l = canvas.getWidth();
        int i1 = canvas.getHeight();
        Paint paint = c;
        int j1;
        if (d != null)
        {
            j1 = f;
        } else
        {
            j1 = e;
        }
        paint.setColor(j1);
        canvas.drawRect(0.0F, 0.0F, l, rect.top, c);
        canvas.drawRect(0.0F, rect.top, rect.left, 1 + rect.bottom, c);
        canvas.drawRect(1 + rect.right, rect.top, l, 1 + rect.bottom, c);
        canvas.drawRect(0.0F, 1 + rect.bottom, l, i1, c);
        if (d != null)
        {
            c.setAlpha(160);
            canvas.drawBitmap(d, null, rect, c);
            return;
        }
        c.setColor(g);
        c.setAlpha(a[i]);
        int k1 = 1 + i;
        a;
        i = k1 % 8;
        int l1 = rect.height() / 2 + rect.top;
        canvas.drawRect(2 + rect.left, l1 - 1, -1 + rect.right, l1 + 2, c);
        Rect rect1 = b.getFramingRectInPreview();
        f1 = (float)rect.width() / (float)rect1.width();
        f2 = (float)rect.height() / (float)rect1.height();
        list = j;
        list1 = k;
        i2 = rect.left;
        j2 = rect.top;
        if (!list.isEmpty()) goto _L2; else goto _L1
_L1:
        k = null;
_L6:
        if (list1 == null) goto _L4; else goto _L3
_L3:
        c.setAlpha(80);
        c.setColor(h);
        list1;
        JVM INSTR monitorenter ;
        ResultPoint resultpoint;
        for (Iterator iterator1 = list1.iterator(); iterator1.hasNext(); canvas.drawCircle(i2 + (int)(f1 * resultpoint.getX()), j2 + (int)(f2 * resultpoint.getY()), 3F, c))
        {
            resultpoint = (ResultPoint)iterator1.next();
        }

          goto _L5
        Exception exception1;
        exception1;
        throw exception1;
_L2:
        j = new ArrayList(5);
        k = list;
        c.setAlpha(160);
        c.setColor(h);
        list;
        JVM INSTR monitorenter ;
        ResultPoint resultpoint1;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); canvas.drawCircle(i2 + (int)(f1 * resultpoint1.getX()), j2 + (int)(f2 * resultpoint1.getY()), 6F, c))
        {
            resultpoint1 = (ResultPoint)iterator.next();
        }

        break MISSING_BLOCK_LABEL_585;
        Exception exception;
        exception;
        throw exception;
        list;
        JVM INSTR monitorexit ;
          goto _L6
_L5:
        list1;
        JVM INSTR monitorexit ;
_L4:
        postInvalidateDelayed(80L, -6 + rect.left, -6 + rect.top, 6 + rect.right, 6 + rect.bottom);
        return;
    }

    public final void setCameraManager(CameraManager cameramanager)
    {
        b = cameramanager;
    }

}
