package com.shomazzap.cardapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.btn_git)
    ImageButton btnGit;
    @BindView(R.id.btn_inst)
    ImageButton btnInst;
    @BindView(R.id.btn_telegram)
    ImageButton btnTelegram;
    @BindView(R.id.btn_vk)
    ImageButton btnVk;
    @BindView(R.id.btn_play)
    ImageButton btnPlay;
    @BindView(R.id.btn_send_msg)
    ImageButton btnSend;
    @BindView(R.id.et_message)
    EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setListenersToButtons();
    }

    private void setListenersToButtons() {
        btnSend.setOnClickListener(onClick -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            String[] mails = {getResources().getString(R.string.email)};
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, mails);  // EXTRA_MAIL works only with string array
            intent.putExtra(Intent.EXTRA_TEXT, etMessage.getText().toString());
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
            else
                Toast.makeText(AboutActivity.this, getResources().getString(R.string.msg_no_mail_app),
                        Toast.LENGTH_SHORT).show();
        });
        btnGit.setOnClickListener(onClick ->
                openLink(getResources().getString(R.string.git_link))
        );
        btnInst.setOnClickListener(onClick ->
                openLink(getResources().getString(R.string.inst_link),
                        getResources().getString(R.string.package_instagram))
        );
        btnTelegram.setOnClickListener(onClick ->
                openLink(getResources().getString(R.string.telegram_link),
                        getResources().getString(R.string.package_telegram))
        );
        btnVk.setOnClickListener(onClick ->
                openLink(getResources().getString(R.string.vk_link),
                        getResources().getString(R.string.package_vk))
        );
        btnPlay.setOnClickListener(onClick ->
                openLink(getResources().getString(R.string.market_link))
        );
    }

    private void openLink(String link, String appPackage) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage(appPackage);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.w("AppFind", "Can't find app whit package \""
                    + appPackage + "\". Error log :");
            e.printStackTrace();
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }

    private void openLink(String link) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
