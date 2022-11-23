Why does the actual response look different from the example one?
```
The reason I decided to go with new structure is to standardize the response, so that our consumers 
can be easily to handle all the exceptions and they easily know what the problem occurs if any and 
can take some immediate  actions right away depending on the response. 
```

What if I have more time?
```
I would like to implement the cache using Redis as user info shouldn't change often and we also can
store the data from Github to database, so that we can re-use in the future.  
```


To run API locally:

```
git clone https://github.com/sontran791/customer-integration.git
./mvnw clean install
./mvnw spring-boot:run

our endpoint will be http://localhost:8080/user/getAllUserRepos/{username}
```

A successful response

```
{
  "http_status_code": 200,
  "http_status": "OK",
  "reason": "ok",
  "message": "USER FOUND!",
  "time_stamp": "22-23-2022 05:22:16",
  "user": {
    "user_name": "octocat",
    "display_name": "The Octocat",
    "avatar": "https://avatars.githubusercontent.com/u/583231?v=4",
    "geo_location": "San Francisco",
    "email": null,
    "url": "https://github.com/octocat",
    "created_at": "2011-01-25 18:44:36",
    "repos": [
      {
        "name": "boysenberry-repo-1",
        "url": "https://github.com/octocat/boysenberry-repo-1"
      },
      {
        "name": "git-consortium",
        "url": "https://github.com/octocat/git-consortium"
      },
      {
        "name": "hello-worId",
        "url": "https://github.com/octocat/hello-worId"
      },
      {
        "name": "Hello-World",
        "url": "https://github.com/octocat/Hello-World"
      },
      {
        "name": "linguist",
        "url": "https://github.com/octocat/linguist"
      },
      {
        "name": "octocat.github.io",
        "url": "https://github.com/octocat/octocat.github.io"
      },
      {
        "name": "Spoon-Knife",
        "url": "https://github.com/octocat/Spoon-Knife"
      },
      {
        "name": "test-repo1",
        "url": "https://github.com/octocat/test-repo1"
      }
    ]
  }
}
```

If no user exists

```
{
  "http_status_code": 404,
  "http_status": "NOT_FOUND",
  "reason": "NOT FOUND",
  "message": "No user found",
  "time_stamp": "28-23-2022 05:28:23",
  "user": {}
}
```
