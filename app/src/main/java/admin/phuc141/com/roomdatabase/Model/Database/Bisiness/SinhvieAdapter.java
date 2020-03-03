package admin.phuc141.com.roomdatabase.Model.Database.Bisiness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import admin.phuc141.com.roomdatabase.Model.Database.Sinhvien;
import admin.phuc141.com.roomdatabase.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SinhvieAdapter extends RecyclerView.Adapter<SinhvieAdapter.ViewHoler> {
    private Context context;
    private List<Sinhvien> msinhvien;

    public SinhvieAdapter(Context context, List<Sinhvien> sinhvien) {
        this.context = context;
        this.msinhvien = sinhvien;
        notifyDataSetChanged();
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

        Sinhvien sinhvien = msinhvien.get(position);
        holder.mten.setText(sinhvien.getTen());
        holder.mnamsinh.setText(""+ sinhvien.getNamSinh());
        holder.mquequan.setText(sinhvien.getQueQuan());
        holder.mbtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "vị trí "+ position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return msinhvien!=null ? msinhvien.size() : 0;
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        private TextView mten,mnamsinh, mquequan;
        private Button mbtnEdit;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            mten=itemView.findViewById(R.id.hotensv);
            mnamsinh=itemView.findViewById(R.id.namsinhsv);
            mquequan=itemView.findViewById(R.id.quequansv);
            mbtnEdit=itemView.findViewById(R.id.btnEdit);
        }
    }
}
