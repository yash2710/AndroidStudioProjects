// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

// Referenced classes of package com.google.android.gms.dynamic:
//            e, d, c

public final class h extends c.a
{

    private Fragment FV;

    private h(Fragment fragment)
    {
        FV = fragment;
    }

    public static h a(Fragment fragment)
    {
        if (fragment != null)
        {
            return new h(fragment);
        } else
        {
            return null;
        }
    }

    public final void c(d d1)
    {
        View view = (View)e.e(d1);
        FV.registerForContextMenu(view);
    }

    public final void d(d d1)
    {
        View view = (View)e.e(d1);
        FV.unregisterForContextMenu(view);
    }

    public final d gI()
    {
        return e.h(FV.getActivity());
    }

    public final c gJ()
    {
        return a(FV.getParentFragment());
    }

    public final d gK()
    {
        return e.h(FV.getResources());
    }

    public final c gL()
    {
        return a(FV.getTargetFragment());
    }

    public final Bundle getArguments()
    {
        return FV.getArguments();
    }

    public final int getId()
    {
        return FV.getId();
    }

    public final boolean getRetainInstance()
    {
        return FV.getRetainInstance();
    }

    public final String getTag()
    {
        return FV.getTag();
    }

    public final int getTargetRequestCode()
    {
        return FV.getTargetRequestCode();
    }

    public final boolean getUserVisibleHint()
    {
        return FV.getUserVisibleHint();
    }

    public final d getView()
    {
        return e.h(FV.getView());
    }

    public final boolean isAdded()
    {
        return FV.isAdded();
    }

    public final boolean isDetached()
    {
        return FV.isDetached();
    }

    public final boolean isHidden()
    {
        return FV.isHidden();
    }

    public final boolean isInLayout()
    {
        return FV.isInLayout();
    }

    public final boolean isRemoving()
    {
        return FV.isRemoving();
    }

    public final boolean isResumed()
    {
        return FV.isResumed();
    }

    public final boolean isVisible()
    {
        return FV.isVisible();
    }

    public final void setHasOptionsMenu(boolean flag)
    {
        FV.setHasOptionsMenu(flag);
    }

    public final void setMenuVisibility(boolean flag)
    {
        FV.setMenuVisibility(flag);
    }

    public final void setRetainInstance(boolean flag)
    {
        FV.setRetainInstance(flag);
    }

    public final void setUserVisibleHint(boolean flag)
    {
        FV.setUserVisibleHint(flag);
    }

    public final void startActivity(Intent intent)
    {
        FV.startActivity(intent);
    }

    public final void startActivityForResult(Intent intent, int i)
    {
        FV.startActivityForResult(intent, i);
    }
}
