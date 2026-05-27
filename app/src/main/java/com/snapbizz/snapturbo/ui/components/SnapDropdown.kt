package com.snapbizz.snapturbo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.snapbizz.snapturbo.R
import com.snapbizz.snapturbo.ui.components.text.SnapText
import com.snapbizz.snapturbo.ui.theme.Dimens.paddingSmall
import com.snapbizz.snapturbo.ui.theme.Dimens.paddingSmallMedium
import com.snapbizz.snapturbo.ui.theme.SnapTextEmphasis
import com.snapbizz.snapturbo.ui.theme.SnapThemeConfig

@Composable
fun <T> SnapDropdown(
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    itemLabel: (T) -> String,

    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,

    label: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    var menuWidth by remember { mutableIntStateOf(0) }

    Column(modifier = modifier.fillMaxWidth()) {

        SnapText(
            text = label,
            emphasis = SnapTextEmphasis.TERTIARY,
            modifier = Modifier.padding(bottom = paddingSmall)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    menuWidth = it.size.width
                }
        ) {

            // Trigger
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        if (isEnabled)
                            SnapThemeConfig.Surface
                        else
                            SnapThemeConfig.Disabled
                    )
                    .border(1.dp, SnapThemeConfig.Border, RoundedCornerShape(4.dp))
                    .clickable(
                        enabled = isEnabled,
                        role = Role.Button
                    ) {
                        onExpandedChange(!expanded)
                    }
                    .padding(horizontal = paddingSmallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Box(modifier = Modifier.weight(1f)) {
                        SnapText(
                            text = selectedItem?.let(itemLabel) ?: "Select",
                            emphasis =
                                if (selectedItem == null)
                                    SnapTextEmphasis.TERTIARY
                                else
                                    SnapTextEmphasis.PRIMARY,
                            maxLines = 1
                        )
                    }

                    SnapIcon(
                        iconResId =
                            if (expanded) R.drawable.baseline_keyboard_arrow_down_24
                            else R.drawable.baseline_keyboard_arrow_down_24,
                        contentDescription = "Dropdown"
                    )
                }
            }

            DropdownMenu(
                expanded = expanded && isEnabled,
                onDismissRequest = { onExpandedChange(false) },
                modifier = Modifier
                    .width(with(LocalDensity.current) { menuWidth.toDp() })
                    .heightIn(max = 250.dp)
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { SnapText(text = itemLabel(item)) },
                        onClick = {
                            onItemSelected(item)
                            onExpandedChange(false)
                        }
                    )
                }
            }
        }
    }
}



/*State (Parent Controls)
var expanded by remember { mutableStateOf(false) }
var selected by remember { mutableStateOf<SnapDropdownItem<String>?>(null) }

Usage
SnapDropdown(
    items = listOf(
        SnapDropdownItem("1", "One"),
        SnapDropdownItem("2", "Two"),
        SnapDropdownItem("3", "Three")
    ),
    selectedItem = selected,
    expanded = expanded,
    onExpandedChange = { expanded = it },
    onItemSelected = { selected = it },
    label = "Numbers"
)*/