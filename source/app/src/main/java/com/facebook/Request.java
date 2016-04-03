// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.internal.Logger;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphMultiResult;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphPlace;
import com.facebook.model.GraphUser;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.facebook:
//            HttpMethod, Session, FacebookException, Response, 
//            RequestBatch, RequestAsyncTask, Settings, AppEventsLogger, 
//            LoggingBehavior

public class Request
{

    private static final String ACCESS_TOKEN_PARAM = "access_token";
    private static final String ATTACHED_FILES_PARAM = "attached_files";
    private static final String ATTACHMENT_FILENAME_PREFIX = "file";
    private static final String BATCH_APP_ID_PARAM = "batch_app_id";
    private static final String BATCH_BODY_PARAM = "body";
    private static final String BATCH_ENTRY_DEPENDS_ON_PARAM = "depends_on";
    private static final String BATCH_ENTRY_NAME_PARAM = "name";
    private static final String BATCH_ENTRY_OMIT_RESPONSE_ON_SUCCESS_PARAM = "omit_response_on_success";
    private static final String BATCH_METHOD_PARAM = "method";
    private static final String BATCH_PARAM = "batch";
    private static final String BATCH_RELATIVE_URL_PARAM = "relative_url";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String FORMAT_JSON = "json";
    private static final String FORMAT_PARAM = "format";
    private static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final int MAXIMUM_BATCH_SIZE = 50;
    private static final String ME = "me";
    private static final String MIGRATION_BUNDLE_PARAM = "migration_bundle";
    private static final String MIME_BOUNDARY = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
    private static final String MY_ACTION_FORMAT = "me/%s";
    private static final String MY_FEED = "me/feed";
    private static final String MY_FRIENDS = "me/friends";
    private static final String MY_OBJECTS_FORMAT = "me/objects/%s";
    private static final String MY_PHOTOS = "me/photos";
    private static final String MY_STAGING_RESOURCES = "me/staging_resources";
    private static final String MY_VIDEOS = "me/videos";
    private static final String OBJECT_PARAM = "object";
    private static final String PICTURE_PARAM = "picture";
    private static final String SDK_ANDROID = "android";
    private static final String SDK_PARAM = "sdk";
    private static final String SEARCH = "search";
    private static final String STAGING_PARAM = "file";
    private static final String USER_AGENT_BASE = "FBAndroidSDK";
    private static final String USER_AGENT_HEADER = "User-Agent";
    private static String defaultBatchApplicationId;
    private static volatile String userAgent;
    private String batchEntryDependsOn;
    private String batchEntryName;
    private boolean batchEntryOmitResultOnSuccess;
    private Callback callback;
    private GraphObject graphObject;
    private String graphPath;
    private HttpMethod httpMethod;
    private String overriddenURL;
    private Bundle parameters;
    private String restMethod;
    private Session session;
    private Object tag;

    public Request()
    {
        this(null, null, null, null, null);
    }

    public Request(Session session1, String s)
    {
        this(session1, s, null, null, null);
    }

    public Request(Session session1, String s, Bundle bundle, HttpMethod httpmethod)
    {
        this(session1, s, bundle, httpmethod, null);
    }

    public Request(Session session1, String s, Bundle bundle, HttpMethod httpmethod, Callback callback1)
    {
        batchEntryOmitResultOnSuccess = true;
        session = session1;
        graphPath = s;
        callback = callback1;
        setHttpMethod(httpmethod);
        if (bundle != null)
        {
            parameters = new Bundle(bundle);
        } else
        {
            parameters = new Bundle();
        }
        if (!parameters.containsKey("migration_bundle"))
        {
            parameters.putString("migration_bundle", "fbsdk:20130708");
        }
    }

    Request(Session session1, URL url)
    {
        batchEntryOmitResultOnSuccess = true;
        session = session1;
        overriddenURL = url.toString();
        setHttpMethod(HttpMethod.GET);
        parameters = new Bundle();
    }

    private void addCommonParameters()
    {
        if (session != null)
        {
            if (!session.isOpened())
            {
                throw new FacebookException("Session provided to a Request in un-opened state.");
            }
            if (!parameters.containsKey("access_token"))
            {
                String s = session.getAccessToken();
                Logger.registerAccessToken(s);
                parameters.putString("access_token", s);
            }
        }
        parameters.putString("sdk", "android");
        parameters.putString("format", "json");
    }

    private String appendParametersToBaseUrl(String s)
    {
        android.net.Uri.Builder builder;
label0:
        {
            builder = (new android.net.Uri.Builder()).encodedPath(s);
            Object obj;
label1:
            do
            {
                String s1;
                for (Iterator iterator = parameters.keySet().iterator(); iterator.hasNext(); builder.appendQueryParameter(s1, parameterToString(obj).toString()))
                {
                    s1 = (String)iterator.next();
                    obj = parameters.get(s1);
                    if (obj == null)
                    {
                        obj = "";
                    }
                    if (!isSupportedParameterType(obj))
                    {
                        continue label1;
                    }
                }

                break label0;
            } while (httpMethod != HttpMethod.GET);
            Object aobj[] = new Object[1];
            aobj[0] = obj.getClass().getSimpleName();
            throw new IllegalArgumentException(String.format("Unsupported parameter type for GET request: %s", aobj));
        }
        return builder.toString();
    }

    static HttpURLConnection createConnection(URL url)
    {
        HttpURLConnection httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection(url.openConnection());
        httpurlconnection.setRequestProperty("User-Agent", getUserAgent());
        httpurlconnection.setRequestProperty("Content-Type", getMimeContentType());
        httpurlconnection.setChunkedStreamingMode(0);
        return httpurlconnection;
    }

    public static Response executeAndWait(Request request)
    {
        List list = executeBatchAndWait(new Request[] {
            request
        });
        if (list == null || list.size() != 1)
        {
            throw new FacebookException("invalid state: expected a single response");
        } else
        {
            return (Response)list.get(0);
        }
    }

