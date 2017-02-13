package usthing.eventmangementsystem;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.skygear.skygear.AuthenticationException;
import io.skygear.skygear.Container;
import io.skygear.skygear.Database;
import io.skygear.skygear.Error;
import io.skygear.skygear.Query;
import io.skygear.skygear.Record;
import io.skygear.skygear.RecordQueryResponseHandler;

/**
 * Created by dipsy on 2017-02-11.
 */

public class page2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        System.out.println(this.getClass().toString());

        final Context context = this;

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("LL");

        final TextView tv = (TextView) findViewById(R.id.textView2);

        Query noteQuery = new Query("event");
        Container skygear = Container.defaultContainer(this);
        Database publicDB = skygear.getPublicDatabase();

        publicDB.query(noteQuery, new RecordQueryResponseHandler() {
            @Override
            public void onQuerySuccess(Record[] records) {
                //Log.i("Record Query", String.format("Successfully got %d records", records.length));
                tv.setText(String.format("Successfully got %d records", records.length));

                LinearLayout llmain = (LinearLayout)findViewById(R.id.llMain);

                for (Record record:records){
                    TextView textView = new TextView(context);
                    textView.setText("title"+record.getData().toString());
                    llmain.addView(textView);
                }
            }

            @Override
            public void onQueryError(Error error) {
                tv.setText("Error "+ error);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ);
                return (true);

        }
        return super.onOptionsItemSelected(item);
    }
}
