package com.imtiaz.rowcolumn.demoTry.ux

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Surface

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imtiaz.rowcolumn.demoTry.model.BrandWiseData
import com.imtiaz.rowcolumn.demoTry.model.Table
import com.imtiaz.rowcolumn.demoTry.repo.RouteSearchHttpRepository


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppView() {
    val context: Context = LocalContext.current

    @Composable
    fun HeaderCell(text: String) {
        if (text.isNullOrBlank()) {
            Surface {}
            return
        }
        Surface(
            modifier = Modifier
                .background(
                    color = Color(0xff0062FF),
                )
                .border(width = .5.dp, color = Color(0xffE7E7E7)), shadowElevation = 10.dp,
            color = Color(0xff0062FF)

        ) {
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)


            )
        }
    }

    @Composable
    fun ContentCell(row: Int, text: String, alignment: Alignment = Alignment.Center) {
        Box(modifier = Modifier
            .background(
                color = if (row.toInt() % 2 == 0) Color(0xffEEEFEF) else Color.White,
                shape = RoundedCornerShape(corner = CornerSize(0.dp))
            )
            .border(width = .5.dp, color = Color(0xffE7E7E7))
            .clickable {
                // do something wonderful
            }
        ) {
            Log.d("row number", row.toString())
            Text(
                text = text,
                modifier = Modifier
                    .padding(10.dp)
                    .align(alignment)
            )
        }
    }

    val result: List<BrandWiseData> by remember {
        mutableStateOf(RouteSearchHttpRepository(context).searchRoutes())
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally/*, modifier = Modifier.padding(top = 200.dp)*/) {
        Text(
            text = "Search Result",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )

        //Spacer(modifier = Modifier.height(200.dp))

        val headers = listOf(
            "Brand",
            "Budget",
            "TP Sale",
            "Ach %",
            "Net Ach %",
        )
        Surface(
            shadowElevation = 8.dp,
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
            tonalElevation = 4.dp

        ) {
            Table(
                modifier = Modifier
                    .fillMaxSize(),
                columnCount = headers.size,
                rowCount = result.size + 1,
                stickyRowCount = 1,
                stickyColumnCount = 1,
                maxCellWidthDp = 320.dp
            ) { rowIndex, columnIndex ->
                val header = headers[columnIndex]
                if (rowIndex == 0) {
                    HeaderCell(header)
                } else {
                    val r = result[rowIndex - 1]
                    when (header) {

                        "Brand" -> ContentCell(rowIndex, r.brand)
                        "Budget" -> ContentCell(rowIndex, r.budget)
                        "TP Sale" -> ContentCell(rowIndex, r.tpSale)
                        "Ach %" -> ContentCell(rowIndex, r.ach)
                        "Net Ach %" -> ContentCell(rowIndex, r.netAch)

                    }
                }
            }
        }

    }
}

