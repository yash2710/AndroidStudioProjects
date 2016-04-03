// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.plus.model.people.Person;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            ks

public final class ld extends d
    implements Person
{

    public ld(DataHolder dataholder, int i)
    {
        super(dataholder, i);
    }

    public final Object freeze()
    {
        return lc();
    }

    public final String getAboutMe()
    {
        return null;
    }

    public final com.google.android.gms.plus.model.people.Person.AgeRange getAgeRange()
    {
        return null;
    }

    public final String getBirthday()
    {
        return null;
    }

    public final String getBraggingRights()
    {
        return null;
    }

    public final int getCircledByCount()
    {
        return 0;
    }

    public final com.google.android.gms.plus.model.people.Person.Cover getCover()
    {
        return null;
    }

    public final String getCurrentLocation()
    {
        return null;
    }

    public final String getDisplayName()
    {
        return getString("displayName");
    }

    public final int getGender()
    {
        return 0;
    }

    public final String getId()
    {
        return getString("personId");
    }

    public final com.google.android.gms.plus.model.people.Person.Image getImage()
    {
        return new ks.c(getString("image"));
    }

    public final String getLanguage()
    {
        return null;
    }

    public final com.google.android.gms.plus.model.people.Person.Name getName()
    {
        return null;
    }

    public final String getNickname()
    {
        return null;
    }

    public final int getObjectType()
    {
        return ks.e.bA(getString("objectType"));
    }

    public final List getOrganizations()
    {
        return kZ();
    }

    public final List getPlacesLived()
    {
        return la();
    }

    public final int getPlusOneCount()
    {
        return 0;
    }

    public final int getRelationshipStatus()
    {
        return 0;
    }

    public final String getTagline()
    {
        return null;
    }

    public final String getUrl()
    {
        return getString("url");
    }

    public final List getUrls()
    {
        return lb();
    }

    public final boolean hasAboutMe()
    {
        return false;
    }

    public final boolean hasAgeRange()
    {
        return false;
    }

    public final boolean hasBirthday()
    {
        return false;
    }

    public final boolean hasBraggingRights()
    {
        return false;
    }

    public final boolean hasCircledByCount()
    {
        return false;
    }

    public final boolean hasCover()
    {
        return false;
    }

    public final boolean hasCurrentLocation()
    {
        return false;
    }

    public final boolean hasDisplayName()
    {
        return true;
    }

    public final boolean hasGender()
    {
        return false;
    }

    public final boolean hasId()
    {
        return true;
    }

    public final boolean hasImage()
    {
        return true;
    }

    public final boolean hasIsPlusUser()
    {
        return false;
    }

    public final boolean hasLanguage()
    {
        return false;
    }

    public final boolean hasName()
    {
        return false;
    }

    public final boolean hasNickname()
    {
        return false;
    }

    public final boolean hasObjectType()
    {
        return true;
    }

    public final boolean hasOrganizations()
    {
        return false;
    }

    public final boolean hasPlacesLived()
    {
        return false;
    }

    public final boolean hasPlusOneCount()
    {
        return false;
    }

    public final boolean hasRelationshipStatus()
    {
        return false;
    }

    public final boolean hasTagline()
    {
        return false;
    }

    public final boolean hasUrl()
    {
        return true;
    }

    public final boolean hasUrls()
    {
        return false;
    }

    public final boolean hasVerified()
    {
        return false;
    }

    public final boolean isPlusUser()
    {
        return false;
    }

    public final boolean isVerified()
    {
        return false;
    }

    public final ArrayList kZ()
    {
        return null;
    }

    public final ArrayList la()
    {
        return null;
    }

    public final ArrayList lb()
    {
        return null;
    }

    public final Person lc()
    {
        return new ks(getDisplayName(), getId(), (ks.c)getImage(), getObjectType(), getUrl());
    }
}
