package com.stanleyidesis.cordova.plugin;

import android.app.Activity;
import android.os.Bundle;

 //The LOQR API
                import io.loqr.loqrCMSDK.Loqr;
                import io.loqr.loqrCMSDK.OnLoqrRequestListener;
                import io.loqr.loqrCMSDK.error.LoqrException;
                import io.loqr.loqrOMSDK.LoqrOnBoarding;
                import io.loqr.loqrOMSDK.utils.OnLoqrOnboardingResult;

public class NewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String package_name = getApplication().getPackageName();
        setContentView(getApplication().getResources().getIdentifier("activity_new", "layout", package_name));
    }
    
    
    
    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.VIEW")) {
            //Example on how to validate the mobile for onboarding cases
            new LoqrOnBoarding(this).validatePhone("http://loqr.io/demo", intent.getDataString(), new OnPhoneValidation);
        }
    }
}