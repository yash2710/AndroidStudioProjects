// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;


public class HarvestResponse
{

    private int a;
    private String b;
    private long c;

    public HarvestResponse()
    {
    }

    public String getResponseBody()
    {
        return b;
    }

    public Code getResponseCode()
    {
        if (!isOK()) goto _L2; else goto _L1
_L1:
        Code code = Code.OK;
_L4:
        return code;
_L2:
        Code acode[] = Code.values();
        int i = acode.length;
        int j = 0;
label0:
        do
        {
label1:
            {
                if (j >= i)
                {
                    break label1;
                }
                code = acode[j];
                if (code.getStatusCode() == a)
                {
                    break label0;
                }
                j++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        return Code.UNKNOWN;
    }

    public long getResponseTime()
    {
        return c;
    }

    public int getStatusCode()
    {
        return a;
    }

    public boolean isDisableCommand()
    {
        return Code.FORBIDDEN == getResponseCode() && "DISABLE_NEW_RELIC".equals(getResponseBody());
    }

    public boolean isError()
    {
        return a >= 400;
    }

    public boolean isOK()
    {
        return !isError();
    }

    public boolean isUnknown()
    {
        return getResponseCode() == Code.UNKNOWN;
    }

    public void setResponseBody(String s)
    {
        b = s;
    }

    public void setResponseTime(long l)
    {
        c = l;
    }

    public void setStatusCode(int i)
    {
        a = i;
    }

    private class Code extends Enum
    {

        public static final Code ENTITY_TOO_LARGE;
        public static final Code FORBIDDEN;
        public static final Code INTERNAL_SERVER_ERROR;
        public static final Code INVALID_AGENT_ID;
        public static final Code OK;
        public static final Code UNAUTHORIZED;
        public static final Code UNKNOWN;
        public static final Code UNSUPPORTED_MEDIA_TYPE;
        private static final Code b[];
        private int a;

        public static Code valueOf(String s)
        {
            return (Code)Enum.valueOf(com/newrelic/agent/android/harvest/HarvestResponse$Code, s);
        }

        public static Code[] values()
        {
            return (Code[])b.clone();
        }

        public final int getStatusCode()
        {
            return a;
        }

        public final boolean isError()
        {
            return this != OK;
        }

        public final boolean isOK()
        {
            return !isError();
        }

        static 
        {
            OK = new Code("OK", 0, 200);
            UNAUTHORIZED = new Code("UNAUTHORIZED", 1, 401);
            FORBIDDEN = new Code("FORBIDDEN", 2, 403);
            ENTITY_TOO_LARGE = new Code("ENTITY_TOO_LARGE", 3, 413);
            INVALID_AGENT_ID = new Code("INVALID_AGENT_ID", 4, 450);
            UNSUPPORTED_MEDIA_TYPE = new Code("UNSUPPORTED_MEDIA_TYPE", 5, 415);
            INTERNAL_SERVER_ERROR = new Code("INTERNAL_SERVER_ERROR", 6, 500);
            UNKNOWN = new Code("UNKNOWN", 7, -1);
            Code acode[] = new Code[8];
            acode[0] = OK;
            acode[1] = UNAUTHORIZED;
            acode[2] = FORBIDDEN;
            acode[3] = ENTITY_TOO_LARGE;
            acode[4] = INVALID_AGENT_ID;
            acode[5] = UNSUPPORTED_MEDIA_TYPE;
            acode[6] = INTERNAL_SERVER_ERROR;
            acode[7] = UNKNOWN;
            b = acode;
        }

        private Code(String s, int i, int j)
        {
            super(s, i);
            a = j;
        }
    }

}
