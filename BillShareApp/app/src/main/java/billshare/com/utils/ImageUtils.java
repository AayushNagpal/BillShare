package billshare.com.utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ImageUtils {
    private static ImageUtils imageUtils;
private GroupInfo groupInfo;
    private ImageUtils() {

    }

    public static ImageUtils instance() {
        if (imageUtils == null) {
            imageUtils = new ImageUtils();
        }
        return imageUtils;
    }

    public String getBinarySting(Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();

        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    public Bitmap getBitmapFromByteArray(String byteArrayString) {
        byte[] bytes = Base64.decode(byteArrayString, Base64.DEFAULT);
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return bitmap;
    }

    public GroupInfo getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }
}
