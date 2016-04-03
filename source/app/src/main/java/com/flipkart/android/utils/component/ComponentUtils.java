// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.component;

import com.flipkart.android.datahandler.param.ComponentDataParams;
import com.flipkart.android.response.component.layout.LayoutData;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.response.component.layout.LayoutInfo;
import java.util.ArrayList;
import java.util.Iterator;

public class ComponentUtils
{

    public ComponentUtils()
    {
    }

    public static boolean checkIfFullScreenIsTrue(ArrayList arraylist)
    {
        int i = arraylist.size();
        for (int j = 0; j < i; j++)
        {
            if (((LayoutInfo)arraylist.get(j)).getWidgetType().equals("TAB"))
            {
                return true;
            }
            if (((LayoutInfo)arraylist.get(j)).getWidgetType().equals("OMU") && ((LayoutInfo)arraylist.get(j)).getLayoutDetails() != null && ((LayoutInfo)arraylist.get(j)).getLayoutDetails().getOrientation().equalsIgnoreCase("vertical"))
            {
                return true;
            }
        }

        return false;
    }

    public static ArrayList fetchDataIdsFromLayout(ArrayList arraylist, String s)
    {
        ArrayList arraylist1 = null;
        if (arraylist != null)
        {
            ArrayList arraylist2 = new ArrayList();
            Iterator iterator = arraylist.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                LayoutInfo layoutinfo = (LayoutInfo)iterator.next();
                if (layoutinfo.getData() != null && !layoutinfo.getData().getUpdatedBy().equals("client"))
                {
                    arraylist2.add(new ComponentDataParams(s, layoutinfo.getData().getDataKey(), layoutinfo.getData().getDataTtl()));
                    if (layoutinfo.getChildren() != null && layoutinfo.getChildren().size() != 0)
                    {
                        arraylist2.addAll(fetchDataIdsFromLayout(layoutinfo.getChildren(), s));
                    }
                }
            } while (true);
            arraylist1 = arraylist2;
        }
        return arraylist1;
    }

    public static boolean isSearchWidgetPresent(ArrayList arraylist)
    {
        int i = arraylist.size();
        for (int j = 0; j < i; j++)
        {
            if (((LayoutInfo)arraylist.get(j)).getWidgetType().equals("SEARCH"))
            {
                return true;
            }
        }

        return false;
    }
}
