// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.encode;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.BitMatrix;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

// Referenced classes of package com.google.zxing.client.android.encode:
//            a, h, c

final class g
{

    private final Activity a;
    private String b;
    private String c;
    private String d;
    private BarcodeFormat e;
    private final int f;
    private final boolean g;

    g(Activity activity, Intent intent, int i, boolean flag)
    {
        a = activity;
        f = i;
        g = flag;
        String s = intent.getAction();
        if (s.equals("com.google.zxing.client.android.ENCODE"))
        {
            a(intent);
        } else
        if (s.equals("android.intent.action.SEND"))
        {
            if (intent.hasExtra("android.intent.extra.STREAM"))
            {
                b(intent);
                return;
            }
            String s1 = com.google.zxing.client.android.encode.a.a(intent.getStringExtra("android.intent.extra.TEXT"));
            if (s1 == null)
            {
                s1 = com.google.zxing.client.android.encode.a.a(intent.getStringExtra("android.intent.extra.HTML_TEXT"));
                if (s1 == null)
                {
                    s1 = com.google.zxing.client.android.encode.a.a(intent.getStringExtra("android.intent.extra.SUBJECT"));
                    if (s1 == null)
                    {
                        String as[] = intent.getStringArrayExtra("android.intent.extra.EMAIL");
                        if (as != null)
                        {
                            s1 = com.google.zxing.client.android.encode.a.a(as[0]);
                        } else
                        {
                            s1 = "?";
                        }
                    }
                }
            }
            if (s1 == null || s1.length() == 0)
            {
                throw new WriterException("Empty EXTRA_TEXT");
            }
            b = s1;
            e = BarcodeFormat.QR_CODE;
            if (intent.hasExtra("android.intent.extra.SUBJECT"))
            {
                c = intent.getStringExtra("android.intent.extra.SUBJECT");
            } else
            if (intent.hasExtra("android.intent.extra.TITLE"))
            {
                c = intent.getStringExtra("android.intent.extra.TITLE");
            } else
            {
                c = b;
            }
            d = a.getString(com.google.zxing.client.android.R.string.contents_text);
            return;
        }
    }

    private static Iterable a(String as[])
    {
        if (as == null)
        {
            return null;
        } else
        {
            return Arrays.asList(as);
        }
    }

