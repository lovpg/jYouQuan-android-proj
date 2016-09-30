package com.frame.view.pull;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * 实现了平滑滚动的Runnable
 * 
 * @author Li Hong
 * @since 2013-8-22
 */
public class SmoothScrollRunnable implements Runnable {

	/** 动画效果 */
	private final Interpolator interpolator;
	/** 结束Y */
	private int scrollToY;
	/** 开始Y */
	private int scrollFromY;
	/** 滑动时间 */
	private long duration;
	/** 是否继续运行 */
	private boolean continueRunning = true;
	/** 开始时刻 */
	private long startTime = -1;
	/** 当前Y */
	private int currentY = -1;

	private View view;

	/**
	 * 构造方法
	 *
	 *            动画时间
	 */
	public SmoothScrollRunnable(View view) {
		this.view = view;
		interpolator = new DecelerateInterpolator();
	}

	private void init(int fromY, int toY, long duration) {
		this.scrollFromY = fromY;
		this.scrollToY = toY;
		this.duration = duration;
		this.continueRunning = true;
		this.startTime = -1;
		this.currentY = -1;
	}

	@Override
	public void run() {
		/**
		 * If the duration is 0, we scroll the view to target y directly.
		 */
		if (duration <= 0) {
			view.scrollTo(0, scrollToY);
			return;
		}

		/**
		 * Only set mStartTime if this is the first time we're starting, else actually calculate the Y delta
		 */
		if (startTime == -1) {
			startTime = System.currentTimeMillis();
		}
		else {

			/**
			 * We do do all calculations in long to reduce software float calculations. We use 1000 as it gives us good accuracy and small rounding errors
			 */
			final long oneSecond = 1000; // SUPPRESS CHECKSTYLE
			long normalizedTime = (oneSecond * (System.currentTimeMillis() - startTime)) / duration;
			normalizedTime = Math.max(Math.min(normalizedTime, oneSecond), 0);

			final int deltaY = Math.round((scrollFromY - scrollToY) * interpolator.getInterpolation(normalizedTime / (float) oneSecond));
			currentY = scrollFromY - deltaY;

			view.scrollTo(0, currentY);
		}

		// If we're not at the target Y, keep going...
		if (continueRunning && scrollToY != currentY) {
			view.postDelayed(this, 16);// SUPPRESS CHECKSTYLE
		}
	}

	/**
	 * 停止滑动
	 */
	public void stop() {
		continueRunning = false;
		view.removeCallbacks(this);
	}

	/**
	 * 平滑滚动
	 * 
	 * @param newScrollValue
	 *            滚动的值
	 * @param duration
	 *            滚动时候
	 * @param delayMillis
	 *            延迟时间，0代表不延迟
	 */
	public void smoothScrollTo(int newScrollValue, long duration, long delayMillis) {
		this.stop();

		int oldScrollValue = view.getScrollY();
		boolean post = (oldScrollValue != newScrollValue);
		if (post) {
			// smoothScrollRunnable = new SmoothScrollRunnable(this, oldScrollValue, newScrollValue, duration);
			this.init(oldScrollValue, newScrollValue, duration);
		}

		if (post) {
			if (delayMillis > 0) {
				view.postDelayed(this, delayMillis);
			}
			else {
				view.post(this);
			}
		}
	}
}
