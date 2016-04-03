// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable;

import android.content.Intent;
import android.os.Bundle;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.wearable.shared.SharedAction;
import com.flipkart.logging.FkLogger;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;
import java.io.UnsupportedEncodingException;

// Referenced classes of package com.flipkart.android.wearable:
//            h, WearableActionHandler, WearableDataSender

public class WearableService extends WearableListenerService
    implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
{

    public static final String EXTRAS_NOTIFICATION_ID = "extras_notification_id";
    public static final String NOTIFICATION_SCREEN_TYPE = "notification";
    private static final String a = com/flipkart/android/wearable/WearableService.getName();
    private GoogleApiClient b;

    public WearableService()
    {
    }

    public void onConnected(Bundle bundle)
    {
        FkLogger.verbose(a, "GoogleAPIClient Connected");
    }

    public void onConnectionFailed(ConnectionResult connectionresult)
    {
        FkLogger.verbose(a, "GoogleAPIClient Connection Failed");
    }

    public void onConnectionSuspended(int i)
    {
        FkLogger.verbose(a, "GoogleAPIClient Suspended");
    }

    public void onCreate()
    {
        super.onCreate();
        b = (new com.google.android.gms.common.api.GoogleApiClient.Builder(this)).addApi(Wearable.API).addOnConnectionFailedListener(this).addConnectionCallbacks(this).build();
    }

    public void onMessageReceived(MessageEvent messageevent)
    {
        super.onMessageReceived(messageevent);
        FkLogger.verbose(a, "onMessageReceived");
        SharedAction sharedaction = SharedAction.getAction(messageevent.getPath());
        switch (h.a[sharedaction.ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            FkLogger.verbose(a, "onMessageReceived Refresh Wish List");
            (new WearableActionHandler(b)).refreshWishList();
            return;

        case 2: // '\002'
            try
            {
                String s1 = new String(messageevent.getData(), "UTF-8");
                FkLogger.verbose(a, (new StringBuilder("onMessageReceived Update Image for ")).append(s1).toString());
                (new WearableDataSender(b)).updateImage(s1);
                return;
            }
            catch (UnsupportedEncodingException unsupportedencodingexception1)
            {
                return;
            }

        case 3: // '\003'
            break;
        }
        Intent intent;
        String s = new String(messageevent.getData(), "UTF-8");
        FkLogger.verbose(a, (new StringBuilder("onMessageReceived Open On Phone for ")).append(s).toString());
        intent = new Intent(this, com/flipkart/android/activity/HomeFragmentHolderActivity);
        intent.putExtra("HOME_ACTIVITY_EXTRAS_FROM_SCREEN", "notification");
        intent.putExtra("actionJson", (new StringBuilder("{\"screenType\":\"productPage\",\"omnitureData\":\"pp_moto_watch\",\"params\":{\"pid\":\"")).append(s).append("\",\"pids\":[\"").append(s).append("\"]}}").toString());
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() <= 10)
        {
            break MISSING_BLOCK_LABEL_272;
        }
        intent.setFlags(0x30008000);
_L1:
        startActivity(intent);
        return;
        try
        {
            intent.setFlags(0x20000000);
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return;
        }
          goto _L1
    }

    public void onPeerConnected(Node node)
    {
        super.onPeerConnected(node);
        FkLogger.verbose(a, (new StringBuilder("onPeerConnected, PeerName:")).append(node.getDisplayName()).toString());
    }

    public void onPeerDisconnected(Node node)
    {
        super.onPeerDisconnected(node);
        FkLogger.verbose(a, (new StringBuilder("onPeerDisconnected, PeerName:")).append(node.getDisplayName()).toString());
    }

}
