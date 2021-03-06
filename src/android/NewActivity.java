    package com.stanleyidesis.cordova.plugin;

    import com.stanleyidesis.cordova.plugin.ToastyPlugin;
    import android.app.Activity;
    import android.os.Bundle;
    import android.content.Intent;

    // The native Toast API
    import android.widget.Toast;

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
            //setContentView(getApplication().getResources().getIdentifier("activity_new", "layout", package_name));
             // Create the toast
            Toast toast = Toast.makeText(this, "Estou no OnCreate", Toast.LENGTH_LONG);
            // Display toast
            toast.show();
        }



        @Override
        public void onNewIntent(Intent intent) {
            super.onNewIntent(intent);
             Toast toast = Toast.makeText(this, "Estou no OnNewIntent", Toast.LENGTH_LONG);
            // Display toast
            toast.show();
            ToastyPlugin plugin = ToastyPlugin.getInstance();
            
            if (intent.getAction() != null && intent.getAction().equals("android.intent.action.VIEW")) {
                //Example on how to validate the mobile for onboarding cases
                //new LoqrOnBoarding(this).validatePhone("http://loqr.io/demo", intent.getDataString(), new OnPhoneValidation());
                Toast toast2 = Toast.makeText(this, "Tenho as confições todas e vou chamar o ValidateMobileNumberResponse", Toast.LENGTH_LONG);
                // Display toast
                toast2.show();
                plugin.validateMobileNumberResponse(intent.getDataString());
            }
        }
    }