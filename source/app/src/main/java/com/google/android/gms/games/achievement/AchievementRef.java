// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.internal.gx;
import com.google.android.gms.internal.hk;

// Referenced classes of package com.google.android.gms.games.achievement:
//            Achievement

public final class AchievementRef extends d
    implements Achievement
{

    AchievementRef(DataHolder dataholder, int i)
    {
        super(dataholder, i);
    }

    public final String getAchievementId()
    {
        return getString("external_achievement_id");
    }

    public final int getCurrentSteps()
    {
        boolean flag = true;
        if (getType() != flag)
        {
            flag = false;
        }
        gx.A(flag);
        return getInteger("current_steps");
    }

    public final String getDescription()
    {
        return getString("description");
    }

    public final void getDescription(CharArrayBuffer chararraybuffer)
    {
        a("description", chararraybuffer);
    }

    public final String getFormattedCurrentSteps()
    {
        boolean flag = true;
        if (getType() != flag)
        {
            flag = false;
        }
        gx.A(flag);
        return getString("formatted_current_steps");
    }

    public final void getFormattedCurrentSteps(CharArrayBuffer chararraybuffer)
    {
        boolean flag = true;
        if (getType() != flag)
        {
            flag = false;
        }
        gx.A(flag);
        a("formatted_current_steps", chararraybuffer);
    }

    public final String getFormattedTotalSteps()
    {
        boolean flag = true;
        if (getType() != flag)
        {
            flag = false;
        }
        gx.A(flag);
        return getString("formatted_total_steps");
    }

    public final void getFormattedTotalSteps(CharArrayBuffer chararraybuffer)
    {
        boolean flag = true;
        if (getType() != flag)
        {
            flag = false;
        }
        gx.A(flag);
        a("formatted_total_steps", chararraybuffer);
    }

    public final long getLastUpdatedTimestamp()
    {
        return getLong("last_updated_timestamp");
    }

    public final String getName()
    {
        return getString("name");
    }

    public final void getName(CharArrayBuffer chararraybuffer)
    {
        a("name", chararraybuffer);
    }

    public final Player getPlayer()
    {
        return new PlayerRef(DG, EC);
    }

    public final Uri getRevealedImageUri()
    {
        return aw("revealed_icon_image_uri");
    }

    public final String getRevealedImageUrl()
    {
        return getString("revealed_icon_image_url");
    }

    public final int getState()
    {
        return getInteger("state");
    }

    public final int getTotalSteps()
    {
        boolean flag = true;
        if (getType() != flag)
        {
            flag = false;
        }
        gx.A(flag);
        return getInteger("total_steps");
    }

    public final int getType()
    {
        return getInteger("type");
    }

    public final Uri getUnlockedImageUri()
    {
        return aw("unlocked_icon_image_uri");
    }

    public final String getUnlockedImageUrl()
    {
        return getString("unlocked_icon_image_url");
    }

    public final long getXpValue()
    {
        if (!av("instance_xp_value") || ax("instance_xp_value"))
        {
            return getLong("definition_xp_value");
        } else
        {
            return getLong("instance_xp_value");
        }
    }

    public final String toString()
    {
        com.google.android.gms.internal.hk.a a = hk.e(this).a("AchievementId", getAchievementId()).a("Type", Integer.valueOf(getType())).a("Name", getName()).a("Description", getDescription()).a("UnlockedImageUri", getUnlockedImageUri()).a("UnlockedImageUrl", getUnlockedImageUrl()).a("RevealedImageUri", getRevealedImageUri()).a("RevealedImageUrl", getRevealedImageUrl()).a("Player", getPlayer()).a("LastUpdatedTimeStamp", Long.valueOf(getLastUpdatedTimestamp()));
        if (getType() == 1)
        {
            a.a("CurrentSteps", Integer.valueOf(getCurrentSteps()));
            a.a("TotalSteps", Integer.valueOf(getTotalSteps()));
        }
        return a.toString();
    }
}
