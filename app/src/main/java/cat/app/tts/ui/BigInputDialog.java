package cat.app.tts.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cat.app.tts.R;

public class BigInputDialog extends Dialog implements View.OnClickListener {

    private EditText mInputEditText;

    private FloatingActionButton mConfirmButton;
    private FloatingActionButton mCancelButton;

    public OnClickListener onClickListener;

    public BigInputDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_dialog);
        setCanceledOnTouchOutside(false);
        mInputEditText = (EditText) findViewById(R.id.mInputEditText);
        mConfirmButton = (FloatingActionButton) findViewById(R.id.mConfirmButton);
        mCancelButton = (FloatingActionButton) findViewById(R.id.mCancelButton);
        mConfirmButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);

    }

    public void setOnClickListener(OnClickListener onClick) {
        this.onClickListener = onClick;
    }

    public String getInputText() {
        return mInputEditText.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mConfirmButton:
                onClickListener.onConfirmButtonClick();
                return;
            case R.id.mCancelButton:
                onClickListener.onCancelButtonClick();
                return;
        }
    }

    public interface OnClickListener {
        public void onConfirmButtonClick();
        public void onCancelButtonClick();
    }

}
