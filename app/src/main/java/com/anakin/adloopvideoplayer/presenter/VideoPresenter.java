package com.anakin.adloopvideoplayer.presenter;

import android.content.Context;

import com.dy.videoplayer.MainActivity;
import com.dy.videoplayer.model.IVideoModel;
import com.dy.videoplayer.model.VideoModel;
import com.dy.videoplayer.view.IView;

import java.util.List;

/**
 * Created by demo on 2017/3/23 0023
 */
public class VideoPresenter implements IVideoPresenter, OnVideoListener {
    private IView mView;
    private IVideoModel mModel;

    private Context mContext;

    public VideoPresenter(IView view) {
        this.mView = view;
        mContext = (MainActivity) view;
        mModel = new VideoModel();
    }

    @Override
    public void getData() {
        mView.showProgress();
        mModel.loadVideo(this,mContext);
    }

    @Override
    public void onDestroy() {
        mModel.unSubscribe();
    }

    @Override
    public void onSuccess(List<String> result) {
        mView.hideProgress();
        mView.showData(result);
    }

    @Override
    public void onFail() {
        mView.hideProgress();
        mView.showError();
        // 获取失败 重新获取
        getData();
    }
}
