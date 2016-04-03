// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.mediameasurement;

import com.adobe.adms.measurement.ADMS_Measurement;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

// Referenced classes of package com.adobe.adms.mediameasurement:
//            b, ADMS_MediaItem, ADMS_MediaState, ADMS_MediaMeasurementDelegate

public class ADMS_MediaMeasurement
{

    public String channel;
    public int completeCloseOffsetThreshold;
    public Hashtable contextDataMapping;
    public ADMS_MediaMeasurementDelegate _flddelegate;
    protected Hashtable list;
    public boolean segmentByMilestones;
    public boolean segmentByOffsetMilestones;
    public String trackEvents;
    public String trackMilestones;
    public String trackOffsetMilestones;
    public int trackSeconds;
    public String trackVars;

    private ADMS_MediaMeasurement()
    {
        list = null;
        trackVars = "";
        trackEvents = "";
        channel = "";
        trackSeconds = 0;
        completeCloseOffsetThreshold = 1;
        trackMilestones = "";
        segmentByMilestones = false;
        trackOffsetMilestones = "";
        segmentByOffsetMilestones = false;
        contextDataMapping = null;
        _flddelegate = null;
    }

    ADMS_MediaMeasurement(byte byte0)
    {
        this();
    }

    private static boolean a(Object obj)
    {
        String s;
        boolean flag;
        try
        {
            s = (String)obj;
        }
        catch (Exception exception)
        {
            return false;
        }
        flag = false;
        if (s != null)
        {
            flag = true;
        }
        return flag;
    }

    private static boolean b(Object obj)
    {
        Integer integer;
        boolean flag;
        try
        {
            integer = (Integer)obj;
        }
        catch (Exception exception)
        {
            return false;
        }
        flag = false;
        if (integer != null)
        {
            flag = true;
        }
        return flag;
    }

    public static ADMS_MediaMeasurement sharedInstance()
    {
        return b.a;
    }

