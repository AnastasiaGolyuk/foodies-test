package test.team.nti.foodies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import test.team.nti.foodies.presentation.cart.CartScreen
import test.team.nti.foodies.presentation.catalog.CatalogScreen
import test.team.nti.foodies.presentation.catalog.CatalogViewModel
import test.team.nti.foodies.presentation.item_card.ItemCardScreen
import test.team.nti.foodies.presentation.item_card.ItemCardViewModel
import test.team.nti.foodies.presentation.splash_screen.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Route.SplashScreen.route
    ) {

        composable(route = Route.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Route.CatalogScreen.route) {
            val viewModel : CatalogViewModel = hiltViewModel()
            CatalogScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = "${Route.ItemCardScreen.route}/{itemId}",
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                }
            )) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            val viewModel : ItemCardViewModel = hiltViewModel()
            ItemCardScreen(navController = navController,itemId = itemId!!, viewModel = viewModel)

        }
        composable(route = Route.CartScreen.route) {
            CartScreen(navController = navController)
        }
    }
}