// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.graphics.Rect;
import android.view.View;

// Referenced classes of package net.simonvt.menudrawer:
//            MenuDrawer

final class i
    implements android.view.ViewTreeObserver.OnScrollChangedListener
{

    private MenuDrawer a;

    i(MenuDrawer menudrawer)
    {
        a = menudrawer;
        super();
    }

    public final void onScrollChanged()
    {
        if (a.mActiveView != null && a.isViewDescendant(a.mActiveView))
        {
            a.mActiveView.getDrawingRect(MenuDrawer.b(a));
            a.offsetDescendantRectToMyCoords(a.mActiveView, MenuDrawer.b(a));
            if (MenuDrawer.b(a).left != a.mActiveRect.left || MenuDrawer.b(a).top != a.mActiveRect.top || MenuDrawer.b(a).right != a.mActiveRect.right || MenuDrawer.b(a).bottom != a.mActiveRect.bottom)
            {
                a.invalidate();
            }
        }
    }
}