    protected void buildContextData(Hashtable hashtable, Hashtable hashtable1, ADMS_MediaItem adms_mediaitem)
    {
        StringBuffer stringbuffer;
        StringBuffer stringbuffer1;
        StringBuffer stringbuffer2;
        stringbuffer = new StringBuffer(String.valueOf(hashtable1.get("linkTrackVars")));
        stringbuffer1 = new StringBuffer(String.valueOf(hashtable1.get("linkTrackEvents")));
        String s = "m_i";
        hashtable.put("a.contentType", "video");
        if (isSet(channel))
        {
            hashtable.put((new StringBuilder()).append("a.media.").append("channel").toString(), channel);
        }
        hashtable.put((new StringBuilder()).append("a.media.").append("name").toString(), adms_mediaitem.name);
        hashtable.put((new StringBuilder()).append("a.media.").append("playerName").toString(), adms_mediaitem.playerName);
        if (adms_mediaitem.length > 0.0D)
        {
            hashtable.put((new StringBuilder()).append("a.media.").append("length").toString(), (new StringBuilder()).append((int)adms_mediaitem.length).toString());
        }
        hashtable.put((new StringBuilder()).append("a.media.").append("timePlayed").toString(), (new StringBuilder()).append((int)Math.floor(adms_mediaitem.timePlayedSinceTrack)).toString());
        if (!adms_mediaitem.viewTracked)
        {
            hashtable.put((new StringBuilder()).append("a.media.").append("view").toString(), "true");
            s = "m_s";
            adms_mediaitem.viewTracked = true;
        }
        if (isSet(adms_mediaitem.segment))
        {
            hashtable.put((new StringBuilder()).append("a.media.").append("segmentNum").toString(), (new StringBuilder()).append(adms_mediaitem.segmentNum).toString());
            hashtable.put((new StringBuilder()).append("a.media.").append("segment").toString(), adms_mediaitem.segment);
            if (adms_mediaitem.segmentChanged && adms_mediaitem.timePlayedSinceTrack > 0.0D)
            {
                hashtable.put((new StringBuilder()).append("a.media.").append("segmentView").toString(), "true");
            }
        }
        if (!adms_mediaitem.completeTracked && adms_mediaitem.complete)
        {
            hashtable.put((new StringBuilder()).append("a.media.").append("complete").toString(), "true");
            adms_mediaitem.completeTracked = true;
        }
        if (adms_mediaitem.lastMilestone > 0)
        {
            hashtable.put((new StringBuilder()).append("a.media.").append("milestone").toString(), (new StringBuilder()).append(adms_mediaitem.lastMilestone).toString());
        }
        if (adms_mediaitem.lastOffsetMilestone > 0)
        {
            hashtable.put((new StringBuilder()).append("a.media.").append("offsetMilestone").toString(), (new StringBuilder()).append(adms_mediaitem.lastOffsetMilestone).toString());
        }
        hashtable1.put("pe", s);
        hashtable1.put("pev3", "video");
        stringbuffer2 = new StringBuffer();
        if (!isSet(contextDataMapping)) goto _L2; else goto _L1
_L1:
        Enumeration enumeration1 = contextDataMapping.keys();
_L8:
        String s1;
        String s2;
        String s5;
        if (!enumeration1.hasMoreElements())
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = (String)enumeration1.nextElement();
        Object obj;
        String as[];
        int i;
        if (s1.length() > "a.media.".length() && s1.substring(0, "a.media.".length()).equals("a.media."))
        {
            s2 = s1.substring("a.media.".length());
        } else
        {
            s2 = "";
        }
        obj = contextDataMapping.get(s1);
        if (!a(obj) || !hashtable.containsKey(s1))
        {
            break MISSING_BLOCK_LABEL_1288;
        }
        as = ((String)obj).split(",");
        i = 0;
_L4:
        if (i >= as.length)
        {
            continue; /* Loop/switch isn't completed */
        }
        s5 = as[i];
        if (!s1.equals("a.contentType"))
        {
            break; /* Loop/switch isn't completed */
        }
        if (stringbuffer.length() > 0)
        {
            stringbuffer.append(",");
            stringbuffer.append(String.valueOf(s5));
        }
        hashtable1.put((String)s5, (String)hashtable.get(s1));
_L6:
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        if (s2.equals("")) goto _L6; else goto _L5
_L5:
        if (s2.equals("view") || s2.equals("segmentView") || s2.equals("complete") || s2.equals("timePlayed"))
        {
            if (s2.equals("timePlayed"))
            {
                if (isSet(hashtable.get(s1)) && Integer.valueOf(String.valueOf(hashtable.get(s1))).intValue() > 0)
                {
                    String s7;
                    if (stringbuffer2.length() > 0)
                    {
                        s7 = ",";
                    } else
                    {
                        s7 = "";
                    }
                    stringbuffer2.append(s7);
                    stringbuffer2.append(String.valueOf(s5));
                    stringbuffer2.append("=");
                    stringbuffer2.append(hashtable.get(s1));
                    if (stringbuffer1.length() > 0)
                    {
                        stringbuffer1.append(",");
                        stringbuffer1.append(String.valueOf(s5));
                        stringbuffer1.append("=");
                        stringbuffer1.append(hashtable.get(s1));
                    }
                }
            } else
            if (isSet(hashtable.get(s1)))
            {
                String s6;
                if (stringbuffer2.length() > 0)
                {
                    s6 = ",";
                } else
                {
                    s6 = "";
                }
                stringbuffer2.append(s6);
                stringbuffer2.append(String.valueOf(s5));
                if (stringbuffer1.length() > 0)
                {
                    stringbuffer1.append(",");
                    stringbuffer1.append(String.valueOf(s5));
                }
            }
        } else
        if (s2.equals("segment") && hashtable.containsKey((new StringBuilder()).append(s1).append("Num").toString()) && isSet(hashtable.get((new StringBuilder()).append(s1).append("Num").toString())))
        {
            if (stringbuffer.length() > 0)
            {
                stringbuffer.append(",");
                stringbuffer.append(String.valueOf(s5));
            }
            hashtable1.put(String.valueOf(s5), (new StringBuilder()).append(hashtable.get((new StringBuilder()).append(s1).append("Num").toString())).append(":").append(String.valueOf(hashtable.get(s1))).toString());
        } else
        {
            if (stringbuffer.length() > 0)
            {
                stringbuffer.append(",");
                stringbuffer.append(String.valueOf(s5));
            }
            hashtable1.put(String.valueOf(s5), String.valueOf(hashtable.get(s1)));
        }
          goto _L6
        if (s2.equals("milestones") || s2.equals("offsetMilestones"))
        {
            String s3 = s1.substring(0, -1 + s1.length());
            Hashtable hashtable2 = (Hashtable)contextDataMapping.get((new StringBuilder()).append(s3).append("s").toString());
            if (hashtable.containsKey(s3) && isSet(hashtable.get(s3)) && hashtable2.containsKey(hashtable.get(s3)))
            {
                if (stringbuffer1.length() > 0)
                {
                    stringbuffer1.append(",");
                    stringbuffer1.append(hashtable2.get(hashtable.get(s3)));
                }
                String s4;
                if (stringbuffer2.length() > 0)
                {
                    s4 = ",";
                } else
                {
                    s4 = "";
                }
                stringbuffer2.append(s4);
                stringbuffer2.append(hashtable2.get(hashtable.get(s3)));
            }
        }
        if (true) goto _L8; else goto _L7
_L7:
        hashtable1.put("events", stringbuffer2.toString());
_L10:
        hashtable1.put("linkTrackVars", stringbuffer.toString());
        hashtable1.put("linkTrackEvents", stringbuffer1.toString());
        return;
_L2:
        if (stringbuffer.length() == 0)
        {
            stringbuffer.append("None");
        }
        Enumeration enumeration = hashtable.keys();
        while (enumeration.hasMoreElements()) 
        {
            stringbuffer.append(",contextdata.");
            stringbuffer.append((String)enumeration.nextElement());
        }
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected String cleanName(String s)
    {
        return s.replace("\n", "").replace("\r", "").replace("--**--", "");
    }

    public Object clone()
    {
        throw new CloneNotSupportedException();
    }

    public void close(String s)
    {
        playerEvent(s, 0, -1D, 0, null, -1D, null);
    }

    public void complete(String s, double d)
    {
        playerEvent(s, 5, d, 0, null, -1D, null);
    }

    protected boolean isBoolean(Object obj)
    {
        Boolean boolean1;
        boolean flag;
        try
        {
            boolean1 = (Boolean)obj;
        }
        catch (Exception exception)
        {
            return false;
        }
        flag = false;
        if (boolean1 != null)
        {
            flag = true;
        }
        return flag;
    }

    protected boolean isSet(double d)
    {
        return d != 0.0D;
    }

    protected boolean isSet(float f)
    {
        return (double)f != 0.0D;
    }

    protected boolean isSet(int i)
    {
        return i != 0;
    }

    protected boolean isSet(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (a(obj))
        {
            return isSet((String)obj);
        }
        if (b(obj))
        {
            return isSet(toInteger(obj));
        }
        if (isBoolean(obj))
        {
            return isSet(toBoolean(obj));
        } else
        {
            return true;
        }
    }

    protected boolean isSet(String s)
    {
        while (s == null || s.length() == 0) 
        {
            return false;
        }
        return true;
    }

    protected boolean isSet(boolean flag)
    {
        return flag;
    }

    public void open(String s, double d, String s1)
    {
        open(s, d, s1, null);
    }

    public void open(String s, double d, String s1, String s2)
    {
        ADMS_MediaItem adms_mediaitem = new ADMS_MediaItem();
        String s3;
        if (!isSet(s1))
        {
            s1 = "Not_Specified";
        }
        s3 = cleanName(s1);
        if (isSet(s))
        {
            s = cleanName(s);
        }
        if (!isSet(d))
        {
            d = -1D;
        }
        if (isSet(s) && isSet(s3))
        {
            if (!isSet(list))
            {
                list = new Hashtable();
            }
            if (list.containsKey(s))
            {
                close(s);
            }
            if (isSet(s2))
            {
                Enumeration enumeration = list.keys();
                do
                {
                    if (!enumeration.hasMoreElements())
                    {
                        break;
                    }
                    String s4 = (String)enumeration.nextElement();
                    if (list.containsKey(s4) && ((ADMS_MediaItem)list.get(s4)).playerID.equals(s2))
                    {
                        close(s4);
                    }
                } while (true);
            }
            adms_mediaitem.name = s;
            adms_mediaitem.length = d;
            adms_mediaitem.offset = 0.0D;
            adms_mediaitem.percent = 0.0D;
            adms_mediaitem.playerName = cleanName(s3);
            adms_mediaitem.playerID = s2;
            adms_mediaitem.timePlayed = 0.0D;
            adms_mediaitem.timePlayedSinceTrack = 0.0D;
            adms_mediaitem.timestamp = Math.floor((double)System.currentTimeMillis() / 1000D);
            adms_mediaitem.lastEventType = 0;
            adms_mediaitem.lastEventTimestamp = adms_mediaitem.timestamp;
            adms_mediaitem.lastEventOffset = 0.0D;
            adms_mediaitem.session = "";
            adms_mediaitem.lastTrackOffset = -1D;
            adms_mediaitem.trackCount = 0;
            adms_mediaitem.firstEventList = new Hashtable();
            adms_mediaitem.viewTracked = false;
            adms_mediaitem.segmentNum = 0;
            adms_mediaitem.segment = "";
            adms_mediaitem.a = 0.0D;
            adms_mediaitem.segmentGenerated = false;
            adms_mediaitem.segmentChanged = false;
            adms_mediaitem.lastMilestone = 0;
            adms_mediaitem.lastOffsetMilestone = 0;
            adms_mediaitem.m = this;
            list.put(s, adms_mediaitem);
        }
    }

    public void play(String s, double d)
    {
        String s1;
        if (isSet(s))
        {
            s1 = cleanName(s);
        } else
        {
            s1 = s;
        }
        playerEvent(s1, 1, d, 0, null, -1D, null);
        if (isSet(s1) && isSet(list) && list.containsKey(s1))
        {
            ((ADMS_MediaItem)list.get(s1)).startMonitor();
        }
    }

    protected ADMS_MediaItem playerEvent(String s, int i, double d, int j, String s1, double d1, Object obj)
    {
        double d2;
        String s2;
        String s3;
        double d3;
        String s4;
        String s5;
        boolean flag;
        boolean flag1;
        boolean flag2;
        ADMS_MediaItem adms_mediaitem;
        d2 = Math.floor((double)System.currentTimeMillis() / 1000D);
        s2 = trackVars;
        s3 = trackEvents;
        d3 = trackSeconds;
        s4 = trackMilestones;
        s5 = trackOffsetMilestones;
        flag = segmentByMilestones;
        flag1 = segmentByOffsetMilestones;
        flag2 = true;
        if (isSet(s))
        {
            s = cleanName(s);
        }
        int j3;
        int k3;
        boolean flag6;
        int l4;
        int i5;
        int j5;
        int l5;
        int i6;
        double d16;
        if (isSet(s) && isSet(list) && list.containsKey(s))
        {
            adms_mediaitem = (ADMS_MediaItem)list.get(s);
        } else
        {
            adms_mediaitem = null;
        }
        if (adms_mediaitem == null) goto _L2; else goto _L1
_L1:
        double d4;
        int k;
        ADMS_MediaState adms_mediastate;
        String s6;
        int l;
        double d13;
        String as2[];
        double d15;
        int k5;
        if (d < 0.0D)
        {
            if (adms_mediaitem.lastEventType == 1 && adms_mediaitem.lastEventTimestamp > 0.0D)
            {
                d = (d2 - adms_mediaitem.lastEventTimestamp) + adms_mediaitem.lastEventOffset;
            } else
            {
                d = adms_mediaitem.lastEventOffset;
            }
        }
        boolean flag3;
        String s7;
        String s8;
        String s9;
        Hashtable hashtable;
        Hashtable hashtable1;
        String s10;
        String s11;
        double d5;
        int i1;
        String s12;
        String s13;
        String s14;
        boolean flag4;
        StringBuilder stringbuilder;
        String s15;
        String s16;
        int j1;
        boolean flag5;
        double d6;
        double d7;
        double d8;
        String as[];
        double d9;
        int k1;
        NumberFormatException numberformatexception;
        double d10;
        StringBuilder stringbuilder1;
        Object aobj[];
        int l1;
        int i2;
        String as1[];
        double d11;
        int j2;
        String s17;
        int k2;
        NumberFormatException numberformatexception1;
        double d12;
        StringBuilder stringbuilder2;
        Object aobj1[];
        StringBuilder stringbuilder3;
        Object aobj2[];
        int l2;
        int i3;
        String as3[];
        int l3;
        NumberFormatException numberformatexception2;
        double d14;
        int i4;
        int j4;
        int k4;
        NumberFormatException numberformatexception3;
        if (adms_mediaitem.length > 0.0D)
        {
            if (d >= adms_mediaitem.length)
            {
                d = adms_mediaitem.length;
            }
            d4 = d;
        } else
        {
            d4 = d;
        }
        if (d4 < 0.0D)
        {
            d4 = 0.0D;
        }
        adms_mediaitem.offset = d4;
        if (adms_mediaitem.length > 0.0D)
        {
            d16 = 100D * (adms_mediaitem.offset / adms_mediaitem.length);
            adms_mediaitem.percent = d16;
            double d17;
            if (adms_mediaitem.percent > 100D)
            {
                d17 = 100D;
            } else
            {
                d17 = adms_mediaitem.percent;
            }
            adms_mediaitem.percent = d17;
        }
        if (adms_mediaitem.lastEventOffset < 0.0D)
        {
            adms_mediaitem.lastEventOffset = d4;
        }
        k = adms_mediaitem.trackCount;
        adms_mediastate = new ADMS_MediaState();
        adms_mediastate.name = s;
        adms_mediastate.length = adms_mediaitem.length;
        adms_mediastate.openTime = new Date();
        adms_mediastate.openTime.setTime((long)adms_mediaitem.timestamp);
        adms_mediastate.offset = adms_mediaitem.offset;
        adms_mediastate.percent = adms_mediaitem.percent;
        adms_mediastate.playerName = adms_mediaitem.playerName;
        if (adms_mediaitem.lastTrackOffset >= 0.0D) goto _L4; else goto _L3
_L3:
        adms_mediastate.mediaEvent = "OPEN";
_L23:
        if (i > 2) goto _L6; else goto _L5
_L5:
        i6 = adms_mediaitem.lastEventType;
        if (i == i6 || i == 2 && adms_mediaitem.lastEventType != 1) goto _L2; else goto _L6
_L6:
        if (!isSet(s1))
        {
            l = adms_mediaitem.segmentNum;
            s6 = adms_mediaitem.segment;
        } else
        {
            s6 = s1;
            l = j;
        }
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_2644;
        }
        if (i == 1)
        {
            adms_mediaitem.lastEventOffset = d4;
        }
        if (i > 3 && i != 5 || adms_mediaitem.lastTrackOffset < 0.0D) goto _L8; else goto _L7
_L7:
        j3 = Math.abs(adms_mediaitem.lastTrackOffset - d4) != 0.0001D;
        flag2 = false;
        if (j3 <= 0) goto _L10; else goto _L9
_L9:
        d13 = adms_mediaitem.lastTrackOffset;
        if (d13 > d4)
        {
            d13 = adms_mediaitem.lastEventOffset;
            if (d13 > d4)
            {
                d13 = d4;
            }
        }
        if (isSet(s4))
        {
            as2 = s4.split(",");
        } else
        {
            as2 = null;
        }
        k3 = adms_mediaitem.length != 0.0D;
        flag2 = false;
        if (k3 <= 0) goto _L12; else goto _L11
_L11:
        flag6 = isSet(as2);
        flag2 = false;
        if (!flag6) goto _L12; else goto _L13
_L13:
        l4 = d4 != d13;
        flag2 = false;
        if (l4 < 0) goto _L12; else goto _L14
_L14:
        i5 = 0;
_L18:
        if (i5 >= as2.length) goto _L12; else goto _L15
_L15:
        if (!isSet(as2[i5])) goto _L17; else goto _L16
_L16:
        l5 = Integer.parseInt((new StringBuilder()).append(as2[i5]).toString());
        k5 = l5;
_L19:
        d15 = k5;
_L20:
        if (isSet(d15) && 100D * (d13 / adms_mediaitem.length) < d15 && adms_mediaitem.percent >= d15)
        {
            flag2 = true;
            adms_mediastate.mediaEvent = "MILESTONE";
            j5 = (int)d15;
            adms_mediastate.milestone = j5;
            adms_mediaitem.lastMilestone = j5;
            i5 = as2.length;
        }
        i5++;
          goto _L18
_L4:
        switch (i)
        {
        default:
            adms_mediastate.mediaEvent = "CLOSE";
            break;

        case 1: // '\001'
            adms_mediastate.mediaEvent = "PLAY";
            break;

        case 2: // '\002'
            adms_mediastate.mediaEvent = "STOP";
            break;

        case 3: // '\003'
            adms_mediastate.mediaEvent = "MONITOR";
            break;

        case 4: // '\004'
            adms_mediastate.mediaEvent = "TRACK";
            break;

        case 5: // '\005'
            adms_mediastate.mediaEvent = "COMPLETE";
            break;
        }
        continue; /* Loop/switch isn't completed */
_L17:
        k5 = 0;
          goto _L19
        numberformatexception3;
        d15 = 0.0D;
          goto _L20
_L12:
        if (isSet(s5))
        {
            as3 = s5.split(",");
        } else
        {
            as3 = null;
        }
        if (!isSet(as3) || d4 < d13) goto _L10; else goto _L21
_L21:
        l3 = 0;
_L26:
        if (l3 < as3.length)
        {
            break MISSING_BLOCK_LABEL_932;
        }
          goto _L10
        if (true) goto _L23; else goto _L22
_L22:
        if (!isSet(as3[l3])) goto _L25; else goto _L24
_L24:
        k4 = Integer.parseInt((new StringBuilder()).append(as3[l3]).toString());
        j4 = k4;
_L27:
        d14 = j4;
_L28:
        if (isSet(d14) && d13 < d14 && d4 >= d14)
        {
            flag2 = true;
            adms_mediastate.mediaEvent = "OFFSET_MILESTONE";
            i4 = (int)d14;
            adms_mediastate.offsetMilestone = i4;
            adms_mediaitem.lastOffsetMilestone = i4;
            l3 = as3.length;
        }
        l3++;
          goto _L26
_L25:
        j4 = 0;
          goto _L27
        numberformatexception2;
        d14 = 0.0D;
          goto _L28
_L10:
        s13 = "None";
        s14 = "None";
_L46:
        if (!adms_mediaitem.segmentGenerated && isSet(s6))
        {
            break MISSING_BLOCK_LABEL_1675;
        }
        if (!flag || !isSet(s4) || adms_mediaitem.length <= 0.0D)
        {
            break MISSING_BLOCK_LABEL_1352;
        }
        as1 = (new StringBuilder()).append(s4).append(",100").toString().split(",");
        if (!isSet(as1))
        {
            break MISSING_BLOCK_LABEL_1660;
        }
        d11 = 0.0D;
        j2 = 0;
        s17 = s6;
        k2 = l;
_L31:
        if (j2 >= as1.length)
        {
            break MISSING_BLOCK_LABEL_1652;
        }
        if (!isSet(as1[j2])) goto _L30; else goto _L29
_L29:
        i3 = Integer.parseInt((new StringBuilder()).append(as1[j2]).toString());
        l2 = i3;
_L32:
        d12 = l2;
_L33:
        if (isSet(d12))
        {
            if (adms_mediaitem.percent < d12)
            {
                k2 = j2 + 1;
                stringbuilder2 = new StringBuilder("M:");
                aobj1 = new Object[1];
                aobj1[0] = Double.valueOf(d11);
                stringbuilder3 = stringbuilder2.append(String.format("%1.0f", aobj1)).append("-");
                aobj2 = new Object[1];
                aobj2[0] = Double.valueOf(d12);
                s17 = stringbuilder3.append(String.format("%1.0f", aobj2)).toString();
                j2 = as1.length;
            }
            d11 = d12;
        }
        j2++;
          goto _L31
_L30:
        l2 = 0;
          goto _L32
        numberformatexception1;
        d12 = 0.0D;
          goto _L33
        if (!flag1 || !isSet(s5))
        {
            break MISSING_BLOCK_LABEL_1660;
        }
        if (adms_mediaitem.length > 0.0D)
        {
            as = (new StringBuilder()).append(s5).append(",").append((long)Math.floor(adms_mediaitem.length)).toString().split(",");
        } else
        {
            as = (new StringBuilder()).append(s5).append(",E").toString().split(",");
        }
        if (!isSet(as))
        {
            break MISSING_BLOCK_LABEL_1660;
        }
        d9 = 0.0D;
        k1 = 0;
        if (k1 >= as.length)
        {
            break MISSING_BLOCK_LABEL_1660;
        }
        if (!isSet(as[k1])) goto _L35; else goto _L34
_L34:
        i2 = Integer.parseInt((new StringBuilder()).append(as[k1]).toString());
        l1 = i2;
_L36:
        d10 = l1;
_L37:
        if (isSet(d10) || as[k1].equals("E"))
        {
            if (d4 < d10 || as[k1].equals("E"))
            {
                l = k1 + 1;
                stringbuilder1 = new StringBuilder("O:");
                aobj = new Object[1];
                aobj[0] = Double.valueOf(d9);
                s6 = stringbuilder1.append(String.format("%1.0f", aobj)).append("-").append(as[k1]).toString();
                k1 = as.length;
            }
            d9 = d10;
        }
        k1++;
        break MISSING_BLOCK_LABEL_1430;
_L35:
        l1 = 0;
          goto _L36
        numberformatexception;
        d10 = 0.0D;
          goto _L37
        s6 = s17;
        l = k2;
        if (isSet(s6))
        {
            adms_mediaitem.segmentGenerated = true;
        }
        if (!isSet(s6) && !isSet(adms_mediaitem.segment) || s6.equals(adms_mediaitem.segment)) goto _L39; else goto _L38
_L38:
        adms_mediaitem.updateSegment = true;
        if (!isSet(adms_mediaitem.segment))
        {
            adms_mediaitem.segmentNum = l;
            adms_mediaitem.segment = s6;
        }
        if (adms_mediaitem.lastTrackOffset < 0.0D) goto _L39; else goto _L40
_L40:
        flag4 = true;
_L45:
        if (i >= 2 && adms_mediaitem.lastEventOffset < d4)
        {
            d7 = adms_mediaitem.timePlayed + (d4 - adms_mediaitem.lastEventOffset);
            adms_mediaitem.timePlayed = d7;
            d8 = adms_mediaitem.timePlayedSinceTrack + (d4 - adms_mediaitem.lastEventOffset);
            adms_mediaitem.timePlayedSinceTrack = d8;
        }
        if (i <= 2 || i == 3 && adms_mediaitem.lastEventType == 0)
        {
            stringbuilder = (new StringBuilder()).append(adms_mediaitem.session);
            if (i == 1 || i == 3)
            {
                s15 = "S";
            } else
            {
                s15 = "E";
            }
            s16 = stringbuilder.append(s15).append(Math.floor(d4)).toString();
            adms_mediaitem.session = s16;
            if (i == 3)
            {
                j1 = 1;
            } else
            {
                j1 = i;
            }
            adms_mediaitem.lastEventType = j1;
        }
        if (flag4 || adms_mediaitem.lastTrackOffset < 0.0D || i > 3) goto _L42; else goto _L41
_L41:
        if (d3 > 0.0D)
        {
            d6 = d3;
        } else
        {
            d6 = 0.0D;
        }
        if (d6 <= 0.0D || adms_mediaitem.timePlayedSinceTrack < d6) goto _L42; else goto _L43
_L43:
        flag5 = true;
        adms_mediastate.mediaEvent = "SECONDS";
_L44:
        adms_mediaitem.lastEventTimestamp = d2;
        adms_mediaitem.lastEventOffset = d4;
        s8 = s13;
        flag3 = flag5;
        s7 = s14;
_L47:
        if (i == 0 || i <= 3 && adms_mediaitem.percent >= 100D)
        {
            if (adms_mediaitem.lastEventType != 2)
            {
                s12 = (new StringBuilder()).append(adms_mediaitem.session).append("E").append(Math.floor(d4)).toString();
                adms_mediaitem.session = s12;
            }
            i = 0;
            s7 = "None";
            s8 = "None";
            adms_mediastate.mediaEvent = "CLOSE";
        }
        if (i == 5 || (i == 0 || adms_mediaitem.percent >= 100D) && adms_mediaitem.length > 0.0D && d4 >= adms_mediaitem.length - (double)completeCloseOffsetThreshold)
        {
            adms_mediastate.complete = true;
            adms_mediaitem.complete = true;
            flag3 = true;
        }
        s9 = adms_mediastate.mediaEvent;
        if (s9.equals("MILESTONE"))
        {
            s9 = (new StringBuilder()).append(s9).append("_").append(adms_mediastate.milestone).toString();
        } else
        if (s9.equals("OFFSET_MILESTONE"))
        {
            s9 = (new StringBuilder()).append(s9).append("_").append(adms_mediastate.offsetMilestone).toString();
        }
        if (!adms_mediaitem.firstEventList.containsKey(s9))
        {
            adms_mediastate.eventFirstTime = true;
            adms_mediaitem.firstEventList.put(s9, Integer.valueOf(1));
        } else
        {
            adms_mediastate.eventFirstTime = false;
        }
        adms_mediastate.timePlayed = adms_mediaitem.timePlayed;
        adms_mediastate.segmentNum = adms_mediaitem.segmentNum;
        adms_mediastate.segment = adms_mediaitem.segment;
        adms_mediastate.segmentLength = 0.0D;
        if (_flddelegate != null && i != 4)
        {
            _flddelegate.ADMS_MediaMeasurementMonitor(ADMS_Measurement.sharedInstance(), adms_mediastate);
        }
        if (i == 0)
        {
            list.remove(s);
            adms_mediaitem.stopMonitor();
        }
        if (flag3 && adms_mediaitem.trackCount == k)
        {
            hashtable = new Hashtable();
            hashtable1 = new Hashtable();
            if (!isSet(s7))
            {
                s10 = "";
            } else
            {
                s10 = s7;
            }
            if (!isSet(s8))
            {
                s11 = "";
            } else
            {
                s11 = s8;
            }
            hashtable.put("linkTrackVars", s10);
            hashtable.put("linkTrackEvents", s11);
            buildContextData(hashtable1, hashtable, adms_mediaitem);
            ADMS_Measurement.sharedInstance().track(hashtable1, hashtable);
            if (adms_mediaitem.updateSegment)
            {
                adms_mediaitem.segmentNum = l;
                adms_mediaitem.segment = s6;
                adms_mediaitem.segmentChanged = true;
                adms_mediaitem.updateSegment = false;
            } else
            if (adms_mediaitem.timePlayedSinceTrack > 0.0D)
            {
                adms_mediaitem.segmentChanged = false;
            }
            adms_mediaitem.session = "";
            adms_mediaitem.lastMilestone = 0;
            adms_mediaitem.lastOffsetMilestone = 0;
            d5 = adms_mediaitem.timePlayedSinceTrack - Math.floor(adms_mediaitem.timePlayedSinceTrack);
            adms_mediaitem.timePlayedSinceTrack = d5;
            adms_mediaitem.lastTrackOffset = d4;
            i1 = 1 + adms_mediaitem.trackCount;
            adms_mediaitem.trackCount = i1;
        }
_L2:
        return adms_mediaitem;
_L42:
        flag5 = flag4;
          goto _L44
_L39:
        flag4 = flag2;
          goto _L45
_L8:
        s13 = s3;
        s14 = s2;
          goto _L46
        flag3 = flag2;
        s7 = s2;
        s8 = s3;
          goto _L47
    }

    public void stop(String s, double d)
    {
        playerEvent(s, 2, d, 0, null, -1D, null);
    }

    protected boolean toBoolean(Object obj)
    {
        return ((Boolean)obj).booleanValue();
    }

    protected int toInteger(Object obj)
    {
        return ((Integer)obj).intValue();
    }

    public void track(String s)
    {
        playerEvent(s, 4, -1D, 0, null, -1D, null);
    }
}
