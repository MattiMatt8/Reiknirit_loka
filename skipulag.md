
### Train Booking System

* #### Station:

name | latitude | longitude | open hours | trains list
---- | --------- | -------- | ---------- | ------
string | double   |  double | hash map | ArrayList Train
 
* #### Train: 

TrainID | Route list | type | Standard Seating | First Class Seating 
-- | -- | ---- | -------- | --
integer | ArrayList Route | string | integer | integer

* #### Route: 

Route ID | from | fromPlatform | leaves | to | toPlatform | arrives
---- | -------- | --- | ----  | -- | --  
integer | station | integer | time long | station | integer | long time  

