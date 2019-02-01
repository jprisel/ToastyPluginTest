                package com.stanleyidesis.cordova.plugin;
                // The native Toast API
                import android.widget.Toast;
                import android.content.Context;
                // Cordova-required packages
                import org.apache.cordova.CallbackContext;
                import org.apache.cordova.CordovaPlugin;
                import org.apache.cordova.PluginResult;
                import org.json.JSONArray;
                import org.json.JSONException;
                import org.json.JSONObject;

                //The LOQR API
import io.loqr.loqrCMSDK.Loqr;
import io.loqr.loqrCMSDK.OnLoqrRequestListener;
import io.loqr.loqrCMSDK.error.LoqrException;
import io.loqr.loqrOMSDK.LoqrOnBoarding;
import io.loqr.loqrOMSDK.utils.OnLoqrOnboardingResult;




                //Permissions
                import android.support.v4.app.ActivityCompat;
                import android.Manifest;



                public class ToastyPlugin extends CordovaPlugin {
                  
                  private static final String DURATION_LONG = "long";
                  private  Context context = cordova.getActivity();
                  
                    @Override
                  public boolean execute(String action, JSONArray args,
                    final CallbackContext callbackContext) {

                      
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
                      else {
                          
                          if(action.equals("show"))
                          {
                              show(cordova.getActivity(), message,duration);
                             
                          }
                          else if(action.equals("initLoqr"))
                          {
                              initLoqr();
                              
                          }
                          else {
                              callbackContext.error("\"" + action + "\" is not a recognized action.");
                              return false;
                          }
                      }
                              
                          
                          
                          
                          
                         



                      PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
                      callbackContext.sendPluginResult(pluginResult);
                      return true;
                  }
                    
                    private static void CreateOnboarding (Context context, String email, String name) 
                    {
                        // Create the toast
                        Toast toast = Toast.makeText(context, "Vou criar o onboarding", Toast.LENGTH_LONG);
                       // Display toast
                        toast.show();

                        JSONObject customData = null;
                        try {
                            customData = new JSONObject().put("email", email).put("shortName", name).put("acceptPrivacyPolicy", true);
                        }  catch (Exception e) {
                        // Create the toast
                        Toast toast5 = Toast.makeText(context, "Vou criar o onboarding",
                        Toast.LENGTH_LONG);
                       // Display toast
                        toast5.show();


                      } 

                        OnLoqrOnboardingResult onboardingResult = new OnLoqrOnboardingResult()  {

                        @Override
                        public void onError(LoqrException e) {
                            String aux = "Fail";
                        }
                        @Override
                        public void onCompleted() {
                            String aux = "OLEEE";
                        }


                        };

                        LoqrOnBoarding onBoarding = new LoqrOnBoarding(context);
                        onBoarding.createProcess("+351", "912992454", customData, onboardingResult);
                    }

                    
                    private static void show(Context context, String message, String duration) {
                        // Create the toast
                        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
                       // Display toast
                        toast.show();
                    }

                    private static void initLoqr()
                    {
                        //final Context cont = context;
                        // Create the toast
                        Toast toast = Toast.makeText(context, "Vou tentar chamar o Loqr", Toast.LENGTH_LONG);
                       // Display toast
                        toast.show();

                      try {
                          new Loqr(context).initLoqr("LOQR_DEMO_ID", "LOQR_DEMO_KEY", "OLA", new OnLoqrRequestListener() {

                              @Override
                            public void loqrResponse(int requestCode, String message, Boolean status) {
                            if (status) {
                                 message = "FUNCIONOU!!";
                                 CreateOnboarding(context, "joao.rodrigues@infosistema.com", "Joao Rodrigues");
                            }

                            // Create the toast
                        Toast toast2 = Toast.makeText(context, message, Toast.LENGTH_LONG);
                        toast2.show();
                         }

                        },
                        0); //use a unique code to distinguish the request results
                      }
                      catch (Exception e) {
                         // Display toast
                        toast.show();


                      }

                    }
                }