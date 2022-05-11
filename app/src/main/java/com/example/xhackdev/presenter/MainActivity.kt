package com.example.xhackdev.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.ActivityMainBinding
import com.example.xhackdev.presenter.fragments.TabsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private val vm: MainViewModel by viewModels()

    // nav controller of the current screen
    private var navController: NavController? = null

    private val topLevelDestinations = setOf(getTabsDestination(), getLoginDestination(), getHomeDestination())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splash = installSplashScreen()
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        observeSplashScreenVisibility(splash)

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, true)
    }


    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        navController = null
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (isStartDestination(navController?.currentDestination)) {
            super.onBackPressed()
        } else {
            navController?.popBackStack()
        }
    }


    private fun observeSplashScreenVisibility(splash: SplashScreen) {
        splash.setKeepOnScreenCondition{ true }

        binding.root.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {

                    vm.isSingIn.value?.let {
                        setupStartDestination(it)
                        binding.root.viewTreeObserver.removeOnPreDrawListener(this)
                        splash.setKeepOnScreenCondition{ false }
                        return true
                    }

                    return false
                }
            }
        )
    }


    // fragment listener is sued for tracking current nav controller
    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            if (f is TabsFragment || f is NavHostFragment) return
            onNavControllerActivated(f.findNavController())
        }
    }


    private fun onNavControllerActivated(navController: NavController) {
        if (this.navController == navController) return
        this.navController = navController
    }


    private fun setupStartDestination(isSingIn: Boolean) {
        val navController = getRootNavController()
        val graph = navController.navInflater.inflate(getMainNavigationGraphId())

        if (isSingIn) {
            graph.setStartDestination(getTabsDestination())
        } else {
            graph.setStartDestination(getLoginDestination())
        }

        navController.graph = graph
        onNavControllerActivated(navController)
    }


    private fun isStartDestination(destination: NavDestination?): Boolean {
        if (destination == null) return false
        return topLevelDestinations.contains(destination.id)
    }


    private fun getRootNavController(): NavController {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.mainFragmentContainer) as NavHostFragment
        return navHost.navController
    }

    private fun getTabsDestination(): Int = R.id.tabsFragment

    private fun getLoginDestination(): Int = R.id.loginFragment

    private fun getHomeDestination(): Int = R.id.homeFragment

    private fun getMainNavigationGraphId(): Int = R.navigation.main_nav_graph
}