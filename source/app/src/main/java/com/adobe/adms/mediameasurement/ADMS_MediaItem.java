// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.mediameasurement;

import java.util.Hashtable;

// Referenced classes of package com.adobe.adms.mediameasurement:
//            a, ADMS_MediaMeasurement

public class ADMS_MediaItem
{

    double a;
    private a b;
    public boolean complete;
    public boolean completeTracked;
    public Hashtable firstEventList;
    public double lastEventOffset;
    public double lastEventTimestamp;
    public int lastEventType;
    public int lastMilestone;
    public int lastOffsetMilestone;
    public double lastTrackOffset;
    public double length;
    protected ADMS_MediaMeasurement m;
    public String name;
    public double offset;
    public double percent;
    public String playerID;
    public String playerName;
    public String segment;
    public boolean segmentChanged;
    public boolean segmentGenerated;
    public int segmentNum;
    public String session;
    public double timePlayed;
    public double timePlayedSinceTrack;
    public double timestamp;
    public int trackCount;
    public boolean updateSegment;
    public boolean viewTracked;

    public ADMS_MediaItem()
    {
    }

    protected void startMonitor()
    {
        while (b != null && !b.a || b != null && !b.a) 
        {
            return;
        }
        stopMonitor();
        b = new a((byte)0);
        b.b = this;
        b.start();
    }

    protected void stopMonitor()
    {
        if (b != null)
        {
            synchronized (b)
            {
                b.a = true;
                b = null;
            }
        }
    }
}
