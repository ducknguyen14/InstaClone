package com.example.instaclone;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instaclone.Utils.BottomNavigationViewHelper;
import com.example.instaclone.Utils.SquareImageView;
import com.example.instaclone.Utils.UniversalImageLoader;
import com.example.instaclone.models.Photo;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ViewPostFragment extends Fragment {

    private static final String TAG = "ViewPostFragment/DEBUG";

    public ViewPostFragment()
    {
        super();

        /*
            Notes: An empty bundle can cause a NullPtrException. Need to always do setArguments to new
                bundle in the constructor when passing information through a bundle. (We passed arguments
                through bundle from OnGridImageSelectedListener interface)

         */
        setArguments(new Bundle());
    }

    // Notes: Widgets
    private SquareImageView mPostImage;
    private BottomNavigationViewEx bottomNavigationView;
    private TextView mBackLabel, mCaption, mUsername, mTimestamp;
    private ImageView mBackArrow, mEllipses, mHeartRed, mHeartWhite, mProfileImage;



    // Notes: Variables
    private Photo mPhoto;
    private int mActivityNumber = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_view_post, container, false);

        mPostImage = (SquareImageView) view.findViewById(R.id.post_image);
        bottomNavigationView = (BottomNavigationViewEx) view.findViewById(R.id.bottomNavViewBar);
        mBackArrow = (ImageView) view.findViewById(R.id.backArrow);
        mBackLabel = (TextView) view.findViewById(R.id.tvBackLabel);
        mCaption = (TextView) view.findViewById(R.id.image_caption);
        mUsername = (TextView) view.findViewById(R.id.username);
        mTimestamp = (TextView) view.findViewById(R.id.image_time_posted);
        mEllipses = (ImageView) view.findViewById(R.id.ivEllipses);
        mHeartRed = (ImageView) view.findViewById(R.id.image_heart_red);
        mHeartWhite = (ImageView) view.findViewById(R.id.image_heart);
        mProfileImage = (ImageView) view.findViewById(R.id.profile_photo);

        try
        {
            mPhoto = getPhotoFromBundle();
            UniversalImageLoader.setimage(mPhoto.getImage_path(), mPostImage, null, "");
            mActivityNumber = getActivityNumFromBundle();

        }
        catch(NullPointerException e)
        {
            // Notes: Bundle could be null
            Log.e(TAG, "\tonCreateView: NullPointerException: " + e.getMessage());

        }

        setupBottomNavigationView();

        return view;
    }


    /**
     * Notes: Retrieve the activity number from the incoming bundle from ProfileActivity interface OnGridImageSelectedListener
     * @return
     */
    private int getActivityNumFromBundle()
    {
        Log.d(TAG, "\tgetPhotoFromBundle: arguments: " + getArguments());

        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            return bundle.getInt(getString(R.string.activity_number));
        }
        else
        {
            return 0;
        }
    }


    /**
     * Notes: Retrieve the photo from the incoming bundle from ProfileActivity interface OnGridImageSelectedListener
     * @return
     */
    private Photo getPhotoFromBundle()
    {
        Log.d(TAG, "\tgetPhotoFromBundle: arguments: " + getArguments());

        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            return bundle.getParcelable(getString(R.string.photo));
        }
        else
        {
            return null;
        }
    }


    /**
     * Notes: BottomNavigationView setup
     */
    private void setupBottomNavigationView()
    {
        Log.d(TAG, "\tsetupBottomNavigationView: setting up BottomNavigationView");

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView);

        BottomNavigationViewHelper.enableNavigation(getActivity(), getActivity() ,bottomNavigationView);

        // Notes: Highlighting the correct Icon when navigating
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(mActivityNumber);
        menuItem.setChecked(true);


    }


}

