package org.me.myandroidstuff;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
 
public class AsyncTaskActivity extends Activity implements OnClickListener
{
 
 private Button startButton;
 private ProgressBar progressBar;
 private TextView txt_percentage;
 
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         
        startButton = (Button) findViewById(R.id.btn_start);
        progressBar =  (ProgressBar) findViewById(R.id.progress);
        txt_percentage= (TextView) findViewById(R.id.txt_percentage);
        startButton.setOnClickListener(this); 
        
    }
 
     
    private class ShowDialogAsyncTask extends AsyncTask<Void, Integer, Void>
    {
 
     int progress_status;
      
     @Override
     protected void onPreExecute() 
     {
    	 // update the UI immediately after the task is executed
    	 super.onPreExecute();
    
    	 Toast.makeText(AsyncTaskActivity.this,"Invoke onPreExecute()", Toast.LENGTH_SHORT).show();
 
    	 progress_status = 0;
    	 txt_percentage.setText("Downloading 0%");
    
     }
      
     @Override
     protected Void doInBackground(Void... params) 
     {
    
    	 while(progress_status<100)
    	 {
     
    		 progress_status += 2;
     
    		 publishProgress(progress_status);
    		 // Sleep but normally do something useful here such as access a web resource
    		 SystemClock.sleep(300); // or Thread.sleep(300);
    		 
    		 // Really need to do some calculation on progress
    	 }
    	 return null;
     }
  
     @Override
     protected void onProgressUpdate(Integer... values) 
     {
    	 super.onProgressUpdate(values);
    
    	 progressBar.setProgress(values[0]);
    	 
    	 txt_percentage.setText("Downloading " +values[0] + "%");
    
     }
   
     @Override
     protected void onPostExecute(Void result) 
     {
    	 super.onPostExecute(result);
    
    	 Toast.makeText(AsyncTaskActivity.this,
            "Invoke onPostExecute()", Toast.LENGTH_SHORT).show();
     
    	 txt_percentage.setText("Download complete");
    	 startButton.setEnabled(true);
    	 progressBar.setVisibility(View.INVISIBLE);
    
     }
     
    }


	@Override
	public void onClick(View aview) 
	{
		if (aview == startButton)
		{
			startButton.setEnabled(false);
			progressBar.setVisibility(View.VISIBLE);
			new ShowDialogAsyncTask().execute();
		}
		
	}
}
