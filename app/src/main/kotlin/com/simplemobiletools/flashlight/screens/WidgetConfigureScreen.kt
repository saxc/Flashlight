package com.simplemobiletools.flashlight.screens

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.simplemobiletools.commons.compose.extensions.MyDevices
import com.simplemobiletools.commons.compose.theme.AppThemeSurface
import com.simplemobiletools.commons.compose.theme.Dimens
import com.simplemobiletools.commons.extensions.adjustAlpha
import com.simplemobiletools.flashlight.R
import com.simplemobiletools.flashlight.helpers.AppDimensions

@Composable
internal fun WidgetConfigureScreen(
    @DrawableRes
    widgetDrawable: Int,
    @ColorInt
    widgetColor: Int,
    widgetAlpha: Float,
    onSliderChanged: (Float) -> Unit,
    onColorPressed: () -> Unit,
    onSavePressed: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Box(
            modifier = Modifier
                .padding(Dimens.margin.activity)
                .padding(bottom = Dimens.margin.activity)
                .fillMaxWidth()
                .weight(1f)
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(AppDimensions.mainButtonSize),
                painter = painterResource(id = widgetDrawable),
                contentDescription = stringResource(id = R.string.bright_display),
                tint = Color(widgetColor.adjustAlpha(widgetAlpha))
            )
        }

        Row {
            Icon(
                modifier = Modifier
                    .size(AppDimensions.widgetColorPickerSize)
                    .padding(Dimens.margin.tiny)
                    .clip(CircleShape)
                    .clickable { onColorPressed() },
                painter = BrushPainter(SolidColor(Color(widgetColor))),
                contentDescription = stringResource(id = R.string.bright_display),
                tint = Color(widgetColor)
            )

            Slider(
                value = widgetAlpha,
                onValueChange = onSliderChanged,
                modifier = Modifier
                    .padding(start = Dimens.margin.medium)
                    .background(
                        color = colorResource(id = R.color.md_grey_white),
                        shape = MaterialTheme.shapes.extraLarge
                    )
                    .padding(horizontal = Dimens.margin.activity)
            )
        }

        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = onSavePressed
        ) {
            Text(text = stringResource(id = R.string.ok))
        }
    }
}

@Composable
@MyDevices
private fun WidgetBrightDisplayConfigureScreenPreview() {
    AppThemeSurface {
        WidgetConfigureScreen(
            widgetDrawable = R.drawable.ic_bright_display_vector,
            widgetColor = MaterialTheme.colorScheme.primary.toArgb(),
            widgetAlpha = 1f,
            onSliderChanged = {},
            onColorPressed = {},
            onSavePressed = {}
        )
    }
}
