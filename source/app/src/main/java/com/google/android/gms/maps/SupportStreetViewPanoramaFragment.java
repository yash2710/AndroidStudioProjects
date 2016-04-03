// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.android.gms.maps:
//            StreetViewPanorama, StreetViewPanoramaOptions

public class SupportStreetViewPanoramaFragment extends Fragment
    implements TraceFieldInterface
{

    private StreetViewPanorama ZT;
    private final b aah = new b(this);

    public SupportStreetViewPanoramaFragment()
    {
    }

    public static SupportStreetViewPanoramaFragment newInstance()
    {
        return new SupportStreetViewPanoramaFragment();
    }

    public static SupportStreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions streetviewpanoramaoptions)
    {
        SupportStreetViewPanoramaFragment supportstreetviewpanoramafragment = new SupportStreetViewPanoramaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("StreetViewPanoramaOptions", streetviewpanoramaoptions);
        supportstreetviewpanoramafragment.setArguments(bundle);
        return supportstreetviewpanoramafragment;
    }

    public final StreetViewPanorama getStreetViewPanorama()
    {
        IStreetViewPanoramaFragmentDelegate istreetviewpanoramafragmentdelegate = jC();
        if (istreetviewpanoramafragmentdelegate != null)
        {
            IStreetViewPanoramaDelegate istreetviewpanoramadelegate;
            try
            {
                istreetviewpanoramadelegate = istreetviewpanoramafragmentdelegate.getStreetViewPanorama();
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
            if (istreetviewpanoramadelegate != null)
            {
                if (ZT == null || ZT.jB().asBinder() != istreetviewpanoramadelegate.asBinder())
                {
                    ZT = new StreetViewPanorama(istreetviewpanoramadelegate);
                }
                return ZT;
            }
        }
        return null;
    }

    protected IStreetViewPanoramaFragmentDelegate jC()
    {
        aah.jz();
        if (aah.gH() == null)
        {
            return null;
        } else
        {
            return ((a)aah.gH()).jC();
        }
    }

    public void onActivityCreated(Bundle bundle)
    {
        if (bundle != null)
        {
            bundle.setClassLoader(com/google/android/gms/maps/SupportStreetViewPanoramaFragment.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        com.google.android.gms.maps.b.a(aah, activity);
    }

    public void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("SupportStreetViewPanoramaFragment");
        TraceMachine.enterMethod(_nr_trace, "SupportStreetViewPanoramaFragment#onCreate", null);
_L1:
        super.onCreate(bundle);
        aah.onCreate(bundle);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "SupportStreetViewPanoramaFragment#onCreate", null);
          goto _L1
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TraceMachine.enterMethod(_nr_trace, "SupportStreetViewPanoramaFragment#onCreateView", null);
_L1:
        View view = aah.onCreateView(layoutinflater, viewgroup, bundle);
        TraceMachine.exitMethod();
        return view;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "SupportStreetViewPanoramaFragment#onCreateView", null);
          goto _L1
    }

    public void onDestroy()
    {
        aah.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView()
    {
        aah.onDestroyView();
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attributeset, Bundle bundle)
    {
        super.onInflate(activity, attributeset, bundle);
        com.google.android.gms.maps.b.a(aah, activity);
        Bundle bundle1 = new Bundle();
        aah.onInflate(activity, bundle1, bundle);
    }

    public void onLowMemory()
    {
        aah.onLowMemory();
        super.onLowMemory();
    }

    public void onPause()
    {
        aah.onPause();
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
        aah.onResume();
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        if (bundle != null)
        {
            bundle.setClassLoader(com/google/android/gms/maps/SupportStreetViewPanoramaFragment.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        aah.onSaveInstanceState(bundle);
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop()
    {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public void setArguments(Bundle bundle)
    {
        super.setArguments(bundle);
    }

    private class b extends com.google.android.gms.dynamic.a
    {

        private final Fragment FV;
        protected f ZF;
        private Activity og;

        static void a(b b1, Activity activity)
        {
            b1.setActivity(activity);
        }

        private void setActivity(Activity activity)
        {
            og = activity;
            jz();
        }

        protected void a(f f1)
        {
            ZF = f1;
            jz();
        }

        public void jz()
        {
            if (og == null || ZF == null || gH() != null)
            {
                break MISSING_BLOCK_LABEL_72;
            }
            MapsInitializer.initialize(og);
            IStreetViewPanoramaFragmentDelegate istreetviewpanoramafragmentdelegate = u.H(og).j(e.h(og));
            ZF.a(new a(FV, istreetviewpanoramafragmentdelegate));
            return;
            RemoteException remoteexception;
            remoteexception;
            throw new RuntimeRemoteException(remoteexception);
            GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception;
            googleplayservicesnotavailableexception;
        }

        b(Fragment fragment)
        {
            FV = fragment;
        }
    }


    private class a
        implements LifecycleDelegate
    {

        private final Fragment FV;
        private final IStreetViewPanoramaFragmentDelegate ZU;

        public IStreetViewPanoramaFragmentDelegate jC()
        {
            return ZU;
        }

        public void onCreate(Bundle bundle)
        {
            if (bundle != null)
            {
                break MISSING_BLOCK_LABEL_12;
            }
            bundle = new Bundle();
            Bundle bundle1 = FV.getArguments();
            if (bundle1 == null)
            {
                break MISSING_BLOCK_LABEL_45;
            }
            if (bundle1.containsKey("StreetViewPanoramaOptions"))
            {
                t.a(bundle, "StreetViewPanoramaOptions", bundle1.getParcelable("StreetViewPanoramaOptions"));
            }
            ZU.onCreate(bundle);
            return;
            RemoteException remoteexception;
            remoteexception;
            throw new RuntimeRemoteException(remoteexception);
        }

        public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
        {
            com.google.android.gms.dynamic.d d;
            try
            {
                d = ZU.onCreateView(e.h(layoutinflater), e.h(viewgroup), bundle);
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
            return (View)e.e(d);
        }

        public void onDestroy()
        {
            try
            {
                ZU.onDestroy();
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onDestroyView()
        {
            try
            {
                ZU.onDestroyView();
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle1)
        {
            try
            {
                ZU.onInflate(e.h(activity), null, bundle1);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onLowMemory()
        {
            try
            {
                ZU.onLowMemory();
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onPause()
        {
            try
            {
                ZU.onPause();
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onResume()
        {
            try
            {
                ZU.onResume();
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            try
            {
                ZU.onSaveInstanceState(bundle);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onStart()
        {
        }

        public void onStop()
        {
        }

        public a(Fragment fragment, IStreetViewPanoramaFragmentDelegate istreetviewpanoramafragmentdelegate)
        {
            ZU = (IStreetViewPanoramaFragmentDelegate)hm.f(istreetviewpanoramafragmentdelegate);
            FV = (Fragment)hm.f(fragment);
        }
    }

}
