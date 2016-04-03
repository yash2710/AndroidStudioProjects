// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hk;

// Referenced classes of package com.google.android.gms.games.quest:
//            Milestone, MilestoneEntityCreator

public final class MilestoneEntity
    implements SafeParcelable, Milestone
{

    public static final MilestoneEntityCreator CREATOR = new MilestoneEntityCreator();
    private final String Ni;
    private final String Ot;
    private final long TM;
    private final long TN;
    private final byte TO[];
    private final int mState;
    private final int xM;

    MilestoneEntity(int i, String s, long l, long l1, byte abyte0[], 
            int j, String s1)
    {
        xM = i;
        Ot = s;
        TM = l;
        TN = l1;
        TO = abyte0;
        mState = j;
        Ni = s1;
    }

    public MilestoneEntity(Milestone milestone)
    {
        xM = 4;
        Ot = milestone.getMilestoneId();
        TM = milestone.getCurrentProgress();
        TN = milestone.getTargetProgress();
        mState = milestone.getState();
        Ni = milestone.getEventId();
        byte abyte0[] = milestone.getCompletionRewardData();
        if (abyte0 == null)
        {
            TO = null;
            return;
        } else
        {
            TO = new byte[abyte0.length];
            System.arraycopy(abyte0, 0, TO, 0, abyte0.length);
            return;
        }
    }

    static int a(Milestone milestone)
    {
        Object aobj[] = new Object[5];
        aobj[0] = milestone.getMilestoneId();
        aobj[1] = Long.valueOf(milestone.getCurrentProgress());
        aobj[2] = Long.valueOf(milestone.getTargetProgress());
        aobj[3] = Integer.valueOf(milestone.getState());
        aobj[4] = milestone.getEventId();
        return hk.hashCode(aobj);
    }

    static boolean a(Milestone milestone, Object obj)
    {
        if (obj instanceof Milestone)
        {
            if (milestone == obj)
            {
                return true;
            }
            Milestone milestone1 = (Milestone)obj;
            if (hk.equal(milestone1.getMilestoneId(), milestone.getMilestoneId()) && hk.equal(Long.valueOf(milestone1.getCurrentProgress()), Long.valueOf(milestone.getCurrentProgress())) && hk.equal(Long.valueOf(milestone1.getTargetProgress()), Long.valueOf(milestone.getTargetProgress())) && hk.equal(Integer.valueOf(milestone1.getState()), Integer.valueOf(milestone.getState())) && hk.equal(milestone1.getEventId(), milestone.getEventId()))
            {
                return true;
            }
        }
        return false;
    }

    static String b(Milestone milestone)
    {
        return hk.e(milestone).a("MilestoneId", milestone.getMilestoneId()).a("CurrentProgress", Long.valueOf(milestone.getCurrentProgress())).a("TargetProgress", Long.valueOf(milestone.getTargetProgress())).a("State", Integer.valueOf(milestone.getState())).a("CompletionRewardData", milestone.getCompletionRewardData()).a("EventId", milestone.getEventId()).toString();
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public final Milestone freeze()
    {
        return this;
    }

    public final volatile Object freeze()
    {
        return freeze();
    }

    public final byte[] getCompletionRewardData()
    {
        return TO;
    }

    public final long getCurrentProgress()
    {
        return TM;
    }

    public final String getEventId()
    {
        return Ni;
    }

    public final String getMilestoneId()
    {
        return Ot;
    }

    public final int getState()
    {
        return mState;
    }

    public final long getTargetProgress()
    {
        return TN;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final int hashCode()
    {
        return a(this);
    }

    public final boolean isDataValid()
    {
        return true;
    }

    public final String toString()
    {
        return b(this);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        MilestoneEntityCreator.a(this, parcel, i);
    }

}
