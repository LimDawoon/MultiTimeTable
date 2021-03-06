package app.com.multitimetable.android.multitimetable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.com.multitimetable.android.multitimetable.data.MTTContract;


public class ScheduleDeleteDialogFragment extends android.support.v4.app.DialogFragment {
    public static final String ARG_SCHEDULE_ID = "schedule_id";
    public static final String ARG_SCHEDULE_NAME = "schedule_name";


    private View mDialogView;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        mDialogView = getActivity().getLayoutInflater().inflate(android.R.layout.simple_list_item_activated_1, null);
        TextView textView = (TextView)mDialogView.findViewById(android.R.id.text1);
        textView.setText("'"+getArguments().getString(ARG_SCHEDULE_NAME)+"'를 삭제하시겠습니까?");

        builder.setTitle(R.string.delete_schedule)
                .setView(mDialogView)
                // Add action buttons
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        int count = getActivity().getContentResolver().delete(
                                MTTContract.ScheduleEntry.CONTENT_URI,
                                MTTContract.ScheduleEntry._ID + "=?",
                                new String[]{Integer.toString(getArguments().getInt(ARG_SCHEDULE_ID))}
                        );

                        Toast.makeText(getActivity(), "count:" + count, Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ScheduleDeleteDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();


    }



}
