// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.facebook.FacebookException;
import com.facebook.internal.ImageDownloader;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.ImageResponse;
import com.facebook.model.GraphObject;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

// Referenced classes of package com.facebook.widget:
//            GraphObjectCursor

class GraphObjectAdapter extends BaseAdapter
    implements SectionIndexer
{

    static final boolean $assertionsDisabled = false;
    private static final int ACTIVITY_CIRCLE_VIEW_TYPE = 2;
    private static final int DISPLAY_SECTIONS_THRESHOLD = 1;
    private static final int GRAPH_OBJECT_VIEW_TYPE = 1;
    private static final int HEADER_VIEW_TYPE = 0;
    private static final String ID = "id";
    private static final int MAX_PREFETCHED_PICTURES = 20;
    private static final String NAME = "name";
    private static final String PICTURE = "picture";
    private Context context;
    private GraphObjectCursor cursor;
    private DataNeededListener dataNeededListener;
    private boolean displaySections;
    private Filter filter;
    private Map graphObjectsById;
    private Map graphObjectsBySection;
    private String groupByField;
    private final LayoutInflater inflater;
    private OnErrorListener onErrorListener;
    private final Map pendingRequests = new HashMap();
    private Map prefetchedPictureCache;
    private ArrayList prefetchedProfilePictureIds;
    private List sectionKeys;
    private boolean showCheckbox;
    private boolean showPicture;
    private List sortFields;

    public GraphObjectAdapter(Context context1)
    {
        sectionKeys = new ArrayList();
        graphObjectsBySection = new HashMap();
        graphObjectsById = new HashMap();
        prefetchedPictureCache = new HashMap();
        prefetchedProfilePictureIds = new ArrayList();
        context = context1;
        inflater = LayoutInflater.from(context1);
    }

    private void callOnErrorListener(Exception exception)
    {
        if (onErrorListener != null)
        {
            Object obj;
            if (!(exception instanceof FacebookException))
            {
                obj = new FacebookException(exception);
            } else
            {
                obj = exception;
            }
            onErrorListener.onError(this, (FacebookException)obj);
        }
    }

    private static int compareGraphObjects(GraphObject graphobject, GraphObject graphobject1, Collection collection, Collator collator)
    {
label0:
        {
            Iterator iterator = collection.iterator();
            String s1;
            String s2;
label1:
            do
            {
                int i;
                do
                {
                    if (!iterator.hasNext())
                    {
                        break label0;
                    }
                    String s = (String)iterator.next();
                    s1 = (String)graphobject.getProperty(s);
                    s2 = (String)graphobject1.getProperty(s);
                    if (s1 == null || s2 == null)
                    {
                        continue label1;
                    }
                    i = collator.compare(s1, s2);
                } while (i == 0);
                return i;
            } while (s1 == null && s2 == null);
            return s1 != null ? 1 : -1;
        }
        return 0;
    }

    private void downloadProfilePicture(final String profileId, URI uri, final ImageView imageView)
    {
        if (uri != null)
        {
            boolean flag;
            if (imageView == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag || !uri.equals(imageView.getTag()))
            {
                if (!flag)
                {
                    imageView.setTag(profileId);
                    imageView.setImageResource(getDefaultPicture());
                }
                ImageRequest imagerequest = (new com.facebook.internal.ImageRequest.Builder(context.getApplicationContext(), uri)).setCallerTag(this).setCallback(new _cls2()).build();
                pendingRequests.put(profileId, imagerequest);
                ImageDownloader.downloadAsync(imagerequest);
                return;
            }
        }
    }

    private View getActivityCircleView(View view, ViewGroup viewgroup)
    {
        if (view == null)
        {
            view = inflater.inflate(com.facebook.android.R.layout.com_facebook_picker_activity_circle_row, null);
        }
        ((ProgressBar)view.findViewById(com.facebook.android.R.id.com_facebook_picker_row_activity_circle)).setVisibility(0);
        return view;
    }

    private void processImageResponse(ImageResponse imageresponse, String s, ImageView imageview)
    {
        pendingRequests.remove(s);
        if (imageresponse.getError() != null)
        {
            callOnErrorListener(imageresponse.getError());
        }
        if (imageview == null)
        {
            if (imageresponse.getBitmap() != null)
            {
                if (prefetchedPictureCache.size() >= 20)
                {
                    String s1 = (String)prefetchedProfilePictureIds.remove(0);
                    prefetchedPictureCache.remove(s1);
                }
                prefetchedPictureCache.put(s, imageresponse);
            }
        } else
        if (s.equals(imageview.getTag()))
        {
            Exception exception = imageresponse.getError();
            android.graphics.Bitmap bitmap = imageresponse.getBitmap();
            if (exception == null && bitmap != null)
            {
                imageview.setImageBitmap(bitmap);
                imageview.setTag(imageresponse.getRequest().getImageUri());
                return;
            }
        }
    }

    private void rebuildSections()
    {
        sectionKeys = new ArrayList();
        graphObjectsBySection = new HashMap();
        graphObjectsById = new HashMap();
        displaySections = false;
        if (cursor == null || cursor.getCount() == 0)
        {
            return;
        }
        cursor.moveToFirst();
        int i = 0;
        do
        {
            GraphObject graphobject = cursor.getGraphObject();
            int j;
            if (filterIncludesItem(graphobject))
            {
                j = i + 1;
                String s = getSectionKeyOfGraphObject(graphobject);
                if (!graphObjectsBySection.containsKey(s))
                {
                    sectionKeys.add(s);
                    graphObjectsBySection.put(s, new ArrayList());
                }
                ((List)graphObjectsBySection.get(s)).add(graphobject);
                graphObjectsById.put(getIdOfGraphObject(graphobject), graphobject);
            } else
            {
                j = i;
            }
            if (!cursor.moveToNext())
            {
                if (sortFields != null)
                {
                    final Collator collator = Collator.getInstance();
                    for (Iterator iterator = graphObjectsBySection.values().iterator(); iterator.hasNext(); Collections.sort((List)iterator.next(), new _cls1())) { }
                }
                Collections.sort(sectionKeys, Collator.getInstance());
                int k = sectionKeys.size();
                boolean flag = false;
                if (k > 1)
                {
                    flag = false;
                    if (j > 1)
                    {
                        flag = true;
                    }
                }
                displaySections = flag;
                return;
            }
            i = j;
        } while (true);
    }

    private boolean shouldShowActivityCircleCell()
    {
        return cursor != null && cursor.areMoreObjectsAvailable() && dataNeededListener != null && !isEmpty();
    }

    public boolean areAllItemsEnabled()
    {
        return displaySections;
    }

    public boolean changeCursor(GraphObjectCursor graphobjectcursor)
    {
        if (cursor == graphobjectcursor)
        {
            return false;
        }
        if (cursor != null)
        {
            cursor.close();
        }
        cursor = graphobjectcursor;
        rebuildAndNotify();
        return true;
    }

    protected View createGraphObjectView(GraphObject graphobject)
    {
        View view = inflater.inflate(getGraphObjectRowLayoutId(graphobject), null);
        ViewStub viewstub = (ViewStub)view.findViewById(com.facebook.android.R.id.com_facebook_picker_checkbox_stub);
        ViewStub viewstub1;
        if (viewstub != null)
        {
            if (!getShowCheckbox())
            {
                viewstub.setVisibility(8);
            } else
            {
                updateCheckboxState((CheckBox)viewstub.inflate(), false);
            }
        }
        viewstub1 = (ViewStub)view.findViewById(com.facebook.android.R.id.com_facebook_picker_profile_pic_stub);
        if (!getShowPicture())
        {
            viewstub1.setVisibility(8);
            return view;
        } else
        {
            ((ImageView)viewstub1.inflate()).setVisibility(0);
            return view;
        }
    }

    boolean filterIncludesItem(GraphObject graphobject)
    {
        return filter == null || filter.includeItem(graphobject);
    }

    public int getCount()
    {
        if (sectionKeys.size() == 0)
        {
            return 0;
        }
        boolean flag = displaySections;
        int i = 0;
        if (flag)
        {
            i = sectionKeys.size();
        }
        Iterator iterator = graphObjectsBySection.values().iterator();
        int j;
        for (j = i; iterator.hasNext(); j += ((List)iterator.next()).size()) { }
        if (shouldShowActivityCircleCell())
        {
            j++;
        }
        return j;
    }

    public GraphObjectCursor getCursor()
    {
        return cursor;
    }

    public DataNeededListener getDataNeededListener()
    {
        return dataNeededListener;
    }

    protected int getDefaultPicture()
    {
        return com.facebook.android.R.drawable.com_facebook_profile_default_icon;
    }

    Filter getFilter()
    {
        return filter;
    }

    protected int getGraphObjectRowLayoutId(GraphObject graphobject)
    {
        return com.facebook.android.R.layout.com_facebook_picker_list_row;
    }

    protected View getGraphObjectView(GraphObject graphobject, View view, ViewGroup viewgroup)
    {
        if (view == null)
        {
            view = createGraphObjectView(graphobject);
        }
        populateGraphObjectView(view, graphobject);
        return view;
    }

    public List getGraphObjectsById(Collection collection)
    {
        HashSet hashset = new HashSet();
        hashset.addAll(collection);
        ArrayList arraylist = new ArrayList(hashset.size());
        Iterator iterator = hashset.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)iterator.next();
            GraphObject graphobject = (GraphObject)graphObjectsById.get(s);
            if (graphobject != null)
            {
                arraylist.add(graphobject);
            }
        } while (true);
        return arraylist;
    }

    public String getGroupByField()
    {
        return groupByField;
    }

    String getIdOfGraphObject(GraphObject graphobject)
    {
        if (graphobject.asMap().containsKey("id"))
        {
            Object obj = graphobject.getProperty("id");
            if (obj instanceof String)
            {
                return (String)obj;
            }
        }
        throw new FacebookException("Received an object without an ID.");
    }

    public Object getItem(int i)
    {
        SectionAndItem sectionanditem = getSectionAndItem(i);
        class SectionAndItem.Type extends Enum
        {

            private static final SectionAndItem.Type $VALUES[];
            public static final SectionAndItem.Type ACTIVITY_CIRCLE;
            public static final SectionAndItem.Type GRAPH_OBJECT;
            public static final SectionAndItem.Type SECTION_HEADER;

            public static SectionAndItem.Type valueOf(String s)
            {
                return (SectionAndItem.Type)Enum.valueOf(com/facebook/widget/GraphObjectAdapter$SectionAndItem$Type, s);
            }

            public static SectionAndItem.Type[] values()
            {
                return (SectionAndItem.Type[])$VALUES.clone();
            }

            static 
            {
                GRAPH_OBJECT = new SectionAndItem.Type("GRAPH_OBJECT", 0);
                SECTION_HEADER = new SectionAndItem.Type("SECTION_HEADER", 1);
                ACTIVITY_CIRCLE = new SectionAndItem.Type("ACTIVITY_CIRCLE", 2);
                SectionAndItem.Type atype[] = new SectionAndItem.Type[3];
                atype[0] = GRAPH_OBJECT;
                atype[1] = SECTION_HEADER;
                atype[2] = ACTIVITY_CIRCLE;
                $VALUES = atype;
            }

            private SectionAndItem.Type(String s, int i)
            {
                super(s, i);
            }
        }

        if (sectionanditem.getType() == SectionAndItem.Type.GRAPH_OBJECT)
        {
            return sectionanditem.graphObject;
        } else
        {
            return null;
        }
    }

    public long getItemId(int i)
    {
        SectionAndItem sectionanditem = getSectionAndItem(i);
        if (sectionanditem != null && sectionanditem.graphObject != null)
        {
            String s = getIdOfGraphObject(sectionanditem.graphObject);
            if (s != null)
            {
                return Long.parseLong(s);
            }
        }
        return 0L;
    }

    public int getItemViewType(int i)
    {
        SectionAndItem sectionanditem = getSectionAndItem(i);
        switch (_cls3..SwitchMap.com.facebook.widget.GraphObjectAdapter.SectionAndItem.Type[sectionanditem.getType().ordinal()])
        {
        default:
            throw new FacebookException("Unexpected type of section and item.");

        case 1: // '\001'
            return 0;

        case 2: // '\002'
            return 1;

        case 3: // '\003'
            return 2;
        }
    }

    public OnErrorListener getOnErrorListener()
    {
        return onErrorListener;
    }

    String getPictureFieldSpecifier()
    {
        ImageView imageview = (ImageView)createGraphObjectView(null).findViewById(com.facebook.android.R.id.com_facebook_picker_image);
        if (imageview == null)
        {
            return null;
        } else
        {
            android.view.ViewGroup.LayoutParams layoutparams = imageview.getLayoutParams();
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(layoutparams.height);
            aobj[1] = Integer.valueOf(layoutparams.width);
            return String.format("picture.height(%d).width(%d)", aobj);
        }
    }

    protected URI getPictureUriOfGraphObject(GraphObject graphobject)
    {
        Object obj = graphobject.getProperty("picture");
        if (!(obj instanceof String)) goto _L2; else goto _L1
_L1:
        String s = (String)obj;
_L6:
        if (s == null) goto _L4; else goto _L3
_L3:
        URI uri = new URI(s);
        return uri;
        urisyntaxexception;
_L4:
        return null;
_L2:
        URISyntaxException urisyntaxexception;
        if (obj instanceof JSONObject)
        {
            ItemPictureData itempicturedata = ((ItemPicture)com.facebook.model.GraphObject.Factory.create((JSONObject)obj).cast(com/facebook/widget/GraphObjectAdapter$ItemPicture)).getData();
            if (itempicturedata != null)
            {
                s = itempicturedata.getUrl();
                continue; /* Loop/switch isn't completed */
            }
        }
        s = null;
        if (true) goto _L6; else goto _L5
_L5:
    }

    int getPosition(String s, GraphObject graphobject)
    {
        Iterator iterator;
        int i;
        iterator = sectionKeys.iterator();
        i = 0;
_L5:
        String s1;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_171;
        }
        s1 = (String)iterator.next();
        if (displaySections)
        {
            i++;
        }
        if (!s1.equals(s)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L9:
        if (flag) goto _L4; else goto _L3
_L3:
        i = -1;
_L7:
        return i;
_L2:
        i += ((ArrayList)graphObjectsBySection.get(s1)).size();
          goto _L5
_L4:
        Iterator iterator1;
        if (graphobject == null)
        {
            boolean flag1 = displaySections;
            int j = 0;
            if (flag1)
            {
                j = 1;
            }
            return i - j;
        }
        iterator1 = ((ArrayList)graphObjectsBySection.get(s)).iterator();
_L8:
        if (!iterator1.hasNext())
        {
            break MISSING_BLOCK_LABEL_169;
        }
        if (com.facebook.model.GraphObject.Factory.hasSameId((GraphObject)iterator1.next(), graphobject)) goto _L7; else goto _L6
_L6:
        i++;
          goto _L8
          goto _L7
        return -1;
        flag = false;
          goto _L9
    }

    public int getPositionForSection(int i)
    {
        boolean flag = displaySections;
        int j = 0;
        if (flag)
        {
            int k = Math.max(0, Math.min(i, -1 + sectionKeys.size()));
            int l = sectionKeys.size();
            j = 0;
            if (k < l)
            {
                j = getPosition((String)sectionKeys.get(k), null);
            }
        }
        return j;
    }

    SectionAndItem getSectionAndItem(int i)
    {
        if (sectionKeys.size() == 0)
        {
            return null;
        }
        if (displaySections) goto _L2; else goto _L1
_L1:
        String s2;
        List list1;
        s2 = (String)sectionKeys.get(0);
        list1 = (List)graphObjectsBySection.get(s2);
        if (i < 0 || i >= list1.size()) goto _L4; else goto _L3
_L3:
        GraphObject graphobject;
        String s;
        graphobject = (GraphObject)((ArrayList)graphObjectsBySection.get(s2)).get(i);
        s = s2;
_L6:
        if (s != null)
        {
            return new SectionAndItem(s, graphobject);
        } else
        {
            throw new IndexOutOfBoundsException("position");
        }
_L4:
        if (!$assertionsDisabled && (dataNeededListener == null || !cursor.areMoreObjectsAvailable()))
        {
            throw new AssertionError();
        } else
        {
            return new SectionAndItem(null, null);
        }
_L2:
        Iterator iterator = sectionKeys.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s1 = (String)iterator.next();
            int j = i - 1;
            if (i == 0)
            {
                s = s1;
                graphobject = null;
                continue; /* Loop/switch isn't completed */
            }
            List list = (List)graphObjectsBySection.get(s1);
            if (j < list.size())
            {
                graphobject = (GraphObject)list.get(j);
                s = s1;
                continue; /* Loop/switch isn't completed */
            }
            i = j - list.size();
        } while (true);
        graphobject = null;
        s = null;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public int getSectionForPosition(int i)
    {
        SectionAndItem sectionanditem = getSectionAndItem(i);
        int j = 0;
        if (sectionanditem != null)
        {
            SectionAndItem.Type type = sectionanditem.getType();
            SectionAndItem.Type type1 = SectionAndItem.Type.ACTIVITY_CIRCLE;
            j = 0;
            if (type != type1)
            {
                j = Math.max(0, Math.min(sectionKeys.indexOf(sectionanditem.sectionKey), -1 + sectionKeys.size()));
            }
        }
        return j;
    }

    protected View getSectionHeaderView(String s, View view, ViewGroup viewgroup)
    {
        TextView textview = (TextView)view;
        TextView textview1;
        if (textview == null)
        {
            textview1 = (TextView)inflater.inflate(com.facebook.android.R.layout.com_facebook_picker_list_section_header, null);
        } else
        {
            textview1 = textview;
        }
        textview1.setText(s);
        return textview1;
    }

    protected String getSectionKeyOfGraphObject(GraphObject graphobject)
    {
        String s = groupByField;
        String s1 = null;
        if (s != null)
        {
            s1 = (String)graphobject.getProperty(groupByField);
            if (s1 != null && s1.length() > 0)
            {
                s1 = s1.substring(0, 1).toUpperCase();
            }
        }
        if (s1 != null)
        {
            return s1;
        } else
        {
            return "";
        }
    }

    public Object[] getSections()
    {
        if (displaySections)
        {
            return sectionKeys.toArray();
        } else
        {
            return new Object[0];
        }
    }

    public boolean getShowCheckbox()
    {
        return showCheckbox;
    }

    public boolean getShowPicture()
    {
        return showPicture;
    }

    public List getSortFields()
    {
        return sortFields;
    }

    protected CharSequence getSubTitleOfGraphObject(GraphObject graphobject)
    {
        return null;
    }

    protected CharSequence getTitleOfGraphObject(GraphObject graphobject)
    {
        return (String)graphobject.getProperty("name");
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        SectionAndItem sectionanditem = getSectionAndItem(i);
        switch (_cls3..SwitchMap.com.facebook.widget.GraphObjectAdapter.SectionAndItem.Type[sectionanditem.getType().ordinal()])
        {
        default:
            throw new FacebookException("Unexpected type of section and item.");

        case 1: // '\001'
            return getSectionHeaderView(sectionanditem.sectionKey, view, viewgroup);

        case 2: // '\002'
            return getGraphObjectView(sectionanditem.graphObject, view, viewgroup);

        case 3: // '\003'
            break;
        }
        if (!$assertionsDisabled && (!cursor.areMoreObjectsAvailable() || dataNeededListener == null))
        {
            throw new AssertionError();
        } else
        {
            dataNeededListener.onDataNeeded();
            return getActivityCircleView(view, viewgroup);
        }
    }

    public int getViewTypeCount()
    {
        return 3;
    }

    public boolean hasStableIds()
    {
        return true;
    }

    public boolean isEmpty()
    {
        return sectionKeys.size() == 0;
    }

    public boolean isEnabled(int i)
    {
        return getSectionAndItem(i).getType() == SectionAndItem.Type.GRAPH_OBJECT;
    }

    boolean isGraphObjectSelected(String s)
    {
        return false;
    }

    protected void populateGraphObjectView(View view, GraphObject graphobject)
    {
        String s;
        URI uri;
        ImageView imageview;
label0:
        {
            s = getIdOfGraphObject(graphobject);
            view.setTag(s);
            CharSequence charsequence = getTitleOfGraphObject(graphobject);
            TextView textview = (TextView)view.findViewById(com.facebook.android.R.id.com_facebook_picker_title);
            if (textview != null)
            {
                textview.setText(charsequence, android.widget.TextView.BufferType.SPANNABLE);
            }
            CharSequence charsequence1 = getSubTitleOfGraphObject(graphobject);
            TextView textview1 = (TextView)view.findViewById(com.facebook.android.R.id.picker_subtitle);
            if (textview1 != null)
            {
                if (charsequence1 != null)
                {
                    textview1.setText(charsequence1, android.widget.TextView.BufferType.SPANNABLE);
                    textview1.setVisibility(0);
                } else
                {
                    textview1.setVisibility(8);
                }
            }
            if (getShowCheckbox())
            {
                updateCheckboxState((CheckBox)view.findViewById(com.facebook.android.R.id.com_facebook_picker_checkbox), isGraphObjectSelected(s));
            }
            if (getShowPicture())
            {
                uri = getPictureUriOfGraphObject(graphobject);
                if (uri != null)
                {
                    imageview = (ImageView)view.findViewById(com.facebook.android.R.id.com_facebook_picker_image);
                    if (!prefetchedPictureCache.containsKey(s))
                    {
                        break label0;
                    }
                    ImageResponse imageresponse = (ImageResponse)prefetchedPictureCache.get(s);
                    imageview.setImageBitmap(imageresponse.getBitmap());
                    imageview.setTag(imageresponse.getRequest().getImageUri());
                }
            }
            return;
        }
        downloadProfilePicture(s, uri, imageview);
    }

    public void prioritizeViewRange(int i, int j, int k)
    {
        if (j >= i && sectionKeys.size() != 0)
        {
            for (int l = j; l >= 0; l--)
            {
                SectionAndItem sectionanditem2 = getSectionAndItem(l);
                if (sectionanditem2.graphObject == null)
                {
                    continue;
                }
                String s1 = getIdOfGraphObject(sectionanditem2.graphObject);
                ImageRequest imagerequest = (ImageRequest)pendingRequests.get(s1);
                if (imagerequest != null)
                {
                    ImageDownloader.prioritizeRequest(imagerequest);
                }
            }

            int i1 = Math.max(0, i - k);
            int j1 = Math.min(j + k, -1 + getCount());
            ArrayList arraylist = new ArrayList();
            for (; i1 < i; i1++)
            {
                SectionAndItem sectionanditem1 = getSectionAndItem(i1);
                if (sectionanditem1.graphObject != null)
                {
                    arraylist.add(sectionanditem1.graphObject);
                }
            }

            for (int k1 = j + 1; k1 <= j1; k1++)
            {
                SectionAndItem sectionanditem = getSectionAndItem(k1);
                if (sectionanditem.graphObject != null)
                {
                    arraylist.add(sectionanditem.graphObject);
                }
            }

            Iterator iterator = arraylist.iterator();
            while (iterator.hasNext()) 
            {
                GraphObject graphobject = (GraphObject)iterator.next();
                URI uri = getPictureUriOfGraphObject(graphobject);
                String s = getIdOfGraphObject(graphobject);
                boolean flag = prefetchedProfilePictureIds.remove(s);
                prefetchedProfilePictureIds.add(s);
                if (!flag)
                {
                    downloadProfilePicture(s, uri, null);
                }
            }
        }
    }

    public void rebuildAndNotify()
    {
        rebuildSections();
        notifyDataSetChanged();
    }

    public void setDataNeededListener(DataNeededListener dataneededlistener)
    {
        dataNeededListener = dataneededlistener;
    }

    void setFilter(Filter filter1)
    {
        filter = filter1;
    }

    public void setGroupByField(String s)
    {
        groupByField = s;
    }

    public void setOnErrorListener(OnErrorListener onerrorlistener)
    {
        onErrorListener = onerrorlistener;
    }

    public void setShowCheckbox(boolean flag)
    {
        showCheckbox = flag;
    }

    public void setShowPicture(boolean flag)
    {
        showPicture = flag;
    }

    public void setSortFields(List list)
    {
        sortFields = list;
    }

    void updateCheckboxState(CheckBox checkbox, boolean flag)
    {
    }

    static 
    {
        boolean flag;
        if (!com/facebook/widget/GraphObjectAdapter.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
    }




    private class OnErrorListener
    {

        public abstract void onError(GraphObjectAdapter graphobjectadapter, FacebookException facebookexception);
    }


    private class _cls2
        implements com.facebook.internal.ImageRequest.Callback
    {

        final GraphObjectAdapter this$0;
        final ImageView val$imageView;
        final String val$profileId;

        public void onCompleted(ImageResponse imageresponse)
        {
            processImageResponse(imageresponse, profileId, imageView);
        }

        _cls2()
        {
            this$0 = GraphObjectAdapter.this;
            profileId = s;
            imageView = imageview;
            super();
        }
    }


    private class _cls1
        implements Comparator
    {

        final GraphObjectAdapter this$0;
        final Collator val$collator;

        public int compare(GraphObject graphobject, GraphObject graphobject1)
        {
            return GraphObjectAdapter.compareGraphObjects(graphobject, graphobject1, sortFields, collator);
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((GraphObject)obj, (GraphObject)obj1);
        }

        _cls1()
        {
            this$0 = GraphObjectAdapter.this;
            collator = collator1;
            super();
        }
    }


    private class Filter
    {

        public abstract boolean includeItem(Object obj);
    }


    private class SectionAndItem
    {

        public GraphObject graphObject;
        public String sectionKey;

        public Type getType()
        {
            if (sectionKey == null)
            {
                return Type.ACTIVITY_CIRCLE;
            }
            if (graphObject == null)
            {
                return Type.SECTION_HEADER;
            } else
            {
                return Type.GRAPH_OBJECT;
            }
        }

        public SectionAndItem(String s, GraphObject graphobject)
        {
            sectionKey = s;
            graphObject = graphobject;
        }
    }


    private class _cls3
    {

        static final int $SwitchMap$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type[];

        static 
        {
            $SwitchMap$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type = new int[SectionAndItem.Type.values().length];
            try
            {
                $SwitchMap$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type[SectionAndItem.Type.SECTION_HEADER.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror) { }
            try
            {
                $SwitchMap$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type[SectionAndItem.Type.GRAPH_OBJECT.ordinal()] = 2;
            }
            catch (NoSuchFieldError nosuchfielderror1) { }
            try
            {
                $SwitchMap$com$facebook$widget$GraphObjectAdapter$SectionAndItem$Type[SectionAndItem.Type.ACTIVITY_CIRCLE.ordinal()] = 3;
            }
            catch (NoSuchFieldError nosuchfielderror2)
            {
                return;
            }
        }
    }


    private class ItemPicture
        implements GraphObject
    {

        public abstract ItemPictureData getData();
    }


    private class ItemPictureData
        implements GraphObject
    {

        public abstract String getUrl();
    }


    private class DataNeededListener
    {

        public abstract void onDataNeeded();
    }

}
