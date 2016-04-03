// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.model;

import com.facebook.FacebookGraphObjectException;
import com.facebook.internal.Utility;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package com.facebook.model:
//            GraphObject, GraphObjectList, PropertyName

public final class GraphObjectListImpl
{

    private static final SimpleDateFormat dateFormats[];
    private static final HashSet verifiedGraphObjectClasses = new HashSet();

    static Object coerceValueToExpectedType(Object obj, Class class1, ParameterizedType parameterizedtype)
    {
        if (obj != null) goto _L2; else goto _L1
_L1:
        if (!Boolean.TYPE.equals(class1)) goto _L4; else goto _L3
_L3:
        obj = Boolean.valueOf(false);
_L6:
        return obj;
_L4:
        if (Character.TYPE.equals(class1))
        {
            return Character.valueOf('\0');
        }
        if (class1.isPrimitive())
        {
            return Integer.valueOf(0);
        } else
        {
            return null;
        }
_L2:
        Class class2 = obj.getClass();
        if (class1.isAssignableFrom(class2) || class1.isPrimitive()) goto _L6; else goto _L5
_L5:
        SimpleDateFormat asimpledateformat[];
        int i;
        int j;
        if (com/facebook/model/GraphObject.isAssignableFrom(class1))
        {
            if (org/json/JSONObject.isAssignableFrom(class2))
            {
                return createGraphObjectProxy(class1, (JSONObject)obj);
            }
            if (com/facebook/model/GraphObject.isAssignableFrom(class2))
            {
                return ((GraphObject)obj).cast(class1);
            } else
            {
                throw new FacebookGraphObjectException((new StringBuilder("Can't create GraphObject from ")).append(class2.getName()).toString());
            }
        }
        if (java/lang/Iterable.equals(class1) || java/util/Collection.equals(class1) || java/util/List.equals(class1) || com/facebook/model/GraphObjectList.equals(class1))
        {
            if (parameterizedtype == null)
            {
                throw new FacebookGraphObjectException((new StringBuilder("can't infer generic type of: ")).append(class1.toString()).toString());
            }
            java.lang.reflect.Type atype[] = parameterizedtype.getActualTypeArguments();
            if (atype == null || atype.length != 1 || !(atype[0] instanceof Class))
            {
                throw new FacebookGraphObjectException("Expect collection properties to be of a type with exactly one generic parameter.");
            }
            Class class3 = (Class)atype[0];
            if (org/json/JSONArray.isAssignableFrom(class2))
            {
                return createList((JSONArray)obj, class3);
            } else
            {
                throw new FacebookGraphObjectException((new StringBuilder("Can't create Collection from ")).append(class2.getName()).toString());
            }
        }
        if (java/lang/String.equals(class1))
        {
            if (java/lang/Double.isAssignableFrom(class2) || java/lang/Float.isAssignableFrom(class2))
            {
                return String.format("%f", new Object[] {
                    obj
                });
            }
            if (java/lang/Number.isAssignableFrom(class2))
            {
                return String.format("%d", new Object[] {
                    obj
                });
            }
            break MISSING_BLOCK_LABEL_442;
        }
        if (!java/util/Date.equals(class1) || !java/lang/String.isAssignableFrom(class2))
        {
            break MISSING_BLOCK_LABEL_442;
        }
        asimpledateformat = dateFormats;
        i = asimpledateformat.length;
        j = 0;
_L9:
        if (j >= i) goto _L8; else goto _L7
_L7:
        SimpleDateFormat simpledateformat = asimpledateformat[j];
        Date date = simpledateformat.parse((String)obj);
        if (date != null)
        {
            return date;
        }
        continue; /* Loop/switch isn't completed */
        ParseException parseexception;
        parseexception;
        j++;
          goto _L9
_L8:
        throw new FacebookGraphObjectException((new StringBuilder("Can't convert type")).append(class2.getName()).append(" to ").append(class1.getName()).toString());
    }

    static String convertCamelCaseToLowercaseWithUnderscores(String s)
    {
        return s.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase(Locale.US);
    }

    public static GraphObject create()
    {
        return create(com/facebook/model/GraphObject);
    }

    public static GraphObject create(Class class1)
    {
        return createGraphObjectProxy(class1, new JSONObject());
    }

