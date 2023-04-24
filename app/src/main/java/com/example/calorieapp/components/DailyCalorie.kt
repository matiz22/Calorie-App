import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.himanshoe.charty.circle.CircleChart
import com.himanshoe.charty.circle.config.CircleConfig
import com.himanshoe.charty.circle.config.StartPosition
import com.himanshoe.charty.circle.model.CircleData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyWidget(
    fatSum: Double, fatLimit: Double,
    carboSum: Double, carboLimit: Double,
    proteinSum: Double, proteinLimit: Double,
    kcalSum: Double, kcalLimit: Double,
    circleData: List<CircleData>,
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "kcal:")
                Text(text = String.format("%.2f", kcalSum) + "/${kcalLimit}")
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "carbo:")
                Text(text = String.format("%.2f", carboSum) + "/${carboLimit}")
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "protein:")
                Text(text = String.format("%.2f", proteinSum) + "/${proteinLimit}")
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "fat:")
                Text(text = String.format("%.2f", fatSum) + "/${fatLimit}")
            }
        }


        Box(modifier = Modifier.fillMaxSize()) {
            CircleChart(
                config = CircleConfig(startPosition = StartPosition.Bottom, maxValue = 1f),
                circleData = circleData,
                modifier = Modifier
                    .width(700.dp)
                    .height(700.dp)

            )
        }
    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DailyWidgetPreview() {


}