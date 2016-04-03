// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.cart;

import com.flipkart.android.response.cart.CartItem;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart
{

    private List a;

    public Cart()
    {
        a = new ArrayList();
    }

    public static Cart fromStringValue(String s)
    {
        Cart cart = new Cart();
        if (StringUtils.isNullOrEmpty(s))
        {
            return cart;
        }
        ArrayList arraylist = new ArrayList();
        String as[] = s.split("&&");
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String as1[] = as[j].split("==");
            if (as1.length == 2)
            {
                arraylist.add(new CartItem(as1[0], as1[1]));
            }
        }

        cart.setItems(arraylist);
        return cart;
    }

    public static String toStringValue(Cart cart)
    {
        if (cart.getItems().size() == 0)
        {
            return "";
        }
        Iterator iterator = cart.getItems().iterator();
        String s;
        CartItem cartitem;
        for (s = ""; iterator.hasNext(); s = (new StringBuilder()).append(s).append(cartitem.getPid()).append("==").append(cartitem.getListId()).append("&&").toString())
        {
            cartitem = (CartItem)iterator.next();
        }

        return s.substring(0, -2 + s.length());
    }

    public int getCartItemCount()
    {
        if (a != null)
        {
            return a.size();
        } else
        {
            return 0;
        }
    }

    public List getItems()
    {
        return a;
    }

    public ArrayList getListingIds()
    {
        ArrayList arraylist = new ArrayList();
        for (Iterator iterator = a.iterator(); iterator.hasNext(); arraylist.add(((CartItem)iterator.next()).getListId())) { }
        return arraylist;
    }

    public ArrayList getProductsIds()
    {
        ArrayList arraylist = new ArrayList();
        for (Iterator iterator = a.iterator(); iterator.hasNext(); arraylist.add(((CartItem)iterator.next()).getPid())) { }
        return arraylist;
    }

    public void setItems(ArrayList arraylist)
    {
        a = arraylist;
    }
}
