// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.model;

import com.facebook.internal.Utility;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.facebook.model:
//            CreateGraphObject, GraphObject, GraphObjectList, PropertyName, 
//            JsonUtil

final class graphObjectClass extends graphObjectClass
{

    private static final String CASTTOMAP_METHOD = "asMap";
    private static final String CAST_METHOD = "cast";
    private static final String CLEAR_METHOD = "clear";
    private static final String CONTAINSKEY_METHOD = "containsKey";
    private static final String CONTAINSVALUE_METHOD = "containsValue";
    private static final String ENTRYSET_METHOD = "entrySet";
    private static final String GETINNERJSONOBJECT_METHOD = "getInnerJSONObject";
    private static final String GETPROPERTYASLIST_METHOD = "getPropertyAsList";
    private static final String GETPROPERTYAS_METHOD = "getPropertyAs";
    private static final String GETPROPERTY_METHOD = "getProperty";
    private static final String GET_METHOD = "get";
    private static final String ISEMPTY_METHOD = "isEmpty";
    private static final String KEYSET_METHOD = "keySet";
    private static final String PUTALL_METHOD = "putAll";
    private static final String PUT_METHOD = "put";
    private static final String REMOVEPROPERTY_METHOD = "removeProperty";
    private static final String REMOVE_METHOD = "remove";
    private static final String SETPROPERTY_METHOD = "setProperty";
    private static final String SIZE_METHOD = "size";
    private static final String VALUES_METHOD = "values";
    private final Class graphObjectClass;

    private Object createGraphObjectsFromParameters(CreateGraphObject creategraphobject, Object obj)
    {
        String s;
label0:
        {
            if (creategraphobject != null && !Utility.isNullOrEmpty(creategraphobject.value()))
            {
                s = creategraphobject.value();
                if (!java/util/List.isAssignableFrom(obj.getClass()))
                {
                    break label0;
                }
                GraphObjectList graphobjectlist = graphObjectClass(com/facebook/model/GraphObject);
                GraphObject graphobject1;
                for (Iterator iterator = ((List)obj).iterator(); iterator.hasNext(); graphobjectlist.add(graphobject1))
                {
                    Object obj1 = iterator.next();
                    graphobject1 = graphObjectClass();
                    graphobject1.setProperty(s, obj1);
                }

                obj = graphobjectlist;
            }
            return obj;
        }
        GraphObject graphobject = graphObjectClass();
        graphobject.setProperty(s, obj);
        return graphobject;
    }

    private final Object proxyGraphObjectGettersAndSetters(Method method, Object aobj[])
    {
        String s = method.getName();
        int i = method.getParameterTypes().length;
        PropertyName propertyname = (PropertyName)method.getAnnotation(com/facebook/model/PropertyName);
        String s1;
        if (propertyname != null)
        {
            s1 = propertyname.value();
        } else
        {
            s1 = oLowercaseWithUnderscores(s.substring(3));
        }
        if (i == 0)
        {
            Object obj1 = ((JSONObject)state).opt(s1);
            Class class1 = method.getReturnType();
            java.lang.reflect.Type type = method.getGenericReturnType();
            Object obj;
            ParameterizedType parameterizedtype;
            if (type instanceof ParameterizedType)
            {
                parameterizedtype = (ParameterizedType)type;
            } else
            {
                parameterizedtype = null;
            }
            return ctedType(obj1, class1, parameterizedtype);
        }
        if (i == 1)
        {
            obj = ctedType(createGraphObjectsFromParameters((CreateGraphObject)method.getAnnotation(com/facebook/model/CreateGraphObject), aobj[0]));
            ((JSONObject)state).putOpt(s1, obj);
            return null;
        } else
        {
            return throwUnexpectedMethodSignature(method);
        }
    }