    private boolean a(Intent intent)
    {
        String s = intent.getStringExtra("ENCODE_FORMAT");
        e = null;
        String s1;
        Bundle bundle;
        float f1;
        float f2;
        Bundle bundle1;
        String s2;
        String s3;
        String s4;
        ArrayList arraylist;
        int i;
        ArrayList arraylist1;
        int j;
        String s5;
        Object obj;
        String s6;
        Object obj1;
        String as[];
        String s7;
        String s8;
        String s9;
        String s10;
        String s11;
        if (s != null)
        {
            try
            {
                e = BarcodeFormat.valueOf(s);
            }
            catch (IllegalArgumentException illegalargumentexception) { }
        }
        if (e != null && e != BarcodeFormat.QR_CODE)
        {
            break MISSING_BLOCK_LABEL_777;
        }
        s1 = intent.getStringExtra("ENCODE_TYPE");
        if (s1 != null && s1.length() != 0) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        e = BarcodeFormat.QR_CODE;
        if (!s1.equals("TEXT_TYPE"))
        {
            break; /* Loop/switch isn't completed */
        }
        s10 = intent.getStringExtra("ENCODE_DATA");
        if (s10 != null && s10.length() > 0)
        {
            b = s10;
            c = s10;
            d = a.getString(com.google.zxing.client.android.R.string.contents_text);
        }
_L4:
        if (b != null && b.length() > 0)
        {
            return true;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (s1.equals("EMAIL_TYPE"))
        {
            s9 = com.google.zxing.client.android.encode.a.a(intent.getStringExtra("ENCODE_DATA"));
            if (s9 != null)
            {
                b = (new StringBuilder("mailto:")).append(s9).toString();
                c = s9;
                d = a.getString(com.google.zxing.client.android.R.string.contents_email);
            }
        } else
        if (s1.equals("PHONE_TYPE"))
        {
            s8 = com.google.zxing.client.android.encode.a.a(intent.getStringExtra("ENCODE_DATA"));
            if (s8 != null)
            {
                b = (new StringBuilder("tel:")).append(s8).toString();
                c = PhoneNumberUtils.formatNumber(s8);
                d = a.getString(com.google.zxing.client.android.R.string.contents_phone);
            }
        } else
        if (s1.equals("SMS_TYPE"))
        {
            s7 = com.google.zxing.client.android.encode.a.a(intent.getStringExtra("ENCODE_DATA"));
            if (s7 != null)
            {
                b = (new StringBuilder("sms:")).append(s7).toString();
                c = PhoneNumberUtils.formatNumber(s7);
                d = a.getString(com.google.zxing.client.android.R.string.contents_sms);
            }
        } else
        if (s1.equals("CONTACT_TYPE"))
        {
            bundle1 = intent.getBundleExtra("ENCODE_DATA");
            if (bundle1 != null)
            {
                s2 = bundle1.getString("name");
                s3 = bundle1.getString("company");
                s4 = bundle1.getString("postal");
                arraylist = new ArrayList(Contents.PHONE_KEYS.length);
                for (i = 0; i < Contents.PHONE_KEYS.length; i++)
                {
                    arraylist.add(bundle1.getString(Contents.PHONE_KEYS[i]));
                }

                arraylist1 = new ArrayList(Contents.EMAIL_KEYS.length);
                for (j = 0; j < Contents.EMAIL_KEYS.length; j++)
                {
                    arraylist1.add(bundle1.getString(Contents.EMAIL_KEYS[j]));
                }

                s5 = bundle1.getString("URL_KEY");
                obj = null;
                if (s5 != null)
                {
                    obj = Collections.singletonList(s5);
                }
                s6 = bundle1.getString("NOTE_KEY");
                if (g)
                {
                    obj1 = new h();
                } else
                {
                    obj1 = new c();
                }
                as = ((a) (obj1)).encode(Collections.singleton(s2), s3, Collections.singleton(s4), arraylist, arraylist1, ((Iterable) (obj)), s6);
                if (as[1].length() > 0)
                {
                    b = as[0];
                    c = as[1];
                    d = a.getString(com.google.zxing.client.android.R.string.contents_contact);
                }
            }
        } else
        if (s1.equals("LOCATION_TYPE"))
        {
            bundle = intent.getBundleExtra("ENCODE_DATA");
            if (bundle != null)
            {
                f1 = bundle.getFloat("LAT", 3.402823E+38F);
                f2 = bundle.getFloat("LONG", 3.402823E+38F);
                if (f1 != 3.402823E+38F && f2 != 3.402823E+38F)
                {
                    b = (new StringBuilder("geo:")).append(f1).append(',').append(f2).toString();
                    c = (new StringBuilder()).append(f1).append(",").append(f2).toString();
                    d = a.getString(com.google.zxing.client.android.R.string.contents_location);
                }
            }
        }
          goto _L4
        s11 = intent.getStringExtra("ENCODE_DATA");
        if (s11 != null && s11.length() > 0)
        {
            b = s11;
            c = s11;
            d = a.getString(com.google.zxing.client.android.R.string.contents_text);
        }
          goto _L4
    }

    private void b(Intent intent)
    {
        Uri uri;
        e = BarcodeFormat.QR_CODE;
        Bundle bundle = intent.getExtras();
        if (bundle == null)
        {
            throw new WriterException("No extras");
        }
        uri = (Uri)bundle.getParcelable("android.intent.extra.STREAM");
        if (uri == null)
        {
            throw new WriterException("No EXTRA_STREAM");
        }
        InputStream inputstream;
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte0[];
        inputstream = a.getContentResolver().openInputStream(uri);
        bytearrayoutputstream = new ByteArrayOutputStream();
        abyte0 = new byte[2048];
_L1:
        int i = inputstream.read(abyte0);
label0:
        {
            if (i <= 0)
            {
                break label0;
            }
            try
            {
                bytearrayoutputstream.write(abyte0, 0, i);
            }
            catch (IOException ioexception)
            {
                throw new WriterException(ioexception);
            }
        }
          goto _L1
        byte abyte1[];
        String s;
        abyte1 = bytearrayoutputstream.toByteArray();
        s = new String(abyte1, 0, abyte1.length, "UTF-8");
        com.google.zxing.client.result.ParsedResult parsedresult = ResultParser.parseResult(new Result(s, abyte1, null, BarcodeFormat.QR_CODE));
        if (!(parsedresult instanceof AddressBookParsedResult))
        {
            throw new WriterException("Result was not an address");
        }
        AddressBookParsedResult addressbookparsedresult = (AddressBookParsedResult)parsedresult;
        Object obj;
        String as[];
        if (g)
        {
            obj = new h();
        } else
        {
            obj = new c();
        }
        as = ((a) (obj)).encode(a(addressbookparsedresult.getNames()), addressbookparsedresult.getOrg(), a(addressbookparsedresult.getAddresses()), a(addressbookparsedresult.getPhoneNumbers()), a(addressbookparsedresult.getEmails()), a(addressbookparsedresult.getURLs()), null);
        if (as[1].length() > 0)
        {
            b = as[0];
            c = as[1];
            d = a.getString(com.google.zxing.client.android.R.string.contents_contact);
        }
        if (b == null || b.length() == 0)
        {
            throw new WriterException("No content to encode");
        } else
        {
            return;
        }
    }

    final String a()
    {
        return b;
    }

    final String b()
    {
        return c;
    }

    final String c()
    {
        return d;
    }

    final boolean d()
    {
        return g;
    }

    final Bitmap e()
    {
        String s;
        int i;
        s = b;
        if (s == null)
        {
            return null;
        }
        i = 0;
_L8:
        if (i >= s.length()) goto _L2; else goto _L1
_L1:
        if (s.charAt(i) <= '\377') goto _L4; else goto _L3
_L3:
        String s1 = "UTF-8";
_L5:
        EnumMap enummap;
        BitMatrix bitmatrix;
        int j;
        int k;
        int ai[];
        int l;
        Bitmap bitmap;
        int i1;
        int j1;
        int k1;
        int l1;
        if (s1 != null)
        {
            enummap = new EnumMap(com/google/zxing/EncodeHintType);
            enummap.put(EncodeHintType.CHARACTER_SET, s1);
        } else
        {
            enummap = null;
        }
        try
        {
            bitmatrix = (new MultiFormatWriter()).encode(s, e, f, f, enummap);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return null;
        }
        j = bitmatrix.getWidth();
        k = bitmatrix.getHeight();
        ai = new int[j * k];
        l = 0;
_L6:
        if (l >= k)
        {
            break MISSING_BLOCK_LABEL_209;
        }
        i1 = l * j;
        j1 = 0;
        while (j1 < j) 
        {
            k1 = i1 + j1;
            if (bitmatrix.get(j1, l))
            {
                l1 = 0xff000000;
            } else
            {
                l1 = -1;
            }
            ai[k1] = l1;
            j1++;
        }
        break MISSING_BLOCK_LABEL_203;
_L4:
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        s1 = null;
          goto _L5
        l++;
          goto _L6
        bitmap = Bitmap.createBitmap(j, k, android.graphics.Bitmap.Config.ARGB_8888);
        bitmap.setPixels(ai, 0, j, 0, 0, j, k);
        return bitmap;
        if (true) goto _L8; else goto _L7
_L7:
    }

    static 
    {
        com/google/zxing/client/android/encode/g.getSimpleName();
    }
}
