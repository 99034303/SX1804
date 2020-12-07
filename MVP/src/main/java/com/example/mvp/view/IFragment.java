package com.example.mvp.view;

import android.view.View;

public interface IFragment extends IView{
    <T extends View> T findViewById(int id);
}
