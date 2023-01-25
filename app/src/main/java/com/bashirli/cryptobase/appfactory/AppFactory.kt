package com.bashirli.cryptobase.appfactory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bashirli.cryptobase.repo.Repo
import com.bashirli.cryptobase.view.ScreenFragment
import javax.inject.Inject

class AppFactory @Inject constructor(
    var repo: Repo
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
       return when(className){
            ScreenFragment::class.java.name->ScreenFragment(repo)
            else->return super.instantiate(classLoader, className)
        }

    }
}