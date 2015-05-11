package com.fzc.rhinooffice.util.reddotface;

public interface Callback {
	void onBefore();

	boolean onRun();

	void onAfter(boolean b);
}
