package csci498.csmyth.encouragework;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ToggleButton;

/**
 * A fragment representing a single Assignment detail screen.
 * This fragment is either contained in a {@link AssignmentListActivity}
 * in two-pane mode (on tablets) or a {@link AssignmentDetailActivity}
 * on handsets.
 */
public class AssignmentDetailFragment extends Fragment {
	EditText name = null;
	DatePicker date_picker = null;
	EditText notes = null;
	ToggleButton complete = null;
	
	AssignmentHelper helper = null;
	String assignmentId = null;
	
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    //public static final String ARG_ITEM_ID = "item_id";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AssignmentDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        /*View rootView = inflater.inflate(R.layout.fragment_assignment_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.assignment_detail)).setText(mItem.content);
        }

        return rootView;*/
    	return inflater.inflate(R.layout.fragment_assignment_detail, container, false);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	
    	name = (EditText)getView().findViewById(R.id.name);
    	date_picker = (DatePicker)getView().findViewById(R.id.date_picker);
    	notes = (EditText)getView().findViewById(R.id.notes);
    	complete = (ToggleButton)getView().findViewById(R.id.complete);
    }

    @Override
    public void onPause() {
    	save();
    	helper.close();
    	super.onPause();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	helper = new AssignmentHelper(getActivity());
    	assignmentId = getActivity().getIntent().getStringExtra(AssignmentListActivity.ID_EXTRA);
    	
    	if (assignmentId != null) load();
    }
    
    private void load() {
    	Cursor c = helper.getById(assignmentId);
    	c.moveToFirst();
    	
    	name.setText(helper.getName(c));
    	date_picker.updateDate(helper.getYear(c), helper.getMonth(c), helper.getDayOfMonth(c));
    	notes.setText(helper.getNotes(c));
    	complete.setChecked(helper.getComplete(c));
    	
    	c.close();
    }
    
    private void save() {
    	if (name.getText().toString().length() > 0) {
    		int checked;
    		if (complete.isChecked()) checked = 1;
    		else checked = 0;
    		
    		if (assignmentId == null) helper.insert(name.getText().toString(), date_picker.getYear(), date_picker.getMonth(), date_picker.getDayOfMonth(), notes.getText().toString(), checked);
    		else helper.update(assignmentId, name.getText().toString(), date_picker.getYear(), date_picker.getMonth(), date_picker.getDayOfMonth(), notes.getText().toString(), checked);
    	}
    }
}
