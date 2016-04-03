// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.os.Binder;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.f;

// Referenced classes of package com.google.android.gms.games.internal:
//            IRoomService

public abstract class attachInterface extends Binder
    implements IRoomService
{

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
    {
        switch (i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1598968902: 
            parcel1.writeString("com.google.android.gms.games.internal.IRoomService");
            return true;

        case 1001: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            a(parcel.readStrongBinder(), cks.Stub.am(parcel.readStrongBinder()));
            return true;

        case 1002: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            hF();
            return true;

        case 1003: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            hG();
            return true;

        case 1004: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            c(parcel.readString(), parcel.readString(), parcel.readString());
            return true;

        case 1005: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            hH();
            return true;

        case 1006: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            DataHolder dataholder;
            int l;
            boolean flag1;
            if (parcel.readInt() != 0)
            {
                dataholder = DataHolder.CREATOR.x(parcel);
            } else
            {
                dataholder = null;
            }
            l = parcel.readInt();
            flag1 = false;
            if (l != 0)
            {
                flag1 = true;
            }
            a(dataholder, flag1);
            return true;

        case 1007: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            hI();
            return true;

        case 1008: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            int k = parcel.readInt();
            boolean flag = false;
            if (k != 0)
            {
                flag = true;
            }
            G(flag);
            return true;

        case 1009: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            a(parcel.createByteArray(), parcel.readString(), parcel.readInt());
            return true;

        case 1010: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            a(parcel.createByteArray(), parcel.createStringArray());
            return true;

        case 1011: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            r(parcel.readString(), parcel.readInt());
            return true;

        case 1012: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            s(parcel.readString(), parcel.readInt());
            return true;

        case 1013: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            be(parcel.readString());
            return true;

        case 1014: 
            parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
            bf(parcel.readString());
            return true;
        }
    }

    public cks.Stub()
    {
        attachInterface(this, "com.google.android.gms.games.internal.IRoomService");
    }
}
