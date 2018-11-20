package charko.tester01.com.imageviewertop10.Data;

import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Charko on 2018. 3. 15..
 * model
 */

public class LoadingImageLink {

    private ArrayList<Image> images = null;
    private int imgCussor = 0;

    public int getImgCussor() {
        return imgCussor;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void addImages(Image image) {
        this.images.add(image);
    }

    public void onInit() {
        imgCussor = 0;
        images = new ArrayList<>();
    }

    public static LoadingImageLink loadingImageLink;

    public int getImageSize() {
        return images.size();
    }

    public Image getImage(int index) {
        return images.get(index);
    }

    public static LoadingImageLink getInstance() {
        if (loadingImageLink == null) {
            loadingImageLink = new LoadingImageLink();
            loadingImageLink.onInit();
        }

        return loadingImageLink;
    }

    public void getImage(Context context, int addCussor) {
        Log.e(">>>>>>>>>>>>", ">>>>>>>>>> start");

        int imageIndex = imgCussor;
        imgCussor += addCussor;
        String[] proj = {MediaStore.Images.Media.DATA};

        Cursor imageCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
                proj, // DATA를 출력
                null,       // 모든 개체 출력
                null,
                MediaStore.Images.Media.DATE_ADDED + " DESC");      // 정렬 안 함


        // 이것도 또한 함수로.?
        imageCursor.moveToFirst();
        Log.e(">>>>>>>>>>>>", " imageIndex : " + imageIndex + ">>>>>>>>>> imgCussor : " + imgCussor);
        while (!imageCursor.isLast()) {
            if (imageIndex == imgCussor)
                break;

            Log.e(">>>>>>>>>>>>", ">>>>>>>>>> imageCursor.getPosition() : " + imageCursor.getPosition());

            if (imageCursor.getPosition() < imageIndex) {
                imageCursor.moveToNext();
                continue;
            }

            Image image = new Image();

            image.setFilePath(imageCursor.getString(0));
            Log.e(">>>>>>>>>>>>", ">>>>>>>>>> getData : " + imageCursor.getString(0));
            image.setId(imageIndex++);


            images.add(image);
            imageCursor.moveToNext();
        }

        ImageMigration();
    }

    private void ImageMigration() {
        for (Image image : images) {
            try {
//                Log.e(">>>>>>>>>>>>", ">>>>>>>>>> " + image.getFilePath());
                if (image.getFilePath().contains(".gif"))
                    continue;

                ExifInterface exif = new ExifInterface(image.getFilePath());
                image.setDate(exif.getAttribute(ExifInterface.TAG_DATETIME));
//                if (exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE) != null)
//                    image.setLatitude(Double.parseDouble(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE)));
//                if (exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE) != null)
//                    image.setLongitude(Double.parseDouble(exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE)));
//                if (exif.getAttribute(ExifInterface.TAG_ORIENTATION) != null)
//                    image.setOrientation(Integer.parseInt(exif.getAttribute(ExifInterface.TAG_ORIENTATION)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
