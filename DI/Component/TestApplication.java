package charko.tester01.com.imageviewertop10.DI.Component;

import android.app.Application;

import charko.tester01.com.imageviewertop10.DI.Module.ContextModule;

public class TestApplication extends Application {
    private TestApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        this.component = DaggerTestApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    TestApplicationComponent getComponent() {
        return component;
    }
}
