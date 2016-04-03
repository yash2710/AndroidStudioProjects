// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.facebook:
//            TokenCachingStrategy, LoggingBehavior

public class SharedPreferencesTokenCachingStrategy extends TokenCachingStrategy
{

    private static final String DEFAULT_CACHE_KEY = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
    private static final String JSON_VALUE = "value";
    private static final String JSON_VALUE_ENUM_TYPE = "enumType";
    private static final String JSON_VALUE_TYPE = "valueType";
    private static final String TAG = com/facebook/SharedPreferencesTokenCachingStrategy.getSimpleName();
    private static final String TYPE_BOOLEAN = "bool";
    private static final String TYPE_BOOLEAN_ARRAY = "bool[]";
    private static final String TYPE_BYTE = "byte";
    private static final String TYPE_BYTE_ARRAY = "byte[]";
    private static final String TYPE_CHAR = "char";
    private static final String TYPE_CHAR_ARRAY = "char[]";
    private static final String TYPE_DOUBLE = "double";
    private static final String TYPE_DOUBLE_ARRAY = "double[]";
    private static final String TYPE_ENUM = "enum";
    private static final String TYPE_FLOAT = "float";
    private static final String TYPE_FLOAT_ARRAY = "float[]";
    private static final String TYPE_INTEGER = "int";
    private static final String TYPE_INTEGER_ARRAY = "int[]";
    private static final String TYPE_LONG = "long";
    private static final String TYPE_LONG_ARRAY = "long[]";
    private static final String TYPE_SHORT = "short";
    private static final String TYPE_SHORT_ARRAY = "short[]";
    private static final String TYPE_STRING = "string";
    private static final String TYPE_STRING_LIST = "stringList";
    private SharedPreferences cache;
    private String cacheKey;

    public SharedPreferencesTokenCachingStrategy(Context context)
    {
        this(context, null);
    }

    public SharedPreferencesTokenCachingStrategy(Context context, String s)
    {
        Validate.notNull(context, "context");
        if (Utility.isNullOrEmpty(s))
        {
            s = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
        }
        cacheKey = s;
        Context context1 = context.getApplicationContext();
        if (context1 != null)
        {
            context = context1;
        }
        cache = context.getSharedPreferences(cacheKey, 0);
    }

