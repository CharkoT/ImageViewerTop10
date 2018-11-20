package charko.tester01.com.imageviewertop10;

import charko.tester01.com.imageviewertop10.Presenter.MainPresenter;
import dagger.Component;

@MainActivityScope
@Component(modules = PresenterModule.class) //다른 Dagger에서 필요한 변수가 있다면 ", dependencies = " 사용
public interface MainActivityComponent {
    MainPresenter mainPresenter();
}
