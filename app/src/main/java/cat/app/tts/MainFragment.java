package cat.app.tts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import cat.app.tts.databinding.FragmentMainBinding;

public class MainFragment extends Fragment implements View.OnClickListener, NumberPicker.OnValueChangeListener {

    private FragmentMainBinding binding;

    private int mRepeatTimes = 0;

    private int mButtonStatus = View.GONE;

    private int mRepeatViewStatus = View.GONE;

    private boolean mRepeatButtonEnable = false;

    private void onStartButtonClick() {

    }

    private void onStopButtonClick() {

    }

    private void onRepeatButtonClick() {
        if (mRepeatViewStatus == View.GONE)
            mRepeatViewStatus = View.VISIBLE;
        else
            mRepeatViewStatus = View.GONE;
        binding.mRepeatView.setVisibility(mRepeatViewStatus);
    }

    private void onToggleButtonClick() {
        if (mButtonStatus == View.GONE) {
            mButtonStatus = View.VISIBLE;
            binding.mToggleButton.setRotation(45);
        }
        else {
            mButtonStatus = View.GONE;
            binding.mToggleButton.setRotation(0);
        }
        binding.mPlayButton.setVisibility(mButtonStatus);
        binding.mExportButton.setVisibility(mButtonStatus);
        binding.mEditButton.setVisibility(mButtonStatus);
    }

    private void onPlayButtonClick() {
        Toast.makeText(getContext(),""+CatAppConfig.tts_tone+mRepeatTimes,Toast.LENGTH_LONG).show();
    }

    private void onExportButtonClick() {

    }

    private void onEditButtonClick() {

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.mStartButton.setOnClickListener(this);
        binding.mStopButton.setOnClickListener(this);
        binding.mRepeatButton.setOnClickListener(this);
        binding.mToggleButton.setOnClickListener(this);
        binding.mPlayButton.setOnClickListener(this);
        binding.mExportButton.setOnClickListener(this);
        binding.mEditButton.setOnClickListener(this);

        binding.mNumberPickerOne.setMinValue(0);
        binding.mNumberPickerTen.setMinValue(0);
        binding.mNumberPickerHundred.setMinValue(0);
        binding.mNumberPickerThousand.setMinValue(0);
        binding.mNumberPickerOne.setMaxValue(9);
        binding.mNumberPickerTen.setMaxValue(9);
        binding.mNumberPickerHundred.setMaxValue(9);
        binding.mNumberPickerThousand.setMaxValue(9);
        binding.mNumberPickerOne.setOnValueChangedListener(this);
        binding.mNumberPickerTen.setOnValueChangedListener(this);
        binding.mNumberPickerHundred.setOnValueChangedListener(this);
        binding.mNumberPickerThousand.setOnValueChangedListener(this);

        binding.mRepeatProgressBar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mRepeatViewStatus == View.VISIBLE)
                    onRepeatButtonClick();
                mRepeatButtonEnable = !mRepeatButtonEnable;
                binding.mRepeatButton.setEnabled(mRepeatButtonEnable);
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        CatAppConfig.refreshData(getContext());
        mButtonStatus = View.GONE;
        mRepeatViewStatus = View.GONE;
        mRepeatButtonEnable = false;
        mRepeatTimes = 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mStartButton :
                onStartButtonClick();
                break;
            case R.id.mStopButton :
                onStopButtonClick();
                break;
            case R.id.mRepeatButton :
                onRepeatButtonClick();
                break;
            case R.id.mToggleButton :
                onToggleButtonClick();
                break;
            case R.id.mPlayButton :
                onPlayButtonClick();
                break;
            case R.id.mExportButton :
                onExportButtonClick();
                break;
            case R.id.mEditButton :
                onEditButtonClick();
                break;
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        int value = newVal - oldVal;
        switch (picker.getId()) {
            case R.id.mNumberPickerOne:
                value *= 1;
                break;
            case R.id.mNumberPickerTen:
                value *= 10;
                break;
            case R.id.mNumberPickerHundred:
                value *= 100;
                break;
            case R.id.mNumberPickerThousand:
                value *= 1000;
                break;
        }
        mRepeatTimes += value;
    }
}