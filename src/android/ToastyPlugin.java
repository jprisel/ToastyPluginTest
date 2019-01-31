package com.stanleyidesis.cordova.plugin;
// The native Toast API
import android.widget.Toast;
// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//The LOQR API
import io.loqr.loqrCMSDK.Loqr;
import io.loqr.loqrCMSDK.*;


//Permissions
import android.support.v4.app.ActivityCompat;
import android.Manifest;



public class ToastyPlugin extends CordovaPlugin {
  private static final String DURATION_LONG = "long";
  @Override
  public boolean execute(String action, JSONArray args,
    final CallbackContext callbackContext) {
      // Verify that the user sent a 'show' action
      if (!action.equals("show")) {
        callbackContext.error("\"" + action + "\" is not a recognized action.");
        return false;
      }
      String message;
      String duration;
      try {
        JSONObject options = args.getJSONObject(0);
        message = options.getString("message");
        duration = options.getString("duration");
      } catch (JSONException e) {
        callbackContext.error("Error encountered: " + e.getMessage());
        return false;
      }
      
      
      if (!Loqr.permissionsChecked(cordova.getActivity())) {
            // Create the toast
        Toast toast3 = Toast.makeText(cordova.getActivity(), "nao tenho permissões, vou pedir",
        Toast.LENGTH_LONG);
       // Display toast
        toast3.show();
          ActivityCompat.requestPermissions(cordova.getActivity(),
                new String[]{Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION},
                27 //use a unique code to distinguish the request results
     );
    }
 
    
      
     // Create the toast
      Toast toast = Toast.makeText(cordova.getActivity(), "Vou tentar chamar o Loqr",
        DURATION_LONG.equals(duration) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
       // Display toast
        toast.show();
      
      try {
          new Loqr(cordova.getActivity()).initLoqr("LOQR_DEMO_ID", "LOQR_DEMO_KEY", "OLA", new OnLoqrRequestListener() {
        @Override
        public void loqrResponse(int requestCode, String message, Boolean status) {
            if (status)
                message = "FUNCIONOU!!";
            
             
            // Create the toast
        Toast toast2 = Toast.makeText(cordova.getActivity(), message, Toast.LENGTH_LONG);
        toast2.show();
         }
              
        },
        0); //use a unique code to distinguish the request results
      }
      catch (Exception e) {
         // Display toast
        toast.show();
       
        
      }
      
      toast = Toast.makeText(cordova.getActivity(), message,
        DURATION_LONG.equals(duration) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
       
      // Display toast
      toast.show();
      
      // Create the toast
      //Toast toast = Toast.makeText(cordova.getActivity(), message,
        //DURATION_LONG.equals(duration) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
      // Display toast
      //toast.show();
      
      // Send a positive result to the callbackContext
      PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
      callbackContext.sendPluginResult(pluginResult);
      return true;
  }
}