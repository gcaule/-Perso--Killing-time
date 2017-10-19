package fr.indianacroft.wildhunt;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by wilder on 16/10/17.
 */
public class ValidateAdapter extends BaseAdapter {

    private String mUserId;
    private String mQuestId;

    private Context context;
    private ArrayList<Pair> map;


    public ValidateAdapter(Context context, ArrayList<Pair>map) {
        this.context = context;
        this.map = map;
    }

    public int getCount() {
        return map.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        // Pour recuperer la key d'un user (pour le lier a une quête)
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mUserId = sharedPreferences.getString("mUserId", mUserId);
        mQuestId = sharedPreferences.getString("mCreatedQuest", "");

        Log.d("key", mUserId);
        /////////////////////////////////////////////////////////////////

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.validatechallenge_recyclerview,
                    null);
        }

        final String challengeName = map.get(position).first.toString();
        final String userName = map.get(position).second.toString();

        DatabaseReference refUser =
                FirebaseDatabase.getInstance().getReference().child("User").child(mUserId);
        final View finalConvertView = convertView;
        final View finalConvertView1 = convertView;
        refUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // on recupere la qûete créee par un user
                User user = dataSnapshot.getValue(User.class);
                final String mQuestId = user.getUser_createdquestID();

                // On recupere le nom du challenge
                DatabaseReference refChallenge = FirebaseDatabase.getInstance().getReference("Challenge").child(mQuestId).child(challengeName);
                refChallenge.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Challenge challenge = dataSnapshot.getValue(Challenge.class);
                        String challName = challenge.getChallenge_name();

                        TextView nameChall = finalConvertView.findViewById(R.id.challengeToValidate);
                        nameChall.setText(challName);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                // On recupere le user a valider
                DatabaseReference refUserToValidate = FirebaseDatabase.getInstance().getReference("User").child(userName);
                refUserToValidate.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        String userNom = user.getUser_name();

                        TextView nameUser = finalConvertView1.findViewById(R.id.userToValidate);
                        nameUser .setText(userNom);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        TextView nameChall = convertView.findViewById(R.id.challengeToValidate);
//        nameChall.setText(challengeName);

//        TextView nameUser = convertView.findViewById(R.id.userToValidate);
//        nameUser .setText(userName);

        Button see = (Button) convertView.findViewById(R.id.see);

        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChallengeToValidateActivity.class);
                intent.putExtra("ToValidate", challengeName);
                intent.putExtra("UserToValidate", userName);
                intent.putExtra("CreatedQuestId", mQuestId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}