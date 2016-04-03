// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.internal.gi;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.in;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaTrack
{

    public static final int SUBTYPE_CAPTIONS = 2;
    public static final int SUBTYPE_CHAPTERS = 4;
    public static final int SUBTYPE_DESCRIPTIONS = 3;
    public static final int SUBTYPE_METADATA = 5;
    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBTITLES = 1;
    public static final int SUBTYPE_UNKNOWN = -1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;
    private JSONObject AA;
    private long AS;
    private int AT;
    private int AU;
    private String Ar;
    private String At;
    private String Av;
    private String mName;

    MediaTrack(long l, int i)
    {
        clear();
        AS = l;
        if (i <= 0 || i > 3)
        {
            throw new IllegalArgumentException((new StringBuilder("invalid type ")).append(i).toString());
        } else
        {
            AT = i;
            return;
        }
    }

    MediaTrack(JSONObject jsonobject)
    {
        b(jsonobject);
    }

    private void b(JSONObject jsonobject)
    {
        clear();
        AS = jsonobject.getLong("trackId");
        String s = jsonobject.getString("type");
        if ("TEXT".equals(s))
        {
            AT = 1;
        } else
        if ("AUDIO".equals(s))
        {
            AT = 2;
        } else
        if ("VIDEO".equals(s))
        {
            AT = 3;
        } else
        {
            throw new JSONException((new StringBuilder("invalid type: ")).append(s).toString());
        }
        At = jsonobject.optString("trackContentId", null);
        Av = jsonobject.optString("trackContentType", null);
        mName = jsonobject.optString("name", null);
        Ar = jsonobject.optString("language", null);
        if (jsonobject.has("subtype"))
        {
            String s1 = jsonobject.getString("subtype");
            if ("SUBTITLES".equals(s1))
            {
                AU = 1;
            } else
            if ("CAPTIONS".equals(s1))
            {
                AU = 2;
            } else
            if ("DESCRIPTIONS".equals(s1))
            {
                AU = 3;
            } else
            if ("CHAPTERS".equals(s1))
            {
                AU = 4;
            } else
            if ("METADATA".equals(s1))
            {
                AU = 5;
            } else
            {
                throw new JSONException((new StringBuilder("invalid subtype: ")).append(s1).toString());
            }
        } else
        {
            AU = 0;
        }
        AA = jsonobject.optJSONObject("customData");
    }

    private void clear()
    {
        AS = 0L;
        AT = 0;
        At = null;
        mName = null;
        Ar = null;
        AU = -1;
        AA = null;
    }

    final void R(int i)
    {
        if (i < 0 || i > 5)
        {
            throw new IllegalArgumentException((new StringBuilder("invalid subtype ")).append(i).toString());
        }
        if (i != 0 && AT != 1)
        {
            throw new IllegalArgumentException("subtypes are only valid for text tracks");
        } else
        {
            AU = i;
            return;
        }
    }

    public final JSONObject dZ()
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("trackId", AS);
        AT;
        JVM INSTR tableswitch 1 3: default 48
    //                   1 180
    //                   2 192
    //                   3 204;
           goto _L1 _L2 _L3 _L4
_L1:
        if (At != null)
        {
            jsonobject.put("trackContentId", At);
        }
        if (Av != null)
        {
            jsonobject.put("trackContentType", Av);
        }
        if (mName != null)
        {
            jsonobject.put("name", mName);
        }
        if (!TextUtils.isEmpty(Ar))
        {
            jsonobject.put("language", Ar);
        }
        AU;
        JVM INSTR tableswitch 1 5: default 160
    //                   1 216
    //                   2 228
    //                   3 240
    //                   4 252
    //                   5 264;
           goto _L5 _L6 _L7 _L8 _L9 _L10
_L5:
        if (AA == null) goto _L12; else goto _L11
_L11:
        jsonobject.put("customData", AA);
        return jsonobject;
_L2:
        try
        {
            jsonobject.put("type", "TEXT");
        }
        catch (JSONException jsonexception)
        {
            return jsonobject;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        jsonobject.put("type", "AUDIO");
        continue; /* Loop/switch isn't completed */
_L4:
        jsonobject.put("type", "VIDEO");
        continue; /* Loop/switch isn't completed */
_L6:
        jsonobject.put("subtype", "SUBTITLES");
          goto _L5
_L7:
        jsonobject.put("subtype", "CAPTIONS");
          goto _L5
_L8:
        jsonobject.put("subtype", "DESCRIPTIONS");
          goto _L5
_L9:
        jsonobject.put("subtype", "CHAPTERS");
          goto _L5
_L10:
        jsonobject.put("subtype", "METADATA");
          goto _L5
_L12:
        return jsonobject;
        if (true) goto _L1; else goto _L13
_L13:
    }

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = true;
_L4:
        return flag1;
_L2:
        boolean flag = obj instanceof MediaTrack;
        flag1 = false;
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        MediaTrack mediatrack = (MediaTrack)obj;
        boolean flag2;
        boolean flag3;
        int i;
        int j;
        int k;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        int l;
        int i1;
        boolean flag8;
        if (AA == null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (mediatrack.AA == null)
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
        if (AA == null || mediatrack.AA == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag8 = in.d(AA, mediatrack.AA);
        flag1 = false;
        if (!flag8) goto _L4; else goto _L3
_L3:
        i = AS != mediatrack.AS;
        flag1 = false;
        if (i == 0)
        {
            j = AT;
            k = mediatrack.AT;
            flag1 = false;
            if (j == k)
            {
                flag4 = gi.a(At, mediatrack.At);
                flag1 = false;
                if (flag4)
                {
                    flag5 = gi.a(Av, mediatrack.Av);
                    flag1 = false;
                    if (flag5)
                    {
                        flag6 = gi.a(mName, mediatrack.mName);
                        flag1 = false;
                        if (flag6)
                        {
                            flag7 = gi.a(Ar, mediatrack.Ar);
                            flag1 = false;
                            if (flag7)
                            {
                                l = AU;
                                i1 = mediatrack.AU;
                                flag1 = false;
                                if (l == i1)
                                {
                                    return true;
                                }
                            }
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

    public final long getId()
    {
        return AS;
    }

    public final String getLanguage()
    {
        return Ar;
    }

    public final String getName()
    {
        return mName;
    }

    public final int getSubtype()
    {
        return AU;
    }

    public final int getType()
    {
        return AT;
    }

    public final int hashCode()
    {
        Object aobj[] = new Object[8];
        aobj[0] = Long.valueOf(AS);
        aobj[1] = Integer.valueOf(AT);
        aobj[2] = At;
        aobj[3] = Av;
        aobj[4] = mName;
        aobj[5] = Ar;
        aobj[6] = Integer.valueOf(AU);
        aobj[7] = AA;
        return hk.hashCode(aobj);
    }

    public final void setContentId(String s)
    {
        At = s;
    }

    public final void setContentType(String s)
    {
        Av = s;
    }

    final void setCustomData(JSONObject jsonobject)
    {
        AA = jsonobject;
    }

    final void setLanguage(String s)
    {
        Ar = s;
    }

    final void setName(String s)
    {
        mName = s;
    }
}
