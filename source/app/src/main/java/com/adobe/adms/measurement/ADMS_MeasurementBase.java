// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.measurement;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TimeZone;

// Referenced classes of package com.adobe.adms.measurement:
//            d, e, ADMS_ContextData, ADMS_DefaultValues

public abstract class ADMS_MeasurementBase
{

    private static ArrayList B = new ArrayList();
    private static ArrayList C = new ArrayList();
    private static final Hashtable y = new d();
    private static final Hashtable z = new e();
    private SecureRandom A;
    private String a;
    private String b;
    private String c;
    private String d;
    protected String dataCenter;
    protected boolean debugLogging;
    private String e;
    protected Hashtable evarMap;
    private String f;
    private String g;
    private String h;
    protected Hashtable hierMap;
    private String i;
    protected boolean isOffline;
    private String j;
    private String k;
    private String l;
    protected Hashtable listMap;
    private String m;
    private String n;
    private String o;
    protected int offlineHitLimit;
    protected boolean offlineTrackingEnabled;
    private String p;
    protected Hashtable persistentContextData;
    protected Hashtable pevMap;
    protected Hashtable propMap;
    private String q;
    private String r;
    private String s;
    protected boolean ssl;
    private String t;
    protected long timestamp;
    private String u;
    protected String userAgent;
    private String v;
    protected String version;
    private int w;
    private int x;

    protected ADMS_MeasurementBase()
    {
        a = null;
        b = null;
        c = null;
        d = null;
        e = null;
        ssl = false;
        debugLogging = false;
        offlineTrackingEnabled = true;
        offlineHitLimit = 1000;
        f = null;
        g = null;
        h = null;
        i = null;
        j = null;
        k = null;
        l = null;
        m = null;
        persistentContextData = null;
        n = null;
        o = null;
        p = null;
        q = null;
        r = null;
        s = null;
        t = null;
        u = null;
        v = null;
        w = 0;
        x = 0;
        userAgent = null;
        dataCenter = null;
        timestamp = 0L;
        isOffline = false;
        A = null;
        setupDefaults();
    }

    protected ADMS_MeasurementBase(String s1, String s2)
    {
        a = null;
        b = null;
        c = null;
        d = null;
        e = null;
        ssl = false;
        debugLogging = false;
        offlineTrackingEnabled = true;
        offlineHitLimit = 1000;
        f = null;
        g = null;
        h = null;
        i = null;
        j = null;
        k = null;
        l = null;
        m = null;
        persistentContextData = null;
        n = null;
        o = null;
        p = null;
        q = null;
        r = null;
        s = null;
        t = null;
        u = null;
        v = null;
        w = 0;
        x = 0;
        userAgent = null;
        dataCenter = null;
        timestamp = 0L;
        isOffline = false;
        A = null;
        setupDefaults();
    }

    private void a(Object obj, ADMS_ContextData adms_contextdata, ArrayList arraylist, int i1)
    {
        do
        {
            int j1 = arraylist.size();
            String s1;
            if (i1 < j1)
            {
                s1 = (String)arraylist.get(i1);
            } else
            {
                s1 = null;
            }
            if (s1 == null)
            {
                return;
            }
            ADMS_ContextData adms_contextdata1 = new ADMS_ContextData();
            if (adms_contextdata.containsKey(s1))
            {
                adms_contextdata1 = adms_contextdata.get(s1);
            }
            if (j1 - 1 == i1)
            {
                adms_contextdata1.value = obj;
                adms_contextdata.put(s1, adms_contextdata1);
                return;
            }
            adms_contextdata.put(s1, adms_contextdata1);
            i1++;
            adms_contextdata = adms_contextdata1;
        } while (true);
    }

    private static boolean a(Object obj)
    {
        String s1;
        boolean flag;
        try
        {
            s1 = (String)obj;
        }
        catch (Exception exception)
        {
            return false;
        }
        flag = false;
        if (s1 != null)
        {
            flag = true;
        }
        return flag;
    }

    private static boolean b(Object obj)
    {
        Integer integer;
        boolean flag;
        try
        {
            integer = (Integer)obj;
        }
        catch (Exception exception)
        {
            return false;
        }
        flag = false;
        if (integer != null)
        {
            flag = true;
        }
        return flag;
    }

    protected static String join(Iterable iterable, String s1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        Object obj;
        for (Iterator iterator = iterable.iterator(); iterator.hasNext(); stringbuilder.append(obj))
        {
            obj = iterator.next();
            if (stringbuilder.length() > 0)
            {
                stringbuilder.append(s1);
            }
        }

        return stringbuilder.toString();
    }

    protected void addAllParameters(ArrayList arraylist, ArrayList arraylist1)
    {
        for (Iterator iterator = arraylist1.iterator(); iterator.hasNext(); arraylist.add(cleanParameterKey((String)iterator.next()))) { }
    }

    protected String cleanCommaDelimitedString(String s1)
    {
        if (!isSet(s1))
        {
            return s1;
        } else
        {
            return s1.replaceAll(" ", "");
        }
    }

    protected String cleanContextKey(String s1)
    {
        return s1.replaceAll("[^a-zA-Z0-9.]", "");
    }

