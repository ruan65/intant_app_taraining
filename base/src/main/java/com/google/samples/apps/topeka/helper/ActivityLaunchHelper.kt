package com.google.samples.apps.topeka.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import com.google.samples.apps.topeka.activity.CategorySelectionActivity
import com.google.samples.apps.topeka.activity.QuizActivity
import com.google.samples.apps.topeka.activity.SignInActivity
import com.google.samples.apps.topeka.model.Category

class ActivityLaunchHelper {

    companion object {

        const val EXTRA_EDIT = "EDIT"
        private const val URL_BASE = "https://topeka.instantappsample.com"
        private const val URL_SIGNIN = "$URL_BASE/signin"
        private const val URL_CATEGORIES = "$URL_BASE/categories"
        private const val URL_QUIZ_BASE = "$URL_BASE/quiz/"

        fun launchCategorySelection(activity: Activity, options: ActivityOptionsCompat? = null) {
            val starter = categorySelectionIntent(activity)
            if (options == null) {
                activity.startActivity(starter)
            } else {
                ActivityCompat.startActivity(activity, starter, options.toBundle())
            }
        }

        fun launchSignIn(activity: Activity, edit: Boolean = false) {
            ActivityCompat.startActivity(activity,
                    signInIntent(activity, edit),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle())
        }

        fun categorySelectionIntent(context: Context? = null) =
                baseIntent(URL_CATEGORIES, context)

        fun quizIntent(category: Category, context: Context? = null) =
                baseIntent("$URL_QUIZ_BASE${category.id}", context).apply {
                    putExtra(Category.TAG, category.id) }

        fun signInIntent(context: Context? = null, edit: Boolean = false): Intent =
                baseIntent(URL_SIGNIN, context).putExtra(EXTRA_EDIT, edit)

        private fun baseIntent(url: String, context: Context? = null): Intent {

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    .addCategory(Intent.CATEGORY_DEFAULT)
                    .addCategory(Intent.CATEGORY_BROWSABLE)
            if (null != context) {
                intent.`package` = context.packageName
            }
            return  intent
        }
    }
}