    public static GraphObject create(JSONObject jsonobject)
    {
        return create(jsonobject, com/facebook/model/GraphObject);
    }

    public static GraphObject create(JSONObject jsonobject, Class class1)
    {
        return createGraphObjectProxy(class1, jsonobject);
    }

    private static GraphObject createGraphObjectProxy(Class class1, JSONObject jsonobject)
    {
        verifyCanProxyClass(class1);
        Class aclass[] = {
            class1
        };
        GraphObjectProxy graphobjectproxy = new GraphObjectProxy(jsonobject, class1);
        return (GraphObject)Proxy.newProxyInstance(com/facebook/model/GraphObject.getClassLoader(), aclass, graphobjectproxy);
    }

    private static Map createGraphObjectProxyForMap(JSONObject jsonobject)
    {
        Class aclass[] = {
            java/util/Map
        };
        GraphObjectProxy graphobjectproxy = new GraphObjectProxy(jsonobject, java/util/Map);
        return (Map)Proxy.newProxyInstance(com/facebook/model/GraphObject.getClassLoader(), aclass, graphobjectproxy);
    }

    public static GraphObjectList createList(Class class1)
    {
        return createList(new JSONArray(), class1);
    }

    public static GraphObjectList createList(JSONArray jsonarray, Class class1)
    {
        class GraphObjectListImpl extends AbstractList
            implements GraphObjectList
        {

            private final Class itemType;
            private final JSONArray state;

            private void checkIndex(int i)
            {
                if (i < 0 || i >= state.length())
                {
                    throw new IndexOutOfBoundsException();
                } else
                {
                    return;
                }
            }

            private void put(int i, Object obj)
            {
                Object obj1 = GraphObject.Factory.getUnderlyingJSONObject(obj);
                try
                {
                    state.put(i, obj1);
                    return;
                }
                catch (JSONException jsonexception)
                {
                    throw new IllegalArgumentException(jsonexception);
                }
            }

            public final void add(int i, Object obj)
            {
                if (i < 0)
                {
                    throw new IndexOutOfBoundsException();
                }
                if (i < size())
                {
                    throw new UnsupportedOperationException("Only adding items at the end of the list is supported.");
                } else
                {
                    put(i, obj);
                    return;
                }
            }

            public final GraphObjectList castToListOf(Class class2)
            {
                if (com/facebook/model/GraphObject.isAssignableFrom(itemType))
                {
                    if (class2.isAssignableFrom(itemType))
                    {
                        return this;
                    } else
                    {
                        return GraphObject.Factory.createList(state, class2);
                    }
                } else
                {
                    throw new FacebookGraphObjectException((new StringBuilder("Can't cast GraphObjectCollection of non-GraphObject type ")).append(itemType).toString());
                }
            }

            public final void clear()
            {
                throw new UnsupportedOperationException();
            }

            public final boolean equals(Object obj)
            {
                if (obj != null)
                {
                    if (this == obj)
                    {
                        return true;
                    }
                    if (getClass() == obj.getClass())
                    {
                        GraphObjectListImpl graphobjectlistimpl = (GraphObjectListImpl)obj;
                        return state.equals(graphobjectlistimpl.state);
                    }
                }
                return false;
            }

            public final Object get(int i)
            {
                checkIndex(i);
                return GraphObject.Factory.coerceValueToExpectedType(state.opt(i), itemType, null);
            }

            public final JSONArray getInnerJSONArray()
            {
                return state;
            }

            public final int hashCode()
            {
                return state.hashCode();
            }

            public final boolean remove(Object obj)
            {
                throw new UnsupportedOperationException();
            }

            public final boolean removeAll(Collection collection)
            {
                throw new UnsupportedOperationException();
            }

            public final boolean retainAll(Collection collection)
            {
                throw new UnsupportedOperationException();
            }

            public final Object set(int i, Object obj)
            {
                checkIndex(i);
                Object obj1 = get(i);
                put(i, obj);
                return obj1;
            }

            public final int size()
            {
                return state.length();
            }

            public final String toString()
            {
                Object aobj[] = new Object[2];
                aobj[0] = itemType.getSimpleName();
                aobj[1] = state;
                return String.format("GraphObjectList{itemType=%s, state=%s}", aobj);
            }

            public GraphObjectListImpl(JSONArray jsonarray, Class class1)
            {
                Validate.notNull(jsonarray, "state");
                Validate.notNull(class1, "itemType");
                state = jsonarray;
                itemType = class1;
            }
        }

        return new GraphObjectListImpl(jsonarray, class1);
    }