    protected String cleanParameterKey(String s1)
    {
        Enumeration enumeration = y.keys();
_L2:
        String s2;
        String s3;
        do
        {
            if (!enumeration.hasMoreElements())
            {
                break MISSING_BLOCK_LABEL_88;
            }
            s2 = (String)enumeration.nextElement();
        } while (!s1.toLowerCase().startsWith(s2));
        s3 = s1.substring(s2.length());
        if (Integer.parseInt(s3) <= 0) goto _L2; else goto _L1
_L1:
        String s4 = (new StringBuilder()).append((String)y.get(s2)).append(s3).toString();
        return s4;
        return replaceKeyWithADMSKey(s1);
        NumberFormatException numberformatexception;
        numberformatexception;
        return s1;
    }

    protected void clearAllVars()
    {
        a = null;
        b = null;
        c = null;
        d = null;
        e = null;
        ssl = false;
        debugLogging = false;
        offlineTrackingEnabled = true;
        offlineHitLimit = 1000;
        clearVars();
    }

    public void clearTrackingQueue()
    {
    }

    public void clearVars()
    {
        resetSingleUseParameters();
        f = null;
        g = null;
        h = null;
        i = null;
        j = null;
        k = null;
        m = null;
        l = null;
        n = null;
        o = null;
        p = null;
        q = null;
        r = null;
        evarMap.clear();
        propMap.clear();
        listMap.clear();
        hierMap.clear();
        persistentContextData.clear();
    }

    public Object clone()
    {
        throw new CloneNotSupportedException();
    }

    public void configureMeasurement(String s1, String s2)
    {
        setReportSuiteIDs(s1);
        setTrackingServer(s2);
    }

    protected abstract void debugLog(String s1);

    protected void filterLightTrackParameters(Hashtable hashtable, Hashtable hashtable1, Hashtable hashtable2)
    {
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        if (isSet(p))
        {
            arraylist1.addAll(Arrays.asList(p.split("[,]")));
        }
        Hashtable hashtable3 = new Hashtable();
        if (hashtable2 != null)
        {
            Enumeration enumeration1 = hashtable2.keys();
            do
            {
                if (!enumeration1.hasMoreElements())
                {
                    break;
                }
                String s2 = (String)enumeration1.nextElement();
                if (s2.equalsIgnoreCase("lightTrackVars"))
                {
                    String s4 = cleanCommaDelimitedString((String)hashtable2.get("lightTrackVars"));
                    if (isSet(s4))
                    {
                        arraylist1.clear();
                        arraylist1.addAll(Arrays.asList(s4.split("[,]")));
                    }
                }
                String s3 = cleanParameterKey(s2);
                if (lightParameterIsValid(s3))
                {
                    hashtable3.put(s3, hashtable2.get(s2));
                }
            } while (true);
        }
        addAllParameters(arraylist, arraylist1);
        putAllParameters(hashtable, hashtable3);
        int i1 = arraylist.size();
        ArrayList arraylist2 = null;
        if (i1 > 0)
        {
            arraylist.addAll(ADMS_DefaultValues.requiredLightVarList);
            Enumeration enumeration = hashtable.keys();
            do
            {
                if (!enumeration.hasMoreElements())
                {
                    break;
                }
                String s1 = (String)enumeration.nextElement();
                if (!arraylist.contains(s1))
                {
                    hashtable.remove(s1);
                }
            } while (true);
            arraylist2 = generateFilterArrayWithVars(arraylist1);
        }
        handlePersistentContextData(hashtable, hashtable1, arraylist2);
    }

