# Premier league Matches (Carefer-Challange)

## Description

This app is a Premier league app for display matches for future and finished past matches

## Design

<p float="left" align="middle">
  <img src="https://github.com/Elbarody/Carefer-Challange/blob/master/ScreenShots/Screenshot_20221114-012408_Carefer-Challange.jpg" width="32%" />
  <img src="https://github.com/Elbarody/Carefer-Challange/blob/master/ScreenShots/Screenshot_20221114-012417_Carefer-Challange.jpg" width="32%" /> 
  <img src="https://github.com/Elbarody/Carefer-Challange/blob/master/ScreenShots/Screenshot_20221114-012422_Carefer-Challange.jpg" width="32%" />
</p>

## How to build (Very Important)
* First, Checkout the project
* Then open football-data website settings, copy base API url and API_KEY place it in credentials.properties

## UI Notes
* You can favorite item by  click on a favourite icon beside match item on any home screen 
* You can remove item from favorites by click on a favourite icon beside match on favorites screen or home screen

### Architecture
* MVVM
* Repository
* Clean Architecture

#### APIs
* Retrofit
* OkHttp
* Gson

#### Async Tasks
* Coroutines , LiveData 

#### Dependency Injection
* Hilt

### Unit testing
* JUnit
* Mockito-Kotlin
* Note -> I didn't have the needed time to make unit tests for all the application, this is just a sample of unit testing