    private void deserializeKey(String s, Bundle bundle)
    {
        int i;
        JSONObject jsonobject;
        String s2;
        i = 0;
        String s1 = cache.getString(s, "{}");
        JVM INSTR new #139 <Class JSONObject>;
        jsonobject = JSONObjectInstrumentation.init(s1);
        s2 = jsonobject.getString("valueType");
        if (!s2.equals("bool")) goto _L2; else goto _L1
_L1:
        bundle.putBoolean(s, jsonobject.getBoolean("value"));
_L4:
        return;
_L2:
        if (s2.equals("bool[]"))
        {
            JSONArray jsonarray8 = jsonobject.getJSONArray("value");
            boolean aflag[];
            for (aflag = new boolean[jsonarray8.length()]; i < aflag.length; i++)
            {
                aflag[i] = jsonarray8.getBoolean(i);
            }

            bundle.putBooleanArray(s, aflag);
            return;
        }
        if (s2.equals("byte"))
        {
            bundle.putByte(s, (byte)jsonobject.getInt("value"));
            return;
        }
        if (s2.equals("byte[]"))
        {
            JSONArray jsonarray7 = jsonobject.getJSONArray("value");
            byte abyte0[];
            for (abyte0 = new byte[jsonarray7.length()]; i < abyte0.length; i++)
            {
                abyte0[i] = (byte)jsonarray7.getInt(i);
            }

            bundle.putByteArray(s, abyte0);
            return;
        }
        if (s2.equals("short"))
        {
            bundle.putShort(s, (short)jsonobject.getInt("value"));
            return;
        }
        if (s2.equals("short[]"))
        {
            JSONArray jsonarray6 = jsonobject.getJSONArray("value");
            short aword0[];
            for (aword0 = new short[jsonarray6.length()]; i < aword0.length; i++)
            {
                aword0[i] = (short)jsonarray6.getInt(i);
            }

            bundle.putShortArray(s, aword0);
            return;
        }
        if (s2.equals("int"))
        {
            bundle.putInt(s, jsonobject.getInt("value"));
            return;
        }
        if (s2.equals("int[]"))
        {
            JSONArray jsonarray5 = jsonobject.getJSONArray("value");
            int ai[];
            for (ai = new int[jsonarray5.length()]; i < ai.length; i++)
            {
                ai[i] = jsonarray5.getInt(i);
            }

            bundle.putIntArray(s, ai);
            return;
        }
        if (s2.equals("long"))
        {
            bundle.putLong(s, jsonobject.getLong("value"));
            return;
        }
        if (s2.equals("long[]"))
        {
            JSONArray jsonarray4 = jsonobject.getJSONArray("value");
            long al[];
            for (al = new long[jsonarray4.length()]; i < al.length; i++)
            {
                al[i] = jsonarray4.getLong(i);
            }

            bundle.putLongArray(s, al);
            return;
        }
        if (s2.equals("float"))
        {
            bundle.putFloat(s, (float)jsonobject.getDouble("value"));
            return;
        }
        if (s2.equals("float[]"))
        {
            JSONArray jsonarray3 = jsonobject.getJSONArray("value");
            float af[];
            for (af = new float[jsonarray3.length()]; i < af.length; i++)
            {
                af[i] = (float)jsonarray3.getDouble(i);
            }

            bundle.putFloatArray(s, af);
            return;
        }
        if (s2.equals("double"))
        {
            bundle.putDouble(s, jsonobject.getDouble("value"));
            return;
        }
        if (s2.equals("double[]"))
        {
            JSONArray jsonarray2 = jsonobject.getJSONArray("value");
            double ad[];
            for (ad = new double[jsonarray2.length()]; i < ad.length; i++)
            {
                ad[i] = jsonarray2.getDouble(i);
            }

            bundle.putDoubleArray(s, ad);
            return;
        }
        if (!s2.equals("char"))
        {
            break; /* Loop/switch isn't completed */
        }
        String s5 = jsonobject.getString("value");
        if (s5 != null && s5.length() == 1)
        {
            bundle.putChar(s, s5.charAt(0));
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (s2.equals("char[]"))
        {
            JSONArray jsonarray1 = jsonobject.getJSONArray("value");
            char ac[] = new char[jsonarray1.length()];
            for (int l = 0; l < ac.length; l++)
            {
                String s4 = jsonarray1.getString(l);
                if (s4 != null && s4.length() == 1)
                {
                    ac[l] = s4.charAt(0);
                }
            }

            bundle.putCharArray(s, ac);
            return;
        }
        if (s2.equals("string"))
        {
            bundle.putString(s, jsonobject.getString("value"));
            return;
        }
        if (s2.equals("stringList"))
        {
            JSONArray jsonarray = jsonobject.getJSONArray("value");
            int j = jsonarray.length();
            ArrayList arraylist = new ArrayList(j);
            int k = 0;
            while (k < j) 
            {
                Object obj = jsonarray.get(k);
                String s3;
                if (obj == JSONObject.NULL)
                {
                    s3 = null;
                } else
                {
                    s3 = (String)obj;
                }
                arraylist.add(k, s3);
                k++;
            }
            bundle.putStringArrayList(s, arraylist);
            return;
        }
        if (s2.equals("enum"))
        {
            try
            {
                bundle.putSerializable(s, Enum.valueOf(Class.forName(jsonobject.getString("enumType")), jsonobject.getString("value")));
                return;
            }
            catch (ClassNotFoundException classnotfoundexception)
            {
                return;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                return;
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    private void serializeKey(String s, Bundle bundle, android.content.SharedPreferences.Editor editor)
    {
        int i = 0;
        Object obj = bundle.get(s);
        if (obj != null)
        {
            JSONObject jsonobject = new JSONObject();
            JSONArray jsonarray1;
            String s1;
            if (obj instanceof Byte)
            {
                jsonobject.put("value", ((Byte)obj).intValue());
                jsonarray1 = null;
                s1 = "byte";
            } else
            if (obj instanceof Short)
            {
                jsonobject.put("value", ((Short)obj).intValue());
                s1 = "short";
                jsonarray1 = null;
            } else
            if (obj instanceof Integer)
            {
                jsonobject.put("value", ((Integer)obj).intValue());
                s1 = "int";
                jsonarray1 = null;
            } else
            if (obj instanceof Long)
            {
                jsonobject.put("value", ((Long)obj).longValue());
                s1 = "long";
                jsonarray1 = null;
            } else
            if (obj instanceof Float)
            {
                jsonobject.put("value", ((Float)obj).doubleValue());
                s1 = "float";
                jsonarray1 = null;
            } else
            if (obj instanceof Double)
            {
                jsonobject.put("value", ((Double)obj).doubleValue());
                s1 = "double";
                jsonarray1 = null;
            } else
            if (obj instanceof Boolean)
            {
                jsonobject.put("value", ((Boolean)obj).booleanValue());
                s1 = "bool";
                jsonarray1 = null;
            } else
            if (obj instanceof Character)
            {
                jsonobject.put("value", obj.toString());
                s1 = "char";
                jsonarray1 = null;
            } else
            if (obj instanceof String)
            {
                jsonobject.put("value", (String)obj);
                s1 = "string";
                jsonarray1 = null;
            } else
            if (obj instanceof Enum)
            {
                jsonobject.put("value", obj.toString());
                jsonobject.put("enumType", obj.getClass().getName());
                s1 = "enum";
                jsonarray1 = null;
            } else
            {
                JSONArray jsonarray = new JSONArray();
                if (obj instanceof byte[])
                {
                    s1 = "byte[]";
                    byte abyte0[] = (byte[])obj;
                    for (int i2 = abyte0.length; i < i2; i++)
                    {
                        jsonarray.put(abyte0[i]);
                    }

                    jsonarray1 = jsonarray;
                } else
                if (obj instanceof short[])
                {
                    s1 = "short[]";
                    short aword0[] = (short[])obj;
                    for (int l1 = aword0.length; i < l1; i++)
                    {
                        jsonarray.put(aword0[i]);
                    }

                    jsonarray1 = jsonarray;
                } else
                if (obj instanceof int[])
                {
                    s1 = "int[]";
                    int ai[] = (int[])obj;
                    for (int k1 = ai.length; i < k1; i++)
                    {
                        jsonarray.put(ai[i]);
                    }

                    jsonarray1 = jsonarray;
                } else
                if (obj instanceof long[])
                {
                    s1 = "long[]";
                    long al[] = (long[])obj;
                    for (int j1 = al.length; i < j1; i++)
                    {
                        jsonarray.put(al[i]);
                    }

                    jsonarray1 = jsonarray;
                } else
                if (obj instanceof float[])
                {
                    s1 = "float[]";
                    float af[] = (float[])obj;
                    for (int i1 = af.length; i < i1; i++)
                    {
                        jsonarray.put(af[i]);
                    }

                    jsonarray1 = jsonarray;
                } else
                if (obj instanceof double[])
                {
                    s1 = "double[]";
                    double ad[] = (double[])obj;
                    for (int l = ad.length; i < l; i++)
                    {
                        jsonarray.put(ad[i]);
                    }

                    jsonarray1 = jsonarray;
                } else
                if (obj instanceof boolean[])
                {
                    s1 = "bool[]";
                    boolean aflag[] = (boolean[])obj;
                    for (int k = aflag.length; i < k; i++)
                    {
                        jsonarray.put(aflag[i]);
                    }

                    jsonarray1 = jsonarray;
                } else
                if (obj instanceof char[])
                {
                    s1 = "char[]";
                    char ac[] = (char[])obj;
                    for (int j = ac.length; i < j; i++)
                    {
                        jsonarray.put(String.valueOf(ac[i]));
                    }

                    jsonarray1 = jsonarray;
                } else
                if (obj instanceof List)
                {
                    s1 = "stringList";
                    Object obj1;
                    for (Iterator iterator = ((List)obj).iterator(); iterator.hasNext(); jsonarray.put(obj1))
                    {
                        obj1 = (String)iterator.next();
                        if (obj1 == null)
                        {
                            obj1 = JSONObject.NULL;
                        }
                    }

                    jsonarray1 = jsonarray;
                } else
                {
                    jsonarray1 = null;
                    s1 = null;
                }
            }
            if (s1 != null)
            {
                jsonobject.put("valueType", s1);
                if (jsonarray1 != null)
                {
                    jsonobject.putOpt("value", jsonarray1);
                }
                String s2;
                if (!(jsonobject instanceof JSONObject))
                {
                    s2 = jsonobject.toString();
                } else
                {
                    s2 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
                }
                editor.putString(s, s2);
                return;
            }
        }
    }

    public void clear()
    {
        cache.edit().clear().commit();
    }

    public Bundle load()
    {
        Bundle bundle = new Bundle();
        Iterator iterator = cache.getAll().keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)iterator.next();
            try
            {
                deserializeKey(s, bundle);
            }
            catch (JSONException jsonexception)
            {
                Logger.log(LoggingBehavior.CACHE, 5, TAG, (new StringBuilder("Error reading cached value for key: '")).append(s).append("' -- ").append(jsonexception).toString());
                return null;
            }
        } while (true);
        return bundle;
    }

    public void save(Bundle bundle)
    {
        android.content.SharedPreferences.Editor editor;
        Iterator iterator;
        Validate.notNull(bundle, "bundle");
        editor = cache.edit();
        iterator = bundle.keySet().iterator();
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        String s = (String)iterator.next();
        serializeKey(s, bundle, editor);
        if (true) goto _L4; else goto _L3
_L3:
        JSONException jsonexception;
        jsonexception;
        Logger.log(LoggingBehavior.CACHE, 5, TAG, (new StringBuilder("Error processing value for key: '")).append(s).append("' -- ").append(jsonexception).toString());
_L6:
        return;
_L2:
        if (!editor.commit())
        {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, "SharedPreferences.Editor.commit() was not successful");
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

}