    private final Object proxyGraphObjectMethods(Object obj, Method method, Object aobj[])
    {
        String s = method.getName();
        if (s.equals("cast"))
        {
            Class class1 = (Class)aobj[0];
            if (class1 != null && class1.isAssignableFrom(graphObjectClass))
            {
                return obj;
            } else
            {
                return graphObjectClass(class1, (JSONObject)state);
            }
        }
        if (s.equals("getInnerJSONObject"))
        {
            return ((state)Proxy.getInvocationHandler(obj)).state;
        }
        if (s.equals("asMap"))
        {
            return state((JSONObject)state);
        }
        if (s.equals("getProperty"))
        {
            return ((JSONObject)state).opt((String)aobj[0]);
        }
        if (s.equals("getPropertyAs"))
        {
            return ctedType(((JSONObject)state).opt((String)aobj[0]), (Class)aobj[1], null);
        }
        class _cls1
            implements ParameterizedType
        {

            final GraphObject.Factory.GraphObjectProxy this$0;
            final Class val$expectedType;

            public Type[] getActualTypeArguments()
            {
                Type atype[] = new Type[1];
                atype[0] = expectedType;
                return atype;
            }

            public Type getOwnerType()
            {
                return null;
            }

            public Type getRawType()
            {
                return com/facebook/model/GraphObjectList;
            }

            _cls1()
            {
                this$0 = GraphObject.Factory.GraphObjectProxy.this;
                expectedType = class1;
                super();
            }
        }

        if (s.equals("getPropertyAsList"))
        {
            return ctedType(((JSONObject)state).opt((String)aobj[0]), com/facebook/model/GraphObjectList, new _cls1());
        }
        if (s.equals("setProperty"))
        {
            return setJSONProperty(aobj);
        }
        if (s.equals("removeProperty"))
        {
            ((JSONObject)state).remove((String)aobj[0]);
            return null;
        } else
        {
            return throwUnexpectedMethodSignature(method);
        }
    }

    private final Object proxyMapMethods(Method method, Object aobj[])
    {
        String s = method.getName();
        if (s.equals("clear"))
        {
            JsonUtil.jsonObjectClear((JSONObject)state);
            return null;
        }
        if (s.equals("containsKey"))
        {
            return Boolean.valueOf(((JSONObject)state).has((String)aobj[0]));
        }
        if (s.equals("containsValue"))
        {
            return Boolean.valueOf(JsonUtil.jsonObjectContainsValue((JSONObject)state, aobj[0]));
        }
        if (s.equals("entrySet"))
        {
            return JsonUtil.jsonObjectEntrySet((JSONObject)state);
        }
        if (s.equals("get"))
        {
            return ((JSONObject)state).opt((String)aobj[0]);
        }
        if (s.equals("isEmpty"))
        {
            boolean flag;
            if (((JSONObject)state).length() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            return Boolean.valueOf(flag);
        }
        if (s.equals("keySet"))
        {
            return JsonUtil.jsonObjectKeySet((JSONObject)state);
        }
        if (s.equals("put"))
        {
            return setJSONProperty(aobj);
        }
        if (s.equals("putAll"))
        {
            Map map;
            if (aobj[0] instanceof Map)
            {
                map = (Map)aobj[0];
            } else
            if (aobj[0] instanceof GraphObject)
            {
                map = ((GraphObject)aobj[0]).asMap();
            } else
            {
                return null;
            }
            JsonUtil.jsonObjectPutAll((JSONObject)state, map);
            return null;
        }
        if (s.equals("remove"))
        {
            ((JSONObject)state).remove((String)aobj[0]);
            return null;
        }
        if (s.equals("size"))
        {
            return Integer.valueOf(((JSONObject)state).length());
        }
        if (s.equals("values"))
        {
            return JsonUtil.jsonObjectValues((JSONObject)state);
        } else
        {
            return throwUnexpectedMethodSignature(method);
        }
    }

    private Object setJSONProperty(Object aobj[])
    {
        String s = (String)aobj[0];
        Object obj = throwUnexpectedMethodSignature(aobj[1]);
        try
        {
            ((JSONObject)state).putOpt(s, obj);
        }
        catch (JSONException jsonexception)
        {
            throw new IllegalArgumentException(jsonexception);
        }
        return null;
    }

    public final Object invoke(Object obj, Method method, Object aobj[])
    {
        Class class1 = method.getDeclaringClass();
        if (class1 == java/lang/Object)
        {
            return proxyObjectMethods(obj, method, aobj);
        }
        if (class1 == java/util/Map)
        {
            return proxyMapMethods(method, aobj);
        }
        if (class1 == com/facebook/model/GraphObject)
        {
            return proxyGraphObjectMethods(obj, method, aobj);
        }
        if (com/facebook/model/GraphObject.isAssignableFrom(class1))
        {
            return proxyGraphObjectGettersAndSetters(method, aobj);
        } else
        {
            return throwUnexpectedMethodSignature(method);
        }
    }

    public final String toString()
    {
        Object aobj[] = new Object[2];
        aobj[0] = graphObjectClass.getSimpleName();
        aobj[1] = state;
        return String.format("GraphObject{graphObjectClass=%s, state=%s}", aobj);
    }

    public _cls1(JSONObject jsonobject, Class class1)
    {
        super(jsonobject);
        graphObjectClass = class1;
    }
}
