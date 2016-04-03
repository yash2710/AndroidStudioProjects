// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.facebook.FacebookException;
import com.facebook.NativeAppCallAttachmentStore;
import com.facebook.internal.NativeProtocol;
import java.util.EnumSet;
import java.util.Iterator;

public class FacebookDialog
{

    public static final String COMPLETION_GESTURE_CANCEL = "cancel";
    private static final String EXTRA_DIALOG_COMPLETE_KEY = "com.facebook.platform.extra.DID_COMPLETE";
    private static final String EXTRA_DIALOG_COMPLETION_GESTURE_KEY = "com.facebook.platform.extra.COMPLETION_GESTURE";
    private static final String EXTRA_DIALOG_COMPLETION_ID_KEY = "com.facebook.platform.extra.POST_ID";
    private static final int MIN_NATIVE_SHARE_PROTOCOL_VERSION = 0x1332b3a;
    private static NativeAppCallAttachmentStore attachmentStore;
    private Activity activity;
    private PendingCall appCall;
    private Fragment fragment;
    private OnPresentCallback onPresentCallback;

    private FacebookDialog(Activity activity1, Fragment fragment1, PendingCall pendingcall, OnPresentCallback onpresentcallback)
    {
        activity = activity1;
        fragment = fragment1;
        appCall = pendingcall;
        onPresentCallback = onpresentcallback;
    }

    FacebookDialog(Activity activity1, Fragment fragment1, PendingCall pendingcall, OnPresentCallback onpresentcallback, _cls1 _pcls1)
    {
        this(activity1, fragment1, pendingcall, onpresentcallback);
    }

    public static transient boolean canPresentOpenGraphActionDialog(Context context, OpenGraphActionDialogFeature aopengraphactiondialogfeature[])
    {
        return handleCanPresent(context, EnumSet.of(OpenGraphActionDialogFeature.OG_ACTION_DIALOG, aopengraphactiondialogfeature));
    }

    public static transient boolean canPresentShareDialog(Context context, ShareDialogFeature asharedialogfeature[])
    {
        return handleCanPresent(context, EnumSet.of(ShareDialogFeature.SHARE_DIALOG, asharedialogfeature));
    }

    private static NativeAppCallAttachmentStore getAttachmentStore()
    {
        if (attachmentStore == null)
        {
            attachmentStore = new NativeAppCallAttachmentStore();
        }
        return attachmentStore;
    }

    private static int getMinVersionForFeatures(Iterable iterable)
    {
        Iterator iterator = iterable.iterator();
        int i;
        for (i = 0x80000000; iterator.hasNext(); i = Math.max(i, ((DialogFeature)iterator.next()).getMinVersion())) { }
        return i;
    }

    public static String getNativeDialogCompletionGesture(Bundle bundle)
    {
        return bundle.getString("com.facebook.platform.extra.COMPLETION_GESTURE");
    }

    public static boolean getNativeDialogDidComplete(Bundle bundle)
    {
        return bundle.getBoolean("com.facebook.platform.extra.DID_COMPLETE", false);
    }

    public static String getNativeDialogPostId(Bundle bundle)
    {
        return bundle.getString("com.facebook.platform.extra.POST_ID");
    }

    private static int getProtocolVersionForNativeDialog(Context context, Integer integer)
    {
        return NativeProtocol.getLatestAvailableProtocolVersion(context, integer.intValue());
    }

    public static boolean handleActivityResult(Context context, PendingCall pendingcall, int i, Intent intent, Callback callback)
    {
        if (i != pendingcall.getRequestCode())
        {
            return false;
        }
        if (attachmentStore != null)
        {
            attachmentStore.cleanupAttachmentsForCall(context, pendingcall.getCallId());
        }
        if (callback != null)
        {
            if (NativeProtocol.isErrorResult(intent))
            {
                callback.onError(pendingcall, NativeProtocol.getErrorFromResult(intent), intent.getExtras());
            } else
            {
                callback.onComplete(pendingcall, intent.getExtras());
            }
        }
        return true;
    }

    private static boolean handleCanPresent(Context context, Iterable iterable)
    {
        return getProtocolVersionForNativeDialog(context, Integer.valueOf(getMinVersionForFeatures(iterable))) != -1;
    }

