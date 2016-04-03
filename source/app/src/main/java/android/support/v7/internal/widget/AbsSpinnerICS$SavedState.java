// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable;

class <init> extends android.view.SpinnerICS.SavedState
{

    public static final android.os.bsSpinnerICS.SavedState._cls1 CREATOR = new _cls1();
    int position;
    long selectedId;

    public String toString()
    {
        return (new StringBuilder("AbsSpinner.SavedState{")).append(Integer.toHexString(System.identityHashCode(this))).append(" selectedId=").append(selectedId).append(" position=").append(position).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.ToParcel(parcel, i);
        parcel.writeLong(selectedId);
        parcel.writeInt(position);
    }


    private _cls1(Parcel parcel)
    {
        super(parcel);
        selectedId = parcel.readLong();
        position = parcel.readInt();
    }

    position(Parcel parcel, position position1)
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

        public final AbsSpinnerICS.SavedState createFromParcel(Parcel parcel)
        {
            return new AbsSpinnerICS.SavedState(parcel, null);
        }

        public final volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public final AbsSpinnerICS.SavedState[] newArray(int i)
        {
            return new AbsSpinnerICS.SavedState[i];
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
