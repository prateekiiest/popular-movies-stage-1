# popular-movies-stage-1

A simple movies app Udacity project made while learning android on [Udacity](https://in.udacity.com/course/android-developer-nanodegree-by-google--nd801-inba1).

The app uses [MovieDB API](https://www.themoviedb.org/documentation/api) API to fetch the details of movies.

## Installation

1. Clone the project.
2. In Android Studio, chose **File > Import Project** and select the root folder of the project.
   Android Studio may ask you to choose the type of project you are importing. If this is the case, make sure to choose **Import project from external model** and select the **Gradle** option.

## Adding API MovieDB API key
Put yout api key in api/APIClient.

```java
public class APIClient implements RequestInterceptor {
    public static  final String API_KEY="your api key";
...
```
