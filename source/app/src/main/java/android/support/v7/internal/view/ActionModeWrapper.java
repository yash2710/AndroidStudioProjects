// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.view;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

// Referenced classes of package android.support.v7.internal.view:
//            SupportMenuInflater

public class ActionModeWrapper extends ActionMode
{

    final MenuInflater mInflater;
    final android.view.ActionMode mWrappedObject;

    public ActionModeWrapper(Context context, android.view.ActionMode actionmode)
    {
        mWrappedObject = actionmode;
        mInflater = new SupportMenuInflater(context);
    }

    public void finish()
    {
        mWrappedObject.finish();
    }

    public View getCustomView()
    {
        return mWrappedObject.getCustomView();
    }

    public Menu getMenu()
    {
        return MenuWrapperFactory.createMenuWrapper(mWrappedObject.getMenu());
    }

    public MenuInflater getMenuInflater()
    {
        return mInflater;
    }

    public CharSequence getSubtitle()
    {
        return mWrappedObject.getSubtitle();
    }

    public Object getTag()
    {
        return mWrappedObject.getTag();
    }

    public CharSequence getTitle()
    {
        return mWrappedObject.getTitle();
    }

    public void invalidate()
    {
        mWrappedObject.invalidate();
    }

    public void setCustomView(View view)
    {
        mWrappedObject.setCustomView(view);
    }

    public void setSubtitle(int i)
    {
        mWrappedObject.setSubtitle(i);
    }

    public void setSubtitle(CharSequence charsequence)
    {
        mWrappedObject.setSubtitle(charsequence);
    }

    public void setTag(Object obj)
    {
        mWrappedObject.setTag(obj);
    }

    public void setTitle(int i)
    {
        mWrappedObject.setTitle(i);
    }

    public void setTitle(CharSequence charsequence)
    {
        mWrappedObject.setTitle(charsequence);
    }
}
