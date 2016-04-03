// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

// Referenced classes of package com.google.android.gms.maps.internal:
//            ILocationSourceDelegate

public abstract class attachInterface extends Binder
    implements ILocationSourceDelegate
{

    public static ILocationSourceDelegate aA(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.maps.internal.ILocationSourceDelegate");
        class a
            implements ILocationSourceDelegate
        {

            private IBinder kq;

            public void activate(h h1)
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ILocationSourceDelegate");
                if (h1 == null)
                {
                    break MISSING_BLOCK_LABEL_59;
                }
                IBinder ibinder1 = h1.asBinder();
_L1:
                parcel.writeStrongBinder(ibinder1);
                kq.transact(1, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
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

            public void deactivate()
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.ILocationSourceDelegate");
                kq.transact(2, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            a(IBinder ibinder)
            {
                kq = ibinder;
            }
        }

        if (iinterface != null && (iinterface instanceof ILocationSourceDelegate))
        {
            return (ILocationSourceDelegate)iinterface;
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
            parcel1.writeString("com.google.android.gms.maps.internal.ILocationSourceDelegate");
            return true;

        case 1: // '\001'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ILocationSourceDelegate");
            activate(activate(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 2: // '\002'
            parcel.enforceInterface("com.google.android.gms.maps.internal.ILocationSourceDelegate");
            deactivate();
            parcel1.writeNoException();
            return true;
        }
    }

    public a()
    {
        attachInterface(this, "com.google.android.gms.maps.internal.ILocationSourceDelegate");
    }
}
