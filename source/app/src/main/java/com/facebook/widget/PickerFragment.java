// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.Request;
import com.facebook.Session;
import com.facebook.internal.SessionTracker;
import com.facebook.model.GraphObject;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.facebook.widget:
//            GraphObjectAdapter, SimpleGraphObjectCursor

public abstract class PickerFragment extends Fragment
    implements TraceFieldInterface
{

    private static final String ACTIVITY_CIRCLE_SHOW_KEY = "com.facebook.android.PickerFragment.ActivityCircleShown";
    public static final String DONE_BUTTON_TEXT_BUNDLE_KEY = "com.facebook.widget.PickerFragment.DoneButtonText";
    public static final String EXTRA_FIELDS_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ExtraFields";
    private static final int PROFILE_PICTURE_PREFETCH_BUFFER = 5;
    private static final String SELECTION_BUNDLE_KEY = "com.facebook.android.PickerFragment.Selection";
    public static final String SHOW_PICTURES_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ShowPictures";
    public static final String SHOW_TITLE_BAR_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ShowTitleBar";
    public static final String TITLE_TEXT_BUNDLE_KEY = "com.facebook.widget.PickerFragment.TitleText";
    private ProgressBar activityCircle;
    GraphObjectAdapter adapter;
    private boolean appEventsLogged;
    private Button doneButton;
    private Drawable doneButtonBackground;
    private String doneButtonText;
    HashSet extraFields;
    private GraphObjectFilter filter;
    private final Class graphObjectClass;
    private final int layout;
    private ListView listView;
    private LoadingStrategy loadingStrategy;
    private OnDataChangedListener onDataChangedListener;
    private OnDoneButtonClickedListener onDoneButtonClickedListener;
    private OnErrorListener onErrorListener;
    private android.widget.AbsListView.OnScrollListener onScrollListener;
    private OnSelectionChangedListener onSelectionChangedListener;
    private SelectionStrategy selectionStrategy;
    private SessionTracker sessionTracker;
    private boolean showPictures;
    private boolean showTitleBar;
    private Drawable titleBarBackground;
    private String titleText;
    private TextView titleTextView;

    PickerFragment(Class class1, int i, Bundle bundle)
    {
        showPictures = true;
        showTitleBar = true;
        extraFields = new HashSet();
        onScrollListener = new _cls6();
        graphObjectClass = class1;
        layout = i;
        setPickerFragmentSettingsFromBundle(bundle);
    }

    private void clearResults()
    {
        boolean flag = true;
        if (adapter != null)
        {
            boolean flag1;
            if (!selectionStrategy.isEmpty())
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            if (adapter.isEmpty())
            {
                flag = false;
            }
            loadingStrategy.clearResults();
            selectionStrategy.clear();
            adapter.notifyDataSetChanged();
            if (flag && onDataChangedListener != null)
            {
                onDataChangedListener.onDataChanged(this);
            }
            if (flag1 && onSelectionChangedListener != null)
            {
                onSelectionChangedListener.onSelectionChanged(this);
            }
        }
    }

    private void inflateTitleBar(ViewGroup viewgroup)
    {
        ViewStub viewstub = (ViewStub)viewgroup.findViewById(com.facebook.android.R.id.com_facebook_picker_title_bar_stub);
        if (viewstub != null)
        {
            View view = viewstub.inflate();
            android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, -1);
            layoutparams.addRule(3, com.facebook.android.R.id.com_facebook_picker_title_bar);
            listView.setLayoutParams(layoutparams);
            if (titleBarBackground != null)
            {
                view.setBackgroundDrawable(titleBarBackground);
            }
            doneButton = (Button)viewgroup.findViewById(com.facebook.android.R.id.com_facebook_picker_done_button);
            if (doneButton != null)
            {
                doneButton.setOnClickListener(new _cls5());
                if (getDoneButtonText() != null)
                {
                    doneButton.setText(getDoneButtonText());
                }
                if (doneButtonBackground != null)
                {
                    doneButton.setBackgroundDrawable(doneButtonBackground);
                }
            }
            titleTextView = (TextView)viewgroup.findViewById(com.facebook.android.R.id.com_facebook_picker_title);
            if (titleTextView != null && getTitleText() != null)
            {
                titleTextView.setText(getTitleText());
            }
        }
    }

    private void loadDataSkippingRoundTripIfCached()
    {
        clearResults();
        Request request = getRequestForLoadData(getSession());
        if (request != null)
        {
            onLoadingData();
            loadingStrategy.startLoading(request);
        }
    }

    private void onListItemClick(ListView listview, View view, int i)
    {
        GraphObject graphobject = (GraphObject)listview.getItemAtPosition(i);
        String s = adapter.getIdOfGraphObject(graphobject);
        selectionStrategy.toggleSelection(s);
        adapter.notifyDataSetChanged();
        if (onSelectionChangedListener != null)
        {
            onSelectionChangedListener.onSelectionChanged(this);
        }
    }

    private void reprioritizeDownloads()
    {
        int i = listView.getLastVisiblePosition();
        if (i >= 0)
        {
            int j = listView.getFirstVisiblePosition();
            adapter.prioritizeViewRange(j, i, 5);
        }
    }

    private static void setAlpha(View view, float f)
    {
        AlphaAnimation alphaanimation = new AlphaAnimation(f, f);
        alphaanimation.setDuration(0L);
        alphaanimation.setFillAfter(true);
        view.startAnimation(alphaanimation);
    }

    private void setPickerFragmentSettingsFromBundle(Bundle bundle)
    {
        if (bundle != null)
        {
            showPictures = bundle.getBoolean("com.facebook.widget.PickerFragment.ShowPictures", showPictures);
            String s = bundle.getString("com.facebook.widget.PickerFragment.ExtraFields");
            if (s != null)
            {
                setExtraFields(Arrays.asList(s.split(",")));
            }
            showTitleBar = bundle.getBoolean("com.facebook.widget.PickerFragment.ShowTitleBar", showTitleBar);
            String s1 = bundle.getString("com.facebook.widget.PickerFragment.TitleText");
            if (s1 != null)
            {
                titleText = s1;
                if (titleTextView != null)
                {
                    titleTextView.setText(titleText);
                }
            }
            String s2 = bundle.getString("com.facebook.widget.PickerFragment.DoneButtonText");
            if (s2 != null)
            {
                doneButtonText = s2;
                if (doneButton != null)
                {
                    doneButton.setText(doneButtonText);
                }
            }
        }
    }

    abstract PickerFragmentAdapter createAdapter();

    abstract LoadingStrategy createLoadingStrategy();

    abstract SelectionStrategy createSelectionStrategy();

    void displayActivityCircle()
    {
        if (activityCircle != null)
        {
            layoutActivityCircle();
            activityCircle.setVisibility(0);
        }
    }

    boolean filterIncludesItem(GraphObject graphobject)
    {
        if (filter != null)
        {
            return filter.includeItem(graphobject);
        } else
        {
            return true;
        }
    }

    String getDefaultDoneButtonText()
    {
        return getString(com.facebook.android.R.string.com_facebook_picker_done_button_text);
    }

    String getDefaultTitleText()
    {
        return null;
    }

    public String getDoneButtonText()
    {
        if (doneButtonText == null)
        {
            doneButtonText = getDefaultDoneButtonText();
        }
        return doneButtonText;
    }

    public Set getExtraFields()
    {
        return new HashSet(extraFields);
    }

    public GraphObjectFilter getFilter()
    {
        return filter;
    }

    public OnDataChangedListener getOnDataChangedListener()
    {
        return onDataChangedListener;
    }

    public OnDoneButtonClickedListener getOnDoneButtonClickedListener()
    {
        return onDoneButtonClickedListener;
    }

    public OnErrorListener getOnErrorListener()
    {
        return onErrorListener;
    }

    public OnSelectionChangedListener getOnSelectionChangedListener()
    {
        return onSelectionChangedListener;
    }

    abstract Request getRequestForLoadData(Session session);

    List getSelectedGraphObjects()
    {
        return adapter.getGraphObjectsById(selectionStrategy.getSelectedIds());
    }

    public Session getSession()
    {
        return sessionTracker.getSession();
    }

    public boolean getShowPictures()
    {
        return showPictures;
    }

    public boolean getShowTitleBar()
    {
        return showTitleBar;
    }

    public String getTitleText()
    {
        if (titleText == null)
        {
            titleText = getDefaultTitleText();
        }
        return titleText;
    }

    void hideActivityCircle()
    {
        if (activityCircle != null)
        {
            activityCircle.clearAnimation();
            activityCircle.setVisibility(4);
        }
    }

    void layoutActivityCircle()
    {
        float f;
        if (!adapter.isEmpty())
        {
            f = 0.25F;
        } else
        {
            f = 1.0F;
        }
        setAlpha(activityCircle, f);
    }

    public void loadData(boolean flag)
    {
        if (!flag && loadingStrategy.isDataPresentOrLoading())
        {
            return;
        } else
        {
            loadDataSkippingRoundTripIfCached();
            return;
        }
    }

    void logAppEvents(boolean flag)
    {
    }

    public void onActivityCreated(Bundle bundle)
    {
label0:
        {
            super.onActivityCreated(bundle);
            sessionTracker = new SessionTracker(getActivity(), new _cls4());
            setSettingsFromBundle(bundle);
            loadingStrategy = createLoadingStrategy();
            loadingStrategy.attach(adapter);
            selectionStrategy = createSelectionStrategy();
            selectionStrategy.readSelectionFromBundle(bundle, "com.facebook.android.PickerFragment.Selection");
            if (showTitleBar)
            {
                inflateTitleBar((ViewGroup)getView());
            }
            if (activityCircle != null && bundle != null)
            {
                if (!bundle.getBoolean("com.facebook.android.PickerFragment.ActivityCircleShown", false))
                {
                    break label0;
                }
                displayActivityCircle();
            }
            return;
        }
        hideActivityCircle();
    }

    public void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("PickerFragment");
        TraceMachine.enterMethod(_nr_trace, "PickerFragment#onCreate", null);
