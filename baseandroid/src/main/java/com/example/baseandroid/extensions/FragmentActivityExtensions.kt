package com.example.baseandroid.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.baseandroid.enum.AnimationsFragments

fun AppCompatActivity.addFragment(
    fragment: Fragment,
    frameId: Int,
    tag: String?,
    animation: AnimationsFragments?
) {
    supportFragmentManager.inTransaction {
        if (animation != null) this.getAnimation(animation)
        add(
            frameId,
            fragment,
            tag ?: fragment.javaClass.simpleName
        )
    }
}

fun AppCompatActivity.addFragment(
    fragment: Fragment,
    frameId: Int,
    tag: String?,
    addToStack: Boolean,
    animation: AnimationsFragments?
) {
    supportFragmentManager.inTransaction {
        if (animation != null) this.getAnimation(animation)
        if (addToStack) add(frameId, fragment, tag ?: fragment.javaClass.simpleName)
            .addToBackStack(tag ?: fragment.javaClass.simpleName)
        else add(frameId, fragment)
    }
}


fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    tag: String?,
    animation: AnimationsFragments?
) {
    supportFragmentManager.inTransaction {
        if (animation != null) this.getAnimation(animation)
        replace(
            frameId,
            fragment,
            tag ?: fragment.javaClass.simpleName
        )
    }
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    tag: String?,
    addToStack: Boolean,
    animation: AnimationsFragments?
) {
    supportFragmentManager.inTransaction {
        if (animation != null) this.getAnimation(animation)
        if (addToStack) replace(frameId, fragment, tag ?: fragment.javaClass.simpleName)
            .addToBackStack(tag ?: fragment.javaClass.simpleName)
        else
            replace(frameId, fragment, tag ?: fragment.javaClass.simpleName)
    }
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    tag: String?,
    addToStack: Boolean,
    clearBackStack: Boolean,
    animation: AnimationsFragments?
) {
    supportFragmentManager.inTransaction {
        if (animation != null) this.getAnimation(animation)
        if (clearBackStack && supportFragmentManager.backStackEntryCount > 0) {
            val first = supportFragmentManager.getBackStackEntryAt(0)
            supportFragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        if (addToStack) replace(frameId, fragment, tag ?: fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
        else
            replace(frameId, fragment, tag ?: fragment.javaClass.simpleName)
    }
}

fun AppCompatActivity.popBackStack() {
    supportFragmentManager.popBackStack()
}

fun AppCompatActivity.popBackStackInclusive() {
    if (supportFragmentManager.backStackEntryCount > 0)
        supportFragmentManager.popBackStack(
            supportFragmentManager.getBackStackEntryAt(0).id,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
}

fun AppCompatActivity.getCurrentFragment(tag: String?): Fragment? {
    val fragmentManager = supportFragmentManager
    var fragmentTag: String = tag ?: ""

    if (fragmentTag.isEmpty() && fragmentManager.backStackEntryCount > 0)
        fragmentTag =
            fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name ?: ""

    return fragmentManager.findFragmentByTag(fragmentTag)
}