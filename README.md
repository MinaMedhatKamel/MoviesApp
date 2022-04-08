# MoviesApp
this is a task for nuegelb company 

### Usecases
- loading the configuration Api at the splash screen from the cache and if it isn't exist, we load it from the network. [https://api.themoviedb.org/3/configuration]
- save the configuration model using the SharedPreferences to access it through the app modules.
- navigate to the list screen. fetching the movies with paging technices.
<img width="200" alt="image" src="https://user-images.githubusercontent.com/10800558/162219257-829c5add-3c9b-4344-93fc-2ef0f8f4defe.png">

- on click on the item in the list screen. it should navigate to the detials screen.
<img width="200" alt="image" src="https://user-images.githubusercontent.com/10800558/162219944-94aacb39-b516-4ae3-8acc-af64abe7f856.png">

### Additonal usecases:
- add search edit text to filter the list using the query.
<img width="200" alt="image" src="https://user-images.githubusercontent.com/10800558/162224220-e2e38c6a-f364-47c0-9670-7819aa15f238.gif">
- add autocomplete methodolgy based on the current list items name.
- when the user type space the app takes the current query search to search for it in the names of the movie list. 
<img width="200" alt="image" src="https://user-images.githubusercontent.com/10800558/162229145-f93ae37e-407b-4855-a1c4-a62d22a98339.gif">

### architecture:
- mvvm + clean architecture.

### Technologies:
* Kotlin + Coroutines
* koin for DJ
* Retrofit2 & Gson
* with SharedPreferences
* Pagging 3
* Material Design
* View Binding
* Android Jetpack's Navigation
* junit with mockk for testing.