_L1:
        super.onCreate(bundle);
        adapter = createAdapter();
        adapter.setFilter(new _cls1());
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "PickerFragment#onCreate", null);
          goto _L1
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TraceMachine.enterMethod(_nr_trace, "PickerFragment#onCreateView", null);
_L1:
        ViewGroup viewgroup1 = (ViewGroup)layoutinflater.inflate(layout, viewgroup, false);
        listView = (ListView)viewgroup1.findViewById(com.facebook.android.R.id.com_facebook_picker_list_view);
        listView.setOnItemClickListener(new _cls2());
        listView.setOnLongClickListener(new _cls3());
        listView.setOnScrollListener(onScrollListener);
        activityCircle = (ProgressBar)viewgroup1.findViewById(com.facebook.android.R.id.com_facebook_picker_activity_circle);
        setupViews(viewgroup1);
        listView.setAdapter(adapter);
        TraceMachine.exitMethod();
        return viewgroup1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "PickerFragment#onCreateView", null);
          goto _L1
    }

    public void onDetach()
    {
        super.onDetach();
        listView.setOnScrollListener(null);
        listView.setAdapter(null);
        loadingStrategy.detach();
        sessionTracker.stopTracking();
    }

    public void onInflate(Activity activity, AttributeSet attributeset, Bundle bundle)
    {
        super.onInflate(activity, attributeset, bundle);
        TypedArray typedarray = activity.obtainStyledAttributes(attributeset, com.facebook.android.R.styleable.com_facebook_picker_fragment);
        setShowPictures(typedarray.getBoolean(0, showPictures));
        String s = typedarray.getString(1);
        if (s != null)
        {
            setExtraFields(Arrays.asList(s.split(",")));
        }
        showTitleBar = typedarray.getBoolean(2, showTitleBar);
        titleText = typedarray.getString(3);
        doneButtonText = typedarray.getString(4);
        titleBarBackground = typedarray.getDrawable(5);
        doneButtonBackground = typedarray.getDrawable(6);
        typedarray.recycle();
    }

    void onLoadingData()
    {
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        saveSettingsToBundle(bundle);
        selectionStrategy.saveSelectionToBundle(bundle, "com.facebook.android.PickerFragment.Selection");
        if (activityCircle != null)
        {
            boolean flag;
            if (activityCircle.getVisibility() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            bundle.putBoolean("com.facebook.android.PickerFragment.ActivityCircleShown", flag);
        }
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    public void onStop()
    {
        ApplicationStateMonitor.getInstance().activityStopped();
        if (!appEventsLogged)
        {
            logAppEvents(false);
        }
        super.onStop();
    }

    void saveSettingsToBundle(Bundle bundle)
    {
        bundle.putBoolean("com.facebook.widget.PickerFragment.ShowPictures", showPictures);
        if (!extraFields.isEmpty())
        {
            bundle.putString("com.facebook.widget.PickerFragment.ExtraFields", TextUtils.join(",", extraFields));
        }
        bundle.putBoolean("com.facebook.widget.PickerFragment.ShowTitleBar", showTitleBar);
        bundle.putString("com.facebook.widget.PickerFragment.TitleText", titleText);
        bundle.putString("com.facebook.widget.PickerFragment.DoneButtonText", doneButtonText);
    }

    public void setArguments(Bundle bundle)
    {
        super.setArguments(bundle);
        setSettingsFromBundle(bundle);
    }

    public void setDoneButtonText(String s)
    {
        doneButtonText = s;
    }

    public void setExtraFields(Collection collection)
    {
        extraFields = new HashSet();
        if (collection != null)
        {
            extraFields.addAll(collection);
        }
    }

    public void setFilter(GraphObjectFilter graphobjectfilter)
    {
        filter = graphobjectfilter;
    }

    public void setOnDataChangedListener(OnDataChangedListener ondatachangedlistener)
    {
        onDataChangedListener = ondatachangedlistener;
    }

    public void setOnDoneButtonClickedListener(OnDoneButtonClickedListener ondonebuttonclickedlistener)
    {
        onDoneButtonClickedListener = ondonebuttonclickedlistener;
    }

    public void setOnErrorListener(OnErrorListener onerrorlistener)
    {
        onErrorListener = onerrorlistener;
    }

    public void setOnSelectionChangedListener(OnSelectionChangedListener onselectionchangedlistener)
    {
        onSelectionChangedListener = onselectionchangedlistener;
    }

    void setSelectionStrategy(SelectionStrategy selectionstrategy)
    {
        if (selectionstrategy != selectionStrategy)
        {
            selectionStrategy = selectionstrategy;
            if (adapter != null)
            {
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void setSession(Session session)
    {
        sessionTracker.setSession(session);
    }

    public void setSettingsFromBundle(Bundle bundle)
    {
        setPickerFragmentSettingsFromBundle(bundle);
    }

    public void setShowPictures(boolean flag)
    {
        showPictures = flag;
    }

    public void setShowTitleBar(boolean flag)
    {
        showTitleBar = flag;
    }

    public void setTitleText(String s)
    {
        titleText = s;
    }

    void setupViews(ViewGroup viewgroup)
    {
    }

    void updateAdapter(SimpleGraphObjectCursor simplegraphobjectcursor)
    {
        if (adapter != null)
        {
            View view = listView.getChildAt(1);
            int i = listView.getFirstVisiblePosition();
            if (i > 0)
            {
                i++;
            }
            GraphObjectAdapter.SectionAndItem sectionanditem = adapter.getSectionAndItem(i);
            int j;
            boolean flag;
            if (view != null && sectionanditem.getType() != GraphObjectAdapter.SectionAndItem.Type.ACTIVITY_CIRCLE)
            {
                j = view.getTop();
            } else
            {
                j = 0;
            }
            flag = adapter.changeCursor(simplegraphobjectcursor);
            if (view != null && sectionanditem != null)
            {
                int k = adapter.getPosition(sectionanditem.sectionKey, sectionanditem.graphObject);
                if (k != -1)
                {
                    listView.setSelectionFromTop(k, j);
                }
            }
            if (flag && onDataChangedListener != null)
            {
                onDataChangedListener.onDataChanged(this);
            }
        }
    }




/*
    static boolean access$202(PickerFragment pickerfragment, boolean flag)
    {
        pickerfragment.appEventsLogged = flag;
        return flag;
    }

*/






    private class _cls6
        implements android.widget.AbsListView.OnScrollListener
    {

        final PickerFragment this$0;

        public void onScroll(AbsListView abslistview, int i, int j, int k)
        {
            reprioritizeDownloads();
        }

        public void onScrollStateChanged(AbsListView abslistview, int i)
        {
        }

        _cls6()
        {
            this$0 = PickerFragment.this;
            super();
        }
    }


    private class SelectionStrategy
    {

        final PickerFragment this$0;

        abstract void clear();

        abstract Collection getSelectedIds();

        abstract boolean isEmpty();

        abstract boolean isSelected(String s);

        abstract void readSelectionFromBundle(Bundle bundle, String s);

        abstract void saveSelectionToBundle(Bundle bundle, String s);

        abstract boolean shouldShowCheckBoxIfUnselected();

        abstract void toggleSelection(String s);

        SelectionStrategy()
        {
            this$0 = PickerFragment.this;
            super();
        }
    }


    private class LoadingStrategy
    {

        protected static final int CACHED_RESULT_REFRESH_DELAY = 2000;
        protected GraphObjectAdapter adapter;
        protected GraphObjectPagingLoader loader;
        final PickerFragment this$0;

        public void attach(GraphObjectAdapter graphobjectadapter)
        {
            class _cls1
                implements android.support.v4.app.LoaderManager.LoaderCallbacks
            {

                final LoadingStrategy this$1;

                public Loader onCreateLoader(int i, Bundle bundle)
                {
                    return LoadingStrategy.this.onCreateLoader();
                }

                public void onLoadFinished(Loader loader1, SimpleGraphObjectCursor simplegraphobjectcursor)
                {
                    if (loader1 != loader)
                    {
                        throw new FacebookException("Received callback for unknown loader.");
                    } else
                    {
                        LoadingStrategy.this.onLoadFinished((GraphObjectPagingLoader)loader1, simplegraphobjectcursor);
                        return;
                    }
                }

                public volatile void onLoadFinished(Loader loader1, Object obj)
                {
                    onLoadFinished(loader1, (SimpleGraphObjectCursor)obj);
                }

                public void onLoaderReset(Loader loader1)
                {
                    if (loader1 != loader)
                    {
                        throw new FacebookException("Received callback for unknown loader.");
                    } else
                    {
                        onLoadReset((GraphObjectPagingLoader)loader1);
                        return;
                    }
                }

                _cls1()
                {
                    this$1 = LoadingStrategy.this;
                    super();
                }
            }

            loader = (GraphObjectPagingLoader)getLoaderManager().initLoader(0, null, new _cls1());
            class _cls2
                implements GraphObjectPagingLoader.OnErrorListener
            {

                final LoadingStrategy this$1;

                public void onError(FacebookException facebookexception, GraphObjectPagingLoader graphobjectpagingloader)
                {
                    hideActivityCircle();
                    if (onErrorListener != null)
                    {
                        onErrorListener.onError(_fld0, facebookexception);
                    }
                }

                _cls2()
                {
                    this$1 = LoadingStrategy.this;
                    super();
                }

                private class OnErrorListener
                {

                    public abstract void onError(PickerFragment pickerfragment, FacebookException facebookexception);
                }

            }

            loader.setOnErrorListener(new _cls2());
            adapter = graphobjectadapter;
            adapter.changeCursor(loader.getCursor());
            class _cls3
                implements GraphObjectAdapter.OnErrorListener
            {

                final LoadingStrategy this$1;

                public void onError(GraphObjectAdapter graphobjectadapter1, FacebookException facebookexception)
                {
                    if (onErrorListener != null)
                    {
                        onErrorListener.onError(_fld0, facebookexception);
                    }
                }

                _cls3()
                {
                    this$1 = LoadingStrategy.this;
                    super();
                }
            }

            adapter.setOnErrorListener(new _cls3());
        }

        public void clearResults()
        {
            if (loader != null)
            {
                loader.clearResults();
            }
        }

        public void detach()
        {
            adapter.setDataNeededListener(null);
            adapter.setOnErrorListener(null);
            loader.setOnErrorListener(null);
            loader = null;
            adapter = null;
        }

        public boolean isDataPresentOrLoading()
        {
            return !adapter.isEmpty() || loader.isLoading();
        }

        protected GraphObjectPagingLoader onCreateLoader()
        {
            return new GraphObjectPagingLoader(getActivity(), graphObjectClass);
        }

        protected void onLoadFinished(GraphObjectPagingLoader graphobjectpagingloader, SimpleGraphObjectCursor simplegraphobjectcursor)
        {
            updateAdapter(simplegraphobjectcursor);
        }

        protected void onLoadReset(GraphObjectPagingLoader graphobjectpagingloader)
        {
            adapter.changeCursor(null);
        }

        protected void onStartLoading(GraphObjectPagingLoader graphobjectpagingloader, Request request)
        {
            displayActivityCircle();
        }

        public void startLoading(Request request)
        {
            if (loader != null)
            {
                loader.startLoading(request, true);
                onStartLoading(loader, request);
            }
        }

        LoadingStrategy()
        {
            this$0 = PickerFragment.this;
            super();
        }
    }


    private class OnDataChangedListener
    {

        public abstract void onDataChanged(PickerFragment pickerfragment);
    }


    private class OnSelectionChangedListener
    {

        public abstract void onSelectionChanged(PickerFragment pickerfragment);
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        final PickerFragment this$0;

        public void onClick(View view)
        {
            logAppEvents(true);
            appEventsLogged = true;
            if (onDoneButtonClickedListener != null)
            {
                onDoneButtonClickedListener.onDoneButtonClicked(PickerFragment.this);
            }
        }

        _cls5()
        {
            this$0 = PickerFragment.this;
            super();
        }

        private class OnDoneButtonClickedListener
        {

            public abstract void onDoneButtonClicked(PickerFragment pickerfragment);
        }

    }


    private class GraphObjectFilter
    {

        public abstract boolean includeItem(Object obj);
    }


    private class _cls4
        implements com.facebook.Session.StatusCallback
    {

        final PickerFragment this$0;

        public void call(Session session, SessionState sessionstate, Exception exception)
        {
            if (!session.isOpened())
            {
                clearResults();
            }
        }

        _cls4()
        {
            this$0 = PickerFragment.this;
            super();
        }
    }


    private class _cls1
        implements GraphObjectAdapter.Filter
    {

        final PickerFragment this$0;

        public boolean includeItem(GraphObject graphobject)
        {
            return filterIncludesItem(graphobject);
        }

        public volatile boolean includeItem(Object obj)
        {
            return includeItem((GraphObject)obj);
        }

        _cls1()
        {
            this$0 = PickerFragment.this;
            super();
        }
    }


    private class _cls2
        implements android.widget.AdapterView.OnItemClickListener
    {

        final PickerFragment this$0;

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            onListItemClick((ListView)adapterview, view, i);
        }

        _cls2()
        {
            this$0 = PickerFragment.this;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnLongClickListener
    {

        final PickerFragment this$0;

        public boolean onLongClick(View view)
        {
            return false;
        }

        _cls3()
        {
            this$0 = PickerFragment.this;
            super();
        }
    }

}