    public static List executeBatchAndWait(RequestBatch requestbatch)
    {
        Validate.notEmptyAndContainsNoNulls(requestbatch, "requests");
        HttpURLConnection httpurlconnection;
        try
        {
            httpurlconnection = toHttpConnection(requestbatch);
        }
        catch (Exception exception)
        {
            List list = Response.constructErrorResponses(requestbatch.getRequests(), null, new FacebookException(exception));
            runCallbacks(requestbatch, list);
            return list;
        }
        return executeConnectionAndWait(httpurlconnection, requestbatch);
    }

    public static List executeBatchAndWait(Collection collection)
    {
        return executeBatchAndWait(new RequestBatch(collection));
    }

    public static transient List executeBatchAndWait(Request arequest[])
    {
        Validate.notNull(arequest, "requests");
        return executeBatchAndWait(((Collection) (Arrays.asList(arequest))));
    }

    public static RequestAsyncTask executeBatchAsync(RequestBatch requestbatch)
    {
        Validate.notEmptyAndContainsNoNulls(requestbatch, "requests");
        RequestAsyncTask requestasynctask = new RequestAsyncTask(requestbatch);
        requestasynctask.executeOnSettingsExecutor();
        return requestasynctask;
    }

    public static RequestAsyncTask executeBatchAsync(Collection collection)
    {
        return executeBatchAsync(new RequestBatch(collection));
    }

    public static transient RequestAsyncTask executeBatchAsync(Request arequest[])
    {
        Validate.notNull(arequest, "requests");
        return executeBatchAsync(((Collection) (Arrays.asList(arequest))));
    }

