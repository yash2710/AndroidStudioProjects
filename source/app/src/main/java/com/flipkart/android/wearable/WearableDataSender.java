// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.flipkart.android.wearable.shared.WearableWishListItem;
import com.flipkart.logging.FkLogger;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.flipkart.android.wearable:
//            d, e, b

public class WearableDataSender
{

    private static final String a = com/flipkart/android/wearable/WearableDataSender.getName();
    private GoogleApiClient b;

    public WearableDataSender(GoogleApiClient googleapiclient)
    {
        b = googleapiclient;
    }

    static GoogleApiClient a(WearableDataSender wearabledatasender)
    {
        return wearabledatasender.b;
    }

    private static Asset a(Bitmap bitmap)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        Asset asset;
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bytearrayoutputstream);
        asset = Asset.createFromBytes(bytearrayoutputstream.toByteArray());
        Exception exception;
        if (bytearrayoutputstream != null)
        {
            try
            {
                bytearrayoutputstream.close();
            }
            catch (IOException ioexception1)
            {
                return asset;
            }
        }
        return asset;
        exception;
        bytearrayoutputstream = null;
_L2:
        if (bytearrayoutputstream != null)
        {
            try
            {
                bytearrayoutputstream.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
        exception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static String a()
    {
        return a;
    }

    static ArrayList a(WearableDataSender wearabledatasender, List list)
    {
        ArrayList arraylist = new ArrayList();
        for (Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(WearableWishListItem.createDataMap((WearableWishListItem)iterator.next()))) { }
        return arraylist;
    }

    static void a(WearableDataSender wearabledatasender, String s, Bitmap bitmap)
    {
        if (!wearabledatasender.b.isConnected() && !wearabledatasender.b.blockingConnect(30L, TimeUnit.SECONDS).isSuccess())
        {
            FkLogger.error(a, "DataLayerListenerService failed to connect to GoogleApiClient.");
            return;
        }
        Asset asset = a(bitmap);
        if (asset != null)
        {
            PutDataMapRequest putdatamaprequest = PutDataMapRequest.create((new StringBuilder("/flipkart_wearable_wish_list_image/")).append(String.valueOf(s.hashCode())).toString());
            putdatamaprequest.getDataMap().putAsset(s, asset);
            putdatamaprequest.getDataMap().putString("timestamp", String.valueOf(System.currentTimeMillis()));
            com.google.android.gms.wearable.PutDataRequest putdatarequest = putdatamaprequest.asPutDataRequest();
            Wearable.DataApi.putDataItem(wearabledatasender.b, putdatarequest).setResultCallback(new d(wearabledatasender));
            return;
        } else
        {
            FkLogger.error(a, (new StringBuilder("Asset is null for url:")).append(s).toString());
            return;
        }
    }

    public void updateImage(String s)
    {
        e e1 = new e(this, s);
        if (Looper.myLooper() != Looper.getMainLooper())
        {
            (new Handler(Looper.getMainLooper())).post(e1);
            return;
        } else
        {
            e1.run();
            return;
        }
    }

    public void updateWishList(List list)
    {
        b b1 = new b(this, list);
        if (Looper.getMainLooper() == Looper.myLooper())
        {
            (new Thread(b1)).start();
            return;
        } else
        {
            b1.run();
            return;
        }
    }

}
