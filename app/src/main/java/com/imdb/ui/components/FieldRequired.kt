package com.imdb.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.imdb.R
import com.imdb.ui.theme.redFFF60000

@Composable
fun FieldRequired(field:Int){
    Text(
        text = stringResource(R.string.field_required, stringResource(field)),
        modifier = Modifier
            .fillMaxWidth(),
        style = MaterialTheme.typography.caption.copy(
            color = redFFF60000,
            fontWeight = FontWeight.SemiBold
        )
    )
}