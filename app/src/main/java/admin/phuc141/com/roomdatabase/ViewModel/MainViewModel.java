package admin.phuc141.com.roomdatabase.ViewModel;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;

import admin.phuc141.com.roomdatabase.Model.Database.Sinhvien;
import admin.phuc141.com.roomdatabase.Repository.MainRepository;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel implements LifecycleObserver {

    private CompositeDisposable compositeDisposable =new CompositeDisposable();
    private MutableLiveData<List<Sinhvien>> mutableLiveDataArraylist= new MutableLiveData();
    private MutableLiveData<List<Long>> mutableLiveDataArrayID= new MutableLiveData();
    public void getAllSinhvien(Context context){
        MainRepository.getInstance(context)
                .getAllSinhvien()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Sinhvien>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onNext(List<Sinhvien> sinhviens) {
                        mutableLiveDataArraylist.setValue(sinhviens);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void insertSinhvien(final Context context, final Sinhvien...sinhviens){
        Observable <List<Long>> observable = Observable.fromCallable(new Callable<List<Long>>() {
            @Override
            public List<Long> call() throws Exception {
                return MainRepository.getInstance(context).insertSinhvien(sinhviens);
            }
        });
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Long>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);

            }

            @Override
            public void onNext(List<Long> longs) {
                mutableLiveDataArrayID.setValue(longs);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public LiveData<List<Sinhvien>> getAllSinhvienSuccess(){
        return mutableLiveDataArraylist;
    }
    public LiveData<List<Long>> insertSinhvienSuccess(){
        return mutableLiveDataArrayID;
    }
}
