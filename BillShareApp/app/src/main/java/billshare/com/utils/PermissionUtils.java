package billshare.com.utils;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {
    private Context context;

    private PermissionUtils(Context context){
        this.context=context;

    }
    public static PermissionUtils getInstance(Context context){
        return   new PermissionUtils(context);
    }
    public boolean isAvaliableCallPermission(){
        if(context.checkCallingOrSelfPermission(Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return  false;
        }
    }
    final public static int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    private Activity activity;
    public static final int REQUEST_CAMERA = 0;
    public static final int REQUEST_CAMERA_PERMISSION = 1;
    public static final int REQUEST_GALLERY_PERMISSION = 3;
    public static final int REQUEST_IMAGE_GALLARY=2;
    public PermissionUtils(Activity activity){
        this.activity=activity;

    }

    public void requestForAllMandataryPermissions(){
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
       /* if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("GPS");*/
        if (!addPermission(permissionsList, Manifest.permission.READ_CONTACTS))
            permissionsNeeded.add("Read Contacts");
       /* if (!addPermission(permissionsList, Manifest.permission.WRITE_CONTACTS))
            permissionsNeeded.add("Write Contacts");*/
        if (!addPermission(permissionsList, Manifest.permission.CAMERA))
            permissionsNeeded.add("CAMERA");
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("Write External Storage");
        if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
            permissionsNeeded.add("Read External Storage");

        /*if (!addPermission(permissionsList, Manifest.permission.CALL_PHONE))
            permissionsNeeded.add("Call");*/

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "We need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(activity,permissionsList.toArray(new String[permissionsList.size()]),
                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        });
                return;
            }
            ActivityCompat. requestPermissions(activity,permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(activity,permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission))
                return false;
        }
        return true;
    }

    public void requestForCameraPermission(View view) {

        final String permission = Manifest.permission.CAMERA;
        final String permission1 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(activity, permission1)
                != PackageManager.PERMISSION_GRANTED) {

            requestForPermission(permission,permission1);
            /*if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showPermissionRationaleDialog("", permission);
            } else {
                requestForPermission(permission);
            }*/
        } /*else {
            launchCamera();
        }*/
    }
    public void requestForGalleryPermission(View view) {

        final String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
           /* if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showPermissionRationaleDialog("Test", permission);
            } else {

            }*/
            requestGalleryPermission(permission);
        } else {
            launchGallary();
        }
    }

    private void showPermissionRationaleDialog(final String message, final String permission) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //requestForPermission(permission);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }
    private void requestGalleryPermission(final String permission){
        ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUEST_GALLERY_PERMISSION);
    }
    private void requestForPermission(final String permission,final String permisson1) {
        ActivityCompat.requestPermissions(activity, new String[]{permission,permisson1}, REQUEST_CAMERA_PERMISSION);
    }
    public void launchGallary(){
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        activity.startActivityForResult(
                Intent.createChooser(intent, "Select File"),
                PermissionUtils.REQUEST_IMAGE_GALLARY);
    }
    /*public void launchCamera() {
        Intent startCustomCameraIntent = new Intent(activity, CameraActivity.class);
        activity.startActivityForResult(startCustomCameraIntent, REQUEST_CAMERA);
    }*/

}