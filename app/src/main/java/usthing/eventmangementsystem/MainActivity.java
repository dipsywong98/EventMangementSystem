package usthing.eventmangementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.skygear.skygear.AuthResponseHandler;
import io.skygear.skygear.Container;
import io.skygear.skygear.Error;
import io.skygear.skygear.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Container skygear = Container.defaultContainer(this);

        String username = "usthing@ust.hk"; // get from user input
        String password = "usthing@1234"; // get from user input

        skygear.loginWithUsername(username, password, new AuthResponseHandler() {
            @Override
            public void onAuthSuccess(User user) {
                Log.i("Skygear Login", "onAuthSuccess: Got token: ");
            }

            @Override
            public void onAuthFail(Error error) {
                Log.i("Skygear Login", "onAuthFail: Fail with reason: " + error);
            }
        });
    }
}
