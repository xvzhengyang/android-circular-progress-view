package com.github.guilhe.circularprogressview_sample;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.Toast;
import com.github.guilhe.circularprogressview.CircularProgressView;
import com.github.guilhe.circularprogressview_sample.databinding.ActivitySampleEditorBinding;

/**
 * Created by gdelgado on 30/08/2017.
 */

public class SampleActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private ActivitySampleEditorBinding mBinding;
    private boolean mTransparent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample_editor);

        mBinding.sizeSeekBar.setOnSeekBarChangeListener(this);
        mBinding.thicknessSeekBar.setOnSeekBarChangeListener(this);
        mBinding.progressSeekBar.setOnSeekBarChangeListener(this);
        mBinding.angleSeekBar.setOnSeekBarChangeListener(this);
        mBinding.colorRSeekBar.setOnSeekBarChangeListener(this);
        mBinding.colorGSeekBar.setOnSeekBarChangeListener(this);
        mBinding.colorBSeekBar.setOnSeekBarChangeListener(this);
        mBinding.bgRSeekBar.setOnSeekBarChangeListener(this);
        mBinding.bgGSeekBar.setOnSeekBarChangeListener(this);
        mBinding.bgBSeekBar.setOnSeekBarChangeListener(this);

        mBinding.shadowSwitch.setOnCheckedChangeListener((compoundButton, checked) -> mBinding.sampleCircularProgressView.setShadowEnabled(checked));
        mBinding.thumbSwitch.setOnCheckedChangeListener((compoundButton, checked) -> mBinding.sampleCircularProgressView.setProgressThumbEnabled(checked));
        mBinding.colorsSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (!mTransparent) {
                mBinding.sampleCircularProgressView.setBackgroundColor(mBinding.sampleCircularProgressView.getProgressColor());
            }
        });
        mBinding.transparentSwitch.setOnCheckedChangeListener((compoundButton, checked) -> {
            mTransparent = checked;
            mBinding.sampleCircularProgressView.setBackgroundColor(Color.TRANSPARENT);
        });

        mBinding.sampleCircularProgressView.setProgressAnimationCallback(new CircularProgressView.OnProgressChangeAnimationCallback() {
            @Override
            public void onProgressChanged(float progress) {
            }

            @Override
            public void onAnimationFinished(float progress) {
                Toast.makeText(SampleActivity.this, String.valueOf(progress) + "%", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.size_SeekBar:
                mBinding.sampleCircularProgressView.setSize((int) (progress * getResources().getDisplayMetrics().density + 0.5f));
                break;
            case R.id.thickness_SeekBar:
                mBinding.sampleCircularProgressView.setProgressStrokeThickness((int) (progress * getResources().getDisplayMetrics().density + 0.5f));
                break;
            case R.id.progress_SeekBar:
                mBinding.sampleCircularProgressView.setProgress(progress, mBinding.animatedSwitch.isChecked());
                break;
            case R.id.angle_SeekBar:
                mBinding.sampleCircularProgressView.setStartingAngle(progress);
                break;
            case R.id.color_r_SeekBar:
                if (mBinding.colorsSwitch.isChecked()) {
                    mBinding.bgRSeekBar.setProgress(progress);
                }
                mBinding.sampleCircularProgressView.setProgressColor(Color.rgb(progress, mBinding.colorGSeekBar.getProgress(), mBinding.colorBSeekBar.getProgress()));
                break;
            case R.id.color_g_SeekBar:
                if (mBinding.colorsSwitch.isChecked()) {
                    mBinding.bgGSeekBar.setProgress(progress);
                }
                mBinding.sampleCircularProgressView.setProgressColor(Color.rgb(mBinding.colorRSeekBar.getProgress(), progress, mBinding.colorBSeekBar.getProgress()));
                break;
            case R.id.color_b_SeekBar:
                if (mBinding.colorsSwitch.isChecked()) {
                    mBinding.bgBSeekBar.setProgress(progress);
                }
                mBinding.sampleCircularProgressView.setProgressColor(Color.rgb(mBinding.colorRSeekBar.getProgress(), mBinding.colorGSeekBar.getProgress(), progress));
                break;
            case R.id.bg_r_SeekBar:
                if (mBinding.colorsSwitch.isChecked()) {
                    mBinding.colorRSeekBar.setProgress(progress);
                }
                if (!mTransparent) {
                    mBinding.sampleCircularProgressView.setBackgroundColor(Color.rgb(progress, mBinding.bgGSeekBar.getProgress(), mBinding.bgBSeekBar.getProgress()));
                }
                break;
            case R.id.bg_g_SeekBar:
                if (mBinding.colorsSwitch.isChecked()) {
                    mBinding.colorGSeekBar.setProgress(progress);
                }
                if (!mTransparent) {
                    mBinding.sampleCircularProgressView.setBackgroundColor(Color.rgb(mBinding.bgRSeekBar.getProgress(), progress, mBinding.bgBSeekBar.getProgress()));
                }
                break;
            case R.id.bg_b_SeekBar:
                if (mBinding.colorsSwitch.isChecked()) {
                    mBinding.colorBSeekBar.setProgress(progress);
                }
                if (!mTransparent) {
                    mBinding.sampleCircularProgressView.setBackgroundColor(Color.rgb(mBinding.bgRSeekBar.getProgress(), mBinding.bgGSeekBar.getProgress(), progress));
                }
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}