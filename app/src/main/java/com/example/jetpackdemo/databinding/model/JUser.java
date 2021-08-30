package com.example.jetpackdemo.databinding.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.jetpackdemo.BR;

/**
 * 单向绑定的第一种方法
 *注意User中 存在Int但是给TextView 赋值为Int是会出现错误的因为TextView不能设置Int类型 只能是String类型
 */
public class JUser extends BaseObservable {
    private String name;
    private String age;

    public JUser(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //只更新指定的单条数据 必须和Bindable配合使用
        notifyPropertyChanged(BR.name);
//        notifyChange(); 是更新所有的数据
    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);//更新所有的数据
    }
}
