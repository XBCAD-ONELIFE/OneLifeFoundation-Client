package com.example.onelifefoundation_client.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.example.onelifefoundation_client.repository.Project
import com.example.onelifefoundation_client.repository.ProjectDataSourceRepository


@OptIn(ExperimentalPagerApi::class)
@ExperimentalCoilApi
@Composable
fun Carousel(
    modifier: Modifier = Modifier,
    images: List<String>,
    onClick: () -> Unit,
) {
    // Create a pager state with the initial page set to 0
    val pagerState = rememberPagerState(initialPage = 0)
    // Create a horizontal pager with the given page count and state
    HorizontalPager(
        count = images.size,
        state = pagerState,
        modifier = modifier
    ) { page ->
        Card(
            modifier = Modifier.fillMaxWidth().height(250.dp).clickable {onClick.invoke() },
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            AsyncImage(
                model = images[page],
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(1f).size(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@ExperimentalCoilApi
@Composable
fun CarouselWithText(
    modifier: Modifier = Modifier,
    images: List<String>,
    onClick: () -> Unit,
    buttonText: String,
    text: String
) {
    // Create a pager state with the initial page set to 0
    val pagerState = rememberPagerState(initialPage = 0)
    // Create a horizontal pager with the given page count and state
    HorizontalPager(
        count = images.size,
        state = pagerState,
        modifier = modifier
    ) { page ->
        Card(
            modifier = Modifier.fillMaxWidth().clickable {  },
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            AsyncImage(
                model =images[page],
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(1f).size(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(text = text, fontSize = 14.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Button(onClick = { onClick.invoke() },
                    modifier=Modifier.align(Alignment.End).padding(8.dp)) {
                    Text(text = buttonText)
                }
            }

        }
    }
}

@Composable
fun ImageCardList(images:List<String>){
    LazyColumn(modifier=Modifier.fillMaxWidth(1f)){
        items(images.size){image ->
            ImageCard(image = images[image])
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

@Composable
fun ImageCard(image:String){
    Column{
        AsyncImage(
            model=image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(1f).height(200.dp),
            contentScale = ContentScale.Crop
        )
    }
}
@Composable
fun ImageWithText(image:String, text:String,
                  buttonText:String, onClick : () -> Unit,
){
    Card(
        modifier = Modifier.fillMaxWidth().clickable {  },
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        AsyncImage(
            model=image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(1f).size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Text(text = text, fontSize = 14.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Button(onClick = { onClick.invoke() },
                modifier=Modifier.align(Alignment.End).padding(8.dp)) {
                Text(text = buttonText)
            }
        }
    }
}

@Composable
fun ProjectViewList(
    projects: List<Project>,
    onDonateClick: (Project) -> Unit,
    onJoinClick: (Project) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(12.dp)
    ) {
        items(projects.size) { project ->
            ProjectView(
                project = projects[project],
                onDonateClick = onDonateClick,
                onJoinClick = onJoinClick
            )
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}

@Composable
fun ProjectView(
    project: Project,
    onDonateClick: (Project) -> Unit,
    onJoinClick: (Project) -> Unit
) {
    // Create a mutable state to track whether the card is clicked
    var isCardClicked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isCardClicked = !isCardClicked }, // Toggle the click state
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        AsyncImage(
            model = project.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(1f)
                .size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column(modifier = Modifier.padding(4.dp)) {
            Text(text = project.projectName, fontSize = 22.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = project.projectLeader, fontSize = 18.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = project.projectLocation, fontSize = 18.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = project.projectDescription,
                fontSize = 16.sp,
                maxLines = if (isCardClicked) Int.MAX_VALUE else 4, // Show all text when card is clicked
                overflow = TextOverflow.Ellipsis
            )
        }

        // Show buttons only when the card is clicked
        if (isCardClicked) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onDonateClick(project) },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(text = "Donate")
                }

                Button(
                    onClick = { onJoinClick(project) },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(text = "Join")
                }
            }
        }
    }
}

