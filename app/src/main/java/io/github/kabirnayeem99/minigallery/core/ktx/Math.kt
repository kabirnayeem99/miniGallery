package io.github.kabirnayeem99.minigallery.core.ktx

import kotlin.math.round


fun Float.round(decimals: Int): Float {
    var multiplier = 1.0F
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}