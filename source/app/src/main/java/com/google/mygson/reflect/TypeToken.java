// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class TypeToken
{

    private Class a;
    private Type b;
    private int c;

    protected TypeToken()
    {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof Class)
        {
            throw new RuntimeException("Missing type parameter.");
        } else
        {
            b = com.google.mygson.internal..Gson.Types.canonicalize(((ParameterizedType)type).getActualTypeArguments()[0]);
            a = com.google.mygson.internal..Gson.Types.getRawType(b);
            c = b.hashCode();
            return;
        }
    }

    private TypeToken(Type type)
    {
        b = com.google.mygson.internal..Gson.Types.canonicalize((Type)com.google.mygson.internal..Gson.Preconditions.checkNotNull(type));
        a = com.google.mygson.internal..Gson.Types.getRawType(b);
        c = b.hashCode();
    }

    private static transient AssertionError a(Type type, Class aclass[])
    {
        StringBuilder stringbuilder = new StringBuilder("Unexpected type. Expected one of: ");
        for (int i = 0; i < 3; i++)
        {
            stringbuilder.append(aclass[i].getName()).append(", ");
        }

        stringbuilder.append("but got: ").append(type.getClass().getName()).append(", for type token: ").append(type.toString()).append('.');
        return new AssertionError(stringbuilder.toString());
    }

    private static boolean a(Type type, GenericArrayType genericarraytype)
    {
        Type type1 = genericarraytype.getGenericComponentType();
        if (!(type1 instanceof ParameterizedType)) goto _L2; else goto _L1
_L1:
        if (!(type instanceof GenericArrayType)) goto _L4; else goto _L3
_L3:
        type = ((GenericArrayType)type).getGenericComponentType();
_L5:
        return a(type, (ParameterizedType)type1, ((Map) (new HashMap())));
_L4:
        if (type instanceof Class)
        {
            type = (Class)type;
            while (type.isArray()) 
            {
                type = type.getComponentType();
            }
        }
        if (true) goto _L5; else goto _L2
_L2:
        return true;
    }

    private static boolean a(Type type, ParameterizedType parameterizedtype, Map map)
    {
        Type type1 = type;
_L10:
        if (type1 == null)
        {
            return false;
        }
        if (parameterizedtype.equals(type1))
        {
            return true;
        }
        Class class1 = com.google.mygson.internal..Gson.Types.getRawType(type1);
        ParameterizedType parameterizedtype1;
        Type atype[];
        int i;
        int j;
        Type type2;
        Type atype1[];
        TypeVariable atypevariable[];
        int k;
        boolean flag;
        Type atype2[];
        Type atype3[];
        int l;
        Type type3;
        Type type4;
        boolean flag1;
        Type type5;
        TypeVariable typevariable;
        if (type1 instanceof ParameterizedType)
        {
            parameterizedtype1 = (ParameterizedType)type1;
        } else
        {
            parameterizedtype1 = null;
        }
        if (parameterizedtype1 == null) goto _L2; else goto _L1
_L1:
        atype1 = parameterizedtype1.getActualTypeArguments();
        atypevariable = class1.getTypeParameters();
        for (k = 0; k < atype1.length; k++)
        {
            type5 = atype1[k];
            typevariable = atypevariable[k];
            for (; type5 instanceof TypeVariable; type5 = (Type)map.get(((TypeVariable)type5).getName())) { }
            map.put(typevariable.getName(), type5);
        }

        if (!parameterizedtype1.getRawType().equals(parameterizedtype.getRawType())) goto _L4; else goto _L3
_L3:
        atype2 = parameterizedtype1.getActualTypeArguments();
        atype3 = parameterizedtype.getActualTypeArguments();
        l = 0;
_L8:
        if (l >= atype2.length) goto _L6; else goto _L5
_L5:
        type3 = atype2[l];
        type4 = atype3[l];
        if (type4.equals(type3) || (type3 instanceof TypeVariable) && type4.equals(map.get(((TypeVariable)type3).getName())))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1) goto _L4; else goto _L7
_L7:
        l++;
          goto _L8
_L6:
        flag = true;
_L9:
        if (flag)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L9; else goto _L2
_L2:
        atype = class1.getGenericInterfaces();
        i = atype.length;
        for (j = 0; j < i; j++)
        {
            if (a(atype[j], parameterizedtype, ((Map) (new HashMap(map)))))
            {
                return true;
            }
        }

        type2 = class1.getGenericSuperclass();
        map = new HashMap(map);
        type1 = type2;
          goto _L10
    }

    public static TypeToken get(Class class1)
    {
        return new TypeToken(class1);
    }

    public static TypeToken get(Type type)
    {
        return new TypeToken(type);
    }

    public final boolean equals(Object obj)
    {
        return (obj instanceof TypeToken) && com.google.mygson.internal..Gson.Types.equals(b, ((TypeToken)obj).b);
    }

    public final Class getRawType()
    {
        return a;
    }

    public final Type getType()
    {
        return b;
    }

    public final int hashCode()
    {
        return c;
    }

    public boolean isAssignableFrom(TypeToken typetoken)
    {
        return isAssignableFrom(typetoken.getType());
    }

    public boolean isAssignableFrom(Class class1)
    {
        return isAssignableFrom(((Type) (class1)));
    }

    public boolean isAssignableFrom(Type type)
    {
        if (type == null)
        {
            return false;
        }
        if (b.equals(type))
        {
            return true;
        }
        if (b instanceof Class)
        {
            return a.isAssignableFrom(com.google.mygson.internal..Gson.Types.getRawType(type));
        }
        if (b instanceof ParameterizedType)
        {
            return a(type, (ParameterizedType)b, new HashMap());
        }
        if (b instanceof GenericArrayType)
        {
            return a.isAssignableFrom(com.google.mygson.internal..Gson.Types.getRawType(type)) && a(type, (GenericArrayType)b);
        } else
        {
            throw a(b, new Class[] {
                java/lang/Class, java/lang/reflect/ParameterizedType, java/lang/reflect/GenericArrayType
            });
        }
    }

    public final String toString()
    {
        return com.google.mygson.internal..Gson.Types.typeToString(b);
    }
}
