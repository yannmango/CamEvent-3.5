package ca.uwaterloo.camevent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

import layout.SubPage01;

//import ca.uwaterloo.maptest.R;

public class MeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     *
     */
    NavigationView navigationView = null;

    Toolbar toolbar = null;
    private static final String TAG = "MeActivity";
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    static Boolean isTouched = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        SubPage01 fragment = new SubPage01();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();





        //SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.switch_compat);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //How to change elements in the header programatically
        View headerView = navigationView.getHeaderView(0);
        TextView emailText = (TextView) headerView.findViewById(R.id.email);


        TextView username = (TextView) headerView.findViewById(R.id.username);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
            username.setText(name);
            emailText.setText(email);
        } else {
            // No user is signed in

        }
        getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);

        navigationView.setNavigationItemSelectedListener(this);

        /*Menu newMenu=null;
        getMenuInflater().inflate(R.menu.activity_main_drawer,newMenu);
        SwitchCompat switchCompat = (SwitchCompat) newMenu.findItem(R.id.switch_compat).getMenuInfo();
        switchCompat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isTouched=true;
                return false;
            }
        });
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isTouched){
                    isTouched=false;
                    if(isChecked){
                        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }else{
                        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                }
            }
        });*/


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //new add:2016/10/27
    private void goToMapActivity() {
        //jump to second activity
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        this.finish();

    }
    private void goToSearchActivity() {
        //jump to second activity
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
        this.finish();
    }
    private void goToPostActivity() {
        //jump to second activity
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
        this.finish();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_me, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id==R.id.nav_search){
            goToSearchActivity();
            finish();
        }
        if(id==R.id.post){
            goToPostActivity();
            finish();
        }
        if(id==R.id.nav_map){
            goToMapActivity();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        SwitchCompat switchCompat=(SwitchCompat)findViewById(R.id.switch_compat);

        int id = item.getItemId();
        if(id==R.id.nav_theme){
           if(isTouched){
               isTouched=false;
               getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
               recreate();
           }else{
               isTouched=true;
               getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
               recreate();
           }

            /*switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    }else{
                        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    }
                }
            });*/

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
                View rootView = inflater.inflate(R.layout.fragment_sub_page01, container, false);
                String[] items={"Apple","Banana","Grape","A","B","C","D","E","F","G","H","I","J","K"};
                ListView listView=(ListView) rootView.findViewById(R.id.postlist);


                ArrayAdapter<String> listviewAdapter=new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        items
                );
                listView.setAdapter(listviewAdapter);
                /*ImageButton btnPost=(ImageButton) rootView.findViewById(R.id.btn_post);
                btnPost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(v.getContext(),RegisterActivity.class);
                        startActivity(intent);
                    }
                });*/



                return rootView;
            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){

                View rootView = inflater.inflate(R.layout.fragment_sub_page02, container, false);
                String[] items={"Apple","Banana","Grape"};
                final ListView listView=(ListView) rootView.findViewById(R.id.listv);
                final ArrayList<String> arrayList=new ArrayList<>(Arrays.asList(items));

                final ArrayAdapter<String> listviewAdapter=new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.list_item,
                        R.id.txtitem,
                        arrayList
                );
                listView.setAdapter(listviewAdapter);
                final EditText txtInput=(EditText)rootView.findViewById(R.id.txtinput);
                Button btAdd=(Button)rootView.findViewById(R.id.btadd);
                btAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newItem=txtInput.getText().toString();
                        arrayList.add(newItem);
                        listviewAdapter.notifyDataSetChanged();
                    }
                });
                return rootView;
            }else{
                View rootView = inflater.inflate(R.layout.fragment_sub_page03, container, false);
                String[] items={"Apple","Banana","Grape"};
                ListView listView=(ListView) rootView.findViewById(R.id.recommlist);


                ArrayAdapter<String> listviewAdapter=new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        items
                );
                listView.setAdapter(listviewAdapter);

                return rootView;
            }

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Post";
                case 1:
                    return "Favourite";
                case 2:
                    return "Recomm";
            }
            return null;
        }
    }
}

