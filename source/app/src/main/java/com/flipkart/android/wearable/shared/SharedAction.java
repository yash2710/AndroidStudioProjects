// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable.shared;


public final class SharedAction extends Enum
{

    public static final SharedAction OPEN_ON_PHONE;
    public static final SharedAction REFRESH_WISH_LIST_PATH;
    public static final SharedAction UPDATE_IMAGE_URL;
    private static final SharedAction b[];
    private String a;

    private SharedAction(String s, int i, String s1)
    {
        super(s, i);
        a = s1;
    }

    public static SharedAction getAction(String s)
    {
        SharedAction asharedaction[] = values();
        int i = asharedaction.length;
        for (int j = 0; j < i; j++)
        {
            SharedAction sharedaction = asharedaction[j];
            if (sharedaction.getPath().equals(s))
            {
                return sharedaction;
            }
        }

        return null;
    }

    public static SharedAction valueOf(String s)
    {
        return (SharedAction)Enum.valueOf(com/flipkart/android/wearable/shared/SharedAction, s);
    }

    public static SharedAction[] values()
    {
        return (SharedAction[])b.clone();
    }

    public final String getPath()
    {
        return a;
    }

    static 
    {
        REFRESH_WISH_LIST_PATH = new SharedAction("REFRESH_WISH_LIST_PATH", 0, "/refresh_wish_list");
        UPDATE_IMAGE_URL = new SharedAction("UPDATE_IMAGE_URL", 1, "/update_image");
        OPEN_ON_PHONE = new SharedAction("OPEN_ON_PHONE", 2, "/open_on_phone");
        SharedAction asharedaction[] = new SharedAction[3];
        asharedaction[0] = REFRESH_WISH_LIST_PATH;
        asharedaction[1] = UPDATE_IMAGE_URL;
        asharedaction[2] = OPEN_ON_PHONE;
        b = asharedaction;
    }
}
