package admin.phuc141.com.roomdatabase.Model.Database.Bisiness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import admin.phuc141.com.roomdatabase.Model.Database.Sinhvien;
import admin.phuc141.com.roomdatabase.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SinhvienAdapter extends RecyclerView.Adapter<SinhvienAdapter.ViewHoler> {
    int REQUEST_CODE_UPDATE =234;
    private ontiemlistener montiemlistener, monDeleteListener;
    private Context context;
    private List<Sinhvien> msinhvien;

    public SinhvienAdapter(Context context, List<Sinhvien> sinhvien) {
        this.context = context;
        this.msinhvien = sinhvien;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.item_sinhvien,parent,false);
        ViewHoler viewHoler =new ViewHoler(view);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, final int position) {

        final Sinhvien sinhvien = msinhvien.get(position);
        holder.mten.setText(sinhvien.getTen());
        holder.mnamsinh.setText(""+ sinhvien.getNamSinh());
        holder.mquequan.setText(sinhvien.getQueQuan());
        holder.mImgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montiemlistener.onitemclick(msinhvien.get(position),position);
            }
        });
        holder.mImgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               monDeleteListener.onitemclick(msinhvien.get(position),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        //là câu lệnh ìf else
        return msinhvien!=null ? msinhvien.size() : 0;
    }

    public void setOnItemClick (ontiemlistener ontiemlistener){montiemlistener=ontiemlistener;}
    public void setDeleteClick(ontiemlistener ontiemlistener){monDeleteListener=ontiemlistener;}

    public class ViewHoler extends RecyclerView.ViewHolder{
        private TextView mten,mnamsinh, mquequan;
        private ImageButton mImgDelete, mImgEdit;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            mten=itemView.findViewById(R.id.hotensv);
            mnamsinh=itemView.findViewById(R.id.namsinhsv);
            mquequan=itemView.findViewById(R.id.quequansv);
            mImgEdit=itemView.findViewById(R.id.btnEdit);
            mImgDelete = itemView.findViewById(R.id.btndelete);
        }
    }
}
