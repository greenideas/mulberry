package com.toura.www;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.net.URL;


import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.text.TextUtils;

import com.phonegap.DroidGap;
import com.toura.www.push.IntentReceiver;

public class TouraMainActivity extends DroidGap {
  private boolean isInForeground;

  public WebView getAppView() {
    return appView;
  }

  /*
   * Disable trackball navigation etc.
   */
  @Override
  public boolean onTrackballEvent(MotionEvent event) {
  	return true;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    super.loadUrl("file:///android_asset/www/index.html");

    /* Galaxy Tab enables pinch/zoom on the entire webview by
       default, thus you can zoom everywhere. Setting this to
       true on regular phone devices seems to have no effect,
       so unfortunately we can't use it for magic pinch/zoom
       in image detail etc. Anyway, disable for sake of
       Galaxy Tab
    */
    WebSettings ws = super.appView.getSettings();
    ws.setSupportZoom(false);
    ws.setBuiltInZoomControls(false);
    ws.setPluginState(PluginState.ON);
    IntentReceiver.setTouraMainActivity(this);
  }

  public boolean isInForeground() {
      return isInForeground;
  }

  public void showAlert(String message) {
    appView.loadUrl("javascript: " + createShowAlertScript(message));
  }

  @Override
  protected void onPause() {
      super.onPause();
      isInForeground = false;
  }

  @Override
  protected void onResume() {
      super.onResume();
      isInForeground = true;
      Intent intent = getIntent();
      if (intent.hasExtra("alert")) {
        String url = "javascript:document.addEventListener('deviceready', function() {dojo.subscribe('/app/started', function() { " + createShowAlertScript(intent.getStringExtra("alert")) + " });}, false);";
        Log.i(TouraApplication.LOG_TAG, "Showing alert in TouraMainActivity.onResume()! url: " + url);
        appView.loadUrl(url);
      }
  }

  private String createShowAlertScript(String message) {
    return "toura.app.Notifications.notify({alert:'" + message.replace("'", "\\'") + "'});";
  }
}
