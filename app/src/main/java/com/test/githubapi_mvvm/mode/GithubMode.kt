package com.test.githubapi_mvvm.mode

data class GithubUserMode(
    val login:String,
    val id:Int,
    val node_id:String,
    val avatar_url:String,
    val gravatar_id:String,
    val url:String,
    val html_url:String,
    val followers_url:String,
    val following_url:String,
    val gists_url:String,
    val starred_url:String,
    val subscriptions_url:String,
    val organizations_url:String,
    val repos_url:String,
    val events_url:String,
    val received_events_url:String,
    val type:String,
    val site_admin:Boolean
)


data class GithubUserInfoMode(
    val login:String,
    val id:Int,
    val avatar_url:String,
    val blog:String,
    val name:String?,
    val location:String?,
    val bio:String?,
    val site_admin:Boolean
)

/*
* {
    "login": "RobYang203",
    "id": 5327043,
    "node_id": "MDQ6VXNlcjUzMjcwNDM=",
    "avatar_url": "https://avatars3.githubusercontent.com/u/5327043?v=4",
    "gravatar_id": "",
    "url": "https://api.github.com/users/RobYang203",
    "html_url": "https://github.com/RobYang203",
    "followers_url": "https://api.github.com/users/RobYang203/followers",
    "following_url": "https://api.github.com/users/RobYang203/following{/other_user}",
    "gists_url": "https://api.github.com/users/RobYang203/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/RobYang203/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/RobYang203/subscriptions",
    "organizations_url": "https://api.github.com/users/RobYang203/orgs",
    "repos_url": "https://api.github.com/users/RobYang203/repos",
    "events_url": "https://api.github.com/users/RobYang203/events{/privacy}",
    "received_events_url": "https://api.github.com/users/RobYang203/received_events",
    "type": "User",
    "site_admin": false,
    "name": null,
    "company": null,
    "blog": "",
    "location": null,
    "email": null,
    "hireable": null,
    "bio": null,
    "twitter_username": null,
    "public_repos": 20,
    "public_gists": 0,
    "followers": 0,
    "following": 0,
    "created_at": "2013-08-28T04:46:33Z",
    "updated_at": "2020-05-27T19:17:30Z"
}
*
* */