package charko.tester01.com.imageviewertop10.Contract;

import android.content.Context;

import java.util.ArrayList;

import charko.tester01.com.imageviewertop10.Data.Image;

public interface MainContract {

    interface View {
        void addItems(ArrayList<Image> images);

        void notifyAdapter();
    }

    interface Presenter {
        void attachView(MainContract.View mainView);

        void detachView();

        void updateImage(Context context, int cur);

//        void updateImage(Context context, int cur);

    }
}
