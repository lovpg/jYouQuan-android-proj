package com.frame.view.pull;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class PullLayout extends LinearLayout {
	/** 上一次移动的点 */
	protected float lastMotionY = -1;

	/** 回滚的时间 */
	private static final int SCROLL_DURATION = 150;

	/** 阻尼系数 */
	private static final float OFFSET_RADIO = 2.0f;

	// View refreshableView;
	/** 平滑滚动的Runnable */
	protected SmoothScrollRunnable smoothScrollRunnable = new SmoothScrollRunnable(this);

	public PullLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.setOrientation(LinearLayout.VERTICAL);
	}

	/**
	 * 重置header
	 */
	protected void resetHeaderLayout() {
		smoothScrollTo(0);
	}

	/**
	 * 平滑滚动
	 * 
	 * @param newScrollValue
	 *            滚动的值
	 */
	protected void smoothScrollTo(int newScrollValue) {
		this.smoothScrollRunnable.smoothScrollTo(newScrollValue, SCROLL_DURATION, 0);
	}

	private boolean mIsHandledTouchEvent = false;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		boolean flag = this.onInterceptTouchEvent2(event);
		// System.err.println("onTouchEvent Intercept:" + " " + mIsHandledTouchEvent + " " + flag + " " + event);
		return flag;
	}

	public boolean onInterceptTouchEvent2(MotionEvent event) {
		final int action = event.getAction();
		if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
			mIsHandledTouchEvent = false;
			return false;
		}

		// if (action != MotionEvent.ACTION_DOWN && mIsHandledTouchEvent) {
		// return true;
		// }

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			lastMotionY = event.getY();
			mIsHandledTouchEvent = false;
			break;
		case MotionEvent.ACTION_MOVE:
			final float deltaY = event.getY() - lastMotionY;
			lastMotionY = event.getY();
			// System.err.println("onInterceptTouchEvent deltaY:" + deltaY);
			// 第一个显示出来，Header已经显示或拉下
			if (deltaY > 0) {
				// 1，Math.abs(getScrollY()) > 0：表示当前滑动的偏移量的绝对值大于0，表示当前HeaderView滑出来了或完全
				// 不可见，存在这样一种case，当正在刷新时并且RefreshableView已经滑到顶部，向上滑动，那么我们期望的结果是
				// 依然能向上滑动，直到HeaderView完全不可见
				// 2，deltaY > 0.5f：表示下拉的值大于0.5f
				mIsHandledTouchEvent = (Math.abs(getScrollY()) > 0 || deltaY > 3f);// TODO ahai 不同分辨率，值会不一样?
			}
			else {
				// 原理如上
				mIsHandledTouchEvent = (Math.abs(getScrollY()) > 0 || deltaY < -3f);
			}

			break;

		default:
			break;
		}

		return mIsHandledTouchEvent;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// System.err.println("onTouchEvent:" + ev);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastMotionY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float deltaY = ev.getY() - lastMotionY;
			// System.err.println("onTouchEvent deltaY:" + deltaY);
			lastMotionY = ev.getY();
			super.scrollBy(0, -(int) (deltaY / OFFSET_RADIO));
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			resetHeaderLayout();
			break;
		default:
			break;
		}
		return true;
	}
}
