package com.hookedroid.androidarchitecture.util

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.sendShortToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.sendShortToast(resId: Int) {
    Toast.makeText(requireContext(), getString(resId), Toast.LENGTH_SHORT).show()
}

fun Fragment.sendLongToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun Fragment.sendLongToast(resId: Int) {
    Toast.makeText(requireContext(), getString(resId), Toast.LENGTH_LONG).show()
}