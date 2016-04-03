// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

// Referenced classes of package com.newrelic.com.google.gson.internal:
//            a, b, c

public final class ditions
{

    static final Type a[] = new Type[0];

    static int a(Object obj)
    {
        if (obj != null)
        {
            return obj.hashCode();
        } else
        {
            return 0;
        }
    }

    private static Type a(Type type, Class class1, Class class2)
    {
        Class class3;
        Type type1;
        class3 = class1;
        type1 = type;
_L3:
        if (class2 != class3) goto _L2; else goto _L1
_L1:
        class2 = type1;
_L6:
        return class2;
_L2:
        Class aclass[];
        int i;
        int j;
        if (!class2.isInterface())
        {
            continue; /* Loop/switch isn't completed */
        }
        aclass = class3.getInterfaces();
        i = 0;
        j = aclass.length;
_L4:
label0:
        {
            if (i >= j)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (aclass[i] == class2)
            {
                return class3.getGenericInterfaces()[i];
            }
            if (!class2.isAssignableFrom(aclass[i]))
            {
                break label0;
            }
            Type type3 = class3.getGenericInterfaces()[i];
            class3 = aclass[i];
            type1 = type3;
        }
          goto _L3
        i++;
          goto _L4
        if (class3.isInterface()) goto _L6; else goto _L5
_L5:
        if (class3 == java/lang/Object) goto _L6; else goto _L7
_L7:
        Class class4;
label1:
        {
            class4 = class3.getSuperclass();
            if (class4 == class2)
            {
                return class3.getGenericSuperclass();
            }
            if (!class2.isAssignableFrom(class4))
            {
                break label1;
            }
            Type type2 = class3.getGenericSuperclass();
            class3 = class4;
            type1 = type2;
        }
          goto _L3
        class3 = class4;
          goto _L5
    }

    static void a(Type type)
    {
        boolean flag;
        if (!(type instanceof Class) || !((Class)type).isPrimitive())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ditions.checkArgument(flag);
    }

    public static GenericArrayType arrayOf(Type type)
    {
        return new a(type);
    }

    private static Type b(Type type, Class class1, Class class2)
    {
        ditions.checkArgument(class2.isAssignableFrom(class1));
        return resolve(type, class1, a(type, class1, class2));
    }

    public static Type canonicalize(Type type)
    {
        if (type instanceof Class)
        {
            type = (Class)type;
            if (type.isArray())
            {
                type = new a(canonicalize(((Type) (type.getComponentType()))));
            }
        } else
        {
            if (type instanceof ParameterizedType)
            {
                ParameterizedType parameterizedtype = (ParameterizedType)type;
                return new b(parameterizedtype.getOwnerType(), parameterizedtype.getRawType(), parameterizedtype.getActualTypeArguments());
            }
            if (type instanceof GenericArrayType)
            {
                return new a(((GenericArrayType)type).getGenericComponentType());
            }
            if (type instanceof WildcardType)
            {
                WildcardType wildcardtype = (WildcardType)type;
                return new c(wildcardtype.getUpperBounds(), wildcardtype.getLowerBounds());
            }
        }
        return type;
    }

