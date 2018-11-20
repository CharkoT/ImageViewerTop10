package charko.tester01.com.imageviewertop10.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import charko.tester01.com.imageviewertop10.R;

/**
 * Created by Charko on 2018. 3. 15..
 */

public class ImageAdapter extends BaseAdapter {

    private ArrayList<Bitmap> images = new ArrayList<>();

//    @BindView((R.id.image_iv)) ImageView ivImg;
//    @BindView((R.id.filename_tv)) TextView tvFilepath;
//    @BindView((R.id.date_tv)) TextView tvDate;
//    @BindView((R.id.gps_iv)) ImageView ivGPS;
//    @BindView((R.id.ori_iv)) ImageView ivOriendtation;

    public ImageAdapter() {
    }

    public void setImage(ArrayList<Bitmap> images) {
        this.images = images;
    }

    public void addImage(Bitmap image) {
        images.add(image);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Bitmap getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        final Bitmap image = getItem(i);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.image_adapter, viewGroup, false);
        }

//        ButterKnife.bind(this, view);

        final ImageView ivImg = (ImageView) view.findViewById(R.id.image_iv);
        final TextView tvFilepath = (TextView) view.findViewById(R.id.filename_tv);
        final TextView tvDate = (TextView) view.findViewById(R.id.date_tv);
        final ImageView ivGPS = (ImageView) view.findViewById(R.id.gps_iv);
        final ImageView ivOriendtation = (ImageView) view.findViewById(R.id.ori_iv);

        tvFilepath.setText("test filePath");//image.getFilePath());
        tvDate.setText("title");//image.getDate());

//        Log.e(">>>>>>>>>>>>>", ">>>>>>>>>>>>>> filepath : " + image.getFilePath());
//        File file = new File(image.getFilePath());
//
//        Log.e(">>>>>>>>>>>>>", ">>>>>>>>>>>>>> file : " + file);
//        Log.e(">>>>>>>>>>>>>", ">>>>>>>>>>>>>> file : " + file.getPath());

        Glide.with(context).load(image).into(ivImg);
//        view.setTag(1, tv);

        return view;
    }
}
