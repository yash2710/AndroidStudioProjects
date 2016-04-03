// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.widget.PopupWindow;
import com.flipkart.logging.FkLogger;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

final class x
    implements android.view.ViewTreeObserver.OnScrollChangedListener
{

    private Field a;
    private PopupWindow b;
    private android.view.ViewTreeObserver.OnScrollChangedListener c;

    x(Field field, PopupWindow popupwindow, android.view.ViewTreeObserver.OnScrollChangedListener onscrollchangedlistener)
    {
        a = field;
        b = popupwindow;
        c = onscrollchangedlistener;
        super();
    }

    public final void onScrollChanged()
    {
        WeakReference weakreference = (WeakReference)a.get(b);
        if (weakreference == null)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        if (weakreference.get() == null)
        {
            return;
        }
        try
        {
            c.onScrollChanged();
            return;
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            FkLogger.printStackTrace(illegalaccessexception);
            return;
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
        }
    }
}
