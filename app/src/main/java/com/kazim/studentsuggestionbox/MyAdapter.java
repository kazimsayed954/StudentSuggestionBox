package com.kazim.studentsuggestionbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final String PO ="http://192.168.56.1/android_login_api/dislike.php?uid=" ;
    private static final String DE="http://192.168.56.1/android_login_api/like.php?uid=";
    private List<MyListData>myListData;
    private Context ct;
    private JsonArrayRequest request,request1;
    private RequestQueue requestQueue,rq;

    public MyAdapter(List<MyListData> myListData, Context ct) {
        this.myListData = myListData;
        this.ct = ct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final MyListData mk=myListData.get(i);
        Picasso.with(ct)
                .load(mk.getImageurl())
                .into(viewHolder.img);
        viewHolder.likecount.setText(mk.getLikecount());
        viewHolder.status.setText(mk.getStatus());
        viewHolder.description.setText(mk.getdescription());
        if (AllSuggestionActivity.myDatabase.myDao().isLiked(mk.getId())==1)
            viewHolder.likebtn.setImageResource(R.drawable.ic_favorite_black_24dp);
        else
            viewHolder.likebtn.setImageResource(R.drawable.ic_like_border_black_24dp);

        viewHolder.likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeDislike_List mt=new LikeDislike_List();
                //String uid=mk.getId();
                int id=mk.getId();
                String imp=mk.getImageurl();
                mt.setId(id);
                mt.setName(imp);

                if (AllSuggestionActivity.myDatabase.myDao().isLiked(id)!=1){
                    viewHolder.likebtn.setImageResource(R.drawable.ic_favorite_black_24dp);
                    AllSuggestionActivity.myDatabase.myDao().addData(mt);

                    String mo=mk.getLikecount();
                    viewHolder.likecount.setText(mo);

                    delieteLike(id,viewHolder);


                }else {
                    AllSuggestionActivity.myDatabase.myDao().delete(mt);
                    viewHolder.likebtn.setImageResource(R.drawable.ic_like_border_black_24dp);
                    String mo=mk.getLikecount();

                    addLike(id,viewHolder);

                }


            }
        });
    }

    private void addLike(final int id, final ViewHolder viewHolder) {
        request=new JsonArrayRequest(PO + id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;

                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject object=response.getJSONObject(i);
                        String like=object.getString("like");
                        viewHolder.likecount.setText(like);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue= Volley.newRequestQueue(ct);
        requestQueue.add(request);


    }

    private void delieteLike(final int id, final ViewHolder viewHolder) {
        request1 =new JsonArrayRequest(DE + id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject object=response.getJSONObject(i);
                        String like=object.getString("like");
                        viewHolder.likecount.setText(like);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq=Volley.newRequestQueue(ct);
        rq.add(request1);

    }

    @Override
    public int getItemCount() {
        return myListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img,likebtn;
        private TextView likecount,description,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageview);
            likebtn=(ImageView)itemView.findViewById(R.id.like_btn);
            likecount=(TextView)itemView.findViewById(R.id.like_count);
            description=itemView.findViewById(R.id.desc);
            status=itemView.findViewById(R.id.status);
        }
    }
}
