package app.com.multitimetable.android.multitimetable;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SubjectDetailActivity extends ActionBarActivity {

    public static final String ARG_SUBJECT_ID="arg_subject_id";
    public static final String ARG_SUBJECT_NAME="arg_subject_name";
    public static final String ARG_SUBJECT_PROFESSOR="arg_subject_professor";
    public static final String ARG_TIME_DAY="arg_time_day";
    public static final String ARG_TIME_START_TIME="arg_time_start_time";
    public static final String ARG_TIME_END_TIME="arg_time_end_time";
    public static final String ARG_TIME_PLACE="arg_time_place";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);
        Intent thisActivityIntent = getIntent();

        int subjectIdData = thisActivityIntent.getIntExtra(ARG_SUBJECT_ID,0);
        String subjectNameData = thisActivityIntent.getStringExtra(ARG_SUBJECT_NAME);
        String professorData = thisActivityIntent.getStringExtra(ARG_SUBJECT_PROFESSOR);
        int dayData = thisActivityIntent.getIntExtra(ARG_TIME_DAY,0);
        int startTimeData = thisActivityIntent.getIntExtra(ARG_TIME_START_TIME,0);
        int endTimeData = thisActivityIntent.getIntExtra(ARG_TIME_END_TIME,0);
        String placeData = thisActivityIntent.getStringExtra(ARG_TIME_PLACE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(thisActivityIntent.getStringExtra(ARG_SUBJECT_NAME));

        TextView subject = (TextView) findViewById(R.id.subjectAtDetailView);
        subject.setText(subjectNameData);

        TextView day = (TextView) findViewById(R.id.dayAtDetailView);
        day.setText(Utility.getDayResource(dayData));

        TextView time = (TextView) findViewById(R.id.timeAtDetailView);
        time.setText(Utility.getFormattedTimeForView(startTimeData)+" - "+Utility.getFormattedTimeForView(endTimeData));

        TextView place = (TextView) findViewById(R.id.placeAtDetailView);
        place.setText(placeData);

        TextView professor = (TextView) findViewById(R.id.professorAtDetailView);
        professor.setText(professorData);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subject_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if ( id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        if (id == R.id.action_edit) {
            Intent intent = new Intent(this, SubjectUpdateActivity.class);
            startActivity(intent);

            return true;
        }
        if (id == R.id.action_discard) {
            DialogFragment subjectDeleteDialogFragment = new SubjectDeleteDialogFragment();
//            Bundle args = new Bundle();
//            args.putInt(ScheduleUpdateDialogFragment.ARG_SCHEDULE_ID, getArguments().getInt(ARG_SCHEDULE_ID));
//            args.putString(ScheduleUpdateDialogFragment.ARG_SCHEDULE_NAME, getArguments().getString(ARG_SCHEDULE_NAME));
//            subjectDeleteDialogFragment.setArguments(args);
            subjectDeleteDialogFragment.show(this.getSupportFragmentManager(), "SUBJECT_DELETE_DIALOG_FRAGMENT");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
