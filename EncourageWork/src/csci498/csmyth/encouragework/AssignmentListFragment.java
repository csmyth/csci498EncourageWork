package csci498.csmyth.encouragework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class AssignmentListFragment extends ListFragment {
	public static final String ID_EXTRA = "csci498.csmyth.encouragework._ID";
    
    Cursor model = null;
    AssignmentHelper helper = null;
    AssignmentAdapter adapter = null;
    OnAssignmentListener listener = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Override 
    public void onResume() {
    	super.onResume();
    	helper = new AssignmentHelper(getActivity());
    	initList();
    }

    @Override
    public void onPause() {
    	helper.close();
    	super.onPause();
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	inflater.inflate(R.menu.option, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId() == R.id.add) {
    		startActivity(new Intent(getActivity(), AssignmentDetailActivity.class));
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
    	if (listener != null) listener.onAssignmentSelected(id);
    }
    
    public void setOnAssignmentListener(OnAssignmentListener listener) {
    	this.listener = listener;
    }
    
    private void initList() {
    	if (model != null) model.close();
    	model = helper.getAll("name");
    	adapter = new AssignmentAdapter(model);
    	setListAdapter(adapter);
    }
    
    public interface OnAssignmentListener {
    	void onAssignmentSelected(long id);
    }
    
    /*********************************************/
    
    class AssignmentAdapter extends CursorAdapter {
    	AssignmentAdapter(Cursor c) {
    		super(getActivity(), c);
    	}
    	
    	@Override
    	public void bindView(View row, Context ctxt, Cursor c) {
    		AssignmentHolder holder = (AssignmentHolder)row.getTag();
    		holder.populateFrom(c, helper);
    	}
    	
    	@Override
    	public View newView(Context ctxt, Cursor c, ViewGroup parent) {
    		LayoutInflater inflater = getActivity().getLayoutInflater();
    		View row = inflater.inflate(R.layout.row, parent, false);
    		AssignmentHolder holder = new AssignmentHolder(row);
    		row.setTag(holder);
    		
    		return row;
    	}
    }
    
    /*********************************************/
    
    static class AssignmentHolder {
    	private TextView name = null;
    	private CheckBox complete = null;
    	private TextView due_date = null;
    	
    	AssignmentHolder(View row) {
    		name = (TextView)row.findViewById(R.id.title);
    		complete = (CheckBox)row.findViewById(R.id.check_complete);
    		due_date = (TextView)row.findViewById(R.id.due_date_row);
    	}
    	
    	void populateFrom(Cursor c, AssignmentHelper helper) {
    		name.setText(helper.getName(c));
    		complete.setChecked(helper.getComplete(c));
    		due_date.setText(helper.getDate(c));
    	}
    }
    
}
