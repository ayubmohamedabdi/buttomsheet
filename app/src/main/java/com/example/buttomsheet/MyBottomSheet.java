package com.example.buttomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MyBottomSheet extends BottomSheetDialogFragment {

    private EditText inputEditText;
    private RecyclerView recyclerView;
    private Button submitButton, cancelButton;
    private RecyclerView.Adapter adapter;
    private List<String> items = new ArrayList<>();
    private OnDataSubmitListener listener;

    public interface OnDataSubmitListener {
        void onDataSubmit(String data);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        inputEditText = view.findViewById(R.id.inputEditText);
        recyclerView = view.findViewById(R.id.itemRecyclerView);
        submitButton = view.findViewById(R.id.submitButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ItemAdapter(items);
        recyclerView.setAdapter(adapter);

        submitButton.setOnClickListener(v -> {
            String inputText = inputEditText.getText().toString();
            if (!inputText.isEmpty()) {
                items.add(inputText);
                adapter.notifyDataSetChanged();
                inputEditText.setText("");
                Toast.makeText(getContext(), "Item Added", Toast.LENGTH_SHORT).show();
                if (listener != null) {
                    listener.onDataSubmit(inputText);
                }
            } else {
                Toast.makeText(getContext(), "Input is empty", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(v -> dismiss());

        return view;
    }

    public void setOnDataSubmitListener(OnDataSubmitListener listener) {
        this.listener = listener;
    }
}
