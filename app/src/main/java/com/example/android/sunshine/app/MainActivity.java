/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }

        Log.d("SEQUENCE", "onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_location) {
            openPreferredLocationInMap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String postalCode = sharedPreferences.getString(getString(R.string.setting_key_location),
                getString(R.string.setting_value_location));

        Uri location = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", postalCode)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(location);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Log.d(MainActivity.class.getCanonicalName(), "Couldn't start map for " + postalCode);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SEQUENCE", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("SEQUENCE", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("SEQUENCE", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SEQUENCE", "onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("SEQUENCE", "onStart");
    }
}
