// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuItemWrapperICS;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.reflect.Constructor;

// Referenced classes of package android.support.v7.internal.view:
//            SupportMenuInflater

class resetGroup
{

    private static final int defaultGroupId = 0;
    private static final int defaultItemCategory = 0;
    private static final int defaultItemCheckable = 0;
    private static final boolean defaultItemChecked = false;
    private static final boolean defaultItemEnabled = true;
    private static final int defaultItemId = 0;
    private static final int defaultItemOrder = 0;
    private static final boolean defaultItemVisible = true;
    private int groupCategory;
    private int groupCheckable;
    private boolean groupEnabled;
    private int groupId;
    private int groupOrder;
    private boolean groupVisible;
    private ActionProvider itemActionProvider;
    private String itemActionProviderClassName;
    private String itemActionViewClassName;
    private int itemActionViewLayout;
    private boolean itemAdded;
    private char itemAlphabeticShortcut;
    private int itemCategoryOrder;
    private int itemCheckable;
    private boolean itemChecked;
    private boolean itemEnabled;
    private int itemIconResId;
    private int itemId;
    private String itemListenerMethodName;
    private char itemNumericShortcut;
    private int itemShowAsAction;
    private CharSequence itemTitle;
    private CharSequence itemTitleCondensed;
    private boolean itemVisible;
    private Menu menu;
    final SupportMenuInflater this$0;

    private char getShortcut(String s)
    {
        if (s == null)
        {
            return '\0';
        } else
        {
            return s.charAt(0);
        }
    }

    private Object newInstance(String s, Class aclass[], Object aobj[])
    {
        Object obj;
        try
        {
            obj = SupportMenuInflater.access$100(SupportMenuInflater.this).getClassLoader().loadClass(s).getConstructor(aclass).newInstance(aobj);
        }
        catch (Exception exception)
        {
            Log.w("SupportMenuInflater", (new StringBuilder("Cannot instantiate class: ")).append(s).toString(), exception);
            return null;
        }
        return obj;
    }

    private void setItem(MenuItem menuitem)
    {
        boolean flag = true;
        MenuItem menuitem1 = menuitem.setChecked(itemChecked).setVisible(itemVisible).setEnabled(itemEnabled);
        boolean flag1;
        if (itemCheckable > 0)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        menuitem1.setCheckable(flag1).setTitleCondensed(itemTitleCondensed).setIcon(itemIconResId).setAlphabeticShortcut(itemAlphabeticShortcut).setNumericShortcut(itemNumericShortcut);
        if (itemShowAsAction >= 0)
        {
            MenuItemCompat.setShowAsAction(menuitem, itemShowAsAction);
        }
        if (itemListenerMethodName != null)
        {
            if (SupportMenuInflater.access$100(SupportMenuInflater.this).isRestricted())
            {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
            menuitem.setOnMenuItemClickListener(new MenuItemClickListener(SupportMenuInflater.access$400(SupportMenuInflater.this), itemListenerMethodName));
        }
        if (itemCheckable >= 2)
        {
            if (menuitem instanceof MenuItemImpl)
            {
                ((MenuItemImpl)menuitem).setExclusiveCheckable(flag);
            } else
            if (menuitem instanceof MenuItemWrapperICS)
            {
                ((MenuItemWrapperICS)menuitem).setExclusiveCheckable(flag);
            }
        }
        if (itemActionViewClassName != null)
        {
            MenuItemCompat.setActionView(menuitem, (View)newInstance(itemActionViewClassName, SupportMenuInflater.access$500(), SupportMenuInflater.access$600(SupportMenuInflater.this)));
        } else
        {
            flag = false;
        }
        if (itemActionViewLayout > 0)
        {
            if (!flag)
            {
                MenuItemCompat.setActionView(menuitem, itemActionViewLayout);
            } else
            {
                Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            }
        }
        if (itemActionProvider != null)
        {
            MenuItemCompat.setActionProvider(menuitem, itemActionProvider);
        }
    }

    public void addItem()
    {
        itemAdded = true;
        setItem(menu.add(groupId, itemId, itemCategoryOrder, itemTitle));
    }

    public SubMenu addSubMenuItem()
    {
        itemAdded = true;
        SubMenu submenu = menu.addSubMenu(groupId, itemId, itemCategoryOrder, itemTitle);
        setItem(submenu.getItem());
        return submenu;
    }

    public boolean hasAddedItem()
    {
        return itemAdded;
    }

    public void readGroup(AttributeSet attributeset)
    {
        TypedArray typedarray = SupportMenuInflater.access$100(SupportMenuInflater.this).obtainStyledAttributes(attributeset, android.support.v7.appcompat.ate.this._fld0);
        groupId = typedarray.getResourceId(1, 0);
        groupCategory = typedarray.getInt(3, 0);
        groupOrder = typedarray.getInt(4, 0);
        groupCheckable = typedarray.getInt(5, 0);
        groupVisible = typedarray.getBoolean(2, true);
        groupEnabled = typedarray.getBoolean(0, true);
        typedarray.recycle();
    }

    public void readItem(AttributeSet attributeset)
    {
        int i = 1;
        TypedArray typedarray = SupportMenuInflater.access$100(SupportMenuInflater.this).obtainStyledAttributes(attributeset, android.support.v7.appcompat.ate.this._fld0);
        itemId = typedarray.getResourceId(2, 0);
        int j = typedarray.getInt(5, groupCategory);
        int k = typedarray.getInt(6, groupOrder);
        itemCategoryOrder = j & 0xffff0000 | k & 0xffff;
        itemTitle = typedarray.getText(7);
        itemTitleCondensed = typedarray.getText(8);
        itemIconResId = typedarray.getResourceId(0, 0);
        itemAlphabeticShortcut = getShortcut(typedarray.getString(9));
        itemNumericShortcut = getShortcut(typedarray.getString(10));
        if (typedarray.hasValue(11))
        {
            int l;
            if (typedarray.getBoolean(11, false))
            {
                l = i;
            } else
            {
                l = 0;
            }
            itemCheckable = l;
        } else
        {
            itemCheckable = groupCheckable;
        }
        itemChecked = typedarray.getBoolean(3, false);
        itemVisible = typedarray.getBoolean(4, groupVisible);
        itemEnabled = typedarray.getBoolean(i, groupEnabled);
        itemShowAsAction = typedarray.getInt(13, -1);
        itemListenerMethodName = typedarray.getString(12);
        itemActionViewLayout = typedarray.getResourceId(14, 0);
        itemActionViewClassName = typedarray.getString(15);
        itemActionProviderClassName = typedarray.getString(16);
        if (itemActionProviderClassName == null)
        {
            i = 0;
        }
        if (i != 0 && itemActionViewLayout == 0 && itemActionViewClassName == null)
        {
            itemActionProvider = (ActionProvider)newInstance(itemActionProviderClassName, SupportMenuInflater.access$200(), SupportMenuInflater.access$300(SupportMenuInflater.this));
        } else
        {
            if (i != 0)
            {
                Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
            }
            itemActionProvider = null;
        }
        typedarray.recycle();
        itemAdded = false;
    }

    public void resetGroup()
    {
        groupId = 0;
        groupCategory = 0;
        groupOrder = 0;
        groupCheckable = 0;
        groupVisible = true;
        groupEnabled = true;
    }


    public MenuItemClickListener(Menu menu1)
    {
        this$0 = SupportMenuInflater.this;
        super();
        menu = menu1;
        resetGroup();
    }
}
