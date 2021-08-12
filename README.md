# GitClosedPull
An app which display all closed pull requests from a particular repo with pagination support. Used open source github api (https://developer.github.com/v3/).


# ScreenShots


<img src = "https://github.com/adarshvris/GitClosedPull/blob/master/app/src/main/java/com/adarsh/gitclosedpullrequest/appdemo/Happy_Flow_State.png" width = 275 /> 
<img src = "https://github.com/adarshvris/GitClosedPull/blob/master/app/src/main/java/com/adarsh/gitclosedpullrequest/appdemo/Initial_Loading_State.png" width = 275 /> 
<img src = "https://github.com/adarshvris/GitClosedPull/blob/master/app/src/main/java/com/adarsh/gitclosedpullrequest/appdemo/Next_Page_Loading_State.png" width = 275 />
<img src = "https://github.com/adarshvris/GitClosedPull/blob/master/app/src/main/java/com/adarsh/gitclosedpullrequest/appdemo/Next_page_Load_Failed.png" width = 275 />
<img src = "https://github.com/adarshvris/GitClosedPull/blob/master/app/src/main/java/com/adarsh/gitclosedpullrequest/appdemo/No_Network_State.png" width = 275 />


# Tech Stack


* __Language__ : Kotlin
* __Architecture__ : MVVM
* __Retrofit__ : For Network calls
* __Coroutines__ : For background operations like fetching network response
* __Paging Library(Jetpack component)__ : For pagination support
* __Glide__ : For image loading
