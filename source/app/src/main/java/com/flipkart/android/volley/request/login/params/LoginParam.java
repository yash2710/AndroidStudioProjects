// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.login.params;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginParam
{

    private LoginType a;
    private String b;
    private String c;
    private String d;

    public LoginParam(LoginType logintype, String s)
    {
        a = logintype;
        d = s;
    }

    public LoginParam(String s, String s1)
    {
        a = LoginType.FLIPKART;
        b = s;
        c = s1;
    }

    public byte[] generateToByteArray()
    {
label0:
        {
            {
                if (!a.equals(LoginType.FLIPKART))
                {
                    break label0;
                }
                StringBuilder stringbuilder = new StringBuilder();
                byte abyte0[];
                StringBuilder stringbuilder1;
                Exception exception;
                boolean flag;
                try
                {
                    if (b != null && b.length() > 0)
                    {
                        stringbuilder.append("email=").append(URLEncoder.encode(b, "UTF-8"));
                    }
                    if (c != null && c.length() > 0)
                    {
                        stringbuilder.append("&password=").append(URLEncoder.encode(c, "UTF-8"));
                    }
                }
                catch (UnsupportedEncodingException unsupportedencodingexception) { }
                abyte0 = stringbuilder.toString().getBytes();
            }
            return abyte0;
        }
        if (a.equals(LoginType.FACEBOOK))
        {
            break; /* Loop/switch isn't completed */
        }
        flag = a.equals(LoginType.GOOGLE);
        abyte0 = null;
        if (!flag) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_101;
_L1:
        stringbuilder1 = new StringBuilder();
        try
        {
            stringbuilder1.append("accessToken=").append(URLEncoder.encode(d, "UTF-8"));
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception) { }
        return stringbuilder1.toString().getBytes();
    }

    public String generateURI()
    {
        String s = "";
        if (a.equals(LoginType.FLIPKART))
        {
            s = "/login";
        } else
        {
            if (a.equals(LoginType.FACEBOOK))
            {
                return "/fbLogin";
            }
            if (a.equals(LoginType.GOOGLE))
            {
                return "/googleLogin";
            }
        }
        return s;
    }

    public String getAccessToken()
    {
        return d;
    }

    public LoginType getLoginType()
    {
        return a;
    }

    public String getPassword()
    {
        return c;
    }

    public String getUserName()
    {
        return b;
    }

    public void setAccessToken(String s)
    {
        d = s;
    }

    public void setLoginType(LoginType logintype)
    {
        a = logintype;
    }

    public void setPassword(String s)
    {
        c = s;
    }

    public void setUserName(String s)
    {
        b = s;
    }

    private class LoginType extends Enum
    {

        public static final LoginType FACEBOOK;
        public static final LoginType FLIPKART;
        public static final LoginType GOOGLE;
        private static final LoginType a[];

        public static LoginType valueOf(String s)
        {
            return (LoginType)Enum.valueOf(com/flipkart/android/volley/request/login/params/LoginParam$LoginType, s);
        }

        public static LoginType[] values()
        {
            return (LoginType[])a.clone();
        }

        static 
        {
            FLIPKART = new LoginType("FLIPKART", 0);
            GOOGLE = new LoginType("GOOGLE", 1);
            FACEBOOK = new LoginType("FACEBOOK", 2);
            LoginType alogintype[] = new LoginType[3];
            alogintype[0] = FLIPKART;
            alogintype[1] = GOOGLE;
            alogintype[2] = FACEBOOK;
            a = alogintype;
        }

        private LoginType(String s, int i)
        {
            super(s, i);
        }
    }

}
