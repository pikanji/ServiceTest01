package net.kanjirusoft.servicetest01;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class TestService extends Service {
	public static final String START_SERVICE = "start";
	public static final String STOP_SERVICE = "stop";
	private static final long INTERVAL = 5;

	private int count = 0;

	@Override
	public void onStart(Intent i, int startId) {
		super.onStart(i, startId);

		Log.v("TestService", "Service#onStart invoked.");

		Intent intent = new Intent(this, TestService.class);
		intent.setAction(TestService.START_SERVICE);

		PendingIntent pi = PendingIntent.getService(this, 0, intent,
				PendingIntent.FLAG_ONE_SHOT);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

		if (i.getAction().equals(START_SERVICE)) {
			Log.v("TestService", "Service started: " + count);
			Toast.makeText(this, "Service started: " + count,
					Toast.LENGTH_SHORT).show();
			long triggerTime = SystemClock.elapsedRealtime()
					+ (INTERVAL * 1000);
			alarmManager.set(AlarmManager.ELAPSED_REALTIME, triggerTime, pi);
			count++;
		} else if (i.getAction().equals(STOP_SERVICE)) {
			Log.v("TestService", "Service stopped.");
			Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
			alarmManager.cancel(pi);
		}
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
