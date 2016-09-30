package com.frame.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.frame.R;


public class ErrorDialog extends Dialog implements OnClickListener{

    public ErrorDialog(Context context){
        this(context, R.style.dialog_frame);
    }

	public ErrorDialog(Context context, int theme) {
		super(context, theme);
	}

	private TextView mContent;
	public Button mOK;
	public Button mNo;
	private String mMessage;
	private int gone = View.VISIBLE;
	private View view;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.layout_prompt_dialog);
		this.setCanceledOnTouchOutside(false);
		mContent = (TextView) findViewById(R.id.error_content);
		mNo = (Button) findViewById(R.id.no);
		mOK = (Button) findViewById(R.id.ok);
		view = findViewById(R.id.view);
	
		mNo.setOnClickListener(this);
		mContent.setText(mMessage);
		mNo.setVisibility(gone);
		view.setVisibility(gone);
	}
	
	public void setContent(String message) {
		// TODO Auto-generated method stub
		this.mMessage = message;
	}


	public void setNoGone() {
		// TODO Auto-generated method stub
		gone = View.GONE;
	}
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		this.dismiss();
	}
}
