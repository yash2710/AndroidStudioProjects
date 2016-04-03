// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable.shared;

import com.google.android.gms.wearable.DataMap;
import java.io.Serializable;

public class WearableWishListItem
    implements Serializable
{

    private String a;
    private int b;
    private String c;
    private String d;
    private double e;
    private long f;
    private boolean g;
    private int h;

    public WearableWishListItem()
    {
    }

    public static WearableWishListItem convertFromDataMap(DataMap datamap)
    {
        if (datamap != null)
        {
            WearableWishListItem wearablewishlistitem = new WearableWishListItem();
            if (datamap.containsKey("wish_list_item_price"))
            {
                wearablewishlistitem.setPrice(datamap.getInt("wish_list_item_price"));
            }
            if (datamap.containsKey("wish_list_item_title"))
            {
                wearablewishlistitem.setProductTitle(datamap.getString("wish_list_item_title"));
            }
            if (datamap.containsKey("wish_list_item_image_url"))
            {
                wearablewishlistitem.setImageUrl(datamap.getString("wish_list_item_image_url"));
            }
            if (datamap.containsKey("wish_list_item_id"))
            {
                wearablewishlistitem.setId(datamap.getString("wish_list_item_id"));
            }
            if (datamap.containsKey("wish_list_item_overall_rating"))
            {
                wearablewishlistitem.setOverallRating(datamap.getDouble("wish_list_item_overall_rating"));
            }
            if (datamap.containsKey("wish_list_item_total_rating"))
            {
                wearablewishlistitem.setTotalRatingCount(datamap.getLong("wish_list_item_total_rating"));
            }
            if (datamap.containsKey("wish_list_item_show_mrp"))
            {
                wearablewishlistitem.setShowMrp(datamap.getBoolean("wish_list_item_show_mrp"));
            }
            if (datamap.containsKey("wish_list_item_mrp"))
            {
                wearablewishlistitem.setMrp(datamap.getInt("wish_list_item_mrp"));
            }
            return wearablewishlistitem;
        } else
        {
            return null;
        }
    }

    public static DataMap createDataMap(WearableWishListItem wearablewishlistitem)
    {
        DataMap datamap = new DataMap();
        datamap.putString("wish_list_item_title", wearablewishlistitem.getProductTitle());
        datamap.putInt("wish_list_item_price", wearablewishlistitem.getPrice());
        datamap.putString("wish_list_item_image_url", wearablewishlistitem.getImageUrl());
        datamap.putString("wish_list_item_id", wearablewishlistitem.getId());
        datamap.putDouble("wish_list_item_overall_rating", wearablewishlistitem.getOverallRating());
        datamap.putLong("wish_list_item_total_rating", wearablewishlistitem.getTotalRatingCount());
        datamap.putBoolean("wish_list_item_show_mrp", wearablewishlistitem.isShowMrp());
        datamap.putInt("wish_list_item_mrp", wearablewishlistitem.getMrp());
        return datamap;
    }

    public String getId()
    {
        return d;
    }

    public String getImageUrl()
    {
        return c;
    }

    public int getMrp()
    {
        return h;
    }

    public double getOverallRating()
    {
        return e;
    }

    public int getPrice()
    {
        return b;
    }

    public String getProductTitle()
    {
        return a;
    }

    public long getTotalRatingCount()
    {
        return f;
    }

    public boolean isShowMrp()
    {
        return g;
    }

    public void setId(String s)
    {
        d = s;
    }

    public void setImageUrl(String s)
    {
        c = s;
    }

    public void setMrp(int i)
    {
        h = i;
    }

    public void setOverallRating(double d1)
    {
        e = d1;
    }

    public void setPrice(int i)
    {
        b = i;
    }

    public void setProductTitle(String s)
    {
        a = s;
    }

    public void setShowMrp(boolean flag)
    {
        g = flag;
    }

    public void setTotalRatingCount(long l)
    {
        f = l;
    }
}
