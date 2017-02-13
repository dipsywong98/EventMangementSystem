package usthing.eventmangementsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Map;

import io.skygear.skygear.AuthResponseHandler;
import io.skygear.skygear.AuthenticationException;
import io.skygear.skygear.Container;
import io.skygear.skygear.Database;
import io.skygear.skygear.Error;
import io.skygear.skygear.Record;
import io.skygear.skygear.RecordSaveResponseHandler;
import io.skygear.skygear.User;

public class MainActivity extends AppCompatActivity {

    public static int screenWidth=0;
    public static Context context;
    public int screenHeight=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        context = this;

        final Button pop_btn = (Button) findViewById(R.id.pop_btn);
        final Button signin_btn = (Button) findViewById(R.id.signin);
        final Button signup_btn = (Button) findViewById(R.id.signup);
        final EditText name_et = (EditText) findViewById(R.id.name);
        final EditText pw_et = (EditText) findViewById(R.id.pw);
        final Container skygear = Container.defaultContainer(this);
        final Button new_btn = (Button) findViewById(R.id.new_btn);

        pop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, page2.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name_et.getText().toString(); // get from user input
                String password = name_et.getText().toString(); // get from user input

                skygear.signupWithUsername(username, password, new AuthResponseHandler() {
                    @Override
                    public void onAuthSuccess(User user) {
                        Toast toast = Toast.makeText(context,"sign up successful",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void onAuthFail(Error error) {
                        Toast toast = Toast.makeText(context,"sign up fail"+error,Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name_et.getText().toString(); // get from user input
                String password = name_et.getText().toString(); // get from user input

                skygear.loginWithUsername(username, password, new AuthResponseHandler() {
                    @Override
                    public void onAuthSuccess(User user) {
                        Toast toast = Toast.makeText(context,"sign in successful",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void onAuthFail(Error error) {
                        Toast toast = Toast.makeText(context,"sign in fail"+error,Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
            }
        });

        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("new message");
                builder.setView(R.layout.pop_layout);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // get Skygear Container
                        Container skygear = Container.defaultContainer(context);

                        // get public database
                        Database database = skygear.getPublicDatabase();

                        // get private database
                        try {
                            Database privateDatabase = skygear.getPrivateDatabase();
                        } catch (AuthenticationException e) {
                            e.printStackTrace();
                        }

                        //EditText et = (EditText)(findViewById(R.id.input));
                        //String text = et.getText().toString();

                        Record aNote = new Record("event");
                        aNote.set("title", "Hello World");
                        aNote.set("readCount", 4);
                        aNote.set("lastReadAt", new Date());

                        RecordSaveResponseHandler handler = new RecordSaveResponseHandler(){
                            @Override
                            public void onSaveSuccess(Record[] records) {
                                Log.i(
                                        "Skygear Record Save",
                                        "Successfully saved " + records.length + " records"
                                );
                                Toast.makeText(context,"success "+ Integer.toString(records.length),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onPartiallySaveSuccess(Map<String, Record> successRecords, Map<String, Error> errors) {
                                Toast.makeText(context,"part success "+ Integer.toString(successRecords.size()),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onSaveFail(Error error) {
                                Toast.makeText(context,"fail"+error,Toast.LENGTH_LONG).show();
                            }

                        };

                        database.save(aNote, handler);

                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }
}
