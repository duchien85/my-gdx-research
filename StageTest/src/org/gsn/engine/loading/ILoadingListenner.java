package org.gsn.engine.loading;

public interface ILoadingListenner {
	void loadedPercent(float percent);
	void loadFinished();
}
