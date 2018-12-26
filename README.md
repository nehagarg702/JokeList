#JokeList

This app  have two screens:
1. Show the users categories fetched by this API:
http://api.icndb.com/categories
Each of these Categories are represented as a Fragment in a Viewpager.

2. On Clicking a button a Detail Screen should be loading where the data is loaded from this API :
http://api.icndb.com/jokes/random/10?limitTo=[CATEGORY_NAME]
the CATEGORY_NAME is here is the one user selected in the previous screen.Based on the results of the API, list of the items (Jokes returned by the API) are showed in recycleview

This app uses the concept of Android Architecture component(LiveData and View Model).
