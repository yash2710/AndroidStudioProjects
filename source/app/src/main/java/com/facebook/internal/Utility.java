// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphObject;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

// Referenced classes of package com.facebook.internal:
//            ImageDownloader, Validate

public final class Utility
{

    private static final String APPLICATION_FIELDS = "fields";
    private static final String APP_SETTING_FIELDS[] = {
        "supports_attribution", "supports_implicit_sdk_logging"
    };
    public static final int DEFAULT_STREAM_BUFFER_SIZE = 8192;
    private static final String HASH_ALGORITHM_MD5 = "MD5";
    static final String LOG_TAG = "FacebookSDK";
    private static final String SUPPORTS_ATTRIBUTION = "supports_attribution";
    private static final String SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
    private static final String URL_SCHEME = "https";
    private static Map fetchedAppSettings = new ConcurrentHashMap();

    public Utility()
    {
    }

    public static boolean areObjectsEqual(Object obj, Object obj1)
    {
        if (obj == null)
        {
            return obj1 == null;
        } else
        {
            return obj.equals(obj1);
        }
    }

    public static transient ArrayList arrayList(Object aobj[])
    {
        ArrayList arraylist = new ArrayList(aobj.length);
        int i = aobj.length;
        for (int j = 0; j < i; j++)
        {
            arraylist.add(aobj[j]);
        }

        return arraylist;
    }

    public static transient List asListNoNulls(Object aobj[])
    {
        ArrayList arraylist = new ArrayList();
        int i = aobj.length;
        for (int j = 0; j < i; j++)
        {
            Object obj = aobj[j];
            if (obj != null)
            {
                arraylist.add(obj);
            }
        }

        return arraylist;
    }

