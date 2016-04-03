// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import com.facebook.internal.CacheableRequestBatch;
import com.facebook.internal.FileLruCache;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

// Referenced classes of package com.facebook:
//            Request, FacebookRequestError, Session, FacebookException, 
//            LoggingBehavior, RequestBatch

public class Response
{

    static final boolean $assertionsDisabled = false;
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    private static final int INVALID_SESSION_FACEBOOK_ERROR_CODE = 190;
    public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
    private static final String RESPONSE_CACHE_TAG = "ResponseCache";
    private static final String RESPONSE_LOG_TAG = "Response";
    private static FileLruCache responseCache;
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final GraphObject graphObject;
    private final GraphObjectList graphObjectList;
    private final boolean isFromCache;
    private final Request request;

    Response(Request request1, HttpURLConnection httpurlconnection, FacebookRequestError facebookrequesterror)
    {
        request = request1;
        connection = httpurlconnection;
        graphObject = null;
        graphObjectList = null;
        isFromCache = false;
        error = facebookrequesterror;
    }

    Response(Request request1, HttpURLConnection httpurlconnection, GraphObject graphobject, boolean flag)
    {
        request = request1;
        connection = httpurlconnection;
        graphObject = graphobject;
        graphObjectList = null;
        isFromCache = flag;
        error = null;
    }

    Response(Request request1, HttpURLConnection httpurlconnection, GraphObjectList graphobjectlist, boolean flag)
    {
        request = request1;
        connection = httpurlconnection;
        graphObject = null;
        graphObjectList = graphobjectlist;
        isFromCache = flag;
        error = null;
    }

    static List constructErrorResponses(List list, HttpURLConnection httpurlconnection, FacebookException facebookexception)
    {
        int i = list.size();
        ArrayList arraylist = new ArrayList(i);
        for (int j = 0; j < i; j++)
        {
            arraylist.add(new Response((Request)list.get(j), httpurlconnection, new FacebookRequestError(httpurlconnection, facebookexception)));
        }

        return arraylist;
    }

    private static Response createResponseFromObject(Request request1, HttpURLConnection httpurlconnection, Object obj, boolean flag, Object obj1)
    {
        if (obj instanceof JSONObject)
        {
            JSONObject jsonobject = (JSONObject)obj;
            FacebookRequestError facebookrequesterror = FacebookRequestError.checkResponseAndCreateError(jsonobject, obj1, httpurlconnection);
            if (facebookrequesterror != null)
            {
                if (facebookrequesterror.getErrorCode() == 190)
                {
                    Session session = request1.getSession();
                    if (session != null)
                    {
                        session.closeAndClearTokenInformation();
                    }
                }
                return new Response(request1, httpurlconnection, facebookrequesterror);
            }
            Object obj2 = Utility.getStringPropertyAsJSON(jsonobject, "body", "FACEBOOK_NON_JSON_RESULT");
            if (obj2 instanceof JSONObject)
            {
                return new Response(request1, httpurlconnection, com.facebook.model.GraphObject.Factory.create((JSONObject)obj2), flag);
            }
            if (obj2 instanceof JSONArray)
            {
                return new Response(request1, httpurlconnection, com.facebook.model.GraphObject.Factory.createList((JSONArray)obj2, com/facebook/model/GraphObject), flag);
            }
            obj = JSONObject.NULL;
        }
        if (obj == JSONObject.NULL)
        {
            return new Response(request1, httpurlconnection, ((GraphObject) (null)), flag);
        } else
        {
            throw new FacebookException((new StringBuilder("Got unexpected object type in response, class: ")).append(obj.getClass().getSimpleName()).toString());
        }
    }

