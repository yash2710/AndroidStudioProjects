// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.internal.gi;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.in;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.cast:
//            MediaMetadata, MediaTrack, TextTrackStyle

public final class MediaInfo
{

    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE;
    private JSONObject AA;
    private final String At;
    private int Au;
    private String Av;
    private MediaMetadata Aw;
    private long Ax;
    private List Ay;
    private TextTrackStyle Az;

    MediaInfo(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("content ID cannot be null or empty");
        } else
        {
            At = s;
            Au = -1;
            return;
        }
    }

    MediaInfo(JSONObject jsonobject)
    {
        int i = 0;
        super();
        At = jsonobject.getString("contentId");
        String s = jsonobject.getString("streamType");
        if ("NONE".equals(s))
        {
            Au = 0;
        } else
        if ("BUFFERED".equals(s))
        {
            Au = 1;
        } else
        if ("LIVE".equals(s))
        {
            Au = 2;
        } else
        {
            Au = -1;
        }
        Av = jsonobject.getString("contentType");
        if (jsonobject.has("metadata"))
        {
            JSONObject jsonobject2 = jsonobject.getJSONObject("metadata");
            Aw = new MediaMetadata(jsonobject2.getInt("metadataType"));
            Aw.b(jsonobject2);
        }
        Ax = gi.b(jsonobject.optDouble("duration", 0.0D));
        if (jsonobject.has("tracks"))
        {
            Ay = new ArrayList();
            for (JSONArray jsonarray = jsonobject.getJSONArray("tracks"); i < jsonarray.length(); i++)
            {
                MediaTrack mediatrack = new MediaTrack(jsonarray.getJSONObject(i));
                Ay.add(mediatrack);
            }

        } else
        {
            Ay = null;
        }
        if (jsonobject.has("textTrackStyle"))
        {
            JSONObject jsonobject1 = jsonobject.getJSONObject("textTrackStyle");
            TextTrackStyle texttrackstyle = new TextTrackStyle();
            texttrackstyle.b(jsonobject1);
            Az = texttrackstyle;
        } else
        {
            Az = null;
        }
        AA = jsonobject.optJSONObject("customData");
    }

    final void a(MediaMetadata mediametadata)
    {
        Aw = mediametadata;
    }

    final void b(List list)
    {
        Ay = list;
    }

    final void dY()
    {
        if (TextUtils.isEmpty(At))
        {
            throw new IllegalArgumentException("content ID cannot be null or empty");
        }
        if (TextUtils.isEmpty(Av))
        {
            throw new IllegalArgumentException("content type cannot be null or empty");
        }
        if (Au == -1)
        {
            throw new IllegalArgumentException("a valid stream type must be specified");
        } else
        {
            return;
        }
    }

    public final JSONObject dZ()
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("contentId", At);
        Au;
        JVM INSTR tableswitch 1 2: default 215
    //                   1 225
    //                   2 232;
           goto _L1 _L2 _L3
_L3:
        break MISSING_BLOCK_LABEL_232;
_L8:
        String s;
        jsonobject.put("streamType", s);
        if (Av != null)
        {
            jsonobject.put("contentType", Av);
        }
        if (Aw != null)
        {
            jsonobject.put("metadata", Aw.dZ());
        }
        jsonobject.put("duration", gi.o(Ax));
        if (Ay == null) goto _L5; else goto _L4
_L4:
        JSONArray jsonarray;
        jsonarray = new JSONArray();
        for (Iterator iterator = Ay.iterator(); iterator.hasNext(); jsonarray.put(((MediaTrack)iterator.next()).dZ())) { }
        jsonobject.put("tracks", jsonarray);
_L5:
        if (Az != null)
        {
            jsonobject.put("textTrackStyle", Az.dZ());
        }
        if (AA == null) goto _L7; else goto _L6
_L6:
        jsonobject.put("customData", AA);
        return jsonobject;
_L1:
        s = "NONE";
          goto _L8
        JSONException jsonexception;
        jsonexception;
_L7:
        return jsonobject;
_L2:
        s = "BUFFERED";
          goto _L8
        s = "LIVE";
          goto _L8
    }

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = true;
_L4:
        return flag1;
_L2:
        boolean flag = obj instanceof MediaInfo;
        flag1 = false;
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        MediaInfo mediainfo = (MediaInfo)obj;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        int i;
        int j;
        boolean flag5;
        boolean flag6;
        int k;
        boolean flag7;
        if (AA == null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (mediainfo.AA == null)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        flag1 = false;
        if (flag2 != flag3)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (AA == null || mediainfo.AA == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag7 = in.d(AA, mediainfo.AA);
        flag1 = false;
        if (!flag7) goto _L4; else goto _L3
_L3:
        flag4 = gi.a(At, mediainfo.At);
        flag1 = false;
        if (flag4)
        {
            i = Au;
            j = mediainfo.Au;
            flag1 = false;
            if (i == j)
            {
                flag5 = gi.a(Av, mediainfo.Av);
                flag1 = false;
                if (flag5)
                {
                    flag6 = gi.a(Aw, mediainfo.Aw);
                    flag1 = false;
                    if (flag6)
                    {
                        k = Ax != mediainfo.Ax;
                        flag1 = false;
                        if (k == 0)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public final String getContentId()
    {
        return At;
    }

    public final String getContentType()
    {
        return Av;
    }

    public final JSONObject getCustomData()
    {
        return AA;
    }

    public final List getMediaTracks()
    {
        return Ay;
    }

    public final MediaMetadata getMetadata()
    {
        return Aw;
    }

    public final long getStreamDuration()
    {
        return Ax;
    }

    public final int getStreamType()
    {
        return Au;
    }

    public final TextTrackStyle getTextTrackStyle()
    {
        return Az;
    }

    public final int hashCode()
    {
        Object aobj[] = new Object[6];
        aobj[0] = At;
        aobj[1] = Integer.valueOf(Au);
        aobj[2] = Av;
        aobj[3] = Aw;
        aobj[4] = Long.valueOf(Ax);
        aobj[5] = String.valueOf(AA);
        return hk.hashCode(aobj);
    }

    final void m(long l)
    {
        if (l < 0L)
        {
            throw new IllegalArgumentException("Stream duration cannot be negative");
        } else
        {
            Ax = l;
            return;
        }
    }

    final void setContentType(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("content type cannot be null or empty");
        } else
        {
            Av = s;
            return;
        }
    }

    final void setCustomData(JSONObject jsonobject)
    {
        AA = jsonobject;
    }

    final void setStreamType(int i)
    {
        if (i < -1 || i > 2)
        {
            throw new IllegalArgumentException("invalid stream type");
        } else
        {
            Au = i;
            return;
        }
    }

    public final void setTextTrackStyle(TextTrackStyle texttrackstyle)
    {
        Az = texttrackstyle;
    }
}
