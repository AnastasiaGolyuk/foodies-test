package test.team.nti.foodies.presentation.navigation

sealed class Route(
    val route: String
) {
    data object SplashScreen : Route(route = "splashScreen")

    data object CatalogScreen : Route(route = "catalogScreen")

    data object ItemCardScreen : Route(route = "itemCardScreen")

    data object CartScreen : Route(route = "cartScreen")
}