    private static List createResponsesFromObject(HttpURLConnection httpurlconnection, List list, Object obj, boolean flag)
    {
        int i;
        int j;
        ArrayList arraylist;
        i = 0;
        if (!$assertionsDisabled && httpurlconnection == null && !flag)
        {
            throw new AssertionError();
        }
        j = list.size();
        arraylist = new ArrayList(j);
        if (j != 1) goto _L2; else goto _L1
_L1:
        Request request2 = (Request)list.get(0);
        JSONObject jsonobject;
        jsonobject = new JSONObject();
        jsonobject.put("body", obj);
        if (httpurlconnection == null) goto _L4; else goto _L3
_L3:
        int k = httpurlconnection.getResponseCode();
_L5:
        Object obj1;
        jsonobject.put("code", k);
        obj1 = new JSONArray();
        ((JSONArray) (obj1)).put(jsonobject);
_L6:
        if (!(obj1 instanceof JSONArray) || ((JSONArray)obj1).length() != j)
        {
            throw new FacebookException("Unexpected number of results");
        }
        break MISSING_BLOCK_LABEL_228;
_L4:
        k = 200;
          goto _L5
        JSONException jsonexception1;
        jsonexception1;
        arraylist.add(new Response(request2, httpurlconnection, new FacebookRequestError(httpurlconnection, jsonexception1)));
        obj1 = obj;
          goto _L6
        IOException ioexception;
        ioexception;
        arraylist.add(new Response(request2, httpurlconnection, new FacebookRequestError(httpurlconnection, ioexception)));
_L2:
        obj1 = obj;
          goto _L6
        JSONArray jsonarray = (JSONArray)obj1;
        while (i < jsonarray.length()) 
        {
            Request request1 = (Request)list.get(i);
            try
            {
                arraylist.add(createResponseFromObject(request1, httpurlconnection, jsonarray.get(i), flag, obj));
            }
            catch (JSONException jsonexception)
            {
                arraylist.add(new Response(request1, httpurlconnection, new FacebookRequestError(httpurlconnection, jsonexception)));
            }
            catch (FacebookException facebookexception)
            {
                arraylist.add(new Response(request1, httpurlconnection, new FacebookRequestError(httpurlconnection, facebookexception)));
            }
            i++;
        }
        return arraylist;
          goto _L5
    }

