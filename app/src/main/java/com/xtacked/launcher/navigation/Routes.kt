package com.xtacked.launcher.navigation

object Routes {
    const val HOME = "home"
    const val MY_LIST = "mylist"
    const val DETAILS = "details"
    const val ARG_CONTENT_ID = "contentId"
    const val DETAILS_ROUTE = "$DETAILS/{$ARG_CONTENT_ID}"

    fun details(contentId: String): String = "$DETAILS/$contentId"
}
