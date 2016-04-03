// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, AddressBookParsedResult, ParsedResult

public final class VCardResultParser extends ResultParser
{

    private static final Pattern a = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern b = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");
    private static final Pattern c = Pattern.compile("\r\n[ \t]");
    private static final Pattern d = Pattern.compile("\\\\[nN]");
    private static final Pattern e = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern f = Pattern.compile("=");
    private static final Pattern g = Pattern.compile(";");
    private static final Pattern h = Pattern.compile("(?<!\\\\);+");
    private static final Pattern i = Pattern.compile(",");
    private static final Pattern j = Pattern.compile("[;,]");

    public VCardResultParser()
    {
    }

    private static String a(CharSequence charsequence, String s)
    {
        int k;
        StringBuilder stringbuilder;
        ByteArrayOutputStream bytearrayoutputstream;
        int l;
        k = charsequence.length();
        stringbuilder = new StringBuilder(k);
        bytearrayoutputstream = new ByteArrayOutputStream();
        l = 0;
_L8:
        if (l >= k) goto _L2; else goto _L1
_L1:
        char c1 = charsequence.charAt(l);
        c1;
        JVM INSTR lookupswitch 3: default 80
    //                   10: 94
    //                   13: 94
    //                   61: 100;
           goto _L3 _L4 _L4 _L5
_L4:
        break; /* Loop/switch isn't completed */
_L3:
        a(bytearrayoutputstream, s, stringbuilder);
        stringbuilder.append(c1);
_L6:
        l++;
        continue; /* Loop/switch isn't completed */
_L5:
        if (l < k - 2)
        {
            char c2 = charsequence.charAt(l + 1);
            if (c2 != '\r' && c2 != '\n')
            {
                char c3 = charsequence.charAt(l + 2);
                int i1 = parseHexDigit(c2);
                int j1 = parseHexDigit(c3);
                if (i1 >= 0 && j1 >= 0)
                {
                    bytearrayoutputstream.write(j1 + (i1 << 4));
                }
                l += 2;
            }
        }
        if (true) goto _L6; else goto _L2
_L2:
        a(bytearrayoutputstream, s, stringbuilder);
        return stringbuilder.toString();
        if (true) goto _L8; else goto _L7
_L7:
    }

    private static String a(List list)
    {
        if (list == null || list.isEmpty())
        {
            return null;
        } else
        {
            return (String)list.get(0);
        }
    }

