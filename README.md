# assignment
Android application to add and search Hotel details

#Folders inside repository
  "TestApp" folder contain the source codes to the application <br>
  "ER" folder contain the ER digram for the scenario<br>
  "DB" Folder contain the test mysql databese of the system<br>
  "APK" Folder contain the installation for a android device<br>
  "Json" folder contain the server side files<br>
  
#How to install

First add the database in to a database host.
Then Put the server side files in to a server host.
Then change the host name user name password according to the hosted location.
Then change the host location inside the Android code.
Then run the project once.
Get the apk file inside the bin folder which was located inside TestApp folder.
Install that file into your android device.

#Run the application

When you run the app you will go to the main page.
There will be 2 buttons which navigate you to "Add Hotel page" and "Search Hotel page".
And there is a third button to exit the application.

If you click the button Add Hotel page you will go a new page.
That include text fields to enter hotel name hotel address and hotel situated city.

After enter the data when you press submit location will take from gps module of the phone.
If gps off will asked to on the gps.
Gps location will send with the above input data to store.

Then there is a back button to go to the main page.

If we click the Search Hotel page while we on menu page it will go to another page.
This page we can search hotels by city name.

when we search sorted list by the hotel name will be display with their other details.
Sorting will be done in the server side files.
and alos back button will navigate back to main page.

#More..

<b>Permissions</b>

There are some permissions needed to add to get internet and GPS location access to te application
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```
<b>Json data</b>

Json Encoding and decoding have to done in the both Android source and also server side

In Android Json encoding
```
String json = "";
				JSONObject jsonObject = new JSONObject();
	            try {
					jsonObject.accumulate("name", nam);
					jsonObject.accumulate("address", adrs);
		            jsonObject.accumulate("city", cty);
		            jsonObject.accumulate("lat", lat);
		            jsonObject.accumulate("lon", lon);
		            
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	 
	            //convert JSONObject to JSON to String
	            json = jsonObject.toString();
```

In Android Json decoding

```
returnString1 = "";
                 
                 //Get json data and convert in to raw data

           JSONArray jArray = new JSONArray(reSult);
                 for(int i=0;i<jArray.length();i++){
                         JSONObject json_data = jArray.getJSONObject(i);
                         Log.i("log_tag",
                        		 "name: "+json_data.getString("name")+
                                 ", address: "+json_data.getString("address")+
                                 ", city: "+json_data.getString("city")+
                                 ", latitude: "+json_data.getString("latitude")+
                                 ", longitude: "+json_data.getString("longitude")
                         );
                         returnString1 +="Name: "+json_data.getString("name")+"\n"+
                        		 		"Address: "+json_data.getString("address")+"\n"+
                                		 "City: "+json_data.getString("city")+"\n"+
                                 		 "Location: "+json_data.getString("latitude")+" / "+json_data.getString("longitude")+"\n\n"
                        		 		;                       
                 }
       
         }
         catch(JSONException e){
                 Log.e("log_tag", "Error parsing data "+e.toString());
         }
```
In Server side Json decoding
```
//decode json array
$hotel_data = json_decode($json);
```
In Server side Json encoding
```
$result=json_encode($output);
```
<b>sorting</b>
Used sql query to sort the result by hotel name
```
$sql = "SELECT * FROM hotel WHERE  city = '$city' ORDER BY name";
```
<b>Sending data getting result Android</b>

Sending the data to server and getting result back. 

response = CustomHttpClient.executeHttpPost(
					   "http://192.168.1.2/json/search.php",postParameters);
					   
Link inside the code needs to be changed according to the server location. (Script files location)
```
ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    			
    		 //post the parameters
    		 postParameters.add(new BasicNameValuePair("json",params[0]));
			 
			 String response = null;
			 
			     try {
			    	 //get the response
					response = CustomHttpClient.executeHttpPost(
					   "http://192.168.1.2/json/search.php",postParameters);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			     
			     //convert resut in to string
			    String result = response.toString();
    		
    		
    		return result;
```
