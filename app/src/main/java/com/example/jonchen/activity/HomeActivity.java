package com.example.jonchen.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.base.BaseFragment;
import com.example.jonchen.base.MyAnnotation;
import com.example.jonchen.base.MyEnum;
import com.example.jonchen.dagger.DaggerHomeActivityComponent;
import com.example.jonchen.dagger.HomeActivityModule;
import com.example.jonchen.mvpview.HomeView;
import com.example.jonchen.presenter.HomePresenter;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.view.MyViewPager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by andychen on 2017/1/22.
 */

public class HomeActivity extends BaseActivity implements HomeView {
    @BindView(R.id.navigationBar)
    BottomNavigationBar navigationBar;
    @BindView(R.id.mViewpager)
    MyViewPager mViewpager;
    private ViewPageAdapter pageAdapter;
    @Inject
    HomePresenter homePresenter;

    private View view;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    @MyAnnotation(value = "test", value2 = MyEnum.Sunday)
    protected void initView() {

      /*  Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.e(s);
            }
        };


        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1111");
                subscriber.onNext("22");
                subscriber.onNext("333");
                subscriber.onNext("444");
            }
        }).subscribe(subscriber);*/


        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(R.mipmap.ic_launcher);
            }
        }).map(new Func1<Integer, Bitmap>() {
            @Override
            public Bitmap call(Integer integer) {
                return BitmapFactory.decodeResource(getResources(), integer);
            }
        }).subscribe(new Subscriber<Bitmap>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Bitmap bitmap) {

            }
        });


        Observable.just("one", "two", "three").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                LogUtils.e(s);
            }
        });


      /*  Class<?> clazz = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            LogUtils.e(constructor.getName());
            Class[] parameterTypes = constructor.getParameterTypes();
            for (Class param : parameterTypes) {
                LogUtils.e(param.getName());
            }
        }

        Method[] methods = clazz.getMethods();

        try {
            Constructor constructor = clazz.getConstructor(InvocationHandler.class);
            Collection collection = (Collection) constructor.newInstance(new MyInvocationHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        try {
            Method initView = HomeActivity.class.getMethod("initView", (Class<?>[]) null);
            if (initView.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation = initView.getAnnotation(MyAnnotation.class);
                String value = annotation.value();
                String myEnum = annotation.value2().name();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        ArrayList<String> list = new ArrayList<>();


       /* final Collection<String> proxy = (Collection<String>) getProxy(list);
        proxy.add("1");
        proxy.add("1");
        proxy.add("1");
        proxy.add("1");
        proxy.size();*/

/*
        Class<Jack> jackClass = Jack.class;
        try {
            Method sayWhat = jackClass.getMethod("sayWhat", (Class<?>[]) null);
            sayWhat.invoke(jackClass.newInstance(), (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //DaggerHomeActivityComponent.create().inject(this);
        DaggerHomeActivityComponent.builder()
                .homeActivityModule(new HomeActivityModule(this)).build().inject(this);
    }


    public Class[] getAllInterfaces(Class clazz, List<Class> interfacesList) {
        Class<?>[] interfaces = clazz.getInterfaces();
        /*for (Class inter : interfaces) {
            interfacesList.add(inter);
        }*/
        Collections.addAll(interfacesList, interfaces);

        Class superclass = clazz.getSuperclass();
        if (superclass != null) {
            getAllInterfaces(superclass, interfacesList);
        }
        return interfacesList.toArray(new Class[interfacesList.size()]);
    }


    private Object getProxy(final Object target) {
        ArrayList<Class> arrayList = new ArrayList<>();
        Class[] interfaces = getAllInterfaces(target.getClass(), arrayList);
        Class[] classes = {Collection.class};
        return Proxy.newProxyInstance(target.getClass().getClassLoader()
                , interfaces
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
                        return method.invoke(target, args);
                    }
                });
    }

    class MyInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }

    @Override
    public void initData() {
        mViewpager.setScrollable(true);
        //HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getBottomNavigationData();
        homePresenter.getFragmentPage();
    }

    @Override
    public void initBottomNavigationBar(List<BottomNavigationItem> itemList) {
        navigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        navigationBar.setActiveColor(R.color.colorPrimary);
        for (BottomNavigationItem item : itemList) {
            navigationBar.addItem(item);
        }
        navigationBar.initialise();
        navigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                mViewpager.setCurrentItem(position, false);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }

    @Override
    public void initFragmentPage(final List<BaseFragment> fragmentList) {
        pageAdapter = new ViewPageAdapter(getSupportFragmentManager(), fragmentList);
        mViewpager.setAdapter(pageAdapter);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(view!=null){
            LogUtils.e(String.valueOf(showViewCanScroll(view,1)));
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setView(View view) {
        this.view = view;
    }

    public boolean showViewCanScroll(View v, int direction) {
        return v.canScrollVertically(direction);
    }

    private class ViewPageAdapter extends FragmentStatePagerAdapter {
        private List<BaseFragment> fragmentList;

        ViewPageAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
