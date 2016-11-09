package com.frame.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.frame.R;
import com.frame.utils.DensityUtil;

import java.util.List;

public class BottomPopupWindow extends PopupWindow implements OnClickListener{

	private Context mContext;
	private TextView mTextCancel;
	private LinearLayout mLinearBottom;
	public BottomPopupWindow(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		initView();
	}
	
	private View mBottomView;
	private void initView() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mBottomView = inflater.inflate(R.layout.bottom_dialog, null);
		mTextCancel = (TextView) mBottomView.findViewById(R.id.txt_cancel);
		mTextCancel.setOnClickListener(this);
		mLinearBottom = (LinearLayout) mBottomView.findViewById(R.id.bottom_view);
		this.setContentView(mBottomView);
		this.setAnimationStyle(R.style.BottomAnimation);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		this.setFocusable(true);
		this.setOutsideTouchable(false);
		mBottomView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				// TODO Auto-generated method stub
				int viewHeight = mLinearBottom.getTop();
				int viewY = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (viewY < viewHeight) {
						dismiss();		
					}
				}
				return false;
			}
		});
        mBottomView.findViewById(R.id.bottom).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return false;
            }
        });
		
//		setItem("Please select your operation", null, R.drawable.actionsheet_top_normal, -10);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		this.dismiss();
	}
	
	public void showLocation(View view) {
		// TODO Auto-generated method stub
		this.showAtLocation(view, Gravity.CENTER|Gravity.BOTTOM, 0, 20);
	}
	
	public void addBottomItem(List<String> list,OnClickListener clickListener) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			setItem(list.get(i), clickListener, i);
		}
	}
	
	private void setItem(String message,OnClickListener clickListener,int id) {
		// TODO Auto-generated method stub
		TextView textView = new TextView(mContext);
		textView.setText(message);
		textView.setGravity(Gravity.CENTER);
		textView.setWidth(LayoutParams.MATCH_PARENT);
		textView.setTextSize(18);
		textView.setBackgroundResource(R.drawable.btn_bottom);
		textView.setTextColor(mContext.getResources().getColor(R.color.bottom_dialog_bule));
		textView.setOnClickListener(clickListener);
		textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, DensityUtil.dip2px(mContext, "55dp")));
		textView.setId(id);
		if (id == -1) {
			textView.setClickable(true);
		}
		
		View view  = new View(mContext);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
		view.setBackgroundResource(R.color.bottom_dialog_divider);
		
		mLinearBottom.addView(textView);
		mLinearBottom.addView(view);
	}
}
