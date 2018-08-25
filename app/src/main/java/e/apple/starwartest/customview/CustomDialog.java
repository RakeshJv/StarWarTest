package e.apple.starwartest.customview;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import e.apple.starwartest.R;
import e.apple.starwartest.constant.Constant;
import e.apple.starwartest.interfaces.DialogClickListner;

public class CustomDialog extends Dialog


{

    DialogClickListner dialogClickListneristener;

    public CustomDialog(@NonNull Context context, DialogClickListner dialogClickListneristener) {
        super(context);
        this.dialogClickListneristener = dialogClickListneristener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert);
        Button btnNo = (Button) findViewById(R.id.btnNo);


        btnNo.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialogClickListneristener.onButtonClick(Constant.No);

            }
        });
        Button btnYes = (Button) findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new android.view.View.OnClickListener()

        {

            @Override
            public void onClick(View arg0)
            {
                dialogClickListneristener.onButtonClick(Constant.YES);
            }
        });
        TextView alertMsg = (TextView) findViewById(R.id.alertMsg);
        TextView alertTitle = (TextView) findViewById(R.id.alertTitle);


    }


}
