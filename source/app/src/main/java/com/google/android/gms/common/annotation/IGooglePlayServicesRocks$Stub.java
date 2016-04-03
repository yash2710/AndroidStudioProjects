// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.annotation;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

// Referenced classes of package com.google.android.gms.common.annotation:
//            IGooglePlayServicesRocks

public abstract class attachInterface extends Binder
    implements IGooglePlayServicesRocks
{

    public static IGooglePlayServicesRocks asInterface(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.common.annotation.IGooglePlayServicesRocks");
        class a
            implements IGooglePlayServicesRocks
        {

            private IBinder kq;

            public IBinder asBinder()
            {
                return kq;
            }

            a(IBinder ibinder)
            {
                kq = ibinder;
            }
        }

        if (iinterface != null && (iinterface instanceof IGooglePlayServicesRocks))
        {
            return (IGooglePlayServicesRocks)iinterface;
        } else
        {
            return new a(ibinder);
        }
    }

    public IBinder asBinder()
    {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
    {
        switch (i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1598968902: 
            parcel1.writeString("com.google.android.gms.common.annotation.IGooglePlayServicesRocks");
            break;
        }
        return true;
    }

    public a()
    {
        attachInterface(this, "com.google.android.gms.common.annotation.IGooglePlayServicesRocks");
    }
}
