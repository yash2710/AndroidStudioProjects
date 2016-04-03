// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.os.Binder;
import android.os.Parcel;

// Referenced classes of package com.google.android.gms.games.internal:
//            IGamesSignInService

public abstract class attachInterface extends Binder
    implements IGamesSignInService
{

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
    {
        switch (i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1598968902: 
            parcel1.writeString("com.google.android.gms.games.internal.IGamesSignInService");
            return true;

        case 5001: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            String s3 = bc(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeString(s3);
            return true;

        case 5002: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            String s2 = bd(parcel.readString());
            parcel1.writeNoException();
            parcel1.writeString(s2);
            return true;

        case 5009: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            String s = parcel.readString();
            boolean flag;
            String s1;
            if (parcel.readInt() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            s1 = f(s, flag);
            parcel1.writeNoException();
            parcel1.writeString(s1);
            return true;

        case 5003: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            a(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray(), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 5004: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            a(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray());
            parcel1.writeNoException();
            return true;

        case 5005: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            a(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 5006: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            a(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 5007: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            b(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 5008: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            b(b.ak(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray());
            parcel1.writeNoException();
            return true;

        case 9001: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
            o(parcel.readString(), parcel.readString());
            parcel1.writeNoException();
            return true;
        }
    }

    public b()
    {
        attachInterface(this, "com.google.android.gms.games.internal.IGamesSignInService");
    }
}