    public static List executeConnectionAndWait(HttpURLConnection httpurlconnection, RequestBatch requestbatch)
    {
        List list = Response.fromHttpConnection(httpurlconnection, requestbatch);
        Utility.disconnectQuietly(httpurlconnection);
        int i = requestbatch.size();
        if (i != list.size())
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(list.size());
            aobj[1] = Integer.valueOf(i);
            throw new FacebookException(String.format("Received %d responses while expecting %d", aobj));
        }
        runCallbacks(requestbatch, list);
        HashSet hashset = new HashSet();
        Iterator iterator = requestbatch.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Request request = (Request)iterator.next();
            if (request.session != null)
            {
                hashset.add(request.session);
            }
        } while (true);
        for (Iterator iterator1 = hashset.iterator(); iterator1.hasNext(); ((Session)iterator1.next()).extendAccessTokenIfNeeded()) { }
        return list;
    }

    public static List executeConnectionAndWait(HttpURLConnection httpurlconnection, Collection collection)
    {
        return executeConnectionAndWait(httpurlconnection, new RequestBatch(collection));
    }

    public static RequestAsyncTask executeConnectionAsync(Handler handler, HttpURLConnection httpurlconnection, RequestBatch requestbatch)
    {
        Validate.notNull(httpurlconnection, "connection");
        RequestAsyncTask requestasynctask = new RequestAsyncTask(httpurlconnection, requestbatch);
        requestbatch.setCallbackHandler(handler);
        requestasynctask.executeOnSettingsExecutor();
        return requestasynctask;
    }

    public static RequestAsyncTask executeConnectionAsync(HttpURLConnection httpurlconnection, RequestBatch requestbatch)
    {
        return executeConnectionAsync(null, httpurlconnection, requestbatch);
    }

    public static RequestAsyncTask executeGraphPathRequestAsync(Session session1, String s, Callback callback1)
    {
        return newGraphPathRequest(session1, s, callback1).executeAsync();
    }

    public static RequestAsyncTask executeMeRequestAsync(Session session1, GraphUserCallback graphusercallback)
    {
        return newMeRequest(session1, graphusercallback).executeAsync();
    }

    public static RequestAsyncTask executeMyFriendsRequestAsync(Session session1, GraphUserListCallback graphuserlistcallback)
    {
        return newMyFriendsRequest(session1, graphuserlistcallback).executeAsync();
    }

    public static RequestAsyncTask executePlacesSearchRequestAsync(Session session1, Location location, int i, int j, String s, GraphPlaceListCallback graphplacelistcallback)
    {
        return newPlacesSearchRequest(session1, location, i, j, s, graphplacelistcallback).executeAsync();
    }

    public static RequestAsyncTask executePostRequestAsync(Session session1, String s, GraphObject graphobject, Callback callback1)
    {
        return newPostRequest(session1, s, graphobject, callback1).executeAsync();
    }

    public static RequestAsyncTask executeRestRequestAsync(Session session1, String s, Bundle bundle, HttpMethod httpmethod)
    {
        return newRestRequest(session1, s, bundle, httpmethod).executeAsync();
    }

    public static RequestAsyncTask executeStatusUpdateRequestAsync(Session session1, String s, Callback callback1)
    {
        return newStatusUpdateRequest(session1, s, callback1).executeAsync();
    }

    public static RequestAsyncTask executeUploadPhotoRequestAsync(Session session1, Bitmap bitmap, Callback callback1)
    {
        return newUploadPhotoRequest(session1, bitmap, callback1).executeAsync();
    }

    public static RequestAsyncTask executeUploadPhotoRequestAsync(Session session1, File file, Callback callback1)
    {
        return newUploadPhotoRequest(session1, file, callback1).executeAsync();
    }

    private static String getBatchAppId(RequestBatch requestbatch)
    {
        if (!Utility.isNullOrEmpty(requestbatch.getBatchApplicationId()))
        {
            return requestbatch.getBatchApplicationId();
        }
        for (Iterator iterator = requestbatch.iterator(); iterator.hasNext();)
        {
            Session session1 = ((Request)iterator.next()).session;
            if (session1 != null)
            {
                return session1.getApplicationId();
            }
        }

        return defaultBatchApplicationId;
    }

    public static final String getDefaultBatchApplicationId()
    {
        return defaultBatchApplicationId;
    }

    private static String getMimeContentType()
    {
        return String.format("multipart/form-data; boundary=%s", new Object[] {
            "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f"
        });
    }

    private static String getUserAgent()
    {
        if (userAgent == null)
        {
            userAgent = String.format("%s.%s", new Object[] {
                "FBAndroidSDK", "3.5.2"
            });
        }
        return userAgent;
    }

    private static boolean isSupportedAttachmentType(Object obj)
    {
        return (obj instanceof Bitmap) || (obj instanceof byte[]) || (obj instanceof ParcelFileDescriptor) || (obj instanceof ParcelFileDescriptorWithMimeType);
    }

    private static boolean isSupportedParameterType(Object obj)
    {
        return (obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Number) || (obj instanceof Date);
    }

    public static Request newCustomAudienceThirdPartyIdRequest(Session session1, Context context, Callback callback1)
    {
        return newCustomAudienceThirdPartyIdRequest(session1, context, null, callback1);
    }

    public static Request newCustomAudienceThirdPartyIdRequest(Session session1, Context context, String s, Callback callback1)
    {
        Session session2;
        String s1;
        Bundle bundle;
        if (session1 == null)
        {
            session2 = Session.getActiveSession();
        } else
        {
            session2 = session1;
        }
        if (session2 != null && !session2.isOpened())
        {
            session2 = null;
        }
        if (s == null)
        {
            if (session2 != null)
            {
                s = session2.getApplicationId();
            } else
            {
                s = Utility.getMetadataApplicationId(context);
            }
        }
        if (s == null)
        {
            throw new FacebookException("Facebook App ID cannot be determined");
        }
        s1 = (new StringBuilder()).append(s).append("/custom_audience_third_party_id").toString();
        bundle = new Bundle();
        if (session2 == null)
        {
            String s2 = Settings.getAttributionId(context.getContentResolver());
            if (s2 != null)
            {
                bundle.putString("udid", s2);
            }
        }
        if (AppEventsLogger.getLimitEventUsage(context))
        {
            bundle.putString("limit_event_usage", "1");
        }
        return new Request(session2, s1, bundle, HttpMethod.GET, callback1);
    }

    public static Request newDeleteObjectRequest(Session session1, String s, Callback callback1)
    {
        return new Request(session1, s, null, HttpMethod.DELETE, callback1);
    }

    public static Request newGraphPathRequest(Session session1, String s, Callback callback1)
    {
        return new Request(session1, s, null, null, callback1);
    }

    public static Request newMeRequest(Session session1, final GraphUserCallback callback)
    {
        return new Request(session1, "me", null, null, new _cls1());
    }

    public static Request newMyFriendsRequest(Session session1, final GraphUserListCallback callback)
    {
        return new Request(session1, "me/friends", null, null, new _cls2());
    }

    public static Request newPlacesSearchRequest(Session session1, Location location, int i, int j, String s, final GraphPlaceListCallback callback)
    {
        if (location == null && Utility.isNullOrEmpty(s))
        {
            throw new FacebookException("Either location or searchText must be specified.");
        }
        Bundle bundle = new Bundle(5);
        bundle.putString("type", "place");
        bundle.putInt("limit", j);
        if (location != null)
        {
            Locale locale = Locale.US;
            Object aobj[] = new Object[2];
            aobj[0] = Double.valueOf(location.getLatitude());
            aobj[1] = Double.valueOf(location.getLongitude());
            bundle.putString("center", String.format(locale, "%f,%f", aobj));
            bundle.putInt("distance", i);
        }
        if (!Utility.isNullOrEmpty(s))
        {
            bundle.putString("q", s);
        }
        _cls3 _lcls3 = new _cls3();
        return new Request(session1, "search", bundle, HttpMethod.GET, _lcls3);
    }

    public static Request newPostOpenGraphActionRequest(Session session1, OpenGraphAction opengraphaction, Callback callback1)
    {
        if (opengraphaction == null)
        {
            throw new FacebookException("openGraphAction cannot be null");
        }
        if (Utility.isNullOrEmpty(opengraphaction.getType()))
        {
            throw new FacebookException("openGraphAction must have non-null 'type' property");
        } else
        {
            Object aobj[] = new Object[1];
            aobj[0] = opengraphaction.getType();
            return newPostRequest(session1, String.format("me/%s", aobj), opengraphaction, callback1);
        }
    }

    public static Request newPostOpenGraphObjectRequest(Session session1, OpenGraphObject opengraphobject, Callback callback1)
    {
        if (opengraphobject == null)
        {
            throw new FacebookException("openGraphObject cannot be null");
        }
        if (Utility.isNullOrEmpty(opengraphobject.getType()))
        {
            throw new FacebookException("openGraphObject must have non-null 'type' property");
        }
        if (Utility.isNullOrEmpty(opengraphobject.getTitle()))
        {
            throw new FacebookException("openGraphObject must have non-null 'title' property");
        }
        Object aobj[] = new Object[1];
        aobj[0] = opengraphobject.getType();
        String s = String.format("me/objects/%s", aobj);
        Bundle bundle = new Bundle();
        JSONObject jsonobject = opengraphobject.getInnerJSONObject();
        String s1;
        if (!(jsonobject instanceof JSONObject))
        {
            s1 = jsonobject.toString();
        } else
        {
            s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        }
        bundle.putString("object", s1);
        return new Request(session1, s, bundle, HttpMethod.POST, callback1);
    }

    public static Request newPostOpenGraphObjectRequest(Session session1, String s, String s1, String s2, String s3, String s4, GraphObject graphobject, Callback callback1)
    {
        OpenGraphObject opengraphobject = com.facebook.model.OpenGraphObject.Factory.createForPost(com/facebook/model/OpenGraphObject, s, s1, s2, s3, s4);
        if (graphobject != null)
        {
            opengraphobject.setData(graphobject);
        }
        return newPostOpenGraphObjectRequest(session1, opengraphobject, callback1);
    }

    public static Request newPostRequest(Session session1, String s, GraphObject graphobject, Callback callback1)
    {
        Request request = new Request(session1, s, null, HttpMethod.POST, callback1);
        request.setGraphObject(graphobject);
        return request;
    }

    public static Request newRestRequest(Session session1, String s, Bundle bundle, HttpMethod httpmethod)
    {
        Request request = new Request(session1, null, bundle, httpmethod);
        request.setRestMethod(s);
        return request;
    }

    public static Request newStatusUpdateRequest(Session session1, String s, Callback callback1)
    {
        return newStatusUpdateRequest(session1, s, ((String) (null)), null, callback1);
    }

    public static Request newStatusUpdateRequest(Session session1, String s, GraphPlace graphplace, List list, Callback callback1)
    {
        ArrayList arraylist;
        if (list != null)
        {
            arraylist = new ArrayList(list.size());
            for (Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(((GraphUser)iterator.next()).getId())) { }
        } else
        {
            arraylist = null;
        }
        String s1;
        if (graphplace == null)
        {
            s1 = null;
        } else
        {
            s1 = graphplace.getId();
        }
        return newStatusUpdateRequest(session1, s, s1, ((List) (arraylist)), callback1);
    }

    private static Request newStatusUpdateRequest(Session session1, String s, String s1, List list, Callback callback1)
    {
        Bundle bundle = new Bundle();
        bundle.putString("message", s);
        if (s1 != null)
        {
            bundle.putString("place", s1);
        }
        if (list != null && list.size() > 0)
        {
            bundle.putString("tags", TextUtils.join(",", list));
        }
        return new Request(session1, "me/feed", bundle, HttpMethod.POST, callback1);
    }

    public static Request newUpdateOpenGraphObjectRequest(Session session1, OpenGraphObject opengraphobject, Callback callback1)
    {
        if (opengraphobject == null)
        {
            throw new FacebookException("openGraphObject cannot be null");
        }
        String s = opengraphobject.getId();
        if (s == null)
        {
            throw new FacebookException("openGraphObject must have an id");
        }
        Bundle bundle = new Bundle();
        JSONObject jsonobject = opengraphobject.getInnerJSONObject();
        String s1;
        if (!(jsonobject instanceof JSONObject))
        {
            s1 = jsonobject.toString();
        } else
        {
            s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        }
        bundle.putString("object", s1);
        return new Request(session1, s, bundle, HttpMethod.POST, callback1);
    }

    public static Request newUpdateOpenGraphObjectRequest(Session session1, String s, String s1, String s2, String s3, String s4, GraphObject graphobject, Callback callback1)
    {
        OpenGraphObject opengraphobject = com.facebook.model.OpenGraphObject.Factory.createForPost(com/facebook/model/OpenGraphObject, null, s1, s2, s3, s4);
        opengraphobject.setId(s);
        opengraphobject.setData(graphobject);
        return newUpdateOpenGraphObjectRequest(session1, opengraphobject, callback1);
    }

    public static Request newUploadPhotoRequest(Session session1, Bitmap bitmap, Callback callback1)
    {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("picture", bitmap);
        return new Request(session1, "me/photos", bundle, HttpMethod.POST, callback1);
    }

    public static Request newUploadPhotoRequest(Session session1, File file, Callback callback1)
    {
        ParcelFileDescriptor parcelfiledescriptor = ParcelFileDescriptor.open(file, 0x10000000);
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("picture", parcelfiledescriptor);
        return new Request(session1, "me/photos", bundle, HttpMethod.POST, callback1);
    }

    public static Request newUploadStagingResourceWithImageRequest(Session session1, Bitmap bitmap, Callback callback1)
    {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", bitmap);
        return new Request(session1, "me/staging_resources", bundle, HttpMethod.POST, callback1);
    }

    public static Request newUploadStagingResourceWithImageRequest(Session session1, File file, Callback callback1)
    {
        ParcelFileDescriptorWithMimeType parcelfiledescriptorwithmimetype = new ParcelFileDescriptorWithMimeType(ParcelFileDescriptor.open(file, 0x10000000), "image/png");
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", parcelfiledescriptorwithmimetype);
        return new Request(session1, "me/staging_resources", bundle, HttpMethod.POST, callback1);
    }

    public static Request newUploadVideoRequest(Session session1, File file, Callback callback1)
    {
        ParcelFileDescriptor parcelfiledescriptor = ParcelFileDescriptor.open(file, 0x10000000);
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(file.getName(), parcelfiledescriptor);
        return new Request(session1, "me/videos", bundle, HttpMethod.POST, callback1);
    }

    private static String parameterToString(Object obj)
    {
        if (obj instanceof String)
        {
            return (String)obj;
        }
        if ((obj instanceof Boolean) || (obj instanceof Number))
        {
            return obj.toString();
        }
        if (obj instanceof Date)
        {
            return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)).format(obj);
        } else
        {
            throw new IllegalArgumentException("Unsupported parameter type.");
        }
    }

    private static void processGraphObject(GraphObject graphobject, String s, KeyValueSerializer keyvalueserializer)
    {
        boolean flag1;
        if (s.startsWith("me/") || s.startsWith("/me/"))
        {
            int i = s.indexOf(":");
            int j = s.indexOf("?");
            boolean flag;
            Iterator iterator;
            java.util.Map.Entry entry;
            if (i > 3 && (j == -1 || i < j))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        iterator = graphobject.asMap().entrySet().iterator();
        while (iterator.hasNext()) 
        {
            entry = (java.util.Map.Entry)iterator.next();
            boolean flag2;
            if (flag1 && ((String)entry.getKey()).equalsIgnoreCase("image"))
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            processGraphObjectProperty((String)entry.getKey(), entry.getValue(), keyvalueserializer, flag2);
        }
    }

    private static void processGraphObjectProperty(String s, Object obj, KeyValueSerializer keyvalueserializer, boolean flag)
    {
        Class class1 = obj.getClass();
        Class class2;
        Object obj1;
        Date date;
        JSONArray jsonarray;
        int i;
        int j;
        Object aobj[];
        JSONObject jsonobject;
        Iterator iterator;
        String s1;
        if (com/facebook/model/GraphObject.isAssignableFrom(class1))
        {
            JSONObject jsonobject1 = ((GraphObject)obj).getInnerJSONObject();
            class2 = jsonobject1.getClass();
            obj1 = jsonobject1;
        } else
        if (com/facebook/model/GraphObjectList.isAssignableFrom(class1))
        {
            JSONArray jsonarray1 = ((GraphObjectList)obj).getInnerJSONArray();
            class2 = jsonarray1.getClass();
            obj1 = jsonarray1;
        } else
        {
            class2 = class1;
            obj1 = obj;
        }
        if (!org/json/JSONObject.isAssignableFrom(class2)) goto _L2; else goto _L1
_L1:
        jsonobject = (JSONObject)obj1;
        if (!flag) goto _L4; else goto _L3
_L3:
        for (iterator = jsonobject.keys(); iterator.hasNext(); processGraphObjectProperty(String.format("%s[%s]", new Object[] {
    s, s1
}), jsonobject.opt(s1), keyvalueserializer, flag))
        {
            s1 = (String)iterator.next();
        }

          goto _L5
_L4:
        if (!jsonobject.has("id")) goto _L7; else goto _L6
_L6:
        processGraphObjectProperty(s, jsonobject.optString("id"), keyvalueserializer, flag);
_L5:
        return;
_L7:
        if (jsonobject.has("url"))
        {
            processGraphObjectProperty(s, jsonobject.optString("url"), keyvalueserializer, flag);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (!org/json/JSONArray.isAssignableFrom(class2))
        {
            break; /* Loop/switch isn't completed */
        }
        jsonarray = (JSONArray)obj1;
        i = jsonarray.length();
        j = 0;
        while (j < i) 
        {
            aobj = new Object[2];
            aobj[0] = s;
            aobj[1] = Integer.valueOf(j);
            processGraphObjectProperty(String.format("%s[%d]", aobj), jsonarray.opt(j), keyvalueserializer, flag);
            j++;
        }
        if (true) goto _L5; else goto _L8
_L8:
        if (java/lang/String.isAssignableFrom(class2) || java/lang/Number.isAssignableFrom(class2) || java/lang/Boolean.isAssignableFrom(class2))
        {
            keyvalueserializer.writeString(s, obj1.toString());
            return;
        }
        if (java/util/Date.isAssignableFrom(class2))
        {
            date = (Date)obj1;
            keyvalueserializer.writeString(s, (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)).format(date));
            return;
        }
        if (true) goto _L5; else goto _L9
_L9:
    }

    static void runCallbacks(final RequestBatch requests, List list)
    {
        _cls4 _lcls4;
        Handler handler;
label0:
        {
            int i = requests.size();
            final ArrayList callbacks = new ArrayList();
            for (int j = 0; j < i; j++)
            {
                Request request = requests.get(j);
                if (request.callback != null)
                {
                    callbacks.add(new Pair(request.callback, list.get(j)));
                }
            }

            if (callbacks.size() > 0)
            {
                _lcls4 = new _cls4();
                handler = requests.getCallbackHandler();
                if (handler != null)
                {
                    break label0;
                }
                _lcls4.run();
            }
            return;
        }
        handler.post(_lcls4);
    }

    private static void serializeAttachments(Bundle bundle, Serializer serializer)
    {
        Iterator iterator = bundle.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)iterator.next();
            Object obj = bundle.get(s);
            if (isSupportedAttachmentType(obj))
            {
                serializer.writeObject(s, obj);
            }
        } while (true);
    }

    private static void serializeParameters(Bundle bundle, Serializer serializer)
    {
        Iterator iterator = bundle.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)iterator.next();
            Object obj = bundle.get(s);
            if (isSupportedParameterType(obj))
            {
                serializer.writeObject(s, obj);
            }
        } while (true);
    }

    private static void serializeRequestsAsJSON(Serializer serializer, Collection collection, Bundle bundle)
    {
        JSONArray jsonarray = new JSONArray();
        for (Iterator iterator = collection.iterator(); iterator.hasNext(); ((Request)iterator.next()).serializeToBatch(jsonarray, bundle)) { }
        String s;
        if (!(jsonarray instanceof JSONArray))
        {
            s = jsonarray.toString();
        } else
        {
            s = JSONArrayInstrumentation.toString((JSONArray)jsonarray);
        }
        serializer.writeString("batch", s);
    }

    private void serializeToBatch(JSONArray jsonarray, Bundle bundle)
    {
        JSONObject jsonobject = new JSONObject();
        if (batchEntryName != null)
        {
            jsonobject.put("name", batchEntryName);
            jsonobject.put("omit_response_on_success", batchEntryOmitResultOnSuccess);
        }
        if (batchEntryDependsOn != null)
        {
            jsonobject.put("depends_on", batchEntryDependsOn);
        }
        String s = getUrlForBatchedRequest();
        jsonobject.put("relative_url", s);
        jsonobject.put("method", httpMethod);
        if (session != null)
        {
            Logger.registerAccessToken(session.getAccessToken());
        }
        ArrayList arraylist = new ArrayList();
        Iterator iterator = parameters.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s1 = (String)iterator.next();
            Object obj = parameters.get(s1);
            if (isSupportedAttachmentType(obj))
            {
                Object aobj[] = new Object[2];
                aobj[0] = "file";
                aobj[1] = Integer.valueOf(bundle.size());
                String s2 = String.format("%s%d", aobj);
                arraylist.add(s2);
                Utility.putObjectInBundle(bundle, s2, obj);
            }
        } while (true);
        if (!arraylist.isEmpty())
        {
            jsonobject.put("attached_files", TextUtils.join(",", arraylist));
        }
        if (graphObject != null)
        {
            final ArrayList keysAndValues = new ArrayList();
            processGraphObject(graphObject, s, new _cls5());
            jsonobject.put("body", TextUtils.join("&", keysAndValues));
        }
        jsonarray.put(jsonobject);
    }

    static final void serializeToUrlConnection(RequestBatch requestbatch, HttpURLConnection httpurlconnection)
    {
        Logger logger;
        int i;
        URL url;
        BufferedOutputStream bufferedoutputstream;
        logger = new Logger(LoggingBehavior.REQUESTS, "Request");
        i = requestbatch.size();
        HttpMethod httpmethod;
        boolean flag;
        if (i == 1)
        {
            httpmethod = requestbatch.get(0).httpMethod;
        } else
        {
            httpmethod = HttpMethod.POST;
        }
        httpurlconnection.setRequestMethod(httpmethod.name());
        url = httpurlconnection.getURL();
        logger.append("Request:\n");
        logger.appendKeyValue("Id", requestbatch.getId());
        logger.appendKeyValue("URL", url);
        logger.appendKeyValue("Method", httpurlconnection.getRequestMethod());
        logger.appendKeyValue("User-Agent", httpurlconnection.getRequestProperty("User-Agent"));
        logger.appendKeyValue("Content-Type", httpurlconnection.getRequestProperty("Content-Type"));
        httpurlconnection.setConnectTimeout(requestbatch.getTimeout());
        httpurlconnection.setReadTimeout(requestbatch.getTimeout());
        if (httpmethod == HttpMethod.POST)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            logger.log();
            return;
        }
        httpurlconnection.setDoOutput(true);
        bufferedoutputstream = new BufferedOutputStream(httpurlconnection.getOutputStream());
        Serializer serializer = new Serializer(bufferedoutputstream, logger);
        if (i != 1) goto _L2; else goto _L1
