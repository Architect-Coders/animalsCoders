package com.architectcoders.animalcoders.search.detail

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.architectcoders.domain.model.Animal

class AnimalDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    fun setAnimal(animal: Animal) = with(animal) {
        text = buildSpannedString {

            bold { append("Gender: ") }
            appendln(gender.toString())

            bold { append("Age: ") }
            appendln(age)

            bold { append("More about: ") }
            appendln(description)
        }
    }
}