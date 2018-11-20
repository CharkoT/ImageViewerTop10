package charko.tester01.com.imageviewertop10.DI.Module;

import android.content.Context;

import charko.tester01.com.imageviewertop10.DI.Qualifier.ActivityContext;
import charko.tester01.com.imageviewertop10.DI.Scope.TestApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Context context;

    public ActivityModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @TestApplicationScope
    @ActivityContext
//    @Named("activity_context")
    public Context context() {
        return context;
    }
}
