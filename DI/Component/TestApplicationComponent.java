package charko.tester01.com.imageviewertop10.DI.Component;

import android.content.Context;

import charko.tester01.com.imageviewertop10.DI.Module.ActivityModule;
import charko.tester01.com.imageviewertop10.DI.Module.ContextModule;
import charko.tester01.com.imageviewertop10.DI.Qualifier.ApplicationContext;
import charko.tester01.com.imageviewertop10.DI.Scope.TestApplicationScope;
import dagger.Component;

@TestApplicationScope
@Component(modules = {ContextModule.class, ActivityModule.class})
public interface TestApplicationComponent {

    @ApplicationContext
    Context context();

//    void injectMainActivity(MainActivity mainActivity);
// dependencies로 사용 당할때 선언 하여 해당 변수를 갖고 올  수 있음.
// MainActivity의 지역변수에 @Inject를 붙이고, 별도의 초기화 없이 사용할 수 있음.

}