    static List createResponsesFromStream(InputStream inputstream, HttpURLConnection httpurlconnection, RequestBatch requestbatch, boolean flag)
    {
        String s = Utility.readStreamToString(inputstream);
        LoggingBehavior loggingbehavior = LoggingBehavior.INCLUDE_RAW_RESPONSES;
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(s.length());
        aobj[1] = s;
        Logger.log(loggingbehavior, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", aobj);
        return createResponsesFromString(s, httpurlconnection, requestbatch, flag);
    }

    static List createResponsesFromString(String s, HttpURLConnection httpurlconnection, RequestBatch requestbatch, boolean flag)
    {
        List list = createResponsesFromObject(httpurlconnection, requestbatch, (new JSONTokener(s)).nextValue(), flag);
        LoggingBehavior loggingbehavior = LoggingBehavior.REQUESTS;
        Object aobj[] = new Object[3];
        aobj[0] = requestbatch.getId();
        aobj[1] = Integer.valueOf(s.length());
        aobj[2] = list;
        Logger.log(loggingbehavior, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", aobj);
        return list;
    }

    static List fromHttpConnection(HttpURLConnection httpurlconnection, RequestBatch requestbatch)
    {
        InputStream inputstream = null;
        if (!(requestbatch instanceof CacheableRequestBatch)) goto _L2; else goto _L1
_L1:
        FileLruCache filelrucache1;
        String s1;
        CacheableRequestBatch cacheablerequestbatch = (CacheableRequestBatch)requestbatch;
        filelrucache1 = getResponseCache();
        s1 = cacheablerequestbatch.getCacheKeyOverride();
        InputStream inputstream2;
        List list5;
        if (Utility.isNullOrEmpty(s1))
        {
            if (requestbatch.size() == 1)
            {
                s1 = requestbatch.get(0).getUrlForSingleRequest();
            } else
            {
                Logger.log(LoggingBehavior.REQUESTS, "ResponseCache", "Not using cache for cacheable request because no key was specified");
            }
        }
        if (cacheablerequestbatch.getForceRoundTrip() || filelrucache1 == null || Utility.isNullOrEmpty(s1)) goto _L4; else goto _L3
_L3:
        inputstream2 = filelrucache1.get(s1);
        inputstream = inputstream2;
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_120;
        }
        list5 = createResponsesFromStream(inputstream, null, requestbatch, true);
        Utility.closeQuietly(inputstream);
        return list5;
        FileLruCache filelrucache;
        Object obj;
        String s;
        Utility.closeQuietly(inputstream);
        filelrucache = filelrucache1;
        String s4 = s1;
        obj = inputstream;
        s = s4;
_L11:
        if (httpurlconnection.getResponseCode() < 400) goto _L6; else goto _L5
_L5:
        obj = httpurlconnection.getErrorStream();
_L8:
        List list4 = createResponsesFromStream(((InputStream) (obj)), httpurlconnection, requestbatch, false);
        Utility.closeQuietly(((java.io.Closeable) (obj)));
        return list4;
        FacebookException facebookexception1;
        facebookexception1;
        Object obj1 = null;
_L9:
        Utility.closeQuietly(((java.io.Closeable) (obj1)));
        s = s1;
        obj = obj1;
        filelrucache = filelrucache1;
        continue; /* Loop/switch isn't completed */
        JSONException jsonexception1;
        jsonexception1;
        Utility.closeQuietly(inputstream);
        filelrucache = filelrucache1;
        String s3 = s1;
        obj = inputstream;
        s = s3;
        continue; /* Loop/switch isn't completed */
        IOException ioexception1;
        ioexception1;
        Utility.closeQuietly(inputstream);
        filelrucache = filelrucache1;
        String s2 = s1;
        obj = inputstream;
        s = s2;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        Utility.closeQuietly(inputstream);
        throw exception1;
_L6:
        obj = httpurlconnection.getInputStream();
        if (filelrucache == null || s == null || obj == null) goto _L8; else goto _L7
_L7:
        InputStream inputstream1 = filelrucache.interceptAndPut(s, ((InputStream) (obj)));
        if (inputstream1 != null)
        {
            obj = inputstream1;
        }
          goto _L8
        FacebookException facebookexception;
        facebookexception;
        List list3;
        Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", new Object[] {
            facebookexception
        });
        list3 = constructErrorResponses(requestbatch, httpurlconnection, facebookexception);
        Utility.closeQuietly(((java.io.Closeable) (obj)));
        return list3;
        JSONException jsonexception;
        jsonexception;
        List list2;
        Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", new Object[] {
            jsonexception
        });
        list2 = constructErrorResponses(requestbatch, httpurlconnection, new FacebookException(jsonexception));
        Utility.closeQuietly(((java.io.Closeable) (obj)));
        return list2;
        IOException ioexception;
        ioexception;
        List list1;
        Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", new Object[] {
            ioexception
        });
        list1 = constructErrorResponses(requestbatch, httpurlconnection, new FacebookException(ioexception));
        Utility.closeQuietly(((java.io.Closeable) (obj)));
        return list1;
        SecurityException securityexception;
        securityexception;
        List list;
        Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", new Object[] {
            securityexception
        });
        list = constructErrorResponses(requestbatch, httpurlconnection, new FacebookException(securityexception));
        Utility.closeQuietly(((java.io.Closeable) (obj)));
        return list;
        Exception exception;
        exception;
        Utility.closeQuietly(((java.io.Closeable) (obj)));
        throw exception;
        FacebookException facebookexception2;
        facebookexception2;
        obj1 = inputstream;
          goto _L9
_L4:
        filelrucache = filelrucache1;
        s = s1;
        obj = null;
        continue; /* Loop/switch isn't completed */
_L2:
        filelrucache = null;
        obj = null;
        s = null;
        if (true) goto _L11; else goto _L10
