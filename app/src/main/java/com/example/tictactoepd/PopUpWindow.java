package com.example.tictactoepd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopUpWindow extends PopupWindow {

    private Button negativeButton;
    private Button positiveButton;
    private TextView popUpHeader;
    private TextView popUpMessageBody;

    public PopUpWindow(Context context, String header, String messageBody) {
        super(LayoutInflater.from(context).inflate(R.layout.popup_window, null),
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                true);

        // Initialize UI components here
        View contentView = getContentView();
        negativeButton = contentView.findViewById(R.id.negativeButton);
        positiveButton = contentView.findViewById(R.id.positiveButton);
        popUpHeader = contentView.findViewById(R.id.popUpHeader);
        popUpMessageBody = contentView.findViewById(R.id.popUpBody);

        //set header and body
        popUpHeader.setText(header);
        popUpMessageBody.setText(messageBody);

        // Set click listeners
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement positive button click action
                dismiss();
            }
        });
    }
}
