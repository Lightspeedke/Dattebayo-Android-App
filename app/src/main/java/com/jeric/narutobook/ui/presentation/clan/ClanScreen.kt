package com.jeric.narutobook.ui.presentation.clan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jeric.narutobook.R
import com.jeric.narutobook.domain.model.clan.ClanModel
import com.jeric.narutobook.ui.presentation.helper.LocalSafeArea
import com.jeric.narutobook.ui.theme.Blue300
import com.jeric.narutobook.ui.theme.Blue500
import com.jeric.narutobook.ui.theme.Green300
import com.jeric.narutobook.ui.theme.Green500
import com.jeric.narutobook.ui.theme.Red300
import com.jeric.narutobook.ui.theme.Red500
import com.jeric.narutobook.ui.theme.Yellow300
import com.jeric.narutobook.ui.theme.Yellow500
import com.jeric.narutobook.ui.theme.titleColor


@Composable
fun ClanScreen(clanModel: ClanViewModel, onBack: ()-> Unit) {
    val clanList by clanModel.clanList.collectAsStateWithLifecycle()
    ClanContent(clanModel = clanList, onBack = onBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClanContent(clanModel: List<ClanModel>, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Naruto Book Clan",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 20.dp, bottom = 6.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBack() }, modifier = Modifier.testTag("clan_back"),
                    ) {
                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        modifier = Modifier.padding(LocalSafeArea.current)
    ) { paddingValue ->
        Box(
            modifier = Modifier.padding(paddingValue)
        ) {
            BoxWithConstraints {
                val columns = when(maxWidth) {
                    in 0.dp..349.dp -> 1
                    in 350.dp..599.dp -> 2
                    in 600.dp..899.dp -> 3
                    in 900.dp..1199.dp -> 4
                    else -> 5
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(20.dp),
                    modifier = Modifier
                        .testTag("clan_col")
                ) {
                    items(items = clanModel, key = {it.id}){ data ->
                        ClanContentItem(
                            modifier = Modifier.testTag(data.id.toString()),
                            clanModel = data,
                            onClanModelClick = {}
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ClanContentItem(
    modifier: Modifier = Modifier,
    clanModel: ClanModel,
    onClanModelClick: (ClanModel) -> Unit,
    contentColor: Color = titleColor
) {

    val brush = remember {
        Brush.linearGradient(
            listOf(
                listOf(
                    Red300,
                    Red500,
                ),
                listOf(
                    Yellow300,
                    Yellow500,
                ),
                listOf(
                    Green300,
                    Green500,
                ),
                listOf(
                    Blue300,
                    Blue500,
                ),
            ).random()
        )
    }

    val interactionSource = remember { MutableInteractionSource() }
    val annotatedStr by remember {
        mutableStateOf(
            buildAnnotatedString {
                withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                    append("$${100.00}")
                }
            }
        )
    }

    Box(
        modifier = modifier
            .aspectRatio(1.2f),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = brush, alpha = .4f, shape = MaterialTheme.shapes.medium)
        )
        Text(
            modifier = Modifier
                .padding(all = 6.dp)
                .align(Alignment.BottomStart)
                .alpha(0.3f),
            textAlign = TextAlign.Center,
            text = clanModel.name.trim(),
            maxLines = 1,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = contentColor,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier
                .padding(all = 6.dp)
                .align(Alignment.TopStart)
                .alpha(0.3f),
            textAlign = TextAlign.Center,
            text = clanModel.id.toString(),
            fontWeight = FontWeight.Black,
            fontSize = 50.sp,
        )

        Image(
            modifier = Modifier
                .fillMaxSize()
                .offset(35.dp, (-20).dp),
            painter = painterResource(id = R.drawable.onboarding_3),
            contentDescription = "Product Image"
        )
    }
}