// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.dynamic;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// Referenced classes of package com.google.android.gms.dynamic:
//            e, d, c

public final class b extends c.a
{

    private Fragment Mj;

    private b(Fragment fragment)
    {
        Mj = fragment;
    }

    public static b a(Fragment fragment)
    {
        if (fragment != null)
        {
            return new b(fragment);
        } else
        {
            return null;
        }
    }

    public final void c(d d1)
    {
        View view = (View)e.e(d1);
        Mj.registerForContextMenu(view);
    }

    public final void d(d d1)
    {
        View view = (View)e.e(d1);
        Mj.unregisterForContextMenu(view);
    }

    public final d gI()
    {
        return e.h(Mj.getActivity());
    }

    public final c gJ()
    {
        return a(Mj.getParentFragment());
    }

    public final d gK()
    {
        return e.h(Mj.getResources());
    }

    public final c gL()
    {
        return a(Mj.getTargetFragment());
    }

    public final Bundle getArguments()
    {
        return Mj.getArguments();
    }

    public final int getId()
    {
        return Mj.getId();
    }

    public final boolean getRetainInstance()
    {
        return Mj.getRetainInstance();
    }

    public final String getTag()
    {
        return Mj.getTag();
    }

    public final int getTargetRequestCode()
    {
        return Mj.getTargetRequestCode();
    }

    public final boolean getUserVisibleHint()
    {
        return Mj.getUserVisibleHint();
    }

    public final d getView()
    {
        return e.h(Mj.getView());
    }

    public final boolean isAdded()
    {
        return Mj.isAdded();
    }

    public final boolean isDetached()
    {
        return Mj.isDetached();
    }

    public final boolean isHidden()
    {
        return Mj.isHidden();
    }

    public final boolean isInLayout()
    {
        return Mj.isInLayout();
    }

    public final boolean isRemoving()
    {
        return Mj.isRemoving();
    }

    public final boolean isResumed()
    {
        return Mj.isResumed();
    }

    public final boolean isVisible()
    {
        return Mj.isVisible();
    }

    public final void setHasOptionsMenu(boolean flag)
    {
        Mj.setHasOptionsMenu(flag);
    }

    public final void setMenuVisibility(boolean flag)
    {
        Mj.setMenuVisibility(flag);
    }

    public final void setRetainInstance(boolean flag)
    {
        Mj.setRetainInstance(flag);
    }

    public final void setUserVisibleHint(boolean flag)
    {
        Mj.setUserVisibleHint(flag);
    }

    public final void startActivity(Intent intent)
    {
        Mj.startActivity(intent);
    }

    public final void startActivityForResult(Intent intent, int i)
    {
        Mj.startActivityForResult(intent, i);
    }
}
