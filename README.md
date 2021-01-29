[![Kotlin Version](https://img.shields.io/badge/kotlin-1.4.21-blue.svg)](http://kotlinlang.org/)
[![AGP](https://img.shields.io/badge/AGP-4.1.0-blue)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-6.5-blue)](https://gradle.org)

# Book Finder

A simple android application that consumes data from [Google Books API](https://developers.google.com/books/docs/v1/using) to display a list of books. It was developed during a 2-hour challenge.

## Outline

- App Walk-through
- Architecture
- Libraries
- Next Steps

### App Walk-through

The presents a screen for the user to search for books. Once a query is entered in the text box, a call is made to [Google Book API](https://developers.google.com/books/docs/v1/using). The results are presented as a list to the user once it completes.

<h4 align="center">
<img alt="Light mode screenshot" src="https://user-images.githubusercontent.com/25648077/106270909-f72ef700-622e-11eb-9d5f-fc1fef2c47d9.gif" width="35%" vspace="10" hspace="10">
Light mode
<img alt="Dark mode screenshot" src="https://user-images.githubusercontent.com/25648077/106271194-6ad10400-622f-11eb-9b21-3c37263afdc2.gif" width="35%" vspace="10" hspace="10">
 Dark mode
<br>


### Architecture

The app follows the clean architecture concept in the most minimal way appropriate for the current state of the app. There are three layers in the app. The data layer, the domain and the presentation.
On the data layer, an API request is done with the [Retrofit](http://square.github.io/retrofit) library. Models are defined for the expected response. A mapper class is present to convert the response to the domain layer model.
The domain layer contains the model class and the use case for searching for books. It defines a state class to notify the presentation layer of the loading, success and error states. In the app, asynchronous operations are carried out with coroutines. The domain layer defines a class to abstract the different dispatchers.
The presentation layer is implemented with `MVVM` using `ViewModel` and `LiveData`. A view state data class is defined. The fragment observes the view state which is wrapped in a `LiveData`. The view is a `Fragment`. Text change events are passed to the `ViewModel`, which then executes the use case. A reducer, which receives the old state and the results (error, failure, or loading) creates a copy of the new state. This new state is posted to the view.

### Libraries Used

- [Material Components](https://github.com/material-components/material-components-android/) - comes with ready made material UI view components
- [Constraint Layout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout) - it enables creation of layouts flat view hierarchies
- [Retrofit](http://square.github.io/retrofit) for REST api communication, it is actively developed and stable
- [Moshi](https://github.com/square/moshi) for JSON serialisation and deserialisation, it's lightweight, stable and works well with Retrofit
- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - for  asynchronous concurrency because it's easy to use and comes out of the box with kotlin
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - to manage UI data in a lifecycle conscious way, surviving configuration change
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - to hold and observe UI state in a lifecycle aware way in the app
- [Navigation Architecture Component](https://developer.android.com/guide/navigation/navigation-getting-started) - for easy navigation, especially with fragments no need to interact with the fragment manager
- [Glide](https://github.com/bumptech/glide) - for image loading and caching with smooth scrolling. It is actively developed and stable
- [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - to interact easily with views from XML through an auto-generated binding class
- [LeakCanary](https://square.github.io/leakcanary/getting_started/) - to detecting memory leaks in development

### Next Steps

- Write tests
- Add dependency injection (maybe with Dagger/Hilt)
- Setup static code analysis (detekt and ktlint)
- Setup Continuous Integration
- UI/UX Improvements
    - Better error handling
    - Add retry functionality
    - Add detail screen
    - UI responsiveness