package com.example.buttomsheet;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button openBottomSheetButton;
    private TextView resultTextView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openBottomSheetButton = findViewById(R.id.openBottomSheetButton);
        resultTextView = findViewById(R.id.resultTextView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout); // SwipeRefreshLayout

        // Open Bottom Sheet
        openBottomSheetButton.setOnClickListener(v -> {
            // Change the text color to white
            openBottomSheetButton.setTextColor(ContextCompat.getColor(this, android.R.color.white));

            MyBottomSheet bottomSheet = new MyBottomSheet();
            bottomSheet.setOnDataSubmitListener(data -> resultTextView.setText("Submitted Data: " + data));
            bottomSheet.show(getSupportFragmentManager(), "MyBottomSheet");
        });


        // Set up Swipe Down to Refresh to clear the data
        swipeRefreshLayout.setOnRefreshListener(() -> {
            resultTextView.setText("");

            // Stop the refreshing animation once done
            swipeRefreshLayout.setRefreshing(false);
        });
    }
}