    protected void filterLinkTrackParameters(Hashtable hashtable, Hashtable hashtable1, Hashtable hashtable2)
    {
label0:
        {
            ArrayList arraylist = new ArrayList();
            ArrayList arraylist1 = new ArrayList();
            if (isSet(q))
            {
                arraylist1.addAll(Arrays.asList(q.split("[,]")));
            }
            ArrayList arraylist2 = new ArrayList();
            if (isSet(r))
            {
                arraylist2.addAll(Arrays.asList(r.split("[,]")));
            }
            ArrayList arraylist3 = new ArrayList();
            if (isSet(m))
            {
                arraylist3.addAll(Arrays.asList(m.split("[,]")));
            }
            Hashtable hashtable3 = new Hashtable();
            if (hashtable2 != null)
            {
                Enumeration enumeration1 = hashtable2.keys();
                do
                {
                    if (!enumeration1.hasMoreElements())
                    {
                        break;
                    }
                    String s4 = (String)enumeration1.nextElement();
                    if (s4.equalsIgnoreCase("linkTrackEvents"))
                    {
                        String s8 = cleanCommaDelimitedString((String)hashtable2.get("linkTrackEvents"));
                        if (isSet(s8))
                        {
                            arraylist2.clear();
                            arraylist2.addAll(Arrays.asList(s8.split("[,]")));
                        }
                    } else
                    if (s4.equalsIgnoreCase("events"))
                    {
                        String s7 = cleanCommaDelimitedString((String)hashtable2.get("events"));
                        if (isSet(s7))
                        {
                            arraylist3.clear();
                            arraylist3.addAll(Arrays.asList(s7.split("[,]")));
                        }
                    } else
                    if (s4.equalsIgnoreCase("linkTrackVars"))
                    {
                        String s6 = cleanCommaDelimitedString((String)hashtable2.get("linkTrackVars"));
                        if (isSet(s6))
                        {
                            arraylist1.clear();
                            arraylist1.addAll(Arrays.asList(s6.split("[,]")));
                        }
                    } else
                    {
                        String s5 = cleanParameterKey(s4);
                        if (parameterKeyIsValid(s5))
                        {
                            hashtable3.put(s5, hashtable2.get(s4));
                        }
                    }
                } while (true);
            }
            addAllParameters(arraylist, arraylist1);
            putAllParameters(hashtable, hashtable3);
            String s1;
            int i1;
            ArrayList arraylist4;
            if (hashtable.containsKey("pe"))
            {
                s1 = (String)hashtable.get("pe");
            } else
            {
                s1 = null;
            }
            if (hashtable2 != null && hashtable2.containsKey("pe"))
            {
                s1 = (String)hashtable2.get("pe");
            }
            if (!isSet(s1))
            {
                boolean flag = isSet(u);
                arraylist4 = null;
                if (!flag)
                {
                    break label0;
                }
            }
            i1 = arraylist.size();
            arraylist4 = null;
            if (i1 > 0)
            {
                arraylist.addAll(ADMS_DefaultValues.requiredVarList);
                Enumeration enumeration = hashtable.keys();
                do
                {
                    if (!enumeration.hasMoreElements())
                    {
                        break;
                    }
                    String s3 = (String)enumeration.nextElement();
                    if (!arraylist.contains(s3))
                    {
                        hashtable.remove(s3);
                    }
                } while (true);
                arraylist4 = generateFilterArrayWithVars(arraylist1);
            }
            if (arraylist2.size() > 0)
            {
                ArrayList arraylist5 = new ArrayList();
                arraylist5.addAll(arraylist3);
                Iterator iterator = arraylist5.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    String s2 = (String)iterator.next();
                    if (!arraylist2.contains(s2))
                    {
                        arraylist3.remove(s2);
                    }
                } while (true);
            }
        }
        handlePersistentContextData(hashtable, hashtable1, arraylist4);
        putParameter(hashtable, "events", arraylist3);
    }

    protected ArrayList generateFilterArrayWithVars(ArrayList arraylist)
    {
        ArrayList arraylist1 = new ArrayList();
        Iterator iterator = arraylist.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s1 = ((String)iterator.next()).toLowerCase();
            if (s1.equals("contextdata"))
            {
                return null;
            }
            if (s1.contains("contextdata"))
            {
                arraylist1.add(s1);
            }
        } while (true);
        if (arraylist1.size() > 0)
        {
            arraylist1.add("contextdata.a.CarrierName");
            arraylist1.add("contextdata.a.OSVersion");
            arraylist1.add("contextdata.a.DeviceName");
            arraylist1.add("contextdata.a.Resolution");
        } else
        {
            arraylist1.add("   ");
        }
        return arraylist1;
    }

    protected Hashtable generateLightTrackParameters()
    {
        Hashtable hashtable = new Hashtable();
        putParameter(hashtable, "ce", d);
        putParameter(hashtable, "mtp", v);
        putParameter(hashtable, "mtss", Integer.valueOf(w));
        putParameter(hashtable, "mti", Integer.valueOf(x));
        putAllStringParameters(hashtable, evarMap);
        putAllStringParameters(hashtable, propMap);
        return hashtable;
    }

    protected Hashtable generateTrackParameters()
    {
        Hashtable hashtable = new Hashtable();
        putParameter(hashtable, "vid", c);
        putParameter(hashtable, "ce", d);
        putParameter(hashtable, "pageName", h);
        putParameter(hashtable, "cc", e);
        putParameter(hashtable, "purchaseID", f);
        putParameter(hashtable, "xact", g);
        putParameter(hashtable, "ch", i);
        putParameter(hashtable, "server", j);
        putParameter(hashtable, "v0", k);
        putParameter(hashtable, "zip", n);
        putParameter(hashtable, "state", o);
        putParameter(hashtable, "products", l);
        putAllStringParameters(hashtable, propMap);
        putAllStringParameters(hashtable, evarMap);
        putAllStringParameters(hashtable, listMap);
        putAllStringParameters(hashtable, hierMap);
        putAllStringParameters(hashtable, pevMap);
        return hashtable;
    }

    public String getAppSection()
    {
        return j;
    }

    public String getAppState()
    {
        return h;
    }

    protected abstract String getApplicationID();

    public String getCampaign()
    {
        return k;
    }

    protected abstract String getCarrierString();

    public String getChannel()
    {
        return i;
    }

    public String getCharSet()
    {
        return d;
    }

    public String getCurrencyCode()
    {
        return e;
    }

    protected abstract String getDefaultCharSet();

    protected abstract String getDefaultUserAgent();

    protected abstract String getDefaultVisitorID();

    public String getEvar(int i1)
    {
        if (ADMS_DefaultValues.evarValid(i1))
        {
            return (String)evarMap.get((new StringBuilder("v")).append(i1).toString());
        } else
        {
            return null;
        }
    }

    public String getEvents()
    {
        return m;
    }

    protected String getFullTrackingServer()
    {
        if (!isSet(b))
        {
            String s1 = "";
            ArrayList arraylist = new ArrayList(Arrays.asList(a.split("[,]")));
            if (arraylist.size() > 0)
            {
                s1 = (String)arraylist.get(0);
            }
            String s2 = s1.replace(".", "-").replace("_", "-");
            if (isSet(dataCenter))
            {
                dataCenter = dataCenter.toLowerCase();
                if (dataCenter.equals("dc2") || dataCenter.equals("122"))
                {
                    dataCenter = "122";
                }
            } else
            {
                dataCenter = "112";
            }
            b = (new StringBuilder()).append(s2).append(".").append(dataCenter).append(".2o7.net").toString();
        }
        return b;
    }

    public String getGeoState()
    {
        return o;
    }

    public String getGeoZip()
    {
        return n;
    }

    public String getHier(int i1)
    {
        if (ADMS_DefaultValues.hierValid(i1))
        {
            return (String)hierMap.get((new StringBuilder("h")).append(i1).toString());
        } else
        {
            return null;
        }
    }

    protected int getLightIncrementBy()
    {
        return x;
    }

    public String getLightProfileID()
    {
        return v;
    }

    protected int getLightStoreForSeconds()
    {
        return w;
    }

    public String getLightTrackVars()
    {
        return p;
    }

    protected String getLinkName()
    {
        return t;
    }

    public String getLinkTrackEvents()
    {
        return r;
    }

    public String getLinkTrackVars()
    {
        return q;
    }

    protected String getLinkType()
    {
        return u;
    }

    protected String getLinkURL()
    {
        return s;
    }

    public String getListVar(int i1)
    {
        if (ADMS_DefaultValues.listValid(i1))
        {
            return (String)listMap.get((new StringBuilder("l")).append(i1).toString());
        } else
        {
            return null;
        }
    }

    protected abstract String getNetworkConnectionString();

    public int getOfflineHitLimit()
    {
        return offlineHitLimit;
    }

    public int getOfflineThrottleDelay()
    {
        return 0;
    }

    protected abstract String getOperatingSystemString();

    public Hashtable getPersistentContextData()
    {
        Hashtable hashtable = persistentContextData;
        Hashtable hashtable1 = null;
        if (hashtable != null)
        {
            hashtable1 = (Hashtable)persistentContextData.clone();
        }
        return hashtable1;
    }

    protected abstract String getPlatformString();

    public String getProducts()
    {
        return l;
    }

    public String getProp(int i1)
    {
        if (ADMS_DefaultValues.propValid(i1))
        {
            return (String)propMap.get((new StringBuilder("c")).append(i1).toString());
        } else
        {
            return null;
        }
    }

    public String getPurchaseID()
    {
        return f;
    }

    public String getReportSuiteIDs()
    {
        return a;
    }

    protected String getRequestString()
    {
        return getRequestString(null, null);
    }

    protected String getRequestString(Hashtable hashtable, Hashtable hashtable1)
    {
        if (!isSet(a))
        {
            return null;
        }
        Hashtable hashtable2 = getTrackingParameters(hashtable, hashtable1);
        debugLog((new StringBuilder("Hit Parameters : ")).append(hashtable2.toString()).toString());
        SecureRandom securerandom = A;
        int i1 = 0;
        if (securerandom != null)
        {
            i1 = 1 + A.nextInt(0x5f5e100);
        }
        String s1 = (new StringBuilder("s")).append(i1).toString();
        String s2 = serializeToQueryString(hashtable2);
        String s3 = getFullTrackingServer();
        String s4;
        if (ssl)
        {
            s4 = "https";
        } else
        {
            s4 = "http";
        }
        return (new StringBuilder()).append(s4).append("://").append(s3).append("/b/ss/").append(urlEncode(a)).append("/0/").append(version).append("/").append(s1).append("?AQB=1&ndh=1").append(s2).append("&AQE=1").toString();
    }

    protected abstract String getResolutionString();

    protected double getTime()
    {
        return (double)Calendar.getInstance().getTime().getTime();
    }

    protected Hashtable getTrackingParameters(Hashtable hashtable, Hashtable hashtable1)
    {
        Hashtable hashtable2 = new Hashtable();
        validateTechnology();
        if (isSet(v))
        {
            putAllParameters(hashtable2, generateLightTrackParameters());
            filterLightTrackParameters(hashtable2, hashtable, hashtable1);
            validateRequiredLightVariables(hashtable2);
        } else
        {
            validateLinkTracking(hashtable2);
            putAllParameters(hashtable2, generateTrackParameters());
            filterLinkTrackParameters(hashtable2, hashtable, hashtable1);
            validateRequiredVariables(hashtable2);
        }
        validateTimestamps(hashtable2);
        return hashtable2;
    }

    public int getTrackingQueueSize()
    {
        return 0;
    }

    public String getTrackingServer()
    {
        return b;
    }

    public String getTransactionID()
    {
        return g;
    }

    protected ArrayList getValidKeys()
    {
        int i1 = 1;
        ArrayList arraylist = C;
        arraylist;
        JVM INSTR monitorenter ;
        if (C.size() != 0) goto _L2; else goto _L1
_L1:
        C.add("vid");
        C.add("ce");
        C.add("ns");
        C.add("pageName");
        C.add("g");
        C.add("r");
        C.add("cc");
        C.add("pe");
        C.add("ts");
        C.add("t");
        C.add("purchaseID");
        C.add("ch");
        C.add("server");
        C.add("pageType");
        C.add("xact");
        C.add("v0");
        C.add("state");
        C.add("zip");
        C.add("events");
        C.add("products");
        C.add("mtsr");
        int j1 = i1;
_L4:
        if (j1 > 75)
        {
            break; /* Loop/switch isn't completed */
        }
        C.add((new StringBuilder("c")).append(j1).toString());
        j1++;
        if (true) goto _L4; else goto _L3
_L5:
        int k1;
        if (k1 > 75)
        {
            break MISSING_BLOCK_LABEL_452;
        }
        C.add((new StringBuilder("v")).append(k1).toString());
        k1++;
          goto _L5
_L6:
        int l1;
        if (l1 > 5)
        {
            break MISSING_BLOCK_LABEL_458;
        }
        C.add((new StringBuilder("h")).append(l1).toString());
        l1++;
          goto _L6
_L7:
        i2++;
        Exception exception;
        int i2;
        for (; i2 > 3; i2 = i1)
        {
            break MISSING_BLOCK_LABEL_380;
        }

        C.add((new StringBuilder("l")).append(i2).toString());
          goto _L7
_L9:
        if (i1 > 3)
        {
            break; /* Loop/switch isn't completed */
        }
        C.add((new StringBuilder("pev")).append(i1).toString());
        i1++;
        if (true) goto _L9; else goto _L8
_L8:
        C.add("c");
        C.add("mtsd");
_L2:
        arraylist;
        JVM INSTR monitorexit ;
        return C;
        exception;
        throw exception;
_L3:
        k1 = i1;
          goto _L5
        l1 = i1;
          goto _L6
    }

    protected ArrayList getValidLightParameters()
    {
        int i1 = 1;
        ArrayList arraylist = B;
        arraylist;
        JVM INSTR monitorenter ;
        if (B.size() != 0) goto _L2; else goto _L1
_L1:
        B.add("ce");
        B.add("ns");
        B.add("cookieDomainPeriods");
        B.add("cookieLifetime");
        B.add("mtp");
        B.add("mtss");
        B.add("mti");
        B.add("mtsd");
        B.add("ts");
        int j1 = i1;
_L4:
        if (j1 > 75)
        {
            break; /* Loop/switch isn't completed */
        }
        B.add((new StringBuilder("c")).append(j1).toString());
        j1++;
        if (true) goto _L4; else goto _L3
_L3:
        if (i1 > 75)
        {
            break; /* Loop/switch isn't completed */
        }
        B.add((new StringBuilder("v")).append(i1).toString());
        i1++;
        if (true) goto _L3; else goto _L2
_L2:
        arraylist;
        JVM INSTR monitorexit ;
        return B;
        Exception exception;
        exception;
        throw exception;
    }

    public String getVersion()
    {
        return version;
    }

    public String getVisitorID()
    {
        return c;
    }

    protected void handlePersistentContextData(Hashtable hashtable, Hashtable hashtable1, ArrayList arraylist)
    {
        Hashtable hashtable2 = new Hashtable();
        synchronized (persistentContextData)
        {
            hashtable2.putAll(persistentContextData);
        }
        if (hashtable1 != null)
        {
            String s1;
            Object obj;
            for (Enumeration enumeration = hashtable1.keys(); enumeration.hasMoreElements(); hashtable2.put(cleanContextKey(s1), obj))
            {
                s1 = (String)enumeration.nextElement();
                obj = hashtable1.get(s1);
            }

        }
        putParameter(hashtable, "c", translateContextData(hashtable2, arraylist));
    }

    protected boolean isBoolean(Object obj)
    {
        Boolean boolean1;
        boolean flag;
        try
        {
            boolean1 = (Boolean)obj;
        }
        catch (Exception exception)
        {
            return false;
        }
        flag = false;
        if (boolean1 != null)
        {
            flag = true;
        }
        return flag;
    }

    public boolean isOfflineTrackingEnabled()
    {
        return offlineTrackingEnabled;
    }

    protected boolean isSet(double d1)
    {
        return d1 != 0.0D;
    }

    protected boolean isSet(float f1)
    {
        return (double)f1 != 0.0D;
    }

    protected boolean isSet(int i1)
    {
        return i1 != 0;
    }

    protected boolean isSet(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (a(obj))
        {
            return isSet((String)obj);
        }
        if (b(obj))
        {
            return isSet(toInteger(obj));
        }
        if (isBoolean(obj))
        {
            return isSet(toBoolean(obj));
        } else
        {
            return true;
        }
    }

    protected boolean isSet(String s1)
    {
        while (s1 == null || s1.length() == 0) 
        {
            return false;
        }
        return true;
    }

    protected boolean isSet(boolean flag)
    {
        return flag;
    }

    protected boolean lightParameterIsValid(String s1)
    {
        return getValidLightParameters().contains(s1);
    }

    protected boolean parameterKeyIsValid(String s1)
    {
        return getValidKeys().contains(s1);
    }

    protected void putAllParameters(Hashtable hashtable, Hashtable hashtable1)
    {
        putAllParameters(hashtable, hashtable1, false);
    }

    protected void putAllParameters(Hashtable hashtable, Hashtable hashtable1, boolean flag)
    {
        if (hashtable1 != null)
        {
            String s1;
            for (Enumeration enumeration = hashtable1.keys(); enumeration.hasMoreElements(); putParameter(hashtable, s1, hashtable1.get(s1), flag))
            {
                s1 = (String)enumeration.nextElement();
            }

        }
    }

    protected void putAllStringParameters(Hashtable hashtable, Hashtable hashtable1)
    {
        String s1;
        for (Enumeration enumeration = hashtable1.keys(); enumeration.hasMoreElements(); putParameter(hashtable, s1, hashtable1.get(s1)))
        {
            s1 = (String)enumeration.nextElement();
        }

    }

    protected void putContextData(String s1, Object obj)
    {
        if (!isSet(s1) || !isSet(obj))
        {
            return;
        } else
        {
            persistentContextData.put(s1, obj);
            return;
        }
    }

    protected void putParameter(Hashtable hashtable, String s1, Object obj)
    {
        putParameter(hashtable, s1, obj, false);
    }

    protected void putParameter(Hashtable hashtable, String s1, Object obj, boolean flag)
    {
        if (!isSet(obj))
        {
            hashtable.remove(s1);
        } else
        {
            boolean flag1 = true;
            if (flag)
            {
                s1 = cleanParameterKey(s1);
                flag1 = parameterKeyIsValid(s1);
            }
            if (isSet(s1) && isSet(obj) && flag1 && (!(obj instanceof ArrayList) || ((ArrayList)obj).size() != 0) && (!(obj instanceof Hashtable) || ((Hashtable)obj).size() != 0))
            {
                hashtable.put(s1, obj);
                return;
            }
        }
    }

    protected void putStringParameter(Hashtable hashtable, String s1, String s2)
    {
        if (!isSet(s2))
        {
            hashtable.remove(s1);
            return;
        } else
        {
            hashtable.put(s1, s2);
            return;
        }
    }

    protected String replaceKeyWithADMSKey(String s1)
    {
        String s2 = s1.toLowerCase();
        if (z.containsKey(s2))
        {
            return (String)z.get(s2);
        } else
        {
            return s1;
        }
    }

    protected void resetSingleUseParameters()
    {
        s = null;
        t = null;
        u = null;
        v = null;
        w = 0;
        x = 0;
        pevMap.clear();
    }

    protected void sendRequest(String s1)
    {
        debugLog((new StringBuilder("requestString")).append(s1).toString());
    }

    protected String serializeToQueryString(Hashtable hashtable)
    {
        Enumeration enumeration;
        String s1;
        enumeration = hashtable.keys();
        s1 = "";
_L9:
        if (!enumeration.hasMoreElements()) goto _L2; else goto _L1
_L1:
        String s2;
        Object obj;
        s2 = (String)enumeration.nextElement();
        obj = hashtable.get(s2);
        if ((obj instanceof String) && isSet((String)obj))
        {
            String s11 = (String)obj;
            s1 = (new StringBuilder()).append(s1).append("&").append(urlEncode(s2)).append("=").append(urlEncode(s11)).toString();
            continue; /* Loop/switch isn't completed */
        }
        if (!(obj instanceof ADMS_ContextData)) goto _L4; else goto _L3
_L3:
        ADMS_ContextData adms_contextdata = (ADMS_ContextData)obj;
        String s3;
        String s4;
        ArrayList arraylist;
        String s5;
        String s6;
        boolean flag;
        Hashtable hashtable1;
        String s7;
        String s8;
        String s9;
        String s10;
        if ((adms_contextdata.value instanceof String) && isSet((String)adms_contextdata.value))
        {
            s1 = (new StringBuilder()).append(s1).append("&").append(urlEncode(s2)).append("=").append(urlEncode((String)adms_contextdata.value)).toString();
            flag = true;
        } else
        {
            flag = false;
        }
        if (adms_contextdata.contextData.size() <= 0) goto _L6; else goto _L5
_L5:
        hashtable1 = new Hashtable();
        hashtable1.putAll(adms_contextdata.contextData);
        s7 = (new StringBuilder()).append(s1).append("&").append(urlEncode(s2)).append(".").toString();
        s8 = (new StringBuilder()).append(s7).append(serializeToQueryString(hashtable1)).toString();
        s4 = (new StringBuilder()).append(s8).append("&.").append(urlEncode(s2)).toString();
_L7:
        s1 = s4;
        continue; /* Loop/switch isn't completed */
_L6:
        if (isSet(adms_contextdata.value) && !flag)
        {
            s10 = adms_contextdata.value.toString();
            s9 = (new StringBuilder()).append(s1).append("&").append(urlEncode(s2)).append("=").append(urlEncode(s10)).toString();
        } else
        {
            s9 = s1;
        }
        s1 = s9;
        continue; /* Loop/switch isn't completed */
_L4:
        if (obj instanceof ArrayList)
        {
            arraylist = (ArrayList)obj;
            if (arraylist.size() > 0)
            {
                s6 = join(arraylist, ",");
                s5 = (new StringBuilder()).append(s1).append("&").append(urlEncode(s2)).append("=").append(urlEncode(s6)).toString();
            } else
            {
                s5 = s1;
            }
            s1 = s5;
            continue; /* Loop/switch isn't completed */
        }
        s3 = obj.toString();
        debugLog((new StringBuilder("Object for parameter \"")).append(s2).append("\" is of class ").append(obj.getClass().getSimpleName()).append(".").toString());
        debugLog((new StringBuilder("We recommend using strings where possible, but we are sending this parameter with a value of ")).append(s3).append(".").toString());
        debugLog("If you would like another value for this parameter please send the value as a string the way you would like it to appear.");
        s4 = (new StringBuilder()).append(s1).append("&").append(urlEncode(s2)).append("=").append(urlEncode(s3)).toString();
        if (true) goto _L7; else goto _L2
_L2:
        return s1;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public void setAppSection(String s1)
    {
        j = s1;
    }

    public void setAppState(String s1)
    {
        h = s1;
    }

    public void setCampaign(String s1)
    {
        k = s1;
    }

    public void setChannel(String s1)
    {
        i = s1;
    }

    public void setCharSet(String s1)
    {
        d = s1;
    }

    public void setCurrencyCode(String s1)
    {
        e = s1;
    }

    public void setDebugLogging(boolean flag)
    {
        debugLogging = flag;
    }

    public void setEvar(int i1, String s1)
    {
        if (ADMS_DefaultValues.evarValid(i1))
        {
            String s2 = (new StringBuilder("v")).append(i1).toString();
            putStringParameter(evarMap, s2, s1);
        }
    }

    public void setEvents(String s1)
    {
        m = cleanCommaDelimitedString(s1);
    }

    public void setGeoState(String s1)
    {
        o = s1;
    }

    public void setGeoZip(String s1)
    {
        n = s1;
    }

    public void setHier(int i1, String s1)
    {
        if (ADMS_DefaultValues.hierValid(i1))
        {
            String s2 = (new StringBuilder("h")).append(i1).toString();
            putStringParameter(hierMap, s2, s1);
        }
    }

    protected void setLightIncrementBy(int i1)
    {
        x = i1;
    }

    protected void setLightProfileID(String s1)
    {
        v = s1;
    }

    protected void setLightStoreForSeconds(int i1)
    {
        w = i1;
    }

    public void setLightTrackVars(String s1)
    {
        p = cleanCommaDelimitedString(s1);
    }

    protected void setLinkName(String s1)
    {
        t = s1;
    }

    public void setLinkTrackEvents(String s1)
    {
        r = cleanCommaDelimitedString(s1);
    }

    public void setLinkTrackVars(String s1)
    {
        q = cleanCommaDelimitedString(s1);
    }

    protected void setLinkType(String s1)
    {
        u = s1;
    }

    protected void setLinkURL(String s1)
    {
        s = s1;
    }

    public void setListVar(int i1, String s1)
    {
        if (ADMS_DefaultValues.listValid(i1))
        {
            String s2 = (new StringBuilder("l")).append(i1).toString();
            putStringParameter(listMap, s2, s1);
        }
    }

    public void setOffline()
    {
        isOffline = true;
    }

    public void setOfflineHitLimit(int i1)
    {
        offlineHitLimit = i1;
    }

    public abstract void setOfflineThrottleDelay(int i1);

    public void setOfflineTrackingEnabled(boolean flag)
    {
        offlineTrackingEnabled = flag;
    }

    public void setOnline()
    {
        isOffline = false;
    }

    public void setPersistentContextData(Hashtable hashtable)
    {
        if (persistentContextData == null)
        {
            persistentContextData = new Hashtable();
        }
        persistentContextData.clear();
        if (hashtable != null)
        {
            String s1;
            Object obj;
            for (Enumeration enumeration = hashtable.keys(); enumeration.hasMoreElements(); persistentContextData.put(cleanContextKey(s1), obj))
            {
                s1 = (String)enumeration.nextElement();
                obj = hashtable.get(s1);
            }

        }
    }

    public void setProducts(String s1)
    {
        if (isSet(s1))
        {
            l = s1.replaceAll(", ", ",");
            l = l.replaceAll(" ,", ",");
            return;
        } else
        {
            l = s1;
            return;
        }
    }

    public void setProp(int i1, String s1)
    {
        if (ADMS_DefaultValues.propValid(i1))
        {
            String s2 = (new StringBuilder("c")).append(i1).toString();
            putStringParameter(propMap, s2, s1);
        }
    }

    public void setPurchaseID(String s1)
    {
        f = s1;
    }

    public void setReportSuiteIDs(String s1)
    {
        a = cleanCommaDelimitedString(s1);
    }

    public void setSSL(boolean flag)
    {
        ssl = flag;
    }

    public void setTrackingServer(String s1)
    {
        b = s1;
    }

    public void setTransactionID(String s1)
    {
        g = s1;
    }

    public void setVisitorID(String s1)
    {
        c = s1;
    }

    protected void setupDefaults()
    {
        version = "JAVA-3.0.5-AN";
        evarMap = new Hashtable();
        propMap = new Hashtable();
        pevMap = new Hashtable();
        hierMap = new Hashtable();
        listMap = new Hashtable();
        persistentContextData = new Hashtable();
        offlineTrackingEnabled = false;
        offlineHitLimit = 1000;
        ssl = false;
        b = null;
        try
        {
            A = SecureRandom.getInstance("SHA1PRNG");
            return;
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            A = null;
            debugLog((new StringBuilder("Unable to create secure random number generator : ")).append(nosuchalgorithmexception.getMessage()).toString());
            return;
        }
    }

    protected boolean toBoolean(Object obj)
    {
        return ((Boolean)obj).booleanValue();
    }

    protected int toInteger(Object obj)
    {
        return ((Integer)obj).intValue();
    }

    public void track()
    {
        track(null);
    }

    public void track(Hashtable hashtable)
    {
        track(hashtable, null);
    }

    public void track(Hashtable hashtable, Hashtable hashtable1)
    {
        this;
        JVM INSTR monitorenter ;
        if (isSet(a))
        {
            break MISSING_BLOCK_LABEL_16;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        sendRequest(getRequestString(hashtable, hashtable1));
        resetSingleUseParameters();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void trackAppState(String s1)
    {
        trackAppState(s1, null);
    }

    public void trackAppState(String s1, Hashtable hashtable)
    {
        Hashtable hashtable1 = new Hashtable();
        hashtable1.put("pageName", s1);
        track(hashtable, hashtable1);
    }

    public void trackEvents(String s1)
    {
        trackEvents(s1, null);
    }

    public void trackEvents(String s1, Hashtable hashtable)
    {
        Hashtable hashtable1 = new Hashtable();
        hashtable1.put("events", s1);
        trackLink(null, "o", (new StringBuilder()).append(getApplicationID()).append(" Event").toString(), hashtable, hashtable1);
    }

    public void trackLight(String s1, int i1, int j1, Hashtable hashtable, Hashtable hashtable1)
    {
        setLightProfileID(s1);
        setLightStoreForSeconds(i1);
        setLightIncrementBy(j1);
        track(hashtable, hashtable1);
    }

    public void trackLink(String s1, String s2, String s3, Hashtable hashtable, Hashtable hashtable1)
    {
        setLinkURL(s1);
        setLinkType(s2);
        setLinkName(s3);
        track(hashtable, hashtable1);
    }

    protected ADMS_ContextData translateContextData(Hashtable hashtable, ArrayList arraylist)
    {
        ADMS_ContextData adms_contextdata = new ADMS_ContextData();
        Enumeration enumeration = hashtable.keys();
        do
        {
            if (!enumeration.hasMoreElements())
            {
                break;
            }
            String s1 = (String)enumeration.nextElement();
            if (arraylist == null || arraylist.contains((new StringBuilder("contextdata.")).append(s1.toLowerCase()).toString()))
            {
                ArrayList arraylist1 = new ArrayList();
                arraylist1.addAll(Arrays.asList(s1.split("[.]")));
                a(hashtable.get(s1), adms_contextdata, arraylist1, 0);
            }
        } while (true);
        return adms_contextdata;
    }

    protected String urlEncode(String s1)
    {
        StringBuffer stringbuffer;
        int i1;
        char c1;
        if (s1 == null)
        {
            return null;
        }
        String s2;
        String s4;
        try
        {
            s2 = new String(s1.getBytes("UTF-8"), "ISO-8859-1");
            stringbuffer = new StringBuffer();
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            debugLog((new StringBuilder("UnsupportedEncodingException : ")).append(unsupportedencodingexception.getMessage()).toString());
            return null;
        }
        i1 = 0;
_L9:
        if (i1 >= s2.length()) goto _L2; else goto _L1
_L1:
        c1 = s2.charAt(i1);
          goto _L3
_L7:
        if (".-*_".indexOf(c1) < 0) goto _L5; else goto _L4
_L4:
        stringbuffer.append(c1);
          goto _L6
_L5:
        s4 = Integer.toString(c1, 16).toUpperCase();
        if (s4.length() == 1)
        {
            s4 = (new StringBuilder("0")).append(s4).toString();
        }
        stringbuffer.append((new StringBuilder("%")).append(s4).toString());
          goto _L6
_L2:
        String s3 = stringbuffer.toString();
        return s3;
_L3:
        if (c1 >= 'a' && c1 <= 'z' || c1 >= 'A' && c1 <= 'Z' || c1 >= '0' && c1 <= '9') goto _L4; else goto _L7
_L6:
        i1++;
        if (true) goto _L9; else goto _L8
_L8:
    }

    protected void validateLinkTracking(Hashtable hashtable)
    {
        if (isSet(u) && (isSet(s) || isSet(t)))
        {
            u = u.toLowerCase();
            if (!u.equalsIgnoreCase("d") && !u.equalsIgnoreCase("e"))
            {
                u = "o";
            }
            putParameter(hashtable, "pe", (new StringBuilder("lnk_")).append(u).toString());
            putParameter(hashtable, "pev1", s);
            putParameter(hashtable, "pev2", t);
        }
    }

    protected void validateRequiredLightVariables(Hashtable hashtable)
    {
        if (!hashtable.containsKey("ce"))
        {
            putParameter(hashtable, "ce", getDefaultCharSet());
        }
    }

    protected void validateRequiredVariables(Hashtable hashtable)
    {
        if (!hashtable.containsKey("vid"))
        {
            putParameter(hashtable, "vid", getDefaultVisitorID());
        }
        if (!hashtable.containsKey("ce"))
        {
            putParameter(hashtable, "ce", getDefaultCharSet());
        }
        if (!hashtable.containsKey("pageName") && !hashtable.containsKey("g"))
        {
            putParameter(hashtable, "pageName", getApplicationID());
        }
    }

    protected void validateTechnology()
    {
        synchronized (persistentContextData)
        {
            persistentContextData.put("a.Resolution", getResolutionString());
            persistentContextData.put("a.DeviceName", getPlatformString());
            persistentContextData.put("a.OSVersion", getOperatingSystemString());
            persistentContextData.put("a.CarrierName", getCarrierString());
        }
    }

    protected void validateTimestamps(Hashtable hashtable)
    {
        Date date = new Date();
        if (timestamp != 0L || !offlineTrackingEnabled) goto _L2; else goto _L1
_L1:
        putParameter(hashtable, "ts", (new StringBuilder()).append((long)Math.floor(getTime() / 1000D)).toString());
_L4:
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        putParameter(hashtable, "t", (new StringBuilder()).append(calendar.get(5)).append("/").append(calendar.get(2)).append("/").append(calendar.get(1)).append(" ").append(calendar.get(11)).append(":").append(calendar.get(12)).append(":").append(calendar.get(13)).append(" ").append(-1 + calendar.get(7)).append(" ").append(-(calendar.getTimeZone().getOffset(1, calendar.get(1), calendar.get(2), calendar.get(5), calendar.get(7), 1000 * (60 * (60 * calendar.get(11) + calendar.get(12)) + calendar.get(13)) + calendar.get(14)) / 60000)).toString());
        return;
_L2:
        if (timestamp != 0L)
        {
            date = new Date(timestamp);
            putParameter(hashtable, "ts", (new StringBuilder()).append(timestamp).toString());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

}
