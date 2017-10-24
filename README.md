## Popular Movies stage 1
This is the stage one of a movies application made for an Udacity Course. This app uses the data provided by [The Movie Database](https://www.themoviedb.org).

## Setting it up with your own API KEY
Put your api key in the api/APIClient class:

``` java
public class APIClient implements RequestInterceptor {
    public static  final String API_KEY= "{your API key here}";
    ...
}
```
And you're good to go.

### Libraries
- **[Retrofit](https://github.com/square/retrofit/)** As a Http client.
- **[ButterKnife](https://github.com/JakeWharton/butterknife)** For view binding.
- **[Picasso](https://github.com/square/picasso)** To perform image request.
