package charko.tester01.com.imageviewertop10.DI.Module;

import android.content.Context;

import charko.tester01.com.imageviewertop10.DI.Qualifier.ApplicationContext;
import charko.tester01.com.imageviewertop10.DI.Scope.TestApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @TestApplicationScope
    @ApplicationContext
//    @Named("application_context")
    public Context context() {
        return context;
    }
}
