// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import com.flipkart.android.fragments.MultiWidgetFragment;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            CustomTabWidget

class pageTitleList extends FragmentStatePagerAdapter
{

    ArrayList pageTitleList;
    final CustomTabWidget this$0;

    public void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        super.destroyItem(viewgroup, i, obj);
        if (aliveFragmentArray != null)
        {
            aliveFragmentArray[i] = null;
        }
    }

    public int getCount()
    {
        return pageTitleList.size();
    }

    public Fragment getItem(int i)
    {
        MultiWidgetFragment multiwidgetfragment = new MultiWidgetFragment();
        multiwidgetfragment.setArguments((Bundle)bundleArrayList.get(i));
        if (aliveFragmentArray != null)
        {
            aliveFragmentArray[i] = multiwidgetfragment;
        }
        return multiwidgetfragment;
    }

    public CharSequence getPageTitle(int i)
    {
        return (CharSequence)pageTitleList.get(i);
    }

    public (FragmentManager fragmentmanager, ArrayList arraylist)
    {
        this$0 = CustomTabWidget.this;
        super(fragmentmanager);
        pageTitleList = arraylist;
    }
}
