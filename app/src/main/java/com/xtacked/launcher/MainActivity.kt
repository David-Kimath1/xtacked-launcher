package com.xtacked.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.xtacked.launcher.navigation.Routes
import com.xtacked.launcher.ui.details.DetailsScreen
import com.xtacked.launcher.ui.home.HomeScreen
import com.xtacked.launcher.ui.mylist.MyListScreen
import com.xtacked.launcher.ui.theme.XtackedBg
import com.xtacked.launcher.ui.theme.XtackedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = (application as XtackedApp).container
        setContent {
            XtackedTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.HOME,
                    modifier = Modifier.fillMaxSize().background(XtackedBg)
                ) {
                    composable(Routes.HOME) {
                        HomeScreen(
                            container = container,
                            onOpenDetails = { id -> navController.navigate(Routes.details(id)) },
                            onOpenMyList = { navController.navigate(Routes.MY_LIST) }
                        )
                    }
                    composable(Routes.MY_LIST) {
                        MyListScreen(
                            container = container,
                            onOpenDetails = { id -> navController.navigate(Routes.details(id)) },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable(
                        route = Routes.DETAILS_ROUTE,
                        arguments = listOf(navArgument(Routes.ARG_CONTENT_ID) {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getString(Routes.ARG_CONTENT_ID).orEmpty()
                        DetailsScreen(
                            container = container,
                            contentId = id,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
