package com.example.streamlinelife

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate

//for navigation bar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.streamlinelife.persistence.DBSupport
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){
    /**
     * FOR the navigation Drawer (Navigation)
     *
     * “Navigation drawer with Fragments Part 2 - youtube.” [Online]. Available: https://www.youtube.com/watch?v=zYVEMCiDcmY.[Accessed: 11-Mar-2022].
     *
     * E. Vaati, “How to code a navigation drawer for an Android app,” Code Envato Tuts+, 17-Jun-2021. [Online]. Available: https://code.tutsplus.com/tutorials/how-to-code-a-navigation-drawer-in-an-android-app--cms-30263. [Accessed: 29-Mar-2022].
     *
     */
    //lateinit --> initialize after declare
    private lateinit var drawerlayout: DrawerLayout
    private lateinit var navigation_menu: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navcontroller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set tool bar
        toolbar = findViewById(R.id.titleInAppBar)
        setSupportActionBar(toolbar)

        // layout of the main
        drawerlayout = findViewById(R.id.drawerView)
        navigation_menu = findViewById(R.id.navigationbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.HomePage, R.id.createReminderFragment, R.id.allReminderPage, R.id.completedPage, R.id.deadlinePage, R.id.calenderPage, R.id.settingPage),
            drawerlayout
        )

        navcontroller = findNavController(R.id.fragment_container_view)
        setupActionBarWithNavController(navcontroller,appBarConfiguration)
        navigation_menu.setupWithNavController(navcontroller)

        /**
         * get values from the  SharedPreferences
         * user1812111, user1812111user1812111 8311 gold badge11 silver badge1111 bronze badges, Pradip TilalaPradip Tilala 1, Rishabh JainRishabh Jain 1581111 bronze badges, and rgaraisayevrgaraisayev 36833 silver badges1212 bronze badges, “How to get value from shared preference in fragment?,” Stack Overflow, 01-Nov-1966. [Online]. Available: https://stackoverflow.com/questions/54360299/how-to-get-value-from-shared-preference-in-fragment. [Accessed: 31-Mar-2022].
         * */
        val sharedPreferences: SharedPreferences = getSharedPreferences("NightMode", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("NightMode",true)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        /**
         *
         * uncommet this to add dummy data
         * once data is added then please comment this line other wise it will add data again and again in the sqlite :)
         *
         * */
//        dummyData()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navcontroller.navigateUp(appBarConfiguration)
    }

    private fun dummyData() {
        DBSupport(this).addReminder("workout","","2022-03-28,06:45:00","",3,"Friday, Wednesday, Tuesday","","15 Mins, 45 Mins,",0,0)
        DBSupport(this).addReminder("work","assignments",	"2022-03-27,06:20:00",	"",1,	"Thrusday, Tuesday, Tuesday,","","15 Mins,",0,	0)
        DBSupport(this).addReminder("work","assignments","2022-03-29,06:45:00","",1,"Tuesday, Friday, Saturday","","30 Mins, 30 Mins, 10 Mins,",0,0)
        DBSupport(this).addReminder("dance","with my friend","2022-03-31,07:10:00","",2,"Saturday, Wednesday,","","15 Mins,",0,0)
        DBSupport(this).addReminder("dinner","with family","2022-04-01,06:10:00","halifax",2,"Friday, Wednesday, Tuesday","","15 Mins, 45 Mins,",0,0)
        DBSupport(this).addReminder("workout","","2022-03-25,05:45:00","",3,"","","45 Mins",1,0)
        DBSupport(this).addReminder("study","","2022-03-10,06:45:00","",3,"","", "10 Mins, 45 Mins",0,1)
        DBSupport(this).addReminder("meeting","with co-worker","2022-03-26,09:59:50","",3,"","","15 Mins, 45 Mins",1,0)
        DBSupport(this).addReminder("interview","with COVE","2022-03-20,06:45:00","",3,"","", "10 Mins, 45 Mins",0,1)
        DBSupport(this).addReminder("trynottoshowindeadline","assignments","2022-03-29,06:45:00","",1,"Tuesday, Friday, Saturday","","30 Mins, 30 Mins, 10 Mins,",1,0)
        DBSupport(this).addReminder("trynottoshowincomplete","assignments","2022-03-29,06:45:00","",1,"Tuesday, Friday, Saturday","","30 Mins, 30 Mins, 10 Mins,",0,1)
    }

}