_L1:
        Request request = requestbatch.get(0);
        logger.append("  Parameters:\n");
        serializeParameters(request.parameters, serializer);
        logger.append("  Attachments:\n");
        serializeAttachments(request.parameters, serializer);
        if (request.graphObject != null)
        {
            processGraphObject(request.graphObject, url.getPath(), serializer);
        }
_L3:
        bufferedoutputstream.close();
        logger.log();
        return;
_L2:
        String s;
        s = getBatchAppId(requestbatch);
        if (Utility.isNullOrEmpty(s))
        {
            throw new FacebookException("At least one request in a batch must have an open Session, or a default app ID must be specified.");
        }
        break MISSING_BLOCK_LABEL_306;
        Exception exception;
        exception;
        bufferedoutputstream.close();
        throw exception;
        serializer.writeString("batch_app_id", s);
        Bundle bundle = new Bundle();
        serializeRequestsAsJSON(serializer, requestbatch, bundle);
        logger.append("  Attachments:\n");
        serializeAttachments(bundle, serializer);
          goto _L3
    }

    public static final void setDefaultBatchApplicationId(String s)
    {
        defaultBatchApplicationId = s;
    }

    public static HttpURLConnection toHttpConnection(RequestBatch requestbatch)
    {
        for (Iterator iterator = requestbatch.iterator(); iterator.hasNext(); ((Request)iterator.next()).validate()) { }
        URL url;
        if (requestbatch.size() != 1)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        url = new URL(requestbatch.get(0).getUrlForSingleRequest());
_L1:
        MalformedURLException malformedurlexception;
        HttpURLConnection httpurlconnection;
        try
        {
            httpurlconnection = createConnection(url);
            serializeToUrlConnection(requestbatch, httpurlconnection);
        }
        catch (IOException ioexception)
        {
            throw new FacebookException("could not construct request body", ioexception);
        }
        catch (JSONException jsonexception)
        {
            throw new FacebookException("could not construct request body", jsonexception);
        }
        return httpurlconnection;
        try
        {
            url = new URL(ServerProtocol.getGraphUrlBase());
        }
        // Misplaced declaration of an exception variable
        catch (MalformedURLException malformedurlexception)
        {
            throw new FacebookException("could not construct URL for request", malformedurlexception);
        }
          goto _L1
    }

    public static HttpURLConnection toHttpConnection(Collection collection)
    {
        Validate.notEmptyAndContainsNoNulls(collection, "requests");
        return toHttpConnection(new RequestBatch(collection));
    }

    public static transient HttpURLConnection toHttpConnection(Request arequest[])
    {
        return toHttpConnection(((Collection) (Arrays.asList(arequest))));
    }

    private static List typedListFromResponse(Response response, Class class1)
    {
        GraphMultiResult graphmultiresult = (GraphMultiResult)response.getGraphObjectAs(com/facebook/model/GraphMultiResult);
        if (graphmultiresult == null)
        {
            return null;
        }
        GraphObjectList graphobjectlist = graphmultiresult.getData();
        if (graphobjectlist == null)
        {
            return null;
        } else
        {
            return graphobjectlist.castToListOf(class1);
        }
    }

    private void validate()
    {
        if (graphPath != null && restMethod != null)
        {
            throw new IllegalArgumentException("Only one of a graph path or REST method may be specified per request.");
        } else
        {
            return;
        }
    }

    public final Response executeAndWait()
    {
        return executeAndWait(this);
    }

    public final RequestAsyncTask executeAsync()
    {
        return executeBatchAsync(new Request[] {
            this
        });
    }

    public final String getBatchEntryDependsOn()
    {
        return batchEntryDependsOn;
    }

    public final String getBatchEntryName()
    {
        return batchEntryName;
    }

    public final boolean getBatchEntryOmitResultOnSuccess()
    {
        return batchEntryOmitResultOnSuccess;
    }

    public final Callback getCallback()
    {
        return callback;
    }

    public final GraphObject getGraphObject()
    {
        return graphObject;
    }

    public final String getGraphPath()
    {
        return graphPath;
    }

    public final HttpMethod getHttpMethod()
    {
        return httpMethod;
    }

    public final Bundle getParameters()
    {
        return parameters;
    }

    public final String getRestMethod()
    {
        return restMethod;
    }

    public final Session getSession()
    {
        return session;
    }

    public final Object getTag()
    {
        return tag;
    }

    final String getUrlForBatchedRequest()
    {
        if (overriddenURL != null)
        {
            throw new FacebookException("Can't override URL for a batch request");
        }
        String s;
        if (restMethod != null)
        {
            s = (new StringBuilder("method/")).append(restMethod).toString();
        } else
        {
            s = graphPath;
        }
        addCommonParameters();
        return appendParametersToBaseUrl(s);
    }

    final String getUrlForSingleRequest()
    {
        if (overriddenURL != null)
        {
            return overriddenURL.toString();
        }
        String s;
        if (restMethod != null)
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = ServerProtocol.getRestUrlBase();
            aobj1[1] = restMethod;
            s = String.format("%s/%s", aobj1);
        } else
        {
            Object aobj[] = new Object[2];
            aobj[0] = ServerProtocol.getGraphUrlBase();
            aobj[1] = graphPath;
            s = String.format("%s/%s", aobj);
        }
        addCommonParameters();
        return appendParametersToBaseUrl(s);
    }

    public final void setBatchEntryDependsOn(String s)
    {
        batchEntryDependsOn = s;
    }

    public final void setBatchEntryName(String s)
    {
        batchEntryName = s;
    }

    public final void setBatchEntryOmitResultOnSuccess(boolean flag)
    {
        batchEntryOmitResultOnSuccess = flag;
    }

    public final void setCallback(Callback callback1)
    {
        callback = callback1;
    }

    public final void setGraphObject(GraphObject graphobject)
    {
        graphObject = graphobject;
    }

    public final void setGraphPath(String s)
    {
        graphPath = s;
    }

    public final void setHttpMethod(HttpMethod httpmethod)
    {
        if (overriddenURL != null && httpmethod != HttpMethod.GET)
        {
            throw new FacebookException("Can't change HTTP method on request with overridden URL.");
        }
        if (httpmethod == null)
        {
            httpmethod = HttpMethod.GET;
        }
        httpMethod = httpmethod;
    }

    public final void setParameters(Bundle bundle)
    {
        parameters = bundle;
    }

    public final void setRestMethod(String s)
    {
        restMethod = s;
    }

    public final void setSession(Session session1)
    {
        session = session1;
    }

    public final void setTag(Object obj)
    {
        tag = obj;
    }

    public String toString()
    {
        return (new StringBuilder("{Request: ")).append(" session: ").append(session).append(", graphPath: ").append(graphPath).append(", graphObject: ").append(graphObject).append(", restMethod: ").append(restMethod).append(", httpMethod: ").append(httpMethod).append(", parameters: ").append(parameters).append("}").toString();
    }




    private class ParcelFileDescriptorWithMimeType
        implements Parcelable
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        private final ParcelFileDescriptor fileDescriptor;
        private final String mimeType;

        public int describeContents()
        {
            return 1;
        }

        public ParcelFileDescriptor getFileDescriptor()
        {
            return fileDescriptor;
        }

        public String getMimeType()
        {
            return mimeType;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mimeType);
            parcel.writeFileDescriptor(fileDescriptor.getFileDescriptor());
        }


        private ParcelFileDescriptorWithMimeType(Parcel parcel)
        {
            mimeType = parcel.readString();
            fileDescriptor = parcel.readFileDescriptor();
        }

        ParcelFileDescriptorWithMimeType(Parcel parcel, _cls1 _pcls1)
        {
            this(parcel);
        }

        public ParcelFileDescriptorWithMimeType(ParcelFileDescriptor parcelfiledescriptor, String s)
        {
            mimeType = s;
            fileDescriptor = parcelfiledescriptor;
        }

        class _cls1
            implements android.os.Parcelable.Creator
        {

            public final ParcelFileDescriptorWithMimeType createFromParcel(Parcel parcel)
            {
                return new ParcelFileDescriptorWithMimeType(parcel, null);
            }

            public final volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public final ParcelFileDescriptorWithMimeType[] newArray(int i)
            {
                return new ParcelFileDescriptorWithMimeType[i];
            }

            public final volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

                _cls1()
                {
                }
        }

    }


    private class _cls1
        implements Callback
    {

        final GraphUserCallback val$callback;

        public final void onCompleted(Response response)
        {
            if (callback != null)
            {
                callback.onCompleted((GraphUser)response.getGraphObjectAs(com/facebook/model/GraphUser), response);
            }
        }

        _cls1()
        {
            callback = graphusercallback;
            super();
        }

        private class GraphUserCallback
        {

            public abstract void onCompleted(GraphUser graphuser, Response response);
        }

    }


    private class _cls2
        implements Callback
    {

        final GraphUserListCallback val$callback;

        public final void onCompleted(Response response)
        {
            if (callback != null)
            {
                callback.onCompleted(Request.typedListFromResponse(response, com/facebook/model/GraphUser), response);
            }
        }

        _cls2()
        {
            callback = graphuserlistcallback;
            super();
        }

        private class GraphUserListCallback
        {

            public abstract void onCompleted(List list, Response response);
        }

    }


    private class _cls3
        implements Callback
    {

        final GraphPlaceListCallback val$callback;

        public final void onCompleted(Response response)
        {
            if (callback != null)
            {
                callback.onCompleted(Request.typedListFromResponse(response, com/facebook/model/GraphPlace), response);
            }
        }

        _cls3()
        {
            callback = graphplacelistcallback;
            super();
        }

        private class GraphPlaceListCallback
        {

            public abstract void onCompleted(List list, Response response);
        }

    }


    private class KeyValueSerializer
    {

        public abstract void writeString(String s, String s1);
    }


    private class _cls4
        implements Runnable
    {

        final ArrayList val$callbacks;
        final RequestBatch val$requests;

        public final void run()
        {
            Pair pair;
            for (Iterator iterator = callbacks.iterator(); iterator.hasNext(); ((Callback)pair.first).onCompleted((Response)pair.second))
            {
                pair = (Pair)iterator.next();
            }

            for (Iterator iterator1 = requests.getCallbacks().iterator(); iterator1.hasNext(); ((RequestBatch.Callback)iterator1.next()).onBatchCompleted(requests)) { }
        }

        _cls4()
        {
            callbacks = arraylist;
            requests = requestbatch;
            super();
        }

        private class Callback
        {

            public abstract void onCompleted(Response response);
        }

    }


    private class Serializer
        implements KeyValueSerializer
    {

        private boolean firstWrite;
        private final Logger logger;
        private final BufferedOutputStream outputStream;

        public transient void write(String s, Object aobj[])
        {
            if (firstWrite)
            {
                outputStream.write("--".getBytes());
                outputStream.write("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f".getBytes());
                outputStream.write("\r\n".getBytes());
                firstWrite = false;
            }
            outputStream.write(String.format(s, aobj).getBytes());
        }

        public void writeBitmap(String s, Bitmap bitmap)
        {
            writeContentDisposition(s, s, "image/png");
            bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, outputStream);
            writeLine("", new Object[0]);
            writeRecordBoundary();
            logger.appendKeyValue((new StringBuilder("    ")).append(s).toString(), "<Image>");
        }

        public void writeBytes(String s, byte abyte0[])
        {
            writeContentDisposition(s, s, "content/unknown");
            outputStream.write(abyte0);
            writeLine("", new Object[0]);
            writeRecordBoundary();
            Logger logger1 = logger;
            String s1 = (new StringBuilder("    ")).append(s).toString();
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(abyte0.length);
            logger1.appendKeyValue(s1, String.format("<Data: %d>", aobj));
        }

        public void writeContentDisposition(String s, String s1, String s2)
        {
            write("Content-Disposition: form-data; name=\"%s\"", new Object[] {
                s
            });
            if (s1 != null)
            {
                write("; filename=\"%s\"", new Object[] {
                    s1
                });
            }
            writeLine("", new Object[0]);
            if (s2 != null)
            {
                writeLine("%s: %s", new Object[] {
                    "Content-Type", s2
                });
            }
            writeLine("", new Object[0]);
        }

        public void writeFile(String s, ParcelFileDescriptor parcelfiledescriptor, String s1)
        {
            android.os.ParcelFileDescriptor.AutoCloseInputStream autocloseinputstream;
            autocloseinputstream = null;
            if (s1 == null)
            {
                s1 = "content/unknown";
            }
            writeContentDisposition(s, s, s1);
            android.os.ParcelFileDescriptor.AutoCloseInputStream autocloseinputstream1 = new android.os.ParcelFileDescriptor.AutoCloseInputStream(parcelfiledescriptor);
            BufferedInputStream bufferedinputstream = new BufferedInputStream(autocloseinputstream1);
            byte abyte0[] = new byte[8192];
            int i = 0;
_L2:
            int j = bufferedinputstream.read(abyte0);
            if (j == -1)
            {
                break; /* Loop/switch isn't completed */
            }
            outputStream.write(abyte0, 0, j);
            i += j;
            if (true) goto _L2; else goto _L1
_L1:
            bufferedinputstream.close();
            autocloseinputstream1.close();
            writeLine("", new Object[0]);
            writeRecordBoundary();
            Logger logger1 = logger;
            String s2 = (new StringBuilder("    ")).append(s).toString();
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            logger1.appendKeyValue(s2, String.format("<Data: %d>", aobj));
            return;
            Exception exception;
            exception;
            bufferedinputstream = null;
_L4:
            if (bufferedinputstream != null)
            {
                bufferedinputstream.close();
            }
            if (autocloseinputstream != null)
            {
                autocloseinputstream.close();
            }
            throw exception;
            exception;
            autocloseinputstream = autocloseinputstream1;
            bufferedinputstream = null;
            continue; /* Loop/switch isn't completed */
            exception;
            autocloseinputstream = autocloseinputstream1;
            if (true) goto _L4; else goto _L3
_L3:
        }

        public void writeFile(String s, ParcelFileDescriptorWithMimeType parcelfiledescriptorwithmimetype)
        {
            writeFile(s, parcelfiledescriptorwithmimetype.getFileDescriptor(), parcelfiledescriptorwithmimetype.getMimeType());
        }

        public transient void writeLine(String s, Object aobj[])
        {
            write(s, aobj);
            write("\r\n", new Object[0]);
        }

        public void writeObject(String s, Object obj)
        {
            if (Request.isSupportedParameterType(obj))
            {
                writeString(s, Request.parameterToString(obj));
                return;
            }
            if (obj instanceof Bitmap)
            {
                writeBitmap(s, (Bitmap)obj);
                return;
            }
            if (obj instanceof byte[])
            {
                writeBytes(s, (byte[])obj);
                return;
            }
            if (obj instanceof ParcelFileDescriptor)
            {
                writeFile(s, (ParcelFileDescriptor)obj, null);
                return;
            }
            if (obj instanceof ParcelFileDescriptorWithMimeType)
            {
                writeFile(s, (ParcelFileDescriptorWithMimeType)obj);
                return;
            } else
            {
                throw new IllegalArgumentException("value is not a supported type: String, Bitmap, byte[]");
            }
        }

        public void writeRecordBoundary()
        {
            writeLine("--%s", new Object[] {
                "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f"
            });
        }

        public void writeString(String s, String s1)
        {
            writeContentDisposition(s, null, null);
            writeLine("%s", new Object[] {
                s1
            });
            writeRecordBoundary();
            if (logger != null)
            {
                logger.appendKeyValue((new StringBuilder("    ")).append(s).toString(), s1);
            }
        }

        public Serializer(BufferedOutputStream bufferedoutputstream, Logger logger1)
        {
            firstWrite = true;
            outputStream = bufferedoutputstream;
            logger = logger1;
        }
    }


    private class _cls5
        implements KeyValueSerializer
    {

        final Request this$0;
        final ArrayList val$keysAndValues;

        public void writeString(String s, String s1)
        {
            ArrayList arraylist = keysAndValues;
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = URLEncoder.encode(s1, "UTF-8");
            arraylist.add(String.format("%s=%s", aobj));
        }

        _cls5()
        {
            this$0 = Request.this;
            keysAndValues = arraylist;
            super();
        }
    }

}
