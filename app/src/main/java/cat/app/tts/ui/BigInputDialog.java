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
        mCancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }
}
