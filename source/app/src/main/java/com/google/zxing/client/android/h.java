// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.flipkart.logging.FkLogger;
import com.google.zxing.DecodeHintType;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

final class h
{

    private static final String a = com/google/zxing/client/android/h.getSimpleName();
    private static final Pattern b = Pattern.compile(",");

    private h()
    {
    }

    static Map a(Intent intent)
    {
        Bundle bundle = intent.getExtras();
        if (bundle == null || bundle.isEmpty())
        {
            return null;
        }
        EnumMap enummap = new EnumMap(com/google/zxing/DecodeHintType);
        DecodeHintType adecodehinttype[] = DecodeHintType.values();
        int i = adecodehinttype.length;
        int j = 0;
        while (j < i) 
        {
            DecodeHintType decodehinttype = adecodehinttype[j];
            if (decodehinttype == DecodeHintType.CHARACTER_SET || decodehinttype == DecodeHintType.NEED_RESULT_POINT_CALLBACK || decodehinttype == DecodeHintType.POSSIBLE_FORMATS)
            {
                continue;
            }
            String s = decodehinttype.name();
            if (bundle.containsKey(s))
            {
                if (decodehinttype.getValueType().equals(java/lang/Void))
                {
                    enummap.put(decodehinttype, Boolean.TRUE);
                } else
                {
                    Object obj = bundle.get(s);
                    if (decodehinttype.getValueType().isInstance(obj))
                    {
                        enummap.put(decodehinttype, obj);
                    } else
                    {
                        FkLogger.warn(a, (new StringBuilder("Ignoring hint ")).append(decodehinttype).append(" because it is not assignable from ").append(obj).toString());
                    }
                }
            }
            j++;
        }
        FkLogger.info(a, (new StringBuilder("Hints from the Intent: ")).append(enummap).toString());
        return enummap;
    }

    static Map a(Uri uri)
    {
        String s;
        HashMap hashmap;
        s = uri.getEncodedQuery();
        if (s == null || s.length() == 0)
        {
            return null;
        }
        hashmap = new HashMap();
_L6:
        int i;
        for (i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) != '&')
            {
                break MISSING_BLOCK_LABEL_52;
            }
        }

          goto _L1
        int i1;
        int j1;
        i1 = s.indexOf('&', i);
        j1 = s.indexOf('=', i);
        if (i1 >= 0) goto _L3; else goto _L2
_L2:
        EnumMap enummap;
        DecodeHintType decodehinttype;
        String s1;
        DecodeHintType adecodehinttype[];
        int j;
        int k;
        String s5;
        String s6;
        if (j1 < 0)
        {
            s5 = Uri.decode(s.substring(i).replace('+', ' '));
            s6 = "";
        } else
        {
            s5 = Uri.decode(s.substring(i, j1).replace('+', ' '));
            s6 = Uri.decode(s.substring(j1 + 1).replace('+', ' '));
        }
        if (!hashmap.containsKey(s5))
        {
            hashmap.put(s5, s6);
        }
_L1:
        enummap = new EnumMap(com/google/zxing/DecodeHintType);
        adecodehinttype = DecodeHintType.values();
        j = adecodehinttype.length;
        k = 0;
_L5:
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        decodehinttype = adecodehinttype[k];
        if (decodehinttype != DecodeHintType.CHARACTER_SET && decodehinttype != DecodeHintType.NEED_RESULT_POINT_CALLBACK && decodehinttype != DecodeHintType.POSSIBLE_FORMATS)
        {
            s1 = (String)hashmap.get(decodehinttype.name());
            String s2;
            String s3;
            String s4;
            if (s1 != null)
            {
                if (decodehinttype.getValueType().equals(java/lang/Object))
                {
                    enummap.put(decodehinttype, s1);
                } else
                if (decodehinttype.getValueType().equals(java/lang/Void))
                {
                    enummap.put(decodehinttype, Boolean.TRUE);
                } else
                if (decodehinttype.getValueType().equals(java/lang/String))
                {
                    enummap.put(decodehinttype, s1);
                } else
                {
label0:
                    {
                        if (!decodehinttype.getValueType().equals(java/lang/Boolean))
                        {
                            break label0;
                        }
                        if (s1.length() == 0)
                        {
                            enummap.put(decodehinttype, Boolean.TRUE);
                        } else
                        if ("0".equals(s1) || "false".equalsIgnoreCase(s1) || "no".equalsIgnoreCase(s1))
                        {
                            enummap.put(decodehinttype, Boolean.FALSE);
                        } else
                        {
                            enummap.put(decodehinttype, Boolean.TRUE);
                        }
                    }
                }
            }
        }
_L11:
        k++;
        if (true) goto _L5; else goto _L4
_L3:
        if (j1 < 0 || j1 > i1)
        {
            s2 = Uri.decode(s.substring(i, i1).replace('+', ' '));
            if (!hashmap.containsKey(s2))
            {
                hashmap.put(s2, "");
            }
            i = i1 + 1;
        } else
        {
            s3 = Uri.decode(s.substring(i, j1).replace('+', ' '));
            s4 = Uri.decode(s.substring(j1 + 1, i1).replace('+', ' '));
            if (!hashmap.containsKey(s3))
            {
                hashmap.put(s3, s4);
            }
            i = i1 + 1;
        }
          goto _L6
        if (!decodehinttype.getValueType().equals([I)) goto _L8; else goto _L7
_L7:
        String as[];
        int ai[];
        int l;
        if (s1.length() > 0 && s1.charAt(-1 + s1.length()) == ',')
        {
            s1 = s1.substring(0, -1 + s1.length());
        }
        as = b.split(s1);
        ai = new int[as.length];
        l = 0;
_L10:
        if (l >= as.length)
        {
            break; /* Loop/switch isn't completed */
        }
        ai[l] = Integer.parseInt(as[l]);
        l++;
        if (true) goto _L10; else goto _L9
        NumberFormatException numberformatexception;
        numberformatexception;
        int ai1[];
        FkLogger.warn(a, (new StringBuilder("Skipping array of integers hint ")).append(decodehinttype).append(" due to invalid numeric value: '").append(as[l]).append('\'').toString());
        ai1 = null;
_L12:
        if (ai1 != null)
        {
            enummap.put(decodehinttype, ai1);
        }
          goto _L11
_L8:
        FkLogger.warn(a, (new StringBuilder("Unsupported hint type '")).append(decodehinttype).append("' of type ").append(decodehinttype.getValueType()).toString());
          goto _L11
_L4:
        FkLogger.info(a, (new StringBuilder("Hints from the URI: ")).append(enummap).toString());
        return enummap;
_L9:
        ai1 = ai;
          goto _L12
    }

}
