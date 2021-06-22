package com.mi.walkthroughandroid.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mi.walkthroughandroid.R
import com.mi.walkthroughandroid.data.WalkThroughModel
import com.mi.walkthroughandroid.main.WalkThroughScreenMaker

class WalkThroughActivity : AppCompatActivity() {

    companion object {
        const val WALK_THROUGH_KEY = "WalkThroughActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_through)
        initUi()
    }

    private fun initUi() {
        val walkThroughModel = intent.extras?.getParcelable<WalkThroughModel>(WALK_THROUGH_KEY)
        walkThroughModel?.let { model ->
            WalkThroughScreenMaker(
                findViewById(R.id.rootLayout),
                model,
                this,
                ::onSkipOrFinish
            )
        }
    }

    private fun onSkipOrFinish(isFromOnSkip: Boolean) {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onBackPressed() {
    }
}