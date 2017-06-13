# android-case-study

Comments on android-case-study:

* MVP architecture is used for both screens- I made a "very minimal" MVP framework in the MVP package
* Using Dagger2 for Dependency Injection
* Using Butterknife for View Injection
* Accesses remote service using Retrofit
* The ListView was changed to a RecyclerView for better performance - also makes it easier to implement Grid view layout.
* Using Picasso for image loading.  This acts a little strangly with the test data since every URL is the same they end up being cached and reused.  In the adapter I do an invalidate which gives a little image variety but not much.  This would not be a problem with actual data.

Additional Features Added:

* Created wide (820dp) layout for tablets in landscape.  When wider than 820dp the detail fragment is shown on the same screen.  Otherwise a new DealItemDetailActivity is created to hold the fragement.
* Added transition animations when going to/from detail activity.
* Created options menu item to toggle from list to grid view.  

I was having a configuration issue with Junit so was not able to create any unit test at this time.
