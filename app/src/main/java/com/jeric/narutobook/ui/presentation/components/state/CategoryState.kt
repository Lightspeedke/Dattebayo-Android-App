package com.jeric.narutobook.ui.presentation.components.state

import androidx.compose.ui.graphics.Color
import com.jeric.narutobook.R

data class CategoryState(
    val title: String,
    val iconUrl: Int,
    val startColor: Color,
    val endColor: Color,
) {
    companion object {
        val pokedex = CategoryState(
            title = "Clan",
            iconUrl = R.drawable.clan,
            startColor = Color(0xFFC0392B),
            endColor = Color(0xFF96281B),
        )

        val moves = CategoryState(
            title = "Character",
            iconUrl = R.drawable.naruto,
            startColor = Color(0xFFF39C12),
            endColor = Color(0xFFD68910),
        )

        val evolutions = CategoryState(
            title = "Villages",
            iconUrl = R.drawable.village,
            startColor = Color(0xFF138D75),
            endColor = Color(0xFF117A65),
        )

        val locations = CategoryState(
            title = "Akatsuki",
            iconUrl = R.drawable.akatsuki,
            startColor = Color(0xFF2C3E50),
            endColor = Color(0xFF1A252F),
        )
    }
}