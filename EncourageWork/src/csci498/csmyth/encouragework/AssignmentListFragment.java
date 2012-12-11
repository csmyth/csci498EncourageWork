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
import android.widget.ListView;
import android.widget.TextView;

import csci498.csmyth.encouragework.dummy.DummyContent;

/**
 * A list fragment representing a list of Assignments. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link AssignmentDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class AssignmentListFragment extends ListFragment {
	public static final String ID_EXTRA = "csci498.csmyth.encouragework._ID";
	
    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    
    Cursor model = null;
    AssignmentHelper helper = null;
    AssignmentAdapter adapter = null;
    OnAssignmentListener listener = null;

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    //private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    //public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        //public void onItemSelected(String id);
    //}

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    /*private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };*/

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AssignmentListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        /*setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DummyContent.ITEMS));*/
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
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
    
    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }*/
    
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
        //super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        //mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
    	if (listener != null) listener.onAssignmentSelected(id);
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
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
    	// TODO: add other row elements here
    	
    	AssignmentHolder(View row) {
    		name = (TextView)row.findViewById(R.id.title);
    	}
    	
    	void populateFrom(Cursor c, AssignmentHelper helper) {
    		name.setText(helper.getName(c));
    	}
    }
    
}
