package com.fengz.personal.fourweeks.di;

import com.fengz.personal.fourweeks.business1.contract.ActivitingContract;
import com.fengz.personal.fourweeks.business1.contract.AddContract;
import com.fengz.personal.fourweeks.business1.contract.DetailsContract;
import com.fengz.personal.fourweeks.business1.contract.MainContract;
import com.fengz.personal.fourweeks.business1.contract.OverdueContract;
import com.fengz.personal.fourweeks.business1.contract.TodayTaskContract;
import com.fengz.personal.fourweeks.business1.presenter.ActivitingPresenter;
import com.fengz.personal.fourweeks.business1.presenter.AddPresenter;
import com.fengz.personal.fourweeks.business1.presenter.DetailsPresenter;
import com.fengz.personal.fourweeks.business1.presenter.MainPresenter;
import com.fengz.personal.fourweeks.business1.presenter.OverduePresenter;
import com.fengz.personal.fourweeks.business1.presenter.TodayTaskPresenter;
import com.fengz.personal.fourweeks.business1.ui.activity.AddActivity;
import com.fengz.personal.fourweeks.business1.ui.activity.DetailsActivity;
import com.fengz.personal.fourweeks.business1.ui.activity.MainActivity;
import com.fengz.personal.fourweeks.business1.ui.activity.WelcomeActivity;
import com.fengz.personal.fourweeks.business1.ui.fragment.ActivitingFragment;
import com.fengz.personal.fourweeks.business1.ui.fragment.OverdueFragment;
import com.fengz.personal.fourweeks.business1.ui.fragment.TodayFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class Business1Module {

    @ActivityScope
    @ContributesAndroidInjector
    abstract WelcomeActivity welcomeActivityInject();

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity mainActivityInject();

    @Binds
    abstract MainContract.Presenter mainPresenterInject(MainPresenter presenter);

    @ActivityScope
    @ContributesAndroidInjector
    abstract ActivitingFragment activitingFragmentInject();

    @Binds
    abstract ActivitingContract.Presenter activitingPresenterInject(ActivitingPresenter presenter);

    @ActivityScope
    @ContributesAndroidInjector
    abstract OverdueFragment overdueFragmentInject();

    @Binds
    abstract OverdueContract.Presenter overduePresenterInject(OverduePresenter presenter);

    @ActivityScope
    @ContributesAndroidInjector
    abstract TodayFragment todayFragmentInject();

    @Binds
    abstract TodayTaskContract.Presenter todayTaskPresenterInject(TodayTaskPresenter presenter);

    @ActivityScope
    @ContributesAndroidInjector
    abstract AddActivity addActivityInject();

    @Binds
    abstract AddContract.Presenter addPresenterInject(AddPresenter presenter);

    @ActivityScope
    @ContributesAndroidInjector
    abstract DetailsActivity detailsActivityInject();

    @Binds
    abstract DetailsContract.Presenter detailsPresenterInject(DetailsPresenter presenter);
}