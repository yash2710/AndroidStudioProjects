// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.wallet:
//            m

public final class NotifyTransactionStatusRequest
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new m();
    String aiQ;
    String ajR;
    int status;
    final int xM;

    NotifyTransactionStatusRequest()
    {
        xM = 1;
    }

    NotifyTransactionStatusRequest(int i, String s, int j, String s1)
    {
        xM = i;
        aiQ = s;
        status = j;
        ajR = s1;
    }

    public static Builder newBuilder()
    {
        NotifyTransactionStatusRequest notifytransactionstatusrequest = new NotifyTransactionStatusRequest();
        notifytransactionstatusrequest.getClass();
        return notifytransactionstatusrequest. new Builder(null);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final String getDetailedReason()
    {
        return ajR;
    }

    public final String getGoogleTransactionId()
    {
        return aiQ;
    }

    public final int getStatus()
    {
        return status;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        m.a(this, parcel, i);
    }


    private class Builder
    {

        final NotifyTransactionStatusRequest ajS;

        public final NotifyTransactionStatusRequest build()
        {
            boolean flag = true;
            boolean flag1;
            if (!TextUtils.isEmpty(ajS.aiQ))
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            hm.b(flag1, "googleTransactionId is required");
            if (ajS.status <= 0 || ajS.status > 8)
            {
                flag = false;
            }
            hm.b(flag, "status is an unrecognized value");
            return ajS;
        }

        public final Builder setDetailedReason(String s)
        {
            ajS.ajR = s;
            return this;
        }

        public final Builder setGoogleTransactionId(String s)
        {
            ajS.aiQ = s;
            return this;
        }

        public final Builder setStatus(int i)
        {
            ajS.status = i;
            return this;
        }

        private Builder()
        {
            ajS = NotifyTransactionStatusRequest.this;
            super();
        }

        Builder(_cls1 _pcls1)
        {
            this();
        }
    }

}
