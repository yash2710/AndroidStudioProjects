// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.identity.intents;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

// Referenced classes of package com.google.android.gms.identity.intents:
//            a

public final class UserAddressRequest
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new a();
    List UE;
    private final int xM;

    UserAddressRequest()
    {
        xM = 1;
    }

    UserAddressRequest(int i, List list)
    {
        xM = i;
        UE = list;
    }

    public static Builder newBuilder()
    {
        UserAddressRequest useraddressrequest = new UserAddressRequest();
        useraddressrequest.getClass();
        return useraddressrequest. new Builder(null);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        a.a(this, parcel, i);
    }


    private class Builder
    {

        final UserAddressRequest UF;

        public final Builder addAllowedCountrySpecification(CountrySpecification countryspecification)
        {
            if (UF.UE == null)
            {
                UF.UE = new ArrayList();
            }
            UF.UE.add(countryspecification);
            return this;
        }

        public final Builder addAllowedCountrySpecifications(Collection collection)
        {
            if (UF.UE == null)
            {
                UF.UE = new ArrayList();
            }
            UF.UE.addAll(collection);
            return this;
        }

        public final UserAddressRequest build()
        {
            if (UF.UE != null)
            {
                UF.UE = Collections.unmodifiableList(UF.UE);
            }
            return UF;
        }

        private Builder()
        {
            UF = UserAddressRequest.this;
            super();
        }

        Builder(_cls1 _pcls1)
        {
            this();
        }
    }

}
