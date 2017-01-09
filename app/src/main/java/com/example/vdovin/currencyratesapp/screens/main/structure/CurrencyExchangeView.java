package com.example.vdovin.currencyratesapp.screens.main.structure;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.vdovin.currencyratesapp.R;
import com.example.vdovin.currencyratesapp.screens.main.CurrencyExchangeActivity;
import com.example.vdovin.currencyratesapp.utils.parser.ParsedResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyExchangeView {

    private static final String SPACE = " ";
    @BindView(R.id.text_field)
    TextView currencyTextView;

    private View view;
    private Activity activity;

    public CurrencyExchangeView(CurrencyExchangeActivity activity) {

        this.activity = activity;

        FrameLayout frameLayout = new FrameLayout(this.activity);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        view = LayoutInflater.from(this.activity).inflate(R.layout.activity_main, frameLayout, true);

        ButterKnife.bind(this, view);
    }

    public View getView() {
        return view;
    }

    public void display(List<ParsedResponse> parsedResponseList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ParsedResponse parsedResponse : parsedResponseList) {
            stringBuilder.append(parsedResponse.getBankName());
            stringBuilder.append(SPACE);
        }
        currencyTextView.setText(stringBuilder.toString());
    }

    public void showErrorDialog(Throwable throwable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(activity.getString(R.string.network_problem))
                .setMessage(throwable.getMessage())
                .setPositiveButton(activity.getString(R.string.ok), (dialog1, which) -> {
                    dialog1.cancel();
                    activity.finish();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
