package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.BearViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType",
    "MutableCollectionMutableState"
)
@Composable
fun BearScreen(onClose: () -> Unit, onDone: () -> Unit) {

    val viewModel = viewModel<BearViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BearViewModel() as T
            }
        }
    )

    val rotate: Float by animateFloatAsState(viewModel.currentRotation)

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

    Scaffold(
        topBar = { TopBar(text = stringResource(R.string.title_location_bear), onClose = onClose) },
    ) {
    }

    Box(modifier = Modifier .fillMaxSize() .background(MaterialTheme.colorScheme.secondary)) {
        viewModel.drops.forEach {
            var alignment = Alignment.TopCenter
            if (it.isDropped) {
                alignment = Alignment.BottomCenter
            }
            Image(
                painter = painterResource(
                    id = it.imgPath),
                contentDescription = "Honey",
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.extraLarge)
                    .animatePlacement()
                    .size(50.dp, 50.dp)
                    .align(alignment),
            )
        }
    }

    Column(modifier = Modifier .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(modifier = Modifier) {

            Column {
                Text(text = "Schüttet dein Smartphone, damit etwas Honig aus dem Bienennest tropft")
                Box {
                    Image(
                        painter = painterResource(
                            id = R.drawable.beetree),
                        contentDescription = "Tree",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                    )

                    Image(
                        painter = painterResource(
                            id = R.drawable.beehome),
                        contentDescription = "Bee Home",
                        modifier = Modifier
                            .padding(start = MaterialTheme.spacing.extraLarge, top = MaterialTheme.spacing.large, end = MaterialTheme.spacing.extraLarge)
                            .rotate(rotate)
                            .clickable {
                                viewModel.rotate()
                                viewModel.addPoint()
                                viewModel.drop()
                            }
                    )
                }
            }
        }

        ProcessBar(
            icon = Icons.Default.Person,
            numberTotal = viewModel.drops.size,
            numberFull = viewModel.currentPoints
        )


    }
}

// https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/package-summary#(androidx.compose.ui.Modifier).onPlaced(kotlin.Function1)
fun Modifier.animatePlacement(): Modifier = composed {
    val scope = rememberCoroutineScope()
    var targetOffset by remember { mutableStateOf(IntOffset.Zero) }
    var animatable by remember {
        mutableStateOf<Animatable<IntOffset, AnimationVector2D>?>(null)
    }
    this
        .onPlaced {
            targetOffset = it
                .positionInParent()
                .round()
        }
        .offset {
            val anim = animatable ?: Animatable(targetOffset, IntOffset.VectorConverter)
                .also {
                    animatable = it
                }
            if (anim.targetValue != targetOffset) {
                scope.launch {
                    anim.animateTo(targetOffset, spring(stiffness = Spring.StiffnessMediumLow))
                }
            }
            animatable?.let { it.value - targetOffset } ?: IntOffset.Zero
        }
}

@Preview(showBackground = true)
@Composable
fun BearScreenPreview() {
    BearScreen(
        onClose = {},
        onDone = {}
    )
}