package admin.phuc141.com.roomdatabase.ViewModel;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.Callable;

import admin.phuc141.com.roomdatabase.Model.Database.Sinhvien;
import admin.phuc141.com.roomdatabase.Repository.MainRepository;
import androidx.lifecycle.LifecycleObserver;
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
    private MutableLiveData mutableLiveDataArrayUDID= new MutableLiveData();
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

    public void updateRoomSv(final Context context, final Sinhvien sinhvien){
        Observable <Sinhvien> observable = Observable.fromCallable(new Callable() {
            @Override
            public Object call() throws Exception {
                MainRepository.getInstance(context).updateRoomSinhvien(sinhvien);
                return null;
            }
        });
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        });


    }
    public void UpdateAsycsinhvien (Context context, Sinhvien sinhvien){
        new upDatesinhvien(context).execute(sinhvien);
    }
    public void DeleteSinhvien (Context context, Sinhvien sinhvien){
        new deleteSinhvien(context).execute(sinhvien);
    }
    public void deleteAllSinhvien(Context context){
        new deleteAllsinhvien(context).execute();
    }
    private class upDatesinhvien extends AsyncTask<Sinhvien,Void,Void>{
        private Context context;
        //Tạo contructor để có thể truyền biến context từ bên ngoài vào
        private upDatesinhvien(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Sinhvien... sinhviens) {
            MainRepository.getInstance(context).updateSinhvien(sinhviens[0]);
            return null;
        }
    }

    private class deleteSinhvien extends AsyncTask<Sinhvien, Void, Void>{
        private Context context;

        public deleteSinhvien(Context context) {
            this.context = context;
        }
        @Override
        protected Void doInBackground(Sinhvien... sinhviens) {
            MainRepository.getInstance(context).deletesinhvien(sinhviens[0]);
            return null;
        }
    }

    private class deleteAllsinhvien extends AsyncTask<Void,Void,Void>{
        private Context context;

        public deleteAllsinhvien(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MainRepository.getInstance(context).deleteAllSinhvien();
            return null;
        }
    }
    public LiveData<List<Sinhvien>> getAllSinhvienSuccess(){
        return mutableLiveDataArraylist;
    }
    public LiveData<List<Long>> insertSinhvienSuccess(){
        return mutableLiveDataArrayID;
    }

}