_L10:
    }

    static FileLruCache getResponseCache()
    {
        if (responseCache == null)
        {
            android.content.Context context = Session.getStaticContext();
            if (context != null)
            {
                responseCache = new FileLruCache(context, "ResponseCache", new com.facebook.internal.FileLruCache.Limits());
            }
        }
        return responseCache;
    }

    public final HttpURLConnection getConnection()
    {
        return connection;
    }

    public final FacebookRequestError getError()
    {
        return error;
    }

    public final GraphObject getGraphObject()
    {
        return graphObject;
    }

    public final GraphObject getGraphObjectAs(Class class1)
    {
        if (graphObject == null)
        {
            return null;
        }
        if (class1 == null)
        {
            throw new NullPointerException("Must pass in a valid interface that extends GraphObject");
        } else
        {
            return graphObject.cast(class1);
        }
    }

    public final GraphObjectList getGraphObjectList()
    {
        return graphObjectList;
    }

    public final GraphObjectList getGraphObjectListAs(Class class1)
    {
        if (graphObjectList == null)
        {
            return null;
        } else
        {
            return graphObjectList.castToListOf(class1);
        }
    }

    public final boolean getIsFromCache()
    {
        return isFromCache;
    }

    public Request getRequest()
    {
        return request;
    }

    public Request getRequestForPagedResults(PagingDirection pagingdirection)
    {
        if (graphObject == null) goto _L2; else goto _L1
_L1:
        PagingInfo paginginfo = ((PagedResults)graphObject.cast(com/facebook/Response$PagedResults)).getPaging();
        if (paginginfo == null) goto _L2; else goto _L3
_L3:
        String s;
        if (pagingdirection == PagingDirection.NEXT)
        {
            s = paginginfo.getNext();
        } else
        {
            s = paginginfo.getPrevious();
        }
_L5:
        while (Utility.isNullOrEmpty(s) || s != null && s.equals(request.getUrlForSingleRequest())) 
        {
            return null;
        }
        Request request1;
        try
        {
            request1 = new Request(request.getSession(), new URL(s));
        }
        catch (MalformedURLException malformedurlexception)
        {
            return null;
        }
        return request1;
_L2:
        s = null;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public String toString()
    {
        Object aobj[] = new Object[1];
        if (connection == null) goto _L2; else goto _L1
_L1:
        int i = connection.getResponseCode();
_L3:
        String s1;
        aobj[0] = Integer.valueOf(i);
        s1 = String.format("%d", aobj);
        String s = s1;
_L4:
        return (new StringBuilder("{Response: ")).append(" responseCode: ").append(s).append(", graphObject: ").append(graphObject).append(", error: ").append(error).append(", isFromCache:").append(isFromCache).append("}").toString();
_L2:
        i = 200;
          goto _L3
        IOException ioexception;
        ioexception;
        s = "unknown";
          goto _L4
    }

    static 
    {
        boolean flag;
        if (!com/facebook/Response.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
    }

    private class PagedResults
        implements GraphObject
    {

        public abstract GraphObjectList getData();

        public abstract PagingInfo getPaging();
    }


    private class PagingDirection extends Enum
    {

        private static final PagingDirection $VALUES[];
        public static final PagingDirection NEXT;
        public static final PagingDirection PREVIOUS;

        public static PagingDirection valueOf(String s)
        {
            return (PagingDirection)Enum.valueOf(com/facebook/Response$PagingDirection, s);
        }

        public static PagingDirection[] values()
        {
            return (PagingDirection[])$VALUES.clone();
        }

        static 
        {
            NEXT = new PagingDirection("NEXT", 0);
            PREVIOUS = new PagingDirection("PREVIOUS", 1);
            PagingDirection apagingdirection[] = new PagingDirection[2];
            apagingdirection[0] = NEXT;
            apagingdirection[1] = PREVIOUS;
            $VALUES = apagingdirection;
        }

        private PagingDirection(String s, int i)
        {
            super(s, i);
        }
    }


    private class PagingInfo
        implements GraphObject
    {

        public abstract String getNext();

        public abstract String getPrevious();
    }

}