    public static boolean equals(Type type, Type type1)
    {
        Type type2 = type1;
        Type type3 = type;
        do
        {
            if (type3 == type2)
            {
                return true;
            }
            if (type3 instanceof Class)
            {
                return type3.equals(type2);
            }
            if (type3 instanceof ParameterizedType)
            {
                if (!(type2 instanceof ParameterizedType))
                {
                    return false;
                }
                ParameterizedType parameterizedtype = (ParameterizedType)type3;
                ParameterizedType parameterizedtype1 = (ParameterizedType)type2;
                Type type4 = parameterizedtype.getOwnerType();
                Type type5 = parameterizedtype1.getOwnerType();
                boolean flag;
                if (type4 == type5 || type4 != null && type4.equals(type5))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                return flag && parameterizedtype.getRawType().equals(parameterizedtype1.getRawType()) && Arrays.equals(parameterizedtype.getActualTypeArguments(), parameterizedtype1.getActualTypeArguments());
            }
            if (!(type3 instanceof GenericArrayType))
            {
                break;
            }
            if (!(type2 instanceof GenericArrayType))
            {
                return false;
            }
            GenericArrayType genericarraytype = (GenericArrayType)type3;
            GenericArrayType genericarraytype1 = (GenericArrayType)type2;
            type3 = genericarraytype.getGenericComponentType();
            type2 = genericarraytype1.getGenericComponentType();
        } while (true);
        if (type3 instanceof WildcardType)
        {
            if (!(type2 instanceof WildcardType))
            {
                return false;
            }
            WildcardType wildcardtype = (WildcardType)type3;
            WildcardType wildcardtype1 = (WildcardType)type2;
            return Arrays.equals(wildcardtype.getUpperBounds(), wildcardtype1.getUpperBounds()) && Arrays.equals(wildcardtype.getLowerBounds(), wildcardtype1.getLowerBounds());
        }
        if (type3 instanceof TypeVariable)
        {
            if (!(type2 instanceof TypeVariable))
            {
                return false;
            }
            TypeVariable typevariable = (TypeVariable)type3;
            TypeVariable typevariable1 = (TypeVariable)type2;
            return typevariable.getGenericDeclaration() == typevariable1.getGenericDeclaration() && typevariable.getName().equals(typevariable1.getName());
        } else
        {
            return false;
        }
    }

    public static Type getArrayComponentType(Type type)
    {
        if (type instanceof GenericArrayType)
        {
            return ((GenericArrayType)type).getGenericComponentType();
        } else
        {
            return ((Class)type).getComponentType();
        }
    }

    public static Type getCollectionElementType(Type type, Class class1)
    {
        Type type1 = b(type, class1, java/util/Collection);
        if (type1 instanceof WildcardType)
        {
            type1 = ((WildcardType)type1).getUpperBounds()[0];
        }
        if (type1 instanceof ParameterizedType)
        {
            return ((ParameterizedType)type1).getActualTypeArguments()[0];
        } else
        {
            return java/lang/Object;
        }
    }

    public static Type[] getMapKeyAndValueTypes(Type type, Class class1)
    {
        if (type == java/util/Properties)
        {
            return (new Type[] {
                java/lang/String, java/lang/String
            });
        }
        Type type1 = b(type, class1, java/util/Map);
        if (type1 instanceof ParameterizedType)
        {
            return ((ParameterizedType)type1).getActualTypeArguments();
        } else
        {
            return (new Type[] {
                java/lang/Object, java/lang/Object
            });
        }
    }

    public static Class getRawType(Type type)
    {
        Type type1 = type;
        do
        {
            if (type1 instanceof Class)
            {
                return (Class)type1;
            }
            if (type1 instanceof ParameterizedType)
            {
                Type type2 = ((ParameterizedType)type1).getRawType();
                ditions.checkArgument(type2 instanceof Class);
                return (Class)type2;
            }
            if (type1 instanceof GenericArrayType)
            {
                return Array.newInstance(getRawType(((GenericArrayType)type1).getGenericComponentType()), 0).getClass();
            }
            if (type1 instanceof TypeVariable)
            {
                return java/lang/Object;
            }
            if (!(type1 instanceof WildcardType))
            {
                break;
            }
            type1 = ((WildcardType)type1).getUpperBounds()[0];
        } while (true);
        String s;
        if (type1 == null)
        {
            s = "null";
        } else
        {
            s = type1.getClass().getName();
        }
        throw new IllegalArgumentException((new StringBuilder("Expected a Class, ParameterizedType, or GenericArrayType, but <")).append(type1).append("> is of type ").append(s).toString());
    }

    public static transient ParameterizedType newParameterizedTypeWithOwner(Type type, Type type1, Type atype[])
    {
        return new b(type, type1, atype);
    }

