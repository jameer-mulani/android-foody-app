package com.example.foodyappstefan2023.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foodyappstefan2023.R

class RecipesRowBinding {
    companion object{

        @BindingAdapter("recipeImage")
        @JvmStatic

        fun setRecipeImage(imageView: ImageView, imageUrl : String){
            imageView.load(imageUrl){
                crossfade(600)
                error(R.drawable.ic_error_placeholder)

            }
        }

        @BindingAdapter("numOfLikes")
        @JvmStatic
        fun setNumberOfLikes(textView: TextView, likes: Int){
            textView.text = likes.toString()
        }

        @BindingAdapter("cookingMinutes")
        @JvmStatic

        fun setCookingMinutes(textView: TextView, minutes : Int){
            textView.text = minutes.toString()
        }

        @BindingAdapter("veganColor")
        @JvmStatic

        fun setVeganTint(view: View, isVegan : Boolean){
            if (isVegan){
                when(view){
                    is TextView ->{
                        view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
                    }
                    is ImageView ->{
                        view.setColorFilter(ContextCompat.getColor(view.context, R.color.green))
                    }
                }
            }
        }
    }

}