    public PendingCall present()
    {
        if (onPresentCallback != null)
        {
            try
            {
                onPresentCallback.onPresent(activity);
            }
            catch (Exception exception)
            {
                throw new FacebookException(exception);
            }
        }
        if (fragment != null)
        {
            fragment.startActivityForResult(appCall.getRequestIntent(), appCall.getRequestCode());
        } else
        {
            activity.startActivityForResult(appCall.getRequestIntent(), appCall.getRequestCode());
        }
        return appCall;
    }



    private class OpenGraphActionDialogFeature extends Enum
        implements DialogFeature
    {

        private static final OpenGraphActionDialogFeature $VALUES[];
        public static final OpenGraphActionDialogFeature OG_ACTION_DIALOG;
        private int minVersion;

        public static OpenGraphActionDialogFeature valueOf(String s)
        {
            return (OpenGraphActionDialogFeature)Enum.valueOf(com/facebook/widget/FacebookDialog$OpenGraphActionDialogFeature, s);
        }

        public static OpenGraphActionDialogFeature[] values()
        {
            return (OpenGraphActionDialogFeature[])$VALUES.clone();
        }

        public final int getMinVersion()
        {
            return minVersion;
        }

        static 
        {
            OG_ACTION_DIALOG = new OpenGraphActionDialogFeature("OG_ACTION_DIALOG", 0, 0x1332b3a);
            OpenGraphActionDialogFeature aopengraphactiondialogfeature[] = new OpenGraphActionDialogFeature[1];
            aopengraphactiondialogfeature[0] = OG_ACTION_DIALOG;
            $VALUES = aopengraphactiondialogfeature;
        }

        private OpenGraphActionDialogFeature(String s, int i, int j)
        {
            super(s, i);
            minVersion = j;
        }
    }


    private class ShareDialogFeature extends Enum
        implements DialogFeature
    {

        private static final ShareDialogFeature $VALUES[];
        public static final ShareDialogFeature SHARE_DIALOG;
        private int minVersion;

        public static ShareDialogFeature valueOf(String s)
        {
            return (ShareDialogFeature)Enum.valueOf(com/facebook/widget/FacebookDialog$ShareDialogFeature, s);
        }

        public static ShareDialogFeature[] values()
        {
            return (ShareDialogFeature[])$VALUES.clone();
        }

        public final int getMinVersion()
        {
            return minVersion;
        }

        static 
        {
            SHARE_DIALOG = new ShareDialogFeature("SHARE_DIALOG", 0, 0x1332b3a);
            ShareDialogFeature asharedialogfeature[] = new ShareDialogFeature[1];
            asharedialogfeature[0] = SHARE_DIALOG;
            $VALUES = asharedialogfeature;
        }

        private ShareDialogFeature(String s, int i, int j)
        {
            super(s, i);
            minVersion = j;
        }
    }


    private class DialogFeature
    {

        public abstract int getMinVersion();
    }


    private class PendingCall
        implements Parcelable
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        private UUID callId;
        private int requestCode;
        private Intent requestIntent;

        private void setRequestCode(int i)
        {
            requestCode = i;
        }

        private void setRequestIntent(Intent intent)
        {
            requestIntent = intent;
            requestIntent.putExtra("com.facebook.platform.protocol.CALL_ID", callId.toString());
        }

        public int describeContents()
        {
            return 0;
        }

        public UUID getCallId()
        {
            return callId;
        }

        public int getRequestCode()
        {
            return requestCode;
        }

        public Intent getRequestIntent()
        {
            return requestIntent;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(callId.toString());
            parcel.writeParcelable(requestIntent, 0);
            parcel.writeInt(requestCode);
        }




        public PendingCall(int i)
        {
            callId = UUID.randomUUID();
            requestCode = i;
        }

        private PendingCall(Parcel parcel)
        {
            callId = UUID.fromString(parcel.readString());
            requestIntent = (Intent)parcel.readParcelable(null);
            requestCode = parcel.readInt();
        }

        PendingCall(Parcel parcel, _cls1 _pcls1)
        {
            this(parcel);
        }

        class _cls1
            implements android.os.Parcelable.Creator
        {

            public final PendingCall createFromParcel(Parcel parcel)
            {
                return new PendingCall(parcel, null);
            }

            public final volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public final PendingCall[] newArray(int i)
            {
                return new PendingCall[i];
            }

            public final volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

                _cls1()
                {
                }
        }

    }


    private class Callback
    {

        public abstract void onComplete(PendingCall pendingcall, Bundle bundle);

        public abstract void onError(PendingCall pendingcall, Exception exception, Bundle bundle);
    }


    private class OnPresentCallback
    {

        public abstract void onPresent(Context context);
    }

}