    public static Type resolve(Type type, Class class1, Type type1)
    {
        int i;
        Object obj;
        i = 0;
        obj = type1;
_L11:
        if (!(obj instanceof TypeVariable)) goto _L2; else goto _L1
_L1:
        TypeVariable typevariable;
        int k;
        typevariable = (TypeVariable)obj;
        java.lang.reflect.GenericDeclaration genericdeclaration = typevariable.getGenericDeclaration();
        Class class3;
        Type type10;
        TypeVariable atypevariable[];
        if (genericdeclaration instanceof Class)
        {
            class3 = (Class)genericdeclaration;
        } else
        {
            class3 = null;
        }
        if (class3 == null) goto _L4; else goto _L3
_L3:
        type10 = a(type, class1, class3);
        if (!(type10 instanceof ParameterizedType)) goto _L4; else goto _L5
_L5:
        atypevariable = class3.getTypeParameters();
        k = 0;
_L12:
        if (k >= atypevariable.length) goto _L7; else goto _L6
_L6:
        if (!typevariable.equals(atypevariable[k])) goto _L9; else goto _L8
_L8:
        obj = ((ParameterizedType)type10).getActualTypeArguments()[k];
_L13:
        if (obj != typevariable) goto _L11; else goto _L10
_L10:
        return ((Type) (obj));
_L9:
        k++;
          goto _L12
_L7:
        throw new NoSuchElementException();
_L4:
        obj = typevariable;
          goto _L13
_L2:
        if ((obj instanceof Class) && ((Class)obj).isArray())
        {
            obj = (Class)obj;
            Class class2 = ((Class) (obj)).getComponentType();
            Type type9 = resolve(type, class1, ((Type) (class2)));
            if (class2 != type9)
            {
                return arrayOf(type9);
            }
        } else
        {
label0:
            {
                if (!(obj instanceof GenericArrayType))
                {
                    break label0;
                }
                obj = (GenericArrayType)obj;
                Type type7 = ((GenericArrayType) (obj)).getGenericComponentType();
                Type type8 = resolve(type, class1, type7);
                if (type7 != type8)
                {
                    return arrayOf(type8);
                }
            }
        }
          goto _L10
        Type type5;
        boolean flag;
        Type atype2[];
        if (!(obj instanceof ParameterizedType))
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (ParameterizedType)obj;
        Type type4 = ((ParameterizedType) (obj)).getOwnerType();
        type5 = resolve(type, class1, type4);
        if (type5 != type4)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        atype2 = ((ParameterizedType) (obj)).getActualTypeArguments();
        for (int j = atype2.length; i < j; i++)
        {
            Type type6 = resolve(type, class1, atype2[i]);
            if (type6 == atype2[i])
            {
                continue;
            }
            if (!flag)
            {
                atype2 = (Type[])atype2.clone();
                flag = true;
            }
            atype2[i] = type6;
        }

        if (!flag) goto _L10; else goto _L14
_L14:
        return newParameterizedTypeWithOwner(type5, ((ParameterizedType) (obj)).getRawType(), atype2);
        if (!(obj instanceof WildcardType)) goto _L10; else goto _L15
_L15:
        Type atype[];
        Type atype1[];
        Type type3;
        obj = (WildcardType)obj;
        atype = ((WildcardType) (obj)).getLowerBounds();
        atype1 = ((WildcardType) (obj)).getUpperBounds();
        if (atype.length != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        type3 = resolve(type, class1, atype[0]);
        if (type3 == atype[0]) goto _L10; else goto _L16
_L16:
        return supertypeOf(type3);
        if (atype1.length != 1) goto _L10; else goto _L17
_L17:
        Type type2 = resolve(type, class1, atype1[0]);
        if (type2 == atype1[0]) goto _L10; else goto _L18
_L18:
        return subtypeOf(type2);
          goto _L12
    }

    public static WildcardType subtypeOf(Type type)
    {
        return new c(new Type[] {
            type
        }, a);
    }

    public static WildcardType supertypeOf(Type type)
    {
        return new c(new Type[] {
            java/lang/Object
        }, new Type[] {
            type
        });
    }

    public static String typeToString(Type type)
    {
        if (type instanceof Class)
        {
            return ((Class)type).getName();
        } else
        {
            return type.toString();
        }
    }


    private ditions()
    {
    }
}
