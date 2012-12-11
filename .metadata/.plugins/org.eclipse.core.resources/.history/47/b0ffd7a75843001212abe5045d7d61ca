package csci498.csmyth.encouragework;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

public class EncourageWork extends Activity {
	List<Assignment> assignments = new ArrayList<Assignment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encourage_work);
        
        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_encourage_work, menu);
        return true;
    }
    
    private View.OnClickListener onSave = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			Assignment asmnt = new Assignment();
			
			asmnt.setName(((EditText)findViewById(R.id.name)).getText().toString());
			DatePicker date_picker = (DatePicker)findViewById(R.id.date_picker);
			asmnt.setDue_date(new Date(date_picker.getYear(), date_picker.getMonth(), date_picker.getDayOfMonth()));
			asmnt.setNotes(((EditText)findViewById(R.id.notes)).getText().toString());
			CheckBox complete = (CheckBox)findViewById(R.id.complete);
			asmnt.setComplete(complete.isChecked());
		}
	};
    
}
