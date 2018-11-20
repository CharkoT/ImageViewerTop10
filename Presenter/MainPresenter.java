package charko.tester01.com.imageviewertop10.Presenter;

import android.content.Context;

import charko.tester01.com.imageviewertop10.Contract.MainContract;
import charko.tester01.com.imageviewertop10.Data.LoadingImageLink;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mainView;
    private LoadingImageLink loadingImageLink;

    @Override
    public void attachView(MainContract.View mainView) {
        this.mainView = mainView;
    }

    @Override
    public void detachView() {
        mainView = null;
    }

    @Override
    public void updateImage(Context context, int cur) {
        if (loadingImageLink == null)
            loadingImageLink = new LoadingImageLink().getInstance();

        loadingImageLink.getImage(context, cur);
        mainView.addItems(loadingImageLink.getImages());
        mainView.notifyAdapter();
    }

//    @Override
//    public void updateImage(Context context, int cur) {
//        loadingImageLink.getImage(context, cur);
//        mainView.addItems(loadingImageLink.getImages());
//        mainView.notifyAdapter();
//
//    }
}
