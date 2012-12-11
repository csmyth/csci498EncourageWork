package csci498.csmyth.encouragework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class AssignmentListActivity extends FragmentActivity implements AssignmentListFragment.OnAssignmentListener {
    public final static String ID_EXTRA = "csci498.csmyth.encouragework._ID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_list);
        AssignmentListFragment ewfrag = (AssignmentListFragment)getSupportFragmentManager().findFragmentById(R.id.assignment_list);
        ewfrag.setOnAssignmentListener(this);
    }

    @Override
    public void onAssignmentSelected(long id) {
        Intent detailIntent = new Intent(this, AssignmentDetailActivity.class);
        detailIntent.putExtra(ID_EXTRA, String.valueOf(id));
        startActivity(detailIntent);
    }
}
