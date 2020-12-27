package fr.nihilus.xenobladechronicles

import android.os.Bundle
import android.view.Menu
import dagger.android.support.DaggerAppCompatActivity
import fr.nihilus.xenobladechronicles.monsters.list.MonsterListFragment

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, MonsterListFragment())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean = true
}
