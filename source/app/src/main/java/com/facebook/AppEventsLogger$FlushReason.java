// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;


final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES EAGER_FLUSHING_EVENT;
    public static final ALUES EVENT_THRESHOLD;
    public static final .VALUES EXPLICIT;
    public static final .VALUES PERSISTED_EVENTS;
    public static final .VALUES SESSION_CHANGE;
    public static final .VALUES TIMER;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/facebook/AppEventsLogger$FlushReason, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        EXPLICIT = new <init>("EXPLICIT", 0);
        TIMER = new <init>("TIMER", 1);
        SESSION_CHANGE = new <init>("SESSION_CHANGE", 2);
        PERSISTED_EVENTS = new <init>("PERSISTED_EVENTS", 3);
        EVENT_THRESHOLD = new <init>("EVENT_THRESHOLD", 4);
        EAGER_FLUSHING_EVENT = new <init>("EAGER_FLUSHING_EVENT", 5);
        n_3B_.clone aclone[] = new <init>[6];
        aclone[0] = EXPLICIT;
        aclone[1] = TIMER;
        aclone[2] = SESSION_CHANGE;
        aclone[3] = PERSISTED_EVENTS;
        aclone[4] = EVENT_THRESHOLD;
        aclone[5] = EAGER_FLUSHING_EVENT;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