    static List a(CharSequence charsequence, String s, boolean flag, boolean flag1)
    {
        int k;
        int l;
        ArrayList arraylist;
        k = 0;
        l = s.length();
        arraylist = null;
_L13:
        if (k >= l) goto _L2; else goto _L1
_L1:
        Matcher matcher;
        matcher = Pattern.compile((new StringBuilder("(?:^|\n)")).append(charsequence).append("(?:;([^:]*))?:").toString(), 2).matcher(s);
        if (k > 0)
        {
            k--;
        }
        if (!matcher.find(k)) goto _L2; else goto _L3
_L3:
        int i1;
        ArrayList arraylist1;
        boolean flag2;
        String s2;
        String as[];
        int l1;
        int i2;
        i1 = matcher.end(0);
        String s1 = matcher.group(1);
        arraylist1 = null;
        flag2 = false;
        s2 = null;
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_631;
        }
        as = g.split(s1);
        l1 = as.length;
        i2 = 0;
_L10:
        if (i2 >= l1) goto _L5; else goto _L4
_L4:
        String as1[];
        String s8 = as[i2];
        if (arraylist1 == null)
        {
            arraylist1 = new ArrayList(1);
        }
        arraylist1.add(s8);
        as1 = f.split(s8, 2);
        if (as1.length <= 1) goto _L7; else goto _L6
_L6:
        String s9;
        String s10;
        s9 = as1[0];
        s10 = as1[1];
        if (!"ENCODING".equalsIgnoreCase(s9) || !"QUOTED-PRINTABLE".equalsIgnoreCase(s10)) goto _L9; else goto _L8
_L8:
        boolean flag3 = true;
_L12:
        i2++;
        flag2 = flag3;
          goto _L10
_L9:
        if (!"CHARSET".equalsIgnoreCase(s9)) goto _L7; else goto _L11
_L11:
        s2 = s10;
        flag3 = flag2;
          goto _L12
_L5:
        String s3;
        ArrayList arraylist2;
        s3 = s2;
        arraylist2 = arraylist1;
_L14:
        int j1 = i1;
        int k1;
        do
        {
            k1 = s.indexOf('\n', j1);
            if (k1 < 0)
            {
                break;
            }
            if (k1 < -1 + s.length() && (s.charAt(k1 + 1) == ' ' || s.charAt(k1 + 1) == '\t'))
            {
                j1 = k1 + 2;
                continue;
            }
            if (!flag2 || (k1 <= 0 || s.charAt(k1 - 1) != '=') && (k1 < 2 || s.charAt(k1 - 2) != '='))
            {
                break;
            }
            j1 = k1 + 1;
        } while (true);
        if (k1 < 0)
        {
            k = l;
        } else
        if (k1 > i1)
        {
            if (arraylist == null)
            {
                arraylist = new ArrayList(1);
            }
            if (k1 > 0 && s.charAt(k1 - 1) == '\r')
            {
                k1--;
            }
            String s4 = s.substring(i1, k1);
            if (flag)
            {
                s4 = s4.trim();
            }
            String s7;
            if (flag2)
            {
                s7 = a(((CharSequence) (s4)), s3);
                if (flag1)
                {
                    s7 = h.matcher(s7).replaceAll("\n").trim();
                }
            } else
            {
                if (flag1)
                {
                    s4 = h.matcher(s4).replaceAll("\n").trim();
                }
                String s5 = c.matcher(s4).replaceAll("");
                String s6 = d.matcher(s5).replaceAll("\n");
                s7 = e.matcher(s6).replaceAll("$1");
            }
            if (arraylist2 == null)
            {
                ArrayList arraylist3 = new ArrayList(1);
                arraylist3.add(s7);
                arraylist.add(arraylist3);
            } else
            {
                arraylist2.add(0, s7);
                arraylist.add(arraylist2);
            }
            k = k1 + 1;
        } else
        {
            k = k1 + 1;
        }
          goto _L13
_L2:
        return arraylist;
_L7:
        flag3 = flag2;
          goto _L12
        s3 = null;
        flag2 = false;
        arraylist2 = null;
          goto _L14
    }

    private static void a(ByteArrayOutputStream bytearrayoutputstream, String s, StringBuilder stringbuilder)
    {
        if (bytearrayoutputstream.size() > 0)
        {
            byte abyte0[] = bytearrayoutputstream.toByteArray();
            String s1;
            if (s == null)
            {
                s1 = new String(abyte0);
            } else
            {
                try
                {
                    s1 = new String(abyte0, s);
                }
                catch (UnsupportedEncodingException unsupportedencodingexception)
                {
                    s1 = new String(abyte0);
                }
            }
            bytearrayoutputstream.reset();
            stringbuilder.append(s1);
        }
    }

    private static void a(String as[], int k, StringBuilder stringbuilder)
    {
        if (as[k] != null)
        {
            stringbuilder.append(' ');
            stringbuilder.append(as[k]);
        }
    }

    private static String[] a(Collection collection)
    {
        if (collection == null || collection.isEmpty())
        {
            return null;
        }
        ArrayList arraylist = new ArrayList(collection.size());
        Iterator iterator = collection.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)((List)iterator.next()).get(0);
            if (s != null && s.length() > 0)
            {
                arraylist.add(s);
            }
        } while (true);
        return (String[])arraylist.toArray(new String[collection.size()]);
    }

    static List b(CharSequence charsequence, String s, boolean flag, boolean flag1)
    {
        List list = a(charsequence, s, flag, flag1);
        if (list == null || list.isEmpty())
        {
            return null;
        } else
        {
            return (List)list.get(0);
        }
    }

    private static String[] b(Collection collection)
    {
        ArrayList arraylist;
        Iterator iterator;
        if (collection == null || collection.isEmpty())
        {
            return null;
        }
        arraylist = new ArrayList(collection.size());
        iterator = collection.iterator();
_L3:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        List list;
        int k;
        list = (List)iterator.next();
        k = 1;
_L4:
        String s;
        if (k >= list.size())
        {
            break MISSING_BLOCK_LABEL_163;
        }
        s = (String)list.get(k);
        int l = s.indexOf('=');
        if (l >= 0)
        {
label0:
            {
                if (!"TYPE".equalsIgnoreCase(s.substring(0, l)))
                {
                    break label0;
                }
                s = s.substring(l + 1);
            }
        }
_L5:
        arraylist.add(s);
          goto _L3
        k++;
          goto _L4
_L2:
        return (String[])arraylist.toArray(new String[collection.size()]);
        s = null;
          goto _L5
    }

    public final AddressBookParsedResult parse(Result result)
    {
        String s = getMassagedText(result);
        Matcher matcher = a.matcher(s);
        if (!matcher.find() || matcher.start() != 0)
        {
            return null;
        }
        List list = a("FN", s, true, false);
        List list1;
        List list2;
        String as[];
        List list3;
        List list4;
        List list5;
        List list6;
        List list7;
        List list8;
        List list9;
        List list10;
        List list11;
        List list12;
        List list13;
        String as1[];
        CharSequence charsequence;
        boolean flag;
        if (list == null)
        {
            List list14 = a("N", s, true, false);
            if (list14 != null)
            {
                List list15;
                StringBuilder stringbuilder;
                for (Iterator iterator = list14.iterator(); iterator.hasNext(); list15.set(0, stringbuilder.toString().trim()))
                {
                    list15 = (List)iterator.next();
                    String s1 = (String)list15.get(0);
                    String as2[] = new String[5];
                    int k = 0;
                    int l = 0;
                    do
                    {
                        if (l >= 4)
                        {
                            break;
                        }
                        int i1 = s1.indexOf(';', k);
                        if (i1 <= 0)
                        {
                            break;
                        }
                        as2[l] = s1.substring(k, i1);
                        l++;
                        k = i1 + 1;
                    } while (true);
                    as2[l] = s1.substring(k);
                    stringbuilder = new StringBuilder(100);
                    a(as2, 3, stringbuilder);
                    a(as2, 1, stringbuilder);
                    a(as2, 2, stringbuilder);
                    a(as2, 0, stringbuilder);
                    a(as2, 4, stringbuilder);
                }

            }
            list1 = list14;
        } else
        {
            list1 = list;
        }
        list2 = b("NICKNAME", s, true, false);
        if (list2 == null)
        {
            as = null;
        } else
        {
            as = i.split((CharSequence)list2.get(0));
        }
        list3 = a("TEL", s, true, false);
        list4 = a("EMAIL", s, true, false);
        list5 = b("NOTE", s, false, false);
        list6 = a("ADR", s, true, true);
        list7 = b("ORG", s, true, true);
        list8 = b("BDAY", s, true, false);
        if (list8 == null) goto _L2; else goto _L1
_L1:
        charsequence = (CharSequence)list8.get(0);
        if (charsequence == null || b.matcher(charsequence).matches())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L3
_L3:
        list9 = null;
_L5:
        list10 = b("TITLE", s, true, false);
        list11 = a("URL", s, true, false);
        list12 = b("IMPP", s, true, false);
        list13 = b("GEO", s, true, false);
        if (list13 == null)
        {
            as1 = null;
        } else
        {
            as1 = j.split((CharSequence)list13.get(0));
        }
        if (as1 != null && as1.length != 2)
        {
            as1 = null;
        }
        return new AddressBookParsedResult(a(list1), as, null, a(list3), b(list3), a(list4), b(list4), a(list12), a(list5), a(list6), b(list6), a(list7), a(list9), a(list10), a(list11), as1);
_L2:
        list9 = list8;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final volatile ParsedResult parse(Result result)
    {
        return parse(result);
    }

}
