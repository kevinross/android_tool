package name.kevinross.debugtestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import name.kevinross.tool.debuggable.DebuggableTool;
import name.kevinross.tool.debuggable.DebuggableToolNative;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        long state = DebuggableToolNative.getGRegistryState();
        Log.d("DebuggableNative", String.format("gRegistry=%d", state));
    }
}
