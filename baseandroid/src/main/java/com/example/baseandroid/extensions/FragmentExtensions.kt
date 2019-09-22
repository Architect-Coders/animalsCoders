package com.example.baseandroid.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(
    func: FragmentTransaction.() -> FragmentTransaction
) {
    beginTransaction().func().commit()
}

fun Fragment.addFragment(frameId: Int, fragment: Fragment, tag: String?) {
    childFragmentManager.inTransaction {
        add(
            frameId,
            fragment,
            tag ?: fragment.javaClass.simpleName
        )
    }
}

fun Fragment.replaceFragment(frameId: Int, fragment: Fragment, tag: String?) {
    childFragmentManager.inTransaction {
        replace(
            frameId,
            fragment,
            tag ?: fragment.javaClass.simpleName
        )
    }
}
