# Movie App

This is my first project using Kotlin, integrated with the TMDB (The Movie Database) API. The app fetches and displays movie data, providing a user-friendly interface to explore various movies and their details.

## Features

- Browse popular movies
- Browse upcoming movies
- View detailed information about movies
- Modern UI with Jetpack Compose
- Dependency injection with Dagger Hilt

## Screenshots

<p style="text-align: center;">
  <img src="https://github.com/OTK-98/MovieApp/blob/master/app/src/main/java/com/example/movieapp/screenshots/Popular%20Screen.png?raw=true" alt="Popular Movie Screen" width="300"/>
  <br/>
  <em>Popular Movie Screen</em>
</p>

<p style="text-align: center;">
  <img src="https://github.com/OTK-98/MovieApp/blob/master/app/src/main/java/com/example/movieapp/screenshots/Upcoming%20Screen.png?raw=true" alt="Upcoming Movie Screen" width="300"/>
  <br/>
  <em>Upcoming Movie Screen</em>
</p>

<p style="text-align: center;">
  <img src="https://github.com/OTK-98/MovieApp/blob/master/app/src/main/java/com/example/movieapp/screenshots/Details%20Screen.png?raw=true" alt="Details Movie Screen" width="300"/>
  <br/>
  <em>Details Movie Screen</em>
</p>

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/movieapp.git
    ```
2. Open the project in Android Studio.
3. Build the project:
    ```sh
    ./gradlew build
    ```
4. Run the project on an emulator or a physical device.

## Usage

- Browse the list of popular/upcoming movies on the home screen.
- Click on a movie to view its details.

## Dependencies

- [Kotlin](https://kotlinlang.org/)
- [TMDB API](https://www.themoviedb.org/documentation/api)
- [Retrofit](https://square.github.io/retrofit/) for networking
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for UI
- [Dagger Hilt](https://dagger.dev/hilt/) for dependency injection

## API Key

To use the TMDB API, you need an API key. Follow these steps to get your key:

1. Sign up on [TMDB](https://www.themoviedb.org/).
2. Go to your account settings and navigate to the API section.
3. Create a new API key.

Add your API key to the `local.properties` file in the root directory of your project:
```properties
TMDB_API_KEY=your_api_key_here
