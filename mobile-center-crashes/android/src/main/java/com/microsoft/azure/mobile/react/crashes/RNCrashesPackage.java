package com.microsoft.azure.mobile.react.crashes;

import android.app.Application;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RNCrashesPackage implements ReactPackage {

    private RNCrashesModule mCrashesModule;

    private static final String WHEN_TO_SEND_CRASHES_ASK_JAVASCRIPT = "ASK_JAVASCRIPT";

    public RNCrashesPackage(Application application, String crashListenerType) {
        // Construct the module up-front to enable crash reporting ASAP
        RNCrashesUtils.logDebug("Creating crashes module with crashListener " + crashListenerType);
        boolean automaticProcessing = !crashListenerType.equals(WHEN_TO_SEND_CRASHES_ASK_JAVASCRIPT);
        this.mCrashesModule = new RNCrashesModule(application, automaticProcessing);
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        // enable JS integrations from this point
        this.mCrashesModule.setReactApplicationContext(reactContext);

        RNCrashesUtils.logDebug("Returning list containing crashes module");
        List<NativeModule> modules = new ArrayList<NativeModule>();
        modules.add(this.mCrashesModule);
        return modules;
    }

    // No @Override to support applications using React Native 0.47.0 or later
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}