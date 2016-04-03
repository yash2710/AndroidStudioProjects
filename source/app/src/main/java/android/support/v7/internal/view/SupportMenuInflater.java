// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SupportMenuInflater extends MenuInflater
{

    private static final Class ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE[];
    private static final Class ACTION_VIEW_CONSTRUCTOR_SIGNATURE[];
    private static final String LOG_TAG = "SupportMenuInflater";
    private static final int NO_ID = 0;
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    private final Object mActionProviderConstructorArguments[];
    private final Object mActionViewConstructorArguments[];
    private Context mContext;
    private Object mRealOwner;

    public SupportMenuInflater(Context context)
    {
        super(context);
        mContext = context;
        mRealOwner = context;
        mActionViewConstructorArguments = (new Object[] {
            context
        });
        mActionProviderConstructorArguments = mActionViewConstructorArguments;
    }

    private void parseMenu(XmlPullParser xmlpullparser, AttributeSet attributeset, Menu menu)
    {
        MenuState menustate;
        int i;
        menustate = new MenuState(menu);
        i = xmlpullparser.getEventType();
_L13:
        if (i != 2) goto _L2; else goto _L1
_L1:
        String s3 = xmlpullparser.getName();
        if (!s3.equals("menu")) goto _L4; else goto _L3
_L3:
        i = xmlpullparser.next();
_L12:
        String s;
        boolean flag;
        int j;
        boolean flag1;
        s = null;
        flag = false;
        j = i;
        flag1 = false;
_L11:
        if (flag1) goto _L6; else goto _L5
_L5:
        j;
        JVM INSTR tableswitch 1 3: default 96
    //                   1 398
    //                   2 165
    //                   3 262;
           goto _L7 _L8 _L9 _L10
_L7:
        boolean flag2 = flag;
_L15:
        int k = xmlpullparser.next();
        boolean flag3 = flag2;
        j = k;
        flag = flag3;
          goto _L11
_L4:
        throw new RuntimeException((new StringBuilder("Expecting menu, got ")).append(s3).toString());
_L2:
        i = xmlpullparser.next();
        if (i != 1) goto _L13; else goto _L12
_L9:
        if (flag) goto _L7; else goto _L14
_L14:
        String s2 = xmlpullparser.getName();
        if (s2.equals("group"))
        {
            menustate.readGroup(attributeset);
            flag2 = flag;
        } else
        if (s2.equals("item"))
        {
            menustate.readItem(attributeset);
            flag2 = flag;
        } else
        if (s2.equals("menu"))
        {
            parseMenu(xmlpullparser, attributeset, ((Menu) (menustate.addSubMenuItem())));
            flag2 = flag;
        } else
        {
            s = s2;
            flag2 = true;
        }
          goto _L15
_L10:
        String s1 = xmlpullparser.getName();
        if (!flag || !s1.equals(s)) goto _L17; else goto _L16
_L16:
        s = null;
        flag2 = false;
          goto _L15
_L17:
        if (!s1.equals("group")) goto _L19; else goto _L18
_L18:
        menustate.resetGroup();
        flag2 = flag;
          goto _L15
_L19:
        if (!s1.equals("item"))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (menustate.hasAddedItem()) goto _L7; else goto _L20
_L20:
        if (menustate.itemActionProvider != null && menustate.itemActionProvider.hasSubMenu())
        {
            menustate.addSubMenuItem();
            flag2 = flag;
        } else
        {
            menustate.addItem();
            flag2 = flag;
        }
          goto _L15
        if (!s1.equals("menu")) goto _L7; else goto _L21
_L21:
        flag1 = true;
        flag2 = flag;
          goto _L15
_L8:
        throw new RuntimeException("Unexpected end of document");
_L6:
          goto _L11
    }

    public void inflate(int i, Menu menu)
    {
        if (menu instanceof SupportMenu) goto _L2; else goto _L1
_L1:
        super.inflate(i, menu);
_L4:
        return;
_L2:
        XmlResourceParser xmlresourceparser = null;
        xmlresourceparser = mContext.getResources().getLayout(i);
        parseMenu(xmlresourceparser, Xml.asAttributeSet(xmlresourceparser), menu);
        if (xmlresourceparser != null)
        {
            xmlresourceparser.close();
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        throw new InflateException("Error inflating menu XML", xmlpullparserexception);
        Exception exception;
        exception;
        if (xmlresourceparser != null)
        {
            xmlresourceparser.close();
        }
        throw exception;
        IOException ioexception;
        ioexception;
        throw new InflateException("Error inflating menu XML", ioexception);
    }

    static 
    {
        Class aclass[] = {
            android/content/Context
        };
        ACTION_VIEW_CONSTRUCTOR_SIGNATURE = aclass;
        ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = aclass;
    }







    private class MenuState
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
                obj = mContext.getClassLoader().loadClass(s).getConstructor(aclass).newInstance(aobj);
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
                if (mContext.isRestricted())
                {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuitem.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(mRealOwner, itemListenerMethodName));
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
                MenuItemCompat.setActionView(menuitem, (View)newInstance(itemActionViewClassName, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, mActionViewConstructorArguments));
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
            TypedArray typedarray = mContext.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.MenuGroup);
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
            TypedArray typedarray = mContext.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.MenuItem);
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
                itemActionProvider = (ActionProvider)newInstance(itemActionProviderClassName, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, mActionProviderConstructorArguments);
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


        public MenuState(Menu menu1)
        {
            this$0 = SupportMenuInflater.this;
            super();
            menu = menu1;
            resetGroup();
        }

        private class InflatedOnMenuItemClickListener
            implements android.view.MenuItem.OnMenuItemClickListener
        {

            private static final Class PARAM_TYPES[] = {
                android/view/MenuItem
            };
            private Method mMethod;
            private Object mRealOwner;

            public boolean onMenuItemClick(MenuItem menuitem)
            {
                try
                {
                    if (mMethod.getReturnType() == Boolean.TYPE)
                    {
                        return ((Boolean)mMethod.invoke(mRealOwner, new Object[] {
                            menuitem
                        })).booleanValue();
                    }
                    mMethod.invoke(mRealOwner, new Object[] {
                        menuitem
                    });
                }
                catch (Exception exception)
                {
                    throw new RuntimeException(exception);
                }
                return true;
            }


            public InflatedOnMenuItemClickListener(Object obj, String s)
            {
                mRealOwner = obj;
                Class class1 = obj.getClass();
                try
                {
                    mMethod = class1.getMethod(s, PARAM_TYPES);
                    return;
                }
                catch (Exception exception)
                {
                    InflateException inflateexception = new InflateException((new StringBuilder("Couldn't resolve menu item onClick handler ")).append(s).append(" in class ").append(class1.getName()).toString());
                    inflateexception.initCause(exception);
                    throw inflateexception;
                }
            }
        }

    }

}
