                package com.stanleyidesis.cordova.plugin;


                
                // The native Toast API
                import android.widget.Toast;
                

                
                


                // Cordova-required packages
                import org.apache.cordova.CallbackContext;
                import org.apache.cordova.CordovaPlugin;
                import org.apache.cordova.CordovaActivity;
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
                import io.loqr.loqrOMSDK.utils.OnPhoneValidation;
                import io.loqr.loqrDAMSDK.camera.LoqrCameraCapture;
                import io.loqr.loqrDAMSDK.camera.CameraCaptureListener;
                import io.loqr.loqrDAMSDK.documentcapture.DocumentUtils;
                import io.loqr.loqrDAMSDK.camera.CaptureType;

                //Android components
                import android.app.Activity;
                import android.os.Looper;
                import android.os.Bundle;
                import android.content.Intent;
                import android.content.Context;
                import android.graphics.Bitmap;
                import java.io.File;
                import android.widget.FrameLayout;
                import android.view.View;


                //Permissions
                import android.support.v4.app.ActivityCompat;
                import android.Manifest;



                public class ToastyPlugin extends CordovaPlugin {
                  
                  private static final String DURATION_LONG = "long";
                  private  Context context;
                  public static ToastyPlugin toasty;
                  private LoqrOnBoarding onBoarding = null;
                  private CordovaActivity activity;
                  private FrameLayout frameLayout;
                  private FrameLayout.LayoutParams fullView;
                  
                    @Override
                  public boolean execute(String action, JSONArray args,
                    final CallbackContext callbackContext) {

                      context = cordova.getActivity();
                      activity = (CordovaActivity) cordova.getActivity();
                      toasty = this;
                      String message;
                      String duration;
                      
                      Toast toast6 = Toast.makeText(context, "entrei na App",
                        Toast.LENGTH_LONG);
                       // Display toast
                        toast6.show();
                      
                      if(activity == null)
                      {
                          toast6 = Toast.makeText(context, "Activity is null",
                        Toast.LENGTH_LONG);
                       // Display toast
                        toast6.show();
                      }
                      else {
                          toast6 = Toast.makeText(context, "Activity não é null, yes!",
                        Toast.LENGTH_SHORT);
                       // Display toast
                        toast6.show();
                      }
                     
                     
                      

                      if (!Loqr.permissionsChecked(cordova.getActivity())) {
                            // Create the toast
                        Toast toast3 = Toast.makeText(cordova.getActivity(), "nao tenho permissões, vou pedir",
                        Toast.LENGTH_LONG);
                       // Display toast
                        toast3.show();
                          ActivityCompat.requestPermissions(cordova.getActivity(),
                                new String[]{Manifest.permission.READ_PHONE_STATE,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.CAMERA},
                                27 //use a unique code to distinguish the request results
                        );

                    }
                      else {
                          toast6 = Toast.makeText(context, action,
                        Toast.LENGTH_LONG);
                       // Display toast
                        toast6.show();
                          
                          if(action.equals("show"))
                          {
                              try {
                                JSONObject options = args.getJSONObject(0);
                                message = options.getString("message");
                                duration = options.getString("duration");
                              } catch (JSONException e) {
                                    callbackContext.error("Error encountered: " + e.getMessage());
                                    return false;
                              }
                              show(cordova.getActivity(), message,duration);
                             
                          }
                          else if(action.equals("initLoqr"))
                          {
                               initLoqr();
                              
                          }
                          else if(action.equals("createOnboarding"))
                          {
                              createOnboarding("joao.rodrigues@infosistema.com", "Joao Rodrigues");
                              
                          }
                          else if(action.equals("bindDevice"))
                          {
                              bindDevice();
                          }
                          else if(action.equals("validateMobileNumber"))
                          {
                              validateMobileNumber();
                          }
                          else if(action.equals("initCamera"))
                          {
                              initCamera();
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
                    
                    public static ToastyPlugin getInstance()
                    {
                        return toasty;
                    }
                    
                    private  void createOnboarding (String email, String name) 
                    {
                        // Create the toast
                        Toast toast = Toast.makeText(context, "Vou criar o onboarding", Toast.LENGTH_LONG);
                       // Display toast
                        toast.show();

                        JSONObject customData = null;
                        try {
                            customData = new JSONObject().put("email", email).put("shortName", name).put("acceptPrivacyPolicy", true);
                        }  catch (Exception e) {
                       


                      } 

                        OnLoqrOnboardingResult onboardingResult = new OnLoqrOnboardingResult()  {

                        @Override
                        public void onError(LoqrException e) {
                            String aux = e.toString();
                             // Create the toast
                        Toast toast5 = Toast.makeText(context, aux,
                        Toast.LENGTH_LONG);
                       // Display toast
                        toast5.show();
                        }
                        @Override
                        public void onCompleted() {
                            String aux = "OLEEE";
                             // Create the toast
                        Toast toast5 = Toast.makeText(context, aux,
                        Toast.LENGTH_LONG);
                       // Display toast
                        toast5.show();
                        }


                        };

                        onBoarding = new LoqrOnBoarding(context);
                        onBoarding.createProcess("+351", "912992454", customData, onboardingResult);
                        
                         // Create the toast
                        Toast toast5 = Toast.makeText(context, "Terminei de criar o onboarding",
                        Toast.LENGTH_LONG);
                       // Display toast
                        toast5.show();
                        
                    }

                    
                    private static void show(Context context, String message, String duration) {
                        // Create the toast
                        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
                       // Display toast
                        toast.show();
                    }
                    
                    private void bindDevice()
                    {
                        if (!onBoarding.isBinded()) {
                            onBoarding.bindDevice(onBoarding.getIdentityId(),new OnLoqrOnboardingResult() {
                            @Override
                            public void onCompleted() {
                                // Create the toast
                                    Toast toast5 = Toast.makeText(context, "Não tinha o Bind e Consegui fazer o Bind",
                                    Toast.LENGTH_LONG);
                                    // Display toast
                                    toast5.show();
                            }

                            @Override
                            public void onError(LoqrException e) {
                                 // Create the toast
                                    Toast toast5 = Toast.makeText(context, "Não Consegui fazer o Bind",
                                    Toast.LENGTH_LONG);
                                    // Display toast
                                    toast5.show();
                            }
                        });
                        } else {
                           // Create the toast
                                    Toast toast5 = Toast.makeText(context, "O Bind já estava feito!! Segueee",
                                    Toast.LENGTH_LONG);
                                    // Display toast
                                    toast5.show();
                        }
                    }

                    private  void initLoqr()
                    {
                        

                      try {
                          new Loqr(context).initLoqr("LOQR_DEMO_ID", "LOQR_DEMO_KEY", "OLA", new OnLoqrRequestListener() {

                              @Override
                            public void loqrResponse(int requestCode, String message, Boolean status) {
                            if (status) {
                                 message = "FUNCIONOU!!";
                                    
                            }

                            // Create the toast
                        Toast toast2 = Toast.makeText(context, message, Toast.LENGTH_LONG);
                        toast2.show();
                         }

                        },
                        0); //use a unique code to distinguish the request results
                      }
                      catch (Exception e) {
                        Toast toast = Toast.makeText(context, e.toString(), Toast.LENGTH_LONG);
                        toast.show();
                       


                      }

                    }
                    
                    private void validateMobileNumber()
                    {
                        onBoarding.validatePhoneRequest(new OnLoqrOnboardingResult() {
                            @Override
                            public void onCompleted() {
                                 // Create the toast
                                Toast toast = Toast.makeText(context, "enviei SMS", Toast.LENGTH_LONG);
                                // Display toast
                                toast.show();
                                //SMS was sent with success
                                
                                
                            }

                            @Override
                            public void onError(LoqrException e) {
                                // Create the toast
                                Toast toast = Toast.makeText(context, e.toString(), Toast.LENGTH_LONG);
                                // Display toast
                                toast.show();
                                //Log.d(TAG, e.getErrorCode().getCode());
                            }
                        });
                    }
                    
                    public void validateMobileNumberResponse(String encryptedSms)
                    {
                        onBoarding.validatePhone(encryptedSms, new OnPhoneValidation() {
                            @Override
                            public void onDecrypt(String otp) {
                                Toast toast = Toast.makeText(context, "Estou no ValidateMobileNumberResponse-OnDecryypt", Toast.LENGTH_LONG);
                                // Display toast
                                toast.show();
                            }

                            @Override
                            public void onValidated() {
                                //The mobile number is validated!
                                //You can now start updated data.
                                Toast toast = Toast.makeText(context, "Estou no ValidateMobileNumberResponse-OnValidated", Toast.LENGTH_LONG);
                                // Display toast
                                toast.show();
                            }

                            @Override
                            public void onError(LoqrException err) {
                                err.printStackTrace();
                                Toast toast = Toast.makeText(context, "Estou no ValidateMobileNumberResponse-OnError", Toast.LENGTH_LONG);
                                // Display toast
                                toast.show();
                            }
                        });
                    }
                    
                    public void initCamera()
                    {
                        /**
                        * CaptureType.DOCUMENT_FILE
                        * CaptureType.CARD
                        * CaptureType.SELFIE
                        */
                        
                        final String filename = "teste_filename";
                        final String folder = "InfosistemaFolder";
                        
                        Toast toast2 = Toast.makeText(context, "Estou no initCamera e vou buscar o layoutID", Toast.LENGTH_LONG);
                                // Display toast
                        toast2.show();
                        
                        
                        /*int layoutID = context.getResources().getIdentifier("capture_test", "layout", context.getPackageName());
                        
                        toast2 = Toast.makeText(context, "LayourID é: "+layoutID, Toast.LENGTH_LONG);
                        toast2.show();
                        
                        FrameLayout frameLayout = (FrameLayout) activity.findViewById(layoutID);
                        
                       
                        
                        
                        
                        if(frameLayout == null)
                        {
                            toast2 = Toast.makeText(context, "framelayout é null porra!", Toast.LENGTH_LONG);
                            toast2.show();
                        }
                        else {
                            toast2 = Toast.makeText(context, "já tenho frameLayout!!!", Toast.LENGTH_LONG);
                        toast2.show();
                        }
                        
                        */
                        
                        frameLayout = new FrameLayout(context);

                        fullView = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                        frameLayout.setLayoutParams(fullView);
                        
                        
                        try {
                            
                            toast2 = Toast.makeText(context, "estou no Try", Toast.LENGTH_LONG);
                            toast2.show();
                            
                            LoqrCameraCapture.getInstance().initCamera(context, frameLayout , CaptureType.CARD, new CameraCaptureListener() {
                            @Override
                            public void onCameraCaptureStarted() {
                                Toast toast = Toast.makeText(context, "Estou no onCameraCaptureStarted", Toast.LENGTH_LONG);
                                // Display toast
                                toast.show();
                            }

                            @Override
                            public void onCameraCaptureCompleted(Bitmap bitmap) {
                                Toast toast = Toast.makeText(context, "Estou no onCameraCaptureCompleted", Toast.LENGTH_LONG);
                                // Display toast
                                toast.show();
                                
                                
                                //Log.d(TAG, "onCameraCaptureCompleted: got image");
                                File file = DocumentUtils.saveCapturedImageFile(folder,context, bitmap, filename, true); //Store the file on the mobile device's internal storage
                                //uploadImage(file) //Send the file to Loqr's platform
                            }

                            @Override
                            public void onError(LoqrException e) {
                                //Log.e(TAG, "onError: " + e.toString());
                            }
                        });
                            
                        } catch(LoqrException e)
                        {
                            Toast toast = Toast.makeText(context, "Estou no catch do InitCamera", Toast.LENGTH_LONG);
                                // Display toast
                            toast.show();
                        }
                        
                        new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                // no teu caso, em vez de this devia de ser provavelmente o cordovoa.getActivity()
                                activity.addContentView(frameLayout, fullView);
                            }
                        });
                        
                        
                        
                        
                        
                        
                        
                        
                        //activity.addContentView(frameLayout, fullView);
                        
                        toast2 = Toast.makeText(context, "Terminei o initCamera", Toast.LENGTH_LONG);
                        toast2.show();
                        
                        
                    }
                    
                    
                }