    public static Uri buildUri(String s, String s1, Bundle bundle)
    {
        android.net.Uri.Builder builder = new android.net.Uri.Builder();
        builder.scheme("https");
        builder.authority(s);
        builder.path(s1);
        Iterator iterator = bundle.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s2 = (String)iterator.next();
            Object obj = bundle.get(s2);
            if (obj instanceof String)
            {
                builder.appendQueryParameter(s2, (String)obj);
            }
        } while (true);
        return builder.build();
    }

    public static void clearCaches(Context context)
    {
        ImageDownloader.clearCache(context);
    }

    private static void clearCookiesForDomain(Context context, String s)
    {
        CookieSyncManager.createInstance(context).sync();
        CookieManager cookiemanager = CookieManager.getInstance();
        String s1 = cookiemanager.getCookie(s);
        if (s1 == null)
        {
            return;
        }
        String as[] = s1.split(";");
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String as1[] = as[j].split("=");
            if (as1.length > 0)
            {
                cookiemanager.setCookie(s, (new StringBuilder()).append(as1[0].trim()).append("=;expires=Sat, 1 Jan 2000 00:00:01 UTC;").toString());
            }
        }

        cookiemanager.removeExpiredCookie();
    }

    public static void clearFacebookCookies(Context context)
    {
        clearCookiesForDomain(context, "facebook.com");
        clearCookiesForDomain(context, ".facebook.com");
        clearCookiesForDomain(context, "https://facebook.com");
        clearCookiesForDomain(context, "https://.facebook.com");
    }

    public static void closeQuietly(Closeable closeable)
    {
        if (closeable == null)
        {
            break MISSING_BLOCK_LABEL_10;
        }
        closeable.close();
        return;
        IOException ioexception;
        ioexception;
    }

    static Map convertJSONObjectToHashMap(JSONObject jsonobject)
    {
        HashMap hashmap = new HashMap();
        JSONArray jsonarray = jsonobject.names();
        int i = 0;
        while (i < jsonarray.length()) 
        {
            try
            {
                String s = jsonarray.getString(i);
                Object obj = jsonobject.get(s);
                if (obj instanceof JSONObject)
                {
                    obj = convertJSONObjectToHashMap((JSONObject)obj);
                }
                hashmap.put(s, obj);
            }
            catch (JSONException jsonexception) { }
            i++;
        }
        return hashmap;
    }

    public static void deleteDirectory(File file)
    {
        if (!file.exists())
        {
            return;
        }
        if (file.isDirectory())
        {
            File afile[] = file.listFiles();
            int i = afile.length;
            for (int j = 0; j < i; j++)
            {
                deleteDirectory(afile[j]);
            }

        }
        file.delete();
    }

    public static void disconnectQuietly(URLConnection urlconnection)
    {
        if (urlconnection instanceof HttpURLConnection)
        {
            ((HttpURLConnection)urlconnection).disconnect();
        }
    }

    public static String getMetadataApplicationId(Context context)
    {
        Validate.notNull(context, "context");
        String s;
        ApplicationInfo applicationinfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        if (applicationinfo.metaData == null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        s = applicationinfo.metaData.getString("com.facebook.sdk.ApplicationId");
        return s;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        return null;
    }

    public static Object getStringPropertyAsJSON(JSONObject jsonobject, String s, String s1)
    {
        Object obj = jsonobject.opt(s);
        Object obj1;
        if (obj != null && (obj instanceof String))
        {
            obj1 = (new JSONTokener((String)obj)).nextValue();
        } else
        {
            obj1 = obj;
        }
        if (obj1 != null && !(obj1 instanceof JSONObject) && !(obj1 instanceof JSONArray))
        {
            if (s1 != null)
            {
                JSONObject jsonobject1 = new JSONObject();
                jsonobject1.putOpt(s1, obj1);
                return jsonobject1;
            } else
            {
                throw new FacebookException("Got an unexpected non-JSON object.");
            }
        } else
        {
            return obj1;
        }
    }

    public static boolean isNullOrEmpty(String s)
    {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrEmpty(Collection collection)
    {
        return collection == null || collection.size() == 0;
    }

    public static boolean isSubset(Collection collection, Collection collection1)
    {
label0:
        {
            boolean flag;
label1:
            {
                if (collection1 != null && collection1.size() != 0)
                {
                    break label0;
                }
                if (collection != null)
                {
                    int i = collection.size();
                    flag = false;
                    if (i != 0)
                    {
                        break label1;
                    }
                }
                flag = true;
            }
            return flag;
        }
        HashSet hashset = new HashSet(collection1);
        for (Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            if (!hashset.contains(iterator.next()))
            {
                return false;
            }
        }

        return true;
    }

    public static void logd(String s, Exception exception)
    {
    }

    public static void logd(String s, String s1)
    {
    }

    static String md5hash(String s)
    {
        MessageDigest messagedigest;
        byte abyte0[];
        StringBuilder stringbuilder;
        int i;
        try
        {
            messagedigest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            return null;
        }
        messagedigest.update(s.getBytes());
        abyte0 = messagedigest.digest();
        stringbuilder = new StringBuilder();
        i = abyte0.length;
        for (int j = 0; j < i; j++)
        {
            byte byte0 = abyte0[j];
            stringbuilder.append(Integer.toHexString(0xf & byte0 >> 4));
            stringbuilder.append(Integer.toHexString(byte0 & 0xf));
        }

        return stringbuilder.toString();
    }

    public static void putObjectInBundle(Bundle bundle, String s, Object obj)
    {
        if (obj instanceof String)
        {
            bundle.putString(s, (String)obj);
            return;
        }
        if (obj instanceof Parcelable)
        {
            bundle.putParcelable(s, (Parcelable)obj);
            return;
        }
        if (obj instanceof byte[])
        {
            bundle.putByteArray(s, (byte[])obj);
            return;
        } else
        {
            throw new FacebookException("attempted to add unsupported type to Bundle");
        }
    }

    public static FetchedAppSettings queryAppSettings(String s, boolean flag)
    {
        if (!flag && fetchedAppSettings.containsKey(s))
        {
            return (FetchedAppSettings)fetchedAppSettings.get(s);
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putString("fields", TextUtils.join(",", APP_SETTING_FIELDS));
            Request request = Request.newGraphPathRequest(null, s, null);
            request.setParameters(bundle);
            GraphObject graphobject = request.executeAndWait().getGraphObject();
            FetchedAppSettings fetchedappsettings = new FetchedAppSettings(safeGetBooleanFromResponse(graphobject, "supports_attribution"), safeGetBooleanFromResponse(graphobject, "supports_implicit_sdk_logging"), null);
            fetchedAppSettings.put(s, fetchedappsettings);
            return fetchedappsettings;
        }
    }

    public static String readStreamToString(InputStream inputstream)
    {
        BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);
        InputStreamReader inputstreamreader = new InputStreamReader(bufferedinputstream);
        StringBuilder stringbuilder;
        char ac[];
        stringbuilder = new StringBuilder();
        ac = new char[2048];
_L3:
        int i = inputstreamreader.read(ac);
        if (i == -1) goto _L2; else goto _L1
_L1:
        stringbuilder.append(ac, 0, i);
          goto _L3
        Exception exception;
        exception;
        Object obj = bufferedinputstream;
_L5:
        closeQuietly(((Closeable) (obj)));
        closeQuietly(inputstreamreader);
        throw exception;
_L2:
        String s = stringbuilder.toString();
        closeQuietly(bufferedinputstream);
        closeQuietly(inputstreamreader);
        return s;
        exception;
        inputstreamreader = null;
        obj = null;
        continue; /* Loop/switch isn't completed */
        exception;
        obj = bufferedinputstream;
        inputstreamreader = null;
        if (true) goto _L5; else goto _L4
_L4:
    }

    private static boolean safeGetBooleanFromResponse(GraphObject graphobject, String s)
    {
        Object obj = Boolean.valueOf(false);
        if (graphobject != null)
        {
            obj = graphobject.getProperty(s);
        }
        if (!(obj instanceof Boolean))
        {
            obj = Boolean.valueOf(false);
        }
        return ((Boolean)obj).booleanValue();
    }

    public static boolean stringsEqualOrEmpty(String s, String s1)
    {
        boolean flag = TextUtils.isEmpty(s);
        boolean flag1 = TextUtils.isEmpty(s1);
        if (flag && flag1)
        {
            return true;
        }
        if (!flag && !flag1)
        {
            return s.equals(s1);
        } else
        {
            return false;
        }
    }

    public static transient Collection unmodifiableCollection(Object aobj[])
    {
        return Collections.unmodifiableCollection(Arrays.asList(aobj));
    }


    private class FetchedAppSettings
    {

        private boolean supportsAttribution;
        private boolean supportsImplicitLogging;

        public boolean supportsAttribution()
        {
            return supportsAttribution;
        }

        public boolean supportsImplicitLogging()
        {
            return supportsImplicitLogging;
        }

        private FetchedAppSettings(boolean flag, boolean flag1)
        {
            supportsAttribution = flag;
            supportsImplicitLogging = flag1;
        }

        FetchedAppSettings(boolean flag, boolean flag1, _cls1 _pcls1)
        {
            this(flag, flag1);
        }
    }

}
