
### Train Booking System

* #### TrainStation:

longitude | latitude | name | address | open hours | trains
--------- | -------- | ---- | ------- | ---------- | ------
 double   |  double  | string | class | hash map | list class ->

* #### Address: 

country | city | zipcode | address
------- | ---- | ------- | --------
 string | string | integer | string
 
* #### Train: 

TrainID | destinations/route and at what time | type | capacity 
-- | -- | ---- | -------- 
integer | class | string | integer  

* #### Route: 

name | stations(right arrangement and when train comes and leaves) | length(how long average)
---- | ----------------------------------------------------------- | ------------------------
string |                   Destination   			               |       double

* #### Destination: 

TrainStation | comes | leaves
------------ | ----- | -------
TrainStation | long integer | long integer
