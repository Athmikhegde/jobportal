package com.example.jobportal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobportal.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostAdapter extends FirebaseRecyclerAdapter<Data, PostAdapter.MyViewHolder> {
    private Context context;
    public PostAdapter(@NonNull FirebaseRecyclerOptions<Data> options, Context context) {
        super(options);
        this.context =context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Data model) {
            holder.mtitle.setText(model.getTitle());
            holder.mdate.setText(model.getDate());
            holder.mdesc.setText(model.getDesc());
            holder.mskill.setText(model.getSkill());
            holder.msalary.setText(model.getSalary());
            holder.mlink.setText(model.getLink());

            //edit from recyclerview
            holder.editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogPlus dialog = DialogPlus.newDialog(context)
                            .setGravity(Gravity.CENTER)
                            .setMargin(50,0,50,0)
                            .setContentHolder(new ViewHolder(R.layout.content))
                            .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                            .create();
                    View holderView =dialog.getHolderView();
                    EditText title =holderView.findViewById(R.id.titletxt2);
                    EditText desc =holderView.findViewById(R.id.desctxt1);
                    EditText salary =holderView.findViewById(R.id.salarystxt2);
                    EditText skill= holderView.findViewById(R.id.skillstxt2);
                    EditText link = holderView.findViewById(R.id.linkstxt2);
                    Button update = holderView.findViewById(R.id.update);

                    title.setText(model.getTitle());
                    desc.setText(model.getDesc());
                    skill.setText(model.getSkill());
                    salary.setText(model.getSalary());
                    link.setText(model.getLink());

                     update.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {

                             Map<String,Object> map =new HashMap<>();
                             map.put("title",title.getText().toString());
                             map.put("desc",desc.getText().toString());
                             map.put("skill",skill.getText().toString());
                             map.put("salary",salary.getText().toString());
                             map.put("link",link.getText().toString());
                             FirebaseAuth mauth;
                             mauth=FirebaseAuth.getInstance();
                             FirebaseUser user = mauth.getCurrentUser();
                             String uId = user.getUid();
                             FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId)
                                     .child(getRef(position)
                                             .getKey()).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                         @Override
                                         public void onComplete(@NonNull Task<Void> task) {
                                             dialog.dismiss();
                                         }
                                     });


                         }
                     });
                    dialog.show();

                }
            });
            //delete from recycler view
            holder.deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogPlus dialog = DialogPlus.newDialog(context)
                            .setGravity(Gravity.CENTER)
                            .setMargin(50,0,50,0)
                            .setContentHolder(new ViewHolder(R.layout.activity_dialogbox))
                            .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                            .create();
                    View holderView =dialog.getHolderView();
                    TextView yes,no;
                    yes=holderView.findViewById(R.id.yes);
                    no=holderView.findViewById(R.id.no);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FirebaseAuth mauth;
                            mauth=FirebaseAuth.getInstance();
                            FirebaseUser user = mauth.getCurrentUser();
                            String uId = user.getUid();
                            FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId).child(getRef(position).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_job_item,parent,false);
        return new MyViewHolder(view);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mtitle, mdate, mdesc, mskill, msalary, mlink;
        ImageButton editbtn,deletebtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mtitle = itemView.findViewById(R.id.titletxt2);
            mdate = itemView.findViewById(R.id.datetxt);
            mdesc = itemView.findViewById(R.id.desctxt1);
            mskill = itemView.findViewById(R.id.skillstxt2);
            msalary = itemView.findViewById(R.id.salarystxt2);
            mlink = itemView.findViewById(R.id.linkstxt2);
            //edit delete
            editbtn=itemView.findViewById(R.id.edit);
            deletebtn=itemView.findViewById(R.id.delete);
        }

    }
}

        /*public void setJobTitle(String title) {
            TextView mtitle = myview.findViewById(R.id.titletxt);
            mtitle.setText(title);
        }

        public void setJobDate(String date) {
            TextView mdate = myview.findViewById(R.id.datetxt);
            mdate.setText(date);
        }

        public void setJobdesc(String desc) {
            TextView mdesc = myview.findViewById(R.id.desctxt);
            mdesc.setText(desc);
        }

        public void setJobskill(String skill) {
            TextView mskill = myview.findViewById(R.id.skillstxt);
            mskill.setText(skill);
        }

        public void setJobsalary(String salary) {
            TextView msalary = myview.findViewById(R.id.salarystxt);
            msalary.setText(salary);
        }

        public void setJoblink(String link) {
            TextView mlink = myview.findViewById(R.id.linkstxt);
            mlink.setText(link);
        }
    }
}*/

