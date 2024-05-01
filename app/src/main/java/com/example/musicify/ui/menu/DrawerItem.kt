package com.example.musicify.ui.menu

import com.example.musicify.ui.screens.Screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp


@Composable
fun DrawerItem (
    modifier: Modifier = Modifier,
    screen: Screens,
    isSelected: Boolean,
) {
    Box (
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val height by animateDpAsState(
            targetValue = if (isSelected) 50.dp else 26.dp,
            label = ""
        )
        val elevation by animateDpAsState(
            targetValue = if (isSelected) 15.dp else 0.dp,
            label = ""
        )
        val alpha by animateFloatAsState(
            targetValue = if (isSelected) 1f else .5f,
            label = ""
        )
        val iconSize by animateDpAsState(
            targetValue = if (isSelected) 24.dp else 20.dp,
            animationSpec = spring(
                stiffness = Spring.StiffnessLow,
                dampingRatio = Spring.DampingRatioMediumBouncy
            ), label = ""
        )

        Column (
            modifier = Modifier
                .height(height)
                .width(60.dp)
                .shadow(
                    elevation = elevation,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                )
                ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )  {
            FlipIcon (
                modifier = Modifier.size(iconSize)
                                   .alpha(alpha),
                isActive = isSelected,
                activeIcon = screen.activeIcon,
                inactiveIcon = screen.inactiveIcon,
                contentDescription = "Bottom Navigation Icon"
            )

            if (isSelected) {
                Text(
                    text = screen.title,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun FlipIcon(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeIcon: ImageVector,
    inactiveIcon: ImageVector,
    contentDescription: String,
) {
    val rotationY = animateFloatAsState(
        targetValue = if (isActive) 180f else 0f,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        ),
        label = ""
    ).value

    val offsetY = animateDpAsState(
        targetValue = if (isActive) (-6).dp else 0.dp,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    ).value

    Box(
        modifier = modifier.graphicsLayer {
            this.rotationY = rotationY
            translationY = offsetY.toPx()
        },
        contentAlignment = Alignment.Center,
    ) {
        val iconPainter = rememberVectorPainter(
            image = if (rotationY > 90f) activeIcon else inactiveIcon
        )

        Icon(
            painter = iconPainter,
            contentDescription = contentDescription,
        )
    }
}
