package charko.tester01.com.imageviewertop10;

import charko.tester01.com.imageviewertop10.Contract.MainContract;
import charko.tester01.com.imageviewertop10.Presenter.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    private final MainPresenter mainPresenter;

    public PresenterModule(MainPresenter mainPresenter, MainContract.View view) {
        this.mainPresenter = mainPresenter;
        mainPresenter.attachView(view);
    }

    @Provides
    @MainActivityScope
    public MainPresenter mainPresenter() {
        return mainPresenter;
    }
}