    private static Object getUnderlyingJSONObject(Object obj)
    {
        if (obj == null)
        {
            obj = null;
        } else
        {
            Class class1 = obj.getClass();
            if (com/facebook/model/GraphObject.isAssignableFrom(class1))
            {
                return ((GraphObject)obj).getInnerJSONObject();
            }
            if (com/facebook/model/GraphObjectList.isAssignableFrom(class1))
            {
                return ((GraphObjectList)obj).getInnerJSONArray();
            }
            if (java/lang/Iterable.isAssignableFrom(class1))
            {
                JSONArray jsonarray = new JSONArray();
                for (Iterator iterator = ((Iterable)obj).iterator(); iterator.hasNext();)
                {
                    Object obj1 = iterator.next();
                    if (com/facebook/model/GraphObject.isAssignableFrom(obj1.getClass()))
                    {
                        jsonarray.put(((GraphObject)obj1).getInnerJSONObject());
                    } else
                    {
                        jsonarray.put(obj1);
                    }
                }

                return jsonarray;
            }
        }
        return obj;
    }

    private static boolean hasClassBeenVerified(Class class1)
    {
        com/facebook/model/GraphObject$Factory;
        JVM INSTR monitorenter ;
        boolean flag = verifiedGraphObjectClasses.contains(class1);
        com/facebook/model/GraphObject$Factory;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public static boolean hasSameId(GraphObject graphobject, GraphObject graphobject1)
    {
        if (graphobject != null && graphobject1 != null && graphobject.asMap().containsKey("id") && graphobject1.asMap().containsKey("id"))
        {
            if (graphobject.equals(graphobject1))
            {
                return true;
            }
            Object obj = graphobject.getProperty("id");
            Object obj1 = graphobject1.getProperty("id");
            if (obj != null && obj1 != null && (obj instanceof String) && (obj1 instanceof String))
            {
                return obj.equals(obj1);
            }
        }
        return false;
    }

    private static void recordClassHasBeenVerified(Class class1)
    {
        com/facebook/model/GraphObject$Factory;
        JVM INSTR monitorenter ;
        verifiedGraphObjectClasses.add(class1);
        com/facebook/model/GraphObject$Factory;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static void verifyCanProxyClass(Class class1)
    {
        if (hasClassBeenVerified(class1))
        {
            return;
        }
        if (!class1.isInterface())
        {
            throw new FacebookGraphObjectException((new StringBuilder("Factory can only wrap interfaces, not class: ")).append(class1.getName()).toString());
        }
        Method amethod[] = class1.getMethods();
        int i = amethod.length;
        for (int j = 0; j < i;)
        {
            Method method = amethod[j];
            String s = method.getName();
            int k = method.getParameterTypes().length;
            Class class2 = method.getReturnType();
            boolean flag = method.isAnnotationPresent(com/facebook/model/PropertyName);
            if (method.getDeclaringClass().isAssignableFrom(com/facebook/model/GraphObject) || (k != 1 || class2 != Void.TYPE ? k == 0 && class2 != Void.TYPE && (flag ? !Utility.isNullOrEmpty(((PropertyName)method.getAnnotation(com/facebook/model/PropertyName)).value()) : s.startsWith("get") && s.length() > 3) : flag ? !Utility.isNullOrEmpty(((PropertyName)method.getAnnotation(com/facebook/model/PropertyName)).value()) : s.startsWith("set") && s.length() > 3))
            {
                j++;
            } else
            {
                throw new FacebookGraphObjectException((new StringBuilder("Factory can't proxy method: ")).append(method.toString()).toString());
            }
        }

        recordClassHasBeenVerified(class1);
    }

    static 
    {
        SimpleDateFormat asimpledateformat[] = new SimpleDateFormat[3];
        asimpledateformat[0] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        asimpledateformat[1] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        asimpledateformat[2] = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateFormats = asimpledateformat;
    }




    private GraphObjectListImpl()
    {
    }
}
