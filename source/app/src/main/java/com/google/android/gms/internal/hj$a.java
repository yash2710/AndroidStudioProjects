// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.d;

// Referenced classes of package com.google.android.gms.internal:
//            hj

public abstract class a extends Binder
    implements hj
{

    public static hj M(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        class a
            implements hj
        {

            private IBinder kq;

            public d a(d d1, int i, int j)
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.ISignInButtonCreator");
                if (d1 == null)
                {
                    break MISSING_BLOCK_LABEL_92;
                }
                IBinder ibinder1 = d1.asBinder();
_L1:
                d d2;
                parcel.writeStrongBinder(ibinder1);
                parcel.writeInt(i);
                parcel.writeInt(j);
                kq.transact(1, parcel, parcel1, 0);
                parcel1.readException();
                d2 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
                parcel1.recycle();
                parcel.recycle();
                return d2;
                ibinder1 = null;
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public IBinder asBinder()
            {
                return kq;
            }

            a(IBinder ibinder)
            {
                kq = ibinder;
            }
        }

        if (iinterface != null && (iinterface instanceof hj))
        {
            return (hj)iinterface;
        } else
        {
            return new a(ibinder);
        }
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
    {
        d d1;
        switch (i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1598968902: 
            parcel1.writeString("com.google.android.gms.common.internal.ISignInButtonCreator");
            return true;

        case 1: // '\001'
            parcel.enforceInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
            d1 = a(com.google.android.gms.dynamic.g(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
            parcel1.writeNoException();
            break;
        }
        IBinder ibinder;
        if (d1 != null)
        {
            ibinder = d1.asBinder();
        } else
        {
            ibinder = null;
        }
        parcel1.writeStrongBinder(ibinder);
        return true;
    }
}
