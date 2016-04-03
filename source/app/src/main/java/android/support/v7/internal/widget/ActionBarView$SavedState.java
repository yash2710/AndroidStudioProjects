// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable;

class <init> extends android.view.ionBarView.SavedState
{

    public static final android.os.ctionBarView.SavedState._cls1 CREATOR = new _cls1();
    int expandedMenuItemId;
    boolean isOverflowOpen;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.ToParcel(parcel, i);
        parcel.writeInt(expandedMenuItemId);
        int j;
        if (isOverflowOpen)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
    }


    private _cls1(Parcel parcel)
    {
        super(parcel);
        expandedMenuItemId = parcel.readInt();
        boolean flag;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isOverflowOpen = flag;
    }

    isOverflowOpen(Parcel parcel, isOverflowOpen isoverflowopen)
    {
        this(parcel);
    }

    <init>(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.Creator
    {

        public final ActionBarView.SavedState createFromParcel(Parcel parcel)
        {
            return new ActionBarView.SavedState(parcel, null);
        }

        public final volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public final ActionBarView.SavedState[] newArray(int i)
        {
            return new ActionBarView.SavedState[i];
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
