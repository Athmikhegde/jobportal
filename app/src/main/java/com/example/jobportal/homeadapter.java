package com.example.jobportal;



        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.jobportal.Model.Data;
        import com.firebase.ui.database.FirebaseRecyclerAdapter;
        import com.firebase.ui.database.FirebaseRecyclerOptions;

public class homeadapter extends FirebaseRecyclerAdapter<Data, homeadapter.MyViewHolder> {

    public homeadapter(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Data model) {
        holder.mtitle.setText(model.getTitle());
        holder.mdate.setText(model.getDate());
        holder.mdesc.setText(model.getDesc());
        holder.mskill.setText(model.getSkill());
        holder.msalary.setText(model.getSalary());
        holder.mlink.setText(model.getLink());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item_home,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mtitle, mdate, mdesc, mskill, msalary, mlink;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mtitle = itemView.findViewById(R.id.titletxt2);
            mdate = itemView.findViewById(R.id.datetxt);
            mdesc = itemView.findViewById(R.id.desctxt1);
            mskill = itemView.findViewById(R.id.skillstxt2);
            msalary = itemView.findViewById(R.id.salarystxt2);
            mlink = itemView.findViewById(R.id.linkstxt2);

        }
    }
}


