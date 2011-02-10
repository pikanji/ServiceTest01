package net.kanjirusoft.servicetest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Demonstrates how to create and start/stop a service. This service won't be
 * killed even the launcher application (MainActivity) is killed or Application
 * killer kills processes. Also, this shows that the Service process keeps it's
 * data after it's task is done. It indicates that the Service process awaken by
 * the AlarmManager for many times is actually the same process. Also, starting
 * Service by startService multiple times does not create multiple processes but
 * simply make the existing Service process run the onStart method. This can be
 * tested by pressing start button of this application multiple times.
 * 
 * @author kanji
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewById(R.id.button_start_service).setOnClickListener(this);
		findViewById(R.id.button_stop_service).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;

		switch (v.getId()) {
		case R.id.button_start_service:
			intent = new Intent(this, TestService.class);
			intent.setAction(TestService.START_SERVICE);
			startService(intent);
			break;
		case R.id.button_stop_service:
			intent = new Intent(this, TestService.class);
			intent.setAction(TestService.STOP_SERVICE);
			startService(intent);
			break;
		}
	}
}