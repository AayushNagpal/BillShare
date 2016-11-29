package billshare.com.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import billshare.com.activities.AddGroupActivity;
import billshare.com.activities.GroupActivity;
import billshare.com.activities.R;
import billshare.com.adapters.GroupAdapter;
import billshare.com.responses.ResponseStatus;
import billshare.com.restservice.RestServiceObject;
import billshare.com.utils.GroupInfo;
import billshare.com.utils.GroupsList;
import billshare.com.utils.ImageUtils;
import billshare.com.utils.PreferenceUtil;
import billshare.com.utils.StringConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView list;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupFragment newInstance(String param1, String param2) {
        GroupFragment fragment = new GroupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        View empty = view.findViewById(R.id.empty);
        list = (ListView) view.findViewById(R.id.friend_list);
        list.setEmptyView(empty);

        showFriendList();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Object itemAtPosition = (GroupInfo) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), GroupActivity.class);
                if (itemAtPosition instanceof GroupInfo) {

                    GroupInfo groupInfo = (GroupInfo) itemAtPosition;
                    ImageUtils.instance().setGroupInfo(groupInfo);
                   // intent.putExtra(StringConstants.GROUP_INFO, groupInfo);

                    startActivity(intent);
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddGroupActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void showFriendList() {
        Call<GroupsList> groups = RestServiceObject.getiRestServicesObject(getActivity()).groups(PreferenceUtil.instance(getActivity()).getIdFromSPreferences());
        groups.enqueue(new Callback<GroupsList>() {
            @Override
            public void onResponse(Call<GroupsList> call, Response<GroupsList> response) {
                GroupsList body = response.body();
                ResponseStatus responseStatus = body.getResponseStatus();
                if (responseStatus != null && responseStatus.getCode() == 200) {
                    List<GroupInfo> groupInfo = body.getGroupInfo();
                    GroupAdapter groupAdapter = new GroupAdapter(getActivity(), groupInfo);
                    groupAdapter.notifyDataSetChanged();
                    list.setAdapter(groupAdapter);
                }
            }

            @Override
            public void onFailure(Call<GroupsList> call, Throwable t) {

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      /*  if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
