// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.d;

// Referenced classes of package com.google.android.gms.maps.internal:
//            IStreetViewPanoramaViewDelegate, IStreetViewPanoramaDelegate

public abstract class a extends Binder
    implements IStreetViewPanoramaViewDelegate
{

    public static IStreetViewPanoramaViewDelegate aV(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
        class a
            implements IStreetViewPanoramaViewDelegate
        {

            private IBinder kq;

            public IBinder asBinder()
            {
                return kq;
            }

            public IStreetViewPanoramaDelegate getStreetViewPanorama()
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                IStreetViewPanoramaDelegate istreetviewpanoramadelegate;
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                kq.transact(1, parcel, parcel1, 0);
                parcel1.readException();
                istreetviewpanoramadelegate = IStreetViewPanoramaDelegate.a.aT(parcel1.readStrongBinder());
                parcel1.recycle();
                parcel.recycle();
                return istreetviewpanoramadelegate;
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public d getView()
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                d d1;
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                kq.transact(8, parcel, parcel1, 0);
                parcel1.readException();
                d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
                parcel1.recycle();
                parcel.recycle();
                return d1;
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void onCreate(Bundle bundle)
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                if (bundle == null)
                {
                    break MISSING_BLOCK_LABEL_56;
                }
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L1:
                kq.transact(2, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void onDestroy()
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                kq.transact(5, parcel, parcel1, 0);
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

            public void onLowMemory()
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                kq.transact(6, parcel, parcel1, 0);
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

            public void onPause()
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                kq.transact(4, parcel, parcel1, 0);
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

            public void onResume()
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                kq.transact(3, parcel, parcel1, 0);
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

            public void onSaveInstanceState(Bundle bundle)
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                if (bundle == null)
                {
                    break MISSING_BLOCK_LABEL_69;
                }
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L1:
                kq.transact(7, parcel, parcel1, 0);
                parcel1.readException();
                if (parcel1.readInt() != 0)
                {
                    bundle.readFromParcel(parcel1);
                }
                parcel1.recycle();
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
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

        if (iinterface != null && (iinterface instanceof IStreetViewPanoramaViewDelegate))
        {
            return (IStreetViewPanoramaViewDelegate)iinterface;
        } else
        {
            return new a(ibinder);
        }
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
    {
        d d1;
        IBinder ibinder;
        switch (i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1598968902: 
            parcel1.writeString("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            return true;

        case 1: // '\001'
            parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            IStreetViewPanoramaDelegate istreetviewpanoramadelegate = getStreetViewPanorama();
            parcel1.writeNoException();
            IBinder ibinder1 = null;
            if (istreetviewpanoramadelegate != null)
            {
                ibinder1 = istreetviewpanoramadelegate.asBinder();
            }
            parcel1.writeStrongBinder(ibinder1);
            return true;

        case 2: // '\002'
            parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            int l = parcel.readInt();
            Bundle bundle1 = null;
            if (l != 0)
            {
                bundle1 = (Bundle)Bundle.CREATOR.l(parcel);
            }
            onCreate(bundle1);
            parcel1.writeNoException();
            return true;

        case 3: // '\003'
            parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            onResume();
            parcel1.writeNoException();
            return true;

        case 4: // '\004'
            parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            onPause();
            parcel1.writeNoException();
            return true;

        case 5: // '\005'
            parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            onDestroy();
            parcel1.writeNoException();
            return true;

        case 6: // '\006'
            parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            onLowMemory();
            parcel1.writeNoException();
            return true;

        case 7: // '\007'
            parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            int k = parcel.readInt();
            Bundle bundle = null;
            if (k != 0)
            {
                bundle = (Bundle)Bundle.CREATOR.l(parcel);
            }
            onSaveInstanceState(bundle);
            parcel1.writeNoException();
            if (bundle != null)
            {
                parcel1.writeInt(1);
                bundle.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;

        case 8: // '\b'
            parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            d1 = getView();
            parcel1.writeNoException();
            ibinder = null;
            break;
        }
        if (d1 != null)
        {
            ibinder = d1.asBinder();
        }
        parcel1.writeStrongBinder(ibinder);
        return true;
    }
}
